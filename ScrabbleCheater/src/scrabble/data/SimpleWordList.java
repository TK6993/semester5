package scrabble.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import scrabble.util.Permutation;

public class SimpleWordList implements WordList {
	Set<String> validWords = new HashSet<>();
	Set<Permutation> wordlistDoc = new HashSet<>();
	ArrayList<String> WList = new ArrayList<>();

	

	@Override
	public Set<String> permutations(String tileRackPart) {
	Permutation perm = new Permutation(tileRackPart);
	String toAdd = perm.getNormalized();
	for(Permutation p : wordlistDoc){
		if(p.getNormalized().equals(toAdd))validWords.add(p.getWord());
		}
	return validWords;
	}
	
	public static void main (String[] args) throws IOException{
		SimpleWordList myWordList = new SimpleWordList();
		myWordList.initFromFile("wordlists\\sowpods.txt");
		myWordList.permutations("");
		for(String v : myWordList.validWords){
		System.out.println(v);}
	}

	@Override
	public Set<String> words(String tileRack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(String word) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<String> words) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public WordList initFromFile(String fileName) throws IOException {
		
		FileReader f = new FileReader(fileName);
		BufferedReader r = new BufferedReader(f);
		String word;
		
		do{
			word = r.readLine();
			if (word != null) WList.add(word);
		}while(word!=(null));
		
		r.close();
		
		for(String s : WList){
			Permutation temp = new Permutation(s);
			wordlistDoc.add(temp);
		}
		return null;
	}

}
