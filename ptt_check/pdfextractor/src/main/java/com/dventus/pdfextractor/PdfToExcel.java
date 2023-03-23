package com.dventus.pdfextractor;
import java.io.File;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import com.spire.xls.*;

import com.groupdocs.conversion.Converter;
import com.groupdocs.conversion.options.convert.SpreadsheetConvertOptions;
import com.groupdocs.parser.Parser;
import com.groupdocs.parser.options.IDocumentInfo;


public class PdfToExcel {
	
	int fileCount = 0;
	public void groupsDocPdf(File path, String name, String dirName) throws FileNotFoundException {
		Converter converter = new Converter(new FileInputStream(path));
		SpreadsheetConvertOptions options = new SpreadsheetConvertOptions();
		name = name.substring(0, name.length() - 4);
		options.setPageNumber(2);
		options.setPagesCount(1);
		converter.convert("output/"+dirName+"/"+name+".xlsx", options);
	}
	
	public void movingFiles(String location, String name) throws IOException {
		Path path = Paths.get("./processed");
		if (!Files.exists(path)){
			File theDir = new File("./processed");
			theDir.mkdirs();
		}
		Path duplicate = Paths.get("./processed/" + name);
		if (Files.exists(duplicate)) {
			File dup = new File("./duplicated");
			dup.mkdirs();
			System.out.println(name + " : This file is already processed !!!!!!!!!!");
			Path temp = Files.move
					(Paths.get("./processed"),
							Paths.get("./duplicated" + "/" + name));
			if(temp != null) {
				System.out.println("File moved successfully");
			}
			else {
				System.out.println("Failed to move the file");
			}
		}
		else {
			Path temp = Files.move
					(Paths.get(location),
							Paths.get(path + "/" + name));
			if(temp != null) {
				System.out.println("File moved successfully");
			}
			else {
				System.out.println("Failed to move the file");
			}
		}
	}
	
	public void pdfpagebypage(String path,  String name, String dirName) throws IOException {
		try (Parser parser = new Parser(path)) {
			IDocumentInfo info = parser.getDocumentInfo();
			int pages = info.getPageCount();

			//SpreadsheetConvertOptions options = new SpreadsheetConvertOptions();
			name = name.substring(0, name.length() - 4);
			File pathAsFile = new File("output/"+dirName+"/"+name);
			File pdfFile = new File(path);
			if (!pathAsFile.isDirectory()) {
				pathAsFile.mkdir();
			}
			this.mergeFiles("output/" + dirName + "/" + name+"/", pdfFile);
		}
		movingFiles(path, name);
		}

	public void mergeFiles(String path, File pdfFile) {
		//System.out.println(path);
		String[] pathnames;
        File f = new File(path);
        pathnames = f.list();
        Arrays.sort(pathnames);
        Workbook newBook = new Workbook();
        newBook.getWorksheets().clear();
        Workbook tempBook = new Workbook();

        for (String file : pathnames)
        {
            tempBook.loadFromFile(path+file);

        }

        newBook.saveToFile(path+"MergedFile.xlsx", ExcelVersion.Version2013);
        InsertMain.insertData(path+"MergedFile.xlsx", pdfFile);
	}	
	public static void main(String[] args) throws Exception {
		PdfToExcel main = new PdfToExcel();
		String mainDir = System.getProperty("user.dir");
		mainDir = args[0];
		File file = new File(mainDir);
		Stack<File> s = new Stack<>();
		String dirName = "";
		s.push(file);
		//System.out.println("Content of Directory " + mainDir + " is");
		while (!s.empty()) {
			File tmpF = s.pop();
			if (tmpF.isFile()) {
				//main.groupsDocPdf(tmpF, tmpF.getName(), dirName);
				System.out.println(tmpF + "/" + tmpF.getName());
				main.pdfpagebypage(String.valueOf(tmpF), tmpF.getName(), dirName);
				main.fileCount++;
			}
			else if (tmpF.isDirectory()) {
				File[] f = tmpF.listFiles();
				dirName = String.valueOf(tmpF.getName());
				File pathAsFile = new File("output/"+dirName);

				pathAsFile.mkdir();
				for (File fpp : f) {
					s.push(fpp);
				}
			} 
		} 

	}
}
