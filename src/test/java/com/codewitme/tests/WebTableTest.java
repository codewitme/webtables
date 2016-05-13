package com.codewitme.tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Ignore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.codewitme.webtables.WebTable;

public class WebTableTest {
	
	WebDriver driver;
	WebTable table;
	@BeforeMethod
	public void beforeClass() throws InterruptedException{
		driver = new FirefoxDriver();
		driver.get("http://the-internet.herokuapp.com/tables");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		table=new WebTable(driver);
	}
	@AfterMethod
	public void afterClass(){
		driver.close();
		driver.quit();
	}
	@Test(enabled=false)
	public void verifyColumnNames(){
		String[] expectedColNames= new String []{"Last Name","First Name"};
		//table=new WebTable(driver); 
		List<String> actualColNames=table.getColumnNames();
		Assert.assertArrayEquals(expectedColNames, actualColNames.toArray());
		
	}
	
	@Test
	public void verifyCellValue(){
		
		//table=new WebTable(driver); 
		//int index=table.getColIndexByColName("Last Name");
		int rowIndex=table.getRowIndex("Last Name", "Bach");
		String actualEmail=table.getCellValue("Email", rowIndex);
		Assert.assertEquals("Faild: The email id is incorrect", "fbach@yahoo.com", actualEmail);
	}

}
