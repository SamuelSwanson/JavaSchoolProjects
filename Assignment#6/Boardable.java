/**
 * interface used for the boardable elements
 * @author Samuel Swanson
 *
 */
public interface Boardable {
	/**
	 * method declaring if it is visible or not
	 * @return - returns the visibility type
	 */
	public boolean isVisible();
	/**
	 * This method tells if the element can share with the passed element
	 * @param elem - the element that wants to share with the current element in the cell
	 * @return - true or false based on if it can share
	 */
	public boolean share(Boardable elem);
	/**
	 * the to string of what the element looks like
	 * @return - the symbol or to string of the element
	 */
	public String toString();
}
