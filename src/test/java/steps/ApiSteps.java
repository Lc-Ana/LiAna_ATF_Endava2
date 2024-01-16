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

    @Given("user is authenticated in the app using {} endpoint")
    @Given("user sent a POST request to {} endpoint")
    @When("user sends a POST request to {} endpoint")
    @And("user is able to login in the account using {} endpoint")
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
                Response logoutResponse = given()
                        .header("Authorization", "Bearer " + accessToken)
                       // .body(requestBodyForLoginLogout)
                        .when()
                        .post(endpoint.getPath())
                        .then()
                        .extract().response();

                logger.info("User is logging out with following credentials: " + logoutResponse.print());
                testContext.scenarioContext.setContext(LOGOUT, logoutResponse);
            }
            break;
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

}
