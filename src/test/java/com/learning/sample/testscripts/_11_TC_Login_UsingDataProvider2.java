package com.learning.sample.testscripts;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.learning.data.Constants;
import com.learning.data.Constants_Element_Text;
import com.learning.pages.HomePage;
import com.learning.pages.SignInPage;
import com.learning.utilities.Custom_Utilties;
import com.learning.utilities.DataProviders;

// _11_TC_Login_UsingDataProvider - Sheet name inside testdata.xlsx file
public class _11_TC_Login_UsingDataProvider2 extends Custom_Utilties {

	@Test(dataProviderClass = DataProviders.class, dataProvider = "excel_dataProvider")
	public void TC_Login_UsingDataProvider(Hashtable<String, String> data) {

		String email = data.get(Constants.excel_email);
		String password = data.get(Constants.excel_password);

		System.out.println("EMail: " + email);
		System.out.println("Password: " + password);
		System.out.println("-------------------------");

		HomePage homePage = new HomePage(getDriver());
		clickOnElement(homePage.get_Header_Sign_In());

		SignInPage signInPage = new SignInPage(getDriver());
		fillTextField(signInPage.get_textBox_EMail(), Constants_Element_Text.Email, email);
		fillTextField(signInPage.get_textBox_Password(), Constants_Element_Text.Password, password);
		clickOnElement(signInPage.get_button_Sign_In());

		// captureScreenshot("_11_TC_Login_UsingDataProvider");

		holdScript(4);
		System.out.println("This is the last line of Test case");
		log.info("This is the last line of Test case");

	}
}
