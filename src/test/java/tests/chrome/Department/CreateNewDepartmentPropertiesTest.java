package tests.chrome.Department;

import config.BaseChromeConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.*;
import utils.PropertiesFile;

@DisplayName("Tests to check creation of New Department")
public class CreateNewDepartmentPropertiesTest extends BaseChromeConfiguration {

    @Test
    @DisplayName("Create new Department (data from properties file)")
    public void loginTest() throws InterruptedException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //create new instance of LoginPage class and pass driver to it
        LoginPage login = new LoginPage(driver);
        //call method SignIn to execute sign in
        login.signIn(PropertiesFile.getUsername(),PropertiesFile.getPassword());

        //initialize menu bar to switch between pages
        Menu menu = new Menu(driver);

        WebDriverWait wait1 = new WebDriverWait(driver, 10);
        wait1.until(ExpectedConditions.visibilityOf(menu.departments));

        DepartmentsPage departmentsPage = menu.clickOnDepartment();

        departmentsPage.clickCreateNewDepartment();
        departmentsPage.createNewDepartment(PropertiesFile.getDepartmentName());

        wait1.until(ExpectedConditions.visibilityOf(departmentsPage.searchDepartmentField));

        departmentsPage.searchDepartment(PropertiesFile.getDepartmentName());
        departmentsPage.checkNewDepartmentPresent(PropertiesFile.getDepartmentName());
        departmentsPage.deleteNewDepartment(PropertiesFile.getDepartmentName());

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }
}