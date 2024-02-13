import java.util.ArrayList;
import java.util.Arrays;

public class BeckerbauerBookAnalytics extends BookAnalytics{
	private String nextLine;
	private String[] withoutSpaces;
	private String withoutPunctuation;
	private int vowelCount;
	private int consonantCount;
	private int characterCount;
	private int wordCount;
	private int sentenceCount;
	private double averageWordLength; 
	private char mostFrequentLetter;
	private String mostFrequentSubject;
	private String mostFrequentWord;
	private double averageWordsPerSentence;
	private int numberOfDifferentWords;
	private String alphabetizeAllByChar;
	private String alphabetizeAllByWord;
	public BeckerbauerBookAnalytics(String aTextFile, String outputFile) {
		super(aTextFile, outputFile);
	}
	@Override
	public String mostFrequentWord() {
		int max = 0;
		int wordCount = 0;
		for(String word : withoutSpaces) {
			for(String wordy : withoutSpaces) {
				if(wordy.equals(word)) {
					wordCount++;
				}
			}
			if(wordCount > max) {
				max = wordCount;
				mostFrequentWord = word;
			}
			wordCount = 0;
		}
		return mostFrequentWord;
	}
	@Override
	public String mostFrequentWordWithCapitalFirstLetter() { 
		int max = 0;
		int subjectCount = 0;
		for(String word : withoutSpaces) {
			if(word.length() > 0) {
				char firstLetter = word.charAt(0);
				if(Character.isUpperCase(firstLetter)) {
					for(String wordy : withoutSpaces) {
						if(wordy.equals(word)) {
							subjectCount++;
						}
					}
					if(subjectCount > max) {
						max = subjectCount;
						mostFrequentSubject = word;
					}
					subjectCount = 0;
				}
			}
		}
		return mostFrequentSubject;
	}
	@Override
	public char mostFrequentLetter() {
		int max = 0; 
		int letterCount = 0; 
		String newNextLine = nextLine.toLowerCase();
		for(int i = 0; i < newNextLine.length(); i++) {
			char currentLetter = newNextLine.charAt(i);
			for(int q = 0; q < newNextLine.length(); q++) {
				if(newNextLine.charAt(q) == currentLetter) {
					letterCount++;
				}
			}
			if((letterCount > max) && (newNextLine.charAt(i) != ' ')) {
				max = letterCount;
				mostFrequentLetter = newNextLine.charAt(i);
			}
		newNextLine = newNextLine.replaceAll(currentLetter + "", "");
		letterCount = 0;
		}
		return mostFrequentLetter;
	}
	@Override
	public int wordCount() {
		wordCount = wordCount + withoutSpaces.length;
		return wordCount;
	}
	@Override
	public int characterCount() { 
		this.characterCount = consonantCount + vowelCount;
		return characterCount;
	}
	@Override
	public int vowelCount() {
		String newNextLine = nextLine.toLowerCase();
		for(int i = 0; i < newNextLine.length(); i++) {
			if(newNextLine.charAt(i) == 'a' || newNextLine.charAt(i) == 'e' || newNextLine.charAt(i) == 'i' || newNextLine.charAt(i) == 'o' || newNextLine.charAt(i) == 'u' || newNextLine.charAt(i) == 'y') {
				vowelCount++;
			}
		}
		return vowelCount;
	}
	@Override
	public int consonantCount() {
		String newNextLine = nextLine.toLowerCase();
		for(int i = 0; i < newNextLine.length(); i++) {
			if(!(newNextLine.charAt(i) == 'a' || newNextLine.charAt(i) == 'e' || newNextLine.charAt(i) == 'i' || newNextLine.charAt(i) == 'o' || newNextLine.charAt(i) == 'u' || newNextLine.charAt(i) == 'y')) {
				consonantCount++;
			}
		}
		return consonantCount;
	}
	@Override
	public double averageWordLength() {
		double totalLength = 0;
		for(String word : withoutSpaces) {
			totalLength += word.length();
		}
		averageWordLength = totalLength/wordCount;
		return this.averageWordLength;
	}
	@Override
	public int sentenceCount() {
		String newNextLine = nextLine.toLowerCase();
		for(int i = 0; i < newNextLine.length(); i++) {
			if(newNextLine.charAt(i) == '.') {
				if(!((newNextLine.charAt(i - 1) == 'r' && newNextLine.charAt(i - 2) == 'M') || (newNextLine.charAt(i - 1) == 's' && newNextLine.charAt(i - 2) == 'r' && newNextLine.charAt(i - 3) == 'M') || (newNextLine.charAt(i - 1) == 's' && newNextLine.charAt(i - 2) == 'M') || (newNextLine.charAt(i - 1) == 'r' && newNextLine.charAt(i - 2) == 'D') || (newNextLine.charAt(i - 1) == 'r' && newNextLine.charAt(i - 2) == 'J') || (newNextLine.charAt(i - 1) == 'r' && newNextLine.charAt(i - 2) == 'S'))) {
					if(!(newNextLine.charAt(i - 1) == '.')){
						sentenceCount++;
					}
				}
			}
		}
		return sentenceCount;
	}
	@Override
	public double averageWordsPerSentance() {
		averageWordsPerSentence = wordCount/sentenceCount;
		return averageWordsPerSentence;
	}
	@Override
	public int numberOfDifferentWords() { 
		String newNextLine = nextLine.toLowerCase();
		ArrayList<String> differentWords = new ArrayList<String>(Arrays.asList(withoutSpaces));
		if(newNextLine.length() == 0) {
			numberOfDifferentWords = 0;
		}
		for(int i = 0; i < differentWords.size(); i++) {
			for(int q = i + 1; q < differentWords.size() && i < differentWords.size(); q++) {
				if(differentWords.get(i).equals(differentWords.get(q))) {
					differentWords.remove(q);
					q--;
				}
			}
		}
		numberOfDifferentWords = differentWords.size();
		return numberOfDifferentWords;
	}
	@Override
	public String alphabetizeAllByChar() {
		String newNextLine = withoutPunctuation;
		newNextLine = newNextLine.replaceAll(" ", "");
		String[] characters = newNextLine.split("");
		Arrays.sort(characters);
		for(int i = 0; i < characters.length; i++) {
			alphabetizeAllByChar += characters[i];
		}
		return alphabetizeAllByChar;
	}
	@Override
	public String alphabetizeAllByWord() {
		String[] words = withoutSpaces;
		Arrays.sort(words);
		for(int i = 0; i < words.length; i++) {
			alphabetizeAllByWord += words[i];
		}
		return alphabetizeAllByWord;
	}
	@Override
	public String replaceWordAndPreserveCase(String aLine, String wordOne, String wordTwo) {
		String newNextLine = aLine;
		String firstWordUpperCase = wordOne.replaceFirst(wordOne.charAt(0) + "", wordOne.toUpperCase().charAt(0) + "");
		String secondWordUpperCase = wordTwo.replaceFirst(wordTwo.charAt(0) + "", wordTwo.toUpperCase().charAt(0) + "");
		newNextLine = newNextLine.replaceAll(firstWordUpperCase, secondWordUpperCase);
		String firstWordLowerCase = wordOne.replaceFirst(wordOne.charAt(0) + "", wordOne.toLowerCase().charAt(0) + "");
		String secondWordLowerCase = wordTwo.replaceFirst(wordTwo.charAt(0) + "", wordTwo.toLowerCase().charAt(0) + "");
		newNextLine = newNextLine.replaceAll(firstWordLowerCase, secondWordLowerCase);
		return newNextLine;
	}
	@Override
	public String toString() {
		String end = "End";
		System.out.println();
		System.out.println("\nMost Frequent Word : " + mostFrequentWord );
		System.out.println("\nMost Frequent Letter: " + mostFrequentLetter);
		System.out.println("\nMost Likely Subject: " + mostFrequentSubject);
		System.out.println("\nWord Count: " + wordCount); 
		System.out.println("\nCharacter Count: " + characterCount);
		System.out.println("\nVowel Count: " + vowelCount);
		System.out.println("\nConsonant Count: " + consonantCount);
		System.out.println("\nSentence Count: " + sentenceCount);
		System.out.println("\nAverage Word Length: " + averageWordLength);
		System.out.println("\nAverage Words Per Sentence: " + averageWordsPerSentence);
		System.out.println("\nNumber of Different Words Used: " + numberOfDifferentWords);
		return end;
	}
	@Override
	public void replaceWordsAndWriteToFile(String[] wordsToBeReplaced, String[] wordsToReplaceWith) {
		String newNextLine = nextLine;
		for(int i = 0; i < wordsToBeReplaced.length && i < wordsToReplaceWith.length; i++) {
			newNextLine = replaceWordAndPreserveCase(newNextLine, wordsToBeReplaced[i], wordsToReplaceWith[i]);
			write.println(newNextLine);
		}
	}
	public void runFunctions() {
		mostFrequentWord();
		mostFrequentLetter();
		mostFrequentWordWithCapitalFirstLetter();
		wordCount();
		characterCount();
		vowelCount();
		consonantCount();
		sentenceCount();
		averageWordLength();
		averageWordsPerSentance();
		numberOfDifferentWords();
	}
	public void printFile() {
		String allText = "";
		while(scan.hasNextLine()) {
			String temp = scan.nextLine();
			this.nextLine = temp;
			removePunctuation();
			removeSpaces();
			runFunctions();
			allText += temp + "\n";
		}
		toString();
		System.out.println(allText);
		write.write(allText); //use a textWriter to output a string to a new file
		write.flush(); //clears the writer for the next use
	}
	public String removePunctuation() {
		withoutPunctuation = nextLine;
		withoutPunctuation = withoutPunctuation.replaceAll("\\p{Punct}", "");
		return withoutPunctuation;
	}
	public String[] removeSpaces() {
		withoutSpaces = withoutPunctuation.split(" ");
		return withoutSpaces;
	}
	private String nukeLetterA(String nextLine) {
//		String anOutput = "";
//		for(int i = 0; i < nextLine.length(); i++) {
//			if(nextLine.charAt(i) == 'a') {
//				anOutput += "*"; //.replace is faster
//			}
//			else {
//				anOutput += nextLine.charAt(i);
//			}
//		}
//		return anOutput;
		return nextLine.replace("a", "*");
	}
}
