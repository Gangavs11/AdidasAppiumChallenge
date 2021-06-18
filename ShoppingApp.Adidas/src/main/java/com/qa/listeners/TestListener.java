package com.qa.listeners;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import org.apache.commons.codec.binary.Base64;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;				
import org.testng.ITestResult;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.qa.base.BaseClass;
import com.qa.reports.ExtentReport;
import org.apache.commons.io.FileUtils;


public class TestListener implements ITestListener {

	public void onTestFailure(ITestResult result) {
		if(result.getThrowable() != null)
		{
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			result.getThrowable().printStackTrace(pw);
			System.out.println(sw.toString());
		}
		
	// Taking screenshots for failed scripts	
		
	SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	Date date = new Date();
	String fileName = sdf.format(date);
	File file = BaseClass.startAppium().getScreenshotAs(OutputType.FILE);

	byte[] encoded = null;
	try {
		encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
	} catch (IOException e1) {
		e1.printStackTrace();
	}
	
	
	try {
		FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"//Screenshots//"+fileName+".png"));
	} catch (IOException e) {
		e.printStackTrace();
	}
	//ExtentReport.getTest().log(Status.FAIL, "Test Failed");	
	
	ExtentReport.getTest().fail("Test Failed",
			MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir")+"//Screenshots//"+fileName+".png").build());
	ExtentReport.getTest().fail("Test Failed",
			MediaEntityBuilder.createScreenCaptureFromBase64String(new String(encoded, StandardCharsets.US_ASCII)).build());
	ExtentReport.getTest().fail(result.getThrowable());
	}
	
	@Override
	public void onTestStart(ITestResult result) {

		
		
		ExtentReport.startTest(result.getName(), result.getMethod().getDescription())
		.assignAuthor("Ganga");		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentReport.getTest().log(Status.PASS, "Test Passed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentReport.getTest().log(Status.SKIP, "Test Skipped");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		//AppiumServer.Stop();
		ExtentReport.getReporter().flush();		
	}
}
