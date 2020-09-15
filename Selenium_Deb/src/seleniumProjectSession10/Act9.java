package seleniumProjectSession10;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

public class Act9 {
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
    public void addMyInfo() throws Exception {
    	
    	Thread.sleep(1000);
		  if(driver.findElement(By.id("menu_pim_viewMyDetails")).isDisplayed()) {
		  Reporter.log("MyInfo option displayed");
		  System.out.println("MyInfo option displayed");
		  
		  driver.findElement(By.id("menu_pim_viewMyDetails")).click();
		  Thread.sleep(3000);
		  
		  		if(driver.findElement(By.xpath("(//*[contains(text(),'Qualifications')])[2]")).isDisplayed()) {
		  			  Reporter.log("MyInfo option clicked");
		  			  System.out.println("MyInfo option clicked");
		  		}else {
		  			  Reporter.log("MyInfo option not clicked");
		  			  System.out.println("MyInfo option not clicked");
		  		}
		  		
		  		driver.findElement(By.xpath("(//*[contains(text(),'Emergency Contacts')])[1]")).click();
		  		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//*[contains(text(),'Assigned Emergency Contacts')])[1]"))));
		  	    Thread.sleep(1000);
		  	    
		  	    
		  	  
		  	    
		  	    
		  	    
		  	    
		  	    /* table data extract */
		  	//Get number of rows In table.
		  	  int Row_count = driver.findElements(By.xpath("//*[@id='emgcontact_list']/tbody/tr")).size();
		  	  System.out.println("Number Of Rows = "+Row_count);
		  	  
		  	  //Get number of columns In table.
		  	  int Col_count = driver.findElements(By.xpath("//*[@id='emgcontact_list']/tbody/tr[1]/td")).size();
		  	  System.out.println("Number Of Columns = "+Col_count);
		  	  
		  	  //divided xpath In three parts to pass Row_count and Col_count values.
		  	  String first_part = "//*[@id='emgcontact_list']/tbody/tr[";
		  	  String second_part = "]/td[";
		  	  String third_part = "]";
		  	  
		  	  //Used for loop for number of rows.
		  	  for (int i=1; i<=Row_count; i++){
		  	   //Used for loop for number of columns.
		  	   for(int j=1; j<=Col_count; j++){
		  	    //Prepared final xpath of specific cell as per values of i and j.
		  	    String final_xpath = first_part+i+second_part+j+third_part;
		  	    //Will retrieve value from located cell and print It.
		  	    String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
		  	    System.out.print(Table_data +"  ");   
		  	   }
		  	    System.out.println("");
		  	    
		  	    
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
