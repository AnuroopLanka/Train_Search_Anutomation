package minipro;

import java.io.File;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



public class TrainSearchAvailability {

	public static WebDriver driver;
	static ExtentHtmlReporter report = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\ExtentReport\\/ExtentReport.html");
	static ExtentReports ex = new ExtentReports();
	WriteToExcel writeData = new WriteToExcel();


	@Test
	public static WebDriver setupDriver(String driverName) {
		ex.attachReporter(report);
		ExtentTest log = ex.createTest("Driver Setup");
		log.log(Status.INFO, "Driver Setup is initiated");
		driver = DriverSetup.getDriver(driverName);
		log.log(Status.PASS, "The Driver Setup is successful");
		ex.flush();
		return driver;

	}

	@Test
	public void verify() throws InterruptedException, IOException {

		// Getting the opened website title
		ex.attachReporter(report);
		ExtentTest log = ex.createTest("Page Title Verification");
		log.log(Status.INFO, "We get the title");
		String PageTitle = driver.getTitle();
		Assert.assertEquals(PageTitle, "IRCTC Next Generation eTicketing System");
		log.log(Status.PASS, "The Title Verification is successful");
		// ex.flush();

		// Accept Alert
		ExtentTest log1 = ex.createTest("Accept alert");
		log1.log(Status.INFO, "Accepting the alerts");
		driver.findElement(By.xpath(
				"/html/body/app-root/app-home/div[1]/app-header/p-dialog[2]/div/div/div[2]/div/form/div[2]/button"))
				.click();
		log1.log(Status.PASS, "The alerts are accepted");

		// Enter from location by taking location from excel sheet
		ExtentTest log2 = ex.createTest("Entering data from excel");
		log2.log(Status.INFO, "Input data from excel");
		try {
			excel.setExcelFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String source = excel.getCellData(1, 0);
		WebElement from = driver.findElement(By.xpath("//*[@id=\'origin\']/span/input"));
		from.sendKeys(source);
		Thread.sleep(5000);
		from.sendKeys(Keys.ARROW_DOWN);
		from.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		// Enter destination by taking data from excel sheet

		String des = excel.getCellData(1, 1);
		WebElement to = driver.findElement(By.xpath("//*[@id=\'destination\']/span/input"));
		Thread.sleep(2000);
		to.sendKeys(des);
		Thread.sleep(5000);
		to.sendKeys(Keys.ARROW_DOWN);
		to.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		log2.log(Status.PASS, "Excel data input successful");

		// Choose future date as 4 days from current date
		ExtentTest log3 = ex.createTest("Entering Date");
		log3.log(Status.INFO, "Date input");
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		LocalDate currentday = LocalDate.now();
		LocalDate bookingdate = currentday.plusDays(4);
		String Datefield = String.format("%02d", bookingdate.getDayOfMonth()) + "/"
				+ String.format("%02d", bookingdate.getMonthValue()) + "/" + bookingdate.getYear();

		WebElement calendar = driver.findElement(By.xpath("//span[contains(@class,'calendar')]/input"));
		jse.executeScript("arguments[0].value = ''", calendar);
		calendar.sendKeys(Datefield);
		WebElement cal1 = driver.findElement(By.xpath("//div[contains(@class,'datepicker')]"));
		jse.executeScript("arguments[0].style.display = 'none'", cal1);
		Thread.sleep(2000);
		log3.log(Status.PASS, "Date input successful");

		// Select 'Divyaang Concession'
		ExtentTest log4 = ex.createTest("Select Divyaang Concession");
		log4.log(Status.INFO, "Select option Divyaang Concession");
		driver.findElement(By.xpath(
				"//*[@id=\"divMain\"]/div/app-main-page/div/div/div[1]/div[1]/div[1]/app-jp-input/div/form/div[4]/div/span[1]/label"))
				.click();
		Thread.sleep(2000);

		driver.findElement(By.xpath(
				"/html/body/app-root/app-home/div[3]/div/app-main-page/div/div/div[1]/div[1]/div[1]/app-jp-input/p-confirmdialog/div/div/div[3]/button"))
				.click();
		Thread.sleep(2000);
		log4.log(Status.PASS, "Divyaang Concession selected Successfully");

		// Select sleeper class
		ExtentTest log5 = ex.createTest("Select Sleeper Class");
		log5.log(Status.INFO, "Select option Sleeper Class");
		driver.findElement(By.id("journeyClass")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='journeyClass']/div/div[4]/div/ul/p-dropdownitem[10]/li")).click();
		Thread.sleep(4000);
		log5.log(Status.PASS, "Sleeper Class Option selected successfully");

		// Take ScreenShot of the entered details
		ExtentTest log6 = ex.createTest("Screenshot of entry details");
		log6.log(Status.INFO, "Taking entered details Screenshot");
	
		Screenshot.takeScreenshot(driver,"search details");
		System.out.println("The entered details screenshot is taken");
		log6.log(Status.PASS, "Screenshot of entry detils taken successfully");

		// Click on Search Button
		ExtentTest log7 = ex.createTest("Search button ");
		log7.log(Status.INFO, "Search button clicked");
		driver.findElement(By.xpath(
				"/html/body/app-root/app-home/div[3]/div/app-main-page/div/div/div[1]/div[1]/div[1]/app-jp-input/div/form/div[5]/div/button"))
				.click();
		log7.log(Status.PASS, "Search button clicked successfully");

		// Navigate to Search Result Window
		ExtentTest log8 = ex.createTest("Navigate to result window ");
		log8.log(Status.INFO, "Navigates to result window");
		Thread.sleep(5000);
		String search = driver.getWindowHandle();
		driver.switchTo().window(search);
		log7.log(Status.PASS, "Navigation to result window successful");

		// Print Number of train search results available
		String Trains = driver.findElement(By.xpath("//*[@id=\"divMain\"]/div/app-train-list/div[4]/div/div[1]"))
				.getText();
		writeData.setCellData(0, 0, Trains);
		System.out.println(Trains);
		Thread.sleep(2000);

		// Print the names of the trains
		String[] results = driver
				.findElement(By.xpath("//*[@id=\"divMain\"]/div/app-train-list/div[4]/div/div[1]/span")).getText()
				.split(" ");
		String f = results[0];
		List<WebElement> trainList = driver.findElements(By.xpath("//div[contains(@class,'train-heading')]/strong"));
		int trainListSize = trainList.size();
		int i = 1;
		String[] outputs = new String[5];
		outputs[0] = "Total Number of trains available are :" + f;
		for (WebElement train : trainList) {
			outputs[i] = i + ":" + train.getText();
			i++;
		}

		for (int j = 1; j <= (trainListSize+1); j++) {
			writeData.setCellData(j, 0, outputs[j - 1]);
			System.out.println(outputs[j-1]);
		}
		
		writeData.closeFileStream();

		// Take ScreenShot of the Result console
		ExtentTest log9 = ex.createTest("Screenshot of result console ");
		log9.log(Status.INFO, "Takes screenshot of result console");

		Screenshot.takeScreenshot(driver, "result");
		System.out.println("The result page screenshot is taken");
		log9.log(Status.PASS, "The result page screenshot is taken successfully.");

		// Quit Driver and Close the WebPage
		ExtentTest log10 = ex.createTest("Quit Driver ");
		log10.log(Status.INFO, "Quits the driver");
		driver.quit();
		log10.log(Status.PASS, "The driver quit successfully.");
		ex.flush();
	}

	public static void main(String[] args) throws InterruptedException, IOException {

		TrainSearchAvailability p = new TrainSearchAvailability();

		p.setupDriver("chrome");
		p.verify();

		//p.setupDriver("firefox");
		//p.verify();

	}

}