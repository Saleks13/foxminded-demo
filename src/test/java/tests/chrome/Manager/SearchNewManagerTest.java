package tests.chrome.Manager;

import config.BaseChromeConfiguration;
import dataBase.DataBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.LoginPage;
import pageObjects.ManagersPage;
import pageObjects.Menu;
import utils.PropertiesFile;
import utils.RandomGenerator;

import java.sql.SQLException;
import java.util.HashMap;


@DisplayName("Tests to check search of Managers")
public class SearchNewManagerTest extends BaseChromeConfiguration {

    //init random generator to generate test data
    RandomGenerator randomGenerator = new RandomGenerator();
    //db instance to run queries
    DataBase dataBase = new DataBase();

    //keys-names for future hashmap
    String firstName = "firstName";
    String lastName = "lastName";
    String email = "email";
    String login = "login";
    String department = "department";
    String phone = "phone";
    String skype = "skype";

    @Test
    @DisplayName("Create and search new Manager in DB")
    public void NewManagerCreation() throws SQLException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //hashmap to store test data
        HashMap<String, String> manager = new HashMap<>();

        //fill in test data
        manager.put(firstName, randomGenerator.generateName(6));
        manager.put(lastName, randomGenerator.generateName(10));
        manager.put(email, randomGenerator.generateEmail(6,5));
        manager.put(login, randomGenerator.generateName(7));
        manager.put(department, "Call Center");
        manager.put(phone, String.valueOf(randomGenerator.generateNumber(10)));
        manager.put(skype, randomGenerator.generateName(5));

        //go to website and log in
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());
        Menu menu = new Menu(driver);

        //go to managers page using menu
        ManagersPage managersPage = menu.clickOnManagers();
        //create new manager
        managersPage.createNewManager(manager.get(firstName), manager.get(lastName), manager.get(email),
                manager.get(login), manager.get(department), manager.get(phone), manager.get(skype));

        //string with query to get phone number via full name to assert it in the future
        String queryCreateManager = "select phone from manager where first_name ='" + manager.get(firstName) +"' and last_name = '" + manager.get(lastName)+ "'";

        //assert that phone number used to create new manager equals to phone number in DB
        assert dataBase.getValue(queryCreateManager).equals(manager.get(phone));
        System.out.println("Manager with login: " + manager.get(login) + " created successfully");

    }

    @Test
    @DisplayName("Search random Manager in DB and check it's present on site")
    public void SearchManager() throws SQLException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //hashmap to store test data
        HashMap<String, String> searchData;
        //variable to store the ID of a random manager
        int id;

        //go to website and log in
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());
        Menu menu = new Menu(driver);

        //go to manager's page
        ManagersPage managersPage = menu.clickOnManagers();

        //query to get id of a random
        String queryRandomManagerId = "Select id from manager order by RANDOM() limit 1";
        id = Integer.parseInt(dataBase.getValue(queryRandomManagerId));

        //query to get test data using that id. Here we store first_name/last_name/department of one given managerId
        String query = "select m.first_name, m.last_name, d.name from manager m inner join department d on m.department_id = d.id where m.id = " + id;

        //execute query and fill hashmap with data
        searchData = dataBase.getHashMap(query);

        //search for manager on webpage using test data from query + this method contains assertion
        managersPage.searchManager(searchData.get("first_name"), searchData.get("last_name"), searchData.get("name"));

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }
}
