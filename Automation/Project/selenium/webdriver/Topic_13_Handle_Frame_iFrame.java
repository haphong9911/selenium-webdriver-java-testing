package webdriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Handle_Frame_iFrame {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		;
	}

	@Test
	public void TC_01_Kyna() {

		// A
		driver.get("https://skills.kynaenglish.vn/");

		// Switch vào frame/iframe trước rồi mới thao tác lên element thược frame/iframe
		// đó
		// A ->B
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'facebook.com/kyna.vn')]")));
		// Verify số lượng like trên fanpage facebook là 167k lượt like
		Assert.assertEquals(
				driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(),
				"165K likes");

		// B->A

		driver.switchTo().defaultContent();
		// Switch qua iframe
		// A->C
		driver.switchTo().frame("cs_chat_iframe");
		// Click vào chat để bật popup lên
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();

		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Phong");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0905777888");
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("Iframe");

		// C->A
		driver.switchTo().defaultContent();
		String keyword = "Excel";
		// A
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(keyword);
		driver.findElement(By.cssSelector("button.search-button")).click();

		// Verify course name vừa search chứa từ khóa
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		for (WebElement courseElement : courseName) {
			Assert.assertTrue(courseElement.getText().contains(keyword));
		}
	}

	@Test
	public void TC_02_Blog() {
		driver.get("https://www.automationfc.com/2020/02/18/training-online-automation-testing");

		// A->B
		driver.switchTo().frame("video-2679-1_youtube_iframe");

		// B
		driver.findElement(By.cssSelector("button.ytp-large-play-button")).click();
		sleepInSecond(5);

		// B -> A
		driver.switchTo().defaultContent();

		// A->C
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb-page iframe")));
		Assert.assertEquals(driver
				.findElement(By.xpath("//a[text()='Automation FC']/parent::div/following-sibling::div")).getText(),
				"3,242 likes");
	}

	public void TC_03_HDFC() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		// A->B
		driver.switchTo().frame("login_page");

		// B
		driver.findElement(By.name("fldLoginUserId")).sendKeys("Ha PHong");
		driver.findElement(By.cssSelector("a.login-btn")).click();

		// Verify
		Assert.assertTrue(driver.findElement(By.id("fldPasswordDispId")).isDisplayed());

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