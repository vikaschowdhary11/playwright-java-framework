package example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Actions {

    public static void main(String args[]){
        Playwright playwright=Playwright.create();
        Browser browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
        Page page=browser.newPage();
        page.navigate("C:\\Users\\Vikas\\IdeaProjects\\Playwright\\locator.html");
//        getByRole
//        Locator locator=page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Sign in"));
//        locator.hover();
//        locator.click();

        //getByRole
        assertThat(page.getByRole(AriaRole.HEADING,new Page.GetByRoleOptions().setName("Sign up"))).isVisible();
        page.getByRole(AriaRole.CHECKBOX,new Page.GetByRoleOptions().setName("Subscribe")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(Pattern.compile("submit",Pattern.CASE_INSENSITIVE)));
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(Pattern.compile("submit", Pattern.CASE_INSENSITIVE))).click();

        page.getByLabel("Password ").fill("secret");
        page.getByPlaceholder("name@example.com");
        page.getByPlaceholder("name@example.com").fill("playwright@microsoft.com");
        assertThat(page.getByText("Welcome, John")).isVisible();
        assertThat(page.getByText("Welcome, John",new Page.GetByTextOptions().setExact(true))).isVisible();
        assertThat(page
                .getByText(Pattern.compile("welcome, john$", Pattern.CASE_INSENSITIVE)))
                .isVisible();

        page.getByAltText("playwright logo").click();

        assertThat(page.getByTitle("Issues count")).hasText("25 issues");
        page.getByTestId("directions").click();

        playwright.selectors().setTestIdAttribute("data-pw");
        page.getByTestId("directions").click();

        page.getByText("Details").click();
        page.locator("x-details",new Page.LocatorOptions().setHasText("Details")).click();
        assertThat(page.locator("x-details")).containsText("Details");

        page.getByRole(AriaRole.LISTITEM)
                .filter(new Locator.FilterOptions().setHasText("Product 2"))
                .getByRole(AriaRole.BUTTON,
                        new Locator.GetByRoleOptions().setName("Add to cart"))
                .click();
//        assertThat(page.getByRole(AriaRole.LISTITEM)
//                .filter(new Locator.FilterOptions().setHasNotText("Out of stock")))
//                .hasCount(12);
        System.out.println("Count of element is "+12);

//        page.getByRole(AriaRole.LISTITEM)
//                        .filter(new Locator.FilterOptions().setHas(page.getByRole(AriaRole.HEADING,new Page.GetByRoleOptions().setName("Product 2"))))
//                                .getByRole(AriaRole.BUTTON,new Locator.GetByRoleOptions().setName("Add to cart")).click();
//        assertThat(page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions()
//                .setHas(page.getByRole(AriaRole.HEADING,new Page.GetByRoleOptions().setName("Product 2"))))).hasCount(1);

        System.out.println("Count of element is "+1);

//        assertThat(page
//                .getByRole(AriaRole.LISTITEM)
//                .filter(new Locator.FilterOptions().setHasNot(page.getByText("Product 2"))))
//                .hasCount(11);
//        System.out.println("Count of element is "+11);

        Locator product = page
                .getByRole(AriaRole.LISTITEM)
                .filter(new Locator.FilterOptions().setHasText("Product 2"));

        product
                .getByRole(AriaRole.BUTTON,
                        new Locator.GetByRoleOptions().setName("Add to cart"))
                .click();
        System.out.println("Matching inside a locator is passed");

        Locator button = page.getByRole(AriaRole.BUTTON).and(page.getByTitle("Subscribe"));
        System.out.println("Matching two locators simultaneously");

//        Locator newEmail=page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("New"));
//        Locator dailog=page.getByText("Confirm security settings");
//        assertThat(newEmail.or(dailog)).isVisible();
//        if(dailog.isVisible()){
//            page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Dismiss")).click();
//        }
//        newEmail.click();
//
//        System.out.println("Matching OR elements");

        assertThat(page.getByRole(AriaRole.LISTITEM)).hasText(new String[] { "apple", "banana", "orange" });
        System.out.println("apple, banana, orange");




        page.close();
        browser.close();
        playwright.close();
    }
}
