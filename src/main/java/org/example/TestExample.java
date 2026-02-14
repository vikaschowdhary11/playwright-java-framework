package org.example;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestExample {
    // 1. Heavy infrastructure must be static and shared safely
    private static Playwright playwright;
    private static Browser browser;
    private static final Object lock = new Object();

    // 2. Test-specific data must be ThreadLocal
    private static ThreadLocal<BrowserContext> threadContext = new ThreadLocal<>();
    private static ThreadLocal<Page> threadPage = new ThreadLocal<>();

    @BeforeAll
    static void launchBrowser() {
        synchronized (lock) {
            if (playwright == null) {
                playwright = Playwright.create();
                // Tweak: Start HEADLESS first to ensure the pipe is stable
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                        .setHeadless(false).setSlowMo(100));
            }
        }
    }
    @AfterAll
    static void stop() {
        synchronized (lock) {
            if (browser != null) browser.close();
            if (playwright != null) playwright.close();
        }
    }
    @BeforeEach
    void setup() {
        // Double-check initialization for parallel threads
        if (browser == null) launchBrowser();

        // Create a unique context and page for THIS specific thread
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        threadContext.set(context);
        threadPage.set(page);
    }
    @AfterEach
    void tearDown() {
        if (threadContext.get() != null) {
            threadContext.get().close();
        }
        threadContext.remove();
        threadPage.remove();
    }

    @Test
    void shouldClickButton() {
        Page page = threadPage.get();
        page.setContent("<button id='btn' onclick='window.clicked=true'>Go</button>");
        page.click("#btn");
        assertTrue((Boolean) page.evaluate("window.clicked"));
    }
    @Test
    void shouldCheckTheBox() {
        Page page = threadPage.get();
        page.setContent("<input id='checkbox' type='checkbox'></input>");
        page.locator("input").check();
        assertTrue((Boolean) page.evaluate("() => window['checkbox'].checked"));
    }
    @Test
    void shouldSearchWiki() {
        Page page = threadPage.get();
        page.navigate("https://www.wikipedia.org/");
        page.locator("//input[@name='search']").fill("Playwright");
        page.press("//input[@name='search']", "Enter");
        assertTrue(page.url().contains("Playwright"));
    }



}