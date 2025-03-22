package TestComponents;

import static java.time.Duration.ofSeconds;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import PageObjects.UserLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public FileInputStream fileInputStream;
	public Properties properties;
	String username, password;
	public UserLoginPage loginPage;
	
	public String getDateandTime() {
		SimpleDateFormat requiredFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm");
		Date currentDate = new Date();
		String dateFormat = requiredFormat.format(currentDate);
		return dateFormat;
	}
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
	
	@BeforeMethod(alwaysRun = true)
	//this executes before every test method
	public UserLoginPage launchApplcation() throws IOException {
		driver = initializeDriver();
		loginPage = new UserLoginPage(driver);
		loginPage.goTo();
		return loginPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	
	public List<HashMap<String, String>> getJsonTestData() throws IOException {
		
		String JsonData = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\Resources\\SubmitOrderTestData.json"), StandardCharsets.UTF_8);
		ObjectMapper objMapper = new ObjectMapper();
		List<HashMap<String, String>> dataMap = objMapper.readValue(JsonData,
				new TypeReference<List<HashMap<String, String>>>(){
		});
		return dataMap;
		}
	
	@DataProvider
    public Object[][] testDataProvider(){
    	return new Object [][] {{"Testdummy@gmail.com","Dummy@123","ZARA COAT 3"},
    		{"Testdummy@gmail.com","Dummy@123","ADIDAS ORIGINAL"},
    		{"Testdummy@gmail.com","Dummy@123","Zebronic Headset"}};
    	
    }
    
	
	 @DataProvider(name = "DataWithHashMap")
	    public Object[][] testDataProviderWithHasMap(){
	    	HashMap<String,String> map1 = new HashMap<>();
	    	map1.put("username", "Testdummy@gmail.com");
	    	map1.put("password", "Dummy@123");
	    	map1.put("product", "ZARA COAT 3");
	    	HashMap<String,String> map2 = new HashMap<>();
	    	map2.put("username", "Testdummy@gmail.com");
	    	map2.put("password", "Dummy@123");
	    	map2.put("product", "ADIDAS ORIGINAL");
	    	return new Object[][]{{map1},{map2}};
	    	
	    }
	 
	 @DataProvider
	 public Object[][] getJsonData() throws IOException{
		 List<HashMap<String, String>> data = getJsonTestData();
		 return new Object[][] {{data.get(0)},{data.get(1)}};
	 }
	 
	 public String takeScreenShot(String testCaseName, WebDriver driver,String currentDateandTime) throws IOException {
		 File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 String screenshotDestination = System.getProperty("user.dir")+"//Reports//Report_"+currentDateandTime+"//" + testCaseName + ".png";			
		 File Destination = new File(screenshotDestination);
		 FileUtils.copyFile(source, Destination);
		 return screenshotDestination;
	 }
	
	

}
