package testngEx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DanubeTest {
	Properties propUrl;
	Properties propLogin;
	Properties propSearch;
	WebDriver driver;
	@BeforeTest
	public void setup() throws IOException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		propUrl = new Properties();
		String path1 = System.getProperty("user.dir") +
			"//src//test//resources//configFiles//DanubeUrl.properties";
		FileInputStream propFile1 = new FileInputStream(path1);
		propUrl.load(propFile1);
	}
	
	@Test(priority = 1)
	public void login() throws IOException {
		driver.get(propUrl.getProperty("url"));
		
		propLogin = new Properties();
		String path2 = System.getProperty("user.dir") + 
						"//src//test//resources//configFiles//emailpass.properties";
		FileInputStream propFile2 = new FileInputStream(path2);
		propLogin.load(propFile2);
		
		driver.findElement(By.cssSelector("button[id=\"login\"]")).click();
		driver.findElement(By.cssSelector("input[id=\"n-email\"]")).sendKeys(propLogin.getProperty("email"));
		driver.findElement(By.cssSelector("input[id=\"n-password2\"]")).sendKeys(propLogin.getProperty("password"));
		driver.findElement(By.cssSelector("button[id=\"goto-signin-btn\"]")).click();
	}
	
	@Test(priority = 2, dependsOnMethods = "login")
	public void searchItem() throws IOException {
		propSearch = new Properties();
		String path3 = System.getProperty("user.dir") + 
							"//src//test//resources//configFiles//searchitems.properties";
		FileInputStream propFile3 = new FileInputStream(path3);
		propSearch.load(propFile3);
		
		WebElement searchBox = driver.findElement(By.cssSelector("input[type=\"text\"]"));
		searchBox.sendKeys(propSearch.getProperty("items"));
		driver.findElement(By.cssSelector("button[id=\"button-search\"]")).click();
	}
}
