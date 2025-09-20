package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Part_II_Register {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		;
		driver.manage().window().maximize();
	}

	@Test
	public void Register_01_Empty_Data() {
		// Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Tìm button
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Kiểm tra messeage lỗi ở các field bắt buộc
		driver.findElement(By.id("txtFirstname-error")).getText();

		// Kiểm tra điều kiện trả về là bằng vs điều kiện mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

	}

	@Test
	public void Register_02_Invalid_Email() {
		// Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Tran Ha Phong");
		driver.findElement(By.id("txtEmail")).sendKeys("Tran Ha Phong");
		driver.findElement(By.id("txtCEmail")).sendKeys("Tran Ha Phong");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0905735852");

		// Tìm button
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Kiểm tra điều kiện trả về là bằng vs điều kiện mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void Register_03_Incorrect_Confirm_Email() {
		// Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Tran Ha Phong");
		driver.findElement(By.id("txtEmail")).sendKeys("haphong702@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("haphong703@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0905735852");

		// Tìm button
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Kiểm tra điều kiện trả về là bằng vs điều kiện mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void Register_04_Password_Less_Than_6_Characters() {
		// Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Tran Ha Phong");
		driver.findElement(By.id("txtEmail")).sendKeys("haphong702@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("haphong702@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123");
		driver.findElement(By.id("txtCPassword")).sendKeys("123");
		driver.findElement(By.id("txtPhone")).sendKeys("0905735852");

		// Tìm button
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Kiểm tra điều kiện trả về là bằng vs điều kiện mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");
	}

	@Test
	public void Register_05_Incorrect_Confirm_Password() {
		// Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Tran Ha Phong");
		driver.findElement(By.id("txtEmail")).sendKeys("haphong702@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("haphong702@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("haphong123");
		driver.findElement(By.id("txtCPassword")).sendKeys("haphong124");
		driver.findElement(By.id("txtPhone")).sendKeys("0905735852");

		// Tìm button
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Kiểm tra điều kiện trả về là bằng vs điều kiện mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
	}

	@Test
	public void Register_06_Invalid_Phone_Number() {
		// Mở app ra
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Tran Ha Phong");
		driver.findElement(By.id("txtEmail")).sendKeys("haphong702@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("haphong702@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("haphong123");
		driver.findElement(By.id("txtCPassword")).sendKeys("haphong124");
		driver.findElement(By.id("txtPhone")).sendKeys("0905735");

		// Tìm button
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Kiểm tra điều kiện trả về là bằng vs điều kiện mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}