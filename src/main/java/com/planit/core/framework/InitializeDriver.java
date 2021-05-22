package com.planit.core.framework;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class InitializeDriver {
	
    final static Logger logger = Logger.getLogger("rootLogger");
    WebDriver driver;
    public  WebDriver getDriver(){

        try{
            // initiating the browser based on configuration
            String browser = Config.getProperty("BROWSER").toLowerCase().trim();
            switch(browser){
                case "chrome":
                    // setting the chrome driver  exe location
                    System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
                    driver = new ChromeDriver();
                    driver.manage().timeouts().implicitlyWait(Long.valueOf(Config.getProperty("IMPLICIT_WAIT").trim()), TimeUnit.SECONDS);
                    driver.manage().window().maximize();
                    driver.manage().deleteAllCookies();
                    logger.info("Chrome browser Launched Successfully");
                    break;
                case "edge":
                	 System.setProperty("webdriver.edge.driver","drivers/msedgedriver.exe");
                	 driver = new EdgeDriver();
                	 driver.manage().timeouts().implicitlyWait(Long.valueOf(Config.getProperty("IMPLICIT_WAIT").trim()), TimeUnit.SECONDS);
                     driver.manage().window().maximize();
                     logger.info("Edge browser Launched Successfully");
                    break;
                case "firefox":
               	 System.setProperty("webdriver.gecko.driver","drivers/geckodriver.exe");
               	  	driver = new FirefoxDriver();
               	  	driver.manage().timeouts().implicitlyWait(Long.valueOf(Config.getProperty("IMPLICIT_WAIT").trim()), TimeUnit.SECONDS);
                    driver.manage().window().maximize();
                    logger.info("Firefox browser Launched Successfully");
                   break;
                default:
                    logger.error("please check the browser name in config file and retry");
            }
            return driver;
        }catch (Exception e){
            logger.error("There was an Error while Launching the browser. please retry");
            logger.error("Exception occurred: " + e.getMessage());
            return null;
        }
       
    }
    
    public void closeDriver() {
    	try {
    	driver.close();
    	logger.info("Driver closed Successfully");
    	}catch(Exception e) {
    		logger.error("There was an Error while closing the driver");
            logger.error("Exception occurred: " + e.getMessage());
    	}
    }
}
