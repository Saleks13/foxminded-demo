package pageObjects.Selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import utils.RandomGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ManagersPage extends BasePage {

    //single elements present on managers overview page
    SelenideElement newManagerButton = $("#managers-new-manager");
    SelenideElement firstNameSearchField = $("#search-manager-firstname");
    SelenideElement lastNameSearchField = $("#search-manager-lastname");
    SelenideElement departmentSearchField = $("#search-manager-department");
    public SelenideElement searchManagerButton = $("#search-manager-filter");
    SelenideElement clearSearchManagerButton = $("#search-manager-clear");

    //single elements present on new manager page/edit page
    SelenideElement firstNameField = $("#firstName");
    SelenideElement lastNameField = $("#lastName");
    SelenideElement emailField = $("#email");
    SelenideElement loginField = $("#login");
    SelenideElement departmentSelector = $("#manager-form-department-select");
    SelenideElement phoneField = $("#phone");
    SelenideElement skypeField = $("#skype");
    public SelenideElement adminCheckboxID = $("#admin");
    SelenideElement adminCheckboxClass = $$(byClassName("check")).get(0);
    public SelenideElement notificationOnStageChangeCheckboxId = $("#notify-on-stage-change");
    SelenideElement notificationOnStageChangeCheckboxClass = $$(byClassName("check")).get(1);
    public SelenideElement notificationOnAssignToTicketCheckboxId = $("#notify-on-ticket-assign");
    SelenideElement notificationOnAssignToTicketCheckboxClass = $$(byClassName("check")).get(2);
    public SelenideElement notificationOnNewCommentCheckboxId = $("#notify-on-new-comment");
    SelenideElement notificationOnNewCommentCheckboxClass = $$(byClassName("check")).get(3);
    SelenideElement submitManagerButton = $("#manager-form-submit");
    SelenideElement cancelManagerButton = $("#manager-form-cancel");

    //single elements present on details page of a manager
    public SelenideElement nameOnDetailsPage = $(byXpath("//div[1]/div[1]/div[2]/p"));
    public ElementsCollection nameOnDetailsPageClass = $$(byClassName("text-left"));
    public SelenideElement phoneOnDetailsPage = $(byXpath("//div/div[1]/div[2]/div[2]/p"));
    public SelenideElement skypeOnDetailsPage = $(byXpath("//div/div[1]/div[3]/div[2]/p"));
    public SelenideElement emailOnDetailsPage = $(byXpath("//div[1]/div[4]/app-user-emails/div/div[2]/p"));
    SelenideElement emailEditButton = $(byXpath("//div[1]/app-user-emails/div/div[1]/div/div[2]"));
    SelenideElement confirmEmailEditButton = $("#contact-form-submit");
    public SelenideElement additionalEmailsOnDetailsPage = $(byXpath("//div/div[1]/div[4]/app-user-emails/div/div[3]/div[2]"));
    public SelenideElement departmentOnDetailsPage = $(byXpath("//div/div[1]/div[5]/div[2]/p"));
    public SelenideElement loginOnDetailsPage = $(byXpath("//div/div[1]/div[6]/div[2]/p"));
    SelenideElement backToManagersListButton = $("#manager-details-backtolist");
    SelenideElement editButtonOnDetailsPage = $("#manager-details-edit");
    SelenideElement paginationArea = $(byXpath("//div/div[3]/pagination-controls/pagination-template/ul"));
    SelenideElement sortingFullNameAsc = $("#managers-fullname-sort-asc");
    SelenideElement sortingFullNameDesc = $("#managers-fullname-sort-desc");
    public SelenideElement previousPageInactiveButton = $(byXpath("//div[3]/pagination-controls/pagination-template/ul/li[1]/span"));
    public SelenideElement previousPageActiveButton = $(byXpath("//div[3]/pagination-controls/pagination-template/ul/li[1]/a"));
    SelenideElement nextPageInactiveButton = $(byXpath("//div[3]/pagination-controls/pagination-template/ul/li[9]/span"));
    public SelenideElement nextPageActiveButton = $(byXpath("//div[3]/pagination-controls/pagination-template/ul/li[9]/a"));

    //set of elements
    public ElementsCollection deleteButtonList = $$(("#managers-delete-btn"));
    public ElementsCollection fullNameList = $$(byXpath("//table/tbody/tr/td[1]/a"));
    ElementsCollection departmentList = $$(byXpath("//table/tbody/tr/td[2]"));
    ElementsCollection phoneList = $$(byXpath("//table/tbody/tr/td[3]/a"));
    ElementsCollection emailList = $$(byXpath("//table/tbody/tr/td[4]/a"));
    ElementsCollection editButtonList = $$(("#managers-edit-btn"));
    public ElementsCollection pagingButtonList = $$(byXpath("//div[3]/pagination-controls/pagination-template/ul/li/a"));

    //click new manager button on manager overview page
    public ManagersPage createNewManagerButton() {
        newManagerButton.click();
        logger.info("New manager page opened");
        return this;
    }

    //method to set value in name field
    public ManagersPage setFirstName(String firstName) {
        firstNameField.setValue(firstName);
        logger.info("First name set");
        return this;
    }

    //method to set value in last name field
    public ManagersPage setLastName(String lastname) {
        lastNameField.setValue(lastname);
        logger.info("Lastname set");
        return this;
    }

    //method to set value in email field
    public ManagersPage setEmail(String email) {
        emailField.setValue(email);
        logger.info("Email set");
        return this;
    }

    //method to edit value in main email field
    public ManagersPage editMainEmail(String email) {

        emailEditButton.click();
        emailField.setValue(email);
        confirmEmailEditButton.click();
        logger.info("Email set");
        return this;
    }

    //method to set value in login field
    public ManagersPage setLogin(String login) {
        loginField.setValue(login);
        logger.info("Login set");
        return this;
    }

    //method to set value in department field
    public ManagersPage selectDepartment(String department) {
        departmentSelector.selectOptionContainingText(department);
        //departmentSelector.selectOptionByValue(department);
        logger.info("Department selected");
        return this;
    }

    //method to set value in phone field
    public ManagersPage setPhone(String phone) {
        phoneField.setValue(phone);
        logger.info("Phone set");
        return this;
    }

    //method to set value in skype field
    public ManagersPage setSkype(String skype) {
        skypeField.setValue(skype);
        logger.info("Skype set");
        return this;
    }

    //method to press submit button create/edit manager
    public ManagersPage pressSubmitButton() {
        submitManagerButton.click();
        logger.info("New Manager created");
        return this;
    }

    //method to set admin option
    public ManagersPage setAdminOption(Boolean adminOption) {

        if (!adminCheckboxID.isSelected() && adminOption) {
            adminCheckboxClass.click();
            logger.info("Admin option selected");
        } else if (adminCheckboxID.isSelected() && !adminOption) {
            adminCheckboxClass.click();
            logger.info("Admin option NOT selected");
        }

        return this;
    }

    //method to set notification on stage change or not
    public ManagersPage setNotificationOnStageChange(Boolean stageChange) {

        //System.out.println("Before click (by ID): " + notificationOnStageChangeCheckboxId.isSelected());
        if (!notificationOnStageChangeCheckboxId.isSelected() && stageChange) {
            notificationOnStageChangeCheckboxClass.click();
            logger.info("Notification on stage change selected");
        } else if (notificationOnStageChangeCheckboxId.isSelected() && !stageChange) {
            notificationOnStageChangeCheckboxClass.click();
            logger.info("Notification on stage change NOT selected");
        }
        //System.out.println("After click (by ID): " + notificationOnStageChangeCheckboxId.isSelected());
        return this;
    }

    //method to set notification on ticket assignment or not
    public ManagersPage setNotificationOnAssignToTicket(Boolean notificationSwitch) {

        if (!notificationOnAssignToTicketCheckboxId.isSelected() && notificationSwitch) {
            notificationOnAssignToTicketCheckboxClass.click();
            logger.info("Notification on assign to ticket selected");
        } else if (notificationOnAssignToTicketCheckboxId.isSelected() && !notificationSwitch) {
            notificationOnAssignToTicketCheckboxClass.click();
            logger.info("Notification on assign to ticket NOT selected");
        }
        //System.out.println("After click (by ID): " + notificationOnAssignToTicketCheckboxId.isSelected());
        return this;
    }

    //method to set notification on new comment or not
    public ManagersPage setNotificationOnNewComment(Boolean stageChange) {

        if (!notificationOnNewCommentCheckboxId.isSelected() && stageChange) {
            notificationOnNewCommentCheckboxClass.click();
            logger.info("Notification on new comment selected");
        } else if (notificationOnNewCommentCheckboxId.isSelected() && !stageChange) {
            notificationOnNewCommentCheckboxClass.click();
            logger.info("Notification on new comment NOT selected");
        }

        return this;
    }

    //method to filter managers on managers page via name/lastname/department
    public ManagersPage filterManagers(String firstName, String lastName, String department) {

        logger.info("Filter Managers via Name/LastName/Department started");
        firstNameSearchField.setValue(firstName);
        lastNameSearchField.setValue(lastName);
        departmentSearchField.setValue(department);
        searchManagerButton.click();

        return this;
    }

    //method to filter managers on managers page department
    public ManagersPage filterManagers(String department) throws InterruptedException {

        logger.info("Filter Managers via Department started");
        departmentSearchField.setValue(department);
        searchManagerButton.click();

        return this;
    }

    //method to filter managers on managers page via name/lastname
    public ManagersPage filterManagers(String firstName, String lastName) {

        logger.info("Filter Managers via Firstname and Lastname started");
        firstNameSearchField.setValue(firstName);
        lastNameSearchField.setValue(lastName);
        searchManagerButton.click();
        logger.info("Filter Managers via Firstname and Lastname complete");
        return this;
    }

    //method to press clear button for filtering - reset any filter if applied
    public ManagersPage clearFilter() {
        clearSearchManagerButton.click();
        sleep(200);
        logger.info("Filtering on page cleared");
        return this;
    }

    //open details page of manager with specific name and last name
    public ManagersPage openDetailsPage(String firstname, String lastname) {
        fullNameList.iterator().next().shouldHave(Condition.text(firstname), Condition.text(lastname)).click();
        logger.info("Manager's details page opened");
        return this;
    }

    //click edit button for the 1st manager in the list
    public ManagersPage clickOnEditButton() {
        editButtonList.get(0).click();
        logger.info("Manager opened for editing");
        return this;
    }

    //click edit button on details page of manager
    public ManagersPage clickOnEditButtonOnDetailsPage() {
        editButtonOnDetailsPage.click();
        logger.info("Manager opened for editing");
        return this;
    }

    //click delete and confirm for 1st manager in the list
    public ManagersPage pressDeleteAndConfirm() {

        deleteButtonList.get(0).click();
        switchTo().alert().accept();
        return this;
    }

    //delete manager with specific name and last name
    public ManagersPage deleteManager(String firstName, String lastName) {

        logger.info("Delete Manager via Firstname and Lastname started");
        filterManagers(firstName, lastName);
        pressDeleteAndConfirm();

        return this;
    }

    //check managers only with one specific department are present on the page
    public ManagersPage checkOnlyFilteredDepartmentPresent(String department) throws InterruptedException {

        Thread.sleep(1000);

        try {
            departmentList.iterator().next().shouldHave(Condition.text(department));
            logger.info("Filtering complete - only managers from department {} present", department);
        } catch (Exception e) {
            logger.info("Filtering failed");
        }
        clearSearchManagerButton.click();
        return this;
    }

    //check manager with specific name and last name present on page
    public ManagersPage checkManagerIsPresent(String firstName, String lastName) {

        try {
            fullNameList.get(0).shouldHave(Condition.text(firstName), Condition.text(lastName));
            logger.info("Filtering complete - Manager {} {} is present", firstName, lastName);
        } catch (Exception e) {
            logger.info("Filtering complete - Manager {} {} is not found", firstName, lastName);
        }
        clearSearchManagerButton.click();
        return this;
    }

    //check manager with specific name and last name NOT present on page
    public ManagersPage checkManagerIsNotPresent(String firstName, String lastName) {

        try {
            fullNameList.get(0).shouldNotHave(Condition.text(firstName), Condition.text(lastName));
            logger.info("Filtering complete - Manager is present");
        } catch (Exception e) {
            logger.info("Filtering complete - Manager is not found");
        }
        clearSearchManagerButton.click();
        return this;
    }

    //create new manager with one method
    public ManagersPage createManager(String firstname, String lastname, String email, String login, String department,
                                      String phone, String skype, Boolean adminOption, Boolean notificationOnStageChange,
                                      Boolean notificationOnAssignToTicket, Boolean notificationOnNewComment) {

        logger.info("Method to create Manager started");

        createNewManagerButton()
                .setFirstName(firstname)
                .setLastName(lastname)
                .setEmail(email)
                .setLogin(login)
                .selectDepartment(department)
                .setPhone(phone)
                .setSkype(skype)
                .setAdminOption(adminOption)
                .setNotificationOnStageChange(notificationOnStageChange)
                .setNotificationOnAssignToTicket(notificationOnAssignToTicket)
                .setNotificationOnNewComment(notificationOnNewComment)
                .pressSubmitButton()
                .filterManagers(firstname, lastname, department)
                .checkManagerIsPresent(firstname, lastname);

        logger.info("Method to create Manager complete");

        return this;
    }

    //set new values (edit manager) using one method
    public ManagersPage editManager(String firstname, String lastname, String email, String department,
                                    String phone, String skype, Boolean adminOption, Boolean notificationOnStageChange,
                                    Boolean notificationOnAssignToTicket, Boolean notificationOnNewComment) {

        logger.info("Method to edit Manager's data started");

        setFirstName(firstname)
                .setLastName(lastname)
                .editMainEmail(email)
                .selectDepartment(department)
                .setPhone(phone)
                .setSkype(skype)
                .setAdminOption(adminOption)
                .setNotificationOnStageChange(notificationOnStageChange)
                .setNotificationOnAssignToTicket(notificationOnAssignToTicket)
                .setNotificationOnNewComment(notificationOnNewComment)
                .pressSubmitButton();

        logger.info("Method to edit Manager's data complete");

        return this;
    }

    //method to create new dummy manager with random values, except department
    public HashMap<String, String> createDummyManager(ManagersPage managersPage, Boolean admin, Boolean notificationOnStageChange,
                                                      Boolean notificationOnAssignToTicket, Boolean notificationOnNewComment) {

        logger.info("Method to create Manager with random data started");

        RandomGenerator randomGenerator = new RandomGenerator();
        //variables to store test data
        String firstname = randomGenerator.generateName(6);
        String lastName = randomGenerator.generateName(8);
        String email = randomGenerator.generateEmail(5, 6);
        String login = randomGenerator.generateName(6);
        String department = "Call Center";
        String phone = randomGenerator.generateAlphanumeric(9);
        String skype = randomGenerator.generateName(5);

        //hashmap stores all values
        HashMap<String, String> managerData = new HashMap<>();

        //filling hashmap with values
        managerData.put("firstname", firstname);
        managerData.put("lastname", lastName);
        managerData.put("email", email);
        managerData.put("login", login);
        managerData.put("department", department);
        managerData.put("phone", phone);
        managerData.put("skype", skype);
        managerData.put("admin", Boolean.toString(admin));
        managerData.put("notificationOnStageChange", Boolean.toString(notificationOnStageChange));
        managerData.put("notificationOnAssignToTicket", Boolean.toString(notificationOnAssignToTicket));
        managerData.put("notificationOnNewComment", Boolean.toString(notificationOnNewComment));

        //open new manager creation page
        managersPage.createNewManagerButton();

        //set of steps to create new manager
        managersPage.setFirstName(firstname)
                .setLastName(lastName)
                .setEmail(email)
                .setLogin(login)
                .selectDepartment(department)
                .setPhone(phone)
                .setSkype(skype)
                .setAdminOption(admin)
                .setNotificationOnStageChange(notificationOnStageChange)
                .setNotificationOnAssignToTicket(notificationOnAssignToTicket)
                .setNotificationOnNewComment(notificationOnNewComment)
                .pressSubmitButton()
                .filterManagers(firstname, lastName, department)
                .checkManagerIsPresent(firstname, lastName)
                .clearFilter();

        logger.info("Method to create Manager with random data complete");

        return managerData;

    }

    //get number of pages of manager overview page
    public Integer getNumberOfPages() {

        logger.info("Method to get number of page on Managers page");
        return Integer.parseInt(pagingButtonList.get(pagingButtonList.size() - 2).getText().replaceAll("[^0-9]", ""));

    }

    //click on the Next page link
    public ManagersPage nextPage() {

        logger.info("'Next page' link clicked");
        nextPageActiveButton.click();
        return this;
    }

    //sort asc list of managers on the overview page
    public ManagersPage sortAsc() {
        logger.info("Sort list via full name Ascending");
        sortingFullNameAsc.click();
        return this;
    }

    //sort desc list of managers on the overview page
    public ManagersPage sortDesc() {
        logger.info("Sort list via full name Descending");
        sortingFullNameDesc.click();
        return this;
    }

    //get list of managers on all pages that exist for managers overview page
    public List<String> getListOfManagersOnAllPages() {

        //to know how many times to click Next link, we need to know how many pages we have
        int numberOfPages = getNumberOfPages();

        //list of full names stores all managers
        List<String> list = new ArrayList<>();

        //loop to count managers on each page
        for (int i = 0; i < numberOfPages; i++) {

            for (SelenideElement selenideElement : fullNameList) {
                list.add(selenideElement.getText());
            }
            if (nextPageActiveButton.is(Condition.visible)) {
                nextPage();
            }
        }

        return list;
    }

    //getting number of managers on all managers overview pages
    public int getNumberOfAllManagers() {

        //counter of managers. First adding number of managers on page #1
        int numberOfManagersOnAllPages = fullNameList.size();

        //to know how many times to click Next link, we need to know how many pages we have
        int numberOfPages = getNumberOfPages();

        //loop to count managers on each page
        for (int i = 0; i < numberOfPages - 1; i++) {

            //System.out.println("Page #" + (i+2));
            if (nextPageActiveButton.is(Condition.visible)) {
                nextPage();
            }
            //System.out.print("Number of managers = " + numberOfManagersOnAllPages + " + " + fullNameList.size());
            sleep(400);
            numberOfManagersOnAllPages += fullNameList.size();
            //System.out.println(" = " + numberOfManagersOnAllPages);

        }
        return numberOfManagersOnAllPages;
    }

}
