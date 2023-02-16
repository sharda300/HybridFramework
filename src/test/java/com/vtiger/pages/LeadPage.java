package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.utilities.CommonActions;

public class LeadPage extends HeaderPage{
	

	
	public LeadPage(WebDriver driver, ExtentTest logger)
	{
		super(driver,logger);
		PageFactory.initElements(driver, this);
		CommonActions ca = new CommonActions(driver,logger);
	}
	
	
	
	@FindBy(name="lastname")
	WebElement tblastname;
	
	@FindBy(name="company")
	WebElement tbcompany;
	
	@FindBy(name="button")
	WebElement Btnsave;
	
	
	public void createLeadwithMandatoryFields(String lname, String comp)
	{
		CommonActions.EnterValue(tblastname, lname," text has been entered in lastname field");
    	CommonActions.EnterValue(tbcompany, comp," text has been entered in company field");
    	CommonActions.ClickElement(Btnsave,"Save button clicked");	
	}
	

}
