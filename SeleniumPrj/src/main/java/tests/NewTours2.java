package tests;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import utilities.BaseClass;

public class NewTours2 extends BaseClass{

	@Test
	public void checkMenu() throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "drivers//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://newtours.demoaut.com/");
		driver.manage().window().maximize();
		
		Map<String,String> testdata = readTestData("TC_Login_001");
		

		String username = testdata.get("username");
		String password = testdata.get("password");
		
		System.out.println("username is : "+username);
		System.out.println("password is : "+password);
		
		driver.findElement(By.name("userName")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		
	}
}
