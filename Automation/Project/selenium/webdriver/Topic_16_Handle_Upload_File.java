package webdriver;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Handle_Upload_File {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String tranhFileName = "Tranh.png";
	String numberFileName= "Number.png";
	String treFileName = "Tre.png";
	
	//Window
	String uploadFileFolderPath = projectPath + File.separator +  "uploadFile" + File.separator;
	String tranhFilePath = uploadFileFolderPath + tranhFileName;
	String numberFilePath = uploadFileFolderPath + numberFileName;
	String treFilePath = uploadFileFolderPath + treFileName;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_One_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
//		//Cach 1 - khong nen dung
//		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
//		uploadFile.sendKeys(tranhFilePath);
//		uploadFile.sendKeys(treFilePath);
//		uploadFile.sendKeys(numberFilePath);
//		
		
		By uploadFileBy = By.xpath("//input[@type='file']");
		//Cach 2 
		//Selenium sendkeys method
		
		//Load file
		driver.findElement(uploadFileBy).sendKeys(tranhFilePath);
		sleepInSecond(3);
		
		driver.findElement(uploadFileBy).sendKeys(treFilePath);
		sleepInSecond(3);
		
		driver.findElement(uploadFileBy).sendKeys(numberFilePath);
		sleepInSecond(3);
		
		//Verify viec load file len success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + numberFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + treFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + tranhFileName + "']")).isDisplayed());

		//Click to upload at each file
		List<WebElement> uploadButton = driver.findElements(By.cssSelector("table button.start"));
		for(WebElement button : uploadButton) {
			button.click();
			sleepInSecond(3);
		}
		
		//Verify //p[@class='name']//a[@title='Quat.jpg']
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + numberFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + treFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + tranhFileName + "']")).isDisplayed());


	}

	@Test
	public void TC_02_Multiple_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		By uploadFileBy = By.xpath("//input[@type='file']");

		driver.findElement(uploadFileBy).sendKeys(tranhFilePath+ "\n" + treFilePath + "\n" + numberFilePath);
		sleepInSecond(3);
		
		//Verify viec load file len success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + numberFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + treFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + tranhFileName + "']")).isDisplayed());
		
		//Click to upload at each file
		List<WebElement> uploadButton = driver.findElements(By.cssSelector("table button.start"));
		for(WebElement button : uploadButton) {
			button.click();
			sleepInSecond(3);
		}
		
		//Verify //p[@class='name']//a[@title='Quat.jpg']
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + numberFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + treFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + tranhFileName + "']")).isDisplayed());

	
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

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

//4 Cách để upload file
//C1: Selenium sendkeys method (Trick):
//- Truyền đường dẫn của file vào thẻ input - tự load lên
//- Không cần bật cái Open File Dialog lên
//- Tối ưu nhất trong tất cả cách làm: Chạy được cho tất cả OS/Browser
//- Chạy được cho run test với UI hoặc none-UI (Headless mode)

//C2: Sử dụng autoIT (tool chỉ support cho window/OS_
//- Không dùng được cho Mac/Linux
//- Bật open file dialog lên -> chạy được
//- Các broswer trên Window, mỗi browswer có 1 script khác nhau

//C3: Sử dụng thư viện robot của java (Java robot)
//- Java chạy được trên tất cả OS
//- Đặc thù Open file dialog để dùng cho robot thì không chạy cho mac/linux được (Không có chỗ nhập đường dẫn

//C4: Sikuli
//- Java library  chạy được trên tất cả OS
//- Chạy được cho run test với UI hoặc none-UI (Headless mode)
//- Không ổn định
