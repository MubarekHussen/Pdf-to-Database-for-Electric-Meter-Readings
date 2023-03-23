package com.dventus.pdfextractor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {
	private List<List<String>> data = new ArrayList<>();
	private Workbook workbook;
	private List<String> availablePoints;
	private List<Integer> indexes;
	private String globalpath;
	
	private String[] testPoints = {"Switch off + Evaluation", "Sample Based Tests 1", "Meter Calibration SM", "No-Load Test", 
			"Input Tester Name & Meter ID", "Sample Based Tests 2","Identifications", "SC adjustment", "Export Test Resuls", 
			"Input tester-Name and Meter ID","Impedance Test", "SC Adjustment", "No load Test", "Noload test", "High Pulse Rate", 
			"High Current Burning Test", "Meter Calibration", "Reset Tampers 1", "Variation Tests Variables Initializer", "Variation Last Result",
			"Test Result", "Operating current Test", "Preparation", "ActiveEnergy Register Test", "Active Energy Register Test", "Variation Tests Variables Initializer", "Variation Tests",
			"Starting Test", "Operating Current", "Starting I test", "Starting Current Test Full", "5 A (Ib) @ PF 1", "5 A (Ib) @ PF 0.5", "5A(Ib) @ PF 0.5", "5A (Ib) @ PF 1",
			"10 A (Ib) @ PF 0.5", "10 A (Ib) @ PF 1", "10A(Ib) @ PF 0.5", "10A (Ib) @ PF 1", "Accuracy Test Active Load", "Accuracy Test Inductive Load", "Accuracy Test Capacitive Load"};
	public List<String> getTestPoints() {
		return (Arrays.asList(this.testPoints));
	}

	public List<Integer> getStartAndEndPages(List<Integer> numRows, int start, int end) {
		int startPage = 0;
		int endPage = 0;
		boolean isStartFound = false;
		for (int i = 0; i < numRows.size(); i++) {
			if (numRows.get(i) > start && isStartFound == false) {
				startPage = i;
				isStartFound = true;
			}
		}
		List<Integer> values = new ArrayList<>();
		values.add(startPage);
		values.add(endPage);
//		System.out.print(values.toString() + " ");
		return values;
	}
	
	public void getData(int start, int end, int startPage, List<Integer> rowsInSheets, String testPoint) {
		int count = startPage;
		Sheet sheet1 = workbook.getSheetAt(count);
		int numSheets = workbook.getNumberOfSheets();
		int j = start;
		List<String> temp = new ArrayList<>();
		for (int i = start; i < end; i++) {
			if (i < rowsInSheets.get(count)) {
				temp.add(testPoint);
				Row row = sheet1.getRow(j);
				if (row != null) {
					for (Cell c : row) {
						if(String.valueOf(c).strip().equals("5") || String.valueOf(c).strip().equals("10")) {
							continue;
						}
						else if (!String.valueOf(c).equals("") && c != null) {
							temp.add(String.valueOf(c));
						}
						else if (String.valueOf(c).equals("") && !((String.valueOf(row.getCell(1)).equals("")) || (String.valueOf(row.getCell(0)).equals("")))) {
							temp.add("1001");
						}
					}
				} else {				
					int init = j - rowsInSheets.get(count - 1);
					ReadNullData(init, temp, globalpath, count);
				}
				j++;
			} else {
				if (count < numSheets) {
					count++;
					sheet1 = workbook.getSheetAt(count);
					i--;
					j = 0;
				}
			}
			if (temp.size() != 0) {
				this.data.add(temp);
			}
			temp = new ArrayList<>();
		}
	}

	public void appendSerialNumber() {
		for (int i = 0; i < this.data.size() - 1; i++) {
			List<String> first = this.data.get(i);
			List<String> second = this.data.get(i + 1);
			if (!second.get(0).equalsIgnoreCase("Identifications")) {
				break;
			}
	//		System.out.println(second);
			char ch = second.get(1).charAt(0);
			if (first.get(1).equals("Serial #") && ch >= '0' && ch <= '9') {
				for (int j = 1; j < second.size(); j++) {
					first.set(j + 1, first.get(j + 1) + second.get(j));
				}
				this.data.remove(i + 1);
			}
		}
	}

	public void fixMeterPosition() {
		int i = 0;
		while (i < this.data.size()) {
			List<String> li = this.data.get(i);
			if (li.get(li.size() - 1).contains("MP")) {
				if (li.get(1).contains("MP")) {
					li.add(1, "Position");
				} else {
					li.set(1, "Position");
				}
				this.data.set(i, li);
			}
			i++;
		}
	}

	public List<String> getHeaderData() {
		List<String> header = new ArrayList<String>();
		for (int j = 0; j < 10; j++) {
			Row headerRow = workbook.getSheetAt(0).getRow(j);
			for (Cell c : headerRow) {
				if (!String.valueOf(c).equals("") && c != null) {
					header.add(String.valueOf(c));
				}
			}	
//			for (int l = 0; l < header.size(); l++) {
//				System.out.println(header.get(l) + "...." + l);
//			}
		}
//			System.out.println(header.size());
//			for (int l = 0; l < header.size(); l++) {
//				System.out.println(header.get(l) + "...." + l);
//			}
		return header;
	}
	
	public void ReadNullData(int vRow, List<String> temp, String path, int count) {
//		vRow = 25;
		int rowForSheet = this.workbook.getSheetAt(count).getLastRowNum();
		Workbook wb = null;           //initialize Workbook null  
		try {  
			//reading data from a file in the form of bytes  
			FileInputStream fis = new FileInputStream(path);  
			//constructs an XSSFWorkbook object, by buffering the whole stream into the memory  
			wb = new XSSFWorkbook(fis);  
		}  
		catch(FileNotFoundException e) {  
			e.printStackTrace();  
		}  
		catch(IOException e1) { 
			e1.printStackTrace();  
		}
		Sheet sheet = wb.getSheetAt(count);   //getting the XSSFSheet object at given index 
//		System.out.println(vRow);
		Row row = sheet.getRow(vRow); //returns the logical row
		if (row != null) {
			for (Cell c : row) {
//				System.out.println(String.valueOf(c));
				if(String.valueOf(c).strip().equals("5") || String.valueOf(c).strip().equals("10")) {
					continue;
				}
				else if (!String.valueOf(c).equals("") && c != null) {
					temp.add(String.valueOf(c));
				}
				else if (String.valueOf(c).equals("") && !((String.valueOf(row.getCell(1)).equals("")) || (String.valueOf(row.getCell(0)).equals("")))) {
					temp.add("1001");
				}
			}
		}

	}
	
	public List<List<String>> getOutputData(String path) {
		this.globalpath = path;
		//ReadExcelFile readExcelFile = new ReadExcelFile();
		List<String> testPointsList = Arrays.asList(this.testPoints);
		this.indexes = new ArrayList<>();
		this.availablePoints = new ArrayList<>();

		String mainDir = System.getProperty("user.dir");
//		String path = mainDir + "/output/EEU India Meters/2021-12-15 15-42-31__359/MergedFile.xlsx";
		// String path = mainDir + "/output/EEU China meters/2021-11-09
		// 14-59-20__59/MergedFile.xlsx";
		// String path = mainDir + "/output/DM 801 Calibration/2022-04-05
		// 12-00-37__2146/MergedFile.xlsx";
		// String path = mainDir + "/output/DM 801 Dosage and accuracy test/2022-04-06
		// 14-44-54__2176/MergedFile.xlsx";
		try {
			FileInputStream fis = new FileInputStream(path);
			this.workbook = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		int numSheets = this.workbook.getNumberOfSheets();
//		 System.out.println(numSheets);
		Sheet firstSheet = this.workbook.getSheetAt(0);

		Row row = firstSheet.getRow(6);
		String[] meterPositions = String.valueOf(row.getCell(1)).split(",");

		int addRows = 0;
		// int sheetsSize = readExcelFile.workbook.getNumberOfSheets();
		List<Integer> rowsInSheets = new ArrayList<>();
		for (int i = 0; i < numSheets - 1; i++) {
			int rows = this.workbook.getSheetAt(i).getLastRowNum();
			for (int j = 0; j <= rows; j++) {
				Row cellRow = this.workbook.getSheetAt(i).getRow(j);
				String str = cellRow.getCell(0).toString();
				String subStr = "";
				if (cellRow.getCell(1) != null) {
					 subStr = cellRow.getCell(0).toString() + cellRow.getCell(1).toString();
				}
				if (str.contains(":")) {
					try {
						str = str.split(":")[1];
					} catch (Exception e) {
						str = str.split(":")[0];
					}
				}
				boolean isInTestPoints = testPointsList.contains(str);
				boolean subIsInTestPoints = testPointsList.contains(subStr);
//				System.out.println(subStr);
				if (isInTestPoints){
					this.availablePoints.add(str);
					this.indexes.add(j + addRows);
				}
			}
			addRows += rows;
			rowsInSheets.add(addRows);
		}
//		System.out.println("Rows In Sheets");
//		for (Integer i : rowsInSheets) {
//			System.out.print(i + " ");
//		}
//		System.out.println("Indexes");
		for (Integer i : this.indexes) {
			System.out.print(i + " ");
		}
//		System.out.println("Available Points");
//		for (String i : this.availablePoints) {
//			System.out.print(i + " ");
//		}
//		System.out.println("");
		int lastIndex = addRows;
		this.indexes.add(lastIndex);
		for (int i = 0; i < this.indexes.size() - 1; i++) {
			int start = this.indexes.get(i);
			this.getData(start, end, startPage, rowsInSheets, this.availablePoints.get(i));
		}
		for (List<String> li : this.data) {
			System.out.println(li.toString());
		}

		this.appendSerialNumber();
		this.fixMeterPosition();
		this.data.add(this.getHeaderData());

		
		return (this.data);
	}
}
