package pages;



/*
 * @Project Name: playwright-automation-framework
 * @Author: Okechukwu Bright Onwumere
 * @Created: 15-Jan-25
 */


import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import pages.base.BasePage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class ContactUsPage extends BasePage {

    Page.WaitForSelectorOptions options;

    public ContactUsPage(BrowserManager browserManager) {
        super(browserManager);
        options = new Page.WaitForSelectorOptions().setTimeout(10000);
    }

    public void typeFirstName(String firstName){
        fillFieldByPlaceholder("First Name", firstName);
    }

    public void typeLastName(String lastName){
        fillFieldByPlaceholder("Last Name", lastName);
    }

    public void enterEmailAddress(String emailAddress){
        fillFieldByPlaceholder("Email Address", emailAddress);
    }

    public void typeComment(String comment){
        fillFieldByPlaceholder("Comments", comment);
    }

    public void clickOnSubmitButton(){
        clickBySelector("input[type='submit']");
    }

    public void verifySuccessfulSubmission(){
        String expectedText = "Thank You for your Message!";
        String locatorElement = "#contact_reply h1";
        getBrowserManager().getPage().waitForSelector(locatorElement, options);
        Locator locator = getBrowserManager().getPage().locator(locatorElement);
        String text = getBrowserManager().getPage().textContent(locatorElement);
        assert text.equals(expectedText);
        assertThat(locator).isVisible();
        assertThat(locator).containsText(expectedText);
    }

    public void verifyUnsuccessfulSubmission(){
        String locatorElement = "body";
        getBrowserManager().getPage().waitForSelector(locatorElement, options);
        Locator bodyElement = getBrowserManager().getPage().locator(locatorElement);
        String bodyText = bodyElement.textContent();
        //Assert that the body text matches the expected pattern
        Pattern pattern = Pattern.compile("Error: (all fields are required|Invalid email address)");
        Matcher matcher = pattern.matcher(bodyText);
        assertTrue(matcher.find(), "The body text does not match the expected error message.");
    }

    public void verifyHeaderText(String exceptedMessage){
        String locatorElement = "//h1 | //body";
        getBrowserManager().getPage().waitForSelector(locatorElement, options);
        List<String> texts = getBrowserManager().getPage().locator(locatorElement).allInnerTexts();
        String foundText = "";

        // Variable to store the found text
        boolean found = false;
        for(String text : texts){
            if(text.strip().equals(exceptedMessage)){
                foundText = text.strip();
                found = true;
                break;
            }
            else{
                foundText = text.strip();
            }
        }

        // Perform an assertion
        assertTrue(found, "The excepted message was not found in the page: " + exceptedMessage + ". Message found was: " + foundText);

    }
}
