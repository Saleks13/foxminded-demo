package config;

import com.codeborne.selenide.Browser;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.PropertiesFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.System.currentTimeMillis;

public class BaseChromeConfiguration {

    protected final Logger logger = LogManager.getLogger(getClass());
    public RemoteWebDriver driver;

    //this constructor initializes driver
    public BaseChromeConfiguration() {

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName(BrowserType.CHROME);
        try {
            driver = new RemoteWebDriver(new URL("http://165.232.67.221:4444/wd/hub"), cap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void openWebSite() {
        driver.get(PropertiesFile.getBaseUrl());
    }

    @AfterEach
    public void closeWebSite() throws IOException {

        String workingDir = System.getProperty("user.dir");

        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_dd_MM_HH_mm_ss");
        String timeString  = dateFormat.format(new Date());

        FileUtils.copyFile(source, new File(workingDir + "\\src\\main\\Screenshots\\screenshot_"+timeString+".png"));
        System.out.println("the Screenshot is taken and placed: " + workingDir + "\\src\\main\\Screenshots\\screenshot_"+timeString+".png");
        driver.quit();

    }
}
