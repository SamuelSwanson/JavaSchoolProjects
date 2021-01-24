/**
 * This class has a driver that will check the function of the program. It
 * will run various tests to make sure the program runs properly
 * @author Samuel Swanson
 *
 */
public class TestDriver {
/**
 * This calls the methods that run the tests. An important note is I can not test my visibility 
 * in the test driver. I know I will need to restructure aspects of my design and that is one flaw so far. I have 
 * not changed it yet since the visibility toggle was not required for this assignment
 * @param args
 */
	public static void main(String[] args) {
		//Board newBoard3 = new Board(-10,10);//uncomment to check board creation throw
		//Board newBoard4 = new Board(10,-10);//uncomment to check board creation throw
		//Board newBoard5 = new Board(101,10);//uncomment to check board creation throw
		//Board newBoard6 = new Board(10,101);//uncomment to check board creation throw
		Board newBoard2 = new Board(10,10);//board to check throw for stylus not on board
		Board newBoard = new Board(5,5);// creates a board
		Stylus stylus = new Stylus(newBoard);// places the first stylus on the board
		//checkTop(newBoard2, stylus);//uncomment to check that the throw will happen if stylus is not on the board
		Stylus stylus2 = new Stylus(newBoard);// places the second stylus on the board
		newBoard.printBoard();// prints the board
		checkTop(newBoard, stylus);// checks to make sure the program will not crash when out of bounds
		checkRight(newBoard, stylus);// just like check top but it looks for out of bounds on the right
		checkBottom(newBoard, stylus);// checks for out of bounds on the bottom
		checkLeft(newBoard, stylus);// checks for out of bounds on the left
		System.out.println();
		newBoard.printBoard();// prints new board
		checkShake(newBoard, stylus);//shakes the board to clear it
		checkShake(newBoard, stylus);//shakes the board to clear it
		checkShake(newBoard, stylus);//shakes the board to clear it
		checkShake(newBoard, stylus);//shakes the board to clear it
		checkShake(newBoard, stylus);//shakes the board to clear it
		checkShake(newBoard, stylus);//shakes the board to clear it
		newBoard.printBoard();//prints new board
		System.out.println();
		checkRight(newBoard, stylus2);// shows that two stylus's can be on the board
		newBoard.printBoard();//prints new board
	}
	/**
	 * goes through each cell on the topmost row and makes sure 
	 * an out of bounds does not happen
	 * @param newBoard - the board the stylus is on
	 * @param stylus - the stylus element to be moved
	 */
	private static void checkTop(Board newBoard, Stylus stylus) {
		for(int i = 0; i<5; i++) {
			newBoard.Move(Direction.UP_LEFT, stylus);
			newBoard.Move(Direction.UP, stylus);
			newBoard.Move(Direction.UP_RIGHT, stylus);
			newBoard.Move(Direction.RIGHT, stylus);
		}
	}
	/**
	 * goes through each cell on the rightmost column and makes sure 
	 * an out of bounds does not happen
	 * @param newBoard - the board the stylus is on
	 * @param stylus - the stylus element to be moved
	 */
	private static void checkRight(Board newBoard, Stylus stylus) {
		for(int i = 0; i<5; i++) {
			newBoard.Move(Direction.UP_RIGHT, stylus);
			newBoard.Move(Direction.RIGHT, stylus);
			newBoard.Move(Direction.DOWN_RIGHT, stylus);
			newBoard.Move(Direction.DOWN, stylus);
		}
	}
	/**
	 * goes through each cell on the bottom row and makes sure 
	 * an out of bounds does not happen
	 * @param newBoard - the board the stylus is on
	 * @param stylus - the stylus element to be moved
	 */
	private static void checkBottom(Board newBoard, Stylus stylus) {
		for(int i = 0; i<5; i++) {
			newBoard.Move(Direction.DOWN_LEFT, stylus);
			newBoard.Move(Direction.DOWN, stylus);
			newBoard.Move(Direction.DOWN_RIGHT, stylus);
			newBoard.Move(Direction.LEFT, stylus);
		}
	}
	/**
	 * goes through each cell on the leftmost column and makes sure 
	 * an out of bounds does not happen
	 * @param newBoard - the board the stylus is on
	 * @param stylus - the stylus element to be moved
	 */
	private static void checkLeft(Board newBoard, Stylus stylus) {
		for(int i = 0; i<5; i++) {
			newBoard.Move(Direction.UP_LEFT, stylus);
			newBoard.Move(Direction.LEFT, stylus);
			newBoard.Move(Direction.DOWN_LEFT, stylus);
			newBoard.Move(Direction.UP, stylus);
		}
	}
	/**
	 * I could not check this due to the visibility being private 
	 * and how I set up my program. I will need to restructure to
	 * test for this
	 * @param newBoard - the board the stylus is on
	 * @param stylus - the stylus element to be moved
	 */
	private static void checkVis(Board newBoard, Stylus stylus) {
		newBoard.Move(Direction.VISIBILITY, stylus);
	}
	/**
	 * this checks to make sure the shake works
	 * @param newBoard - the board the stylus is on
	 * @param stylus - the stylus element to be moved
	 */
	private static void checkShake(Board newBoard, Stylus stylus) {
		newBoard.Move(Direction.SHAKE, stylus);
	}
	
}
