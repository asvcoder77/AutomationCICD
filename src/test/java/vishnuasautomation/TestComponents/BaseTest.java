package vishnuasautomation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import vishnuasautomation.pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;

	public WebDriver initializeDriver() throws IOException {

		// properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\vishnuasautomation\\resources\\GlobalData.properties");
		prop.load(fis);
//		String browserName = prop.getProperty("browser");
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();// invoking chromedriver
			driver = new ChromeDriver();
			

		}
		else if(browserName.equalsIgnoreCase("firefox")) {
//			WebDriverManager.firefoxdriver().setup(); // invoking firefoxdriver
			System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");

			driver = new FirefoxDriver();	
		}
		else if(browserName.equalsIgnoreCase("edge")){
			WebDriverManager.edgedriver().setup(); // invoking edgedriver
			driver = new EdgeDriver();

			
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // implicit wait
		driver.manage().window().maximize();
		return driver;
	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver=initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
		
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		String jsonContent = FileUtils.readFileToString(
				new File(filepath),
				StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>>data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file =new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
}