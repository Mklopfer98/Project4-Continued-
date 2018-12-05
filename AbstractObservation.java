/**
 * @author Matthew Klopfer
 * @version 10/28/2018 Lab 11
 * 
 *          This is the AbstractObservation class and it will hold the data for
 *          the abstract object
 */
public abstract class AbstractObservation extends Observation {

	/**
	 * The abstract constructor for this abstract class
	 * 
	 * @param value
	 *            is the value of the data at hand
	 * @param stid
	 *            the station id tag
	 */
	protected AbstractObservation(double value, String stid) {
		super(value, stid);
	}

	/** The boolean value valid */
	protected boolean valid;

	/** abstract method for is valid */
	public abstract boolean isValid();
}
