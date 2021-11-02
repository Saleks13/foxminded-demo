package pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ManagersPage extends BasePage {

    public ManagersPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "managers-new-manager")
    WebElement newManagerButton;

    @FindBy(id = "search-manager-firstname")
    WebElement firstNameSearchField;

    @FindBy(id = "search-manager-lastname")
    WebElement lastNameSearchField;

    @FindBy(id = "search-manager-department")
    WebElement departmentSearchField;

    @FindBy(id = "search-manager-filter")
    public WebElement searchManagerButton;

    @FindBy(id = "search-manager-clear")
    WebElement clearSearchManagerButton;

    @FindBy(xpath = "//div/table/tbody/tr/td[1]/a")
    public List<WebElement> fullNameList;

    @FindBy(xpath = "//div/table/tbody/tr[2]/td[2]")
    List<WebElement> departmentList;

    @FindBy(xpath = "//div/table/tbody/tr/td[3]/a")
    List<WebElement> phoneList;

    @FindBy(xpath = "//div/table/tbody/tr/td[4]/a")
    List<WebElement> emailList;

    @FindBy(id = "managers-edit-btn")
    List<WebElement> editButtonList;

    @FindBy(id = "managers-delete-btn")
    public List<WebElement> deleteButtonList;

    @FindBy(id = "firstName")
    WebElement firstNameField;

    @FindBy(id = "lastName")
    WebElement lastNameField;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "login")
    WebElement loginField;

    @FindBy(id = "manager-form-department-select")
    WebElement departmentSelector;

    @FindBy(id = "phone")
    WebElement phoneField;

    @FindBy(id = "skype")
    WebElement skypeField;

    @FindBy(id = "admin")
    WebElement adminCheckbox;

    @FindBy(id = "notify-on-stage-change")
    WebElement notificationOnStageChangeCheckbox;

    @FindBy(id = "notify-on-ticket-assign")
    WebElement notificationOnAssignToTicketCheckbox;

    @FindBy(id = "notify-on-new-comment")
    WebElement notificationOnNewCommentCheckbox;

    @FindBy(id = "manager-form-submit")
    WebElement newManagerSubmitButton;

    @FindBy(id = "manager-form-cancel")
    WebElement newManagerCancelButton;

    @FindBy(xpath = "//p-tabview/div/div/p-tabpanel[1]/div/div[1]/div[1]")
    public WebElement nameOnDetailsPage;

    @FindBy(xpath = "//p-tabview/div/div/p-tabpanel[1]/div/div[1]/div[2]")
    public WebElement phoneOnDetailsPage;

    @FindBy(xpath = "//p-tabview/div/div/p-tabpanel[1]/div/div[1]/div[3]")
    public WebElement skypeOnDetailsPage;

    @FindBy(xpath = "//p-tabview/div/div/p-tabpanel[1]/div/div[1]/div[4]")
    public WebElement emailOnDetailsPage;

    @FindBy(xpath = "//p-tabview/div/div/p-tabpanel[1]/div/div[1]/div[5]")
    public WebElement departmentOnDetailsPage;

    @FindBy(xpath = "//p-tabview/div/div/p-tabpanel[1]/div/div[1]/div[6]")
    public WebElement loginOnDetailsPage;

    @FindBy(id = "manager-details-backtolist")
    WebElement backToManagersListButton;

    @FindBy(xpath = "//div/div[3]/pagination-controls/pagination-template/ul")
    WebElement paginationArea;

    //init wait object to use it in methods
    WebDriverWait wait = new WebDriverWait(driver, 15);


    //click new manager button on manager overview page
    public ManagersPage createNewManagerButton() {
        wait.until(ExpectedConditions.visibilityOf(newManagerButton));
        newManagerButton.click();
        logger.info("New manager page opened");
        return this;
    }

    //specify value in Fist Name field
    public ManagersPage setNewManagerFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField));
        firstNameField.sendKeys(firstName);
        logger.info("First name of new manager set");
        return this;
    }

    //specify value in Last Name field
    public ManagersPage setNewManagerLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameField));
        lastNameField.sendKeys(lastName);
        logger.info("Last name of new manager set");
        return this;
    }

    //specify value in Email field
    public ManagersPage setNewManagerEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(email);
        logger.info("Email of new manager set");
        return this;
    }

    //specify value in Login field
    public ManagersPage setNewManagerLogin(String login) {
        wait.until(ExpectedConditions.visibilityOf(loginField));
        loginField.clear();
        loginField.sendKeys(login);
        logger.info("Login name of new manager set");
        return this;
    }

    //select Department
    public ManagersPage setNewManagerDepartment(String department) {
        wait.until(ExpectedConditions.visibilityOf(departmentSelector));
        Select departmentSelect = new Select(departmentSelector);
        departmentSelect.selectByVisibleText(department);
        logger.info("Department of new manager selected");
        return this;
    }

    //specify value in Phone field
    public ManagersPage setNewManagerPhone(String phone) {
        wait.until(ExpectedConditions.visibilityOf(phoneField));
        phoneField.sendKeys(phone);
        logger.info("Phone of new manager set");
        return this;

    }

    //specify value in Fist Name field
    public ManagersPage setNewManagerSkype(String skype) {
        wait.until(ExpectedConditions.visibilityOf(skypeField));
        skypeField.sendKeys(skype);
        logger.info("Skype of new manager set");
        return this;
    }

    //select Admin checkbox
    public ManagersPage selectAdminCheckbox() {
        adminCheckbox.click();
        logger.info("New manager set as admin");
        return this;
    }

    //select notifications in case Stage Changes
    public ManagersPage switchNotificationOnStageChange() {
        notificationOnStageChangeCheckbox.click();
        logger.info("Notification on stage change activated");
        return this;
    }

    //select notifications in case Ticket Assigned
    public ManagersPage switchNotificationOnAssignTicket() {
        notificationOnAssignToTicketCheckbox.click();
        logger.info("Notification on ticket assigned activated");
        return this;
    }

    //select notification on case New Comment added
    public ManagersPage switchNotificationOnNewComment() {
        notificationOnNewCommentCheckbox.click();
        logger.info("Notification on new comment activated");
        return this;
    }

    //click submit button on new manager creation page
    public ManagersPage clickSubmitButton() {
        wait.until(ExpectedConditions.visibilityOf(newManagerSubmitButton));
        newManagerSubmitButton.click();
        logger.info("Submit button on new manager page pressed");
        return this;
    }

    //click cancel button on new manager creation page
    public ManagersPage clickCancelButton() {
        newManagerCancelButton.click();
        logger.info("New manager creation canceled");
        return this;
    }

    //method to create new manager. Includes open new manage page
    public void createNewManager(String firstName, String lastName, String email, String login,
                                 String department, String phone, String skype) {

        logger.info("Method gets all the data and creates new manager - started");
        //open new manager page
        createNewManagerButton();

        //set values
        setNewManagerFirstName(firstName);
        setNewManagerLastName(lastName);
        setNewManagerEmail(email);
        setNewManagerLogin(login);
        setNewManagerDepartment(department);
        setNewManagerPhone(phone);
        setNewManagerSkype(skype);

        //complete creation by pressing submit button
        clickSubmitButton();
        wait.until(ExpectedConditions.visibilityOf(newManagerButton));
        logger.info("Method to create new manager - completed");
    }

    //search for manager using first and last names
    public ManagersPage searchManager(String firstName, String lastName, String department) {

        logger.info("---Method to search and assert manager - started");
        //wait to be sure search fields are present
        wait.until(ExpectedConditions.visibilityOf(firstNameSearchField));
        //fill in search data
        firstNameSearchField.sendKeys(firstName);
        lastNameSearchField.sendKeys(lastName);
        departmentSearchField.sendKeys(department);
        //perform search by pressing filter button
        searchManagerButton.click();

        //make full name string
        String fullName = firstName + " " + lastName;

        //clear search fields - used to gain time until search result is shown on the page
        firstNameSearchField.clear();
        lastNameSearchField.clear();
        departmentSearchField.clear();

        logger.info("Search of manager is executed");
        logger.info("Assertion if manager '{}' present - started", fullName);

        //check full name is correct
        assert fullNameList.get(0).getText().equals(fullName);
        //check department shown on the page is correct
        assert departmentList.get(0).getText().equals(department);

        logger.info("Assertion is complete");
        logger.info("---Method to search and assert manager - complete");

        return this;
    }


    //press edit button for the fist manager in the list
    public ManagersPage editFirstManager() {
        editButtonList.get(0).click();
        logger.info("1st manager in the list opened for edit");
        return this;
    }

    //delete first manager in the list
    public ManagersPage deleteFirstManager() {

        deleteButtonList.get(0).click();
        //switch control to the alert - confirmation of the company deletion
        Alert alert = driver.switchTo().alert();
        //accept deletion of a department
        alert.accept();
        logger.info("First manager on page deleted");
        return this;

    }

    //open details page for the first manager in the list
    public ManagersPage openFirstManager() {
        wait.until(ExpectedConditions.visibilityOf(deleteButtonList.get(0)));
        wait.until(ExpectedConditions.visibilityOf(fullNameList.get(0)));
        fullNameList.get(0).click();
        wait.until(ExpectedConditions.visibilityOf(backToManagersListButton));
        logger.info("First manager on page opened");
        return this;
    }

    //go back to namagers list page from details page
    public ManagersPage clickBackButton() {
        wait.until(ExpectedConditions.visibilityOf(backToManagersListButton));
        backToManagersListButton.click();
        wait.until(ExpectedConditions.visibilityOf(newManagerButton));
        logger.info("Back button pressed - manager overview page opened");
        return this;
    }

}
