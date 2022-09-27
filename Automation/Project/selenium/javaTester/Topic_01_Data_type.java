package javaTester;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Topic_01_Data_type {

	public static void main(String[] args) {
		//Khai báo biến
		int studenNumber;
		
		//Khởi tạo dữ liệu
		studenNumber = 100;
		int teacherNumber = 20;
		//I - Nguyên thuỷ (Primitive type)
		
		//boolean
		boolean studenStatus = true;
		
		//byte
		byte bEmployee = 10; 
		//short
		short sEmployee = 20;
		//int
		int iEmployee = 10;
		//long
		long lEmployee = 10000;
		//float
		float fEmployee= 10f	;
		//double
		double dEmployee = 8.4d;
		//char
		char a = 'B';
		
		//II - Tham chiếu (Reference)
		//Array
		int[] studenNumbers = {15,50,-7};
		String[] studenName = {"aass","as"};
		
		//Class / Interface
		WebDriver driver = new ChromeDriver();
		
		Actions action = new Actions(driver);
		
		//Collection: List/Set/Queue
		ArrayList<String> studenCountry = new ArrayList<String>();
		
		//Object
		Object phone;
		
		//Chuỗi kí tự - String
		String studentName = "B";
		String companyName = "Tran Ha Phong";
	}

}
