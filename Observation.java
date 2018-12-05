/**
 * @author Matthew Klopfer
 * @version 10/28/18 Lab 11
 * 
 *          This class is the observation class and it holds the methods that
 *          will be used on the Observation object. Each object holds the value
 *          it is passed and the id of its Station along with whether the value
 *          passed is true or not
 */
public class Observation {

	/** The value of the observation at hand */
	private double value;

	/** The station ID */
	private String stid;

	/**
	 * Method will create the Observation object
	 * 
	 * @param value
	 *            is the value of whatever number is being passed to it (tair, ta9m,
	 *            srad)
	 * @param stid
	 *            is the station ID tag
	 * @return nothing
	 */
	public Observation(double value, String stid) {
		this.value = value;
		this.stid = stid;
	}

	/**
	 * Method will return a double type of the variable value.
	 * 
	 * @return double
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Method will return a boolean type of the variable valid.
	 * 
	 * @return boolean
	 */
	public boolean isValid() {
		return (this.getValue() > -900);
	}

	/**
	 * Method will return a String type of the String stid
	 * 
	 * @return String
	 */
	public String getStid() {
		return stid;
	}

	/**
	 * Method will create the toString of the object that is being passed
	 * 
	 * @return String containing the objects station ID and the value in question
	 */
	public String toString() {
		return String.format("Station: %s, Value: %.2f\n", getStid(), getValue());
	}

}
