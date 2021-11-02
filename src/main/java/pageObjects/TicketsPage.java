package pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TicketsPage extends BasePage {

    public TicketsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "create-new-ticket")
    WebElement newTicketButton;

    @FindBy(id = "OPEN")
    WebElement openTicketsFilter;

    @FindBy(id = "IN PROGRESS")
    WebElement inProgressTicketsFilter;

    @FindBy(id = "Rejected/SaaS")
    WebElement rejectedTicketsFilter;

    @FindBy(id = "DONE")
    WebElement doneTicketsFilter;

    @FindBy(id = "stage-total")
    WebElement totalTicketsFilter;

    @FindBy(id = "stage-closed")
    WebElement closedTicketsFilter;

    @FindBy(id = "tickets")
    WebElement allTicketsFilter;

    @FindBy(id = "user_ticekts")
    WebElement myTicketsFilter;

    @FindBy(id = "followed_tickets")
    WebElement followedTicketsFilter;

    @FindBy(id = "unprocessed_tickets")
    WebElement unprocessedTicketsFilter;

    @FindBy(id = "unprocessedTasks")
    WebElement unprocessedTasksFilter;

    @FindBy(id = "myTasks")
    WebElement myTasksFilter;

    @FindBy(xpath = "//table/tbody/tr/td[2]")
    List<WebElement> idColumn;

    @FindBy(xpath = "//table/tbody/tr/td[3]")
    public List<WebElement> titleColumn;

    @FindBy(xpath = "//table/tbody/tr/td[6]")
    List<WebElement> assigneeColumn;

    @FindBy(xpath = "//table/tbody/tr/td[7]")
    List<WebElement> stageColumn;

    @FindBy(id = "new-default-ticket")
    WebElement newGeneralTicketButton;

    @FindBy(id = "new-inner-ticket")
    WebElement newInnertTicketButton;

    @FindBy(id = "new-service-ticket")
    WebElement newServiceTicketButton;

    @FindBy(id = "title")
    WebElement newTicketTitleField;

    @FindBy(id = "description")
    WebElement newTicketDescriptionField;

    @FindBy(id = "categoryId")
    WebElement newTicketCategorySelector;

    @FindBy(id = "stageId")
    WebElement newTicketStageSelector;

    @FindBy(id = "company")
    WebElement newTicketCompanySelector;

    @FindBy(id = "contactId")
    WebElement newTicketContactSelector;

    @FindBy(id = "new-contact-for-ticket")
    WebElement newTicketNewContactButton;

    @FindBy(id = "priority")
    WebElement newTicketPrioritySelector;

    @FindBy(id = "done-deadline-date")
    WebElement newTicketDeadlineDatePicker;

    @FindBy(id = "department")
    WebElement newTicketDepartmentSelector;

    @FindBy(id = "manager")
    WebElement newTicketManagerSelector;

    @FindBy(id = "filesInput")
    WebElement newTicketAddFilesButton;

    @FindBy(id = "submit-btn")
    WebElement newTicketSubmitButton;

    @FindBy(className = "check")
    List<WebElement> ticketCheckBoxes;

    @FindBy(id = "stage-409")
    WebElement closeTicketButton;

    @FindBy(id = "search-bar")
    WebElement searchBar;

    @FindBy(id = "company-additional-information")
    List<WebElement> ticketsSearchAccordionsList;

    @FindBy(xpath = "//div/div[2]/div//div//div/h4")
    List<WebElement> ticketsOnSearchPageList;

    @FindBy(id = "edit-1")
    WebElement editTicketButton;

    @FindBy(id = "delete-file")
    List<WebElement> deleteFileButtonList;

    @FindBy(id = "upload")
    WebElement uploadFilesButton;

    WebDriverWait wait = new WebDriverWait(driver, 15);

    public TicketsPage clickCreateNewTicket() {
        wait.until(ExpectedConditions.visibilityOf(newTicketButton));
        newTicketButton.click();
        logger.info("New ticket page opened");
        return this;
    }

    //method to print all values of ID column
    public void getId() {
        logger.info("Method to print all IDs on the page - started");
        for (WebElement webElement : idColumn) {
            System.out.println(webElement.getText());
        }
    }

    //method to print all values of Title column
    public void getTitle() {
        logger.info("Method to print all ticket titles on the page - started");
        for (WebElement webElement : titleColumn) {
            System.out.println(webElement.getText());
        }
    }

    //method to print all values of Assignee column
    public void getAssignee() {
        logger.info("Method to print all assignees on the page - started");
        for (WebElement webElement : assigneeColumn) {
            System.out.println(webElement.getText());
        }
    }

    //method to print all values of Stage column
    public void getStage() {
        logger.info("Method to print all stages on the page - started");
        for (WebElement webElement : stageColumn) {
            System.out.println(webElement.getText());
        }
    }

    public TicketsPage checkTicketPresent(String ticketTitle) {
        wait.until(ExpectedConditions.visibilityOf(newTicketButton));
        for (WebElement webElement : titleColumn) {
            if (webElement.getText().equals(ticketTitle)) {
                System.out.println("Ticket \"" + ticketTitle + "\" created and present");
            }
        }
        logger.info("Method to check ticket '{}' is present on the page - executed", ticketTitle);
        return this;
    }

    public TicketsPage selectTicket(String ticketTitle) {
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOf(titleColumn.get(0)));
        for (int i = 0; i < titleColumn.size(); i++) {
            if (titleColumn.get(i).getText().equals(ticketTitle)) {
                ticketCheckBoxes.get(i + 1).click();
            }
        }
        logger.info("Method to select ticket '{}' on page - executed", ticketTitle);
        return this;
    }

    public TicketsPage closeTicket(String ticketTitle) throws InterruptedException {
        //checking all ticket titles present on the page
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfAllElements(titleColumn));
        for (int i = 0; i < titleColumn.size(); i++) {
            //comparing ticket titles with our expected title
            if (titleColumn.get(i).getText().equals(ticketTitle)) {
                //select required title
                ticketCheckBoxes.get(i + 1).click();
                //wait to avoid element not present
                WebDriverWait wait = new WebDriverWait(driver, 15);
                wait.until(ExpectedConditions.visibilityOf(closeTicketButton));
                //press close button to send out ticket to closed
                closeTicketButton.click();
                //confirm closing the ticket
                driver.switchTo().alert().accept();
            }
        }
        logger.info("Method to close ticket '{}' on page - executed", ticketTitle);
        return this;
    }

    //method to select ticket type on creation page
    public TicketsPage selectTicketType(String ticketType) {
        wait.until(ExpectedConditions.visibilityOf(newServiceTicketButton));
        switch (ticketType) {
            case "General":
                selectGeneralTicketType();
            case "Inner":
                selectInnerTicketType();
            case "Service":
                selectServiceTicketType();

        }
        logger.info("Method to select ticket type '{}' on creation - executed", ticketType);
        return this;
    }

    //method to select General ticket type on creation page
    public TicketsPage selectGeneralTicketType() {
        wait.until(ExpectedConditions.visibilityOf(newGeneralTicketButton));
        newGeneralTicketButton.click();
        logger.info("Method to select general type of ticket - executed");
        return this;
    }

    //method to select Inner ticket type on creation page
    public TicketsPage selectInnerTicketType() {
        wait.until(ExpectedConditions.visibilityOf(newInnertTicketButton));
        newInnertTicketButton.click();
        logger.info("Method to select inner type of ticket - executed");
        return this;
    }

    //method to select Service ticket type on creation page
    public TicketsPage selectServiceTicketType() {
        wait.until(ExpectedConditions.visibilityOf(newServiceTicketButton));
        newServiceTicketButton.click();
        logger.info("Method to select service type of ticket - executed");
        return this;
    }

    //method to specify name/title of the ticket
    public TicketsPage setTicketTitle(String newTicketTitle) {
        wait.until(ExpectedConditions.visibilityOf(newTicketTitleField));
        newTicketTitleField.clear();
        newTicketTitleField.sendKeys(newTicketTitle);
        logger.info("Method to set ticket title '{}' - executed", newTicketTitle);
        return this;
    }

    //method to specify description of the ticket
    public TicketsPage setTicketDescription(String newTicketDescription) {
        wait.until(ExpectedConditions.visibilityOf(newTicketDescriptionField));
        newTicketDescriptionField.clear();
        newTicketDescriptionField.sendKeys(newTicketDescription);
        logger.info("Method to set ticket description '{}' - executed", newTicketDescription);
        return this;
    }

    //method to select category of the ticket
    public TicketsPage setTicketCategory(String newTicketCategory) {
        wait.until(ExpectedConditions.visibilityOf(newTicketCategorySelector));
        Select categorySelector = new Select(newTicketCategorySelector);
        categorySelector.selectByVisibleText(newTicketCategory);
        logger.info("Method to select ticket category '{}' - executed", newTicketCategory);
        return this;
    }

    //method to select Stage of the ticket
    public TicketsPage setTicketStage(String newTicketStage) {
        wait.until(ExpectedConditions.visibilityOf(newTicketStageSelector));
        wait.until(ExpectedConditions.textToBePresentInElement(newTicketStageSelector, newTicketStage));
        Select stageSelector = new Select(newTicketStageSelector);
        stageSelector.selectByVisibleText(newTicketStage);
        logger.info("Method to select ticket stage '{}' - executed", newTicketStage);
        return this;
    }

    //method to select Company to which refers the ticket
    public TicketsPage setTicketCompany(String newTicketCompany) {
        wait.until(ExpectedConditions.visibilityOf(newTicketCompanySelector));
        Select companySelector = new Select(newTicketCompanySelector);
        companySelector.selectByVisibleText(newTicketCompany);
        logger.info("Method to set ticket company '{}' - executed", newTicketCompany);
        return this;
    }

    //method to select Contact of a given Company
    public TicketsPage setTicketContact(String newTicketContact) {
        wait.until(ExpectedConditions.visibilityOf(newTicketContactSelector));
        Select contactSelector = new Select(newTicketContactSelector);
        contactSelector.selectByVisibleText(newTicketContact);
        logger.info("Method to set ticket contact '{}' - executed", newTicketContact);
        return this;
    }

    //method to select Priority
    public TicketsPage setTicketPriority(String newTicketPriority) {
        wait.until(ExpectedConditions.visibilityOf(newTicketPrioritySelector));
        Select prioSelector = new Select(newTicketPrioritySelector);
        prioSelector.selectByVisibleText(newTicketPriority);
        logger.info("Method to select ticket priority '{}' - executed", newTicketPriority);
        return this;
    }

    //method to set the Deadline date/time, (does not work)
    public TicketsPage setNewTicketDeadlineDate(String newTicketDeadlineDate) {
        newTicketDeadlineDatePicker.sendKeys(newTicketDeadlineDate);
        return this;
    }

    //method to select Department
    public TicketsPage setTicketDepartment(String newTicketDepartment) {
        wait.until(ExpectedConditions.visibilityOf(newTicketDepartmentSelector));
        Select ticketDepartment = new Select(newTicketDepartmentSelector);
        ticketDepartment.selectByVisibleText(newTicketDepartment);
        logger.info("Method to set ticket department '{}' - executed", newTicketDepartment);
        return this;
    }

    //method to select Manager of the ticket
    public TicketsPage setTicketManager(String newTicketManager) {
        wait.until(ExpectedConditions.visibilityOf(newTicketManagerSelector));
        wait.until(ExpectedConditions.textToBePresentInElement(newTicketManagerSelector, newTicketManager));
        Select ticketManager = new Select(newTicketManagerSelector);
        ticketManager.selectByVisibleText(newTicketManager);
        logger.info("Method to set ticket manager '{}' - executed", newTicketManager);
        return this;

    }

    //method to attach some files to new ticket
    public TicketsPage selectFiles(String absolutePath) {
        String workingDir = System.getProperty("user.dir");
        newTicketAddFilesButton.sendKeys(workingDir + absolutePath);
        logger.info("Method to select file for attachment executed");
        return this;
    }

    //method to click Sumbit button to finish ticket creation
    public TicketsPage clickSubmitButton() {
        wait.until(ExpectedConditions.visibilityOf(newTicketSubmitButton));
        newTicketSubmitButton.click();
        logger.info("Method to confirm ticket creation - submit button pressed");
        return this;
    }

    //method to create new ticket (including pressing New Ticket button on the the Tickets Overview page)
    public TicketsPage createNewGeneralTicket(String newTicketTitle, String newTicketDescription, String newTicketCategory, String newTicketStage, String newTicketCompany,
                                              String newTicketContact, String newTicketPriority, String newTicketDepartment, String newTicketManager, String attachmentFilePath) {

        logger.info("Method to create new ticket - started");
        //this variable stores path of the project in the current system
        String projectPath = System.getProperty("user.dir");

        //open new ticket creation page
        clickCreateNewTicket();
        //specify details of new ticket:
        selectGeneralTicketType();
        setTicketTitle(newTicketTitle);
        setTicketDescription(newTicketDescription);
        setTicketCategory(newTicketCategory);
        setTicketStage(newTicketStage);
        setTicketCompany(newTicketCompany);
        setTicketContact(newTicketContact);
        setTicketPriority(newTicketPriority);
        setTicketDepartment(newTicketDepartment);
        setTicketManager(newTicketManager);
        selectFiles(attachmentFilePath);
        //finish creation by clicking submit button
        clickSubmitButton();
        wait.until(ExpectedConditions.visibilityOf(newTicketButton));
        logger.info("Method to create new ticket - complete");

        return this;

    }

    public TicketsPage searchTicket(String ticketTitle) {

        logger.info("Search for ticket with specific name started");
        wait.until(ExpectedConditions.visibilityOf(searchBar));
        searchBar.clear();
        searchBar.sendKeys(ticketTitle);
        searchBar.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfAllElements(ticketsSearchAccordionsList));
        logger.info("Ticket search complete");

        return this;

    }

    public TicketsPage expandTicketSearchAccordion(String accordionName) {

        logger.info("Method to expand specific accordion started");
        for (int i = 0; i < ticketsSearchAccordionsList.size(); i++) {
            //get rid of numbers in the ticketsSearchAccordionsList's elements
            String accordionElementTitle = ticketsSearchAccordionsList.get(i).getText().replaceAll("[0-9]","");
            //get rid of spaces in the names of accordions
            accordionElementTitle = accordionElementTitle.replaceAll("\\s+","");
            //service print to console
            //System.out.println("Accordion list is: " + accordionElementTitle);
            //checking of current accordion is the one required - open it
            if (accordionElementTitle.equals(accordionName)) {
                ticketsSearchAccordionsList.get(i).click();
            }
        }
        //try/catch to verify that accordion actually contains some items
        try {
            wait.until(ExpectedConditions.elementToBeClickable(ticketsOnSearchPageList.get(0)));
        } catch (Exception e) {
            logger.info("Search for ticket found 0 results.");
        }

        return this;
    }

    public TicketsPage openTicketWithName(String ticketName) {
        //looping through tickets in the search results
        for (int i = 0; i < ticketsOnSearchPageList.size(); i++) {
            if (ticketsOnSearchPageList.get(i).getText().contains(ticketName)) {
                //clicking on required ticket
                ticketsOnSearchPageList.get(i).click();
            }
        }
        //waiting for Edit button to be clickable - so that it's known page with ticket details loaded
        wait.until(ExpectedConditions.elementToBeClickable(editTicketButton));
        return this;
    }

    public TicketsPage editTicket() {
        try {
            editTicketButton.click();
        } catch (Exception e) {
            editTicketButton.click();
        }
        return this;
    }

    public TicketsPage editExistingTicket(String newTitle, String newDescription,
                                          String newCategory, String newStage, String newCompany,
                                          String newContact, String newPriority, String newDeadline,
                                          String newDepartment, String newManager) {

        return this;
    }

    public TicketsPage removeAttachedFile() {

        try {
            deleteFileButtonList.get(0).click();
            Alert alert = driver.switchTo().alert();
            //accept deletion of a department
            alert.accept();
        } catch (Exception e) {
            System.out.println("This ticket does not have attached files");
        }
        logger.info("1st file on the list of attached filed deleted");
        return this;
    }

    public TicketsPage attachNewFileOnEditPage(String filePath) {

        selectFiles(filePath);
        uploadFilesButton.click();
        logger.info("Method to upload selected file executed - file now attached");

        return this;
    }


}
