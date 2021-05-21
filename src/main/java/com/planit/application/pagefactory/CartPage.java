package com.planit.application.pagefactory;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.planit.core.framework.BaseUtil;



public class CartPage extends BaseUtil{
	
	final static Logger logger = Logger.getLogger("rootLogger");
	WebDriver driver;
	// list all elements on Shop Page
	
	// list of header Menu

	//@FindBy(xpath="//*[contains(@class, 'products')]/ul")
	@FindBy(xpath = "//*[contains(@class, 'alert')]")
	private WebElement cartMessage;
	
	@FindBy(xpath = "//*[contains(@class, 'btn btn-success')]")
	private WebElement startShoppingbtn;
	
	@FindBy(xpath = "//*[contains(@class, 'table table-striped cart-items')]")
	private WebElement cartTable;
	
	@FindBy(xpath = "//*[contains(@class, 'btn-checkout')]")
	private WebElement checkOutbtn;
	@FindBy(xpath = "//*[contains(@class, 'btn btn-danger ng-scope')]")
	private WebElement emptyCartbtn;
	@FindBy(xpath = "//*[contains(@class, 'cart-msg')]")
	private WebElement cartItemMessage;
	@FindBy(xpath = "//*[contains(@class, 'total ng-binding')]")
	private WebElement totalAmount;
	

	
	
	public CartPage(WebDriver wd) {
		super(wd);
		driver = wd;
		
	}
	
	
	public Map<String,List<String>> getSelectedCartItems() {
		
		Map<String,List<String>> cartItems = new LinkedHashMap<String,List<String>>();
		List<WebElement> TotalRowCount=cartTable.findElements(By.xpath("./tbody/tr"));

		
		
		
		int RowIndex=1;
		for(WebElement rowElement:TotalRowCount)
		{
		      List<WebElement> TotalColumnCount=rowElement.findElements(By.xpath("td"));
		      int ColumnIndex=1;
		      List<String> itemDetails =new LinkedList<String>();
		      for(WebElement colElement:TotalColumnCount)
		      {
		    	 
		    	  if(ColumnIndex==3) {
		    		
		    		 itemDetails.add(colElement.findElement(By.name("quantity")).getAttribute("value"));
		    		 
		    	  } else {
		    	 
		    	  if(!colElement.getText().trim().equals("")) {
		    	  itemDetails.add(colElement.getText());
		    	  }
		       }
		    	  ColumnIndex=ColumnIndex+1;
		    	  
		      }
		      cartItems.put(itemDetails.get(0), itemDetails);
		      RowIndex=RowIndex+1;
		 }
		return cartItems;

		}
	
	public float getTotalCost() {
		
		return Float.parseFloat(totalAmount.getText().substring(6));
	}
		
	}


