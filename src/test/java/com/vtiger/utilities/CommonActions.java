package com.vtiger.utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class CommonActions {
	
	private static WebDriver driver;
	private static ExtentTest logger;
    static WebDriverWait Wait;
	
	public CommonActions(WebDriver driver,ExtentTest logger)
	{
		this.driver = driver;
		this.logger = logger;
		Wait = new WebDriverWait(driver ,Duration.ofSeconds(30));
	}
	
	public static void EnterValue(WebElement elm,String value, String msg)
	{
		try
		{       Wait.until(ExpectedConditions.visibilityOf(elm));
				elm.clear();
				elm.sendKeys(value);
				logger.pass("<B>"+value+":</B> " + msg+" <a href="+getscreenshot()+"><span Class='label end-time'>Screeshot</span></a>");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			logger.fail("unable to enter text due to error :"+e.getMessage()+" <a href="+getscreenshot()+"><span Class='label end-time'>Screeshot</span></a>");
		}
	}
	 
	public static void ClickElement(WebElement elm,String msg)
	{
		try
		{       Wait.until(ExpectedConditions.elementToBeClickable(elm));
				elm.click();
				logger.pass(msg+" <a href="+getscreenshot()+"><span Class='label end-time'>Screeshot</span></a>");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			logger.fail("unable to click due to error :"+e.getMessage()+" <a href="+getscreenshot()+"><span Class='label end-time'>Screeshot</span></a>");
		}
	}
	
	public static void ElementExist(WebElement elm)
	{
		try
		{       Wait.until(ExpectedConditions.visibilityOf(elm));
				elm.isDisplayed();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static String getscreenshot()
	{
		DateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
		Date d = new Date();
		String str = f.format(d);
		TakesScreenshot scrshot = ((TakesScreenshot)driver);
	    //Call getScreenshotAs method to create image file
		File SrcFile=scrshot.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/src/test/java/com/vtiger/reports/screenshots/image"+str+".png";
		//Move image file to new destination
		File DestFile=new File(path);
		//Copy file at destination
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
		}
		

}

	
		
	




	




		
	




	
	


