package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class Logout_Page {
	//properties
public WebDriver driver;
	
//locators - elements

	public By logout=By.linkText("Logout?");
		
	//methods - constructor methods
	public Logout_Page(WebDriver driver) throws InterruptedException
	{
		this.driver=driver;
		Thread.sleep(3000);
	
	}
	//methods- operational methods
	public void click_Logout()
	{
		driver.findElement(logout).click();
	}
	
}
