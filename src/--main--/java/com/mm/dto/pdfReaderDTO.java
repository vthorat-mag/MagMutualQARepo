package com.mm.dto;

import java.util.List;

import MMTestCase.SmokeTestCase;

public class pdfReaderDTO {
	
	public List<String> verifyPDFcontent;
	
	public pdfReaderDTO() {
		for (int iFC = 0; iFC < pdfReaderDTO.class.getFields().length; iFC++) {
			
			if (pdfReaderDTO.class.getFields()[iFC].getType().toString().toLowerCase().contains("java.util.list")) {
				try {
					pdfReaderDTO.class.getFields()[iFC].set(this,
							SmokeTestCase.testDataMap.get(pdfReaderDTO.class.getFields()[iFC].getName().toLowerCase()));

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(SmokeTestCase.testDataMap.get(pdfReaderDTO.class.getFields()[iFC].getName()));
				}

			} else {
				try {
					pdfReaderDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap
							.get(pdfReaderDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(SmokeTestCase.testDataMap.get(pdfReaderDTO.class.getFields()[iFC].getName()));
				}
			}
		}
	}
}