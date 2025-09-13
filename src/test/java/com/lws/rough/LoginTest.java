package com.lws.rough;

import com.lws.base.Page;
import com.lws.pages.HomePage;
import com.lws.pages.LoginPage;
import com.lws.pages.ZohoAppPage;
import com.lws.pages.accounts.AccountPage;
import com.lws.pages.accounts.CreateAccountPage;

public class LoginTest {
	public static void main(String[] args) throws InterruptedException {
		HomePage homepage=new HomePage();
		LoginPage lp=homepage.goToLogIn();
		ZohoAppPage zohoapp=lp.doLogin("amibongbabu@gmail.com", "AutomationTest123!");
		zohoapp.goToCRM();
		AccountPage accountpage=Page.menu.goToAccounts();
		CreateAccountPage create=accountpage.goToCreateAccount();
		create.createAccount("Bongbabu");
		Thread.sleep(5000);
	}

}
