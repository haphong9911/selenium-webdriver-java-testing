package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Handle_Pop_Up {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_NgoaiNgu24h_WebEleMent() {
		driver.get("https://ngoaingu24h.vn/");
		
		By loginPopUpBy = By.cssSelector("div#modal-login-v1");
		
		WebElement loginPopUp = driver.findElement(By.cssSelector("div#modal-login-v1"));
		
		//Verify displayed
		Assert.assertFalse(loginPopUp.isDisplayed());
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("button.login_")).click();
		
		//Verify displayed
		Assert.assertTrue(driver.findElement(loginPopUpBy).isDisplayed());
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationtest");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationtest");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		
		//Verify displayed
		Assert.assertFalse(loginPopUp.isDisplayed());
		sleepInSecond(2);
	}

	@Test
	public void TC_02_NgoaiNgu24h_By() {
		driver.get("https://ngoaingu24h.vn/");
		
		By loginPopUpBy = By.cssSelector("div#modal-login-v1");
		
		WebElement loginPopUp = driver.findElement(By.cssSelector("div#modal-login-v1"));
		
		//Verify displayed
		Assert.assertFalse(driver.findElement(loginPopUpBy).isDisplayed());
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("button.login_")).click();
		
		//Verify displayed
		Assert.assertTrue(driver.findElement(loginPopUpBy).isDisplayed());
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationtest");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationtest");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		
		//Verify displayed
		Assert.assertFalse(driver.findElement(loginPopUpBy).isDisplayed());
		sleepInSecond(2);

	}

	public void TC_03_JTExpress() {
		driver.get("https://jtexpress.vn/vi");
		
		By HomeSlidePopup = By.cssSelector("div#home-modal-slider");
		
		//Verify popup	is displayed
		Assert.assertTrue(driver.findElement(HomeSlidePopup).isDisplayed());
		
		driver.findElement(By.cssSelector("button.close")).click();
		sleepInSecond(2);
		
		Assert.assertFalse(driver.findElement(HomeSlidePopup).isDisplayed());
		
		String billCode = "8400005984444";
		
		driver.findElement(By.name("billCode")).sendKeys(billCode);
		driver.findElement(By.xpath("//form[@id='formTrack']//button[text()='Tra cứu vận đơn'")).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h5")).getText().contains("billCode"));
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