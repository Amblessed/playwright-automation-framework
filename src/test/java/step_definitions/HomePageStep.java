package step_definitions;



/*
 * @Project Name: playwright-automation-framework
 * @Author: Okechukwu Bright Onwumere
 * @Created: 12-Jan-25
 */


import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.HomePage;


public class HomePageStep {

    private final HomePage homePage;

    public HomePageStep(HomePage homePage) {
        this.homePage = homePage;
    }

    @Given("I navigate to the webdriveruniversity homepage")
    public void i_navigate_to_the_webdriveruniversity_homepage() {
        homePage.navigateToHomePage();
    }

    @When("I click on the contact us button")
    public void i_click_on_the_contact_us_button() {
        homePage.clickOnContactUs();
    }

    @When("I click on the login portal button")
    public void i_click_on_the_login_portal_button() {
       homePage.clickOnLoginPortal();
    }
}
