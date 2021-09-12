package com.learning.listeners;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.learning.configuration.TestSuiteBase;
import com.learning.data.Constants;
import com.learning.email.java_Mail_API.EmailAttachmentsSender;
import com.learning.email.java_Mail_API.TestConfig;
import com.learning.utilities.Custom_Utilties;
import com.learning.utilities.ExtentManager;

public class Test_Listener extends TestSuiteBase implements ITestListener {

	static int count_passedTCs;
	static int count_skippedTCs;
	static int count_failedTCs;
	static int count_totalTCs;

	static Date d = new Date();

	static String fileName = getExtentReportName();

	private static String getExtentReportName() {
		String prefix = "Automation_Report_";
		String os = Custom_Utilties.getOSName().replace(" ", "_") + "_";
		handleBrowserValue();
		String browser = Custom_Utilties.getValueFrom_Environment_PropertiesFile(Constants.Properties_Browser)
				.toUpperCase() + "_";
		String current_Date = d.toString().replace(":", "_").replace(" ", "_");
		String fileFormat = ".html";
		String extentReports_FileName = prefix + os + browser + current_Date + fileFormat;
		// System.out.println(extentReports_FileName);
		return extentReports_FileName;
	}

	private static ExtentReports extent = ExtentManager.createInstance(Constants.Reports_Extent_Folder + fileName);

	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		System.out.println("Thread.currentThread().getId(): " + Thread.currentThread().getId());
		String testCaseName = result.getMethod().getMethodName();

		count_totalTCs = count_totalTCs + 1;
		// Getting the name of class and method dynamically
		/*
		 * ExtentTest test = extent .createTest(result.getTestClass().getName() + "::" +
		 * result.getMethod().getMethodName());
		 */

		ExtentTest test = extent.createTest(testCaseName);
		extentTest.set(test);
		new Custom_Utilties().getBrowserAndOS_Details();

	}

	public void onTestSuccess(ITestResult result) {
		count_passedTCs = count_passedTCs + 1;
		String logText = "<b>Test Method " + result.getMethod().getMethodName() + " is Passed</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		// extentTest.get() - Because, we are accessing it from ThreadLocal
		extentTest.get().log(Status.PASS, m);
	}

	public void onTestFailure(ITestResult result) {
		count_failedTCs = count_failedTCs + 1;
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		extentTest.get()
				.fail("<details><summary><b><font color=red> Exception occured, click to see details: </font></b>"
						+ "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details> \n");

		// WebDriver driver = ((TestClassUsingListeners) result.getInstance()).driver;
		// String path = takeScreenshot(driver, result.getMethod().getMethodName());
		try {
			extentTest.get().fail("<b><font color=red>" + "Screenshot of Failure" + "</font></b>",
					// MediaEntityBuilder.createScreenCaptureFromPath(path).build());
					MediaEntityBuilder.createScreenCaptureFromBase64String(
							((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64)).build());
		} catch (IOException e) {
			extentTest.get().fail("Test failed, can not attach Screenshot");
		}

		String logText = "<b>Test Method " + result.getMethod().getMethodName() + " is Failed</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);

	}

	public void onTestSkipped(ITestResult result) {
		count_skippedTCs = count_skippedTCs + 1;
		String logText = "<b>Test Method " + result.getMethod().getMethodName() + " is Skipped</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		// extentTest.get() - Becasue, we are accessing it from ThreadLocal
		extentTest.get().log(Status.SKIP, m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext result) {

	}

	public void onFinish(ITestContext result) {

		if (extent != null) {
			extent.flush();
		}

		print_Info_In_Console();

		// Zipping reports folder and putting it under root directory
		zip(Constants.Reports_Extent_Folder);

		// sendEmail_WithAttachmentsAndFormattedBodyText_ToManyUsersRajat();
	}

	private void print_Info_In_Console() {

		System.out.println(
				"***************************************************************************************************************************************");
		System.out.println(
				"***************************************************************************************************************************************");
		 System.out.println("Logs files Location: " + Constants.Path_Project+"logs");
		System.out.println(
				"Reports.zip Location: " + Constants.Path_Project + Constants.Zipped_ExtentReports_Folder_Name);
		System.out.println("Extent Report Name: " + fileName);
		System.out.println(
				"***************************************************************************************************************************************");
		System.out.println(
				"***************************************************************************************************************************************");
	}

	public void onStart(ISuite suite) {

	}

	private void sendEmail_WithAttachmentsAndFormattedBodyText_ToManyUsersRajat() {

		System.out.println("File name: " + fileName);

		// String messageBody = "Test Message Body";
		String messageBody = getTestCasesCountInFormat();
		// String messageBody = getTestCasesCountInFormatFromXML();

		/*
		 * String attachmentFile_ExtentReport = Constants.REPORTS_Folder +
		 * "All_Automation_Report_Fri_Sep_10_03_47_17_IST_2021.html";
		 */

		String attachmentFile_ExtentReport = Constants.Reports_Extent_Folder + fileName;

		String attachmentFile_EMailableReport = Constants.Report_TestNG_Emailable;

		// Do not send it via Email - it a folder contains n no. of files
		// String attachmentFile_ExtentReports_Zip =
		// Constants.Zipped_ExtentReports_Folder_Name_WithLocation;

		System.out.println(messageBody);

		try {
			EmailAttachmentsSender.sendEmailWithAttachments(TestConfig.server, TestConfig.port, TestConfig.from,
					TestConfig.password, TestConfig.to, TestConfig.subject, messageBody, attachmentFile_ExtentReport,
					attachmentFile_EMailableReport/* , attachmentFile_ExtentReports_Zip */);

			System.out.println("****************************************");
			System.out.println("Email sent successfully.");
			System.out.println("****************************************");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	private String getTestCasesCountInFormat() {
		System.out.println("count_totalTCs: " + count_totalTCs);
		System.out.println("count_passedTCs: " + count_passedTCs);
		System.out.println("count_failedTCs: " + count_failedTCs);
		System.out.println("count_skippedTCs: " + count_skippedTCs);

		String messageBodyInFormat = "<html>\r\n" + "\r\n" + " \r\n" + "\r\n"
				+ "        <body> \r\n<table class=\"container\" align=\"center\" style=\"padding-top:20px\">\r\n<tr align=\"center\"><td colspan=\"4\"><h2>"
				+ Constants.Project_Name + "</h2></td></tr>\r\n<tr><td>\r\n\r\n"
				+ "       <table style=\"background:#67c2ef;width:120px\" >\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
				+ count_totalTCs + "</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Total</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                </td>\r\n" + "                <td>\r\n"
				+ "               \r\n" + "                 <table style=\"background:#79c447;width:120px\">\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
				+ count_passedTCs + "</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Passed</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                </td>\r\n" + "                <td>\r\n"
				+ "                <table style=\"background:#ff5454;width:120px\">\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
				+ count_failedTCs + "</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Failed</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                \r\n" + "                </td>\r\n"
				+ "                <td>\r\n" + "                <table style=\"background:#fabb3d;width:120px\">\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
				+ count_skippedTCs + "</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Skipped</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                \r\n" + "                </td>\r\n"
				+ "                </tr>\r\n" + "               \r\n" + "                \r\n"
				+ "            </table>\r\n" + "       \r\n" + "    </body>\r\n" + "</html>";

		return messageBodyInFormat;
	}

	// make zip of reports
	private void zip(String filepath) {
		try {
			File inFolder = new File(filepath);
			// File outFolder = new File("Reports.zip");
			File outFolder = new File(Constants.Zipped_ExtentReports_Folder_Name);
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
			BufferedInputStream in = null;
			byte[] data = new byte[1000];
			String files[] = inFolder.list();
			for (int i = 0; i < files.length; i++) {
				in = new BufferedInputStream(new FileInputStream(inFolder.getPath() + "/" + files[i]), 1000);
				out.putNextEntry(new ZipEntry(files[i]));
				int count;
				while ((count = in.read(data, 0, 1000)) != -1) {
					out.write(data, 0, count);
				}
				out.closeEntry();
			}
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}