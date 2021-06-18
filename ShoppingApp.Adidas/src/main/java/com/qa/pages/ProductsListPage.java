package com.qa.pages;

import org.openqa.selenium.support.PageFactory;
import com.qa.base.BaseClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.MobileElement;


public class ProductsListPage extends BaseClass{
	
	 
	public ProductsListPage(AppiumDriver<?> driver)
		{
			BaseClass.driver=driver;
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
			
		}
	
			
	@AndroidFindBy(xpath = "//*[@content-desc=\"More options\"]//following::android.widget.TextView[@text=\"product\"]")
	public MobileElement product1Name;
	
	@AndroidFindBy(xpath = "//*[@content-desc=\"More options\"]//following::android.widget.TextView[@text=\"59.0 $\"]")
	public MobileElement product1Price;
	
	public void clickProduct1Name() {
		product1Name.click();
	}
	
	public String getProduct1Name() {
		String Product1Name = product1Name.getText();
		return Product1Name;
	}
	
	public String getProduct1Price() {
		String Product1Price = product1Price.getText();
		return Product1Price;
	}
}
