/**
 * This abstract class implements runnable and boardable. 
 * It has a few methods within run which makes making a thread
 * possible, it holds he board, creates a super class giving
 * jarvis and player the same board, lastly is has the move method
 * @author Samuel Swanson
 *
 */
public abstract class Mobile implements Runnable, Boardable {
	/**
	 * the board passed to the classes that extend this class
	 */
	protected Board board;
	/**
	 * from the runnable interface, allows for threads
	 */
	public void run() {

	}
	/**
	 * super class for the classes that extend mobile
	 * passes the board the elements will be placed on
	 * @param board - the board passed
	 */
	public Mobile(Board board) {
		this.board=board;
	}
	/**
	 * moves the players around the board
	 */
	protected void move() {
		
	}

}
