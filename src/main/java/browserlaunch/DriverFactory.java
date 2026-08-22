package browserlaunch;

import com.microsoft.playwright.*;

public class DriverFactory {
    public static Page getPage() {
        Playwright playwright = null;
        Browser browser = null;
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        return browser.newPage();
    }
//    public static void closeBrowser() {
//        if (browser != null) browser.close();
//        if (playwright != null) playwright.close();
//    }
}
