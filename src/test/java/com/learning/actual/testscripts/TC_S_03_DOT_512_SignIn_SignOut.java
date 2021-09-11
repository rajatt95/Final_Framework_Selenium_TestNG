package com.learning.actual.testscripts;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.learning.data.Constants;
import com.learning.data.Constants_Element_Text;
import com.learning.data.Constants_Expected_Text;
import com.learning.data.Constants_Expected_Title;
import com.learning.pages.HomePage;
import com.learning.pages.MyAccountPage;
import com.learning.pages.SignInPage;
import com.learning.utilities.Custom_Utilties;

public class TC_S_03_DOT_512_SignIn_SignOut extends Custom_Utilties {

	@Test(groups = { "Sanity", "Regression" })
	public void S_03_DOT_512_SignIn_SignOut() {

		String email = properties.getProperty(Constants.Properties_Email);
		String password = properties.getProperty(Constants.Properties_Password);

		HomePage homePage = new HomePage(getDriver());
		clickOnElement(homePage.get_Header_Sign_In());

		SignInPage signInPage = new SignInPage(getDriver());
		fillTextField(signInPage.get_textBox_EMail(), Constants_Element_Text.Email, email);
		fillTextField(signInPage.get_textBox_Password(), Constants_Element_Text.Password, password);
		clickOnElement(signInPage.get_button_Sign_In());

		wait.until(ExpectedConditions.titleContains(Constants_Expected_Title.SignIn_Success));
		verifyPageTitle(Constants_Expected_Title.SignIn_Success);

		MyAccountPage myAccountPage = new MyAccountPage(getDriver());

		webElementPresent(myAccountPage.get_Header_Sign_Out());

		verifyElementText(myAccountPage.get_Header_Sign_Out(), Constants_Expected_Text.SignOut);
		verifyElementText(myAccountPage.get_Header_SignIn_Sucess_User(), Constants_Expected_Text.User_TestMail);

		clickOnElement(myAccountPage.get_Header_Sign_Out());

		webElementPresent(homePage.get_Header_Sign_In());
		webElementAbsentWithElementName(myAccountPage.get_Header_Sign_Out(), Constants_Expected_Text.SignOut);
		// webElementAbsentWithElementName(myAccountPage.get_Header_Sign_Out(),
		// Constants_Expected_Text.User_TestMail);

	}
}