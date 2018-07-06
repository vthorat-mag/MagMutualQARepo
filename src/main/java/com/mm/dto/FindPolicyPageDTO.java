package com.mm.dto;

import MMTestCase.SmokeTestCase;

public class FindPolicyPageDTO {
	
	
	public static String phase;
	public static String status;
	
	public FindPolicyPageDTO() throws Exception{
		
		for (int iFC=0; iFC < FindPolicyPageDTO.class.getFields().length; iFC++)
		{
			if (SmokeTestCase.testDataMap.containsKey(FindPolicyPageDTO.class.getFields()[iFC].getName().toLowerCase()))
			{
			FindPolicyPageDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap.get(FindPolicyPageDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));
			}
		}
	}
}
