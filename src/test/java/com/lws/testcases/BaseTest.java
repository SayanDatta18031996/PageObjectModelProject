package com.lws.testcases;

import org.testng.annotations.AfterSuite;

import com.lws.base.Page;

public class BaseTest extends Page{
@AfterSuite
public void tearDown() {
	log.debug("Quiting the Driver");
	Page.quit();
}
}
