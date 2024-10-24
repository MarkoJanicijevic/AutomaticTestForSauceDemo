package Test;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddToCartTest extends BaseTest {
    @BeforeMethod
    public void setUpPage() {
        driver.navigate().to("https://www.saucedemo.com/");
        preconditionLogIn();
    }

    @Test
    public void checkIfUserCanAddAnItemToTheCart() throws InterruptedException {
        inventoryPage.clickOnAddToCart(0);
        Thread.sleep(2000);
        Assert.assertTrue(inventoryPage.shoppingCartBadge.isDisplayed());
        Assert.assertEquals(inventoryPage.shoppingCartBadgeNumber(), "1");
        inventoryPage.removeAllItemsFromTheCart();
    }

    @Test
    public void checkIfUserCanAddMultipleItemsToTheCart() throws InterruptedException {
        inventoryPage.clickOnAddToCart(5);
        inventoryPage.clickOnAddToCart(4);
        Thread.sleep(2000);
        Assert.assertTrue(inventoryPage.shoppingCartBadge.isDisplayed());
        Assert.assertEquals(inventoryPage.shoppingCartBadgeNumber(), "2");
        inventoryPage.removeAllItemsFromTheCart();

    }

    @Test
    public void checkIfUserCanAddAllItemsToTheCart() throws InterruptedException {
        inventoryPage.addAllItemsToTheCart();
        Thread.sleep(2000);
        Assert.assertTrue(inventoryPage.shoppingCartBadge.isDisplayed());
        Assert.assertEquals(inventoryPage.shoppingCartBadgeNumber(), inventoryPage.numberOfItemsToString());
        inventoryPage.removeAllItemsFromTheCart();

    }

    @Test
    public void checkIfUserCanRemoveAllItemsFromTheCart() throws InterruptedException {
        inventoryPage.clickOnAddToCart(0);
        inventoryPage.removeAllItemsFromTheCart();
        Assert.assertEquals(inventoryPage.listOfAddToCartButtons.size(), inventoryPage.listOfItems.size());
        inventoryPage.addAllItemsToTheCart();
        inventoryPage.removeAllItemsFromTheCart();
        Assert.assertEquals(inventoryPage.listOfAddToCartButtons.size(), inventoryPage.listOfItems.size());

    }

    @Test
    public void checkIfRightNumberOfItemsIsInTheCart() {
        preconditionAddTwoItemsAndNavigateToTheCart();
        Assert.assertEquals(cartPage.listOfItemsInTheCart.size(), 2);
    }

    @Test
    public void checkIfUserCanRemoveAnItemFromCartPage() throws InterruptedException {
        preconditionAddTwoItemsAndNavigateToTheCart();
        Thread.sleep(3000);
        int numberOfItems = cartPage.removeButtonsInTheCart.size();
        cartPage.removeAnItem(0);
        Assert.assertEquals(cartPage.listOfItemsInTheCart.size(), numberOfItems - 1);

    }

    @Test
    public void checkIfUserCanRemoveAllItemsFromCartPage() {
        inventoryPage.addAllItemsToTheCart();
        inventoryPage.clickOnShoppingCart();
        cartPage.removeAllItemsFromTheCart();
        Assert.assertEquals(cartPage.listOfItemsInTheCart.size(), 0);
    }

    @Test
    public void checkIfUserCanContinueShoppingAfterAddingItemToTheCart() {
        inventoryPage.clickOnAddToCart(0);
        inventoryPage.clickOnShoppingCart();
        cartPage.clickOnContinueShoppingButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

//    @Test
//    public void Test1() throws InterruptedException {
//        inventoryPage.addAllItemsToTheCart();
//        inventoryPage.clickOnShoppingCart();
//        Thread.sleep(2000);
//        System.out.println(cartPage.listOfItemsInCartString());
//        System.out.println(cartPage.pricesOfItemsInCartString());
//    }


}
