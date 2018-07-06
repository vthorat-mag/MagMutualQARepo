package com.mm.dto;

import java.util.List;

import MMTestCase.SmokeTestCase;

public class PolicyIndicationPageDTO {

	public List<String> underwriterName;
	public List<String> teamName;
	public List<String> teamMembername;
	public List<String> coverage;
	public List<String> retroDate;
	public List<String>	premiumAmount;
	public List<String> retroDateValue;
	public List<String> coverageTitle;
	public List<String> coverageName;
	public List<String> form;
	public List<String> sharedGroupCoverage;
	public List<String> sharedGroupDescription;
	public String policyAction;
	public String producer;
	public String riskCountry;
	public String riskSpeciality;
	public String coverageFromPopup;
	public String policyForms;
	public String coverageClass;
	public String exposureUnit;
	
	
public PolicyIndicationPageDTO() throws IllegalArgumentException, IllegalAccessException, SecurityException{
		
		for (int iFC=0; iFC < PolicyIndicationPageDTO.class.getFields().length; iFC++) 
		{
			if(PolicyIndicationPageDTO.class.getFields()[iFC].getType().toString().toLowerCase().contains("java.util.list"))
			{
				try{
				PolicyIndicationPageDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap.get(PolicyIndicationPageDTO.class.getFields()[iFC].getName().toLowerCase()));
			
				}catch(Exception e){
				 e.printStackTrace();
				 System.out.println(SmokeTestCase.testDataMap.get(PolicyIndicationPageDTO.class.getFields()[iFC].getName()));
				}
				
			}else				
			{
				try{
				PolicyIndicationPageDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap.get(PolicyIndicationPageDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));
				
				}catch(Exception e){
					 e.printStackTrace();
					 System.out.println(SmokeTestCase.testDataMap.get(PolicyIndicationPageDTO.class.getFields()[iFC].getName()));
						}
				
				}
			
			
		}
}
	
}
