package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.abstractcomponents.AbstractComponents;

public class SubmitPayment extends AbstractComponents {
	
	WebDriver driver;
	
	public SubmitPayment(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement placeOrder;
	
	By waitUntil = By.cssSelector(".ta-results");
	
	public void doPayment(String countryName)
	{
	      Actions a = new Actions(driver);
	      a.sendKeys(country, countryName).build().perform();
	      waitForElementToAppear(waitUntil);
	      selectCountry.click();
	}
	
	public ThankYouPage placeOrderClick()
	{
		placeOrder.click();
		ThankYouPage thankyoupage = new ThankYouPage(driver);
		return thankyoupage;
	}
	
}
