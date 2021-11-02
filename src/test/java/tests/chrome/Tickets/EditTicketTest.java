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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@DisplayName("Tests check that editing of Ticket is working")
public class EditTicketTest extends BaseChromeConfiguration {

    @Test
    @DisplayName("Test editing of new Ticket")
    public void editNewTicketTest() throws InterruptedException, SQLException {

        //go to website under test - login form
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());
        //init menu
        Menu menu = new Menu(driver);
        //go to tickets page
        TicketsPage ticketsPage = menu.clickOnTickets();

        //init random generator to generate input data
        RandomGenerator randomGenerator = new RandomGenerator();
        String title = randomGenerator.generateName(5) + " " + randomGenerator.generateNumber(4);
        String description = randomGenerator.generateName(3) + " "
                + randomGenerator.generateName(6) + " "
                + randomGenerator.generateName(5) + " ";
        String category = "YouTube";
        String stage = "OPEN";
        String company = "Company16590";
        String contact = "Adam Harlem";
        String priority = "P4";
        String department = "Call Center";
        String manager = "Julius Caesar";
        String filePath = "\\src\\main\\java\\helpFiles\\screenshot1.png";


        //create new ticket
        ticketsPage.createNewGeneralTicket(title, description, category, stage, company, contact, priority, department, manager, filePath);

        //init database instance to execute queries
        DataBase dataBase = new DataBase();
        HashMap<String, String> conditions = new HashMap<>();
        //fill in hashmap with conditions for future query
        conditions.put("description", description);
        //execute query over ticket table
        ResultSet rs = dataBase.selectResultSet("ticket", conditions);
        //get id of the recently create ticket
        String id = dataBase.getValue(rs, "id");

        //print id - for test purposes
        //System.out.println("ID is: " + id);

        //create new variables, which will be used for to set new values during ticket editing
        String editedTitle = title + "EDITED";
        String editedDescription = description + "EDITED";
        String editedCategory = "Events";
        String editedStage = "IN PROGRESS";
        String editedCompany = "Sergii C1";
        String editedContact = "Adam Savage";
        String editedPriority = "P3";
        String editedDepartment = "Managers";
        String editedManager = "Valeria Alexandre";

        //edit ticket
        ticketsPage.searchTicket(title)
                .expandTicketSearchAccordion("Tickets")
                .openTicketWithName(title)
                .editTicket()
                .setTicketTitle(editedTitle)
                .setTicketDescription(editedDescription)
                .setTicketCategory(editedCategory)
                .setTicketStage(editedStage)
                .setTicketCompany(editedCompany)
                .setTicketContact(editedContact)
                .setTicketPriority(editedPriority)
                .setTicketDepartment(editedDepartment)
                .setTicketManager(editedManager)
                .removeAttachedFile()
                .attachNewFileOnEditPage("\\src\\main\\java\\helpFiles\\screenshot2.png")
                .clickSubmitButton();


        //create new hashmap with conditions for future query
        HashMap<String, String> conditions1 = new HashMap<>();
        //use id obtained in a query after ticket creation
        conditions1.put("id", id);
        //execute query
        ResultSet rs1 = dataBase.selectResultSet("ticket", conditions1);
        //extract description of edited ticket
        String actualDescription = dataBase.getValue(rs1, "description");

        //compare description used during editing via UI and the one in DB
        assert actualDescription.equals(editedDescription);

    }
}
