package testngScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleTwoTest {
	WebDriver driver;

	@Test(retryAnalyzer = RetrySampleTest.class)
	public void cypressTest() {
		// System.setProperty("webdriver.chrome.driver",
		// "F:\\Dharun\\webdrivers\\chromedriver.exe");
		// WebDriver driver = new ChromeDriver();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Cypress Tutorial");
		searchBox.submit();
		Assert.assertEquals(driver.getTitle(), "Cypress Tutorial - Google Search Page");
		driver.close();
	}
}
