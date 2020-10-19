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
	
	//Standard User and Problem User login user validation
	@Test(dataProvider = "ValidLogin",priority=3)
	public void standardUser(String sUserName, String sPassword) throws Exception {
		pageObj.loginPage.validUserLogin(sUserName,sPassword);
		pageObj.standardUser.verifyStandardUser_ProductTitle();
		pageObj.standardUser.imageCheck();
		pageObj.standardUser.selectProduct("Test.allTheThings() T-Shirt (Red)");
		pageObj.standardUser.validateProductDescription("Test.allTheThings() T-Shirt (Red)");
		
		if(sUserName.equals("problem_user")) {
			pageObj.problemUser.findHiddenProduct("Test.allTheThings() T-Shirt (Red");
			pageObj.cart.addToCart("Test.allTheThings() T-Shirt (Red)");
			pageObj.cart.allItems();	
		}
		else {
			pageObj.standardUser.verifyHamburgerMenu();
			pageObj.standardUser.closeMenu();
			pageObj.cart.addToCart("Test.allTheThings() T-Shirt (Red)");
			pageObj.cart.removeCart();
			pageObj.cart.allItems();	
		}
		pageObj.standardUser.sort("Name (Z to A)");	
		pageObj.cart.logOut();
	}
	
	@DataProvider(name="Invalidlogin")
	public Object[][] getInvaliddata()
	{
	return new Object[][]
			{{"",""},{"standard_user",""},{"standard_user","Test"}};
	}
	
	@DataProvider(name="ValidLogin")
	public Object[][] getValiddata()
	{
	return new Object[][]
			{{"standard_user","secret_sauce"},{"problem_user","secret_sauce"}};	
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
