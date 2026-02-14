package org.example;

import java.util.regex.Pattern;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class App {
    public static void main(String[] args) {
//        Program1
//        try (Playwright playwright = Playwright.create()) {
//            Browser browser = playwright.chromium().launch();
//            Page page = browser.newPage();
//            page.navigate("https://playwright.dev");
//            System.out.println(page.title());
//        }
//        Program2-Screenshot
//        try(Playwright playwright=Playwright.create()){
//            Browser browser=playwright.webkit().launch();
//            Page page=browser.newPage();
//            page.navigate("https://playwright.dev/");
//            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
//        }
        //Program3-Headless
//        try(Playwright playwright=Playwright.create()){
//            Browser browser=playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
//            Page page=browser.newPage();
//            page.navigate("https://playwright.dev/");
//            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
//        }

        //Program4 assertions and locator
        try(Playwright playwright=Playwright.create()){
                Browser browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                Page page=browser.newPage();
                page.navigate("https://playwright.dev");
                assertThat(page).hasTitle(Pattern.compile("Playwright"));
                Locator getStarted=page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Get Started")); //page.locator("text=Get Started");
                assertThat(getStarted).hasAttribute("href","/docs/intro");
                getStarted.click();
                assertThat(page.getByRole(AriaRole.HEADING,new Page.GetByRoleOptions().setName("Installation"))).isAttached(); //assertThat(page.locator("text=Installation")).isVisible();
        }
    }
}