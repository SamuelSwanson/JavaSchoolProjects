import java.util.Random;
/**
 * This class creates the die constructor that represents a board game die
 * 
 * @author Samuel Swanson
 *
 */
public class Die {
	/**
	 * Stores number of sides on each die
	 */
	private int numSides;
	/**
	 * Stores the id of each die
	 */
	private int id;
	/**
	 * Used to generate random numbers in the role method
	 */
	private Random rand;
	/**
	 * Constructor to create each die
	 * @param numSides - integer storing the number of sides on the die
	 * @param id - integer storing the id of the die
	 */
	public Die(int numSides, int id){
		rand = new Random();
		this.numSides = numSides;
		this.id = id;
	}
	/**
	 * Gets the number of sides for the die
	 * @return - returns an integer stating the number of sides the die has
	 */
	public int getNumSides() {
		return numSides;
	}
	/**
	 * Gets the id of the die
	 * @return - returns an integer stating the id of the die
	 */
	public int getId() {
		return id;
	}
	/** 
	 * Simulates the roll of a die.  
	 * It randomly returns a value in the range [1,numSides]. 
	 *  @return a random value in the range  [1, numSides]. 
	 */
	public int roll() {
		int rollRes = rand.nextInt(this.numSides)+1;
		return rollRes;
	}
	/**
	 * Returns a to string of the contents of the die
	 */
	public String toString() {
		String ret = "";
		ret = ret + "this die has " + this.numSides + " sides and its id is " + this.id + " ";
		return ret;
	}
}








