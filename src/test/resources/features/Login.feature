@regression @login
Feature: WebdriverUniversity.com - Login Page

  Background: Preconditions
    Given I navigate to the webdriveruniversity homepage
    When I click on the contact us button

  Scenario Outline: Validate valid and Invalid login Credentials
    Given I navigate to the webdriveruniversity homepage
    When I click on the login portal button
    And I type a username '<username>'
    And I type a password '<password>'
    And I click the login button
    Then I should be presented with an alert box which contains text '<exceptedAlertText>'

  Examples:
    | username  | password     | exceptedAlertText    |
    | webdriver | webdriver123 | validation succeeded |
    | webdriver | password123  | validation failed    |

    @smoke
  Examples:
    | username  | password     | exceptedAlertText    |
    | webdriver | webdriver123 | validation succeeded |