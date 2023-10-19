package steps;

import cucumber.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.FileReaderManager;
import org.junit.jupiter.api.Assertions;
import pageObjects.DashboardPage;

import java.util.List;
import java.util.Map;

public class DashboardSteps {
    TestContext testContext;
    DashboardPage dashboardPage;

    public DashboardSteps(TestContext context) {
        testContext = context;
        dashboardPage = testContext.getPageObjectManager().getDashboardPage();
    }

    @When("clicks on logout button")
    public void clicks_on_logout_button() {
        dashboardPage.clickLogoutButton();
    }

    @Then("is redirected to DashboardPage")
    public void is_redirected_to_dashboard_page() throws Exception {
        String expectedResult = FileReaderManager.getInstance().getConfigReader().getDashboardURLEndpoint();
        Thread.sleep(1000);
        String actualResult = testContext.getWebDriverManager().getDriver().getCurrentUrl();
        Assertions.assertTrue(actualResult.contains(expectedResult),"User is NOT on Dashboard Page");
    }

}
