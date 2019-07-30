package gov.usgs.processingformats;

import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONObject;

/**
 * a conversion class used to create, parse, and validate hypocenter data as
 * part of processing data.
 *
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class Hypocenter implements ProcessingInt {

	/**
	 * JSON Keys
	 */
	public static final String LATITUDE_KEY = "Latitude";
	public static final String LONGITUDE_KEY = "Longitude";
	public static final String DEPTH_KEY = "Depth";
	public static final String TIME_KEY = "Time";
	public static final String LATITUDE_ERROR_KEY = "LatitudeError";
	public static final String LONGITUDE_ERROR_KEY = "LongitudeError";
	public static final String DEPTH_ERROR_KEY = "DepthError";
	public static final String TIME_ERROR_KEY = "TimeError";

	/**
	 * Required Double containing the latitude in degrees 
	 */
	private Double latitude;

	/**
	 * Required Double containing the longitude in degrees 
	 */
	private Double longitude;

	/**
	 * Required time for this hypo
	 */
	private Date time;

	/**
	 * Required Double containing the depth in kilometers
	 */
	private Double depth;

	/**
	 * Optional Double containing the latitude error
	 */
	private Double latitudeError;

	/**
	 * Optional Double containing the longitude error
	 */
	private Double longitudeError;

	/**
	 * Optional Double containing the time error
	 */
	private Double timeError;

	/**
	 * Optional Double containing the depth error
	 */
	private Double depthError;

	/**
	 * The constructor for the Hypo class. Initializes members to null values.
	 */
	public Hypocenter() {

		latitude = null;
		longitude = null;
		time = null;
		depth = null;
		latitudeError = null;
		longitudeError = null;
		timeError = null;
		depthError = null;
	}

	/**
	 * Advanced constructor
	 *
	 * The alternate advanced constructor for the Hypo class. Initializes
	 * members to provided values.
	 *
	 * @param newLatitude
	 *            - A Double containing the latitude in degrees to use
	 * @param newLongitude
	 *            - A Double containing the longitude in degrees to use
	 * @param newTime
	 *            - A Date containing the new origin time to use
	 * @param newDepth
	 *            - A Double containing the depth in kilometers to use
	 * @param newLatitudeError
	 *            - A Double containing the latitude error to use, null to omit
	 * @param newLongitudeError
	 *            - A Double containing the longitude error to use, null to omit
	 * @param newTimeError
	 *            - A Double containing the new time error to use, null to omit
	 * @param newDepthError
	 *            - A Double containing the depth error to use, null to omit
	 */
	public Hypocenter(Double newLatitude, Double newLongitude, Date newTime,
			Double newDepth, Double newLatitudeError, Double newLongitudeError,
			Double newTimeError, Double newDepthError) {

		reload(newLatitude, newLongitude, newTime, newDepth, newLatitudeError,
				newLongitudeError, newTimeError, newDepthError);
	}

	/**
	 * Reload Function
	 *
	 * The reload function for the Hypo class. Initializes members to provided
	 * values.
	 *
	 *
	 * @param newLatitude
	 *            - A Double containing the latitude in degrees to use
	 * @param newLongitude
	 *            - A Double containing the longitude in degrees to use
	 * @param newTime
	 *            - A Date containing the new origin time to use
	 * @param newDepth
	 *            - A Double containing the depth in kilometers to use
	 * @param newLatitudeError
	 *            - A Double containing the latitude error to use, null to omit
	 * @param newLongitudeError
	 *            - A Double containing the longitude error to use, null to omit
	 * @param newTimeError
	 *            - A Double containing the new time error to use, null to omit
	 * @param newDepthError
	 *            - A Double containing the depth error to use, null to omit
	 */
	public void reload(Double newLatitude, Double newLongitude, Date newTime,
			Double newDepth, Double newLatitudeError, Double newLongitudeError,
			Double newTimeError, Double newDepthError) {

		latitude = newLatitude;
		longitude = newLongitude;
		time = newTime;
		depth = newDepth;
		latitudeError = newLatitudeError;
		longitudeError = newLongitudeError;
		timeError = newTimeError;
		depthError = newDepthError;
	}

	/**
	 * Constructs the class from a JSONObject, populating members
	 *
	 * @param newJSONObject
	 *            - A JSONObject.
	 */
	public Hypocenter(JSONObject newJSONObject) {

		// Required values
		// latitude
		if (newJSONObject.containsKey(LATITUDE_KEY)) {
			latitude = (double) newJSONObject.get(LATITUDE_KEY);
		} else {
			latitude = null;
		}

		// longitude
		if (newJSONObject.containsKey(LONGITUDE_KEY)) {
			longitude = (double) newJSONObject.get(LONGITUDE_KEY);
		} else {
			longitude = null;
		}

		// time
		if (newJSONObject.containsKey(TIME_KEY)) {
			time = Utility.getDate(newJSONObject.get(TIME_KEY).toString());
		} else {
			time = null;
		}

		// depth
		if (newJSONObject.containsKey(DEPTH_KEY)) {
			depth = (double) newJSONObject.get(DEPTH_KEY);
		} else {
			depth = null;
		}

		// optional values
		// latitude error
		if (newJSONObject.containsKey(LATITUDE_ERROR_KEY)) {
			latitudeError = (double) newJSONObject.get(LATITUDE_ERROR_KEY);
		} else {
			latitudeError = null;
		}

		// longitude error
		if (newJSONObject.containsKey(LONGITUDE_ERROR_KEY)) {
			longitudeError = (double) newJSONObject.get(LONGITUDE_ERROR_KEY);
		} else {
			longitudeError = null;
		}

		// time error
		if (newJSONObject.containsKey(TIME_ERROR_KEY)) {
			timeError = (double) newJSONObject.get(TIME_ERROR_KEY);
		} else {
			timeError = null;
		}

		// depth error
		if (newJSONObject.containsKey(DEPTH_ERROR_KEY)) {
			depthError = (double) newJSONObject.get(DEPTH_ERROR_KEY);
		} else {
			depthError = null;
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

		Double jsonLatitude = getLatitude();
		Double jsonLongitude = getLongitude();
		Date jsonTime = getTime();
		Double jsonDepth = getDepth();
		Double jsonLatitudeError = getLatitudeError();
		Double jsonLongitudeError = getLongitudeError();
		Double jsonTimeError = getTimeError();
		Double jsonDepthError = getDepthError();

		// Required values
		// latitude
		if (jsonLatitude != null) {
			newJSONObject.put(LATITUDE_KEY, jsonLatitude);
		}

		// longitude
		if (jsonLongitude != null) {
			newJSONObject.put(LONGITUDE_KEY, jsonLongitude);
		}

		// depth
		if (jsonDepth != null) {
			newJSONObject.put(DEPTH_KEY, jsonDepth);
		}

		// time
		if (jsonTime != null) {
			newJSONObject.put(TIME_KEY, Utility.formatDate(jsonTime));
		}

		// Optional values
		// latitude error
		if (jsonLatitudeError != null) {
			newJSONObject.put(LATITUDE_ERROR_KEY, jsonLatitudeError);
		}

		// longitude error
		if (jsonLongitudeError != null) {
			newJSONObject.put(LONGITUDE_ERROR_KEY, jsonLongitudeError);
		}

		// depth error
		if (jsonDepthError != null) {
			newJSONObject.put(DEPTH_ERROR_KEY, jsonDepthError);
		}

		// time error
		if (jsonTimeError != null) {
			newJSONObject.put(TIME_ERROR_KEY, jsonTimeError);
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

		Double jsonLatitude = getLatitude();
		Double jsonLongitude = getLongitude();
		Date jsonTime = getTime();
		Double jsonDepth = getDepth();

		ArrayList<String> errorList = new ArrayList<String>();

		// Required Keys
		// latitude
		if (jsonLatitude == null) {
			// latitude not found
			errorList.add("No Latitude in Hypo Class.");
		} else if ((jsonLatitude < -90.0) || (jsonLatitude > 90.0)) {
			// invalid latitude
			errorList.add(
					"Latitude in Hypo Class not in the range of -90 to 90.");
		}

		// longitude
		if (jsonLongitude == null) {
			// longitude not found
			errorList.add("No Longitude in Hypo Class.");
		} else if ((jsonLongitude < -180.0) || (jsonLongitude > 180.0)) {
			// invalid longitude
			errorList.add(
					"Longitude in Hypo Class not in the range of -180 to 180.");
		}

		// time
		if (jsonTime == null) {
			// time not found
			errorList.add("No Time in Hypo Class.");
		}

		// depth
		if (jsonDepth == null) {
			// depth not found
			errorList.add("No Depth in Hypo Class.");
		} else if ((jsonDepth < -100.0) || (jsonDepth > 1500.0)) {
			// invalid depth
			errorList.add(
					"Depth in Hypo Class not in the range of -100 to 1500.");
		}

		// Optional Keys
		// Currently no validation criteria for optional values LatitudeError,
		// LongitudeError, TimeError, and DepthError.

		// success
		return (errorList);
	}

	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @return the depth
	 */
	public Double getDepth() {
		return depth;
	}

	/**
	 * @return the latitudeError
	 */
	public Double getLatitudeError() {
		return latitudeError;
	}

	/**
	 * @return the longitudeError
	 */
	public Double getLongitudeError() {
		return longitudeError;
	}

	/**
	 * @return the timeError
	 */
	public Double getTimeError() {
		return timeError;
	}

	/**
	 * @return the depthError
	 */
	public Double getDepthError() {
		return depthError;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @param depth
	 *            the depth to set
	 */
	public void setDepth(Double depth) {
		this.depth = depth;
	}

	/**
	 * @param latitudeError
	 *            the latitudeError to set
	 */
	public void setLatitudeError(Double latitudeError) {
		this.latitudeError = latitudeError;
	}

	/**
	 * @param longitudeError
	 *            the longitudeError to set
	 */
	public void setLongitudeError(Double longitudeError) {
		this.longitudeError = longitudeError;
	}

	/**
	 * @param timeError
	 *            the timeError to set
	 */
	public void setTimeError(Double timeError) {
		this.timeError = timeError;
	}

	/**
	 * @param depthError
	 *            the depthError to set
	 */
	public void setDepthError(Double depthError) {
		this.depthError = depthError;
	}

}
