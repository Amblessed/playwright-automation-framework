package pages;



/*
 * @Project Name: playwright-automation-framework
 * @Author: Okechukwu Bright Onwumere
 * @Created: 15-Jan-25
 */


import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.Assert;
import pages.base.BasePage;

public class LoginPage extends BasePage {

    Page.WaitForSelectorOptions options;
    private String alertText;

    public LoginPage(BrowserManager browserManager) {
        super(browserManager);
        options = new Page.WaitForSelectorOptions().setTimeout(10000);
    }

    public void enterUsername(String username){
        fillFieldByPlaceholder("Username", username);
    }

    public void enterPassword(String password){
        fillFieldByPlaceholder("Password", password);
    }

    public void clickLoginButton(){
        getBrowserManager().getPage().waitForSelector("button[type='submit'][id='login-button']", options);
        getBrowserManager().getPage().onceDialog(dialog -> {
            alertText = dialog.message();
            dialog.accept(alertText);
        });
        Locator loginButton = getBrowserManager().getPage().locator("button[type='submit'][id='login-button']");
        loginButton.hover();
        loginButton.click(new Locator.ClickOptions().setForce(true));
    }

    public void verifyAlertText(String exceptedAlertText){
        Assert.assertEquals(alertText, exceptedAlertText, "The alert text does not match the expected text.");
    }
}
