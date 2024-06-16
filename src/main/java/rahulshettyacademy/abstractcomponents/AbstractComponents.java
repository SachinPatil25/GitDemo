package rahulshettyacademy.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartProductPage;
import rahulshettyacademy.pageobjects.OrderHistory;

public class AbstractComponents {

	WebDriver driver;
	public AbstractComponents(WebDriver driver) {

	this.driver = driver;
	PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement clickcart;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderhistory;
	
	
	
	public CartProductPage clickOnCart()
	{
		clickcart.click();
		 CartProductPage cartproductpage = new CartProductPage(driver);
		 return cartproductpage;
	}
	
	public OrderHistory clickOrder()
	{
		orderhistory.click();
		OrderHistory OrderPage = new OrderHistory(driver);
		 return OrderPage;
	}
	
	

	public void waitForElementToAppear(By FindBy)
	{
		 WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(5));
	     wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	
	public void waitForWebElementToAppear(WebElement FindBy)
	{
		 WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(5));
	     wait.until(ExpectedConditions.visibilityOf(FindBy));
	}
	
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(3000);
		//WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
}
