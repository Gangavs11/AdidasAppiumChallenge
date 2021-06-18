package com.qa.base;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.qa.appiumServer.AppiumServer;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseClass {

	public static Logger log;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static FileInputStream fInputStream;
	protected static Properties properties;
	static AppiumDriverLocalService service;

	public static AppiumDriver<?> driver ; 

	@BeforeSuite
	/*
	 * This method is used for initializing the Log4j and Config.properties
	 */
	public static void startSuite() throws InterruptedException{
		
		AppiumServer.startServer();
		log = Logger.getLogger("devpinoyLogger");
		log.info("Test Started successfully");

		log.info("Initializing the Config.properties file");
		// Path where Config.properties file is placed
		String propertyFilePath = "C:\\Users\\Ganga\\eclipse-workspace_jdk11\\ShoppingApp.Adidas\\src\\main\\resources\\config.properties";

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
				log.info("Properties file loaded successfully:");
				// Config file is loaded successfully, now with the help of
				// properties.getProperty(key) we can retrieve the value.
			} catch (IOException ioE) {
				ioE.printStackTrace();
			}
		} catch (FileNotFoundException fnfE) {
			fnfE.printStackTrace();
			log.fatal("Unable to find/Load the Properties file ");
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}

	}


	/*
	 * This method is used for init the Appium Driver
	 */


	@BeforeTest
	public static void startTest() throws IOException
	{

		// Getting the driver declared using our Capabilities
		try {
			driver = startAppium();
		} catch (Exception e) {
			log.fatal("Driver is not Initiated as Expected" + e.getMessage());
		}

		// LOC for initializing the Extent Report
		log.info("Initializing the Extent Report");
	}

	/*
	 * This method is used to initiate the AppiumDriver with caps and connection protocol
	 */

	public static AppiumDriver<?> startAppium(){

		try {

			DesiredCapabilities caps = new DesiredCapabilities();

			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, properties.getProperty("platformName"));
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, properties.getProperty("automationName"));
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, properties.getProperty("platformVersion"));
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, properties.getProperty("deviceName"));
			caps.setCapability(MobileCapabilityType.UDID, properties.getProperty("udid"));
			caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
			caps.setCapability(MobileCapabilityType.APP, properties.getProperty("app"));

			URL url = new URL("http://localhost:4728/wd/hub");
			driver = new AndroidDriver<MobileElement>(url, caps);

			log.info("Driver declared successfully : " +driver);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		}catch(Exception e) {
			//driver = null;
			log.fatal("Driver declaration failed : " +driver);
			log.fatal(e.getStackTrace());
			//log.printStackTrace();

		}
		//Returning the instance of the driver to the parent method
		return driver;
	}


	@AfterTest
	public void teardown()
	{
		driver.quit();
	}


}
