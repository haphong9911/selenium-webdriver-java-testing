package loop;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Loop {
	WebDriver driver;

	String loginPageUrl, userID, userPassword, email;

	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");

	@BeforeClass
	public void beforeMethod() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		loginPageUrl = "https://demo.guru99.com/v4/";
		email = "haphong" + getRandomNumber() + "@gmail.com";
		driver.get(loginPageUrl);
	}

	@Test(invocationCount = 3)
	public void Register_01() {

		driver.findElement(By.xpath("//a[text()='here']")).click();

		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		userPassword = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

		System.out.println(userID);
		System.out.println(userPassword);
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
