package PageObjects;

import Utils.Utils;
import jdk.jfr.MetadataDefinition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends Utils {
    WebDriver driver;
    public ProductCatalogue(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = ".mb-3")
    List<WebElement> products;

   
    By productsLocator = By.cssSelector(".mb-3");
    By productNameLocator= By.tagName("b");

    By addToCartButton = By.cssSelector(".mb-3 .card-body .w-10");

    By toastMessage = By.xpath("//div[contains(text(),\"Product Added\")]");

    public List<WebElement> getProductsList(){
        waitUntilElementIsVisible(productsLocator);
        return products;
    }

    public WebElement selectDesiredProduct(String productName) {
        WebElement desiredProduct = getProductsList().stream().filter(product -> product.findElement(productNameLocator).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
        return  desiredProduct;
    }
    public void addProductToCart(String productName){
        WebElement productToBeSelected =  selectDesiredProduct(productName);
        productToBeSelected.findElement(addToCartButton).click();
        waitUntilElementIsInvisible(toastMessage);
    }

   



}
