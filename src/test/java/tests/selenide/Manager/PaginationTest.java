package tests.selenide.Manager;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.conditions.webdriver.Url;
import config.BaseChromeConfigurationSelenide;
import dataBase.DataBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.Selenide.LoginPage;
import pageObjects.Selenide.ManagersPage;
import pageObjects.Selenide.Menu;
import utils.PropertiesFile;

import java.net.MalformedURLException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class PaginationTest extends BaseChromeConfigurationSelenide {

    @Test
    @DisplayName("Test to check pagination works")
    public void checkPagingWorksTest() throws SQLException, MalformedURLException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //go to login page of the website under test and sign in
        LoginPage loginPage = new LoginPage();
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());

        //init menu for further navigation
        Menu menu = new Menu();
        //go to managers page
        ManagersPage managersPage = menu.openManagersPage();

        //use Database utility to get number of Managers
        DataBase database = new DataBase();
        int numberOfManagersInDb = Integer.parseInt(database.getValue("select count (first_name) from manager where deleted = false and is_fake = false"));

        //counter of managers. First adding number of managers on page #1
        int numberOfManagersOnAllPages = managersPage.getNumberOfAllManagers();

        //assert that number of managers obtained from DB equals number of managers obtained from FE
        assertEquals(numberOfManagersInDb, numberOfManagersOnAllPages);

        logger.info("TEST SUCCESSFULLY COMPLETED");
    }


}
