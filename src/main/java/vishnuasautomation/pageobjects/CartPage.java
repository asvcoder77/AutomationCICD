package vishnuasautomation.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import vishnuasautomation.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div.cartSection h3")
	private List<WebElement> cartproducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutButton;
	
	
	public Boolean checkProductPresent(String productName) {
		
		Boolean match=cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
				return match;
	}
	public CheckOutPage clickCheckOut() throws InterruptedException {
		checkOutButton.click();
		Thread.sleep(3000);
		CheckOutPage checkoutpage = new CheckOutPage(driver);
		return checkoutpage;
	}
}
