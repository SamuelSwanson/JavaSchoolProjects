import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
 * This class extends mobile and is the object
 * jarvis which is who you are trying to catch during
 * the game
 * @author Samuel Swanson
 *
 */
public class Jarvis extends Mobile{
	/**
	 * The random used in this class
	 */
	private Random rand = new Random();
	/**
	 * keeps track of the amount of times jarvis moves and 
	 * lays a trap every 6
	 */
	static int counter = 0;
	/**
	 * array of the places the trap could be laid
	 */
	private ArrayList<String> arrayOfPossiblePlacements = new ArrayList<String>();
	/**
	 * array of the moves jarvis could make
	 */
	private ArrayList<Direction> arrayOfPossibleMoves = new ArrayList<Direction>();
	/**
	 * this sets the arrayOfPossibleMoves and gives jarvis the board
	 * @param board - the board jarvis is on and uses throught the game
	 */
	public Jarvis(Board board) {
		super(board);
		setPlacementArray();
		setJarvisMovesArray();
	}
	/**
	 * I did this a few ways and found this to be the best way
	 * This method randomly selects SlapTrap or HomeworkTrap
	 * it also shuffles the arrayOfPossibleMoves so that would be random 
	 * every time and then depending on the int will create a slaptrap
	 * or homework trap. It also calls the method selectTrap. I would
	 * rename layTrap to selectTrap and vice versa but im sticking with the uml
	 */
	private void layTrap() {
		int trapSelection = rand.nextInt(2);
		Collections.shuffle(arrayOfPossiblePlacements);
		if(trapSelection == 0) {
			HomeworkTrap hwTrap = new HomeworkTrap(board);
			selectTrap(hwTrap);
		}else {
			SlapTrap slapTrap = new SlapTrap(board);
			selectTrap(slapTrap);
		}

	}
	/**
	 * I found this to be the best way because it ensures only 8 moves happen
	 * I initially was doing it randomly but the amount of executions could be very
	 * high, this ensures at most 8 checks and move on
	 * @param trap - the trap wanted to be placed
	 */
	private void selectTrap(Boardable trap) {
		for(int i = 0; i<arrayOfPossiblePlacements.size(); i++) {
			String temp = arrayOfPossiblePlacements.get(i);
			String[] split = temp.split(",");
			int pos1 = Integer.parseInt(split[0]);
			int pos2 = Integer.parseInt(split[1]);
				if(board.placeElement(trap, board.getRow(this)+pos1, board.getColumn(this)+pos2)) {
					i = arrayOfPossiblePlacements.size();
				}
	
		}
	}
	/**
	 * not the most fancy method but works, I initially thought of doing this as
	 * a 2d array but a string is easier due to knowing and setting the inputs myself
	 * also I add 8 instead of 16 so overall better
	 */
	private void setPlacementArray() {
		arrayOfPossiblePlacements.add("0,1");
		arrayOfPossiblePlacements.add("1,1");
		arrayOfPossiblePlacements.add("0,-1");
		arrayOfPossiblePlacements.add("-1,0");
		arrayOfPossiblePlacements.add("-1,-1");
		arrayOfPossiblePlacements.add("1,0");
		arrayOfPossiblePlacements.add("-1,1");
		arrayOfPossiblePlacements.add("1,-1");
	}
	/**
	 * sets jarvis moves to the array and it is an array of Direction type
	 * this will be shuffled before every move to keep it random
	 */
	private void setJarvisMovesArray() {
		arrayOfPossibleMoves.add(Direction.UP_LEFT);
		arrayOfPossibleMoves.add(Direction.UP);
		arrayOfPossibleMoves.add(Direction.UP_RIGHT);
		arrayOfPossibleMoves.add(Direction.LEFT);
		arrayOfPossibleMoves.add(Direction.RIGHT);
		arrayOfPossibleMoves.add(Direction.DOWN_LEFT);
		arrayOfPossibleMoves.add(Direction.DOWN);
		arrayOfPossibleMoves.add(Direction.DOWN_RIGHT);
	}
	/**
	 * While Jarvis has not been hugged jarvis moves every 800 milliseconds
	 * He lays a trap every 6 moves and choses where to move randomly
	 */
	protected void move() {
		while(!super.board.beenHugged()) {
			//super.board.printBoard();//uncomment for testing purposes
				try {
					Thread.sleep(800);
					if(counter%6 == 0) {
						layTrap();
					}
						} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Collections.shuffle(arrayOfPossibleMoves);
				for(int i = 0; i<arrayOfPossibleMoves.size(); i++) {
					Direction temp = arrayOfPossibleMoves.get(i);
						if(super.board.move(temp, this)) {
							i = arrayOfPossibleMoves.size();
						}
			
				}
				counter++;
			}/**/
		
		}
	/**
	 * Creates the thread for jarvis and calls move to get
	 * the thread started
	 */
	public void run() {
		move();
	}
	/**
	 * does not share with anything else besides player
	 * player can only end if its visibility is true
	 * if it is true then setHugged is set to true
	 */
	public boolean share(Boardable elem) {
		if(elem instanceof Player) {
			if(((Player) elem).isVisible() == false) {
				return true;
			}else {
				board.setHugged(true);
				return true;
			}
		}
		return false;
	}
	/**
	 * visibility of jarvis which is false 
	 */
	public boolean isVisible() {
		return false;
	}
	/**
	 * this to string is the symbol that the stylus displays on the board
	 */
	public String toString() {
		return "?";
	}
}
