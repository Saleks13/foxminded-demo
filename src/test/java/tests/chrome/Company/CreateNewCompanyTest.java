package tests.chrome.Company;

import config.BaseChromeConfiguration;
import dataBase.DataBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.CompaniesPage;
import pageObjects.LoginPage;
import pageObjects.Menu;
import utils.PropertiesFile;
import utils.RandomGenerator;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

@DisplayName("Tests to check creation of new Company")
public class CreateNewCompanyTest extends BaseChromeConfiguration {

    //init random generator to generate test data
    RandomGenerator randomGenerator = new RandomGenerator();
    //db instance to run queries
    DataBase dataBase = new DataBase();

    //keys-values to store test data
    String companyName = "companyName";
    String companyPrefix = "companyPrefix";
    String additionalShippingAddress = "additionalShippingAddress";
    String additionalShippingAddressComment = "additionalShippingAddressComment";
    String companyCountry = "companyCountry";
    String companyCity = "companyCity";
    String companyStreet = "companyStreet";
    String companyBuilding = "companyBuilding";
    String companyZipcode = "companyZipcode";
    String companyRoomNumber = "companyRoomNumber";
    String companySkype = "companySkype";
    String companyWebsite = "companyWebsite";
    String companyEmail = "companyEmail";
    String companyEdrpou = "companyEdrpou";
    String companyPhone = "companyPhone";

    @Test
    @DisplayName("Creation of new Company + validation in BD")
    public void CreateCompany() throws SQLException, InterruptedException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //hashmap to store testdata
        HashMap<String, String> company = new HashMap();

        //variable to store verification SQL query
        String query;

        //test data
        company.put(companyName, randomGenerator.generateName(6));
        company.put(companyPrefix, randomGenerator.generateName(6));
        company.put(additionalShippingAddress, randomGenerator.generateName(6));
        company.put(additionalShippingAddressComment, randomGenerator.generateName(8));
        company.put(companyCountry, randomGenerator.generateName(7));
        company.put(companyCity, randomGenerator.generateName(5));
        company.put(companyStreet, randomGenerator.generateName(9));
        company.put(companyBuilding, String.valueOf(randomGenerator.generateNumber(2)));
        company.put(companyZipcode, String.valueOf(randomGenerator.generateNumber(5)));
        company.put(companyRoomNumber, String.valueOf(randomGenerator.generateNumber(3)));
        company.put(companySkype, "Skype" + randomGenerator.generateName(4));
        company.put(companyWebsite, "www." + company.get(companyName) + ".com");
        company.put(companyEmail, "support@" + company.get(companyName) + ".com");
        company.put(companyEdrpou, Integer.toString(randomGenerator.generateNumber(10)));
        company.put(companyPhone, Integer.toString(randomGenerator.generateNumber(9)));

        //start webpage and perform sign-in
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());
        Menu menu = new Menu(driver);

        //open company page
        CompaniesPage companiesPage = menu.clickOnCompanies();
        //create new company using dedicated method
        companiesPage.createNewCompany(company.get(companyName), company.get(companyPrefix), company.get(additionalShippingAddress),
                company.get(additionalShippingAddressComment), company.get(companyCountry), company.get(companyCity),
                company.get(companyStreet), company.get(companyBuilding), company.get(companyZipcode), company.get(companyRoomNumber),
                company.get(companySkype), company.get(companyWebsite), company.get(companyEmail), company.get(companyEdrpou),
                company.get(companyPhone), 1);

        //query to get prefix of created company
        query = "Select ticket_prefix from company where name = '" + company.get(companyName) + "'";

        //validate that prefix used in the creation is equal to the one from DB
        assert dataBase.getValue(query).equals(company.get(companyPrefix).toUpperCase());
        System.out.println("Company created and present in the DataBase");

        //delete company to clean
        companiesPage.deleteFirstCompany();

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }

    @Test
    @DisplayName("Create new Company and check it's present on UI")
    public void task10part4() throws InterruptedException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //random generator is used to avoid creation of company with exiting name
        int randomNum = ThreadLocalRandom.current().nextInt(10000, 99999);
        //list of variables used to create new company
        String companyName = "Company SA1" + randomNum;
        String companyPrefix = "S" + randomNum;
        String additionalShippingAddress = "Perova, 3";
        String additionalShippingAddressComment = "Ship to this address";
        String companyCountry = "Ukraine";
        String companyCity = "Zaporizhzhia";
        String companyStreet = "Soborniy";
        String companyBuilding = "168";
        String companyZipcode = "69031";
        String companyRoomNumber = "13";
        String companySkype = "department_skype_account";
        String companyWebsite = "https://www.department.com";
        String companyEmail = "department@gmail.com";
        String companyEdrpou = "3015366945";
        String companyPhone = "+380332346583";
        int serviceLevel = 2;

        //load webapp and execute sign in
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());

        //create object of the menu class, for further navigation
        Menu menu = new Menu(driver);
        CompaniesPage companiesPage = menu.clickOnCompanies();

        //create new ticket method is called
        companiesPage.createNewCompany(companyName, companyPrefix, additionalShippingAddress, additionalShippingAddressComment, companyCountry,
                companyCity, companyStreet, companyBuilding, companyZipcode, companyRoomNumber, companySkype, companyWebsite,
                companyEmail, companyEdrpou, companyPhone, serviceLevel);

        //somehow helps to prevent element not found issue
        driver.navigate().refresh();

        //search for the company
        companiesPage.searchCompany(companyName);
        //check company is in the list;
        companiesPage.checkCompanyPresent(companyName);
        //delete
        companiesPage.deleteFirstCompany();

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }
}
