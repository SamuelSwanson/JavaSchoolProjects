import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * This class has the board and cell classes. It is used to 
 * regulate the board and cells in the game
 * @author Samuel Swanson
 *
 */
public class Board {

	/**
	 * the board that holds cells.
	 */
	private Cell[][] board;
	/**
	 * the height of the board
	 */
	private int height;
	/**
	 * the width of the board
	 */
	private int width;
	/**
	 * stores the location of where the element(s) is
	 */
	private Map<Boardable,Cell> elementPlace = new HashMap<Boardable,Cell>();
	/**
	 * location of cells to reset for the shake method
	 */
	private List<Cell> elementsToShake = new ArrayList<Cell>();
	/**
	 * constructor creates the board and sets it to the height and width 
	 * sent in by the user.
	 * Will throw if the board is out of bounds
	 * @param height - height user wants the board to be
	 * @param width - width user wants the board to be
	 */
	public Board(int height, int width) {
		this.height = height;
		this.width = width;
		if(height <= 0 || height > 100 || width <= 0 || width > 100) {
			throw new IllegalArgumentException();
		}
		board = new Cell[height][width];
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				board[i][j] = new Cell(i, j);
			}
		}
	}
	/**
	 * if the element is not of the board(in the hashmap) then
	 * it will throw otherwise it will make the changes to the location 
	 * of the stylus
	 * @param dir - direction the user wants the stylus to go
	 * @param elem - element the user want to move
	 * @return - returns boolean if the move can or cant happen
	 */
	public boolean Move(Direction dir, Boardable elem) {
		if(!elementPlace.containsKey(elem)) {
			throw new IllegalArgumentException();
		}
		boolean ret = false;
		if(dir.toString().equalsIgnoreCase("UP")) {//the equalsIgnoreCase is overkill but checking all the boxes
			ret = makeChanges(elem, -1, 0);
		}else if(dir.toString().equalsIgnoreCase("DOWN")) {
			ret = makeChanges(elem, 1, 0);
		}else if(dir.toString().equalsIgnoreCase("LEFT")) {
			ret = makeChanges(elem, 0, -1);
		}else if(dir.toString().equalsIgnoreCase("RIGHT")) {
			ret = makeChanges(elem, 0, 1);
		}else if(dir.toString().equalsIgnoreCase("UP_LEFT")) {
			ret = makeChanges(elem, -1, -1);
		}else if(dir.toString().equalsIgnoreCase("UP_RIGHT")) {
			ret = makeChanges(elem, -1, 1);
		}else if(dir.toString().equalsIgnoreCase("DOWN_LEFT")) {
			ret = makeChanges(elem, 1, -1);
		}else if(dir.toString().equalsIgnoreCase("DOWN_RIGHT")) {
			ret = makeChanges(elem, 1, 1);
		}else if(dir.toString().equalsIgnoreCase("VISIBILITY")) {
			Cell temp = elementPlace.get(elem);
			temp.removeElement(elem);
			ret = placeElement(elem, temp.row,temp.col);
		}else if(dir.toString().equalsIgnoreCase("SHAKE")) {
			shake(elem);
		}
		return ret;
		
	}
	/**
	 * method used to shake and reset the board. Intentionally not
	 * perfect because etch-a-sketches take a while to clear
	 * @param elem - ensures the element will not be covered with #
	 */
	private void shake(Boardable elem) {
		if(elementsToShake.size()>0) {
			for(int i = 0; i< ((elementsToShake.size())/2)+1; i++) {
				Cell temp = elementsToShake.get(i);
				if(temp != elementPlace.get(elem)) {
					elementsToShake.remove(i);
					temp.isVisible = false;
				}
			}
		}
	}
	/**
	 * makes the changes to the board and sets the new location to the element passed in
	 * @param elem - element user wants to move
	 * @param rowChange - where the element needs to move on the column
	 * @param colChange - where the element needs to move on the row
	 * @return - returns a boolean if the move can be made
	 */
	private boolean makeChanges(Boardable elem, int rowChange, int colChange) {
		Cell temp = elementPlace.get(elem);
		int row = temp.getRow()+rowChange;//could be done temp.row
		int col = temp.getCol()+colChange;//could be done temp.col
		if((row >= 0 && row < this.height) && (col >= 0 && col < this.width)) {
			temp.removeElement(elem);
			if(temp.isVisible == true) {
				elementsToShake.add(temp);
			}
			placeElement(elem, row, col);
			return true;
		}
		return false;
	}
	/**
	 * adds the element to the board and updates its location
	 * in the element place map
	 * @param elem - element passed to update
	 * @param row - new row passed
	 * @param col - new column passed
	 * @return - returns true if the element can be updated
	 */
	public boolean placeElement(Boardable elem, int row, int col) {
		board[row][col].addElement(elem);
		elementPlace.put(elem, board[row][col]);
		return true;
		
	}
	/**
	 * prints the board by calling the to string of each cell in the board
	 */
	public void printBoard() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				System.out.print(board[i][j].toString());
			}
			System.out.print("\n");
		}
	}
	/**
	 * the private class cell which is the individual cells
	 * for the board
	 * @author Samuel Swanson
	 *
	 */
	private class Cell{
		/**
		 * the row the cell is in
		 */
		private int row;
		/**
		 * the column the cell is in
		 */
		private int col;
		/**
		 * the visibility the cell has
		 */
		private boolean isVisible;
		/**
		 * the elements within the cell
		 */
		private ArrayList<Boardable> elements = new ArrayList<Boardable>();
		/**
		 * the constructor used to create the cell
		 * it takes in the row and column passed in and
		 * automatically sets the cell to no be visible
		 * @param row - row passed in
		 * @param col - column passed in
		 */
		public Cell(int row, int col) {
			this.row = row;
			this.col = col;
			this.isVisible = false;	
		}
		/**
		 * not needed but it is the getter to get the row the cell has
		 * @return - returns the row the cell is in
		 */
		private int getRow() {
			return row;
			
		}
		/**
		 * not needed but it is the getter to get the column the cell has
		 * @return - returns the column the cell is in
		 */
		private int getCol() {
			return col;
		} 
		/**
		 * adds the element passed in to the cell and sets the cell
		 * visibility based on the elements visibility
		 * @param elem - element passed in to be placed in the cell
		 */
		public void addElement(Boardable elem) {
			elements.add(elem);
			if(isVisible == false) {
				isVisible = elem.isVisible();
			}else if (isVisible == true && elem.isVisible() == false) {
				isVisible = true;
			}
			
		}
		/**
		 * removes the element from the cell
		 * @param elem - element that is removed from the cell
		 * @return - returns a boolean if the element can be removed or not
		 */
		public boolean removeElement(Boardable elem) {
			return elements.remove(elem);
		}
		/**
		 * to string used to assign the symbol that should appear in the cell
		 */
		public String toString() {
			String ret = "";
			if(!this.isVisible) {
				ret = "#";// returned if the visibility is false
			}else if(elements.isEmpty() || (!elements.isEmpty() && elements.get(0).isVisible() == false)) {
				ret = " ";// if the visibilty is true and there is no element inside or if the elements visibility is false and the 
				//cells vis is true then it will return this
			}else{
			 ret = elements.get(elements.size()-1).toString();//otherwise it will return the stylus to string symbol
			}
			return ret;
		}
			
	}

}
