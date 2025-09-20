package webdriver;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Default_DropDown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	JavascriptExecutor jsExcutor;
	WebDriverWait expliciWait;
	Actions action;
	Select select;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();

		// Khởi tạo sau khi driver được sinh ra
		// JavascriptExecutor /WebdriverWait/ Actions/...
		jsExcutor = (JavascriptExecutor) driver;
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		action = new Actions(driver);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		;

	}

	public void TC_01_Rode() {
		driver.get("https://rode.com/en/support/where-to-buy");

		// Khởi tạo khi sử dụng (element xuất hiện)
		// Khởi tạo select để thao tác vs element (contry dropdown)
		select = new Select(driver.findElement(By.xpath("//select[@id='where_country']")));

		// Không support multiple select
		Assert.assertFalse(select.isMultiple());

		// Select VietNam in dropdown
		select.selectByVisibleText("Vietnam");

		// Check giá trị là Vietnam
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");

		// Click button search
		driver.findElement(By.cssSelector("button.btn.btn-default")).click();
		sleepInSecond(5);

		// Verify so ket qua tra ve
		Assert.assertEquals(driver.findElements(By.cssSelector("div.result_count>span")), "32");

		List<WebElement> storeName = driver.findElements(By.cssSelector("div#search_result div.store_name"));
		Assert.assertEquals(storeName.size(), "32");

		for (WebElement store : storeName) {
			System.out.println(store.getText());
		}
	}

	@Test
	public void TC_02_NopCommerce() {
		String firstName = "Hong";
		String lastName = "Honsu";
		String date = "27";
		String month = "February";
		String year = "1999";
		String emailAddress = "hongphong" + getRandomNumber() + "@gmail.com";
		String password = "honsu270899";

		driver.get("https://demo.nopcommerce.com/register");

		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);

		// Date
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(date);

		// Month
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);

		// Year
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);

		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

		driver.findElement(By.className("ico-account")).click();

		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);

		// Date
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);

		// Month
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

		// Year
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);

		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long Second) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}