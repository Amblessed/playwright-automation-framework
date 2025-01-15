package pages;


/*
 * @Project Name: playwright-automation-framework
 * @Author: Okechukwu Bright Onwumere
 * @Created: 15-Jan-25
 */


import browser.BrowserManager;
import pages.base.BasePage;

public class HomePage extends BasePage {

    public HomePage(BrowserManager browserManager) {
        super(browserManager);
    }

    public void navigateToHomePage(){
        navigateToURL("https://www.webdriveruniversity.com/");
    }

    public void clickOnContactUs(){
        getBrowserManager().setPage(getBrowserManager().getBrowserContext().waitForPage(() -> clickByRole("LINK", "CONTACT US Contact Us Form")));
        getBrowserManager().getPage().bringToFront();
    }

    public void clickOnLoginPortal(){
        getBrowserManager().setPage(getBrowserManager().getBrowserContext().waitForPage(() -> clickByRole("LINK", "LOGIN PORTAL Login Portal")));
        getBrowserManager().getPage().bringToFront();
    }
}
