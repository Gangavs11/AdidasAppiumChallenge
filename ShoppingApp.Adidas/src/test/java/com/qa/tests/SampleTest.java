package com.qa.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.qa.base.BaseClass;
import com.qa.pages.AddReviewPage;
import com.qa.pages.ProductDetailsPage;
import com.qa.pages.ProductsListPage;
import com.qa.reports.ExtentReport;


@Listeners(com.qa.listeners.TestListener.class)

public class SampleTest extends BaseClass {
	
	public static ProductsListPage ProductsListPage;
	public static ProductDetailsPage ProductDetailsPage;
	public static AddReviewPage AddReviewPage;
	
	@BeforeClass
	
	public static void initiateDrivers()
	{
		ProductsListPage = new ProductsListPage(driver);
		ProductDetailsPage = new ProductDetailsPage(driver);
		AddReviewPage = new AddReviewPage(driver);
	}
	
	@Test
	public void testMobAppShopping() {
		
		String actualProductTitle = ProductDetailsPage.getSelectedProductName();
		String expectedProductTitle = ProductsListPage.getProduct1Name();
		
		SoftAssert SoftAssert = new SoftAssert();
		ProductsListPage.clickProduct1Name();
		log.info("Selected a product from the product list page");
		ExtentReport.getTest().log(Status.INFO, "clickProduct1Image");
			
		SoftAssert.assertEquals(actualProductTitle, expectedProductTitle);
		SoftAssert.assertAll();
		
		
		ProductDetailsPage.addReviewButton.click();
		log.info("ADD REVIEW button clicked");
		ExtentReport.getTest().log(Status.INFO,"addReviewButton clicked");
		
		AddReviewPage.reviewDetails.sendKeys("Awsome!...Very comfortable and stylish.");
		log.info("review added");
		ExtentReport.getTest().log(Status.INFO, "reviewDetails entered");
		
		AddReviewPage.reviewNumber.click();
		AddReviewPage.chosenReviewNumber.click();
		log.info("rating added");
		ExtentReport.getTest().log(Status.INFO, "chosenReviewNumber");
		
		AddReviewPage.saveButton.click();
		log.info("Save button clicked");
		ExtentReport.getTest().log(Status.INFO, "saveButton clicked");
	}

}
