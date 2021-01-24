import java.util.Scanner;
/**
 * This class creates an object to act as the person in the game 
 * this class does all the scanning and user interaction
 * 
 * @author Samuel Swanson
 *
 */
public class Player {
	
	/**
	 * scanner used to get user inputs
	 */
	private Scanner input = new Scanner(System.in);
	/**
	 * Stores the name of the player
	 */
	private String name;
	/**
	 * This variable stores the array length(size) 
	 */
	private int diceSize;
	/**
	 * Constructor used to store name and create specific player
	 * @param name - the name passed in
	 */
	public Player(String name) {
		this.name = name;
	}
	/**
	 * This method prints the amount of players that are playing
	 * @param num - the amount of players
	 */
	public void receiveNumberOfPlayers(int num) {
		System.out.println(getName() + " the number of players playing is " + num + "\n");
	}
	/**
	 * This method prints how many dice are in the game, how many
	 * sides each die have, and instantiates class variable diceSize
	 * 
	 * @param info - the array passed in declaring the amount of dice and their sides
	 */
	public void receiveDiceInfo(int[]info) {
		diceSize = info.length;
		System.out.println(getName() + " there are " + info.length + " dice in the game");
		for(int i = 0; i < info.length; i++) {
			System.out.println("The number of sides on dice number " + (i + 1) +" is " + info[i]);
		}
		System.out.print("\n");
	}
	/**
	 * Asks if the player wants to guess where the non repeating die are
	 * 
	 * @return - returns true of false depending on if they want to guess
	 */
	public boolean wantsToGuess() {
		System.out.print("Do you want to guess where the non repeating die are? Enter 1 for yes and 0 for no: ");
		int answer = input.nextInt();
		if(answer == 1) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Asks the player for their guesses
	 * 
	 * @return - returns an array of there guesses
	 */
	public int[] guess() {
		int[] guess = new int[2];//the array that stores the guesses, will always be size two because only two nrd in the game
		System.out.print("Enter your guess for id #1: ");
		int guessForPosition1 = input.nextInt();//is their first guessed position
		guess[0] = guessForPosition1;
		
		int guessForPosition2;
		do {
		System.out.print("What do you want to guess for id #2, it can not equal your previous guess: ");
		guessForPosition2 = input.nextInt();//is their second guessed position
		}while(guessForPosition1 == guessForPosition2);
		guess[1] = guessForPosition2;
		System.out.print("\n");
		return guess;
	}
	/**
	 * gets the roll results to the player
	 * 
	 * @param results - is an array of the results of the roll
	 */
	public void receiveRollResults(int[] results) {
		System.out.print(getName() + " the rolled values are ["); 
		for(int i = 0; i < results.length; i++) {
			System.out.print(results[i]);
			if(i<results.length-1) {
				System.out.print(",");
			}
		}
		System.out.println("]\n");
	}
	/**
	 * if the player chose to re-roll this method will ask for a 1 or 0 to find out which
	 * positions the player would like to re-roll
	 * 
	 * @return - returns an array of the same length of how many die are in the game and
	 * the stored values are true or false depending on which dice the user wants to re-roll
	 */
	public boolean[] reroll() {
		boolean[] positions = new boolean[diceSize];//the array made and returned of the positions the user wanted to re-roll
		int answer;//the choice the user makes
		for(int i = 0; i < positions.length; i++) {
			System.out.print(getName()+" would you like to change the die in position " + (i + 1) + " enter a 0 for no and a 1 for yes: ");
			answer = input.nextInt();
			if(answer == 1) {
				positions[i] = true;
			}else {
				positions[i] = false;
			}
			
		}
		System.out.print("\n");
		return positions;
	}
	/**
	 * Method returns the name of the player
	 * @return - returns the name of the player
	 */
	public String getName() {
		return name;
	}
}




