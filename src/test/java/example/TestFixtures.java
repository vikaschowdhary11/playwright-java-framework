package example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestFixtures {
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;
    @BeforeAll
    void launchBrowser(){
        playwright=Playwright.create();
        browser=playwright.chromium().launch();
    }
    @AfterAll
    void closeBrowser(){
        playwright.close();
    }
    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }
}
