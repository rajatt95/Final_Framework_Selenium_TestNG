package com.learning.data;

import java.io.File;

public interface Constants {
	public static final String LOG4J_PROPERTIES = ".//src//test//resources//properties//log4j.properties";

	final String RESOURCES_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
			+ File.separator + "resources" + File.separator;
	final String PAGEPATH = RESOURCES_PATH + "locators" + File.separator;

	final String Browser_Chrome = "chrome";
	final String Browser_Chrome_Headless = "chrome_headless";
	final String Browser_Firefox = "firefox";
	final String Browser_Edge = "edge";
	final String Browser_Safari = "safari";
	final String Browser_Opera = "opera";
	final String Headless = "headless";

	final String Zipped_ExtentReports_Folder_Name = "Reports.zip";
	final String Zipped_ExtentReports_Folder_Name_WithLocation = ".\\Reports.zip";
	final String ExtentReports_Location = ".\\extent_reports";
	// IRetryAnalyzer
	final int RetryCount = 0;
	final int MaxRetryCount = 1;

	// Parameters inside properties file
	final String Properties_URL = "application_url";
	final String Properties_Browser = "browser";

	final String Properties_ImplicitWait = "implicitWait";
	final String Properties_ExplicitWait = "explicitWait";

	final String Properties_SeleniumGrid_Hub_URL = "seleniumGrid_Hub_URL";
	
	final String Properties_Email = "automationPractice_email";
	final String Properties_Password = "automationPractice_password";

	public final static String ScreenshotsPath_DuringScriptRun = ".//screenshots//";

	public final static String ScreenshotsPath_DuringScriptRunFail = ".//reports//screenshots//";

	public final static String excelSheet = ".//src//test//resources//excel//testdata.xlsx";

	/* Email, Password - Column Headings in Excel sheet */
	public static String excel_email = "Email";
	public static String excel_password = "Password";

	public static String ExtentReports_Path = ".//reports//extent//";

	/**
	 * Variables used while sending the REport via Java Mail API
	 */
	public final String Project_Name = "Automation Test Suite Report";
	public final String Emailable_Report = ".\\target\\surefire-reports\\emailable-report.html";
	public final String TestNG_Results_XML = ".\\target\\surefire-reports\\testng-results.xml";
	// public final String REPORTS_Folder = System.getProperty("user.dir") +
	// "/extent_reports/";
	public final String REPORTS_Folder = ".\\extent_reports\\";

}
