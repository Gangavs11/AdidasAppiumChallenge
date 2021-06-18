package com.qa.pages;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.BaseClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductDetailsPage extends BaseClass{
	
	 
	public ProductDetailsPage(AppiumDriver<?> driver)
		{
			BaseClass.driver=driver;
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
			
		}
	
	@AndroidFindBy(id = "com.example.challenge:id/productName")
	public MobileElement selectedProductName;
	
	@AndroidFindBy(id = "com.example.challenge:id/productPrice")
	public MobileElement selectedProductPrice;
	
	@AndroidFindBy(accessibility = "TODO")
	public MobileElement addReviewButton;
	
	
	public String getSelectedProductName()
	{
		String selectedProduct = selectedProductName.getText();
		return selectedProduct;
	}
	
	
	public void clickAddReviewButton() {
		addReviewButton.click();
	}
	
	

}
