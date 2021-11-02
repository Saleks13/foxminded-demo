package pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CategoriesPage extends BasePage {

    public CategoriesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "new-category-btn")
    WebElement newCategoryButton;

    @FindBy(id = "name")
    WebElement titleField;

    @FindBy(id = "color")
    WebElement colorField;

    @FindBy(id = "category-form-submit")
    WebElement submitButton;

    @FindBy(id = "category-form-cancel")
    WebElement cancelButton;

    @FindBy(xpath = "//div/table/tbody/tr[2]/td[2]")
    WebElement openStage;

    @FindBy(xpath = "//div/table/tbody/tr[3]/td[2]")
    WebElement inProgressStage;

    @FindBy(xpath = "//div/table/tbody/tr[4]/td[2]")
    WebElement doneStage;

    @FindBy(xpath = "//div/table/tbody/tr[5]/td[2]")
    WebElement closedStage;

    @FindBy(id = "stage-details-back-to-list")
    WebElement backToListButton;

    @FindBy(id = "category-delete-btn")
    List<WebElement> deleteButtons;

    WebDriverWait wait = new WebDriverWait(driver, 15);


    public CategoriesPage clickCreateNewCategory() {

        //wait to be sure new category button is visible on the page
        wait.until(ExpectedConditions.visibilityOf(newCategoryButton));
        try {
            //click button
            newCategoryButton.click();
            logger.info("'New category' page opened");
        } catch (Exception e) {
            logger.error("Error on opening 'new category' page");
        }
        return this;
    }

    public CategoriesPage createNewCategory(String categoryTitle, String categoryColor) {

        try {
            logger.info("Method to create new category started");
            //open create new category page
            clickCreateNewCategory();
            //wait to be sure fields to create category is present and visible
            wait.until(ExpectedConditions.visibilityOf(titleField));
            titleField.sendKeys(categoryTitle);
            colorField.sendKeys(categoryColor);
            //confirm creation
            submitButton.click();
            logger.info("Creation of new category '{}' and color '{}' completed", categoryTitle, categoryColor);
            //wait until back button is present
            wait.until(ExpectedConditions.visibilityOf(backToListButton));
            //click on back button to return to categories list page
            backToListButton.click();
            logger.info("Creation complete. Back on Categories overview page");

        } catch (Exception e) {
            logger.error("Error during creation of new category");
        }
        return this;
    }

    public CategoriesPage deleteLastOnPage(){

        try {
            //method to delete last entry on the categories page
            deleteButtons.get(deleteButtons.size()-1).click();
            Alert alert = driver.switchTo().alert();
            alert.accept();
            logger.info("Last category on the page deleted");
        } catch (Exception e) {
            logger.error("Error during deletion of last item in categories page");
        }
        return this;
    }

}

