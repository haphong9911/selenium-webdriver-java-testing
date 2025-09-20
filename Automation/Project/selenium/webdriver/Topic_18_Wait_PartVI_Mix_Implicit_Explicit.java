package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Wait_PartVI_Mix_Implicit_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
	}

	@Test
	public void TC_01_Element_Found() {
		driver.get("https://www.facebook.com/");

		By emailIdBy = By.id("email");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		;

		driver.findElement(emailIdBy).isDisplayed();

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIdBy));
	}

	@Test
	public void TC_02_Element_Not_Found_Only_Implicit() {
		driver.get("https://www.facebook.com/");

		By emailIdBy = By.id("email");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		;

		driver.findElement(emailIdBy).isDisplayed();

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIdBy));
	}

	@Test
	public void TC_03_Only_Explicit() {
		driver.get("https://www.facebook.com/");

		By emailIdBy = By.id("email");

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIdBy));
	}

	@Test
	public void TC_04_Element_Not_Found() {
		driver.get("https://www.facebook.com/");

		// 1 - Implicit < Explicit
		// 2 - Implicit = Explicit
		// 3 - Implicit > Explicit
		By emailIdBy = By.id("email");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		try {
			driver.findElement(emailIdBy).isDisplayed();
		} catch (Exception e) {

		}

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIdBy));
		} catch (Exception e) {

		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
