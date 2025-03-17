package PageObjects;

import Utils.Utils;
import org.openqa.selenium.AcceptedW3CCapabilityKeys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class OrderConfirmationPage extends Utils {
    WebDriver driver;
    public OrderConfirmationPage(WebDriver driver){
        super(driver);
        this.driver =  driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".hero-primary")
    WebElement ActualMessage;

    public void verifyTheConfirmationMessage(String ExpectedMessage){
        Assert.assertTrue(ActualMessage.getText().equalsIgnoreCase(ExpectedMessage));
    }

}
