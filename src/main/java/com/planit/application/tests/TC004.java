package com.planit.application.tests;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.planit.application.component.Cart;
import com.planit.application.component.Contact;
import com.planit.application.pagefactory.CartPage;
import com.planit.application.pagefactory.ContactPage;
import com.planit.application.pagefactory.HomePage;
import com.planit.application.pagefactory.ShopPage;
import com.planit.core.framework.BaseTest;
import com.planit.core.framework.DataReader;

public class TC004 extends BaseTest{
	final static Logger logger = Logger.getLogger("rootLogger");
	int iterationCount = 0;
	int totalIterations =0;
	
    @Test (dataProvider="testData", testName="Verify Price,SubTotal and GrandTotal of selected Items")
    public void testcase(Map<Object, Object> data) throws InterruptedException {
    	logger.info("#############################################START#############################################");
    	logger.info("################# Verify Price,SubTotal and GrandTotal of selected Items ######################");
    	logger.info("#############################################START#############################################");
    	iterationCount++;
    	logger.info("Executing Iteation :" +iterationCount+"/"+totalIterations );

    	goToBaseURL();
    	System.out.println("data:" + data);
    	HomePage homePage = PageFactory.initElements(driver, HomePage.class);
    	ShopPage shopPage = PageFactory.initElements(driver, ShopPage.class);
    	CartPage cartPage = PageFactory.initElements(driver, CartPage.class);
        System.out.println("Executing Test");
        homePage.goToShoppingPage();
        Map<String,Integer> cartdata = new LinkedHashMap<String,Integer>();
        cartdata.put(data.get("item1").toString().split("=")[0], Integer.valueOf(data.get("item1").toString().split("=")[1]));
        cartdata.put(data.get("item2").toString().split("=")[0], Integer.valueOf(data.get("item2").toString().split("=")[1]));
        cartdata.put(data.get("item3").toString().split("=")[0], Integer.valueOf(data.get("item3").toString().split("=")[1]));
        Map<String, Float> productPriceMap= shopPage.buyItem(cartdata);
        homePage.goToCart();
        Cart cart = new Cart(driver);
        cart.verifyCartItems(cartdata);
        cart.veifyPrices(productPriceMap);
       Float sumOfSubTotal= cart.verifySubTotal();
       cart.verifyGrandTotal(sumOfSubTotal);
   	logger.info("#############################################END###############################################");
	logger.info("################# Verify Price,SubTotal and GrandTotal of selected Items ######################");
   	logger.info("#############################################END###############################################");
       
      
        
    }


    @DataProvider(name="testData")
    public Object[][] readExcel() throws InvalidFormatException, IOException {
    	totalIterations = DataReader.getData("testdata.xlsx", "TC004").length;
        return DataReader.getData("testdata.xlsx", "TC004");
    }
}
