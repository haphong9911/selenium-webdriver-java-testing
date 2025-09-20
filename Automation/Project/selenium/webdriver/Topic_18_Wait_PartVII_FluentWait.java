package webdriver;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_18_Wait_PartVII_FluentWait {
	WebDriver driver;
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentWait;
	FluentWait<WebElement> fluentElement;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	public void TC_01_ViDu() {

		fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.withTimeout(Duration.ofSeconds(15))
				// Tần số mỗi 1s check 1 lần
				.pollingEvery(Duration.ofSeconds(1))
				// Nếu gặp exception là find không thấy sẽ bỏ qua
				.ignoring(NoSuchElementException.class).until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver driver) {
						return driver.findElement(By.xpath("//input[@name='btnI-fail']"));
					}
				});

		WebElement loginButton = driver.findElement(By.xpath(""));

		fluentElement = new FluentWait<WebElement>(loginButton);

		// Setting time
		fluentElement.withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(20))
				.ignoring(NoSuchElementException.class, TimeoutException.class);

		// Apply dieu kien
		String loginButtonText = fluentElement.until(new Function<WebElement, String>() {
			public String apply(WebElement element) {
				return element.getText();
			}
		});

		Assert.assertEquals(loginButtonText, "");

		// Apply dieu kien tra ve boolean
		Boolean loginButtonStatus = fluentElement.until(new Function<WebElement, Boolean>() {
			public Boolean apply(WebElement element) {
				return element.isDisplayed();
			}
		});

		Assert.assertEquals(loginButtonStatus, "");
	}

	@Test
	public void TC_02() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		fluentWait = new FluentWait<WebDriver>(driver);

		driver.findElement(By.cssSelector("div#start>button")).click();

		// Sau khi bấm thì loading icon xuất hiện và mất đi sau 5s
		// Icon loading biến mất -> Hello word xuất hiện

		fluentWait.withTimeout(Duration.ofSeconds(6)).pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class).until(new Function<WebDriver, Boolean>() {

					@Override
					public Boolean apply(WebDriver driver) {
						boolean text = driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed();
						System.out.println(text);
						return driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed();
					}
				});
	}

	public void TC_03() {

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