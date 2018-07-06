/**
 * 
 */
package com.mm.browsers;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author surendrane
 *
 */
public class MobileEmulatorDriver implements DriverFactory{
	
	WebDriver driver;
	String deviceName;

	public WebDriver getDriver() {
		
		String chromePath = System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromePath);
		
		Map<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put("deviceName", getDeviceName());

		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put("mobileEmulation", mobileEmulation);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		driver = new ChromeDriver(capabilities);

		return driver;
	}

	public void setDeviceName(String deviceName)
	{
		this.deviceName = deviceName;
	}
	
	public String getDeviceName()
	{
		return this.deviceName;
	}
}
