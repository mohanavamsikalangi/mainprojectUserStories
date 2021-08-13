package com.selenium.mainproject.utils;

import java.util.Hashtable;

public class TestDataProvider {
	
	
	

	public static Object[][] getTestData(String dataFileName, String sheetName, String testName) {
		
		ReadExcelDataFile readdata = new ReadExcelDataFile(
				System.getProperty("user.dir") + "//TestData//" + dataFileName);
		String sheetName1 = sheetName;
		String testName1 = testName;

		// Find Start Row of TestCase
		int startRowNum = 0;
		while (!readdata.getCellData(sheetName1, 0, startRowNum).equalsIgnoreCase(testName1)) {
			startRowNum++;
		}
		
		int startTestColumn = startRowNum + 1;
		int startTestRow = startRowNum + 2;

		// Find Number of Rows of TestCase
		int rows = 0;
		while (!readdata.getCellData(sheetName1, 0, startTestRow + rows).equals("")) {
			rows++;
		}
		
		// Find Number of Columns in Test
		int colmns = 0;
		while (!readdata.getCellData(sheetName1, colmns, startTestColumn).equals("")) {
			colmns++;
		}
		
		//Define Two Object Array
		Object[][] dataSet = new Object[rows][1];
		Hashtable<String, String> dataTable = null;
		int dataRowNumber=0;
		for (int rowNumber = startTestRow; rowNumber <= startTestColumn + rows; rowNumber++) {
			dataTable = new Hashtable<String, String>();
			for (int colNumber = 0; colNumber < colmns; colNumber++) {
				String key = readdata.getCellData(sheetName1, colNumber, startTestColumn);
				String value = readdata.getCellData(sheetName1, colNumber, rowNumber);
				dataTable.put(key, value);
				//dataSet[dataRowNumber][colNumber]=readdata.getCellData(sheetName, colNumber, rowNumber);
				//00,01,02,03
				//10,11,12
			}
			dataSet[dataRowNumber][0]=dataTable;
			dataRowNumber++;
		}
	 return dataSet;

	}

}
