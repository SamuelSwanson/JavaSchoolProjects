/**
 * This class creates the constructor to create the SIC object. It returns, adjusts, and returns to string
 * of all of the class variables in the SIC
 * 
 * @author Samuel Swanson
 *
 */

public class SIC {
	/**
	 *  stores the id of the Stock index card
	 */
	private int id;
	/**
	 *  stores the title of the Stock index card
	 */
	private String title;
	/**
	 *  stores the author of the Stock index card
	 */
	private String author;
	/**
	 * stores the price of the Stock index card
	 */
	private double price;
	/**
	 * stores the quantity of books in the Stock index card
	 */
	private int stock;
	/**
	 * constructor takes in input and instantiates the user input to the class variables
	 * @param id - user input of the id they wanted the SIC to have
	 * @param title - user input of the title they wanted the SIC to have
	 * @param author - user input of the author they wanted the SIC to have
	 * @param price - user input of the price they wanted the SIC to have
	 * @param stock - user input of the stock amount they wanted the SIC to have
	 */
	public SIC(int id, String title, String author, double price, int stock) {
	this.id = id;
	this.title = title;
	this.author = author;
	this.price = price;
	this.stock = stock;
	}
	/**
	 * gets class variable id and returns it
	 * @return returns class variable id
	 */
	public  int getId() {
		return id;
	}
	/**
	 * gets class variable title and returns it
	 * @return returns class variable title
	 */
	public  String getTitle() {
		return title;
	}
	/**
	 * gets class variable author and returns it
	 * @return returns class variable author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * gets class variable price and returns it
	 * @return returns class variable price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * gets class variable stock and returns it
	 * @return returns class variable stock
	 */
	public int getStock() {
		return stock;
	}
	/**
	 * method that increase the class variable stock by the amount passed in by the user
	 * @param increaseStock - This variable holds the value that the user wants to increase the stock by.
	 */
	public void increaseStock(int increaseStock) {
		stock = stock + increaseStock;
	}
	/**
	 * method that decreases the class variable stock by the amount passed in by the user
	 * @param decreaseStock - This variable holds the amount that the user wants to decrease the stock by.
	 */
	public void decreaseStock(int decreaseStock) {
			stock = stock - decreaseStock;
	}
	/**
	 * method that changes the price of the variable price with the new price passed from the user
	 * @param newPrice - This variable holds the amount the user wants the new price to be
	 */
	public void changePrice(double newPrice) {
			price = newPrice;			
	}
	/**
	 * method that checks to see if the passed id and the class id are the same
	 * @param id - id passed from the user 
	 * @return returns true of false to tell if the id is the same
	 */
	public boolean sameId(int id) {
		return this.id == id;
	}
	/**
	 * method that checks to see if the passed author and the class author are the same
	 * @param author - author passed from the user
	 * @return returns true and false to tell if the author is the same
	 */
	public boolean sameAuthor(String author) {
		return this.author.equalsIgnoreCase(author);
	}
	/**
	 * method that checks to see if the passed author and the class author are the same
	 * @param title - title passed from the user
	 * @return returns true and false to tell if the title is the same
	 */
	public boolean sameTitle(String title) {
		return this.title.equalsIgnoreCase(title);
	}
	/**
	 * string passed to display all of the contents in the stock index card
	 */
	public String toString() {
		String ret = "\n";
		ret = ret + "Stock Index Card " + id + "\n";
		ret = ret + "  Title:" + title + "\n";
		ret = ret + "  Author:" + author + "\n";
		ret = ret + "  Price:" + price + "\n";
		ret = ret + "  Quantity:" + stock + "\n";
		return ret;
		
	}
}
