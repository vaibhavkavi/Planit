package com.planit.core.framework;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
	final static Logger logger = Logger.getLogger("rootLogger");
	public  WebDriver driver;
	InitializeDriver iniDriver = new InitializeDriver();
    
	
	@BeforeClass
	public void beforeClass() {
    
		driver = iniDriver.getDriver();
		
	}
	
	@AfterClass
	public void afterClass() {
		
		iniDriver.closeDriver();
	}
	
	public void goToBaseURL() {
		try {
		driver.get(Config.getProperty("BASE_URL"));
		Reporter.log("Base Url: " + Config.getProperty("BASE_URL") +" launched Successfully");
		logger.info("Base Url: " + Config.getProperty("BASE_URL") +" launched Successfully");
		}catch(Exception e) {
			logger.error("Cannot able to go to url:" + Config.getProperty("BASE_URL") );
			logger.error("Error Message:" + e.getMessage() );
			e.printStackTrace();
			Assert.fail("Cannot able to go to url:" + Config.getProperty("BASE_URL"));
		}
	}

	public void goToURL(String url) {
		try {
		driver.get(url);
		Reporter.log("Url: " + url +" launched Successfully");
	}catch(Exception e) {
		logger.error("Cannot able to go to url:" + url );
		logger.error("Error Message:" + e.getMessage() );
		e.printStackTrace();
		Assert.fail("Cannot able to go to url:" + Config.getProperty("BASE_URL"));
	}
	}
}
