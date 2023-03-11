package testngEx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.awt.Robot;
import java.awt.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import io.github.bonigarcia.wdm.WebDriverManager;
import javafx.scene.input.KeyEvent;

public class DanubeStore {
	WebDriver driver;
	@BeforeTest
	public void setup() throws IOException, AWTException, InterruptedException{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
//	@Test(priority = 1)
//	public void signUp() throws InvalidFormatException, IOException {
//	  driver.get("https://danube-webshop.herokuapp.com/");
//	  driver.findElement(By.cssSelector("button[id=\"signup\"]")).click();
//	  driver.findElement(By.cssSelector("input[id=\"s-name\"]")).sendKeys(readData("Name"));
//	  driver.findElement(By.cssSelector("input[id=\"s-surname\"]")).sendKeys(readData("Surname"));
//	  driver.findElement(By.cssSelector("input[id=\"s-email\"]")).sendKeys(readData("Email"));
//	  driver.findElement(By.cssSelector("input[id=\"s-password2\"]")).sendKeys(readData("Password"));
//	  driver.findElement(By.cssSelector("input[id=\"myself\"]")).click();
//	  driver.findElement(By.cssSelector("input[id=\"marketing-agreement\"]")).click();
//	  driver.findElement(By.cssSelector("input[id=\"privacy-policy\"]")).click();
//	  driver.findElement(By.cssSelector("button[id=\"register-btn\"]")).click();
//	}
	
	@Test(dataProvider = "signUp")
	public void signUp(String url,String name,String surname, String email, String password) throws InvalidFormatException, IOException {
	  driver.get(url);
	  driver.findElement(By.cssSelector("button[id=\"signup\"]")).click();
	  driver.findElement(By.cssSelector("input[id=\"s-name\"]")).sendKeys(name);
	  driver.findElement(By.cssSelector("input[id=\"s-surname\"]")).sendKeys(surname);
	  driver.findElement(By.cssSelector("input[id=\"s-email\"]")).sendKeys(email);
	  driver.findElement(By.cssSelector("input[id=\"s-password2\"]")).sendKeys(password);
	  driver.findElement(By.cssSelector("input[id=\"myself\"]")).click();
	  driver.findElement(By.cssSelector("input[id=\"marketing-agreement\"]")).click();
	  driver.findElement(By.cssSelector("input[id=\"privacy-policy\"]")).click();
	  driver.findElement(By.cssSelector("button[id=\"register-btn\"]")).click();
	}
	
	public String readData(String keys) throws InvalidFormatException, IOException {
		String values = "";
		String path = System.getProperty("user.dir") +
					"//src//test//resources//testData//danubeWebApp.xlsx";
		XSSFWorkbook workbook = new XSSFWorkbook(new File(path)); 
		XSSFSheet sheet = workbook.getSheet("signUp");
		int numRows = sheet.getLastRowNum();
		for(int i=1;i<=numRows;i++) {
			XSSFRow row = sheet.getRow(i);
			if(row.getCell(0).getStringCellValue().equalsIgnoreCase(keys)) {
				values = row.getCell(1).getStringCellValue();
			}
		}
		return values;
	}
	
  
	@Test(priority = 2, dependsOnMethods = "signUp")
	public void searchItem() throws IOException, InterruptedException, ParserConfigurationException, SAXException {
	  	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.name("searchbar")).sendKeys(readXmlData("searchName"));
		driver.findElement(By.cssSelector("button[id=\"button-search\"]")).click();
		driver.findElement(By.cssSelector("li[class=\"preview\"]")).click();
		driver.findElement(By.xpath("(//button[@class=\"call-to-action\"])[2]")).click();
		driver.findElement(By.xpath("(//button[@class=\"call-to-action\"])[2]")).click();
		driver.findElement(By.cssSelector("input[id=\"s-name\"]")).sendKeys(readXmlData("name"));
		driver.findElement(By.cssSelector("input[id=\"s-surname\"]")).sendKeys(readXmlData("surname"));
		driver.findElement(By.cssSelector("input[id=\"s-address\"]")).sendKeys(readXmlData("address"));
		driver.findElement(By.cssSelector("input[id=\"s-zipcode\"]")).sendKeys(readXmlData("zipcode"));
		driver.findElement(By.cssSelector("input[id=\"s-city\"]")).sendKeys(readXmlData("city"));
		driver.findElement(By.id("asap")).click();
		driver.findElement(By.id("s-company")).sendKeys(readXmlData("company"));
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//button[@class=\"call-to-action\"])[2]")).click();
	}
	
	public String readXmlData(String tagName) throws ParserConfigurationException, SAXException, IOException {
		String path = System.getProperty("user.dir") + 
						"//src//test//resources//testData//XmlDataRepo.xml";
		File file = new File(path);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder build = factory.newDocumentBuilder();
	    Document doc = build.parse(file);
	    NodeList list = doc.getElementsByTagName("XmlDataRepo");
	    Node node = list.item(0);
	    Element elem = (Element)node;
	    return elem.getElementsByTagName(tagName).item(0).getTextContent();
	}
	
	@DataProvider(name = "signUp")
	public Object[][] getData() throws CsvValidationException, IOException {
		String path = System.getProperty("user.dir") + 
				"//src//test//resources//testData//signUp.csv";
		String cols[];
		CSVReader reader = new CSVReader(new FileReader(path));
		ArrayList<Object> dataList = new ArrayList<Object>();
		while((cols = reader.readNext())!=null) {
			Object[] record = {cols[0], cols[1], cols[2], cols[3]};
			dataList.add(record);
		}
		return dataList.toArray(new Object[dataList.size()][]);
	}
	
  	@AfterTest
  	public void teardown() {
  		driver.close();
  	}
  	
}
