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

public class ParameterizationusingjsonTest extends BaseTest {
	
	@Test(dataProvider="getData",groups= {"errorhandling"})
	public void standAloneNewTwo(HashMap<String,String>input) throws IOException, InterruptedException {

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
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>>data=getJsonDataToMap(System.getProperty("user.dir") + "/src/test/java/vishnuasautomation/data/PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	

}
