package testng;

import java.sql.Driver;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assert {
	
	@Test
	public void TC_01() {
		//Thư viện Assert: Kiểm tra tính đúng đắn của dữ liệu
		//Mong đợi nó đúng/ sai / đầu vào đầu ra như nhau
		//Bằng null, khác null,///
		
		String address ="Tran Cao Van";
		
		//Kiểm tra một đk đầu vào là luôn luôn đúng
		Assert.assertTrue(address.equals("Tran Cao Van"));
		Assert.assertTrue(address.contains("Cao"));

		//Kiểm tra một đk đầu vào là luôn luôn sai
		Assert.assertFalse(address.contains("Ha Noi"));
		
		//Kiểm tra dữ liệu đầu vào là như nhau
		Assert.assertEquals(address, "Tran Cao Van");
		
		Object null1 = null;
		//Kiểm tra dữ liệu là null/ not null
		Assert.assertNull(null1);
		Assert.assertNotNull(address);
	}
}
