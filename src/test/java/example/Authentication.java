package example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.regex.Pattern;

public class Authentication {
    @Test
    public void authentication(){
        try(Playwright playwright=Playwright.create()){
            Browser browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
            BrowserContext context=browser.newContext();
            Page page = context.newPage();
            page.navigate("https://github.com/login");
            page.getByLabel("Username or email address").fill("vikaschowdhary11@gmail.com");
            page.getByLabel("Password").fill("Best@12345");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(Pattern.compile("^Sign in$"))).click();
            context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("state.json")));
        }
    }
    @Test
    public void authenticationUsingAlreadySavingState(){
        try(Playwright playwright=Playwright.create()){
            Browser browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
            BrowserContext context = browser.newContext(
                    new Browser.NewContextOptions().setStorageStatePath(Paths.get("state.json")));
            Page page = context.newPage();
            page.navigate("https://github.com/login");
        }
    }
}
