package tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class BrowserOption {

	@Test
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "drivers" + File.separator + "chromedriver.exe");
		
		ChromeOptions option = new ChromeOptions(); 
		option.setHeadless(true);
		
		WebDriver driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("http://www.gmail.com");
		
		System.out.println(driver.getTitle());
		
		driver.quit();
	}
	
}
