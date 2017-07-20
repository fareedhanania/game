package game;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Hangman {
	
	static String randomWord = "Fareed Hanania";
	static String[] word = new String[50];
	static String[] tempWord = new String[50];
	static int lives = 3;
	static ArrayList<String> lettersGuessed;
	static String[] letter;

	public static void main(String[] args) {
		tokenWord(randomWord);
		createWord(word);
		System.out.println(Arrays.toString(word));
		System.out.println(Arrays.toString(tempWord));
	}
	
	public static void createWord(String[] createWord) { //changes random word to all "-"
		
		for(int i = 0; i < createWord.length; i++) {
			if(createWord[i] == (" ")) {
				tempWord[i] = (" ");
			} else {
				tempWord[i] = ("-");
			}
		}//END for
		
	}//END createWord
	
	public static void tokenWord(String random) {
		for(int i = 0; i < random.length(); i++) {
			if(i+1 > random.length()) {
				word[i] = random.substring(random.length() - 1);
			} else {
			String temp = random.substring(i, i+1);
			word[i] = temp;
			}
		}//END for 
	}//END tokenWord

}
