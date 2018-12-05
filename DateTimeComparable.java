
/** @author Matthew Klopfer
 *  @version 10/28/2018
 *  Lab 11
 * 
 *  This is the interface that will be implemented by the statistics class.
 *  Its main purpose is to provide the method names for comparing the 
 *  GregorianCalendar objects if they are older, newer or the same
 *  as a previous calendar object
 */
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

public interface DateTimeComparable {

	/** See if the calendar is newer than the old one */
	boolean newerThan(GregorianCalendar inDateTimeUTC);

	/** See if the calendar is older than the new one */
	boolean olderThan(GregorianCalendar inDateTimeUTC);

	/** See if the calendar is the same as another */
	boolean sameAs(GregorianCalendar inDateTimeUTC);
	
	/** See if the Zoned Time is newer than another */
	boolean newerThan(ZonedDateTime inDateTimeUTC);
	
	/** See if the Zoned Time is older than another */
	boolean olderThan(ZonedDateTime inDateTimeUTC);
	
	/** See if the Zoned Time is the same as another */
	boolean sameAs(ZonedDateTime inDateTimeUTC);
}
