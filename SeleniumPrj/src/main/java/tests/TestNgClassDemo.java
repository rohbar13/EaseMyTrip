package tests;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNgClassDemo {

	@AfterMethod
	public void AfterMethod() {
		System.out.println("This is AfterMethod");
	}
	
	@BeforeMethod
	public void BeforeMethod() {
		System.out.println("This is BeforeMethod");
	}
	
	
	@BeforeTest
	public void BeforeTest() {
		System.out.println("This is BeforeTest");
	}
	
	@AfterTest
	public void AfterTest() {
		System.out.println("This is AfterTest");
	}
	
	@BeforeClass
	public void BeforeClass() {
		System.out.println("This is BeforeClass");
	}
	
	@AfterClass
	public void AfterClass() {
		System.out.println("This is AfterClass");
	}
	
	@BeforeSuite
	public void BeforeSuite() {
		System.out.println("This is BeforeSuite");
	}
	
	@AfterSuite
	public void AfterSuite() {
		System.out.println("This is AfterSuite");
	}
	
	@Test(dependsOnMethods = "testMethod1")
	public void testMethod()  {
		System.out.println("------This is testMethod");
	}
	
	@Test(invocationCount = 5)
	public void testMethod1() throws Exception {
		Reporter.log("---------Method - testMethod1 is getting executed now-----------");
		System.out.println("------This is testMethod1");
		throw new Exception("-----Explicit Method Failed----");
	}
}
