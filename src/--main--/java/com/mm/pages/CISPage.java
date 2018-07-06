package com.mm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mm.utils.ExcelUtil;
import com.mm.utils.ExtentReporter;
import com.relevantcodes.extentreports.LogStatus;
import com.mm.dto.CISPageDTO;
import com.mm.utils.CommonAction;
import com.mm.utils.CommonUtilities;

import BaseClass.CommonActionInterface;

public class CISPage extends CommonAction {

	WebDriver driver;
	CISPageDTO oCISPageDTO;

	@FindBy(id = "CI_NEW_ORG")
	WebElement New_Org;

	@FindBy(name = "entity_veryLongName")
	WebElement Long_Name;

	@FindBy(name = "entity_organizationName")
	WebElement CIS_OrgName;

	@FindBy(name = "address_addressTypeCode")
	WebElement Addr_Type;

	@FindBy(name = "address_addressLine1")
	WebElement Addr_Line1;

	@FindBy(name = "address_addressLine2")
	WebElement Addr_Line2;

	@FindBy(name = "address_city")
	WebElement Addr_City;

	@FindBy(name = "address_stateCode")
	WebElement State;

	@FindBy(name = "address_zipCode")
	WebElement zipCode;

	@FindBy(xpath = "//input[@type='button' and @value='OK']")
	WebElement OK;

	@FindBy(name = "phoneNumber_phoneNumber_DISP_ONLY")
	WebElement Ph_no;

	@FindBy(name = "phoneNumber_areaCode")
	WebElement AreaCode;

	@FindBy(name = "entityClass_entityClassCode")
	WebElement Classification;

	@FindBy(name = "entityClass_effectiveToDate")
	WebElement Eff_To_Date;

	@FindBy(id = "CI_ENTADDOU_SAV")
	WebElement Save_btn;

	// Locators for TC42253 Verify CIS Page Displays

	@FindBy(name = "entitySearch_lastOrOrgName")
	WebElement clientLastName;

	@FindBy(name = "entity_firstName")
	WebElement clientFirstName;

	@FindBy(id = "CI_ENSRC_SEARCH")
	WebElement searchButton;

	@FindBy(xpath = "//span[@id='CCLIENT_NAME']")
	List<WebElement> clientNameEntityList;

	@FindBy(xpath = "//div[@id='CCLIENT_ID']") // id= 0003683482- Automation QA
	List<WebElement> clientIDEntityList;

	@FindBy(id = "panelTitleForEntityList")
	WebElement entityListPanelTitle;

	@FindBy(id = "CI_SUMMARY_TG")
	WebElement summaryMainTab;

	@FindBy(xpath = "//span[@class='selectedTabWithDropDownImage']")
	WebElement selectedMainTab;

	@FindBy(xpath = "//ul[@id='tabSubMenuForCI_SUMMARY_TG']//li//a//span")
	List<WebElement> summaryDDMenu;

	@FindBy(id = "CI_SUMMARY_MI")
	WebElement summaryTab;

	@FindBy(xpath = "//td[@class='tabTitle']//b")
	WebElement displayedWindowTitle;

	@FindBy(id = "CI_DEMO_TG")
	WebElement demographicMainTab;

	@FindBy(xpath = "//ul[@id='tabSubMenuForCI_DEMO_TG']//li//a//span")
	List<WebElement> demographicDDMenu;

	@FindBy(xpath = "//span[@class='selectedTabWithDropDownImage']")
	WebElement selectedDemographicTab;

	@FindBy(id = "CI_BG_TG")
	WebElement backgroundMainTab;

	@FindBy(xpath = "//ul[@id='tabSubMenuForCI_BG_TG']//li//a//span")
	List<WebElement> backgroundDDMenu;

	@FindBy(xpath = "//span[@class='selectedTabWithDropDownImage']")
	WebElement selectedBackgroundTab;

	@FindBy(id = "CI_REL_TG")
	WebElement relationMainTab;

	@FindBy(xpath = "//ul[@id='tabSubMenuForCI_REL_TG']//li//a//span")
	List<WebElement> relationDDMenu;

	@FindBy(xpath = "//span[@class='selectedTabWithDropDownImage']")
	WebElement selectedRelationTab;

	@FindBy(id = "CI_VENDOR_TG")
	WebElement vendorMainTab;
	
	@FindBy(xpath="//a[@id='CI_MNT_AGENT']//span")
	WebElement agencyMainTab;

	@FindBy(xpath = "//ul[@id='tabSubMenuForCI_VENDOR_TG']//li//a//span")
	List<WebElement> vendorDDMenu;

	@FindBy(xpath = "//span[@class='selectedTabWithDropDownImage']")
	WebElement selectedVendorTab;

	@FindBy(xpath = "//span[@class='txtOrange']")
	WebElement pageLoader;

	@FindBy(id = "pageTitleForpageHeader")
	WebElement entityListPageTitle;

	@FindBy(id = "pageTitleForpageHeader")
	WebElement pageTitleForPersonInfoPage;

	
	// Constructor to initialize driver, page elements and DTO PageObject for CISPage
	public CISPage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		oCISPageDTO = new CISPageDTO();
	}

	// Search and Select the client name from the Entity List
	public CISPage searchAndSelectAClientName() throws Exception {

		waitForElementToLoad(driver, 10, clientLastName);
		enterTextIn(driver, clientLastName, oCISPageDTO.clientLastName, "Last/Org Name");
		enterTextIn(driver, clientFirstName, oCISPageDTO.clientFirstName, "First Name");
		clickButton(driver, searchButton, "Search");
		ExtentReporter.logger.log(LogStatus.INFO, "Search results returned");
		// getPageTitle(driver, entityListPageTitle);

		// invisibilityOfLoader(driver, pageLoader);
		Thread.sleep(3000);

		// Searching the client using client name and matching id
		for (int i = 0; i < clientNameEntityList.size(); i++) {

			// compare the client name with the client id and select client name whose id matches
			if (clientNameEntityList.get(i).getAttribute("innerHTML").trim().equals(oCISPageDTO.clientNameValue)
					&& clientIDEntityList.get(i).getAttribute("innerHTML").trim().equals(oCISPageDTO.clientIDValue)) {
				ExtentReporter.logger.log(LogStatus.INFO, "CIS Demographic Client screen opens");
				selectValue(driver, clientNameEntityList.get(i), oCISPageDTO.clientNameValue);
			} else {

				break;
			}
		}

		return new CISPage(driver);
	}

	
	//Verify that all main tabs having menu options are opened and page title is verified
	public CISPage verifyPagesHavingMenuOnPersonPageAreDisplayed() throws Exception {
		Thread.sleep(3000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		
		//Get list of main tabs from Excel sheet column
		for (int i = 0; i < oCISPageDTO.mainTabList.size(); i++) 
		{
			//Get the list of all sub tabs under the selected main tab from application
			List<WebElement> tabMenuOption = driver.findElements(By.xpath("//ul[@id='tabSubMenuForCI_" + oCISPageDTO.mainTabList.get(i).trim() + "_TG']//span"));
			
			//Get the count of all sub tabs under selected main tab
 			for (int j = 0; j < tabMenuOption.size(); j++)
			{
 				//Get list of all sub tabs from excel sheet
 				for (int k = 0; k < oCISPageDTO.allMenuOptions.size(); k++) {
 				
 				//verify if the main tab displayed is selected
				if(verifyelementDisplay(selectedMainTab)==true)
				{
					//Reinitialize the tab menu options elements to avoid stale element
					List<WebElement> tabMenuOption1 = driver.findElements(By.xpath("//ul[@id='tabSubMenuForCI_" + oCISPageDTO.mainTabList.get(i).trim() + "_TG']//span"));
					
					//compare if tab menu text is equal to menu option from excel sheet
					if (tabMenuOption1.get(j).getAttribute("innerHTML").equalsIgnoreCase(oCISPageDTO.allMenuOptions.get(k)))
					{
					waitForElementToLoad(driver, 10, selectedMainTab);
					JavascriptExecutor executor1 = (JavascriptExecutor) driver;
					//click on tab main tab and then click on tab menu
					executor1.executeScript("arguments[0].click();", selectedMainTab);
					ExtentReporter.logger.log(LogStatus.INFO, oCISPageDTO.allMenuOptions.get(k)+" window opens");
					executor1.executeScript("arguments[0].click();", tabMenuOption1.get(j));
					invisibilityOfLoader(driver, pageLoader);
					Thread.sleep(1000);
					//verifying the page title of an open window
					verifyPageTitleForTheOpenWindow(displayedWindowTitle,
							oCISPageDTO.windowTitlesForSubMenuTabs.get(k) + " " + oCISPageDTO.clientNameValue,
							oCISPageDTO.allMenuOptions.get(k));
					}
				}else{
				if (tabMenuOption.get(j).getAttribute("innerHTML").equals(oCISPageDTO.allMenuOptions.get(k)))
				{
					//click on tab menu option (when main tab is not in selected mode) 
					ExtentReporter.logger.log(LogStatus.INFO, oCISPageDTO.allMenuOptions.get(k)+" window opens");
					executor.executeScript("arguments[0].click();", tabMenuOption.get(j));
					invisibilityOfLoader(driver, pageLoader);
					//verifying the page title of an open window
					verifyPageTitleForTheOpenWindow(displayedWindowTitle,oCISPageDTO.windowTitlesForSubMenuTabs.get(k) + " " + oCISPageDTO.clientNameValue,
							oCISPageDTO.allMenuOptions.get(k));
				}
				}
 			 }
		  }
   }
		return new CISPage(driver);
}

	//Verify that main tab not having menu options is opened and page title is verified
	public CISPage verifyPagesWithoutSubMenu() throws Exception{
		
		//Get the list and count of all menus from excel sheet list
		for (int i = 0; i < oCISPageDTO.allMenuOptions.size(); i++) {
			
			//compare tab name with names from excel column 
			Thread.sleep(1000);
			if (agencyMainTab.getAttribute("innerHTML").equalsIgnoreCase(oCISPageDTO.allMenuOptions.get(i))){
			try{
				//click on tab name when it matches with name from excel sheet
				JavascriptExecutor executor1 = (JavascriptExecutor) driver;
				ExtentReporter.logger.log(LogStatus.INFO,oCISPageDTO.allMenuOptions.get(i)+" window opens" );
				executor1.executeScript("arguments[0].click();", agencyMainTab);
				Thread.sleep(2000);
				//verify the page title when the tab window opens
				verifyPageTitleForTheOpenWindow(displayedWindowTitle,
						oCISPageDTO.windowTitlesForSubMenuTabs.get(i) + " " + oCISPageDTO.clientNameValue,
						oCISPageDTO.allMenuOptions.get(i));
				break;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}			
		return new CISPage(driver);
}

	
	// Verify the page title for the open window, this method is called after each tab that is selected
	public void verifyPageTitleForTheOpenWindow(WebElement tabTitleElement, String tabTitle, String tabName) {

		waitForElementToLoad(driver, 10, tabTitleElement);
		try {
			//verify if tab title matches with tab title from excel sheet and log message
			if (tabTitleElement.getAttribute("innerHTML").trim().equals(tabTitle)) {
				ExtentReporter.logger.log(LogStatus.PASS,
						tabName + " page title is as expected i.e. " + tabTitleElement.getAttribute("innerHTML").trim());
			}
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.FAIL, tabName + " page title did not match, expected was " + tabTitle
					+ " but found " + tabTitleElement.getAttribute("innerHTML").trim());
		}
	}
	
	
	// verify that Menu tab is displayed and return boolean value
	public Boolean verifyelementDisplay(WebElement pageElement) {
		try {
			if (pageElement.isEnabled())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}


	// Navigate to Add Organization details page
	public CISPage clickOnNewOrganization() throws Exception {
		click(driver, New_Org, "New Organization tab");
		return new CISPage(driver);
	}

	// Enter data in fields for new organization
	public CISPage enterDataInNewOrgPage() throws Exception {

		enterTextIn(driver, Long_Name, oCISPageDTO.LongName, "Long Name");
		click(driver, CIS_OrgName, "Org Name text field");
		enterTextIn(driver, Addr_Line1, oCISPageDTO.Address_Line1, "Adress Line");
		enterTextIn(driver, Addr_City, oCISPageDTO.City, "City");
		enterTextIn(driver, Ph_no, oCISPageDTO.Phone_no, "Phone Number");
		enterTextIn(driver, AreaCode, oCISPageDTO.Area_code, "Area Code");
		Thread.sleep(2000);
		enterTextIn(driver, Eff_To_Date, oCISPageDTO.Class_Eff_To_Date, "Class_Eff_To_Date");
		selectDropdownByValue(driver, Classification, oCISPageDTO.Classification, "Classification");
		selectDropdownByValue(driver, Addr_Type, oCISPageDTO.Addr_Type, "Address_Type");
		selectDropdownByValue(driver, State, oCISPageDTO.State, "State");
		Thread.sleep(3000);

		return new CISPage(driver);
	}

	// Select zip code for the new organization from the new pop up window using
	// window handle
	// And close the zip code window and navigate back to parent window
	public CISPage selectZipCode() throws Exception {
		String parentwindow = switchToWindow(driver);
		Thread.sleep(2000);
		WebElement zipCodeValue = driver.findElement(By.xpath("//input[@value='" + oCISPageDTO.zipCode + "']"));
		click(driver, zipCodeValue, "ZipCode");
		click(driver, OK, "OK button");
		Thread.sleep(2000);
		switchToParentWindowfromotherwindow(driver, parentwindow);
		waitForElementToLoad(driver, 10, zipCode);
		// Verify that selected zip code is added to zipcode field.
		Assert.assertTrue(zipCode.getText().trim() == oCISPageDTO.zipCode);
		return new CISPage(driver);
	}

	
	
	// Save the details for new organization
	public void saveNewOrgDetails() throws InterruptedException {
		Thread.sleep(3000);
		click(driver, Save_btn, "Save button");
	}
}
