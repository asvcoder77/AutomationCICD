package vishnuasautomation.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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

public class ParameterizationusinghashmapTest extends BaseTest {
	
	@Test(dataProvider="getData")
	public void standAloneNewOne(HashMap<String,String>input) throws IOException, InterruptedException {

		ProductCatalogue productcatalogue=landingpage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products=productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("productname"));
		CartPage cartpage=productcatalogue.goToCartPageUsingJS(); //use "goToCartPage()" method if it is not working
		
		Boolean match = cartpage.checkProductPresent(input.get("productname"));
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
		HashMap<String,String>data1=new HashMap();
		data1.put("email", "asvishnukaruvannur7@gmail.com");
		data1.put("password", "Vi@12345");
		data1.put("productname", "ADIDAS ORIGINAL");
		
		HashMap<String,String>data2=new HashMap<>();
		data2.put("email", "vishnuas131@gmail.com");
		data2.put("password", "Qa@12345");
		data2.put("productname", "ZARA COAT 3");
		return new Object[][] {{data1},{data2}};
	}
	

}
