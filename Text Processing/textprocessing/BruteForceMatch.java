package textprocessing;

/***************************************************************
*
*  Compilation:  javac Brtue.java
*  Execution:    java Brute pattern text
*
*  Reads in two strings, the pattern and the input text, and
*  searches for the pattern in the input text using brute force.
*
*  % java Brute abracadabra abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad 
*  pattern:               abracadabra          
*
*  % java Brute rab abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad 
*  pattern:         rab                         
* 
*  % java Brute rabrabracad abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad
*  pattern:                        rabrabracad

*
*  % java Brute bcara abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad 
*  pattern:                                   bcara
* 
*  % java Brute abacad abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad
*  pattern: abacad
*
***************************************************************/

public class BruteForceMatch {

  /***************************************************************************
   *  String versions
   ***************************************************************************/

   // return offset of first match or N if no match
   public static int search1(String pat, String txt) {
       int M = pat.length();
       int N = txt.length();

       for (int i = 0; i <= N - M; i++) {
           int j;
           for (j = 0; j < M; j++) {
               if (txt.charAt(i+j) != pat.charAt(j))
                   break;
           }
           if (j == M) return i;            // found at offset i
       }
       return N;                            // not found
   }

   // return offset of first match or N if no match
   public static int search2(String pat, String txt) {
       int M = pat.length();
       int N = txt.length();
       int i, j;
       for (i = 0, j = 0; i < N && j < M; i++) {
           if (txt.charAt(i) == pat.charAt(j)) j++;
           else { i -= j; j = 0;  }
       }
       if (j == M) return i - M;    // found
       else        return N;        // not found
   }


  /***************************************************************************
   *  char[] array versions
   ***************************************************************************/

   // return offset of first match or N if no match
   public static int search1(char[] pattern, char[] text) {
       int M = pattern.length;
       int N = text.length;

       for (int i = 0; i <= N - M; i++) {
           int j;
           for (j = 0; j < M; j++) {
               if (text[i+j] != pattern[j])
                   break;
           }
           if (j == M) return i;            // found at offset i
       }
       return N;                            // not found
   }

   // return offset of first match or N if no match
   public static int search2(char[] pattern, char[] text) { 
       int M = pattern.length;
       int N = text.length;
       int i, j;
       for (i = 0, j = 0; i < N && j < M; i++) {
           if (text[i] == pattern[j]) j++;
           else { i -= j; j = 0;  }
       }
       if (j == M) return i - M;    // found
       else        return N;        // not found
   } 


   // test client
   public static void main(String[] args) {
       //String pat = args[0];
       //String txt = args[1];
	   
       // There are two implmentations of search
	   // one is with String and the other is an array of chars
	   In myfile=new In("HardDisk.txt");
	   String mytext=myfile.readAll();
	   //mytext.sub
	   
       String pat = "drive";
       int wordlength=pat.length();
       String txt = mytext;
       char[] pattern = pat.toCharArray();
       char[] textchar = txt.toCharArray();

       char[] text    = txt.toCharArray();
       char[] textchar2=new char[1000000000];
       int offset1a = search1(pat, txt);
      //int  nextStart=offset1a+pattern.length;
     // String txt1=txt+nextStart;
       int offset2a = search2(pattern, text);
       int offset1 = search1(pattern, text);
       int offset2b = search2(pattern, text);
       StdOut.print(text[110]);
       StdOut.print(text[111]);
       StdOut.print(text[112]);
       StdOut.print(text[113]);
       StdOut.print(text[114]);
       StdOut.print(text[62]);
       StdOut.print(text[110]);
       StdOut.println();
       StdOut.print("pattern: ");
       for (int i = 0; i < offset1a; i++)
           StdOut.print(" ");
       StdOut.println(pat + " at pos " + offset1);
       
       // print results
       for(int k=offset1+wordlength,l=0;k<mytext.length();k++,l++)
       {
    	   textchar2[l]=textchar[k];
    	 //  StdOut.print(textchar2[l]);
    	   
    	  // int offset3a = search1(pat, txt);
       }
       offset1=offset1+wordlength;
     // int wordlength=4;
      int offset2=search1(pattern,textchar2)+offset1;
      
      StdOut.print("pattern: ");
      for (int i = 0; i < offset1; i++)
          StdOut.print(" ");
      StdOut.println(pat + " at pos " + offset2);
       //StdOut.println("text:    " + txt);
      char[] textchar3=new char[10000000];
      for(int k=offset2+wordlength,l=0;k<mytext.length();k++,l++)
      {
   	   textchar3[l]=textchar[k];
   	 //  StdOut.print(textchar3[l]);
   	   
   	  // int offset3a = search1(pat, txt);
      }
      offset2=offset2+wordlength;
int offset3=search1(pattern,textchar3)+offset2;
      
      StdOut.print("pattern: ");
      for (int i = 0; i < offset1a; i++)
          StdOut.print(" ");
      StdOut.println(pat + " at pos " + offset3);
       
       // from brute force search method 1a
     /*  StdOut.print("pattern: ");
       for (int i = 0; i < offset1a; i++)
           StdOut.print(" ");
       StdOut.println(pat + " at pos " + offset2b);

       // from brute force search method 2a
       StdOut.print("pattern: ");
       for (int i = 0; i < offset2a; i++)
           StdOut.print(" ");
       StdOut.println(pat + " at pos " + offset2a);

       // from brute force search method 1b
       StdOut.print("pattern: ");
       for (int i = 0; i < offset1b; i++)
           StdOut.print(" ");
       StdOut.println(pat + " at pos " + offset1b);

       // from brute force search method 2b
       StdOut.print("pattern: ");
       for (int i = 0; i < offset2b; i++)
           StdOut.print(" ");
       StdOut.println(pat + " at pos " + offset2b);
    //   StdOut.println("text:    " + txt1);
   }*/
}
}