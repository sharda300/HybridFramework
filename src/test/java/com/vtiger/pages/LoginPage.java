package com.vtiger.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.utilities.CommonActions;

public class LoginPage {
	
	private WebDriver driver;
	
	public LoginPage(WebDriver driver, ExtentTest logger)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		CommonActions ca = new CommonActions(driver,logger);
	}
	
	
	
	@FindBy(name="user_name")
	WebElement tbUserName;
	
	@FindBy(name="user_password")
	WebElement tbUserpassword;
	
	@FindBy(name="Login")
	WebElement BtnLogin;
	
	@FindBy(xpath="//img[@src='include/images/vtiger-crm.gif']")
	WebElement ImgLogo;
	
	//By tbUserName = By.name("user_name");
	
	
    public void login(String userid,String pwd)
    {
    	CommonActions.EnterValue(tbUserName, userid," text has been entered in username field");
    	CommonActions.EnterValue(tbUserpassword, pwd," text has been entered in password field");
    	CommonActions.ClickElement(BtnLogin,"Login button clicked");	
    }
    public void verifyLogo()
    {
    	CommonActions.ElementExist(ImgLogo);
    }
    
    
}
