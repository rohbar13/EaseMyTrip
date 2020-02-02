package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvider1 {

	@DataProvider(name = "methoddata")
	public Object[][] dataProviderMethod() {
		Object[][] obj = new Object[][] { { "data one" }, { "data two" } };
		return obj;
	}

	@Test(dataProvider = "methoddata")
	public void testMethod(String data) {
		System.out.println("Data is: " + data);
	}
	
}
