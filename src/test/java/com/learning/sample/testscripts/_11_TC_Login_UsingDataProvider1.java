package com.learning.sample.testscripts;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.learning.data.Constants;
import com.learning.utilities.DataProviders;

// _11_TC_Login_UsingDataProvider1 - Sheet name inside testdata.xlsx file
public class _11_TC_Login_UsingDataProvider1 {

	@Test(dataProviderClass = DataProviders.class, dataProvider = "excel_dataProvider")
	public void TC_Login_UsingDataProvider(Hashtable<String, String> data) {

		String email = data.get(Constants.excel_email);
		String password = data.get(Constants.excel_password);

		System.out.println("EMail: " + email);
		System.out.println("Password: " + password);
		System.out.println("-------------------------");
	}
}
