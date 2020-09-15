package seleniumProjectSession10;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class Act3 {
    WebDriver driver;
    //WebElement element= null;
    WebDriverWait wait ;
    
    
    @BeforeMethod
    public void beforeMethod() {
        
    	 //Create a new instance of the Chrome driver
    	ChromeOptions options = new ChromeOptions();  
		System.setProperty("webdriver.chrome.driver", "C:\\SDET_workspace\\Selenium_Deb\\chromedriver.exe");
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.addArguments("--disable-extensions");
		options.addArguments("test-type");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver,30);
        //Open browser
        driver.get("http://alchemy.hguy.co/orangehrm");
        driver.manage().window().maximize();
    }

    @Test
    public void Login() throws Exception {
       
		// System.out.println("OrangeHRM page did not launched"); 
    	//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
    	 Thread.sleep(3000);
		  if(driver.findElement(By.xpath("//*[@id='divLogo']/img")).isDisplayed()) {
		  Reporter.log("OrangeHRM page launched");
		  System.out.println("OrangeHRM page launched");
		  
		  driver.findElement(By.id("txtUsername")).sendKeys("orange");
		  Thread.sleep(1000);
		  driver.findElement(By.id("txtPassword")).sendKeys("orangepassword123");
		  Thread.sleep(1000);
		  
		  driver.findElement(By.id("btnLogin")).click();
		 
		  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("welcome" ))));
		  Reporter.log("Login Successfull");
		  System.out.println("Login Successfull");
		  Thread.sleep(2000);
		  
		  }else
		  { 
			  Reporter.log("OrangeHRM page did not launched");
			  System.out.println("OrangeHRM page did not launched"); 
		  }
		 
    }

    @AfterMethod
    public void afterMethod() {
        //Close the browser
        driver.quit();
    }

}
