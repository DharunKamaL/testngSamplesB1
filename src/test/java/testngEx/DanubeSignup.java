package testngEx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.awt.Robot;
import java.awt.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import javafx.scene.input.KeyEvent;

public class DanubeSignup {
	WebDriver driver;
	Properties prop;
	Properties propSearch;
	Properties propDetails;
	@BeforeTest
	public void setup() throws IOException, AWTException, InterruptedException{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		prop = new Properties();
		String path = System.getProperty("user.dir") + 
					"//src//test//resources//configFiles//config.properties";
		FileInputStream propFile = new FileInputStream(path);
		prop.load(propFile);
		
//		Robot robot = new Robot();
//		for (int i = 0; i < 2; i++) {
//			robot.keyPress(KeyEvent.VK_CONTROL);
//			robot.keyPress(KeyEvent.VK_SUBTRACT);
//			robot.keyRelease(KeyEvent.VK_SUBTRACT);
//			robot.keyRelease(KeyEvent.VK_CONTROL);
//		}
		//		propSearch = new Properties();
//		String path2 = System.getProperty("user.dir") + 
//							"//src//test//resources//configFiles//searchitems.properties";
//		FileInputStream propFile2 = new FileInputStream(path2);
//		propSearch.load(propFile2);
//		
//		propDetails = new Properties();
//		String path3 = System.getProperty("user.dir") + 
//							"//src//test//resources//configFiles//details.properties";
//		FileInputStream propFile3 = new FileInputStream(path3);
//		propDetails.load(propFile3);
	}
  @Test(priority = 1)
  public void signUp() {
	  driver.get(prop.getProperty("url"));
	  driver.findElement(By.cssSelector("button[id=\"signup\"]")).click();
	  driver.findElement(By.cssSelector("input[id=\"s-name\"]")).sendKeys("Automation");
	  driver.findElement(By.cssSelector("input[id=\"s-surname\"]")).sendKeys("Tester");
	  driver.findElement(By.cssSelector("input[id=\"s-email\"]")).sendKeys("dk23@yopmail.com");
	  driver.findElement(By.cssSelector("input[id=\"s-password2\"]")).sendKeys("sdetatzuci");
	  driver.findElement(By.cssSelector("input[id=\"myself\"]")).click();
	  driver.findElement(By.cssSelector("input[id=\"marketing-agreement\"]")).click();
	  driver.findElement(By.cssSelector("input[id=\"privacy-policy\"]")).click();
	  driver.findElement(By.cssSelector("button[id=\"register-btn\"]")).click();
  }
  
  @Test(priority = 2)
  public void searchItem() throws IOException, InterruptedException {
//		propSearch = new Properties();
//		String path2 = System.getProperty("user.dir") + 
//							"//src//test//resources//configFiles//searchitems.properties";
//		FileInputStream propFile2 = new FileInputStream(path2);
//		propSearch.load(propFile2);
//		
//		propDetails = new Properties();
//		String path3 = System.getProperty("user.dir") + 
//							"//src//test//resources//configFiles//details.properties";
//		FileInputStream propFile3 = new FileInputStream(path3);
//		propDetails.load(propFile3);
		//Thread.sleep(2000);
	  	//driver.get(prop.getProperty("url"));
	  	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.name("searchbar")).sendKeys(prop.getProperty("item"));
		driver.findElement(By.cssSelector("button[id=\"button-search\"]")).click();
		driver.findElement(By.cssSelector("li[class=\"preview\"]")).click();
		driver.findElement(By.xpath("(//button[@class=\"call-to-action\"])[2]")).click();
		driver.findElement(By.xpath("(//button[@class=\"call-to-action\"])[2]")).click();
		driver.findElement(By.cssSelector("input[id=\"s-name\"]")).sendKeys(prop.getProperty("name"));
		driver.findElement(By.cssSelector("input[id=\"s-surname\"]")).sendKeys(prop.getProperty("surname"));
		driver.findElement(By.cssSelector("input[id=\"s-address\"]")).sendKeys(prop.getProperty("address"));
		driver.findElement(By.cssSelector("input[id=\"s-zipcode\"]")).sendKeys(prop.getProperty("zipcode"));
		driver.findElement(By.cssSelector("input[id=\"s-city\"]")).sendKeys(prop.getProperty("city"));
		driver.findElement(By.id("asap")).click();
		driver.findElement(By.id("s-company")).sendKeys("zuci");
//		Robot robot = new Robot();
//		for (int i = 0; i < 2; i++) {
//			robot.keyPress(KeyEvent.VK_CONTROL);
//			robot.keyPress(KeyEvent.VK_SUBTRACT);
//			robot.keyRelease(KeyEvent.VK_SUBTRACT);
//			robot.keyRelease(KeyEvent.VK_CONTROL);
//		}
//		for(int i=0; i<3; i++){
//			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,Keys.SUBTRACT));
//		}
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//button[@class=\"call-to-action\"])[2]")).click();
	}
  	@AfterTest
  	public void teardown() {
  		driver.close();
  	}
}
