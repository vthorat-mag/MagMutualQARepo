package com.mm.dto;

import java.util.List;

import MMTestCase.SmokeTestCase;

public class CISPageDTO {
	
	public String LongName;
	public String Address_Line1;
	public String City;
	public String Phone_no;
	public String Area_code; 
	public String Class_Eff_To_Date;
	public String zipCode;
	public String Classification;
	public String Addr_Type;
	public String State;
	
	public String clientFirstName;
	public String clientLastName;
	public String clientNameValue;
	public String clientIDValue;
	public List <String> mainTabList;
	public List <String> personInfoTabsListbyID;
	public List <String> demographicTabMenuOption;
	public List <String> windowTitlesForDemographicTabs;
	public List <String> summaryTabMenuOption;
	public List <String> windowTitlesForSubMenuTabs;
	public List <String> backgroundTabMenuOption;
	public List <String> windowTitlesForBackgroundTabs;	
	public List <String> relationTabMenuOption;	
	public List <String> windowTitlesForRelationTabs;
	public List <String> vendorTabMenuOption;	
	public List <String> windowTitlesForVendorTabs;
	public List <String> auditTabMenuOption;
	public List <String> windowTitlesForAuditTabs;
	public List <String> allMenuOptions;

	
	public CISPageDTO() throws Exception{
		
		for (int iFC=0; iFC < CISPageDTO.class.getFields().length; iFC++) 
		{
			if(CISPageDTO.class.getFields()[iFC].getType().toString().toLowerCase().contains("java.util.list"))
			{
				try{
					CISPageDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap.get(CISPageDTO.class.getFields()[iFC].getName().toLowerCase()));
			
				}catch(Exception e){
				 e.printStackTrace();
				 System.out.println(SmokeTestCase.testDataMap.get(CISPageDTO.class.getFields()[iFC].getName()));
				}
				
			}else				
			{
				try{
					CISPageDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap.get(CISPageDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));
				
				}catch(Exception e){
					 e.printStackTrace();
					 System.out.println(SmokeTestCase.testDataMap.get(CISPageDTO.class.getFields()[iFC].getName()));
						}
				
				}
		}
	}
}
