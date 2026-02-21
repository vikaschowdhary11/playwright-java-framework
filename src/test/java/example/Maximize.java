package example;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.util.Arrays;

public class Maximize {
    @Test
    public void maximize(){
        Playwright playwright=Playwright.create();
        //method 1
        //Browser browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        //BrowserContext browserContext=browser.newContext(new Browser.NewContextOptions().setViewportSize(1920,1080));

        //method 2
        //Browser browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500).setArgs(Arrays.asList("--start-maximized")));
        //BrowserContext browserContext=browser.newContext(new Browser.NewContextOptions().setViewportSize(null));

        //method 3
        Browser browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(10000));
        BrowserContext browserContext=browser.newContext();
        Page page=browserContext.newPage();
        page.setViewportSize(1980,1080);
        page.navigate("https://google.co.in");

        page.close();
        browserContext.close();
        browser.close();
        playwright.close();;

    }
}
