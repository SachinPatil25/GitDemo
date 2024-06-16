package rahulshettyacademy.test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartProductPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalouge;
import rahulshettyacademy.pageobjects.SubmitPayment;
import rahulshettyacademy.pageobjects.ThankYouPage;

public class ErrorValidationTest extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException
	{
	 
      landingpage.loginApplication("patillms@gmail.com", "Ptil555");
      Assert.assertEquals("Incorrect email or password.", landingpage.errorMessageValidation());
            
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException
	{
	  String productName = "ZARA COAT 3";	 
      ProductCatalouge productcatalouge = landingpage.loginApplication("patillms@gmail.com", "P@til555");
      List<WebElement> products = productcatalouge.getProductList();
      productcatalouge.addProductToCart(productName);
      CartProductPage cartproductpage = productcatalouge.clickOnCart(); 
      Boolean match = cartproductpage.getCartProductListMatch("ZARA COAT 33");
      Assert.assertFalse(match);
            
	}


}
