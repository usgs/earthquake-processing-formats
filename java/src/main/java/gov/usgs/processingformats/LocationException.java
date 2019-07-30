package gov.usgs.processingformats;


/**
 * LocationException represents an error that occurred while
 * processing a LocationRequest.
 */
public class LocationException extends Exception {

    /** Default serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for error message.
     *
     * @param message description of error.
     */
    public LocationException(final String message) {
        super(message);
    }

    /**
     * Constructor to wrap another exception.
     *
     * @param message description of error.
     * @param cause exception that caused error.
     */
    public LocationException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
