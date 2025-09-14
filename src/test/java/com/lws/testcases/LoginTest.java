package com.lws.testcases;

import org.testng.annotations.Test;

import com.lws.pages.HomePage;
import com.lws.pages.LoginPage;
import com.lws.pages.ZohoAppPage;

public class LoginTest extends BaseTest  {
	@Test
	public void loginTest(){
		HomePage homepage=new HomePage();
		LoginPage lp=homepage.goToLogIn();
		log.info("adding ID password and calling doLogin");
		ZohoAppPage zohoapp=lp.doLogin("amibongbabu@gmail.com", "AutomationTest123!");
		menu.Signout();
	}

}
