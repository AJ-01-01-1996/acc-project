package textprocessing;


import textprocessing.BoyerMoore;
import textprocessing.BruteForceMatch;
import textprocessing.In;
import textprocessing.KMP;

public class Question1_Time {
	
	static void BruteForceMatch(String pattern, String text) {
		
		int index = 0;
		int offset = 0;
		String newText = text;
		
		while(index < newText.length()) {

			newText = newText.substring(index);
			
			int newOffset = BruteForceMatch.search1(pattern, newText);
			if(index == 0)
				offset = offset + newOffset;
			else
				offset = offset + newOffset + pattern.length();
			index = newOffset + pattern.length();
			
		}
	}
	
	static void BoyerMoore(String pattern, String text) {
		
		BoyerMoore boyermoore = new BoyerMoore(pattern);
		int index = 0;
		int offset = 0;
		
		String newText = text;
		
		while(index < newText.length()) {

			newText = newText.substring(index);
			
			int newOffset = boyermoore.search(newText);
			if(index == 0)
				offset = offset + newOffset;
			else
				offset = offset + newOffset + pattern.length();
			
			index = newOffset + pattern.length();
		}
	}

	static void KMP(String pattern, String text) {
		
		KMP kmp = new KMP(pattern);
		int index = 0;
		int offset = 0;
		
		String newText = text;
		
		while(index < newText.length()) {

			newText = newText.substring(index);
			
			int newOffset = kmp.search(newText);
			if(index == 0)
				offset = offset + newOffset;
			else
				offset = offset + newOffset + pattern.length();
			index = newOffset + pattern.length();
			
		}
	}
	
	public static void main(String[] args) {
		String[] patterns = { "hard", "disk", "hard disk", "hard drive", "hard dist", "xltpru" };
		In in = new In("HardDisk.txt");
		String text = in.readAll();
		
		double startTime; double endTime; double averageCPUTime;
		
		startTime = System.currentTimeMillis();
		for(int i = 0; i < 100; i++) {
			for(String p: patterns)
				BruteForceMatch(p, text);
		}
		endTime = System.currentTimeMillis();
		averageCPUTime = (endTime - startTime)/100;
		System.out.println("The Average CPU Time for BruteForceMatch \n" + averageCPUTime);
		
		
		startTime = System.currentTimeMillis();
		for(int i = 0; i < 100; i++) {
			for(String p: patterns)
				BoyerMoore(p, text);
		}
		endTime = System.currentTimeMillis();
		averageCPUTime = (endTime - startTime)/100;
		System.out.println("The Average CPU Time for BoyerMoore \n" + averageCPUTime);
		
		startTime = System.currentTimeMillis();
		for(int i = 0; i < 100; i++) {
			for(String p: patterns)
				KMP(p, text); 
		}
		endTime = System.currentTimeMillis();
		averageCPUTime = (endTime - startTime)/100;
		System.out.println("The Average CPU Time for KMP \n" + averageCPUTime);
		
		System.out.println("   ");
		System.out.println("Complete");
	}

}
