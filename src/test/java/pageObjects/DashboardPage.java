package pageObjects;

import managers.FileReaderManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "logout")
    private WebElement logoutButton;

    @FindBy(xpath = "/html/body/div/header/h1")
    private WebElement header;

    public void clickLogoutButton(){
        logoutButton.click();
    }

    public String userRedirectedToDashboardPage(){
        String dashBoardLink = (FileReaderManager.getInstance().getConfigReader().getApplicationUrl()).concat(FileReaderManager.getInstance().getConfigReader().getDashboardURLEndpoint());
        return dashBoardLink;
    }
    public boolean elementIsDisplayed(){
        return logoutButton.isDisplayed();
    }

}
