package steps;

import cucumber.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.FileReaderManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import pageObjects.DashboardPage;

public class DashboardSteps {
    TestContext testContext;
    DashboardPage dashboardPage;
    private static final Logger logger = LogManager.getLogger(DashboardSteps.class);

    public DashboardSteps(TestContext context) {
        testContext = context;
        dashboardPage = testContext.getPageObjectManager().getDashboardPage();
    }

    @When("clicks on logout button")
    public void clicks_on_logout_button() {
        logger.info("User clicks on login button");
        dashboardPage.clickLogoutButton();
    }

    @Then("user is redirected to DashboardPage")
    public void is_redirected_to_dashboard_page() throws Exception {
        String expectedResult = FileReaderManager.getInstance().getConfigReader().getDashboardURLEndpoint();
        Thread.sleep(1000);
        String actualResult = testContext.getWebDriverManager().getDriver().getCurrentUrl();
        logger.info("User is redirected to Dashboard page");
        Assertions.assertTrue(actualResult.contains(expectedResult),"User is NOT on Dashboard Page");
    }

}
