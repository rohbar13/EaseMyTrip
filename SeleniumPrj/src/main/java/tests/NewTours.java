package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class NewTours {

	@Test
	public void checkMenu() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://newtours.demoaut.com/");
		driver.manage().window().maximize();
		List<WebElement> menus= driver.findElements(By.xpath("//tr[@class='mouseOut']/td[2]"));
		
		for(int x=0;x<menus.size();x++) {
			System.out.println(menus.get(x).getText());
			menus.get(x).click();
			Thread.sleep(2000);
			menus = driver.findElements(By.xpath("//tr[@class='mouseOut']/td[2]"));
		}
	}
}
