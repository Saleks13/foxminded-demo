package tests.chrome.Contacts;

import config.BaseChromeConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.ContactsPage;
import pageObjects.LoginPage;
import pageObjects.Menu;
import pageObjects.NewContactPage;
import utils.PropertiesFile;
import utils.RandomGenerator;

public class CreateNewContactTest extends BaseChromeConfiguration {

    @Test
    @DisplayName("Create new Contact and check it's present on UI")
    public void task14BuilderNewContact() throws InterruptedException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //load site and login page
        LoginPage loginPage = new LoginPage(driver);
        //perform sign in action
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());
        //init menu object for navigation
        Menu menu = new Menu(driver);
        RandomGenerator randomGenerator = new RandomGenerator();

        //generate random value for the test data:
        String newContactName = randomGenerator.generateName(6);
        String newContactLastName = randomGenerator.generateName(10);
        String newContactEmail = randomGenerator.generateEmail(6, 8);

        //open contact page
        ContactsPage contactsPage = menu.clickOnContacts();
        //open new contact page
        contactsPage.clickCreateNewContact();

        //create new instance of new contact page and create new contact using constructor with help of builder
        new NewContactPage.Builder()
                .setContactName(newContactName)
                .setContactLastName(newContactLastName)
                .setContactEmail(newContactEmail)
                .build();

        //contactsPage.searchContact(newContactName,newContactLastName);
        contactsPage
                .searchContact(newContactName,newContactLastName)
                .checkFirstContactByFullname(newContactName, newContactLastName)
                .deleteFirstContact();

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }
}