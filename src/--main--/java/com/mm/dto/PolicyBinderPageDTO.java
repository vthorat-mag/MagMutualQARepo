package com.mm.dto;

import java.util.List;

import MMTestCase.SmokeTestCase;

public class PolicyBinderPageDTO {

	public String policyNumber;
	public String valueOfPolicyActionEndorse;
	public String saveAsPolicyValue;
	public String ProductNotifyValue;
	public String valueOfSelectReason;
	public String valueOfPolicyActionCopyToQuote;
	public String FileSearchPageTitle;
	public String addFilePageTitle;
	public String entitySelectListPageTitle;
	public String entitySearchListPageTitle;
	public String fileTypeDropDownValue;
	public String lobDropDownValue;
	public String fileHandlerDropDownValue;
	public String stateOfLossDropDownValue;
	public String searchEntityPageTitle;
	public String lastName;
	public String firstName;
	public String description;
	
	
public PolicyBinderPageDTO() throws IllegalArgumentException, IllegalAccessException, SecurityException{
		
	for (int iFC=0; iFC < PolicyBinderPageDTO.class.getFields().length; iFC++) 
	{
		if(PolicyBinderPageDTO.class.getFields()[iFC].getType().toString().toLowerCase().contains("java.util.list"))
		{
			try{
				PolicyBinderPageDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap.get(PolicyBinderPageDTO.class.getFields()[iFC].getName().toLowerCase()));
		
			}catch(Exception e){
			 e.printStackTrace();
			 System.out.println(SmokeTestCase.testDataMap.get(PolicyBinderPageDTO.class.getFields()[iFC].getName()));
			}
			
		}else				
		{
			try{
				PolicyBinderPageDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap.get(PolicyBinderPageDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));
			
			}catch(Exception e){
				 e.printStackTrace();
				 System.out.println(SmokeTestCase.testDataMap.get(PolicyBinderPageDTO.class.getFields()[iFC].getName()));
					}
			
			}
	}
}
	
}
