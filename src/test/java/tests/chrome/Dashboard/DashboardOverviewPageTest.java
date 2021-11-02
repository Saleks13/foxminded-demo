package tests.chrome.Dashboard;

import config.BaseChromeConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.DashboardsPage;
import pageObjects.LoginPage;
import pageObjects.Menu;
import utils.PropertiesFile;

@DisplayName("Test performed on Dashboard page")
public class DashboardOverviewPageTest extends BaseChromeConfiguration {

    @Test
    @DisplayName("Prints specific Tickets present on page")
    public void testingTickets(){

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //Open login page
        LoginPage login = new LoginPage(driver);

        //perform sign in
        login.signIn(PropertiesFile.getUsername(),PropertiesFile.getPassword());

        //initialize menu bar to switch between pages
        Menu menu = new Menu(driver);

        //Task #9. Point 4:
        //initialize dashboard page by clicking on menu button
        DashboardsPage dashboardsPage = menu.clickOnDashboard();
        //switch to Done Deadline view by pressing button
        dashboardsPage.clickDoneDeadline();
        //expand Deadline is over accordion
        dashboardsPage.clickOnSeadLineIsOver();

        //print all titles with разработка category
        System.out.println("All Titles of 'РАЗРАБОТКА' category");
        dashboardsPage.getTicketTitlesOfCategory("РАЗРАБОТКА");

        //print all titles with финансы category
        System.out.println("All Titles of 'ФИНАНСЫ' category");
        dashboardsPage.getTicketTitlesOfCategory("ФИНАНСЫ");

        //print all IDs with priority P3
        System.out.println("All IDs of 'P3' category");
        dashboardsPage.getIDsOfPriority("P3");

        logger.info("TEST SUCCESSFULLY COMPLETED");
    }
}
