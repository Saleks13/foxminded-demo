package tests.chrome.Company;

import config.BaseChromeConfiguration;
import dataBase.DataBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.CompaniesPage;
import pageObjects.LoginPage;
import pageObjects.Menu;
import utils.PropertiesFile;
import utils.RandomGenerator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@DisplayName("Tests to check creation of Company")
public class CreateNewCompanyDbCheckTest extends BaseChromeConfiguration {

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
    @DisplayName("Create new Company and verify it in DB")
    public void CreateCompany() throws SQLException, InterruptedException {

        logger.info(new Throwable().getStackTrace()[0].getMethodName() + " TEST STARTED");

        //hashmap to store testdata
        HashMap<String, String> company = new HashMap();
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

        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());
        Menu menu = new Menu(driver);
        CompaniesPage companiesPage = menu.clickOnCompanies();

        //create new company using dedicated method
        companiesPage.createNewCompany(company.get(companyName), company.get(companyPrefix), company.get(additionalShippingAddress),
                company.get(additionalShippingAddressComment), company.get(companyCountry), company.get(companyCity),
                company.get(companyStreet), company.get(companyBuilding), company.get(companyZipcode), company.get(companyRoomNumber),
                company.get(companySkype), company.get(companyWebsite), company.get(companyEmail), company.get(companyEdrpou),
                company.get(companyPhone), 1);

        //to assert our company is present, first we need to execute search
        companiesPage.searchCompany(company.get(companyName));
        //assert found company is present on the page with correct name
        companiesPage.checkCompanyPresent(company.get(companyName));

        //this hashmap stores conditions for the future query to DB
        HashMap<String, String> conditions = new HashMap<>();
        conditions.put("name", company.get(companyName));
        conditions.put("country", company.get(companyCountry));
        conditions.put("city", company.get(companyCity));

        //getting result set from Manager DB using conditions above
        ResultSet rs = dataBase.selectResultSet("company", conditions);

        //converting result to a list of hashmaps, so it's easier to get values
        List<HashMap<String, String>> resultList = dataBase.convertToListHashMap(rs);

        //getting values of our input parameters but from DB
        String nameDb = resultList.get(0).get("name");
        String ticketPrefixDb = resultList.get(0).get("ticket_prefix").toLowerCase();
        String countryDb = resultList.get(0).get("country");
        String cityDb = resultList.get(0).get("city");
        String streetDb = resultList.get(0).get("street");
        String buildingDb = resultList.get(0).get("building");
        String zipcodeDb = resultList.get(0).get("zipcode");
        String roomNumberDb = resultList.get(0).get("room_number");
        String skypeDb = resultList.get(0).get("skype");
        String websiteDb = resultList.get(0).get("website");
        String emailDb = resultList.get(0).get("email");
        String edrpouDb = resultList.get(0).get("edrpou");
        String phoneDb = resultList.get(0).get("phone");

        //checking input date equals to the one from DB
        Assertions.assertEquals(nameDb, company.get(companyName));
        Assertions.assertEquals(ticketPrefixDb, company.get(companyPrefix).toLowerCase());
        Assertions.assertEquals(countryDb, company.get(companyCountry));
        Assertions.assertEquals(cityDb, company.get(companyCity));
        Assertions.assertEquals(streetDb, company.get(companyStreet));
        Assertions.assertEquals(buildingDb, company.get(companyBuilding));
        Assertions.assertEquals(zipcodeDb, company.get(companyZipcode));
        Assertions.assertEquals(roomNumberDb, company.get(companyRoomNumber));
        Assertions.assertEquals(skypeDb, company.get(companySkype));
        Assertions.assertEquals(websiteDb, company.get(companyWebsite));
        Assertions.assertEquals(emailDb, company.get(companyEmail));
        Assertions.assertEquals(edrpouDb, company.get(companyEdrpou));
        Assertions.assertEquals(phoneDb, company.get(companyPhone));

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }
}
