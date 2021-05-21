package com.planit.application.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.planit.application.pagefactory.ContactPage;
import com.planit.application.pagefactory.HomePage;

import com.planit.core.framework.BaseTest;

public class Contact {
	final static Logger logger = Logger.getLogger("rootLogger");
	WebDriver driver;
	HomePage homePage;
	ContactPage contactPage;

	public Contact(WebDriver wd){
		driver = wd;
		homePage = PageFactory.initElements(driver, HomePage.class);
		contactPage = PageFactory.initElements(driver, ContactPage.class);
	}


	public void validatevalidErrorsDisplayed() {
		logger.info("Validating the required manadatory validation errors..");
		List<String> errorList = contactPage.validateErrors();
		List<String> standardErrorList = new ArrayList<>( 
				Arrays.asList("Forename is required","Email is required","Message is required","We welcome your feedback - but we won't get it unless you complete the form correctly.")
				);
		logger.info("Excepected error list:" + standardErrorList );
		Reporter.log("Excepected error list:" + standardErrorList);
		logger.info("Actual error list:" + errorList );
		Reporter.log("Actual error list:" + errorList);
		Assert.assertEquals(standardErrorList, errorList,"Actual error list and Expected Error List are matching"); 
   
	}
	
	public void validateNoErrorDisplayed() {
		logger.info("Validating the required manadatory validation are not displayed any more..");
		List<String> errorList = contactPage.validateErrors();
		if(errorList.size()==0) {
			logger.info("No Errors Displayed" );
			Reporter.log("No Errors Displayed");
		} else {
			logger.info("Errors are still displayed" );
			Reporter.log("Errors are still displayed");
			for(String error:errorList) {
				logger.info(error);
				Reporter.log(error);
			}
			Assert.fail("Some Errors are displayed"); 
		}

	}
	
	public void madatoryFormFill(Map<Object,Object> data) {
		contactPage.enterForeName(data.get("forename").toString());
		contactPage.enterEmail(data.get("email").toString());
		contactPage.enterMessage(data.get("message").toString());
	}
    
	public void fullFormFill(Map<Object,Object> data) {
		contactPage.enterForeName(data.get("forename").toString());
		contactPage.enterSurname(data.get("surname").toString());
		contactPage.enterEmail(data.get("email").toString());
		contactPage.enterTelephone(data.get("telephone").toString());
		contactPage.enterMessage(data.get("message").toString());
	}
	
	public void verifyMessageStatus(String forname) {
		contactPage = PageFactory.initElements(driver, ContactPage.class);
		String message = contactPage.getSuccessMessage();
		logger.info("message:" + message);
		Assert.assertEquals(message, "Thanks "+forname+", we appreciate your feedback.");
	}

}
