package testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_05_Multiple_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");

	@Parameters("browser")

	@BeforeClass
	public void beforeClass(String browserName) {
		switch (browserName) {
		case "chrome": {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
			driver = new ChromeDriver();
			break;

		}
		case "firefox": {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
			driver = new FirefoxDriver();
			break;

		}
		case "edge": {
			System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver.exe");
			driver = new EdgeDriver();
			break;
		}
		default:
			System.out.println("Browser Name is not valid");
			break;
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	@Test
	public void TC_01_Login_To_System(String username, String password) throws InterruptedException {
		driver.get("http://live.techpanda.org/index.php/customer/login");

		driver.findElement(emailTextbox).sendKeys("selenium_11_01@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("111111");
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));
		driver.findElement(By.xpath("//header[@id='header']/span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();

		// Order
		// Add payment
		// Checkout
		// ...

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
