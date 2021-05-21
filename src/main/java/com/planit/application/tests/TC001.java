package com.planit.application.tests;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.planit.application.component.Contact;
import com.planit.application.pagefactory.ContactPage;
import com.planit.application.pagefactory.HomePage;
import com.planit.core.framework.BaseTest;
import com.planit.core.framework.DataReader;

public class TC001 extends BaseTest{
	final static Logger logger = Logger.getLogger("rootLogger");
	int iterationCount = 0;
	int totalIterations =0;
	
    @Test (dataProvider="testData", testName="verify the valid errors on contact page")
    public void testcase(Map<Object, Object> data) throws InterruptedException {
    	logger.info("#############################################START#############################################");
    	logger.info("########################### Verify the valid errors on contact page ###########################");
    	logger.info("#############################################START#############################################");
    	iterationCount++;
    	logger.info("Executing Iteation :" +iterationCount+"/"+totalIterations );
    	goToBaseURL();
    	System.out.println("data:" + data);
    	HomePage homePage = PageFactory.initElements(driver, HomePage.class);
    	ContactPage contactPage = PageFactory.initElements(driver, ContactPage.class);
        homePage.goToContactPage();
    	contactPage.submitContactForm();
        Contact contactForm = new Contact(driver);
        contactForm.validatevalidErrorsDisplayed();
        contactForm.madatoryFormFill(data);
        contactForm.validateNoErrorDisplayed();
    	logger.info("#############################################END###############################################");
    	logger.info("########################### Verify the valid errors on contact page ###########################");
    	logger.info("#############################################END###############################################");
       
        
    }


    @DataProvider(name="testData")
    public Object[][] readExcel() throws InvalidFormatException, IOException {
    	totalIterations = DataReader.getData("testdata.xlsx", "TC001").length;
        return DataReader.getData("testdata.xlsx", "TC001");
    }
}
