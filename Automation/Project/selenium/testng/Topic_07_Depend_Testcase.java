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

public class Topic_07_Depend_Testcase {
		WebDriver driver;
		String projectPath = System.getProperty("user.dir");
		By emailTextbox = By.xpath("//*[@id='email']");
		By passwordTextbox = By.xpath("//*[@id='pass']");
		By loginButton = By.xpath("//*[@id='send2']");
		
		
		@BeforeClass
		public void beforeClass(String browserName) {	
		
		}
		@Test()
		public void Product_01_Create() {
			Assert.assertTrue(false);
		}
		
		@Test(dependsOnMethods = "Product_01_Create")
		public void Product_02_View() {
			
		}
		
		@Test(dependsOnMethods = "Product_01_Create")
		public void Product_03_Edit() {
			
		}
		
		@Test(dependsOnMethods = {"Product_01_Create","Product_03_Edit"})
		public void Product_04_Delete() {
			
		}
		
		@AfterClass
		public void afterClass() {
			driver.quit();
		}

}
