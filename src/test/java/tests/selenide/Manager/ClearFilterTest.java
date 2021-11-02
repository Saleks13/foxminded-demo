package tests.selenide.Manager;

import config.BaseChromeConfigurationSelenide;
import dataBase.DataBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.Selenide.LoginPage;
import pageObjects.Selenide.ManagersPage;
import pageObjects.Selenide.Menu;
import utils.PropertiesFile;

import static org.junit.Assert.*;

import java.util.HashMap;

public class ClearFilterTest extends BaseChromeConfigurationSelenide {

    @Test
    @DisplayName("Test to check clear filtering works")
    public void clearFilterTest() {
        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //create Database instance for further queries
        DataBase dataBase = new DataBase();

        //go to login page of the website under test and sign in
        LoginPage loginPage = new LoginPage();
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());

        //init menu for further navigation
        Menu menu = new Menu();
        //go to managers page
        ManagersPage managersPage = menu.openManagersPage();

        //hashmap which gets data related to new dummy manager
        HashMap<String, String> managerData = managersPage.createDummyManager(managersPage, true, true, true, true);

        //store the value before applying any filter
        int numberOfManagersBeforeFilter = managersPage.fullNameList.size();
        //System.out.println("Before filter = " + numberOfManagersBeforeFilter);
        //execute filtering using previously created manager
        managersPage.filterManagers(managerData.get("firstname"), managerData.get("lastname"));

        //store number of managers shown on page
        int numberOfManagersAfterFilter = managersPage.fullNameList.size();
        //System.out.println("After filter = " + numberOfManagersAfterFilter);

        //check number of managers on page differs before and after filtering
        assertNotEquals(numberOfManagersBeforeFilter, numberOfManagersAfterFilter);

        //clear filtering
        managersPage.clearFilter();

        //store number of managers present on page after clearing filtering
        int numberOfManagerAfterClearingFilter = managersPage.fullNameList.size();
        //System.out.println("After clear = " + numberOfManagerAfterClearingFilter);

        //check that number of managers before filtering equals to the number of manager after clearing filtering
        assertEquals(numberOfManagersBeforeFilter, numberOfManagerAfterClearingFilter);

        logger.info("TEST SUCCESSFULLY COMPLETED");
    }

}
