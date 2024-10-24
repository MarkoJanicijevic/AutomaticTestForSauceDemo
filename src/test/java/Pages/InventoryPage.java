package Pages;

import Base.BaseTest;
import Base.ExcelWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InventoryPage extends BaseTest {
    public List<String> manuallySortedItems;
    public List<Double> manuallySortedPrices;

    public InventoryPage() {
        PageFactory.initElements(driver, this);
    }

    //______________________________________________________________________________________WEB ELEMENTS

    @FindBy(id = "react-burger-menu-btn")
    public WebElement sideMenuButton;

    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutButton;

    @FindBy(className = "inventory_item")
    public List<WebElement> listOfItems;

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    public List<WebElement> listOfAddToCartButtons;

    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory")
    public List<WebElement> listOfRemoveFromCartButtons;

    @FindBy(className = "shopping_cart_link")
    public WebElement shoppingCart;

    @FindBy(className = "shopping_cart_badge")
    public WebElement shoppingCartBadge;

    @FindBy(className = "inventory_item_name")                              //LIST OF ITEMS BEFORE SORTING
    public List<WebElement> inventoryList;

    @FindBy(css = "option[value]")
    public List<WebElement> sortingOptions;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> getListOfPrices;

    @FindBy(id = "inventory_sidebar_link")
    public WebElement allItemsButton;


    //__________________________________________________________________________________________________________


    public String shoppingCartBadgeNumber() {
        return shoppingCartBadge.getText();
    }

    public void sideMenuButtonClick() {
        sideMenuButton.click();
    }

    public void clickOnLogOutButton() {
        logoutButton.click();
    }

    public void clickOnAllItemsButton() {
        allItemsButton.click();
    }

    public void clickOnAddToCart(int x) {
        listOfAddToCartButtons.get(x).click();
    }

    public void addAllItemsToTheCart() {
        for (int i = listOfAddToCartButtons.size() - 1; i >= 0; i--) {
            listOfAddToCartButtons.get(i).click();
        }
    }

    public void removeAllItemsFromTheCart() {
        for (int i = listOfRemoveFromCartButtons.size() - 1; i >= 0; i--) {
            listOfRemoveFromCartButtons.get(i).click();
        }
    }

    public void clickOnShoppingCart() {
        shoppingCart.click();
    }

    public String numberOfItemsToString() {
        return String.valueOf(listOfItems.size());

    }

    public List<String> listOfPricesString() {
        return listElementsToString(getListOfPrices);
    }

    public List<Double> listPricesToDouble() {
        return listStringToDoubleWithoutFirstChar(listOfPricesString());
    }


    //_____________________________________________________________________________________________________________


    public void clickSortItemsAtoZ() {
        sortingOptions.get(0).click();
    }

    public void clickSortItemsZtoA() {
        sortingOptions.get(1).click();
    }

    public void clickSortItemsLowToHigh() {
        sortingOptions.get(2).click();
    }

    public void clickSortItemsHighToLow() {
        sortingOptions.get(3).click();
    }


    //_____________________________________________________________________________ Compare Lists and change List to List of Strings


    //__________________________________________________________________________________________________

    public List<String> inventoryListString() {                     //LIST OF ITEMS TO BEFORE SORTING STRING
        return listElementsToString(inventoryList);
    }

    public List<String> manuallySortingAZ() {
        manuallySortedItems = inventoryListString();
        Collections.sort(manuallySortedItems);
        return manuallySortedItems;
    }

    public List<String> manuallySortingZA() {
        manuallySortedItems = inventoryListString();
        manuallySortedItems.sort(Collections.reverseOrder());
        return manuallySortedItems;
    }

    public List<Double> manuallySortingByPriceAsc() {
        manuallySortedPrices = listPricesToDouble();
        Collections.sort(manuallySortedPrices);
        return manuallySortedPrices;
    }

    public List<Double> manuallySortingByPriceDesc() {
        manuallySortedPrices = listPricesToDouble();
        manuallySortedPrices.sort(Collections.reverseOrder());
        return manuallySortedPrices;
    }






    //___________________________________________________________________________________________________________________


    public void addItemDataToExcel() throws InterruptedException {


        String filepath = "C:\\Users\\Djang\\FinalProjectITBootcamp\\src\\test\\java\\TestData.xlsx";
        String sheetName = "ItemData";
        int rowNumber = 0;
        int columnNumber = 0;
        String data = "";

        clickOnAllItemsButton();
        clickSortItemsAtoZ();


        for (int i = 0; i < inventoryListString().size(); i++) {
            rowNumber++;
            ExcelWriter.excelWriter(filepath, sheetName, rowNumber, 1, inventoryListString().get(i));
            ExcelWriter.excelWriter(filepath, sheetName, rowNumber, 2, listOfPricesString().get(i));

        }


    }


}
