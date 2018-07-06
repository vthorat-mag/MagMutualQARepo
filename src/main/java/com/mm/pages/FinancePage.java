package com.mm.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mm.dto.FinancePageDTO;
import com.mm.dto.FindPolicyPageDTO;
import com.mm.utils.CommonAction;

public class FinancePage extends CommonAction {

	WebDriver driver;
	FinancePageDTO financePageDTO;
	static String batchNumber;
	static String accountNumber;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	String accountSearchPageTitle = "Account Search";
	String allTxnInquireyPageTitle = "All Transactions Inquiry";
	String validateBatchPageTitle = "Validate Batch";
	String cashEntryPageTitle = "Cash Entry";
	String batchFunctionPageTitle = "Batch Functions";
	String invoiceOptionDDLValue = "I";
	String createChargesDDLValue = "N";
	String specifyInvoiceDateDDLValue = "N";
	String specifyDueDateDDLValue = "N";
	String paymentTypeDDLValue = "CHECK";
	String BatchAmount = "1";
	String BatchNoPayment = "1";
	String validlateFieldExpectedValue = "VALIDATE";
	String validlateFieldAttributeValue = "innerHTML";
	String validlateFieldName = "Validate";

	@FindBy(name = "globalSearch")
	WebElement Policy_Search;

	@FindBy(id = "FM_ACCOUNT_LIST_SEARCH")
	WebElement Search_btn;

	@FindBy(name = "policyNo")
	WebElement PolicyNoTxtBox;

	@FindBy(xpath = "//a[@id='URL_CACCOUNTNO']")
	List<WebElement> accountList;
	
	@FindBy(xpath = "//a[@id='URL_CACCOUNTNO']//span")
	List<WebElement> accountNumList;
	

	@FindBy(xpath = "//li[@id='FM_BILLING_ADMIN']//a[@class='fNiv isParent']//span")
	WebElement billingAdminMenu;

	@FindBy(xpath = "//li[@id='FM_ON_DEMAND_INVOICE']//a")
	WebElement onDemandInvoiceMenuOption;

	@FindBy(xpath = "//li[@id='FM_CASH_APPLICATION']//span")
	WebElement paymentsMenu;

	@FindBy(xpath = "//li[@id='FM_CASH_ENTRY']//span")
	WebElement cashEntryMenuOption;

	@FindBy(xpath = "//li[@id='FM_BATCH_FUNCTION']//span")
	WebElement batchFunctionMenuOption;

	@FindBy(name = "invoiceOption")
	WebElement invoiceOptionDDL;

	@FindBy(name = "createCharges")
	WebElement createChargesDDL;

	@FindBy(name = "specifyInvoiceDate")
	WebElement specifyInvoiceDateDDL;

	@FindBy(name = "specifyDueDate")
	WebElement specifyDueDateDDL;

	@FindBy(name = "paymentTypeCode")
	WebElement paymentTypeDDL;

	@FindBy(id = "FM_ON_DEMAND_INV_PROCESS")
	WebElement processButton;

	@FindBy(id = "amountROSPAN")
	WebElement invoiceAmt;

	@FindBy(id = "invoiceNoROSPAN")
	WebElement invoiceNo;

	@FindBy(id = "accountNoCriteriaROSPAN")
	WebElement accountNo;

	@FindBy(id = "FM_CONFIRM_INVOICE_JUMP")
	WebElement jumpButton;

	@FindBy(id = "FM_CE_BATCH_NEW")
	WebElement newButton;

	@FindBy(name = "invoiceNo")
	WebElement invoiceNoOnCashEntryPage;

	@FindBy(name = "receiptNo")
	WebElement checkNoOnCashEntryPage;

	@FindBy(name = "receiptAmount")
	WebElement amountOnCashEntryPage;

	@FindBy(id = "FM_CE_BATCH_SAVE")
	WebElement saveBtnOnCashEntryPage;

	@FindBy(id = "batchNoROSPAN")
	WebElement batchNo;

	@FindBy(id = "CBATCHNO")
	List<WebElement> batchNoOnBatchFunPage;

	@FindBy(id = "FM_CASH_BATCH_LOG_VALIDAT")
	WebElement validateBatchBtn;

	@FindBy(id = "noOfPayment")
	WebElement numberOfPaymentTxtBox;

	@FindBy(id = "batchAmount")
	WebElement batchAmount;

	@FindBy(id = "FM_VALIDATE_BATCH_DONE")
	WebElement doneBatchPopUp;

	@FindBy(id = "FM_CASH_BATCH_LOG_POST")
	WebElement postBatchBtn;

	@FindBy(id = "CBATCHSTATUSCODE")
	WebElement validateField;

	@FindBy(id = "currentAccountBalanceROSPAN")
	WebElement currBalOnAllTxnEnqPage;
	
	@FindBy(xpath="//a[@class='selectedMenu fNiv isParent']//span")
	WebElement accountMenuTab;
	
	@FindBy(xpath="//li[@id='FM_MAINTAIN_ACCT_MENU']//a//span")
	WebElement maintainAccount;
	
	@FindBy(name="billingFrequency")
	WebElement billingFrequencyDDL;
	
	@FindBy(name="baseBillMmDd")
	WebElement startDate;
	
	@FindBy(name="billLeadDays")
	WebElement leadDays;
	
	@FindBy(id="FM_MAINT_ACCT_SAVE")
	WebElement saveMaintAction;
	
	public FinancePage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		financePageDTO = new FinancePageDTO();
	}
	
	
	public void maintainAccount() throws InterruptedException{
		
		clickButton(driver, Search_btn, "Search for account");
		invisibilityOfLoader(driver);
		Thread.sleep(2000);
		
		for(int i=0;i<accountNumList.size();i++){
			
			if(accountNumList.get(i).getAttribute("innerHTML").trim().equals("A0000550")){
				
				selectValue(driver, accountNumList.get(i), "Account Number"+accountNumList.get(i));
				break;
			}
		}//TODO - if the account num is not available in list
		//invisibilityOfLoader(driver);
		Thread.sleep(2000);
		Actions act = new Actions(driver);
		act.moveToElement(accountMenuTab).build().perform();
		JavascriptExecutor jse= (JavascriptExecutor)driver; 
		jse.executeScript("arguments[0].click();",maintainAccount);
		
		Thread.sleep(2000);
		selectDropdownByVisibleText(driver, billingFrequencyDDL, "Bi-Monthly", "Billing Frequency");
		clearTextBox(driver, startDate, "Start Date");
		enterDataIn(driver, startDate, "0301", "Start Date");
		clearTextBox(driver, leadDays, "Lead Days");
		enterDataIn(driver, leadDays, "9", "Lead Days");
		clickButton(driver, saveMaintAction, "Save");
		
	}
	
	

	// Search Account from Search Account text field on Finanace Home Page.
	public FinancePage searchPolicyOnFinanceHomePage() throws Exception {
		invisibilityOfLoader(driver);
		enterTextIn(driver, PolicyNoTxtBox, financepagedto.policyNo, "Policy Number");
		clickButton(driver, Search_btn, "Search");
		invisibilityOfLoader(driver);
		Assert.assertTrue(accountList.size() != 0, "Account list is not displayed on " + "Account Search" + "page");
		Thread.sleep(3000);
		return new FinancePage(driver);
	}

	// This method will click on first account number displayed after Policy
	// search.
	public FinancePage openFirstAccount() throws Exception {
		clickButton(driver, accountList.get(0), "Account List");
		invisibilityOfLoader(driver);
		getPageTitle(driver, allTxnInquireyPageTitle);
		return new FinancePage(driver);
	}

	public FinancePage onDemandInvoice() throws Exception {
		navigatetoMenuItemPage(driver,billingAdminMenu,onDemandInvoiceMenuOption);
		selectDropdownByValue(driver, invoiceOptionDDL, invoiceOptionDDLValue, "Invoice Option");
		selectDropdownByValue(driver, createChargesDDL, createChargesDDLValue, "Invoice Option");
		selectDropdownByValue(driver, specifyInvoiceDateDDL, specifyInvoiceDateDDLValue, "Invoice Option");
		selectDropdownByValue(driver, specifyDueDateDDL, specifyDueDateDDLValue, "Invoice Option");
		accountNumber = accountNo.getAttribute("innerHTML");
		clickButton(driver, processButton, "Process");
		invisibilityOfLoader(driver);
		Thread.sleep(15000);
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'accountNo=" + accountNumber + "')]")));
		invoiceNumber = invoiceNo.getAttribute("innerHTML");
		invoiceAmount = invoiceAmt.getAttribute("innerHTML");
		clickButton(driver, jumpButton, "Jump");
		switchToParentWindowfromframe(driver);
		getPageTitle(driver, allTxnInquireyPageTitle);
		return new FinancePage(driver);
	}

	public FinancePage cashEntry() throws InterruptedException, IllegalArgumentException, IllegalAccessException, SecurityException {
		navigatetoMenuItemPage(driver,paymentsMenu,cashEntryMenuOption);
		invisibilityOfLoader(driver);
		getPageTitle(driver, cashEntryPageTitle);
		clickButton(driver, newButton, "New");
		invisibilityOfLoader(driver);
		selectDropdownByValue(driver, paymentTypeDDL, paymentTypeDDLValue, "Payment Type");
		enterTextIn(driver, invoiceNoOnCashEntryPage, invoiceNumber, "Cash Entry Page's invoice Number");
		enterTextIn(driver, checkNoOnCashEntryPage, randomNoGenerator(), "Cash Entry Page's Check Number");
		enterTextIn(driver, amountOnCashEntryPage, invoiceAmount, "Cash Entry Page's Amount");
		clickButton(driver, saveBtnOnCashEntryPage, "Cash Entry Page's Save");
		return new FinancePage(driver);
	}

	public FinancePage batchFunction() throws Exception {
		invisibilityOfLoader(driver);
		batchNumber = batchNo.getAttribute("innerHTML");
		js.executeScript("arguments[0].click();", paymentsMenu);
		js.executeScript("arguments[0].click();", batchFunctionMenuOption);
		invisibilityOfLoader(driver);
		getPageTitle(driver, batchFunctionPageTitle);
		for (int i = 0; i < batchNoOnBatchFunPage.size(); i++) {
			if (batchNoOnBatchFunPage.get(i).getAttribute("innerHTML").equals(batchNumber)) {
				clickButton(driver, batchNoOnBatchFunPage.get(i), "Batch Number");
				clickButton(driver, validateBatchBtn, "Validate Batch");
				switchToFrameUsingElement(driver, driver.findElement(By.xpath("//iframe[contains(@src,'batchNo="
						+ batchNoOnBatchFunPage.get(i).getAttribute("innerHTML") + "')]")));
				break;
			}
		}
		getPageTitle(driver, validateBatchPageTitle);
		enterTextIn(driver, batchAmount, BatchAmount, "batch Amount");
		enterTextIn(driver, numberOfPaymentTxtBox, BatchNoPayment, "Batch Number Of Payment");
		clickButton(driver, doneBatchPopUp, "Done");
		switchToParentWindowfromframe(driver);
		invisibilityOfLoader(driver);
		verifyValueFromField(driver, validateField, validlateFieldExpectedValue, validlateFieldAttributeValue,
				validlateFieldName);
		return new FinancePage(driver);
	}
	
	public FinancePage postBatchFunctionality() throws InterruptedException, IllegalArgumentException, IllegalAccessException, SecurityException
	{
		clickButton(driver, postBatchBtn, "Post Batch");
		Alert alert = driver.switchTo().alert();
		alert.accept();
		policySearch(driver, accountNumber, Policy_Search, Search_btn);
		invisibilityOfLoader(driver);
		getPageTitle(driver, allTxnInquireyPageTitle);
		Assert.assertTrue(currBalOnAllTxnEnqPage.getAttribute("innerHTML").equals(financepagedto.currunetBalance),
				"Current Balance is not zero on All transaction enquirey Page");
		return new FinancePage(driver);
	}
}
