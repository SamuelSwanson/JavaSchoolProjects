/**
 * This interface is used to establish what each question will
 * implement
 * @author Samuel Swanson
 *
 */
public interface Question {
	/**
	 * This method returns the question that the question has in the
	 * question format
	 * @return - returns the question
	 */
	public String getQuestion();
	/**
	 * This method checks to see if the string passed in is the same as the
	 * answer that the question has
	 * @param ans - the users input on what they think the answer is
	 * @return - returns true if the answer is the same as the input
	 */
	public boolean isCorrect(String ans);
	/**
	 * This method returns the answer the question holds
	 * @return - returns the answer the question holds
	 */
	public String getCorrectAns();
	/**
	 * This method gets the points the question is worth
	 * @return - returns the points the question is worth
	 */
	public int getPoints();
	/**
	 * This method returns the question. This is used in questionMaker
	 * to see if the question is already in the array
	 * @return - returns the question within the question without the format
	 */
	public String questionChecker();
	
}
