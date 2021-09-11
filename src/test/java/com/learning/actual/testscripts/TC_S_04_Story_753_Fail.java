package com.learning.actual.testscripts;

import org.testng.annotations.Test;

import com.learning.pages.HomePage;
import com.learning.pages.MyAccountPage;
import com.learning.utilities.Custom_Utilties;

public class TC_S_04_Story_753_Fail extends Custom_Utilties {

	@Test(groups = { "Smoke", "Regression", "Sanity","BVT" })
	public void S_04_Story_753() {

		HomePage homePage = new HomePage(getDriver());
		clickOnElement(homePage.get_Header_Sign_In());

		MyAccountPage myAccountPage = new MyAccountPage(getDriver());
		webElementPresent(myAccountPage.get_Header_Sign_Out());

	}
}