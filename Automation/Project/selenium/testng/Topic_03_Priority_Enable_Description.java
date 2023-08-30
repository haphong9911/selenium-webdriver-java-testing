package testng;

import org.testng.annotations.Test;

public class Topic_03_Priority_Enable_Description {
	
	@Test (priority = 1)
	public void Order_01_View_Product() {
		
	}
	
	@Test (enabled = false)
	public void Order_02_Add_To_Cart() {
		
	}
	
	@Test (priority = 3, description = "User order and add payment for checkout")
	public void Order_03_Add_Payment_Method() {
		
	}

	@Test (priority = 4)
	public void Order_04_Checkout() {
		
	}
}
