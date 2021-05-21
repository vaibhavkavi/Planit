package com.planit.application.component;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.planit.application.pagefactory.CartPage;



public class Cart {

	
	final static Logger logger = Logger.getLogger("rootLogger");
	WebDriver driver;
	CartPage cartPage;
	public Cart(WebDriver wd){
		driver = wd;
		cartPage = PageFactory.initElements(driver, CartPage.class);
		
	}
	
	
	public void verifyCartItems(Map<String,Integer> cartData) {
		 Map<String,List<String>> cartItems =  cartPage.getSelectedCartItems();
		 boolean isSame = true;
		 for (Map.Entry<String, Integer> entry : cartData.entrySet()) {
		       logger.info("Verifying the product Details for Product: "+entry.getKey() + ": and quantity: " + entry.getValue());
		       if(cartItems.get(entry.getKey()).get(0).equals(entry.getKey()) && Integer.valueOf(cartItems.get(entry.getKey()).get(2))==entry.getValue()) {
		    	   logger.info("Actul Product: "+cartItems.get(entry.getKey()).get(0)+" and Given Product: "+entry.getKey() + " are Matching. ");
		    	   logger.info("Actul Product Quantity: "+Integer.valueOf(cartItems.get(entry.getKey()).get(2))+" and Given Quality: "+entry.getValue() + " are Matching ");
				      
		       } else {
		    	   isSame =false;
		       }
		 
		 }
		 if(isSame) {
			 logger.info("All Records are verified and all are correct");
		 } else {
			 Assert.fail("Some/All records are not matching");
		 }
	
	}


	public void veifyPrices(Map<String, Float> productPriceMap) {
		Map<String,List<String>> cartItems =  cartPage.getSelectedCartItems();
		boolean isSame = true;
		 for (Map.Entry<String, Float> entry : productPriceMap.entrySet()) {
			 
			 logger.info("Verifying price for product: " + entry.getKey());
			 if(entry.getValue()==Float.parseFloat(cartItems.get(entry.getKey()).get(1).substring(1))) {
				 logger.info("Prices on Shopping page: "+entry.getValue()+" and Shopping Cart: "+Float.parseFloat(cartItems.get(entry.getKey()).get(1).substring(1))+" are Same");
			 } else {
				 logger.info("Prices on Shopping page: "+entry.getValue()+" and Shopping Cart: "+Float.parseFloat(cartItems.get(entry.getKey()).get(1).substring(1))+" are Not Same");
					
				 isSame=false;
			 }
		 }
		 
		 if(isSame) {
			 logger.info("Prices are matching for all selected items in the cart");
		 } else {
			 Assert.fail("Some or all Prices are Not matching for all selected items in the cart");
		 }
		
	}


	public Float verifySubTotal() {
		Map<String,List<String>> cartItems =  cartPage.getSelectedCartItems();
		 DecimalFormat f = new DecimalFormat("##.00");
		 Float sumOfSubTotal=0.00F;
		boolean isSame = true;
		for (Map.Entry<String, List<String>> entry : cartItems.entrySet()) {
			 logger.info("Verifying SubTotal for product: " + entry.getKey());
			 Float productPrice = Float.parseFloat(cartItems.get(entry.getKey()).get(1).substring(1));
			 int quantity = Integer.valueOf(cartItems.get(entry.getKey()).get(2));
			 Float SubTotal = Float.parseFloat(cartItems.get(entry.getKey()).get(3).substring(1));
			 double product = (double) productPrice*quantity;
			 sumOfSubTotal = sumOfSubTotal + Float.parseFloat(f.format(product));
			 if(Float.parseFloat(f.format(product))==SubTotal) {
				 logger.info("Calculated SubTotal of Product: "+Float.parseFloat(f.format(product))+" and Printed Subtotal on page: "+SubTotal+" are Same");
			 } else {
				 logger.info("Calculated SubTotal of Product: "+Float.parseFloat(f.format(product))+" and Printed Subtotal on page: "+SubTotal+" are Not Same");
				 isSame=false;
			 }
			
		}
		 if(isSame) {
			 logger.info("SubTotal are matching for all selected items in the cart");
		 }else {
			logger.info("Some or all SubTotal are Not matching for all selected items in the cart");
			 Assert.fail("Some or all SubTotal are Not matching for all selected items in the cart");
		 }
		 return sumOfSubTotal;
	}


	public void verifyGrandTotal(Float sumOfSubTotal) {

		if(sumOfSubTotal==cartPage.getTotalCost()) {
			 logger.info("Calculated Sum of SubTotal:"+ sumOfSubTotal +" and Grand Total on Cart page: "+cartPage.getTotalCost()+" are Same");
		} else {
			 logger.info("Calculated Sum of SubTotal: "+ sumOfSubTotal +" and Grand Total on Cart page: "+cartPage.getTotalCost()+" are Not Same");
			 Assert.fail("Calculated Sum of SubTotal: "+ sumOfSubTotal +" and Grand Total on Cart page: "+cartPage.getTotalCost()+" are Not Same");
		}

	}
}
