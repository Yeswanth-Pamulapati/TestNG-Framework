package PageObjects;

import Utils.Utils;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jdk.jfr.MetadataDefinition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserLoginPage extends Utils {
    WebDriver driver;
    public UserLoginPage(WebDriver driver){
        super(driver);
        this.driver =  driver;
        PageFactory.initElements(driver,this);
    }
    //using page factory we can reduce the syntax in writing the webelement
    //page object should not hold any data.
    @FindBy(css = "input[type=\"email\"]")
        WebElement username;
    @FindBy(css = "input[type=\"password\"]")
        WebElement password;
    @FindBy(css = "input[name=\"login\"]")
        WebElement loginButton;


    public ProductCatalogue loginApplication(String userEmail, String userPassword){
        username.sendKeys(userEmail);
        password.sendKeys(userPassword);
        loginButton.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;

    }

    public void goTo(){

        driver.get("https://rahulshettyacademy.com/client");
    }
}
