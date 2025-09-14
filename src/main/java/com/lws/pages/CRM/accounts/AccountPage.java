package com.lws.pages.CRM.accounts;

import org.openqa.selenium.By;

import com.lws.base.Page;

public class AccountPage extends Page {
	//TopMenu menu=new TopMenu();
	public CreateAccountPage goToCreateAccount() {
		driver.findElement(By.xpath("//button[@aria-label=\"Create Account\"]")).click();
		return new CreateAccountPage();
	}
	public ImportAccountPage goToImportAccounts() {
		driver.findElement(By.xpath("//button[@id='importButton']")).click();
		driver.findElement(By.xpath("//link-to[@data-zcqa=\"cv_importParent\"]"));
		return new ImportAccountPage();
	}

}
