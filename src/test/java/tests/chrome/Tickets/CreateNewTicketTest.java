package tests.chrome.Tickets;

import config.BaseChromeConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.LoginPage;
import pageObjects.Menu;
import pageObjects.TicketsPage;
import utils.PropertiesFile;

import java.util.concurrent.ThreadLocalRandom;

@DisplayName("Tests to check creation of new Ticket")
public class CreateNewTicketTest extends BaseChromeConfiguration {

    @Test
    @DisplayName("Create new Ticket with static data")
    public void task10part2() throws InterruptedException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //random generator to avoid creation with same name
        int randomNum = ThreadLocalRandom.current().nextInt(10000, 99999);
        //list of variables to create new ticket
        String newTicketTitle = "SAtest1" + randomNum;
        String newTicketDescription = "New ticket is being created";
        String newTicketCategory = "YouTube";
        String newTicketStage = "OPEN";
        String newTicketCompany = "Sergii C1";
        String newTicketContact = "Adam Savage";
        String newTicketPriority = "P4";
        String newTicketDepartment = "Call Center";
        String newTicketManager = "Julius Caesar";
        String filePath = "\\src\\main\\java\\helpFiles\\screenshot.png";

        //load webapp and execute sign in
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());

        //create object of the menu class, for further navigation
        Menu menu = new Menu(driver);
        TicketsPage ticketsPage = menu.clickOnTickets();

        //method to create new ticker is called
        ticketsPage.createNewGeneralTicket(
                newTicketTitle,
                newTicketDescription,
                newTicketCategory,
                newTicketStage,
                newTicketCompany,
                newTicketContact,
                newTicketPriority,
                newTicketDepartment,
                newTicketManager,
                filePath);

        //check new ticket is present
        ticketsPage.checkTicketPresent(newTicketTitle);

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }
}
