import java.util.ArrayList;
import java.util.Random;

/**
 * This class runs the game but does not start the game.
 * It acts as an unbiased person who runs and keeps the game fair
 * 
 * @author Samuel Swanson
 *
 */
public class GameMaster {
	/**
	 * the array that stores the players playing
	 */
	private ArrayList<Player> gamePlayers = new ArrayList<Player>();
	/**
	 * the array that stores the die used in the game
	 */
	private ArrayList<Die> dice = new ArrayList<Die>();
	/**
	 * the array stores a boolean to ensure the players that can't win won't
	 */
	private ArrayList<Boolean> canWin = new ArrayList<Boolean>();
	/**
	 * the array stores the scores of each player
	 */
	private ArrayList<Integer> score = new ArrayList<Integer>();
	/**
	 * the array stores the Players who have scores below 200 if there are any
	 */
	private ArrayList<Integer> arrayOfThePlayersBelowTwoHundo = new ArrayList<Integer>();
	/**
	 * this boolean helps keep track of if they won by guessing or not
	 */
	private boolean wonByGuess = false;
	/**
	 * this boolean helps keep track of if they won by rolls
	 */
	private boolean wonByRolls = false;
	/**
	 * the random variable used in the game
	 */
	private Random rand;
	/**
	 * registers or gets each player into the game
	 * 
	 * @param curPlayer - the current player entering the game
	 */
	public void registerPlayer(Player curPlayer) {
		this.gamePlayers.add(curPlayer);
		this.canWin.add(true);
		this.score.add(0);
		giveNumDice(curPlayer);
	}
	/**
	 * this method runs the game 
	 */
	public void playGame() {
		int[] gameEnds = new int[2];
		giveNumPlayers();
		for(int i = 0; i<5; i++) {
			System.out.print("Round " + (i+1) + "\n" + "\n");
			for(int j = 0; j< gamePlayers.size(); j++) {
				System.out.println(gamePlayers.get(j).getName().toUpperCase() + "'S TURN\n");
				Player temp = gamePlayers.get(j);
				boolean wantToGuess = temp.wantsToGuess();
				if(wantToGuess == true) {
					boolean theirGuess = checkGuess(temp.guess());
					if(theirGuess == true && this.canWin.get(j) == true) {
						System.out.print(temp.getName() + " guessed the position of the nonRepeating dice and wins the game");
						this.wonByGuess = true;
						i = 6;
						j = gamePlayers.size();
					}else {
						cantWin(j);
						gameEnds = winByRolls(i, j, temp);
						i = gameEnds[0];
						j = gameEnds[1];
					}
				}else {
						gameEnds = winByRolls(i, j, temp);
						i = gameEnds[0];
						j = gameEnds[1];
					}
				}
				if(this.wonByGuess == false && this.wonByRolls == false) {
					printScores();
				}
			}
			if(this.wonByGuess == false && this.wonByRolls == false) {
				declareTheWinner();
			}
		}
	
	/**
	 * This method makes the dice
	 * It sets how many dice will be played with and each will
	 * have a random number of sides
	 */
	public void makeDice() {
		rand = new Random(); 
		for(int i = 0; i< rand.nextInt(5)+4; i++) {
			int sides = rand.nextInt(9)+3;
			if(i < 2) {
				this.dice.add(new NonRepeatingDie(sides, i+1));
			}else {
				this.dice.add(new Die(sides, i+1));
			}
		}
		shuffleOrder();
	}
	/**
	 * Shuffles the order of where the dice are in the array
	 * This makes it so the player will not know where
	 * the non repeating dice are
	 */
	private void shuffleOrder() {
		Random rand = new Random();
		Die die;
		for(int i = 0;  i<10_000; i++) {
			die = this.dice.remove(0);
			int position = rand.nextInt(this.dice.size()+1);
			this.dice.add(position, die);
		}
	}
	/**
	 * this method passes the size of the gamePlayers are to
	 * tell the players how many players are playing
	 */
	private void giveNumPlayers() {
		int playerNumber = gamePlayers.size();
		for(int i = 0; i<gamePlayers.size(); i++) {
			gamePlayers.get(i).receiveNumberOfPlayers(playerNumber);
		}
	}
	/**
	 * this method passes the size and number of sides
	 * on each die to the user so they know what they're
	 * working with
	 */
	private void giveNumDice(Player curPlayer) {	
		curPlayer.receiveDiceInfo(makeCopyOfOriginal());
	}
	/**
	 * Makes a copy of the original array it changes the arraylist to an array 
	 * this is to ensure aliasing will not happen
	 * 
	 * @return - returns a replica of the class arraylist dice in an array 	 
	 */
	private int[] makeCopyOfOriginal() {
		int[] copyArray = new int[dice.size()];
		for(int i = 0; i < dice.size(); i++) {
			copyArray[i] = dice.get(i).getNumSides();
		}
		return copyArray;
	}
	/**
	 * gets the positions in the arraylist of where each non repeating die are
	 * @return - returns an array of size two. the store values are the positons
	 * of where the non repeating die are
	 */
	private int[] getNonRepeatingDie() {
		int[] arrayOfPosition = new int[2];
		int j = 0;
		for(int i = 0; i < dice.size(); i++) {
			if(dice.get(i).getId() == 1 || dice.get(i).getId() == 2) {
				arrayOfPosition[j] = i;
				j++;
			}
		}
		return arrayOfPosition;
	}
	/**
	 * method checks to see if the guess equals the position of the nonRepeatingDies
	 * @param guess - the users guess
	 * @return - returns boolean telling whether they are right or wrong
	 */
	private boolean checkGuess(int[] guess) {
		int[] positionOfNonRepeatingDie = getNonRepeatingDie();
		return (((guess[0] == positionOfNonRepeatingDie[0] || guess[0] == positionOfNonRepeatingDie[1]) && (guess[1] == positionOfNonRepeatingDie[0] || guess[1] == positionOfNonRepeatingDie[1])));
	}
	/**
	 * method rolls the dice for the player and broadcasts the results to all
	 */
	private int[] rollForPlayer() {
		int[] arrayOfRolls = new int[dice.size()];
		for(int i = 0; i<arrayOfRolls.length; i++) {
			arrayOfRolls[i] = dice.get(i).roll();
		}
		return arrayOfRolls;
	}
	/**
	 * method checks if all the sides are the same for each die
	 * @param arrayOfRolls - the array used to check if the sides are the same
	 * @return - boolean based on if they are the same or not
	 */
	private boolean checkForAllSameSides(int[] arrayOfRolls) {
		for(int i = 0; i<arrayOfRolls.length-1; i++) {
			if(arrayOfRolls[i] != arrayOfRolls[i+1]) {
				return false;
			}
		}
		return true;
	}
	/**
	 * method broadcasts the results to all the players
	 * @param arrayOfRolls - the array of all the roll values
	 */
	private void  broadcastRollResults(int[] arrayOfRolls) {
		for(int i = 0; i<gamePlayers.size(); i++) {
			gamePlayers.get(i).receiveRollResults(arrayOfRolls);
		}
	}
	/**
	 * method re-rolls the requested positions
	 * @param arrayOfRolls - the array of the previous roll
	 */
	private int[] rerollForPlayer(int[] arrayOfRolls, int player){
		boolean[] wantToReRoll = gamePlayers.get(player).reroll();
		for(int i = 0; i<arrayOfRolls.length; i++) {
			if(wantToReRoll[i] == true) {
			arrayOfRolls[i] = dice.get(i).roll();
			}
		}
		return arrayOfRolls;
	}
	/**
	 * makes a copy of the roll so score tampering will not happen
	 * @param arrayOfRolls - the real array of rolls
	 * @return - a copy of the array of rolls used to broadcast
	 */
	private int[] makeCopyOfRoll(int[] arrayOfRolls) {
		int[] copyArray = new int[arrayOfRolls.length];
		for(int i = 0; i < arrayOfRolls.length; i++) {
			copyArray[i] = arrayOfRolls[i];
		}
		return copyArray;
	}
	/**
	 * Does the roll and re-roll for the player, makes sure the gameflow will continue even if they
	 * cant win and roll all the same values
	 * @param position - number passed for which players turn it is
	 */
	private boolean manageTheRolls(int position) {
		int[] arrayOfRolls = rollForPlayer();
		int[] copyOfOriginalRolls = makeCopyOfRoll(arrayOfRolls);
		if(checkForAllSameSides(arrayOfRolls) == false) {
			broadcastRollResults(copyOfOriginalRolls);
			int[] arrayOfReRolls = rerollForPlayer(arrayOfRolls, position);
			int[] copyOfReRolls = makeCopyOfRoll(arrayOfReRolls);
			if(checkForAllSameSides(arrayOfReRolls) == false) {
				broadcastRollResults(copyOfReRolls);
				addScore(arrayOfReRolls, position);
				return false;
			}else {
				if(canWin.get(position) == false) {
					broadcastRollResults(copyOfReRolls);
					addScore(arrayOfReRolls, position);
				}
				return true;
			}
		}else {
			if(canWin.get(position) == false) {
				logicInManageRolls(arrayOfRolls, copyOfOriginalRolls, position);
			}
			return true;
		}
	}
	/**
	 * this method ensures the printing and score keeping still happens with the looser
	 * @param copyOfOriginalRolls - is the copy of the original rolls not concerned with aliasing they cant win no matter the rolls
	 * @param position -which spot in the arrays we are
	 */
	private void logicInManageRolls(int[] arrayOfRolls, int[] copyOfOriginalRolls, int position) {
		broadcastRollResults(copyOfOriginalRolls);
		int[] arrayOfReRolls = rerollForPlayer(arrayOfRolls, position);
		int[] copyOfReRolls = makeCopyOfRoll(arrayOfReRolls);
		broadcastRollResults(copyOfReRolls);
		addScore(arrayOfReRolls, position);
	}
	/**
	 * keeps track of the scores and adds them up
	 * @param arrayOfRolls - the array of the rolled values
	 * @param position - where in the array we are
	 */
	private void addScore(int [] arrayOfRolls, int position) {
		int total = 0;
		for(int i = 0; i< arrayOfRolls.length; i++) {
			total = total + arrayOfRolls[i];
		}
		int temp = this.score.get(position);
		temp = temp + total;
		this.score.set(position, temp);
	}
	/**
	 * makes sure those that guess nonRepeating dice incorrectly cant win the game
	 * @param position - to determine which player can no longer win
	 */
	private void cantWin(int position) {
		canWin.remove(position);
		canWin.add(position, false);
	}
	/**
	 * if the player has all the same sides showing it ensure they win and starts the rolls
	 * @param position - which spot in the arrays we are at
	 * @param temp - the player that would win
	 * @param round - the round the game is in
	 */
	private int[] winByRolls(int round, int position, Player temp) {
		int[] endGame = new int[2];
			if(manageTheRolls(position) == true && canWin.get(position) == true) {
				System.out.print(temp.getName() + " got all the same values and wins the game");
				this.wonByRolls = true;
				endGame[0] = 6;
				endGame[1] = gamePlayers.size();
			}else {
				endGame[0] = round;
				endGame[1] = position;
			}
		return endGame;
	}
	/**
	 * prints the scores at the end
	 */
	private void printScores() {
		System.out.println("The scores are as follows ");
			for(int i = 0; i<gamePlayers.size(); i++) {
				System.out.println(gamePlayers.get(i).getName() + " has a score of " + score.get(i));
			}
		System.out.println("\n");
	}
	/**
	 * processes who the winner is in the game
	 */
	private void declareTheWinner() {
		ArrayList<Integer> peopleWhoCanWin = new ArrayList<Integer>();
		peopleWhoCanWin = makeArrayOfWhoCanWin();
		setScore(peopleWhoCanWin);
		if(peopleWhoCanWin.size() != 0) {
				if(checkIfOneIsBelowTwoHundo(peopleWhoCanWin) == false) {
					System.out.print("Sorry its a draw, the people that could win were above 200 ");
				}else {
					addToArrayBelowTwoHundo(peopleWhoCanWin);
					int winner = arrayOfThePlayersBelowTwoHundo.get(0);
					for(int i = 0; i< arrayOfThePlayersBelowTwoHundo.size()-1; i++) {
						if((score.get(arrayOfThePlayersBelowTwoHundo.get(winner))) > (score.get(arrayOfThePlayersBelowTwoHundo.get(i+1)))) {
							winner = arrayOfThePlayersBelowTwoHundo.get(i+1);
						}
					}
					System.out.print("Congrats the winner is " + gamePlayers.get(winner).getName());
				}
		}else {
			System.out.print("Sorry there are no winners ");
		}
	}
	/**
	 * makes an array of which people can win
	 * @return - the array of who can win
	 */
	private ArrayList<Integer> makeArrayOfWhoCanWin(){
		ArrayList<Integer> peopleWhoCanWin = new ArrayList<Integer>();
		for(int i = 0; i< canWin.size(); i++) {
			if(canWin.get(i) == true) {
				peopleWhoCanWin.add(i);
			}
		}
		return peopleWhoCanWin;
	}
	/**
	 * checks if the people who can win have scores below 200
	 * @param peopleWhoCanWin - array of who can win
	 * @return - returns a boolean to say if one person has a score below 200
	 */
	private boolean checkIfOneIsBelowTwoHundo(ArrayList<Integer> peopleWhoCanWin){
		for(int i = 0; i< peopleWhoCanWin.size(); i++) {
			if(score.get(peopleWhoCanWin.get(i)) == Math.abs(score.get(peopleWhoCanWin.get(i)))) {
				return true;
			}
		}
		return false;
		
	}
	/**
	 * adds people who can win and their score is below two hundred to arrayOfThePlayersBelowTwoHundo arraylist
	 * @param peopleWhoCanWin - people that can win in the game
	 */
	private void addToArrayBelowTwoHundo(ArrayList<Integer> peopleWhoCanWin){
		for(int i = 0; i< peopleWhoCanWin.size(); i++) {
			if(score.get(peopleWhoCanWin.get(i)) == Math.abs(score.get(peopleWhoCanWin.get(i)))) {
				arrayOfThePlayersBelowTwoHundo.add(peopleWhoCanWin.get(i));
			}
		}	
	}
	/**
	 * Changes the scores to make it easier to process
	 * @param peopleWhoCanWin - the people who can win in the game
	 */
	private void setScore(ArrayList<Integer> peopleWhoCanWin) {
		for(int i = 0; i< peopleWhoCanWin.size(); i++) {
			score.set(peopleWhoCanWin.get(i), (200 - score.get(peopleWhoCanWin.get(i))));
		}
	}
}
