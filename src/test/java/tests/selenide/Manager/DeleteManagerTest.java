package tests.selenide.Manager;

import config.BaseChromeConfigurationSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.Selenide.LoginPage;
import pageObjects.Selenide.ManagersPage;
import pageObjects.Selenide.Menu;
import utils.PropertiesFile;
import utils.RandomGenerator;

import java.util.HashMap;

public class DeleteManagerTest extends BaseChromeConfigurationSelenide {

    @Test
    @DisplayName("Test to check deletion of new Manager is working properly")
    public void deleteManagerTest() {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //go to login page of the website under test and sign in
        LoginPage loginPage = new LoginPage();
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());

        //init menu for further navigation
        Menu menu = new Menu();
        //go to managers page
        ManagersPage managersPage = menu.openManagersPage();

        //hashmap which gets data related to new dummy manager
        HashMap<String, String> managerData = managersPage.createDummyManager(managersPage, true,true,true,true);

        //now goes the test - find manager using first name and lastname, delete it and check it's deleted
        managersPage.filterManagers(managerData.get("firstname"), managerData.get("lastname"))
                .checkManagerIsPresent(managerData.get("firstname"), managerData.get("lastname"))
                //deletion process
                .pressDeleteAndConfirm()
                .clearFilter()
                .filterManagers(managerData.get("firstname"), managerData.get("lastname"))
                .checkManagerIsNotPresent(managerData.get("firstname"), managerData.get("lastname"));

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }
}
