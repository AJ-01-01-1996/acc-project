package textprocessing;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class Q2TST<Value> {
	private int N; // size
	private Node root; // root of TST

	private class Node {
		private char c; // character
		private Node left, mid, right; // left, middle, and right subtries
		private Value val; // value associated with string
	}

	// return number of key-value pairs
	public int size() {
		return N;
	}

	/**************************************************************
	 * Is string key in the symbol table?
	 **************************************************************/
	public boolean contains(String key) {
		return get(key) != null;
	}

	public Value get(String key) {
		if (key == null)
			throw new NullPointerException();
		if (key.length() == 0)
			throw new IllegalArgumentException("key must have length >= 1");
		Node x = get(root, key, 0);
		if (x == null)
			return null;
		return x.val;
	}

	// return subtrie corresponding to given key
	private Node get(Node x, String key, int d) {
		if (key == null)
			throw new NullPointerException();
		if (key.length() == 0)
			throw new IllegalArgumentException("key must have length >= 1");
		if (x == null)
			return null;
		char c = key.charAt(d);
		if (c < x.c)
			return get(x.left, key, d);
		else if (c > x.c)
			return get(x.right, key, d);
		else if (d < key.length() - 1)
			return get(x.mid, key, d + 1);
		else
			return x;
	}

	/**************************************************************
	 * Insert string s into the symbol table.
	 **************************************************************/
	public void put(String s, Value val) {
		if (!contains(s))
			N++;
		root = put(root, s, val, 0);
	}

	private Node put(Node x, String s, Value val, int d) {
		char c = s.charAt(d);
		if (x == null) {
			x = new Node();
			x.c = c;
		}
		if (c < x.c)
			x.left = put(x.left, s, val, d);
		else if (c > x.c)
			x.right = put(x.right, s, val, d);
		else if (d < s.length() - 1)
			x.mid = put(x.mid, s, val, d + 1);
		else
			x.val = val;
		return x;
	}

	/**************************************************************
	 * Find and return longest prefix of s in TST
	 **************************************************************/
	public String longestPrefixOf(String s) {
		if (s == null || s.length() == 0)
			return null;
		int length = 0;
		Node x = root;
		int i = 0;
		while (x != null && i < s.length()) {
			char c = s.charAt(i);
			if (c < x.c)
				x = x.left;
			else if (c > x.c)
				x = x.right;
			else {
				i++;
				if (x.val != null)
					length = i;
				x = x.mid;
			}
		}
		return s.substring(0, length);
	}

	// all keys in symbol table
	public Iterable<String> keys() {
		Queue<String> queue = new Queue<String>();
		collect(root, "", queue);
		return queue;
	}

	// all keys starting with given prefix
	public Iterable<String> prefixMatch(String prefix) {
		Queue<String> queue = new Queue<String>();
		Node x = get(root, prefix, 0);
		if (x == null)
			return queue;
		if (x.val != null)
			queue.enqueue(prefix);
		collect(x.mid, prefix, queue);
		return queue;
	}

	// all keys in subtrie rooted at x with given prefix
	private void collect(Node x, String prefix, Queue<String> queue) {
		if (x == null)
			return;
		collect(x.left, prefix, queue);
		if (x.val != null)
			queue.enqueue(prefix + x.c);
		collect(x.mid, prefix + x.c, queue);
		collect(x.right, prefix, queue);
	}

	// return all keys matching given wildcard pattern
	public Iterable<String> wildcardMatch(String pat) {
		Queue<String> queue = new Queue<String>();
		collect(root, "", 0, pat, queue);
		return queue;
	}

	private void collect(Node x, String prefix, int i, String pat, Queue<String> q) {
		if (x == null)
			return;
		char c = pat.charAt(i);
		if (c == '.' || c < x.c)
			collect(x.left, prefix, i, pat, q);
		if (c == '.' || c == x.c) {
			if (i == pat.length() - 1 && x.val != null)
				q.enqueue(prefix + x.c);
			if (i < pat.length() - 1)
				collect(x.mid, prefix + x.c, i + 1, pat, q);
		}
		if (c == '.' || c > x.c)
			collect(x.right, prefix, i, pat, q);
	}

	// test client
	public static void main(String[] args) {
		/*
		 * String[] keys = {"she","sells","sea","shells","by","the","sea","shore"};
		 * 
		 * // build symbol table from standard input TST<Integer> st = new
		 * TST<Integer>(); for (int i = 0; i < keys.length; i++) { //String key =
		 * In.readString(); st.put(keys[i], i); }
		 * 
		 * 
		 * // print results // for (String key : st.keys()) { // StdOut.println(key +
		 * " " + st.get(key)); //}
		 * 
		 * // print value of a key String key = "shells";
		 * StdOut.println("key = shells, value = "+ st.get(keys[2]));
		 */

		String text = null;
		System.out.println("Reading the File : Protein.txt ");
		In input = new In("Protein.txt");
		while (!input.isEmpty()) {
			text = input.readAll();

		}
		Q2TST<Queue<Integer>> tst = new Q2TST<Queue<Integer>>();
		StringTokenizer st1 = new StringTokenizer(text, " ", false);
		Queue<Integer> index;
		int indexPosition = 0;
		ArrayList<Integer> wordPosition;
		while (st1.hasMoreTokens()) {
			String key = st1.nextToken().toString();
			wordPosition = getMatchingKeysKMP(key, text);
			index = new Queue<Integer>();
			index.enqueue(indexPosition);
			for (int i : wordPosition) {
				index.enqueue(i);
			}
			tst.put(key, index);
			indexPosition++;
		}
		System.out.println("TRIE creation is Done");
		String[] keys = { "protein", "complex", "PPI", "prediction" ,"interactions","physicochemical","hydrophobic","yeast"};
		for (String key : keys) {
			Queue<Integer> que = tst.get(key);
			System.out.println("String Pattern :" + key);
			if (que == null) {
				System.out.println("This pattern does not exist in the file");
			} else {
				System.out.print("IndexNumber:" + que.dequeue());
				System.out.println("\tOccurrences:" + que.size());
				System.out.print("Positions:");
				for (int i : que) {
					System.out.print(que.dequeue() + "  ");
				}
			}
			System.out.println("\n\n");
		}
	}

	private static ArrayList<Integer> getMatchingKeysKMP(String pat, String txt) {
		KMP kmp = new KMP(pat);
		int offsetPos = 0, searchPos = 0;
		ArrayList<Integer> patternPosition = new ArrayList<Integer>();
		int patternLength = pat.length();
		String subStr;
		while ((offsetPos <= (txt.length() - patternLength + 1))) {
			subStr = txt.substring(offsetPos);
			searchPos = kmp.search(txt);
			if (searchPos == subStr.length())
				break;
			patternPosition.add(offsetPos + searchPos);
			offsetPos = offsetPos + searchPos + patternLength;
		}
		if (patternPosition.size() != 0)
			return patternPosition;
		else
			patternPosition.add(searchPos);
		return patternPosition;
	}

}