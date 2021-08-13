package com.selenium.mainproject.pageClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.mainproject.baseClass.BaseClass;

public class LandingPage extends BaseClass{

	WebDriver driver;     //declaration of the web driver public in in order to share the instance of driver
	public ExtentTest logger;     //To log the statements on to the extent report
	
	//to pass the instance of driver and logger
	public LandingPage(WebDriver driver,ExtentTest logger) {
		this.logger=logger;
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//web element of three lines
	@FindBy(xpath="//*[@id=\"navbar\"]/div[3]/div[1]/span")
	WebElement threeLines;
	
	//web element of project title
	@FindBy(xpath="//*[@id=\"projectIcon\"]/ul/li[1]/a")
	WebElement projectTitle;
	
	
	//this method is used to click the three lines icon
	public void clickThreeLines() {
		logger.log(Status.INFO, "Navigating to mainspring menu");
		threeLines.click();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)							
				.withTimeout(30, TimeUnit.SECONDS) 			
				.pollingEvery(5, TimeUnit.SECONDS) 			
				.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOf(projectTitle));
		logger.log(Status.PASS, "Main spring menu is visible");
		
	}
	
    //this method is used to click the project tile and return the object of project page
	public ProjectPage clickOnProjectTitle() {
		projectTitle.click();
		logger.log(Status.PASS,"Navigating to project page");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return new ProjectPage(driver, logger);
	}
}
