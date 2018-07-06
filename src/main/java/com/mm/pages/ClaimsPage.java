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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mm.dto.ClaimsDTO;
import com.mm.dto.HomePageDTO;
import com.mm.dto.RateAPolicyPageDTO;
import com.mm.utils.CommonAction;
import com.mm.utils.CommonUtilities;
import com.mm.utils.ExtentReporter;
import com.relevantcodes.extentreports.LogStatus;

public class ClaimsPage extends CommonAction {
	WebDriver driver;
	ClaimsDTO claimsdto;
	HomePage homePage;
	CommonUtilities comUtil = new CommonUtilities();

	String fileStatusDropDownOption = "OPENERROR";
	String verifyFileStatusValue = "Opened in Error";

	@FindBy(name = "globalSearch")
	WebElement claim_Search;

	@FindBy(name = "search")
	WebElement Search_btn;

	@FindBy(name = "claim_claimStatusDate")
	WebElement chagneFileDate;

	@FindBy(id = "CM_GENL_STAT_AI")
	WebElement chagneStatus;

	@FindBy(id = "CM_PICKER_SAVE")
	WebElement saveBtnOnChagneStatusPopup;

	@FindBy(xpath = "//span[contains(@class,'ui-button-icon']")
	WebElement closeSymbolOnChagneStatusPopup;

	@FindBy(name = "claim_claimCurrentStatus")
	WebElement fileStatus;

	@FindBy(id = "claimHeaderClaimStatusCodeLongDescROSPAN1")
	WebElement fileStatusOnClaimFolderPage;

	@FindBy(xpath = "//iframe[contains(@id,'popupframe')]")
	WebElement changeFileStatusFrameEle;

	@FindBy(xpath = "//td[@class='errortext']")
	WebElement ErrorMsg;

	@FindBy(xpath = "//a[@class='selectedMenu fNiv isParent']//span")
	WebElement filesMenuTab;

	@FindBy(xpath = "//li[@id='CM_ADD_CLAIM_MI']//a")
	WebElement fileAddMenuOption;

	@FindBy(id = "btnFind_claimantFullName")
	WebElement patientSearchIcon;

	@FindBy(xpath = "//input[@name='entitySearch_lastOrOrgName']")
	WebElement lastNameEntitySearchPage;

	@FindBy(xpath = "//input[@name='entity_firstName']")
	WebElement firstNameEntitySearchPage;

	@FindBy(id = "CI_ENTITY_SELECT_SCH_SCH")
	WebElement searchBtnOnEntitySearchPage;

	@FindBy(id = "CCLIENT_NAME")
	WebElement resultOnEntityListPage;

	@FindBy(xpath = "//input[@name='chkCSELECTIND']")
	WebElement selectEntityChkBox;

	@FindBy(id = "CI_ENT_SEL_LST_FORM_SEL")
	WebElement selectBtnOnEntitySelectListPage;

	@FindBy(xpath = "//select[@name ='claimType']")
	WebElement FileTypeDropDown;

	@FindBy(xpath = "//select[@name ='cmLobCode']")
	WebElement lobDropDown;

	@FindBy(xpath = "//textarea[@class='oasis_formfieldreq']")
	WebElement descriptionTextBox;

	@FindBy(xpath = "//input[@name='claimantFullName']")
	WebElement patientSelectedValue;

	@FindBy(xpath = "//select[@name='entityExaminerId']")
	WebElement fileHandlerDorpDown;

	@FindBy(xpath = "//select[@name='claimStateCode']")
	WebElement stateOfLossDorpDown;

	@FindBy(xpath = "//input[@name='lossDate']")
	WebElement accidentDateTextBox;

	@FindBy(xpath = "//img[@id='btnFind_insuredFullName']")
	WebElement insuredSearchIcon;

	@FindBy(xpath = "//input[@name = 'entity_clientID']")
	WebElement entityClientId;
	
	//WebELements for TC42252
	
	@FindBy(xpath = "//li[@id='CM_CLAIMS_MENU']")
	WebElement filesMenu;
	
	@FindBy(xpath = "//ul[@id='subMenuForCM_CLAIMS_MENU']//li//a//span")
	List <WebElement> filesMenuList;
	
	@FindBy(xpath="//a//img[@id='btnFind_claimantFullName']")
	WebElement patientSearchBtn;
	
	@FindBy(name = "entitySearch_lastOrOrgName")
	WebElement lastOrgName;
	
	@FindBy(id="CI_ENTITY_SELECT_SCH_SCH")
	WebElement searchEntityButton;
	
	@FindBy(id="CCLIENT_NAME")
	List <WebElement> entitySelectclientNameList;
	
	@FindBy(xpath = "//input[@name='chkCSELECTIND']")
	List <WebElement> entityCheckboxList;

	@FindBy(xpath="//div[@id='CCLIENT_ID']")
	List <WebElement> entitySelectClientIdList;
	
	@FindBy(id = "CI_ENT_SEL_LST_FORM_SEL")
	WebElement selectEntity;
	
	@FindBy(name="name=claimType")
	WebElement fileTypeDDL;
	
	@FindBy(name="cmLobCode")
	WebElement LOBDDL;
	
	@FindBy(xpath="//textarea[@name='headline']")
	WebElement fileDescription;
	
	@FindBy(name="entityExaminerId")
	WebElement fileHandlerDDL;
	
	@FindBy(name="claimStateCode")
	WebElement stateOfLoss;
	
	@FindBy(name="lossDate")
	WebElement accidentDate;
	
	@FindBy(xpath="//a//img[@id='btnFind_insuredFullName']")
	WebElement searchInsuredName;
	
	@FindBy(name="entity_clientID")
	WebElement entityClientID;
	
	
	
	// Constructor to initialize driver, page elements and DTO PageObject for
	// Claims Page.
	public ClaimsPage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		claimsdto = new ClaimsDTO();
	}


	//
	public void addFile() throws Exception{
		//Move to Files tab and select Add File option from menu
 		invisibilityOfLoader(driver);
		Actions action = new Actions(driver);
		action.moveToElement(filesMenu).click().build().perform();
		clickButton(driver, filesMenuList.get(0), "Add File");
		invisibilityOfLoader(driver);
	//	getPageTitle(driver, "Add File");
		Thread.sleep(2000);
		//Click search button will open a new window
		clickButton(driver, patientSearchBtn, "Search for patient");
		Thread.sleep(2000);
		//Switch to new window using get window handles
		String parentWindow = switchToWindow(driver);
		searchAPatientNameFromEntitySelectList(parentWindow);
		selectDropdownByVisibleText(driver, fileTypeDDL, "Claim", "File Type");
		selectDropdownByVisibleText(driver, LOBDDL, "HLP", "LOB");
		enterTextIn(driver, fileDescription, "Automation Test", "Desciption");
		selectDropdownByVisibleText(driver, fileHandlerDDL, " ", "File Handler");
		selectDropdownByVisibleText(driver, stateOfLoss, " ", "State of Loss");
		clickButton(driver, insuredSearchIcon, "Insured ");
		enterDataIn(driver, accidentDate, "06252018", "Accident Date");
		
	}
	
	// Search and select the client from 'Entity Select List' using client name and matching id from excel file
	public void searchAPatientNameFromEntitySelectList(String parentWindow) throws InterruptedException{
		
		waitForElementToLoad(driver, 20, lastOrgName);
		//getPageTitle(driver, entitySelectSearchPageTitle);
		visibilityOfElement(driver, lastOrgName, "Last Org Name on Entity Select Search window");
		Thread.sleep(3000);
		//click Search button to load Entity List
		clickButton(driver, searchEntityButton, "Search Name of an entity");		
		invisibilityOfLoader(driver);
		Thread.sleep(3000);
		
		//Get the list of client names from system
		for (int i = 0; i < entitySelectclientNameList.size(); i++) {

		// compare the client name from system with client name from excel sheet 
		// also the client id from system to client id from sheet 
		if (entitySelectclientNameList.get(i).getAttribute("innerHTML").trim().equals(claimsdto.clientNameValue)
			&& entitySelectClientIdList.get(i).getAttribute("innerHTML").trim().equals(claimsdto.clientIDValue)) {
			// select client name whose id matches
			ExtentReporter.logger.log(LogStatus.INFO, claimsdto.clientNameValue+" check box is selected ");
			selectValue(driver, entitySelectclientNameList.get(i), claimsdto.clientNameValue);
			break;
			}else{
			break;
			}
		}
			clickButton(driver, selectEntity, "Select Entity");
			switchToParentWindowfromotherwindow(driver, parentWindow);
	}
	
	
	// method to search claims from 'Enter Text #' text box(Top right corner of
	// the screen)
	public ClaimsPage searchClaim() throws Exception {
		
		getPageTitle(driver, claimsdto.FileSearchPageTitle);
		ExtentReporter.logger.log(LogStatus.INFO,
				"Enter Claim # from Hospital Create Claim Test Case(Example 66429) & Click Search.");
		policySearch(driver, claimsdto.claimNum, claim_Search, Search_btn);
		getPageTitle(driver, "Claim Folder " + claimsdto.claimNum);

		return new ClaimsPage(driver);
	}

	// This method will change the status of claims.
	public void statusChange(String claimNo) throws Exception {
		ExtentReporter.logger.log(LogStatus.INFO, "Click Green Change Status button.");
		clickButton(driver, chagneStatus, "Change Status");
		switchToFrameUsingElement(driver, changeFileStatusFrameEle);
		// getPageTitle(driver, "Change File Status");

		// As File Search Page (Claims) having 2 headers Hence instead of using
		// getPageTitle method below logic is written to verify page title.
		List<WebElement> pageheaders = driver.findElements(By.xpath("//div[@class='pageTitle']"));
		WebElement pageLoader = driver.findElement(By.xpath("//span[@class='txtOrange']"));
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.invisibilityOf(pageLoader));
		Assert.assertEquals(pageheaders.get(0).getAttribute("innerHTML").trim(), "Change File Status",
				"Page title is not matching.");

		ExtentReporter.logger.log(LogStatus.INFO, "Enter/select the below information");
		ExtentReporter.logger.log(LogStatus.INFO, "File Status Date: Enter in Current Date if no date is entered");
		clearTextBox(driver, chagneFileDate, "File Status Date");
		CommonUtilities commUtil = new CommonUtilities();
		enterDataIn(driver, chagneFileDate, commUtil.getSystemDatemmddyyyy(), "File Status Date");
		ExtentReporter.logger.log(LogStatus.INFO, "File Status: Select Opened in Error");
		selectDropdownByValue(driver, fileStatus, fileStatusDropDownOption, "File Status");
		ExtentReporter.logger.log(LogStatus.INFO, "Click [Save]");
		clickButton(driver, saveBtnOnChagneStatusPopup, "Change Status Save");
		Thread.sleep(2000);
		ExtentReporter.logger.log(LogStatus.INFO,
				"Are you sure you want to change the File Status to Opened in Error?");
		ExtentReporter.logger.log(LogStatus.INFO, "Click [OK]");
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(2000);

		// TODO - Need to check below code is required or not as this is
		// required in case when application is down.
		// ****************
		switchToParentWindowfromframe(driver);
		switchToFrameUsingElement(driver, changeFileStatusFrameEle);
		click(driver, closeSymbolOnChagneStatusPopup, "Close");
		// *****************************

		Thread.sleep(5000);
		switchToParentWindowfromframe(driver);

		getPageTitle(driver, "Claim Folder " + claimNo);
		verifyValueFromField(driver, fileStatusOnClaimFolderPage, verifyFileStatusValue, "innerHTML","File Status");
	}

	// Select Patient.
	public void getPatientDetails(String clientIdValue) throws Exception {
		Thread.sleep(2000);
		Actions builder = new Actions(driver);
		builder.moveToElement(filesMenuTab).build().perform();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", fileAddMenuOption);
		// clickButton(driver, fileAddMenuOption, "Add File Menu");
		getPageTitle(driver, ClaimsDTO.addFilePageTitle);
		clickButton(driver, patientSearchIcon, "Patient Search");
		waitFor(driver, 10);
		String parentWindowId = switchToWindow(driver);
		getPageTitle(driver, ClaimsDTO.entitySearchListPageTitle);
		enterTextIn(driver, lastNameEntitySearchPage, ClaimsDTO.lastName, "Last Name");
		enterTextIn(driver, firstNameEntitySearchPage, ClaimsDTO.firstName, "First Name");
		clickButton(driver, searchBtnOnEntitySearchPage, "Entity Search Page's Search");
		Assert.assertEquals(resultOnEntityListPage.getAttribute("innerHTML").trim(),
				ClaimsDTO.lastName + ", " + ClaimsDTO.firstName + ",", "Data displayed after search is not correct");
		waitFor(driver, 5);
		clickButton(driver, selectEntityChkBox, "Select Entity Check Box");
		clickButton(driver, selectBtnOnEntitySelectListPage, "Entity Select List Page's Select");
		switchToParentWindowfromotherwindow(driver, parentWindowId);
		Assert.assertEquals(patientSelectedValue.getAttribute("value").trim(),
				ClaimsDTO.lastName + ", " + ClaimsDTO.firstName + ",", "Patient selected is NOT displayed correctly");
		selectDropdownByValue(driver, FileTypeDropDown, ClaimsDTO.fileTypeDropDownValue, "File Type");
		selectDropdownByValue(driver, lobDropDown, ClaimsDTO.lobDropDownValue, "LOB");
		enterTextIn(driver, descriptionTextBox, ClaimsDTO.description, "Description");
		selectDropdownByValue(driver, fileHandlerDorpDown, ClaimsDTO.fileHandlerDropDownValue, "File Handler");
		selectDropdownByValue(driver, stateOfLossDorpDown, ClaimsDTO.stateOfLossDropDownValue, "State Of Loss");
		enterTextIn(driver, accidentDateTextBox, comUtil.getSystemDatemmddyyyy(), "Accident Date");
		clickButton(driver, insuredSearchIcon, "Insured Search Icon");
		String parentWindowIdSearchEntity = switchToWindow(driver);
		String searchEntityTitle = getPageTitle(driver, ClaimsDTO.searchEntityPageTitle);
		enterTextIn(driver, entityClientId, clientIdValue, "Client Id");
		clickButton(driver, searchBtnOnEntitySearchPage, "Entity Search Page's Search");
		waitFor(driver, 10);
		Assert.assertTrue(resultOnEntityListPage.isDisplayed(),
				"Insured Name is not populated on 'Entity Select List' page.");
		clickButton(driver, selectEntityChkBox, "Insured Name");
		clickButton(driver, selectBtnOnEntitySelectListPage, "Select");

		// TODO - Need To add below steps once got confirmaiton on query - Cant
		// see policy No from Policy No drop down field.
		/*
		 * In the filter criteria section, click the Policy No dropdown and
		 * Select [Policy number entered in step 3] Click the checkbox next the
		 * Prof Liab coverage Click Save as Claim Possible duplicate claim
		 * screen displays Click Save as Claim Claim No displays in the upper
		 * left corner. Note (and save for later input) the claim number:
		 * ****add ########### Click [Close]
		 */
	}
}
