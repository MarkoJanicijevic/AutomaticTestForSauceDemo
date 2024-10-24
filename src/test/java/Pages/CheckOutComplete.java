package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutComplete extends BaseTest {

    public CheckOutComplete() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "complete-header")
    public WebElement completeMessage;

    public String readCompleteMessage() {
        return completeMessage.getText();
    }
}
