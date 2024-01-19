package steps;

import api.ApiEndpoint;
import cucumber.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cucumber.DataKeys.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiSteps {

    static TestContext testContext;
    private static final Logger logger = LogManager.getLogger(ApiSteps.class);

    public ApiSteps(TestContext context) {
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
                logger.info("Request body sent to create user: " + userCreationResponse.print());
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

                logger.info("User is logging in with following credentials: " + loginResponse.print());
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

                logger.info("User is logging out with following credentials: " + logoutResponse.print());
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

                logger.info("User Profile details: " + userProfileResponse.print());
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

                logger.info("User Profile was updated!  " + userProfileUpdatedResponse.print());
                testContext.scenarioContext.setContext(UPDATED_USER_PROFILE, userProfileUpdatedResponse);
            }
        }
    }

    @Then("an account is created successfully")
    public void receiveResponseCode() {
        Response response = (Response) testContext.scenarioContext.getContext(USER);
        logger.info("User is created successfully: " + response.getStatusCode());
        assertEquals(HttpStatus.SC_CREATED, response.getStatusCode());

        //what about Hooks and @AfterAll tag???
        //we need a cleanUp method to pass this scenario
        Hooks.deleteUser();
    }

    @Then("user is logged {} successfully")
    public void userLogsInOrOutSuccessfully(String option) {
        switch (option) {
            case "in": {
                Response response = (Response) testContext.scenarioContext.getContext(LOGIN);
                logger.info("User is logged in successfully: " + response.getStatusCode());
                assertEquals(HttpStatus.SC_OK, response.getStatusCode());
            }
            break;
            case "out": {
                Response response = (Response) testContext.scenarioContext.getContext(LOGOUT);
                logger.info("User is logged out successfully: " + response.getStatusCode() + response.print());
                assertEquals(HttpStatus.SC_OK, response.getStatusCode());
            }
            break;
        }
    }

    @Then("the user profile is updated successfully")
    public void theUserProfileIsUpdatedSuccessfully() {
        Response response = (Response) testContext.scenarioContext.getContext(UPDATED_USER_PROFILE);
        String newFirstName = response.jsonPath().getString("firstName");

        logger.info("User is updated successfully: " + response.getStatusCode() + " The new user firstName is: " + newFirstName);

        assertTrue(requestBodyForUpdateUSer.contains(newFirstName));
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    }
}
