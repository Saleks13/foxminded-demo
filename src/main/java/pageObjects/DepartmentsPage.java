package pageObjects;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class DepartmentsPage extends BasePage {

    public DepartmentsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "new-department")
    WebElement newDepartmentButton;

    @FindBy(id = "department-search-title")
    public WebElement searchDepartmentField;

    @FindBy(id = "department-search-filter")
    WebElement searchDepartmentButton;

    @FindBy(id = "department-search-clear")
    WebElement clearSearchButton;

    @FindBy(id = "department-sort-asc")
    WebElement filterAscButton;

    @FindBy(id = "department-sort-desc")
    WebElement filterDescButton;

    @FindBy(xpath = "//tbody//td[1]")
    public List<WebElement> departments;

    @FindBy(id = "department-delete")
    public List<WebElement> deleteDepartmentButtons;

    @FindBy(xpath = "//div/div/table/tbody/tr/th[1]")
    WebElement departmentTableTitleHeader;

    @FindBy(id = "name")
    WebElement titleField;

    @FindBy(id = "department-form-submit")
    public WebElement submitButton;

    @FindBy(id = "department-form-cancel")
    WebElement cancelButton;

    @FindBy(id = "department-form-additional-info")
    WebElement additionalInfoButton;

    @FindBy(id = "phone")
    WebElement departmentPhoneField;

    @FindBy(id = "skype")
    WebElement departmentSkypeField;

    @FindBy(id = "website")
    WebElement departmentWebsiteField;

    @FindBy(id = "email")
    WebElement departmentEmailField;

    @FindBy(id = "country")
    WebElement departmentCountryField;

    @FindBy(id = "city")
    WebElement departmentCityField;

    @FindBy(id = "street")
    WebElement departmentStreetField;

    @FindBy(id = "building")
    WebElement departmentBuildingField;

    @FindBy(id = "zipcode")
    WebElement departmentZipcodeField;

    @FindBy(id = "roomNumber")
    WebElement departmentRoomNumberField;

    //init wait object to use it in methods
    WebDriverWait wait = new WebDriverWait(driver, 15);

    //this method is to enter page of new department creation
    public DepartmentsPage clickCreateNewDepartment() {
        this.newDepartmentButton.click();
        //timeout to ensure New Department page is loaded
        wait.until(ExpectedConditions.visibilityOf(submitButton));
        logger.info("Page to create new Department opened");
        return this;
    }

    //open additional info area
    public DepartmentsPage activateAdditionalInfo() {
        this.additionalInfoButton.click();

        //wait added to ensure that additional fields are shown, so that further methods are able to operate with fields
        wait.until(ExpectedConditions.visibilityOf(departmentPhoneField));

        logger.info("Additional info area of the new department opened");
        return this;
    }

    //method to create simple department (with no additional info)
    public DepartmentsPage createNewDepartment(String departmentName) {
        this.titleField.sendKeys(departmentName);
        this.submitButton.click();
        logger.info("Method to create department only with name executed");
        return this;
    }

    //click Submit button on department creation page
    public DepartmentsPage clickSubmitButton() {
        this.submitButton.click();
        logger.info("Button to submit department creation pressed");
        return this;
    }

    //abandon department creation by clicking cancel button
    public DepartmentsPage cancelCreation() {
        this.cancelButton.click();
        Alert alert = driver.switchTo().alert();
        //accept deletion of a department
        alert.accept();
        logger.info("Department creation canceled");
        return this;
    }

    //specify title
    public DepartmentsPage setNewDepartmentTitle(String newDepartmentTitle) {
        this.titleField.sendKeys(newDepartmentTitle);
        logger.info("Name for new department set");
        return this;
    }


    //specify phone number
    public DepartmentsPage setDepartmentPhone(String departmentPhone) {
        this.departmentPhoneField.sendKeys(departmentPhone);
        logger.info("Phone for new department set");
        return this;
    }

    //specify skype account
    public DepartmentsPage setDepartmentSkype(String departmentSkype) {
        this.departmentSkypeField.sendKeys(departmentSkype);
        logger.info("Skype for new department set");
        return this;
    }

    //specify website
    public DepartmentsPage setDepartmentWebsite(String departmentWebsite) {
        this.departmentWebsiteField.sendKeys(departmentWebsite);
        logger.info("Website for new department set");
        return this;
    }

    //specify email
    public DepartmentsPage setDepartmentEmail(String departmentEmail) {
        this.departmentEmailField.sendKeys(departmentEmail);
        logger.info("Email for new department set");
        return this;
    }

    //specify country
    public DepartmentsPage setDepartmentCountry(String departmentCountry) {
        this.departmentCountryField.sendKeys(departmentCountry);
        logger.info("Country for new department set");
        return this;
    }

    //specify city
    public DepartmentsPage setDepartmentCity(String departmentCity) {
        this.departmentCityField.sendKeys(departmentCity);
        logger.info("City for new department set");
        return this;
    }

    //specify street
    public DepartmentsPage setDepartmentStreet(String departmentStreet) {
        this.departmentStreetField.sendKeys(departmentStreet);
        logger.info("Street for new department set");
        return this;
    }

    //specify building
    public DepartmentsPage setDepartmentBuilding(String departmentBuilding) {
        this.departmentBuildingField.sendKeys(departmentBuilding);
        logger.info("Building for new department set");
        return this;
    }

    //specify zipcode
    public DepartmentsPage setDepartmentZipcode(String departmentZipcode) {
        this.departmentZipcodeField.sendKeys(departmentZipcode);
        logger.info("ZipCode for new department set");
        return this;
    }

    //specify room number
    public DepartmentsPage setDepartmentRoomNumber(String departmentRoomNumber) {
        this.departmentRoomNumberField.sendKeys(departmentRoomNumber);
        logger.info("Room Number for new department set");
        return this;
    }

    //department creation method, including all parameters
    public void createNewDepartment(String departmentTitle, String departmentPhone, String departmentSkype, String departmentWebsite,
                                    String departmentEmail, String departmentCountry, String departmentCity,
                                    String departmentStreet, String departmentBuilding, String departmentZipcode, String departmentRoomNumber) {

        logger.info("Method gets all data and creates new department is started");
        //open new department page
        clickCreateNewDepartment();
        setNewDepartmentTitle(departmentTitle);
        activateAdditionalInfo();
        setDepartmentPhone(departmentPhone);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        setDepartmentSkype(departmentSkype);
        setDepartmentWebsite(departmentWebsite);
        setDepartmentEmail(departmentEmail);
        setDepartmentCountry(departmentCountry);
        setDepartmentCity(departmentCity);
        setDepartmentStreet(departmentStreet);
        setDepartmentBuilding(departmentBuilding);
        setDepartmentZipcode(departmentZipcode);
        setDepartmentRoomNumber(departmentRoomNumber);
        clickSubmitButton();
        logger.info("Method gets all data and creates new department complete");
    }

    //method to search for a given department name
    public DepartmentsPage searchDepartment(String departmentName) throws InterruptedException {

        logger.info("Search for department '{}' started", departmentName);
        //to ensure search fields are present, wait is added
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(searchDepartmentField));

        //clear search field and perform department search
        searchDepartmentField.clear();
        searchDepartmentField.sendKeys(departmentName);
        searchDepartmentButton.click();

        //timeout to wait search is complete and all elements are shown on page
        wait.until(ExpectedConditions.visibilityOf(departmentTableTitleHeader));

        logger.info("Search for department '{}' complete", departmentName);
        return this;
    }

    //method to check that recently created department is actually present
    public DepartmentsPage checkNewDepartmentPresent(String departmentName) throws InterruptedException {

        Thread.sleep(1000);
        //before checking new department is present, wait required element is present
        wait.until(ExpectedConditions.visibilityOf(departments.get(0)));

        //check department is created with correct name
        assertEquals(departmentName, departments.get(0).getText());
        System.out.println("Department \"" + departments.get(0).getText() + "\" is present.");
        logger.info("Method which checks department '{}' is present on page executed", departmentName);
        return this;
    }

    //method to delete department of a given name
    public DepartmentsPage deleteNewDepartment(String departmentName) {

        //added verification that required department is being deleted
        if (departments.get(0).getText().equals(departmentName)) {
            this.deleteDepartmentButtons.get(0).click();
            Alert alert = driver.switchTo().alert();
            //accept deletion of a department
            alert.accept();
            System.out.println("Department \"" + departmentName + "\" is deleted.");
            logger.info("Department '{}' deleted", departmentName);
        }
        return this;
    }

    public void deleteFirstDepartment() {

        //wait to load the search results page
        wait.until(ExpectedConditions.elementToBeClickable(deleteDepartmentButtons.get(0)));
        //press delete button
        deleteDepartmentButtons.get(0).click();
        //switch control to the alert - confirmation of the company deletion
        Alert alert = driver.switchTo().alert();
        //accept deletion of a department
        alert.accept();
        //clear search field on Departments page to see all departments
        clearSearchButton.click();
        logger.info("First department in the list deleted");
    }

}





