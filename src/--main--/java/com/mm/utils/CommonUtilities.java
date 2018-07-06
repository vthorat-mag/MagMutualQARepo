package com.mm.utils;

import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;


public class CommonUtilities {
	
	
	
	public void selectDropdown(WebElement Element, String value){
		
		Select Sel = new Select(Element);
		
		Sel.selectByValue(value);
		
		}
	public void verifyFormIsAdded(List<WebElement> pageElement,String formValue){
		
			if(pageElement.get(pageElement.size()-1).getAttribute("innerHTML").trim().equals(formValue.trim())){
				
				ExtentReporter.logger.log(LogStatus.PASS, formValue+"is added successfully unedr Manuscript List");
			}else{
				
				ExtentReporter.logger.log(LogStatus.FAIL, formValue+"is Not added unedr Manuscript List");
			}
		}

	
	public String getSystemDateMMddyy_hhmmss(){
		
		 DateFormat dateFormatter = new SimpleDateFormat("MMddyy_hhmmss");
		 Date today = Calendar.getInstance().getTime();        
		 String date= dateFormatter.format(today);
		
		 return date;
	}
	
	public String getSystemDatemmddyyyy(){
		
		 DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		 Date today = Calendar.getInstance().getTime();        
		 String date= dateFormatter.format(today);
		
		 return date;
	}
	

	public void downloadedFileExists(String fileNamePath){
		
		 try{
	    	
			File file = new File(fileNamePath);
	    	
	    
	    	if(file.exists())
	    	{
	    	System.out.println("File is available at location");
	    	ExtentReporter.logger.log(LogStatus.PASS, "Excel file is available at download location.");
	    	}
	    	 
	    	}catch(Exception e)
	    	{
	    	System.out.println("File is Not available at location");
	    	ExtentReporter.logger.log(LogStatus.FAIL, "Excel file is Not available at download location.");
	    	 e.printStackTrace();	
	    	}
		
	}
	
}

 
