package TestComponents;

import static java.time.Duration.ofSeconds;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import PageObjects.UserLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	WebDriver driver;
	FileInputStream fileInputStream;
	Properties properties;
	String username, password;
	
	public Properties handleProperties() throws IOException {
		fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/test/Resources/Global.properties");
        properties = new Properties();
        properties.load(fileInputStream);
        return properties;
	}
	public WebDriver initializeDriver() throws IOException {
		
        String browserName = handleProperties().getProperty("browser");
		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver();
		    driver.manage().timeouts().implicitlyWait(ofSeconds(10));
		    driver.manage().window().maximize();
			break;
		case "edge"	:
			WebDriverManager.edgedriver().setup();
		    driver = new EdgeDriver();
		    driver.manage().timeouts().implicitlyWait(ofSeconds(10));
		    driver.manage().window().maximize();
		    break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		    driver.manage().timeouts().implicitlyWait(ofSeconds(10));
		    driver.manage().window().maximize();
		    break;
			
		default:
			break;
		}
		return driver;
	}
	
	public UserLoginPage launchApplcation() throws IOException {
		driver = initializeDriver();
		UserLoginPage loginPage = new UserLoginPage(driver);
		loginPage.goTo();
		return loginPage;
	}
	
	

}
