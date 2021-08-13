package com.selenium.mainproject.utils;

import java.io.FileWriter;

public class CustomReport {
	
	/*
	 * Create the html file
	 */
	public static FileWriter htmlreportWriter() throws Exception {
		String filePath = System.getProperty("user.dir")+"\\CustomReport\\"+DateUtils.getTimeStamp()+".html";
		FileWriter file = new FileWriter(filePath,true);
		return file;
	}
	
	/*
	 * Intitiate the report
	 */
	public static void intiateHtmlFile(FileWriter file,String ModuleName) throws Exception {
		file.write("<html>" + "\n");
		file.write("<head><title>" + " USER STORIES TEST EXECUTION REPORT" + "</title>" + "\n");
		file.write("</head>" + "\n");
		file.write("<body>");
		file.write("<font face='Tahoma'size='2'>" + "\n");
		file.write("<u><h1 align='center'>" + "TEST EXECUTION REPORT" + "</h1></u>" + "\n");
		file.write("<table align='center' Border='1' CellPadding='1' cellspacing='1' width='50%'>" + "\n");
		file.write("<tr>");
		file.write("<td bgcolor='#5DADE2' align=right width='50%'><b><font color=white>" + "MODULE NAME" + "</b></td>");
		file.write("<td align=left width='50%'>" + "USER STORIES" + "</td>");
		file.write("</tr>");
		file.write("<tr>");
		file.write("<td bgcolor='#5DADE2' align=right width='50%'><b><font color=white>" + "TEAM NAME" + "</b></td>");
		file.write("<td align=left width='50%'>" + "TEAM_03_SD006" + "</td>");
		file.write("</tr>");
		file.write("<tr>");
		file.write("<td bgcolor='#5DADE2' align=right width='50%'><b><font color=white>" + "START EXECUTION TIME" + "</b></td>");
		file.write("<td align=left width='50%'>" + DateUtils.getTimeStamp() + "</td>");
		file.write("</tr>");
		file.write("<tr>");
		file.write("<td bgcolor='#5DADE2' align=right width='50%'><b><font color=white>" + "SCRIPT NAME" + "</b></td>");
		file.write("<td align=left width='50%'>" + "USER STORY CREATION WITH VALID DETAILS" + "</td>");
		file.write("</tr>");
		file.write("</table>");
		file.write("<table width='80%' align='center' cellPadding='1' cellspacing='1' Border='2' >" + "\n");
		file.write("<tr>");
		file.write("<th bgcolor='#17202A' align=center width='20%'><b><font color=white size='3'>" + "S.No" + "</b></th>");
		file.write("<th bgcolor='#17202A' align=center width='20%'><b><font color=white size='3'>" + "TEST CASE DESCRIPTION" + "</b></th>");
		file.write("<th bgcolor='#17202A' align=center width='20%'><b><font color=white size='3'>" + "START TIME" + "</b></th>");
		file.write("<th bgcolor='#17202A' align=center width='20%'><b><font color=white size='3'>" + "STATUS" + "</b></th>");
		
		file.write("</tr>");
	}
	/*
	 * If test passes report it as pass 
	 */
	public static void reportTestPass(FileWriter file,int index,String testName) throws Exception {
		file.write("<tr>" + "\n");
		file.write("<th bgcolor='#2ECC71' width='20%'><Font color=white size='2'>" + index + "</Font></th>" + "\n");
		file.write("<th bgcolor='#2ECC71' width='20%'><Font color=white size='2'>" + testName + "</Font></th>" + "\n");
		file.write("<th bgcolor='#2ECC71' width='20%'><Font color=white size='2'>" +DateUtils.getTimeStamp() + "</Font></th>" + "\n");
		file.write("<th bgcolor='#2ECC71' width='20%'><Font color=white size='2'>" + "PASS" + "</Font></th>" + "\n");
		
		file.write("</tr>" + "\n");
	}
	/*
	 * If test fails report it as failed
	 */
	public static void reportTestFail(FileWriter file,int index,String testName) throws Exception {
		file.write("<tr>" + "\n");
		file.write("<th bgcolor='#E74C3C' width='20%'><Font color=white size='2'>" + index + "</Font></th>" + "\n");
		file.write("<th bgcolor='#E74C3C' width='20%'><Font color=white size='2'>" + testName + "</Font></th>" + "\n");
		file.write("<th bgcolor='#E74C3C' width='20%'><Font color=white size='2'>" +DateUtils.getTimeStamp() + "</Font></th>" + "\n");
		file.write("<th bgcolor='#E74C3C' width='20%'><Font color=white size='2'>" + "FAIL" + "</Font></th>" + "\n");
		
		file.write("</tr>" + "\n");
	}
	/*
	 * end the html report
	 */
	public static void endHtmlReport(FileWriter file, int index) throws Exception {
		file.write("</table>");
		file.write("<table align='center' Border='1' CellPadding='1' cellspacing='1' width='50%'>" + "\n");
		file.write("<tr>");
		file.write("<td bgcolor='#5DADE2' align=right width='50%'><b><font color=white>" + "TOTAL NO OF STEPS PASSED" + "</b></td>");
		file.write("<td align=left width='50%'>" + index + "</td>");
		file.write("</tr>");
		file.write("<tr>");
		file.write("<td bgcolor='#5DADE2' align=right width='50%'><b><font color=white>" + "SCREENSHOT" + "</b></td>");
		file.write("<td align=left width='50%'><a href=C:\\Users\\himas\\eclipse-workspace\\Cts-Main-Projrct\\ScreenShots  target=_Blank>" + "Click Here" + "</td>");
		file.write("</tr>");
		file.write("<tr>");
		file.write("<td bgcolor='#5DADE2' align=right width='50%'><b><font color=white>" + "END EXECUTION TIME" + "</b></td>");
		file.write("<td align=left width='50%'>" + DateUtils.getTimeStamp() + "</td>");
		file.write("</tr>");
		file.write("</table>");
		file.write("</body>" + "\n");
		file.write("</html>");
		file.close();
	}
	
	


	
}
