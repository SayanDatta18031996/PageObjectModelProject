package com.lws.pages;

import com.lws.base.Page;

public class LoginPage extends Page{
	public ZohoAppPage doLogin(String userName, String password) {
		typing("loginEmail_css",userName);
		clicking("next_css");
		typing("loginPass_css", password);
		clicking("next_css");
		return new ZohoAppPage();
	}
}
