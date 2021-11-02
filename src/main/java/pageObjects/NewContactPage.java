package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewContactPage extends BasePage {

    //list of webelements required to create new contact
    @FindBy(id = "firstName")
    WebElement newContactFirstName;

    @FindBy(id = "lastName")
    WebElement newContactLastName;

    @FindBy(id = "email")
    WebElement newContactEmail;

    @FindBy(id = "contact-form-submit")
    WebElement newContactSubmitButton;

    @FindBy(id = "last-name")
    WebElement lastNameSearchField;

    @FindBy(id = "new-contact")
    WebElement newContactButton;

    //create new instance of wait object to use it when it's required to wait for some elements
    WebDriverWait wait = new WebDriverWait(driver, 15);

    //constructor only with driver in arguments
    public NewContactPage(WebDriver driver) {
        super(driver);
    }

    //constructor to create new contact
    public NewContactPage(WebDriver driver, String contactName, String contactLastName, String contactEmail) {

        //refer to driver from BasePage class
        super(driver);

        logger.info("Method gets all data and creates new contact - started");

        //wait first name field is shown
        wait.until(ExpectedConditions.visibilityOf(newContactFirstName));

        //fill in first name field
        newContactFirstName.clear();
        newContactFirstName.sendKeys(contactName);

        //wait last name field is shown
        wait.until(ExpectedConditions.visibilityOf(newContactLastName));

        //fill in last name field
        newContactLastName.clear();
        newContactLastName.sendKeys(contactLastName);

        //wait email field is shown
        wait.until(ExpectedConditions.visibilityOf(newContactEmail));

        newContactEmail.clear();
        newContactEmail.sendKeys(contactEmail);

        //click submit button
        wait.until(ExpectedConditions.elementToBeClickable(newContactSubmitButton));
        newContactSubmitButton.click();

        logger.info("Method to create new contact - completed");

    }

    //builder class to fill in the NewContactClass
    public static class Builder {
        private String contactName;
        private String contactLastName;
        private String contactEmail;

        //create new instance of wait object to use it when it's required to wait for some elements
        WebDriverWait wait = new WebDriverWait(driver, 15);

        public Logger logger = LogManager.getLogger(getClass());

        //empty constructor method
        public Builder() {
        }

        //setter for Contact name
        public Builder setContactName(String contactName) {
            this.contactName = contactName;
            logger.info("Name of new contact set");
            return this;
        }

        //setter for Contact last name
        public Builder setContactLastName(String contactLastName) {
            this.contactLastName = contactLastName;
            logger.info("Last name of new contact set");
            return this;
        }

        //setter for Contact email
        public Builder setContactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
            logger.info("Email of new contact set");
            return this;
        }

        //method to create new instance of NewContactPage with filled arguments
        public NewContactPage build() {
            return new NewContactPage(driver, contactName, contactLastName, contactEmail);
        }


    }

}
