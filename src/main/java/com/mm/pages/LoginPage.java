
package com.mm.pages;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mm.utils.ExcelUtil;
import com.mm.utils.ExtentReporter;
import com.mm.dto.LoginPageDTO;
import com.mm.utils.CommonAction;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage extends CommonAction{
	
	//Global Assignment/initialization of variables.
	WebDriver driver;
	
	
	//Element repository for Login page.
	@FindBy(name="j_username")
	WebElement userName;
		
	@FindBy(name="j_password")
	WebElement password;
		
	@FindBy(name="btnSearch")
	WebElement loginBtn;
	
	//Constructor to initialize elements on Login page.
	public LoginPage(WebDriver driver) throws IllegalArgumentException, IllegalAccessException, SecurityException
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	//Code to login to eOasis application.
	public HomePage loginToeOasis(String UserName, String PassWord) throws Exception
	{
		ExcelUtil exlutil = new ExcelUtil();
		driver.get("http://oasiscloud2017t:8081/oas17bts/CS/login.jsp");
		ExtentReporter.logger.log(LogStatus.INFO, "Accessing the URL - http://oasiscloud2017t:8081/oas17bts/CS/login.jsp");
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		//Entering User Name.
		try{
			Assert.assertTrue(userName.isDisplayed(), "User Name Field is displayed.");
			userName.sendKeys(UserName);
			ExtentReporter.logger.log(LogStatus.PASS, " User Name is entered in to userName Field");
		}catch(Exception e)
		{
			ExtentReporter.logger.log(LogStatus.FAIL, " Error while entering data into username field.");
		}
		
		//Entering Password.
		try{
			Assert.assertTrue(password.isDisplayed(), "Password Field is displayed.");
			Thread.sleep(2000);
			password.sendKeys(PassWord);
			ExtentReporter.logger.log(LogStatus.PASS, " Password is entered in to password Field");
		}catch(Exception e)
		{
			ExtentReporter.logger.log(LogStatus.FAIL, " Error while entering data into password field.");
		}

		//Clicking on login button.
		try{
			Assert.assertTrue(password.isDisplayed(), "Login button is displayed.");
			loginBtn.click();
			ExtentReporter.logger.log(LogStatus.PASS, "Clicked on Login Button");
			}catch(Exception e)
			{
				ExtentReporter.logger.log(LogStatus.FAIL, "Issue with login button.");
			}
		return new HomePage(driver);
	}
}
