package gov.usgs.processingformats;

import java.util.*;

import org.json.simple.JSONArray;
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
	 * Required Type of Data as a String
	 */
	public String Type;

	/**
	 * Required source receiver Distance in seconds
	 */
	public Double Distance;

	/**
	 * Required receiver Elevation relative to the WGS84 datum in kilometers
	 */
	public Double Elevation;

	/**
	 * Optional geographic receiver Latitude in degrees
	 */
	public Double Latitude;

	/**
	 * Optional geographic receiver Longitude in degrees
	 */
	public Double Longitude;

	/**
	 * Returned travel time Data (empty for requests)
	 */
	public ArrayList<TravelTimeData> Data;

	/**
	 * Returned travel time plot Data (empty for requests)
	 */
	public ArrayList<TravelTimePlotData> PlotData;

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
	 *            - A String containing the request Type, "Standard", "Plot", or
	 *            "PlotStatistics", defaults to standard
	 * @param newDistance
	 *            - A Double containing the source-receiver Distance in degrees
	 * @param newElevation
	 *            - A Double containing the receiver Elevation relative to the
	 *            WGS84 datum in kilometers
	 * @param newLatitude
	 *            - An optional Double containing the geographic receiver
	 *            Latitude in degrees, null to omit
	 * @param newLongitude
	 *            - An optional Double containing the geographic receiver
	 *            Longitude in degrees, null to omit
	 * @param newData
	 *            - A ArrayList&lt;TravelTimeData&gt; containing the returned
	 *            travel time Data
	 * @param newPlotData
	 *            - A ArrayList&lt;TravelTimePlotData&gt;  containing the
	 *            returned travel time plot Data
	 */
	public TravelTimeRequest(String newType, Double newDistance,
			Double newElevation, Double newLatitude, Double newLongitude,
			ArrayList<TravelTimeData> newData,
			ArrayList<TravelTimePlotData> newPlotData) {

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
		// Type
		if (newJSONObject.containsKey(TYPE_KEY)) {
			Type = newJSONObject.get(TYPE_KEY).toString();
		} else {
			Type = null;
		}

		// Distance
		if (newJSONObject.containsKey(DISTANCE_KEY)) {
			Distance = (double) newJSONObject.get(DISTANCE_KEY);
		} else {
			Distance = null;
		}

		// Elevation
		if (newJSONObject.containsKey(ELEVATION_KEY)) {
			Elevation = (double) newJSONObject.get(ELEVATION_KEY);
		} else {
			Elevation = null;
		}

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

		// Data
		if (newJSONObject.containsKey(DATA_KEY)) {

			Data = new ArrayList<TravelTimeData>();
			PlotData = new ArrayList<TravelTimePlotData>();

			// get the array
			JSONArray DataArray = (JSONArray) newJSONObject.get(DATA_KEY);

			if ((DataArray != null) && (!DataArray.isEmpty())) {

				// go through the whole array
				for (int i = 0; i < DataArray.size(); i++) {

					// get the object
					JSONObject DataObject = (JSONObject) DataArray.get(i);

					// check for Type
					if (DataObject.containsKey(TYPE_KEY)) {

						// Route based on Type
						String TypeString = (String) DataObject.get(TYPE_KEY);
						if (TypeString.equals("TTData")) {

							// add to vector
							Data.add(new TravelTimeData(DataObject));
						} else if (TypeString.equals("TTPlotData")) {

							// add to vector
							PlotData.add(new TravelTimePlotData(DataObject));
						}
					}
				}
			}
		} else {
			Data = null;
			PlotData = null;
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
		reload(sourceObject.Type, sourceObject.Distance, sourceObject.Elevation,
				sourceObject.Latitude, sourceObject.Longitude,
				sourceObject.Data, sourceObject.PlotData);
	}

	/**
	 * Reload Function
	 * 
	 * The reload function for the TravelTimeData class. Initializes members to
	 * provided values.
	 * 
	 * @param newType
	 *            - A String containing the request Type, "Standard", "Plot", or
	 *            "PlotStatistics", defaults to standard
	 * @param newDistance
	 *            - A Double containing the source-receiver Distance in degrees
	 * @param newElevation
	 *            - A Double containing the receiver Elevation relative to the
	 *            WGS84 datum in kilometers
	 * @param newLatitude
	 *            - An optional Double containing the geographic receiver
	 *            Latitude in degrees, null to omit
	 * @param newLongitude
	 *            - An optional Double containing the geographic receiver
	 *            Longitude in degrees, null to omit
	 * @param newData
	 *            - A ArrayList&lt;TravelTimeData&gt; containing the returned
	 *            travel time Data
	 * @param newPlotData
	 *            - A ArrayList&lt;TravelTimePlotData&gt;  containing the
	 *            returned travel time plot Data
	 */
	public void reload(String newType, Double newDistance, Double newElevation,
			Double newLatitude, Double newLongitude,
			ArrayList<TravelTimeData> newData,
			ArrayList<TravelTimePlotData> newPlotData) {

		if (newType == null) {
			Type = "Standard";
		} else {
			Type = newType;
		}
		Distance = newDistance;
		Elevation = newElevation;
		Latitude = newLatitude;
		Longitude = newLongitude;
		Data = newData;
		PlotData = newPlotData;
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
		// Type
		if (Type != null) {
			newJSONObject.put(TYPE_KEY, Type);
		}

		// Distance
		if (Distance != null) {
			newJSONObject.put(DISTANCE_KEY, Distance);
		}

		// Elevation
		if (Elevation != null) {
			newJSONObject.put(ELEVATION_KEY, Elevation);
		}

		// optional values
		// Latitude
		if (Latitude != null) {
			newJSONObject.put(LATITUDE_KEY, Latitude);
		}

		// Longitude
		if (Longitude != null) {
			newJSONObject.put(LONGITUDE_KEY, Longitude);
		}

		// returned Data
		JSONArray DataArray = new JSONArray();
		if ((Type != null) && (Type.equals("Standard"))) {

			// enumerate through the whole arraylist
			for (Iterator<TravelTimeData> DataIterator = Data
					.iterator(); DataIterator.hasNext();) {

				// convert pick to JSON object
				JSONObject DataObject = ((TravelTimeData) DataIterator.next())
						.toJSON();

				DataArray.add(DataObject);
			}
		} else if ((Type != null) && (Type.equals("Plot"))) {

			// enumerate through the whole arraylist
			for (Iterator<TravelTimePlotData> DataIterator = PlotData
					.iterator(); DataIterator.hasNext();) {

				// convert pick to JSON object
				JSONObject DataObject = ((TravelTimePlotData) DataIterator
						.next()).toJSON();

				DataArray.add(DataObject);
			}

		} else if ((Type != null) && (Type.equals("PlotStatistics"))) {

			// enumerate through the whole arraylist
			for (Iterator<TravelTimePlotData> DataIterator = PlotData
					.iterator(); DataIterator.hasNext();) {

				// convert pick to JSON object
				JSONObject DataObject = ((TravelTimePlotData) DataIterator
						.next()).toJSON();

				DataArray.add(DataObject);
			}
		}

		if (!DataArray.isEmpty()) {
			newJSONObject.put(DATA_KEY, DataArray);
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
		// Type
		if (Type == null) {
			// Type not found
			errorList.add("No Type in TravelTimeRequest Class.");
		} else if (Type.isEmpty()) {
			// Type empty
			errorList.add("Empty Type in TravelTimeRequest Class.");
		} else if (!(Type.equals("Standard") || Type.equals("Plot")
				|| Type.equals("PlotStatistics"))) {
			// wrong Type
			errorList.add("Unsupported Type in TravelTimeRequest Class.");
		}

		// Distance
		if (Distance == null) {
			// Distance not found
			errorList.add("No Distance in TravelTimeRequest Class.");
		}

		// Elevation
		if (Elevation == null) {
			// Elevation not found
			errorList.add("No Elevation in TravelTimeRequest Class.");
		}

		// optional values
		// Latitude
		if (Latitude != null) {

			if ((Latitude < -90) || (Latitude > 90)) {
				// invalid Latitude
				errorList.add(
						"Latitude in TravelTimeRequest not in the range of -90 to 90.");
			}
		}

		// Longitude
		if (Longitude != null) {

			if ((Longitude < -180) || (Longitude > 180)) {
				// invalid Longitude
				errorList.add(
						"Longitude in TravelTimeRequest not in the range of -180 to 180.");
			}
		}

		// Data
		// returned Data
		if ((Type != null) && (Type.equals("Standard"))) {

			// Data
			if (Data != null) {
				// enumerate through the whole arraylist
				for (Iterator<TravelTimeData> DataIterator = Data
						.iterator(); DataIterator.hasNext();) {

					// convert pick to JSON object
					TravelTimeData TTData = ((TravelTimeData) DataIterator
							.next());

					if (!TTData.isValid()) {
						errorList.add(
								"Invalid TravelTimeData in TravelTimeRequest Class of Type Standard.");
						break;
					}
				}
				if ((PlotData != null) && (!PlotData.isEmpty())) {
					// wrong Data
					errorList.add(
							"Erroneous TravelTimePlotData in TravelTimeRequest Class of Type Standard.");
				}
			}

		} else if ((Type != null) && (Type.equals("Plot"))) {

			// plot Data
			if (PlotData != null) {
				// enumerate through the whole arraylist
				for (Iterator<TravelTimePlotData> DataIterator = PlotData
						.iterator(); DataIterator.hasNext();) {

					// convert pick to JSON object
					TravelTimePlotData TTPlotData = ((TravelTimePlotData) DataIterator
							.next());

					if (!TTPlotData.isValid()) {
						errorList.add(
								"Invalid TravelTimePlotData in TravelTimeRequest Class of Type Plot.");
						break;
					}
				}

				if ((Data != null) && (!Data.isEmpty())) {
					// wrong Data
					errorList.add(
							"Erroneous TravelTimeData in TravelTimeRequest Class of Type Plot.");
				}
			}
		} else if ((Type != null) && (Type.equals("PlotStatistics"))) {

			// enumerate through the whole arraylist
			for (Iterator<TravelTimePlotData> DataIterator = PlotData
					.iterator(); DataIterator.hasNext();) {

				// convert pick to JSON object
				TravelTimePlotData TTPlotData = ((TravelTimePlotData) DataIterator
						.next());

				if (!TTPlotData.isValid()) {
					errorList.add(
							"Invalid TravelTimePlotData in TravelTimeRequest Class of Type PlotStatistics.");
					break;
				}
			}

			if ((Data != null) && (!Data.isEmpty())) {
				// wrong Data
				errorList.add(
						"Erroneous TravelTimeData in TravelTimeRequest Class of Type PlotStatistics.");
			}
		}

		return (errorList);
	}
}
