package com.learning.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {

	WebDriver driver;

	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@title='Log me out']")
	private WebElement header_Sign_Out;

	@FindBy(xpath = "//a[@title='View my customer account']")
	private WebElement header_SignIn_Sucess_User;

	
	@FindBy(xpath = "//a[@title='View my shopping cart']")
	private WebElement cart;

	
	
	public WebElement get_Header_Sign_Out() {
		return header_Sign_Out;
	}

	public WebElement get_Header_SignIn_Sucess_User() {
		return header_SignIn_Sucess_User;
	}

	
	
	public WebElement get_Cart() {
		return cart;
	}

}
