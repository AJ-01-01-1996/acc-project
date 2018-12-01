package sorting;

import java.util.Arrays;
import java.util.Random;

/**
 * A class that contains several sorting routines, implemented as static
 * methods. Arrays are rearranged with smallest item first, using compareTo.
 * 
 * @author Mark Allen Weiss
 */
public final class Sortd {
	/**
	 * Simple insertion sort.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 */
	public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a) {
		int j;

		for (int p = 1; p < a.length; p++) {
			AnyType tmp = a[p];
			for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--)
				a[j] = a[j - 1];
			a[j] = tmp;
		}
	}

	/**
	 * Shellsort, using Shell's (poor) increments.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 */
	public static <AnyType extends Comparable<? super AnyType>> void shellsort(AnyType[] a) {
		int j;

		for (int gap = a.length / 2; gap > 0; gap /= 2)
			for (int i = gap; i < a.length; i++) {
				AnyType tmp = a[i];
				for (j = i; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap)
					a[j] = a[j - gap];
				a[j] = tmp;
			}
	}

	/**
	 * Internal method for heapsort.
	 * 
	 * @param i
	 *            the index of an item in the heap.
	 * @return the index of the left child.
	 */
	private static int leftChild(int i) {
		return 2 * i + 1;
	}

	/**
	 * Internal method for heapsort that is used in deleteMax and buildHeap.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @index i the position from which to percolate down.
	 * @int n the logical size of the binary heap.
	 */
	private static <AnyType extends Comparable<? super AnyType>> void percDown(AnyType[] a, int i, int n) {
		int child;
		AnyType tmp;

		for (tmp = a[i]; leftChild(i) < n; i = child) {
			child = leftChild(i);
			if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0)
				child++;
			if (tmp.compareTo(a[child]) < 0)
				a[i] = a[child];
			else
				break;
		}
		a[i] = tmp;
	}

	/**
	 * Standard heapsort.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 */
	public static <AnyType extends Comparable<? super AnyType>> void heapsort(AnyType[] a) {
		for (int i = a.length / 2 - 1; i >= 0; i--) /* buildHeap */
			percDown(a, i, a.length);
		for (int i = a.length - 1; i > 0; i--) {
			swapReferences(a, 0, i); /* deleteMax */
			percDown(a, 0, i);
		}
	}

	/**
	 * Mergesort algorithm.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 */
	public static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a) {
		AnyType[] tmpArray = (AnyType[]) new Comparable[a.length];

		mergeSort(a, tmpArray, 0, a.length - 1);
	}

	/**
	 * Internal method that makes recursive calls.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @param tmpArray
	 *            an array to place the merged result.
	 * @param left
	 *            the left-most index of the subarray.
	 * @param right
	 *            the right-most index of the subarray.
	 */
	private static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a, AnyType[] tmpArray,
			int left, int right) {
		if (left < right) {
			int center = (left + right) / 2;
			mergeSort(a, tmpArray, left, center);
			mergeSort(a, tmpArray, center + 1, right);
			merge(a, tmpArray, left, center + 1, right);
		}
	}

	/**
	 * Internal method that merges two sorted halves of a subarray.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @param tmpArray
	 *            an array to place the merged result.
	 * @param leftPos
	 *            the left-most index of the subarray.
	 * @param rightPos
	 *            the index of the start of the second half.
	 * @param rightEnd
	 *            the right-most index of the subarray.
	 */
	private static <AnyType extends Comparable<? super AnyType>> void merge(AnyType[] a, AnyType[] tmpArray,
			int leftPos, int rightPos, int rightEnd) {
		int leftEnd = rightPos - 1;
		int tmpPos = leftPos;
		int numElements = rightEnd - leftPos + 1;

		// Main loop
		while (leftPos <= leftEnd && rightPos <= rightEnd)
			if (a[leftPos].compareTo(a[rightPos]) <= 0)
				tmpArray[tmpPos++] = a[leftPos++];
			else
				tmpArray[tmpPos++] = a[rightPos++];

		while (leftPos <= leftEnd) // Copy rest of first half
			tmpArray[tmpPos++] = a[leftPos++];

		while (rightPos <= rightEnd) // Copy rest of right half
			tmpArray[tmpPos++] = a[rightPos++];

		// Copy tmpArray back
		for (int i = 0; i < numElements; i++, rightEnd--)
			a[rightEnd] = tmpArray[rightEnd];
	}

	/**
	 * Quicksort algorithm.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 */
	public static <AnyType extends Comparable<? super AnyType>> void quicksort(AnyType[] a) {
		quicksort(a, 0, a.length - 1);
	}

	private static final int CUTOFF = 3;

	/**
	 * Method to swap to elements in an array.
	 * 
	 * @param a
	 *            an array of objects.
	 * @param index1
	 *            the index of the first object.
	 * @param index2
	 *            the index of the second object.
	 */
	public static <AnyType> void swapReferences(AnyType[] a, int index1, int index2) {
		AnyType tmp = a[index1];
		a[index1] = a[index2];
		a[index2] = tmp;
	}

	/**
	 * Return median of left, center, and right. Order these and hide the pivot.
	 */
	private static <AnyType extends Comparable<? super AnyType>> AnyType median3(AnyType[] a, int left, int right) {
		int center = (left + right) / 2;
		if (a[center].compareTo(a[left]) < 0)
			swapReferences(a, left, center);
		if (a[right].compareTo(a[left]) < 0)
			swapReferences(a, left, right);
		if (a[right].compareTo(a[center]) < 0)
			swapReferences(a, center, right);

		// Place pivot at position right - 1
		swapReferences(a, center, right - 1);
		return a[right - 1];
	}

	/**
	 * Internal quicksort method that makes recursive calls. Uses median-of-three
	 * partitioning and a cutoff of 10.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @param left
	 *            the left-most index of the subarray.
	 * @param right
	 *            the right-most index of the subarray.
	 */
	private static <AnyType extends Comparable<? super AnyType>> void quicksort(AnyType[] a, int left, int right) {
		if (left + CUTOFF <= right) {
			AnyType pivot = median3(a, left, right);

			// Begin partitioning
			int i = left, j = right - 1;
			for (;;) {
				while (a[++i].compareTo(pivot) < 0) {
				}
				while (a[--j].compareTo(pivot) > 0) {
				}
				if (i < j)
					swapReferences(a, i, j);
				else
					break;
			}

			swapReferences(a, i, right - 1); // Restore pivot

			quicksort(a, left, i - 1); // Sort small elements
			quicksort(a, i + 1, right); // Sort large elements
		} else // Do an insertion sort on the subarray
			insertionSort(a, left, right);
	}

	/**
	 * Internal insertion sort routine for subarrays that is used by quicksort.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @param left
	 *            the left-most index of the subarray.
	 * @param right
	 *            the right-most index of the subarray.
	 */
	private static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a, int left, int right) {
		for (int p = left + 1; p <= right; p++) {
			AnyType tmp = a[p];
			int j;

			for (j = p; j > left && tmp.compareTo(a[j - 1]) < 0; j--)
				a[j] = a[j - 1];
			a[j] = tmp;
		}
	}

	/**
	 * Quick selection algorithm. Places the kth smallest item in a[k-1].
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @param k
	 *            the desired rank (1 is minimum) in the entire array.
	 */
	public static <AnyType extends Comparable<? super AnyType>> void quickSelect(AnyType[] a, int k) {
		quickSelect(a, 0, a.length - 1, k);
	}

	/**
	 * Internal selection method that makes recursive calls. Uses median-of-three
	 * partitioning and a cutoff of 10. Places the kth smallest item in a[k-1].
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @param left
	 *            the left-most index of the subarray.
	 * @param right
	 *            the right-most index of the subarray.
	 * @param k
	 *            the desired index (1 is minimum) in the entire array.
	 */
	private static <AnyType extends Comparable<? super AnyType>> void quickSelect(AnyType[] a, int left, int right,
			int k) {
		if (left + CUTOFF <= right) {
			AnyType pivot = median3(a, left, right);

			// Begin partitioning
			int i = left, j = right - 1;
			for (;;) {
				while (a[++i].compareTo(pivot) < 0) {
				}
				while (a[--j].compareTo(pivot) > 0) {
				}
				if (i < j)
					swapReferences(a, i, j);
				else
					break;
			}

			swapReferences(a, i, right - 1); // Restore pivot

			if (k <= i)
				quickSelect(a, left, i - 1, k);
			else if (k > i + 1)
				quickSelect(a, i + 1, right, k);
		} else // Do an insertion sort on the subarray
			insertionSort(a, left, right);
	}

	private static void checkSort(Integer[] a) {
		for (int i = 0; i < a.length - 1; i++)
			if (a[i] > a[i + 1])
				System.out.println("Error at " + i);
		System.out.println("Finished checksort");
	}

	private static void checkSort(String[] a) {
		for (int i = 0; i < a.length - 1; i++)
			if (a[i].compareTo(a[i + 1]) > 0)
				System.out.println("Error at " + i);
		System.out.println("Finished checksort");
	}

	private static void print(String[] e) {
		for (int i = 0; i < e.length; i++)
			System.out.print(e[i] + " ");
		System.out.println();
	}

	// Some tests of the sorting algorithms
	private static final int NUM_ITEMS = 100000;
	private static int theSeed = 0;

	public static void main(String[] args) {
		Long[] a = new Long[NUM_ITEMS];
		Long[] b = new Long[NUM_ITEMS];
		Long[] c = new Long[NUM_ITEMS];
		Long[] d = new Long[NUM_ITEMS];
		Random rand = new Random(System.currentTimeMillis());
		double time_m = 0, time_q = 0, time_h = 0, time_dp = 0, start;
		for (int k = 1; k <= 100; k++) {
			// Fill array a with random numbers
			for (int i = 0; i < a.length; i++) {
				d[i] = c[i] = b[i] = a[i] = rand.nextLong();
			}
			// Merge Sort
			start = System.currentTimeMillis();
			Sortd.mergeSort(a);
			time_m += System.currentTimeMillis() - start;

			// Quick Sort
			start = System.currentTimeMillis();
			Sortd.quicksort(b);
			time_q += System.currentTimeMillis() - start;

			// Heap Sort
			start = System.currentTimeMillis();
			Sortd.heapsort(c);
			time_h += System.currentTimeMillis() - start;

			// Sort dual pivot sorting
			start = System.currentTimeMillis();
			Arrays.sort(d);
			time_dp += System.currentTimeMillis() - start;
		}
		System.out.println("Average Time for Merge Sort is : " + time_m / 100);
		System.out.println("Average Time for Quick Sort is : " + time_q / 100);
		System.out.println("Average Time for Heap Sort is : " + time_h / 100);
		System.out.println("Average Time for Dual Pivot Quick Sort is : " + time_dp / 100);
		System.out.println("\n------------------------------------------------------------------------------------\n");
		time_m = 0;
		time_q = 0;
		time_h = 0;
		time_dp = 0;
		// Sorting String objects
		// Fill array a with random numbers
		String[] e = new String[NUM_ITEMS];
		String[] f = new String[NUM_ITEMS];
		String[] g = new String[NUM_ITEMS];
		String[] h = new String[NUM_ITEMS];
		final int strLen = 4;

		RandomGen rmgenObj = new RandomGen();
		Random rnd = new Random();
		/* for(int k=2;k<=10;k=k+2)
		{
			time_m = 0;
			time_q = 0;
			time_h = 0;
			time_dp = 0; */
		for (int l = 1; l <= 10; l++) {
			
			for (int i = 0; i < e.length; i++) {
				e[i] = f[i] = g[i] = h[i] = rmgenObj.GetRandomStr(strLen, rnd);
			}

			// Merge Sort
			start = System.currentTimeMillis();
			Sortd.mergeSort(e);
			time_m += System.currentTimeMillis() - start;

			// Quick Sort
			start = System.currentTimeMillis();
			Sortd.quicksort(f);
			time_q += System.currentTimeMillis() - start;

			// Heap Sort
			start = System.currentTimeMillis();
			Sortd.heapsort(g);
			time_h += System.currentTimeMillis() - start;

			// Sort dual pivot sorting
			start = System.currentTimeMillis();
			Arrays.sort(h);
			time_dp += System.currentTimeMillis() - start;
		}

		System.out.println("Average Time of Merge Sort for Array of length " + strLen + " is : " + time_m / 10);
		System.out.println("Average Time of Quick Sort for Array of length " + strLen + " is : " + time_q / 10);
		System.out.println("Average Time of Heap Sort for Array of length " + strLen + " is : " + time_h / 10);
		System.out.println("Average Time of Dual Pivot Quick Sort for Array of length " + strLen + " is : " + time_dp / 10);
		System.out.println("\n------------------------------------------------------------------------------------\n");
		}
		/*// Fill array a with random numbers
		for (int i = 0; i < a.length; i++)
			a[i] = rand.nextLong();
		// Quick select
		int k = 10; // k must be greater than 0 and less than n
		// Sort.quickSelect(a, k); // NUM_ITEMS / 2 );
		// System.out.println("Quickselect:");
		// Sort.print(a);
		// System.out.println("kth smallest = " + a[k - 1]);
*/
}
