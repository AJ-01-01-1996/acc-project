package AssignmentSection2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import htmlparser.HTMLtoText;
import utility.FileExtension;

public class Question4 {
	
	private static void SearchForPhoneNumber() throws IOException{
		 
		 String pattern = "(\\()?(\\d){3}(\\))?[- ](\\d){3}-(\\d){4}";
		  
	     Pattern r = Pattern.compile(pattern);
	     
		 HTMLtoText parser = new HTMLtoText();
		 String textHTML="";
		 String t = "";
		 File file = new File(FileExtension.FilePath(FileExtension.TextFileFolder()));
		  File[] fileList = file.listFiles();
		  System.out.println("The Phone numbers found:");
		  if (fileList != null) {
			  int counter = 1;
			    for (File child : fileList) {
			    		FileReader in = new FileReader(child);
			    		parser.parse(in);
				    in.close();
				    textHTML = parser.getText();
				    Matcher m = r.matcher(textHTML);
				    while (m.find( )) {
				    		t +="\t "+counter+++".) Found phone number: " + m.group(0)+"\n\t\t File name: "+child.getName()+"\n";
				    } 
			    } 
		    FileExtension.WriteToFile(t, "foundPhoneNumbers.txt", "Question4");
		    System.out.println("Found Phone numbers were written to " + FileExtension.FilePath("Question4" + File.separator + "foundPhoneNumbers.txt"));
		  } else {
		    System.out.println("Empty directory.");
		  }
	 }
	
	static void SearchForEmailAddresses() throws IOException{
		 
		 String pattern = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,4}\\b";
		  
	      Pattern r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
	      	      
	      
		 HTMLtoText parser = new HTMLtoText();
		 String textHTML="";
		 String t="";
		 File file = new File(FileExtension.FilePath(FileExtension.TextFileFolder()));
		  File[] fileList = file.listFiles();
		  System.out.println("\nThe Email addresses found:");
		  if (fileList != null) {
			  int counter = 1;
			    for (File child : fileList) {
			    	FileReader in = new FileReader(child);
				     parser.parse(in);
				     in.close();
				    textHTML = parser.getText();
				      Matcher m = r.matcher(textHTML);
				      while (m.find( )) {
				          t += "\t "+counter+++".) Found email: " + m.group(0)+"\n \t\tFile name: "+child.getName()+"\n";
				      }
			    } 
			    
			    FileExtension.WriteToFile(t, "foundEmailAddresses.txt", "Question4");
			    System.out.println("Found email addresses were written to " + FileExtension.FilePath("Question4" + File.separator +"foundEmailAddresses.txt"));
			    
		  } else {
		    System.out.println("Empty  directory.");
		  }
	 }

	 public static void main(String[] args)  throws IOException{
		// TODO Auto-generated method stub
		 SearchForPhoneNumber();
		 SearchForEmailAddresses();
	 }

}
