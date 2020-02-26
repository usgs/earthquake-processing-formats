package gov.usgs.processingformats;

import java.util.ArrayList;
import java.util.Date;
import org.json.simple.JSONObject;

/**
 * a conversion class used to create, parse, and validate hypocenter data as part of processing
 * data.
 *
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class Hypocenter implements ProcessingInt {

  /** JSON Keys */
  public static final String LATITUDE_KEY = "Latitude";

  public static final String LONGITUDE_KEY = "Longitude";
  public static final String DEPTH_KEY = "Depth";
  public static final String TIME_KEY = "Time";
  public static final String LATITUDE_ERROR_KEY = "LatitudeError";
  public static final String LONGITUDE_ERROR_KEY = "LongitudeError";
  public static final String DEPTH_ERROR_KEY = "DepthError";
  public static final String TIME_ERROR_KEY = "TimeError";

  /** Required Double containing the Latitude in decimal degrees */
  public Double Latitude;

  /** Required Double containing the Longitude in decimal degrees */
  public Double Longitude;

  /** Required Time for this hypo as a java Date */
  public Date Time;

  /** Required Double containing the Depth in kilometers */
  public Double Depth;

  /** Optional Double containing the Latitude error */
  public Double LatitudeError;

  /** Optional Double containing the Longitude error */
  public Double LongitudeError;

  /** Optional Double containing the Time error */
  public Double TimeError;

  /** Optional Double containing the Depth error */
  public Double DepthError;

  /** The constructor for the Hypo class. Initializes members to null values. */
  public Hypocenter() {

    Latitude = null;
    Longitude = null;
    Time = null;
    Depth = null;
    LatitudeError = null;
    LongitudeError = null;
    TimeError = null;
    DepthError = null;
  }

  /**
   * Advanced constructor
   *
   * <p>The alternate advanced constructor for the Hypo class. Initializes members to provided
   * values.
   *
   * @param newLatitude - A Double containing the Latitude in degrees to use
   * @param newLongitude - A Double containing the Longitude in degrees to use
   * @param newTime - A Date containing the new origin Time to use
   * @param newDepth - A Double containing the Depth in kilometers to use
   * @param newLatitudeError - A Double containing the Latitude error to use, null to omit
   * @param newLongitudeError - A Double containing the Longitude error to use, null to omit
   * @param newTimeError - A Double containing the new Time error to use, null to omit
   * @param newDepthError - A Double containing the Depth error to use, null to omit
   */
  public Hypocenter(
      Double newLatitude,
      Double newLongitude,
      Date newTime,
      Double newDepth,
      Double newLatitudeError,
      Double newLongitudeError,
      Double newTimeError,
      Double newDepthError) {

    reload(
        newLatitude,
        newLongitude,
        newTime,
        newDepth,
        newLatitudeError,
        newLongitudeError,
        newTimeError,
        newDepthError);
  }

  /**
   * Reload Function
   *
   * <p>The reload function for the Hypo class. Initializes members to provided values.
   *
   * @param newLatitude - A Double containing the Latitude in degrees to use
   * @param newLongitude - A Double containing the Longitude in degrees to use
   * @param newTime - A Date containing the new origin Time to use
   * @param newDepth - A Double containing the Depth in kilometers to use
   * @param newLatitudeError - A Double containing the Latitude error to use, null to omit
   * @param newLongitudeError - A Double containing the Longitude error to use, null to omit
   * @param newTimeError - A Double containing the new Time error to use, null to omit
   * @param newDepthError - A Double containing the Depth error to use, null to omit
   */
  public void reload(
      Double newLatitude,
      Double newLongitude,
      Date newTime,
      Double newDepth,
      Double newLatitudeError,
      Double newLongitudeError,
      Double newTimeError,
      Double newDepthError) {

    Latitude = newLatitude;
    Longitude = newLongitude;
    Time = newTime;
    Depth = newDepth;
    LatitudeError = newLatitudeError;
    LongitudeError = newLongitudeError;
    TimeError = newTimeError;
    DepthError = newDepthError;
  }

  /**
   * Constructs the class from a JSONObject, populating members
   *
   * @param newJSONObject - A JSONObject.
   */
  public Hypocenter(JSONObject newJSONObject) {

    // Required values
    // Latitude
    if (newJSONObject.containsKey(LATITUDE_KEY)) {
      Latitude = (double) newJSONObject.get(LATITUDE_KEY);
    } else {
      Latitude = null;
    }

    // Longitude
    if (newJSONObject.containsKey(LONGITUDE_KEY)) {
      Longitude = (double) newJSONObject.get(LONGITUDE_KEY);
    } else {
      Longitude = null;
    }

    // Time
    if (newJSONObject.containsKey(TIME_KEY)) {
      Time = Utility.getDate(newJSONObject.get(TIME_KEY).toString());
    } else {
      Time = null;
    }

    // Depth
    if (newJSONObject.containsKey(DEPTH_KEY)) {
      Depth = (double) newJSONObject.get(DEPTH_KEY);
    } else {
      Depth = null;
    }

    // optional values
    // Latitude error
    if (newJSONObject.containsKey(LATITUDE_ERROR_KEY)) {
      LatitudeError = (double) newJSONObject.get(LATITUDE_ERROR_KEY);
    } else {
      LatitudeError = null;
    }

    // Longitude error
    if (newJSONObject.containsKey(LONGITUDE_ERROR_KEY)) {
      LongitudeError = (double) newJSONObject.get(LONGITUDE_ERROR_KEY);
    } else {
      LongitudeError = null;
    }

    // Time error
    if (newJSONObject.containsKey(TIME_ERROR_KEY)) {
      TimeError = (double) newJSONObject.get(TIME_ERROR_KEY);
    } else {
      TimeError = null;
    }

    // Depth error
    if (newJSONObject.containsKey(DEPTH_ERROR_KEY)) {
      DepthError = (double) newJSONObject.get(DEPTH_ERROR_KEY);
    } else {
      DepthError = null;
    }
  }

  /**
   * Converts the contents of the class to a json object
   *
   * @return Returns a JSONObject containing the class contents
   */
  @SuppressWarnings("unchecked")
  public JSONObject toJSON() {

    JSONObject newJSONObject = new JSONObject();

    // Required values
    // Latitude
    if (Latitude != null) {
      newJSONObject.put(LATITUDE_KEY, Latitude);
    }

    // Longitude
    if (Longitude != null) {
      newJSONObject.put(LONGITUDE_KEY, Longitude);
    }

    // Depth
    if (Depth != null) {
      newJSONObject.put(DEPTH_KEY, Depth);
    }

    // Time
    if (Time != null) {
      newJSONObject.put(TIME_KEY, Utility.formatDate(Time));
    }

    // Optional values
    // Latitude error
    if (LatitudeError != null) {
      newJSONObject.put(LATITUDE_ERROR_KEY, LatitudeError);
    }

    // Longitude error
    if (LongitudeError != null) {
      newJSONObject.put(LONGITUDE_ERROR_KEY, LongitudeError);
    }

    // Depth error
    if (DepthError != null) {
      newJSONObject.put(DEPTH_ERROR_KEY, DepthError);
    }

    // Time error
    if (TimeError != null) {
      newJSONObject.put(TIME_ERROR_KEY, TimeError);
    }

    return (newJSONObject);
  }

  /**
   * Validates the class.
   *
   * @return Returns true if successful
   */
  public boolean isValid() {
    if (getErrors() == null) {
      return (true);
    } else if (getErrors().size() == 0) {
      return (true);
    } else {
      return (false);
    }
  }

  /**
   * Gets any validation errors in the class.
   *
   * @return Returns a List&lt;String&gt; of any errors found
   */
  public ArrayList<String> getErrors() {

    ArrayList<String> errorList = new ArrayList<String>();

    // Required Keys
    // Latitude
    if (Latitude == null) {
      // Latitude not found
      errorList.add("No Latitude in Hypo Class.");
    } else if ((Latitude < -90.0) || (Latitude > 90.0)) {
      // invalid Latitude
      errorList.add("Latitude in Hypo Class not in the range of -90 to 90.");
    }

    // Longitude
    if (Longitude == null) {
      // Longitude not found
      errorList.add("No Longitude in Hypo Class.");
    } else if ((Longitude < -180.0) || (Longitude > 180.0)) {
      // invalid Longitude
      errorList.add("Longitude in Hypo Class not in the range of -180 to 180.");
    }

    // Time
    if (Time == null) {
      // Time not found
      errorList.add("No Time in Hypo Class.");
    }

    // Depth
    if (Depth == null) {
      // Depth not found
      errorList.add("No Depth in Hypo Class.");
    } else if ((Depth < -100.0) || (Depth > 1500.0)) {
      // invalid Depth
      errorList.add("Depth in Hypo Class not in the range of -100 to 1500.");
    }

    // Optional Keys
    // Currently no validation criteria for optional values LatitudeError,
    // LongitudeError, TimeError, and DepthError.

    // success
    return (errorList);
  }
}
