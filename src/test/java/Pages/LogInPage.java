package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LogInPage extends BaseTest {

    public LogInPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    public WebElement usernameField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    @FindBy(className = "login-credentials")
    public List<WebElement> listOfCredentials;

    public void inputUsername(String x) {
        usernameField.sendKeys(x);
    }

    public void inputPassword(String x) {
        passwordField.sendKeys(x);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

//    public List<String> listOfCredentials_string (List<WebElement> x) {
//        ArrayList<String> listOfCredentials_string = new ArrayList<>();
//        for (int i = 0; i < listOfCredentials.size(); i++) {
//            listOfCredentials_string.get(i) = listOfCredentials.get(i).getText();
//        }
//        return listOfCredentials_string;
//    }
}
