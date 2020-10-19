package saucedemo.TestCases;

import PageClass.PageClassManager;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {
	
	PageClassManager pageObj = new PageClassManager();
	
	//To test login form
	@Test (priority = 1)
	public void validateLogin() {
		pageObj.loginPage.loginURL();
		pageObj.loginPage.validateTitle();
	}
	
	//To test invalid User Login
	@Test(dataProvider = "Invalidlogin",priority=2)
	public void verifyInvalidLogin(String sUserName, String sPassword){
		pageObj.loginPage.validateInvalidLogin(sUserName, sPassword);		
	}
	
	@DataProvider(name="Invalidlogin")
	public Object[][] getInvaliddata()
	{
	return new Object[][]
			{{"",""},{"standard_user",""},{"standard_user","Test"}};
	}
	
	@AfterTest
	public void tearDownTest() {
		pageObj.objWebDriver.close();
	}

	@AfterSuite
	public void teardown() {
		pageObj.flush();
	}
	
}
