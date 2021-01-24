import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
/**
 * The class asks the user what dimensions they want and throws if it is out of bounds
 * it then creates the board followed by stylus and calls the stylus move method. This allows the 
 * user to use the method until they are done with it.
 * @author samue
 *
 */
public class DrawingDriver {
	/**
	 * the scanner used in the drawing driver
	 */
	private static Scanner input = new Scanner(System.in);
	/**
	 * puts the user in a while loop until the correct type is entered and 
	 * creates the board. Then it creates the stylus and calls the stylus move
	 * function.
	 * @param args
	 */
	public static void main(String[] args) {
		int height;// height the user wants the board to have
		int width;// width the user wants the board to have
		boolean checker = true;// is true until the correct input is entered
		String intro = "Welcome to hug the angry jarvis, The object of this game and the only way to end the game is \n" +
				"if you hug jarvis. You will move around the board and try to hug him while he places slap traps and homework traps \n"+
				"you can also cheat. Cheating will let you not be phased by homework traps. Be warned if you cheat you can not hug jarvis until \n"+
				"you stop cheating, and if you stop cheating and are on a homework trap bad things happen. So you can take the risk if you want to but \n"+
				"i advise against it. One last note about cheating is while you are cheating you will not be able reveal locations of cells.";
		System.out.println(intro);//prints the intro
		while(checker) {
			try {
				System.out.println("Please enter the height you would like the board to have");
				height = input.nextInt();
				System.out.println("Please enter the width you would like the board to have");
				width = input.nextInt();
				Board newBoard = new Board(height,width);// board created by user with throw if out of bounds
				Player player = new Player(newBoard);// stylus created by user
				Jarvis jarvis = new Jarvis(newBoard);
				Random rand = new Random();//used to place jarvis randomly on the board
				newBoard.placeElement(jarvis,rand.nextInt(height),rand.nextInt(width));
				newBoard.placeElement(player,0,0);
				Thread playerThread = new Thread(player);
				Thread jarvisThread = new Thread(jarvis);
				jarvisThread.start();
				playerThread.start();
				try {
					jarvisThread.join();
					playerThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				checker = false;
			}catch(InputMismatchException c) {
				System.out.println("invalid input");
				input.nextLine();
			}
		}
	}

}
