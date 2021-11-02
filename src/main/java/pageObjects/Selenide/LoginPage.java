package pageObjects.Selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import utils.PropertiesFile;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;


public class LoginPage extends BasePage{

    //list of elements present on login page
    private SelenideElement loginField = $("#username");
    private SelenideElement passwordField = $("#password");
    private SelenideElement submitButton = $("#login-signin");
    private SelenideElement usernameLogin = $(byXpath("//div/div[2]/ul/li[4]/a/p"));
    private SelenideElement popupOnLoginPage = $("#infoDescription");

    //method to sign in using username/password
    public LoginPage signIn(String username, String password) {

        logger.info("Sign in started");
        loginField.setValue(username);
        passwordField.setValue(password);
        submitButton.click();
        usernameLogin.shouldHave(Condition.text(PropertiesFile.getUsername()));
        logger.info("Sign in process complete");

        return this;
    }
}
