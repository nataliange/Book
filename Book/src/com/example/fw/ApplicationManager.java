package com.example.fw;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class ApplicationManager {
	
	private WebDriver driver;
	public String baseUrl;
	
	//ApplicationManager contains info about all the helpers
	public NavigationHelper navigationHelper;
	public GroupHelper groupHelper;
	public HibernateHelper hibernateHelper;
	
	private Properties properties;

	
	public ApplicationManager(Properties properties) throws Exception{
		System.setProperty("webdriver.ie.driver", "C:\\Users\\Nata\\IEDriverServer.exe");
		this.properties = properties;
	}

	
	public void stop() {
	    driver.quit();
		
	}
	
	public NavigationHelper getNavigationHelper(){
		if (navigationHelper == null) {
			navigationHelper = new NavigationHelper(this);
		}
		return navigationHelper;
	}
	
	public GroupHelper getGroupHelper(){
		if (groupHelper == null) {
			groupHelper = new GroupHelper(this);
		}
		return groupHelper;
	}

//get browser from properties file
	public WebDriver getDriver() {
		String browser = properties.getProperty("browser");
		if (driver == null) {
			if ("firefox".equals(browser)) {
				
			//changing browser settings
				FirefoxProfile profile = new FirefoxProfile();
				profile.setPreference("intl.accept_languages", "en-us, en");
				driver = new FirefoxDriver(profile);
				
			} else if ("ie".equals(browser)) {
				driver = new InternetExplorerDriver();
			} else if ("chrome".equals(browser)){
				driver = new ChromeDriver();
			} else {
				throw new Error ("unsupported browser " + browser);
			}
		    baseUrl = properties.getProperty("baseUrl");
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    driver.manage().window().maximize();
		    driver.get(baseUrl);
		}
		return driver;
	}


	public HibernateHelper getHibernateHelper() {
		if (hibernateHelper == null) {
			hibernateHelper = new HibernateHelper(this);
		}
		return hibernateHelper;
	}
	
}
