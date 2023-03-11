package testngScripts;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import CommonUtils.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class ExtentReportTest {
	WebDriver driver;
	ExtentReports reports;
	ExtentSparkReporter spark;
	ExtentTest extentTest;

	@BeforeMethod
	public void beforeMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
	}

	@BeforeTest
	public void extentSetup() {
		reports = new ExtentReports();
		spark = new ExtentSparkReporter("target\\SparkReport.html");
		reports.attachReporter(spark);
	}

	@Test
	public void javaSearchTest() {
		extentTest = reports.createTest("javaSearchTest");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Java Tutorial");
		searchBox.submit();
		Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search Page");

	}

	@Test
	public void seleniumSearchTest() {
		extentTest = reports.createTest("seleniumSearchTest");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Selenium Tutorial");
		searchBox.submit();
		Assert.assertEquals(driver.getTitle(), "Selenium Tutorial - Google Search");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			extentTest.log(Status.FAIL, result.getThrowable().getMessage());
			String path = Utility.getScreenshotPath(driver);
			extentTest.addScreenCaptureFromPath(path);
		}
		driver.close();
	}

	@AfterTest
	public void extentFinishup() {
		reports.flush();
	}

}
