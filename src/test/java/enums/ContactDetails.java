package enums;

public enum ContactDetails {
    FIRST_NAME(),
    LAST_NAME(),
    DATE_OF_BIRTH(),
    EMAIL(),
    PHONE(),
    STREET_ADDRESS1(),
    STREET_ADDRESS2(),
    CITY(),
    STATE(),
    POSTAL_CODE(),
    COUNTRY();

    private final String path;

    ContactDetails(String path) {
        this.path = path;
    }

}
