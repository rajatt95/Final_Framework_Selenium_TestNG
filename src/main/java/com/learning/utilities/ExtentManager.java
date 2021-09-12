package com.learning.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.learning.data.Constants;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports createInstance(String fileName) {

		/**
		 * This will generate the Report with pattern -
		 * Automation_Report_Windows_10_EDGE_Sun_Sep_12_22_51_34_IST_2021
		 */
		ExtentSparkReporter htmlReporter = reports_ALL(fileName);

		ExtentSparkReporter htmlReporter_All = reports_ALL(Constants.Reports_Extent_Folder + "Only_ALL.html");
		ExtentSparkReporter htmlReporter_Fail = reports_FAIL(Constants.Reports_Extent_Folder + "Only_FAIL.html");
		ExtentSparkReporter htmlReporter_Skip = reports_SKIP(Constants.Reports_Extent_Folder + "Only_SKIP.html");
		ExtentSparkReporter htmlReporter_Pass = reports_PASS(Constants.Reports_Extent_Folder + "Only_PASS.html");

		extent = new ExtentReports();
		extent.setSystemInfo("Organization", "Nagarro");
		extent.setSystemInfo("Employee", "Rajat Verma");
		extent.setSystemInfo("Domain", "Engineering (IT - Software)");
		extent.setSystemInfo("Skill", "Full Stack Test Automation Engineer");

		extent.attachReporter(htmlReporter, htmlReporter_All, htmlReporter_Fail, htmlReporter_Skip, htmlReporter_Pass);

		return extent;
	}

	private static ExtentSparkReporter reports_ALL(String fileName) {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Test Suite Reports - ALL");
		htmlReporter.config().setReportName("Automation Test Suite Results - ALL");
		htmlReporter.config().setTheme(Theme.STANDARD);
		return htmlReporter;
	}

	private static ExtentSparkReporter reports_FAIL(String fileName) {
		ExtentSparkReporter failedSpark = new ExtentSparkReporter(fileName).filter().statusFilter()
				.as(new Status[] { Status.FAIL }).apply();
		failedSpark.config().setEncoding("utf-8");
		failedSpark.config().setDocumentTitle("Automation Test Suite Reports - Only FAILED TCs");
		failedSpark.config().setReportName("Automation Test Suite Results - Only FAILED TCs");
		failedSpark.config().setTheme(Theme.STANDARD);
		return failedSpark;
	}

	private static ExtentSparkReporter reports_SKIP(String fileName) {

		ExtentSparkReporter skippedSpark = new ExtentSparkReporter(fileName).filter().statusFilter()
				.as(new Status[] { Status.SKIP }).apply();
		skippedSpark.config().setEncoding("utf-8");
		skippedSpark.config().setDocumentTitle("Automation Test Suite Reports - Only SKIPPED TCs");
		skippedSpark.config().setReportName("Automation Test Suite Results - Only SKIPPED TCs");
		skippedSpark.config().setTheme(Theme.STANDARD);
		return skippedSpark;
	}

	private static ExtentSparkReporter reports_PASS(String fileName) {
		ExtentSparkReporter passedSpark = new ExtentSparkReporter(fileName).filter().statusFilter()
				.as(new Status[] { Status.PASS }).apply();
		passedSpark.config().setEncoding("utf-8");
		passedSpark.config().setDocumentTitle("Automation Test Suite Reports - Only PASSED TCs");
		passedSpark.config().setReportName("Automation Test Suite Results - Only PASSED TCs");
		passedSpark.config().setTheme(Theme.STANDARD);
		return passedSpark;
	}

}