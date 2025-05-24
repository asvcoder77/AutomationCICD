package vishnuasautomation.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import vishnuasautomation.TestComponents.BaseTest;
import vishnuasautomation.pageobjects.CartPage;
import vishnuasautomation.pageobjects.CheckOutPage;
import vishnuasautomation.pageobjects.ConfirmationPage;
import vishnuasautomation.pageobjects.OrdersPage;
import vishnuasautomation.pageobjects.ProductCatalogue;

public class StandAloneTest extends BaseTest {
	
	@Test
	public void standAlone() throws IOException, InterruptedException {

		ProductCatalogue productcatalogue=landingpage.loginApplication("asvishnukaruvannur7@gmail.com", "Vi@12345");
		
		List<WebElement> products=productcatalogue.getProductList();
		productcatalogue.addProductToCart("ZARA COAT 3");
		CartPage cartpage=productcatalogue.goToCartPageUsingJS(); //use "goToCartPage()" method if it is not working
		
		Boolean match = cartpage.checkProductPresent("ZARA COAT 3");
		Assert.assertTrue(match);
		CheckOutPage checkoutpage =cartpage.clickCheckOut();
		
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage=checkoutpage.clickSubmit();
		
		String confmessage=confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confmessage.equalsIgnoreCase("Thankyou for the order."));		
	}
	@Test(dependsOnMethods= {"standAlone"})
	public void orderHistoryTest() {
		ProductCatalogue productcatalogue=landingpage.loginApplication("asvishnukaruvannur7@gmail.com", "Vi@12345");
		
		OrdersPage orderspage=productcatalogue.goToOrderPageUsingJS();
		
		Boolean match=orderspage.checkProductPresentInOrders("ZARA COAT 3");
		Assert.assertTrue(match);	
	}
}
