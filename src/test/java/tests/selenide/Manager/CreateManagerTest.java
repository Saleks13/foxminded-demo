package tests.selenide.Manager;

import config.BaseChromeConfigurationSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pageObjects.Selenide.LoginPage;
import pageObjects.Selenide.ManagersPage;
import pageObjects.Selenide.Menu;
import utils.PropertiesFile;
import utils.RandomGenerator;

public class CreateManagerTest extends BaseChromeConfigurationSelenide {

    @Test
    @DisplayName("Test to check creation of new Manager is working properly")
    public void createManager() throws InterruptedException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //random generator to generate test data
        RandomGenerator randomGenerator = new RandomGenerator();

        //variables to store test data
        String firstname = randomGenerator.generateName(6);
        String lastName = randomGenerator.generateName(8);
        String email = randomGenerator.generateEmail(5, 6);
        String login = randomGenerator.generateName(6);
        String department = "Call Center";
        String phone = randomGenerator.generateAlphanumeric(9);
        String skype = randomGenerator.generateName(5);

        //open login page and sign in
        LoginPage loginPage = new LoginPage();
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());

        //use menu to navigate to Managers page and open New Manager creation page
        Menu menu = new Menu();
        ManagersPage managersPage = menu.openManagersPage();
        managersPage.createNewManagerButton();

        //set of steps to create new manager
        managersPage.setFirstName(firstname)
                .setLastName(lastName)
                .setEmail(email)
                .setLogin(login)
                .selectDepartment(department)
                .setPhone(phone)
                .setSkype(skype)
                .setAdminOption(false)
                .setNotificationOnStageChange(false)
                .setNotificationOnAssignToTicket(true)
                .setNotificationOnNewComment(false)
                .pressSubmitButton()
                .filterManagers(firstname, lastName, department)
                .checkManagerIsPresent(firstname, lastName);

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }
}
