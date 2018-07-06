package com.mm.dto;

import MMTestCase.SmokeTestCase;

public class ClaimsDTO {
	
	public static String addFilePageTitle;
	public static String entitySearchListPageTitle;
	public static String lastName;
	public static String firstName;
	public static String fileTypeDropDownValue;
	public static String lobDropDownValue;
	public static String fileHandlerDropDownValue;
	public static String stateOfLossDropDownValue;
	public static String searchEntityPageTitle;
	public static String description;
	
	public ClaimsDTO() throws Exception{
		
		for (int iFC=0; iFC < ClaimsDTO.class.getFields().length; iFC++)
		{
			ClaimsDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap.get(ClaimsDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));
			
		}
	}
}
