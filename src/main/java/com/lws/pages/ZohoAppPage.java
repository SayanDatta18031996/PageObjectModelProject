package com.lws.pages;

import org.openqa.selenium.By;
import com.lws.base.Page;
import com.lws.pages.CRM.CRMHomePage;

public class ZohoAppPage extends Page{

	public void goToCliq() {
		driver.findElement(By.cssSelector("._logo-chat._logo-x96.zod-app-logo")).click(); 
	}

	public void goToCampaigns() {
		driver.findElement(By.cssSelector("._logo-campaigns._logo-x96.zod-app-logo")).click();
	}
	public void goToMail() {
		driver.findElement(By.cssSelector("._logo-mail._logo-x96.zod-app-logo")).click();
	}
	public CRMHomePage goToCRM() {
		driver.findElement(By.xpath("//div[contains(text(),'CRM')]")).click();
		
		return new CRMHomePage();
	}
}
