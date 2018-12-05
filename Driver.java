
/** @author Rafal & Matthew Klopfer
 *  @version 10/28/2018
 *  Lab 11
 * 
 *  This is the Driver file for Project2, the file is the main for the program that will use
 *  data from the MapData, Observation and Statistics class to output the weather statistics 
 *  for the day given to the Driver class. It will also be able to compare date times
 *  to make sure that they are older, same or newer than other date files
 */
import java.io.IOException;

import javax.swing.SwingUtilities;

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

		// Initialize the GUI
		SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                try
                {
                    new MesonetFrame("Mesonet Data Calculator");
                } catch (IOException e)
                {
                   
                    e.printStackTrace();
                }
            }
        });
		
		//final int YEAR = 2018;// Year of the data
		//final int MONTH = 8;// Month of the data
		//final int DAY = 30;// Day of the data
		//final int HOUR = 17;// Hour of the data
		//final int MINUTE = 45;// Minute of the data

		//final String directory = "data";// The file that holds the mdf files to be read

		//MapData mapData = new MapData(YEAR, MONTH, DAY, HOUR, MINUTE, directory);

		//mapData.parseFile();// Parse the file read
		//System.out.println(mapData.toString());// Print out the statistics calculated
	}
}