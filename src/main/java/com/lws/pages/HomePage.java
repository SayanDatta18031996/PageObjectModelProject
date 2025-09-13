package com.lws.pages;

import com.lws.base.Page;

public class HomePage extends Page{

	public void goToSignUp() {
		log.info("Click on login button in homepage");
		clicking("signup_css");
	}

	public LoginPage goToLogIn() {
		log.info("Click on login button in homepage");
		clicking("login_css");
		return new LoginPage();
	}

	public void goToSupport() {

	}

	public void goToZohoEdu() {

	}

	public void goToLearnMore() {

	}

	public void ValidateFooterLinks() {

	}
}
