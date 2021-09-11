package com.learning.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {

	WebDriver driver;

	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "email")
	private WebElement textBox_EMail;

	@FindBy(name = "passwd")
	private WebElement textBox_Password;

	@FindBy(xpath = "//button[@id='SubmitLogin']")
	private WebElement button_Sign_In;

	public WebElement get_textBox_EMail() {
		return textBox_EMail;
	}

	public WebElement get_textBox_Password() {
		return textBox_Password;
	}

	public WebElement get_button_Sign_In() {
		return button_Sign_In;
	}

}
