package textprocessing;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import org.jsoup.Jsoup;
import textprocessing.In;
import textprocessing.TST;
import textprocessing.BoyerMoore;
import textprocessing.BruteForceMatch;
import textprocessing.In;
import textprocessing.KMP;

//import org.jsoup.Jsoup;

import htmlparser.HTMLtoText;
import utility.FileExtension;  
class projmain {
	static <AnyType extends Comparable<? super AnyType>>
	  void insertionSort( AnyType [ ] a )
	  {
	      int j;

	      for( int p = 1; p < a.length; p++ )
	      {
	          AnyType tmp = a[ p ];
	          for( j = p; j > 0 && tmp.compareTo( a[ j - 1 ] ) < 0; j-- )
	              a[ j ] = a[ j - 1 ];
	          a[ j ] = tmp;
	      }
	  }
	
	static <AnyType extends Comparable<? super AnyType>>
	  void insertionSort( AnyType [ ] a, int left, int right )
	  {
	      for( int p = left + 1; p <= right; p++ )
	      {
	          AnyType tmp = a[ p ];
	          int j;

	          for( j = p; j > left && tmp.compareTo( a[ j - 1 ] ) < 0; j-- )
	              a[ j ] = a[ j - 1 ];
	          a[ j ] = tmp;
	      }
	  }
	static void writeTotxt(File filename){

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
static int BruteForceMatch(String pattern, String text) {
		
		System.out.println("Finding " + pattern + " in the text using BruteForceMatch");
		int index = 0;
		int offset = 0;
		int count=0;
		
		String newText = text;
		System.out.println("Found at pos");
		while(index < newText.length()) {

			newText = newText.substring(index);
			
			int newOffset = BruteForceMatch.search1(pattern, newText);
			if(offset!=0)
			{
				count++;
			System.out.print(offset+",");
			}
			if(index == 0)
				offset = offset + newOffset;
			else
				offset = offset + newOffset + pattern.length();
			
			
			
			
			index = newOffset + pattern.length();
			
		}
		System.out.println("\n");
		System.out.println();
		System.out.println("occurence: "+count+" times");
		System.out.println();
		return count;
	}
static void BoyerMoore(String pattern, String text) {
		
		System.out.println("Finding " + pattern + " in the text using BooyerMoore");
		BoyerMoore boyermoore = new BoyerMoore(pattern);
		int index = 0;
		int offset = 0;
		
		String newText = text;
		System.out.println("Found at pos: ");

		while(index < newText.length()) {

			newText = newText.substring(index);
			
			if(offset!=0)
				System.out.print(offset+",");
			int newOffset = boyermoore.search(newText);
			if(index == 0)
				offset = offset + newOffset;
			else
				offset = offset + newOffset + pattern.length();
			//System.out.print(offset+",");
			
			index = newOffset + pattern.length();
			
		}
		System.out.println("\n");
	}
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
		
		while(index < newText.length() ) {  ///newText.length()

			newText = newText.substring(index);
			if(offset!=0)
			{
				System.out.print(offset+" ");
				count=count+1;
				
			}
			newOffset = kmp.search(newText);
			if(newOffset==0)
			{
				//System.out.print("pattern not found");
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
	public static void main(String args[]) throws IOException
	{

		String keyword;
		Integer[] occurence=new Integer[101];
		Integer[] occurence2=new Integer[101];
		System.out.println("------------------------");
		System.out.println("What do you want to search?");
		Scanner sc=new Scanner(System.in);
		//System.out.println("Did you mean"+ +"?");;
		keyword=sc.next();
		System.out.println("Results for "+ keyword);
				
    	File file2 = new File(FileExtension.FilePath(FileExtension.TextFileFolder()));
		 
		File[] fileList2 = file2.listFiles();
		//TST<Integer> st = new TST<Integer>();
		for(int i=0;i<101;i++)
		{
		org.jsoup.nodes.Document doc = Jsoup.parse(fileList2[i], "utf-8");
		//org.jsoup.nodes.Element body = doc.body();
		String text = doc.body().text(); // doc.toString();// {};      
		String key=keyword;
       
		occurence[i]=BruteForceMatch(key, text);
		
		System.out.println(fileList2[i]);
        		System.out.println("-------------------------------------------");
        		
		}
		System.out.println("Storing the occurences in an array...........");

		for(int j=0;j<101;j++)
		{
			//System.out.println();
			System.out.print(" "+occurence[j]);
			occurence2[j]=occurence[j];
		}
		//Sort s=new Sort();
		insertionSort(occurence);
		System.out.println();

		System.out.println("-------------------------------------------");

		System.out.println("Sorting the occurences using insertion sort...........");
		for(int i=0;i<101;i++)
		{
			//System.out.println();
			System.out.print(" "+occurence[i]);
		}
		System.out.println();
		System.out.println("-------------------------------------------");

		System.out.println("The top 10 Pages with maximum matches are:...........");

		int count=0;
		for(int i=100;i>=0;i--)
		{ 
			for(int j=100;j>=0;j--)
			{
		
		if(occurence[i]==occurence2[j]&&count<10)
		{
			count++;
			System.out.println("Rank :"+count+"  "+fileList2[j]+" with "+occurence[i]+" matches of "+keyword);
		}
	}
	}
	}
	}
		



