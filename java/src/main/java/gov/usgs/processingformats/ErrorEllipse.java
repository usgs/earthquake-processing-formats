package gov.usgs.processingformats;

import java.util.ArrayList;
import org.json.simple.JSONObject;

/**
 * a conversion class used to create, parse, and validate error ellipse data
 *
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class ErrorEllipse implements ProcessingInt {

  /** JSON Keys */
  public static final String E0_KEY = "E0";

  public static final String E1_KEY = "E1";
  public static final String E2_KEY = "E2";

  public static final String MAXIMUM_HORIZONTAL_KEY = "MaximumHorizontalProjection";
  public static final String MAXIMUM_VERTICAL_KEY = "MaximumVerticalProjection";
  public static final String EQUIVALENT_HORIZONTAL_KEY = "EquivalentHorizontalRadius";

  /** Required first error semi-axis. */
  public ErrorEllipseAxis E0;

  /** Required second error semi-axis. */
  public ErrorEllipseAxis E1;

  /** Required third error semi-axis. */
  public ErrorEllipseAxis E2;

  /** Required horizontal projection of the error ellipsoid in kilometers */
  public Double MaximumHorizontalProjection;

  /** Required maximum vertical projection of the error ellipsoid in kilometers. */
  public Double MaximumVerticalProjection;

  /** Required equivalent radius of the horizontal error ellipsoid in kilometers. */
  public Double EquivalentHorizontalRadius;

  /** The constructor for the ErrorEllipse class. Initializes members to null values. */
  public ErrorEllipse() {
    E0 = null;
    E1 = null;
    E2 = null;
    MaximumHorizontalProjection = null;
    MaximumVerticalProjection = null;
    EquivalentHorizontalRadius = null;
  }

  /**
   * The alternate advanced constructor for the ErrorEllipse class. Initializes members to provided
   * values.
   *
   * @param newE0 - An ErrorEllipseAxis containing the first axis of the error ellipsoid
   * @param newE1 - An ErrorEllipseAxis containing the second axis of the error ellipsoid
   * @param newE2 - An ErrorEllipseAxis containing the third axis of the error ellipsoid
   * @param newMaximumHorizontalProjection - A Double containing the horizontal projection of the
   *     error ellipsoid in kilometers
   * @param newMaximumVerticalProjection - A Double containing the vertical projection of the error
   *     ellipsoid in km in kilometers
   * @param newEquivalentHorizontalRadius - A Double containing the equivalent radius of the
   *     horizontal error ellipsoid in kilometers
   */
  public ErrorEllipse(
      ErrorEllipseAxis newE0,
      ErrorEllipseAxis newE1,
      ErrorEllipseAxis newE2,
      Double newMaximumHorizontalProjection,
      Double newMaximumVerticalProjection,
      Double newEquivalentHorizontalRadius) {

    reload(
        newE0,
        newE1,
        newE2,
        newMaximumHorizontalProjection,
        newMaximumVerticalProjection,
        newEquivalentHorizontalRadius);
  }

  /**
   * The alternate advanced constructor for the ErrorEllipse class. Initializes members to provided
   * values.
   *
   * @param newE0Error - A Double containing the length of the first axis of the error ellipsoid in
   *     kilometers
   * @param newE0Azimuth - A Double containing the azimuth of the first axis of the error ellipsoid
   *     in degrees
   * @param newE0Dip - A Double containing the dip of the first axis of error ellipsoid in degrees.
   * @param newE1Error - A Double containing the length of the second axis of the error ellipsoid in
   *     kilometers
   * @param newE1Azimuth - A Double containing the azimuth of the second axis of the error ellipsoid
   *     in degrees
   * @param newE1Dip - A Double containing the dip of the second axis of error ellipsoid in degrees.
   * @param newE2Error - A Double containing the length of the third axis of the error ellipsoid in
   *     kilometers
   * @param newE2Azimuth - A Double containing the the azimuth of the third axis of the error
   *     ellipsoid in degrees
   * @param newE2Dip - A Double containing the dip of the third axis of error ellipsoid in degrees.
   * @param newMaximumHorizontalProjection - A Double containing the horizontal projection of the
   *     error ellipsoid in kilometers
   * @param newMaximumVerticalProjection - A Double containing the vertical projection of the error
   *     ellipsoid in km in kilometers
   * @param newEquivalentHorizontalRadius - A Double containing the equivalent radius of the
   *     horizontal error ellipsoid in kilometers
   */
  public ErrorEllipse(
      Double newE0Error,
      Double newE0Azimuth,
      Double newE0Dip,
      Double newE1Error,
      Double newE1Azimuth,
      Double newE1Dip,
      Double newE2Error,
      Double newE2Azimuth,
      Double newE2Dip,
      Double newMaximumHorizontalProjection,
      Double newMaximumVerticalProjection,
      Double newEquivalentHorizontalRadius) {

    reload(
        new ErrorEllipseAxis(newE0Error, newE0Azimuth, newE0Dip),
        new ErrorEllipseAxis(newE1Error, newE1Azimuth, newE1Dip),
        new ErrorEllipseAxis(newE2Error, newE2Azimuth, newE2Dip),
        newMaximumHorizontalProjection,
        newMaximumVerticalProjection,
        newEquivalentHorizontalRadius);
  }

  /**
   * Reload Function
   *
   * <p>The reload function for the error ellipse class. Initializes members to provided values.
   *
   * @param newE0 - An ErrorEllipseAxis containing the first axis of the error ellipsoid
   * @param newE1 - An ErrorEllipseAxis containing the second axis of the error ellipsoid
   * @param newE2 - An ErrorEllipseAxis containing the third axis of the error ellipsoid
   * @param newMaximumHorizontalProjection - A Double containing the horizontal projection of the
   *     error ellipsoid in kilometers
   * @param newMaximumVerticalProjection - A Double containing the vertical projection of the error
   *     ellipsoid in km in kilometers
   * @param newEquivalentHorizontalRadius - A Double containing the equivalent radius of the
   *     horizontal error ellipsoid in kilometers
   */
  public void reload(
      ErrorEllipseAxis newE0,
      ErrorEllipseAxis newE1,
      ErrorEllipseAxis newE2,
      Double newMaximumHorizontalProjection,
      Double newMaximumVerticalProjection,
      Double newEquivalentHorizontalRadius) {
    E0 = newE0;
    E1 = newE1;
    E2 = newE2;
    MaximumHorizontalProjection = newMaximumHorizontalProjection;
    MaximumVerticalProjection = newMaximumVerticalProjection;
    EquivalentHorizontalRadius = newEquivalentHorizontalRadius;
  }

  /**
   * Constructs the class from a JSONObject, populating members
   *
   * @param newJSONObject - A JSONObject.
   */
  public ErrorEllipse(JSONObject newJSONObject) {
    // Required values
    // E0
    if (newJSONObject.containsKey(E0_KEY)) {
      E0 = new ErrorEllipseAxis((JSONObject) newJSONObject.get(E0_KEY));
    } else {
      E0 = null;
    }

    // E1
    if (newJSONObject.containsKey(E1_KEY)) {
      E1 = new ErrorEllipseAxis((JSONObject) newJSONObject.get(E1_KEY));
    } else {
      E1 = null;
    }

    // E2
    if (newJSONObject.containsKey(E2_KEY)) {
      E2 = new ErrorEllipseAxis((JSONObject) newJSONObject.get(E2_KEY));
    } else {
      E2 = null;
    }

    // MaximumHorizontalProjection
    if (newJSONObject.containsKey(MAXIMUM_HORIZONTAL_KEY)) {
      MaximumHorizontalProjection = (double) newJSONObject.get(MAXIMUM_HORIZONTAL_KEY);
    } else {
      MaximumHorizontalProjection = null;
    }

    // MaximumVerticalProjection
    if (newJSONObject.containsKey(MAXIMUM_VERTICAL_KEY)) {
      MaximumVerticalProjection = (double) newJSONObject.get(MAXIMUM_VERTICAL_KEY);
    } else {
      MaximumVerticalProjection = null;
    }

    // EquivalentHorizontalRadius
    if (newJSONObject.containsKey(EQUIVALENT_HORIZONTAL_KEY)) {
      EquivalentHorizontalRadius = (double) newJSONObject.get(EQUIVALENT_HORIZONTAL_KEY);
    } else {
      EquivalentHorizontalRadius = null;
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
    if (E0 != null) {
      newJSONObject.put(E0_KEY, E0.toJSON());
    }

    // E1
    if (E1 != null) {
      newJSONObject.put(E1_KEY, E1.toJSON());
    }

    // E2
    if (E2 != null) {
      newJSONObject.put(E2_KEY, E2.toJSON());
    }

    // MaximumHorizontalProjection
    if (MaximumHorizontalProjection != null) {
      newJSONObject.put(MAXIMUM_HORIZONTAL_KEY, MaximumHorizontalProjection);
    }

    // MaximumVerticalProjection
    if (MaximumVerticalProjection != null) {
      newJSONObject.put(MAXIMUM_VERTICAL_KEY, MaximumVerticalProjection);
    }

    // jEquivalentHorizontalRadius
    if (EquivalentHorizontalRadius != null) {
      newJSONObject.put(EQUIVALENT_HORIZONTAL_KEY, EquivalentHorizontalRadius);
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

    // E0
    if (E0 == null) {
      // E0 not found
      errorList.add("No E0 in ErrorEllipse Class.");
    } else if (!E0.isValid()) {
      // E0 invalid
      errorList.add("Invalid E0 in ErrorEllipse Class.");
    }

    // E1
    if (E1 == null) {
      // E1 not found
      errorList.add("No E1 in ErrorEllipse Class.");
    } else if (!E1.isValid()) {
      // E1 invalid
      errorList.add("Invalid E1 in ErrorEllipse Class.");
    }

    // E2
    if (E2 == null) {
      // E2 not found
      errorList.add("No E2 in ErrorEllipse Class.");
    } else if (!E2.isValid()) {
      // E2 invalid
      errorList.add("Invalid E2 in ErrorEllipse Class.");
    }

    // MaximumHorizontalProjection
    if (MaximumHorizontalProjection == null) {
      // MaximumHorizontalProjection not found
      errorList.add("No MaximumHorizontalProjection in ErrorEllipse Class.");
    }

    // MaximumVerticalProjection
    if (MaximumVerticalProjection == null) {
      // MaximumVerticalProjection not found
      errorList.add("No MaximumVerticalProjection in ErrorEllipse Class.");
    }

    // EquivalentHorizontalRadius
    if (EquivalentHorizontalRadius == null) {
      // EquivalentHorizontalRadius not found
      errorList.add("No EquivalentHorizontalRadius in ErrorEllipse Class.");
    }

    // success
    return (errorList);
  }
}
