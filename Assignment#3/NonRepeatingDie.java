import java.util.ArrayList;
/**
 * This class extends die, the only difference is the die will not re-roll the same side until
 * all sides have been rolled
 * 
 * @author Samuel Swanson
 *
 */
public class NonRepeatingDie extends Die {
	
	/**
	 * Creates an arraylist to store integers
	 */
	private ArrayList<Integer> arrayOfRolls = new ArrayList<Integer>();
	/**
	 * Constructor to create the non repeating die
	 * @param numSides - integer storing the number of sides on the die
	 * @param id - integer storing the id of the die
	 */
	public NonRepeatingDie(int numSides, int id){
		super(numSides,id);
	}
	/** 
	 * Simulates the roll of a die.  
	 * It randomly returns a value in the range [1,numSides]. 
	 * It will not select the same side until all sides are rolled.
	 * Keeps track by adding roll value to arraylist each time value is not in arraylist
	 *  @return a random value in the range  [1, numSides]. 
	 */
	public int roll() {
		int rollVal = super.roll();//rollval is the value rolled from super class roll
		do {
			 rollVal = super.roll();
		}while(checkIfInArray(rollVal) == true);
		
		arrayListManagement(rollVal);
		return rollVal;
	}
	/**
	 * Passed the current roll value to check if it is in the array, if it is in the array it passes true if not false
	 * @param rollVal - is the current rolled value
	 * @return - returns a boolean to state if the value is in the arraylist or not
	 */
	public boolean checkIfInArray(int rollVal) {
		if(arrayOfRolls.contains(rollVal)) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Adds the rolled value to the arraylist and if the arraylist is full or 
	 * equal to the number of sides on the dice, it resets the arraylist so it can keep track of the die again
	 * @param rollVal - the rolled value that is not yet in the arraylist
	 */
	public void arrayListManagement(int rollVal) {
		arrayOfRolls.add(rollVal);
		if(arrayOfRolls.size() == super.getNumSides()) {
			arrayOfRolls.clear();
		}
	}
}
