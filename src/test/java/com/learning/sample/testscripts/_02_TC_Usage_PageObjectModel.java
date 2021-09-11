package com.learning.sample.testscripts;

import org.testng.annotations.Test;

import com.learning.data.Constants;
import com.learning.pages.HomePage;
import com.learning.pages.SignInPage;
import com.learning.utilities.Custom_Utilties;

public class _02_TC_Usage_PageObjectModel extends Custom_Utilties {

	@Test
	public void S_01_Story_134_SignIn() {

		HomePage homePage = new HomePage(getDriver());
		clickOnElement(homePage.get_Header_Sign_In());

		SignInPage signInPage = new SignInPage(getDriver());

		String email = properties.getProperty(Constants.Properties_Email);
		String password = properties.getProperty(Constants.Properties_Password);

	}
}