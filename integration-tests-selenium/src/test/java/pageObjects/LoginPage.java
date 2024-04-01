package pageObjects;

import managers.FileReaderManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email")
    private WebElement usernameInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(id = "submit")
    private WebElement loginButton;

    @FindBy(id = "signup")
    private WebElement submitButton;
    @FindBy(id = "error")
    private WebElement loginError;


    private void enterUser(String user) {
        usernameInput.sendKeys(user);
    }

    private void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void fillUsernamePasswordForm(String username, String password) {
        enterUser(username);
        enterPassword(password);
    }

    public void navigateToLoginPage() {
        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
    }

    public void setUsernameInput(WebElement usernameInput) {
        this.usernameInput = usernameInput;
    }

    public void clickOnSubmitButton(){
        submitButton.click();
    }

    public WebElement getLoginError() {
        return loginError;
    }
}
