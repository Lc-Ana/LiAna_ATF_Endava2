package steps;

import cucumber.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;

import java.util.List;

public class LoginSteps {
    TestContext testContext;
    LoginPage loginPage;

    public LoginSteps(TestContext context) {
        testContext = context;
        loginPage = testContext.getPageObjectManager().getLoginPage();
    }

    @Given("user is on LoginPage")
    public void user_is_on_login_page() {
        loginPage.navigateToLoginPage();
    }

    @When("he enters credentials")
    public void he_enters_credentials(DataTable table) {
        List<List<String>> data = table.asLists();
        loginPage.fillUsernamePasswordForm(data.get(0).get(0),data.get(0).get(1));
    }

    @When("clicks on login button")
    public void clicks_on_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("is redirected to DashboardPage")
    public void is_redirected_to_dashboard_page() {

    }

    @When("clicks on logout button")
    public void clicks_on_logout_button() {

    }

    @Then("is redirected to LoginPage")
    public void is_redirected_to_login_page() {

    }

}
