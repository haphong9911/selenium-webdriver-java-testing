package webdriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Wait_PartVII_Page_Ready {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	@Test
	public void TC_01_OrangeHRM_Implicit() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		;
		driver.get("https://api.orangehrm.com/#api-_");

		Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(),
				"OrangeHRM REST API Documentation");
	}

	@Test
	public void TC_02_OrangeHRM_Explicit() {
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Wait cho cái spinner invisible
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner")));

		Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(),
				"OrangeHRM REST API Documentation");
	}

	@Test
	public void TC_03_OrangeHRM_PageReady() {
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

		// Wait cho page load success
		Assert.assertTrue(isPageLoadedSuccess());

		Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(),
				"OrangeHRM REST API Documentation");
	}

	@Test
	public void TC_04_OrangeHRM_API_PageReady() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		;

		driver.findElement(By.id("txtUserName")).sendKeys("");
		driver.findElement(By.id("txtPassword")).sendKeys("");
		driver.findElement(By.id("btnLogin")).click();

		// Cách 1 Wait page ready
		Assert.assertTrue(isPageLoadedSuccess());
		Assert.assertTrue(driver.findElement(By.cssSelector("div#div_graph_display_emp_distribution")).isDisplayed());

		// Cách 2 Wait cho loading icon biến mất
		// explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loadmask")));

	}

	public void TC_05_TestProject_PageReady() {
		driver.get("https://blog.testproject.io/");

		// Handle popup
		if (driver.findElement(By.cssSelector("div#mailch-bg")).isDisplayed()) {
			driver.findElement(By.cssSelector("div#close-mailch")).click();
		}

		driver.findElement(By.cssSelector("")).sendKeys("");
		driver.findElement(By.cssSelector("")).click();

		// Hover mouse
		action.moveToElement(driver.findElement(By.cssSelector(""))).perform();

		List<WebElement> firstAllPostTitle = driver.findElements(By.cssSelector("h3.post-title>a"));
		for (WebElement postTitle : firstAllPostTitle) {
			Assert.assertTrue(postTitle.getText().contains("Selenium"));

		}
	}

	public boolean isPageLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	// Only JQuery
	public boolean isJQueryLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	// JQuery + Javascript
	public boolean isPageLoadedSuccess1() {

		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	// JQuery + Element
	public boolean isJQueryAndAjaxIconLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
					// TODO: handle exception
				}
			}
		};

		ExpectedCondition<Boolean> ajaxIconLoading = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return $('div.spinner').hidden").toString().equals("undefined");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(ajaxIconLoading);
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