package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.abstractcomponents.AbstractComponents;

public class CartProductPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CartProductPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts; 
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	 public Boolean getCartProductListMatch(String productName)
	 {
     Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
     return match;
	 }

	 public SubmitPayment clickcheckout()
	 {
		 checkoutButton.click();
		 SubmitPayment submitpayment = new SubmitPayment(driver);
		 return submitpayment;
	 }
}
