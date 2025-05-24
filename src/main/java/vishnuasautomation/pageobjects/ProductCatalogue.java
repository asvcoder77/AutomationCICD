package vishnuasautomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vishnuasautomation.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".card-body")
	List<WebElement> products;
	
//	driver.findElement(By.cssSelector(".ng-animating"))
	@FindBy(css=".ng-animating")
	WebElement animation;

	By productsBy = By.cssSelector(".card-body");
	By addToCartBy = By.cssSelector(".card-body button:last-of-type");
	By toaster = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String name) {

		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(name)).findFirst()
				.orElse(null);
		return prod;
	}
	public void addProductToCart(String name) {
		WebElement prod = getProductByName(name);
		prod.findElement(addToCartBy).click();
		waitForElementToAppear(toaster);
		waitForElementToDisappear(animation);
		
	}

}
