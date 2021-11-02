package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DashboardsPage extends BasePage {

    public DashboardsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className="btn btn-default")
    private WebElement reactionDeadlineButton;

    @FindBy(id="dashboard-done")
    WebElement doneDeadlineButton;

    @FindBy(id="accordion1")
    WebElement needImmediateReaction;

    @FindBy(id="company-additional-information")
    WebElement deadLineIsOver;

    @FindBy(xpath="//table/tbody/tr/td[2]")
    List<WebElement> idsList;

    @FindBy(xpath="//table/tbody/tr/td[3]")
    public List<WebElement> titlesList;

    @FindBy(xpath="//table/tbody/tr/td[5]")
    List<WebElement> priorityList;

    @FindBy(xpath="//table/tbody/tr/td[9]")
    List<WebElement> categoryList;



    public void clickDoneDeadline(){
        doneDeadlineButton.click();
    }

    public void clickOnSeadLineIsOver(){
        deadLineIsOver.click();
    }

    //method to get all Ticket titles of specific category
    public void getTicketTitlesOfCategory(String category){
        for (int i = 0; i<titlesList.size();i++) {
            if (categoryList.get(i).getText().equals(category)) {
                System.out.println(titlesList.get(i).getText());
            }
        }
        logger.info("Method to print ticket title of specific category executed");
    }

    //method to get all IDs of specific priority
    public void getIDsOfPriority(String priority){
        for (int i =0; i<idsList.size();i++){
            if (priorityList.get(i).getText().equals(priority)) {
                System.out.println(idsList.get(i).getText());
            }
        }
        logger.info("Method to get all IDs of specific priority executed");
    }

}
