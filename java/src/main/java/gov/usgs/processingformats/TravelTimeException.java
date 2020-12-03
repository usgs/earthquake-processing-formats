package gov.usgs.processingformats;

/** TravelTimeException represents an error that occurred while processing a TravelTimeRequest. */
public class TravelTimeException extends Exception {

  /** Default serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Constructor for error message.
   *
   * @param message description of error.
   */
  public TravelTimeException(final String message) {
    super(message);
  }

  /**
   * Constructor to wrap another exception.
   *
   * @param message description of error.
   * @param cause exception that caused error.
   */
  public TravelTimeException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
