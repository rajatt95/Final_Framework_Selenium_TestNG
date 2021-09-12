package com.learning.data;

import java.io.File;

public interface Constants {

	final String Path_Project = System.getProperty("user.dir") + File.separator;

	final String Browser_Chrome = "chrome";
	final String Browser_Chrome_Headless = "chrome_headless";
	final String Browser_Firefox = "firefox";
	final String Browser_Edge = "edge";
	final String Browser_Safari = "safari";
	final String Browser_Opera = "opera";
	final String Browser_Headless = "headless";

	/** Zip file of Extent Reports */
	final String Zipped_ExtentReports_Folder_Name = "Extent_Reports.zip";

	/** ExtentReports */
	final String Reports_Extent_Location = ".//extent_reports";
	public final String Reports_Extent_Folder = ".//extent_reports//";

	/** IRetryAnalyzer */
	final int RetryCount = 0;
	final int MaxRetryCount = 1;

	/** Properties file */
	final String Properties_PATH = ".//src//test//resources//properties//";
	final String Properties_LOG4J = Properties_PATH + "log4j.properties";
	final String Properties_Environment = Properties_PATH + "environment.properties";

	/** Parameters inside properties file */
	final String Properties_URL = "application_url";
	final String Properties_Browser = "browser";

	final String Properties_ImplicitWait = "implicitWait";
	final String Properties_ExplicitWait = "explicitWait";

	final String Properties_SeleniumGrid_Hub_URL = "seleniumGrid_Hub_URL";

	final String Properties_Email = "automationPractice_email";
	final String Properties_Password = "automationPractice_password";

	public final static String ScreenshotsPath_DuringScriptRun = ".//screenshots//";
	public final static String ScreenshotsPath_DuringScriptRunFail = ".//reports//screenshots//";

	/** Excel sheet */
	public final static String excelSheet = ".//src//test//resources//excel//testdata.xlsx";
	/* Email, Password - Column Headings in Excel sheet */
	public static String excel_email = "Email";
	public static String excel_password = "Password";

	/**
	 * Variables used while sending the REport via Java Mail API
	 */
	public final String Project_Name = "Automation Test Suite Report";
	public final String Report_TestNG_Emailable = ".//target//surefire-reports//emailable-report.html";
	public final String Report_TestNG_Results_XML = ".//target//surefire-reports//testng-results.xml";

}
