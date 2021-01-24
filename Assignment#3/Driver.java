import java.util.Scanner;

/**
 * Assignment #3
 * This class starts the program
 * you can create and then register the players and once they are all registered
 * the game is played
 * @author samuel swanson
 *
 */
public class Driver {
	/**
	 * this can be simplified and you dont need the interface but this makes it easier to start
	 * the game and it adds said players to the game
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		GameMaster gm = new GameMaster();
		gm.makeDice();
		String answer;
		String playerType;
		String playerName;
		System.out.print("Do you want to add anyone to the game? Enter yes or no: ");
		answer = input.nextLine();
		answer.trim();
		do {
			System.out.print("Do you want to enter an automated or human player? Enter a for automated and h for human: " );
			playerType = input.nextLine();
			playerType.trim();
			if(playerType.equalsIgnoreCase("a")) {
				System.out.print("What is their name?");
				playerName = input.nextLine();
				Player autoPlayer = new AutomatedPlayer(playerName);
				gm.registerPlayer(autoPlayer);
			}else {
				System.out.print("What is their name?");
				playerName = input.nextLine();
				Player Player = new Player(playerName);
				gm.registerPlayer(Player);
			}
			System.out.print("Do you want to add anyone to the game? Enter yes or no: ");
			answer = input.nextLine();
			answer.trim();
		}while(!answer.equalsIgnoreCase("no"));
		gm.playGame();
		input.close();
	}

}
