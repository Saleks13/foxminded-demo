package pageObjects.Selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Selenide.$;

public class Menu extends BasePage{

    public SelenideElement dashboard = $("#menu-dashboard");
    public SelenideElement tickets = $("#menu-tickets");
    public SelenideElement companies = $("#menu-companies");
    public SelenideElement contacts = $("#menu-contacts");
    public SelenideElement devices = $("#menu-device-list");
    public SelenideElement departments =  $("#menu-departments");
    public SelenideElement managers = $("#menu-managers");
    public SelenideElement categories = $("#menu-categories");

//    public DashboardsPage clickOnDashboard(){
//        dashboard.click();
//        logger.info("Dashboard page opened");
//        return new DashboardsPage;
//    }
//
//    public TicketsPage clickOnTickets(){
//
//        tickets.click();
//        logger.info("Tickets page opened");
//        return new TicketsPage;
//    }
//
//    public CompaniesPage clickOnCompanies() {
//
//        companies.click();
//        logger.info("Companies page opened");
//        return new CompaniesPage;
//    }
//
//    public ContactsPage clickOnContacts(){
//
//        contacts.click();
//        logger.info("Contacts page opened");
//        return new ContactsPage;
//    }
//
//    public DevicesPage clickOnDevices(){
//
//        devices.click();
//        logger.info("Devices page opened");
//        return new DevicesPage;
//    }
//
//    public DepartmentsPage clickOnDepartment(){
//
//        departments.click();
//        logger.info("Department page opened");
//        return new DepartmentsPage;
//    }

    //method ot open managers page
    public ManagersPage openManagersPage(){

        managers.click();
        logger.info("Managers page opened");
        return new ManagersPage();
    }
    //method ot open category page
    public CategoryPage openCategoriesPage(){

        categories.click();
        logger.info("Categories page opened");
        return new CategoryPage();
    }

}
