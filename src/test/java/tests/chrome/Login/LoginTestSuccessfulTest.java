package tests.chrome.Login;

import config.BaseChromeConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.LoginPage;
import utils.PropertiesFile;

//@RunWith(JUnitPlatform.class)
@DisplayName("A set of test cases with successful sign ins")
public class LoginTestSuccessfulTest extends BaseChromeConfiguration {

    @Test
    @DisplayName("Successful login test")
    public void loginTest() {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");
        //create new instance of LoginPage class and pass driver to it
        LoginPage loginPage = new LoginPage(driver);
        //call method SignIn to execute sign in
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());
        loginPage.checkLoginSuccessful(PropertiesFile.getUsername());
        logger.info("TEST SUCCESSFULLY COMPLETED");
    }
}
