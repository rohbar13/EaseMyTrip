package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	
	@BeforeSuite
	public String createReportingFolder() {
		File file = new File("");
		File reportfolder = new File(
				file.getAbsolutePath() + File.separator + "Reporting" + File.separator + "Screenshot");
		if (reportfolder.exists() == false) {
			reportfolder.mkdirs(); // mkdirs is used to create subdirectories and mkdir for just one directory.
		}
		return reportfolder.getAbsolutePath();
	}

	public WebDriver openBrowser(String browserName) {
		switch (browserName) {
		case "chrome":
			return openChromeBrowser();
		case "firefox":
			return openFirefoxBrowser();
		}
		return null;
	}

	private WebDriver openChromeBrowser() {
		System.setProperty("webdriver.chrome.driver", "drivers" + File.separator + "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}

	private WebDriver openFirefoxBrowser() {
		System.setProperty("webdriver.gecko.driver", "drivers" + File.separator + "geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}

	public void clickElement(WebDriver driver, WebElement element) throws InterruptedException {
		validatePresenceOfElement(element);
		scrollToTheElement(driver, element);
		element.click();
	}

	public void EnterText(WebDriver driver, WebElement element, String value) throws InterruptedException {
		validatePresenceOfElement(element);
		scrollToTheElement(driver, element);
		element.clear();
		element.sendKeys(value);
	}

	public void validatePresenceOfElement(WebElement element) {

		boolean elementPresent = false;

		try {
			elementPresent = element.isDisplayed();
		} catch (Exception e1) {

		}

		if (elementPresent == false) {
			try {
				elementPresent = element.isEnabled();
			} catch (Exception e1) {

			}
		}
		if (elementPresent == false) {
			try {
				elementPresent = element.isSelected();
			} catch (Exception e1) {

			}
		}

		if(elementPresent==false) {
			Reporter.log("Element is not present on the form " + element);
		}
	}

	public void scrollToTheElement(WebDriver driver, WebElement element) throws InterruptedException {
		Thread.sleep(500);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public String takeScreenshot(WebDriver driver, String fileName) throws IOException {

		TakesScreenshot sc = ((TakesScreenshot) driver);
		File src = sc.getScreenshotAs(OutputType.FILE);
		File dst = new File(createReportingFolder() + File.separator + fileName);
		FileUtils.copyFile(src, dst);
		
		return dst.getAbsolutePath();

	}
	
	
	public Map<String, String> readTestData(String tcid) throws IOException {
		
		File file = new File("testdata//Testing.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		Sheet sh = wb.getSheet("Sheet1");

		int lastRowNumber = sh.getLastRowNum();
		
		System.out.println(lastRowNumber);
		
		Map<String,String> testdata = new HashMap<String, String>();
		
		for(int x=0;x<lastRowNumber;x++) {
			try
			{
				Row row = sh.getRow(x);
				Cell cell = row.getCell(0);
				System.out.println(cell.getStringCellValue());
				
				
				if(cell.getStringCellValue().equalsIgnoreCase(tcid)) {
					Row valuerow = sh.getRow(x+1);
					Cell cellvalue = row.getCell(0);
					
					
					int lastCellNumber = row.getLastCellNum();
					
					for(int y=1;y<lastCellNumber;y++) {
						
						String key = row.getCell(y).getStringCellValue();
						String value = valuerow.getCell(y).getStringCellValue();
						
						testdata.put(key, value);
						
					}
					
					
					
					
					break;
				}
				
			}catch(Exception e) {
				
			}
		}
		
		
		/*
		 * for(Entry<String, String> s:testdata.entrySet()) {
		 * System.out.println(s.getKey()+" - "+s.getValue()); }
		 */
		
		return testdata;
		
	}
	
	
	

}
