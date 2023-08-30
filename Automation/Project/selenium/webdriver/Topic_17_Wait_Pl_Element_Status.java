package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Pl_Element_Status {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver, 15); 
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		
		driver.get("https://www.facebook.com/");

	}

	@Test
	public void TC_01_Visible() {
		//Visible: Có trên UI + Có trong DOM
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());
	}

	@Test
	public void TC_02_Invisible_In_Dom() {
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		//Invisible: Không có trên UI, không có trong DOM
		//Kết quả như nhau nhưng thời gian chạy khác nhau
		//~1s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
		//Không hiển thị --> Pass -> 1s
		Assert.assertFalse(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());

	}
	
	@Test
	public void TC_02_Invisible_Not_In_Dom() {
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		//Invisible: Không có trên UI, không có trong DOM
		
		//15s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
		//Không hiển thị --> Failed -> 20s
		Assert.assertFalse(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());

	}
	
	@Test
	public void TC_03_Presence() {
		//Presence: Có trên UI + Có trong DOM -> Pass
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);

		//Presence: Không có trên UI, không có trong DOM
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));

	}
	
	@Test
	public void TC_04_Staleness() {
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);
		
		//Tại thời điểm này có trong DOM
		WebElement confirmationEmailAddressTextbox = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
		
		//Đóng Registration form lại 
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();

		//Wait cho  Confirmation Email Textbox khoong còn trong DOM
		explicitWait.until(ExpectedConditions.stalenessOf(confirmationEmailAddressTextbox));
		
	}
	
	public void sleepInSecond(long Second) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}