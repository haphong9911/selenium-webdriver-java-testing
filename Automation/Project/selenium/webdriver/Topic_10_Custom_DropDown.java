package webdriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Custom_DropDown {
	WebDriver driver;
	JavascriptExecutor jsExcutor;
	WebDriverWait expliciWait;

	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();

		// Wait cho các trạng thái của element: visible/ presence/ invisible/ staleness
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(15));

		// Ép kiểu tường minh
		jsExcutor = (JavascriptExecutor) driver;

		// Wait cho việc tìm element
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		;

	}

	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "5");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),
				"5");

		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "15");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),
				"15");

		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "19");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),
				"19");

		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "3");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),
				"3");

	}

	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectItemInCustomDropdownList("i.dropdown", "div.item span.text", "Jenny Hess");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");

		selectItemInCustomDropdownList("i.dropdown", "div.item span.text", "Justen Kitsune");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Justen Kitsune");

		selectItemInCustomDropdownList("i.dropdown", "div.item span.text", "Matt");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");

	}

	public void TC_03_VueJs() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		selectItemInCustomDropdownList("div.btn-group", "ul.dropdown-menu a", "First Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");

		selectItemInCustomDropdownList("div.btn-group", "ul.dropdown-menu a", "Third Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");

		selectItemInCustomDropdownList("div.btn-group", "ul.dropdown-menu a", "Second Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
	}

	public void TC_04_Angular_Select() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(2);

		selectItemInCustomDropdownList("ng-select[bindvalue='provinceCode'] span.ng-arrow-wrapper",
				"div[role='option']>span.ng-option-label", "Thành phố Hà Nội");
		// cachs 1 text không nằm trong HTML
		// String actualText = (String) jsExcutor.executeScript("return
		// document.querySelector(\"ng-select[bindvalue='provinceeCode']
		// span.ng-value-label\").innerText");
		// Assert.assertEquals(actualText, "Thành phố Hà Nội");

		// Cách 2
		Assert.assertEquals(
				driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getText(),
				"Thành phố Hà Nội");

		// Cách 3
		Assert.assertEquals(
				driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label"))
						.getAttribute("innerText"),
				"Thành phố Hà Nội");

		selectItemInCustomDropdownList("ng-select[bindvalue='districtCode'] span.ng-arrow-wrapper",
				"div[role='option']>span.ng-option-label", "Quận Đống Đa");

		selectItemInCustomDropdownList("ng-select[bindvalue='wardCode'] span.ng-arrow-wrapper",
				"div[role='option']>span.ng-option-label", "Phường Ô Chợ Dừa");

	}

	@Test
	public void TC_04_Angular_Enter() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(2);

		enterToCustomDropdownList("ng-select[bindvalue='provinceCode'] input[role='combobox']",
				"div[role='option']>span.ng-option-label", "Thành phố Hà Nội");
		enterToCustomDropdownList("ng-select[bindvalue='districtCode'] input[role='combobox']",
				"div[role='option']>span.ng-option-label", "Quận Đống Đa");
		enterToCustomDropdownList("ng-select[bindvalue='wardCode'] input[role='combobox']",
				"div[role='option']>span.ng-option-label", "Phường Ô Chợ Dừa");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void selectItemInCustomDropdownList(String parentLocator, String childLocator, String expectedTextItem) {
		// Step1: Click vào 1 element cho xổ ra hết
		driver.findElement(By.cssSelector(parentLocator)).click();
		sleepInSecond(2);

		// Step2: Chờ cho các item load hết thành công
		// Lưu ý 1: Locator chứa tất cả item
		// Lưu ý 2: Locator đến note cuối cùng chứa text
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

		// Step3: Tìm item cần chọn
		// tìm

		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
		// Lấy tất cả item ra, sau đó duyệt qua từng item, get text của item ra

		for (WebElement item : allItems) {
			String actualText = item.getText();
			System.out.println("Actual Text = " + actualText);
			// Nếu text = item mong muôn => Click
			if (actualText.equals(expectedTextItem)) {
				// B1: Nếu item cần chọn nằm trong vùng nhìn thấy => Ko cần scroll
				// B2: Nếu item cần chọn không nằm trong vùng nhìn thấy => scroll chuột xuống để
				jsExcutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(3);

				// Step4: Click vào item đó
				item.click();
				sleepInSecond(2);
				// Thoát khỏi vòng lập - không có kiểm tra vòng lập tiếp theo
				break;
			}
		}
	}

	public void enterToCustomDropdownList(String parentLocator, String childLocator, String expectedTextItem) {
		// Step1: Tìm được ô input để sendkeys vào
		driver.findElement(By.cssSelector(parentLocator)).sendKeys(expectedTextItem);
		sleepInSecond(2);

		// Step2: Chờ cho các item load hết thành công
		// Lưu ý 1: Locator chứa tất cả item
		// Lưu ý 2: Locator đến note cuối cùng chứa text
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

		// Step3: Tìm item cần chọn
		// tìm

		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
		// Lấy tất cả item ra, sau đó duyệt qua từng item, get text của item ra

		for (WebElement item : allItems) {
			String actualText = item.getText();
			System.out.println("Actual Text = " + actualText);
			// Nếu text = item mong muôn => Click
			if (actualText.equals(expectedTextItem)) {
				// B1: Nếu item cần chọn nằm trong vùng nhìn thấy => Ko cần scroll
				// B2: Nếu item cần chọn không nằm trong vùng nhìn thấy => scroll chuột xuống để
				jsExcutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(3);

				// Step4: Click vào item đó
				item.click();
				sleepInSecond(2);
				// Thoát khỏi vòng lập - không có kiểm tra vòng lập tiếp theo
				break;
			}
		}
	}

	public void sleepInSecond(long Second) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}