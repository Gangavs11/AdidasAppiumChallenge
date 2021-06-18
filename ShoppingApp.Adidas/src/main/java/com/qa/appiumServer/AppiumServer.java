package com.qa.appiumServer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumServer {
	
	static String NodePath = "C:\\Program Files\\nodejs\\node.exe";
	static String AppiumMainJS_path= "C:\\Users\\Ganga\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
	
	static AppiumDriverLocalService service;
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	@BeforeSuite
	public static void startServer() throws InterruptedException
	{
		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File(NodePath))
				.withAppiumJS(new File(AppiumMainJS_path))
				.withIPAddress("127.0.0.1")
				.usingPort(4728)
				.withLogFile(new File("E:\\Appium's packages\\AppiumLog.txt")));
		
		System.out.println("Starting the appium server...."+"\n"+df.format(new Date())+
		"\n---------------------------------------------------------------------------");
			service.start();
			Thread.sleep(10000);
	}
	
	@AfterSuite
	public void afterSuite() throws InterruptedException {
		if(service.isRunning()==true) {
			service.stop();
			//log.info("Appium server stopped");
			System.out.println("\n--------------------------------------------------------------------------------------"+
			                    "\n Stopping the appium server................."+"\n"+df.format(new Date()));
		}
	
        
	}
}	
	

	


