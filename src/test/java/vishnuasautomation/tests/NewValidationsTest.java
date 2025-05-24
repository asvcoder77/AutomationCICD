package vishnuasautomation.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import vishnuasautomation.TestComponents.BaseTest;
import vishnuasautomation.pageobjects.CartPage;
import vishnuasautomation.pageobjects.ProductCatalogue;

public class NewValidationsTest extends BaseTest {
	
	@Test
	public void errorValidationOne() throws IOException, InterruptedException {

		landingpage.loginApplication("asvishnukaruvannur7@gmail.com", "Vi@123456");
		String toasterMessage=landingpage.getToasterMessage();
		Assert.assertEquals(toasterMessage, "Incorrect email or password.");
	}
	@Test
	public void validationTwo() throws IOException, InterruptedException {

		ProductCatalogue productcatalogue=landingpage.loginApplication("asvishnukaruvannur7@gmail.com", "Vi@12345");
		
		List<WebElement> products=productcatalogue.getProductList();
		productcatalogue.addProductToCart("ZARA COAT 3");
		CartPage cartpage=productcatalogue.goToCartPageUsingJS(); //use "goToCartPage()" method if it is not working
		
		Boolean match = cartpage.checkProductPresent("ZARA COAT 3");
		Assert.assertTrue(match);
	
}}
