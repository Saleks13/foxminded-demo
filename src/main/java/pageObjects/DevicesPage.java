package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DevicesPage extends BasePage {

    public DevicesPage(WebDriver driver) {
        super(driver);
    }

    //overview page webelements
    @FindBy(id = "device-list-new-device")
    WebElement newDeviceButton;

    @FindBy(id = "device-list-delete")
    List<WebElement> deleteDeviceButtons;

    @FindBy(id = "device-list-edit")
    List<WebElement> editDeviceButtons;

    @FindBy(xpath = "//div/div/table/tbody/tr/td[4]")
    List<WebElement> deviceTitleList;

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

    @FindBy(id = "send-notification")
    WebElement newDeviceSendNotificationCheckbox;

    @FindBy(id = "device-form-submit-btn")
    WebElement newDeviceSubmitButton;

    @FindBy(id = "device-form-cancel-btn")
    WebElement newDeviceCancelButton;

    public DevicesPage clickOnNewDeviceButton() {
        this.newDeviceButton.click();
        logger.info("New device page opened");
        return this;
    }

}
