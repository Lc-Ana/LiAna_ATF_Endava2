@API @Test
Feature: API Tests

  Scenario: User is able to create an account
    Given user sent a POST request to ADD_USER endpoint
    Then an account is created successfully

  Scenario: User is able to login into the app and logout from the app
    Given user sent a POST request to LOGIN_USER endpoint
    And user is logged in successfully
    When user sends a POST request to LOGOUT_USER endpoint
    Then user is logged out successfully

#TO DO refactor the tests, login logout to be together Given request sent And user authenticated When logout Then logout successfully
