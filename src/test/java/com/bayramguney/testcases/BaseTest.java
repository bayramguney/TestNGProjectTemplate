package com.bayramguney.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.BeforeSuite;

import com.bayramguney.ExtentListeners.ExtentListeners;
import com.bayramguney.utilities.DriverFactory;
import com.bayramguney.utilities.DriverManager;

public class BaseTest extends PageInitializer {

	protected WebDriver driver;
	private Properties Config = new Properties();
	private FileInputStream fis;
	public Logger log = Logger.getLogger(BaseTest.class);
	public WebDriverWait wait;


	@BeforeSuite
	public void setUpFramework() {

		configureLogging();

		DriverFactory.setConfigPropertyFilePath(
				System.getProperty("user.dir") + "//src//test//resources//properties//Config.properties");
	
		
        if (System.getProperty("os.name").equalsIgnoreCase("mac")) {
        	
        	DriverFactory.setChromeDriverExePath(
    				System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver");
    		DriverFactory.setGeckoDriverExePath(
    				System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver");
    	
        }else {
		
		
		DriverFactory.setChromeDriverExePath(
				System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver.exe");
		DriverFactory.setGeckoDriverExePath(
				System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver.exe");
		DriverFactory.setIeDriverExePath(
				System.getProperty("user.dir") + "//src//test//resources//executables//IEDriverServer.exe");

        }
		/*
		 * Initialize properties Initialize logs load executables
		 * 
		 */
		try {
			fis = new FileInputStream(DriverFactory.getConfigPropertyFilePath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Config.load(fis);
			log.info("Config properties file loaded");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	

	
	
	public void logInfo(String message) {
		
		ExtentListeners.testReport.get().info(message);
	}

	public void configureLogging() {
		String log4jConfigFile = System.getProperty("user.dir") + File.separator + "src/test/resources/properties" + File.separator
				+ "log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
	}

	public void destroyFramework() {

	}

	public void openBrowser(String browser) {
		
		if (browser.equals("chrome")) {
			System.out.println("Launching : " + browser);
			System.setProperty("webdriver.chrome.driver",
					DriverFactory.getChromeDriverExePath());
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			System.out.println("Launching : " + browser);
			System.setProperty("webdriver.gecko.driver",
					DriverFactory.getGeckoDriverExePath());
			driver = new FirefoxDriver();

		}

		DriverManager.setWebDriver(driver);
		log.info("Driver Initialized !!!");
initializePages();
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 wait = new WebDriverWait(driver,30);

	}

	public void quit() {

		DriverManager.getDriver().quit();
		log.info("Test Execution Completed !!!");
	}

	public void waitClickability(WebElement element){
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}
	public void waitVisibility(WebElement element){
		wait.until(ExpectedConditions.visibilityOf(element));

	}
	public void sleep(int second) {
		try {
			Thread.sleep(second* 1000);
		}catch(Exception e){

		}
	}
}
