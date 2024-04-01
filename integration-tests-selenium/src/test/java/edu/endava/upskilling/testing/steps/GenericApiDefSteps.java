package edu.endava.upskilling.testing.steps;

import edu.endava.upskilling.testing.enums.ApiEndpoint;
import edu.endava.upskilling.testing.context.TestContext;
import edu.endava.upskilling.testing.hooks.defaultHooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;

import static edu.endava.upskilling.testing.context.DataKeys.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class GenericApiDefSteps {

    static TestContext testContext;

    public GenericApiDefSteps(TestContext context) {
        testContext = context;
    }

    private static String requestBodyForCreateUser = "{\n" +
            "  \"firstName\": \"test\",\n" +
            "  \"lastName\": \"user7\",\n" +
            "  \"email\": \"tuser7@gmail.com\",\n" +
            "  \"password\": \"testuser7*\" \n  }";
    private static String requestBodyForLoginLogout = "{\n" +
            "  \"email\": \"tuser1@gmail.com\",\n" +
            "  \"password\": \"testuser1*\" \n  }";
    private static String requestBodyForUpdateUSer = "{\n" +
            "  \"firstName\": \"testtest\" \n  }";

    @Given("user sent a POST request to {} endpoint")
    @Given("user sends a GET request to {} endpoint")
    @When("user sends a POST request to {} endpoint")
    @And("user sends an UPDATE request to {} endpoint")
    public void userSendsAPOSTRequestToEndpoint(ApiEndpoint endpoint) {
        switch (endpoint) {
            case ADD_USER: {
                Response userCreationResponse = given()
                        .body(requestBodyForCreateUser)
                        .when()
                        .post(endpoint.getPath())
                        .then()
                        .extract().response();
                log.info("Request body sent to create user: " + userCreationResponse.print());
                testContext.scenarioContext.setContext(USER, userCreationResponse);
            }
            break;
            case LOGIN_USER: {
                Response loginResponse = given()
                        .body(requestBodyForLoginLogout)
                        .when()
                        .post(endpoint.getPath())
                        .then()
                        .extract().response();

                log.info("User is logging in with following credentials: " + loginResponse.print());
                testContext.scenarioContext.setContext(LOGIN, loginResponse);
            }
            break;
            case LOGOUT_USER: {
                Response loginResponse = (Response) testContext.scenarioContext.getContext(LOGIN);
                String accessToken = loginResponse.jsonPath().getString("token");
                testContext.scenarioContext.setContext(TOKEN, accessToken);

                Response logoutResponse = given()
                        .header("Authorization", "Bearer " + accessToken)
                        .when()
                        .post(endpoint.getPath())
                        .then()
                        .extract().response();

                log.info("User is logging out with following credentials: " + logoutResponse.print());
                testContext.scenarioContext.setContext(LOGOUT, logoutResponse);
            }
            break;
            case GET_USER_PROFILE: {
                Response loginResponse = (Response) testContext.scenarioContext.getContext(LOGIN);
                String accessToken = loginResponse.jsonPath().getString("token");
                testContext.scenarioContext.setContext(TOKEN, accessToken);

                Response userProfileResponse = given()
                        .header("Authorization", "Bearer " + accessToken)
                        .when()
                        .get(endpoint.getPath())
                        .then()
                        .extract().response();

                log.info("User Profile details: " + userProfileResponse.print());
                testContext.scenarioContext.setContext(USER_PROFILE, userProfileResponse);
            }
            break;
            case UPDATE_USER: {
                String token = (String) testContext.scenarioContext.getContext(TOKEN);

                Response userProfileUpdatedResponse = given()
                        .header("Authorization", "Bearer " + token)
                        .body(requestBodyForUpdateUSer)
                        .when()
                        .patch(endpoint.getPath())
                        .then()
                        .extract().response();

                log.info("User Profile was updated!  " + userProfileUpdatedResponse.print());
                testContext.scenarioContext.setContext(UPDATED_USER_PROFILE, userProfileUpdatedResponse);
            }
        }
    }

    @Then("an account is created successfully")
    public void receiveResponseCode() {
        Response response = (Response) testContext.scenarioContext.getContext(USER);
        log.info("User is created successfully: " + response.getStatusCode());
        assertEquals(HttpStatus.SC_CREATED, response.getStatusCode());

        //what about Hooks and @AfterAll tag???
        //we need a cleanUp method to pass this scenario
        defaultHooks.deleteUser();
    }

    @Then("user is logged {} successfully")
    public void userLogsInOrOutSuccessfully(String option) {
        switch (option) {
            case "in": {
                Response response = (Response) testContext.scenarioContext.getContext(LOGIN);
                log.info("User is logged in successfully: " + response.getStatusCode());
                assertEquals(HttpStatus.SC_OK, response.getStatusCode());
            }
            break;
            case "out": {
                Response response = (Response) testContext.scenarioContext.getContext(LOGOUT);
                log.info("User is logged out successfully: " + response.getStatusCode() + response.print());
                assertEquals(HttpStatus.SC_OK, response.getStatusCode());
            }
            break;
        }
    }

    @Then("the user profile is updated successfully")
    public void theUserProfileIsUpdatedSuccessfully() {
        Response response = (Response) testContext.scenarioContext.getContext(UPDATED_USER_PROFILE);
        String newFirstName = response.jsonPath().getString("firstName");

        log.info("User is updated successfully: " + response.getStatusCode() + " The new user firstName is: " + newFirstName);

        assertTrue(requestBodyForUpdateUSer.contains(newFirstName));
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    }
}
