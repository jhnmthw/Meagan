package com.tavant.nfr.meagan.selenium.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tavant.nfr.meagan.selenium.DriverBase;
public class ZapTestIT extends DriverBase {
  
    WebDriver driver;
    WebElement usernameElement;
    WebElement passwordElement;
    WebElement loginButton;
    WebElement logoutButton;
    WebElement loginButtonHead;
    
//    final String[] DEFAULT_TOKENS ={"asp.net_sessionid", "aspsessionid", "siteserver", "cfid",
//            "cftoken", "jsessionid", "phpsessid", "sessid", "sid", "viewstate", "zenid"};

	@BeforeClass
	public void setup() throws Exception{
        // Create a new WebDriver instance
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
	
		driver = getDriver();
	}
	
	@Test
	public void launchBrowser() {
		

        //navigating application URL
       driver.get(System.getProperty("appURL"));
        
	}  
	
	
	@Test
	 public void login() throws Exception{  
	   
		// Implicit Wait
       driver.manage().timeouts().implicitlyWait(1,TimeUnit.MINUTES) ;
       
       	loginButtonHead = driver.findElement(By.xpath(System.getProperty("loginBtnHeadXPath")));
       	
        if(loginButtonHead!=null)
        {
        	
       	 	loginButtonHead.click();
       	 	Thread.sleep(1000);
        }
        
        // Find the username input text element by its XPATH
         usernameElement = driver.findElement(By.xpath(System.getProperty("usernameElementXPath")));
        
        // Find the password input element by its XPATH
         passwordElement = driver.findElement(By.xpath(System.getProperty("passwordElementXPath")));
         
        
        try{
        	
//			Assert.assertEquals(usernameElement.isEnabled(), true, "The username text field is not displayed");
//			Assert.assertEquals(passwordElement.isEnabled(), true, "The passsword text field is not displayed");
			
			usernameElement.clear();
			Thread.sleep(1000);
			usernameElement.sendKeys(System.getProperty("username"));
			
			passwordElement.clear();
			Thread.sleep(1000);
			passwordElement.sendKeys(System.getProperty("password"));			
			Thread.sleep(5000);
	        
			// Find the login button element by its XPATH 
	         loginButton = driver.findElement(By.xpath(System.getProperty("loginBtnXPath")));
	         
	         driver.findElement(By.xpath("")).click();
	         
	         driver.manage().timeouts().implicitlyWait(1,TimeUnit.MINUTES) ;
	         
	     //    Assert.assertEquals(loginButton.isEnabled(), true, "The login button is not displayed");
			
			loginButton.click();
			
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.MINUTES) ;
			
        	// Find the logout button element by its XPATH 
        //    logoutButton = driver.findElement(By.xpath(System.getProperty("loginBtnXPath")));
			
		}catch(Exception e){
			Assert.fail("The login page did not load properly");
		}
        
        
   
        
        
        
            //Waiting for performing business actions
        	
	        Thread.sleep(300000);
	       
        

	 }
	 
	
	 public void logBackIn() throws Exception{
	 driver.get(System.getProperty("appURL"));
	 login();
	 }


	 }




