package tests.chrome.Manager;

import config.BaseChromeConfiguration;
import dataBase.DataBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.LoginPage;
import pageObjects.ManagersPage;
import pageObjects.Menu;
import utils.PropertiesFile;
import utils.RandomGenerator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@DisplayName("Tests to check creation of new Manager via DB validation")
public class CreateNewManagerDbCheckTest extends BaseChromeConfiguration {

    RandomGenerator randomGenerator = new RandomGenerator();
    //db instance to run queries
    DataBase dataBase = new DataBase();

    String name = randomGenerator.generateName(6);
    String lastName = randomGenerator.generateName(9);
    String email = randomGenerator.generateEmail(7, 8);
    String login = randomGenerator.generateName(8);
    String department = "Call Center";
    String phone = Integer.toString(randomGenerator.generateNumber(3) + randomGenerator.generateNumber(6));
    String skype = randomGenerator.generateName(7);


    @Test
    @DisplayName("Create new Manager test and check in DB")
    public void CreateManager() throws SQLException {

        logger.info(new Throwable().getStackTrace()[0].getMethodName() + " test started");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());
        Menu menu = new Menu(driver);
        ManagersPage managersPage = menu.clickOnManagers();
        managersPage.createNewManager(name, lastName, email, login, department, phone, skype);
        managersPage.searchManager(name, lastName, department);

        //this hashmap stores conditions for the future query to DB
        HashMap<String, String> conditions = new HashMap<>();
        conditions.put("first_name", name);
        conditions.put("last_name", lastName);
        String departmentId = dataBase.getValue("select id from department where name = '" + department + "'");
        conditions.put("department_id", departmentId);

        //getting result set from Manager DB using conditions above
        ResultSet rs = dataBase.selectResultSet("manager", conditions);

        //converting result to a list of hashmaps, so it's easier to get values
        List<HashMap<String,String>> resultList= dataBase.convertToListHashMap(rs);

        //getting values of our input parameters but from DB
        String nameFromDb = resultList.get(0).get("first_name");
        String lastNameFromDb = resultList.get(0).get("last_name");
        String departmentIdFromDb = resultList.get(0).get("department_id");

        //checking input date equals to the one from DB
        Assertions.assertEquals(nameFromDb, name);
        Assertions.assertEquals(lastNameFromDb, lastName);
        Assertions.assertEquals(departmentIdFromDb, departmentId);

        logger.info("TEST SUCCESSFULLY COMPLETED. See screenshot of result in the Screenshots folder");

    }


}
