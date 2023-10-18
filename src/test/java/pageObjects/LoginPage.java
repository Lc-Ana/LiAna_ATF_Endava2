package pageObjects;

import io.cucumber.datatable.DataTable;
import managers.FileReaderManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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
}
