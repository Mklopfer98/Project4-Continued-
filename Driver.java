
/** @author Rafal & Matthew Klopfer
 *  @version 12/5/2018
 *  Lab 11
 * 
 *  This is the Driver file for Project2, the file is the main for the program that will use
 *  data from the MapData, Observation and Statistics class to output the weather statistics 
 *  for the day given to the Driver class. It will also be able to compare date times
 *  to make sure that they are older, same or newer than other date files
 */
import java.io.IOException;

/**
 * This is the main Class for the program all the things needed to run the
 * program will be called from within this Class
 * 
 * @author rafal
 * @return nothing
 */
public class Driver {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		// Initialize the GUI and run it, this is all we should have to do!!
		new MesonetFrame("Mesonet Data Calculator");

	}
}