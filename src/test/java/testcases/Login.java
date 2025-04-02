package testcases;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Login {
	
	WebDriver driver;
	@BeforeMethod
	
	public void Setup()
	{
		driver =new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()=\'My Account\']")).click();
		driver.findElement(By.linkText("Login")).click();	
	}
	
	
	
	
	@Test(priority=1)
	public void Loginwithvalidcreds()
	{
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("bhargava372@gmail.com");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Bhargava#96");
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),"Edit your account Information is not displayed");
		
	}
	
	@Test(priority=2)
		public void LoginwithInvalidcreds()
		{
	
			driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("bhargava3398"+generateTimeStamp()+"@gmail.com");
			driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Bhargava96");
			driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();	
			
			//String ActualwarningMessage = driver.findElement(By.xpath("//div[contains(@class,\"alert-dismissible\")]")).getText();
			//String ExpectedwarningMessage="Warning: No match for E-Mail Address and/or Password.";
			//Assert.assertTrue(ActualwarningMessage.contains(ExpectedwarningMessage),"Expected Warning Message is not displayed");
			    //[or]
			String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
			String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
			Assert.assertEquals(actualWarningMessage, expectedWarningMessage, "Expected Warning Message is not displayed");

		}
	@Test(priority=3)
	 
	public void LoginwithInvalidEmailandValidPass()
	{
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("bhargava3398"+generateTimeStamp()+"@gmail.com");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Bhargava#96");
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage, "Expected Warning Message is not displayed");

	}
	
	@Test(priority=4)

	public void LoginwithValidEmailandInvalidPass()
	{
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("bhargava372@gmail.com");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Bhargava#1996");
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage, "Expected Warning Message is not displayed");

	}
	@Test(priority=5)
	
		public void Loginwithoutcreds()
		{
			driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("");
			driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("");
			driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
			
			String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
			String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
			Assert.assertEquals(actualWarningMessage, expectedWarningMessage, "Expected Warning Message is not displayed");

		}
	//1:24:10
	@AfterMethod
	
	public void TearDown()
	{
		driver.quit();
	}
	
			
		public	String generateTimeStamp()  //If you are testing user registration, you may need a unique email ID every time.
		//So Use a Timestamp to generate dynamic email IDs. To avoid "email already exists" error during registration.
			{
			// Create a Date object to capture the current time
				Date date =new Date();
				// Convert Date to String and clean it by replacing spaces and colons
					return date.toString().replace(" ","_").replace(":","_");
			}
			
			
			
	}



