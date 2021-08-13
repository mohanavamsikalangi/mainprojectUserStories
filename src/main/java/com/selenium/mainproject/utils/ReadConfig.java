package com.selenium.mainproject.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	Properties prop = new Properties();

	public ReadConfig() {
		FileInputStream fis = null;// object creation for reading file
		File src = new File(
				System.getProperty("user.dir") + "//TestData//config.properties");
		try {
			// path of config file in the project
			fis = new FileInputStream(src);
			try {
				prop.load(fis);// loads the file into properties Object
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}

	public String getBrowserName() {
		// this method is used to get the browser name from the properties file
		String browser = prop.getProperty("browser");
		return browser;
	}
	
	public String getUrlFromConfig() {
		// this method is used to get the Url of the website from the properties file
		String Url = prop.getProperty("URL");
		return Url;
	}
}
