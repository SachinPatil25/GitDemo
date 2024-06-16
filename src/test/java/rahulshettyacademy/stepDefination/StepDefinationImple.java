package rahulshettyacademy.stepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartProductPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalouge;
import rahulshettyacademy.pageobjects.SubmitPayment;
import rahulshettyacademy.pageobjects.ThankYouPage;

public class StepDefinationImple extends BaseTest {
	
	public LandingPage landingpage;
	public ProductCatalouge productcatalouge;
	public CartProductPage cartproductpage;
	public ThankYouPage thankyoupage;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingpage = launchApplication();
		
	}
	
	@Given("Login with username {string} and password {string}")
	public void login_with_username_and_password(String username, String password)
	{
	 productcatalouge = landingpage.loginApplication(username,password);
	}
	
	@When("I add product {string} from cart")
	public void add_product_in_cart(String productName ) throws InterruptedException
	{
		 List<WebElement> products = productcatalouge.getProductList();
	      productcatalouge.addProductToCart(productName);
	}

	@When("checkout {string} and submit the order")
	public void checkout_productname_submit_order(String productName)
	{
		 cartproductpage = productcatalouge.clickOnCart(); 
	      Boolean match = cartproductpage.getCartProductListMatch(productName);
	      Assert.assertTrue(match);
	      SubmitPayment submitpayment = cartproductpage.clickcheckout();  
	      submitpayment.doPayment("India");
	      thankyoupage = submitpayment.placeOrderClick(); 
	      thankyoupage.thanYouMessage();
	}
	
	@Then("{string} message is displayed on confirmation page")
	public void message_is_displayed_on_confirmation_page(String string) {
		  
		  String confirmMessage = thankyoupage.thanYouMessage();
	      Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));  
	      driver.close();
	}
	
	 @Then("{string} message is displayed")
	 public void error_message_is_displaye(String errorMessage)
	 {
		 Assert.assertEquals(errorMessage, landingpage.errorMessageValidation());
		 driver.close();
	 }
	
}
