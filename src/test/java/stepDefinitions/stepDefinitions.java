package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.selenium.mainproject.baseClass.BaseClass;
import com.selenium.mainproject.pageClasses.LandingPage;
import com.selenium.mainproject.pageClasses.ProjectPage;
import com.selenium.mainproject.pageClasses.UserStoriesPage;
import com.selenium.mainproject.utils.ExtentReportManager;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class stepDefinitions extends BaseClass{
	
	
	@Before
	public void launchSite() {
	//launchChrome();
		invokeBrowser("chrome");
		openUrl();
	}
	
	
	@Given("^user is on landing page$")
	public void user_is_on_landing_page() throws Throwable {
		driver.findElement(By.xpath("//*[@id=\"navbar\"]/div[3]/div[1]/span")).click();
		Thread.sleep(4000);
	}

	@Then("^Finding the project title$")
	public void finding_the_project_title() throws Throwable {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"projectIcon\"]/ul/li[1]/a")).click();//*[@id="projectIcon"]/ul/li[1]/a
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);//*[@id="projectIcon"]/ul/li[1]/a/span
	}

	@Then("^Find the execute menu in project page$")
	public void find_the_execute_menu_in_project_page() throws Throwable {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement ele=driver.findElement(By.id("LOCK_Execute"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Actions action = new Actions(driver);
		action.moveToElement(ele).perform();
		driver.findElement(By.id("LOCK_User_Stories")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Then("^click Add the user story$")
	public void click_Add_the_user_story() throws Throwable {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("KEY_BUTTON_Add-btnIconEl")).click();
		 driver.switchTo().frame("contentframe");
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Then("^enter description$")
	public void enter_description() throws Throwable {
	}

	@Then("^enter story name$")
	public void enter_story_name() throws Throwable {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("_Text_Check_CM_Name")).sendKeys("Team");
	   
	}

	@Then("^enter due date$")
	public void enter_due_date() throws Throwable {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("CM_DUEDATE")).sendKeys("12-Aug-2021");
	    
	}

	@Then("^select card owner name$")
	public void select_card_owner_name() throws Throwable {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement ele=driver.findElement(By.id("AG_CARDOWNER"));
		Select cardOwnerName=new Select(ele);
		cardOwnerName.selectByVisibleText("Shreya Dehankar (918996)");
	    
	}
	
	@Then("^select user story type$")
	public void select_user_story_type() throws Throwable {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement ele=driver.findElement(By.id("DN_UserStoryType"));
		Select cardOwnerName=new Select(ele);
		cardOwnerName.selectByVisibleText("Test Automation");

	}
	
	@Then("^enter story points$")
	public void enter_story_points() throws Throwable {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("DN_StoryPoints")).sendKeys("150"); 
	    
	}
	
	@Then("^select resource group$")
	public void select_resource_group() throws Throwable {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement ele=driver.findElement(By.id("DN_ResourceGroup"));
		Select cardOwnerName=new Select(ele);
		cardOwnerName.selectByVisibleText("Test");

	}

	@Then("^select theme$")
	public void select_theme() throws Throwable {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement ele=driver.findElement(By.id("THEME"));
		Select cardOwnerName=new Select(ele);
		cardOwnerName.selectByVisibleText("main project");
 
	}
	
	@Then("^enter rank$")
	public void enter_rank() throws Throwable {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("AG_RANK")).sendKeys("1"); 
	    
	}
	
	@Then("^select priority$")
	public void select_priority() throws Throwable {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement ele=driver.findElement(By.id("CM_Priority"));
		Select cardOwnerName=new Select(ele);
		cardOwnerName.selectByVisibleText("High");  
		
	}
	
	@Then("^save user story$")
	public void save_user_story() throws Throwable {
		driver.findElement(By.id("SaveBtn")).click();
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		Thread.sleep(7000);
		String id= driver.findElement(By.id("CM_ItemCode")).getText();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		System.out.println(id);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@After
	public void closingBrowser() {
		closeBrowser();
	}
}
