package com.learning.sample.testscripts;

import org.testng.annotations.Test;

import com.learning.data.Constants;
import com.learning.utilities.Custom_Utilties;

public class _01_TC_ReadDataFromProperties extends Custom_Utilties {

	@Test
	public void S_01_Story_134_SignIn() {

		String email = properties.getProperty(Constants.Properties_Email);
		String password = properties.getProperty(Constants.Properties_Password);

		System.out.println("Email: " + email);
		System.out.println("Password: " + password);

	}
}