package vishnuasautomation.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import vishnuasautomation.pageobjects.LandingPage;

public class SubmitOrderTest {
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//hi
		WebDriverManager.chromedriver().setup();//invoking chromedriver
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//implicitwait
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingpage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("asvishnukaruvannur7@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Vi@12345");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));
		List<WebElement> products=driver.findElements(By.cssSelector(".card-body"));
		WebElement prod = products.stream()
			    .filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3"))
			    .findFirst()
			    .orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//applying JavascriptExecutor
		WebElement cart=driver.findElement(By.cssSelector("[routerlink*='cart']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", cart);
		
		List<WebElement>cartproducts=driver.findElements(By.cssSelector("div.cartSection h3"));
		Boolean match=cartproducts.stream()
					.anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase("ZARA COAT 3"));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Thread.sleep(3000);
		
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india" ).build().perform();
//		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
		
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String Confirmmessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(Confirmmessage);
		Assert.assertTrue(Confirmmessage.equalsIgnoreCase("Thankyou for the order."));
		
		
	}


}
