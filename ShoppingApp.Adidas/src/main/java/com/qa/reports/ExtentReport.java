package com.qa.reports;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	static ExtentReports extent;
	final static String filePath = "Extent.html";
	static Map<Integer, ExtentTest>extentTestMap = new HashMap();
	
	public synchronized static ExtentReports getReporter() {
		if(extent == null)
		{
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/JustReports.html");
			spark.config().setTheme(Theme.DARK);
			spark.config().setReportName("MobileOnlineShoppingApp");
			spark.config().setDocumentTitle("Appium Framework");
			extent.attachReporter(spark);
		}
		
		return extent;
	}
	
	public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = getReporter().createTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }
	
	
	
	

}
