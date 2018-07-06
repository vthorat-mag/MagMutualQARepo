package com.mm.dto;
import MMTestCase.SmokeTestCase;

public class LoginPageDTO {
	public String username;
	public String password;
		
	public LoginPageDTO() throws IllegalArgumentException, IllegalAccessException, SecurityException{
		
		for (int iFC=0; iFC < LoginPageDTO.class.getFields().length; iFC++)
		{
			LoginPageDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap.get(LoginPageDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));
			
		}
		
		
	}
	

}
