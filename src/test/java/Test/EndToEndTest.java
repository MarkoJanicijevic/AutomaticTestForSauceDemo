package Test;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EndToEndTest extends BaseTest {
    @BeforeMethod
    public void setUpPage() {
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @Test
    public void endToEnd() throws InterruptedException {
        logInPage.inputUsername("standard_user");
        logInPage.inputPassword("secret_sauce");
        logInPage.clickLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        inventoryPage.sideMenuButton.click();
        Assert.assertTrue(inventoryPage.logoutButton.isDisplayed());

        inventoryPage.clickOnAddToCart(0);
        Assert.assertTrue(inventoryPage.shoppingCartBadge.isDisplayed());
        Assert.assertEquals(inventoryPage.shoppingCartBadgeNumber(), "1");

        inventoryPage.clickOnShoppingCart();
        cartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");

        checkOutStepOne.inputFirstName("Marko");
        checkOutStepOne.inputLastName("Janicijevic");
        checkOutStepOne.inputPostalCode("11080");
        checkOutStepOne.clickContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
        Assert.assertTrue(checkOutStepTwo.finishButton.isDisplayed());

        checkOutStepTwo.clickFinishButton();
        Assert.assertEquals(checkOutComplete.readCompleteMessage(), "Thank you for your order!");
    }


}
