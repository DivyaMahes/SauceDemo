package PageClass;

import org.openqa.selenium.WebDriver;
import Utility.BrowserManager;

//Class which holds all page objects
public class PageClassManager extends BrowserManager {
	
	public LoginPage loginPage;
	public StandardUserProduct standardUser;
	public ProblemUserProduct problemUser;
	public Cart cart;
	
	WebDriver pageClassdriver = objWebDriver;
	
	public PageClassManager() {
		super();
		loginPage = new LoginPage(this);
		standardUser = new StandardUserProduct(this);	
		cart = new Cart(this);
		problemUser = new ProblemUserProduct(this);
	}
}
