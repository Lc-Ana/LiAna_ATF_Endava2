package edu.endava.upskilling.testing.steps;

import edu.endava.upskilling.testing.context.TestContext;
import edu.endava.upskilling.testing.pageObjects.DashboardPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import edu.endava.upskilling.testing.managers.FileReaderManager;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class DashboardSteps {
    TestContext testContext;
    DashboardPage dashboardPage;

    public DashboardSteps(TestContext context) {
        testContext = context;
        dashboardPage = testContext.getPageObjectManager().getDashboardPage();
    }

    @When("clicks on logout button")
    public void clicks_on_logout_button() {
        log.info("User clicks on login button");
        dashboardPage.clickLogoutButton();
    }

    @Then("user is redirected to DashboardPage")
    public void is_redirected_to_dashboard_page() throws Exception {
        String expectedResult = FileReaderManager.getInstance().getConfigReader().getDashboardURLEndpoint();
        Thread.sleep(1000);
        String actualResult = testContext.getWebDriverManager().getDriver().getCurrentUrl();
        log.info("User is redirected to Dashboard page");
        Assertions.assertTrue(actualResult.contains(expectedResult), "User is NOT on Dashboard Page");
    }


    @When("he fills up the contact form")
    public void heFillsUpContactForm() throws IOException, ParseException, InterruptedException {
        dashboardPage.clickAddNewContactButton();
        log.info("Filling the contact form");
        dashboardPage.fillContactForm();
        dashboardPage.submitContactForm();
    }

    @Then("the table contains the following details")
    public void verifyTheTableDetails(DataTable expectedRowData) {
        log.info("getting actual row data... ");
        List<String> actualRowData = dashboardPage.getRowData();

        List<List<String>> tableValues = expectedRowData.asLists();
        List<String> expectedValues = tableValues.get(0);

        log.info("asserting row data... ");
        assertEquals(expectedValues, actualRowData);
    }

}
