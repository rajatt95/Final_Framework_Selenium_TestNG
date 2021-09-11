package com.learning.actual.testscripts;

import org.testng.annotations.Test;

import com.learning.data.Constants_Element_Text;
import com.learning.data.Constants_Expected_Text;
import com.learning.pages.HomePage;
import com.learning.utilities.Custom_Utilties;

public class TC_S_01_DOT_983_Footer_Validation extends Custom_Utilties {

	@Test(groups = { "Smoke", "Regression" ,"BVT"})
	public void S_01_DOT_983_Footer_Validation() {

		HomePage homePage = new HomePage(getDriver());

		scrollToElement(homePage.get_footer_Social_FaceBook(), Constants_Element_Text.Facebook);

		webElementPresent(homePage.get_footer_Social_FaceBook(), Constants_Element_Text.Facebook);
		webElementPresent(homePage.get_footer_Social_Twitter(), Constants_Element_Text.Twitter);
		webElementPresent(homePage.get_footer_Social_Youtube(), Constants_Element_Text.Youtube);

		verifyElementText(homePage.get_footer_Follow_Us(), Constants_Expected_Text.Follow_us);

	}
}