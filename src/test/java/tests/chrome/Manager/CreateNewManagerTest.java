package tests.chrome.Manager;

import config.BaseChromeConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.LoginPage;
import pageObjects.ManagersPage;
import pageObjects.Menu;
import utils.PropertiesFile;
import utils.RandomGenerator;

import java.net.MalformedURLException;
import java.util.HashMap;

@DisplayName("Test to check creation of Manager using UI validation")
public class CreateNewManagerTest extends BaseChromeConfiguration {


    public CreateNewManagerTest() throws MalformedURLException {
    }

    //@Test
    @DisplayName("Create new Manager")
    public void createManagerTest() {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //here is a list of keys in the future HashMap
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "emailAddress";
        String login = "login";
        String phone = "phone";
        String skype = "skype";
        String department = "department";
        String departmentValue = "Комната добра";

        //creation of RandomGeneratorPlayground object to have access to its methods
        RandomGenerator randomGenerator = new RandomGenerator();

        //create new HashMap with string key and string value
        HashMap<String, String> manager = new HashMap<>();

        //initialize LoginPage
        LoginPage loginPage = new LoginPage(driver);

        //new Menu object for further navigation
        Menu menu = new Menu(driver);

        //create a pair of key/value of required data and put is to HashMap
        manager.put(firstName, randomGenerator.generateName(6));
        manager.put(lastName, randomGenerator.generateName(10));
        manager.put(email, randomGenerator.generateEmail(6, 5));
        manager.put(login, randomGenerator.generateName(8));
        manager.put(phone, Integer.toString(randomGenerator.generateNumber(10)));
        manager.put(skype, randomGenerator.generateAlphanumeric(7));
        manager.put(department, departmentValue);

        //sign in
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());

        //go to managers page
        ManagersPage managersPage = menu.clickOnManagers();

        //create new manager using data from HashMap
        managersPage.createNewManager(manager.get(firstName), manager.get(lastName), manager.get(email),
                manager.get(login), manager.get(department), manager.get(phone), manager.get(skype));

        //search for the manager, using First and Last names
        managersPage.searchManager(manager.get(firstName), manager.get(lastName), manager.get(department));

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(managersPage.deleteButtonList.get(0)));

        //open first found manager's details page
        managersPage.openFirstManager();

        //set of asserts to check data used creation of manager is stored and represented correctly
        assert managersPage.nameOnDetailsPage.getText().contains(manager.get(firstName) + " " + manager.get(lastName));
        assert managersPage.phoneOnDetailsPage.getText().contains(manager.get(phone));
        assert managersPage.skypeOnDetailsPage.getText().contains(manager.get(skype));
        assert managersPage.emailOnDetailsPage.getText().contains(manager.get(email));
        assert managersPage.departmentOnDetailsPage.getText().contains(manager.get(department));
        assert managersPage.loginOnDetailsPage.getText().contains(manager.get(login));

        //go back to managers overview list
        managersPage.clickBackButton();

        //delete recently created manager
        //Note: deletion does not work now - BUG
        managersPage.deleteFirstManager();

        System.out.println(managersPage.fullNameList.size());
        //if assertion is true, that means deletion was successful
        Assertions.assertEquals(managersPage.fullNameList.size(),0);

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }
}
