package com.mm.dto;

import MMTestCase.SmokeTestCase;

public class FinanacePageDTO {
	
	public String policyNo;
	public Object currunetBalance;
	
	public FinanacePageDTO() throws IllegalArgumentException, IllegalAccessException, SecurityException{
		
		for (int iFC = 0; iFC < FinanacePageDTO.class.getFields().length; iFC++) {
			
			if (FinanacePageDTO.class.getFields()[iFC].getType().toString().toLowerCase().contains("java.util.list")) {
				try {
					FinanacePageDTO.class.getFields()[iFC].set(this,
							SmokeTestCase.testDataMap.get(FinanacePageDTO.class.getFields()[iFC].getName().toLowerCase()));

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				try {
					FinanacePageDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap
							.get(FinanacePageDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
