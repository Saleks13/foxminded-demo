package tests.chrome.SecondaryTests;
import config.BaseChromeConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.*;
import utils.PropertiesFile;

@DisplayName("Tests to check Menu elements are working as expected")
public class ChromeTest extends BaseChromeConfiguration {

    @Test
    @DisplayName("Click on menu Items")
    public void loginTest() {

        //create new instance of LoginPage class and pass driver to it
        LoginPage login = new LoginPage(driver);
        //call method SignIn to execute sign in
        login.signIn(PropertiesFile.getUsername(),PropertiesFile.getPassword());

        //pass driver to new instance on menu class,
        //so that we may use its method for navigation
        Menu menu = new Menu(driver);

        //Just checking new locators are working:
        menu.clickOnDashboard();
        menu.clickOnCategories();
        menu.clickOnTickets();
        menu.clickOnCompanies();
        menu.clickOnContacts();
        menu.clickOnManagers();
        menu.clickOnDepartment();
        menu.clickOnDevices();
    }
}

