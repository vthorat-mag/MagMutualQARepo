package com.mm.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mm.utils.ExcelUtil;
import com.mm.utils.CommonAction;
import com.mm.utils.CommonUtilities;

import BaseClass.CommonActionInterface;

public class CISPage  extends CommonAction{
	
	WebDriver driver;
	
	
	@FindBy(id="CI_NEW_ORG")				
	WebElement New_Org;

	@FindBy(name="entity_veryLongName")
	WebElement Long_Name;

	@FindBy(name="entity_organizationName")
	WebElement CIS_OrgName;

	@FindBy(name="address_addressTypeCode")
	WebElement Addr_Type;

	@FindBy(name="address_addressLine1")

	WebElement Addr_Line1;
	@FindBy(name="address_addressLine2")
	WebElement Addr_Line2;

	@FindBy(name="address_addressLine3")
	WebElement Addr_Line3;

	@FindBy(name="address_city")
	WebElement Addr_City;

	@FindBy(name="address_stateCode")
	WebElement State;

	@FindBy(xpath="//input[@type='button' and @value='OK']")
	WebElement OK;

	@FindBy(name="phoneNumber_phoneNumber_DISP_ONLY")
	WebElement Ph_no;

	@FindBy(name="phoneNumber_areaCode")
	WebElement AreaCode;

	@FindBy(name="entityClass_entityClassCode")
	WebElement Classification;

	@FindBy(name="entityClass_effectiveToDate")			
	WebElement Eff_To_Date;

	@FindBy(id="CI_ENTADDOU_SAV")
	WebElement Save_btn;

		
	public CISPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	public void clickOnNewOrganization()
	{
		click(driver,New_Org, "New Organization tab");
	}
			
	public void enterDataInNewOrgPage(String LongName,String Address_Line1,String City,String Phone_no,String Area_code, String Class_Eff_To_Date) throws Exception{

		ExcelUtil exlutil = new ExcelUtil();
   	    enterTextIn(driver,Long_Name,LongName,"Long Name");
	    
   	    click(driver,CIS_OrgName, "Org Name text field");				
	    
	    enterTextIn(driver,Addr_Line1,Address_Line1,"Adress Line");
	    
	    enterTextIn(driver,Addr_City,City,"City");
	    
	    enterTextIn(driver,Ph_no,Phone_no,"Phone Number");
	    
	    enterTextIn(driver,AreaCode,Area_code,"Area Code");
	    Thread.sleep(2000);
	    
	    enterTextIn(driver,Eff_To_Date,Class_Eff_To_Date,"Class_Eff_To_Date");
	    
	    selectDropdownByValue(driver,Classification, "CARRIER", "Classification");
		
	    selectDropdownByValue(driver,Addr_Type, "POLICY", "Address_Type");
	    
		selectDropdownByValue(driver,State,"GA","State");
	    Thread.sleep(3000);
	}
	   
	    public void selectZipCode() throws InterruptedException
	    {
	    	   String parentwindow = switchToWindow(driver);
	    	   Thread.sleep(2000);
	    	  	    	   
	    	   WebElement zipCode = driver.findElement(By.xpath("//input[@value='30004']")); 
	    	   click(driver,zipCode,"ZipCode");

	    	   click(driver,OK,"OK button");

	    	   Thread.sleep(2000);
      	       switchToParentWindowfromotherwindow(driver, parentwindow); 
      	      
		}
		
		public void saveNewOrgDetails() throws InterruptedException
		{
			Thread.sleep(3000);
			click(driver,Save_btn, "Save button");
		}
	}
	
