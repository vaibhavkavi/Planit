package com.planit.core.framework;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if(Config.getProperty("EXEXUTION_MODE").toLowerCase().trim().equals("headless")) {
                        chromeOptions.addArguments("--headless");
                    }
                    driver = new ChromeDriver(chromeOptions);
                    driver.manage().timeouts().implicitlyWait(Long.valueOf(Config.getProperty("IMPLICIT_WAIT").trim()), TimeUnit.SECONDS);
                    driver.manage().window().maximize();
                    driver.manage().deleteAllCookies();
                    logger.info("Chrome browser Launched Successfully");
                    break;
                case "firefox":

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
