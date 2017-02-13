package gov.usgs.processingformats;

import java.util.*;

import org.json.simple.JSONObject;

import gov.usgs.processingformats.ProcessingInt;

/**
 * a conversion class used to create, parse, and validate travel time plot
 * sample data
 * 
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class TravelTimePlotDataSample implements ProcessingInt {

	/**
	 * JSON Keys
	 */
	public static final String DISTANCE_KEY = "Distance";
	public static final String TRAVELTIME_KEY = "TravelTime";
	public static final String STATISTICALSPREAD_KEY = "StatisticalSpread";
	public static final String OBSERVABILITY_KEY = "Observability";

	/**
	 * Required distance in degrees
	 */
	private Double distance;

	/**
	 * Required travel time in seconds
	 */
	private Double travelTime;

	/**
	 * Optional observed travel time scatter in seconds.
	 */
	private Double statisticalSpread;

	/**
	 * Optional statistical observability of the seismic phase.
	 */
	private Double observability;

	/**
	 * The constructor for the TravelTimePlotDataSample class. Initializes
	 * members to null values.
	 */
	public TravelTimePlotDataSample() {

		reload(null, null, null, null);
	}

	/**
	 * Advanced constructor
	 * 
	 * The advanced constructor for the TravelTimePlotDataSample class.
	 * Initializes members to provided values.
	 * 
	 * @param newDistance
	 *            - A Double containing the distance in degrees
	 * @param newTravelTime
	 *            - A Double containing the travel time in seconds
	 * @param newStatisticalSpread
	 *            - A Double containing the observed travel time scatter in
	 *            seconds
	 * @param newObservability
	 *            - A Double containing the statistical observability of the
	 *            sample
	 */
	public TravelTimePlotDataSample(Double newDistance, Double newTravelTime,
			Double newStatisticalSpread, Double newObservability) {

		reload(newDistance, newTravelTime, newStatisticalSpread,
				newObservability);
	}

	/**
	 * Constructs the class from a JSONObject, populating members
	 * 
	 * @param newJSONObject
	 *            - A JSONObject.
	 */
	public TravelTimePlotDataSample(JSONObject newJSONObject) {

		// Required values
		// distance
		if (newJSONObject.containsKey(DISTANCE_KEY)) {
			distance = (double) newJSONObject.get(DISTANCE_KEY);
		} else {
			distance = null;
		}

		// travel time
		if (newJSONObject.containsKey(TRAVELTIME_KEY)) {
			travelTime = (double) newJSONObject.get(TRAVELTIME_KEY);
		} else {
			travelTime = null;
		}

		// Optional values
		// statistical spread
		if (newJSONObject.containsKey(STATISTICALSPREAD_KEY)) {
			statisticalSpread = (double) newJSONObject
					.get(STATISTICALSPREAD_KEY);
		} else {
			statisticalSpread = null;
		}

		// observability
		if (newJSONObject.containsKey(OBSERVABILITY_KEY)) {
			observability = (double) newJSONObject.get(OBSERVABILITY_KEY);
		} else {
			observability = null;
		}
	}

	/**
	 * Constructs the class from a TravelTimeData object, populating members
	 * (copy constructor)
	 * 
	 * @param sourceObject
	 *            - A TravelTimeData object.
	 */
	public TravelTimePlotDataSample(TravelTimePlotDataSample sourceObject) {
		reload(sourceObject.distance, sourceObject.travelTime,
				sourceObject.statisticalSpread, sourceObject.observability);
	}

	/**
	 * Reload Function
	 * 
	 * The reload function for the TravelTimeData class. Initializes members to
	 * provided values.
	 * 
	 * @param newDistance
	 *            - A Double containing the distance in degrees
	 * @param newTravelTime
	 *            - A Double containing the travel time in seconds
	 * @param newStatisticalSpread
	 *            - A Double containing the observed travel time scatter in
	 *            seconds
	 * @param newObservability
	 *            - A Double containing the statistical observability of the
	 *            sample
	 */
	public void reload(Double newDistance, Double newTravelTime,
			Double newStatisticalSpread, Double newObservability) {

		distance = newDistance;
		travelTime = newTravelTime;
		statisticalSpread = newStatisticalSpread;
		observability = newObservability;
	}

	/**
	 * Converts the contents of the class to a json object
	 * 
	 * @return Returns a JSONObject containing the class contents
	 */
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {

		JSONObject newJSONObject = new JSONObject();

		Double jsonDistance = getDistance();
		Double jsonTravelTime = getTravelTime();
		Double jsonStatisticalSpread = getStatisticalSpread();
		Double jsonObservability = getObservability();

		// distance
		if (jsonDistance != null) {
			newJSONObject.put(DISTANCE_KEY, jsonDistance);
		}

		// travel time
		if (jsonTravelTime != null) {
			newJSONObject.put(TRAVELTIME_KEY, jsonTravelTime);
		}

		// statistical spread
		if (jsonStatisticalSpread != null) {
			newJSONObject.put(STATISTICALSPREAD_KEY, jsonStatisticalSpread);
		}

		// observability
		if (jsonObservability != null) {
			newJSONObject.put(OBSERVABILITY_KEY, jsonObservability);
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

		Double jsonDistance = getDistance();
		Double jsonTravelTime = getTravelTime();
		Double jsonStatisticalSpread = getStatisticalSpread();
		Double jsonObservability = getObservability();

		// Required values
		// distance
		if (jsonDistance == null) {
			// distance not found
			errorList.add("No Distance in TravelTimePlotDataSample Class.");
		}

		// travel time
		if (jsonTravelTime == null) {
			// travel time not found
			errorList.add("No Travel Time in TravelTimePlotDataSample Class.");
		}

		// statistical spread
		if (jsonStatisticalSpread == null) {
			// statistical spread not found
			errorList.add(
					"No Statistical Spread in TravelTimePlotDataSample Class.");
		}

		// observability
		if (jsonObservability == null) {
			// observability not found
			errorList
					.add("No Observability in TravelTimePlotDataSample Class.");
		}

		return (errorList);
	}

	/**
	 * @return the distance
	 */
	public Double getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(Double distance) {
		this.distance = distance;
	}

	/**
	 * @return the travelTime
	 */
	public Double getTravelTime() {
		return travelTime;
	}

	/**
	 * @param travelTime
	 *            the travelTime to set
	 */
	public void setTravelTime(Double travelTime) {
		this.travelTime = travelTime;
	}

	/**
	 * @return the statisticalSpread
	 */
	public Double getStatisticalSpread() {
		return statisticalSpread;
	}

	/**
	 * @param statisticalSpread
	 *            the statisticalSpread to set
	 */
	public void setStatisticalSpread(Double statisticalSpread) {
		this.statisticalSpread = statisticalSpread;
	}

	/**
	 * @return the observability
	 */
	public Double getObservability() {
		return observability;
	}

	/**
	 * @param observability
	 *            the observability to set
	 */
	public void setObservability(Double observability) {
		this.observability = observability;
	}

}
