package gov.usgs.processingformats;

import java.util.*;
import org.json.simple.JSONObject;

/**
 * a conversion class used to create, parse, and validate travel time requests
 *
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class TravelTimeSource implements ProcessingInt {

  /** JSON Keys */
  public static final String LATITUDE_KEY = "Latitude";

  public static final String LONGITUDE_KEY = "Longitude";
  public static final String DEPTH_KEY = "Depth";

  /** Required geographic source Latitude in degrees */
  public Double Latitude;

  /** Required geographic source Longitude in degrees */
  public Double Longitude;

  /** Required geographic source depth in kilometers */
  public Double Depth;

  /** The constructor for the TravelTimeData class. Initializes members to null values. */
  public TravelTimeSource() {

    reload(null, null, null);
  }

  /**
   * Advanced constructor
   *
   * <p>The advanced constructor for the TravelTimeSource class. Initializes members to provided
   * values.
   *
   * @param newLatitude - A required Double containing the geographic source Latitude in degrees
   * @param newLongitude - A required Double containing the geographic source Longitude in degrees
   * @param newDepth - A required Double containing the geographic source Depth relative to the
   *     WGS84 datum in kilometers
   */
  public TravelTimeSource(Double newLatitude, Double newLongitude, Double newDepth) {

    reload(newLatitude, newLongitude, newDepth);
  }

  /**
   * Constructs the class from a JSONObject, populating members
   *
   * @param newJSONObject - A JSONObject.
   */
  public TravelTimeSource(JSONObject newJSONObject) {

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

    // Depth
    if (newJSONObject.containsKey(DEPTH_KEY)) {
      Depth = (double) newJSONObject.get(DEPTH_KEY);
    } else {
      Depth = null;
    }
  }

  /**
   * Constructs the class from a TravelTimeData object, populating members (copy constructor)
   *
   * @param sourceObject - A TravelTimeData object.
   */
  public TravelTimeSource(TravelTimeSource sourceObject) {
    reload(sourceObject.Latitude, sourceObject.Longitude, sourceObject.Depth);
  }

  /**
   * Reload Function
   *
   * <p>The reload function for the TravelTimeData class. Initializes members to provided values.
   *
   * @param newLatitude - A required Double containing the geographic source Latitude in degrees,
   *     null to omit
   * @param newLongitude - A required Double containing the geographic source Longitude in degrees
   * @param newDepth - A required Double containing the geographic source Depth relative to the
   *     WGS84 datum in kilometers
   */
  public void reload(Double newLatitude, Double newLongitude, Double newDepth) {

    Latitude = newLatitude;
    Longitude = newLongitude;
    Depth = newDepth;
  }

  /**
   * Converts the contents of the class to a json object
   *
   * @return Returns a JSONObject containing the class contents
   */
  @SuppressWarnings("unchecked")
  public JSONObject toJSON() {

    JSONObject newJSONObject = new JSONObject();

    // required values
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

    // required values
    // Latitude
    if (Latitude == null) {
      // Latitude not found
      errorList.add("No Latitude in TravelTimeSource Class.");
    } else if ((Latitude < -90) || (Latitude > 90)) {
      // invalid Latitude
      errorList.add("Latitude in TravelTimeSource not in the range of -90 to 90.");
    }

    // Longitude
    if (Longitude == null) {
      // Longitude not found
      errorList.add("No Longitude in TravelTimeSource Class.");
    } else if ((Longitude < -180) || (Longitude > 180)) {
      // invalid Longitude
      errorList.add("Longitude in TravelTimeSource not in the range of -180 to 180.");
    }

    // Depth
    if (Depth == null) {
      // Depth not found
      errorList.add("No Depth in TravelTimeSource Class.");
    } else if ((Depth < -100) || (Depth > 1000)) {
      // invalid Longitude
      errorList.add("Depth in TravelTimeSource not in the range of -100 to 1000.");
    }

    return (errorList);
  }
}
