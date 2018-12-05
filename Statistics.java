
/** @author Matthew Klopfer
 *  @author Reece Reinke (Collaborated)
 *  @version 10/28/18
 *  Lab 11
 *  
 *  This is the statistics class and the the methods that will be used on this object.
 *  It is an extension of the object observation because it holds important
 *  data that a normal observation object cannot hold
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Matthew Klopfer
 * @version 10/3/18 Lab 11
 * 
 *          The statistics class is the child of the observation object and it
 *          implements the interface DateTimeComparable
 */
public class Statistics extends Observation implements DateTimeComparable {

	/** The date format of gregorian calendar */
	protected String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss z";

	/** Date of the gregorian calendar */
	private GregorianCalendar utcDateTime;

	/** Date of the zoned date time */
	private ZonedDateTime zdtDateTime;

	/** The number of reporting stations */
	private int numberOfReportingStations;

	/** The stat type of the statistics object */
	@SuppressWarnings("unused")
	private StatsType statType;

	/**
	 * One of the constructors for the Statistics object, with a GregorianCalendar
	 * for the date being handed in to the constructor.
	 * 
	 * @param value
	 *            Is the value at hand
	 * @param stid
	 *            The station ID
	 * @param dateTime
	 *            The date object of the time
	 * @param numberOfValidStations
	 *            The number of stations online
	 * @param inStatType
	 *            The constant value container
	 */
	public Statistics(double value, String stid, GregorianCalendar dateTime, int numberOfValidStations,
			StatsType inStatType) {
		super(value, stid); // Use the super constructor to set these values
		this.numberOfReportingStations = numberOfValidStations;
		this.statType = inStatType;
		this.utcDateTime = dateTime;
	}

	/**
	 * One of the constructors for the Statistics object, with a ZDT object for the
	 * date being handed in to the constructor.
	 * 
	 * @param value
	 *            Is the value at hand
	 * @param stid
	 *            The station ID
	 * @param dateTime
	 *            The date of the statistic
	 * @param numberOfValidStations
	 *            The number of stations online
	 * @param inStatType
	 *            The constant value container
	 */
	public Statistics(double value, String stid, ZonedDateTime dateTime, int numberOfValidStations,
			StatsType inStatType) {
		super(value, stid); // Use the super constructor to set these values
		this.numberOfReportingStations = numberOfValidStations;
		this.statType = inStatType;
		this.zdtDateTime = dateTime;
	}

	/**
	 * Method to create a time from a dateString
	 * 
	 * @param dateTimeStr
	 * @return
	 */
	public GregorianCalendar createDateFromString(String dateTimeStr) {
		DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
		Date date = null;

		try // Try to read the date information
		{
			date = dateFormat.parse(dateTimeStr);
		} catch (ParseException e) // If it is not correct we can't parse it
		{
			e.printStackTrace();
		}

		// Create a new calendar and initialize the date of it
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);

		return (GregorianCalendar) cal; // Return the new calendar
	}

	/**
	 * Method will return a ZonedDateTime object from a string
	 * 
	 * @param dateTimeStr
	 *            is the string that we want to convert to a ZDT object
	 * @return zdtDate is the ZDT object
	 */
	public ZonedDateTime createZDateFromString(String dateTimeStr) {
		// Create a zoned date time out of the string passed into the method
		ZonedDateTime zdtDate = ZonedDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
		return zdtDate;
	}

	/**
	 * Create a new String from the GregorianCalendar given to it
	 * 
	 * @param calendar
	 *            The gregorian calendar object
	 * @return String containing the date
	 */
	public String createStringFromDate(GregorianCalendar calendar) {
		SimpleDateFormat cal = new SimpleDateFormat(DATE_TIME_FORMAT);
		cal.setCalendar(calendar);
		String dateFormatted = cal.format(calendar.getTime());
		return dateFormatted;
	}

	/**
	 * Create a new String from the ZonedDateTime given to it
	 * 
	 * @param calendar
	 *            The ZonedDateTime object
	 * 
	 * @return String containing the date
	 */
	public String createStringFromDate(ZonedDateTime calendar) {
		// Create the string from the ZonedDateTime object
		String calStr = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT).format(calendar);
		return calStr;
	}

	/**
	 * Get the number of stations that are active
	 * 
	 * @return int the number of stations reporting
	 */
	public int getNumberOfReportingStations() {
		return numberOfReportingStations;
	}

	/**
	 * Get the Date string from a gregorian calendar object
	 * 
	 * @return String contining the date
	 */
	public String getUTCDateTimeString() {
		return createStringFromDate(utcDateTime);
	}

	/**
	 * Method will return true or false depending on whether a date is newer than
	 * another date
	 * 
	 * @return boolean with if it is newer or not (true if new)
	 */
	@Override
	public boolean newerThan(GregorianCalendar inDateTimeUTC) {
		int compareRes = this.utcDateTime.compareTo(inDateTimeUTC);
		return (compareRes == 1);
	}

	/**
	 * Method will return true or false depending on whether a date is older than
	 * another date
	 * 
	 * @return boolean with if it is older or not (true if old)
	 */
	@Override
	public boolean olderThan(GregorianCalendar inDateTimeUTC) {
		int compareRes = this.utcDateTime.compareTo(inDateTimeUTC);
		return (compareRes == -1);
	}

	/**
	 * Method will return true or false depending on whether a date is the same as
	 * another date
	 * 
	 * @return boolean with if it is the same or not (true if same)
	 */
	@Override
	public boolean sameAs(GregorianCalendar inDateTimeUTC) {
		int compareRes = this.utcDateTime.compareTo(inDateTimeUTC);
		return (compareRes == 0);
	}

	/**
	 * Method will return true or false depending on whether a date is newer than
	 * another ZDT date
	 * 
	 * @return boolean that is true if the date is newer than
	 */
	@Override
	public boolean newerThan(ZonedDateTime inDateTimeUTC) {
		int compareRes = this.zdtDateTime.compareTo(inDateTimeUTC);
		return (compareRes == 1);
	}

	/**
	 * Method will return true or false depending on whether a date is older than
	 * another ZDT date
	 * 
	 * @return boolean that is true if the date is older than
	 */
	@Override
	public boolean olderThan(ZonedDateTime inDateTimeUTC) {
		int compareRes = this.zdtDateTime.compareTo(inDateTimeUTC);
		return (compareRes == -1);
	}

	/**
	 * Method will return true or false depending on whether a date is the same
	 * as another ZDT date
	 * 
	 * @return boolean that is true if the ZDT is the same as the one at hand
	 */
	@Override
	public boolean sameAs(ZonedDateTime inDateTimeUTC) {
		int compareRes = this.zdtDateTime.compareTo(inDateTimeUTC);
		return (compareRes == 0);
	}
}
