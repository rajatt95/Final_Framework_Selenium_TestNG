/***
 *
 */
package com.learning.configuration;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.learning.data.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestSuiteBase {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public static void setDriver() {

		handleBrowserValue();
		String browserName = properties.getProperty(Constants.Properties_Browser);
		if (browserName.equalsIgnoreCase(Constants.Browser_Firefox)) {
			// WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
		} else if (browserName.equalsIgnoreCase(Constants.Browser_Chrome)) {
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase(Constants.Browser_Edge)) {
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
		} else if (browserName.equalsIgnoreCase(Constants.Browser_Safari)) {
			// WebDriverManager.safaridriver().setup();
			driver.set(new SafariDriver());
		} else if (browserName.equalsIgnoreCase(Constants.Browser_Opera)) {
			WebDriverManager.operadriver().setup();
			driver.set(new OperaDriver());
		} else if (browserName.equalsIgnoreCase(Constants.Browser_Chrome_Headless)) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments(Constants.Browser_Headless);
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver(options));
		} else {
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
		}

		setImplicitWait_And_MaximizeBrowserWindow();

		String application_url = properties.getProperty(Constants.Properties_URL);
		// System.out.println("Application URL : " + application_url);
		getDriver().get(application_url);
		log.debug("Navigated to URL : " + application_url);
		// extentTest.get().log(Status.INFO, "Navigated to URL : " + application_url);
		// return getDriver();

	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void closeBrowser() {
		driver.get().close();
		driver.remove();
	}

	TypedProperties loadPage = new TypedProperties();
	public static Properties properties = new Properties();

	protected static Logger log = Logger.getLogger(TestSuiteBase.class);

	// protected SoftAssert softAssertion;

	protected WebDriverWait wait;

	@BeforeSuite(alwaysRun = true)
	public void do_Setup_Before_Suite() throws IOException {

		// Load all .properties file
		properties = loadPage.loadAllFiles();

		// Configure log4j.properties file
		PropertyConfigurator.configure(Constants.Properties_LOG4J);

	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
		// System.out.println("Success");
		log.debug("Success");

		// softAssertion.assertAll();
		getDriver().quit();
		// System.out.println("Driver instance is killed");
		log.debug("Driver instance is killed");

	}

	protected static void handleBrowserValue() {
		String browser;
		if (System.getenv(Constants.Properties_Browser) != null
				&& !System.getenv(Constants.Properties_Browser).isEmpty()) {
			// browser value from Jenkins
			browser = System.getenv(Constants.Properties_Browser);
			// Setting the value of browser parameter in config.properties file
			properties.setProperty(Constants.Properties_Browser, browser);

		} else {
			// browser value from config.propertiesfile
			browser = properties.getProperty(Constants.Properties_Browser);
		}
	}

	@BeforeMethod(alwaysRun = true)
	public void preSetup() {
		// initializeBrowser();
		setDriver();

		// softAssertion = new SoftAssert();
		String explicitWait = properties.getProperty(Constants.Properties_ExplicitWait);
		long explicitWaitInLong = Long.parseLong(explicitWait);
		wait = new WebDriverWait(getDriver(), explicitWaitInLong);

	}

	private static void setImplicitWait_And_MaximizeBrowserWindow() {
		String implicitWait = properties.getProperty(Constants.Properties_ImplicitWait);

		log.debug("implicitWait (in seconds) : " + implicitWait);
		Long implicitWaitInLong = Long.parseLong(implicitWait);

		getDriver().manage().timeouts().implicitlyWait(implicitWaitInLong, TimeUnit.SECONDS);

		log.debug("Maxmizing Browser window");
		getDriver().manage().window().maximize();
	}

}