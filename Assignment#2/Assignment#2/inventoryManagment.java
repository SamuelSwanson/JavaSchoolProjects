import java.util.ArrayList;
/**
 * 
 * This class does the processing and communication between the SIC card and the user input.
 * It also creates an arraylist to hold all the SIC objects created.
 * @author Samuel Swanson
 *
 */
public class inventoryManagment {
	/**
	 * This is the arraylist that holds the SIC(stock index cards)
	 */
	private ArrayList<SIC> indexCard;
	/**
	 * Constructor used to start and instantiate the SIC arraylist it is called in userInput
	 */
	public inventoryManagment() {
		indexCard = new ArrayList<SIC>();
	}
	/**
	 * This method adds an SIC to the arraylist
	 * @param id - id passed from user input
	 * @param title - title passed from user input
	 * @param author - author passed from user input
	 * @param price - price passed from user input
	 * @param stock - stock passed from user input
	 */
	public void addSIC(int id, String title, String author, double price, int stock) {
		indexCard.add(new SIC(id,title,author,price,stock));
	}
	/**
	 * This removes an SIC from the arraylist
	 * @param position - passed id number of the card they want removed from the array
	 */
	public void removeSIC(int position) {
		indexCard.remove(searchForPositionInArray(position));//searchForPositionInArray can be found below but is used to find position in arraylist
	}
	/**
	 * This method increases the stock of the desired SIC in the arraylist
	 * @param id - id is passed from user to find the SIC they want to increase the stock
	 * @param stock - the desired amount they want to increase the stock by
	 */
	public void increaseStock(int id, int stock) {
		SIC temp = indexCard.get(searchForPositionInArray(id));//Temporary desired SIC used to increase the stock
		temp.increaseStock(stock);
	}
	/**
	 * This method returns the stock of the desired SIC
	 * Method is used so the stock will never fall below zero
	 * @param id - id passed from user to tell which stock they want to see
	 * @return returns the stock
	 */
	public int viewStock(int id) {
		SIC temp = indexCard.get(searchForPositionInArray(id));// Temporary desired SIC used to check the stock
		return temp.getStock();
	}
	/**
	 * This method decreases the stock of the desired SIC in the arraylist
	 * @param id - id passed from the user to find the SIC they want to decrease the stock
	 * @param stock - the desired amount they want to decrease the stock by
	 */
	public void decreaseStock(int id, int stock) {
		SIC temp = indexCard.get(searchForPositionInArray(id));//Temporary desired SIC used to decrease the stock
		temp.decreaseStock(stock);
	}
	/**
	 * Method returns the toString of the desired SIC in the arraylist
	 * @param id - id passed from the user to find the SIC they want to display
	 * @return returns the SIC toString based on the id passed
	 */
	public String displayById(int id) {
		SIC temp = indexCard.get(searchForPositionInArray(id));//Temporary desired SIC used to display toString
		return	 temp.toString();

	}
	/**
	 * Method returns the toString of the desired SIC or SIC's in the arraylist
	 * @param author - author passed from the user to find all SIC's with the same author
	 * @return returns the SIC or SIC's toString based on how many SIC's have the same author
	 */
	public String displayByAuthor(String author) {
		String ret = "";//This ensures that a string is returned and the "" is used to show if the author passed is not in any SIC's
		for(int i = 0; i<indexCard.size(); i++) { // For loop runs through all SIC's in the arraylist
			SIC temp = indexCard.get(i); // the temporary SIC card stored to check if author is the same as the author passed
			if(temp.sameAuthor(author) == true) {//checks if author is the same
				ret = ret + temp.toString(); // if author is the same its SIC toString is added to the ret string
			}
		}
		return ret;
	}
	/**
	 * Method returns the toString of the desired SIC or SIC's in the arraylist
	 * @param title - title passed from the user to find all SIC's with the same title
	 * @return the SIC or SIC's toString based on how many SIC's have the same title
	 */
	public String displayByTitle(String title) {
		String ret = "";//This ensures that a string is returned and the "" is used to show if the title passed is not in any SIC's
		for(int i = 0; i<indexCard.size(); i++) {// For loop runs through all SIC's in the arraylist
			SIC temp = indexCard.get(i);// the temporary SIC card stored to check if title is the same as the title passed
			if(temp.sameTitle(title) == true) {//checks if title is the same
				ret = ret + temp.toString();// if title is the same its SIC toString is added to the ret string
			}
		}
		return ret;
	}
	/**
	 * Method changes the price of the desired SIC in the arraylist
	 * @param id - id passed from the user to find the SIC with that id
	 * @param price - the new price that the user wants to change the SIC's price to
	 */
	public void changePrice(int id, double price) {
		SIC temp = indexCard.get(searchForPositionInArray(id));//Temporary desired SIC used to change the price of SIC
		temp.changePrice(price);
	}
	/**
	 * Method searches for the position in the arraylist that holds the SIC id passed
	 * @param id - id passed from user to check if it is in the array or not
	 * @return returns a location or a -1 based on if the id is in on of the SIC's in the arraylist
	 */
	public int searchForPositionInArray(int id) {
		int check = -1;//initial value -1 used to almost be used as a boolean the -1 declares there is no id an SIC in the Arraylist
		for(int i = 0; i<indexCard.size(); i++) {//goes through entire arraylist 
			SIC temp = indexCard.get(i);//temporary SIC based on position in the array
			if(temp.sameId(id) == true) {//if id is the same as temporary SIC the position is saved to variable check
				check = i;
				i = indexCard.size();//since all ids are unique it is not necessary to continue the for loop so I end it if an id is found
			}
		}
		return check;
	}
	/**
	 * returns a string of all of the SIC toStrings in the array
	 */
	public String toString() {
		String ret = "Displaying all books: \n";
		if(indexCard.size() > 0) {//checks to see if array is empty or not
		for(int i = 0; i<indexCard.size(); i++) {//runs through the arraylist
			SIC temp = indexCard.get(i);//temporary SIC used based on position
			ret = ret + temp.toString();//string adding all the SIC to strings together
			}
		}else {
			ret = ret + " The inventory is empty";
		}
		return ret;
	}
}
