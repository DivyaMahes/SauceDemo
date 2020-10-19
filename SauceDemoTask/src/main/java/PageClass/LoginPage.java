package PageClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Utility.TestData;

public class LoginPage {
	
	WebDriver driver;
	PageClassManager client;

	By user=By.xpath("//input[@id='user-name']");
	By password=By.xpath("//input[@id='password']");
	By login = By.xpath("//input[@id='login-button']");
	By loginError = By.xpath("//h3[@data-test='error']");
	
	public LoginPage(PageClassManager client) {
		this.client = client;
		driver = client.pageClassdriver;
	}
	
	//Method to validate URL Navigation
	public void loginURL() {
		String loginURL = TestData.WebsiteURL;
		driver.get(loginURL);
		String sActURL = driver.getCurrentUrl();
		PageClassManager.logInfo("Login Form", "Validating URL Navigation ");
		PageClassManager.ValidateContent(sActURL, loginURL, "URL Navigation");
	}
	
	//Method to validate Title
	public void validateTitle() {
		String sActualtitle = driver.getTitle();
		String sExpectedTitle = TestData.sWebPageTitle;
		PageClassManager.logInfo("", "Validating Web Page Title");
		PageClassManager.ValidateContent(sActualtitle, sExpectedTitle, "Title Validation");
	}

	//Method to validate invalid user login
	public void validateInvalidLogin(String sUserName, String sPassword) {
		PageClassManager.logInfo("", "Validating for User Login : "+sUserName+" , "+sPassword);
		driver.findElement(user).clear();
		driver.findElement(user).sendKeys(sUserName);
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(sPassword);
		driver.findElement(login).click();
		
		if (sUserName.equals("") && sPassword.equals("")) {
			String sErrorMessage = driver.findElement(loginError).getText();
			String sExpectedMessage = "Epic sadface: Username is required";
			PageClassManager.ValidateContent(sErrorMessage, sExpectedMessage, "Error Message Validation");	
		} else if(sUserName.equals("")) {
			String sErrorMessage = driver.findElement(loginError).getText();
			String sExpectedMessage = "Epic sadface: Username is required";
			PageClassManager.ValidateContent(sErrorMessage, sExpectedMessage, "Error Message Validation");
		} else if(sPassword.equals("")) {
			String sErrorMessage = driver.findElement(loginError).getText();
			String sExpectedMessage = "Epic sadface: Password is required";
			PageClassManager.ValidateContent(sErrorMessage, sExpectedMessage, "Error Message Validation");
		} else {
			String sErrorMessage = driver.findElement(loginError).getText();
			String sExpectedMessage = "Epic sadface: Username and password do not match any user in this service";
			PageClassManager.ValidateContent(sErrorMessage, sExpectedMessage, "Error Message Validation");
		}
	}
	
	//Method for Login 
	public void validUserLogin(String sUserName, String sPassword) {
		PageClassManager.logInfo(sUserName, "Validating "+sUserName+" login");
		driver.findElement(user).clear();
		driver.findElement(user).sendKeys(sUserName);
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(sPassword);
		driver.findElement(login).click();
	}
}