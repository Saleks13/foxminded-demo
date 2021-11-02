package pageObjects;

import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.sound.midi.Soundbank;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "username")
    WebElement loginField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "login-signin")
    WebElement submitButton;

    @FindBy(xpath = "//div/div[2]/ul/li[4]/a/p")
    WebElement usernameLogin;

    @FindBy(id = "infoDescription")
    WebElement popupOnLoginPage;

    WebDriverWait wait = new WebDriverWait(driver, 15);

    public void signIn(String username, String password) {

        try {
            //wait till submit button visible to be sure page loaded
            wait.until(ExpectedConditions.visibilityOf(submitButton));
            //specify username using function to get values from Properties
            loginField.sendKeys(username);
            //specify password using function to get values from Properties
            passwordField.sendKeys(password);
            //click on submit button to initiate sign in process
            submitButton.click();
            logger.info("Sign in method executed with user '{}' ", username);
        } catch (Exception e) {
            logger.error("Error during sign in");
        }
    }

    public void checkLoginSuccessful(String username) {

        wait.until(ExpectedConditions.visibilityOf(usernameLogin));
        try {
            assertEquals(usernameLogin.getText(), username);
            logger.info("Username '{}' is present on the page", username);
        } catch (Exception e) {
            logger.error("Sign in is not successful");
        }
    }

    public void isErrorMessagePresent(String errorMessage){
        wait.until(ExpectedConditions.visibilityOf(popupOnLoginPage));
        try {
            String actualMessage = popupOnLoginPage.getText();
            //System.out.println("Actual message is: " + actualMessage);
            //System.out.println("Expected message is: " + errorMessage);
            assertEquals(errorMessage, actualMessage);
            //assertEquals(popupOnLoginPage.getText(), errorMessage);
            //System.out.println("Message is:" +popupOnLoginPage.getText());
            logger.info("Sign in failed and error message '{}' shown", popupOnLoginPage.getText());
        } catch (Exception e) {
            logger.error("Something went wrong");
        }
    }

}
