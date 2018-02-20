package gov.usgs.processingformats;

import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * a conversion class used to create, parse, and validate error ellipse data
 *
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class ErrorEllipse implements ProcessingInt {

	/**
	 * JSON Keys
	 */
	public static final String E0_KEY = "E0";
	public static final String E1_KEY = "E1";
	public static final String E2_KEY = "E2";
	public static final String ERROR_KEY = "Error";
	public static final String AZIMUTH_KEY = "Azimuth";
	public static final String DIP_KEY = "Dip";
	public static final String MAXIMUM_HORIZONTAL_KEY = "MaximumHorizontalProjection";
	public static final String MAXIMUM_VERTICAL_KEY = "MaximumVerticalProjection";
	public static final String EQUIVALENT_HORIZONTAL_KEY = "EquivalentHorizontalRadius";

	/**
	 * Required E0 error.
	 */
	private Double e0Error;

	/**
	 * Required E0 azimuth.
	 */
	private Double e0Azimuth;

	/**
	 * Required E0 dip.
	 */
	private Double e0Dip;

	/**
	 * Required E1 error.
	 */
	private Double e1Error;

	/**
	 * Required E1 azimuth.
	 */
	private Double e1Azimuth;

	/**
	 * Required E1 dip.
	 */
	private Double e1Dip;

	/**
	 * Required E2 error.
	 */
	private Double e2Error;

	/**
	 * Required E2 azimuth.
	 */
	private Double e2Azimuth;

	/**
	 * Required E2 dip.
	 */
	private Double e2Dip;

	/**
	 * Required Maximum horizontal projection.
	 */
	private Double maximumHorizontalProjection;

	/**
	 * Required Maximum vertical projection.
	 */
	private Double maximumVerticalProjection;

	/**
	 * Required Equivalent horizontal radius.
	 */
	private Double equivalentHorizontalRadius;

	/**
	 * The constructor for the ErrorEllipse class. Initializes members to null
	 * values.
	 */
	public ErrorEllipse() {
		e0Error = null;
		e0Azimuth = null;
		e0Dip = null;
		e1Error = null;
		e1Azimuth = null;
		e1Dip = null;
		e2Error = null;
		e2Azimuth = null;
		e2Dip = null;
		maximumHorizontalProjection = null;
		maximumVerticalProjection = null;
		equivalentHorizontalRadius = null;
	}

	/**
	 * The advanced constructor for the ErrorEllipse class. Initializes members
	 * to provided values.
	 *
	 * @param newE0Error
	 *            - A Double containing the length of the first axis of the
	 *            error ellipsoid in kilometers
	 * @param newE0Azimuth
	 *            - A Double containing the azimuth of the first axis of the
	 *            error ellipsoid in degrees
	 * @param newE0Dip
	 *            - A Double containing the dip of the first axis of error
	 *            ellipsoid in degrees.
	 * @param newE1Error
	 *            - A Double containing the length of the second axis of the
	 *            error ellipsoid in kilometers
	 * @param newE1Azimuth
	 *            - A Double containing the azimuth of the second axis of the
	 *            error ellipsoid in degrees
	 * @param newE1Dip
	 *            - A Double containing the dip of the second axis of error
	 *            ellipsoid in degrees.
	 * @param newE2Error
	 *            - A Double containing the length of the third axis of the
	 *            error ellipsoid in kilometers
	 * @param newE2Azimuth
	 *            - A Double containing the the azimuth of the third axis of the
	 *            error ellipsoid in degrees
	 * @param newE2Dip
	 *            - A Double containing the dip of the third axis of error
	 *            ellipsoid in degrees.
	 * @param newMaximumHorizontalProjection
	 *            - A Double containing the horizontal projection of the error
	 *            ellipsoid in kilometers
	 * @param newMaximumVerticalProjection
	 *            - A Double containing the vertical projection of the error
	 *            ellipsoid in km in kilometers
	 * @param newEquivalentHorizontalRadius
	 *            - A Boolean containing the equivalent radius of the horizontal
	 *            error ellipsoid in kilometers
	 */
	public ErrorEllipse(Double newE0Error, Double newE0Azimuth, Double newE0Dip,
			Double newE1Error, Double newE1Azimuth, Double newE1Dip,
			Double newE2Error, Double newE2Azimuth, Double newE2Dip,
			Double newMaximumHorizontalProjection,
			Double newMaximumVerticalProjection,
			Double newEquivalentHorizontalRadius) {
		e0Error = newE0Error;
		e0Azimuth = newE0Azimuth;
		e0Dip = newE0Dip;
		e1Error = newE1Error;
		e1Azimuth = newE1Azimuth;
		e1Dip = newE1Dip;
		e2Error = newE2Error;
		e2Azimuth = newE2Azimuth;
		e2Dip = newE2Dip;
		maximumHorizontalProjection = newMaximumHorizontalProjection;
		maximumVerticalProjection = newMaximumVerticalProjection;
		equivalentHorizontalRadius = newEquivalentHorizontalRadius;
	}

	/**
	 * Constructs the class from a JSONObject, populating members
	 *
	 * @param newJSONObject
	 *            - A JSONObject.
	 */
	public ErrorEllipse(JSONObject newJSONObject) {
		// Required values
		// E0
		if (newJSONObject.containsKey(E0_KEY)) {
			JSONObject e0JSONObject = (JSONObject) newJSONObject.get(E0_KEY);

			// error
			if (e0JSONObject.containsKey(ERROR_KEY)) {
				e0Error = (double) e0JSONObject.get(ERROR_KEY);
			} else {
				e0Error = null;
			}

			// azimuth
			if (e0JSONObject.containsKey(AZIMUTH_KEY)) {
				e0Azimuth = (double) e0JSONObject.get(AZIMUTH_KEY);
			} else {
				e0Azimuth = null;
			}

			// dip
			if (e0JSONObject.containsKey(DIP_KEY)) {
				e0Dip = (double) e0JSONObject.get(DIP_KEY);
			} else {
				e0Dip = null;
			}
		} else {
			e0Error = null;
			e0Azimuth = null;
			e0Dip = null;
		}

		// E1
		if (newJSONObject.containsKey(E1_KEY)) {
			JSONObject e1JSONObject = (JSONObject) newJSONObject.get(E1_KEY);

			// error
			if (e1JSONObject.containsKey(ERROR_KEY)) {
				e1Error = (double) e1JSONObject.get(ERROR_KEY);
			} else {
				e1Error = null;
			}

			// azimuth
			if (e1JSONObject.containsKey(AZIMUTH_KEY)) {
				e1Azimuth = (double) e1JSONObject.get(AZIMUTH_KEY);
			} else {
				e1Azimuth = null;
			}

			// dip
			if (e1JSONObject.containsKey(DIP_KEY)) {
				e1Dip = (double) e1JSONObject.get(DIP_KEY);
			} else {
				e1Dip = null;
			}
		} else {
			e1Error = null;
			e1Azimuth = null;
			e1Dip = null;
		}

		// E2
		if (newJSONObject.containsKey(E2_KEY)) {
			JSONObject e2JSONObject = (JSONObject) newJSONObject.get(E2_KEY);

			// error
			if (e2JSONObject.containsKey(ERROR_KEY)) {
				e2Error = (double) e2JSONObject.get(ERROR_KEY);
			} else {
				e2Error = null;
			}

			// azimuth
			if (e2JSONObject.containsKey(AZIMUTH_KEY)) {
				e2Azimuth = (double) e2JSONObject.get(AZIMUTH_KEY);
			} else {
				e2Azimuth = null;
			}

			// dip
			if (e2JSONObject.containsKey(DIP_KEY)) {
				e2Dip = (double) e2JSONObject.get(DIP_KEY);
			} else {
				e2Dip = null;
			}
		} else {
			e2Error = null;
			e2Azimuth = null;
			e2Dip = null;
		}

		// maximumHorizontalProjection
		if (newJSONObject.containsKey(MAXIMUM_HORIZONTAL_KEY)) {
			maximumHorizontalProjection = (double) newJSONObject
					.get(MAXIMUM_HORIZONTAL_KEY);
		} else {
			maximumHorizontalProjection = null;
		}

		// maximumVerticalProjection
		if (newJSONObject.containsKey(MAXIMUM_VERTICAL_KEY)) {
			maximumVerticalProjection = (double) newJSONObject
					.get(MAXIMUM_VERTICAL_KEY);
		} else {
			maximumVerticalProjection = null;
		}

		// equivalentHorizontalRadius
		if (newJSONObject.containsKey(EQUIVALENT_HORIZONTAL_KEY)) {
			equivalentHorizontalRadius = (double) newJSONObject
					.get(EQUIVALENT_HORIZONTAL_KEY);
		} else {
			equivalentHorizontalRadius = null;
		}

	}

	/**
	 * Converts the contents of the class to a json object Overridden from
	 * ProcessingBase.
	 *
	 * @return Returns a JSONObject containing the class contents
	 */
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {

		JSONObject newJSONObject = new JSONObject();

		Double jsonE0Error = getE0Error();
		Double jsonE0Azimuth = getE0Azimuth();
		Double jsonE0Dip = getE0Dip();
		Double jsonE1Error = getE1Error();
		Double jsonE1Azimuth = getE1Azimuth();
		Double jsonE1Dip = getE1Dip();
		Double jsonE2Error = getE2Error();
		Double jsonE2Azimuth = getE2Azimuth();
		Double jsonE2Dip = getE2Dip();
		Double jsonMaximumHorizontalProjection = getMaximumHorizontalProjection();
		Double jsonMaximumVerticalProjection = getMaximumVerticalProjection();
		Double jsonEquivalentHorizontalRadius = getEquivalentHorizontalRadius();

		// Required values
		// E0
		JSONObject newE0JSONObject = new JSONObject();
		if (jsonE0Error != null) {
			newE0JSONObject.put(ERROR_KEY, jsonE0Error);
		}
		if (jsonE0Azimuth != null) {
			newE0JSONObject.put(AZIMUTH_KEY, jsonE0Azimuth);
		}
		if (jsonE0Dip != null) {
			newE0JSONObject.put(DIP_KEY, jsonE0Dip);
		}
		newJSONObject.put(E0_KEY, newE0JSONObject);

		// E1
		JSONObject newE1JSONObject = new JSONObject();
		if (jsonE1Error != null) {
			newE1JSONObject.put(ERROR_KEY, jsonE1Error);
		}
		if (jsonE1Azimuth != null) {
			newE1JSONObject.put(AZIMUTH_KEY, jsonE1Azimuth);
		}
		if (jsonE1Dip != null) {
			newE1JSONObject.put(DIP_KEY, jsonE1Dip);
		}
		newJSONObject.put(E1_KEY, newE1JSONObject);

		// E2
		JSONObject newE2JSONObject = new JSONObject();
		if (jsonE2Error != null) {
			newE2JSONObject.put(ERROR_KEY, jsonE2Error);
		}
		if (jsonE2Azimuth != null) {
			newE2JSONObject.put(AZIMUTH_KEY, jsonE2Azimuth);
		}
		if (jsonE2Dip != null) {
			newE2JSONObject.put(DIP_KEY, jsonE2Dip);
		}
		newJSONObject.put(E2_KEY, newE2JSONObject);

		// maximumHorizontalProjection
		if (jsonMaximumHorizontalProjection != null) {
			newJSONObject.put(MAXIMUM_HORIZONTAL_KEY,
					jsonMaximumHorizontalProjection);
		}

		// maximumVerticalProjection
		if (jsonMaximumVerticalProjection != null) {
			newJSONObject.put(MAXIMUM_VERTICAL_KEY,
					jsonMaximumVerticalProjection);
		}

		// jequivalentHorizontalRadius
		if (jsonEquivalentHorizontalRadius != null) {
			newJSONObject.put(EQUIVALENT_HORIZONTAL_KEY,
					jsonEquivalentHorizontalRadius);
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

		Double jsonE0Error = getE0Error();
		Double jsonE0Azimuth = getE0Azimuth();
		Double jsonE0Dip = getE0Dip();
		Double jsonE1Error = getE1Error();
		Double jsonE1Azimuth = getE1Azimuth();
		Double jsonE1Dip = getE1Dip();
		Double jsonE2Error = getE2Error();
		Double jsonE2Azimuth = getE2Azimuth();
		Double jsonE2Dip = getE2Dip();
		Double jsonMaximumHorizontalProjection = getMaximumHorizontalProjection();
		Double jsonMaximumVerticalProjection = getMaximumVerticalProjection();
		Double jsonEquivalentHorizontalRadius = getEquivalentHorizontalRadius();

		ArrayList<String> errorList = new ArrayList<String>();

		// E0 errpr
		if (jsonE0Error == null) {
			// error not found
			errorList.add("No E0 error in ErrorEllipse Class.");
		}

		// E0 azimuth
		if (jsonE0Azimuth == null) {
			// azimuth not found
			errorList.add("No E0 azimuth in ErrorEllipse Class.");
		}

		// E0 dip
		if (jsonE0Dip == null) {
			// dip not found
			errorList.add("No E0 dip in ErrorEllipse Class.");
		}

		// E1 errpr
		if (jsonE1Error == null) {
			// error not found
			errorList.add("No E1 error in ErrorEllipse Class.");
		}

		// E1 azimuth
		if (jsonE1Azimuth == null) {
			// azimuth not found
			errorList.add("No E1 azimuth in ErrorEllipse Class.");
		}

		// E1 dip
		if (jsonE1Dip == null) {
			// dip not found
			errorList.add("No E1 dip in ErrorEllipse Class.");
		}

		// E2 errpr
		if (jsonE2Error == null) {
			// error not found
			errorList.add("No E2 error in ErrorEllipse Class.");
		}

		// E2 azimuth
		if (jsonE2Azimuth == null) {
			// azimuth not found
			errorList.add("No E2 azimuth in ErrorEllipse Class.");
		}

		// E2 dip
		if (jsonE2Dip == null) {
			// dip not found
			errorList.add("No E2 dip in ErrorEllipse Class.");
		}

		// MaximumHorizontalProjection
		if (jsonMaximumHorizontalProjection == null) {
			// MaximumHorizontalProjection not found
			errorList.add(
					"No MaximumHorizontalProjection in ErrorEllipse Class.");
		}

		// MaximumVerticalProjection
		if (jsonMaximumVerticalProjection == null) {
			// MaximumVerticalProjection not found
			errorList.add(
					"No MaximumVerticalProjection in ErrorEllipse Class.");
		}

		// EquivalentHorizontalRadius
		if (jsonEquivalentHorizontalRadius == null) {
			// EquivalentHorizontalRadius not found
			errorList.add(
					"No EquivalentHorizontalRadius in ErrorEllipse Class.");
		}

		// success
		return (errorList);
	}

	/**
	 * @return the e0Error
	 */
	public Double getE0Error() {
		return e0Error;
	}

	/**
	 * @return the e0Azimuth
	 */
	public Double getE0Azimuth() {
		return e0Azimuth;
	}

	/**
	 * @return the e0Dip
	 */
	public Double getE0Dip() {
		return e0Dip;
	}

	/**
	 * @return the e1Error
	 */
	public Double getE1Error() {
		return e1Error;
	}

	/**
	 * @return the e1Azimuth
	 */
	public Double getE1Azimuth() {
		return e1Azimuth;
	}

	/**
	 * @return the e1Dip
	 */
	public Double getE1Dip() {
		return e1Dip;
	}

	/**
	 * @return the e2Error
	 */
	public Double getE2Error() {
		return e2Error;
	}

	/**
	 * @return the e2Azimuth
	 */
	public Double getE2Azimuth() {
		return e2Azimuth;
	}

	/**
	 * @return the e2Dip
	 */
	public Double getE2Dip() {
		return e2Dip;
	}

	/**
	 * @return the maximumHorizontalProjection
	 */
	public Double getMaximumHorizontalProjection() {
		return maximumHorizontalProjection;
	}

	/**
	 * @return the maximumVerticallProjection
	 */
	public Double getMaximumVerticalProjection() {
		return maximumVerticalProjection;
	}

	/**
	 * @return the equivalentHorizontalRadius
	 */
	public Double getEquivalentHorizontalRadius() {
		return equivalentHorizontalRadius;
	}

}
