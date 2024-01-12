@Registration @Regression
Feature: User is able to register or cancel the process of registration on the portal

  Scenario: Registration with valid data
    Given user is on LoginPage
    When he clicks on Sign up button
    And fills up the form
    And clicks on Submit button
    Then user is redirected to DashboardPage
#    And user is saved in db