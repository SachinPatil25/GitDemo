package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingpage;
	public Properties prop;
	
	public WebDriver intializeDriver() throws IOException
	{
		prop =new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/rahulshettyacademy/resources/GlobalData.properties");
		prop.load(fis);
		//String browsername = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browsername =  prop.getProperty("browser");
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
		  if(browserName.contains("chrome"))
		  {
		  WebDriverManager.chromedriver().setup();
		  ChromeOptions options = new ChromeOptions();
	      options.setAcceptInsecureCerts(true);
	      if(browserName.contains("headless"))
	      {
	      options.addArguments("headless");
	      }
	      driver = new ChromeDriver(options);
	      driver.manage().window().setSize(new Dimension(1440,900));
	      
		  }
		  else if(browserName.equalsIgnoreCase("firefox"))
		  {
			 FirefoxOptions options = new FirefoxOptions();
		     options.setAcceptInsecureCerts(true);
			  driver = new FirefoxDriver(options);
			  //Firefox
		  }
		  
		  else if(browserName.equalsIgnoreCase("edge"))
		  {
			  EdgeOptions options = new EdgeOptions();
		      options.setAcceptInsecureCerts(true);
			  driver = new EdgeDriver(options);
			  // Eddge
		  }
		  
		  driver.manage().window().maximize();
	      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	      return driver;
	}
	
	public List<HashMap<String,String>> getJasonDataToMap(String filePath) throws IOException 
	{
		//read json to String
	    String jasonContent = FileUtils.readFileToString(new File(filePath)
	    		,StandardCharsets.UTF_8);
	
	// String to Hashmap jakson databind
	    
	    ObjectMapper mapper = new ObjectMapper();
	    List<HashMap<String, String>> data = mapper.readValue(jasonContent, new TypeReference<List<HashMap<String,String>>>()
	    		{ });
	    return data;
	 
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver ) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png";
	}
	
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		 driver = intializeDriver();
		 landingpage = new LandingPage(driver);
	     landingpage.goTo();
	     return landingpage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void teaDown()
	{
		 driver.close();
	}

}
