package textprocessing;


import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;

import textprocessing.In;
import textprocessing.TST;

public class Q2 {
static void KMP(String pattern, String text) {
		
		KMP kmp = new KMP(pattern);
		System.out.println();
		System.out.println("Finding " + pattern + " in the text using KMP");
		int index = 0;
		int offset = 0;
		int newOffset=0;
		int count=0;
		
		String newText = text;
		
		System.out.println("Found at pos");
		
		while(index < newText.length()) {

			newText = newText.substring(index);
			if(offset!=0)
			{
				System.out.print(offset+" ");
				count=count+1;
				
			}
			newOffset = kmp.search(newText);
			if(newOffset==0)
			{
				System.out.print("pattern not found");
			}
			if(index == 0)
				offset = offset + newOffset;
			else
				offset = offset + newOffset + pattern.length();
			
			
		//	System.out.print(offset+",");
			index = newOffset + pattern.length();
			
		}
		System.out.println();
		System.out.println("occurence: "+count+" times");
		System.out.println();
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		File in = new File("C:\\Users\\Akshay Jagadish\\eclipse-workspace\\Text Processing\\textprocessing\\Protein.txt");
		
		org.jsoup.nodes.Document doc = Jsoup.parse(in, "utf-8");
		//org.jsoup.nodes.Element body = doc.body();
		String text = doc.body().text(); // doc.toString();// {};
		
		String[] splitstring = text.split("[\u00A0 \n]");
		
		TST<Integer> st = new TST<Integer>();
        
        for (int i = 0; i < splitstring.length; i++) {
            st.put(splitstring[i], i);
        }
        String[] keys = { "protein", "complex", "PPI", "prediction", "interactions", "physicochemical", "hydrophobic", "yeast" };
        for (String key : keys) {
        		System.out.println(key + " IndexNo: " + st.get(key));
        		KMP(key, text); 
        		System.out.println("-------------------------------------------");
        }
        
       
	}

}
