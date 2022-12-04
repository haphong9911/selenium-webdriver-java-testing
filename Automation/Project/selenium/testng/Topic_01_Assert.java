package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_01_Assert {
	WebDriver driver;
	
	@Test
	public void TC_01() {
		//3 hàm Assert hay dùng
		//Kiểm tra tính đúng đắn của dữ liệu
		
		//1 - Kiểm tra dữ liệu mình mong muốn là đúng
		//Email text box hiển thị
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
		//2 - Kiểm tra dữ liệu mình mong muốn là sai
		//Email text box khong hien thi
		Assert.assertFalse(driver.findElement(By.id("email")).isDisplayed());

		//3 - Kiểm tra dữ liệu mình mong muôn với dữ liệu thực tế là băngf nhau
		Assert.assertEquals(driver.findElement(By.id("email")).isDisplayed(),"abc");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),"abc");
		
		//Tương đối
		String	benefixText = driver.findElement(By.cssSelector("ul.benefits")).getText();
		Assert.assertTrue(benefixText.contains("Faster Checkout"));
		Assert.assertTrue(benefixText.contains("Save Checkout"));

	}

}
