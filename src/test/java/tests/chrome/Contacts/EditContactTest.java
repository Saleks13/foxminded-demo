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

import java.sql.SQLException;

@DisplayName("Tests to check that Editing of Contact works")
public class EditContactTest extends BaseChromeConfiguration {

    @Test
    @DisplayName("Test checks that editing of Contact works properly")
    public void editContactTest() throws SQLException, InterruptedException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //init random generator
        RandomGenerator randomGenerator = new RandomGenerator();
        //generate random value for the test data:
        String newContactName = randomGenerator.generateName(6);
        String newContactLastName = randomGenerator.generateName(10);
        String newContactEmail = randomGenerator.generateEmail(6, 8);
        String country = randomGenerator.generateName(8);
        String street = randomGenerator.generateName(6);
        String zipcode = Integer.toString(randomGenerator.generateNumber(5));
        String phone = Integer.toString(randomGenerator.generateNumber(9));
        String website = "www." + randomGenerator.generateName(9) + ".com";
        String city = randomGenerator.generateName(7);
        String building = Integer.toString(randomGenerator.generateNumber(3));
        String roomNumber = Integer.toString(randomGenerator.generateNumber(2));
        String skype = randomGenerator.generateName(12);
        String jobPosition = "QA";

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

        //new variables based on current ones
        String editedContactFirstName = newContactName + "edit";
        String editedContactLastName = newContactLastName + "edit";
        String editedEmailAddress = randomGenerator.generateEmail(5, 5);

        //search for contact, open it and edit using new variables
        contactsPage.searchContact(newContactName, newContactLastName)
                .clickEditContactButton()
                .setFirstName(editedContactFirstName)
                .setLastName(editedContactLastName)
                .clickEmailEditIcon()
                .setEmailAddress(editedEmailAddress)
                .clickOkForEditingEmailAddress()
                .clickOnAdditionalInformation()
                .setCountry(country)
                .setStreet(street)
                .setZipcode(zipcode)
                .setPhone(phone)
                .setWebsite(website)
                .setCity(city)
                .setBuilding(building)
                .setRoomNumber(roomNumber)
                .setSkype(skype)
                .setJobPosition(jobPosition)
                .clickSubmitOnEditingContact()
                //make sure that new values are saved:
                .checkFirstContactByFullname(editedContactFirstName, editedContactLastName)
                //to avoid redundant data deleting the contact:
                .deleteFirstContact();

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }
}
