package pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompaniesPage extends BasePage {

    public CompaniesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "new-company")
    WebElement newCompanyButton;

    @FindBy(id = "name")
    WebElement filterField;

    @FindBy(id = "search-company-submit")
    WebElement filterButton;

    @FindBy(id = "search-company-clear")
    WebElement clearFilterButton;

    @FindBy(xpath = "//tbody//td[1]")
    List<WebElement> companiesNames;

    @FindBy(id = "company-edit")
    List<WebElement> companyEditButtonsList;

    @FindBy(id = "company-delete")
    List<WebElement> deleteCompanyButtonsList;

    @FindBy(id = "name")
    WebElement newCompanyNameField;

    @FindBy(id = "ticketPrefix")
    WebElement newCompanyPrefixField;

    @FindBy(id = "company-additional-information")
    WebElement additionalInfoButton;

    @FindBy(xpath = "//div/fieldset/div[1]/button")
    WebElement addNewShippingAddressButton;

    @FindBy(id = "shipping-address")
    WebElement newShippingAddressField;

    @FindBy(id = "shipping-comment")
    WebElement newShipppingCommentField;

    @FindBy(xpath = "//div/fieldset/div[1]/div[2]/button[2]")
    WebElement closeNewShippingAddress;

    @FindBy(xpath = "//div/fieldset/div[1]/div[2]/button[1]")
    WebElement saveNewShippingAddressButton;

    @FindBy(id = "company-country")
    WebElement newCompanyCountryField;

    @FindBy(id = "company-city")
    WebElement newCompanyCityField;

    @FindBy(id = "company-street")
    WebElement newCompanyStreetField;

    @FindBy(id = "company-building")
    WebElement newCompanyBuildingField;

    @FindBy(id = "company-zipcode")
    WebElement newCompanyZipcodeField;

    @FindBy(id = "company-roomNumber")
    WebElement newCompanyRoomNumberField;

    @FindBy(id = "company-skype")
    WebElement newCompanySkypeField;

    @FindBy(id = "company-website")
    WebElement newCompanyWebsiteField;

    @FindBy(id = "email")
    WebElement newCompanyEmailField;

    @FindBy(id = "edrpou")
    WebElement newCompanyEdrpouField;

    @FindBy(id = "company.phone")
    WebElement newCompanyPhoneField;

    @FindBy(name = "plan")
    List<WebElement> newCompanyLevelRadioButtons;

    @FindBy(id = "company-submit-btn")
    WebElement submitNewCompanyButton;

    WebDriverWait wait = new WebDriverWait(driver, 15);

    //open new company creation page
    public void clickNewCompanyButton() {
        wait.until(ExpectedConditions.visibilityOf(newCompanyButton));
        newCompanyButton.click();
        logger.info("'New company' page opened");
    }

    //set name of the company
    public void setNewCompanyName(String newCompanyName) {
        wait.until(ExpectedConditions.visibilityOf(newCompanyNameField));
        newCompanyNameField.sendKeys(newCompanyName);
        logger.info("Name '{}' for new Company set", newCompanyName);
    }

    //set prefix of the company
    public void setNewCompanyPrefix(String newCompanyPrefix) {
        wait.until(ExpectedConditions.visibilityOf(newCompanyPrefixField));
        newCompanyPrefixField.clear();
        newCompanyPrefixField.sendKeys(newCompanyPrefix);
        logger.info("Prefix '{}' for new company set", newCompanyPrefix);
    }

    //open additional info area
    public void clickAdditionalInfoButton() {
        wait.until(ExpectedConditions.visibilityOf(additionalInfoButton));
        additionalInfoButton.click();
        logger.info("Additional info area opened");
    }

    //open new shipping address form
    public void clickAddNewShippingAddress() {
        wait.until(ExpectedConditions.visibilityOf(addNewShippingAddressButton));
        addNewShippingAddressButton.click();
        logger.info("New shipping address dialog activated");
    }

    //fill in additional shipping address/comment/and press save button
    public void addNewShippingAddress(String newShippingAddress, String shippingAddressComment) {
        wait.until(ExpectedConditions.visibilityOf(newShippingAddressField));
        newShippingAddressField.sendKeys(newShippingAddress);
        wait.until(ExpectedConditions.visibilityOf(newShipppingCommentField));
        newShipppingCommentField.sendKeys(shippingAddressComment);
        saveNewShippingAddressButton.click();
        logger.info("New shipping address created/added");
    }

    //specify country
    public void setNewCompanyCountry(String newCompanyCountry) {
        newCompanyCountryField.sendKeys(newCompanyCountry);
        logger.info("Country of new company is set");
    }

    //specify city
    public void setNewCompanyCity(String newCompanyCity) {
        newCompanyCityField.sendKeys(newCompanyCity);
        logger.info("City of new company set");
    }

    //specify street
    public void setNewCompanyStreet(String newCompanyStreet) {
        newCompanyStreetField.sendKeys(newCompanyStreet);
        logger.info("Street of new company set");
    }

    //specify building
    public void setNewCompanyBuilding(String newCompanyBuilding) {
        newCompanyBuildingField.sendKeys(newCompanyBuilding);
        logger.info("Building of new company set");
    }

    //specify zipcode
    public void setNewCompanyZipcode(String newCompanyZipcode) {
        newCompanyZipcodeField.sendKeys(newCompanyZipcode);
        logger.info("ZipCode of new company set");
    }

    //specify room number
    public void setNewCompanyRoomNumber(String newCompanyRoomNumber) {
        newCompanyRoomNumberField.sendKeys(newCompanyRoomNumber);
        logger.info("Room number of new company set");
    }

    //specify skype account
    public void setNewCompanySkype(String newCompanySkype) {
        newCompanySkypeField.sendKeys(newCompanySkype);
        logger.info("Skype of new company set");
    }

    //specify website
    public void setNewCompanyWebsite(String newCompanyWebsite) {
        newCompanyWebsiteField.sendKeys(newCompanyWebsite);
        logger.info("WebSite of new company set");
    }

    //specify email
    public void setNewCompanyEmail(String newCompanyEmail) {
        newCompanyEmailField.sendKeys(newCompanyEmail);
        logger.info("Email of new company set");
    }

    //specify edrpou
    public void setNewCompanyEdrpou(String newCompanyEdrpou) {
        newCompanyEdrpouField.sendKeys(newCompanyEdrpou);
        logger.info("EDRPOU of new company set");
    }

    //specify phone
    public void setNewCompanyPhone(String newCompanyPhone) {
        newCompanyPhoneField.sendKeys(newCompanyPhone);
        logger.info("Phone of new company set");
    }

    //specify service program level
    public void selectCompanyServiceProgram(int serviceLevel) {
        newCompanyLevelRadioButtons.get(serviceLevel).click();
        logger.info("Service level of new company set");
    }

    //press submit button to confirm creation
    public void clickSubmitNewCompany() {
        submitNewCompanyButton.click();
        logger.info("Creation of new company confirmed by pressing submit button");
    }

    public void searchCompany(String companyName) throws InterruptedException {

        Thread.sleep(500);
        wait.until(ExpectedConditions.visibilityOf(filterField));
        //specify Company name for search
        filterField.clear();
        filterField.sendKeys(companyName);
        //press filter button to start the search
        Thread.sleep(500);
        filterButton.click();
        wait.until(ExpectedConditions.visibilityOf(filterButton));
        logger.info("Search using Company name '{}' executed", companyName);
    }

    public void checkCompanyPresent(String companyName) throws InterruptedException {

        logger.info("Method to assert company '{}' present requested", companyName);
        wait.until(ExpectedConditions.visibilityOf(filterButton));
        Thread.sleep(1000);
        //assert if company is present on the page
        assertEquals(companyName.trim(), companiesNames.get(0).getText().trim());
        System.out.println("Company \"" + companiesNames.get(0).getText() + "\" is present");
        logger.info("Assertion made - company found");

    }

    public void createNewCompany(String companyName, String companyPrefix, String additionalShippingAddress, String additionalShippingAddressComment,
                                 String companyCountry, String companyCity, String companyStreet, String companyBuilding, String companyZipcode,
                                 String companyRoomNumber, String companySkype, String companyWebsite, String companyEmail, String companyEdrpou,
                                 String companyPhone, int serviceLevel) {
        logger.info("Method that gets all data to create new company STARTED");

        //open page to create new Company
        clickNewCompanyButton();
        //specify new company parameters
        setNewCompanyName(companyName);
        setNewCompanyPrefix(companyPrefix);
        clickAdditionalInfoButton();
        clickAddNewShippingAddress();
        addNewShippingAddress(additionalShippingAddress, additionalShippingAddressComment);
        setNewCompanyCountry(companyCountry);
        setNewCompanyCity(companyCity);
        setNewCompanyStreet(companyStreet);
        setNewCompanyBuilding(companyBuilding);
        setNewCompanyZipcode(companyZipcode);
        setNewCompanyRoomNumber(companyRoomNumber);
        setNewCompanySkype(companySkype);
        setNewCompanyWebsite(companyWebsite);
        setNewCompanyEmail(companyEmail);
        setNewCompanyEdrpou(companyEdrpou);
        setNewCompanyPhone(companyPhone);
        selectCompanyServiceProgram(serviceLevel);
        //confirm creation by clicking submit button
        clickSubmitNewCompany();
        logger.info("Method of a complete company creation COMPLETE");

    }

    public void deleteFirstCompany() throws InterruptedException {

        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(deleteCompanyButtonsList.get(0)));
        //press delete button for the found company
        deleteCompanyButtonsList.get(0).click();
        //switch control to the alert - confirmation of the company deletion
        Alert alert = driver.switchTo().alert();
        //accept deletion of a department
        alert.accept();
        //clear search field on Departments page to see all departments
        clearFilterButton.click();
        logger.info("First company on the page deleted");
    }

}

