package edu.endava.upskilling.testing.steps;

import edu.endava.upskilling.testing.context.TestContext;
import edu.endava.upskilling.testing.managers.FileReaderManager;
import edu.endava.upskilling.testing.pageObjects.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;

import java.util.List;

@Slf4j
public class LoginSteps {
    TestContext testContext;
    LoginPage loginPage;

    public LoginSteps(TestContext context) {
        testContext = context;
        loginPage = testContext.getPageObjectManager().getLoginPage();
    }

    @Given("user is on LoginPage")
    public void user_is_on_login_page() {
        log.info("User is on LoginPage");
        loginPage.navigateToLoginPage();
    }

    @When("he enters credentials")
    public void entersCredentials(DataTable table) {
        List<List<String>> data = table.asLists();
        log.info("User enters credentials");
        loginPage.fillUsernamePasswordForm(data.get(0).get(0), data.get(0).get(1));
    }

    @When("clicks on login button")
    public void clicks_on_login_button() {
        log.info("User clicks on login button");
        loginPage.clickLoginButton();
    }

    @Then("user is redirected on LoginPage")
    public void isRedirectedToLoginPage() {
        log.info("User is redirected on LoginPage");
        testContext.getWebDriverManager().getDriver().getCurrentUrl();
    }

    @When("he enters invalid {} or {}")
    public void enterInvalidCredentials(String username, String password) throws InterruptedException {
        log.info("User enters invalid credentials");
        loginPage.fillUsernamePasswordForm(username, password);
        loginPage.clickLoginButton();
        Thread.sleep(1000);
    }

    @Then("user receives an error")
    public void userReceivesAnError() {
        String expectedLoginError = "Incorrect username or password";
        String actualLoginError = loginPage.getLoginError().getText();
        log.info("User receives an error.");
        Assertions.assertEquals(actualLoginError, expectedLoginError, "Error messages do not match.");
    }

    @Given("user is logged in")
    public void userIsLogged() throws InterruptedException {
        log.info("User is logged in the app");
        loginPage.navigateToLoginPage();
        loginPage.fillUsernamePasswordForm(FileReaderManager.getInstance().getConfigReader().getUser(),
                FileReaderManager.getInstance().getConfigReader().getPassword());
        loginPage.clickLoginButton();
        Thread.sleep(1000);

    }
}
