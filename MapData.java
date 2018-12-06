
/** @author Matthew Klopfer
 *  @author Connor Smith (Collaborated)
 *  @author Alexi Musick (Collaborated)
 *  @author Reece Reinke (Collaborated)
 *  @author Punit Bhakta (Collaborated)
 *  @version 10/28/2018
 *  Lab 11
 * 
 *  MapData is a class that reads off of a mdf file given to it by the data in the Driver class,
 *  the class then constructs the file to read from this information, and parses it into 3 arrayList
 *  of Observation objects. The class will then compute data for the averages, maximums and minimums
 *  for solar radiation, air temperature and the air temperature at 9m high and store them as an 
 *  extended object of observation, a statistics object. Finally the class will
 *  create a toString method displaying all of this data on the console window.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author Matthew Klopfer
 * @version 10/3/2018 Lab 11
 * 
 *          This is the MapData class that will map the data out for the
 *          information given to it from an mdf file. The Class has methods to
 *          calculate the statistics, parse the file, create a toString and many
 *          other getters and setters to return the value of certain Observation
 *          objects when needed.
 */
public class MapData {

	/** HashMap containing the data */
	private HashMap<String, ArrayList<Observation>> dataCatalog;

	/** EnumMap containing the statistics that are calculated */
	private EnumMap<StatsType, TreeMap<String, Statistics>> statistics;

	/** TreeMap holding the positions of the parameters */
	private TreeMap<String, Integer> paramPositions;

	/** Integer of the number of stations active */
	private Integer numberOfStations = null;

	/** Holds the name of the column with ta9m data */
	final private String TA9M = "TA9M";

	/** Holds the name of the column with tair data */
	final private String TAIR = "TAIR";

	/** Holds the name of the column with srad data */
	final private String SRAD = "SRAD";

	/** Holds the name of the column with stid data */
	final private String STID = "STID";

	/** Holds the name of the column with wspd data **/
	final private String WSPD = "WSPD";

	/** Holds the name of the column with pres data **/
	final private String PRES = "PRES";

	/** The default station for statistics object */
	private String MESONET = "Mesonet";

	/** The file name that the program will open */
	private String fileName;

	/** The GregorianCalendar object that holds the date of the file */
	private GregorianCalendar utcDateTime;

	/** String to hold the directory of the file **/
	@SuppressWarnings("unused")
	private String directory;

	/**
	 * MapData constructor from a file passed into it from the GUI
	 */
	public MapData(File file) throws IOException {

		this.directory = file.getParentFile().getPath(); // Get the path to the file
		this.fileName = file.getName();
		
		int year = Integer.parseInt(fileName.substring(0, 4));
		int month = Integer.parseInt(fileName.substring(4, 6));
		int day = Integer.parseInt(fileName.substring(6, 8));
		int hour = Integer.parseInt(fileName.substring(8, 10));
		int minute = Integer.parseInt(fileName.substring(10, 12));

		utcDateTime = new GregorianCalendar(year, month, day, hour, minute);
		prepareDataCatalog();
		parseFile();
		calculateAllStatistics();

	}

	/**
	 * Sometimes when a user gives a file to our program, we cannot expect the data
	 * to be in the same row as previous pieces of data. That is why we will parse
	 * out the location of the data within the file the user gives us.
	 * 
	 * @param inParamStr
	 *            The line read from the bufferedReader that has the string array of
	 *            headers
	 */
	private void parseParamHeader(String inParamStr) {
		String[] header = inParamStr.split("\\s+");

		for (int i = 0; i < header.length; ++i) {

			if (header[i].equals(SRAD)) {
				paramPositions.put(SRAD, new Integer(i)); // Position of the SRAD
			} else if (header[i].equals(TAIR)) {
				paramPositions.put(TAIR, new Integer(i)); // Position of the TAIR
			} else if (header[i].equals(TA9M)) {
				paramPositions.put(TA9M, new Integer(i)); // Position of the TA9M
			} else if (header[i].equals(STID)) {
				paramPositions.put(STID, new Integer(i)); // Position of the STID
			} else if (header[i].equals(WSPD)) {
				paramPositions.put(WSPD, new Integer(i)); // Position of the WSPD
			} else if (header[i].equals(PRES)) {
				paramPositions.put(PRES, new Integer(i)); // Position of the PRES
			}
		}

	}

	/**
	 * This method will go into the TreeMap containing the values of the parameter
	 * position and then return it for the program to use
	 * 
	 * @param inParamStr
	 *            is a String object containing the parameter the program wants
	 * 
	 */
	public Integer getIndexOf(String inParamStr) {
		// Returns the map value of the specified key
		return (Integer) (paramPositions.get(inParamStr) - 1);
	}

	/**
	 * Parse the file given to us by the user and stores it in the appropriate
	 * ArrayLists My throw an IOException
	 * 
	 * @return void
	 */
	public void parseFile() throws IOException {
		// Create a fileReader and take its output
		BufferedReader br = new BufferedReader(new FileReader(directory + "/" + fileName));

		// Throw out the first two lines
		br.readLine();
		br.readLine();

		parseParamHeader(br.readLine()); // Parse the position of the data we need: TAIR, SRAD, TA9M, STID

		// Store the data as an observation
		ArrayList<Observation> sradInfo = new ArrayList<Observation>();
		ArrayList<Observation> tairInfo = new ArrayList<Observation>();
		ArrayList<Observation> ta9mInfo = new ArrayList<Observation>();
		ArrayList<Observation> wspdInfo = new ArrayList<Observation>();
		ArrayList<Observation> presInfo = new ArrayList<Observation>();

		String readInput = br.readLine(); // Read a line from the file to be looked at
		do {
			String[] data = readInput.split("\\s+"); // Split the line read from the BufferedReader

			tairInfo.add(new Observation(Double.parseDouble(data[(int) paramPositions.get(TAIR)]),
					data[(int) paramPositions.get(STID)])); // Add the data to the tair ArrayList
			ta9mInfo.add(new Observation(Double.parseDouble(data[(int) paramPositions.get(TA9M)]),
					data[(int) paramPositions.get(STID)])); // Add the data to the ta9m ArrayList
			sradInfo.add(new Observation(Double.parseDouble(data[(int) paramPositions.get(SRAD)]),
					data[(int) paramPositions.get(STID)])); // Add the data to the srad ArrayList
			wspdInfo.add(new Observation(Double.parseDouble(data[(int) paramPositions.get(WSPD)]),
					data[(int) paramPositions.get(STID)])); // Add the data to the wspd ArrayList
			presInfo.add(new Observation(Double.parseDouble(data[(int) paramPositions.get(PRES)]),
					data[(int) paramPositions.get(STID)])); // Add the data to the PRES ArrayList

			readInput = br.readLine(); // Read another line to be looked at

		} while (readInput != null); // While the read line is not null
		br.close(); // Close the bufferedReader

		// Add the data to the HashMap
		dataCatalog.put(TAIR, tairInfo);
		dataCatalog.put(TA9M, ta9mInfo);
		dataCatalog.put(SRAD, sradInfo);
		dataCatalog.put(WSPD, wspdInfo);
		dataCatalog.put(PRES, presInfo);

		// Calculate all the statistics
		calculateStatistics();
	}

	/**
	 * This method will calculate the statistics for our program. It will be
	 * re-implemented in project 4 to have more use
	 */
	private void calculateStatistics() {
		calculateAllStatistics();
	}

	/**
	 * Method will calculate the statistics by cycling through the parameters with
	 * their respective arrayList. It will then throw out bad observations, sort the
	 * list and find the average, max, min and total of the data population.
	 * 
	 */
	private void calculateAllStatistics() {

		// I don't know another way to access the headers without being to complex
		String[] paramHeader = new String[] { TAIR, SRAD, TA9M, WSPD, PRES };

		// Declare the tree maps that will hold the statistics objects
		TreeMap<String, Statistics> totalStats = new TreeMap<String, Statistics>();
		TreeMap<String, Statistics> averageStats = new TreeMap<String, Statistics>();
		TreeMap<String, Statistics> maxStats = new TreeMap<String, Statistics>();
		TreeMap<String, Statistics> minStats = new TreeMap<String, Statistics>();

		for (String param : paramHeader) {
			// Set this equal to 0.0
			double totalVal = 0.0;

			// Access the array from the HashMap so we can perform statistics on it
			ArrayList<Observation> inData = dataCatalog.get(param);
			ArrayList<Observation> invalidStation = new ArrayList<Observation>();
			// Remove invalid Observations
			for (Observation o : inData) {
				// If the data is incorrect we do not want it in our calculations
				if (!o.isValid()) {
					// Remove the data from the ArrayList
					invalidStation.add(o);
				} else { // Add the value of a valid station to the totalVal
					totalVal += o.getValue();
				}
			}
			// Remove the invalid stations
			inData.removeAll(invalidStation);

			// Initialize the number of stations that are active
			numberOfStations = new Integer(inData.size());

			// Sort our array list in ascending order
			Collections.sort(inData, new Comparator<Observation>() {
				@Override
				public int compare(Observation o1, Observation o2) {
					if (o1.getValue() > o2.getValue()) {
						return 1;
					}
					if (o1.getValue() < o2.getValue()) {
						return -1;
					} else {
						return 0;
					}
				}
			});

			/*
			 * Now with everything sorted and all of the unValid stations thrown out, we
			 * will start to find the maximum, minimum and average for the data set
			 */
			double average = totalVal / (double) numberOfStations;
			Observation max = inData.get(inData.size() - 1);
			Observation min = inData.get(0);

			totalStats.put(param, new Statistics(totalVal, MESONET, utcDateTime, numberOfStations, StatsType.TOTAL));
			averageStats.put(param, new Statistics(average, MESONET, utcDateTime, numberOfStations, StatsType.AVERAGE));
			maxStats.put(param,
					new Statistics(max.getValue(), max.getStid(), utcDateTime, numberOfStations, StatsType.MAXIMU));
			minStats.put(param,
					new Statistics(min.getValue(), min.getStid(), utcDateTime, numberOfStations, StatsType.MINIMUM));
		}
		// Add the TreeMap to the statistics EnumMap
		statistics.put(StatsType.TOTAL, totalStats);
		statistics.put(StatsType.AVERAGE, averageStats);
		statistics.put(StatsType.MAXIMU, maxStats);
		statistics.put(StatsType.MINIMUM, minStats);
	}

	/**
	 * This method is used to make sure the Maps are initiated correctly before the
	 * program can calculate the statistics
	 * 
	 */
	private void prepareDataCatalog() {
		dataCatalog = new HashMap<String, ArrayList<Observation>>();
		statistics = new EnumMap<StatsType, TreeMap<String, Statistics>>(StatsType.class);
		paramPositions = new TreeMap<String, Integer>();
	}

	/**
	 * This method will return the statistic that is wanted from the Enumerated list
	 * 
	 * @return The statistics object from the enumMap
	 */
	public Statistics getStatistics(StatsType type, String paramID) {
		return statistics.get(type).get(paramID);
	}

	/**
	 * This toString method will output the data in a nicely formated console output
	 * 
	 * @return nothing
	 */
	public String toString() {
		for (int i = 0; i < 56; ++i)// Print a line of 57 equals characters
		{
			System.out.print("=");
		}
		// Print out the date and time of the weather results
		System.out
				.print(String.format("\n=== %s-%s-%s %s:%s ===\n", fileName.substring(5, 9), fileName.substring(9, 11),
						fileName.substring(11, 13), fileName.substring(13, 15), fileName.substring(15, 17)));

		for (int i = 0; i < 56; ++i)// Print a line of 57 equals characters
		{
			System.out.print("=");
		}
		// Print out the tair calculations
		System.out.println(String.format("\nMaximum Air Temperature[1.5m] = %.1f C at %s",
				getStatistics(StatsType.MAXIMU, TAIR).getValue(), getStatistics(StatsType.MAXIMU, TAIR).getStid()));
		System.out.println(String.format("Minimum Air Temperature[1.5m] = %.1f C at %s",
				getStatistics(StatsType.MINIMUM, TAIR).getValue(), getStatistics(StatsType.MINIMUM, TAIR).getStid()));
		System.out.println(String.format("Average Air Temperature[1.5m] = %.1f C at %s",
				getStatistics(StatsType.AVERAGE, TAIR).getValue(), getStatistics(StatsType.AVERAGE, TAIR).getStid()));

		for (int i = 0; i < 2; ++i)// Print 2 lines of 57 equals characters
		{
			for (int j = 0; j < 56; ++j) {
				System.out.print("=");
			}
			System.out.print("\n");
		}
		// Print out the ta9m calculations
		System.out.println(String.format("Maximum Air Temperature[9.0m] = %.1f C at %s",
				getStatistics(StatsType.MAXIMU, TA9M).getValue(), getStatistics(StatsType.MAXIMU, TA9M).getStid()));
		System.out.println(String.format("Minimum Air Temperature[9.0m] = %.1f C at %s",
				getStatistics(StatsType.MINIMUM, TA9M).getValue(), getStatistics(StatsType.MINIMUM, TA9M).getStid()));
		System.out.println(String.format("Average Air Temperature[9.0m] = %.1f C at %s",
				getStatistics(StatsType.AVERAGE, TA9M).getValue(), getStatistics(StatsType.AVERAGE, TA9M).getStid()));

		for (int i = 0; i < 2; ++i)// Print 2 lines of 57 equals characters
		{
			for (int j = 0; j < 56; ++j) {
				System.out.print("=");
			}
			System.out.print("\n");
		}
		// Print out the srad calculations
		System.out.println(String.format("Maximum Solar Radiation[1.5m] = %.1f W/m^2 at %s",
				getStatistics(StatsType.MAXIMU, SRAD).getValue(), getStatistics(StatsType.MAXIMU, SRAD).getStid()));
		System.out.println(String.format("Minimum Solar Radiation[1.5m] = %.1f W/m^2 at %s",
				getStatistics(StatsType.MINIMUM, SRAD).getValue(), getStatistics(StatsType.MINIMUM, SRAD).getStid()));
		System.out.println(String.format("Average Solar Radiation[1.5m] = %.1f W/m^2 at %s",
				getStatistics(StatsType.AVERAGE, SRAD).getValue(), getStatistics(StatsType.AVERAGE, SRAD).getStid()));

		for (int i = 0; i < 56; ++i)// Print a line of 57 equals characters
		{
			System.out.print("=");
		}
		System.out.println();// Print a blank line at the end of the toString method
		return String.format("");// Return a blank String
	}

}
