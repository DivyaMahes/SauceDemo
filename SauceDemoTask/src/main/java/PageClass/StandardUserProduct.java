package PageClass;

import static org.testng.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import Utility.TestData;

public class StandardUserProduct {
	
	WebDriver driver;
	PageClassManager client;
	
	By product = By.xpath("//div[contains(text(),'Products')]");
	By menu = By.xpath("//div[@class='bm-burger-button']");
	By menuList = By.xpath("//nav[@class='bm-item-list']");
	By closeMenu = By.xpath("//button[contains(text(),'Close Menu')]");
	By productName = By.xpath("//div[@class='inventory_item_name']");
	By productDesc = By.xpath("//div[@class='inventory_details_name']");
	By sort = By.xpath("//select[@class='product_sort_container']");
	
	boolean ProductFound = false;
	
	public StandardUserProduct(PageClassManager client) {
		this.client = client;
		driver = client.pageClassdriver;
	}

	//Method to validate Title
	public void verifyStandardUser_ProductTitle() {
		String sActualTitle = TestData.standardUserPageTitle;
		String sExpectedTitle = driver.findElement(product).getText();
		PageClassManager.ValidateContent(sActualTitle, sExpectedTitle, "Product Title");
	}
	
	//Method to open Menu
	public void verifyHamburgerMenu() {
		driver.findElement(menu).click();
		boolean status = driver.findElement(menuList).isDisplayed();	
		PageClassManager.ValidateStatus(status, "Hamburger Menu - Sidebar is opened");
	}
	
	//Method to close Menu
	public void closeMenu() throws Exception {
		Thread.sleep(1000);
		driver.findElement(closeMenu).click();
	}
	
	//Method to check whether product exist and then select it
	public void selectProduct(String Product) {
		
		List<WebElement> lis = driver.findElements(productName);
		for(int i=1;i<=lis.size();i++) {
			String sActProductName = driver.findElement(By.xpath("(//div[@class='inventory_item_name'])["+i+"]")).getText();
			if(sActProductName.equals(Product)){
				ProductFound = true;
				driver.findElement(By.xpath("(//div[@class='inventory_item_name'])["+i+"]")).click();
				}
			}
		
		if(ProductFound)
			PageClassManager.ValidateStatus(true, "Product "+Product+" is found");
		else
			PageClassManager.ValidateStatus(false, "Product "+Product+" is not found");
	}
	
	//Method to validate Product Description
	public void validateProductDescription(String Product) {
		String sActualProductDes = driver.findElement(productDesc).getText();
		if(sActualProductDes.equals(Product))
			PageClassManager.ValidateStatus(true, "Product Description for "+Product+" Shown correctly");
		else
			PageClassManager.ValidateStatus(false, "Product Description not Shown correctly");
	}
	
	//Method to sort products
	public void sort(String sortOrder) throws Exception {
		Thread.sleep(1000);
		Select s=new Select(driver.findElement(sort));
		s.selectByVisibleText(sortOrder);
		
		ArrayList<String> obtainedList = new ArrayList<>(); 
		List<WebElement> elementList= driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
		for(WebElement we:elementList){
		   obtainedList.add(we.getText());
		}
		ArrayList<String> sortedList = new ArrayList<>();   
		for(String str:obtainedList){
		sortedList.add(str);
		}
		Collections.sort(sortedList);
		
		if(sortOrder.equals("Name (Z to A)"))
			Collections.reverse(sortedList);

		try {
		assertEquals(sortedList,obtainedList);
		PageClassManager.ValidateStatus(true, "Items sorted Successfully");
		} catch(AssertionError e)
		{
			PageClassManager.ValidateStatus(false, "Items not sorted");
		}	
	}
	
	//Method to validate whether the image is loaded
	public void imageCheck() {
		Object result = ((JavascriptExecutor) driver).executeScript(
				   "return arguments[0].complete && "+
				   "typeof arguments[0].naturalWidth != \"undefined\" && "+
				   "arguments[0].naturalWidth > 0", driver.findElement(By.xpath("//img[@class='inventory_item_img']")));

				    boolean loaded = false;
				    if (result instanceof Boolean) {
				      loaded = (Boolean) result;
				      if(loaded) 
				    	  PageClassManager.ValidateStatus(true, "Image loaded");
				      else
				    	  PageClassManager.ValidateStatus(false, "Image not loaded");
				    }
	}
}
