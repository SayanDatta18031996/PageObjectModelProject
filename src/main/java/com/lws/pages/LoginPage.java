package com.lws.pages;

import com.lws.base.Page;

public class LoginPage extends Page{
	public ZohoAppPage doLogin(String userName, String password) {
		log.info("Inside DoLogin");
		typing("loginEmail_css",userName);
		clicking("next_css");
		typing("loginPass_css", password);
		clicking("next_css");
		log.info("Returning ZohoAppPage");
		return new ZohoAppPage();
	}
}
