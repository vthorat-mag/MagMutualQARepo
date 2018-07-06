package com.mm.dto;

import MMTestCase.SmokeTestCase;

public class PolicySubmissionPageDTO {
	
	public String policyPhase;
	public String organisationType;
	public String discoveryPeriodRating;
	public String quoteDescription;
	public String valueOfPolicyActionCopy;
	public String indicationPhaseValue;
	
	
public PolicySubmissionPageDTO() throws IllegalArgumentException, IllegalAccessException, SecurityException{
		
	
		for (int iFC=0; iFC < PolicySubmissionPageDTO.class.getFields().length; iFC++)
		{
			if (SmokeTestCase.testDataMap.containsKey(PolicySubmissionPageDTO.class.getFields()[iFC].getName().toLowerCase()))
			{
			try{
			PolicySubmissionPageDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap.get(PolicySubmissionPageDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));
			}catch(Exception e){
				e.printStackTrace();
				System.out.println(SmokeTestCase.testDataMap.get(PolicySubmissionPageDTO.class.getFields()[iFC].getName()));
			}
		}
	}
  }
}
