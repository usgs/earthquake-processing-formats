package gov.usgs.processingformats;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import gov.usgs.processingformats.ProcessingInt;

/**
 * a conversion class used to create, parse, and validate travel time session
 * 
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class TravelTimeSession implements ProcessingInt {

	/**
	 * JSON Keys
	 */
	public static final String SOURCEDEPTH_KEY = "SourceDepth";
	public static final String EARTHMODEL_KEY = "EarthModel";
	public static final String PHASETYPES_KEY = "PhaseTypes";
	public static final String SOURCELATITUDE_KEY = "SourceLatitude";
	public static final String SOURCELONGITUDE_KEY = "SourceLongitude";
	public static final String RETURNALLPHASES_KEY = "ReturnAllPhases";
	public static final String RETURNBACKBRANCHES_KEY = "ReturnBackBranches";
	public static final String CONVERTTECTONIC_KEY = "ConvertTectonic";
	public static final String ISPLOT_KEY = "IsPlot";

	/**
	 * Required depth of the source relative to the WGS84 datum in kilometers
	 */
	public Double SourceDepth;

	/**
	 * Optional earth model to use, defaults to the AK135 earth model
	 */
	public String EarthModel;

	/**
	 * Optional ArrayList of strings listing the phase types desired
	 */
	public ArrayList<String> PhaseTypes;

	/**
	 * Optional geographic source latitude in degrees
	 */
	public Double SourceLatitude;

	/**
	 * Optional geographic source longitude in degrees
	 */
	public Double SourceLongitude;

	/**
	 * Optional flag that indicates whether to return all phases, defaults to
	 * false
	 */
	public Boolean ReturnAllPhases;

	/**
	 * Optional flag that indicates whether to return all arrivals of all
	 * phases, defaults to false
	 */
	public Boolean ReturnBackBranches;

	/**
	 * Optional flag that indicates whether to convert tectonic phases, defaults
	 * to false
	 */
	public Boolean ConvertTectonic;

	/**
	 * Optional flag that indicates whether the travel-time session is for
	 * plotting rather than lookups
	 */
	public Boolean IsPlot;

	/**
	 * The constructor for the TravelTimeSession class. Initializes members to
	 * null values.
	 */
	public TravelTimeSession() {

		reload(null, null, null, null, null, null, null, null, null);
	}

	/**
	 * Advanced constructor
	 * 
	 * The advanced constructor for the TravelTimeSession class. Initializes
	 * members to provided values.
	 * 
	 * @param newSourceDepth
	 *            - A String containing the seismic phase code
	 * @param newEarthModel
	 *            - A Double containing the travel time in seconds
	 * @param newPhaseTypes
	 *            - A Double containing the derivative with respect to distance
	 *            of the travel time in seconds/degree
	 * @param newSourceLatitude
	 *            - A Double containing the derivative with respect to ray
	 *            parameter of the travel time in degrees/second
	 * @param newSourceLongitude
	 *            - A Double containing the derivative with respect to ray
	 *            parameter of the travel time in degrees/second
	 * @param newReturnAllPhases
	 *            - A Double containing the observed travel time scatter in
	 *            seconds
	 * @param newReturnBackBranches
	 *            - A Double containing the statistical observability of the
	 *            seismic phase
	 * @param newConvertTectonic
	 *            - A Long containing the teleseismic phase group identifier
	 * @param newUseRSTT
	 *            - A Long containing the auxiliary phase group identifier
	 * @param newIsPlot
	 *            - A Boolean containing the flag indicating whether the phase
	 *            may be used in a location
	 */
	public TravelTimeSession(Double newSourceDepth, String newEarthModel,
			ArrayList<String> newPhaseTypes, Double newSourceLatitude,
			Double newSourceLongitude, Boolean newReturnAllPhases,
			Boolean newReturnBackBranches, Boolean newConvertTectonic,
			Boolean newIsPlot) {

		reload(newSourceDepth, newEarthModel, newPhaseTypes, newSourceLatitude,
				newSourceLongitude, newReturnAllPhases, newReturnBackBranches,
				newConvertTectonic, newIsPlot);
	}

	/**
	 * Constructs the class from a JSONObject, populating members
	 * 
	 * @param newJSONObject
	 *            - A JSONObject.
	 */
	public TravelTimeSession(JSONObject newJSONObject) {

		// Required values
		// SourceDepth
		if (newJSONObject.containsKey(SOURCEDEPTH_KEY)) {
			SourceDepth = (double) newJSONObject.get(SOURCEDEPTH_KEY);
		} else {
			SourceDepth = null;
		}

		// Optional values
		// EarthModel
		if (newJSONObject.containsKey(EARTHMODEL_KEY)) {
			EarthModel = newJSONObject.get(EARTHMODEL_KEY).toString();
		} else {
			EarthModel = null;
		}

		// PhaseTypes
		if (newJSONObject.containsKey(PHASETYPES_KEY)) {

			PhaseTypes = new ArrayList<String>();

			// get the array
			JSONArray PhaseTypesArray = (JSONArray) newJSONObject
					.get(PHASETYPES_KEY);

			if ((PhaseTypesArray != null) && (!PhaseTypesArray.isEmpty())) {

				// go through the whole array
				for (int i = 0; i < PhaseTypesArray.size(); i++) {

					// get the String
					String phaseType = PhaseTypesArray.get(i).toString();

					// add to ArrayList
					PhaseTypes.add(phaseType);

				}
			}
		} else {
			PhaseTypes = null;
		}

		// SourceLatitude
		if (newJSONObject.containsKey(SOURCELATITUDE_KEY)) {
			SourceLatitude = (double) newJSONObject.get(SOURCELATITUDE_KEY);
		} else {
			SourceLatitude = null;
		}

		// SourceLongitude
		if (newJSONObject.containsKey(SOURCELONGITUDE_KEY)) {
			SourceLongitude = (double) newJSONObject.get(SOURCELONGITUDE_KEY);
		} else {
			SourceLongitude = null;
		}

		// ReturnAllPhases
		if (newJSONObject.containsKey(RETURNALLPHASES_KEY)) {
			ReturnAllPhases = (boolean) newJSONObject.get(RETURNALLPHASES_KEY);
		} else {
			ReturnAllPhases = null;
		}

		// ReturnBackBranches
		if (newJSONObject.containsKey(RETURNBACKBRANCHES_KEY)) {
			ReturnBackBranches = (boolean) newJSONObject
					.get(RETURNBACKBRANCHES_KEY);
		} else {
			ReturnBackBranches = null;
		}

		// ConvertTectonic
		if (newJSONObject.containsKey(CONVERTTECTONIC_KEY)) {
			ConvertTectonic = (boolean) newJSONObject.get(CONVERTTECTONIC_KEY);
		} else {
			ConvertTectonic = null;
		}

		// IsPlot
		if (newJSONObject.containsKey(ISPLOT_KEY)) {
			IsPlot = (boolean) newJSONObject.get(ISPLOT_KEY);
		} else {
			IsPlot = null;
		}

	}

	/**
	 * Constructs the class from a TravelTimeSession object, populating members
	 * (copy constructor)
	 * 
	 * @param sourceObject
	 *            - A TravelTimeSession object.
	 */
	public TravelTimeSession(TravelTimeSession sourceObject) {
		reload(sourceObject.SourceDepth, sourceObject.EarthModel,
				sourceObject.PhaseTypes, sourceObject.SourceLatitude,
				sourceObject.SourceLongitude, sourceObject.ReturnAllPhases,
				sourceObject.ReturnBackBranches, sourceObject.ConvertTectonic,
				sourceObject.IsPlot);
	}

	/**
	 * Reload Function
	 * 
	 * The reload function for the TravelTimeData class. Initializes members to
	 * provided values.
	 * 
	 * @param newSourceDepth
	 *            - A String containing the seismic phase code
	 * @param newEarthModel
	 *            - A Double containing the travel time in seconds
	 * @param newPhaseTypes
	 *            - A Double containing the derivative with respect to distance
	 *            of the travel time in seconds/degree
	 * @param newSourceLatitude
	 *            - A Double containing the derivative with respect to ray
	 *            parameter of the travel time in degrees/second
	 * @param newSourceLongitude
	 *            - A Double containing the derivative with respect to ray
	 *            parameter of the travel time in degrees/second
	 * @param newReturnAllPhases
	 *            - A Double containing the observed travel time scatter in
	 *            seconds
	 * @param newReturnBackBranches
	 *            - A Double containing the statistical observability of the
	 *            seismic phase
	 * @param newConvertTectonic
	 *            - A Long containing the teleseismic phase group identifier
	 * @param newIsPlot
	 *            - A Boolean containing the flag indicating whether the phase
	 *            may be used in a location
	 */
	public void reload(Double newSourceDepth, String newEarthModel,
			ArrayList<String> newPhaseTypes, Double newSourceLatitude,
			Double newSourceLongitude, Boolean newReturnAllPhases,
			Boolean newReturnBackBranches, Boolean newConvertTectonic,
			Boolean newIsPlot) {

		SourceDepth = newSourceDepth;
		EarthModel = newEarthModel;
		PhaseTypes = newPhaseTypes;
		SourceLatitude = newSourceLatitude;
		SourceLongitude = newSourceLongitude;
		ReturnAllPhases = newReturnAllPhases;
		ReturnBackBranches = newReturnBackBranches;
		ConvertTectonic = newConvertTectonic;
		IsPlot = newIsPlot;

	}

	/**
	 * Converts the contents of the class to a json object
	 * 
	 * @return Returns a JSONObject containing the class contents
	 */
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {

		JSONObject newJSONObject = new JSONObject();

		// SourceDepth
		if (SourceDepth != null) {
			newJSONObject.put(SOURCEDEPTH_KEY, SourceDepth);
		}

		// EarthModel
		if (EarthModel != null) {
			newJSONObject.put(EARTHMODEL_KEY, EarthModel);
		}

		// PhaseTypes
		if ((PhaseTypes != null) && (!PhaseTypes.isEmpty())) {

			JSONArray PhaseTypesArray = new JSONArray();

			// enumerate through the whole arraylist
			for (Iterator<String> PhaseTypesIterator = PhaseTypes
					.iterator(); PhaseTypesIterator.hasNext();) {

				// add to  array
				PhaseTypesArray.add(PhaseTypesIterator.next());
			}

			if (!PhaseTypesArray.isEmpty()) {
				newJSONObject.put(PHASETYPES_KEY, PhaseTypesArray);
			}
		}

		// SourceLatitude
		if (SourceLatitude != null) {
			newJSONObject.put(SOURCELATITUDE_KEY, SourceLatitude);
		}

		// SourceLongitude
		if (SourceLongitude != null) {
			newJSONObject.put(SOURCELONGITUDE_KEY, SourceLongitude);
		}

		// ReturnAllPhases
		if (ReturnAllPhases != null) {
			newJSONObject.put(RETURNALLPHASES_KEY, ReturnAllPhases);
		}

		// ReturnBackBranches
		if (ReturnBackBranches != null) {
			newJSONObject.put(RETURNBACKBRANCHES_KEY, ReturnBackBranches);
		}

		// ConvertTectonic
		if (ConvertTectonic != null) {
			newJSONObject.put(CONVERTTECTONIC_KEY, ConvertTectonic);
		}

		// IsPlot
		if (IsPlot != null) {
			newJSONObject.put(ISPLOT_KEY, IsPlot);
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

		// SourceDepth
		if (SourceDepth == null) {
			// SourceDepth not found
			errorList.add("No Source Depth in TravelTimeSession Class.");
		} else if ((SourceDepth < -100) || (SourceDepth > 1500)) {
			// invalid depth
			errorList.add(
					"Source Depth in TravelTimeSession Class not in the range of -100 to 1500.");
		}

		// SourceLatitude
		if (SourceLatitude != null) {
			if ((SourceLatitude < -90) || (SourceLatitude > 90)) {
				// invalid latitude
				errorList.add(
						"Source Latitude in TravelTimeSession Class not in the range of -90 to 90.");
			}
		}

		// SourceLongitude
		if (SourceLongitude != null) {
			if ((SourceLongitude < -180) || (SourceLongitude > 180)) {
				// invalid longitude
				errorList.add(
						"Source Longitude in TravelTimeSession Class not in the range of -180 to 180.");
			}
		}

		// EarthModel and PhaseTypes are strings, validation could be done on
		// them in the future
		// no validation necessary for boolean values
		
		return (errorList);
	}
}
