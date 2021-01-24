import java.io.IOException;
import java.util.Scanner;
/**
 * Creates the stylus and places the stylus onto the board
 * @author Samuel Swanson
 *
 */
public class Player extends Mobile {
	/**
	 * the scanner used to get the user input
	 */
	private Scanner input = new Scanner(System.in);
	/**
	 * the visibility assigned upon creation
	 */
	private boolean visibility = true;
	/**
	 * the delay the user gets when they hit a homework trap
	 * set to 0 initially because they don't have a delay to start
	 */
	private long delayTime = 0;
	/**
	 * the constructor which creates places the object on the board and 
	 * creates the player object
	 * @param board - board passed in to place the element on to
	 */
	public Player(Board board) {
		super(board);
	}
	/**
	 * this method is called to get the
	 * visibility of the player
	 */
	public boolean isVisible() {
		return visibility;
	}
	/**
	 * acts as if the player can win or not
	 */
	protected boolean canWin = true;
	/**
	 * changes based on if the player is cheating or not
	 */
	protected boolean isCheating = false;
	/**
	 * this method may need to be re-written in the future but it
	 * starts a loop that goes until the user enters an input that does
	 * not work
	 * @return - returns a boolean based on if it were possible to move or not
	 */
	protected void move() {
		while(!super.board.beenHugged()) {
			String question = "To move enter 'q' for up left, 'w' for up, 'e' for up right, 'a' for left, 'f' to cheat,\n "+
					"'d' for right, 'z' for down left, 'x' for down, 'c' for down right, 's' to stay still,";
			System.out.println(question);//prints the question
			super.board.printBoard();//prints the board each time after an input
			delay();
			String choice = input.nextLine();// the users input
			moveLogic(choice);//calls move logic to do the users choice and if invalid checker is set to false and the loop ends
		}
		printWinStatement();// prints the end statement based on the players fate(canwin or not)
	}
	/**
	 * runs the move with lets the player move.
	 * This run is from runnable and starts the run/thread
	 */
	public void run() {
		move();
	}
	/**
	 * sets the delay time to the time passed in
	 * @param Time - the delay wanted
	 */
	public void setDelay(long Time) {
		this.delayTime = Time;
	}
	/**
	 * this method delays the thread by making it sleep for the amount
	 * based on delay time
	 */
	private void delay() {
		try {
			Thread.sleep(this.delayTime);
			try {
				System.in.read(new byte[System.in.available()]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		delayTime = 0;
	}
	/**
	 * player can not share with anyone else so it returns false
	 * this means nothing can go into or be placed into its cell
	 */
	public boolean share(Boardable elem) {
		return false;
	}
	/**
	 * the if else statements to determine which moves to make on the board
	 * @param choice - the users input 
	 * @return - boolean based on if the move could or couldnt happen
	 */
	private boolean moveLogic(String choice) {
		if(choice.equalsIgnoreCase("q")) {
			super.board.move(Direction.UP_LEFT, this);
			return true;
		}else if(choice.equalsIgnoreCase("w")) {
			super.board.move(Direction.UP, this);
			return true;
		}else if(choice.equalsIgnoreCase("e")) {
			super.board.move(Direction.UP_RIGHT, this);
			return true;
		}else if(choice.equalsIgnoreCase("a")) {
			super.board.move(Direction.LEFT, this);
			return true;
		}else if(choice.equalsIgnoreCase("f")) {
			visibility = !visibility;//visibility switched if visibility is changed
			super.board.move(Direction.VISIBILITY, this);
			isCheating = !isCheating;
			return true;
		}else if(choice.equalsIgnoreCase("d")) {
			super.board.move(Direction.RIGHT, this);
			return true;
		}else if(choice.equalsIgnoreCase("z")) {
			super.board.move(Direction.DOWN_LEFT, this);
			return true;
		}else if(choice.equalsIgnoreCase("x")) {
			super.board.move(Direction.DOWN, this);
			return true;
		}else if(choice.equalsIgnoreCase("c")) {
			super.board.move(Direction.DOWN_RIGHT, this);
			return true;
		}else if(choice.equalsIgnoreCase("s")) {
			super.board.move(Direction.STAY, this);
			return true;
		}else if(choice.equalsIgnoreCase("shake")){
			super.board.move(Direction.SHAKE, this);
			return true;
		}else {
			return false;
		}
	}
	/**
	 * This method prints out the winning statement based on if they can win or not
	 */
	private void printWinStatement() {
		if(canWin == true) {
			super.board.printBoard();
			System.out.println("You soothed the angry Jarvis");
		}else {
			super.board.printBoard();
			String finalMessage = "Your excesive cheating resulted in your expulsion. Still your obsession with hugging Jarvis\n"+
		"drove you to stalk him... you just needed that hug. This made jarvis paranoid so he bought a gun. One night\n"+
		"while jarvis was coming home you hid behind a tree to get that hug. Unfortunatly when you jumped out from behind\n"+
		"the tree, the savage startled jarvis shoots you.... and it was all for a hug.... you black out... who knows what happend next.";
			System.out.println(finalMessage);
		}
	}
	/**
	 * this to string is the symbol that the stylus displays on the board
	 */
	public String toString() {
		return "*";
	}
}
