package steps;

import cucumber.TestContext;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import pageObjects.LoginPage;
import pageObjects.RegistrationPage;

import java.io.IOException;

public class RegistrationSteps {
    TestContext testContext;
    RegistrationPage registrationPage;
    LoginPage loginPage;
    private static final Logger logger = LogManager.getLogger(LoginSteps.class);

    public RegistrationSteps(TestContext context) {
        testContext = context;
        registrationPage = testContext.getPageObjectManager().getRegistrationPage();
        loginPage = testContext.getPageObjectManager().getLoginPage();
    }

    @When("he clicks on Sign up button")
    public void clickOnSignUpButton() {
        logger.info("User clicks Submit Button");
        loginPage.clickOnSubmitButton();
    }

    @When("fills up the form")
    public void fillTheForm() throws IOException, ParseException {
        logger.info("User fills the form");
        registrationPage.fillTheForm();
    }

    @When("clicks on Submit button")
    public void submitTheForm() {
        logger.info("User clicks on Submit Button");
        registrationPage.submitButton();
    }

}
