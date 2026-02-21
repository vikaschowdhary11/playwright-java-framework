package example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.KeyboardModifier;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.Arrays;

public class PlaywrightActions {
    @Test
    public void playwrightactions(){
        try(Playwright playwright=Playwright.create()){
            Page page=playwright.chromium().launch(new BrowserType.LaunchOptions().
                    setHeadless(false).setSlowMo(3000)).newPage();

            //page.navigate("https://the-internet.herokuapp.com/login");
            //page.getByRole(AriaRole.TEXTBOX,new Page.GetByRoleOptions().setName("username").setExact(true)).fill("Peter");
            //page.getByLabel("Username",new Page.GetByLabelOptions().setExact(true)).fill("peter");

            //page.navigate("https://www.w3schools.com/howto/howto_css_custom_checkbox.asp");
            //page.navigate("https://the-internet.herokuapp.com/checkboxes");
            //page.locator("#checkboxes").getByRole(AriaRole.CHECKBOX).first().check();

            //page.navigate("https://the-internet.herokuapp.com/dropdown");
            //page.getByRole(AriaRole.COMBOBOX).selectOption("Option 1");
            //page.getByRole(AriaRole.COMBOBOX).selectOption(new SelectOption().setValue("2"));

//            page.navigate("https://the-internet.herokuapp.com/add_remove_elements/");
//            page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Add Element")).click();
//            page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Add Element")).dblclick();
//            page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Add Element")).
//                    click(new Locator.ClickOptions().setModifiers(Arrays.asList(KeyboardModifier.SHIFT)));
//            page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Add Element")).
//                    click(new Locator.ClickOptions().setModifiers(Arrays.asList(KeyboardModifier.CONTROLORMETA)));
//            page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Add Element")).hover();
//            page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Add Element")).
//                    click(new Locator.ClickOptions().setPosition(0,0));


//            page.navigate("https://the-internet.herokuapp.com/forgot_password");
//            page.getByLabel("E-mail").pressSequentially("Hello World");
//            page.getByLabel("E-mail").press("Control+ArrowRight");
//            page.getByLabel("E-mail").press("$");
//            page.getByLabel("E-mail").press("Enter");

//            page.navigate("https://the-internet.herokuapp.com/upload");
//            page.locator("#file-upload").setInputFiles(Paths.get("example.png"));
//            page.locator("#file-submit").click();
            page.navigate("https://the-internet.herokuapp.com/upload");
            FileChooser fileChooser=page.waitForFileChooser(()->{
                page.locator("#file-upload").click();
            });
            fileChooser.setFiles(Paths.get("example.png"));
            page.locator("#file-submit").click();




            page.close();
        }




    }
}
