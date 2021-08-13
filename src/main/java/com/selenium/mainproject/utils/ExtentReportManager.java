package com.selenium.mainproject.utils;
/*
 * This class is used to create extent report of test cases
 */
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportManager {

	public static ExtentReports report;
	public static ExtentReports getReportInstance() {
		
		
		if (report == null) {
			
			
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//ExtentReports//"+DateUtils.getTimeStamp()+".html");
			report = new ExtentReports();
			report.attachReporter(htmlReporter);
			
			report.setSystemInfo("Project", "Main Project");
			report.setSystemInfo("Cohort", "INTQEA21SD006");
			report.setSystemInfo("Team", "03 ");
			report.setSystemInfo("Project Module", "User Story");
			
			htmlReporter.config().setDocumentTitle("User Story Module");
			htmlReporter.config().setReportName("Cohort06--Team 03's Main project");
			htmlReporter.config().setTimeStampFormat("dd-MMM-yyyy HH:mm:ss");
		}
		return report;
	}
}
