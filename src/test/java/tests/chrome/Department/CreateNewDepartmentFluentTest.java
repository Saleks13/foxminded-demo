package tests.chrome.Department;

import config.BaseChromeConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.DepartmentsPage;
import pageObjects.LoginPage;
import pageObjects.Menu;
import utils.PropertiesFile;

import java.net.MalformedURLException;

@DisplayName("Tests to check creation of New Department using fluent pattern")
public class CreateNewDepartmentFluentTest extends BaseChromeConfiguration {

    public CreateNewDepartmentFluentTest() throws MalformedURLException {
    }

    @Test
    @DisplayName("Create new Department")
    public void fluentTesting() throws InterruptedException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //list of variables required for department creation
        String departmentTitle = "departSA";
        String departmentPhone = "1234567890";
        String departmentSkype = "dep_skype";
        String departmentWebsite = "https://department.com";
        String departmentEmail = "department@department.com";
        String departmentCountry = "Ukraine";
        String departmentCity = "Kyiv";
        String departmentStreet = "Perova";
        String departmentBuilding = "13";
        String departmentZipcode = "02200";
        String departmentRoomNumber = "27";

        //load site and login page
        LoginPage loginPage = new LoginPage(driver);
        //perform sign in action
        loginPage.signIn(PropertiesFile.getUsername(),PropertiesFile.getPassword());
        //init menu object for navigation
        Menu menu = new Menu(driver);

        //init department page by clicking department item in the menu
        DepartmentsPage departmentsPage = menu.clickOnDepartment();

        //create new department using fluent style
        departmentsPage
                .clickCreateNewDepartment()
                .setNewDepartmentTitle(departmentTitle)
                .activateAdditionalInfo()
                .setDepartmentPhone(departmentPhone)
                .setDepartmentSkype(departmentSkype)
                .setDepartmentWebsite(departmentWebsite)
                .setDepartmentEmail(departmentEmail)
                .setDepartmentCountry(departmentCountry)
                .setDepartmentCity(departmentCity)
                .setDepartmentStreet(departmentStreet)
                .setDepartmentBuilding(departmentBuilding)
                .setDepartmentZipcode(departmentZipcode)
                .setDepartmentRoomNumber(departmentRoomNumber)
                .clickSubmitButton()
                .searchDepartment(departmentTitle)
                .checkNewDepartmentPresent(departmentTitle)
                .deleteNewDepartment(departmentTitle);

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }
}
