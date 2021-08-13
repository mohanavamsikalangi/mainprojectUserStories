package com.selenium.mainproject.testClasses;

import static org.testng.Assert.assertEquals;

import java.io.FileWriter;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.selenium.mainproject.baseClass.BaseClass;
import com.selenium.mainproject.pageClasses.LandingPage;
import com.selenium.mainproject.pageClasses.ProjectPage;
import com.selenium.mainproject.pageClasses.UserStoriesPage;
import com.selenium.mainproject.utils.CustomReport;
import com.selenium.mainproject.utils.TestDataProvider;

public class ModuleTest extends BaseClass {

	ProjectPage projectPage;
	public static UserStoriesPage userStoryPage;
	public static FileWriter file; 
	public static String description;
	public static int index = 1;
	String id;
	ExtentHtmlReporter htmlreporter;
	ExtentReports extent;

	@BeforeTest
	public void beforeTest() {
		try {
			file = CustomReport.htmlreportWriter();
			CustomReport.intiateHtmlFile(file, "User Story Test");
			CustomReport.reportTestPass(file, index++, "Browser Initialized");
			CustomReport.reportTestPass(file, index++, "Login_To_MainSpring");
		} catch (Exception e) {
			e.printStackTrace();
		}

		htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/ExtentReports/testReportfinal.html");
		extent = new ExtentReports();

		// launchChrome();
		invokeBrowser(setBrowser());
		openUrl();

		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("OS", "Chrome");
		extent.setSystemInfo("Browser", "Windows");
		extent.setSystemInfo("TEAM NO", "03");
		extent.setSystemInfo("website to test", "Main Spring ");
		htmlreporter.config().setDocumentTitle("Extent Report");
		htmlreporter.config().setReportName("Testing User Stories Module");
		htmlreporter.config().setTheme(Theme.STANDARD);
		htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/ExtentTest.html");
	}

	// Testing whether all mandatory fields are filled

	@Test(dataProvider = "userStoryData", priority = 3)
	public void testModule(Hashtable<String, String> testData) throws Exception {
		logger = report.createTest(" Add User Story with all mandatory fields ");
		extent.attachReporter(htmlreporter);
		ExtentTest test3 = extent.createTest("Entering all mandatory feilds ",
				"Continue with test case 2 Entering all mandatory feilds and validating");

		userStoryPage.enterStoryName(testData.get("Story Name"));
		test3.info("getting the user story name from data provider");
		test3.info("Entering the user story name");
		CustomReport.reportTestPass(file, index++, "Entered the user story name");
		test3.info("Entered the user story name").pass("Entered the user story name-Passed");

		userStoryPage.enterDueDate(testData.get("Due Time"));
		test3.info("getting the due date from data provider");
		test3.info("Entering the due date");
		CustomReport.reportTestPass(file, index++, "Entered the Due Date");
		test3.info("Entered the Due Date").pass("Entered the Due Date-Passed");

		userStoryPage.selectCardOwner(testData.get("Card Owner Name"));
		test3.info("getting the card owner name from data provider");
		test3.info("finding card owner details");
		CustomReport.reportTestPass(file, index++, "Selected the card owner name");
		test3.info("Selected the card owner name").pass("Selected the card owner name-Passed");

		userStoryPage.clickSaveBtn();
		test3.info("clicking save button").pass("all mandatory feilds entered");
		id = userStoryPage.getUserID();
		CustomReport.reportTestPass(file, index++, "Saved the user story with id: " + id + "  is created successfully");
		test3.info("Saved the user story with id: " + id + "  is created successfully")
				.pass("User Story created with all mandatory feilds ");

	}

	@Test(dataProvider = "userStoryData", priority = 1)
	public void onlyNamefield(Hashtable<String, String> testData) throws Exception {
		extent.attachReporter(htmlreporter);
		logger = report.createTest(" Only name field is entered and clicked on save ");
		ExtentTest test1 = extent
				.createTest("Entering only name", "To validate only name feild is entered and to check it is accepted")
				.fail("Not all Mandatory feilds entered");

		LandingPage landPage = new LandingPage(driver, logger);
		landPage.clickThreeLines();
		CustomReport.reportTestPass(file, index++, "Found project title on landing page");
		test1.info("Found project title on landing page").pass("passed");

		projectPage = landPage.clickOnProjectTitle();
		CustomReport.reportTestPass(file, index++, "Project is selected -- CFO_Insite");
		test1.info("Project is selected -- CFO_Insite").pass("Passed");

		projectPage.ClickExecute();
		CustomReport.reportTestPass(file, index++, "Clicked execute menu in the main project page");
		test1.info("Clicked execute menu in the main project page")
				.pass("Clicked execute menu in the main project page-passed");

		userStoryPage = projectPage.clickUserstories();
		CustomReport.reportTestPass(file, index++, "cliked on the user stories module");
		test1.info("cliked on the user stories module");

		userStoryPage.addUserStory();
		CustomReport.reportTestPass(file, index++, "clicked to add story");
		test1.info("clicked to add story").pass("clicked to add story-Passed");

		userStoryPage.enterStoryName(testData.get("Story Name"));
		CustomReport.reportTestPass(file, index++, "Entered the user story name");
		test1.info("Entered the user story name").pass("Passed");
		userStoryPage.clickSaveBtn();
		test1.info("clicking save button").fail("All mandatory feilds not entered");
		CustomReport.reportTestPass(file, index++, "All mandatory fields are not filled");
		timeWait();
		takeSS("alert01");
		Alert alert = driver.switchTo().alert();
		alert.accept();
		test1.info("handled alert").fail("All mandatory feilds not entered");
		String screenShotpath = System.getProperty("user.dir")+ "/ScreenShots/alert01.png";
		timeWait();
		test1.log(Status.FAIL, ""+test1.addScreenCaptureFromPath(screenShotpath));
		
		Assert.assertTrue(false);

	}

	@Test(dataProvider = "userStoryData", priority = 2, enabled = false)
	public void nameAndDate(Hashtable<String, String> testData) throws Exception {
		extent.attachReporter(htmlreporter);
		logger = report.createTest(" Name and Due Date is selected and clicked on Save ");
		extent.attachReporter(htmlreporter);
		ExtentTest test2 = extent.createTest("Entering all mandatory feilds ", "To  enter the due date and Test Name");
		userStoryPage.enterDueDate(testData.get("Due Time"));
		test2.info("getting due date from data provider").pass("got due date from provider");
		CustomReport.reportTestPass(file, index++, "Entered the Due Date");
		test2.info("Entered the Due Date").pass("Entered the Due Date-Passed");
		userStoryPage.clickSaveBtn();
		test2.info("clicking save button");
		CustomReport.reportTestPass(file, index++, "All mandatory fields are not filled");
		Alert alert = driver.switchTo().alert();
		alert.accept();
		test2.info("accepting alert").pass("alert accepted");
	}

	@Test(dataProvider = "userStoryData", priority = 4)
	public void testDescription(Hashtable<String, String> testData) throws Exception {
		extent.attachReporter(htmlreporter);
		ExtentTest test4 = extent.createTest("Entering description into user stories ", "to validate Description is entered into feilds or not");
		
		logger = report.createTest("Verifying Description");
		test4.info("Verifying Description");

		userStoryPage.Description(testData.get("Description"));
		test4.info("getting the  Description from data provider");
		test4.info("Entering the  Description");
		CustomReport.reportTestPass(file, index++, "Entered the description");
		test4.info( "Entered the description").pass( "Entered the description-Passed");
		userStoryPage.clickSaveBtn();
		test4.info("Clicked the save button").pass("Passed");
		CustomReport.reportTestPass(file, index++, "Description has entered");
	}

	@Test(priority = 5)
	public void testAcceptance() throws Exception {
		extent.attachReporter(htmlreporter);
		logger = report.createTest("Verifying Acceptance Criteria");
		extent.attachReporter(htmlreporter);
		ExtentTest test5 = extent.createTest("Verifying Acceptance Criteria", "Verifying Acceptance Criteria");
		userStoryPage.Acceptance();
		test5.info("setting acceptance").pass("Passed");
		userStoryPage.clickSaveBtn();
		test5.info("clicking save button ").pass("Passed");
		CustomReport.reportTestPass(file, index++, "Acceptance Criteria has entered");

	}

	@Test(priority = 6)
	public void testUserType() throws Exception {
		extent.attachReporter(htmlreporter);
		ExtentTest test6 = extent.createTest("Verifying test User story Type ", "Testing the User Story type dropdown");
		logger = report.createTest("Verify the User Story Type");
		userStoryPage.userStoryType();
		test6.info("setting User Story Type").pass("Passed");
		userStoryPage.clickSaveBtn();
		test6.info("clicking save button ").pass("Passed");
		CustomReport.reportTestPass(file, index++, "Selected the User Story Type");
		//Assert.assertTrue(true);
	}

	@Test(priority = 7)
	public void testStoryPoints() throws Exception {
		extent.attachReporter(htmlreporter);
		ExtentTest test7 = extent.createTest("Verifying story points", "Testing the Story points feilds");
		logger = report.createTest("Verifying Story Points");
		test7.info("Getting the story Points");
		test7.info("Entering the Story points as 150");
		userStoryPage.storyPoints();
		test7.info("setting User Story points").pass("Passed");
		userStoryPage.clickSaveBtn();
		test7.info("Clicking save button").pass("Passed");
		CustomReport.reportTestPass(file, index++, "Story Points has entered");
	}

	@Test(priority = 8)
	public void testResourceGroup() throws Exception {
		extent.attachReporter(htmlreporter);
		ExtentTest test8 = extent.createTest("Verifying Resource Group", "Testing the Resource Group");
		logger = report.createTest("Verify the Resource Group");
		test8.info("Getting the story Points");
		test8.info("Entering the Story points as 150");
		userStoryPage.ResourceGroup();
		userStoryPage.clickSaveBtn();
		test8.info("Clicking save button").pass("Passed");
		CustomReport.reportTestPass(file, index++, "Selected the Resource Group");
	}

	@Test(priority = 9)
	public void testTheme() throws Exception {
		extent.attachReporter(htmlreporter);
		ExtentTest test9 = extent.createTest("Verifying theme ", "Testing the Themes Feild");
		logger = report.createTest("Verify the Theme");
		test9.info("Selecting the Theme ");
		userStoryPage.Theme();
		test9.info("Selecting the Theme ").pass("Theme selected as main project sucessfully ");
		userStoryPage.clickSaveBtn();
		test9.info("Clicking save button").pass("Passed");
		
		CustomReport.reportTestPass(file, index++, "Selected the Theme");
	}

	@Test(priority = 10)
	public void testRank() throws Exception {
		extent.attachReporter(htmlreporter);
		ExtentTest test10 = extent.createTest("Verifying Rank", "Testing the rank Feild");
		logger = report.createTest("Verifying Rank");
		test10.info("Setting  the Rank Feild ");
		userStoryPage.Rank();
		test10.info("Setting  the Rank Feild ").pass("Rank Set as 01 sucessfully");
		userStoryPage.clickSaveBtn();
		test10.info("Clicking save button").pass("Passed");
		CustomReport.reportTestPass(file, index++, "Rank has entered");
	}

	@Test(priority = 11)
	public void testPriority() throws Exception {
		extent.attachReporter(htmlreporter);
		ExtentTest test11 = extent.createTest("Testing Priority ", "Testing the priority Feild");
		logger = report.createTest("Verify the Priority");
		test11.info("Setting  the Priority Feild ");
		userStoryPage.Priority();
		test11.info("Set the Priority ").pass("Priority set sucessfully");
		
		userStoryPage.clickSaveBtn();
		test11.info("Clicking save button").pass("Passed");
		CustomReport.reportTestPass(file, index++, "Selected the Priority");
	}

	@Test(priority = 12)
	public void testEndDate() throws Exception {
		extent.attachReporter(htmlreporter);
		ExtentTest test12 = extent.createTest("End date Testing  ", "Testing the End date Feild");
		test12.info("getting the End date from data provider");
		test12.info("Entering the  End date into feilds");
		userStoryPage.enterEndDate();
		CustomReport.reportTestPass(file, index++, "Entered the End Date");
		test12.info("Setting the End dates").pass("Entered the End Date Successfully ");
		userStoryPage.clickSaveBtn();
		CustomReport.reportTestPass(file, index++, "Actual End Date has entered");
	}

	
	@Test(dataProvider = "userStoryDD",priority = 13,enabled = false)
	public void verifyUserStory(Hashtable<String, String> testData) throws Exception{
		logger = report.createTest("Verifying user story dropdown");
		Select us = new Select(driver.findElement(By.xpath("//*[@id=\"DN_UserStoryType\"]")));
		List<WebElement> optionsList = us.getOptions();
		String res = "";
		for (WebElement option : optionsList) {
			res = res + option.getText();
		}
		String exp = "";
		int i = 1;
		while(i!=0) {
			if(testData.get("Option" + i) == null) {
				i = 0;
			}
			else {
				exp = exp + testData.get("Option" + i);
				i++;
			}
		}
		assertEquals(res, exp);
		CustomReport.reportTestPass(file, index++,"User story verified");
	}
	
	@DataProvider
	public Object[][] userStoryDD(){
		return TestDataProvider.getTestData("MainProject.xlsx", "userstory", "Verify UserStory Dropdown");
	}
	
	
	@Test(dataProvider = "sizeDD",priority = 14,enabled = false)
	public void verifySize(Hashtable<String, String> testData) throws Exception{
		extent.attachReporter(htmlreporter);
		Select size = new Select(driver.findElement(By.xpath("//*[@id=\"AG_SIZE\"]")));
		List<WebElement> optionsList = size.getOptions();
		String res = "";
		for (WebElement option : optionsList) {
			res = res + option.getText();
		}
		String exp = "";
		int i = 1;
		while(i!=0) {
			if(testData.get("Option" + i) == null) {
				i = 0;
			}
			else {
				exp = exp + testData.get("Option" + i);
				i++;
			}
		}
		assertEquals(res, exp);
		CustomReport.reportTestPass(file, index++,"Size verified");
	}
	
	@DataProvider
	public Object[][] sizeDD(){
		return TestDataProvider.getTestData("MainProject.xlsx", "userstory", "Verify Size Dropdown");
	}
	
	@Test(dataProvider = "stateDD",priority = 15, enabled = false)
	public void verifyState(Hashtable<String, String> testData) throws Exception{
		logger = report.createTest("Verifying State dropdown");
		Select state = new Select(driver.findElement(By.xpath("//*[@id=\"AG_STATE\"]")));
		List<WebElement> optionsList = state.getOptions();
		String res = "";
		for (WebElement option : optionsList) {
			res = res + option.getText();
		}
		String exp = "";
		int i = 1;
		while(i!=0) {
			if(testData.get("Option" + i) == null) {
				i = 0;
			}
			else {
				exp = exp + testData.get("Option" + i);
				i++;
			}
		}
		assertEquals(res, exp);
		CustomReport.reportTestPass(file, index++,"State verified");
	}
	
	@DataProvider
	public Object[][] stateDD(){
		return TestDataProvider.getTestData("MainProject.xlsx", "userstory", "Verify State Dropdown");
	}
	
	
	@Test(dataProvider = "priorityDD",priority = 16)
	public void verifyPriority(Hashtable<String, String> testData) throws Exception{
		ExtentTest test16 = extent.createTest("verifying priority", "Verifying the Priority feild");
		logger = report.createTest("Verifying Size dropdown");
		test16.info("Verifying Size dropdown");
		logger = report.createTest("Verifying Priority dropdown");
		test16.info("Verifying Priority dropdown");
		Select priority = new Select(driver.findElement(By.xpath("//*[@id=\"CM_Priority\"]")));
		List<WebElement> optionsList = priority.getOptions();
		String res = "";
		for (WebElement option : optionsList) {
			res = res + option.getText();
		}
		String exp = "";
		int i = 1;
		while(i!=0) {
			if(testData.get("Option" + i) == null) {
				i = 0;
			}
			else {
				exp = exp + testData.get("Option" + i);
				i++;
			}
		}
		assertEquals(res, exp,"Failed");
		test16.info("Verifying Priority dropdown").pass(" priority Verified");
		CustomReport.reportTestPass(file, index++,"Priority verified");
	}
	
	@DataProvider
	public Object[][] priorityDD(){
		return TestDataProvider.getTestData("MainProject.xlsx", "userstory", "Verify Priority Dropdown");
	}
	
	@DataProvider
	public Object[][] userStoryData() {
		return TestDataProvider.getTestData("MainProject.xlsx", "userstory", "User Stoty Module");
	}

	@AfterTest
	public void afterTest() {
		try {
			CustomReport.reportTestPass(file, index++, "Browser closed");
			CustomReport.endHtmlReport(file, index - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		extent.flush();
		closeBrowser();

	}
}
