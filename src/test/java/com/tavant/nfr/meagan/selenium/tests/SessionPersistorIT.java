//package com.tavant.nfr.meagan.selenium.tests;
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
////import org.openqa.selenium.Cookie;
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.tavant.nfr.meagan.selenium.DriverBase;
//public class SessionPersistorIT extends DriverBase {
//  
//    WebDriver driver;
//    WebElement usernameElement;
//    WebElement passwordElement;
//    WebElement loginButton;
//    WebElement logoutButton;
//    
//
//	@BeforeClass
//	public void setup() throws Exception{
//        // Create a new WebDriver instance
//        // Notice that the remainder of the code relies on the interface,
//        // not the implementation.
//	
//		driver = getDriver();
//	}
//	
//	@Test
//	public void launchBrowser() {
//		
//
//        //navigating application URL
//       driver.get(System.getProperty("appURL"));
//        
//	}  
//	
//	
//	@Test
//	 public void login() throws Exception{  
//	   // Implicit Wait
//       driver.manage().timeouts().implicitlyWait(1,TimeUnit.MINUTES) ;
//        
//        // Find the username input text element by its XPATH
//         usernameElement = driver.findElement(By.xpath(System.getProperty("usernameElementXPath")));
//        
//        // Find the password input element by its XPATH
//         passwordElement = driver.findElement(By.xpath(System.getProperty("passwordElementXPath")));
//        
//        // Find the login button element by its XPATH 
//         loginButton = driver.findElement(By.xpath(System.getProperty("loginBtnXPath")));
//        
//        try{
//        	
//			Assert.assertEquals(usernameElement.isDisplayed(), true, "The username text field is not displayed");
//			Assert.assertEquals(passwordElement.isDisplayed(), true, "The passsword text field is not displayed");
//			Assert.assertEquals(loginButton.isDisplayed(), true, "The login button is not displayed");
//			
//			usernameElement.sendKeys(System.getProperty("username"));
//			passwordElement.sendKeys(System.getProperty("password"));
//			
//			loginButton.click();
//			
//		//	driver.manage().timeouts().implicitlyWait(20,TimeUnit.MINUTES) ;
//			
//        	// Find the logout button element by its XPATH 
//     //       logoutButton = driver.findElement(By.xpath(System.getProperty("loginBtnXPath")));
//			
//		}catch(Exception e){
//			Assert.fail("The login page did not load properly");
//		}
//        
//        
//   
////        Thread.sleep(30000);
//        
//        
//        //Waiting for performing business actions 
//        Thread.sleep(1200000);
//	        
//
//        
//
//	 }
//	 
//	
//	 public void logBackIn() throws Exception{
//	 driver.get(System.getProperty("appURL"));
//	 login();
//	 }
//
//
//	 }
//
//
//
//
