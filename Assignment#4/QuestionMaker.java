import java.util.ArrayList;
/**
 * This class makes the questions from the selected file and stores them to an array that is passed to
 * study buddy so that the user can study the questions
 * @author Samuel Swanson
 *
 */
public class QuestionMaker {

	/**
	 * This method creates the object of fileReader type, creates the arraylist of questions from
	 * the file the user selected. In order to make the questions it checks the format of the lines if it does
	 * not meet certain requirements the line will not be used and it will check until the file has no more lines
	 * @return - returns the arraylist of questions that it was able to form
	 */
	public ArrayList<Question> getQuestions() {
		FileReader fr = new FileReader();// object of fileReader type
		ArrayList<Question> questionsToAsk = new ArrayList<Question>();// arraylist that will store the questions to ask
		while(fr.fileHasNextLine()) {// runs while the file has a next line
			String curline = fr.getNextLineOfFile();// curline is the files next line
			String[] split = curline.split(";");// split splits the file based on the ;
			int points = 0;// the amount of points the question has
			if(split.length >= 4) {// this ensures that the split length has at least 4 ; if not it rejects the line
				if(checkForSameLine(split[1], questionsToAsk) == false) {//checks to see if the same question is already in the arraylist
					points = checkForPoints(split, curline, points);// checks to make sure the points are correct and that the TF and MC has the correct structure
					if(split[0].equals("TF") && split.length == 4 && answerChecker(split) == true) {// determines if it is a true false question if so it adds it to the array
						Question trueFalse = new QuestionTF(split[1],split[2],points);
						questionsToAsk.add(trueFalse);
					}else if(split[0].equals("MC") && split.length >5 && split.length-5 < 20 && answerChecker(split) == true){// determines if it is a Multi choices question and adds it to the array
						String question = "";
						for(int i = 1; i <= split.length-3; i++) {
							question = question + split[i]+ ";";
						}
						Question multi = new QuestionMC(question,split[split.length-2],points);
						questionsToAsk.add(multi);
					}else if(split[0].equals("FB") && split.length == 4) {// determines if it is a fill in the blank and adds it to the array if yes
						Question fill = new QuestionFB(split[1],split[2],points);
						questionsToAsk.add(fill);
					}else {
						System.out.println("\tInvalid format, cannot form a question out of this line: "+curline+"\n");
					}
				}else {
					System.out.println("\tThis question is already in the system: "+split[1]+"\n");
				}
			}else {
				System.out.println("\tInvalid format, cannot form a question out of this line: "+curline+"\n");
			}

		}
		fr.finalize();// ends the fileReader scanner because it is no longer needed
		return questionsToAsk;
	}
	/**
	 * This method goes through the arraylist to see if the same question is already in the system
	 * @param curline - is the question from the current line from the file
	 * @param questionsToAsk - is the array of all the questions already in the arraylist
	 * @return - returns true if the file is in the arraylist and false otherwise
	 */
	private boolean checkForSameLine(String curline, ArrayList<Question> questionsToAsk) {
		for(int i = 0; i<questionsToAsk.size(); i++) {
			String curQuestion = questionsToAsk.get(i).questionChecker();
			if(curline.equals(curQuestion)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * This method checks to see if the question has the correct format and points can become an int, if not it assigns a 
	 * default number of 5 to points. It also checks to make sure the MC questions number of questions is correct if it isnt
	 * it sets it to the correct number
	 * @param split - the split array of the current line
	 * @param curline - the current line being used
	 * @param points - the points that the question has
	 * @return - returns the amount of points the question is worth
	 */
	private int checkForPoints(String[]split, String curline, int points) {
		try {
			points = Integer.parseInt(split[split.length-1]);
			if(split[0].equals("MC")){
				try {
					int numberOfQuestions = Integer.parseInt(split[2]);
					if(numberOfQuestions != split.length-5) {
						System.out.println("\tInvalid format, calculating amount of questions in: "+curline+"\n");
						split[2] = String.valueOf(split.length-5);
					}
				}catch(NumberFormatException a) {
					System.out.println("\tInvalid format, calculating amount of questions in: "+curline+"\n");
					split[2] = String.valueOf(split.length-5);
				}catch (Exception a) {
		            System.err.println("A mysterious error occurred.");
		            a.printStackTrace(System.err);
		        }
			}
		}catch(NumberFormatException e) {
			System.out.println("\tInvalid format, assigning default points of 5 to: "+curline+"\n");
			points = 5;
		}catch (Exception e) {
            System.err.println("A mysterious error occurred.");
            e.printStackTrace(System.err);
        }
		return points;
	}
	/**
	 * This method checks the format of MC and TF questions. It makes sure TF questions have the answer true or false.
	 * For Multiple choice questions it makes sure the answer is a letter and that letter in fact in the question or possible answers
	 * @param split - the split line arraylist of the current line
	 * @return - returns true if it is in the correct format and false otherwise
	 */
	private boolean answerChecker(String[] split) {
		if(split[0].equals("MC")) {
			String alphabet = "ABCDEFGHIJKLMNOPQRS";// used to check if answer correct for MC questions
			if(alphabet.indexOf(split[split.length-2].toUpperCase()) <= Integer.parseInt(split[2])-1 && alphabet.indexOf(split[split.length-2].toUpperCase()) >= 0){
				return true;
			}
		}else{
			if(split[2].trim().equals("false") || split[2].trim().equals("true")) {
				return true;
			}
		}
		return false;
	}
}
