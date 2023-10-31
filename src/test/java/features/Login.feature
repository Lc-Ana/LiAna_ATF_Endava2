@Login
Feature: User is able to login

  Scenario: Login with valid credentials
    Given user is on LoginPage
    When he enters credentials
      | Lili@gmail.com | lili123 |
    And clicks on login button
    Then user is redirected to DashboardPage
