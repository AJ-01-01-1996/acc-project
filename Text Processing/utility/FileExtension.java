package utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileExtension {
	
	public static String FilePath(String name){
		
		File f = new File(name);
		String absPath = f.getAbsolutePath();
		return absPath;
	 }
	
	public static String WebFileFolder()
	{
		final String webFileFolder = "W3C Web Pages";
		return webFileFolder;
	}
	
	public static String TextFileFolder()
	{
		final String convertedTextFolder = "HTML_TextFiles";
		return convertedTextFolder;
	}
	
	public static String TextFileName(File filename){
		String name = filename.getName();
		String[] temp = name.split("htm");
		return temp[0] + "txt";
	}
	
	public static void WriteToFile(String string, String filename, String foldername) throws IOException{
		String  newFileName = FilePath(foldername) + File.separator + filename;
	    BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName));
	    writer.write(string);
	    writer.close();
	}
}
