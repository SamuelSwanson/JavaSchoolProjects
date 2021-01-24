import java.util.InputMismatchException;
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
		while(checker) {
			try {
				System.out.println("Please enter the height you would like the board to have");
				height = input.nextInt();
				System.out.println("Please enter the width you would like the board to have");
				width = input.nextInt();
				Board newBoard = new Board(height,width);// board created by user with throw if out of bounds
				Stylus stylus = new Stylus(newBoard);// stylus created by user
				stylus.move();// calls the stylus move method
				checker = false;
			}catch(InputMismatchException c) {
				System.out.println("invalid input");
				input.nextLine();
			}
		}
	}

}
