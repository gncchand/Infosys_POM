package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login_Page {
	//properties
	public WebDriver driver;
	
	//locators - elements

	//By login=By.linkText("Login");
    By uid=By.id("username");
	By pwd=By.id("password");
	By submit=By.id("tp-sign-in");
	
	public By errmsg=By.xpath("//*[@id='tp-message-error']");
	
	//methods - constructor methods
	public Login_Page(WebDriver driver) throws InterruptedException 
	{
		this.driver=driver;
	    driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
	}
	
	//methods- operational methods
	public void uid_enter(String x)
	{
		driver.findElement(uid).sendKeys(x);
	}
	
	public void pwd_enter(String y)
	{
		
		driver.findElement(pwd).sendKeys(y);
	}
	
	public void click_Submit()
	{
		driver.findElement(submit).click();
	}	

		
}
