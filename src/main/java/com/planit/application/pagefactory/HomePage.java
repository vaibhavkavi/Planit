package com.planit.application.pagefactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.planit.core.framework.BaseUtil;



public class HomePage extends BaseUtil{
	
	final static Logger logger = Logger.getLogger("rootLogger");
	
	// list all elements on Home Page
	
	// list of header Menu
	@FindBy(id="nav-home")
	private WebElement homeLink;
	@FindBy(id="nav-shop")
	private WebElement shopLink;
	@FindBy(id="nav-contact")
	private WebElement contactLink;
	@FindBy(id="nav-cart")
	private WebElement cartLink;
	@FindBy(id="nav-logout")
	private WebElement logoutLink;
	@FindBy(id="nav-login")
	private WebElement loginLink;
	@FindBy(id="nav-user")
	private WebElement userLink;
	// hero button
	@FindBy(className="btn btn-success btn-large")
	private WebElement startShoppingbtn;
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public void goToHomePage() {
		clickOn(homeLink);
		
	}
	
	public void goToShoppingPage() {
		clickOn(shopLink);
	}
	
	public void goToContactPage() {
		clickOn(contactLink);
	}
	
	public void goToCart() {
		clickOn(cartLink);
	}
	
	public void userLogin() {
		clickOn(loginLink);
	}
	
	public void logout() {
		clickOn(logoutLink);
	}
	
	public void userprofile() {
		clickOn(userLink);
	}
	
	
}
