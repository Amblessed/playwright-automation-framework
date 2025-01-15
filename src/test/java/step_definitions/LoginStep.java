package step_definitions;



/*
 * @Project Name: playwright-automation-framework
 * @Author: Okechukwu Bright Onwumere
 * @Created: 13-Jan-25
 */


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginStep {

    private final LoginPage loginPage;

    public LoginStep(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    @When("I type a username {string}")
    public void i_type_a_username(String username) {
        loginPage.enterUsername(username);
    }

    @When("I type a password {string}")
    public void i_type_a_password(String password) {
        loginPage.enterPassword(password);
    }

    @When("I click the login button")
    public void i_click_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("I should be presented with an alert box which contains text {string}")
    public void i_should_be_presented_with_an_alert_box_which_contains_text(String exceptedAlertText) {
        loginPage.verifyAlertText(exceptedAlertText);
    }
}
