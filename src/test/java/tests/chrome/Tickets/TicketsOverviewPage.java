package tests.chrome.Tickets;

import config.BaseChromeConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.LoginPage;
import pageObjects.Menu;
import pageObjects.TicketsPage;
import utils.PropertiesFile;

@DisplayName("Tests to check specific data present on Ticket Overview page")
public class TicketsOverviewPage extends BaseChromeConfiguration {

    @Test
    @DisplayName("Print IDs, Titles, Assignees, Stages")
    public void testingTickets() {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //Open login page
        LoginPage login = new LoginPage(driver);

        //perform sign in
        login.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());

        //initialize menu bar to switch between pages
        Menu menu = new Menu(driver);

        //Open Tickets page
        TicketsPage ticketsPage = menu.clickOnTickets();

        //refresh the page to avoid "element is not attached to the page document"
        driver.navigate().refresh();

        System.out.println("List of all IDs:");
        //call method to print Titles of all Tickets present on Tickets page
        ticketsPage.getId();

        System.out.println("List of all Titles:");
        //call method to print Titles of all Tickets present on Tickets page
        ticketsPage.getTitle();

        System.out.println("List of all Assignees:");
        //call method to print Titles of all Tickets present on Tickets page
        ticketsPage.getAssignee();

        System.out.println("List of all Stages:");
        //call method to print Titles of all Tickets present on Tickets page
        ticketsPage.getStage();

        logger.info("TEST SUCCESSFULLY COMPLETED");
    }
}
