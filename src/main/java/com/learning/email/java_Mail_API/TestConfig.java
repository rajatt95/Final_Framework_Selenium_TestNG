package com.learning.email.java_Mail_API;

/**
 * Data for Sending EMail after execution
 */
public class TestConfig {

	// cc

	public static String server = "smtp.gmail.com";
	/* public static String port = "465"; */
	public static String port = "587";

	public static String from = "your-email@gmail.com";
	public static String password = "your-password";

	public static String[] to = { "recipient-email@gmail.com", "recipient@gmail.com" };

	// Used in Rough package
	public static String new_to = "receipient-email@gmail.com";

//	public static String subject = "Extent Project Report - API_TestingFramework";
	public static String subject = "Automation Test Suite Report - Framework_Selenium_TestNG_Final";

	public static String messageBody = "Message Body - Automation Test Suite Report - Framework_Selenium_TestNG_Final";

}
