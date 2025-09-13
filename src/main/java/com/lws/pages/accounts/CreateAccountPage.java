package com.lws.pages.accounts;

import org.openqa.selenium.By;

import com.lws.base.Page;


public class CreateAccountPage extends Page {
	//TopMenu menu=new TopMenu();
	public void createAccount(String accountName) {
		driver.findElement(By.cssSelector("#Crm_Accounts_ACCOUNTNAME_LInput")).sendKeys(accountName);;
	}
	
}
