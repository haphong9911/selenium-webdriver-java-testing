package webdriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath {
	// Khai báo 1 biến driver đại diện cho selenium driver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// Set geckodrive: giao tiếp giữa driver và code
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");

		// Bật trình duyệt firefox
		driver = new FirefoxDriver();

		// Set thời gian đi tìm element, áp dụng cho việc tìm element
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		;

		// Bật browser to lên
		driver.manage().window().maximize();

		// Mở app ra
		driver.get("facebook.com");
	}

	@Test
	public void TC_01() {
		// Tìm (Find) - số ít, trả về 1 cái
		// Thao tác trực tiếp, không khai báo biến - sử dụng 1 lần, không sử dụng lại
		// element này
		driver.findElement(By.id("")).click();

		// Khai báo biến - có thể dùng lại element nhiều lần
		WebElement loginButton = driver.findElement(By.id(""));
		loginButton.click();
		loginButton.isDisplayed();
		// Tìm (Find) - số nhiều, trả về >= 1 cái
		List<WebElement> loginCheckboxes = driver.findElements(By.id(""));

		// Thao tác (Action): click/type/select/hover/...

		// Kiểm tra (Verify / Assert): getText / getAttribute / getCss/..

		// Thao tác với email textbox

		// Thao tác với password textbox

		// Thao tác Login
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