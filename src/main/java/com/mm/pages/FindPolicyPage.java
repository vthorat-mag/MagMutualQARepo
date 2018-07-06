package com.mm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mm.utils.ExtentReporter;
import com.mm.dto.FindPolicyPageDTO;
import com.mm.utils.CommonAction;
import com.relevantcodes.extentreports.LogStatus;

public class FindPolicyPage extends CommonAction {

	// Global Assignment/initialization of variables.
	WebDriver driver;
	String policyNo;
	String selectedPhaseValue = "Policy";
	String selectedStatusValue = "Active";

	// Element repository for Find a policy page.
	@FindBy(xpath = "//input[@name='policyPhaseCodeMultiSelectText']")
	WebElement policyPhaseTextBox;

	@FindBy(xpath = "//a[@class='panelDownTitle']")
	WebElement paneDown;

	@FindBy(xpath = "//a[@id='AFD_policyStatus']//img[@id='btnFind']")
	WebElement policyStatusSearch;

	@FindBy(xpath = "//table[@class='popupcontent']//input[@name='chkMultiSelectpolicyStatus']")
	List<WebElement> policyStatusValues;

	@FindBy(xpath = "//a[@id='AFD_policyPhaseCode']")
	WebElement policyPhaseSearch;

	@FindBy(xpath = "//table[@class='popupcontent']//td[2]")
	List<WebElement> policyPhaseValues;

	@FindBy(name = "chkMultiSelectpolicyPhaseCode")
	List<WebElement> policyPhaseCheckBox;

	@FindBy(xpath = "//div[@class='horizontalButtonCollection']//input[@value='Search']")
	WebElement searchBtn;

	@FindBy(xpath = "//table[@class='clsGrid']//tbody//span[@id='CPOLICYNO']")
	List<WebElement> policyList;

	@FindBy(xpath = "//table[@class='clsGrid']//tbody//input[@name='chkCSELECT_IND']")
	List<WebElement> policyListChkBox;

	@FindBy(id = "polPhaseCodeLOVLABELSPAN")
	WebElement selectedPhaseValueEle;

	@FindBy(id = "termStatusLOVLABELSPAN")
	WebElement selectedStatusValueEle;

	// Constructor to initialize elements on Find a policy page.
	public FindPolicyPage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		FindPolicyPageDTO findpolicypagedto = new FindPolicyPageDTO();
	}

	// Search Policy from filter.
	public String findQuotewithActiveState(String phase, String status) throws InterruptedException {
		invisibilityOfLoader(driver);
		try {
			Assert.assertTrue(paneDown.isEnabled());
			ExtentReporter.logger.log(LogStatus.INFO, "Search Criteria Section is expanded");
			click(driver, paneDown, "Search Criteria");
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.INFO, "Search Criteria Section is already Expanded");
		}
		click(driver, policyPhaseSearch, "Policy Phase Search Icon");
		waitFor(driver, 3);
		for (int i = 0; i < policyPhaseCheckBox.size(); i++) {
			if (policyPhaseCheckBox.get(i).getAttribute("value").equals("POLICY")) {
				click(driver, policyPhaseCheckBox.get(i),
						policyPhaseCheckBox.get(i).getAttribute("value") + " Check Box");
			}
			if (policyPhaseCheckBox.get(i).getAttribute("value").equals(phase)) {
				click(driver, policyPhaseCheckBox.get(i), "Policy phase Check Box");
			}
		}
		click(driver, policyStatusSearch, "Policy Status Search");
		for (int i = 0; i < policyStatusValues.size(); i++) {
			if (policyStatusValues.get(i).getAttribute("value").equals(status)) {
				click(driver, policyStatusValues.get(i),
						policyStatusValues.get(i).getAttribute("value") + " Check Box");
			}
		}
		click(driver, searchBtn, "SearchButton");
		waitFor(driver, 5);
		try {
			if (policyList.size() > 0) {
				for (int i = 0; i < policyList.size(); i++) {
					if (policyList.get(i).getAttribute("innerHTML").contains("NB19")
							&& policyList.get(i).getAttribute("innerHTML").contains("-01")) {
						policyNo = policyList.get(i).getAttribute("innerHTML");
						break;
					}
				}
			}
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.FAIL, "No Policy available for this Criteria.");
		}
		return policyNo;
	}

	public RateApolicyPage searchFromFindPolicyPage() throws Exception {
		invisibilityOfLoader(driver);
		ExtentReporter.logger.log(LogStatus.INFO, "click Policy>Find Policy Select Status: Active Click [Search]");
		// Below code will verify if search pane is expanded or not.
		try {
			Assert.assertTrue(paneDown.isEnabled());
			click(driver, paneDown, "Search Criteria");
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.PASS, "Search Criteria Section is already expanded.");
		}

		// below code will select phase from Policy Phase drop down.
		click(driver, policyPhaseSearch, "Policy Phase Search Icon");
		waitFor(driver, 3);
		for (int i = 0; i < policyPhaseCheckBox.size(); i++) {
			if (policyPhaseCheckBox.get(i).getAttribute("value").equals("POLICY")) {
				clickButton(driver, policyPhaseCheckBox.get(i), FindPolicyPageDTO.phase + " Check Box");
			}
		}
		for (int i = 0; i < policyPhaseCheckBox.size(); i++) {
			if (policyPhaseValues.get(i).getAttribute("innerHTML").equals(FindPolicyPageDTO.phase )) {
				clickButton(driver, policyPhaseCheckBox.get(i), FindPolicyPageDTO.phase  + " Check Box");
				ExtentReporter.logger.log(LogStatus.PASS, FindPolicyPageDTO.phase  + " is  selected from Policy phase drop down");
				break;
			}
		}

		// below code will select Status from Status drop down.
		Thread.sleep(2000);
		clickButton(driver, policyStatusSearch, "Policy Status Search");
		for (int i = 0; i < policyStatusValues.size(); i++) {
			if (policyStatusValues.get(i).getAttribute("value").equals(FindPolicyPageDTO.status)) {
				click(driver, policyStatusValues.get(i),
						policyStatusValues.get(i).getAttribute("value") + " Check Box");
				ExtentReporter.logger.log(LogStatus.PASS, FindPolicyPageDTO.status + " is  selected from Status drop down");
			}
		}
		click(driver, searchBtn, "SearchButton");
		invisibilityOfLoader(driver);

		// Below code will check if policies are displayed after filter.search
		// and will open/select policy.
		try {
			if (policyList.size() > 0) {
				for (int i = 0; i < policyList.size(); i++) {
					ExtentReporter.logger.log(LogStatus.INFO, "Select Policy with Policy No." +policyList.get(i).getAttribute("innerHTML"));
					clickButton(driver, policyList.get(i), "Policy Name");
					break;
				}
			}
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.FAIL, "No Policy available for this Criteria.");
		}

		invisibilityOfLoader(driver);

		// Below line of code will verify Selected phase value is correct or
		// not.
		verifyValueFromField(driver, selectedPhaseValueEle, selectedPhaseValue, "innerHTML","Phase Value");

		// Below line of code will verify Selected status value is correct or
		// not.
		verifyValueFromField(driver, selectedStatusValueEle, selectedStatusValue, "innerHTML","Status Value");
		return new RateApolicyPage(driver);
	}

}
