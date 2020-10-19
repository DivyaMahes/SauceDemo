package PageClass;

import org.openqa.selenium.WebDriver;
import Utility.BrowserManager;

//Class which holds all page objects
public class PageClassManager extends BrowserManager {
	
	public LoginPage loginPage;
	
	WebDriver pageClassdriver = objWebDriver;
	
	public PageClassManager() {
		super();
		loginPage = new LoginPage(this);
	}
}
