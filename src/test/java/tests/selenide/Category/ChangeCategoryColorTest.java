package tests.selenide.Category;

import config.BaseChromeConfigurationSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.Selenide.CategoryPage;
import pageObjects.Selenide.LoginPage;
import pageObjects.Selenide.Menu;
import utils.PropertiesFile;
import utils.RandomGenerator;
import static org.junit.Assert.assertEquals;

@DisplayName("Tests to check Color editing for Category")
public class ChangeCategoryColorTest extends BaseChromeConfigurationSelenide {

    @Test
    @DisplayName("Test to check new color applied to Category")
    public void changeColorTest() throws InterruptedException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //generate random name for category title
        RandomGenerator randomGenerator = new RandomGenerator();
        String categoryTitle = randomGenerator.generateName(6);
        //color value that will be used during creation of category
        String initialColor = "RED";
        //color value that will be used during editing of color
        String newColor = "BROWN";

        //go to login page of the website under test and sign in
        LoginPage loginPage = new LoginPage();
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());

        //init menu for further navigation
        Menu menu = new Menu();
        //go to managers page
        CategoryPage categoryPage = menu.openCategoriesPage();
        categoryPage.createNewCategory(categoryTitle, initialColor, "OPEN");
        menu.openCategoriesPage();

        //variable to store actual color value of create category
        String actualColor = categoryPage.getCategoryColor(categoryTitle);

        //checking if actual value is equal to the one used during creation of category
        assertEquals(initialColor, actualColor);

        //edit color - set new value
        categoryPage.setNewColor(categoryTitle, newColor);

        //reopen category page
        menu.openCategoriesPage();

        //get the color of edited category
        actualColor = categoryPage.getCategoryColor(categoryTitle);

        //check that new color applied
        assertEquals(newColor, actualColor);

        //delete category to clean after test
        categoryPage.deleteCategory(categoryTitle);

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }
}
