package PageClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProblemUserProduct {
	
	WebDriver driver;
	PageClassManager client;
	
	By productName = By.xpath("//div[@class='inventory_item_name']");
	By productDesc = By.xpath("//div[@class='inventory_details_name']");
	
	public ProblemUserProduct(PageClassManager client) {
		this.client = client;
		driver = client.pageClassdriver;
	}

	// Problem User product validation
	public void findHiddenProduct(String sProduct) throws Exception {
		Thread.sleep(1000);
		List<WebElement> lis = driver.findElements(productName);
		for(int i=1;i<=lis.size();i++) {
			driver.findElement(By.xpath("(//div[@class='inventory_item_name'])["+i+"]")).click();
			String sActProductName = driver.findElement(productDesc).getText();
			if(sActProductName.equals(sProduct)){
				System.out.println(sProduct+" Found");
				break;
				}
			else
				driver.findElement(By.xpath("//button[@class='inventory_details_back_button']")).click();
		}	
	}
}
