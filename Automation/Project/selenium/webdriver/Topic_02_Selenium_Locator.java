package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	// Khai báo 1 biến driver đại diện cho selenium driver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// Set geckodrive: giao tiếp giữa driver và code
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");

		// Bật trình duyệt firefox
		driver = new FirefoxDriver();

		// Set thời gian đi tìm element
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		;

		// Bật browser to lên
		driver.manage().window().maximize();

		// Mở app ra
		driver.get("");
	}

	@Test
	public void TC_01() {
		// Selenium có 8 loại locator
		// Id
		driver.findElement(By.id("email")).sendKeys("haphong702@gmail.com");
		// Class
		driver.findElement(By.className("fb_logo")).isDisplayed();
		// Name
		driver.findElement(By.name("email"));

		// Driver đại diện cho thư viện của selenium
		// Tìm element để tương tác
		driver.findElement(By.name("name"));
		// Tagname tìm xem có bao nhiêu tag name
		driver.findElements(By.tagName("a"));
		// Link text
		driver.findElement(By.linkText("a"));
		// Partical link text
		driver.findElement(By.partialLinkText("a"));
		// Xpath
		driver.findElement(By.xpath("//input[@id='email']"));
		driver.findElement(By.xpath("//img[@class='fb_logo _8ilh img']"));
		driver.findElement(By.xpath("//img[@constains(@class,'fb_logo')]"));
		driver.findElement(By.xpath("//img[@start-with(@class,'fb_logo')]"));

		driver.findElement(By.xpath("//input[@name = 'email']"));
		driver.findElement(By.xpath("//name"));

		driver.findElement(By.xpath("//a[text() = 'Tiếng Việt']"));
		driver.findElement(By.xpath("//a[constain(text(),'Tiếng Việt')]"));

		// Css
		driver.findElement(By.cssSelector("input[id = 'email']"));
		driver.findElement(By.cssSelector("input#email"));
		driver.findElement(By.cssSelector("#email"));

		driver.findElement(By.cssSelector("img.fb_logo"));
		driver.findElement(By.cssSelector("img[class='fb_logo _8ilh img']"));
		driver.findElement(By.cssSelector(".fb_logo"));

		driver.findElement(By.cssSelector("input[name = 'email']"));
		driver.findElement(By.cssSelector("name"));

		driver.findElement(By.cssSelector("a[title='Vietnamese']"));
		driver.findElement(By.cssSelector("a[onClick*='vi_VN']"));

	}

	@Test
	public void TC_02() {

	}

	public void TC_03() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}