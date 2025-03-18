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
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import static java.time.Duration.ofSeconds;

public class ErrorHandlingTest extends BaseTest {
    @Test(groups = {"ErrorHandling"})
    public void loginPageError() throws IOException
    {
        ProductCatalogue productCatalogue = loginPage.loginApplication("randomemail@gmail.com","@Wrongpassword55");
        Assert.assertEquals("Incorrect email or password.",loginPage.getErrorMessageText());
        		
       
    }
    
    @Test(groups = {"ErrorHandling"})
    public void productCatalogueError() throws IOException {
    	//Login into the application
        ProductCatalogue productCatalogue = loginPage.loginApplication(handleProperties().getProperty("username"),handleProperties().getProperty("password"));
        //Add products to cart
        productCatalogue.addProductToCart("ZARA COAT 3");
        //click on the cart button and open the cart page
        CartPage cartPage = productCatalogue.clickTheCart();
        //verify products on cart page.
        Boolean productMatch = cartPage.verifyTheProductsInCart("ZARA COAT");
        Assert.assertFalse(productMatch);
    }
}
