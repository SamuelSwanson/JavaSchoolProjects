/**
 * This class creates HomeworkTrap objects. If
 * player enter the same with the HwTrap then it will set
 * the players delay and remove itself from the cell
 * @author Samuel Swanson
 *
 */
public class HomeworkTrap implements Boardable{
		/**
		 * the board the itself and all other objects are on
		 */
		private Board board;	
		/**
		 * constructor setting the board to the board passed in
		 * @param board - the board all object are on in this instance
		 */
		public HomeworkTrap(Board board) {
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
		 * If it ends up being a player then it also sets the players delayTime 
		 *  @param board - the board being used
		 *  return - true or false based on if the element can share with HwTrap
		 */
		public boolean share(Boardable elem) {
			if(elem instanceof Player) {
				if(((Player) elem).isCheating == true && ((Player) elem).isVisible() == true) {
					((Player) elem).canWin = false;
					System.out.println("You were caught cheating... you fate has changed\n");
					 return setDelay(elem);
				}else if(((Player) elem).isCheating == true && ((Player) elem).isVisible() == false) {
					return true;
				}else {
					return setDelay(elem);
						}
			}else if(elem instanceof Jarvis) {
				return true;
			}else {	
				return false;
			}
		}
		/**
		 * This method sets the delay time based on if the player
		 * has been caught cheating or not. If they have not cheated or been caught then
		 * they can still win and the delay isnt that bad. Otherwise their delay is doubled
		 * @param elem - element being changed will be instanceOf player
		 * @return - true mainly to help share function
		 */
		private boolean setDelay(Boardable elem) {
			if(((Player) elem).canWin == false) {
				((Player) elem).setDelay(10000);
				this.board.removeElement(this);
				return true;
			}else {
				((Player) elem).setDelay(5000);
				this.board.removeElement(this);
				return true;
			}
		}
		/**
		 * This method is the to string and will appear  blank to 
		 * not show the player where the traps are when the cells visibility is
		 * true
		 */
		public String toString() {
			return " ";
		}
	}