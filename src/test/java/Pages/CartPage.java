package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BaseTest {

    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "continue-shopping")
    public WebElement continueButton;

    @FindBy (id = "checkout")
    public WebElement checkoutButton;

    @FindBy (className = "inventory_item_name")
    public List<WebElement> listOfItemsInTheCart;

    @FindBy (css = ".btn.btn_secondary.btn_small.cart_button")
    public List<WebElement> removeButtonsInTheCart;

    @FindBy (className = "inventory_item_price")
    public List<WebElement> listOfItemPriceInCart;

    public void removeAnItem (int x) {
        removeButtonsInTheCart.get(x).click();
    }

    public void removeAllItemsFromTheCart () {
        for (int i = removeButtonsInTheCart.size() - 1; i >= 0 ; i--) {
            removeButtonsInTheCart.get(i).click();
        }
    }

    public void clickOnContinueShoppingButton () {
        continueButton.click();
    }

    public void clickOnCheckoutButton () {
        checkoutButton.click();
    }

    public List<String> listOfItemsInCartString () {
        return inventoryPage.listElementsToString(listOfItemsInTheCart);
    }

    public List<String> pricesOfItemsInCartString () {
        return inventoryPage.listElementsToString(listOfItemPriceInCart);
    }





}
