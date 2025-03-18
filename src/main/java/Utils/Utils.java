package Utils;

import org.testng.Assert;

import PageObjects.CartPage;
import PageObjects.OrdersPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utils {
    WebDriverWait wait;
    WebDriver driver;
    public Utils(WebDriver driver){
            this.driver = driver;
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            PageFactory.initElements(driver, this);


    }
    @FindBy(css = ".fa-shopping-cart" )
    WebElement cartButtonLocator;
    
    @FindBy(css = "button[routerlink=\"/dashboard/myorders\"]")
    WebElement OrdersLocator;
    public void waitUntilElementIsVisible(By elementLocator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));

    }

    public void waitUntilElementIsVisible(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementIsInvisible(WebElement element){
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitUntilElementIsInvisible(By elementLocator){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));

    }
    
    public CartPage clickTheCart(){
        cartButtonLocator.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
        
}  public OrdersPage clickonOrdersHeader() {
	    OrdersLocator.click();
	    OrdersPage ordersPage = new OrdersPage(driver);
	    return ordersPage;
}
     


}
