package tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class IncognitoBrowser {

	@Test
	public void openBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers" + File.separator + "chromedriver.exe");
		
		ChromeOptions option = new ChromeOptions(); 
		option.addArguments("--incognito");
		option.addArguments("disable-infobars");
        option.addArguments("start-maximized");
		
		
		WebDriver driver = new ChromeDriver(option);
		driver.get("chrome://settings/clearBrowserData");
		
		
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("http://www.gmail.com");
		
		System.out.println(driver.getTitle());
		
		Thread.sleep(15000);
		
		driver.quit();
	}
	
}
