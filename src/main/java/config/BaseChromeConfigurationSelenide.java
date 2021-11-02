package config;

import com.codeborne.selenide.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.PropertiesFile;

import static com.codeborne.selenide.Selenide.open;

@Execution(ExecutionMode.CONCURRENT)
public class BaseChromeConfigurationSelenide {

    protected final Logger logger = LogManager.getLogger(getClass());

    public BaseChromeConfigurationSelenide() {
    }

    @BeforeEach
    public void openWebSite() {

        //ggr
        //Configuration.remote = "http://test:test-password@165.22.123.70:4444/wd/hub";
        //selenoid1
        Configuration.remote = "http://165.232.67.221:4444/wd/hub";
        //selenoid2
        //Configuration.remote = "http://64.227.71.69:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "83.0";
        Configuration.browserSize = "1920x1080";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        Configuration.browserCapabilities = capabilities;
        open(PropertiesFile.getBaseUrl());
    }

    //Added thread sleep only for testing purposes
    @AfterEach
    public void afterTest() throws InterruptedException {
        Thread.sleep(1000);
    }


}
