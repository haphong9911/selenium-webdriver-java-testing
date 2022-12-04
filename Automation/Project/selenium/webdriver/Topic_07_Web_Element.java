package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("");
	}

	/**
	 * 
	 */
	@Test
	public void TC_01_Define_Element() {
		//Muốn thao tác với Element thì phải tìm được element đó trước
		//Sau đó mới áp dụng hành vi
		
		//1- Tìm element
		//2- Với loại locator gì
		//3- Tương tác/ kiểm tra nó
		
		//Muốn thao tác trực tiếp thì không cần khai báo biến
		driver.findElement(By.id("send2"));
		
		//Thao tác từ 2 lần trở lên thì phải khai báo biến
		driver.findElement(By.id("email")).clear();;
		driver.findElement(By.id("email")).sendKeys("123");
		driver.findElement(By.id("email")).isDisplayed();
		
		WebElement emailTextbox = driver.findElement(By.id("email"));
		emailTextbox.clear();
		emailTextbox.sendKeys("");
		emailTextbox.isDisplayed();
		
	}

	@Test
	public void TC_02_Element_Method() {
		WebElement element = driver.findElement(By.id(""));
		
		//Xoá dữ liệu field cho phép nhập
		element.clear();
		
		//Nhập dữ liệu trong field cho phép nhập
		element.sendKeys("");
		element.sendKeys(Keys.ENTER);
		
		driver.findElement(By.className("footer")).findElement(By.cssSelector("a[title='My Account']"));
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
		
		
		//Khai báo biển để dùng nhiều lần
		String searchTextboxPlaceholderValue = driver.findElement(By.id("search")).getAttribute("placeholder");
		Assert.assertEquals(searchTextboxPlaceholderValue , "Search entire store here...");
		
		//GUI: Font/ Size/ Color/ Pixel
		element.getCssValue("background-color");//rgb(22,22,22)
		element.getCssValue("font-size");//13px
		element.getLocation();
		element.getRect();
		element.getSize();
		
		element.getScreenshotAs(OutputType.FILE);
		
		element.getTagName();
		
		element.getText();
		
		element.isDisplayed();
		
		element.isEnabled();
		
		//Mong muốn element được chọn hay chưa
		//Áp dụng: Check box/ Radio button/ Dropdown
		element.isSelected();
		
		element.submit();
		
		//Click vào 1 element
		//Áp dụng Button/ Link/ Icon/...
		element.click();
		
	}

	public void TC_03() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}