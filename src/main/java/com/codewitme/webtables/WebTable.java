package com.codewitme.webtables;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebTable {
	
	WebDriver driver;
	
	public WebTable(WebDriver driver) throws InterruptedException{
		this.driver=driver;
		Thread.sleep(1);
	}
	
	
	public WebElement tableElement(){
		return  driver.findElement(By.id("table1"));
	}
	
	public List<String> getColumnNames(){
		List<String> colNames=new ArrayList();
		List<WebElement> headerElements=tableElement().findElement(By.tagName("thead")).findElement(By.tagName("tr")).findElements(By.tagName("th"));
		for(WebElement headerElement:headerElements){
			String columnName=headerElement.findElement(By.tagName("span")).getText();
			colNames.add(columnName);
		}
		return colNames;
	}
	
	public List<WebElement> getColumnElements(){
		return tableElement().findElement(By.tagName("thead")).findElement(By.tagName("tr")).findElements(By.tagName("th"));
	}
	
	public List<WebElement> getRowElements(){
		return tableElement().findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
	}
	
	public Integer getColIndexByColName(String columnName){
		int index;
		List<WebElement> columnElements=getColumnElements();
		for(int i=0;i<columnElements.size();i++){
			String colName=columnElements.get(i).findElement(By.tagName("span")).getText().trim();
			if(colName.equals(columnName)){
				index=i;
				return index;
			}
		}
		
		return null;
	}
	
	public String getCellValue(String columnName,Integer rowIndex){
		int colIndex = getColIndexByColName(columnName);//colIndex=2,Email
		return getRowElements().get(rowIndex).findElements(By.tagName("td")).get(colIndex).getText();
	}
	
	public Integer getRowIndex(String ColumnName,String expectedCellValue){
		int colIndex = getColIndexByColName(ColumnName);//colIndex=1
		int rowIndex = -1;
		List<WebElement> rowElements=getRowElements();
		//Iterating rows tr
		for(int rows=0;rows<getRowElements().size();rows++){
			//Iterating through each td
			List<WebElement> datas=rowElements.get(rows).findElements(By.tagName("td"));
			String actualCellData=datas.get(colIndex).getText();
			if(expectedCellValue.equals(actualCellData)){
				rowIndex=rows;
			}
		}
		return rowIndex;		
	}

}
