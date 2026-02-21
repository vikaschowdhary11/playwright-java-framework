package example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PopupHandlingExample {
    public static void main(String args[]){
        try (Playwright playwright = Playwright.create()){
            Browser browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(3000));
            Page page=browser.newPage();
            page.navigate("https://the-internet.herokuapp.com/windows");

           // Setup a "waitForPopup" that listens for the popup event
            Page popup=page.waitForPopup(()->{
                // 2. Perform the action that triggers the popup (like a click)
                page.getByText("Click Here").click();
            });
            System.out.println("Main Page Title: " + page.title());
            System.out.println("Popup Page Title: " + popup.title());

            popup.close();

            if(page.isVisible("text=Click Here"))
                System.out.println("Successfully returned to main page.");
            browser.close();
        }
    }
}
