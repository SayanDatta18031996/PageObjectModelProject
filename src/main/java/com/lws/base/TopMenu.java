package com.lws.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.lws.pages.CRM.accounts.AccountPage;

/*
 *Is Top Menu is a page?? No. It's not a page 
 * Home page has a top menu
 * Accounts page has a top menu
 * Page has a top menu
 */
public class TopMenu {
	WebDriver driver;

	public TopMenu(WebDriver driver) {
		this.driver = driver;
	}

	public void goToHome() {

	}

	public void goToLeads() {

	}

	public void goToContacts() {

	}

	public AccountPage goToAccounts() {
		driver.findElement(By.cssSelector("#Visible_Accounts")).click();
		
		return new AccountPage();
	}

	public void Signout() {
		System.out.println("Siging Out");
	}
}
