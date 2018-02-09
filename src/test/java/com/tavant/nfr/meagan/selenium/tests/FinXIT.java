//package com.tavant.nfr.meagan.selenium.tests;
//
//import java.util.regex.Pattern;
//import java.util.concurrent.TimeUnit;
//import org.testng.annotations.*;
//
//import com.tavant.nfr.meagan.selenium.DriverBase;
//
//import static org.testng.Assert.*;
//import org.openqa.selenium.*;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.Select;
//
//public class FinXIT extends DriverBase{
//  private WebDriver driver;
//  private String baseUrl;
//  private boolean acceptNextAlert = true;
//  private StringBuffer verificationErrors = new StringBuffer();
//
//  @BeforeClass(alwaysRun = true)
//  public void setUp() throws Exception {
//    driver = getDriver();
//
//  }
//
//  @Test
//  public void testUntitledTestCase() throws Exception {
//    driver.get("http://finexp-alb-2137398505.us-east-1.elb.amazonaws.com/#/");
//    driver.findElement(By.cssSelector("button.btn.btn-default.head-login-btn")).click();
//    driver.findElement(By.id("login_userId")).click();
//    driver.findElement(By.id("login_userId")).clear();
//    driver.findElement(By.id("login_userId")).sendKeys("john.mathew@tavant.com");
//    driver.findElement(By.id("login_password")).click();
//    driver.findElement(By.id("login_password")).clear();
//    driver.findElement(By.id("login_password")).sendKeys("Test123$");
//    driver.findElement(By.cssSelector("button.btn.btn-primary.submit.auto-prompt.bold-text.go-next")).click();
//  }
//
//  @AfterClass(alwaysRun = true)
//  public void tearDown() throws Exception {
//    driver.quit();
//    String verificationErrorString = verificationErrors.toString();
//    if (!"".equals(verificationErrorString)) {
//      fail(verificationErrorString);
//    }
//  }
//
//  private boolean isElementPresent(By by) {
//    try {
//      driver.findElement(by);
//      return true;
//    } catch (NoSuchElementException e) {
//      return false;
//    }
//  }
//
//  private boolean isAlertPresent() {
//    try {
//      driver.switchTo().alert();
//      return true;
//    } catch (NoAlertPresentException e) {
//      return false;
//    }
//  }
//
//  private String closeAlertAndGetItsText() {
//    try {
//      Alert alert = driver.switchTo().alert();
//      String alertText = alert.getText();
//      if (acceptNextAlert) {
//        alert.accept();
//      } else {
//        alert.dismiss();
//      }
//      return alertText;
//    } finally {
//      acceptNextAlert = true;
//    }
//  }
//}
