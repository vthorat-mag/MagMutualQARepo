package com.mm.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IEWebDriver implements DriverFactory{
	WebDriver driver;
	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		String iepath = System.getProperty("user.dir") + "\\src\\main\\resources\\IEDriverServer.exe";
		System.setProperty("webdriver.ie.driver","C:\\Drivers\\IEDriverServer.exe");
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);
		driver = new InternetExplorerDriver();
		return driver;
	}
}
