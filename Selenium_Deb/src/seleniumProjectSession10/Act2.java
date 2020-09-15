package seleniumProjectSession10;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Act2 {
    WebDriver driver;
    WebElement element= null;
    
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
        
        //Open browser
        driver.get("http://alchemy.hguy.co/orangehrm");
        driver.manage().window().maximize();
    }

    @Test
    public void verifyHeaderImgURL() throws Exception {
       
    	WebElement img = driver.findElement(By.xpath("//*[@id='divLogo']/img"));
    	String src = img.getAttribute("src");
        System.out.println("Page title is: " + src);
            
        Thread.sleep(1000);
    }

    @AfterMethod
    public void afterMethod() {
        //Close the browser
        driver.quit();
    }

}
