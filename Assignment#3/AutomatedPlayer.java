import java.util.Arrays;
/**
 * This class creates the automated player that is almost
 * like a ghost in the game, simulates a player
 * @author samuel swanson
 *
 */
public class AutomatedPlayer extends Player {
	/**
	 * the reference to the original dice info
	 */
	private int[] reference;
	/**
	 * the roll results of each roll
	 */
	private int[] rollResults;
	/**
	 * this array adds 1 to the arrays array position and that
	 * represents the side
	 */
	private int[][] keepTrackArray;
	/**
	 * this array is a 2d array with all its arrays values set to 1
	 */
	private int[][] checkerArray;
	/**
	 * this is the array that stores the positions of the NRD
	 */
	private int[] AutoGuess = new int[2];
	/**
	 * array used and set to false until both positions of NRD are known
	 */
	private boolean[] knowsPositions = new boolean[2];
	/**
	 * array set to true to check
	 */
	private boolean[] knowsPositionsChecker = new boolean[2];
	/**
	 * calls the super name from player and initiates the knowsPositionsChecker array
	 * @param name - the name passed in
	 */
	public AutomatedPlayer(String name) {
		super(name);
		initiateKnowsPositionsCheckerArray();
	}
	/**
	 * Receives the dice makes the 2d arrays and sets the checker array arrays to 1 so that
	 * it can check if the arrays are the same later. This helps the player know
	 * if it the die is NonRepeating or not
	 */
	public void receiveDiceInfo(int[]info) {
		reference = info;
		keepTrackArray = new int[reference.length][];
		checkerArray = new int[reference.length][];
		for(int i = 0; i < reference.length; i++) {
			keepTrackArray[i] = new int[reference[i]];
			checkerArray[i] = new int[reference[i]];
			for(int j = 0; j < checkerArray[i].length; j++) {
				checkerArray[i][j] = 1;
			}
		}
	}
	/**
	 * will guess if the two arrays are the same
	 */
	public boolean wantsToGuess() {
		if(Arrays.equals(knowsPositions, knowsPositionsChecker)) {
			return true;
		}
		return false;
	}
	/**
	 * returns the array autoguess which is array of positions
	 * of where the NRD are
	 */
	public int[] guess() {
		return AutoGuess;
	}
	/**
	 * method rerolls position if the rolled value is less than half the number of sides
	 * goal is not to get all the same sides but to get the highest score
	 */
	public boolean[] reroll() {
		boolean[] positions = new boolean[reference.length];//the array made and returned of the positions the user wanted to re-roll
		int answer;//the choice the user makes
		for(int i = 0; i < positions.length; i++) {
			if(rollResults[i] < reference[i]/2) {
				answer = 1;
			}else {
				answer = 0;
			}
			if(answer == 1) {
				positions[i] = true;
			}else {
				positions[i] = false;
			}
		}
		return positions;
	}
	/**
	 * method receives the results stores the values in the 2d array
	 * keeps track and changes necessary logic  to keep if they want to guess
	 * in order
	 */
	public void receiveRollResults(int[] results) {
		int j = 0;
		int checkIfSameNumber = 20;
		rollResults = results;
		for(int i = 0; i < keepTrackArray.length; i++) {
			keepTrackArray[i][rollResults[i]-1]++;
		}
		for(int i = 0; i < keepTrackArray.length; i++) {
			if(Arrays.equals(keepTrackArray[i], checkerArray[i]) ) {
				if(j<2 && checkIfSameNumber != i) {
					knowsPositions[j] = true;
					AutoGuess[j] = i;
					checkIfSameNumber = i;
					j++;
				}
			}
		}
	}
	/**
	 * method ensures the default false values are changed to true
	 */
	private void initiateKnowsPositionsCheckerArray() {
		for(int i = 0; i<knowsPositionsChecker.length; i++) {
			knowsPositionsChecker[i] = true;
		}
	}
	/**
	 * overrides the original so no printing is done
	 */
	public void receiveNumberOfPlayers(int num) {
		
	}
}
