package testngEx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportTestEx {
	WebDriver driver;
	Actions actions;
	ExtentReports reports;
	ExtentSparkReporter spark;
	ExtentTest extentTest;

	// @Parameters("browser")

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
//	public void setUp(String strBrowser) {
//		if (strBrowser.equalsIgnoreCase("chrome")) {
//    		System.setProperty("webdriver.chrome.driver", "F:\\Dharun\\webdrivers\\chromedriver.exe");
//			driver = new ChromeDriver();
//			driver.manage().window().maximize();
//		}

	@BeforeTest
	public void extentTestsetUp() {
		reports = new ExtentReports();
		spark = new ExtentSparkReporter("target\\SignupReport.html");
		reports.attachReporter(spark);
	}

	@Test(priority = 1)
	public void danubeSignUp() throws InvalidFormatException, IOException {
		extentTest = reports.createTest("danubeSignUp");
		driver.get(readData("DanubeUrl"));
		driver.findElement(By.cssSelector("button[id=\"signup\"]")).click();
		driver.findElement(By.cssSelector("input[id=\"s-name\"]")).sendKeys(readData("Name"));
		driver.findElement(By.cssSelector("input[id=\"s-surname\"]")).sendKeys(readData("Surname"));
		driver.findElement(By.cssSelector("input[id=\"s-email\"]")).sendKeys(readData("Email"));
		driver.findElement(By.cssSelector("input[id=\"s-password2\"]")).sendKeys(readData("Password"));
		driver.findElement(By.cssSelector("input[id=\"myself\"]")).click();
		driver.findElement(By.cssSelector("input[id=\"marketing-agreement\"]")).click();
		driver.findElement(By.cssSelector("input[id=\"privacy-policy\"]")).click();
		driver.findElement(By.cssSelector("button[id=\"register-btn\"]")).click();
	}

	public String readData(String keys) throws InvalidFormatException, IOException {
		String values = "";
		String path = System.getProperty("user.dir") + "//src//test//resources//testData//danubeWebApp.xlsx";
		XSSFWorkbook workbook = new XSSFWorkbook(new File(path));
		XSSFSheet sheet = workbook.getSheet("signUp");
		int numRows = sheet.getLastRowNum();
		for (int i = 1; i <= numRows; i++) {
			XSSFRow row = sheet.getRow(i);
			if (row.getCell(0).getStringCellValue().equalsIgnoreCase(keys)) {
				values = row.getCell(1).getStringCellValue();
			}
		}
		return values;
	}

	@Test(priority = 2, dataProvider = "opencartRegData")
	public void demoOpenCartReg(String url, String FirstName, String LastName, String Email, String Password) {
		// Actions actions = new Actions(driver);
		extentTest = reports.createTest("demoCartReg");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.get(url);
		actions = new Actions(driver);
		WebElement menu = driver.findElement(By.xpath("(//li/descendant::i[@class='fas fa-caret-down'])[2]"));
		WebElement submenu = driver.findElement(By.xpath("//li/a[contains(text(),'Register')]"));
		actions.click(menu).perform();
		actions.click(submenu).perform();
		driver.findElement(By.cssSelector("input[name=firstname]")).sendKeys(FirstName);
		driver.findElement(By.cssSelector("input[name=lastname]")).sendKeys(LastName);
		driver.findElement(By.cssSelector("input[name=email]")).sendKeys(Email);
		driver.findElement(By.cssSelector("input[name=password]")).sendKeys(Password);
		actions.scrollByAmount(0, 1500);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("agree")));
		driver.findElement(By.name("agree")).click();
		WebElement cont = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
		wait.until(ExpectedConditions.elementToBeClickable(cont));
		cont.click();
	}

	@DataProvider(name = "opencartRegData")
	public Object[][] getData() throws CsvValidationException, IOException {
		String path = System.getProperty("user.dir") + "//src//test//resources//testData//openCartRegData.csv";
		CSVReader read = new CSVReader(new FileReader(path));
		String[] cols;
		ArrayList<Object> dList = new ArrayList<Object>();
		while ((cols = read.readNext()) != null) {
			Object[] rec = { cols[0], cols[1], cols[2], cols[3], cols[4] };
			dList.add(rec);
		}
		return dList.toArray(new Object[dList.size()][]);
	}

	@Test(priority = 3)
	public void parabanksignUp() throws SAXException, IOException, ParserConfigurationException {
		extentTest = reports.createTest("parabanksignUp");
		driver.get(xmlData("link"));
		Assert.assertEquals(driver.getTitle(), "ParaBank | Welcome | Online Banking");
		driver.findElement(By.linkText("Register")).click();
		// actions.scrollByAmount(0, 300).perform();
		// WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		driver.findElement(By.id("customer.firstName")).sendKeys(xmlData("firstname"));
		driver.findElement(By.id("customer.lastName")).sendKeys(xmlData("lastname"));
		driver.findElement(By.id("customer.address.street")).sendKeys(xmlData("street"));
		driver.findElement(By.id("customer.address.city")).sendKeys(xmlData("city"));
		driver.findElement(By.id("customer.address.state")).sendKeys(xmlData("state"));
		driver.findElement(By.id("customer.address.zipCode")).sendKeys(xmlData("zipcode"));
		driver.findElement(By.id("customer.phoneNumber")).sendKeys(xmlData("ph.no"));
		driver.findElement(By.id("customer.ssn")).sendKeys(xmlData("ssn"));
		driver.findElement(By.id("customer.username")).sendKeys(xmlData("username"));
		driver.findElement(By.id("customer.password")).sendKeys(xmlData("password"));
		driver.findElement(By.id("repeatedPassword")).sendKeys(xmlData("repeatpass"));
		driver.findElement(By.xpath("(//input[@class='button'])[2]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Log')]")).click();
	}

	public String xmlData(String tagname) throws SAXException, IOException, ParserConfigurationException {
		String path = System.getProperty("user.dir") + "//src//test//resources//testData//paraBankData.xml";
		File file = new File(path);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(file);
		NodeList list = doc.getElementsByTagName("paraBankData");
		Node node = list.item(0);
		Element elem = (Element) node;
		return elem.getElementsByTagName(tagname).item(0).getTextContent();
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			extentTest.log(Status.FAIL, result.getThrowable().getMessage());
		}
		driver.close();
	}

	@AfterTest
	public void extentTestfinishUp() {
		reports.flush();
	}

}
