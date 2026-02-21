package example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DialogExampleTest {
    @Test
    public void testDialog() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setTimeout(3000));
            Page page = browser.newPage();
            page.navigate("https://the-internet.herokuapp.com/javascript_alerts");
            page.pause();

            page.onDialog(dialog -> {
                System.out.println("Dialog Message: " + dialog.message());
                if ("confirm".equals(dialog.type())) {
                    dialog.accept(); // Clicks 'OK'
                }
            });
            page.getByText("Click for JS Confirm").click();
            assertThat(page.locator("#result")).hasText("You clicked: Ok");
            browser.close();
        }
    }

}
