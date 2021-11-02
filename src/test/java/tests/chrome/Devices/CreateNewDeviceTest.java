package tests.chrome.Devices;

import config.BaseChromeConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.DevicesPage;
import pageObjects.LoginPage;
import pageObjects.Menu;
import pageObjects.NewDevicePage;
import utils.PropertiesFile;

@DisplayName("Tests to check creation of new Device")
public class CreateNewDeviceTest extends BaseChromeConfiguration {

    @Test
    @DisplayName("Create new Device (build pattern)")
    public void task14() {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //load site and login page
        LoginPage loginPage = new LoginPage(driver);
        //perform sign in action
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());
        //init menu object for navigation
        Menu menu = new Menu(driver);

        DevicesPage devicesPage = menu.clickOnDevices();
        devicesPage.clickOnNewDeviceButton();

        NewDevicePage newDevicePage = new NewDevicePage.Builder()
                .setDeviceTitle("Test")
                .setDeviceIp("123.213.132.112")
                .setDevicePort(222)
                .setDevicePassword("123456Ab")
                .setDeviceCompany("Without company")
                .setDeviceContact("Alex QA")
                .setDeviceNotifications(false)
                .build();

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }
}
