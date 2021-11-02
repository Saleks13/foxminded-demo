package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Menu extends BasePage {

    public Menu(WebDriver driver) {
        super(driver);
    }

    @FindBy(id="menu-dashboard")
    public WebElement dashboard;

    @FindBy(id="menu-tickets")
    public WebElement tickets;

    @FindBy(id="menu-companies")
    public WebElement companies;

    @FindBy(id="menu-contacts")
    public WebElement contacts;

    @FindBy(id="menu-device-list")
    public WebElement devices;

    @FindBy(id="menu-departments")
    public WebElement departments;

    @FindBy(id="menu-managers")
    public WebElement managers;

    @FindBy(id="menu-categories")
    public WebElement categories;

    WebDriverWait wait = new WebDriverWait(driver, 15);

        public DashboardsPage clickOnDashboard(){
            wait.until(ExpectedConditions.visibilityOf(dashboard));
            dashboard.click();
            logger.info("Dashboard page opened");
            return new DashboardsPage(driver);
        }

        public TicketsPage clickOnTickets(){
            wait.until(ExpectedConditions.visibilityOf(tickets));
            tickets.click();
            logger.info("Tickets page opened");
            return new TicketsPage(driver);
        }

        public CompaniesPage clickOnCompanies() {
            wait.until(ExpectedConditions.visibilityOf(companies));
            companies.click();
            logger.info("Companies page opened");
            return new CompaniesPage(driver);
        }

        public ContactsPage clickOnContacts(){
            wait.until(ExpectedConditions.visibilityOf(contacts));
            contacts.click();
            logger.info("Contacts page opened");
            return new ContactsPage(driver);
        }

        public DevicesPage clickOnDevices(){
            wait.until(ExpectedConditions.visibilityOf(devices));
            devices.click();
            logger.info("Devices page opened");
            return new DevicesPage(driver);
        }

        public DepartmentsPage clickOnDepartment(){
            wait.until(ExpectedConditions.visibilityOf(departments));
            departments.click();
            logger.info("Department page opened");
            return new DepartmentsPage(driver);
        }

        public ManagersPage clickOnManagers(){
            wait.until(ExpectedConditions.elementToBeClickable(managers));
            managers.click();
            logger.info("Managers page opened");
            return new ManagersPage(driver);
        }

        public CategoriesPage clickOnCategories(){
            wait.until(ExpectedConditions.visibilityOf(categories));
            categories.click();
            logger.info("Categories page opened");
            return new CategoriesPage(driver);
        }
}




