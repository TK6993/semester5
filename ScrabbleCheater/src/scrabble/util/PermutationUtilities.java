package scrabble.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PermutationUtilities {

	static Set<String> subSets;
	/**
	 * creates a set with all subsets of the input string
	 * 
	 * @param str
	 * @return
	 */
	public static Set<String> getSubSets(String str) {
		subSets = new HashSet<>();
		Permutation p = new Permutation(str);
		if(str.length()>=2){
			findSubs(p.getNormalized());
		}
		//for(String e : subSets){
		//	System.out.println(e);}
		return subSets;
				}

	private static void findSubs(String s) {
		subSets.add(s);
		char[] StringAsChar = s.toCharArray();
		int indexToSkip = 0;
		while(indexToSkip < StringAsChar.length){
		String toAdd = "";
		for(int i = 0; i < StringAsChar.length;i++ ){
			if(i != indexToSkip){ 
				toAdd += StringAsChar[i];}
		}
		if(toAdd.length()>=2){
		subSets.add(toAdd);
		findSubs(toAdd);
		}
		indexToSkip++;
		}

	}

	public static String createPermutation(int length) {
		char[] letters = new char[length];
		Random r = new Random();
		for(int i = 0; i < length; i++){
			letters[i] = (char)('a' + r.nextInt(26));
		}
		return String.valueOf(letters);
	}
			
	
	

public static String createPermutation(String p) {
	char[] chars = p.toCharArray();
	char[] charPermutation = new char[chars.length];
	Random r = new Random();
	for(int i = 0; i < chars.length; i++){
		int current = r.nextInt(chars.length);
		while(chars[current]==0){
			current = r.nextInt(chars.length);
		}
		charPermutation[i] = chars[current];
		chars[current] = 0;
	}
	return String.valueOf(charPermutation);
}
}
