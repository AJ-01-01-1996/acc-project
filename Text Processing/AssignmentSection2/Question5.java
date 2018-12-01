package AssignmentSection2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import htmlparser.HTMLtoText;
import utility.FileExtension;

public class Question5 {
	
	private static void LinksWithW3org() throws IOException{
		
		//pattern for finding w3.org
		String pattern = "(w3.org)";
		
	    Pattern r = Pattern.compile(pattern);
		HTMLtoText parser = new HTMLtoText();
		String textHTML="";
		String t = "";
		File dir = new File(FileExtension.FilePath(FileExtension.WebFileFolder()));
		File[] fileList = dir.listFiles();
		System.out.println("List of domain with \"w3.org\":");
		if (fileList != null) {
			
			int counterId = 1;
			for (File child : fileList) {
		    		FileReader in = new FileReader(child);
			    parser.parse(in);
			    in.close();
			    textHTML = parser.getText();
			    Matcher m = r.matcher(textHTML);
			    while (m.find( )) {
			    		t += "\t "+counterId+++". domain: " + m.group(0)+"\n\t\t File name: " + child.getName() +"\n";
			    } 
				    
		    } 
		    FileExtension.WriteToFile(t, "question5a.txt", "Question5");
		    System.out.println("The output was written to File: " + FileExtension.FilePath("Question5" + File.separator + "question5a.txt")+"\n");
		  } else {
			  System.out.println("Directory is empty.");
		  }
      
	 }
	 
	 
	 static void LinksWithFolder() throws IOException{
	
		 String pattern = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|](/){1}[-a-zA-Z0-9+&@#/%?=~_|!:,.;]";
		 
	      Pattern r = Pattern.compile(pattern);
	      	      
		 HTMLtoText parser = new HTMLtoText();
		 String textHTML="";
		 String t="";
		 File dir = new File(FileExtension.FilePath(FileExtension.WebFileFolder()));
		  File[] fileList = dir.listFiles();
		  System.out.println("List of links that contain folders:");
		  if (fileList != null) {
			  int counter = 1;
			    for (File child : fileList) {
			    		FileReader in = new FileReader(child);
				    parser.parse(in);
				    in.close();
				    textHTML = parser.getText();
				    Matcher m = r.matcher(textHTML);
				    while (m.find()) {
				    		t +="\t "+counter+++". Link: " + m.group(0) +"\n\t\t File name: "+ child.getName() + "\n";
				    }
			    } 
			    FileExtension.WriteToFile(t, "question5b.txt", "Question5");
			    System.out.println("The output was written to File: " + FileExtension.FilePath("Question5" + File.separator + "question5b.txt")+"\n");
		  } else {
			  System.out.println("Empty directory.");
		  }
      
	 }
	 
	 private static void LinksWithReferences() throws IOException{
			
		 String pattern = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|](/){1}[#]{1}[-a-zA-Z0-9+&@#/%?=~_|!:,.;]+";
		  
	      Pattern r = Pattern.compile(pattern);
	      	      
		 HTMLtoText parser = new HTMLtoText();
		 String textHTML="";
		 String t ="";
		 File dir = new File(FileExtension.FilePath(FileExtension.WebFileFolder()));
		 File[] fileList = dir.listFiles();
		 System.out.println("Links that contain references:");
		 if (fileList != null) {
			 int counter = 1;
			 for (File child : fileList) {
				 FileReader in = new FileReader(child);
			     parser.parse(in);
			     in.close();
			     textHTML = parser.getText();
			     Matcher m = r.matcher(textHTML);
			     while (m.find()) {
			    	 	t += "\t "+counter+++".) Link: " + m.group(0)+"\n\t\t File name: "+child.getName()+"\n";
			     } 
		    } 
			FileExtension.WriteToFile(t, "question5c.txt", "Question5");
		    System.out.println("The output was written to File: " + FileExtension.FilePath("Question5"+ File.separator + "question5c.txt")+"\n");
		  } else {
			  System.out.println("Directory is empty.");
		  }
      
	 }
	 
	 
	 private static void LinksWithDomain() throws IOException{
		 
		 String pattern = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*(org|com|net){1}";
		  
	     Pattern r = Pattern.compile(pattern);
	      
	     String t="";    
		 HTMLtoText parser = new HTMLtoText();
		 String textHTML="";
		 File dir = new File(FileExtension.FilePath(FileExtension.WebFileFolder()));
		 File[] fileList = dir.listFiles();
		 System.out.println("Domain with .net, .com, .org:");
		 if (fileList != null) {
			 int counter = 1;
			 for (File child : fileList) {
				 FileReader in = new FileReader(child);
			     parser.parse(in);
			     in.close();
			     textHTML = parser.getText();
			     Matcher m = r.matcher(textHTML);
			     while (m.find()) {
			    	 	t += "\t "+counter+++".) domain: " + m.group(0)+"\n\t\t File name: "+child.getName()+"\n";
			     } 
		    }
		    FileExtension.WriteToFile(t, "question5d.txt", "Question5");
		    System.out.println("The output was written to File: " + FileExtension.FilePath("Question5" + File.separator + "question5d.txt")+"\n");
		  } else {
		    System.out.println("empty directory.");
		  }
      
	 }
	 
	 
	 public static void main(String[] args) throws IOException{
		//Question 5a
		LinksWithW3org();
		 
		//Question 5b
		LinksWithFolder();
		
		//Question 5c
		LinksWithReferences();

		//Question 5d
		LinksWithDomain();
	 }

}
