@API @Test
Feature: API Tests

  Scenario: Create User
    Given user sent a POST request to ADD_USER endpoint
    Then an account is created successfully

  Scenario: Login with the user created previously
    Given user sent a POST request to LOGIN_USER endpoint
    Then user is logged in successfully

  Scenario: Logout with the user created previously
    Given user is authenticated in the app using LOGIN_USER endpoint
    When user sends a POST request to LOGOUT_USER endpoint
    Then user is logged out successfully
