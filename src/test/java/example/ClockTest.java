package example;
import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ClockTest {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setTimezoneId("UTC"));
            Page page = context.newPage();

            // 1. Initialize the Clock BEFORE navigating
            // This allows Playwright to override the browser's native Date/Timer functions
            page.clock().install(new Clock.InstallOptions().setTime(0));

            // 2. Go to a page with a timer
            page.navigate("https://demo.playwright.dev/clock/");

            // 3. Verify the initial state (Timer should show 00:00)
            assertThat(page.locator("#clock")).hasText("0:00:05");

            // 4. Fast-Forward time by 30 seconds
            // This happens INSTANTLY. No actual waiting occurs.
            page.clock().fastForward("00:00:30");

            // 5. Assert that the UI updated as if 30 seconds passed
            assertThat(page.locator("#clock")).hasText(Pattern.compile("^0:00.*"));

            // 6. You can also jump to a specific date/time
            // Useful for testing "Expired" banners or Holiday sales
            page.clock().fastForward("01:00:00"); // Fast forward 1 hour

            System.out.println("Test Passed: Time jumped successfully!");

            browser.close();
        }
    }
}