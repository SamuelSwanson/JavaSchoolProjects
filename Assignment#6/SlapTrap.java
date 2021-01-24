/**
 * This class creates SlapTrap objects. If
 * player enter the same with the slapTrap then it will set
 * the players delay and remove itself from the cell
 * @author Samuel Swanson
 *
 */
public class SlapTrap implements Boardable{
	/**
	 * the board the itself and all other objects are on
	 */
	private Board board;	
	/**
	 * constructor setting the board to the board passed in
	 * @param board - the board all object are on in this instance
	 */
	public SlapTrap(Board board) {
		this.board = board;
	}
	/**
	 * visibility is set to false because we dont want the player
	 * to know where they are
	 */
	public boolean isVisible() {
		return false;
	}
	/**
	 * This determines which elements can share with itself.
	 * If it ends up being a player then it also shakes or makes less of the 
	 * boards cells visible
	 *  @param board - the board being used
	 *  return - true or false based on if the element can share with slapTrap
	 */
	public boolean share(Boardable elem) {
		if(elem instanceof Player) {
			if(((Player) elem).isCheating == true && ((Player) elem).isVisible() == true) {
				return slap(elem);
			}else if(((Player) elem).isCheating == true && ((Player) elem).isVisible() == false) {
				return true;
			}else {
					return slap(elem);
					}
		}else if(elem instanceof Jarvis) {
			return true;
		}else {	
			return false;
		}
	}
	/**
	 * This method changes the visibility of the cells 
	 * simulating that when the player is slapped they can
	 * not remember everywhere they looked, it also prints the 
	 * message of what happend
	 * @param elem - will be of type player but the element passed
	 * @return - boolean to help the share method
	 */
	private boolean slap(Boardable elem) {
		message();
		this.board.move(Direction.SHAKE,((Player) elem));
		this.board.removeElement(this);
		return true;
	}
	/**
	 * This method prints the message of what happend
	 */
	private void message() {
		String message = "Jarvis notices you are following so he hids behind a corner and waits for you.\n"+
				"When you approach he jumps out and slaps you screaming 'fly you fool!'. He then disapears into\n"+
				"the abyss. Not only do these actions confuse you but the slap disorients you leading you to\n"+
				"forget some places you have searched.";
		System.out.println(message);
	}
	/**
	 * The to string will return a blank so if a cells visibility is 
	 * true than it will be camouflaged
	 */
	public String toString() {
		return " ";
	}
}




