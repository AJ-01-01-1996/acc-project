package AssignmentSection2;

//package AssignmentSection2;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import htmlparser.HTMLtoText;
import utility.FileExtension;

public class Question3 {
	
	private static void writeTotxt(File filename){

		String txtname = FileExtension.FilePath(FileExtension.TextFileFolder()) + File.separator + FileExtension.TextFileName(filename);
		
		try {
		  FileReader in = new FileReader(filename);
		  HTMLtoText parser = new HTMLtoText();
		  parser.parse(in);
		  in.close();
		  String textHTML = parser.getText();	
		  BufferedWriter writerTxt = new BufferedWriter(new FileWriter( txtname));
		  writerTxt.write(textHTML);
		  writerTxt.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File(FileExtension.FilePath(FileExtension.WebFileFolder()));
		 
		File[] fileList = file.listFiles();
	  
		if (fileList != null) {
			System.out.println("Converting the W3C Web Page files from HTML to TEXT FILE........");
		    for (File specificFile : fileList) {
		    	System.out.println("Converted "+specificFile+" to .text");
		    		writeTotxt(specificFile);
		    		
		    }
		    System.out.println("Conversion completed: Refresh the project folder to see the newly created files in HTML_TextFiles folder");
		} else {
			System.out.println("The Web file directory is empty");
		}
	}

}
