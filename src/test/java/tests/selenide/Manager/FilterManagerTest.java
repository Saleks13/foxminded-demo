package tests.selenide.Manager;

import config.BaseChromeConfigurationSelenide;
import dataBase.DataBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.Selenide.LoginPage;
import pageObjects.Selenide.ManagersPage;
import pageObjects.Selenide.Menu;
import utils.PropertiesFile;

import java.sql.SQLException;
import java.util.HashMap;

public class FilterManagerTest extends BaseChromeConfigurationSelenide {

    DataBase dataBase = new DataBase();
    HashMap<String, String> randomManager = dataBase.getHashMap("select * " +
            "from manager where department_id IN " +
            "(select id from department where name is not NULL) " +
            "order by RANDOM() limit 1");

    //attributes of a known manager and department
    String firstName = randomManager.get("first_name");
    String lastName = randomManager.get("last_name");
    String department = dataBase.getValue("select name from department where id = " + randomManager.get("department_id"));


    public FilterManagerTest() throws SQLException {
    }

    @Test
    @DisplayName("Test to filtering Managers page via Name, Lastname, Department")
    public void filterViaNameLastnameDepartment() throws InterruptedException, SQLException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //open login page and sign in
        LoginPage loginPage = new LoginPage();
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());

        //use menu to navigate to Managers page
        Menu menu = new Menu();
        ManagersPage managersPage = menu.openManagersPage();
        //filter page and check manager is present
        managersPage.filterManagers(firstName, lastName, department)
                .checkManagerIsPresent(firstName, lastName);

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }


    @Test
    @DisplayName("Test to filtering Managers page via Department only")
    public void filterViaDepartment() throws InterruptedException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //open login page and sign in
        LoginPage loginPage = new LoginPage();
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());

        //use menu to navigate to Managers page
        Menu menu = new Menu();
        ManagersPage managersPage = menu.openManagersPage();

        //filter managers page using department name and check it's present
        managersPage.filterManagers(department)
                .checkOnlyFilteredDepartmentPresent(department);

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }

    @Test
    @DisplayName("Test to filtering Managers page via First and Last names")
    public void filterFirstAndLastnames() throws InterruptedException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //open login page and sign in
        LoginPage loginPage = new LoginPage();
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());

        //use menu to navigate to Managers page
        Menu menu = new Menu();
        ManagersPage managersPage = menu.openManagersPage();

        //filter managers page using firstname/lastname and check it's present
        managersPage.filterManagers(firstName, lastName)
                    .checkManagerIsPresent(firstName, lastName);

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }

}
