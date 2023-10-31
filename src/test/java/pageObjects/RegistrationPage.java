package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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


    public void fillTheForm() {
        firstNameInput.sendKeys("Iurie");
        lastNameInput.sendKeys("Kiritop");
        emailInput.sendKeys("ikiritop04@gmail.com");
        passwordInput.sendKeys("kiritop2023");
    }

    public void submitButton() {
        submitButton.click();
    }

    public void cancelRegistrationProcess() {
        cancelButton.click();
    }

}
