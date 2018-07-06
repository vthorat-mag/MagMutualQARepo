package com.mm.utils;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mm.pages.PolicyQuotePage;
import com.relevantcodes.extentreports.LogStatus;

import BaseClass.CommonActionInterface;

public class CommonAction implements CommonActionInterface {

	Properties pro = new Properties();

	// Integer.valueOf(properties.prop.getProperty("High"));

	int Low = 10;
	int Medium = 30;
	int High = 50;
	String findPolicyQuotePage = "Find Policy/Quote";

	public void selectValue(WebDriver driver, WebElement pageElement, String value) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Medium);
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			Assert.assertTrue(pageElement.isDisplayed(), pageElement + " is Not displayed on screen.");
			ExtentReporter.logger.log(LogStatus.PASS, "Selected value " + value);
			clickButton(driver, pageElement, value);
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.FAIL, value + " element is not found.");
		}
	}

	public void switchToSecondFramefromFirst(WebDriver driver, String frameID) {

		List<WebElement> secondFrame = driver.findElements(By.id(frameID));
		driver.switchTo().frame(secondFrame.get(0));

	}

	public void switchToParentWindowfromotherwindow(WebDriver driver, String parentwindow) {

		try {
			driver.switchTo().window(parentwindow);
			ExtentReporter.logger.log(LogStatus.PASS, "Control switched back to parent window.");
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, "Error while switching to parent window.");
		}
	}

	public void switchToFrameUsingId(WebDriver driver, String frameName) {

		try {
			driver.switchTo().frame(frameName);
			ExtentReporter.logger.log(LogStatus.PASS, "Control switched Switched to frame.");
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, "Error while switching to frame.");
		}

	}

	public void switchToFrameUsingElement(WebDriver driver, WebElement element) throws Exception {
		waitFor(driver, 15);
		Thread.sleep(5000);
		try {
			driver.switchTo().frame(element);
			ExtentReporter.logger.log(LogStatus.PASS, "Control switched Switched to frame.");
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, "Error while switching to frame.");
		}
	}

	public void switchToParentWindowfromframe(WebDriver driver) {
		try {
			driver.switchTo().defaultContent();
			ExtentReporter.logger.log(LogStatus.PASS, "User Switched to default frame.");
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, "Error while switching to default frame.");
		}
	}

	public String getSelectedTextFromDropDown(WebDriver driver, WebElement dropDownElement) {

		Select dropDownList = new Select(dropDownElement);
		String selectedDDLValue = dropDownList.getFirstSelectedOption().getText();

		return selectedDDLValue;
	}

	public String randomNoGenerator() {
		return RandomStringUtils.random(2, "1234567890");
	}

	public void navigateTo(String url) {

	}

	// Enter text values in the text field
	public void enterTextIn(WebDriver driver, WebElement pageElement, String text, String textField) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, High);
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			Assert.assertTrue(pageElement.isDisplayed(), textField + " is not displayed on screen.");
			pageElement.sendKeys(text);
			ExtentReporter.logger.log(LogStatus.PASS, "Value entered in text box -" + textField);
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, textField + " element is not found.");
		}
	}

	// Enter the data values which are not text, like date and amount.
	public void enterDataIn(WebDriver driver, WebElement pageElement, String text, String textField) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Medium);
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			Assert.assertTrue(pageElement.isDisplayed(), textField + " is not displayed on screen.");
			pageElement.sendKeys(text);
			// driver.executeScript("arguments[0].setAttribute(value,
			// arguments[1]);", pageElement, text);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value=text;", pageElement);

			ExtentReporter.logger.log(LogStatus.PASS, "Value entered in text box -" + textField);
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, textField + " element is not found.");
		}
	}

	public void clickButton(WebDriver driver, WebElement pageElement, String buttonName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, High);
			wait.until(ExpectedConditions.elementToBeClickable(pageElement));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Assert.assertTrue(pageElement.isDisplayed(), buttonName + " button is displayed on screen.");
			js.executeScript("arguments[0].click();", pageElement);
			ExtentReporter.logger.log(LogStatus.PASS, "clicked on button / Link- " + buttonName);
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.FAIL, buttonName + " element is not found.");
		}
	}

	public void waitFor(WebDriver driver, long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	public void close(WebDriver driver) {
		driver.quit();
		ExtentReporter.logger.log(LogStatus.PASS, "Browser is closed.");
	}

	public void takeScreenShot(String pageTitle) {
		// TODO Auto-generated method stub
	}

	public void navigatetoMenuItemPage(WebDriver driver, WebElement mainMenu, WebElement menuItem) {
		Actions act = new Actions(driver);
		act.moveToElement(mainMenu).build().perform();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", menuItem);
		invisibilityOfLoader(driver);
	}

	public String getPageTitle(WebDriver driver, String expectedPageTitle) throws InterruptedException {
		invisibilityOfLoader(driver);
		Thread.sleep(3000);
		List<WebElement> getPageTitleFromPage = driver.findElements(By.xpath("//div[@class='pageTitle']"));
		WebDriverWait wait = new WebDriverWait(driver, High);
		WebElement pageLoader = driver.findElement(By.xpath("//span[@class='txtOrange']"));
		wait.until(ExpectedConditions.invisibilityOf(pageLoader));
		try {
			int i = 0;
			for (i = 0; i < getPageTitleFromPage.size(); i++) {
				if ((getPageTitleFromPage.get(i).getAttribute("innerHTML").trim().equals(expectedPageTitle))) {
					ExtentReporter.logger.log(LogStatus.PASS,
							getPageTitleFromPage.get(i).getAttribute("innerHTML").trim()
									+ " is sucessfully displayed.");
					break;
				}
			}
			// if Expected page title is not found then below code will stop the
			// test case and throw the error.
			if (i == getPageTitleFromPage.size()) {
				ExtentReporter.logger.log(LogStatus.FAIL, expectedPageTitle + " Page is NOT displayed");
				assertTrue(false, expectedPageTitle + " Page is NOT displayed");
			}
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.FAIL, " Expected page title is NOT displayed.");
			return "false";
		}
		return "true";
	}

	public String getPageTitleWithPolicyNumber(WebDriver driver, String policyNum) throws InterruptedException {
		Thread.sleep(5000);

		List<WebElement> pageheaders = driver.findElements(By.xpath("//div[@class='pageTitle']"));
		WebElement pageLoader = driver.findElement(By.xpath("//span[@class='txtOrange']"));
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.invisibilityOf(pageLoader));
		Assert.assertEquals(pageheaders.get(1).getAttribute("innerHTML").trim(), "Policy Folder " + policyNum,
				"Page title is not matching.");

		/*
		 * String getPageTitleFromPage =
		 * driver.findElement(By.xpath("//div[@class='pageTitle']"))
		 * .getAttribute("innerHTML").trim(); WebDriverWait wait = new
		 * WebDriverWait(driver, High); // WebDriverWait wait=new
		 * WebDriverWait(driver, 40); WebElement pageLoader =
		 * driver.findElement(By.xpath("//span[@class='txtOrange']"));
		 * wait.until(ExpectedConditions.invisibilityOf(pageLoader)); //
		 * wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.
		 * xpath("//div[@class='pageTitle']"))));
		 * Assert.assertEquals(getPageTitleFromPage, expectedPageTitle,
		 * "Page title is not matching.");
		 */
		return null;
	}

	public String getText(WebDriver driver, WebElement pageElement) {
		WebDriverWait wait = new WebDriverWait(driver, High);
		wait.until(ExpectedConditions.visibilityOf(pageElement));
		return pageElement.getAttribute("innerHTML");
	}

	public void clearTextBox(WebDriver driver, WebElement pageElement, String textField) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, High);
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			Assert.assertTrue(pageElement.isDisplayed(), textField + " is displayed");
			pageElement.clear();
			ExtentReporter.logger.log(LogStatus.PASS, "Cleared the initial contents from " + textField);
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, textField + " element is not found.");
		}
	}

	public String getAttributeValue(WebElement pageElement, String attributeName) {

		return null;
	}

	public void click(WebDriver driver, WebElement pageElement, String ElementName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, High);
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			Assert.assertTrue(pageElement.isDisplayed(), ElementName + " is not displayed on screen.");
			pageElement.click();
			ExtentReporter.logger.log(LogStatus.PASS, "clicked on Element - " + ElementName);
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, ElementName + " element is not found on page.");
		}
	}

	public void visibilityOfElement(WebDriver driver, WebElement pageElement, String text) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, High);
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			Assert.assertTrue(pageElement.isDisplayed(), "Logo / text" + text + " is not displayed on the page.");
			ExtentReporter.logger.log(LogStatus.PASS, "Logo / text" + text + " is displayed on page after login");

		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, text + " is not displayed on page after login");
		}
	}

	public String switchToWindow(WebDriver driver) throws InterruptedException {

		ExtentReporter.logger.log(LogStatus.INFO, "Switching to the pop up window");

		Set<String> handles = driver.getWindowHandles(); // Return a set of
															// window handle

		String parentWindow = driver.getWindowHandle();

		Thread.sleep(5000);
		for (String currentWindow : handles) {
			if (!currentWindow.equals(parentWindow)) {
				driver.switchTo().window(currentWindow);
				driver.manage().window().maximize();
				ExtentReporter.logger.log(LogStatus.INFO, "Control is switched to pop up window");
			}
		}
		Thread.sleep(5000);
		return parentWindow;
	}

	// Select drop down value based on passed parameter driver, element locator
	// for drop down, DropDown Option and lable of drop down.
	public void selectDropdownByValue(WebDriver driver, WebElement element, String DropDownOption, String label) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, High);
			wait.until(ExpectedConditions.visibilityOf(element));
			Select Sel = new Select(element);
			Sel.selectByValue(DropDownOption);
			ExtentReporter.logger.log(LogStatus.PASS, "Value is selected from " + label + " drop down");

		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, "Value is not selected from" + label + "drop down");
		}

	}

	public void selectDropdownByVisibleText(WebDriver driver, WebElement element, String DropDownOption, String label) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, High);
			wait.until(ExpectedConditions.visibilityOf(element));
			Select Sel = new Select(element);
			Sel.selectByVisibleText(DropDownOption);
			ExtentReporter.logger.log(LogStatus.PASS, DropDownOption + "  is selected from " + label + " drop down");

		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, DropDownOption + "   is not selected from" + label + "drop down");
		}

	}

	public void excelRead() throws Exception {
		ExcelUtil exlutil = new ExcelUtil();
	}

	public Boolean verifyValueFromField(WebDriver driver, WebElement pageElement, String expectedValue,
			String attributeName, String fieldName) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, High);
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			Assert.assertEquals(pageElement.getAttribute(attributeName).trim(), expectedValue,
					"Value entered/ selected in " + fieldName + " is NOT as expected. Expected value is "
							+ expectedValue + ". And actual value is  " + pageElement.getAttribute(attributeName).trim()
							+ ".");
			ExtentReporter.logger.log(LogStatus.PASS, expectedValue,
					"Value entered/ selected in " + fieldName + " is as expected. Expected value is " + expectedValue
							+ ". And actual value is  " + pageElement.getAttribute(attributeName).trim() + ".");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.logger.log(LogStatus.FAIL, expectedValue,
					"Value entered/ selected in " + fieldName + " is NOT as expected. Expected value is "
							+ expectedValue + ". And actual value is  " + pageElement.getAttribute(attributeName).trim()
							+ ".");
			return false;
		}

	}

	// This method is called to load a page during navigation through pages

	public void waitForPageLoad(WebDriver driver, int Timeout) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, Timeout);
		wait.until(pageLoadCondition);

	}

	public void waitForElementToLoad(WebDriver driver, int time, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void policySearch(WebDriver driver, String policyNo, WebElement policySearchTxtBox, WebElement searchBtn) {
		clearTextBox(driver, policySearchTxtBox, "Enter Policy text field");
		ExtentReporter.logger.log(LogStatus.INFO, "Click policy in right corner of screen");
		enterTextIn(driver, policySearchTxtBox, policyNo, "Enter Policy text field");
		click(driver, searchBtn, "Search button");
		ExtentReporter.logger.log(LogStatus.INFO, "Enter Policy # into Policy entry box, Click Search.");
	}

	public void invisibilityOfLoader(WebDriver driver) {
		
		try {
			if (verifypageloaderdisplayedornot(driver)==true)
			{
				WebElement pageLoader = driver.findElement(By.xpath("//span[@class='txtOrange']"));
				WebDriverWait wait = new WebDriverWait(driver, High);
				wait.until(ExpectedConditions.invisibilityOf(pageLoader));
				Thread.sleep(2000);
				ExtentReporter.logger.log(LogStatus.PASS, "Page Loader disappeared sucessfully.");
			}
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.WARNING, "Page is taking longer time than usual for loading.");
		}
	}
	
	public boolean verifypageloaderdisplayedornot(WebDriver driver)
	{
		try{
		if(driver.findElement(By.xpath("//span[@class='txtOrange']")).isDisplayed())
		{
			return true;
		}
		else
		{return false;}
		}catch(Exception e)
		{
			ExtentReporter.logger.log(LogStatus.FAIL, "Page Loader is not displayed.");
		}
		return false;
	}

	public void saveOption(WebDriver driver, WebElement saveOptionBtn, WebElement saveAsDropDown,
			WebElement saveOptionOkBtn, String saveOption)
			throws InterruptedException, IllegalArgumentException, IllegalAccessException, SecurityException {
		Thread.sleep(5000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click Save Options");
		waitForElementToLoad(driver, 15, saveOptionBtn);
		clickButton(driver, saveOptionBtn, "Save Option");
		Thread.sleep(4000);
		switchToFrameUsingId(driver, "popupframe1");
		getPageTitle(driver, "Save As");
		selectDropdownByVisibleText(driver, saveAsDropDown, saveOption, "Selected " + saveOption);
		ExtentReporter.logger.log(LogStatus.INFO, "Select " + saveOption + " Click [OK]");
		clickButton(driver, saveOptionOkBtn, "Save");
		switchToParentWindowfromframe(driver);
		Thread.sleep(3000);
	}
}
