package step_definitions;



/*
 * @Project Name: playwright-automation-framework
 * @Author: Okechukwu Bright Onwumere
 * @Created: 12-Jan-25
 */


import context.PersonContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.datafaker.Faker;
import pages.ContactUsPage;

public class ContactUsStep {

    private final Faker faker;
    private String firstName;
    private String lastName;
    private final PersonContext personContext;
    private final ContactUsPage contactUsPage;

    public ContactUsStep(PersonContext personContext, ContactUsPage contactUsPage) {
        faker = new Faker();
        this.personContext = personContext;
        this.contactUsPage = contactUsPage;
    }

    @And("I type a first name")
    public void i_type_a_first_name() {
        contactUsPage.typeFirstName("Joe");
    }

    @And("I type a last name")
    public void i_type_a_last_name() {
        contactUsPage.typeLastName("Johnson");
    }

    @And("I type an email address")
    public void i_type_an_email_address() {
        contactUsPage.enterEmailAddress("joe.johnson@gmail.com");
    }

    @And("I type a comment")
    public void i_type_a_comment() {
        contactUsPage.typeComment("Hello World Programming!!!!");
    }

    @And("I type a random comment")
    public void i_type_a_random_comment() {
        contactUsPage.typeComment("Hi, please can you contact me" +
                "\nMy Details are: " + personContext.getRandomFirstName() + " " + personContext.getRandomLastName() + " " + personContext.getRandomEmailAddress());
    }

    @And("I click the submit button")
    public void i_click_the_submit_button() {
        contactUsPage.clickOnSubmitButton();
    }

    @Then("I should be presented with a successful contact us submission message")
    public void i_should_be_presented_with_a_successful_contact_us_submission_message() {
        contactUsPage.verifySuccessfulSubmission();
    }

    @Then("I should be presented with a unsuccessful contact us submission message")
    public void i_should_be_presented_with_a_unsuccessful_contact_us_submission_message() {
        contactUsPage.verifyUnsuccessfulSubmission();
    }


    @And("I type a specific first name {string}")
    public void i_type_a_specific_first_name(String firstName) {
        contactUsPage.typeFirstName(firstName);
    }

    @And("I type a specific last name {string}")
    public void i_type_a_specific_last_name(String lastName) {
        contactUsPage.typeLastName(lastName);
    }

    @When("I type an email address {string}")
    public void i_type_an_email_address(String emailAddress) {
        contactUsPage.enterEmailAddress(emailAddress);
    }

    @When("I type specific text {string} and a number {int} within the comment input field")
    public void i_type_specific_text_and_a_number_within_the_comment_input_field(String text, Integer number) {
        contactUsPage.typeComment(text + " " + number);
    }


    @And("I type a random first name")
    public void i_type_a_random_first_name() {
        firstName = faker.name().firstName();
        personContext.setRandomFirstName(firstName);
        contactUsPage.typeFirstName(firstName);
    }

    @And("I type a random last name")
    public void i_type_a_random_last_name() {
        lastName = faker.name().lastName();
        personContext.setRandomLastName(lastName);
        contactUsPage.typeLastName( lastName);
    }

    @When("I type a random email address")
    public void i_type_a_random_email_address() {
        String emailAddress = firstName + "." + lastName + "@gmail.com";
        personContext.setRandomEmailAddress(emailAddress);
        contactUsPage.enterEmailAddress(emailAddress.toLowerCase());
    }

    // Scenario Outlines
    @When("I type a first name {string} and a last name {string}")
    public void i_type_a_first_name_sarah_and_a_last_name_smith(String firstName, String lastName) {
        contactUsPage.typeFirstName(firstName);
        contactUsPage.typeLastName(lastName);
    }

    @When("I type an email address {string} and a comment {string}")
    public void i_type_an_email_address_and_a_comment(String emailAddress, String comment) {
        contactUsPage.enterEmailAddress(emailAddress);
        contactUsPage.typeComment(comment);
    }

    @Then("I should be presented with header text {string}")
    public void i_should_be_presented_with_header_text(String exceptedMessage) {
        contactUsPage.verifyHeaderText(exceptedMessage);
    }

}
