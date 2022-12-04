package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Part_III_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String emailAddress ;
	String fullname;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		emailAddress = "haphong" + generateRandomNumber() + "@gmail.com";
	}

	@Test
	public void Login_01_Empty_Data() {
		//Mở app ra
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.cssSelector("div.footer a[title = 'My Account']")).click();
		
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.name("login[password]")).clear();
		driver.findElement(By.cssSelector("button[title='Login']")).click();
		
		
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),"This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),"This is a required field.");
		
	}

	@Test
	public void Login_02_Invalid_Email() {
		//Mở app ra
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.cssSelector("div.footer a[title = 'My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("123@345");
		driver.findElement(By.name("login[password]")).sendKeys("123456");
		driver.findElement(By.cssSelector("button[title='Login']")).click();
		
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test
	public void Login_03_Invalid_Password() {
		//Mở app ra
		driver.get("http://live.techpanda.org/");
		
driver.findElement(By.cssSelector("div.footer a[title = 'My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("haphong@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("12312");
		driver.findElement(By.cssSelector("button[title='Login']")).click();
		driver.findElement(By.cssSelector("p.required")).click();
		
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
	
	}
	
	@Test
	public void Login_04_Incorrect_Email_Password() {
		//Mở app ra
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.cssSelector("div.footer a[title = 'My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123123123");
		driver.findElement(By.cssSelector("button[title='Login']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),"Invalid login or password.");
	
	}
	
	@Test
	public void Login_05_Create_New_Account() {
		//Mở app ra
		driver.get("http://live.techpanda.org/");
		
		
		driver.findElement(By.cssSelector("div.footer a[title = 'My Account']")).click();
		
		driver.findElement(By.xpath("//a[@class='button']")).click();
		
		driver.findElement(By.name("firstname")).sendKeys("Tran");
		driver.findElement(By.name("lastname")).sendKeys("Phong");
		driver.findElement(By.name("email")).sendKeys(emailAddress);
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.name("confirmation")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("input#is_subscribed")).click();
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Thank you for registering with Main Website Store.");

		
		
				
	
	}

	@Test
	public void Login_06_Login_Valid_Email_Password() {
		//Mở app ra
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.cssSelector("div.footer a[title = 'My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("haphong123@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123456");
				
	
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);
		
	}
}