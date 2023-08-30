package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

public class Topic_06_Multiple_Server {
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
			
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
		
		@Parameters("server")
		@Test
		public void TC_01_Login_To_System(String serverName) throws InterruptedException{
			serverName = getServerURL(serverName);
			driver.get("http://"+serverName+"index.php/customer/login");
			driver.findElement(emailTextbox).sendKeys("selenium_11_01@gmail.com");
			driver.findElement(passwordTextbox).sendKeys("111111");
			driver.findElement(loginButton).click();
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));
			driver.findElement(By.xpath("//header[@id='header']/span[text()='Account']")).click();
			driver.findElement(By.xpath("//a[text()='Log Out']")).click();
			
			//Order
			//Add payment
			//Checkout
			//...

		}
		
		private String getServerURL(String serverName) {
				switch (serverName) {
				case "DEV": {
					serverName = "dev.techpanda.org/";
					break;

				}
				case "TESTING": {
					serverName = "test.techpanda.org/";
					break;

				}
				case "LIVE": {
					serverName = "live.techpanda.org/";
					break;
				}
				default:
					System.out.println("Server Name is not valid");
					break;
				}
			return serverName; 
		}
		
		@AfterClass
		public void afterClass() {
			driver.quit();
		}

}
