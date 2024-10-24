package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutStepOne extends BaseTest {

    public CheckOutStepOne() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    public WebElement firstNameField;

    @FindBy(id = "last-name")
    public WebElement lastNameField;

    @FindBy(id = "postal-code")
    public WebElement postalCode;

    @FindBy(id = "cancel")
    public WebElement cancelButton;

    @FindBy(id = "continue")
    public WebElement continueButton;

    @FindBy(className = "error-button")
    public WebElement errorButton;

    public void inputFirstName(String x) {
        firstNameField.sendKeys(x);
    }

    public void inputLastName(String x) {
        lastNameField.sendKeys(x);
    }

    public void inputPostalCode(String x) {
        postalCode.sendKeys(x);
    }

    public void clickCancelButton() {
        cancelButton.click();
    }

    public void clickContinueButton() {
        continueButton.click();
    }
}
