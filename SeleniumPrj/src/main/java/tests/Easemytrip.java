package tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.BaseClass;

public class Easemytrip extends BaseClass{
	
	ExtentTest test;
	ExtentReports report = new ExtentReports(createReportingFolder()+"\\ExtentReportResults.html");
	
	@Test
	public void openEaseMyTripURL() throws InterruptedException, IOException {
		WebDriver driver = openBrowser("chrome");
		driver.get("https://www.easemytrip.com/");
		
		clickElement(driver, driver.findElement(By.xpath("//li[contains(text(),'One Way ')]")));
		EnterText(driver, driver.findElement(By.id("FromSector_show")), "New Delhi(DEL)");
		
		String scrpath = takeScreenshot(driver,"File1.png");
		
		test = report.startTest("SearchFrom_Test");
		test.log(LogStatus.PASS, "Search text passed");
		test.addScreenCapture(scrpath);
		report.endTest(test);
		report.flush();

		
	}
	
}
