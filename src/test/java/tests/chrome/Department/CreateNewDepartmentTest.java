package tests.chrome.Department;

import config.BaseChromeConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.DepartmentsPage;
import pageObjects.LoginPage;
import pageObjects.Menu;
import utils.PropertiesFile;

import java.util.concurrent.ThreadLocalRandom;

@DisplayName("Tests to check creation of New Department")
public class CreateNewDepartmentTest extends BaseChromeConfiguration {

    @Test
    @DisplayName("Create new Department")
    public void task10Part3() throws InterruptedException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //random generator to avoid creation with same name
        int randomNum = ThreadLocalRandom.current().nextInt(10000, 99999);
        //list of variables to create new ticket
        String departmentTitle = "SA Department 3" + randomNum;
        String departmentPhone = "+380227953423";
        String departmentSkype = "department_skype_account";
        String departmentWebsite = "https://www.department.com";
        String departmentEmail = "department@gmail.com";
        String departmentCountry = "Ukraine";
        String departmentCity = "Zaporizhzhia";
        String departmentStreet = "Soborniy";
        String departmentBuilding = "168";
        String departmentZipcode = "69031";
        String departmentRoomNumber = "13";

        //load webapp and execute sign in
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());

        //create object of the menu class, for further navigation
        Menu menu = new Menu(driver);
        DepartmentsPage departmentsPage = menu.clickOnDepartment();

        //method to create new department called
        departmentsPage.createNewDepartment(departmentTitle, departmentPhone, departmentSkype,
                departmentWebsite, departmentEmail, departmentCountry, departmentCity, departmentStreet, departmentBuilding, departmentZipcode, departmentRoomNumber);

        driver.navigate().refresh();
        //search for specific department
        departmentsPage.searchDepartment(departmentTitle);
        //check required department is present
        departmentsPage.checkNewDepartmentPresent(departmentTitle);
        //delete first found department
        departmentsPage.deleteFirstDepartment();

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }
}
