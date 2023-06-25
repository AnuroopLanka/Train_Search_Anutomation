package minipro;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverSetup {

public static WebDriver driver=null;
	
	public static WebDriver getDriver(String driverName) {
		
		  //Input for the browser
		 System.out.println("Enter your choice\n1.Chrome-1\n2.Firefox-2");
		  String browser = driverName; 
		  Scanner sc=new Scanner(System.in);
		  browser=sc.next();
		  sc.close();
		
		
		//To setup the  Chrome WebDriver
		if(browser.equalsIgnoreCase("chrome")) {
			//Set chrome driver path 
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			//creating a map object where key and value fields accept String and object type of data and cast into HashMap
			Map<String, Object> prefs = new HashMap<String, Object>();
			 prefs.put("profile.default_content_setting_values.notifications", 2);		 
			 //Create an object for chrome options
			 ChromeOptions op=new ChromeOptions();
			 op.setExperimentalOption("prefs", prefs);
			 //Disable unexpected notifications
			 op.addArguments("disable-infobars");
			 op.addArguments("--disable-notifications");
			 driver = new ChromeDriver(op);
			 driver.manage().deleteAllCookies();	
			 }
		
		
		//To setup the Firefox WebDriver
		else if(browser.equalsIgnoreCase("firefox")) {
			//Set firefox driver path
			System.setProperty("webdriver.gecko.driver","C:\\selenium_drivers\\web browser drivers\\geckodriver.exe");
			
			FirefoxOptions fo = new FirefoxOptions();			
			//Disable unexpected notifications
			fo.addPreference("dom.webnotifications.enabled", false);			
			driver=new FirefoxDriver(fo);	
		}
		
		//When the input is found as Incorrect browser
		else {
			System.out.println("Incorrect browser. Enter the browser name again");
			System.exit(0);
		}
		//Maximizing the browser window
		driver.manage().window().maximize();		        
        //Implicit Wait 
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
	 	driver.manage().timeouts().pageLoadTimeout(40,TimeUnit.SECONDS);
	 	
	 	//Assign the  address to baseUrl variable
		String baseUrl="https://www.irctc.co.in" ;       
		
		//Navigating to IRCTC website
		driver.get(baseUrl);                             
	   	return driver;
	}
	
}
