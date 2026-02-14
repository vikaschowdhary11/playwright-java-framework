package example;

import com.microsoft.playwright.*;

import java.nio.file.Paths;


public class Trace {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
            BrowserContext browserContext = browser.newContext();

            //start tracing
            browserContext.tracing().start(
                    new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true)
            );

            Page page = browserContext.newPage();
            page.navigate("https://playwright.dev");

            browserContext.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace.zip")));



        }
    }
}
