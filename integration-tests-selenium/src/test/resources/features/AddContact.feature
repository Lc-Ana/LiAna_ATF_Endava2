@UI @AddContact @Regression
Feature: User is able to add contacts on Homepage

  Scenario: User is able to create user populating all fields
    Given user is logged in
    When he fills up a contact form
    Then the table contains the following details
      | Maribel Jacobi | 2001-06-11 | gracie.jacobi@gmail.com | 0441770410 | 8896 Angelica Parkways Suite 518 22616 Major Union Apt. 644 | Niagara Falls Palm Bay 86484-3588 | Japan |
