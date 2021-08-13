package com.selenium.mainproject.baseClass;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.selenium.mainproject.utils.ExtentReportManager;
import com.selenium.mainproject.utils.ReadConfig;

public class BaseClass {
	public static WebDriver driver;// declaration of the web driver public in in order to share the instance of
									// driver
	public static ExtentReports report = ExtentReportManager.getReportInstance();// To create the extent report
	public ExtentTest logger;// To log the statements on to the extent report
	ReadConfig rc = new ReadConfig();// object of the read config class to read values from the properties file

	public static String userDir = System.getProperty("user.dir");

	/****** Invoke Browser *********/

	// This method is used to invoke the browser based on the string value
	public void invokeBrowser(String browserName) {

		try {

			if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", userDir + "\\Drivers\\chromedriver.exe"); // "C:\Users\Anisha\Desktop\Cts-Main-Project\Cts-Main-Project\drivers\chromedriver
																										// (1).exe"
				/*
				 * System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
				 * "//drivers//chromedriver.exe");
				 */
				driver = new ChromeDriver();
				System.out.println("Executing in chrome");

			} else if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.chrome.driver", userDir + "\\Drivers\\geckodriver.exe");
				/*
				 * System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
				 * "//drivers//geckodriver.exe");
				 */
				driver = new FirefoxDriver();
				System.out.println("Executing in firefox");

			} else {
				System.out.println("Select chrome or firefox");
			}
		} catch (Exception e) {

		}

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// this method the website on the required browser
	public void openUrl() {
		driver.get(rc.getUrlFromConfig());
		try {
			Thread.sleep(75000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
	}

	// this method closes browser opened by selenium and flush the extent report
	public void closeBrowser() {
		driver.quit();
		report.flush();

	}

	// To read the browser name using read config class object
	public String setBrowser() {
		return rc.getBrowserName();
	}

	// To implement send keys into the web elements
	public void senKeys(WebElement element, String Text) {
		element.sendKeys(Text);
	}


	public static boolean isFirstrun = true;

	public void takeSS(String filename) throws IOException {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") + "/ScreenShots/" + filename + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "/ScreenShots/" +filename + ".png");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void timeWait() throws InterruptedException {
		Thread.sleep(2000);
	}

}
