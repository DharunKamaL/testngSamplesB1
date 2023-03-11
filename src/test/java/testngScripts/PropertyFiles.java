package testngScripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PropertyFiles {
	WebDriver driver;
	Properties connProp;
	@BeforeTest
	public void setup() throws IOException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		String path = System.getProperty("user.dir")+"//src//test//resources//configFiles//config.properties";
		connProp = new Properties();
		FileInputStream propsFile = new FileInputStream(path);
		connProp.load(propsFile);
//		String url = connProp.getProperty("url");
//		driver.get(url);
//		propsFile.close();
	}
	
	@Test
	public void javaSearchTest() {
		driver.get(connProp.getProperty("url"));
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Java Tutorial");
		searchBox.submit();
		Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
	
	}
	
	 @Test
	 public void seleniumSearchTest() {
		 	driver.get(connProp.getProperty("url"));
			WebElement searchBox = driver.findElement(By.name("q"));
			searchBox.sendKeys("Selenium Tutorial");
			searchBox.submit();
			Assert.assertEquals(driver.getTitle(), "Selenium Tutorial - Google Search");	
	 }
	 
	 @AfterTest
	 public void tearDown() {
		 	driver.close();
	}
}
