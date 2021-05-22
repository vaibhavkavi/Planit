package com.planit.application.pagefactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import com.planit.core.framework.BaseUtil;



public class ShopPage extends BaseUtil{
	
	final static Logger logger = Logger.getLogger("rootLogger");
	
	// list all elements on Shop Page

	@FindBy(xpath = "//*[contains(@class, 'products ng-scope')]/ul/li")
	private List<WebElement> Items;
	@FindBy(id="nav-cart")
	private WebElement cartLink;
	@FindBy(xpath = "//*[@id=\"nav-cart\"]/a/span")
	private WebElement cartItems;
	
	
	
	public ShopPage(WebDriver driver) {
		super(driver);
	}
	

	public Map<String,Float> buyItem(Map<String,Integer> cartData) throws InterruptedException {
	int initialCartCount = Integer.valueOf(cartItems.getText());
	int actualCartCount = Integer.valueOf(cartItems.getText());
	Map<String,Float> productPriceMap= new LinkedHashMap<String,Float>();
	for (Map.Entry<String, Integer> entry : cartData.entrySet()) {
       logger.info("Buying for Product: "+entry.getKey() + ": and quantity: " + entry.getValue());
       actualCartCount = actualCartCount + entry.getValue();
		for(WebElement item:Items) {
			if(item.findElement(By.xpath("./div/h4")).getText().equals(entry.getKey())){
				for(int i=0;i<entry.getValue();i++) {
			item.findElement(By.xpath("./div/p/a")).click();
			productPriceMap.put(entry.getKey(), Float.parseFloat(item.findElement(By.xpath("./div/p/span")).getText().substring(1)));
				}
			break;
		}
		}

	}
	// Wait for 2 sec to ensure, Cart Link has properly update. slowness issue observed on Edge so adding below wait condition.
	Thread.sleep(2000);
	
		if((initialCartCount + actualCartCount) ==Integer.valueOf(cartItems.getText())) {
			logger.info("Added Items are matching in the cart count link");
		} else {
			Assert.fail("number of items are not matching in the cart link");
		}
		return productPriceMap;

	}
	
	public float getPriceOfItem(String name) {
		//*[@id="product-1"]/div/p/span
		float price =0;
		for(WebElement item:Items) {
			if(item.findElement(By.xpath("./div/h4")).getText().equals(name)){
				price = Float.parseFloat(item.findElement(By.xpath("./div/p/span")).getText().substring(1));
			break;
		}
		}
		return price;
		
	}
	
	public List<String> getListOfItems(String name) {
		
		return null;
	}
	
}
