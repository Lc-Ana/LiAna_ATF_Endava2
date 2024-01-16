package steps;

import api.ApiEndpoint;
import cucumber.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import managers.FileReaderManager;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cucumber.DataKeys.USER;
import static io.restassured.RestAssured.given;

public class Hooks {
    private static TestContext testContext;

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    public Hooks(TestContext context) {
        testContext = context;
    }

    @Before(value = "UI")
    public void setup() {
        logger.info("Browser opens");
        testContext.getWebDriverManager().getDriver();
    }

    @After(value = "UI")
    public void tearDown() {
        logger.info("Browser is closing");
        testContext.getWebDriverManager().closeDriver();
    }

    //Uncomment the lines when needed more info about requests
    @Before(value = "@API")
    public void apiSetup() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(FileReaderManager.getInstance().getConfigReader().getBaseUrl())
                .setContentType("application/json")
                .addHeader("Accept", "application/json")
                //.addFilter(new RequestLoggingFilter())
                //.addFilter(new ResponseLoggingFilter())
                .build();

        logger.info("API Tests Setup...");
    }

    //We need to delete User in order to pass the Scenario CreateUser
    static public void deleteUser() {
        Response userCreationResponse = (Response) testContext.scenarioContext.getContext(USER);
        String accessToken = userCreationResponse.jsonPath().getString("token");

        Response deleteUser = given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .delete(ApiEndpoint.DELETE_USER.getPath())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        logger.info("User deleted successfully: " + deleteUser.statusCode());
    }
}
