@AddContact @Regression
Feature: User is able to add contacts on Homepage

  @Test
  Scenario: User is able to create user populating all fields
    Given user is logged in
    When he fills up <all fields> with the <values>
      | all fields      | values          |
      | firstName       | FIRST_NAME      |
      | lastName        | LAST_NAME       |
      | dateOfBirth     | DATE_OF_BIRTH   |
      | email           | EMAIL           |
      | phone           | PHONE           |
      | streetAddress1  | STREET_ADDRESS1 |
      | streetAddress2  | STREET_ADDRESS2 |
      | city            | CITY            |
      | stateOrProvince | STATE           |
      | postalCode      | POSTAL_CODE     |
      | country         | COUNTRY         |
    #Then he gets a contact created on Homepage
