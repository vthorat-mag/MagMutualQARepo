package com.mm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mm.dto.CincomPageDTO;
import com.mm.utils.CommonAction;
import com.mm.utils.CommonUtilities;
import com.mm.utils.ExtentReporter;
import com.relevantcodes.extentreports.LogStatus;

public class CincomPage extends CommonAction {

	WebDriver driver;
	CincomPageDTO cincomedto;
	CommonUtilities comUtil;

	@FindBy(xpath = "//table[@id='coverageListGrid']//tbody//td//div[@id='CPRODUCTCOVERAGEDESC']")
	List<WebElement> coverageList;

	@FindBy(id = "PM_MANU_PUP")
	WebElement optionalFormBtn;

	@FindBy(xpath = "//table[@id='maintainManuscriptListGrid']")
	WebElement manuscriptList;

	@FindBy(id = "PM_MANU_DELETE")
	WebElement manuscriptPageDeleteBtn;

	@FindBy(id = "PM_MANU_ADD")
	WebElement manuscriptPageAddBtn;

	@FindBy(xpath = "//table[@id='maintainManuscriptListGrid']//tr")
	List<WebElement> manuscriptInformationTypeListGrid;

	@FindBy(xpath = "//table[@id='selectManuscriptGrid']//tr//div[@id='CSHORTDESCRIPTION']")
	List<WebElement> manuscriptAddListformName;

	@FindBy(xpath = "//table[@id='selectManuscriptGrid']//tr//input[@name='chkCSELECT_IND']")
	List<WebElement> manuscriptAddListformNameChkBox;

	@FindBy(id = "PM_SEL_MANU_DONE")
	WebElement manuscriptAddListDoneBtn;

	@FindBy(id = "PM_MANU_SAVE")
	WebElement manuscriptPageSaveBtn;

	@FindBy(id = "PM_MANU_CLOSE")
	WebElement manuscriptPageCloseBtn;

	@FindBy(xpath = "//span[@class='dragbox_main_head_class']")
	WebElement spellchkBoxHeading;

	@FindBy(id = "PM_MANU_DTENTRY")
	WebElement dataEntryBtn;

	@FindBy(id = "ictextField")
	WebElement titleHFLHPLCHGGE;

	@FindBy(name = "paraSel")
	WebElement freeFormCHGGEBeginChkBox;

	@FindBy(xpath = "//button[@role='presentation']//i[@class='mce-ico mce-i-paste']")
	WebElement saveIconOnCincom;

	@FindBy(xpath = "//body[@id = 'tinymce']//p")
	WebElement freeFormCHGGEBeginTxtField;

	@FindBy(xpath = "//iframe[contains(@title,'Rich Text Area')]")
	WebElement cincomIFrame;

	@FindBy(name = "IgnoreAllBtn")
	WebElement ignoreAllBtn;

	@FindBy(xpath = "//input[contains(@id,'delopt')]")
	WebElement DelOptions;

	@FindBy(id = "Deliver")
	WebElement deliverBtn;

	@FindBy(xpath = "//div[@class='noerror']")
	WebElement sucessMsg;
	
	@FindBy(name = "additionalText")
	WebElement addText;


	public CincomPage(WebDriver driver) throws IllegalArgumentException, IllegalAccessException, SecurityException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		cincomedto = new CincomPageDTO();
	}

	public RateApolicyPage cincomFlow(String PolicyNo) throws Exception {
		for (int j = 0; j < cincomedto.coverage.size(); j++) {
			for (int i = 0; i < coverageList.size(); i++) {
				if (coverageList.get(i).getAttribute("innerHTML").equals(cincomedto.coverage.get(j))) {
					clickButton(driver, coverageList.get(i), coverageList.get(i).getAttribute("innerHTML"));
					ExtentReporter.logger.log(LogStatus.INFO, "Select" + cincomedto.coverage.get(j) + " Coverage.");
					break;
				}
			}
			ExtentReporter.logger.log(LogStatus.INFO, "Click [Optional Forms]");
			Thread.sleep(4000);
			clickButton(driver, optionalFormBtn, "Optional Form");
			// invisibilityOfLoader(driver, PageloaderSymbol);
			switchToFrameUsingElement(driver,
					driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + PolicyNo + "')]")));
			ExtentReporter.logger.log(LogStatus.INFO, "Click [Add].");

			clickButton(driver, manuscriptPageAddBtn, "Manu script Add");
		}
		Thread.sleep(4000);
		invisibilityOfLoader(driver);
		switchToFrameUsingElement(driver,
				driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + PolicyNo + "')]")));
		for (int k = 0; k < cincomedto.phase.size(); k++) {
			for (int l = 0; l < manuscriptAddListformName.size(); l++) {
				if (manuscriptAddListformName.get(l).getAttribute("innerHTML").equals(cincomedto.phase.get(k))) {
					clickButton(driver, manuscriptAddListformNameChkBox.get(l),
							"check Box for " + cincomedto.phase.get(k));
					ExtentReporter.logger.log(LogStatus.INFO, "Select " + cincomedto.phase.get(k) + ", Click done.");
					break;
				}
			}
			clickButton(driver, manuscriptAddListDoneBtn, "Done");
			Thread.sleep(4000);
			switchToParentWindowfromframe(driver);
			switchToFrameUsingElement(driver,
					driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + PolicyNo + "')]")));
			ExtentReporter.logger.log(LogStatus.INFO, "Enter additional text: " + cincomedto.phase.get(k) + "");
			enterTextIn(driver, addText, cincomedto.phase.get(k) + " form added.", "Aditional Text");
			clickButton(driver, manuscriptPageSaveBtn, "Manu Script page Save");
			Thread.sleep(4000);

			clickButton(driver, dataEntryBtn, "Data Entry");
			Thread.sleep(4000);
			String parentWindowId = switchToWindow(driver);
			titleHFLHPLCHGGE.clear();
			enterTextIn(driver, titleHFLHPLCHGGE, "Automated Test CHGGE", " Cincom Title");
			clickButton(driver, freeFormCHGGEBeginChkBox, "freeFormCHGGEBeginChkBox");
			Thread.sleep(2000);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("tinyMCE.activeEditor.setContent('<p>Automated Test Case {"
					+ comUtil.getSystemDatemmddyyyy()
					+ "}</p>This test is to <ul><li>Adds the form</li><li>Enter data entry </li><li>Verify Bulletpoints display as entered</li></ul>')");
			executor.executeScript("document.getElementById('mceu_43-open').innerHTML = 'Arial';");
			executor.executeScript("document.getElementById('mceu_44-open').innerHTML = '10pt';", "");
			driver.switchTo().defaultContent();
			clickButton(driver, DelOptions, "Delivery Options");
			boolean chkspellpopupvalue = verifyCheckSpellingPopup();
			if (chkspellpopupvalue == true) {
				do {
					clickButton(driver, ignoreAllBtn, "Ignore All");
					Thread.sleep(1000);
				} while (verifyCheckSpellingPopup() == true);
			}
			clickButton(driver, DelOptions, "Delivery Options");
			clickButton(driver, deliverBtn, "deliver button");
			visibilityOfElement(driver, sucessMsg, "Sucess Message");
			driver.close();
			switchToParentWindowfromotherwindow(driver, parentWindowId);

			switchToFrameUsingElement(driver,
					driver.findElement(By.xpath("//iframe[contains(@src,'policyNo=" + PolicyNo + "')]")));

			clickButton(driver, manuscriptPageSaveBtn, "Manu Script page Save");
			Thread.sleep(2000);
			clickButton(driver, manuscriptPageCloseBtn, "Manu Script page Close");
			switchToParentWindowfromframe(driver);
		}
		return new RateApolicyPage(driver);
	}

	public boolean verifyCheckSpellingPopup() throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			if (wait.until(ExpectedConditions.visibilityOf(spellchkBoxHeading)) != null)
				ExtentReporter.logger.log(LogStatus.INFO, "Spell check pop up window displayed..");
			return true;
		} catch (Exception e) {
			ExtentReporter.logger.log(LogStatus.INFO, "Spell check pop up window is NOT displayed..");
			return false;
		}
	}

}
