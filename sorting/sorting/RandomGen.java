package sorting;

import java.util.Random;

public class RandomGen {
	private String patt;

	public RandomGen() {
		patt = "abc123";
	}

	public String GetRandomStr(int length, Random rnd) {
		String rs = "";
		for (int i = 1; i <= length; i++) {
			rs += patt.charAt(rnd.nextInt(patt.length()));

		}
		return rs;

	}

}