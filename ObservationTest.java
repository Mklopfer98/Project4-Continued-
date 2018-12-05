import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for Observation class
 * 
 * @author Matthew Klopfer
 * @version 2018-10-28
 *
 */
public class ObservationTest {

	/**
	 * Test the Observation Object and its constructor
	 */
	@Test
	public void testObservation() {
		// Create the observation object
		Observation test = new Observation(2.0, "Test");

		// Test if the object set itself correctly
		Assert.assertEquals("The value variable was not the same", test.getValue(), 2.0, 0.01);
		Assert.assertEquals("The stid value was not the same", test.getStid(), "Test");
	}

	/**
	 * Test the getValue method for observation
	 */
	@Test
	public void testGetValue() {
		// Create the observation object
		Observation test = new Observation(2.0, "Test");

		// Test the variable value
		double expected = test.getValue();
		double actual = 2.000;

		Assert.assertEquals("THe value was not the same", actual, expected, 0.01);
	}

	/**
	 * Test the isValid method for observation given a true value
	 */
	@Test
	public void testIsValidTrue() {
		// Create the observation object
		Observation test = new Observation(10.0, "Test");

		boolean expected = test.isValid();
		Assert.assertTrue(expected);// We want a true value to be returned
	}

	/**
	 * Test the isValid method for observation given a false value
	 */
	@Test
	public void testIsValidFalse() {
		// Create the observation object
		Observation test = new Observation(-901, "Test");

		boolean expected = test.isValid();
		Assert.assertFalse(expected);// We want a false value to be returned
	}

	/**
	 * Test the getStid method for observation
	 */
	@Test
	public void testGetStid() {
		// Create the observation object
		Observation test = new Observation(2.0, "Test");

		String expected = test.getStid();
		String actual = "Test";

		Assert.assertSame("The stid was not the same", expected, actual);
	}

	/**
	 * Test the toString method for observation
	 */
	@Test
	public void testToString() {
		// Create the observation object
		Observation test = new Observation(2.0, "Test");

		String expected = test.toString();
		String actual = "Station: Test, Value: 2.00\n";

		Assert.assertEquals("The toString was not the same", expected, actual);
	}
}
