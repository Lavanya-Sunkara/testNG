package commonFunctions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class FunctionaLibrary {
public static WebDriver driver;
public static Properties conpro;
	
public static WebDriver startBrowser()throws Throwable
{
	conpro = new Properties();
	conpro.load(new FileInputStream(".//PropertyFile/Environment.properties"));
	if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
	{
		driver = new FirefoxDriver();
		
	}
	else
	{
		Reporter.log("Browser value is not Matching",true);
	}
	return driver;
}
public static void openUrl()
{
	driver.get(conpro.getProperty("Url"));
}
public static void waitForElement(String Ltype,String Lvalue,String Mywait)
{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(Mywait)));
	if(Ltype.equalsIgnoreCase("xpath"))
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Lvalue)));
		
	}
	if(Ltype.equalsIgnoreCase("name"))
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Lvalue)));
		
	}
	if(Ltype.equalsIgnoreCase("id"))
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Lvalue)));
		
	}
}
public static void typeAction(String Ltype, String Lvalue,String TestData)
{
	if(Ltype.equalsIgnoreCase("xpath"))
	{
		driver.findElement(By.xpath(Lvalue)).clear();
		driver.findElement(By.xpath(Lvalue)).sendKeys(TestData);
	}
	if(Ltype.equalsIgnoreCase("name"))
	{
		driver.findElement(By.name(Lvalue)).clear();
		driver.findElement(By.name(Lvalue)).sendKeys(TestData);
	}
	if(Ltype.equalsIgnoreCase("id"))
	{
		driver.findElement(By.id(Lvalue)).clear();
		driver.findElement(By.id(Lvalue)).sendKeys(TestData);
	}
}
public static void clickAction(String Ltype, String Lvalue)
{
	if(Ltype.equalsIgnoreCase("xpath"))
	{
		driver.findElement(By.xpath(Lvalue)).click();
	}
	if(Ltype.equalsIgnoreCase("name"))
	{
		driver.findElement(By.name(Lvalue)).click();
	}
	if(Ltype.equalsIgnoreCase("id"))
	{
		driver.findElement(By.id(Lvalue)).sendKeys(Keys.ENTER);
	}
}
 public static void validateTitle(String Expected_Title)
 {
	 String Actual_title = driver.getTitle();
	 try {
		 Assert.assertEquals(Actual_title, Expected_Title, "Title is Not Matching");
	 } catch (AssertionError e){
		System.out.println(e.getMessage());
 }
 }
 public static void closeBrowser()
 {
	 driver.quit();
 }
 public static void dropDownAction(String Ltype, String Lvalue,String TestData)
 {
	 if(Ltype.equalsIgnoreCase("xpath"))
	 {
		 int value =Integer.parseInt(TestData);
		 Select element = new Select(driver.findElement(By.xpath(Lvalue)));
		 element.selectByIndex(value);
		 
	 }
	 if(Ltype.equalsIgnoreCase("name"))
	 {
		 int value =Integer.parseInt(TestData);
		 Select element = new Select(driver.findElement(By.name(Lvalue)));
		 element.selectByIndex(value);
		 
	 }
	 if(Ltype.equalsIgnoreCase("id"))
	 {
		 int value =Integer.parseInt(TestData);
		 Select element = new Select(driver.findElement(By.id(Lvalue)));
		 element.selectByIndex(value);
		 
	 }
 }
 public static void capturestock(String Ltype, String Lvalue) throws Throwable
 {
	 String stockNum="";
	 if(Ltype.equalsIgnoreCase("xpath"))
	 {
		 stockNum =driver.findElement(By.xpath(Lvalue)).getAttribute("value");
	 } 
 
 if(Ltype.equalsIgnoreCase("id"))
 {
	 stockNum =driver.findElement(By.id(Lvalue)).getAttribute("value");
 } 
 if(Ltype.equalsIgnoreCase("name"))
 {
	 stockNum =driver.findElement(By.name(Lvalue)).getAttribute("value");
 } 
     FileWriter fw = new FileWriter("./CaptureData/stocknumber.txt");
     BufferedWriter bw = new BufferedWriter(fw);
     bw.write(stockNum);
     bw.flush();
     bw.close();
     }
 public static void stockTable()throws Throwable
 {
	 FileReader fr = new FileReader("./CaptureData/stocknumber.txt");
	 BufferedReader br = new BufferedReader(fr);
	 String Exp_Data =br.readLine();
	 if(!driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).isDisplayed())
		 driver.findElement(By.xpath(conpro.getProperty("search-panel"))).click();
	     driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).clear();
	     driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).sendKeys(Exp_Data);
	     driver.findElement(By.xpath(conpro.getProperty("search-button"))).click();
	     Thread.sleep(5000);
	     String Act_Data = driver.findElement(By.xpath("//table[@class='table ewTable']/tbody/tr[1]/td[8]/div/span/span")).getText();
	     
	    		 Reporter.log(Exp_Data+"    "+Act_Data, true);
	      try {
			Assert.assertEquals(Act_Data, Exp_Data, "stock Number Not Found In Table");
		} catch (AssertionError e) {
			System.out.println(e.getMessage());
		}
 }
 public static void capturesupplier(String Ltype, String Lvalue) throws Throwable
 {
	 String supplierNum="";
	 if(Ltype.equalsIgnoreCase("xpath"))
	 {
		 supplierNum =driver.findElement(By.xpath(Lvalue)).getAttribute("value");
	 } 
 
 if(Ltype.equalsIgnoreCase("id"))
 {
	 supplierNum =driver.findElement(By.id(Lvalue)).getAttribute("value");
 } 
 if(Ltype.equalsIgnoreCase("name"))
 {
	 supplierNum =driver.findElement(By.name(Lvalue)).getAttribute("value");
 } 
     FileWriter fw = new FileWriter("./CaptureData/supplier.txt");
     BufferedWriter bw = new BufferedWriter(fw);
     bw.write(supplierNum);
     bw.flush();
     bw.close();
     }
 
 public static void suppliertable()throws Throwable
 {
	 FileReader fr = new FileReader("./CaptureData/supplier.txt");
	 BufferedReader br = new BufferedReader(fr);
	 String Exp_Data =br.readLine();
	 if(!driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).isDisplayed())
		 driver.findElement(By.xpath(conpro.getProperty("search-panel"))).click();
	     driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).clear();
	     driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).sendKeys(Exp_Data);
	     driver.findElement(By.xpath(conpro.getProperty("search-button"))).click();
	     Thread.sleep(5000);
	     String Act_Data = driver.findElement(By.xpath("//table[@class='table ewTable']/tbody/tr[1]/td[6]/div/span/span")).getText();
	     
	    		 Reporter.log(Exp_Data+"    "+Act_Data, true);
	      try {
			Assert.assertEquals(Act_Data, Exp_Data, "supplier Number Not Found In Table");
		} catch (AssertionError e) {
			System.out.println(e.getMessage());
		}
 }
 
 public static void capturecustomer(String Ltype, String Lvalue) throws Throwable
 {
	 String customerNum="";
	 if(Ltype.equalsIgnoreCase("xpath"))
	 {
		 customerNum =driver.findElement(By.xpath(Lvalue)).getAttribute("value");
	 } 
 
 if(Ltype.equalsIgnoreCase("id"))
 {
	 customerNum =driver.findElement(By.id(Lvalue)).getAttribute("value");
 } 
 if(Ltype.equalsIgnoreCase("name"))
 {
	 customerNum =driver.findElement(By.name(Lvalue)).getAttribute("value");
 } 
     FileWriter fw = new FileWriter("./CaptureData/customer.txt");
     BufferedWriter bw = new BufferedWriter(fw);
     bw.write(customerNum);
     bw.flush();
     bw.close();
     }
 
 public static void customertable()throws Throwable
 {
	 FileReader fr = new FileReader("./CaptureData/customer.txt");
	 BufferedReader br = new BufferedReader(fr);
	 String Exp_Data =br.readLine();
	 if(!driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).isDisplayed())
		 driver.findElement(By.xpath(conpro.getProperty("search-panel"))).click();
	     driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).clear();
	     driver.findElement(By.xpath(conpro.getProperty("search-textbox"))).sendKeys(Exp_Data);
	     driver.findElement(By.xpath(conpro.getProperty("search-button"))).click();
	     Thread.sleep(5000);
	     String Act_Data = driver.findElement(By.xpath("//table[@class='table ewTable']/tbody/tr/td[5]/div/span/span")).getText();
	     
	    		 Reporter.log(Exp_Data+"    "+Act_Data, true);
	      try {
			Assert.assertEquals(Act_Data, Exp_Data, "customer Number Not Found In Table");
		} catch (AssertionError e) {
			System.out.println(e.getMessage());
		}
 }
}























