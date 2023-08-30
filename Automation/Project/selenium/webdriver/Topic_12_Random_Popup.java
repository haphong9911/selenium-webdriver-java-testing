package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Random_Popup {
	WebDriver driver;
	JavascriptExecutor jsExcutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_Random_In_DOM() {
		driver.get("https://vnk.edu.vn/");
		
		//Step2 - Luôn có element trong DOM dù có hiển thị hoặc không
		if (driver.findElement(By.cssSelector("div#tve_editor")).isDisplayed()) {
			driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
			
			//Expected pop-up không còn hiển thị nữa
			Assert.assertFalse(driver.findElement(By.cssSelector("div#tve_editor")).isDisplayed());
		}
		//Nếu như pop-up hiển thị -> Thao tác rồi close nó đi

		//Nếu như không hiển thị -> qua step tiếp theo lun
		
		//Step3 - Click vào trang liên hệ
		driver.findElement(By.xpath("//a[@title='Liên hệ']")).click();
		
		//Step4 - Verify qua trang liên hệ thành công
		Assert.assertTrue(driver.findElement(By.cssSelector("div.title-content>h1")).isDisplayed());

	}

	@Test
	public void TC_02_KMPlayer() {
		driver.get("http://www.kmplayer.com/");
		
		//Step2 - Luôn có element trong DOM dù có hiển thị hoặc không
		if (driver.findElement(By.cssSelector("div.pop-layer")).isDisplayed()) {
			
			//Nếu như pop-up hiển thị -> Thao tác rồi close nó đi
			//Enter/ submit/....
			jsExcutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("area#btn-r")));
			sleepInSecond(2);
			
			//Expected pop-up không còn hiển thị nữa
			Assert.assertFalse(driver.findElement(By.cssSelector("div.pop-layer")).isDisplayed());
		}

		//Nếu như không hiển thị -> qua step tiếp theo lun
				
		//Step3 - Click vào trang liên hệ
		driver.findElement(By.xpath("//a[text()='MOVIEBLOC ']")).click();
				
		//Step4 - Verify qua trang moi thành công
		Assert.assertTrue(driver.findElement(By.cssSelector("section.main-top-banner")).isDisplayed());

	}
	
	@Test
	public void TC_03_Random_Not_In_Dom() {
		driver.get("https://dehieu.vn/");
		
		sleepInSecond(10);
		
		List<WebElement> popupContent = driver.findElements(By.cssSelector("div.popup-content"));
		
		//Step2 - Luôn có element trong DOM dù có hiển thị hoặc không
		if (popupContent.size() > 0 ) {
			System.out.println("Case 1");
			//Nếu như pop-up hiển thị -> Thao tác rồi close nó đi
			//Enter/ submit/....
			driver.findElement(By.cssSelector("input#popup-name")).sendKeys("123");
			driver.findElement(By.cssSelector("input#popup-email")).sendKeys("123");
			driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("123");

			sleepInSecond(2);
			
			driver.findElement(By.cssSelector("button#close-popup")).click();
			
			//Không dùng cách này để verify không hiển thị nữa, vì khi close popup rồi thì popup không còn trong dom => Không thể find element
			//Expected pop-up không còn hiển thị nữa
			popupContent = driver.findElements(By.cssSelector("div.popup-content")); 
			Assert.assertEquals(popupContent.size(), 0);
		}else {
			System.out.println("Case 2 ");
		}

		//Nếu như không hiển thị -> qua step tiếp theo lun
				
		//Step3 - Click vào trang liên hệ
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
				
		//Step4 - Verify qua trang moi thành công
		Assert.assertTrue(driver.findElement(By.cssSelector("input#search-courses")).isDisplayed());

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
};