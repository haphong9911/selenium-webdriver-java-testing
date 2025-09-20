package webdriver;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Wait_PartV_Explicit {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String tranhFileName = "Tranh.png";
	String numberFileName = "Number.png";
	String treFileName = "Tre.png";

	String uploadFileFolderPath = projectPath + File.separator + "uploadFile" + File.separator;
	String tranhFilePath = uploadFileFolderPath + tranhFileName;
	String numberFilePath = uploadFileFolderPath + numberFileName;
	String treFilePath = uploadFileFolderPath + treFileName;

	// Wait rõ ràng với các điều kiện / các trạng thái cụ thể
	WebDriverWait explicitWait;

	By loadingIcon = By.cssSelector("div#loading");
	By hellowordText = By.cssSelector("div#finish>h4");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
	}

	public void TC_01_Invisible() {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.cssSelector("div#start>button")).click();

		// Loading icon biến mất sau 30s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));

		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

	public void TC_02_Visible() {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.cssSelector("div#start>button")).click();

		// Hellword icon biến mất sau 30s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(hellowordText));

		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

	public void TC_03_Count() {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.cssSelector("div#start>button")).click();

		// Helloword text element = 1
		explicitWait.until(ExpectedConditions.numberOfElementsToBe(hellowordText, 1));

		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

	public void TC_04_ajaxLoading() {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		// Wait cho date picker xuat hien
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));

		WebElement selectedDateText = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
		Assert.assertEquals(selectedDateText.getText(), "No Selected Dates to display.");

		// Click vao 1 ngay bat ky
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='2']"))).click();

		// Wait cho cái loading icon biến mất
		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar']>div.raDiv")));

		// Sau khi click vao ngay do thi element da duoc update lai -> neu dung lai
		// element o tren de find thi sai
		selectedDateText = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));

		// Verify ngày được updated
		Assert.assertEquals(selectedDateText.getText(), "Sunday, April 2, 2023");

		// Wait cho ngay duoc seletec thanh cong (visible)
		WebElement todaySeleted = explicitWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='2']")));

		// Verify ngày được chọn
		Assert.assertTrue(todaySeleted.isDisplayed());
	}

	@Test
	public void TC_05_uploadFiles() {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(90));

		driver.get("https://gofile.io/uploadFiles");

		By uploadFileBy = By.xpath("//input[@type='file']");

		// Loading file + upload
		driver.findElement(uploadFileBy).sendKeys(tranhFilePath);

		// Wait cho các file được upload thành công
		explicitWait.until(
				ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));

		// Wait cho cái text được visible
		WebElement uploadText = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//div[@class='col-auto text-center']//div[text()='Your files have been successfully uploaded']")));

		// Verify
		Assert.assertTrue(uploadText.isDisplayed());
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