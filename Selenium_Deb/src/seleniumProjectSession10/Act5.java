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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class Act5 {
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
    public void myInfoEdit() throws Exception {
    	
    	Thread.sleep(1000);
		  if(driver.findElement(By.id("menu_pim_viewMyDetails")).isDisplayed()) {
		  Reporter.log("MyInfo option displayed");
		  System.out.println("MyInfo option displayed");
		  
		  driver.findElement(By.id("menu_pim_viewMyDetails")).click();
		  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("btnSave"))));
		  driver.findElement(By.id("btnSave")).click();
		  Reporter.log("Edit button clicked");
		  System.out.println("Edit button clicked");
		  Thread.sleep(3000);
		  
		  driver.findElement(By.id("personal_txtEmpLastName" )).clear();
		  driver.findElement(By.id("personal_txtEmpLastName" )).sendKeys("lastName "+ n);
		  
		  // click gender
		  WebElement radio1 =  driver.findElement(By.id("personal_optGender_1" ));
		  WebElement radio2 =  driver.findElement(By.id("personal_optGender_2" ));
		  if(radio1.isSelected()) {
			  radio2.click();
			  Reporter.log("Gender selected as female");
			  System.out.println("Gender selected as female");
		  }else if (radio2.isSelected()) {
			  radio1.click();
			  Reporter.log("Gender selected as male");
			  System.out.println("Gender selected as male");
		  }
		  Thread.sleep(1000);
		//Using Select class for selecting value from dropdown  
		  	if(driver.findElement(By.id("personal_cmbNation")).isDisplayed()) {
			  Select dropdown = new Select(driver.findElement(By.id("personal_cmbNation")));  
			  dropdown.selectByIndex(n);  
		  
			  Reporter.log("Nationality selected ");
			  System.out.println("Nationality selected");
		  	}else {
			  Reporter.log("Nationality not selected ");
			  System.out.println("Nationality not selected");
		  	}
		  	Thread.sleep(1000);
		  //Datepicker
		  	if(driver.findElement(By.id("personal_DOB")).isDisplayed()) {
		  		driver.findElement(By.id("personal_DOB")).clear();
		  		driver.findElement(By.id("personal_DOB")).sendKeys("1990-01-06");
			  
				  Reporter.log("Date of Birth changed ");
				  System.out.println("Date of Birth changed");
			  	}else {
				  Reporter.log("Date of Birth not changed ");
				  System.out.println("Date of Birth not changed");
			  	}
		  	Thread.sleep(1000);
		  	driver.findElement(By.id("btnSave")).click();
		  	Thread.sleep(3000);
		 
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
