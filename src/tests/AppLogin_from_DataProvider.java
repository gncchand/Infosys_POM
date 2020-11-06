package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.Login_Page;
import pages.Logout_Page;

/*Page Object model using
1.By (Not using @FindBy and PageFactory) 
2.Extent Reports
3.Collections(ArrayList)
4.Capture Screenshot*/

public class AppLogin_from_DataProvider 
{
public static void main(String arr[]) throws Exception
{
	
	ExtentReports er=new ExtentReports("logintest.html",false);
	ExtentTest et=er.startTest("login validation", "positive and negative test for login");
	 ChromeDriver driver; 
	 Login_Page lin;
  	 Logout_Page lout;

	ArrayList <String> uid = new ArrayList<String> (Arrays.asList("naveengaddipatis@gmail.com","test@yahoo.com","")); 
	ArrayList <String> pwd = new ArrayList<String> (Arrays.asList("Auto@123","pass123","kkk")); 
	ArrayList <String> uc = new ArrayList<String> (Arrays.asList("valid","invalid","valid")); 
	

	for(int i=0;i<3;i++)
	 {
		  driver=new ChromeDriver();
		  driver.get("https://auth.testproject.io/auth/realms/TP/protocol/openid-connect/auth?client_id=Blog&scope=openid&redirect_uri=https://blog.testproject.io&response_type=code&state=12e50a1a4100e0f98db768071eb802ad_VGVzdFByb2plY3Q7aHR0cHM6Ly9ibG9nLnRlc3Rwcm9qZWN0Lmlv");
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
				
		  lin=new Login_Page(driver);
			 Thread.sleep(3000);
			  
			   lin.uid_enter(uid.get(i));  
			   lin.pwd_enter(pwd.get(i));
			   Thread.sleep(3000);	 
			   
			 lin.click_Submit();
			
			  Thread.sleep(3000);  
			  
			  lout=new Logout_Page(driver); 
			  try {	   
				  if(uc.get(i).equalsIgnoreCase("valid") && uid.get(i)=="" && driver.findElement(lin.errmsg).isDisplayed()) 
				  {
					  String fname1=scrnsht(driver);
					   et.log(LogStatus.PASS, "blank login test passed"+et.addScreenCapture(fname1));
					   Thread.sleep(3000);
					   }
				  else  if(uc.get(i).equalsIgnoreCase("valid") && driver.findElement(lout.logout).isDisplayed()) 
				  {
					  String fname2=scrnsht(driver);
					   et.log(LogStatus.PASS, "positive login test passed"+et.addScreenCapture(fname2));
					   Thread.sleep(3000);
					   lout.click_Logout();
				  }
		  		  
				  else if(uc.get(i).equalsIgnoreCase("invalid") && driver.findElement(lin.errmsg).isDisplayed())
			   {
					  String fname3=scrnsht(driver);
				   et.log(LogStatus.PASS, "negative login test passed"+et.addScreenCapture(fname3));
				   Thread.sleep(3000);
			   }
			   		   else 
				  {
			   			String fname4=scrnsht(driver);
			   			et.log(LogStatus.FAIL, " login test failed"+et.addScreenCapture(fname4));
				  }
			   }//try end
			  catch(Exception e)
			   {
				   System.out.println("Error message : "+e.getMessage());
			   }//catch end
			
			  	er.endTest(et);
			  	er.flush();
			  	
				driver.close();
	 	}//for end
	
	
}// main end

public static String scrnsht(ChromeDriver driver) throws IOException 
{
	SimpleDateFormat sf=new SimpleDateFormat("dd-MM-yy-hh-mm-ss");
	Date d =new Date();
	String fname=sf.format(d)+".png";
	
	File src=driver.getScreenshotAs(OutputType.FILE);
	File dest=new File(fname);
	
	FileHandler.copy(src, dest);
	
	return(fname);
	
}


}