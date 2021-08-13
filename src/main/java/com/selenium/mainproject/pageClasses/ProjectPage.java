package com.selenium.mainproject.pageClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.mainproject.baseClass.BaseClass;

public class ProjectPage extends BaseClass{

	
	WebDriver driver;      //declaration of the web driver public in in order to share the instance of driver
	public ExtentTest logger;      //To log the statements on to the extent report
	
	//to pass the instance of driver and logger
	public ProjectPage(WebDriver driver,ExtentTest logger) {
		this.driver=driver;
		this.logger=logger;
		PageFactory.initElements(driver, this);
	}
	
	//web element of execute menu
	@FindBy(id="LOCK_Execute")
	WebElement ExecuteBtn;
	
	//web element of user story module
	@FindBy(id="LOCK_User_Stories")
	WebElement UserStoriesbtn;
	
	//this method is used to click on execute menu
	public void ClickExecute() {
		logger.log(Status.INFO, "Checking if execute is found and able to find modules");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Actions action = new Actions(driver);
		action.moveToElement(ExecuteBtn).perform();
		logger.log(Status.PASS, "Modules are visible");
	}
	
	//this method is used to select user story module and return object of user story page
	public UserStoriesPage clickUserstories() {
		logger.log(Status.INFO, "checking if Userstories is visible");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		UserStoriesbtn.click();
		logger.log(Status.PASS, "clicked on user stories module and navigating to user stories page");
		return new UserStoriesPage(driver, logger);
		
	}
}
