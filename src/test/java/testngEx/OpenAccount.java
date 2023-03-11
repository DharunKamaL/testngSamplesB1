package testngEx;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OpenAccount {
	
	WebDriver driver;
	Actions actions;
  	@BeforeClass
  	public void setup() {
	    System.setProperty("webdriver.chrome.driver", "F:\\Dharun\\webdrivers\\chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    actions= new Actions(driver);
  	}
  	
  	@Test(priority = 1)
  	public void registrationTest(){
  		driver.get("https://parabank.parasoft.com/parabank/index.htm?ConnType=JDBC");
  		Assert.assertEquals(driver.getTitle(), "ParaBank | Welcome | Online Banking");
  		driver.findElement(By.linkText("Register")).click();
  		actions.scrollByAmount(0, 300).perform();
  		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
  		driver.findElement(By.id("customer.firstName")).sendKeys("Automation");
  		driver.findElement(By.id("customer.lastName")).sendKeys("Tester");
  		driver.findElement(By.id("customer.address.street")).sendKeys("31/2, Vivekanandhar Colony");
  		driver.findElement(By.id("customer.address.city")).sendKeys("Chennai");
  		driver.findElement(By.id("customer.address.state")).sendKeys("TamilNadu");
  		driver.findElement(By.id("customer.address.zipCode")).sendKeys("600 032");
  		driver.findElement(By.id("customer.phoneNumber")).sendKeys("9876543210");
  		driver.findElement(By.id("customer.ssn")).sendKeys("12345");
  		driver.findElement(By.id("customer.username")).sendKeys("Test");
  		driver.findElement(By.id("customer.password")).sendKeys("sdetatzuci");
  		driver.findElement(By.id("repeatedPassword")).sendKeys("sdetatzuci");
  		driver.findElement(By.xpath("(//input[@class='button'])[2]")).click();
  		driver.findElement(By.xpath("//a[contains(text(),'Log')]")).click();
  	}
  	
  	@Test(priority = 2 ,dependsOnMethods = "registrationTest")
  	public void login() {
  		driver.findElement(By.cssSelector("input[name=\"username\"]")).sendKeys("Test");
  		driver.findElement(By.cssSelector("input[name=\"password\"]")).sendKeys("sdetatzuci");
  		driver.findElement(By.cssSelector("input[value=\"Log In\"]")).click();
    }
  	
  	@Test(priority = 3)
  	public void openNewAccount() {

  		driver.findElement(By.xpath("//a[contains(text(),'Open')]")).click();
  		Select sel = new Select(driver.findElement(By.id("type")));
  		sel.selectByVisibleText("SAVINGS");
  		Select selId = new Select(driver.findElement(By.id("fromAccountId")));
  		selId.getFirstSelectedOption().click();
  		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
  	   
  		//Assert.assertEquals(driver.findElement(By.xpath("//h1[contains(text(),'Account Opened')]")), "Your account was created successfully. You are now logged in.");
  	}
  	
  	@Test(priority = 4, dependsOnMethods = "openNewAccount")
  	public void transferFunds() throws InterruptedException{
  		
  	  	driver.findElement(By.xpath("//a[contains(text(),'Transfer')]")).click();
  		//driver.findElement(By.xpath("//a[contains(text(),'Accounts Overview')]")).click();
  	  	//driver.findElement(By.xpath("//a[contains(text(),'Transfer')]")).click();
  		driver.findElement(By.id("amount")).sendKeys("1000");
  		Select fromSel = new Select(driver.findElement(By.id("fromAccountId")));
  		Thread.sleep(100);
  		fromSel.selectByIndex(0);
  		Select toSel = new Select(driver.findElement(By.id("toAccountId")));
  		Thread.sleep(100);
  		toSel.selectByIndex(0);
  		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
  		
  		//Assert.assertEquals(driver.findElement(By.xpath("//h1[contains(text(),'Transfer Complete')]")), "Transfer Complete!");
  	}
  	
  	@Test(priority = 5, dependsOnMethods = "transferFunds")
  	public void accountsOverview() throws InterruptedException {
  		driver.findElement(By.xpath("//a[contains(text(),'Accounts Overview')]")).click();
//		Thread.sleep(100);
//  		String str = driver.findElement(By.xpath("(//td[@class='ng-binding'])[1]")).getText();
//  		System.out.println(str);
//  		actions.scrollByAmount(0, 300).perform();
//  		boolean bal = driver.findElement(By.cssSelector("td[id='balance']")).isDisplayed();
//  		if(bal) {
//  			System.out.println("Balance displayed...");
//  		}
  		//Assert.assertEquals(driver.findElement(By.cssSelector("td[id='balance']")), "$3900.00");
  		//driver.findElement(By.cssSelector("td[id='balance']"));
  	}
  	
	
  	@AfterClass
  	public void teardown() {
  		driver.close();
  	}
}
