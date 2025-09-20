package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Browser {
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
	public void TC_01_Method() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		;

		driver.close();
		driver.quit();

		// Get ur;
		driver.get("");

		// ********* Wait **********
		// Tìm 1 Element
		driver.findElement(By.id(""));

		// Tìm nhiều Element
		driver.findElements(By.id(""));

		// Get URL của page hiện tại
		driver.getCurrentUrl();

		// Lấy ra source code của page hiện tại
		driver.getPageSource();

		// Lấy title của page
		driver.getTitle();

		// *********** Windows / Tab ***********
		// Dùng để xử lý window/tab - Lấy id của tab đang active
		driver.getWindowHandle();

		// Dùng để xử lý window/tab - Lấy id của tất cả tab đang active
		driver.getWindowHandles();

		// *********** Framwork - Cookies ***********
		// Cookie: lưu lại phiên đăng nhập/ session/ dữ liệu duyệt web/....
		driver.manage().deleteAllCookies();
		;

		// *********** Logs ***********
		// driver.manage().logs().get(logType);

		// Chờ 1 elemen
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		;

		// Chờ load 1 pahe
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

		// ***************** Java script executor ********************
		// Chờ 1 đoạn script được thực thi
		// asynchronous - bất đồng bộ
		// synchronous - đồng bộ
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

		driver.manage().window().fullscreen();
		driver.manage().window().maximize();

		driver.manage().window().setPosition(new Point(100, 250));
		driver.manage().window().getPosition();

		driver.manage().window().setSize(new Dimension(1920, 1000));
		driver.manage().window().getSize();

		// Tracking history tốt hơn
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("");

		// ******** Alert ********
		driver.switchTo().alert();

		// ********* Frame / Iframe *************
		driver.switchTo().frame(0);

		// ********* Windows / Tabs ************
		driver.switchTo().window("");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}