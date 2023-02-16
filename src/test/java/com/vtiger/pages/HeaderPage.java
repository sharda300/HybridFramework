package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.utilities.CommonActions;

public class HeaderPage {
	
public WebDriver driver;
	
	public HeaderPage(WebDriver driver, ExtentTest logger)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		CommonActions ca = new CommonActions(driver,logger);
	}
	
	
	
	@FindBy(linkText="New Lead")
	WebElement lnkNewLead;
	
	@FindBy(linkText="Leads")
	WebElement lnkLeads;
	
	@FindBy(linkText="Logout")
	WebElement lnkLogout;
	
	public void clickLogout()
	{
		CommonActions.ClickElement(lnkLogout, "link Logout clicked successfully");
	}
	
	public void clickNewLead()
	{
		CommonActions.ClickElement(lnkNewLead, "link New Lead clicked successfully");
	}
	
	public void clickLeads()
	{
		CommonActions.ClickElement(lnkLeads, "link Leads clicked successfully");
	}

}
