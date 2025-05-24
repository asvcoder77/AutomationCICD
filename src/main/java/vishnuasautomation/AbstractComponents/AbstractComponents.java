package vishnuasautomation.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import vishnuasautomation.pageobjects.CartPage;
import vishnuasautomation.pageobjects.OrdersPage;

public class AbstractComponents {

	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy (css="[routerlink*='cart']")
	WebElement cart;
	
	@FindBy (css="[routerlink*='myorders']")
	WebElement orders;

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForElementToAppearByWebElement(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));

	}
	public CartPage goToCartPage() {
		cart.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	public CartPage goToCartPageUsingJS() {

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", cart);
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	public OrdersPage goToOrderPage() {
		orders.click();
		OrdersPage orderspage = new OrdersPage(driver);
		return orderspage;
}	
	public OrdersPage goToOrderPageUsingJS() {

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", orders);
		OrdersPage orderspage = new OrdersPage(driver);
		return orderspage;
	}
}