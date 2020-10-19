package Utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Reports.ExtentReportManager;

public class BrowserManager extends ExtentReportManager {
	public WebDriver objWebDriver =null;
	String sProjectPath=System.getProperty("user.dir");
	
	protected BrowserManager(){
		super();
		initiateDriver("Chrome");
	}
	
	//Initialize the webdriver
	public void initiateDriver(String sBrowser) {
		switch(sBrowser) {
			case "Chrome":
				System.setProperty("webdriver.chrome.driver", sProjectPath+"/Driver/chromedriver.exe");
				objWebDriver = new ChromeDriver();
				logInfo("Browser Launch","Opening Chrome browser");	
				break;
		}
		objWebDriver.manage().window().maximize();
		objWebDriver.manage().timeouts().pageLoadTimeout(1000, TimeUnit.SECONDS);
	}
 }
