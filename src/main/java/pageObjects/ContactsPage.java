package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.WatchEvent;
import java.util.List;

public class ContactsPage extends BasePage {

    public ContactsPage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, 10);

    @FindBy(id = "first-name")
    WebElement firstNameSearchField;

    @FindBy(id = "last-name")
    WebElement lastNameSearchField;

    @FindBy(id = "search-contacts")
    WebElement searchContactButton;

    @FindBy(id = "clear-search-contact")
    WebElement clearSearchButton;

    @FindBy(id = "new-contact")
    WebElement newContactButton;

    @FindBy(xpath = "//div/div/table/tbody/tr/td[1]")
    List<WebElement> fullNameList;

    @FindBy(xpath = "//div/div/table/tbody/tr[2]/td[1]")
    WebElement firstFullNameInList;

    @FindBy(xpath = "//div/div/table/tbody/tr/td[2]")
    List<WebElement> companyNameList;

    @FindBy(id = "delete-btn")
    List<WebElement> deleteButtons;

    @FindBy(id = "firstName")
    WebElement firstNameField;

    @FindBy(id = "lastName")
    WebElement lastNameField;

    @FindBy(xpath = "//div[2]/div/div/div[1]/div[3]")
    public WebElement lastNameAlertMessage;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "login")
    WebElement loginField;

    @FindBy(xpath = "//div[5]/div/div/div/div/div[1]")
    public WebElement loginAlertMessage;

    @FindBy(xpath = "//div[4]/div/div/div/div[1]/div[2]")
    public WebElement emailInvalidMessage;

    @FindBy(xpath = "//div[4]/div/div/div/div[1]/div[1]")
    public WebElement emailRequiredMessage;

    @FindBy(id = "ticketPrefix")
    WebElement prefixField;

    @FindBy(xpath = "//div/form/div[13]/div[1]/div/div/div/div[3]")
    public WebElement prefixAlertMessage;

    @FindBy(id = "edit-btn")
    List<WebElement> editButtonList;

    @FindBy(xpath = "//div/div[2]/a/i")
    WebElement editEmailIcon;

    @FindBy(id = "addition-info")
    WebElement additionalInfoLink;

    @FindBy(id = "country")
    WebElement countryField;

    @FindBy(id = "street")
    WebElement streetField;

    @FindBy(id = "zipcode")
    WebElement zipCodeField;

    @FindBy(id = "phone")
    WebElement phoneField;

    @FindBy(id = "website")
    WebElement webSiteField;

    @FindBy(id = "city")
    WebElement cityField;

    @FindBy(id = "building")
    WebElement buildingField;

    @FindBy(id = "roomNumber")
    WebElement roomNumberField;

    @FindBy(id = "skype")
    WebElement skypeField;

    @FindBy(id = "jobPosition")
    WebElement jobPositionField;

    @FindBy(id = "notify-on-stage-change")
    WebElement notifyOnStageChangeCheckbox;

    @FindBy(id = "notify-on-done-stage")
    WebElement notifyOnDoneStageCheckbox;

    @FindBy(id = "notify-on-new-comment")
    WebElement notifyOnNewComment;

    //on edit page, the OK button to confirm email editing
    //and button to submit new changes to Contact
    //has the same ID, so buttons will be separated by caption
    @FindBy(id = "contact-form-submit")
    List<WebElement> submitButtonList;




    //click new contact to open new contact page
    public ContactsPage clickCreateNewContact() {
        wait.until(ExpectedConditions.visibilityOf(newContactButton));
        this.newContactButton.click();
        logger.info("New contact page opened");
        return this;
    }

    public ContactsPage clickEditContactButton() {

        logger.info("Method to open first contact on page is started");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(editButtonList.get(0)));
            editButtonList.get(0).click();
            wait.until(ExpectedConditions.visibilityOf(firstNameField));
            logger.info("Contact edit page is opened");
        } catch (Exception e) {
            logger.warn("Edit button is not available");
        }
        return this;
    }

    public ContactsPage clickEmailEditIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(editEmailIcon));
        editEmailIcon.click();
        logger.info("Dialog to edit email opened");
        return this;
    }

    public ContactsPage clickOkForEditingEmailAddress() {

        for (int i = 0; i < submitButtonList.size(); i++) {
            if (submitButtonList.get(i).getText().equals("OK")) {
                submitButtonList.get(i).click();
            }
        }
        logger.info("Editing of email on Contacts page is confirmed");
        return this;
    }

    public ContactsPage clickSubmitOnEditingContact() {

        wait.until(ExpectedConditions.visibilityOf(submitButtonList.get(0)));
        submitButtonList.get(0).click();
        logger.info("New changes to Contact info stored");
        return this;
    }

    public ContactsPage clickOnAdditionalInformation() {
        wait.until(ExpectedConditions.elementToBeClickable(additionalInfoLink));
        additionalInfoLink.click();
        logger.info("Additional info area on Contact's edit page is opened");
        return this;
    }

    public ContactsPage setCountry(String country) {
        wait.until(ExpectedConditions.visibilityOf(countryField));
        countryField.sendKeys(country);
        logger.info("Country is set");
        return this;
    }

    public ContactsPage setStreet(String street) {
        wait.until(ExpectedConditions.visibilityOf(streetField));
        streetField.sendKeys(street);
        logger.info("Street is set");
        return this;
    }

    public ContactsPage setZipcode(String zipcode) {
        wait.until(ExpectedConditions.visibilityOf(zipCodeField));
        zipCodeField.sendKeys(zipcode);
        logger.info("Zipcode is set");
        return this;
    }

    public ContactsPage setPhone(String phone) {
        wait.until(ExpectedConditions.visibilityOf(phoneField));
        phoneField.sendKeys(phone);
        logger.info("Phone is set");
        return this;
    }

    public ContactsPage setWebsite(String website) {
        wait.until(ExpectedConditions.visibilityOf(webSiteField));
        webSiteField.sendKeys(website);
        logger.info("Website is set");
        return this;
    }

    public ContactsPage setCity(String city) {
        wait.until(ExpectedConditions.visibilityOf(cityField));
        cityField.sendKeys(city);
        logger.info("City is set");
        return this;
    }

    public ContactsPage setBuilding(String building) {
        wait.until(ExpectedConditions.visibilityOf(buildingField));
        buildingField.sendKeys(building);
        logger.info("Building is set");
        return this;
    }

    public ContactsPage setRoomNumber(String roomNumber) {
        wait.until(ExpectedConditions.visibilityOf(roomNumberField));
        roomNumberField.sendKeys(roomNumber);
        logger.info("Room number is set");
        return this;
    }

    public ContactsPage setSkype(String skype) {
        wait.until(ExpectedConditions.visibilityOf(skypeField));
        skypeField.sendKeys(skype);
        logger.info("Skype is set");
        return this;
    }

    public ContactsPage setJobPosition(String jobPosition) {
        wait.until(ExpectedConditions.visibilityOf(jobPositionField));
        jobPositionField.sendKeys(jobPosition);
        logger.info("Job position is set");
        return this;
    }

    public ContactsPage setFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        logger.info("Contact fist name set");
        return this;
    }

    public ContactsPage setLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameField));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        logger.info("Contact lastname set");
        return this;
    }

    public ContactsPage setEmailAddress(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);
        logger.info("Contact email set");
        return this;
    }

    public ContactsPage pressTabOnEmail() {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(Keys.TAB);
        logger.info("Contact email skipped by Tab button");
        return this;
    }

    public ContactsPage setLoginName(String login) {
        wait.until(ExpectedConditions.visibilityOf(loginField));
        loginField.clear();
        loginField.sendKeys(login);
        logger.info("Contact login set");
        return this;
    }

    public ContactsPage clearLoginField() {
        wait.until(ExpectedConditions.visibilityOf(loginField));
        loginField.clear();
        loginField.sendKeys(Keys.TAB);
        logger.info("Login field cleared and Tab button pressed");
        return this;
    }

    public ContactsPage setPrefix(String prefix) {
        wait.until(ExpectedConditions.visibilityOf(prefixField));
        prefixField.clear();
        prefixField.sendKeys(prefix);
        prefixField.sendKeys(Keys.TAB);
        logger.info("Prefix set");
        return this;
    }


    //method to search for contact using first and last names
    public ContactsPage searchContact(String firstName, String lastName) throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(firstNameSearchField));
        wait.until(ExpectedConditions.visibilityOf(lastNameSearchField));
        //specify search values
        firstNameSearchField.sendKeys(firstName);
        lastNameSearchField.sendKeys(lastName);
        //click on filter button
        searchContactButton.click();
        Thread.sleep(1000);
        //wait until delete button is shown
        wait.until(ExpectedConditions.visibilityOf(firstFullNameInList));
        //one more wait to ensure elements are there
        logger.info("Method to search contact executed");
        return this;

    }

    public ContactsPage checkFirstContactByFullname(String firstName, String lastName) throws InterruptedException {

        Thread.sleep(1000);
        assert firstFullNameInList.getText().contains(firstName);
        assert firstFullNameInList.getText().contains(lastName);
        System.out.println(firstFullNameInList.getText());

        //show message with name of the contact in the console
        System.out.println("Contact \"" + firstFullNameInList.getText() + "\" is present.");
        wait.until(ExpectedConditions.visibilityOf(deleteButtons.get(0)));
        logger.info("Method to assert that found contact present - executed");

        return this;

    }

    public ContactsPage deleteFirstContact() {


        //click on delete button
        deleteButtons.get(0).click();
        //switch control to the alert - confirmation of the company deletion
        Alert alert = driver.switchTo().alert();
        //accept deletion of a department
        alert.accept();
        //clear search field on Departments page to see all departments
        clearSearchButton.click();
        logger.info("First contact deleted");
        return this;
    }


}
