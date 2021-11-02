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

@DisplayName("Tests check Search of Category")
public class SearchCompanyTest extends BaseChromeConfiguration {

    //init random generator to generate test data
    RandomGenerator randomGenerator = new RandomGenerator();
    //db instance to run queries
    DataBase dataBase = new DataBase();

    @Test
    @DisplayName("Checks that UI shows found random Category")
    public void SearchCompany() throws SQLException, InterruptedException {

        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");

        //query to get name of some real/not deleted company
        String query = "select name from company where deleted = false and name is not null and is_fake_company is false order by RANDOM() limit 1";

        //execute query and store it in the variable
        String title = dataBase.getValue(query);

        //start webpage and perform sign-in
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(PropertiesFile.getUsername(), PropertiesFile.getPassword());
        Menu menu = new Menu(driver);

        //go to companies page
        CompaniesPage companiesPage = menu.clickOnCompanies();
        //search for company using title, which was obtained using SQL query
        companiesPage.searchCompany(title);
        //assert company is present on the page
        companiesPage.checkCompanyPresent(title);

        logger.info("TEST SUCCESSFULLY COMPLETED");

    }
}
