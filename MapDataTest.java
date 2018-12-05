import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for MapData class
 * 
 * @author Matthew Klopfer
 * @version 10/28/2018 Lab 11
 *
 */
public class MapDataTest {

	/**
	 * Test the MapData Object and its constructor
	 */
	@Test
	public void testMapData() {

		// Create a MapData object and test to see if the constructor works
		MapData md = new MapData(2018, 8, 1, 7, 0, "data");

		// Test the createFile method
		String actual = md.createFileName(2018, 8, 1, 7, 0, "data");
		String expected = "data/201808010700.mdf";
		Assert.assertEquals("the file name was different than expected", actual, expected);
	}

	/**
	 * Test if the file given was correct and parsed appropriately
	 */
	@Test
	public void testParseFile() {

		try {
			// Create a MapData object and give it correct file info
			MapData md = new MapData(2018, 8, 1, 7, 0, "data");
			md.parseFile();// Parse the file
		} catch (Exception e) {
			Assert.fail("Legal expression developed an error at run time");
		}

		// Attempt to make the file fail
		try {
			// Create a MapData object and give it incorrect file info
			MapData md = new MapData(2018, 8, 2, 7, 0, "notData");
			md.parseFile();// Parse the file

			Assert.fail("Illegal command passed"); // Fail if the command doesn't throw and error
		} catch (IOException e) {
			// We intend to catch and IOException because the file given will not open
			Assert.assertEquals(1, 1);
		} catch (Exception e) {
			// Catch any unexpected errors
			Assert.fail("Unexpected error was thrown");
		}
	}

	/**
	 * Test that the correct file name was created successfully
	 */
	@Test
	public void testCreateFileName() {

		// Create a mapdata object and create the file name
		MapData md = new MapData(1, 0, 0, 0, 1, "test");
		String expected = md.createFileName(1, 0, 0, 0, 1, "test");

		// Expect the file to be the same with 0s added on if needed
		Assert.assertEquals("The file name created did not match the expected", expected, "test/100000001.mdf");
	}

	/**
	 * Test correct get of the srad average object
	 */
	@Test
	public void testGetSradAverage() {
		try {
			// Create a MapData object and give it correct file info
			MapData md = new MapData(2018, 8, 1, 7, 0, "data");
			md.parseFile();// Parse the file

			Observation expected = md.getStatistics(StatsType.AVERAGE, "SRAD");// Get the observation object
			Observation actual = new Observation(0.0, "Mesonet");

			// Test if the value is the same
			Assert.assertEquals("The values are not the same", actual.getValue(), expected.getValue(), 0.01);
			// Test if the stid is the same
			Assert.assertEquals("The stids are not the same", actual.getStid(), expected.getStid());
		} catch (Exception e) {
			Assert.fail("Legal expression threw an error");
		}
	}

	/**
	 * Test correct get of the srad max object
	 */
	@Test
	public void testGetSradMax() {
		try {
			// Create a MapData object and give it correct file info
			MapData md = new MapData(2018, 8, 1, 7, 0, "data");
			md.parseFile();// Parse the file

			Observation expected = md.getStatistics(StatsType.MAXIMU, "SRAD");// Get the observation object
			Observation actual = new Observation(0.0, "YUKO");

			// Test if the value is the same
			Assert.assertEquals("The values are not the same", actual.getValue(), expected.getValue(), 0.01);
			// Test if the stid is the same
			Assert.assertEquals("The stids are not the same", actual.getStid(), expected.getStid());
		} catch (Exception e) {
			Assert.fail("Legal expression threw an error");
		}
	}

	/**
	 * Test correct get of the srad min object
	 */
	@Test
	public void testGetSradMin() {
		try {
			// Create a MapData object and give it correct file info
			MapData md = new MapData(2018, 8, 1, 7, 0, "data");
			md.parseFile();// Parse the file

			Observation expected = md.getStatistics(StatsType.MINIMUM, "SRAD");// Get the observation object
			Observation actual = new Observation(0.0, "ACME");

			// Test if the value is the same
			Assert.assertEquals("The values are not the same", actual.getValue(), expected.getValue(), 0.01);
			// Test if the stid is the same
			Assert.assertEquals("The stids are not the same", actual.getStid(), expected.getStid());
		} catch (Exception e) {
			Assert.fail("Legal expression threw an error");
		}
	}

	/**
	 * Test correct get of the srad total object
	 */
	@Test
	public void testGetSradTotal() {
		try {
			// Create a MapData object and give it correct file info
			MapData md = new MapData(2018, 8, 1, 7, 0, "data");
			md.parseFile();// Parse the file

			Observation expected = md.getStatistics(StatsType.TOTAL, "SRAD");// Get the observation object
			Observation actual = new Observation(0.0, "Mesonet");

			// Test if the value is the same
			Assert.assertEquals("The values are not the same", actual.getValue(), expected.getValue(), 0.01);
			// Test if the stid is the same
			Assert.assertEquals("The stids are not the same", actual.getStid(), expected.getStid());
		} catch (Exception e) {
			Assert.fail("Legal expression threw an error");
		}
	}

	/**
	 * Test correct get of the ta9m average object
	 */
	@Test
	public void testGetTa9mAverage() {
		try {
			// Create a MapData object and give it correct file info
			MapData md = new MapData(2018, 8, 1, 7, 0, "data");
			md.parseFile();// Parse the file

			Observation expected = md.getStatistics(StatsType.AVERAGE, "TA9M");// Get the observation object
			Observation actual = new Observation(19.68, "Mesonet");

			// Test if the value is the same
			Assert.assertEquals("The values are not the same", actual.getValue(), expected.getValue(), 0.01);
			// Test if the stid is the same
			Assert.assertEquals("The stids are not the same", actual.getStid(), expected.getStid());
		} catch (Exception e) {
			Assert.fail("Legal expression threw an error");
		}
	}

	/**
	 * Test correct get of the ta9m max object
	 */
	@Test
	public void testGetTa9mMax() {
		try {
			// Create a MapData object and give it correct file info
			MapData md = new MapData(2018, 8, 1, 7, 0, "data");
			md.parseFile();// Parse the file

			Observation expected = md.getStatistics(StatsType.MAXIMU, "TA9M");// Get the observation object
			Observation actual = new Observation(23.3, "MARE");

			// Test if the value is the same
			Assert.assertEquals("The values are not the same", actual.getValue(), expected.getValue(), 0.01);
			// Test if the stid is the same
			Assert.assertEquals("The stids are not the same", actual.getStid(), expected.getStid());
		} catch (Exception e) {
			Assert.fail("Legal expression threw an error");
		}
	}

	/**
	 * Test correct get of the ta9m min object
	 */
	@Test
	public void testGetTa9mMin() {
		try {
			// Create a MapData object and give it correct file info
			MapData md = new MapData(2018, 8, 1, 7, 0, "data");
			md.parseFile();// Parse the file

			Observation expected = md.getStatistics(StatsType.MINIMUM, "TA9M");// Get the observation object
			Observation actual = new Observation(15.8, "COOK");

			// Test if the value is the same
			Assert.assertEquals("The values are not the same", actual.getValue(), expected.getValue(), 0.01);
			// Test if the stid is the same
			Assert.assertEquals("The stids are not the same", actual.getStid(), expected.getStid());
		} catch (Exception e) {
			Assert.fail("Legal expression threw an error");
		}
	}

	/**
	 * Test correct get of the tair Average object
	 */
	@Test
	public void testGetTairAverage() {
		try {
			// Create a MapData object and give it correct file info
			MapData md = new MapData(2018, 8, 1, 7, 0, "data");
			md.parseFile();// Parse the file

			Observation expected = md.getStatistics(StatsType.AVERAGE, "TAIR");// Get the observation object
			Observation actual = new Observation(17.98, "Mesonet");

			// Test if the value is the same
			Assert.assertEquals("The values are not the same", actual.getValue(), expected.getValue(), 0.01);
			// Test if the stid is the same
			Assert.assertEquals("The stids are not the same", actual.getStid(), expected.getStid());
		} catch (Exception e) {
			Assert.fail("Legal expression threw an error");
		}
	}

	/**
	 * Test correct get of the tair max object
	 */
	@Test
	public void testGetTairMax() {
		try {
			// Create a MapData object and give it correct file info
			MapData md = new MapData(2018, 8, 1, 7, 0, "data");
			md.parseFile();// Parse the file

			Observation expected = md.getStatistics(StatsType.MAXIMU, "TAIR");// Get the observation object
			Observation actual = new Observation(21.7, "MEDI");

			// Test if the value is the same
			Assert.assertEquals("The values are not the same", actual.getValue(), expected.getValue(), 0.01);
			// Test if the stid is the same
			Assert.assertEquals("The stids are not the same", actual.getStid(), expected.getStid());
		} catch (Exception e) {
			Assert.fail("Legal expression threw an error");
		}
	}

	/**
	 * Test correct get of the tair min object
	 */
	@Test
	public void testGetTairMin() {
		try {
			// Create a MapData object and give it correct file info
			MapData md = new MapData(2018, 8, 1, 7, 0, "data");
			md.parseFile();// Parse the file

			Observation expected = md.getStatistics(StatsType.MINIMUM, "TAIR");// Get the observation object
			Observation actual = new Observation(13.8, "EVAX");

			// Test if the value is the same
			Assert.assertEquals("The values are not the same", actual.getValue(), expected.getValue(), 0.01);
			// Test if the stid is the same
			Assert.assertEquals("The stids are not the same", actual.getStid(), expected.getStid());
		} catch (Exception e) {
			Assert.fail("Legal expression threw an error");
		}
	}

	/**
	 * Test correct toString of the MapData object
	 */
	@Test
	public void testToString() {
		try {
			// Create a MapData object and give it correct file info
			MapData md = new MapData(2018, 8, 1, 7, 0, "data");
			md.parseFile();// Parse the file

			md.toString();// Test to see if the to string has no errors
		} catch (Exception e) {
			Assert.fail("Legal expression threw an error");
		}
	}

	/**
	 * Test to see if the getIndexOf method works properly
	 * 
	 */
	@Test
	public void testGetIndexOf() {
		try {
			// Create a MapData object and give it correct file info
			MapData md = new MapData(2018, 8, 1, 7, 0, "data");
			md.parseFile();// Parse the file

			// Return the position of the STID
			int actual = md.getIndexOf("STID");

			// See if the parameter position is in the correct location
			Assert.assertEquals("The stid was not in the correct place", actual, 0);
		} catch (Exception e) {
			// Catch an unexpected exception
			Assert.fail("Valid expression threw the error" + e.getMessage());
		}
	}
}
