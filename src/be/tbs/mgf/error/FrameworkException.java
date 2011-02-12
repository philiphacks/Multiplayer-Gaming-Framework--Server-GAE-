package be.tbs.mgf.error;

/**
 * Exception to be used in the MGF
 * Contains an error code and a description that can be sent to clients.
 * @author Gino
 */
public class FrameworkException extends Exception {

	/**
	 * Generated serial id
	 */
	private static final long serialVersionUID = 6173581335258226555L;
	private int fCode;
	private String fDescription;

	/**
	 * Creates a {@link FrameworkException} with the given error code.
	 * @param error - The error code.
	 */
	public FrameworkException(int error) {
		this.fCode = error;
	}
	
	/**
	 * Creates a {@link FrameworkException} with the given error code and description.
	 * @param error - The error code.
	 * @param description - A pretty description for the error.
	 */
	public FrameworkException(int error, String description) {
		this.fCode = error;
		this.fDescription = description;
	}
	
	/**
	 * Gets the error code for this exception.
	 * @return The error code.
	 */
	public int getErrorCode() {
		return this.fCode;
	}
	
	/**
	 * Gets the description.
	 * @return The description of the error.
	 */
	public String getDescription() {
		return this.fDescription;
	}

}
