package webdriver;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Wait_Part_II_FindElement_FindElements {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_Find_Element() {
		//Có duy nhất 1 element
		//Nếu như element xuất hiện ngay => trả vể element đó không cần chờ hết timeout
		//Nếu element chưa xuất hiện => Sau 0.5s tìm lại cho đến khi hết thời gian thì thôi
		//Trả về duy nhất 1 element
//		System.out.println("Start time = " + getCurrentTime());
//		driver.findElement(By.xpath("//input[@id='email']"));
//		System.out.println("End time = " + getCurrentTime());
		
		
		//Không có element nào hết
		//Nó sẽ tìm đi tìm lại cho đến khi hết timeout
		//Sau khi hết timeout thì đánh failed test case -> không chạy các step còn lại
		//Log ra exception (thông báo lỗi)
//		System.out.println("Start time = " + getCurrentTime());
//		driver.findElement(By.xpath("//input[@id='automation']"));
//		System.out.println("End time = " + getCurrentTime());
		
		//Có nhiều element
		System.out.println("Start time = " + getCurrentTime());
		driver.findElement(By.xpath("//div[@id='pageFooterChildren']//a[text()]")).click();
		System.out.println("End time = " + getCurrentTime());
		
	}

	@Test
	public void TC_02_Find_Elements() {
		int elementNumber = 0;
		//Có duy nhất 1 element
		
		//Không có element nào hết
		//Sẽ tìm đi tìm lại cho hết timeout
		//Nhưng khi hết timeout k đánh failed liền mà chạy tiếp các steps tiếp theo
		
		//Có nhiều element
		elementNumber = driver.findElements(By.xpath("//div[@id='pageFooterChildren']//a[text()]")).size();
		System.out.println("n element" + elementNumber);
		
		elementNumber = driver.findElements(By.xpath("//input[@id='email']")).size();
		System.out.println("n element" + elementNumber);
	}

	public void TC_03() {
		
	}
	
	public void sleepInSecond(long Second) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getCurrentTime() {
		Date date = new Date();
		return date.toString();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}