package com.selenium.mainproject.pageClasses;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.mainproject.baseClass.BaseClass;
import com.selenium.mainproject.utils.DateUtils;

public class UserStoriesPage extends BaseClass{
	
	WebDriver driver;          //declaration of the web driver public in in order to share the instance of driver
	public ExtentTest logger;       //To log the statements on to the extent report

	
	public  UserStoriesPage(WebDriver driver,ExtentTest logger) {
		this.driver=driver;
		this.logger=logger;
		PageFactory.initElements(driver, this);
	}
	
	//web element of add story button
	@FindBy(id="KEY_BUTTON_Add-btnIconEl")
	WebElement AddstoryBtn;
	
	//web element of name text box
	@FindBy(id="_Text_Check_CM_Name")
	WebElement nameTextBox;

	//web element of due date text box
	@FindBy(id="CM_DUEDATE")
	WebElement dueDateTextBox;
	
	//web element of card owner drop down 
	@FindBy(id="AG_CARDOWNER")
	WebElement cardOwnerDrpDown;
	
	//web element of save button
	@FindBy(id="SaveBtn")
	WebElement saveBtn;
	
	//web element of user story ID
	@FindBy(id="CM_ItemCode")
	WebElement userStoryID;
	
	@FindBy(id="DN_Description1")
	WebElement description;
	
	@FindBy(id = "DN_AcceptanceCriteria1_ifr")
	WebElement acceptance;
	
	@FindBy(id = "DN_UserStoryType")
	WebElement userType;
	
	@FindBy(id = "CM_Release")
	WebElement release;
	
	@FindBy(id = "AG_SPRINT")
	WebElement sprint;
	
	@FindBy(id = "DN_StoryPoints")
	WebElement storyPoints;
	
	@FindBy(id = "DN_ResourceGroup")
	WebElement resourceGroup;
	
	@FindBy(id = "THEME")
	WebElement theme;
	
	@FindBy(id = "AG_RANK")
	WebElement rank;
	
	@FindBy(id = "CM_Priority")
	WebElement priority;
	
	@FindBy(id = "DN_ActualEndDate")
	WebElement endDate;
	
	@FindBy(id = "AG_SIZE")
	WebElement size;
	
	@FindBy(id = "_Text_Check_DN_MovedCard")
	WebElement movedCard;
	
	@FindBy(id = "DN_DefectBacklog")
	WebElement defectBacklog;
	
	//this method is used to click add story button
	public void addUserStory() {
		logger.log(Status.INFO, "checking functionality of adding user story");
		AddstoryBtn.click();
		logger.log(Status.PASS, "Able to add new user story");
		  driver.switchTo().frame("contentframe");
		  WebDriverWait wait = new WebDriverWait(driver, 3);
		 wait.until(ExpectedConditions.visibilityOf(nameTextBox));
		  
	}


    //To enter the story name	
	public void enterStoryName(String storyName) {
		logger.log(Status.INFO, "Entering mandatory field story name");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		senKeys(nameTextBox, storyName);
		logger.log(Status.PASS, "text box is accepting text story name");
		
	}
	
	//TO enter the due date
	public void enterDueDate(String dueDate) {
		logger.log(Status.INFO, "checking if date text box is accepting it in dd-MMM-yyyy format");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println(dueDate.substring(1, 12));
		String date=dueDate.substring(1, 12);
		senKeys(dueDateTextBox, date);
		logger.log(Status.PASS, "Calender can accept text in dd-MMM-yyyy format");
	}
	
	//To get unique User Story ID
	public String getUserID() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String userid= userStoryID.getText();
		return userid;
	}
	
	//To Save User Story
	public void clickSaveBtn() {
		logger.log(Status.INFO,"checking if save button is working");
		saveBtn.click();
		scrollUpWebPage();
		if(userStoryID.isDisplayed()) {
			String id = getUserID();
			System.out.println(id);
			logger.log(Status.PASS, "user story with id:  "+id+"  is saved successfully");
			takeScreenShot();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}else {
			Alert alert=driver.switchTo().alert();
			System.out.println(alert.getText());
			takeScreenShot();
			alert.accept();
			logger.log(Status.FAIL, "user story not saved successfully");
		}
	}
	
	
	//To select card owner name
	public void selectCardOwner(String ownername) {
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Select cardOwnerName=new Select(cardOwnerDrpDown);
		cardOwnerName.selectByVisibleText(ownername);
		logger.log(Status.PASS,"Added card owner");		
	}
	
	//to take screenshot
	public void takeScreenShot() {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") + "/ScreenShots/" + DateUtils.getTimeStamp() + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "/ScreenShots/" + DateUtils.getTimeStamp() + ".png");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//to scroll up web page
	public void scrollUpWebPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(userStoryID));
	}
	
	//to enter description
	public void Description(String des) {
		logger.log(Status.INFO, "Entering description");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		senKeys(description, des);
		logger.log(Status.PASS, "text box is accepting text description");
		
	}
	
	//to enter acceptance criteria
	public void Acceptance() {
		logger.log(Status.INFO, "Entering Acceptance Criteria");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		senKeys(acceptance, "Yes I am accepting");
		logger.log(Status.PASS, "text box is accepting text accepting criteria");

	}
	
	public void userStoryType() {
		logger.log(Status.INFO, "Select User Story Type");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Select type=new Select(driver.findElement(By.id("DN_UserStoryType")));    //(userType)
		type.selectByVisibleText("Test Automation");
		logger.log(Status.PASS,"Added User Story Type");		
	}
	
	public void storyPoints() {
		logger.log(Status.INFO, "Entering story points");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		senKeys(storyPoints, "150");
		logger.log(Status.PASS, "text box is accepting text points");
	}
	
	public void ResourceGroup() {
		logger.log(Status.INFO, "Select Resource Group");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Select resGroup=new Select(resourceGroup);
		resGroup.selectByVisibleText("Test");
		logger.log(Status.PASS,"Added Resource Group");
		
	}
	
	public void Theme() {
		
		logger.log(Status.INFO, "Select Theme");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Select the=new Select(theme);
		the.selectByVisibleText("main project");
		logger.log(Status.PASS,"Added Theme");
	}
	
	public void Rank() {
		logger.log(Status.INFO, "Entering Rank");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		senKeys(rank, "1");
		logger.log(Status.PASS, "text box is accepting text rank");
	}
	
	public void Priority() {
		logger.log(Status.INFO, "Select Priority");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Select prio=new Select(priority);
		prio.selectByVisibleText("High");
		logger.log(Status.PASS,"Added Priority");
	}
	
	public void enterEndDate() {
		logger.log(Status.INFO, "checking if date text box is accepting it in dd-MMM-yyyy format");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		senKeys(endDate, "12-Aug-2021");
		logger.log(Status.PASS, "Calender can accept text in dd-MMM-yyyy format");
	}
	
	
}
