package com.mm.utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	String xlFilePath = System.getProperty("user.dir")+"\\src\\main\\resources\\Form_Data.xlsx";
	
	ExcelApiTest eat = null;
	List<String> listRowData =null;

	//Logic to get the excel sheet data into object.
	public HashMap<String, List<String>> testData(String sheetName) throws Exception {
		
		HashMap<String, List<String>> excelData = new HashMap<String, List<String>>();
		//Read excel sheet and get rows and column count
		eat = new ExcelApiTest(xlFilePath);
		int rows = eat.getRowCount(sheetName);
		int columns = eat.getColumnCount(sheetName);

		//Browse through all columns
		for (int j = 0; j < columns; j++) {
			//Read the ColumnName
			String sColumnName = eat.getCellData(sheetName, j, 0).toLowerCase();
			listRowData = new ArrayList<String>();
			
			//Read All rows
			for (int i = 1; i < rows; i++) {
				listRowData.add(eat.getCellData(sheetName, j, i));
			}			
			//Populate the HashMap
			
			excelData.put(sColumnName, listRowData);			
		}
		
		return excelData;
	}
}

/*for (int i = 1; i < rows; i++) {
for (int j = 0; j < columns; j++) {
	excelData[i - 1][j] = eat.getCellData(sheetName, j, i);
}
}*/