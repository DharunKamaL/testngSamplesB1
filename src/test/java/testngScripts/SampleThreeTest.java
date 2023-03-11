package testngScripts;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SampleThreeTest {
	  WebDriver driver;
	  //@BeforeMethod
	  @BeforeClass
	  public void setup() {
	  	    System.setProperty("webdriver.chrome.driver", "F:\\Dharun\\webdrivers\\chromedriver.exe");
	  		driver = new ChromeDriver();
	  		driver.manage().window().maximize();
	  	}
	  	
	  @Test(alwaysRun = true,dependsOnMethods = "seleniumSearchTest")
	  public void javaSearchTest() {
			driver.get("https://www.google.com/");
			WebElement searchBox = driver.findElement(By.name("q"));
			searchBox.sendKeys("Java Tutorial");
			searchBox.submit();
			Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
	  }
	  
	  @Test
	  public void seleniumSearchTest() {
			driver.get("https://www.google.com/");
			WebElement searchBox = driver.findElement(By.name("q"));
			searchBox.sendKeys("Selenium Tutorial");
			searchBox.submit();
			Assert.assertEquals(driver.getTitle(), "Selenium Tutorial - Google Search Page");	
	 }
	  
	  //@AfterMethod()
	  @AfterClass
	  public void teardown() {
		  driver.close();
	  }
	  
	/*
	  dependsOnMethods will indicate that certain test method to be implemented first
	  and the current method will depends on that and current method will not run if the dependency 
	  fails, instead we use always run to run our current method
	*/
	  
}
