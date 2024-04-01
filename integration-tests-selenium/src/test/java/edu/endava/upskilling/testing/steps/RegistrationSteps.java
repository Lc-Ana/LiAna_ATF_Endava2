package edu.endava.upskilling.testing.steps;

import edu.endava.upskilling.testing.context.TestContext;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import edu.endava.upskilling.testing.pageObjects.LoginPage;
import edu.endava.upskilling.testing.pageObjects.RegistrationPage;

import java.io.IOException;

@Slf4j
public class RegistrationSteps {
    TestContext testContext;
    RegistrationPage registrationPage;
    LoginPage loginPage;

    public RegistrationSteps(TestContext context) {
        testContext = context;
        registrationPage = testContext.getPageObjectManager().getRegistrationPage();
        loginPage = testContext.getPageObjectManager().getLoginPage();
    }

    @When("he clicks on Sign up button")
    public void clickOnSignUpButton() {
        log.info("User clicks Submit Button");
        loginPage.clickOnSubmitButton();
    }

    @When("fills up the form")
    public void fillTheForm() throws IOException, ParseException {
        log.info("User fills the form");
        registrationPage.fillTheForm();
    }

    @When("clicks on Submit button")
    public void submitTheForm() {
        log.info("User clicks on Submit Button");
        registrationPage.submitButton();
    }
}
