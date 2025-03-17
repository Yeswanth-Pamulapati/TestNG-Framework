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
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import static java.time.Duration.ofSeconds;

public class StandardCode extends BaseTest {
    @Test 
    public void submitOrder() throws IOException
    {
        //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\Resources\\msedgedriver.exe");
    	UserLoginPage userLoginPage = launchApplcation();
        //Go to Website
        //Login into the application
        ProductCatalogue productCatalogue = userLoginPage.loginApplication(handleProperties().getProperty("username"),handleProperties().getProperty("password"));
        //Add products to cart
        productCatalogue.addProductToCart("ZARA COAT 3");
        //click on the cart button and open the cart page
        CartPage cartPage = productCatalogue.clickTheCart();
        //verify products on cart page.
        Boolean productMatch = cartPage.verifyTheProductsInCart("ZARA COAT 3");
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
        Boolean messageMatch = confirmationPage.verifyTheConfirmationMessage("Thankyou for the order.");
        Assert.assertTrue(messageMatch);



    }
}
