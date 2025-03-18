package TestCases;
import PageObjects.UserLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import static java.time.Duration.*;

public class StandAloneTest {
    public static void main(String[] args) throws IOException {
        //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\Resources\\chromedriver.exe");
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/test/Resources/Global.properties");
        properties.load(fileInputStream);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        UserLoginPage userLoginPage = new UserLoginPage(driver);
        driver.findElement(By.cssSelector("input[type=\"email\"]")).sendKeys(properties.getProperty("username"));
        driver.findElement(By.cssSelector("input[type=\"password\"]")).sendKeys(properties.getProperty("password"));
        driver.findElement(By.cssSelector("input[name=\"login\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement desiredProduct = products.stream().filter(
                product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase("ZARA COAT 3")).findFirst().orElse(null);
        if (desiredProduct!=null)
            desiredProduct.findElement(By.cssSelector(".mb-3 .card-body .w-10")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),\"Product Added\")]")));
        driver.findElement(By.cssSelector(".fa-shopping-cart")).click();
        List<WebElement> cartProducts =  driver.findElements(By.cssSelector(".cart .cartSection"));
        Boolean isProductMatching= cartProducts.stream().anyMatch(product->product.findElement(By.tagName("h3")).getText().equals("ZARA COAT 3"));
        Assert.assertTrue(isProductMatching);
        driver.findElement(By.cssSelector(".totalRow .btn-primary")).click();
//        driver.findElement(By.cssSelector("input[placeholder=\"Select Country\"]")).sendKeys("united");
//        List<WebElement> countyList = driver.findElements(By.cssSelector(".ta-results span"));
//        WebElement countryToSelect = countyList.stream().filter(country->country.getText().equalsIgnoreCase("United Arab Emirates")).findFirst().orElse(null);
//        countryToSelect.click();
        Actions actions = new Actions(driver);
        actions.sendKeys(driver.findElement(By.cssSelector("input[placeholder=\"Select Country\"]")),"united").perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        actions.moveToElement(driver.findElement(By.xpath("//span[text()=\" United States\"]"))).click().build().perform();
        driver.findElement(By.cssSelector(".action__submit ")).click();
        String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));


    }
}
