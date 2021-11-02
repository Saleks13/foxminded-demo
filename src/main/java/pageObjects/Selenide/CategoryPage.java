package pageObjects.Selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class CategoryPage extends BasePage {

    //elements on page
    SelenideElement newCategoryButton = $("#new-category-btn");
    SelenideElement titleField = $("#name");
    SelenideElement colorField = $("#color");
    SelenideElement submitButton = $("#category-form-submit");
    SelenideElement cancelButton = $("#category-form-cancel");
    SelenideElement openStage = $(By.xpath("//div/table/tbody/tr[2]/td[2]/a"));
    SelenideElement inProgressStage = $(By.xpath("//div/table/tbody/tr[3]/td[2]/a"));
    SelenideElement doneStage = $(By.xpath("//div/table/tbody/tr[4]/td[2]/a"));
    SelenideElement closedStage = $(By.xpath("//div/table/tbody/tr[5]/td[2]/a"));
    SelenideElement backToListButton = $("#stage-details-back-to-list");
    SelenideElement searchBar = $("#search-bar");
    SelenideElement closeInfoButton = $(By.xpath("//div[2]/div/button/span"));
    SelenideElement editButtonOnDetailsPage = $("#category-details-edit");

    //elements collections(lists) on page
    ElementsCollection deleteButtons = $$("#category-delete-btn");
    ElementsCollection categoriesList = $$(By.xpath("//tbody/tr/td[1]/a"));
    ElementsCollection editCategoryButtons = $$("#category-stage-edit-btn");
    ElementsCollection colorsOnCategoryList = $$(By.xpath("//table/tbody/tr/td[2]/span"));

    //method to enter new category creation page
    public CategoryPage clickNewCategory() {

        //click on the button located on categories overview page
        newCategoryButton.click();
        logger.info("New Category page opened");
        return this;

    }

    //method to set value in title field
    public CategoryPage setTitle(String title) {

        titleField.setValue(title);
        logger.info("Category title set");
        return this;

    }

    //method to set value in color field
    public CategoryPage setColor(String color) {

        colorField.setValue(color);
        logger.info("Category color set");
        return this;

    }

    //method to click submit button
    public CategoryPage clickSubmitButton() {

        submitButton.click();
        logger.info("New Category created");
        return this;

    }

    //method to select one of 4 stages
    public CategoryPage selectStage(String stage) {

        //else if to select specific stage for the category
        if (stage.contains("OPEN")) {
            openStage.click();
            logger.info("Stage 'Open' selected");
        } else if (stage.contains("IN PROGRESS")) {
            inProgressStage.click();
            logger.info("Stage 'In Progress' selected");
        } else if (stage.contains("DONE")) {
            doneStage.click();
            logger.info("Stage 'Done' selected");
        } else if (stage.contains("CLOSED")) {
            closedStage.click();
            logger.info("Stage 'Closed' selected");
        }
        logger.info("Stage of the category is selected");
        return this;

    }

    //method containing complete set of steps to create new category
    public CategoryPage createNewCategory(String title, String color, String stage) {

        logger.info("Method to create new Category started");

        //steps to create new category
        clickNewCategory();
        setTitle(title);
        setColor(color);
        clickSubmitButton();
        selectStage(stage);

        return this;
    }

    //method to delete category with specific title
    public CategoryPage deleteCategory(String title) {

        logger.info("Method to delete specific Category started");

        //sometimes info popup line shown, so closing it
        if (closeInfoButton.isDisplayed()) {
            closeInfoButton.click();
        }

        //loop to go through all categories titles present on the page
        for (int i = 0; i < categoriesList.size(); i++) {
            categoriesList.get(i).scrollTo();
            if (categoriesList.get(i).getText().equals(title)) {
                //pressing delete button for specific category
                deleteButtons.get(i - 1).click();
                switchTo().alert().accept();
            }
        }
        logger.info("Category deleted");
        return this;
    }

    //method to open category with specific name
    public CategoryPage openCategory(String title) {

        logger.info("Method to open specific Category started");

        //sometimes info popup line shown, so closing it
        if (closeInfoButton.isDisplayed()) {
            closeInfoButton.click();
        }
        //loop to go through all categories titles present on the page and find required one
        for (SelenideElement selenideElement : categoriesList) {
            selenideElement.scrollTo();
            if (selenideElement.getText().equals(title)) {
                selenideElement.click();
            }
        }
        logger.info("Category details page opened");
        return this;
    }

    //method to click on edit button located on the category details page
    public CategoryPage clickEditOnDetailsPage() {

        editButtonOnDetailsPage.click();
        logger.info("Category opened in edit mode");
        return this;

    }

    //method to edit category by setting new values for title,color,stage
    public CategoryPage editCategory(String title, String color, String stage) throws InterruptedException {

        logger.info("Method to edit Category started");

        setTitle(title);
        setColor(color);
        clickSubmitButton();
        selectStage(stage);

        logger.info("Category was edited");
        return this;
    }

    //dedicated method to get color of a given category
    public String getCategoryColor(String title) {

        logger.info("Method to get the Color value of specific Category started");
        String color = "";

        //sometimes info popup line shown, so closing it
        if (closeInfoButton.isDisplayed()) {
            closeInfoButton.click();
        } else {
            //if info popup is not shown, to get focus on main area of page we click on colors on category page
            colorsOnCategoryList.get(0).click();
        }
        //loop to go through all categories titles present on the page and find required one
        for (int i = 0; i < categoriesList.size(); i++) {
            categoriesList.get(i).scrollTo();
            if (categoriesList.get(i).getText().equals(title)) {
                //storing current color value
                color = colorsOnCategoryList.get(i).getText();
            }
        }
        logger.info("Color of the category obtained");
        return color;
    }

    //dedicated method to set color of a given category
    public CategoryPage setNewColor(String categoryTitle, String newColor) {

        logger.info("Method to set new Color value for specific Category started");

        openCategory(categoryTitle);
        clickEditOnDetailsPage();
        setColor(newColor);
        clickSubmitButton();

        logger.info("New color for Categor set");
        return this;
    }
}
