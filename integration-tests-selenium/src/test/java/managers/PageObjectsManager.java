package managers;

import org.openqa.selenium.WebDriver;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import pageObjects.RegistrationPage;

public class PageObjectsManager {
    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private RegistrationPage registrationPage;

    public PageObjectsManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
    }
    public DashboardPage getDashboardPage() {
        return (dashboardPage == null) ? dashboardPage = new DashboardPage(driver) : dashboardPage;
    }
    public RegistrationPage getRegistrationPage() {
        return (registrationPage == null) ? registrationPage = new RegistrationPage(driver) : registrationPage;
    }
}
