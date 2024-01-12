package pageObjects;

import managers.FileReaderManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class DashboardPage {
    WebDriver driver;

    @FindBy(id = "logout")
    private WebElement logoutButton;
    @FindBy(xpath = "/html/body/div/header/h1")
    private WebElement header;
    @FindBy(id = "add-contact")
    private WebElement addNewContact;
    @FindBy(id = "firstName")
    private WebElement firstNameInput;
    @FindBy(id = "lastName")
    private WebElement lastNameInput;
    @FindBy(id = "birthdate")
    private WebElement birthDateInput;
    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(id = "phone")
    private WebElement phoneInput;
    @FindBy(id = "street1")
    private WebElement street1Input;
    @FindBy(id = "street2")
    private WebElement street2Input;
    @FindBy(id = "city")
    private WebElement cityInput;
    @FindBy(id = "stateProvince")
    private WebElement stateInput;
    @FindBy(id = "postalCode")
    private WebElement postalCodeInput;
    @FindBy(id = "country")
    private WebElement countryInput;
    @FindBy(id = "submit")
    private WebElement submitButton;
    @FindBy(id = "cancel")
    private WebElement cancelButton;
    @FindBy(className = "contacts")
    private WebElement table;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

    public String userRedirectedToDashboardPage() {
        String dashBoardLink = (FileReaderManager.getInstance().getConfigReader().getApplicationUrl()).concat(FileReaderManager.getInstance().getConfigReader().getDashboardURLEndpoint());
        return dashBoardLink;
    }

    public boolean elementIsDisplayed() {
        return logoutButton.isDisplayed();
    }

    public void clickAddNewContactButton() {
        addNewContact.click();
    }

    public void fillContactForm() throws IOException, ParseException {
        firstNameInput.sendKeys(FileReaderManager.getInstance().getUserDetailsReader().getContactDetails().getFirstName());
        lastNameInput.sendKeys(FileReaderManager.getInstance().getUserDetailsReader().getContactDetails().getLastName());
        birthDateInput.sendKeys(FileReaderManager.getInstance().getUserDetailsReader().getContactDetails().getDate());
        emailInput.sendKeys(FileReaderManager.getInstance().getUserDetailsReader().getContactDetails().getEmail());
        phoneInput.sendKeys(FileReaderManager.getInstance().getUserDetailsReader().getContactDetails().getPhone());
        street1Input.sendKeys(FileReaderManager.getInstance().getUserDetailsReader().getContactDetails().getAddress1());
        street2Input.sendKeys(FileReaderManager.getInstance().getUserDetailsReader().getContactDetails().getAddress2());
        cityInput.sendKeys(FileReaderManager.getInstance().getUserDetailsReader().getContactDetails().getCity());
        stateInput.sendKeys(FileReaderManager.getInstance().getUserDetailsReader().getContactDetails().getProvince());
        postalCodeInput.sendKeys(FileReaderManager.getInstance().getUserDetailsReader().getContactDetails().getPostal());
        countryInput.sendKeys(FileReaderManager.getInstance().getUserDetailsReader().getContactDetails().getCountry());
    }

    public void submitContactForm() throws InterruptedException {
        submitButton.click();
    }

    public int getTheDataRowsOfTheTable(){
        List<WebElement> dataRows = table.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).stream().toList();
        return dataRows.size();
    }

}
