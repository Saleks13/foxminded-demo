package tests.selenide.Category;

import config.BaseChromeConfigurationSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.Selenide.CategoryPage;
import pageObjects.Selenide.LoginPage;
import pageObjects.Selenide.Menu;
import utils.PropertiesFile;
import utils.RandomGenerator;

@DisplayName("Tests to check editing of Category")
public class EditCategoryTest extends BaseChromeConfigurationSelenide {

    @Test
    @DisplayName("Test to check editing of Category works")
    public void editCategoryTest() throws InterruptedException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //generate random name for category title
        RandomGenerator randomGenerator = new RandomGenerator();
        String categoryTitle = randomGenerator.generateName(6);

        //go to login page of the website under test and sign in
        LoginPage loginPage = new LoginPage();
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());

        //init menu for further navigation
        Menu menu = new Menu();
        //go to managers page
        CategoryPage categoryPage = menu.openCategoriesPage();
        categoryPage.createNewCategory(categoryTitle, "PINK", "OPEN");

        //open list of all categories
        menu.openCategoriesPage();

        categoryPage.openCategory(categoryTitle)
                .clickEditOnDetailsPage()
                .editCategory(categoryTitle + 1, "GREY", "CLOSED");
        menu.openCategoriesPage()
                .deleteCategory(categoryTitle + 1);

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }
}
