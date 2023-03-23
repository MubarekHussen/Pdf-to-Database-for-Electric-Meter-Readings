package com.dventus.pdfextractor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Stack;

import com.groupdocs.conversion.Converter;
import com.groupdocs.conversion.contracts.IDocumentInfo;
import com.groupdocs.conversion.contracts.PdfDocumentInfo;
import com.groupdocs.conversion.filetypes.FileType;
import com.groupdocs.conversion.filetypes.SpreadsheetFileType;
import com.groupdocs.conversion.options.convert.ConvertOptions;
import com.groupdocs.conversion.options.convert.SpreadsheetConvertOptions;


public class Main {


	public void groupsDocPdf(File path, String name, String dirName) throws FileNotFoundException {
		Converter converter = new Converter(new FileInputStream(path));
		SpreadsheetConvertOptions options = new SpreadsheetConvertOptions();
		name = name.substring(0, name.length() - 4);
		converter.convert("output/"+dirName+"/"+name+".xlsx", options);
	}

	public static void main(String[] args) throws Exception {
		Main main = new Main();
		String mainDir = System.getProperty("user.dir");
		mainDir = mainDir +"/src/main/resources/data";
		File file = new File(mainDir);
		Stack<File> s = new Stack<>();
		String dirName = "";
		s.push(file);
		//System.out.println("Content of Directory " + mainDir + " is");
		while (!s.empty()) {

			if (tmpF.isFile()) {
				//System.out.println(tmpF.getName());
				//System.out.println(dirName);
				main.groupsDocPdf(tmpF, tmpF.getName(), dirName);
			}
			else if (tmpF.isDirectory()) {
				File[] f = tmpF.listFiles();
				dirName = String.valueOf(tmpF.getName());
				File pathAsFile = new File("output/"+dirName);

				pathAsFile.mkdir();
			} 
		} 

	}
}
