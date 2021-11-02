package tests.selenide.Manager;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.conditions.Checked;
import config.BaseChromeConfigurationSelenide;
import dataBase.DataBase;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.Selenide.LoginPage;
import pageObjects.Selenide.ManagersPage;
import pageObjects.Selenide.Menu;
import utils.PropertiesFile;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.HashMap;

public class EditManagerTest extends BaseChromeConfigurationSelenide {

    @Test
    @DisplayName("Test to check edit of Manager info is working properly")
    public void editManagerFields() throws SQLException {

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

        //list of variables to use when editing the manager data
        String firstname = managerData.get("firstname") + "1";
        String lastname = managerData.get("lastname") + "1";
        String email = managerData.get("email") + "1";
        String department = dataBase.getValue("select name from department where name not in ('" + managerData.get("department") + "', 'Scheduler_System_Department') and deleted is false order by RANDOM() limit 1");
        String phone = managerData.get("phone") + "1";
        String skype = managerData.get("skype") + "1";
        Boolean admin = !Boolean.parseBoolean(managerData.get("admin"));
        Boolean notificationOnStageChange = !Boolean.parseBoolean(managerData.get("notificationOnStageChange"));
        Boolean notificationOnAssignToTicket = !Boolean.parseBoolean(managerData.get("notificationOnAssignToTicket"));
        Boolean notificationOnNewComment = !Boolean.parseBoolean(managerData.get("notificationOnNewComment"));

        //filter managers page to find previously created manager
        managersPage.filterManagers(managerData.get("firstname"), managerData.get("lastname"));
        //open manager for editing
        managersPage.clickOnEditButton();
        //set new values and store them
        managersPage.editManager(firstname, lastname, email, department, phone, skype, admin, notificationOnStageChange, notificationOnAssignToTicket, notificationOnNewComment);

        //clear filtering, find manager using new names and open details page
        managersPage.clearFilter();
        managersPage.filterManagers(firstname, lastname, department);
        managersPage.openDetailsPage(firstname, lastname);

        //validate new values on details page
        managersPage.nameOnDetailsPageClass.get(0).shouldHave(Condition.text(firstname), Condition.text(lastname));
        managersPage.phoneOnDetailsPage.shouldHave(Condition.text(phone));
        managersPage.skypeOnDetailsPage.shouldHave(Condition.text(skype));
        managersPage.emailOnDetailsPage.shouldHave(Condition.text(email));
        managersPage.departmentOnDetailsPage.shouldHave(Condition.text(department));

        //open edit page from details page
        managersPage.clickOnEditButtonOnDetailsPage();

        //validate checkboxes on edit page
        assertEquals(managersPage.adminCheckboxID.isSelected(), admin);
        assertEquals(managersPage.notificationOnStageChangeCheckboxId.isSelected(), notificationOnStageChange);
        assertEquals(managersPage.notificationOnAssignToTicketCheckboxId.isSelected(), notificationOnAssignToTicket);
        assertEquals(managersPage.notificationOnNewCommentCheckboxId.isSelected(), notificationOnNewComment);

        logger.info("TEST SUCCESSFULLY COMPLETED");
    }

}
