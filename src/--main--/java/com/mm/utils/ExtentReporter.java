package com.mm.utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReporter {

	public static ExtentTest logger;

	public static ExtentReports report = new ExtentReports(
			System.getProperty("user.dir") + "\\target\\Reports\\SmokeTestReport.HTML");

}
