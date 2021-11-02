package tests.selenide.Manager;

import com.codeborne.selenide.Condition;
import config.BaseChromeConfigurationSelenide;
import dataBase.DataBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.Selenide.LoginPage;
import pageObjects.Selenide.ManagersPage;
import pageObjects.Selenide.Menu;
import utils.PropertiesFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class SortingTest extends BaseChromeConfigurationSelenide {

    @Test
    @DisplayName("Test to check sorting works")
    public void sortingViaNameTest() {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //go to login page of the website under test and sign in
        LoginPage loginPage = new LoginPage();
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());

        //init menu for further navigation
        Menu menu = new Menu();
        //go to managers page
        ManagersPage managersPage = menu.openManagersPage();

        //counter of managers. First adding number of managers on page #1
        int numberOfManagersOnAllPages = managersPage.fullNameList.size();

        //to know how many times to click Next link, we need to know how many pages we have
        int numberOfPages = managersPage.getNumberOfPages();

        //list of full names stores all managers
        List<String> listOfManagersAsc;

        //list of full names stores all managers
        List<String> listOfManagersDesc;

        //to get focus on page click on clear button - otherwise left side navigation menu may overlap the sorting controls
        managersPage.clearFilter();
        //sort the list Asc
        managersPage.sortAsc();
        //get the list of all managers
        listOfManagersAsc = managersPage.getListOfManagersOnAllPages();

        //to clear sorting and pagination press clear filter
        managersPage.clearFilter();
        //sort the list Desc
        managersPage.sortDesc();
        //get the list of all managers
        listOfManagersDesc = managersPage.getListOfManagersOnAllPages();

        //reverse Asc list
        Collections.reverse(listOfManagersAsc);

        //assert that reversed Asc list equals Desc list
        assertEquals(listOfManagersAsc, listOfManagersDesc);
        //GETTING ASSERTION ERROR, BECAUSE DESC WORKS INCORRECT

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }
}
