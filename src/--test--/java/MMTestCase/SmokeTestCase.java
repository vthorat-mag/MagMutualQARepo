package MMTestCase;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//import com.mm.dto.PDFReaderDTO;
import com.mm.browsers.BrowserTypes;
import com.mm.dto.LoginPageDTO;
import com.mm.dto.PolicyBinderPageDTO;
import com.mm.dto.PolicyQuotePageDTO;
import com.mm.dto.RateAPolicyPageDTO;
import com.mm.pages.CISPage;
import com.mm.pages.EndorsePolicyPage;
import com.mm.pages.FindPolicyPage;
import com.mm.pages.HomePage;
import com.mm.pages.LoginPage;
import com.mm.pages.PolicyBinderPage;
import com.mm.pages.PolicyIndicationPage;
import com.mm.pages.PolicyQuotePage;
import com.mm.pages.PolicySubmissionPage;
import com.mm.pages.QuickAddOrganisation;
import com.mm.pages.RateApolicyPage;
import com.mm.utils.ExcelApiTest;
import com.mm.utils.ExcelUtil;
import com.mm.utils.ExtentReporter;
import com.mm.utils.PDFReader;
import com.relevantcodes.extentreports.LogStatus;

public class SmokeTestCase extends BrowserTypes {

	// Global Assignment/initialization of variables.
	WebDriver driver = BrowserTypes.getDriver();
	public static HashMap<String, List<String>> testDataMap;

	//Page variables should be local to test case
	
	LoginPage loginpage;
	CISPage cispage;
	HomePage homepage;
	RateApolicyPage rateapolicyPage;
	FindPolicyPage findpolicypage;
	PolicyBinderPage policybinderpage;
	PolicyQuotePage policyquotepage;
	PolicySubmissionPage policysubmissionpage;
	QuickAddOrganisation quickaddorganisation;
	PolicyIndicationPage policyindicationpage;
	EndorsePolicyPage endorsepolicypage;
	// DTO page objects
	PolicyBinderPageDTO policybinderpageDTO;
	RateAPolicyPageDTO rateApolicyPageDTO;
	PolicyQuotePageDTO policyquotepageDTO;
//	PDFReaderDTO pdfReaderDTO;
	LoginPageDTO lpDTO;

	//Extent report initialization before every test case.
	@BeforeMethod
	public void Setup(Method method) throws Exception {
		ExtentReporter.logger = ExtentReporter.report.startTest(method.getName());
		// Code to populate HashMap from excel
		// Instantiate ExcelUtil and call testData and fill a HashMap
		// testDataMap
		ExcelUtil excelUtil = new ExcelUtil();
		testDataMap = excelUtil.testData(method.getName());
	}

	
	//DTO done
	//@Test(description = "Verify CIS Page Displays", groups = { "Smoke Test" })
	 public void TC42253() throws Exception {
			lpDTO = new LoginPageDTO();
			loginpage = new LoginPage(driver);
			loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToCISPage().searchAndSelectAClientName()
			.verifyPagesHavingMenuOnPersonPageAreDisplayed().verifyPagesWithoutSubMenu();
			
		}
	

	// DTO done
	//@Test(description="Hospital Rate", groups = { "Smoke Test" })
	public void TC42239() throws Exception {
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPage();
		homepage= new HomePage(driver);   
		String policyNum=homepage.policySearchUsingSearchCriteria();
		rateapolicyPage = new RateApolicyPage(driver);
		rateapolicyPage.rateFunctionality(policyNum);
				
	}

	//DTO done
	//@Test(description="Hospital Verify Attach Form", groups = { "Smoke Test" })
	public void TC42399() throws Exception {
		
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPage();
		homepage= new HomePage(driver);   
		String policyNum=homepage.policySearchUsingSearchCriteria();
		rateapolicyPage = new RateApolicyPage(driver);
		String policyNumber =rateapolicyPage.checkPolicyViewModeAndUpdateCoverage(policyNum);
										rateapolicyPage.rateFunctionality(policyNumber).clickPreviewTab().savePDF().verifyPdfContent(policyNumber);
		
	}

	
	// DTO done
	// @Test(description = "Verify Add Organization", groups = { "Smoke Test" })
	public void TC42404() throws Exception {
			lpDTO = new LoginPageDTO();
			loginpage = new LoginPage(driver);
			loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToCISPage().clickOnNewOrganization()
					.enterDataInNewOrgPage().selectZipCode().saveNewOrgDetails();
			
			//ToDo - Need to change test case according to updated test steps
		}
	

	// DTO done
	// @Test(description="HPL - Binder", groups = { "Smoke Test" })
	public void TC42242() throws Exception {
			String searchPolicyNum = "Q09101726-NB16-01";
			lpDTO = new LoginPageDTO();
			loginpage = new LoginPage(driver);
			loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromrateApolicyPage()
					.searchPolicyRateAPolicyPage(searchPolicyNum).AcceptFromActionDropDown().isAlertPresent()
					.identifyPhase().billingSetup().coverageDetailsSelect();

			rateapolicyPage = new RateApolicyPage(driver);
			String policyNumber = rateapolicyPage.policyNo();

		// Below code will run same test steps based on number of coverage you
		// want to update.
		for (int i = 0; i < rateApolicyPageDTO.coverageNames.size(); i++) {
			rateapolicyPage.coverageUpdates(rateApolicyPageDTO.coverageNames.get(i), rateApolicyPageDTO.phase.get(i),
					policyNumber);
		}
		rateapolicyPage.rateFunctionality(policyNumber).clickPreviewTab().savePDF().verifyPdfContent("HC")
				.saveOption(policyNumber);
	}

	// DTO Implemented
	// @Test(description="Hospital Claim - Verify Change Claim Status", groups ={ "Smoke Test" })
	public void TC42405() throws Exception {
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password);
		String claimNo = "7683S";
		policybinderpage = new PolicyBinderPage(driver);
		policybinderpage.navigatetoClaimsPage().searchClaim(claimNo).statusChange(claimNo);
	}

	// DTO done
	// @Test(description="Hospital Issue Policy Forms",dataProvider =
	// "userTestData", dataProviderClass=ExcelApiTest.class,groups = { "Smoke
	// Test" })
	public void TC42665() throws Exception {
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		String searchPolicyNum = "09100275";
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromPolicyBinderPage()
				.searchPolicyPolicyBinderPage(searchPolicyNum);

		policybinderpage = new PolicyBinderPage(driver);
		String policyNumber = policybinderpage.policyNo();

		policybinderpage.endorsementFromActionDropDown().endorsPolicy(policyNumber).identifyPhase()
				.rateFunctionality(policyNumber).saveOption(policyNumber).exit_SaveOption().clickPreviewTab().savePDF()
				.verifyPdfContent("ASD");
	}
	
	// DTO Done
	//@Test(description = "Verify Hospital Preview Forms", groups = { "Smoke Test" })
	public void TC42240() throws Exception {
		RateApolicyPage rateapolicypage = new RateApolicyPage(driver);
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).headerPolicyTab()
		.searchPolicyRateAPolicyPage("Dummy Vlaue-Need to remove this param");
		
		String PolicyNo = rateapolicypage.policyNo();
		rateapolicypage.policyEndorsement(PolicyNo).rateFunctionality(PolicyNo).clickPreviewTab().savePDF().verifyPdfContent("Need to remove this para");

		
	}

	// DTO Done
	// @Test(description = "Hospital Create Claim", groups = { "Smoke Test" })
	public void TC43666() throws Exception {
		String searchPolicyNum = "Q09101675-NB16-01";
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).headerPolicyTab()
				.searchPolicyRateAPolicyPage(searchPolicyNum);

		policybinderpage = new PolicyBinderPage(driver);
		String clientID = policybinderpage.getClientId();

		policybinderpage.navigatetoClaimsPage().getPatientDetails(clientID);
	}

	//DTO done
	// @Test(description="Hospital Renewal", groups = {"Smoke Test"
	// })
	public void TC42400() throws Exception {

		String policy_no = "09101673";
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		policyquotepageDTO = new PolicyQuotePageDTO();
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromrateApolicyPage()
				.searchPolicyPolicyQuotePage(policy_no).select_policyAction().save_CaptureTransactionDetails()
				.saveOption(policyquotepageDTO.saveAsPolicyValueRenewal).switchToNextFrame().save_CaptureTransactionDetails().saveOption(policyquotepageDTO.saveAsPolicyValueOfficial)
				.product_Notify().exit_SaveOption();
		
	}

	//DTO done
	//@Test(description = "Hospital Quote", groups = { "Smoke Test" })
	public void TC42238() throws Exception {
		String searchPolicyNum = "09100274";
		// lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis("vthorat", "M@G580746").navigateToPolicyPageFromrateApolicyPage()
				.searchPolicyPolicyQuotePage(searchPolicyNum).CopyOptionFromActionDropDown().changePhaseToQuote()
				.coverageDetailsSelect();

		policyquotepage = new PolicyQuotePage(driver);
		String policyNumber = policyquotepage.policyNo();
		policyquotepageDTO = new PolicyQuotePageDTO();
		
		for (int i = 0; i < policyquotepageDTO.coverages.size(); i++) {
			rateapolicyPage.coverageUpdates(rateApolicyPageDTO.coverageNames.get(i), rateApolicyPageDTO.binderForms.get(i), policyNumber);
		}

		policyquotepage.rateFunctionality(policyNumber).saveOption(policyquotepageDTO.saveAsPolicyValue).exit_SaveOption()
				.clickPreviewTab().savePDF().verifyPdfContent("ABCD");
	}

	//DTO done
	// @Test(description = "Hospital Copy to Quote",groups = { "Smoke Test" })
	public void TC42245(String UserName, String PassWord) throws Exception {
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		String searchPolicyNum = "09100200";
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPageFromrateApolicyPage()
				.searchPolicyRateAPolicyPage(searchPolicyNum);

		policybinderpage = new PolicyBinderPage(driver);
		String policyNumber = policybinderpage.policyNo();

		policybinderpage.copyToQuoteFromActionDropDown(policyNumber).copyFromActionDropDown(policyNumber)
				.changePhaseToIndication().saveWip().clickPreviewTab().savePDF().verifyPdfContent("ASD");
	}

	// DTO done
	// @Test(testName="HospitalIndication",groups = { "Smoke Test" })
	public void TC42249() throws Exception {
		lpDTO = new LoginPageDTO();
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(lpDTO.username, lpDTO.password).navigateToPolicyPage().create_New();

		homepage = new HomePage(driver);
		String ParentWindow = homepage.create_Quote();

		homepage.search_Quote(ParentWindow).selectPolicyType().updatePolicyDetails();

		policyindicationpage = new PolicyIndicationPage(driver);
		List<WebElement> firstFrame = policyindicationpage.open_Underwriter();

		policyindicationpage.add_Underwriter(firstFrame).close_Underwriter().addAgent().addRiskInformation()
				.addCoverage().selectCoverageFromPopupListAddDatePremium().closeAddCoveragetab()
				.selectCoverageFromGridList().addCoverageClass();

		String PolicyNo = policyindicationpage.policyNo();

		policyindicationpage.coverageUpdates(PolicyNo).openLimitSharingTab(PolicyNo).addSharedGroup(PolicyNo)
				.closeLimitSharingtab();
		
		// TODO - Add PDF verification.
	}

	// DTO code is not implemented as this is not part of scope.
	// @Test(testName="EndorsePolicy",groups = { "Smoke Test" })
	public void TC42530(Method method, String UserName, String PassWord) throws Exception {
		loginpage = new LoginPage(driver);
		String PolicyNo = "9865321";
		loginpage.loginToeOasis(UserName, PassWord).navigateToPolicyPageFromPolicyBinderPage()
				.searchPolicyPolicyBinderPage(PolicyNo).endorsPolicy(PolicyNo).rateFunctionality(PolicyNo)
				.saveOption(PolicyNo).clickPreviewTab().savePDF().verifyPdfContent("ASD");
	}

	// DTO code is not implemented as this is not part of scope.
	// This is additional test, removed later from rally
	// @Test(testName="VerifyExistingPolicy",groups = { "Smoke Test" })
	public void TC42536(String UserName, String PassWord) throws Exception {
		loginpage = new LoginPage(driver);
		String searchPolicyNum = "09100275";
		loginpage.loginToeOasis(UserName, PassWord).navigateToPolicyPageFromrateApolicyPage()
				.searchPolicyRateAPolicyPage(searchPolicyNum);
	}

	// DTO code is not implemented as this is not part of scope.
	// @Test(description= "Quick_Add_Organisation",groups = { "Smoke Test" })
	public void Quick_Add(String UserName, String PassWord) throws Exception {
		loginpage = new LoginPage(driver);
		loginpage.loginToeOasis(UserName, PassWord).navigateToCISPage();
		quickaddorganisation = new QuickAddOrganisation(driver);
		quickaddorganisation.navigate_To_Add_Org_Window().add_Org_Information().add_Org_Address().selectZipCode()
				.add_Phone_Number();
	}

	@AfterMethod
	public void logoffFromAppclication(ITestResult result) throws IOException, InterruptedException {
		// homepage.logoutFromeOasis();
		ExtentReporter.report.endTest(ExtentReporter.logger);

		if (ITestResult.FAILURE == result.getStatus()) {
			ExtentReporter.logger.log(LogStatus.FAIL, result.getName());
			ExtentReporter.logger.log(LogStatus.FAIL, result.getThrowable());

		} else if (ITestResult.SUCCESS == result.getStatus()) {
			ExtentReporter.logger.log(LogStatus.INFO, "User is logged out of applciation");
			ExtentReporter.logger.log(LogStatus.PASS, result.getName());
		} else if (ITestResult.SKIP == result.getStatus()) {
			ExtentReporter.logger.log(LogStatus.SKIP, result.getName());
		}
		ExtentReporter.report.flush();
		Thread.sleep(2000);
		// driver.close();
	}

	@AfterClass
	public void closeBrowser() {
		// ExtentReporter.report.close();
		// driver.quit();
	}
}