package com.planit.application.pagefactory;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.planit.core.framework.BaseTest;
import com.planit.core.framework.BaseUtil;

public class ContactPage extends BaseUtil{
	
	final static Logger logger = Logger.getLogger("rootLogger");
	WebDriver driver;
	// list all elements on contact Page
	
	// list of contact form elements
	@FindBy(id="header-message")
	private WebElement headerMessage;
	@FindBy(id="forename")
	private WebElement foreName;
	@FindBy(id="forename-err")
	private WebElement foreNameError;
	@FindBy(id="surname")
	private WebElement surname;
	@FindBy(id="email")
	private WebElement email;
	@FindBy(id="email-err")
	private WebElement emailError;
	@FindBy(id="telephone")
	private WebElement telephone;
	@FindBy(id="telephone-err")
	private WebElement telephoneError;
	@FindBy(id="message")
	private WebElement message;
	@FindBy(id="message-err")
	private WebElement messageError;
	
	// submit button
	@FindBy(xpath="//*[contains(@class, 'btn-contact')]")
	private WebElement submitbtn;
	
	// modal dialog header 
	@FindBy(xpath="//*[contains(@class, 'modal-header')]")
	private WebElement modalHeaderText;
	
	// feedback success message
	@FindBy(xpath="//*[contains(@class, 'alert alert-success')]")
	private WebElement successmessage;
	@FindBy(xpath="//*[contains(@bg-Click, 'goBack()')]")
	private WebElement backbtn;
	
	
	
	public ContactPage(WebDriver wd) {
		super(wd);
		driver = wd;
	}
	
	public void enterForeName(String firstName) {
		enterData(foreName, firstName);
	}
	
	public void enterSurname(String lastName) {
		enterData(surname,lastName);
	}
	
	public void enterEmail(String emailId) {
		enterData(email,emailId);
	}
	
	public void enterTelephone(String telephoneNumber) {
		enterData(telephone,telephoneNumber);
	}
	
	public void enterMessage(String messageContent) {
		enterData(message,messageContent);
	}
	
	public void submitContactForm() {
		clickOn(submitbtn);
	}
	
	public void clickOnBackButton() {
		clickOn(driver.findElement(By.xpath("//*[contains(@bg-Click, 'goBack()')]")));
	}
	
	public String getSuccessMessage() {
		logger.info("getting message");
		String message ="";
		try {
			message = driver.findElement(By.xpath("//*[contains(@class, 'alert')]")).getText();
		}catch(Exception e) {
			e.printStackTrace();
			return "";
		}
		return message;
	}
	
	public List<String> validateErrors() {
		List<String> errorList = new LinkedList<String>();
		try {
		if(foreNameError.isDisplayed()) errorList.add(foreNameError.getText());
		}catch(Exception e) {
			logger.info("forname Error is not displayed");
		}
		try {
		if(emailError.isDisplayed()) errorList.add(emailError.getText());
		}catch(Exception e) {
			logger.info("Email Error is not displayed");
		}
		try {
		if(telephoneError.isDisplayed()) errorList.add(telephoneError.getText());
		}catch(Exception e) {
			logger.info("Telephone Error is not displayed");
		}
		try {
		if(messageError.isDisplayed()) errorList.add(messageError.getText());
		}catch(Exception e) {
			logger.info("Message Error is not displayed");
		}
		if(errorList.size()>0) errorList.add(headerMessage.getText());
		return errorList;
	}
	
	public void sendingMessage() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		 try {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[contains(@class, 'popup modal hide ng-scope in')]"))));
        logger.info("modal dialog is present ");
        Reporter.log("modal dialog is present ");
        logger.info("Wait for modal dialog to disappear ");
		 }catch(Exception e) {
			 logger.info("Modal dialog does not appeaed");
			 Assert.fail("Modal dialog does not appeaed");
		 }
        try {
        WebDriverWait wait1 = new WebDriverWait(driver,20);
        wait1.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//*[contains(@class, 'popup modal hide ng-scope')]"))));
        logger.info("modal dialog disappeared ");
        Reporter.log("modal dialog disappeared ");
        }catch(Exception e) {
        	 logger.info("Modal dialog does not disappeaed");
        	 Assert.fail("Modal dialog does not disappeaed");
        }
       
        
        
	}
}
