package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_01_Anotation {

  @Test
  public void Register() {
	  System.out.println("Register function");
  }
  
  @Test
  public void Login() {
	  System.out.println("Login function");
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("beforeMethod function");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("afterMethod function");
  }


  @BeforeClass
  public void beforeClass() {
	  System.out.println("beforeClass function");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("afterClass function");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("beforeTest function");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("afterTest function");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("beforeSuite function");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("afterSuite function");
  }

}
