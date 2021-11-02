package tests.chrome.Category;

import config.BaseChromeConfiguration;
import dataBase.DataBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.CategoriesPage;
import pageObjects.LoginPage;
import pageObjects.Menu;
import utils.PropertiesFile;
import utils.RandomGenerator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Tests to check Category creation")
public class CreateCategoryTest extends BaseChromeConfiguration {

    //init random generator to generate test data
    RandomGenerator randomGenerator = new RandomGenerator();
    //db instance to run queries
    DataBase dataBase = new DataBase();

    @Test
    @DisplayName("Create new Category. Assert in DB")
    public void CreateCategory() throws SQLException {

        logger.info(new Throwable().getStackTrace()[0].getMethodName() + " test started");

        //string which stores random name of the category
        String categoryName = randomGenerator.generateName(6);
        //color of future category
        String color = "Red";

        //query to get ID of created category
        String query = "select id from category where name = '" + categoryName + "' and color = " + "'" + color + "'";
        //initialize variable to store result of the query
        String result;

        //start webpage and perform sign-in
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());
        Menu menu = new Menu(driver);
        //go to categories page
        CategoriesPage categoriesPage = menu.clickOnCategories();
        //method to create new category
        categoriesPage.createNewCategory(categoryName, color);

        //get result of a query - id of the created category
        result = dataBase.getValue(query);
        //check is not null - meaning it us actually present in the DB
        assertNotNull(result);
        System.out.println("Category with name: \"" + categoryName + "\" created successfully");

        //delete category
        categoriesPage.deleteLastOnPage();

        logger.info("TEST SUCCESSFULLY COMPLETED");
    }

    @Test
    @DisplayName("Create new Category - I. Assert in DB (ResultSet)")
    public void CreateCategory2() throws SQLException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //string which stores random name of the category
        String categoryName = randomGenerator.generateName(6);
        //color of future category
        String color = "Red";

        HashMap<String, String> conditions = new HashMap<>();
        conditions.put("name", categoryName);
        conditions.put("color", color);

        //start webpage and perform sign-in
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());
        Menu menu = new Menu(driver);
        //go to categories page
        CategoriesPage categoriesPage = menu.clickOnCategories();
        //method to create new category
        categoriesPage.createNewCategory(categoryName, color);

        ResultSet rs = dataBase.selectResultSet("category", conditions);
        //get result of a query - id of the created category
        String result = dataBase.getValue(rs, "id");
        //check is not null - meaning it us actually present in the DB
        assertNotNull(result);

        //delete category
        categoriesPage.deleteLastOnPage();

        logger.info("TEST SUCCESSFULLY COMPLETED");
    }

    @Test
    @DisplayName("Create new Category - II. Assert in DB (ResultSet)")
    public void CreateNewCategoryTest() throws SQLException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(PropertiesFile.getUsername(),PropertiesFile.getPassword());
        Menu menu = new Menu(driver);
        CategoriesPage categoriesPage = menu.clickOnCategories();

        String categoryName = randomGenerator.generateName(6);
        String categoryColor = "RED";

        categoriesPage.createNewCategory(categoryName,categoryColor);

        String tableName = "category";
        String columnName = "name";

        HashMap<String, String> conditions = new HashMap<>();

        conditions.put(columnName, categoryName);
        conditions.put("color", categoryColor);

        DataBase dataBase = new DataBase();
        ResultSet rs = dataBase.selectResultSet(tableName, conditions);

        String result = dataBase.getValue(rs, columnName);
        System.out.println(result);

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }


}
