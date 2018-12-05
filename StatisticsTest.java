
/** @author Matthew Klopfer
 *  @version 10/28/2018
 *  Lab 11
 * 
 *  This is the file that holds the test for the Statistics class
 */
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Matthew Klopfer
 * @version 10/28/2018 Lab 11
 * 
 *          The statisticsTest will make sure to cover ever possible problem
 *          within the statistics class
 */
public class StatisticsTest {

	/**
	 * Test the other statistics constructor with the gregorian calendar object
	 * 
	 */
	@Test
	public void testStatisticsDoubleStringGregorianCalendarIntStatsType() {
		GregorianCalendar greg = new GregorianCalendar(2018, 10, 3, 2, 44);// Date to be parsed
		Statistics wrongst = new Statistics(17.5, "Test", greg, 5, StatsType.MAXIMU);// Create the statistics object

		String expected = wrongst.createStringFromDate(greg);
		String actual = "2018-11-03 02:44:00 CDT";
		Assert.assertEquals("The date string is not the same", actual, expected);
	}

	/**
	 * Test the Statistics constructor with the zoned date time object
	 */
	@Test
	public void testStatisticsDoubleStringZonedDateTimeIntStatsType() {
		ZonedDateTime greg = ZonedDateTime.parse("2018-08-30 17:45:00 CDT",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));

		Statistics test = new Statistics(17.5, "Test", greg, 5, StatsType.AVERAGE);

		String expected = test.createStringFromDate(greg);
		String actual = "2018-08-30 17:45:00 CDT";
		Assert.assertEquals("The date string is not the same", actual, expected);
	}

	/**
	 * Test to see if the right date is created from a gregorian calendar object
	 * 
	 */
	@Test
	public void testCreateDateFromString() {
		GregorianCalendar dateTest = new GregorianCalendar(2018, 4, 20, 17, 1); // Date to be parsed
		Statistics st = new Statistics(17.5, "Test", dateTest, 5, StatsType.MINIMUM);// Create the statistics object
		dateTest.setTimeZone(TimeZone.getTimeZone("CDT"));

		Assert.assertEquals("The Gregorian Calendars are not the same",
				st.createDateFromString("2018-05-20 12:01:00 CDT").getTime(), dateTest.getTime()); // make sure the
																									// conversion back
																									// is correct
		try {
			st.createDateFromString("MATTHEWKLOPFER");
			Assert.fail("Illegal command passed");
		} catch (Exception e) {
			Assert.assertEquals("The error message was different", e.getMessage(), null);
		}
	}

	/**
	 * 
	 */
	@Test
	public void testCreateZDateFromString() {
		ZonedDateTime dateTest = ZonedDateTime.parse("2018-10-28 17:45:00 UTC",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")); // Date to be parsed
		Statistics st = new Statistics(17.5, "Test", dateTest, 5, StatsType.MINIMUM);// Create the statistics object

		Assert.assertEquals("The ZDT string is not the same", dateTest,
				st.createZDateFromString("2018-10-28 17:45:00 UTC"));
	}

	/**
	 * Test the getter for the number of reporting stations
	 * 
	 */
	@Test
	public void testGetNumberOfReportingStations() {
		GregorianCalendar greg = new GregorianCalendar(2018, 10, 28, 12, 35); // Date to be parsed
		Statistics st = new Statistics(17.5, "Test", greg, 5, StatsType.TOTAL);// Create the statistics object

		// Call the number of stations method and declare what we want to come back
		int expected = st.getNumberOfReportingStations();
		int actual = 5;

		// Make sure the same number of stations is correct
		Assert.assertEquals("The number of ststions is not the same", actual, expected);
	}

	/**
	 * Test to see if the time string is returned correctly
	 * 
	 */
	@Test
	public void testGetUTCDateTimeString() {
		GregorianCalendar greg = new GregorianCalendar(2018, 10, 3, 2, 44);// Date to be parsed
		Statistics wrongst = new Statistics(17.5, "Test", greg, 5, StatsType.AVERAGE);// Create the statistics object

		// Initialize what we expect and what the actual is
		String expected = wrongst.getUTCDateTimeString();
		String actual = "2018-11-03 02:44:00 CDT";

		// See if the 2 strings are the same
		Assert.assertEquals("The date time string was not the same", actual, expected);
	}

	/**
	 * Test the newer than method for it to be newer than
	 * 
	 */
	@Test
	public void testNewerThanTrue() {
		// This will be the GregorianCalendar object at question
		GregorianCalendar greg = new GregorianCalendar(2018, 10, 3, 2, 44);// Date to be parsed
		Statistics wrongst = new Statistics(17.5, "Test", greg, 5, StatsType.AVERAGE);// Create the statistics object

		GregorianCalendar tom = new GregorianCalendar(1994, 5, 5, 3, 44);// Older GregorianCalendar

		// Assert that the greg is newer than tom
		Assert.assertTrue(wrongst.newerThan(tom));

	}

	/**
	 * Test the newer than method for it to not be newer than
	 * 
	 */
	@Test
	public void testNewerThanFalse() {
		// This will be the GregorianCalendar object at question
		GregorianCalendar greg = new GregorianCalendar(2018, 10, 3, 2, 44);// Date to be parsed
		Statistics wrongst = new Statistics(17.5, "Test", greg, 5, StatsType.AVERAGE);// Create the statistics object

		GregorianCalendar tom = new GregorianCalendar(2019, 5, 5, 3, 44);// Older GregorianCalendar

		// Assert that the greg is not newer than tom
		Assert.assertFalse(wrongst.newerThan(tom));

	}

	/**
	 * Test the older than method and make sure it returns true
	 * 
	 */
	@Test
	public void testOlderThanTrue() {
		// This will be the GregorianCalendar object at question
		GregorianCalendar greg = new GregorianCalendar(2018, 10, 3, 2, 44);// Date to be parsed
		Statistics wrongst = new Statistics(17.5, "Test", greg, 5, StatsType.AVERAGE);// Create the statistics object

		GregorianCalendar tom = new GregorianCalendar(1994, 5, 5, 3, 44);// Older GregorianCalendar

		// Assert that the greg is older than tom
		Assert.assertFalse(wrongst.olderThan(tom));

	}

	/**
	 * Test the older than method and make sure it returns false
	 * 
	 */
	@Test
	public void testOlderThanFalse() {
		// This will be the GregorianCalendar object at question
		GregorianCalendar greg = new GregorianCalendar(2018, 10, 3, 2, 44);// Date to be parsed
		Statistics wrongst = new Statistics(17.5, "Test", greg, 5, StatsType.AVERAGE);// Create the statistics object

		GregorianCalendar tom = new GregorianCalendar(2019, 5, 5, 3, 44);// Older GregorianCalendar

		// Assert that the greg is not older than tom
		Assert.assertTrue(wrongst.olderThan(tom));
	}

	/**
	 * Test the same as method and make sure it returns true
	 * 
	 */
	@Test
	public void testSameAsTrue() {
		// This will be the GregorianCalendar object at question
		GregorianCalendar greg = new GregorianCalendar(2018, 10, 3, 2, 44);// Date to be parsed
		Statistics wrongst = new Statistics(17.5, "Test", greg, 5, StatsType.AVERAGE);// Create the statistics object

		GregorianCalendar tom = new GregorianCalendar(2018, 10, 3, 2, 44);// Older GregorianCalendar

		// Assert that the greg is the same as tom
		Assert.assertTrue(wrongst.sameAs(tom));
	}

	/**
	 * Test the same as method and make sure it returns false
	 * 
	 */
	@Test
	public void testSameAsFalse() {
		// This will be the GregorianCalendar object at question
		GregorianCalendar greg = new GregorianCalendar(2018, 10, 3, 2, 44);// Date to be parsed
		Statistics wrongst = new Statistics(17.5, "Test", greg, 5, StatsType.AVERAGE);// Create the statistics object

		GregorianCalendar tom = new GregorianCalendar(1678, 10, 3, 2, 44);// Older GregorianCalendar

		// Assert that the greg is not the same as as tom
		Assert.assertFalse(wrongst.sameAs(tom));
	}

	/**
	 * Test the newer than method for it to be newer than
	 * 
	 */
	@Test
	public void testNewerThanTrueZDT() {
		// This will be the ZDT object at question
		ZonedDateTime dateTest1 = ZonedDateTime.parse("2018-10-28 17:45:00 UTC",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
		ZonedDateTime dateTest2 = ZonedDateTime.parse("2018-05-08 17:45:00 UTC",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
		Statistics wrongst = new Statistics(17.5, "Test", dateTest1, 5, StatsType.AVERAGE);// Create the statistics
																							// object
		// Assert that the dateTest2 is newer than dateTest1
		Assert.assertTrue(wrongst.newerThan(dateTest2));

	}

	/**
	 * Test the newer than method for it to not be newer than
	 * 
	 */
	@Test
	public void testNewerThanFalseZDT() {
		// This will be the ZDT object at question
		ZonedDateTime dateTest1 = ZonedDateTime.parse("2018-10-28 17:45:00 UTC",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
		ZonedDateTime dateTest2 = ZonedDateTime.parse("2018-05-28 17:45:00 UTC",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
		Statistics wrongst = new Statistics(17.5, "Test", dateTest2, 5, StatsType.AVERAGE);// Create the statistics
																							// object
		// Assert that the dateTest1 is newer than dateTest2
		Assert.assertFalse(wrongst.newerThan(dateTest1));

	}

	/**
	 * Test the olderThan method to see if the ZDT is older
	 * 
	 */
	@Test
	public void testOlderThanTrueZDT() {
		// This will be the ZDT object at question
		ZonedDateTime dateTest1 = ZonedDateTime.parse("2018-10-28 17:45:00 UTC",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
		ZonedDateTime dateTest2 = ZonedDateTime.parse("2018-05-28 17:45:00 UTC",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
		Statistics wrongst = new Statistics(17.5, "Test", dateTest2, 5, StatsType.AVERAGE);// Create the statistics
																							// object
		// Assert that the dateTest2 is older than dateTest1
		Assert.assertTrue(wrongst.olderThan(dateTest1));
	}

	/**
	 * Test the olderThan method to see if the ZDT is not older
	 * 
	 */
	@Test
	public void testOlderThanFalseZDT() {
		// This will be the ZDT object at question
		ZonedDateTime dateTest1 = ZonedDateTime.parse("2018-10-28 17:45:00 UTC",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
		ZonedDateTime dateTest2 = ZonedDateTime.parse("2018-05-28 17:45:00 UTC",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
		Statistics wrongst = new Statistics(17.5, "Test", dateTest1, 5, StatsType.AVERAGE);// Create the statistics
																							// object
		// Assert that the dateTest1 is older than dateTest2
		Assert.assertFalse(wrongst.olderThan(dateTest2));
	}

	/**
	 * Test the sameAs method for ZDT and we expect it to be the same as
	 * 
	 */
	@Test
	public void testSameAsTrueZDT() {
		// This will be the ZDT object at question
		ZonedDateTime dateTest1 = ZonedDateTime.parse("2018-10-28 17:45:00 UTC",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
		ZonedDateTime dateTest2 = ZonedDateTime.parse("2018-10-28 17:45:00 UTC",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
		Statistics wrongst = new Statistics(17.5, "Test", dateTest1, 5, StatsType.AVERAGE);// Create the statistics
																							// object
		// Assert that the dateTest1 is the same as dateTest2
		Assert.assertTrue(wrongst.sameAs(dateTest2));
	}

	/**
	 * Test the sameAs method for ZDT and we expect it to not be the same as
	 * 
	 */
	@Test
	public void testSameAsFalseZDT() {
		// This will be the ZDT object at question
		ZonedDateTime dateTest1 = ZonedDateTime.parse("2018-10-28 17:45:00 UTC",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
		ZonedDateTime dateTest2 = ZonedDateTime.parse("2018-10-01 17:45:00 UTC",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
		Statistics wrongst = new Statistics(17.5, "Test", dateTest1, 5, StatsType.AVERAGE);// Create the statistics
																							// object
		// Assert that the dateTest1 is the same as dateTest2
		Assert.assertFalse(wrongst.sameAs(dateTest2));
	}

}
