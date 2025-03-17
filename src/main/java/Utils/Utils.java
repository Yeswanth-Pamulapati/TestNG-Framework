package Utils;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utils {
    WebDriverWait wait;
    WebDriver driver;
    public Utils(WebDriver driver){
            this.driver = driver;
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    }
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


}
