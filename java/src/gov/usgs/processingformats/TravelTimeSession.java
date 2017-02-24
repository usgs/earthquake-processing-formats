package gov.usgs.processingformats;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import gov.usgs.processingformats.ProcessingInt;

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
	public static final String USERSTT_KEY = "UseRSTT";
	public static final String ISPLOT_KEY = "IsPlot";

	/**
	 * Required depth of the source relative to the WGS84 datum in kilometers
	 */
	private Double sourceDepth;

	/**
	 * Optional earth model to use, defaults to the AK135 earth model
	 */
	private String earthModel;

	/**
	 * Optional ArrayList of strings listing the phase types desired
	 */
	private ArrayList<String> phaseTypes;

	/**
	 * Optional geographic source latitude in degrees
	 */
	private Double sourceLatitude;

	/**
	 * Optional geographic source longitude in degrees
	 */
	private Double sourceLongitude;

	/**
	 * Optional flag that indicates whether to return all phases, defaults to
	 * false
	 */
	private Boolean returnAllPhases;

	/**
	 * Optional flag that indicates whether to return all arrivals of all
	 * phases, defaults to false
	 */
	private Boolean returnBackBranches;

	/**
	 * Optional flag that indicates whether to convert tectonic phases, defaults
	 * to false
	 */
	private Boolean convertTectonic;

	/**
	 * Optional flag that indicates whether theRSTT regional travel-time model
	 * is to be used
	 */
	private Boolean useRSTT;

	/**
	 * Optional flag that indicates whether the travel-time session is for
	 * plotting rather than lookups
	 */
	private Boolean isPlot;

	/**
	 * The constructor for the TravelTimeSession class. Initializes members to
	 * null values.
	 */
	public TravelTimeSession() {

		reload(null, null, null, null, null, null, null, null, null, null);
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
			Boolean newUseRSTT, Boolean newIsPlot) {

		reload(newSourceDepth, newEarthModel, newPhaseTypes, newSourceLatitude,
				newSourceLongitude, newReturnAllPhases, newReturnBackBranches,
				newConvertTectonic, newUseRSTT, newIsPlot);
	}

	/**
	 * Constructs the class from a JSONObject, populating members
	 * 
	 * @param newJSONObject
	 *            - A JSONObject.
	 */
	public TravelTimeSession(JSONObject newJSONObject) {

		// Required values
		// sourceDepth
		if (newJSONObject.containsKey(SOURCEDEPTH_KEY)) {
			sourceDepth = (double) newJSONObject.get(SOURCEDEPTH_KEY);
		} else {
			sourceDepth = null;
		}

		// Optional values
		// earthModel
		if (newJSONObject.containsKey(EARTHMODEL_KEY)) {
			earthModel = newJSONObject.get(EARTHMODEL_KEY).toString();
		} else {
			earthModel = null;
		}

		// phaseTypes
		if (newJSONObject.containsKey(PHASETYPES_KEY)) {

			phaseTypes = new ArrayList<String>();

			// get the array
			JSONArray phaseTypesArray = (JSONArray) newJSONObject
					.get(PHASETYPES_KEY);

			if ((phaseTypesArray != null) && (!phaseTypesArray.isEmpty())) {

				// go through the whole array
				for (int i = 0; i < phaseTypesArray.size(); i++) {

					// get the String
					String phaseType = phaseTypesArray.get(i).toString();

					// add to ArrayList
					phaseTypes.add(phaseType);

				}
			}
		} else {
			phaseTypes = null;
		}

		// sourceLatitude
		if (newJSONObject.containsKey(SOURCELATITUDE_KEY)) {
			sourceLatitude = (double) newJSONObject.get(SOURCELATITUDE_KEY);
		} else {
			sourceLatitude = null;
		}

		// sourceLongitude
		if (newJSONObject.containsKey(SOURCELONGITUDE_KEY)) {
			sourceLongitude = (double) newJSONObject.get(SOURCELONGITUDE_KEY);
		} else {
			sourceLongitude = null;
		}

		// returnAllPhases
		if (newJSONObject.containsKey(RETURNALLPHASES_KEY)) {
			returnAllPhases = (boolean) newJSONObject.get(RETURNALLPHASES_KEY);
		} else {
			returnAllPhases = null;
		}

		// returnBackBranches
		if (newJSONObject.containsKey(RETURNBACKBRANCHES_KEY)) {
			returnBackBranches = (boolean) newJSONObject
					.get(RETURNBACKBRANCHES_KEY);
		} else {
			returnBackBranches = null;
		}

		// convertTectonic
		if (newJSONObject.containsKey(CONVERTTECTONIC_KEY)) {
			convertTectonic = (boolean) newJSONObject.get(CONVERTTECTONIC_KEY);
		} else {
			convertTectonic = null;
		}

		// useRSTT
		if (newJSONObject.containsKey(USERSTT_KEY)) {
			useRSTT = (boolean) newJSONObject.get(USERSTT_KEY);
		} else {
			useRSTT = null;
		}

		// isPlot
		if (newJSONObject.containsKey(ISPLOT_KEY)) {
			isPlot = (boolean) newJSONObject.get(ISPLOT_KEY);
		} else {
			isPlot = null;
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
		reload(sourceObject.sourceDepth, sourceObject.earthModel,
				sourceObject.phaseTypes, sourceObject.sourceLatitude,
				sourceObject.sourceLongitude, sourceObject.returnAllPhases,
				sourceObject.returnBackBranches, sourceObject.convertTectonic,
				sourceObject.useRSTT, sourceObject.isPlot);
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
	 * @param newUseRSTT
	 *            - A Long containing the auxiliary phase group identifier
	 * @param newIsPlot
	 *            - A Boolean containing the flag indicating whether the phase
	 *            may be used in a location
	 */
	public void reload(Double newSourceDepth, String newEarthModel,
			ArrayList<String> newPhaseTypes, Double newSourceLatitude,
			Double newSourceLongitude, Boolean newReturnAllPhases,
			Boolean newReturnBackBranches, Boolean newConvertTectonic,
			Boolean newUseRSTT, Boolean newIsPlot) {

		sourceDepth = newSourceDepth;
		earthModel = newEarthModel;
		phaseTypes = newPhaseTypes;
		sourceLatitude = newSourceLatitude;
		sourceLongitude = newSourceLongitude;
		returnAllPhases = newReturnAllPhases;
		returnBackBranches = newReturnBackBranches;
		convertTectonic = newConvertTectonic;
		useRSTT = newUseRSTT;
		isPlot = newIsPlot;

	}

	/**
	 * Converts the contents of the class to a json object
	 * 
	 * @return Returns a JSONObject containing the class contents
	 */
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {

		JSONObject newJSONObject = new JSONObject();

		Double jsonSourceDepth = getSourceDepth();
		String jsonEarthModel = getEarthModel();
		ArrayList<String> jsonPhaseTypes = getPhaseTypes();
		Double jsonSourceLatitude = getSourceLatitude();
		Double jsonSourceLongitude = getSourceLongitude();
		Boolean jsonReturnAllPhases = getReturnAllPhases();
		Boolean jsonReturnBackBranches = getReturnBackBranches();
		Boolean jsonConvertTectonic = getConvertTectonic();
		Boolean jsonUseRSTT = getUseRSTT();
		Boolean jsonIsPlot = getIsPlot();

		// sourceDepth
		if (jsonSourceDepth != null) {
			newJSONObject.put(SOURCEDEPTH_KEY, jsonSourceDepth);
		}

		// earthModel
		if (jsonEarthModel != null) {
			newJSONObject.put(EARTHMODEL_KEY, jsonEarthModel);
		}

		// phaseTypes
		if ((jsonPhaseTypes != null) && (!jsonPhaseTypes.isEmpty())) {

			JSONArray phaseTypesArray = new JSONArray();

			// enumerate through the whole arraylist
			for (Iterator<String> phaseTypesIterator = jsonPhaseTypes
					.iterator(); phaseTypesIterator.hasNext();) {

				// add to json array
				phaseTypesArray.add(phaseTypesIterator.next());
			}

			if (!phaseTypesArray.isEmpty()) {
				newJSONObject.put(PHASETYPES_KEY, phaseTypesArray);
			}
		}

		// sourceLatitude
		if (jsonSourceLatitude != null) {
			newJSONObject.put(SOURCELATITUDE_KEY, jsonSourceLatitude);
		}

		// sourceLongitude
		if (jsonSourceLongitude != null) {
			newJSONObject.put(SOURCELONGITUDE_KEY, jsonSourceLongitude);
		}

		// returnAllPhases
		if (jsonReturnAllPhases != null) {
			newJSONObject.put(RETURNALLPHASES_KEY, jsonReturnAllPhases);
		}

		// returnBackBranches
		if (jsonReturnBackBranches != null) {
			newJSONObject.put(RETURNBACKBRANCHES_KEY, jsonReturnBackBranches);
		}

		// convertTectonic
		if (jsonConvertTectonic != null) {
			newJSONObject.put(CONVERTTECTONIC_KEY, jsonConvertTectonic);
		}

		// useRSTT
		if (jsonUseRSTT != null) {
			newJSONObject.put(USERSTT_KEY, jsonUseRSTT);
		}

		// isPlot
		if (jsonIsPlot != null) {
			newJSONObject.put(ISPLOT_KEY, jsonIsPlot);
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

		Double jsonSourceDepth = getSourceDepth();
//		String jsonEarthModel = getEarthModel();
//		ArrayList<String> jsonPhaseTypes = getPhaseTypes();
		Double jsonSourceLatitude = getSourceLatitude();
		Double jsonSourceLongitude = getSourceLongitude();
//		Boolean jsonReturnAllPhases = getReturnAllPhases();
//		Boolean jsonReturnBackBranches = getReturnBackBranches();
//		Boolean jsonConvertTectonic = getConvertTectonic();
//		Boolean jsonUseRSTT = getUseRSTT();
//		Boolean jsonIsPlot = getIsPlot();

		// sourceDepth
		if (jsonSourceDepth == null) {
			// sourceDepth not found
			errorList.add("No Source Depth in TravelTimeSession Class.");
		} else if ((jsonSourceDepth < -100) || (jsonSourceDepth > 1500)) {
			// invalid depth
			errorList.add(
					"Source Depth in TravelTimeSession Class not in the range of -100 to 1500.");
		}

		// sourceLatitude
		if (jsonSourceLatitude != null) {
			if ((jsonSourceLatitude < -90) || (jsonSourceLatitude > 90)) {
				// invalid latitude
				errorList.add(
						"Source Latitude in TravelTimeSession Class not in the range of -90 to 90.");
			}
		}

		// sourceLongitude
		if (jsonSourceLongitude != null) {
			if ((jsonSourceLongitude < -180) || (jsonSourceLongitude > 180)) {
				// invalid longitude
				errorList.add(
						"Source Longitude in TravelTimeSession Class not in the range of -180 to 180.");
			}
		}

		// earthModel and phaseTypes are strings, validation could be done on
		// them in the future
		// no validation necessary for boolean values
		
		return (errorList);
	}

	/**
	 * @return the sourceDepth
	 */
	public Double getSourceDepth() {
		return sourceDepth;
	}

	/**
	 * @param sourceDepth
	 *            the sourceDepth to set
	 */
	public void setSourceDepth(Double sourceDepth) {
		this.sourceDepth = sourceDepth;
	}

	/**
	 * @return the earthModel
	 */
	public String getEarthModel() {
		return earthModel;
	}

	/**
	 * @param earthModel
	 *            the earthModel to set
	 */
	public void setEarthModel(String earthModel) {
		this.earthModel = earthModel;
	}

	/**
	 * @return the phaseTypes
	 */
	public ArrayList<String> getPhaseTypes() {
		return phaseTypes;
	}

	/**
	 * @param phaseTypes
	 *            the phaseTypes to set
	 */
	public void setPhaseTypes(ArrayList<String> phaseTypes) {
		this.phaseTypes = phaseTypes;
	}

	/**
	 * @return the sourceLatitude
	 */
	public Double getSourceLatitude() {
		return sourceLatitude;
	}

	/**
	 * @param sourceLatitude
	 *            the sourceLatitude to set
	 */
	public void setSourceLatitude(Double sourceLatitude) {
		this.sourceLatitude = sourceLatitude;
	}

	/**
	 * @return the sourceLongitude
	 */
	public Double getSourceLongitude() {
		return sourceLongitude;
	}

	/**
	 * @param sourceLongitude
	 *            the sourceLongitude to set
	 */
	public void setSourceLongitude(Double sourceLongitude) {
		this.sourceLongitude = sourceLongitude;
	}

	/**
	 * @return the returnAllPhases
	 */
	public Boolean getReturnAllPhases() {
		return returnAllPhases;
	}

	/**
	 * @param returnAllPhases
	 *            the returnAllPhases to set
	 */
	public void setReturnAllPhases(Boolean returnAllPhases) {
		this.returnAllPhases = returnAllPhases;
	}

	/**
	 * @return the returnBackBranches
	 */
	public Boolean getReturnBackBranches() {
		return returnBackBranches;
	}

	/**
	 * @param returnBackBranches
	 *            the returnBackBranches to set
	 */
	public void setReturnBackBranches(Boolean returnBackBranches) {
		this.returnBackBranches = returnBackBranches;
	}

	/**
	 * @return the convertTectonic
	 */
	public Boolean getConvertTectonic() {
		return convertTectonic;
	}

	/**
	 * @param convertTectonic
	 *            the convertTectonic to set
	 */
	public void setConvertTectonic(Boolean convertTectonic) {
		this.convertTectonic = convertTectonic;
	}

	/**
	 * @return the useRSTT
	 */
	public Boolean getUseRSTT() {
		return useRSTT;
	}

	/**
	 * @param useRSTT
	 *            the useRSTT to set
	 */
	public void setUseRSTT(Boolean useRSTT) {
		this.useRSTT = useRSTT;
	}

	/**
	 * @return the isPlot
	 */
	public Boolean getIsPlot() {
		return isPlot;
	}

	/**
	 * @param isPlot
	 *            the isPlot to set
	 */
	public void setIsPlot(Boolean isPlot) {
		this.isPlot = isPlot;
	}

}
