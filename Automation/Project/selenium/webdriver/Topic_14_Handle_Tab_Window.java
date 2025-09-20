package webdriver;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Handle_Tab_Window {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		;

	}

	public void TC_01() {
		// A
		driver.get("http://naruki.com/");
		String HomePageWindowId = driver.getWindowHandle();
		System.out.println("Tab A" + HomePageWindowId);
		// Click vào job link -> B
		driver.findElement(By.xpath("//a[@title='Search Jobs']")).click();
		sleepInSecond(3);

		switchToWindowByID(HomePageWindowId);

		// Sau khi switch qua
		System.out.println("Tab B" + driver.getCurrentUrl());

		// Hiện tại đang ở B
		driver.switchTo().window(HomePageWindowId);
	}

	public void TC_02_Naukri_Tab_Title() {
		driver.get("http://naruki.com/");
		driver.findElement(By.xpath("//a[@title='Search Jobs']")).click();
		sleepInSecond(3);
		System.out.println("naukri.com:" + driver.getCurrentUrl());

		switchToWindowByLink("browser-jobs");
	}

	@Test
	public void TC_03_Oxford_Dictionary_Tab_Title() {
		driver.get("https://dictionary.cambridge.org/vi");

		driver.findElement(By.xpath("//header[@id='header']//span[text()='Đăng nhập']")).click();
		sleepInSecond(3);

		switchToWindowByTitle("Login");

		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver
				.findElement(
						By.xpath("//form[@id='gigya-login-form']//input[@name='username']/following-sibling::span"))
				.getText(), "This field is required");
		Assert.assertEquals(driver
				.findElement(
						By.xpath("//form[@id='gigya-login-form']//input[@name='password']/following-sibling::span"))
				.getText(), "This field is required");

		driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='username']"))
				.sendKeys("automationfc.com@gmail.com");
		driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='password']"))
				.sendKeys("Automation000***");
		sleepInSecond(3);
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		System.out.println(driver.getTitle());
		// Business tự close và nhảy về trang trước đó
		// Switch về trang home
		switchToWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa'");

		// Verify login thành công
		Assert.assertEquals(driver.findElement(By.cssSelector("header#header span.cdo-username")).getText(),
				"Automation FC");

	}

	// Dùng được cho cả 2 tab/window hoặc nhiều hơn 2 cũng được
	public void switchToWindowByLink(String expectedRelativeLink) {
		// Lay all ID
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			// Switch vào trước rồi mới kiểm tra cái điều kiện sau
			driver.switchTo().window(id);

			// Lấy title của page đó ra
			String actualLink = driver.getCurrentUrl();
			if (actualLink.contains(expectedRelativeLink)) {
				break;
			}
		}
	}

	public void switchToWindowByTitle(String expectedTitle) {
		// Lay all ID
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			// Switch vào trước rồi mới kiểm tra cái điều kiện sau
			driver.switchTo().window(id);

			// Lấy title của page đó ra
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	// Viết hàm khi nào muốn dùng thì gọi nó ra
	public void switchToWindowByID(String currentWindowID) {
		// Lay all ID
		Set<String> allWindowIDs = driver.getWindowHandles();

		// Dùng vòng lặp để duyệt qua
		for (String id : allWindowIDs) {
			if (!id.equals(currentWindowID)) {
				// switch qua id cua tab do
				driver.switchTo().window(id);
			}
		}
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