package com.qa.pages;

import org.openqa.selenium.support.PageFactory;

import com.qa.base.BaseClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AddReviewPage {
	
	
	public AddReviewPage(AppiumDriver<?> driver)
	{
		BaseClass.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}

		@AndroidFindBy(id = "com.example.challenge:id/reviewDetails")
		public MobileElement reviewDetails;

		@AndroidFindBy(id = "com.example.challenge:id/reviewNumber")
		public MobileElement reviewNumber;
		
		@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[@text='5']")
		public MobileElement chosenReviewNumber;

		@AndroidFindBy(id = "com.example.challenge:id/saveReview")
		public MobileElement saveButton;
		
		


		public void clickSaveButton() {
		saveButton.click();
}


}
