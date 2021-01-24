/**
 * This class implements question and creates a constructor to create a multiple choice question object
 * It returns the the different parts of the question so that the user can study
 * @author Samuel Swanson
 *
 */
public class QuestionMC implements Question{
	/**
	 * the question passed from the file
	 */
	String question;
	/**
	 * the answer passed from the file
	 */
	String answer;
	/**
	 * the points passed from the file
	 */
	int points;
	/**
	 * stores the questions parts
	 */
	String[] split;
	/**
	 * This constructor sets question, answer, and points to the passed in question, answer, and points
	 * It also splits up the question and stores it to split
	 * @param question - the question passed in from the user(current line from file)
	 * @param answer - the answer passed in from the user(current line from file)
	 * @param points - the points passed in from the user(current line from file)
	 */
	public QuestionMC(String question, String answer, int points) {
		this.question = question;
		this.answer = answer;
		this.points = points;
		split = question.split(";");
	}
	/**
	 * This method returns the question that the question has in the
	 * question format. This is more complicated from the other getQuestion objects because
	 * it needs to go through a loop and make each question, will not have more than S(19) questions
	 * 
	 * @return - returns the question in correct format
	 */
	public String getQuestion() {
		String ret = "Multiple Choice: ";
		String alphabet = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S";
		String[] splitAlphabet = alphabet.split(",");
		ret = ret + split[0] + "\n";
		int numberOfQuestions = Integer.parseInt(split[1]);
		for(int i = 0; i<numberOfQuestions; i++) {
			ret = ret + splitAlphabet[i] + ")" + split[i+2] + "\n";
		}
		return ret;
	}
	/**
	 * This method checks to see if the string passed in is the same as the
	 * answer that the question has
	 * @param ans - the users input on what they think the answer is
	 * @return - returns true if the answer is the same as the input
	 */
	
	public boolean isCorrect(String ans) {
		return ans.equalsIgnoreCase(this.answer);
	}

	/**
	 * This method returns the answer the question holds
	 * @return - returns the answer the question holds
	 */
	public String getCorrectAns() {
		return answer;
	}

	/**
	 * This method gets the points the question is worth
	 * @return - returns the points the question is worth
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * This method returns the question. This is used in questionMaker
	 * to see if the question is already in the array
	 * @return - returns the question within the question without the format
	 */
	public String questionChecker() {
		return this.split[0]; 
	}
	
}
