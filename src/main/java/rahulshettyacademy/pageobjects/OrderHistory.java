package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.abstractcomponents.AbstractComponents;

public class OrderHistory extends AbstractComponents {
	
	WebDriver driver;
	
	public OrderHistory(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderedProducts; 
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	
	 public Boolean veridyOderDisplay(String productName)
	 {
     Boolean match = orderedProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
     return match;
	 }

	
}
