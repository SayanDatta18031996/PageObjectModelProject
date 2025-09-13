package com.lws.pages.accounts;

import org.openqa.selenium.By;

import com.lws.base.Page;

public class AccountPage extends Page {
	//TopMenu menu=new TopMenu();
	public CreateAccountPage goToCreateAccount() {
		driver.findElement(By.cssSelector(".customPlusBtn")).click();
		return new CreateAccountPage();
	}
	public void goToImportAccounts() {}
}
