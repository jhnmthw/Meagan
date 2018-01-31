//package com.tavant.nfr.meagan.selenium.tests;
//
//import java.util.regex.Pattern;
//import java.util.concurrent.TimeUnit;
//import org.testng.annotations.*;
//import static org.testng.Assert.*;
//import org.openqa.selenium.*;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.Select;
//
//public class TestIT {
//  private WebDriver driver;
//  private String baseUrl;
//  private boolean acceptNextAlert = true;
//  private StringBuffer verificationErrors = new StringBuffer();
//
//  @BeforeClass(alwaysRun = true)
//  public void setUp() throws Exception {
//    driver = new FirefoxDriver();
//    baseUrl = "http://finexp-alb-2137398505.us-east-1.elb.amazonaws.com/";
//    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//  }
//
//  @Test
//  public void testCase() throws Exception {
//    driver.get(baseUrl + "/#/");
//    driver.findElement(By.xpath("//div[2]/button")).click();
//    driver.findElement(By.id("login_userId")).clear();
//    driver.findElement(By.id("login_userId")).sendKeys("nfr104@harakirimail.com");
//    driver.findElement(By.id("login_password")).clear();
//    driver.findElement(By.id("login_password")).sendKeys("Test123$");
//    driver.findElement(By.xpath("(//button[@type='submit'])[4]")).click();
//    driver.findElement(By.id("dropdownMenu1")).click();
//    driver.findElement(By.linkText("Logout")).click();
//    driver.findElement(By.id("btn_confirm_modal_Yes")).click();
//    driver.findElement(By.id("reset_newPassword")).clear();
//    driver.findElement(By.id("reset_newPassword")).sendKeys("Test123$");
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