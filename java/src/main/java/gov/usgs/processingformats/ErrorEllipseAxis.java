package gov.usgs.processingformats;

import java.util.ArrayList;
import org.json.simple.JSONObject;

/**
 * a conversion class used to create, parse, and validate error ellipse data
 *
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class ErrorEllipseAxis implements ProcessingInt {
  /** JSON Keys */
  public static final String ERROR_KEY = "Error";

  public static final String AZIMUTH_KEY = "Azimuth";
  public static final String DIP_KEY = "Dip";

  /** Required error. */
  public Double Error;

  /** Required azimuth. */
  public Double Azimuth;

  /** Required dip. */
  public Double Dip;

  /** The constructor for the ErrorEllipseAxis class. Initializes members to null values. */
  public ErrorEllipseAxis() {
    Error = null;
    Azimuth = null;
    Dip = null;
  }

  /**
   * The alternate advanced constructor for the ErrorEllipse class. Initializes members to provided
   * values.
   *
   * @param newError - A Double containing the length of the axis of the error ellipsoid in
   *     kilometers
   * @param newAzimuth - A Double containing the azimuth of the axis of the error ellipsoid in
   *     degrees
   * @param newDip - A Double containing the dip of the axis of error ellipsoid in degrees.
   */
  public ErrorEllipseAxis(Double newError, Double newAzimuth, Double newDip) {
    reload(newError, newAzimuth, newDip);
  }

  /**
   * Reload Function
   *
   * <p>The reload function for the ErrorEllipseAxis class. Initializes members to provided values.
   *
   * @param newError - A Double containing the length of the axis of the error ellipsoid in
   *     kilometers
   * @param newAzimuth - A Double containing the azimuth of the axis of the error ellipsoid in
   *     degrees
   * @param newDip - A Double containing the dip of the axis of error ellipsoid in degrees.
   */
  public void reload(Double newError, Double newAzimuth, Double newDip) {
    Error = newError;
    Azimuth = newAzimuth;
    Dip = newDip;
  }

  /**
   * Constructs the class from a JSONObject, populating members
   *
   * @param newJSONObject - A JSONObject.
   */
  public ErrorEllipseAxis(JSONObject newJSONObject) {
    // Required values
    // error
    if (newJSONObject.containsKey(ERROR_KEY)) {
      Error = (double) newJSONObject.get(ERROR_KEY);
    } else {
      Error = null;
    }

    // azimuth
    if (newJSONObject.containsKey(AZIMUTH_KEY)) {
      Azimuth = (double) newJSONObject.get(AZIMUTH_KEY);
    } else {
      Azimuth = null;
    }

    // dip
    if (newJSONObject.containsKey(DIP_KEY)) {
      Dip = (double) newJSONObject.get(DIP_KEY);
    } else {
      Dip = null;
    }
  }

  /**
   * Converts the contents of the class to a json object Overridden from ProcessingBase.
   *
   * @return Returns a JSONObject containing the class contents
   */
  @SuppressWarnings("unchecked")
  public JSONObject toJSON() {

    JSONObject newJSONObject = new JSONObject();

    // Required values
    // E0
    if (Error != null) {
      newJSONObject.put(ERROR_KEY, Error);
    }
    if (Azimuth != null) {
      newJSONObject.put(AZIMUTH_KEY, Azimuth);
    }
    if (Dip != null) {
      newJSONObject.put(DIP_KEY, Dip);
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

    // error
    if (Error == null) {
      // error not found
      errorList.add("No error in ErrorEllipseAxis Class.");
    }

    // azimuth
    if (Azimuth == null) {
      // azimuth not found
      errorList.add("No azimuth in ErrorEllipseAxis Class.");
    }

    //  dip
    if (Dip == null) {
      // dip not found
      errorList.add("No  dip in ErrorEllipseAxis Class.");
    }

    // success
    return (errorList);
  }
}
