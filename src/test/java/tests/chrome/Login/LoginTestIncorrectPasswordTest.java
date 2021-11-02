package tests.chrome.Login;

import config.BaseChromeConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.LoginPage;
import utils.PropertiesFile;

@DisplayName("Tests to check sign in with incorrect password")
public class LoginTestIncorrectPasswordTest extends BaseChromeConfiguration {

    @Test
    @DisplayName("Unsuccessful login test")
    public void loginTest() {

        String errorMessageEn = "You entered the wrong login or password. Please try again.";
        String errorMessageUa = "Ви ввели невірний логін чи пароль. Спробуйте ще раз.";
        String errorMessageUaFlaky = "Р’Рё РІРІРµР»Рё РЅРµРІС–СЂРЅРёР№ Р»РѕРіС–РЅ С‡Рё РїР°СЂРѕР»СЊ. РЎРїСЂРѕР±СѓР№С‚Рµ С‰Рµ СЂР°Р·.";

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");
        //create new instance of LoginPage class and pass driver to it
        LoginPage loginPage = new LoginPage(driver);
        //call method SignIn to execute sign in
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword()+"1");
        //System.out.println("Passing this string: " + errorMessageUa);
        loginPage.isErrorMessagePresent(errorMessageUa);
        logger.info("TEST SUCCESSFULLY COMPLETED");
    }
}
