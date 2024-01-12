@AddContact @Regression
Feature: User is able to add contacts on Homepage

  @Test
  Scenario: User is able to create user populating all fields
    Given user is logged in
    When he fills up a contact form
   # Then the table contains the following details
