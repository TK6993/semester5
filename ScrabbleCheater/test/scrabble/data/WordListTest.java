package scrabble.data;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class WordListTest {

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] {
				{ "just two", "ba", 2, 2, null, new String[] { "ab", "ba" } },
				{ "just two with three characters", "abc", 2, 2, null,
						new String[] { "abc", "cba" } },
				{
						"the correct number from the word list",
						"abc",
						2,
						4,
						new String[] { "ab", "bc", "abc", "cba" },
						new String[] { "ab", "bc", "ad", "abc", "cba", "asd",
								"asdfjkafs", "aa" } },
				{ "the correct number from the word list II", "abc", 2, 2,
						null,
						new String[] { "cab", "cba", "asd", "asdfjkafs", "aa" } },
				{
						"no permutations, but shorter suggestions",
						"abcd",
						0,
						2,
						null,
						new String[] { "cab", "cba", "asd", "axdr",
								"asdfjkafs", "aa" } } };
		//data = addSubjects(data);
		return Arrays.asList(data);
	}

	

	String message;
	String tileRack;
	int permutationCount;
	int wordSuggestionCount;
	String[] words;
	WordList wl;

	public WordListTest(String message, String tileRack, int permutationCount,
			int wordSuggestionCount, String[] validSuggestions, String[] words) {
		this.message = message;
		this.tileRack = tileRack;
		this.permutationCount = permutationCount;
		this.wordSuggestionCount = wordSuggestionCount;
		this.words = words;
	}

	@Before
	public void createWordList() {
		wl = new SimpleWordList();
	}

	@Test
	public void sizeShouldGiveTotalNumberOfStoredWords() {
		wl.addAll(Arrays.asList(words));
		assertEquals(message, words.length, wl.size());
	}

	@Test
	public void shouldReturnCorrectAmountOfPermutations() {
		wl.addAll(Arrays.asList(words));
		assertEquals(message, permutationCount, wl.permutations(tileRack)
				.size());
	}

	@Ignore
	public void shouldReturnCorrectNumberOfSuggestions() {
		wl.addAll(Arrays.asList(words));
		assertEquals(message, wordSuggestionCount, wl.words(tileRack).size());
	}

}
