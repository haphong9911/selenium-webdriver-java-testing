package javaTester;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Topic_02_And_Or {

	public static void main(String[] args) {
		boolean answerPersonA;
		boolean answerPersonB;
		boolean resultC;
		
		//AND
		answerPersonA = true;
		answerPersonB = false;
		resultC = answerPersonA && answerPersonB;
		System.out.println(resultC);
		
		//AND
		answerPersonA = false;
		answerPersonB = true;
		resultC = answerPersonA && answerPersonB;
		System.out.println(resultC);

		//AND
		answerPersonA = true;
		answerPersonB = true;
		resultC = answerPersonA && answerPersonB;
		System.out.println(resultC);
		
		//AND
		answerPersonA = false;
		answerPersonB = false;
		resultC = answerPersonA && answerPersonB;
		System.out.println(resultC);
		
	}

}
