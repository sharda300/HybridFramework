package com.vtiger.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver;
	public static Properties prop;
	public static Map<String,Map<String,String>> dt;
	public static ExtentHtmlReporter htmLReporter;
	public static ExtentReports extent;
	public static ExtentTest Logger;
	
	@BeforeSuite
	public void Intiation()
	{
		createExtentReport();
		//System.exit(0);
		System.out.println("Iniation is in progress");
		prop = readConfig();
		dt = getDataWithFillo();
		launchApp();
	}
	
	
	
	public void launchApp()
	{
		System.out.println("Launching Application");
		if(prop.getProperty("browser").equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if(prop.getProperty("browser").equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else
		{
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
		}
	    driver.get(prop.getProperty("AppUrl"));
	    driver.manage().window().maximize();
	    int time = Integer.parseInt(prop.getProperty("globaltimeout"));
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time ));
	}
	
	public void createExtentReport()
	{
		Date d = new Date();
		DateFormat ft = new SimpleDateFormat("ddMMyyyyhhmmss");
		String fileName = ft.format(d);
		htmLReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/src/test/java/com/vtiger/reports/ExtentReport"+fileName+".html");
		//Create an object of Extent Reports
		extent = new ExtentReports();
		extent.attachReporter(htmLReporter);
		extent.setSystemInfo("Host Name", "Automation Test Hub");
		      extent.setSystemInfo("Environment", "Test");
		extent.setSystemInfo("User Name", "DELL LATITUDE 5470") ;
		htmLReporter.config().setTheme(Theme.STANDARD);
		
		
	}
	
	public Properties readConfig() 
	{
		System.out.println("Reading Properties");
		Properties prop = null;
		try {
		 prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/Properties/Config.properties");
			prop.load(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	public Map<String,Map<String,String>> getDataWithFillo()
	{
		Map<String,Map<String,String>> AllData = null;
		try {
		Fillo f = new Fillo();
		Connection connection = f.getConnection(System.getProperty("user.dir")+"/src/test/resources/TestData/Data.xlsx");
		Recordset recordset = connection.executeQuery("SELECT * FROM Sheet1");
		int numberOfRows = recordset.getCount();
		List<String> AllColms = recordset.getFieldNames();
		int numberColum = AllColms.size();
		
		 AllData = new LinkedHashMap<String,Map<String,String>>();
		while (recordset.next()) {
			Map<String,String> colmData = new LinkedHashMap<String,String>();
			for(int i=1;i<numberColum;i++)
			{
				String Key = AllColms.get(i).trim();
				String Val = recordset.getField(AllColms.get(i)).trim();
				colmData.put(Key, Val);
			}
			
			
			AllData.put(recordset.getField("TestCaseName"), colmData);
		}
		 System.out.println(AllData);
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return AllData;
	}
	
	
	

}
