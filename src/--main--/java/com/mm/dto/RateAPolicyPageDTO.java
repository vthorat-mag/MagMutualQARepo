package com.mm.dto;

import java.util.List;

import MMTestCase.SmokeTestCase;

public class RateAPolicyPageDTO {
	
	public List<String> coverageNames;
	public List<String> binderForms;
	public List<String> phase;
	public String valueOfPolicyActionAccept;
	public String billingSetup;
	public String paymentPlanValue;
	public String saveAsPolicyValue;
	public String productNotifyValue;
	public String coverageFromCoverageTabGrid;
	public String manuscriptForm;
	public String endorsementReason;
	public String policyAction;
	public String policyNum;
	public String viewModeOfficial;
	public String viewModeWIP;
	
	
public RateAPolicyPageDTO() throws IllegalArgumentException, IllegalAccessException, SecurityException{
		
		for (int iFC=0; iFC < RateAPolicyPageDTO.class.getFields().length; iFC++)
		{
			try{
				RateAPolicyPageDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap.get(RateAPolicyPageDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));
			
			}catch(Exception e){
				 e.printStackTrace();
				}
		}
	}
}