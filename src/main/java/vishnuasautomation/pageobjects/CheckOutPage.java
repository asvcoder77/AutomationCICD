package vishnuasautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vishnuasautomation.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents{
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement countryField;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submitbutton;
	
	By fieldBy =By.cssSelector(".ta-item");
	
	
	public void selectCountry(String countryName) {
		
		Actions a=new Actions(driver);
		a.sendKeys(countryField, countryName ).build().perform();
		waitForElementToAppear(fieldBy);
		country.click();
	}
	public ConfirmationPage clickSubmit() {
		submitbutton.click();
		ConfirmationPage confirmationpage= new ConfirmationPage(driver);
		return confirmationpage;
	}
	
	
	

}
