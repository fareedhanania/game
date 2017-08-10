package game;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Hangman {
	
	//imports
	static Scanner scanner = new Scanner(System.in);
	
	//word attributes
	static String randomWord = "";
	static String[] word;
	static ArrayList<String> lettersGuessed = new ArrayList<String>();
	
	//status of player
	static int lettersLeftCounter;
	static int lives;
	
	//dictionary
	static String[] nouns = {"potato","computer","animal","phone","house","dog","brownies","television"};
	static String[] verbs = {"jumps", "writes", "drives", "turns", "eats", "hugs", "wraps", "swings"};
	static String[] articles = {"a","the","an"};
	static String[] vowels = {"a","e","i","o","u"};
	
	public static void main(String[] args) {
		System.out.println("Welcome to hangman! You have 3 lives to guess the phrase right.");
		System.out.println("Get two or more letters in a row to start a combo!\n");
		
		startGame();
		setUpBoard();
		
		while(lettersLeftCounter > 0 || lives > 0) {
			System.out.print("\nGuess a letter: ");
			String playerLetterGuessed;
			
			//check if letter has already been guessed
			boolean guessAgain = true;
			do { //BROKEN - potentially change
				playerLetterGuessed = scanner.nextLine();
				if(lettersGuessed.size() == 0) {
					guessALetter(playerLetterGuessed);
				} else {
					for(String element : lettersGuessed) {
						if(element.equalsIgnoreCase(playerLetterGuessed)) {
							System.out.println("You have already guessed the letter " 
										+ playerLetterGuessed + ". " + "Guess again.\n");
							break;
						} else {
							guessAgain = false;
						}
					} //END of FOR
				}
				
			}while(guessAgain);
			
			//if letter is a valid guess, letter is added and checked against hidden word
			guessALetter(playerLetterGuessed);
		}
		
	}
	
	public static void startGame() {
		generateRandomWord();
		System.out.println(randomWord);
		createWordBlanks();
	}
	
	public static void generateRandomWord() {
		int firstNounNum = (int)(Math.random()*nouns.length);
		int verbNum = (int)(Math.random()*verbs.length);
		int secondNounNum = (int)(Math.random()*nouns.length);
		int articleNum;
		
		String firstNounString = nouns[firstNounNum];
		String verbString = verbs[verbNum];
		String secondNounString = nouns[secondNounNum];
		String articleString = ""; //defined below
		
		boolean checkArticle = false;
		
		for(String element : vowels) {
			if(element.equalsIgnoreCase(secondNounString.substring(0, 1))) {
				checkArticle = true;
				break;
			}
		}
		
		if(checkArticle) {
			articleString = articles[2];
		} else {
			articleNum = (int)(Math.random()*(articles.length-1));
			articleString = articles[articleNum];
		}
		
		randomWord = firstNounString + " " + verbString + " " + articleString + " " + secondNounString;
	}//END of generateRandomWord
	
	
	public static void createWordBlanks() { //changes random word to all "-"
		
		int wordSize = randomWord.length();
		word = new String[wordSize];

		for(int i = 0; i < randomWord.length(); i++) {
			char c = randomWord.charAt(i);
			
			if(c != ' ') {
				word[i] = "-";
				lettersLeftCounter++;
			}
			else {
				word[i] = " ";
			}
		}//END of FOR in createWordBlanks
		
	}//END createWordBlanks
	
	public static void setUpBoard() {
		System.out.println("The phrase is: \n");
		
		for(String element : word) {
			System.out.print(element);
		}
	}//END of setUpBoard
	
	public static void guessALetter(String letter) {
		lettersGuessed.add(letter);
		for (String element : lettersGuessed) {
			System.out.print(element);
		}
	}
	

}
