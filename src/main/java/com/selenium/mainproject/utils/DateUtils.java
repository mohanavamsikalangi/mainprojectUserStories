package com.selenium.mainproject.utils;
/*
 * This class is used get date and convert to string
 */
import java.util.Date;

public class DateUtils {

	public static String getTimeStamp() {
		Date date = new Date();
		
		return date.toString().replaceAll(":", "-").replaceAll(" ", "-");
		
	}
}
