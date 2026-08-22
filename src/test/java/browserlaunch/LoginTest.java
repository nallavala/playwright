package browserlaunch;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    private Page page;

    @BeforeClass
    public void setup() {
        page = DriverFactory.getPage();

    }

    @Test
    public void testLoginForm() {
        page.navigate("https://the-internet.herokuapp.com/login");
        page.locator("#username").fill("tomsmith");
        page.locator("#password").fill("SuperSecretPassword!");
        page.locator("button[type='submit']").click();
        String message = page.locator("#flash").textContent();
        System.out.println("Login message: " + message);
    }
    @Test
    public void testCheckboxes() {
        page.navigate("https://the-internet.herokuapp.com/checkboxes");
        // First checkbox
        Locator checkbox1 = page.locator("xpath=(//input[@type='checkbox'])[1]");
        checkbox1.check();
        Assert.assertTrue(checkbox1.isChecked(), "First checkbox should be checked");

        // Second checkbox
        Locator checkbox2 = page.locator("xpath=(//input[@type='checkbox'])[2]");
        checkbox2.uncheck();
        Assert.assertFalse(checkbox2.isChecked(), "Second checkbox should be unchecked");
    }
    @Test
    public void testDropdownSelection() {
        page.navigate("https://the-internet.herokuapp.com/dropdown");
        Locator dropdown = page.locator("#dropdown");

        // Select by value
        dropdown.selectOption("1");
        Assert.assertEquals(dropdown.inputValue(), "1", "Option 1 should be selected");

        // Select by visible text
        dropdown.selectOption(new SelectOption().setLabel("Option 2"));
        Assert.assertEquals(dropdown.inputValue(), "2", "Option 2 should be selected");
    }

    @Test
    public void testDynamicContent() {
        page.navigate("https://the-internet.herokuapp.com/dynamic_loading/1");
        System.out.print("this is for testing");
        // Click Start button
        page.locator("button").click();

        // Wait until the hidden element appears
        page.waitForSelector("#finish h4");

        // Get the text
        String message = page.locator("#finish h4").textContent();
        System.out.println("Loaded message: " + message);

        // Assertion
        Assert.assertEquals(message.trim(), "Hello World!");
    }

    @AfterClass
    public void teardown() {
       // DriverFactory.closeBrowser();
    }
}
