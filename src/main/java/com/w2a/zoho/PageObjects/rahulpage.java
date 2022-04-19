package com.w2a.zoho.PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class rahulpage {

	public rahulpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="#username")
	public WebElement username;
	

	@FindBy(css="[type='password']")
	public WebElement password;
	
	
	@FindBy(css="#signInBtn")
	public WebElement signin;
	
	

	

	
	

}
