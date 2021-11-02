package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class NewDevicePage extends BasePage {

    public NewDevicePage(WebDriver driver) {
        super(driver);
    }

    //newDevice page webelements
    @FindBy(id = "name")
    WebElement newDeviceTitleField;

    @FindBy(id = "ip")
    WebElement newDeviceIpField;

    @FindBy(id = "port")
    WebElement newDevicePortField;

    @FindBy(id = "password")
    WebElement newDevicePasswordField;

    @FindBy(id = "device-form-company-select")
    WebElement newDeviceCompanySelect;

    @FindBy(id = "device-form-contact-select")
    WebElement newDeviceContactSelect;

    @FindBy(xpath = "//div/div/form/div[7]/div/div/label/span")
    WebElement newDeviceSendNotificationCheckbox;

    @FindBy(id = "device-form-submit-btn")
    WebElement newDeviceSubmitButton;

    @FindBy(id = "device-form-cancel-btn")
    WebElement newDeviceCancelButton;

    WebDriverWait wait = new WebDriverWait(driver, 15);


    public NewDevicePage(WebDriver driver, String deviceTitle, String deviceIp, int devicePort,
                         String devicePassword, String deviceCompany, String deviceContact, boolean sendNotifications) {
        super(driver);

        logger.info("Method to create new device - started");

        wait.until(ExpectedConditions.visibilityOf(newDeviceTitleField));
        newDeviceTitleField.sendKeys(deviceTitle);
        wait.until(ExpectedConditions.visibilityOf(newDeviceIpField));
        newDeviceIpField.sendKeys(deviceIp);
        wait.until(ExpectedConditions.visibilityOf(newDevicePortField));
        newDevicePortField.sendKeys(Integer.toString(devicePort));
        wait.until(ExpectedConditions.visibilityOf(newDevicePasswordField));
        newDevicePasswordField.sendKeys(devicePassword);
        Select company = new Select(newDeviceCompanySelect);
        company.selectByVisibleText(deviceCompany);
        Select contact = new Select(newDeviceContactSelect);
        contact.selectByVisibleText(deviceContact);
        wait.until(ExpectedConditions.visibilityOf(newDeviceSendNotificationCheckbox));

        if (sendNotifications && newDeviceSendNotificationCheckbox.isSelected()) {
            newDeviceSendNotificationCheckbox.click();
        } else if (!sendNotifications && !newDeviceSendNotificationCheckbox.isSelected()) {
            newDeviceSendNotificationCheckbox.click();
        }
        newDeviceSubmitButton.click();

        logger.info("Method to create new device - complete");

    }

    public static class Builder {
        private String deviceTitle;
        private String deviceIp;
        private int devicePort;
        private String devicePassword;
        private String deviceCompany;
        private String deviceContact;
        private boolean sendNotifications;

        public Builder() {
        }

        public static Logger logger = LogManager.getLogger(Builder.class);

        public Builder setDeviceTitle(String deviceTitle) {
            this.deviceTitle = deviceTitle;
            logger.info("Title of new device set");
            return this;
        }

        public Builder setDeviceIp(String deviceIp) {
            this.deviceIp = deviceIp;
            logger.info("IP address of new device set");
            return this;
        }

        public Builder setDevicePort(int devicePort) {
            this.devicePort = devicePort;
            logger.info("Port of new device set");
            return this;
        }

        public Builder setDevicePassword(String devicePassword) {
            this.devicePassword = devicePassword;
            logger.info("Password of new device set");
            return this;
        }

        public Builder setDeviceCompany(String deviceCompany) {
            this.deviceCompany = deviceCompany;
            logger.info("Company of new device set");
            return this;
        }

        public Builder setDeviceContact(String deviceContact) {
            this.deviceContact = deviceContact;
            logger.info("Contact of new device set");
            return this;
        }

        public Builder setDeviceNotifications(boolean sendNotifications) {
            this.sendNotifications = sendNotifications;
            logger.info("Notifications are ON for new device");
            return this;
        }

        public NewDevicePage build() {

            logger.info("Method to build new device");

            return new NewDevicePage(driver, deviceTitle, deviceIp,
                    devicePort, devicePassword,
                    deviceCompany, deviceContact,
                    sendNotifications);
        }

    }

}
