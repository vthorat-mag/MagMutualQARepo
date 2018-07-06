package com.mm.browsers;

import org.openqa.selenium.WebDriver;

public class BrowserTypes {

	public static WebDriver getDriver() {
		// TODO Auto-generated method stub
		
		//String browserType = System.getProperty("browser.type");
		String browserType = "IE";
		if(browserType != null)
		{
			if(browserType.equalsIgnoreCase("Firefox"))
				return new FirefoxWebDriver().getDriver();
			else if(browserType.equalsIgnoreCase("Device"))
			{
				String deviceName = System.getProperty("device.name");
				MobileEmulatorDriver mobileDriver = new MobileEmulatorDriver();
				mobileDriver.setDeviceName(deviceName);
				
				return mobileDriver.getDriver();
			}else if(browserType.equalsIgnoreCase("ResolutionScreen"))
			{
				String width = System.getProperty("screen.width");
				String height = System.getProperty("screen.height");
				
				ResolutionBasedDriver resolutionDriver = new ResolutionBasedDriver();
				resolutionDriver.setWidth(Integer.parseInt(width));
				resolutionDriver.setHeight(Integer.parseInt(height));
				
				return resolutionDriver.getDriver();
			}else if(browserType.equalsIgnoreCase("IE"))
			{
				return new IEWebDriver().getDriver();
				
			}
		}
		return new ChromeWebDriver().getDriver();
	}

}
