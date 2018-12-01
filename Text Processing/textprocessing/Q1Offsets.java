package textprocessing;

import textprocessing.BoyerMoore;
import textprocessing.BruteForceMatch;
import textprocessing.In;
import textprocessing.KMP;

public class Q1Offsets {
	
	static void BruteForceMatch(String pattern, String text) {
		
		System.out.println("Finding " + pattern + " in the text using BruteForceMatch");
		int index = 0;
		int offset = 0;
		
		String newText = text;
		System.out.println("Found at pos");
		while(index < newText.length()) {

			newText = newText.substring(index);
			
			int newOffset = BruteForceMatch.search1(pattern, newText);
			if(offset!=0)
			System.out.print(offset+",");
			if(index == 0)
				offset = offset + newOffset;
			else
				offset = offset + newOffset + pattern.length();
			
			
			
			
			index = newOffset + pattern.length();
			
		}
		System.out.println("\n");
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
		System.out.println("Finding " + pattern + " in the text using KMP");
		int index = 0;
		int offset = 0;
		int newOffset=0;
		
		String newText = text;
		
		System.out.println("Found at pos");
		
		while(index < newText.length()) {

			newText = newText.substring(index);
			if(offset!=0)
				System.out.print(offset+",");
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
		System.out.println("\n");
	}

	public static void main(String[] args) {
		
		String[] patterns = { "hard", "disk", "hard disk", "hard drive", "hard dist", "xltpru" };
		In in = new In("HardDisk.txt");
		String text = in.readAll();
		
		for(String pattern: patterns) {
			BruteForceMatch(pattern, text);
		}
		for(String pattern: patterns) {
			BoyerMoore(pattern, text);
		}
		for(String pattern: patterns) {
			KMP(pattern, text); 
		}
	}

}
