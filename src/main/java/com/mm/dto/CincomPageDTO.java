package com.mm.dto;

import java.util.List;

import MMTestCase.SmokeTestCase;

public class CincomPageDTO {
	
	public List<String> coverage;
	public List<String> coverageList;
	public List<String> phase;

	
	public CincomPageDTO() throws IllegalArgumentException, IllegalAccessException, SecurityException{
		
		for (int iFC = 0; iFC < RateAPolicyPageDTO.class.getFields().length; iFC++) {
			
			if (RateAPolicyPageDTO.class.getFields()[iFC].getType().toString().toLowerCase().contains("java.util.list")) {
				try {
					RateAPolicyPageDTO.class.getFields()[iFC].set(this,
							SmokeTestCase.testDataMap.get(RateAPolicyPageDTO.class.getFields()[iFC].getName().toLowerCase()));

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(SmokeTestCase.testDataMap.get(RateAPolicyPageDTO.class.getFields()[iFC].getName()));
				}

			} else {
				try {
					RateAPolicyPageDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap
							.get(RateAPolicyPageDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(SmokeTestCase.testDataMap.get(RateAPolicyPageDTO.class.getFields()[iFC].getName()));
				}
			}
		}
	}
}
