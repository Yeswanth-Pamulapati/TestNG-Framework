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

public class OrdersPage extends Utils {

    WebDriver driver;
    public OrdersPage(WebDriver driver){
        super(driver);
        this.driver =  driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(css = "td:nth-child(3)")
    List<WebElement> orderedProducts;

    public List<WebElement> getOrderedProductsList() {
        return orderedProducts;
    }

    public Boolean verifyTheProductsOrderHistory(String productName){
       Boolean isProductMatching = getOrderedProductsList().stream().anyMatch(product->product.getText().equals(productName));
       return isProductMatching;
    }

    
}

