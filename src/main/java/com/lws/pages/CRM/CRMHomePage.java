package com.lws.pages.CRM;

import org.openqa.selenium.By;
import org.testng.Assert;
import com.lws.base.Page;

public class CRMHomePage extends Page {

	public void verifyUserHomeButton() {
		
	}
	public void verifyWelcomeText() {
		String Welmsg=driver.findElement(By.xpath("//span[@id='show-uName']") ).getText();
		Assert.assertEquals(Welmsg,"Welcome Bong Babu");
	}
	
}
