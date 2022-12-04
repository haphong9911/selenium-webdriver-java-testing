package webdriver;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_03_Locator_Performation {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@Test
	public void TC_01_ID() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		//Find element = ID 10000 lần
		for (int i = 0; i < 1000; i++) {
			System.out.println("ID Lần thứ:" + i);
			driver.findElement(By.id("email"));
		}
		
		driver.quit();
	}
	@Test
	public void TC_02_Css() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		//Find element = ID 10000 lần
		for (int i = 0; i < 1000; i++) {
			System.out.println("Css Lần thứ:" + i);
			driver.findElement(By.cssSelector("input[id='email']"));
		}
		
		driver.quit();
	}
	@Test
	public void TC_03_Xpath() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		//Find element = ID 10000 lần
		for (int i = 0; i < 1000; i++) {
			System.out.println("Xpath Lần thứ:" + i);
			driver.findElement(By.xpath("//input[@id='email']"));
		}
		
		driver.quit();
	}
}