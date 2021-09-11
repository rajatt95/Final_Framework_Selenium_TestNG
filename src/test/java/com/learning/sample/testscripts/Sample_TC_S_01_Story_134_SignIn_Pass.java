package com.learning.sample.testscripts;

import org.testng.annotations.Test;

import com.learning.data.Constants;
import com.learning.data.Constants_Element_Text;
import com.learning.data.Constants_Expected_Text;
import com.learning.pages.HomePage;
import com.learning.pages.MyAccountPage;
import com.learning.pages.SignInPage;
import com.learning.utilities.Custom_Utilties;

public class Sample_TC_S_01_Story_134_SignIn_Pass extends Custom_Utilties {

	@Test
	public void S_01_Story_134_SignIn() {

		// navigateToURL("http://automationpractice.com/");

		String email = properties.getProperty(Constants.Properties_Email);
		String password = properties.getProperty(Constants.Properties_Password);

		//String expected_Text_SignOut = properties.getProperty(Constants.Properties_Password);

		HomePage homePage = new HomePage(getDriver());
		clickOnElement(homePage.get_Header_Sign_In());

		SignInPage signInPage = new SignInPage(getDriver());
		fillTextField(signInPage.get_textBox_EMail(), Constants_Element_Text.Email, email);
		fillTextField(signInPage.get_textBox_Password(), Constants_Element_Text.Password, password);
		clickOnElement(signInPage.get_button_Sign_In());

		//captureScreenshot("TC_S_01_Story_134_SignIn");
		MyAccountPage myAccountPage = new MyAccountPage(getDriver());

		webElementPresent(myAccountPage.get_Header_Sign_Out());
		// webElementPresent(homePage.get_Header_Sign_In());

		verifyElementText(myAccountPage.get_Header_Sign_Out(), Constants_Expected_Text.SignOut);

		holdScript(4);
		System.out.println("This is the last line of Test case");
		log.info("This is the last line of Test case");
	}
}