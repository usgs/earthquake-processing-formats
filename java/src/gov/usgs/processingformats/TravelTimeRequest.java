package gov.usgs.processingformats;

import java.util.*;

import org.json.simple.JSONObject;

import gov.usgs.processingformats.ProcessingInt;

/**
 * a conversion class used to create, parse, and validate travel time requests
 * 
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class TravelTimeRequest implements ProcessingInt {

	/**
	 * JSON Keys
	 */
	public static final String TYPE_KEY = "Type";
	public static final String DISTANCE_KEY = "Distance";
	public static final String ELEVATION_KEY = "Elevation";
	public static final String LATITUDE_KEY = "Latitude";
	public static final String LONGITUDE_KEY = "Longitude";
	public static final String DATA_KEY = "Data";

	/**
	 * Required type of data as a String
	 */
	private String type;

	/**
	 * Required source receiver distance in seconds
	 */
	private Double distance;

	/**
	 * Required receiver elevation relative to the WGS84 datum in kilometers
	 */
	private Double elevation;

	/**
	 * Optional geographic receiver latitude in degrees
	 */
	private Double latitude;

	/**
	 * Optional geographic receiver longitude in degrees
	 */
	private Double longitude;

	/**
	 * Returned travel time data (empty for requests)
	 */
	private TravelTimeData data;

	/**
	 * Returned travel time lot data (empty for requests)
	 */
	private TravelTimePlotData plotData;

	/**
	 * The constructor for the TravelTimeData class. Initializes members to null
	 * values.
	 */
	public TravelTimeRequest() {

		reload(null, null, null, null, null, null, null);
	}

	/**
	 * Advanced constructor
	 * 
	 * The advanced constructor for the TravelTimeRequest class. Initializes
	 * members to provided values.
	 * 
	 * @param newType
	 *            - A String containing the request type, "Standard", "Plot", or
	 *            "PlotStatistics", defaults to standard
	 * @param newDistance
	 *            - A Double containing the source-receiver distance in degrees
	 * @param newElevation
	 *            - A Double containing the receiver elevation relative to the
	 *            WGS84 datum in kilometers
	 * @param newLatitude
	 *            - An optional Double containing the geographic receiver
	 *            latitude in degrees, null to omit
	 * @param newLongitude
	 *            - An optional Double containing the geographic receiver
	 *            longitude in degrees, null to omit
	 * @param newData
	 *            - A TravelTimeData containing the returned travel time data
	 * @param newPlotData
	 *            - A TravelTimePlotData containing the returned travel time
	 *            plot data
	 */
	public TravelTimeRequest(String newType, Double newDistance,
			Double newElevation, Double newLatitude, Double newLongitude,
			TravelTimeData newData, TravelTimePlotData newPlotData) {

		reload(newType, newDistance, newElevation, newLatitude, newLongitude,
				newData, newPlotData);
	}

	/**
	 * Constructs the class from a JSONObject, populating members
	 * 
	 * @param newJSONObject
	 *            - A JSONObject.
	 */
	public TravelTimeRequest(JSONObject newJSONObject) {

		// Required values
		// type
		if (newJSONObject.containsKey(TYPE_KEY)) {
			type = newJSONObject.get(TYPE_KEY).toString();
		} else {
			type = null;
		}

		// distance
		if (newJSONObject.containsKey(DISTANCE_KEY)) {
			distance = (double) newJSONObject.get(DISTANCE_KEY);
		} else {
			distance = null;
		}

		// elevation
		if (newJSONObject.containsKey(ELEVATION_KEY)) {
			elevation = (double) newJSONObject.get(ELEVATION_KEY);
		} else {
			elevation = null;
		}

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

		// data
		if (newJSONObject.containsKey(DATA_KEY)) {
			JSONObject dataObject = (JSONObject) newJSONObject.get(DATA_KEY);

			// did we get an object
			if (dataObject == null) {
				data = null;
				plotData = null;
			} else {
				// does it have a type
				if (dataObject.containsKey(TYPE_KEY)) {

					// check type
					String dataType = dataObject.get(TYPE_KEY).toString();
					if (dataType.equals("TTData")) {
						data = new TravelTimeData(dataObject);
						plotData = null;
					} else if (dataType.equals("TTPlotData")) {
						data = null;
						plotData = new TravelTimePlotData(dataObject);
					} else {
						data = null;
						plotData = null;
					}
				} else {
					data = null;
					plotData = null;
				}
			}
		} else {
			data = null;
			plotData = null;
		}

	}

	/**
	 * Constructs the class from a TravelTimeData object, populating members
	 * (copy constructor)
	 * 
	 * @param sourceObject
	 *            - A TravelTimeData object.
	 */
	public TravelTimeRequest(TravelTimeRequest sourceObject) {
		reload(sourceObject.type, sourceObject.distance, sourceObject.elevation,
				sourceObject.latitude, sourceObject.longitude,
				sourceObject.data, sourceObject.plotData);
	}

	/**
	 * Reload Function
	 * 
	 * The reload function for the TravelTimeData class. Initializes members to
	 * provided values.
	 * 
	 * @param newType
	 *            - A String containing the request type, "Standard", "Plot", or
	 *            "PlotStatistics", defaults to standard
	 * @param newDistance
	 *            - A Double containing the source-receiver distance in degrees
	 * @param newElevation
	 *            - A Double containing the receiver elevation relative to the
	 *            WGS84 datum in kilometers
	 * @param newLatitude
	 *            - An optional Double containing the geographic receiver
	 *            latitude in degrees, null to omit
	 * @param newLongitude
	 *            - An optional Double containing the geographic receiver
	 *            longitude in degrees, null to omit
	 * @param newData
	 *            - A TravelTimeData containing the returned travel time data
	 * @param newPlotData
	 *            - A TravelTimePlotData containing the returned travel time
	 *            plot data
	 */
	public void reload(String newType, Double newDistance, Double newElevation,
			Double newLatitude, Double newLongitude, TravelTimeData newData,
			TravelTimePlotData newPlotData) {

		if (newType == null) {
			type = "Standard";
		} else {
			type = newType;
		}
		distance = newDistance;
		elevation = newElevation;
		latitude = newLatitude;
		longitude = newLongitude;
		data = newData;
		plotData = newPlotData;
	}

	/**
	 * Converts the contents of the class to a json object
	 * 
	 * @return Returns a JSONObject containing the class contents
	 */
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {

		JSONObject newJSONObject = new JSONObject();

		String jsonType = getType();
		Double jsonDistance = getDistance();
		Double jsonElevation = getElevation();
		Double jsonLatitude = getLatitude();
		Double jsonLongitude = getLongitude();
		TravelTimeData jsonData = getData();
		TravelTimePlotData jsonPlotData = getPlotData();

		// required values
		// type
		if (jsonType != null) {
			newJSONObject.put(TYPE_KEY, jsonType);
		}

		// distance
		if (jsonDistance != null) {
			newJSONObject.put(DISTANCE_KEY, jsonDistance);
		}

		// elevation
		if (jsonElevation != null) {
			newJSONObject.put(ELEVATION_KEY, jsonElevation);
		}

		// optional values
		// latitude
		if (jsonLatitude != null) {
			newJSONObject.put(LATITUDE_KEY, jsonLatitude);
		}

		// longitude
		if (jsonLongitude != null) {
			newJSONObject.put(LONGITUDE_KEY, jsonLongitude);
		}

		// returned data
		if ((jsonType != null) && (jsonType.equals("Standard"))) {

			// data
			if (jsonData != null) {
				newJSONObject.put(DATA_KEY, jsonData.toJSON());
			}

		} else if ((jsonType != null) && (jsonType.equals("Plot"))) {

			// plot data
			if (jsonPlotData != null) {
				newJSONObject.put(DATA_KEY, jsonPlotData.toJSON());
			}
		} else if ((jsonType != null) && (jsonType.equals("PlotStatistics"))) {

			// plot data
			if (jsonPlotData != null) {
				newJSONObject.put(DATA_KEY, jsonPlotData.toJSON());
			}
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

		String jsonType = getType();
		Double jsonDistance = getDistance();
		Double jsonElevation = getElevation();
		Double jsonLatitude = getLatitude();
		Double jsonLongitude = getLongitude();
		TravelTimeData jsonData = getData();
		TravelTimePlotData jsonPlotData = getPlotData();

		// required values
		// type
		if (jsonType == null) {
			// type not found
			errorList.add("No Type in TravelTimeRequest Class.");
		} else if (jsonType.isEmpty()) {
			// type empty
			errorList.add("Empty Type in TravelTimeRequest Class.");
		} else if (!(jsonType.equals("Standard") || jsonType.equals("Plot")
				|| jsonType.equals("PlotStatistics"))) {
			// wrong type
			errorList.add("Unsupported type in TravelTimeRequest Class.");
		}

		// distance
		if (jsonDistance == null) {
			// distance not found
			errorList.add("No Distance in TravelTimeRequest Class.");
		}

		// elevation
		if (jsonElevation == null) {
			// elevation not found
			errorList.add("No Elevation in TravelTimeRequest Class.");
		}

		// optional values
		// latitude
		if (jsonLatitude != null) {

			if ((jsonLatitude < -90) || (jsonLatitude > 90)) {
				// invalid latitude
				errorList.add(
						"Latitude in TravelTimeRequest not in the range of -90 to 90.");
			}
		}

		// longitude
		if (jsonLongitude != null) {

			if ((jsonLongitude < -180) || (jsonLongitude > 180)) {
				// invalid longitude
				errorList.add(
						"Longitude in TravelTimeRequest not in the range of -180 to 180.");
			}
		}

		// data
		// returned data
		if ((jsonType != null) && (jsonType.equals("Standard"))) {

			// data
			if (jsonData != null) {
				if (!jsonData.isValid()) {
					// data invalid
					errorList.add("Invalid TravelTimeData in TravelTimeRequest Class of type Standard.");
				}
				
				if (jsonPlotData != null) {
					// wrong data
					errorList.add("Erroneous TravelTimePlotData in TravelTimeRequest Class of type Standard.");
				}
			}

		} else if ((jsonType != null) && (jsonType.equals("Plot"))) {

			// plot data
			if (jsonPlotData != null) {
				if (!jsonPlotData.isValid()) {
					// data invalid
					errorList.add("Invalid TravelTimePlotData in TravelTimeRequest Class of type Plot.");
				}
				
				if (jsonData != null) {
					// wrong data
					errorList.add("Erroneous TravelTimeData in TravelTimeRequest Class of type Plot.");
				}
			}
		} else if ((jsonType != null) && (jsonType.equals("PlotStatistics"))) {

			// plot data
			if (!jsonPlotData.isValid()) {
				// data invalid
				errorList.add("Invalid TravelTimePlotData in TravelTimeRequest Class of type PlotStatistics.");
			}
			
			if (jsonData != null) {
				// wrong data
				errorList.add("Erroneous TravelTimeData in TravelTimeRequest Class of type PlotStatistics.");
			}
		}

		return (errorList);
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the elevation
	 */
	public Double getElevation() {
		return elevation;
	}

	/**
	 * @param elevation
	 *            the elevation to set
	 */
	public void setElevation(Double elevation) {
		this.elevation = elevation;
	}

	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the data
	 */
	public TravelTimeData getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(TravelTimeData data) {
		this.data = data;
	}

	/**
	 * @return the plotData
	 */
	public TravelTimePlotData getPlotData() {
		return plotData;
	}

	/**
	 * @param plotData
	 *            the plotData to set
	 */
	public void setPlotData(TravelTimePlotData plotData) {
		this.plotData = plotData;
	}
}
