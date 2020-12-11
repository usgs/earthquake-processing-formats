package gov.usgs.processingformats;

import java.util.*;
import org.json.simple.JSONObject;

/**
 * a conversion class used to create, parse, and validate travel time plot sample data
 *
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class TravelTimePlotDataSample implements ProcessingInt {

  /** JSON Keys */
  public static final String DISTANCE_KEY = "Distance";

  public static final String TRAVELTIME_KEY = "TravelTime";
  public static final String STATISTICALSPREAD_KEY = "StatisticalSpread";
  public static final String OBSERVABILITY_KEY = "Observability";
  public static final String RAYPARAMETER_KEY = "RayParameter";

  /** Required Distance in degrees */
  public Double Distance;

  /** Required travel time in seconds */
  public Double TravelTime;

  /** Optional observed travel time scatter in seconds. */
  public Double StatisticalSpread;

  /** Optional statistical Observability of the seismic phase. */
  public Double Observability;

  /** Optional ray parameter in seconds/degree. */
  public Double RayParameter;

  /** The constructor for the TravelTimePlotDataSample class. Initializes members to null values. */
  public TravelTimePlotDataSample() {

    reload(null, null, null, null, null);
  }

  /**
   * Advanced constructor
   *
   * <p>The advanced constructor for the TravelTimePlotDataSample class. Initializes members to
   * provided values.
   *
   * @param newDistance - A Double containing the Distance in degrees
   * @param newTravelTime - A Double containing the travel time in seconds
   * @param newStatisticalSpread - A Double containing the observed travel time scatter in seconds
   * @param newObservability - A Double containing the statistical Observability of the sample
   * @param newRayParameter - A Double containing the ray parameter of the sample
   */
  public TravelTimePlotDataSample(
      Double newDistance,
      Double newTravelTime,
      Double newStatisticalSpread,
      Double newObservability,
      Double newRayParameter) {

    reload(newDistance, newTravelTime, newStatisticalSpread, newObservability, newRayParameter);
  }

  /**
   * Constructs the class from a JSONObject, populating members
   *
   * @param newJSONObject - A JSONObject.
   */
  public TravelTimePlotDataSample(JSONObject newJSONObject) {

    // Required values
    // Distance
    if (newJSONObject.containsKey(DISTANCE_KEY)) {
      Distance = (double) newJSONObject.get(DISTANCE_KEY);
    } else {
      Distance = null;
    }

    // travel time
    if (newJSONObject.containsKey(TRAVELTIME_KEY)) {
      TravelTime = (double) newJSONObject.get(TRAVELTIME_KEY);
    } else {
      TravelTime = null;
    }

    // Optional values
    // statistical spread
    if (newJSONObject.containsKey(STATISTICALSPREAD_KEY)) {
      StatisticalSpread = (double) newJSONObject.get(STATISTICALSPREAD_KEY);
    } else {
      StatisticalSpread = null;
    }

    // Observability
    if (newJSONObject.containsKey(OBSERVABILITY_KEY)) {
      Observability = (double) newJSONObject.get(OBSERVABILITY_KEY);
    } else {
      Observability = null;
    }

    // RayParameter
    if (newJSONObject.containsKey(RAYPARAMETER_KEY)) {
      RayParameter = (double) newJSONObject.get(RAYPARAMETER_KEY);
    } else {
      RayParameter = null;
    }
  }

  /**
   * Constructs the class from a TravelTimePlotDataSample object, populating members (copy
   * constructor)
   *
   * @param sourceObject - A TravelTimeData object.
   */
  public TravelTimePlotDataSample(TravelTimePlotDataSample sourceObject) {
    reload(
        sourceObject.Distance,
        sourceObject.TravelTime,
        sourceObject.StatisticalSpread,
        sourceObject.Observability,
        sourceObject.RayParameter);
  }

  /**
   * Reload Function
   *
   * <p>The reload function for the TravelTimeData class. Initializes members to provided values.
   *
   * @param newDistance - A Double containing the Distance in degrees
   * @param newTravelTime - A Double containing the travel time in seconds
   * @param newStatisticalSpread - A Double containing the observed travel time scatter in seconds
   * @param newObservability - A Double containing the statistical Observability of the sample
   * @param newRayParameter - A Double containing the ray parameter of the sample
   */
  public void reload(
      Double newDistance,
      Double newTravelTime,
      Double newStatisticalSpread,
      Double newObservability,
      Double newRayParameter) {

    Distance = newDistance;
    TravelTime = newTravelTime;
    StatisticalSpread = newStatisticalSpread;
    Observability = newObservability;
    RayParameter = newRayParameter;
  }

  /**
   * Converts the contents of the class to a json object
   *
   * @return Returns a JSONObject containing the class contents
   */
  @SuppressWarnings("unchecked")
  public JSONObject toJSON() {

    JSONObject newJSONObject = new JSONObject();

    // Distance
    if (Distance != null) {
      newJSONObject.put(DISTANCE_KEY, Distance);
    }

    // travel time
    if (TravelTime != null) {
      newJSONObject.put(TRAVELTIME_KEY, TravelTime);
    }

    // statistical spread
    if (StatisticalSpread != null) {
      newJSONObject.put(STATISTICALSPREAD_KEY, StatisticalSpread);
    }

    // Observability
    if (Observability != null) {
      newJSONObject.put(OBSERVABILITY_KEY, Observability);
    }

    // RayParameter
    if (RayParameter != null) {
      newJSONObject.put(RAYPARAMETER_KEY, RayParameter);
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

    // Required values
    // Distance
    if (Distance == null) {
      // Distance not found
      errorList.add("No Distance in TravelTimePlotDataSample Class.");
    }

    // travel time
    if (TravelTime == null) {
      // travel time not found
      errorList.add("No Travel Time in TravelTimePlotDataSample Class.");
    }

    return (errorList);
  }
}
