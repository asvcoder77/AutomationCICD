package vishnuasautomation.tests;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import vishnuasautomation.TestComponents.BaseTest;
import vishnuasautomation.pageobjects.CartPage;
import vishnuasautomation.pageobjects.CheckOutPage;
import vishnuasautomation.pageobjects.ConfirmationPage;
import vishnuasautomation.pageobjects.OrdersPage;
import vishnuasautomation.pageobjects.ProductCatalogue;

public class ParameterizationTest extends BaseTest {
	
	@Test(dataProvider="getData")
	public void standAloneNew(String email,String password,String productName) throws IOException, InterruptedException {

		ProductCatalogue productcatalogue=landingpage.loginApplication(email, password);
		
		List<WebElement> products=productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		CartPage cartpage=productcatalogue.goToCartPageUsingJS(); //use "goToCartPage()" method if it is not working
		
		Boolean match = cartpage.checkProductPresent(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutpage =cartpage.clickCheckOut();
		
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage=checkoutpage.clickSubmit();
		
		String confmessage=confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confmessage.equalsIgnoreCase("Thankyou for the order."));		
	}
	@Test(dependsOnMethods= {"standAloneNew"})
	public void orderHistoryTest() {
		ProductCatalogue productcatalogue=landingpage.loginApplication("asvishnukaruvannur7@gmail.com", "Vi@12345");
		
		OrdersPage orderspage=productcatalogue.goToOrderPageUsingJS();
		
		Boolean match=orderspage.checkProductPresentInOrders("ZARA COAT 3");
		Assert.assertTrue(match);	
	}
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"asvishnukaruvannur7@gmail.com","Vi@12345","ADIDAS ORIGINAL"},{"vishnuas131@gmail.com","Qa@12345","ZARA COAT 3"}};
	}
	public String getScreenshot(String testCaseName) throws IOException {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file =new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}

}
