import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

public class game {
	static int numOfDecks;
	static int numOfPlayers;
	static int money = 100;
	static boolean endRound = true;
	static boolean playerBust;
	static Scanner scanner = new Scanner(System.in);
	static HashMap<String, Integer> cardValues = new HashMap<String, Integer>();
	static ArrayList<String> deckOfCards= new ArrayList<String>(); 	
	static ArrayList<String> dealerHand = new ArrayList<String>();
	static String dealerHiddenCard;
	static ArrayList<String> playerHand = new ArrayList<String>();
	static int playerValue;
	static int dealerValue;
	static int playerBet;
	
	public static void main(String[] args){

		startGame();
		createHash();
		System.out.println("\nLet's get started.\n");
		
		while(money > 0) {
			newRound();
			System.out.print("How much money do you want to bet? Total: " + "$" + money);
			playerBet = scanner.nextInt();
			
			while(playerBet <= 0) {
				System.out.println("You must bet more than $0. Bet again.");
				playerBet = scanner.nextInt();
			}
			
			System.out.println("You have bet " + "$" + playerBet + ". Good luck!\n");
			
			if(dealCards() == 0) {
				System.out.println("Player's Turn");
				for(int i = 0; i < 13;i++) {
					System.out.print("-");
				}
				System.out.println();
				int playerChoice = playerTurn();
				while(playerChoice == 0 && playerValue != 21) {
					if(playerValue > 21) {
						System.out.println("\nYou have busted and lost " + "$" + playerBet + ".");
						money -= playerBet;
						System.out.println("Total Money: " + "$" + money + ".\n");
						playerChoice = -1;
						playerBust = false;
					} //END of IF
					else{
						playerChoice = playerTurn();
					} //END of ELSE
					
				}//END of PlayerChoice WHILE
				
				if(playerValue == 21) {
					System.out.println("Player value is 21. Good job!");
				}
				
				if(playerBust) {
					System.out.println("Dealer's Turn");
					for(int i = 0; i < 13;i++) {
						System.out.print("-");
					}
					System.out.println();
					dealerTurn();
					if(dealerValue > 21) {
						System.out.println("Dealer Busted. You win " + "$" + (2*playerBet) +"!\n");
						money += (2*playerBet);
						} //END of 1st IF			
					else if(playerValue > dealerValue){
						System.out.println("You win " + "$" + (2*playerBet) +"!\n");
						money += (2*playerBet);
						}
					else if(playerValue == dealerValue) {
						System.out.println("It's a push! You don't lose any money.\n");
						}
					else {
						System.out.println("You lose " + "$" + playerBet +".\n");
						money -= playerBet;
					}
				}//END of playerBust IF
				
			} //END of dealCards IF
			
			
		}//END of STARTING WHILE
		
		System.out.println("You lost all your money! Get a job!");
		
	} //Main END
	
	public static void startGame(){
		System.out.print("Welcome to the wonderful game of BlackJack! How many players?");
		numOfPlayers = scanner.nextInt();
	
		while(numOfPlayers<=0) {
			System.out.println("You need at least 1 player to play. How many players?");
			numOfPlayers = scanner.nextInt();
		}
		
		if(numOfPlayers == 1){
			System.out.println("This game will have " + numOfPlayers + " player.");
		}
		else {
			System.out.println("This game will have " + numOfPlayers + " players.");
		}

		System.out.print("\nHow many decks do you want to play with?");
		numOfDecks = scanner.nextInt();
		while(numOfDecks<=0) {
			System.out.print("You need to play with at least 1 deck. How many decks?");
			numOfDecks = scanner.nextInt();
		}
		
	} //startGame END
	
	public static void newRound() {
		endRound = true;
		playerBust = true;
		createDeck();
		shuffle(deckOfCards);
		playerHand.clear();
		playerValue = 0;
		dealerHand.clear();
		dealerValue = 0;
	}
	
	public static void createDeck(){
		//int sizeOfArray = 52*numOfDecks;
		int multDecks = numOfDecks;
		
		while(multDecks > 0) {
			//Clubs
			for(int c = 0; c < 9; c++) {
				deckOfCards.add(c+2+"C");
			}//END of FOR
			
			deckOfCards.add("JC");
			deckOfCards.add("QC");
			deckOfCards.add("KC");
			deckOfCards.add("AC");
			
			//Diamonds
			for(int c = 0; c < 9; c++) {
				deckOfCards.add(c+2+"D");
			}//END of FOR
			

			deckOfCards.add("JD");
			deckOfCards.add("QD");
			deckOfCards.add("KD");
			deckOfCards.add("AD");
			
			//Spades
			for(int c = 0; c < 9; c++) {
				deckOfCards.add(c+2+"S");
			}//END of FOR
			
			deckOfCards.add("JS");
			deckOfCards.add("QS");
			deckOfCards.add("KS");
			deckOfCards.add("AS");

			
			//Hearts
			for(int c = 0; c < 9; c++) {
				deckOfCards.add(c+2+"H");
			}//END of FOR
			
			deckOfCards.add("JH");
			deckOfCards.add("QH");
			deckOfCards.add("KH");
			deckOfCards.add("AH");

			multDecks--;
		}//END of WHILE
			
		//System.out.println("Your deck contains " + deck.length + " cards.");

	} //createDeck END
	
	public static void createHash() { //Creates HashMap for the values of each card
		//Clubs
		cardValues.put("2C", 2);
		cardValues.put("3C", 3);
		cardValues.put("4C", 4);
		cardValues.put("5C", 5);
		cardValues.put("6C", 6);
		cardValues.put("7C", 7);
		cardValues.put("8C", 8);
		cardValues.put("9C", 9);
		cardValues.put("10C", 10);
		cardValues.put("JC", 10);
		cardValues.put("QC", 10);
		cardValues.put("KC", 10);
		cardValues.put("AC", 11);
		//Diamonds
		cardValues.put("2D", 2);
		cardValues.put("3D", 3);
		cardValues.put("4D", 4);
		cardValues.put("5D", 5);
		cardValues.put("6D", 6);
		cardValues.put("7D", 7);
		cardValues.put("8D", 8);
		cardValues.put("9D", 9);
		cardValues.put("10D", 10);
		cardValues.put("JD", 10);
		cardValues.put("QD", 10);
		cardValues.put("KD", 10);
		cardValues.put("AD", 11);
		//Spades
		cardValues.put("2S", 2);
		cardValues.put("3S", 3);
		cardValues.put("4S", 4);
		cardValues.put("5S", 5);
		cardValues.put("6S", 6);
		cardValues.put("7S", 7);
		cardValues.put("8S", 8);
		cardValues.put("9S", 9);
		cardValues.put("10S", 10);
		cardValues.put("JS", 10);
		cardValues.put("QS", 10);
		cardValues.put("KS", 10);
		cardValues.put("AS", 11);
		//Hearts
		cardValues.put("2H", 2);
		cardValues.put("3H", 3);
		cardValues.put("4H", 4);
		cardValues.put("5H", 5);
		cardValues.put("6H", 6);
		cardValues.put("7H", 7);
		cardValues.put("8H", 8);
		cardValues.put("9H", 9);
		cardValues.put("10H", 10);
		cardValues.put("JH", 10);
		cardValues.put("QH", 10);
		cardValues.put("KH", 10);
		cardValues.put("AH", 11);
	} //HashMap END
	
	public static void shuffle(ArrayList<String> deck){ //Takes in ArrayList to shuffle
		
		Collections.shuffle(deck);
		
		/*for(String i : deck) {
			System.out.print("|" + i);
		}*/
		
		//System.out.println("|");
	
	} //shuffle END
	
	public static int dealCards() {
		
		System.out.println("Initial deal");
		for(int i = 0; i < 12;i++) {
			System.out.print("-");
		}
		System.out.println();
		for(int i = 0; i < 2;i++){ //FOR to deal two cards to dealer
			dealerHand.add(deckOfCards.get(i));
			deckOfCards.remove(i);
		}
		
		String temp = dealerHand.get(1); //store dealer's hidden card
		dealerHand.set(1,"--");
		dealerHiddenCard = temp;
		
		for(int i = 0; i < 2;i++) { //FOR to deal two cards to player
			playerHand.add(deckOfCards.get(i));
			deckOfCards.remove(i);
		}
		
		System.out.println("Dealer Hand: " + "|" + dealerHand.get(0) + "|" + "|" + dealerHand.get(1) + "|");
		System.out.println("Player Hand: " + "|" + playerHand.get(0) + "|" + "|" + playerHand.get(1) + "|");
		System.out.println();
		
		
		playerValue = calculateHand(playerHand, playerValue);
		if(playerValue == 21) {
			System.out.println("Winner Winner Chicken Dinner! You win +" + "$" + (playerBet*1.5) + "!");
			return 1;
		}
		return 0;
	}
	
	public static void splitCards(){ //NOT DONE - must figure out how to create an ArrayList if player decides to split hand
		
		System.out.println("Your cards are the same. Would you like to split (Y or N)?");
		String playerChoice = scanner.nextLine();
		
		while(!playerChoice.equalsIgnoreCase("Y") && !playerChoice.equalsIgnoreCase("N")){
			System.out.println("What? Would you like to split (Y or N)?");
			playerChoice = scanner.nextLine();
		}
		
		if(playerChoice.equalsIgnoreCase("Y")){
			
			ArrayList<String> x = new ArrayList<String>();
		}
		
		
	}//END of splitCards
	
	public static boolean compareHand(ArrayList<String> hand){ //compares two cards
		
		if(hand.get(0).substring(0).equalsIgnoreCase(hand.get(1).substring(0))){
			return true;
		} else {
			return false;
		}
		
	} //END of compareHand
	
	public static int playerTurn() {
		String playerChoice;
		
		playerValue = calculateHand(playerHand, playerValue);
		
		System.out.println("Player total value: " + playerValue);
		
		System.out.println("Would you like to Hit or Stay?");
		playerChoice = scanner.next();
		playerChoice = playerChoice.toLowerCase();
		
		while(!playerChoice.equals("hit") && !playerChoice.equals("stay")) {
			System.out.println("You must hit or stay. Try again.");
			playerChoice = scanner.next();
			playerChoice = playerChoice.toLowerCase();
		}
		
		if(playerChoice.equals("hit")) {
			playerHand.add(deckOfCards.get(0));
			deckOfCards.remove(0);
			playerValue = calculateHand(playerHand, playerValue);
			System.out.print("Dealer Hand: ");
			for(int h = 0; h < dealerHand.size(); h++) {
				System.out.print("|" + dealerHand.get(h) + "|");
			}
			System.out.println();
			System.out.print("Player Hand: ");
			for(int h = 0; h < playerHand.size(); h++) {
				System.out.print("|" + playerHand.get(h) + "|");
			}
			System.out.println("\nPlayer Total value: " + playerValue);
			return 0;
		}//IF END
		
		else {
			System.out.println("Dealer's Turn");
			for(int i = 0; i < 14;i++) {
				System.out.print("-");
			}
			System.out.println();
			return -1;
		} //ELSE END
	} //playerTurn END
	
	public static void dealerTurn() {
		dealerHand.set(1, dealerHiddenCard);
		
		System.out.print("Dealer Hand: ");
		for(int h = 0; h < dealerHand.size(); h++) {
			System.out.print("|" + dealerHand.get(h) + "|");
		}
		System.out.println();
		System.out.print("Player Hand: ");
		for(int h = 0; h < playerHand.size(); h++) {
			System.out.print("|" + playerHand.get(h) + "|");
		}
		System.out.println();
		dealerValue = calculateHand(dealerHand, dealerValue);
		
		System.out.println("Dealer total value: " + dealerValue);
		
		while(dealerValue <= 17) {
			dealerHand.add(deckOfCards.get(0));
			deckOfCards.remove(0);
			
			dealerValue = calculateHand(dealerHand, dealerValue);
			
			System.out.print("\nDealer Hand: ");
			for(int h = 0; h < dealerHand.size(); h++) {
				System.out.print("|" + dealerHand.get(h) + "|");
			}
			System.out.println();
			System.out.print("Player Hand: ");
			for(int h = 0; h < playerHand.size(); h++) {
				System.out.print("|" + playerHand.get(h) + "|");
			}
			System.out.println("\nDealer total value: " + dealerValue + "\n");
		}//WHILE END
		

	} //dealerTurn END
	
	public static int calculateHand(ArrayList<String> hand, int value) {
		value = 0;  
		int numOfAces = 0;
		for(int p = 0; p < hand.size(); p++) {
			String card = hand.get(p);
			char a = card.charAt(0);
			if(a == 'A') {
				numOfAces++;
				value += cardValues.get(hand.get(p));
				if(value > 21) {
					value -= 10;
					numOfAces--;
				}
			}
			else {
				value += cardValues.get(hand.get(p));
				while(value > 21 && numOfAces > 0) {
					value -= 10;
					numOfAces--;
				}
			}
			
		}
		return value;
	} //END of calculateHand
	
}