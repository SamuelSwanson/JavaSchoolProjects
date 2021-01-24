import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
/**
 * This class gets the file the user wants and gets each line
 * so that questionmaker can for its questions
 * @author Samuel Swanson
 *
 */
public class FileReader {
	/**
	 * this scanner scans or takes in files from the user
	 */
	private Scanner fileScanner;
	
	/**
	 * this method gets the wanted file from the user
	 */
	public FileReader(){
		JFileChooser chooser = new JFileChooser();//object of fileChooser so the user can find and select a file
        
        try {   // trys to get file wanted if no file is selected it throws otherwise it lets us no if no file is found or if something unusual happens
            if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION){
                throw new Error("Input file not selected");
            }
            File questionFile = chooser.getSelectedFile(); // stores the file to questionFile
            fileScanner = new Scanner(questionFile); // creates variable of the file selected used to get each line in the file
          
        } catch (FileNotFoundException e) {
            System.err.println("Data file not found.");
        } catch (Exception e) {
            System.err.println("A mysterious error occurred.");
            e.printStackTrace(System.err);
        }
	}
	
	/**
	 * tells us if the file has next line
	 * @return - returns boolean if the file has a next line
	 */
	public boolean fileHasNextLine(){
		return this.fileScanner.hasNextLine();
	}
	
	
	/**
	 * gets the next line from the file
	 * @return - returns the next line from the file
	 */
	public String getNextLineOfFile(){
		return this.fileScanner.nextLine();
	}
	
	
	/**
	 * when the file is empty it closes the scanner
	 */
	public void finalize(){
		try{
			this.fileScanner.close();
		}catch(Exception ex){
			 System.err.println("A mysterious error occurred on closing Scanner.");
	         ex.printStackTrace(System.err);
		}
	}
}