package rahulshettyacademy.test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartProductPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderHistory;
import rahulshettyacademy.pageobjects.ProductCatalouge;
import rahulshettyacademy.pageobjects.SubmitPayment;
import rahulshettyacademy.pageobjects.ThankYouPage;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";	
	@Test(dataProvider="getData",groups={"purchase"})
	public void SubmitOrderTest(HashMap<String,String> input) throws IOException, InterruptedException
	{
	   
      ProductCatalouge productcatalouge = landingpage.loginApplication(input.get("email"),input.get("password"));
      List<WebElement> products = productcatalouge.getProductList();
      productcatalouge.addProductToCart(input.get("productName"));
      CartProductPage cartproductpage = productcatalouge.clickOnCart(); 
      Boolean match = cartproductpage.getCartProductListMatch(input.get("productName"));
      Assert.assertTrue(match);
      SubmitPayment submitpayment = cartproductpage.clickcheckout();  
      submitpayment.doPayment("India");
      ThankYouPage thankyoupage = submitpayment.placeOrderClick();     
      thankyoupage.thanYouMessage();
      String confirmMessage = thankyoupage.thanYouMessage();
      Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));          
	}
	
	@Test (dependsOnMethods= {"SubmitOrderTest"})
	public void goToOrderTest()
	{
	 ProductCatalouge productcatalouge = landingpage.loginApplication("patillms@gmail.com", "P@til555");
	 OrderHistory OrderPage  =  productcatalouge.clickOrder();		
	 Assert.assertTrue(OrderPage.veridyOderDisplay(productName));
	}
	

	
	@DataProvider
	
	public Object[][] getData() throws IOException
	
	{

		List<HashMap<String,String>> data = getJasonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object[] [] {{data.get(0)},{data.get(1)}};
	}
	
	/*public Object[][] getData()
	
	{
		return new Object[] [] {{"patillms@gmail.com","P@til555","ZARA COAT 3"},{"nikhil55@gmail.com","P@til555","ADIDAS ORIGINAL"}};
	}*/
	
//	HashMap<String,String> map = new HashMap<String,String> ();
//	map.put("email", "patillms@gmail.com");
//	map.put("password", "P@til555");
//	map.put("productName", "ZARA COAT 3");
//	
//	HashMap<String,String> map1 = new HashMap<String,String> ();
//	map1.put("email","nikhil55@gmail.com");
//	map1.put("password", "P@til555");
//	map1.put("productName", "ADIDAS ORIGINAL");
	

}
