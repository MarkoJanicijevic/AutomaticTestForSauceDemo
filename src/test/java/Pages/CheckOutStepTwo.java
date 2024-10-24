package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutStepTwo extends BaseTest {

    public CheckOutStepTwo() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "finish")
    public WebElement finishButton;

    @FindBy(id = "cancel")
    public WebElement cancelPurchaseButton;

    public void clickFinishButton() {
        finishButton.click();
    }

    public void clickCancelPurchaseButton() {
        cancelPurchaseButton.click();
    }
}
