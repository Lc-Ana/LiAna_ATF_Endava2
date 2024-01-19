package api;

import lombok.Getter;

@Getter
public enum ApiEndpoint {

    ADD_USER("/users"),
    GET_USER_PROFILE("/users/me"),
    UPDATE_USER("/users/me"),
    LOGIN_USER("/users/login"),
    LOGOUT_USER("/users/logout"),
    DELETE_USER("/users/me"),
    ADD_CONTACT("/contacts"),
    GET_CONTACT_LIST("/contacts"),
    GET_CONTACTS("/contacts/"),
    UPDATE_CONTACT("/contacts/"),
    DELETE_CONTACT("/contacts/");

    private final String path;

    ApiEndpoint(String path) {
        this.path = path;
    }
}
