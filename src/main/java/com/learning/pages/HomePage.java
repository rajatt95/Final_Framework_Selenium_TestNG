package com.learning.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[normalize-space()='Sign in']")
	private WebElement header_Sign_In;

	@FindBy(xpath = "//h4[text()='Information']")
	private WebElement footer_Information;

	@FindBy(xpath = "//a[@title='Manage my customer account']")
	private WebElement footer_MyAccount;

	@FindBy(xpath = "//a[@title='My orders']")
	private WebElement footer_MyAccount_MyOrders;

	@FindBy(xpath = "//h4[text()='Follow us']")
	private WebElement footer_Follow_Us;

	@FindBy(xpath = "//a[contains(@href,'facebook')]")
	private WebElement footer_Social_FaceBook;

	@FindBy(xpath = "//a[contains(@href,'twitter')]")
	private WebElement footer_Social_Twitter;

	@FindBy(xpath = "//a[contains(@href,'youtube')]")
	private WebElement footer_Social_Youtube;

	public WebElement get_Header_Sign_In() {
		return header_Sign_In;
	}

	public WebElement get_Footer_Information() {
		return footer_Information;
	}

	public WebElement get_Footer_MyAccount() {
		return footer_MyAccount;
	}

	public WebElement get_Footer_MyAccount_MyOrders() {
		return footer_MyAccount_MyOrders;
	}

	public WebElement get_footer_Follow_Us() {
		return footer_Follow_Us;
	}

	public WebElement get_footer_Social_FaceBook() {
		return footer_Social_FaceBook;
	}

	public WebElement get_footer_Social_Twitter() {
		return footer_Social_Twitter;
	}

	public WebElement get_footer_Social_Youtube() {
		return footer_Social_Youtube;
	}

}
