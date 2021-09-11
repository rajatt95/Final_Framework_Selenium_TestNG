package com.learning.actual.testscripts;

import org.testng.annotations.Test;

import com.learning.data.Constants_Element_Text;
import com.learning.data.Constants_Expected_Text;
import com.learning.data.Constants_Expected_Title;
import com.learning.data.Constants_Expected_URL;
import com.learning.pages.HomePage;
import com.learning.pages.MyAccountPage;
import com.learning.utilities.Custom_Utilties;

public class TC_S_02_DOT_342_Footer_Links_Validation extends Custom_Utilties {

	@Test(groups = { "Smoke", "Regression", "Sanity" })
	public void S_02_DOT_342_Footer_Links_Validation() {

		HomePage homePage = new HomePage(getDriver());
		// scrollToElement(homePage.get_footer_Social_FaceBook());
		scrollToElement(homePage.get_footer_Social_FaceBook(), Constants_Element_Text.Facebook);

		MyAccountPage myAccountPage = new MyAccountPage(getDriver());

		webElementPresent(homePage.get_footer_Social_FaceBook(), Constants_Element_Text.Facebook);
		webElementPresent(homePage.get_footer_Social_Twitter(), Constants_Element_Text.Twitter);
		webElementPresent(homePage.get_footer_Social_Youtube(), Constants_Element_Text.Youtube);

		verifyElementText(homePage.get_footer_Follow_Us(), Constants_Expected_Text.Follow_us);

		verifyURLContainsExpectedString(homePage.get_Footer_MyAccount(), Constants_Expected_URL.Footer_MyAccount,
				myAccountPage.get_Cart());

		verifyPageTitle(Constants_Expected_Title.HomePage);

	}
}