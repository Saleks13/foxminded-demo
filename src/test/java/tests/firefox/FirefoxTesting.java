package tests.firefox;

import config.BaseFirefoxConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageObjects.*;
import utils.PropertiesFile;

public class FirefoxTesting extends BaseFirefoxConfiguration {

    @BeforeEach
    public void openWebSite(){

        //load our site under test
        driver.get(PropertiesFile.getBaseUrl());
        //create new instance of LoginPage class and pass driver to it
        LoginPage login = new LoginPage(driver);
        //call method SignIn to execute sign in
        login.signIn(PropertiesFile.getUsername(),PropertiesFile.getPassword());

    }

    @AfterEach
    public  void closeBrowser(){
        driver.quit();
    }

    @Test
    public void testNewTicket() {

        //pass driver to new instance on menu class,
        // so that we may use its method for navigation
        Menu menu = new Menu(driver);

        //open tickets page
        menu.clickOnTickets();
        //pass control to the tickets page
        TicketsPage tickets = new TicketsPage(driver);
        //and open new ticket page
        tickets.clickCreateNewTicket();

    }

    @Test
    public void testNewDepartment() {

        //pass driver to new instance on menu class,
        // so that we may use its method for navigation
        Menu menu = new Menu(driver);

        //open department page
        menu.clickOnDepartment();
        //pass driver to departments page
        DepartmentsPage departments = new DepartmentsPage(driver);
        //click on new department button
        departments.clickCreateNewDepartment();
    }

    @Test
    public void testNewContact() {

        //pass driver to new instance on menu class,
        // so that we may use its method for navigation
        Menu menu = new Menu(driver);

        //using menu method open contacts page
        menu.clickOnContacts();
        //pass driver to instance of contacts page
        ContactsPage contacts = new ContactsPage(driver);
        //click on create new contact to start creation
        contacts.clickCreateNewContact();
    }

    @Test
    public void testNewCategory() {

        //pass driver to new instance on menu class,
        // so that we may use its method for navigation
        Menu menu = new Menu(driver);

        //open categories page
        menu.clickOnCategories();
        //pass driver to categories page
        CategoriesPage categories = new CategoriesPage(driver);
        //open new category creation page
        categories.clickCreateNewCategory();
    }

}
