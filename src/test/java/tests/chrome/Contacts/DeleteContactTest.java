package tests.chrome.Contacts;

import config.BaseChromeConfiguration;
import dataBase.DataBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.ContactsPage;
import pageObjects.LoginPage;
import pageObjects.Menu;
import pageObjects.NewContactPage;
import utils.PropertiesFile;
import utils.RandomGenerator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@DisplayName("Tests to check that deletion of Contacts works")
public class DeleteContactTest extends BaseChromeConfiguration {

    @Test
    @DisplayName("Test checks deletion of new Contact")
    public void editContactTest() throws SQLException, InterruptedException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //init random generator
        RandomGenerator randomGenerator = new RandomGenerator();
        //generate random value for the test data:
        String newContactName = randomGenerator.generateName(6);
        String newContactLastName = randomGenerator.generateName(10);
        String newContactEmail = randomGenerator.generateEmail(6, 8);


        //open login form of the required website
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());
        //init menu for further navigation
        Menu menu = new Menu(driver);
        //go to contacts page
        ContactsPage contactsPage = menu.clickOnContacts();

        //open new contact page
        contactsPage.clickCreateNewContact();

        //create new instance of new contact page and create new contact using constructor with help of builder
        new NewContactPage.Builder()
                .setContactName(newContactName)
                .setContactLastName(newContactLastName)
                .setContactEmail(newContactEmail)
                .build();

        //init db object
        DataBase dataBase = new DataBase();
        HashMap<String, String> conditions = new HashMap<>();
        conditions.put("first_name", newContactName);
        conditions.put("last_name", newContactLastName);

        ResultSet rs = dataBase.selectResultSet("contact", conditions);
        String id = dataBase.getValue(rs, "id");
        //this means that contact is created and exists in DB
        System.out.println("ID is :" + id);
        Assertions.assertNotNull(id);

        //search for recently created contact
        contactsPage.searchContact(newContactName, newContactLastName);

        //delete contact using UI
        contactsPage.deleteFirstContact();
        Thread.sleep(1000);

        //run query to get the value of Delete column of the contact
        rs = dataBase.selectResultSet("contact", conditions);
        String deleted = dataBase.getValue(rs, "deleted");

        //this means that deleted value if True, which means contact was deleted
        Assertions.assertEquals("t", deleted);

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }
}
