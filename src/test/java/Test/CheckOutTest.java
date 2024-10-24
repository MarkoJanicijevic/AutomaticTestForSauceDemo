package Test;

import Base.BaseTest;
import Base.ExcelWriter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckOutTest extends BaseTest {
    @BeforeMethod
    public void setUpPage() {
        driver.navigate().to("https://www.saucedemo.com/");
        preconditionLogIn();
        inventoryPage.removeAllItemsFromTheCart();
        preconditionAddTwoItemsAndNavigateToTheCart();
        cartPage.clickOnCheckoutButton();
    }

    @Test
    public void userCanProceedToCheckout() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
    }

    @Test
    public void userCanCancelOrder() {
        checkOutStepOne.clickCancelButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
    }

    @Test
    public void userCanNotProceedWithoutFirstName() {
        checkOutStepOne.inputLastName(excelReader.getStringData("Form information", 1, 1));
        checkOutStepOne.inputPostalCode(excelReader.getStringData("Form information", 1, 2));
        checkOutStepOne.clickContinueButton();
        Assert.assertTrue(checkOutStepOne.errorButton.isDisplayed());
    }

    @Test
    public void userCanNotProceedWithoutLastName() {
        checkOutStepOne.inputFirstName(excelReader.getStringData("Form information", 1, 0));
        checkOutStepOne.inputPostalCode(excelReader.getStringData("Form information", 1, 2));
        checkOutStepOne.clickContinueButton();
        Assert.assertTrue(checkOutStepOne.errorButton.isDisplayed());
    }

    @Test
    public void userCanNotProceedWithoutPostalCode() {
        checkOutStepOne.inputFirstName(excelReader.getStringData("Form information", 1, 0));
        checkOutStepOne.inputLastName(excelReader.getStringData("Form information", 1, 1));
        checkOutStepOne.clickContinueButton();
        Assert.assertTrue(checkOutStepOne.errorButton.isDisplayed());
    }

    @Test
    public void userCanProceedToStepTwoWithValidCredentials() {
        checkOutStepOne.inputFirstName(excelReader.getStringData("Form information", 1, 0));
        checkOutStepOne.inputLastName(excelReader.getStringData("Form information", 1, 1));
        checkOutStepOne.inputPostalCode(excelReader.getStringData("Form information", 1, 2));
        checkOutStepOne.clickContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
        Assert.assertTrue(checkOutStepTwo.finishButton.isDisplayed());
    }

    @Test
    public void userCanCancelPurchase() {
        checkOutStepOne.inputFirstName(excelReader.getStringData("Form information", 1, 0));
        checkOutStepOne.inputLastName(excelReader.getStringData("Form information", 1, 1));
        checkOutStepOne.inputPostalCode(excelReader.getStringData("Form information", 1, 2));
        checkOutStepOne.clickContinueButton();
        checkOutStepTwo.clickCancelPurchaseButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void userCanFinishPurchase() {
        checkOutStepOne.inputFirstName(excelReader.getStringData("Form information", 1, 0));
        checkOutStepOne.inputLastName(excelReader.getStringData("Form information", 1, 1));
        checkOutStepOne.inputPostalCode(excelReader.getStringData("Form information", 1, 2));
        checkOutStepOne.clickContinueButton();
        checkOutStepTwo.clickFinishButton();
        Assert.assertEquals(checkOutComplete.readCompleteMessage(), "Thank you for your order!");
    }



}
