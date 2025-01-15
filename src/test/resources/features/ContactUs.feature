@regression @contact-us
Feature: WebdriverUniversity.com - Contact Us Page

  Background: Preconditions
    Given I navigate to the webdriveruniversity homepage
    When I click on the contact us button

  Scenario: Valid Contact Us Form Submission
    And I type a first name
    And I type a last name
    And I type an email address
    And I type a comment
    And I click the submit button
    Then I should be presented with a successful contact us submission message

  Scenario: Invalid Contact Us Form Submission
    And I type a first name
    And I type a last name
    # And I type an email address
    And I type a comment
    And I click the submit button
    Then I should be presented with a unsuccessful contact us submission message

  Scenario: Valid Contact Us Form Submission
    And I type a specific first name "Sarah"
    And I type a specific last name "Smith"
    And I type an email address "sarah.smith@gmail.com"
    And I type specific text "Hello World Programming!!!" and a number 2 within the comment input field
    And I click the submit button
    Then I should be presented with a successful contact us submission message

    @randomData
  Scenario: Valid Contact Us Form Submission - Using Random Data
    And I type a random first name
    And I type a random last name
    And I type a random email address
    #And I type a comment
    And I type a random comment
    And I click the submit button
    Then I should be presented with a successful contact us submission message

  @smoke
  Scenario Outline: Validate Contact Us Page
    And I type a first name '<firstName>' and a last name '<lastName>'
    And I type an email address '<emailAddress>' and a comment '<comment>'
    And I click the submit button
    Then I should be presented with header text '<message>'

    Examples:
      | firstName | lastName | emailAddress          | comment                    | message                      |
      | Sarah     | Smith    | sarah.smith@gmail.com | Hello World Programming!!! | Thank You for your Message!  |
      | John      | Doe      | john.doe@yahoo.com    | Welcome to WebdriverUni!   | Thank You for your Message!  |
      | Bob       | Smith    | bob.smith             | Hello to Playwright!       | Error: Invalid email address |


