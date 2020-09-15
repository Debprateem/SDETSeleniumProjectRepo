package seleniumProjectSession10;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class Act4 {
    WebDriver driver;
    //WebElement element= null;
    WebDriverWait wait ;
    int n;
    
    @BeforeClass
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
        
        //Generate Random number
        Random rand = new Random();

     // Obtain a number between [0 - 49].
        n = rand.nextInt(50);

     // Add 1 to the result to get a number from the required range
     // (i.e., [1 - 50]).
     n += 1;
    }

    @Test (priority=1)
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
    
    @Test (priority=2)
    public void addEmployee() throws Exception {
    	
    	Thread.sleep(1000);
		  if(driver.findElement(By.id("menu_pim_viewPimModule")).isDisplayed()) {
		  Reporter.log("PIM option displayed");
		  System.out.println("PIM option displayed");
		  
		  driver.findElement(By.id("menu_pim_viewPimModule")).click();
		  Thread.sleep(3000);
		  driver.findElement(By.id("menu_pim_addEmployee")).click();
		  
		 
		  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("formInputText" ))));
		  Reporter.log("Add employee page displayed");
		  System.out.println("Add employee page displayed");
		  driver.findElement(By.className("formInputText" )).sendKeys("Deb");
		  driver.findElement(By.id("lastName" )).sendKeys("Test"+ n);
		  driver.findElement(By.id("btnSave" )).click();
		  Thread.sleep(2000);
		  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='profile-pic']/h1"))));
		  
		  //Nme verification
		  
		//*[@id='profile-pic']/h1
		  WebElement name = driver.findElement(By.xpath("//*[@id='profile-pic']/h1"));
	    	String Nm = name.getText();
	        System.out.println("Page title is: " + Nm);
		  if(Nm.equalsIgnoreCase("Deb Test"+n)) {
			  Reporter.log("Input name and saved name matched");
			  System.out.println("Input name and saved name matched");
		  }else 
		  {
			  Reporter.log("Input name and saved name not matched");
			  System.out.println("Input name and saved name not matched");
		  }
		  
		  }else
		  { 
			  Reporter.log("OrangeHRM page did not launched");
			  System.out.println("OrangeHRM page did not launched"); 
		  }
    	
    }

    @AfterClass
    public void afterMethod() {
        //Close the browser
        driver.quit();
    }

}
