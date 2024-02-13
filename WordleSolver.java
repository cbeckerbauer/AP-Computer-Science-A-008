import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public abstract class WordleSolver {
	protected File file;
	protected Scanner scan;
	protected Scanner userScan;
	protected PrintWriter write; 
	protected String[] words;
	protected String allText;
	protected ArrayList<String> possibleWords;
	
	public WordleSolver(String aTextFile, String outputFile) {
		userScan = new Scanner(System.in); 
		file = new File(aTextFile);
		try {
			scan = new Scanner(file);
			write = new PrintWriter(outputFile, "UTF-8");
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		allText = "";
		while(scan.hasNext()) {
			allText += scan.nextLine().toUpperCase() + " ";
		}
		words = allText.split(" "); 
		possibleWords = new ArrayList<>(Arrays.asList(words));
	}
	
	/**
	 * Ask the user what letters have been guessed that are not in the Wordle<br>
	 * Remove the words from possibleWords that contain letters not in the Wordle 
	 */
	public abstract void getLettersNotInWordle();
	
	/**
	 * Ask the user what letters have been guessed that are in the Wordle<br>
	 * Remove the words from possibleWords that do not contain letters in the Wordle 
	 */
	public abstract void getLettersInWordle();
	
	/**
	 * For each index in the Wordle. <br>
	 * Ask the user for letters found at that index or to type * for unknown letters<br>
	 * Remove all words from possibleWords that do not have known letters as known indexes. 
	 */
	public abstract void whatLettersAreKnown();
	
	/**
	 * syso all words that remain in possibleWords
	 */
	public abstract void printRemainingWords(); 
	
	/**
	 * Calls all methods necessary to run the Wordle Solver. 
	 */
	public abstract void runWordleSolver();
	
	/*
	 * Check your Wordle solver against three Wordles generated with the Wordle class.,
	 */
}
