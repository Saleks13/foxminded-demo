package config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.PropertiesFile;

public class BaseFirefoxConfiguration {

        public WebDriver driver;

        //this constructor initializes driver
        public BaseFirefoxConfiguration() {
            System.setProperty("webdriver.gecko.driver", "src/main/java/helpFiles/geckodriver.exe"); //specify project path to driver
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
    }

    public void openWebSite(){
        driver.get(PropertiesFile.getBaseUrl());
    }

    public void closeWebSite(){
        driver.quit();
    }
}
