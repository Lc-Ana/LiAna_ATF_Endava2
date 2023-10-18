@Login
Feature: User is able to login

  Scenario: Login with valid credentials
    Given user is on LoginPage
    When he enter username and password
    And clicks on login button
    Then is redirected to DashboardPage
    When clicks on logout button
    Then is redirected to LoginPage