package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    public static WebDriver driver;
    public LogInPage logInPage;
    public InventoryPage inventoryPage;
    public CartPage cartPage;
    public CheckOutStepOne checkOutStepOne;
    public CheckOutStepTwo checkOutStepTwo;
    public CheckOutComplete checkOutComplete;
    public ExcelReader excelReader;
    public ExcelWriter excelWriter;

    @BeforeClass
    public void setUP() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        logInPage = new LogInPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        checkOutStepOne = new CheckOutStepOne();
        checkOutStepTwo = new CheckOutStepTwo();
        checkOutComplete = new CheckOutComplete();
        excelReader = new ExcelReader("C:\\Users\\Djang\\FinalProjectITBootcamp\\src\\test\\java\\TestData.xlsx");
        excelWriter = new ExcelWriter();


    }

    public boolean compareIfListsMatch(List<String> x, List<String> y) {
        boolean doTheyMatch = false;
        for (int i = 0; i < x.size(); i++) {
            if (x.size() == y.size() && x.get(i).equals(y.get(i))) {
                doTheyMatch = true;
            } else {
                doTheyMatch = false;
            }
        }
        return doTheyMatch;

    }

    public boolean compareIfListsMatchDouble(List<Double> x, List<Double> y) {
        boolean doTheyMatch = false;
        for (int i = 0; i < x.size(); i++) {
            if (x.size() == y.size() && x.get(i).equals(y.get(i))) {
                doTheyMatch = true;
            } else {
                doTheyMatch = false;
            }
        }
        return doTheyMatch;

    }

    public List<String> listElementsToString(List<WebElement> x) {
        ArrayList<String> y = new ArrayList<>();
        for (int i = 0; i < x.size(); i++) {
            y.add(i, x.get(i).getText());
        }
        return y;
    }

    public List<String> listDoubleToString(List<Double> x) {
        ArrayList<String> y = new ArrayList<>();
        for (int i = 0; i < x.size(); i++) {
            y.add(i, x.get(i).toString());
        }
        return y;
    }

    public List<Double> listStringToDoubleWithoutFirstChar(List<String> x) {
        ArrayList<Double> y = new ArrayList<>();
        for (int i = 0; i < x.size(); i++) {
            y.add(i, StringToDoubleWithoutFirstChar(x.get(i)));
        }
        return y;
    }

    public Double StringToDoubleWithoutFirstChar(String x) {
        return Double.parseDouble(x.substring(1));
    }





    public void preconditionLogIn() {
        logInPage.inputUsername("standard_user");
        logInPage.inputPassword("secret_sauce");
        logInPage.clickLoginButton();
    }

    public void preconditionAddTwoItemsAndNavigateToTheCart() {
        inventoryPage.clickOnAddToCart(5);
        inventoryPage.clickOnAddToCart(4);
        inventoryPage.clickOnShoppingCart();
    }


    @AfterClass
    public void closeChrome () {
        driver.quit();
    }

}
