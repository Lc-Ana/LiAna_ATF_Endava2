package edu.endava.upskilling.testing.hooks;

import edu.endava.upskilling.testing.enums.ApiEndpoint;
import edu.endava.upskilling.testing.context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import edu.endava.upskilling.testing.managers.FileReaderManager;
import org.apache.http.HttpStatus;

import static edu.endava.upskilling.testing.context.DataKeys.USER;
import static io.restassured.RestAssured.given;
@Slf4j
public class defaultHooks {
    private static TestContext testContext;

    public defaultHooks(TestContext context) {
        testContext = context;
    }

    @Before(value = "@UI")
    public void setup() {
        log.info("Browser is opening...");
        testContext.getWebDriverManager().getDriver();
    }

    @After(value = "@UI")
    public void tearDown() {
        log.info("Browser is closing...");
        testContext.getWebDriverManager().closeDriver();
    }

    //Uncomment the lines when needed more info about requests
    @Before(value = "@API")
    public void apiSetup() {
        log.info("API Tests Setup...");
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(FileReaderManager.getInstance().getConfigReader().getBaseUrl())
                .setContentType("application/json")
                .addHeader("Accept", "application/json")
                //.addFilter(new RequestLoggingFilter())
                //.addFilter(new ResponseLoggingFilter())
                .build();
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

        log.info("User deleted successfully: " + deleteUser.statusCode());
    }
}
