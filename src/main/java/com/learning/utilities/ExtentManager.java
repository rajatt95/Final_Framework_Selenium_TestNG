package com.learning.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports createInstance(String fileName) {

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setEncoding("utf-8");

		htmlReporter.config().setDocumentTitle("Automation Test Suite Reports");
		htmlReporter.config().setReportName("Automation Test Suite Results");
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.setSystemInfo("Organization", "Nagarro");
		extent.setSystemInfo("Employee", "Rajat Verma");
		extent.setSystemInfo("Domain", "Engineering (IT - Software)");
		extent.setSystemInfo("Skill", "Full Stack Test Automation Engineer");

		extent.attachReporter(htmlReporter);
		return extent;
	}

}