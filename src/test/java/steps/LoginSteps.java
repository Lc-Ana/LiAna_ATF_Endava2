package steps;

import cucumber.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjects.LoginPage;

import java.util.List;

public class LoginSteps {
    TestContext testContext;
    LoginPage loginPage;
    private static final Logger logger = LogManager.getLogger(LoginSteps.class);

    public LoginSteps(TestContext context) {
        testContext = context;
        loginPage = testContext.getPageObjectManager().getLoginPage();
    }

    @Given("user is on LoginPage")
    public void user_is_on_login_page() {
        logger.info("User is on LoginPage");
        loginPage.navigateToLoginPage();
    }

    @When("he enters credentials")
    public void he_enters_credentials(DataTable table) {
        List<List<String>> data = table.asLists();
        logger.info("User enters credentials");
        loginPage.fillUsernamePasswordForm(data.get(0).get(0),data.get(0).get(1));
    }

    @When("clicks on login button")
    public void clicks_on_login_button() {
        logger.info("User clicks on login button");
        loginPage.clickLoginButton();
    }

    @Then("user is redirected on LoginPage")
    public void isRedirectedToLoginPage() {
        logger.info("User is redirected on LoginPage");
        testContext.getWebDriverManager().getDriver().getCurrentUrl();
    }

}
