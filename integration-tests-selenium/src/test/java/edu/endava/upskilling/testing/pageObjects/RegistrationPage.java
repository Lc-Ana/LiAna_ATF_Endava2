package edu.endava.upskilling.testing.pageObjects;

import edu.endava.upskilling.testing.managers.FileReaderManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class RegistrationPage {

    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    private WebElement firstNameInput;
    @FindBy(id = "lastName")
    private WebElement lastNameInput;
    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(id = "submit")
    private WebElement submitButton;
    @FindBy(id = "cancel")
    private WebElement cancelButton;


    public void fillTheForm() throws IOException, ParseException {
        firstNameInput.sendKeys(FileReaderManager.getInstance().getUserDetailsReader().getUserForRegistration().getFirstName());
        lastNameInput.sendKeys(FileReaderManager.getInstance().getUserDetailsReader().getUserForRegistration().getLastName());
        emailInput.sendKeys(FileReaderManager.getInstance().getUserDetailsReader().getUserForRegistration().getEmail());
        passwordInput.sendKeys(FileReaderManager.getInstance().getUserDetailsReader().getUserForRegistration().getPassword());
    }

    public void submitButton() {
        submitButton.click();
    }

    public void cancelRegistrationProcess() {
        cancelButton.click();
    }

}
