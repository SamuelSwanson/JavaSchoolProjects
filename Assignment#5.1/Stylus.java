import java.util.Scanner;
/**
 * Creates the stylus and places the stylus onto the board
 * @author Samuel Swanson
 *
 */
public class Stylus implements Boardable {
	/**
	 * the board passed in by the user, it is where the stylus is placed
	 */
	private Board board;
	/**
	 * the scanner used to get the user input
	 */
	private Scanner input = new Scanner(System.in);
	/**
	 * the visability assigned upon creation
	 */
	private boolean visibility = true;
	/**
	 * the constructor which creates places the object on the board and 
	 * creates the stylus object
	 * @param board - board passed in to place the element on to
	 */
	public Stylus(Board board) {
		this.board = board;
		board.placeElement(this,0,0);
	}
	/**
	 * this method is called to get the
	 * visibility of the stylus
	 */
	public boolean isVisible() {
		return visibility;
	}
	/**
	 * this method may need to be re-written in the future but it
	 * starts a loop that goes until the user enters an input that does
	 * not work
	 * @return - returns a boolean based on if it were possible to move or not
	 */
	public boolean move() {
		boolean checker = true;//keeps track and ends the loop when the user enters the wrong input
		while(checker) {
			board.printBoard();//prints the board each time after an input
			String question = "To move enter 'q' for up left, 'w' for up, 'e' for up right, 'a' for left, 's' for visibility change, "+
					"'d' for right, 'z' for down left, 'x' for down, 'c' for down right, shake to shake, and anything else to quit";
			System.out.println(question);//prints the question
			String choice = input.nextLine();// the users input
			checker = moveLogic(choice);//calls move logic to do the users choice and if invalid checker is set to false and the loop ends
		}
		System.out.println("Goodbye");
		return checker;

		
	}
	/**
	 * the if else statements to determine which moves to make on the board
	 * @param choice - the users input 
	 * @return - boolean based on if the move could or couldnt happen
	 */
	private boolean moveLogic(String choice) {
		if(choice.equalsIgnoreCase("q")) {
			board.Move(Direction.UP_LEFT, this);
			return true;
		}else if(choice.equalsIgnoreCase("w")) {
			board.Move(Direction.UP, this);
			return true;
		}else if(choice.equalsIgnoreCase("e")) {
			board.Move(Direction.UP_RIGHT, this);
			return true;
		}else if(choice.equalsIgnoreCase("a")) {
			board.Move(Direction.LEFT, this);
			return true;
		}else if(choice.equalsIgnoreCase("s")) {
			visibility = !visibility;//visibility switched if visibility is changed
			board.Move(Direction.VISIBILITY, this);
			return true;
		}else if(choice.equalsIgnoreCase("d")) {
			board.Move(Direction.RIGHT, this);
			return true;
		}else if(choice.equalsIgnoreCase("z")) {
			board.Move(Direction.DOWN_LEFT, this);
			return true;
		}else if(choice.equalsIgnoreCase("x")) {
			board.Move(Direction.DOWN, this);
			return true;
		}else if(choice.equalsIgnoreCase("c")) {
			board.Move(Direction.DOWN_RIGHT, this);
			return true;
		}else if(choice.equalsIgnoreCase("shake")){
			board.Move(Direction.SHAKE, this);
			return true;
		}else {
			return false;
		}
	}
	/**
	 * this to string is the symbol that the stylus displays on the board
	 */
	public String toString() {
		return "*";
	}
}
