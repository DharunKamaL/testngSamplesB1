package testngScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.beust.jcommander.Parameter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleOneTest {

	WebDriver driver;

	@Parameters("browser")
	@BeforeMethod
	public void setup(String strBrowser) {
		if (strBrowser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
		}
		
		else if(strBrowser.equalsIgnoreCase("edge")){
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
	}
	
//	public void setup(String strBrowser) {
//		if (strBrowser.equalsIgnoreCase("edge")) {
//			System.setProperty("webdriver.chrome.driver", "F:\\Dharun\\webdrivers\\chromedriver.exe");
//			driver = new ChromeDriver();
//		}
//		driver.manage().window().maximize();
//	}

	@Test(groups = "feature1",priority = 1) // prioritizing the test methods to be implemented first...
	public void javaSearchTest() {
		driver.get("https://www.google.com/");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Java Tutorial");
		searchBox.sendKeys(Keys.ENTER);
//		searchBox.submit();
		Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
	}

//	@Test
//	public void seleniumSearchTest() {
//		driver.get("https://www.google.com/");
//		WebElement searchBox = driver.findElement(By.name("q"));
//		searchBox.sendKeys("Selenium Tutorial");
//		searchBox.submit();
//		Assert.assertEquals(driver.getTitle(), "Selenium Tutorial - Google Search");
//	}

//	@Test(priority = 3)
//	public void cucumberSearchTest() {
//		driver.get("https://www.google.com/");
//		WebElement searchBox = driver.findElement(By.name("q"));
//		searchBox.sendKeys("Cucumber Tutorial");
//		searchBox.submit();
//		Assert.assertEquals(driver.getTitle(), "Cucumber Tutorial - Google Search");
//	}

	@AfterMethod
	public void teardown() {
		driver.close();
	}
	
}
