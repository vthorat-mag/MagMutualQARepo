package com.mm.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mm.utils.ExtentReporter;
import com.mm.utils.PDFReader;
import com.mm.dto.PolicyQuotePageDTO;
import com.mm.utils.CommonAction;
import com.relevantcodes.extentreports.LogStatus;

public class PolicyQuotePage extends CommonAction {
	
	WebDriver driver;
	PolicyQuotePageDTO policyquotepageDTO;
	
	String valueOfPolicyActionCopy = "javascript:copyQuote();";
//	String saveAsPolicyValue="OFFICIAL";
	String QuotePhaseValue="QUOTE";
	String ProductNotifyValue="Y";
	
	
	
	@FindBy(name="globalSearch")
	WebElement Policy_Search;

	@FindBy(name="search")
	WebElement Search_btn;
	
	@FindBy(id="pageTitleForpageHeaderForPolicyFolder")
	WebElement pageHeaderForPolicyFolder;
	
	@FindBy(xpath="//div[@id='globalDropdownActionItems']//select[@class='globalActionItemList']")
	WebElement policyAction;
	
	@FindBy(id="PM_CPT_TRAN_OK")
	WebElement capt_Tranx_Ok;
	
	@FindBy(xpath="//a[@id='PM_PT_VIEWCVG']//span[@class='tabWithNoDropDownImage']")
	WebElement coverageTab;
	
	@FindBy(xpath = "//table[@id='coverageListGrid']//tbody//td//div[@id='CPRODUCTCOVERAGEDESC']")
	List<WebElement> coverageList;
	
	@FindBy(xpath = "//input[@id='PM_QT_MANU_PUP']")
	WebElement optionalFormBtn;
	
	@FindBy(xpath = "//table[@id='maintainManuscriptListGrid']")
	WebElement 	manuscriptList;
	
	@FindBy(xpath="//table[@id='maintainManuscriptListGrid']//div[@id='CFORMTYPECODELOVLABEL']")
	WebElement manuscriptPageFirstItem;
	
	@FindBy(id="PM_MANU_DELETE")
	WebElement manuscriptPageDeleteBtn;
	
	@FindBy(id="PM_MANU_ADD")
	WebElement manuscriptPageAddBtn;
	
	@FindBy(xpath="//table[@id='selectManuscriptGrid']//tr//div[@id='CSHORTDESCRIPTION']")
	List<WebElement> manuscriptAddListformName;
	
	@FindBy(xpath="//table[@id='selectManuscriptGrid']//tr//input[@name='chkCSELECT_IND']")
	List<WebElement> manuscriptAddListformNameChkBox;
	
	@FindBy(id="PM_SEL_MANU_DONE")
	WebElement manuscriptAddListDoneBtn;
	
	@FindBy(id="PM_MANU_SAVE")
	WebElement manuscriptPageSaveBtn;
	
	@FindBy(id="PM_MANU_CLOSE")
	WebElement manuscriptPageCloseBtn;
	
	@FindBy(id="PM_COMMON_TABS_RATE")
	WebElement rateBtn;
	
	@FindBy(id="PM_VIEW_PREM_CLOSE")
	WebElement closeBtnOnViewPremiumPopup;
	
	@FindBy(name="workflowExit_Ok")
	WebElement okPolicySaveAsWIPPopup;
	
	@FindBy(id="PM_COMMON_TABS_SAVE")
	WebElement saveOptionBtn;
	
	@FindBy(xpath="//select[@name='saveAsCode']")
	WebElement saveAsDropDown;
	
	@FindBy(id="PM_SAVE_OPTION_OK")
	WebElement saveOptionOkBtn;
	
	@FindBy(name="workflowExit_Ok")
	WebElement Exit_Ok;
	
	@FindBy(name="policyPhaseCode")
	WebElement policyPhase;
	
//	@FindBy(xpath="//select[contains(@name,'confirmed')]")
	@FindBy(name="483865737.confirmed")
	WebElement productNotifyDropDown;
	
	@FindBy(id="PM_NOTIFY_CLOSE")
	WebElement prodNotifyClose;
	
	@FindBy(name="additionalText")
	WebElement addText;
	
	@FindBy(id="PM_COMMON_TABS_PREVIEW")
	WebElement PreviewTab;
	
	@FindBy(xpath="//div[@aria-describedby='newpopup1']")
	WebElement pdfPane;
	
	@FindBy(xpath = "//select[@name='policyNavLevelCode']//option[@value='RISK']")
	WebElement verifyRisk;
	
	@FindBy(xpath="//span[@class='txtOrange']")
	WebElement loader;
	
	
	
	
	public PolicyQuotePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Identify Policy number from page.
	public String policyNo()
	{
		String profileNoLable = pageHeaderForPolicyFolder.getAttribute("innerHTML");
		String[] portfolioNo = profileNoLable.split(" ",3);
		return portfolioNo[2];
	}
	
	//Select Copy to action from "Action DropDown".
	public PolicyQuotePage CopyOptionFromActionDropDown() throws InterruptedException
	{
		Thread.sleep(4000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click Policy Actions>Copy>Ok");
		selectDropdownByValue(driver, policyAction, policyquotepageDTO.valueOfPolicyActionCopy, "Policy Action");
		return new PolicyQuotePage(driver);
	}
	
	//Search Policy from Search Policy text field.
	public RateApolicyPage searchPolicy( String policy_no) throws Exception
	{
		Thread.sleep(3000);
		policySearch(driver, policy_no,Policy_Search, Search_btn);
		String actual=getText(driver,pageHeaderForPolicyFolder);
		ExtentReporter.logger.log(LogStatus.INFO, "Policy # dispalys correctly under Policy Folder");
		Assert.assertEquals(actual, "Policy Folder "+policy_no, "The policy "+policy_no+" is Not available.");
		Thread.sleep(3000);
		return new RateApolicyPage(driver);
	}
	
	//Coverage details flow.
	public void coverageDetailsSelect() throws InterruptedException
	{
		Thread.sleep(3000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click Coverage tab");
		clickButton(driver, coverageTab, "Coverage");
		Thread.sleep(3000);
		Assert.assertEquals(coverageList.get(0).getAttribute("innerHTML"), "Primary", "Coverage for Primary Risk is NOT displayed");
	}
	
	//Coverage Update flow.
	public void coverageUpdates(String CoverageName, String binderForm, String PolicyNo) throws Exception
	{
		for (int i = 0; i<coverageList.size();i++)
		{
			if(coverageList.get(i).getAttribute("innerHTML").equals(CoverageName))
			{
				ExtentReporter.logger.log(LogStatus.INFO, "Select"+ CoverageName +" Coverage.");
				clickButton(driver, coverageList.get(i),coverageList.get(i).getAttribute("innerHTML"));
				Thread.sleep(4000);
				break;
			}
		}
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Optional Forms]");
		clickButton(driver, optionalFormBtn, "Optional Form");
		getPageTitle(driver,"Manuscript Information");
		Thread.sleep(4000);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+PolicyNo+"')]")));
		if (manuscriptList.isDisplayed())
		{
			String firstManuScriptInfoName = manuscriptPageFirstItem.getAttribute("innerHTML");
			ExtentReporter.logger.log(LogStatus.INFO, "Delete current Indication form, Are you sure you want to delete this? Click Ok");
			clickButton(driver, manuscriptPageDeleteBtn, "Manu script Delete");
			driver.switchTo().alert().accept();
			//Verify first item displayed in manu script list is not displayed in list.
			Assert.assertEquals(manuscriptPageFirstItem.getAttribute("innerHTML"),firstManuScriptInfoName,"Manuscript lsit first item "+firstManuScriptInfoName+" is not deleted.");
			Thread.sleep(2000);
			ExtentReporter.logger.log(LogStatus.INFO, "Click [Add].");
			clickButton(driver, manuscriptPageAddBtn, "Manu script Add");
			
		}else{
			ExtentReporter.logger.log(LogStatus.INFO, "Click [Add].");
			clickButton(driver, manuscriptPageAddBtn, "Manu script Add");
		}
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+PolicyNo+"')]")));
		Thread.sleep(3000);
		for(int i=0;i<manuscriptAddListformName.size();i++)
		{
			if(manuscriptAddListformName.get(i).getAttribute("innerHTML").equals(binderForm))
			{
				ExtentReporter.logger.log(LogStatus.INFO, "Select "+ binderForm +", Click done.");
				clickButton(driver, manuscriptAddListformNameChkBox.get(i), "check Box for "+binderForm);
				break;
			}
		}
		clickButton(driver, manuscriptAddListDoneBtn, "Done");
		Thread.sleep(2000);
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+PolicyNo+"')]")));
		enterTextIn(driver, addText, binderForm+" form added.", "Aditional Text");
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+PolicyNo+"')]")));
		ExtentReporter.logger.log(LogStatus.INFO, "Enter additional text: "+ binderForm+" form added  Click [Save] and Click [Close].");
		clickButton(driver, manuscriptPageSaveBtn, "Manu Script page Save");
		Thread.sleep(2000);
		clickButton(driver, manuscriptPageCloseBtn, "Manu Script page Close");
		switchToParentWindowfromframe(driver);
	}
	
	//Rate A functionality flow.
	public PolicyQuotePage rateFunctionality(String policyNo) throws Exception
	{
		Thread.sleep(3000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Rate]");
		clickButton(driver, rateBtn, "Rate Tab");
		Thread.sleep(4000);
		/*try{
			switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+policyNo+"')]")));
			selectDropdownByValue(driver, productNotifyDropDown, ProductNotifyValue, "product notify");
			Thread.sleep(1000);
			clickButton(driver, prodNotifyClose, "Product Notify Close");
			ExtentReporter.logger.log(LogStatus.INFO, "Product Notify Window dispalyed to user.");
			ExtentReporter.logger.log(LogStatus.PASS, " Yes selection from Product Notify dorp down.");
		}catch (Exception e)
		{
			ExtentReporter.logger.log(LogStatus.FAIL, "Product Notify Window is NOT dispalyed to user.");
		}*/
		Thread.sleep(2000);
		//switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+policyNo+"')]")));
		//switchToFrameUsingId(driver,"popupframe1");
		Thread.sleep(2000);
		//Verify page title for View Premium[Rate Btn.]
		getPageTitle(driver, "View Premium");
		clickButton(driver, closeBtnOnViewPremiumPopup, "Close");
		Thread.sleep(2000);
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'policyNo="+policyNo+"')]")));
		clickButton(driver, okPolicySaveAsWIPPopup, "Ok");
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Close] click [Ok]");
		switchToParentWindowfromframe(driver);
		return new PolicyQuotePage(driver);
	}
	

	//Save option functionality flow.
	//We need to call multiple times with different values, so we are passing values in test case call 
	public PolicyQuotePage saveOption(String saveAsPolicyValue) throws InterruptedException
	{
		Thread.sleep(5000);
		ExtentReporter.logger.log(LogStatus.INFO, "Click Save Options");
		waitForElementToLoad(driver, 15, saveOptionBtn);
		clickButton(driver, saveOptionBtn, "Save Option");
		Thread.sleep(4000);
		switchToFrameUsingId(driver, "popupframe1");
		getPageTitle(driver, "Save As");
		selectDropdownByVisibleText(driver, saveAsDropDown, saveAsPolicyValue, "Selected "+saveAsPolicyValue);
		//Verify Save as value selected from DDL is correct
		//verifyValueFromField(driver, saveAsDropDown, policyquotepageDTO.saveAsPolicyValue,"value");
		ExtentReporter.logger.log(LogStatus.INFO,  "Select Click [OK]");
		clickButton(driver, saveOptionOkBtn, "Save");
		switchToParentWindowfromframe(driver);
		Thread.sleep(3000);
		return new PolicyQuotePage(driver);
}

	public PolicyQuotePage product_Notify() throws InterruptedException{
		
			switchToFrameUsingId(driver, "popupframe1");
			waitForElementToLoad(driver, 10, productNotifyDropDown);
			selectDropdownByValue(driver, productNotifyDropDown, policyquotepageDTO.productNotifyValue, "product notify");
			//Verify Notify value selected from DDL is correct
		//	verifyValueFromField(driver, productNotifyDropDown, policyquotepageDTO.productNotifyValue,"value");
			Thread.sleep(1000);
			ExtentReporter.logger.log(LogStatus.INFO, "Product Notify Window dispalyed to user.");
			clickButton(driver, prodNotifyClose, "Product Notify Close");
			ExtentReporter.logger.log(LogStatus.PASS, " Yes selected from Product Notify drop down list.");
		//	switchToParentWindowfromframe(driver);
			
	/*	}catch(Exception e)
		{
			ExtentReporter.logger.log(LogStatus.INFO, "Product Notify Window is NOT dispalyed to user.");
		//	switchToParentWindowfromframe(driver);
		}*/
			
		return new PolicyQuotePage(driver);
	}
	
	//Change phase to Quote.
	public PolicyQuotePage changePhaseToQuote() throws InterruptedException
	{
		Thread.sleep(4000);
		ExtentReporter.logger.log(LogStatus.INFO, "Change Phase from Indication to Quote");
		selectDropdownByValue(driver, policyPhase, policyquotepageDTO.quotePhaseValue, "Phase");
		//Verify phase value  selected from DDL is correct
	//	verifyValueFromField(driver, policyPhase, policyquotepageDTO.quotePhaseValue,"value");
		return new PolicyQuotePage(driver);
	}
	
	//Click preview tab.
	public PDFReader clickPreviewTab() throws InterruptedException
	{
		invisibilityOfLoader(driver, loader); 
		Thread.sleep(3000);
		ExtentReporter.logger.log(LogStatus.INFO, "Verify CHG 08 form is displayed and information that was entered is on form");
		clickButton(driver, PreviewTab, "Preview");
		invisibilityOfLoader(driver, loader);
		Thread.sleep(8000);
		
		return new PDFReader(driver);
	}

		
	//Select the policy Action from DDL
	public PolicyQuotePage select_policyAction() throws InterruptedException{
		
	//	waitForElementToLoad(driver, 15, policyAction);
		Thread.sleep(5000);
		ExtentReporter.logger.log(LogStatus.INFO, "Capture Transaction Details window opens");
		selectDropdownByVisibleText(driver, policyAction, policyquotepageDTO.policyActionValue, "Renewal");
		Thread.sleep(3000);
		switchToFrameUsingId(driver, "popupframe1");
		return new PolicyQuotePage(driver);
		
		//ToDo- Enter Quote Description- Renewal
	}
		
	//Switch to second frame from first frame
	public PolicyQuotePage switchToNextFrame() throws InterruptedException{
		
		//switchToParentWindowfromframe(driver);
		
		List<WebElement> secondFrame = driver.findElements(By.id("popupframe2"));
     	driver.switchTo().frame(secondFrame.get(0));
     	return new PolicyQuotePage(driver);
	}
	
	//Save the Transaction details and switch to parent window
	public PolicyQuotePage save_CaptureTransactionDetails () throws InterruptedException{
		
		Thread.sleep(3000);
		click(driver, capt_Tranx_Ok, "Ok button for Renewal");
		switchToParentWindowfromframe(driver);
		return new PolicyQuotePage(driver);
	}
		
	//Close the exit window 
	public PolicyQuotePage exit_SaveOption() throws InterruptedException{
			
		waitForElementToLoad(driver, 10, Exit_Ok);
		ExtentReporter.logger.log(LogStatus.INFO, "Click [OK]");
		clickButton(driver, Exit_Ok, "Exit Ok");
		return new PolicyQuotePage(driver);
	}
}
