package webdriver;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Javascript_Excutor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String emailAddress;
	String projectPath = System.getProperty("user.dir");
	FirefoxOptions options = new FirefoxOptions();

	@BeforeClass
	public void beforeClass() {
		options.setBinary("/Applications/Firefox.app/Contents/MacOS/firefox");
		driver = new FirefoxDriver(options);

		// Ep kieu
		jsExecutor = (JavascriptExecutor) driver;
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		;
		driver.manage().timeouts();

		emailAddress = "haphong" + generateRandomNumber() + "@gmail.com";
	}

	@Test
	public void TC_01_Live_Guru() {
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(5);

		String homePageDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(homePageDomain, "live.techpanda.org");

		String homePageURL = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(homePageURL, "http://live.techpanda.org/");

		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(3);

		clickToElementByJS("//a[@title='IPhone']/parent::h2/following-sibling::div/button");
		sleepInSecond(3);

		String pageShoppingCart = getInnerText();
		Assert.assertTrue(pageShoppingCart.contains("IPhone was added to your shopping cart."));

		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(3);

		String customerPageTitle = (String) executeForBrowser("return document.title;");
		Assert.assertEquals(customerPageTitle, "Customer Service");

		scrollToElementOnDown("//input[@id='newsletter']");
		sleepInSecond(3);

		sendkeyToElementByJS("//input[@id='newsletter']", emailAddress);
		sleepInSecond(3);

		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(3);

		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));

		navigateToUrlByJS("http://demo.guru99.com/v4");
		sleepInSecond(5);

		String guruPageDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(guruPageDomain, "demo.guru99.com");
	}

	@Test
	public void TC_02_HTML5_Validation_Message() {
		driver.get("https://www.pexels.com/vi-vn/join-contributor/");

		By firstName = By.id("user_first_name");
		By email = By.id("user_email");
		By password = By.id("user_password");
		By createButton = By.xpath("//button[contains(text(),'Tạo tài khoản mới')]");

		driver.findElement(createButton).click();
		sleepInSecond(2);

		Assert.assertEquals(getElementValidationMessage(firstName), "Please fill out this field.");

		driver.findElement(firstName).sendKeys("Automation");
		driver.findElement(createButton).click();
		sleepInSecond(2);

		Assert.assertEquals(getElementValidationMessage(email), "Please fill out this field.");
		driver.findElement(email).sendKeys("Automation@1234@22");
		driver.findElement(createButton).click();
		Assert.assertEquals(getElementValidationMessage(email), "Please enter an email address.");

		driver.findElement(email).clear();
		driver.findElement(email).sendKeys("Automation@gmail.com");
		Assert.assertEquals(getElementValidationMessage(password), "Please fill out this field.");

	}

	@Test
	public void TC_03_image() {
		driver.get("https://automationfc.github.io/basic-form/");

		Assert.assertTrue(isImageLoaded("//img[@alt='User Avatar 05']"));
		Assert.assertFalse(isImageLoaded("//img[@alt='broken_04']"));

	}

	public void sleepInSecond(long Second) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(By bylocator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
				driver.findElement(bylocator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
}