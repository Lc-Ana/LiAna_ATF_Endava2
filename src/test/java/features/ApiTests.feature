@API #@Test
Feature: API Tests

  Scenario: User is able to create an account
    Given user sent a POST request to ADD_USER endpoint
    Then an account is created successfully

  Scenario: User is able to login and logout
    Given user sent a POST request to LOGIN_USER endpoint
    And user is logged in successfully
    When user sends a POST request to LOGOUT_USER endpoint
    Then user is logged out successfully

  Scenario: User gets a user profile and updates it
    Given user sent a POST request to LOGIN_USER endpoint
    When user sends a GET request to GET_USER_PROFILE endpoint
    And user sends an UPDATE request to UPDATE_USER endpoint
    Then the user profile is updated successfully