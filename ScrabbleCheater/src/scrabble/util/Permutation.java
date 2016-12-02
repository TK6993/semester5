package scrabble.util;

import java.util.Arrays;


public class Permutation {
String word;
	public Permutation(String word) {
	 this.word = word;
	}

	@Override
	public int hashCode() {
		return getNormalized().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return this.getNormalized().equals(((Permutation)obj).getNormalized());

	}

	@Override
	public String toString() {
		return getWord() + " / " + getNormalized();
	}

	public String getNormalized() {
		char[] chars = word.toCharArray();
		Arrays.sort(chars);
		String toReturn = String.valueOf(chars);
		return toReturn;
	}

	public String getWord() {
		return this.word;
	}

	public int length() {
	return this.word.length();
	}

}
