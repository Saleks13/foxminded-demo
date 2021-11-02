package tests.chrome.Tickets;

import config.BaseChromeConfiguration;
import dataBase.DataBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.LoginPage;
import pageObjects.Menu;
import pageObjects.TicketsPage;
import utils.PropertiesFile;
import utils.RandomGenerator;

import java.sql.SQLException;
import java.util.HashMap;

@DisplayName("Tests to check creation of new Ticket of inner type")
public class CreateNewInnerTicketTest extends BaseChromeConfiguration {

    //create new instance of randomGenerator
    RandomGenerator randomGenerator = new RandomGenerator();
    int randomNum = randomGenerator.generateNumber(6);

    //database instance to run queries
    DataBase dataBase = new DataBase();

    //initialize keys-names for the future hashmap
    String newTicketType = "newTicketType";
    String newTicketTitle = "newTicketTitle";
    String newTicketDescription = "newTicketDescription";
    String newTicketCategory = "newTicketCategory";
    String newTicketStage = "newTicketStage";
    String newTicketCompany = "newTicketCompany";
    String newTicketContact = "newTicketContact";
    String newTicketPriority = "newTicketPriority";
    String newTicketDepartment = "newTicketDepartment";
    String newTicketManager = "newTicketManager";
    String filePath = "filePath";

    @Test
    @DisplayName("Create new inner Ticket")
    public void InnerTicketCreation() throws SQLException {

        logger.info(new Throwable().getStackTrace()[0].getMethodName() + " test started");

        //new hashmaps to fill it in with test data
        HashMap<String, String> ticket = new HashMap<>();

        //fill in hashmap with data
        ticket.put(newTicketType, "Inner");
        ticket.put(newTicketTitle, "TicketName" + randomNum);
        ticket.put(newTicketDescription, "Ticket Description" + randomNum);
        ticket.put(newTicketCategory, "YouTube");
        ticket.put(newTicketStage, "OPEN");
        ticket.put(newTicketPriority, "P4");
        ticket.put(newTicketDepartment, "Call Center");
        ticket.put(newTicketManager, "Julius Caesar");
        ticket.put(filePath, "\\src\\main\\java\\helpFiles\\screenshot.png");

        //open web-site under test and perform log in
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());
        Menu menu = new Menu(driver);

        //go to tickets page using menu bar
        TicketsPage ticketsPage = menu.clickOnTickets();
        //create new inner ticket using fluent pattern
        ticketsPage
                .clickCreateNewTicket()
                .selectInnerTicketType()
                .setTicketTitle(ticket.get(newTicketTitle))
                .setTicketDescription(ticket.get(newTicketDescription))
                .setTicketCategory(ticket.get(newTicketCategory))
                .setTicketStage(ticket.get(newTicketStage))
                .setTicketPriority(ticket.get(newTicketPriority))
                .setTicketDepartment(ticket.get(newTicketDepartment))
                .setTicketManager(ticket.get(newTicketManager))
                .selectFiles(ticket.get(filePath))
                .clickSubmitButton();

        //string that contains query, which returns description of recently created ticket via its name
        String query = "Select description from ticket where title ='" + ticket.get(newTicketTitle) + "'";

        //assert that description in DB is equal to the one we used
        assert dataBase.getValue(query).equals(ticket.get(newTicketDescription));

        logger.info("TEST SUCCESSFULLY COMPLETED");
    }

}
