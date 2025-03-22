package TestCases;
import PageObjects.*;
import TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import static java.time.Duration.ofSeconds;
import static org.testng.Assert.fail;

public class StandardCode extends BaseTest {
	public String productName = "ADIDAS ORIGINAL";
    @Test(dataProvider = "getJsonData")
    public void submitOrder(HashMap<String, String> dataMap) throws IOException
    {
        //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\Resources\\msedgedriver.exe");
    	;
        //Go to Website
        //Login into the application
       // ProductCatalogue productCatalogue = loginPage.loginApplication(handleProperties().getProperty("username"),handleProperties().getProperty("password"));
    	ProductCatalogue productCatalogue = loginPage.loginApplication(dataMap.get("username"),dataMap.get("password"));
    	//Add products to cart
        productCatalogue.addProductToCart(dataMap.get("product"));
        //click on the cart button and open the cart page
        CartPage cartPage = productCatalogue.clickTheCart();
        //verify products on cart page.
        Boolean productMatch = cartPage.verifyTheProductsInCart(dataMap.get("product"));
        Assert.assertTrue(productMatch);
        PaymentPage paymentPage = cartPage.clickTheCheckoutButton();
//        driver.findElement(By.cssSelector("input[placeholder=\"Select Country\"]")).sendKeys("united");
//        List<WebElement> countyList = driver.findElements(By.cssSelector(".ta-results span"));
//        WebElement countryToSelect = countyList.stream().filter(country->country.getText().equalsIgnoreCase("United Arab Emirates")).findFirst().orElse(null);
//        countryToSelect.click();
        //enter country name
        paymentPage.enterACountryName("united");
        //select required country
        paymentPage.selectADesiredCountryFromDropdown();
        //place the order
        OrderConfirmationPage confirmationPage = paymentPage.placeTheOrder();
        //verify the confirmation message displayed on the final page.
        Boolean messageMatch = confirmationPage.verifyTheConfirmationMessage("Thanyou for the order.");
        Assert.assertTrue(messageMatch);

    }
    
    @Test(dependsOnMethods = {"submitOrder"}, dataProvider = "getJsonData")
    public void verifyOrderHistory(HashMap<String, String> dataMap) throws IOException {
    	ProductCatalogue productCatalogue = loginPage.loginApplication(dataMap.get("username"),dataMap.get("password"));
        //Add products to cart
        //productCatalogue.addProductToCart(dataMap.get("product"));
        OrdersPage ordersPage = productCatalogue.clickonOrdersHeader();
        Boolean isOrderPresent = ordersPage.verifyTheProductsOrderHistory(dataMap.get("product"));
        Assert.assertTrue(isOrderPresent);
        
        
    }
    
    
   
    
}
