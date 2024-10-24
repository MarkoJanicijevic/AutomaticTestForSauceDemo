package Test;

import Base.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogInTest extends BaseTest {

    @BeforeMethod
    public void setUpPage() {
        driver.navigate().to("https://www.saucedemo.com/");

    }

    @Test
    public void userCanLogInWithValidCredentials() throws InterruptedException {
        logInPage.inputUsername(excelReader.getStringData("Credentials", 1, 0));
        logInPage.inputPassword(excelReader.getStringData("Credentials", 1, 1));
        logInPage.clickLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        inventoryPage.sideMenuButton.click();
        Assert.assertTrue(inventoryPage.logoutButton.isDisplayed());

    }

    @Test
    public void userCanNotLogInWithInvalidUsername() {
        logInPage.inputUsername(excelReader.getStringData("Credentials", 1, 2));
        logInPage.inputPassword(excelReader.getStringData("Credentials", 1, 1));
        logInPage.clickLoginButton();
        Assert.assertTrue(logInPage.loginButton.isDisplayed());
    }

    @Test
    public void userCanNotLogInWithInvalidPassword() {
        logInPage.inputUsername(excelReader.getStringData("Credentials", 1, 0));
        logInPage.inputPassword(excelReader.getStringData("Credentials", 1, 3));
        logInPage.clickLoginButton();
        Assert.assertTrue(logInPage.loginButton.isDisplayed());
    }

    @Test
    public void checkIfUserCanLogOut() throws InterruptedException {
        preconditionLogIn();
        inventoryPage.sideMenuButton.click();
        inventoryPage.clickOnLogOutButton();
        Assert.assertTrue(logInPage.loginButton.isDisplayed());

    }


}
