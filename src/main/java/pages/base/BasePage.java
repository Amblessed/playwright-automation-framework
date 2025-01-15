package pages.base;



/*
 * @Project Name: playwright-automation-framework
 * @Author: Okechukwu Bright Onwumere
 * @Created: 14-Jan-25
 */


import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class BasePage {

    private final BrowserManager browserManager;

    public BasePage(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    // This means that the member can be accessed by classes in the same package or subclasses
    protected BrowserManager getBrowserManager() {
        return browserManager;
    }


    public void navigateToURL(String url) {
        browserManager.getPage().navigate(url);
    }

    public void clickByRole(String role, String name){
        Locator element = browserManager.getPage().getByRole(AriaRole.valueOf(role.toUpperCase()), new Page.GetByRoleOptions().setName(name));
        element.click();
    }

    public void clickBySelector(String selector){
        browserManager.getPage().waitForSelector(selector, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        browserManager.getPage().click(selector);
    }

    public void clickByLocator(Locator locator){
        locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        locator.click();
    }

    public void fillFieldByPlaceholder(String placeholder, String text){
        getBrowserManager().getPage().getByPlaceholder(placeholder).fill(text);
    }
}
