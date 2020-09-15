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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class Act8 {
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
    public void applyLeave() throws Exception {
    	
    	Thread.sleep(1000);
		  if(driver.findElement(By.id("menu_leave_viewLeaveModule")).isDisplayed()) {
		  Reporter.log("Leave option displayed");
		  System.out.println("Leave option displayed");
		  
		  Actions builder = new Actions(driver);
		  builder.moveToElement(driver.findElement(By.id("menu_leave_viewLeaveModule"))).perform();
		  By locator = By.id("menu_leave_applyLeave");
		 // driver.click(locator);
		  driver.findElement(By.id("menu_leave_applyLeave")).click();
		  
			  if(driver.findElement(By.id("applyleave_txtLeaveType")).isDisplayed()) {
	  			  Reporter.log("Apply Leave page opened");
	  			  System.out.println("Apply Leave page opened");
	  			  
	  			Select drpOption = new Select(driver.findElement(By.id("applyleave_txtLeaveType")));
	  			drpOption.selectByVisibleText("Paid Leave");
	  			Thread.sleep(1000);
	  			driver.findElement(By.id("applyleave_txtFromDate")).clear();
		  		driver.findElement(By.id("applyleave_txtFromDate")).sendKeys("2020-08-19");
		  		driver.findElement(By.id("applyleave_txtToDate")).clear();
		  		driver.findElement(By.id("applyleave_txtToDate")).sendKeys("2020-08-20");
		  		
		  		driver.findElement(By.xpath("//*[contains(text(),'Leave Type ')]")).click();
		  		driver.findElement(By.id("applyleave_txtComment")).sendKeys("new comments"+ n);
		  		
		  		Thread.sleep(2000);
		  		driver.findElement(By.id("applyBtn")).click();
		  		Reporter.log("Leave applied");
				System.out.println("Leave applied");
				
				//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[contains(text(),'Successfully Submitted')]"))));
		  		Thread.sleep(2000);
		  		Reporter.log("Leave applied successfully");
				System.out.println("Leave applied successfully");
	  			
	  		}else {
	  			  Reporter.log("Apply Leave page not opened");
	  			  System.out.println("Apply Leave page not opened");
	  		}
		  
			  driver.findElement(By.id("menu_leave_viewMyLeaveList")).click();
			  driver.findElement(By.id("calFromDate")).clear();
		  		driver.findElement(By.id("calFromDate")).sendKeys("2020-08-19");
		  		driver.findElement(By.id("calToDate")).clear();
		  		driver.findElement(By.id("calToDate")).sendKeys("2020-08-20");
		  		driver.findElement(By.id("btnSearch")).click();
		  		Thread.sleep(2000);
		  
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
