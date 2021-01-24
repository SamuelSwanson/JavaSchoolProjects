import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
/**
 * This class runs the studying. It makes an object of itself in order to start studying
 * Then it creates takes in a file and asks the questions to the user. It also keeps track of 
 * the users score. So in short it runs the show.
 * 
 * @author Samuel Swanson
 *
 */
public class StudyBuddy {
	/**
	 * The scanner is used to take in user input in order for them to study
	 */
	private static Scanner input = new Scanner(System.in);
	/**
	 * This is the players score and it is initially set/(gets) to 0 because why would they start anywhere else
	 */
	private  int score = 0;
	/**
	 * This is the arraylist that stores the questions from the user
	 */
	private  ArrayList<Question> myQuestions= new ArrayList<Question>();
	/**
	 * This keeps track of the total possible high-score. Used to calculate
	 * the percentage the user gets
	 */
	private  double pointTotal = 0;
	/**
	 * The main class that initiates and gets the program running, if
	 * it were its own class it would be the driver because it drives the
	 * program. It creates object studybuddy and calls the method study to
	 * begin studying
	 * @param args - java command line arguments
	 */
	public static void main(String[] args) {
		System.out.print("Welcome to StudyBubby! Press enter when you are ready to select the file holding your study questions.");
		String userInput = input.nextLine();// user input anything besides enter will result in not running study buddy
		if(userInput.equals("")) {
			StudyBuddy studyBuddy = new StudyBuddy();//object of study buddy helps avoid static everywhere 
			studyBuddy.study();//calls study to start studying
		}else {
			System.out.print("GoodBye");
		}
	}
	/**
	 * Study first creates an object of question maker, gets the questions add
	 * from the user and stores them to the question arraylist myQuestions.
	 * If the arraylist has questions it will ask the user the questions 
	 * keep track of score and print results. Otherwise it will end the program
	 * since the user did not load in any questions.
	 */
	public void study() {
		QuestionMaker qm = new QuestionMaker();//object of type QuestionMaker
		myQuestions = qm.getQuestions(); // creates questions and stores them to the quesiton arraylist
		System.out.println("Thank you");
		if(myQuestions.size() != 0) {
			shuffleOrder();//shuffles arraylist
			int howManyQuestions = 0;// the amount of questions the user wants to answer from the arraylist gets 0 to start
			ArrayList<Question> arrayOfQuestionsWanted = new ArrayList<Question>();//arraylist of the amount of questions they wanted to answer
			int correctAnswers = 0;//keeps track of how many questions they get correct again gets 0
			howManyQuestions = askHowManyQuestions(howManyQuestions);//asks user for amount of quesitons wanted and sets it to the returned value
			arrayOfQuestionsWanted = setArrayOfQuestions(howManyQuestions, arrayOfQuestionsWanted);//sets arraylist of the amount of questions wanted
			correctAnswers = askQuestions(arrayOfQuestionsWanted, correctAnswers);// asks the questions,keeps track of score and returns the amount they got right
			printResults(howManyQuestions, correctAnswers);// prints the results to the user
		}else {
			System.out.println("There are no questions goodbye");
		}

	}
	/**
	 * Shuffles the order of where the questions are in the array
	 * This makes the questions drawn randomly
	 */
	private void shuffleOrder() {
		Random rand = new Random();
		Question temp;// temporary question holder
		for(int i = 0;  i<10_000; i++) {//cycles through the arraylist 10_000 times to randomize arraylist
			temp = myQuestions.remove(0);
			int position = rand.nextInt(myQuestions.size()+1);
			myQuestions.add(position, temp);
		}
	}
	/**
	 * This method asks how many questions the user wants, try catch to catch the wrong type entered
	 * 
	 * @param howManyQuestions - passes in the original how many questions just so there is no need to 
	 * create a duplicate variable
	 * @return - returns how many questions the user wants to answer
	 */
	private int askHowManyQuestions(int howManyQuestions) {
		boolean checker = true;// runs while user does not enter a response either of type int or if they
		// want to many to many or to little questions than the arraylist actually has
		while(checker) {
			System.out.println("How many questions would you like to answer?");
			try {// trys to get next int, if string is entered it asks again and if they enter an int less than 0 or greater than the
				// arraylist size it will not take there input and will ask again
				howManyQuestions = input.nextInt();
				if(howManyQuestions > myQuestions.size() || howManyQuestions < 0) {
					System.out.print("\n");
					System.out.print("Sorry but you have only loaded " + myQuestions.size() +" questions. ");
				}else {
					input.nextLine();
					System.out.print("\n");
					checker = false;// ends loop once howManyQuestions is finally able to be set
				}
			}catch(InputMismatchException b){
				System.out.print("\n");
				System.out.println("Sorry, that is not a valid input.");
				input.nextLine();
			}catch (Exception e) {
	            System.err.println("A mysterious error occurred.");
	            e.printStackTrace(System.err);
	        }
		}
		return howManyQuestions;
	}
	/**
	 * Goes through the myQuestions array for the amount of questions wanted and adds them to the arrayOfQuestionsWanted arraylist
	 * @param howManyQuestions - the amount of questions the user wants to answer
	 * @param arrayOfQuestionsWanted - the array containing the questions based on the amount of questions the user wants to 
	 * answer(it is passed in empty and is filled)
	 * @return - returns the filled arraylist of quesitons based on the amount of questions the user wanted to answer
	 */
	private ArrayList<Question> setArrayOfQuestions(int howManyQuestions, ArrayList<Question> arrayOfQuestionsWanted){
		for(int i = 0; i<howManyQuestions; i++) {
			Question temp = myQuestions.get(i);
			arrayOfQuestionsWanted.add(temp);
		}
		return arrayOfQuestionsWanted;
	}
	/**
	 * This method asks the questions from the arrayOfQuestionsWanted, keeps score and track of how many correct answers they get
	 * @param arrayOfQuestionsWanted - the array with the questions to be asked
	 * @param correctAnswers - if they get it correct, the correctAnswers will add one to itself(users amount of correct ans)
	 * @return - returns the amount of correct answers the user gets
	 */
	private int askQuestions(ArrayList<Question> arrayOfQuestionsWanted, int correctAnswers) {
		String answer = "";// the users answer
		for(int i = 0; i<arrayOfQuestionsWanted.size(); i++) {// for loop runs through the whole arrayOfQuestionsWanted
			Question temp = arrayOfQuestionsWanted.get(i);// temporary question from position i of arrayOfQuestionsWanted
			System.out.println("Points: "+ temp.getPoints());
			System.out.print(temp.getQuestion());
			answer = input.nextLine();
			answer.trim();
			if(temp.isCorrect(answer)) {//answer is correct
				System.out.println("Correct! You get " + temp.getPoints() + " points.\n");
				score = score + temp.getPoints();
				correctAnswers = correctAnswers + 1;
				pointTotal = pointTotal + temp.getPoints();
			}else if(answer.equalsIgnoreCase("delay")) {// they want to wait to answer
				System.out.println("Ok, I will ask that one later.\n");
				arrayOfQuestionsWanted.add(temp);
			}else if(answer.equalsIgnoreCase("pass")) {// they want to skip the question
				System.out.println("Ok, let's skip that one.\n");
				pointTotal = pointTotal + temp.getPoints();
			}else {// anything else so incorrect answers
				System.out.println("Incorrect, the answer was "+ temp.getCorrectAns() + ". You lose " + temp.getPoints() +" points\n");
				score = score - temp.getPoints();
				pointTotal = pointTotal + temp.getPoints();
			}
			
		}
		return correctAnswers;
	}
	/**
	 * this method prints the results after the user has answered all the questions
	 * @param howManyQuestions - how many questions they answered
	 * @param correctAnswers - how many quesitons they got correct
	 */
	private void printResults(int howManyQuestions, int correctAnswers) {
		System.out.println("Of the " + howManyQuestions + " questions you attempted, you got " +correctAnswers + " correct.");
		System.out.println("You earned a total of "+ score + " points.");
		double ratio = 0;// the percent total of their results (percentage of points not how many questions were answered correctly
		ratio = (score/pointTotal);
		if(ratio<.70) {// if else statements based on the percent they got correct
			System.out.print("Better Luck Next Time");
		}else if(ratio >=.70 && ratio <= .90) {
			System.out.print("You can do better");
		}else {
			System.out.print("Good Job");
		}
	}
}
