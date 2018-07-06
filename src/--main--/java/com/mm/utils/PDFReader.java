package com.mm.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.management.relation.InvalidRelationServiceException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mm.dto.pdfReaderDTO;
import com.mm.pages.PolicyBinderPage;
import com.mm.pages.RateApolicyPage;
import com.relevantcodes.extentreports.LogStatus;

import bsh.Parser;

public class PDFReader extends CommonAction {
	CommonUtilities comUtil = new CommonUtilities();
	WebDriver driver;
	pdfReaderDTO pdfreaderdto;
	int i =0;
	
	//AUTOIT script execution to save PDF.
	public PDFReader savePDF() throws IOException, InterruptedException {
		//invisibilityOfLoader(driver);
		Thread.sleep(6000);
		String[] savePDFPath = 
				{System.getProperty("user.dir") + "\\src\\main\\resources\\StoredPDF\\pdfDocument.pdf"};
		String[] executionPath = {System.getProperty("user.dir") + "\\src\\main\\java\\autoItScripts\\savePdf.exe"};
		Runtime.getRuntime().exec(executionPath);
		//ProcessBuilder pb = new ProcessBuilder(executionPath);
		return new PDFReader(driver);
	}
	
	public PDFReader(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		pdfreaderdto = new pdfReaderDTO();
	}

	//Logic to verify PDF content.
	public PolicyBinderPage verifyPdfContent(String PolicyNo) throws IOException, AWTException, InterruptedException, IllegalArgumentException, IllegalAccessException, SecurityException {
		Thread.sleep(15000);
		//getPageTitle(driver, "Policy Folder "+PolicyNo);
		boolean flag = false;
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		String parsedText = null;
		int noOfPDFPages = 0;

		try {
			File file = new File("C:\\savePDF\\verifyPDF.pdf");
			pdDoc = PDDocument.load(file);
			noOfPDFPages = pdDoc.getNumberOfPages();
			pdfStripper = new PDFTextStripper();
			parsedText = pdfStripper.getText(pdDoc);
		} catch (MalformedURLException e2) {
			System.err.println("URL string could not be parsed " + e2.getMessage());
		} catch (IOException e) {
			System.err.println("Unable to open PDF Parser. " + e.getMessage());
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e1) {
				e.printStackTrace();
			}
		}
		try {
			  for (i =0;i<pdfreaderdto.verifyPDFcontent.size();i++)
				{
				Assert.assertTrue(parsedText.contains(pdfreaderdto.verifyPDFcontent.get(i)), "PDF pages does not contain '" + pdfreaderdto.verifyPDFcontent.get(i) + "'.");
				ExtentReporter.logger.log(LogStatus.PASS, "Verify footer display '" + pdfreaderdto.verifyPDFcontent/*.get(i)*/ + "'.");
				break;
			}
		}catch (Exception e){
				ExtentReporter.logger.log(LogStatus.FAIL, "Expceted value  is not present in PDF.");
		}
		return new PolicyBinderPage(driver);
	}
}
