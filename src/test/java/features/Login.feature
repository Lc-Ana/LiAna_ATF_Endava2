@Login @Regression
Feature: Login with valid & invalid credentials

  Scenario: Login with valid credentials
    Given user is on LoginPage
    When he enters credentials
      | Lili@gmail.com | lili123 |
    And clicks on login button
    Then user is redirected to DashboardPage

  Scenario Outline: User cannot login with invalid credentials
    Given user is on LoginPage
    When he enters invalid <username> or <password>
    And clicks on login button
    Then user receives an error
    Examples:
      | username       | password |
      | @gmail.com     | lili123  |
      | Lili           | lili123  |
      | Lili@gmail.com | lili124  |
      |                | lili123  |
      | Lili@gmail.com |          |