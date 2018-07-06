package com.mm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mm.utils.CommonAction;

public class EndorsePolicyPage extends CommonAction {

	WebDriver driver;

	@FindBy(linkText = "Policy ")
	WebElement Policy_tab;

	@FindBy(linkText = "Find Policy")
	// link//a[@onclick='doMenuItem('PM_FIND_POLICY','~/policymgr/findPolicy.do?')']
	WebElement Find_Policy;

	@FindBy(name = "policyPhaseCodeMultiSelectText") // not needed
	WebElement Policy_Phase;

	@FindBy(name = "AFD_policyPhaseCode")
	WebElement PolicyPhase_Search;

	@FindBy(xpath = "//input[@type='checkbox' and @value='BINDER']")
	WebElement Binder_checkbox;

	@FindBy(id = "PM_SPOL_SEARCH")
	WebElement Search_Policy;

	public EndorsePolicyPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void findPolicy() {
		Find_Policy.click();
	}

	public void selectBinder() {

	}

}
