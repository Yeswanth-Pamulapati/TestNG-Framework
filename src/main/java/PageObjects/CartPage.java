package PageObjects;

import Utils.Utils;
import jdk.jfr.MetadataDefinition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CartPage extends Utils {

    WebDriver driver;
    public CartPage(WebDriver driver){
        super(driver);
        this.driver =  driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".cart .cartSection")
    List<WebElement> productsInCart;

    @FindBy(css = ".totalRow .btn-primary")
    WebElement checkoutButtonLocator;

    By productNameLocator= By.tagName("h3");
    public List<WebElement> getProductsInCart() {
        return productsInCart;
    }

    public Boolean verifyTheProductsInCart(String productName){
       Boolean isProductMatching = getProductsInCart().stream().anyMatch(product->product.findElement(productNameLocator).getText().equals(productName));
       return isProductMatching;
    }

    public PaymentPage clickTheCheckoutButton(){
        checkoutButtonLocator.click();
        PaymentPage paymentPage = new PaymentPage(driver);
        return paymentPage;
    }
}

