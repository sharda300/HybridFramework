package com.vtiger.tests;


import org.testng.annotations.Test;

import com.vtiger.pages.HeaderPage;
import com.vtiger.pages.LoginPage;

public class LoginTest extends BaseTest  {
	
	
	
	
	@Test
	public void InvalidLoginTC01()
	{
		String TCName = "InvalidLoginTC01";
		Logger = extent.createTest(TCName);
		 LoginPage lp = new LoginPage(driver,Logger);
		 lp.login(dt.get(TCName).get("Userid"), dt.get(TCName).get("Password"));
		 extent.flush();
	}
	
	
	
	@Test
	public void ValidLoginTC02()
	{
		String TCName = "ValidLoginTC02";
		Logger = extent.createTest(TCName);
		 LoginPage lp = new LoginPage(driver,Logger);
		 lp.login(dt.get(TCName).get("Userid"), dt.get(TCName).get("Password"));
		 extent.flush();
		 HeaderPage hdp = new HeaderPage(driver,Logger);
		 hdp.clickLogout();
	}

}
