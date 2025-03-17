import PageObjects.*;
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

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import static java.time.Duration.ofSeconds;

public class StandardCode {
    public static void main(String[] args) throws IOException {
        //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\Resources\\msedgedriver.exe");
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(ofSeconds(10));
        UserLoginPage userLoginPage = new UserLoginPage(driver);
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/test/Resources/Global.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        //Go to Website
        userLoginPage.goTo();
        //Login into the application
        ProductCatalogue productCatalogue = userLoginPage.loginApplication(properties.getProperty("username"),properties.getProperty("password"));
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
