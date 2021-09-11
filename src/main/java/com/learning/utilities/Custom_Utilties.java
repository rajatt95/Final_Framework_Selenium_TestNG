package com.learning.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;
import com.learning.configuration.TestSuiteBase;
import com.learning.data.Constants;
import com.learning.listeners.Test_Listener;

public class Custom_Utilties extends TestSuiteBase {

	public void navigateToURL(String URL) {

		publishMessageInReports("Navigating to URL: '" + URL + "'");
		getDriver().get(URL);
	}

	public void clickOnElement(WebElement element) {
		String elementName = getElementText(element);

		publishMessageInReports("Clicking on '" + elementName + "'");
		element.click();
	}

	public void fillTextField(WebElement element, String textBoxName, String valueToBeFilled) {

		publishMessageInReports("Fill '" + valueToBeFilled + "' in '" + textBoxName + "' Text Field");
		element.sendKeys(valueToBeFilled);
	}

	public void webElementPresent(WebElement element) {
		Assert.assertTrue(element.isDisplayed(),
				"Verified that '" + getElementText(element) + "' is present on the page");
		publishMessageInReports_PASS(
				"Presence of Web Element - Verified that '" + getElementText(element) + "' is present on the page");

	}

	public void webElementPresent(WebElement element, String elementName) {
		Assert.assertTrue(element.isDisplayed(), "Verified that '" + elementName + "' is present on the page");

		publishMessageInReports_PASS(
				"Presence of Web Element - Verified that '" + elementName + "' is present on the page");

	}

	public void verifyElementText(WebElement element, String expectedText) {
		String elementName = getElementText(element);
		Assert.assertTrue(elementName.contains(expectedText));

		publishMessageInReports_PASS(
				"Web Element Text - Verified that '" + elementName + "' contains '" + expectedText + "'");

	}

	private String getElementText(WebElement element) {
		String elementName = element.getText();
		return elementName;
	}

	public void scrollToElement(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) getDriver();

		System.out.println("Scroll to Element");
		String elementName = getElementText(element);
		publishMessageInReports("Scrolling to Element: '" + elementName + "'");

		jse.executeScript("arguments[0].scrollIntoView(true);", element);

	}

	public void scrollToElement(WebElement element, String elementName) {
		JavascriptExecutor jse = (JavascriptExecutor) getDriver();

		publishMessageInReports("Scrolling to Element: '" + elementName + "'");

		jse.executeScript("arguments[0].scrollIntoView(true);", element);

	}

	protected void publishMessageInReports(String message) {
		// System.out.println(message);
		log.info(message);
		Test_Listener.extentTest.get().log(Status.INFO, message);
		Reporter.log(message);

	}

	protected void publishMessageInReports_PASS(String message) {
		// System.out.println(message);
		log.info(message);
		Test_Listener.extentTest.get().log(Status.PASS, message);
		Reporter.log(message);

	}

	public void verifyURLContainsExpectedString(WebElement webElementToClick, String expectedString_InURL,
			WebElement... nextPageElements) {

		String webElementName = webElementToClick.getText();

		clickOnElement(webElementToClick);

		holdScript(2);
		for (WebElement nextPageElement : nextPageElements) {
			webElementPresent(nextPageElement);
		}
		verifyPageURL(expectedString_InURL, webElementName);
		getDriver().navigate().back();
		holdScript(1);
	}

	private void verifyPageURL(String expectedString_InURL, String webElementName) {
//		checkForPagenotFound();
		String elementClickedURL = getDriver().getCurrentUrl();

		publishMessageInReports("Current URL: " + elementClickedURL);
		publishMessageInReports("Expected String in URL: " + expectedString_InURL);

		Assert.assertTrue(elementClickedURL.contains(expectedString_InURL),
				"Verified \"" + webElementName + "\" link is navigated to [ \"" + elementClickedURL + "\" ]");

		publishMessageInReports_PASS("URL Validation - Verified \"" + webElementName + "\" link is navigated to [ \""
				+ elementClickedURL + "\" ]");

	}

	public void verifyPageTitle(String expectedTitle) {
		String actualTitle = getDriver().getTitle();

		publishMessageInReports("Actual Title: " + actualTitle);
		publishMessageInReports("Expected Title: " + expectedTitle);

		Assert.assertEquals(actualTitle, expectedTitle, "Verified the page Title: '" + actualTitle + "'");

		publishMessageInReports_PASS("Title Validation - Verified the page Title: '" + actualTitle + "'");

	}

	protected void webElementAbsentWithElementName(WebElement webElement, String webElementName) {
		try {
			webElement.isDisplayed();
		} catch (NoSuchElementException exception) {
			Assert.assertTrue(true, "Verified '" + webElementName + "' is absent.");

			publishMessageInReports_PASS("Verified '" + webElementName + "' is absent.");
			/*
			 * log.info("Verified '" + webElementName + "' is absent.");
			 * extentTest.get().log(Status.PASS, "Verified '" + webElementName +
			 * "' is absent.");
			 */
		}
	}

	public void getBrowserAndOS_Details() {
		Capabilities cap = ((RemoteWebDriver) getDriver()).getCapabilities();
		String browser = cap.getBrowserName().toUpperCase();
		String browserVersion = cap.getVersion();
		String os = getOSName();

		publishMessageInReports(os + " -- >  " + browser + "-" + browserVersion);
		publishMessageInReports("Navigating to URL: " + ((RemoteWebDriver) getDriver()).getCurrentUrl());
	}

	public String getOSName() {
		return System.getProperty("os.name");
	}

	public void holdScript(int seconds) {
		try {

			publishMessageInReports("Hold script for: " + seconds + " seconds");
			/*
			 * // System.out.println("Hold script for: " + seconds + " seconds");
			 * log.info("Hold script for: " + seconds + " seconds");
			 * extentTest.get().log(Status.INFO, "Hold script for: " + seconds +
			 * " seconds");
			 */
			Thread.sleep(1000 * seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void captureScreenshot(WebDriver driver) {

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		String screenshotName = date.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		publishMessageInReports("Capturing screenshot: " + screenshotName);
		/*
		 * // System.out.println("Capturing screenshot: " + screenshotName);
		 * log.info("Capturing screenshot: " + screenshotName);
		 * extentTest.get().log(Status.INFO, "Capturing screenshot: " + screenshotName);
		 */

		try {
			FileUtils.copyFile(srcFile, new File(Constants.ScreenshotsPath_DuringScriptRun + screenshotName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
