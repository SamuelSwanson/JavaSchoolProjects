/**
 * Assignment #2
 * Drives Stock index card management
 * This class makes object of user input and calls the menu to start up the inventory
 * @author Samuel Swanson
 *
 */

public class driver {
	/**
	 * This method runs the whole program, it creates the object to start off the inventory manager 
	 * @param args
	 */
	public static void main(String[] args) {	
		userInput options = new userInput();//object options created from userInput constructor
		options.menu(); 
	}

	}
