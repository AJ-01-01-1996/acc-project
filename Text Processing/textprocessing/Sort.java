package textprocessing;

//package AssignmentSection2;

//package sortF;

import java.util.Random;
import java.util.Arrays;

/**
* A class that contains several sorting routines,
* implemented as static methods.
* Arrays are rearranged with smallest item first,
* using compareTo.
* @author Mark Allen Weiss
*/
public final class Sort
{
  /**
   * Simple insertion sort.
   * @param a an array of Comparable items.
   */
  public static <AnyType extends Comparable<? super AnyType>>
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

  /**
   * Shellsort, using Shell's (poor) increments.
   * @param a an array of Comparable items.
   */
  public static <AnyType extends Comparable<? super AnyType>>
  void shellsort( AnyType [ ] a )
  {
      int j;

      for( int gap = a.length / 2; gap > 0; gap /= 2 )
          for( int i = gap; i < a.length; i++ )
          {
              AnyType tmp = a[ i ];
              for( j = i; j >= gap &&
                          tmp.compareTo( a[ j - gap ] ) < 0; j -= gap )
                  a[ j ] = a[ j - gap ];
              a[ j ] = tmp;
          }
  }


  /**
   * Internal method for heapsort.
   * @param i the index of an item in the heap.
   * @return the index of the left child.
   */
  private static int leftChild( int i )
  {
      return 2 * i + 1;
  }
  
  /**
   * Internal method for heapsort that is used in deleteMax and buildHeap.
   * @param a an array of Comparable items.
   * @index i the position from which to percolate down.
   * @int n the logical size of the binary heap.
   */
  private static <AnyType extends Comparable<? super AnyType>>
  void percDown( AnyType [ ] a, int i, int n )
  {
      int child;
      AnyType tmp;

      for( tmp = a[ i ]; leftChild( i ) < n; i = child )
      {
          child = leftChild( i );
          if( child != n - 1 && a[ child ].compareTo( a[ child + 1 ] ) < 0 )
              child++;
          if( tmp.compareTo( a[ child ] ) < 0 )
              a[ i ] = a[ child ];
          else
              break;
      }
      a[ i ] = tmp;
  }
  
  /**
   * Standard heapsort.
   * @param a an array of Comparable items.
   */
  public static <AnyType extends Comparable<? super AnyType>>
  void heapsort( AnyType [ ] a )
  {
      for( int i = a.length / 2 - 1; i >= 0; i-- )  /* buildHeap */
          percDown( a, i, a.length );
      for( int i = a.length - 1; i > 0; i-- )
      {
          swapReferences( a, 0, i );                /* deleteMax */
          percDown( a, 0, i );
      }
  }


  /**
   * Mergesort algorithm.
   * @param a an array of Comparable items.
   */
  public static <AnyType extends Comparable<? super AnyType>>
  void mergeSort( AnyType [ ] a )
  {
      AnyType [ ] tmpArray = (AnyType[]) new Comparable[ a.length ];

      mergeSort( a, tmpArray, 0, a.length - 1 );
  }

  /**
   * Internal method that makes recursive calls.
   * @param a an array of Comparable items.
   * @param tmpArray an array to place the merged result.
   * @param left the left-most index of the subarray.
   * @param right the right-most index of the subarray.
   */
  private static <AnyType extends Comparable<? super AnyType>>
  void mergeSort( AnyType [ ] a, AnyType [ ] tmpArray,
             int left, int right )
  {
      if( left < right )
      {
          int center = ( left + right ) / 2;
          mergeSort( a, tmpArray, left, center );
          mergeSort( a, tmpArray, center + 1, right );
          merge( a, tmpArray, left, center + 1, right );
      }
  }

  /**
   * Internal method that merges two sorted halves of a subarray.
   * @param a an array of Comparable items.
   * @param tmpArray an array to place the merged result.
   * @param leftPos the left-most index of the subarray.
   * @param rightPos the index of the start of the second half.
   * @param rightEnd the right-most index of the subarray.
   */
  private static <AnyType extends Comparable<? super AnyType>>
  void merge( AnyType [ ] a, AnyType [ ] tmpArray, int leftPos, int rightPos, int rightEnd )
  {
      int leftEnd = rightPos - 1;
      int tmpPos = leftPos;
      int numElements = rightEnd - leftPos + 1;

      // Main loop
      while( leftPos <= leftEnd && rightPos <= rightEnd )
          if( a[ leftPos ].compareTo( a[ rightPos ] ) <= 0 )
              tmpArray[ tmpPos++ ] = a[ leftPos++ ];
          else
              tmpArray[ tmpPos++ ] = a[ rightPos++ ];

      while( leftPos <= leftEnd )    // Copy rest of first half
          tmpArray[ tmpPos++ ] = a[ leftPos++ ];

      while( rightPos <= rightEnd )  // Copy rest of right half
          tmpArray[ tmpPos++ ] = a[ rightPos++ ];

      // Copy tmpArray back
      for( int i = 0; i < numElements; i++, rightEnd-- )
          a[ rightEnd ] = tmpArray[ rightEnd ];
  }

  /**
   * Quicksort algorithm.
   * @param a an array of Comparable items.
   */
  public static <AnyType extends Comparable<? super AnyType>>
  void quicksort( AnyType [ ] a )
  {
      quicksort( a, 0, a.length - 1 );
  }

  private static final int CUTOFF = 3;

  /**
   * Method to swap to elements in an array.
   * @param a an array of objects.
   * @param index1 the index of the first object.
   * @param index2 the index of the second object.
   */
  public static <AnyType> void swapReferences( AnyType [ ] a, int index1, int index2 )
  {
      AnyType tmp = a[ index1 ];
      a[ index1 ] = a[ index2 ];
      a[ index2 ] = tmp;
  }

  /**
   * Return median of left, center, and right.
   * Order these and hide the pivot.
   */
  private static <AnyType extends Comparable<? super AnyType>>
  AnyType median3( AnyType [ ] a, int left, int right )
  {
      int center = ( left + right ) / 2;
      if( a[ center ].compareTo( a[ left ] ) < 0 )
          swapReferences( a, left, center );
      if( a[ right ].compareTo( a[ left ] ) < 0 )
          swapReferences( a, left, right );
      if( a[ right ].compareTo( a[ center ] ) < 0 )
          swapReferences( a, center, right );

          // Place pivot at position right - 1
      swapReferences( a, center, right - 1 );
      return a[ right - 1 ];
  }

  /**
   * Internal quicksort method that makes recursive calls.
   * Uses median-of-three partitioning and a cutoff of 10.
   * @param a an array of Comparable items.
   * @param left the left-most index of the subarray.
   * @param right the right-most index of the subarray.
   */
  private static <AnyType extends Comparable<? super AnyType>>
  void quicksort( AnyType [ ] a, int left, int right )
  {
      if( left + CUTOFF <= right )
      {
          AnyType pivot = median3( a, left, right );

              // Begin partitioning
          int i = left, j = right - 1;
          for( ; ; )
          {
              while( a[ ++i ].compareTo( pivot ) < 0 ) { }
              while( a[ --j ].compareTo( pivot ) > 0 ) { }
              if( i < j )
                  swapReferences( a, i, j );
              else
                  break;
          }

          swapReferences( a, i, right - 1 );   // Restore pivot

          quicksort( a, left, i - 1 );    // Sort small elements
          quicksort( a, i + 1, right );   // Sort large elements
      }
      else  // Do an insertion sort on the subarray
          insertionSort( a, left, right );
  }

  /**
   * Internal insertion sort routine for subarrays
   * that is used by quicksort.
   * @param a an array of Comparable items.
   * @param left the left-most index of the subarray.
   * @param right the right-most index of the subarray.
   */
  private static <AnyType extends Comparable<? super AnyType>>
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

  /**
   * Quick selection algorithm.
   * Places the kth smallest item in a[k-1].
   * @param a an array of Comparable items.
   * @param k the desired rank (1 is minimum) in the entire array.
   */     
  public static <AnyType extends Comparable<? super AnyType>>
  void quickSelect( AnyType [ ] a, int k )
  {
      quickSelect( a, 0, a.length - 1, k );
  }

  /**
   * Internal selection method that makes recursive calls.
   * Uses median-of-three partitioning and a cutoff of 10.
   * Places the kth smallest item in a[k-1].
   * @param a an array of Comparable items.
   * @param left the left-most index of the subarray.
   * @param right the right-most index of the subarray.
   * @param k the desired index (1 is minimum) in the entire array.
   */
  private static <AnyType extends Comparable<? super AnyType>>
  void quickSelect( AnyType [ ] a, int left, int right, int k )
  {
      if( left + CUTOFF <= right )
      {
          AnyType pivot = median3( a, left, right );

              // Begin partitioning
          int i = left, j = right - 1;
          for( ; ; )
          {
              while( a[ ++i ].compareTo( pivot ) < 0 ) { }
              while( a[ --j ].compareTo( pivot ) > 0 ) { }
              if( i < j )
                  swapReferences( a, i, j );
              else
                  break;
          }

          swapReferences( a, i, right - 1 );   // Restore pivot

          if( k <= i )
              quickSelect( a, left, i - 1, k );
          else if( k > i + 1 )
              quickSelect( a, i + 1, right, k );
      }
      else  // Do an insertion sort on the subarray
          insertionSort( a, left, right );
  }


  private static void checkSort( Integer [ ] a )
  {
      for( int i = 0; i < a.length-1; i++ )
          if( a[i] > a[i+1])
              System.out.println( "Error at " + i );
      System.out.println( "Finished checksort" );
  }

  private static void checkSort( String [ ] a )
  {
      for( int i = 0; i < a.length-1; i++ )
          if( a[i].compareTo(a[i+1]) > 0)
              System.out.println( "Error at " + i );
      System.out.println( "Finished checksort" );
  }

  private static void print( Integer [ ] a )
  {
      for( int i = 0; i < a.length; i++ )
      	System.out.print(a[i] + " ");
      System.out.println();
  }

  // Some tests of the sorting algorithms
  private static final int NUM_ITEMS = 100000;
  private static int theSeed = 0;
  
  public static void main( String [ ] args )
  {
  	int NUM_ITEMS=100000;
  	//double starttime1,avgtime,avgtime1,avgtime2,avgtime3,endtime1,starttime2,endtime2,starttime,endtime,starttime3,endtime3;
  	double Mergetime=0,Quicktime=0,Heaptime=0,DualPivottime=0,startime;
      Long [ ] A = new Long[ NUM_ITEMS ];
      Long [ ] B = new Long[ NUM_ITEMS ];
      Long [ ] C = new Long[ NUM_ITEMS ];
      Long [ ] D = new Long[ NUM_ITEMS ];
     // Long [ ] E = new Long[ NUM_ITEMS ];
      
      Random rand = new Random(System.currentTimeMillis());
      
      // Fill array a with random numbers
      for(int i=0;i<100;i++)
      {
      for( int j = 0; j < A.length; j++ )
      {
          A[j] = rand.nextLong();
          B[j]=A[j];
          C[j]=A[j];
          D[j]=A[j];
        
      }
      
      startime= System.currentTimeMillis();
      Sort.mergeSort(A);
      Mergetime+=System.currentTimeMillis()-startime;
     // a=a+(endtime-starttime);
      startime= System.currentTimeMillis();
      Sort.quicksort(B);
      Quicktime+=System.currentTimeMillis()-startime;
      //a1=a1+(endtime1-starttime1);
      startime= System.currentTimeMillis();
      Sort.heapsort(C);
      Heaptime+=System.currentTimeMillis()-startime;
      //a2=a2+(endtime2-starttime2);
      startime= System.currentTimeMillis();
      Arrays.sort(D);
      DualPivottime+=System.currentTimeMillis()-startime;
     // a3=a3+(endtime3-starttime3);
    
      }
		System.out.println("the average time to sort 100,000 random keys using MergeSort is:" + Mergetime / 100);
		System.out.println("the average time to sort 100,000 random keys using QuickSort is: " + Quicktime / 100);
		System.out.println("the average time to sort 100,000 random keys using HeapSort is: " + Heaptime / 100);
		System.out.println("the average time to sort 100,000 random keys using dual-pivot QuickSort is: " + DualPivottime / 100);
      
  
      System.out.println();
 


      System.out.println();
      System.out.println();
  
      
     /* final int stringlength4=4;
      final int stringlength6=6;
      final int stringlength8=8;
      final int stringlength10=10;
  	RandomString ranstr = new RandomString();
      Random ranob=new Random();
      RadixSort radix=new RadixSort();
      String[] a4 = new String[NUM_ITEMS];
		String[] b4 = new String[NUM_ITEMS];
		String[] c4 = new String[NUM_ITEMS];
		String[] d4 = new String[NUM_ITEMS];
		String[] e4 = new String[NUM_ITEMS];
		String[] a6 = new String[NUM_ITEMS];
		String[] b6 = new String[NUM_ITEMS];
		String[] c6 = new String[NUM_ITEMS];
		String[] d6 = new String[NUM_ITEMS];
		String[] e6 = new String[NUM_ITEMS];
		String[] a8 = new String[NUM_ITEMS];
		String[] b8 = new String[NUM_ITEMS];
		String[] c8 = new String[NUM_ITEMS];
		String[] d8 = new String[NUM_ITEMS];
		String[] e8 = new String[NUM_ITEMS];
		String[] a10 = new String[NUM_ITEMS];
		String[] b10= new String[NUM_ITEMS];
		String[] c10 = new String[NUM_ITEMS];
		String[] d10 = new String[NUM_ITEMS];
		String[] e10 = new String[NUM_ITEMS];
		double avg_mergestr4=0,avg_quickstr4=0,avg_heapstr4=0,avg_dualpivot4=0,avg_radix4=0;
		double avg_mergestr6=0,avg_quickstr6=0,avg_heapstr6=0,avg_dualpivot6=0,avg_radix6=0;
		double avg_mergestr8=0,avg_quickstr8=0,avg_heapstr8=0,avg_dualpivot8=0,avg_radix8=0;
		double avg_mergestr10=0,avg_quickstr10=0,avg_heapstr10=0,avg_dualpivot10=0,avg_radix10=0;
		for (int i = 1; i <= 10; i++) 
		{
			for (int j = 0; j < a4.length; j++) 
				{
				a4[j] = b4[j] = c4[j] = d4[j] = e4[j] = ranstr.GenerateRandomString(stringlength4, ranob);
			
				}
			// Sorting strings of length 4
						startime = System.currentTimeMillis();
						Sort.mergeSort(a4);
						avg_mergestr4 += System.currentTimeMillis() - startime;

						startime = System.currentTimeMillis();
						Sort.quicksort(b4);
						avg_quickstr4 += System.currentTimeMillis() - startime;

						startime = System.currentTimeMillis();
						Sort.heapsort(c4);
						avg_heapstr4 += System.currentTimeMillis() - startime;

						startime = System.currentTimeMillis();
						Arrays.sort(d4);
						avg_dualpivot4 += System.currentTimeMillis() - startime;
						
						startime = System.currentTimeMillis();
						radix.radixSortA( e4, stringlength4  );
						avg_radix4 += System.currentTimeMillis() - startime;
					}

					System.out.println("the average time to sort 100,000 random keys using Merge Sort for String Array of length  4  is : " + avg_mergestr4 / 10);
					System.out.println("the average time to sort 100,000 random keys using Quick Sort for String Array of length 4 is : "+ avg_quickstr4 / 10);
					System.out.println("the average time to sort 100,000 random keys using Heap Sort for String Array of length 4 is : " + avg_heapstr4 / 10);
					System.out.println("the average time to sort 100,000 random keys using Dual Pivot Quick Sort for String Array of length 4 is : " + avg_dualpivot4 / 10);
					System.out.println("the average time to sort 100,000 random keys using radix Sort for String Array of length 4 is : " + avg_radix4 / 10);

			
					System.out.println();
					System.out.println();
    
					for (int i = 1; i <= 10; i++) 
					{
						for (int j = 0; j < a6.length; j++) 
							{
							a6[j] = b6[j] = c6[j] = d6[j] = e6[j] =ranstr.GenerateRandomString(stringlength6, ranob);
						
							}
						// Sorting strings of length 6
									startime = System.currentTimeMillis();
									Sort.mergeSort(a6);
									avg_mergestr6 += System.currentTimeMillis() - startime;

									startime = System.currentTimeMillis();
									Sort.quicksort(b6);
									avg_quickstr6 += System.currentTimeMillis() - startime;

									startime = System.currentTimeMillis();
									Sort.heapsort(c6);
									avg_heapstr6 += System.currentTimeMillis() - startime;

									startime = System.currentTimeMillis();
									Arrays.sort(d6);
									avg_dualpivot6 += System.currentTimeMillis() - startime;
									
									startime = System.currentTimeMillis();
									radix.radixSortA( e6, stringlength6  );
									avg_radix6 += System.currentTimeMillis() - startime;
					 }

						System.out.println("the average time to sort 100,000 random keys using Merge Sort for String Array of length  6  is : " + avg_mergestr6 / 10);
						System.out.println("the average time to sort 100,000 random keys using Quick Sort for String Array of length 6 is : "+ avg_quickstr6 / 10);
						System.out.println("the average time to sort 100,000 random keys using Heap Sort for String Array of length 6 is : " + avg_heapstr6 / 10);
						System.out.println("the average time to sort 100,000 random keys using Dual Pivot Quick Sort for String Array of length 6 is : " + avg_dualpivot6 / 10);
						System.out.println("the average time to sort 100,000 random keys using radix Sort for String Array of length 6 is : " + avg_radix6 / 10);

						
					
					
					System.out.println();
					System.out.println();
					
					for (int i = 1; i <= 10; i++) 
					{
						for (int j = 0; j < a8.length; j++) 
							{
							a8[j] = b8[j] = c8[j] = d8[j]= e8[j] = ranstr.GenerateRandomString(stringlength8, ranob);
						
							}
						// Sorting strings of length 8
									startime = System.currentTimeMillis();
									Sort.mergeSort(a8);
									avg_mergestr8 += System.currentTimeMillis() - startime;

									startime = System.currentTimeMillis();
									Sort.quicksort(b8);
									avg_quickstr8 += System.currentTimeMillis() - startime;

									startime = System.currentTimeMillis();
									Sort.heapsort(c8);
									avg_heapstr8 += System.currentTimeMillis() - startime;

									startime = System.currentTimeMillis();
									Arrays.sort(d8);
									avg_dualpivot8 += System.currentTimeMillis() - startime;
									
									startime = System.currentTimeMillis();
									radix.radixSortA( e8, stringlength8  );
									avg_radix8 += System.currentTimeMillis() - startime;
								}

								System.out.println("the average time to sort 100,000 random keys using Merge Sort for String Array of length  8  is : " + avg_mergestr8 / 10);
								System.out.println("the average time to sort 100,000 random keys using Quick Sort for String Array of length 8 is : "+ avg_quickstr8 / 10);
								System.out.println("the average time to sort 100,000 random keys using Heap Sort for String Array of length 8 is : " + avg_heapstr8 / 10);
								System.out.println("the average time to sort 100,000 random keys using Dual Pivot Quick Sort for String Array of length 8 is : " + avg_dualpivot8 / 10);
								System.out.println("the average time to sort 100,000 random keys using radix Sort for String Array of length 8 is : " + avg_radix8 / 10);

						
								System.out.println();
								System.out.println();
								
								
								for (int i = 1; i <= 10; i++) 
								{
									for (int j = 0; j < a10.length; j++) 
										{
										a10[j] = b10[j] = c10[j] = d10[j] = e10[j] = ranstr.GenerateRandomString(stringlength10, ranob);
									
										}
									// Sorting strings of length 10
												startime = System.currentTimeMillis();
												Sort.mergeSort(a10);
												avg_mergestr10 += System.currentTimeMillis() - startime;

												startime = System.currentTimeMillis();
												Sort.quicksort(b10);
												avg_quickstr10 += System.currentTimeMillis() - startime;

												startime = System.currentTimeMillis();
												Sort.heapsort(c10);
												avg_heapstr10 += System.currentTimeMillis() - startime;

												startime = System.currentTimeMillis();
												Arrays.sort(d10);
												avg_dualpivot10 += System.currentTimeMillis() - startime;
												
												startime = System.currentTimeMillis();
												radix.radixSortA( e10, stringlength10  );
												avg_radix10 += System.currentTimeMillis() - startime;
											}

											System.out.println("the average time to sort 100,000 random keys using Merge Sort for String Array of length  10  is : " + avg_mergestr10 / 10);
											System.out.println("the average time to sort 100,000 random keys using Quick Sort for String Array of length 10 is : "+ avg_quickstr10 / 10);
											System.out.println("the average time to sort 100,000 random keys using Heap Sort for String Array of length 10 is : " + avg_heapstr10 / 10);
											System.out.println("the average time to sort 100,000 random keys using Dual Pivot Quick Sort for String Array of length 10 is : " + avg_dualpivot10 / 10);
											System.out.println("the average time to sort 100,000 random keys using radix Sort for String Array of length 10 is : " + avg_radix10 / 10);

									
											System.out.println();
											System.out.println();
	*/
}
}

