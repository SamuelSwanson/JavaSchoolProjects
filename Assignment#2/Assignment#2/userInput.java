import java.util.Scanner;
/**
 * This class is used to instantiate the inventoryManagment constructor. It runs the show. Offers the options to manage 
 * the SIC's. Takes in inputs from scanner and ends the program
 * @author Samuel Swanson
 *
 */
public class userInput {
	/**
	 * scanner used to get user inputs
	 */
	private Scanner input = new Scanner(System.in);
	/**
	 * This is the object used to manage the SIC's in the arraylist
	 */
	private inventoryManagment manage;
		/**
		 * Constructor used to start management in the program
		 */
		public userInput() {
			manage = new inventoryManagment();
		}
		/**
		 * Displays the menu options and calls two methods to start user input
		 */
		public void menu() {
			System.out.println("Please select a number from the following options:");
			System.out.println("1) Add new Stock Index Card");
			System.out.println("2) Remove Stock Index Card");
			System.out.println("3) Increase Stock by SIC-ID");
			System.out.println("4) Decrease Stock by SIC-ID");
			System.out.println("5) Display Stock Index Card by SIC-ID");
			System.out.println("6) Display Stock Index Card by Author");
			System.out.println("7) Display Stock Index Card by Title");
			System.out.println("8) Display All Stock Index Cards");
			System.out.println("9) Change price by SIC-ID");
			System.out.println("10) Quit");
			choiceSwitch(numberInput());
		}
		/**
		 * this takes in the next double value casts to an int and ensures it is within the parameters
		 * @return returns int when it is finally withing the parameters of the menu choices
		 */
		private int numberInput() {
			double cast;//double used to cast inputs from user
			int userInput;// the variable stores the cast variable as an int
			cast = input.nextDouble(); 
			userInput =	(int)cast;
			do {
				if(userInput<1 || userInput>10) {
					System.err.println("Error: the value must be between 1 and 10.");
					menu();
					cast = input.nextDouble();
					userInput = (int)cast;
				}
			}while(userInput<1 || userInput>10);//Runs while the userinput is not within menu parameters
			return userInput;
			
		}
		/**
		 * Method that asks for id and checks to see if desired id is already in arraylist. If it is the user is notified.
		 * If not the methods asks for the the rest of required information and passes it to inventoryManagment 
		 */
		private void makeSic() {
			System.out.println("Please enter the new SIC-ID: ");
			int id = input.nextInt();//id the user wants SIC to have
			 input.nextLine();
			if(manage.searchForPositionInArray(id) == -1) {
				System.out.println("Please enter the title of the book: ");
				String title = input.nextLine();//title the user wants SIC to have
				title = title.trim();
				System.out.println("Please enter the author of the book: ");
				String author = input.nextLine();//author the user wants SIC to have
				author = author.trim();
				System.out.println("Please enter the price of the book: ");
				double price = input.nextDouble();//price the user wants the SIC to have
				System.out.println("Please enter the number of the books in inventory: ");
				int stock = input.nextInt();//the stock the user wants the SIC to have
				manage.addSIC(id, title, author, price, stock);
			}else {
				System.err.println("Error: SIC-ID " + id +" is already in the system.");
			}
			
		}
		/**
		 * Method passes the desired id that the user wants to remove from the arraylist
		 */
		private void removeSic() {
			System.out.println("Please enter the SIC-ID you would like to remove: ");
			int id = input.nextInt();//id of SIC user wants to remove
			if(manage.searchForPositionInArray(id) != -1) {
				manage.removeSIC(id);
			}else {
				System.err.println("Error: SIC-ID " + id +" is not in the system.");
			}
			
		}
		/**
		 * Method asks for id and stock from user which is passed to inventoryManagment to increase stock
		 */
		private void stockIncrease() {
			System.out.println("Please enter the SIC-ID: ");
			int id = input.nextInt();// id of the SIC the user wants to change the stock for
			if(manage.searchForPositionInArray(id) != -1) {
			System.out.println("Please enter the amount you want to increase the stock by: ");
			int stock = input.nextInt();// the amount the user wants to increase the stock by
			if(stock >= 0) {
				manage.increaseStock(id, stock);
				}else {
					System.err.println("Error:  unable to increase the inventory amount");
				}
			}else {
				System.err.println("Error: SIC-ID " + id +" is not in the system.");
			}
		}
		/**
		 * Method asks for id and stock from user which is passed to inventoryManagment to decrease stock
		 */
		private void stockDecrease() {
			System.out.println("Please enter the SIC-ID: ");
			int id = input.nextInt();//the id of the SIC the user wants to change the stock for 
			if(manage.searchForPositionInArray(id) != -1) {
			System.out.println("Please enter the amount you want to decrease the stock by: ");
			int stock = input.nextInt();//the amount the user wants to decrease the stock by
			if(stock >= 0 && stock <= manage.viewStock(id)) {
				manage.decreaseStock(id, stock);
				}else {
					System.err.println("Error:  unable to decrease the inventory amount");
				}
			}else {
				System.err.println("Error: SIC-ID " + id +" is not in the system.");
			}
		}
		/**
		 * Method asks for id of the SIC user wants to display
		 * and passes it to inventoryManagment
		 */
		private void idDisplay() {
			System.out.println("Displaying book by SIC-ID: ");
			System.out.println("Please enter the SIC-ID: ");
			int id = input.nextInt();//id of the SIC that the user wants to display
			if(manage.searchForPositionInArray(id) != -1) {
				System.out.println(manage.displayById(id));
			}else {
				System.err.println("Error: SIC-ID " + id +" is not in the system.");
			}
		}
		/**
		 * Method asks for the author in the SIC that the user wants to display
		 * and passes it to inventoryManagment
		 */
		private void authorDisplay() {
			System.out.println("Displaying book by Authors: ");
			System.out.println("Please enter the Author: ");
			input.nextLine();
			String author = input.nextLine();//author of the SIC or SIC's that the user wants to display
			author = author.trim();
			if(manage.displayByAuthor(author).equals("")) {
				System.err.println("Error: SIC-Author " + author +" is not in the system.");
			}else {
				System.out.println(manage.displayByAuthor(author));
			}
		}
		/**
		 * Method asks for the title in the SIC that the user wants to display
		 * and passes it to inventoryManagment
		 */
		private void titleDisplay() {
			System.out.println("Displaying book by Title: ");
			System.out.println("Please enter the Title: ");
			input.nextLine();
			String title = input.nextLine();//title of the SIC or SIC's that the user wants to display
			title = title.trim();
			if(manage.displayByTitle(title).equals("")) {
				System.err.println("Error: SIC-Title " + title +" is not in the system.");
			}else {
			System.out.println(manage.displayByTitle(title));
			}
		}
		/**
		 * Method asks for id and new price that the user wants the SIC to have
		 * and passes it to the inventoryManagment
		 */
		private void priceChange() {
			System.out.println("Please enter the SIC-ID: ");
			int id = input.nextInt();//id of the SIC the user wants access
			if(manage.searchForPositionInArray(id) != -1) {
			System.out.println("Please enter the new price: ");
			double price = input.nextDouble();//new price the user wants the SIC to have
				if(price >= 0) {
					manage.changePrice(id,price);			
				}else {
					System.err.println("Error:  unable to change the price");			
				}
		    }else {
			System.err.println("Error: SIC-ID " + id +" is not in the system.");
		    }
		}
		/**
		 * Method uses a switch to drive the user inputs
		 * calls the helper methods
		 * @param number - number the user inputed from menu
		 */
		private void choiceSwitch(int number) {
			switch (number) {
			case 1:
				makeSic();
				menu();
				break;
			case 2:
				removeSic();
				menu();
				break;
			case 3:
				stockIncrease();
				menu();
				break;
			case 4:
				stockDecrease();
				menu();
				break;
			case 5:
				idDisplay();
				menu();
				break;
			case 6:
				authorDisplay();
				menu();
				break;
			case 7:
				titleDisplay();
				menu();
				break;
			case 8:
				System.out.println(manage.toString());
				menu();
				break;
			case 9:
				priceChange();
				menu();
				break;
			case 10:
				System.out.println("Thank you for using Inventory Incredible");
				break;
			}
		}
}
