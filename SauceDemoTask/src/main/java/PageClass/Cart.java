package PageClass;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class Cart {
	WebDriver driver;
	PageClassManager client;
	
	By addtocart = By.xpath("//button[contains(text(),'ADD TO CART')]");
	By carticon = By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']");
	By productName = By.xpath("//div[@class='inventory_item_name']");
	By productRemove = By.xpath("//button[contains(text(),'REMOVE')]");
	By continueShopping = By.xpath("//a[contains(text(),'Continue Shopping')]");
	By menu = By.xpath("//div[@class='bm-burger-button']");
	By allItems = By.xpath("//a[@id='inventory_sidebar_link']");
	
	public Cart(PageClassManager client) {
		this.client = client;
		driver = client.pageClassdriver;
	}

	//To add an item from Product Description page
	public void addToCart(String sProductName) {
		driver.findElement(addtocart).click();
		try {
			boolean status = driver.findElement(carticon).isDisplayed();
			if(status)
			{
				driver.findElement(carticon).click();
				String sActualProduct = driver.findElement(productName).getText();
				String sExpectedProduct = sProductName;
				if(sActualProduct.equals(sExpectedProduct))
					PageClassManager.ValidateStatus(true, "Product added to the cart");
				else
					PageClassManager.ValidateStatus(false, "Product not added to the cart");
			}
		}catch (NoSuchElementException e){
			PageClassManager.ValidateStatus(false, sProductName+" not added to the cart");
		}
	}
	
	//Remove an product added to the cart from Your Cart Page
	public void removeCart() {
		driver.findElement(productRemove).click();
	}
	
	//Continue shopping from Your Cart page
	public void continueShopping() {
		driver.findElement(continueShopping).click();
	}
	
	//Method to log out
	public void logOut() throws Exception {
		driver.findElement(menu).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).click();
	}
	
	//Method to navigate to main menu
	public void allItems() throws Exception {
		driver.findElement(menu).click();
		Thread.sleep(1000);
		driver.findElement(allItems).click();
	}
}
