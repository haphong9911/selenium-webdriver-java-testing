package javaTester;

import org.testng.annotations.Test;

public class Topic_05_Param {
	
	//Hàm không có tham số
	public void clickToElement() {
		
	}
	
	//Hàm có tham số + kiểu dữ liệu String
	public void clickToElement(String element) {
		
	}
	
	public void clickToElement(String name, String locatorName) {
		
	}
	
	@Test //Test method/test case/ test script
	public void TC_01_Login() {
		clickToElement("Text box");
	}
}
