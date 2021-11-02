package tests.chrome.Contacts;

import config.BaseChromeConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.ContactsPage;
import pageObjects.LoginPage;
import pageObjects.Menu;
import utils.PropertiesFile;
import utils.RandomGenerator;

@DisplayName("Tests to check validation messages on New Contact page")
public class NewContactFieldsValidationMessagesTest extends BaseChromeConfiguration {

    //task22 part1 contains 3 negative cases for invalid mail address

    //random generator to generate variables
    RandomGenerator randomGenerator = new RandomGenerator();

    //warning message in case email is not valid
    String invalidEmailAlert = "Email must be valid";
    String requiredEmailAlert = "Email is required";
    String prefixAlert = "Tickets prefix must be from 3 to 6 characters long";
    String loginRequiredAlert = "Login is required";
    String lastNameAlert = "Last name must be at least 2 characters long.";
    String firstName = randomGenerator.generateName(5);
    String lastName = randomGenerator.generateName(7);
    String email = randomGenerator.generateEmail(5, 4);
    String login = randomGenerator.generateName(5);


    @Test
    @DisplayName("Invalid value in Email field")
    public void CreateNewContactInvalidMail() {

        logger.info(new Throwable().getStackTrace()[0].getMethodName() + " TEST STARTED");

        //this test covers cases when value in the email field is not valid

        //login to website and go to contacts page
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());
        Menu menu = new Menu(driver);
        ContactsPage contactsPage = menu.clickOnContacts();

        //go to create new contact page
        contactsPage.clickCreateNewContact();

        //variable with test data - email without @
        String emailWithNoAt = "usernamecompany.com";
        contactsPage.setEmailAddress(emailWithNoAt);
        //assert that alert is equal to required one
        Assertions.assertEquals(contactsPage.emailInvalidMessage.getText(), invalidEmailAlert);


        //variable with test data - email with invalid TLD
        String emailWithShortTLD = "username@company.c";
        contactsPage.setEmailAddress(emailWithShortTLD);
        //assert that alert is equal to required one
        Assertions.assertEquals(contactsPage.emailInvalidMessage.getText(), invalidEmailAlert);


        //variable with test data - email with no TLD
        String emailWithNoTLD = "username@company";
        contactsPage.setEmailAddress(emailWithNoTLD);
        //assert that alert is equal to required one
        Assertions.assertEquals(contactsPage.emailInvalidMessage.getText(), invalidEmailAlert);

        logger.info("TEST SUCCESSFULLY COMPLETED");
    }

    @Test
    @DisplayName("Empty Email field")
    public void CreateNewContactEmailRequired() {

        logger.info(new Throwable().getStackTrace()[0].getMethodName() + " TEST STARTED");

        //this test covers case when email field is empty

        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());
        Menu menu = new Menu(driver);
        ContactsPage contactsPage = menu.clickOnContacts();
        contactsPage.clickCreateNewContact()
                .setFirstName(firstName)
                .setLastName(lastName)
                .pressTabOnEmail();

        //assert that alert is equal to required one
        Assertions.assertEquals(contactsPage.emailRequiredMessage.getText(), requiredEmailAlert);

        logger.info("TEST SUCCESSFULLY COMPLETED");
    }

    @Test
    @DisplayName("Too short value in Prefix field")
    public void CreateNewContactShortPrefix() {

        logger.info(new Throwable().getStackTrace()[0].getMethodName() + " TEST STARTED");

        //this test covers case when tickets prefix is too short
        String shortPrefix = randomGenerator.generateName(2);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());
        Menu menu = new Menu(driver);
        ContactsPage contactsPage = menu.clickOnContacts();
        contactsPage.clickCreateNewContact()
                .setPrefix(shortPrefix);

        //assert that alert is equal to required one
        Assertions.assertEquals(contactsPage.prefixAlertMessage.getText(), prefixAlert);

        logger.info("TEST SUCCESSFULLY COMPLETED");
    }

    @Test
    @DisplayName("Empty login field")
    public void CreateNewContactLoginRequired() {

        logger.info(new Throwable().getStackTrace()[0].getMethodName() + " TEST STARTED");

        //this test covers case when login field is empty

        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());
        Menu menu = new Menu(driver);
        ContactsPage contactsPage = menu.clickOnContacts();
        contactsPage.clickCreateNewContact().clearLoginField();

        //assert that alert is equal to required one
        Assertions.assertEquals(contactsPage.loginAlertMessage.getText(), loginRequiredAlert);

        logger.info("TEST SUCCESSFULLY COMPLETED");
    }

    @Test
    @DisplayName("Too short lastname")
    public void CreateNewContactShortLastName() {

        logger.info(new Throwable().getStackTrace()[0].getMethodName() + " TEST STARTED");

        //this test covers case when last name too short
        String shortLastName = randomGenerator.generateName(1);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());
        Menu menu = new Menu(driver);
        ContactsPage contactsPage = menu.clickOnContacts();
        contactsPage
                .clickCreateNewContact()
                .setLastName(shortLastName);

        //assert that alert is equal to required one
        Assertions.assertEquals(contactsPage.lastNameAlertMessage.getText(), lastNameAlert);


        logger.info("TEST SUCCESSFULLY COMPLETED");
    }

}
