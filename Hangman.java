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
	static int lives = 3;
	
	//dictionary
	static String[] nouns = {"potato","computer","animal","phone","house","dog","brownies","television"};
	static String[] verbs = {"jumps", "writes", "drives", "turns", "eats", "hugs", "wraps", "swings"};
	static String[] articles = {"a","the","an"};
	static String[] vowels = {"a","e","i","o","u"};
	static String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "y", "z"};
	
	public static void main(String[] args) {
		System.out.println("Welcome to hangman! You have 3 lives to guess the phrase right.");
		System.out.println("Get two or more letters in a row to start a combo!\n");
		
		startGame();
		setUpBoard();
		
		while(lettersLeftCounter > 0 && lives > 0) {
			System.out.print("\n\nGuess a letter: ");
			String playerLetterGuessed;
			playerLetterGuessed = scanner.nextLine();
			while(guessALetter(playerLetterGuessed) == 0) {
				System.out.print("Guess Again: ");
				playerLetterGuessed = scanner.nextLine();
				System.out.println();
			}
			System.out.println();
			showStatus();
		}//END of WHILE
		
		System.out.println("You lose!");
		
	}
	
	public static void startGame() {
		generateRandomWord();
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
	
	public static int guessALetter(String letter) {
		if(letter.equals("")) {
			System.out.println("Please enter a valid letter.");
			return 0;
		}else if(!Arrays.asList(alphabet).contains(letter)) {
			System.out.println("Please enter a valid letter.");
			return 0;
		} else if(lettersGuessed.size() < 1) { //if no letters were guessed yet, add first letter guessed
			lettersGuessed.add(letter);
			updateHiddenWord(letter);
			return 1;
		} else { //else check the currently guessed letter against the already guessed letters
			for(int i = 0; i < lettersGuessed.size();i++) {
				if(lettersGuessed.get(i).equalsIgnoreCase(letter)) { //if the letterGuessed.get(i) = letter guessed, then guess again
					System.out.println("You have already guessed the letter " 
								+ letter + ".");
					return 0;
				} 
			}//END of FOR 
			lettersGuessed.add(letter);
			updateHiddenWord(letter);
			return 1;
			
		}//END of IF to check if letter was already guessed

	}//END of guessALetter
	
	public static void updateHiddenWord(String correctLetter) {
		int counter = 0;
		char c = correctLetter.charAt(0); //covert letter guessed to a char
		for(int j = 0; j < word.length;j++) { //cycle through the "-" word 
			if(c == randomWord.charAt(j)) { //if the letter guessed equals the letter at index(j) of random word...
				word[j] = correctLetter; //...then index(j) of the "-" word equals the letter guessed
				counter++;
			}
		}//END of FOR
		
		if(counter == 1) {
			System.out.printf("There is %d %s",counter,correctLetter);
			lettersLeftCounter--;
		} else if(counter > 1) {
			System.out.printf("There are %d %s's",counter,correctLetter);
			lettersLeftCounter -= counter;
		} else {
			System.out.printf("There are no %s's",correctLetter);
			lives--;
		}
		
	}//END of updateHiddenWord
	
	public static void showStatus() {
		System.out.print("Phrase: ");
		for (String element : word) {
			System.out.print(element);
		}
		System.out.println();
		System.out.print("Guessed Letters: ");
		for (String element : lettersGuessed) {
			System.out.print(element);
		}
		
		System.out.print(" | Lives remaining: " + lives);
	}
	

}
