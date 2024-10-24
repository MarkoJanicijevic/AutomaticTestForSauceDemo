package Test;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingItemsTest extends BaseTest {
    @BeforeMethod
    public void setUpPage() {
        driver.navigate().to("https://www.saucedemo.com/");
        preconditionLogIn();
    }

    @Test
    public void VerifyThatUserCanSortItemsByNameAtoZ() throws InterruptedException {
        inventoryPage.clickSortItemsZtoA();
        inventoryPage.clickSortItemsAtoZ();
        Assert.assertTrue(inventoryPage.compareIfListsMatch(inventoryPage.inventoryListString(), inventoryPage.manuallySortingAZ()));
    }

    @Test
    public void VerifyThatUserCanSortItemsByNameZtoA() throws InterruptedException {
        inventoryPage.clickSortItemsAtoZ();
        inventoryPage.clickSortItemsZtoA();
        Assert.assertTrue(inventoryPage.compareIfListsMatch(inventoryPage.inventoryListString(), inventoryPage.manuallySortingZA()));
    }

    @Test
    public void VerifyThatUserCanSortItemsByPriceAsc() {
        inventoryPage.clickSortItemsHighToLow();
        inventoryPage.clickSortItemsLowToHigh();
        Assert.assertTrue((inventoryPage.compareIfListsMatchDouble(inventoryPage.listPricesToDouble(), inventoryPage.manuallySortingByPriceAsc())));

    }

    @Test
    public void VerifyThatUserCanSortItemsByPriceDesc() {
        inventoryPage.clickSortItemsLowToHigh();
        inventoryPage.clickSortItemsHighToLow();
        Assert.assertTrue((inventoryPage.compareIfListsMatchDouble(inventoryPage.listPricesToDouble(), inventoryPage.manuallySortingByPriceDesc())));

    }
}
