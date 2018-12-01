package sorting;

import java.util.Random;

public class RandomString {
	private String pattern;

	public RandomString() {
		pattern = "qwe438";
	}

	public String GenerateRandomString(int length, Random random) {
		String ran = "";
		for (int i = 1; i <= length; i++) {
		ran += pattern.charAt(random.nextInt(pattern.length()));

		}return ran;
	}}