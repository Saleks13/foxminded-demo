package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected final Logger logger = LogManager.getLogger(getClass());

    public static WebDriver driver;

        public BasePage(WebDriver driver) {

            this.driver = driver;
            driver.manage().window().maximize();
            PageFactory.initElements(driver,this);
        }

}


