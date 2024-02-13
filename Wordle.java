import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Wordle {
	protected File file;
	protected Scanner scan;
	protected Scanner userScan;
	protected PrintWriter write; 
	protected String[] words;
	protected String allText;
	
	public Wordle(String aTextFile, String outputFile) {
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
	}
	
	/**
	 * picks a random word from the available words
	 * @return a String word for the game
	 */
	public String chooseWord() {
		int index = (int)(Math.random() * words.length);
		return words[index]; 
	}
	
	/**
	 * Directs the user to guess a word <br>
	 * Validates that the guess is in the unguessed words<br>
	 * Removes the guess from the unguessed words
	 * @return String user guess
	 */
	public String getGuess() { 
		while(true) {
			System.out.println("Type your guess.");
			String guess = userScan.nextLine().toUpperCase(); 
			if(containsGuess(guess)) {
				return guess;
			}
			System.out.println("Not a valid Guess.");
		}
	}
	
	/**
	 * Function to determine if the String guess is a valid word.
	 * @param guess A user guess String
	 * @return True if the String is found in the unguessed words
	 */
	private boolean containsGuess(String guess) {
		for(int i = 0; i < words.length; i++) {
			String word = words[i]; 
			if(word.equals(guess)) {
				words[i] = "NULL";
				return true; 
			}
		}
		return false;
	}

	/**
	 * This function runs the game of wordle <br>
	 * 
	 * @return The count of the number of guesses <br>
	 *         count is returned as an int <br>
	 *         I was going to do something else with the count later and abandoned it.
	 */
	public int playGame() {
		int guessCount = 0; 
		String mysteryWord = chooseWord();
		ArrayList<String> wordsGuessed = new ArrayList<>();
		ArrayList<String> letters = getAllLetters(); 
		while(guessCount < 6) {
			String guess = getGuess(); 
			String output = processGuess(guess, mysteryWord);
			wordsGuessed.add(guess); 
			letters = removeLetters(letters, guess, mysteryWord);
			printGuesses(wordsGuessed); 
			System.out.println("Guess: " + guess);
			System.out.println("Result: " + output);
			guessCount++; 
			if(output.equals(mysteryWord)) {
				System.out.println("You Win!");
				System.out.println("Count: " + guessCount);
				break; 
			}
			System.out.println(letters);
		}
		System.out.println("Mystery Word: " + mysteryWord);
		return guessCount; 
	}

	/**
	 * Removes letters from the ArrayList of remaining characters
	 * @param letters unguessed letters
	 * @param guess Current user String guess
	 * @param mysteryWord Current String Wordle puzzle
	 * @return Shortened ArrayList of remaining letters
	 */
	private ArrayList<String> removeLetters(ArrayList<String> letters, String guess, String mysteryWord) {
		for(int i = 0; i < guess.length(); i++) {
			String current = guess.charAt(i) + ""; 
			if(letters.contains(current) && !(mysteryWord.contains(current))) {
				letters.remove(current); 
			}
			else if(letters.contains(current)) {
				letters.set(letters.indexOf(current), current.toLowerCase()); 
			}
		}
		return letters;
	}

	/**
	 * syso the user guesses so far
	 * @param wordsGuessed all current guesses of the user
	 */
	private void printGuesses(ArrayList<String> wordsGuessed) {
		for(int i = 0; i < wordsGuessed.size(); i++) {
			System.out.println("#" + (i+1) + ": " + wordsGuessed.get(i));
		}
		
	}

	/**
	 * Outputs the processed String guess as compared to the mystery word.
	 * @param guess User String guess
	 * @param mysteryWord Current mystery String Wordle
	 * @return String where capital letters are letters in position<br>
	 * 			lowercase letters are character out of position but in the mystery word<br>
	 *          * are letters not found in the mystery word. 
	 */
	private String processGuess(String guess, String mysteryWord) {
		String output = "";
		for(int i = 0 ; i < guess.length(); i++) {
				if(guess.charAt(i) == mysteryWord.charAt(i)) {
					output += guess.charAt(i); 
				}
				else if(mysteryWord.contains(guess.charAt(i) +"")) {
					String letter = guess.charAt(i) + "";
					output += letter.toLowerCase(); 
				}
				else {
					output+= "*";
				}
		}
		return output;
	}

	/**
	 * Builds a default ArrayList of characters 
	 * @return String ArrayList containing the capital letters A-Z
	 */
	private ArrayList<String> getAllLetters() {
		String[] letters = {"A","B","C", "D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		return new ArrayList<String>(Arrays.asList(letters));
	}
	

}
