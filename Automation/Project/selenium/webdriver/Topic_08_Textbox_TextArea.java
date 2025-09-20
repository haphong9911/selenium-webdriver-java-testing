package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Textbox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, editFirstName, editLastName;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();

		firstName = "Luis";
		lastName = "Suarez";
		editFirstName = "Steven";
		editLastName = "Gerrard";

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		;
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Add_Employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		// Textbox
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");

		// button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(5);

		// At dashboard page: 'Add Employee' sub-menu is not displayed
		Assert.assertFalse(driver.findElement(By.cssSelector("a#menu_pim_addEmployee")).isDisplayed());

		// Open "Add Employee" screen
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee");
		driver.findElement(By.name("firstname")).sendKeys(firstName);
		driver.findElement(By.name("lastname")).sendKeys(lastName);

		// At employee page: 'Add Employee' sub-menu is displayed
		Assert.assertTrue(driver.findElement(By.cssSelector("a#menu_pim_addEmployee")).isDisplayed());

		String employeeId = driver.findElement(By.id("employeeId")).getAttribute("value");

		// Click save
		driver.findElement(By.id("btnSave")).click();
		sleepInSecond(3);

		By firstNameTextBoxBy = By.id("personal_txtEmpFirstName");
		By lastNameTextBoxBy = By.id("personal_txtEmpFirstName");
		By employeeIDTextBoxBy = By.id("personal_txtEmpFirstName");

		// Verify field firstname,lastname,id,text box are disabled
		Assert.assertFalse(driver.findElement(firstNameTextBoxBy).isEnabled());
		Assert.assertFalse(driver.findElement(lastNameTextBoxBy).isEnabled());
		Assert.assertFalse(driver.findElement(employeeIDTextBoxBy).isEnabled());

		// Verify field firstname,lastname,id,text box value matching with input value
		Assert.assertEquals(driver.findElement(firstNameTextBoxBy).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameTextBoxBy).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(employeeIDTextBoxBy).getAttribute("value"), employeeId);

		// Click edit button
		driver.findElement(By.cssSelector("input#btnSave")).click();

		// Nhập giá trị mới
		driver.findElement(firstNameTextBoxBy).sendKeys(editFirstName);
		driver.findElement(lastNameTextBoxBy).sendKeys(editLastName);

		// Click save
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInSecond(3);

		// Verify field firstname,lastname,id,text box are disabled
		Assert.assertFalse(driver.findElement(firstNameTextBoxBy).isEnabled());
		Assert.assertFalse(driver.findElement(lastNameTextBoxBy).isEnabled());
		Assert.assertFalse(driver.findElement(employeeIDTextBoxBy).isEnabled());

		// Verify field firstname,lastname,id,text box value matching with input value
		Assert.assertEquals(driver.findElement(firstNameTextBoxBy).getAttribute("value"), editFirstName);
		Assert.assertEquals(driver.findElement(lastNameTextBoxBy).getAttribute("value"), editLastName);

		// Click to 'Imigration' tab
		driver.findElement(By.xpath("//a[@text='Immigration']")).click();
		driver.findElement(By.cssSelector("input#btnAdd")).click();

		// Input to immgration number textbox
		driver.findElement(By.id("immigration_number")).sendKeys("123-456-789");
		driver.findElement(By.cssSelector("textarea#immigration_comments")).sendKeys("Steven's\nGerrard\nPassport\nID");
		sleepInSecond(5);

		driver.findElement(By.id("btnSave")).click();
		sleepInSecond(5);

		driver.findElement(By.xpath("//td[@class='document']/a[@text='Passport']")).click();

		// verify
		Assert.assertEquals(driver.findElement(By.id("immigration_number")).getAttribute("value"), "123-456-789");
	}

	@Test
	public void TC_02() {

	}

	public void TC_03() {

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
}