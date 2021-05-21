package com.planit.core.framework;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

public class BaseUtil {
	final static Logger logger = Logger.getLogger("rootLogger");
	WebDriver driver;
	public BaseUtil(WebDriver wd) {
		driver=wd;
	}

	public void clickOn(WebElement element) {
		try {
		element.click();
		logger.info("Clicked on "+element.getText());
		Reporter.log("Clicked on "+element.getText());
		}catch(Exception e) {
			logger.info("Error Occured while clicking on "+element.getText());
			e.printStackTrace();
			Assert.fail("Error Occured while clicking on "+element.getText());
			
		}
	}
	
	public void enterData(WebElement element, String data) {
		try {
		element.clear();
		element.sendKeys(data);
		logger.info("Entered Data for "+element.getAttribute("id"));
		Reporter.log("Entered Data for "+element.getAttribute("id"));
		}catch(Exception e) {
			logger.info("Error Occured while entering data "+element.getText());
			e.printStackTrace();
			Assert.fail("Error Occured while entering data "+element.getText());
			
		}
	}
	
}
