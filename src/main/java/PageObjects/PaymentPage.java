package PageObjects;

import Utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends Utils {
    WebDriver driver;
    Actions actions;
    public PaymentPage(WebDriver driver){
        super(driver);
        this.driver =  driver;
        actions = new Actions(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "input[placeholder=\"Select Country\"]")
    WebElement CountryFieldLocator;
    @FindBy(xpath = "//span[text()=\" United States\"]")
    WebElement countryToSelect;
    @FindBy(css = ".action__submit " )
    WebElement placeOrderButtonLocator;
    By countryOptions= By.cssSelector(".ta-results");

    public void enterACountryName(String countryName){
        actions.sendKeys(CountryFieldLocator,countryName).perform();

    }

    public void selectADesiredCountryFromDropdown(){
        waitUntilElementIsVisible(countryOptions);
        actions.moveToElement(countryToSelect).click().build().perform();
    }

    public OrderConfirmationPage placeTheOrder(){
        placeOrderButtonLocator.click();
        OrderConfirmationPage confirmationPage = new OrderConfirmationPage(driver);
        return confirmationPage;
    }
}

