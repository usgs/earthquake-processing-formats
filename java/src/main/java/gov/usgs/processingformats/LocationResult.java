package gov.usgs.processingformats;

import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

/**
 * a conversion class used to create, parse, and validate location processing
 * data
 *
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class LocationResult implements ProcessingInt {

	/**
	 * JSON Keys
	 */
	public static final String ID_KEY = "ID";
	public static final String HYPOCENTER_KEY = "Hypocenter";
	public static final String SUPPORTINGDATA_KEY = "SupportingData";
	public static final String ASSOCIATEDSTATIONS_KEY = "NumberOfAssociatedStations";
	public static final String ASSOCIATEDPHASES_KEY = "NumberOfAssociatedPhases";
	public static final String USEDSTATIONS_KEY = "NumberOfUsedStations";
	public static final String USEDPHASES_KEY = "NumberOfUsedPhases";
	public static final String GAP_KEY = "Gap";
	public static final String SECONDARYGAP_KEY = "SecondaryGap";
	public static final String MINIMUMDISTANCE_KEY = "MinimumDistance";
	public static final String RMS_KEY = "RMS";
	public static final String QUALITY_KEY = "Quality";
	public static final String BAYESIANDEPTH_KEY = "BayesianDepth";
	public static final String BAYESIANRANGE_KEY = "BayesianRange";
  public static final String DEPTHIMPORTANCE_KEY = "DepthImportance";
  public static final String LOCATOREXITCODE_KEY = "LocatorExitCode";
	public static final String ERRORELLIPSE_KEY = "ErrorEllipse";

	/**
	 * Optional string containing the id.
	 */
	private String id;

	/**
	 * Required hypocenter
	 */
	private Hypocenter hypocenter;

	/**
	 * A required vector of Pick objects used to generate this location
	 */
	private ArrayList<Pick> supportingData;

	/**
	 * Optional integer containing the number of associated stations
	 */
	private Integer numberOfAssociatedStations;

	/**
	 * Optional integer containing the number of associated phases
	 */
	private Integer numberOfAssociatedPhases;

	/**
	 * Optional integer containing the number of used stations
	 */
	private Integer numberOfUsedStations;

	/**
	 * Optional integer containing the number of used phases
	 */
	private Integer numberOfUsedPhases;

	/**
	 * Optional Double containing the gap
	 */
	private Double gap;

	/**
	 * Optional Double containing the secondary gap
	 */
	private Double secondaryGap;

	/**
	 * Required Double containing the Detection minimum distance
	 */
	private Double minimumDistance;

	/**
	 * Optional Double containing the rms
	 */
	private Double rms;

	/**
	 * Optional String containing the quality flag
	 */
	private String quality;

	/**
	 * Optional Double containing the bayesan depth
	 */
	private Double bayesianDepth;

	/**
	 * Optional Double containing the bayesian range
	 */
	private Double bayesianRange;

	/**
	 * Optional Double containing the depth importance
	 */
	private Double depthImportance;

	/**
	 * Optional String containing the locator exit code
	 */
	private String locatorExitCode;

	/**
	 * Optional error ellipse
	 */
	public ErrorEllipse ErrorEllipse;

	/**
	 * The constructor for the LocationResult class. Initializes members to null
	 * values.
	 */
	public LocationResult() {
		id = null;
		hypocenter = null;
		supportingData = null;
		numberOfAssociatedStations = null;
		numberOfAssociatedPhases = null;
		numberOfUsedStations = null;
		numberOfUsedPhases = null;
		gap = null;
		secondaryGap = null;
		minimumDistance = null;
		rms = null;
		quality = null;
		bayesianDepth = null;
		bayesianRange = null;
    depthImportance = null;
    locatorExitCode = null;
		ErrorEllipse = null;
	}

	/**
	 * Advanced constructor
	 *
	 * The advanced constructor for the LocationResult class. Initializes members
	 * to provided values.
	 *
	 * @param newID
	 * 					  - A String containing the optional ID
	 * @param newLatitude
	 *            - A Double containing the latitude to use
	 * @param newLongitude
	 *            - A Double containing the longitude to use
	 * @param newTime
	 *            - A Date containing the origin time to use
	 * @param newDepth
	 *            - A Double containing the depth to use
	 * @param newLatitudeError
	 *            - A Double containing the latitude error to use, null to omit
	 * @param newLongitudeError
	 *            - A Double containing the longitude error to use, null to omit
	 * @param newTimeError
	 *            - A Double containing the new time error to use, null to omit
	 * @param newDepthError
	 *            - A Double containing the depth error to use, null to omit
	 * @param newSupportingData
	 *            - A ArrayList&lt;Pick&gt; newPickData containing the data that
	 *            went into this location
	 * @param newAssociatedStations
	 *            - An Integer containing the number of associated stations,
	 *            null to omit
	 * @param newAssociatedPhases
	 *            - An Integercontaining the number of associated phases, null
	 *            to omit
	 * @param newUsedStations
	 *            - An Integer containing the number of used stations, null to
	 *            omit
	 * @param newUsedPhases
	 *            - An Integer containing the number of used phases, null to
	 *            omit
	 * @param newGap
	 *            - A Double containing the gap to use, null to omit
	 * @param newSecondaryGap
	 *            - A Double containing the secondary gap to use, null to omit
	 * @param newMinimumDistance
	 *            - A Double containing the minimum distance to use, null to
	 *            omit
	 * @param newRMS
	 *            - A Double containing the rms to use, null to omit
	 * @param newQuality
	 *            - A String containing the quality to use, null to omit
	 * @param newBayesianDepth
	 *            - A Double containing the bayesian depth to use, null to omit
	 * @param newBayesianRange
	 *            - A Double containing the bayesian range to use, null to omit
	 * @param newDepthImportance
	 *            - A Double containing the depth importance to use, null to
	 *            omit
   * @param newLocatorExitCode
   *            - A String containing the locator exit code, null to omit
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
	 *            - A Double containing the equivalent radius of the horizontal
	 *            error ellipsoid in kilometers
	 */
	public LocationResult(String newID, Double newLatitude, Double newLongitude, Date newTime,
			Double newDepth, Double newLatitudeError, Double newLongitudeError,
			Double newTimeError, Double newDepthError,
			ArrayList<Pick> newSupportingData, Integer newAssociatedStations,
			Integer newAssociatedPhases, Integer newUsedStations,
			Integer newUsedPhases, Double newGap, Double newSecondaryGap,
			Double newMinimumDistance, Double newRMS, String newQuality,
			Double newBayesianDepth, Double newBayesianRange,
      Double newDepthImportance, String newLocatorExitCode, Double newE0Error, 
      Double newE0Azimuth, Double newE0Dip, Double newE1Error, 
      Double newE1Azimuth, Double newE1Dip, Double newE2Error, 
      Double newE2Azimuth, Double newE2Dip, 
      Double newMaximumHorizontalProjection,
			Double newMaximumVerticalProjection,
			Double newEquivalentHorizontalRadius) {

		this(newID, new Hypocenter(newLatitude, newLongitude, newTime, newDepth,
				    newLatitudeError, newLongitudeError, newTimeError,
            newDepthError), 
        newSupportingData, newAssociatedStations, newAssociatedPhases, 
        newUsedStations, newUsedPhases, newGap, newSecondaryGap, 
        newMinimumDistance, newRMS, newQuality, newBayesianDepth, 
        newBayesianRange, newDepthImportance, newLocatorExitCode,
				new ErrorEllipse(newE0Error, newE0Azimuth, newE0Dip, newE1Error,
						newE1Azimuth, newE1Dip, newE2Error, newE2Azimuth,
						newE2Dip, newMaximumHorizontalProjection,
						newMaximumVerticalProjection,
						newEquivalentHorizontalRadius));
	}

	/**
	 * Alternate Advanced constructor
	 *
	 * The alternate advanced constructor for the LocationResult class.
	 * Initializes members to provided values.
	 *
	 * @param newID
	 * 					  - A String containing the optional ID
	 * @param newLatitude
	 *            - A Double containing the latitude to use
	 * @param newLongitude
	 *            - A Double containing the longitude to use
	 * @param newTime
	 *            - A Date containing the origin time to use
	 * @param newDepth
	 *            - A Double containing the depth to use
	 * @param newLatitudeError
	 *            - A Double containing the latitude error to use, null to omit
	 * @param newLongitudeError
	 *            - A Double containing the longitude error to use, null to omit
	 * @param newTimeError
	 *            - A Double containing the new time error to use, null to omit
	 * @param newDepthError
	 *            - A Double containing the depth error to use, null to omit
	 * @param newSupportingData
	 *            - A ArrayList&lt;Pick&gt; newPickData containing the data that
	 *            went into this location
	 */
	public LocationResult(String newID, Double newLatitude, Double newLongitude, Date newTime,
			Double newDepth, Double newLatitudeError, Double newLongitudeError,
			Double newTimeError, Double newDepthError,
			ArrayList<Pick> newSupportingData) {
		this(newID, new Hypocenter(newLatitude, newLongitude, newTime, newDepth,
				newLatitudeError, newLongitudeError, newTimeError,
				newDepthError), newSupportingData, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null);
	}

	/**
	 * Alternate Advanced constructor
	 *
	 * The alternate advanced constructor for the LocationResult class.
	 * Initializes members to provided values.
	 *
	 * @param newHypocenter
	 *            - A Hypocenter containing the hypocenter to use
	 * @param newSupportingData
	 *            - A ArrayList&lt;Pick&gt; newPickData containing the data that
	 *            went into this location
	 */
	public LocationResult(Hypocenter newHypocenter,
			ArrayList<Pick> newSupportingData) {

		this(null, newHypocenter, newSupportingData, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null);
	}

	/**
	 * Alternate advanced constructor
	 *
	 * The alternate advanced constructor for the LocationResult class.
	 * Initializes members to provided values.
	 *
	 * @param newID
	 * 					  - A String containing the optional ID
	 * @param newHypocenter
	 *            - A Hypocenter containing the hypocenter to use
	 * @param newSupportingData
	 *            - A ArrayList&lt;Pick&gt; newPickData containing the data that
	 *            went into this location
	 * @param newAssociatedStations
	 *            - An Integer containing the number of associated stations,
	 *            null to omit
	 * @param newAssociatedPhases
	 *            - An Integercontaining the number of associated phases, null
	 *            to omit
	 * @param newUsedStations
	 *            - An Integer containing the number of used stations, null to
	 *            omit
	 * @param newUsedPhases
	 *            - An Integer containing the number of used phases, null to
	 *            omit
	 * @param newGap
	 *            - A Double containing the gap to use, null to omit
	 * @param newSecondaryGap
	 *            - A Double containing the secondary gap to use, null to omit
	 * @param newMinimumDistance
	 *            - A Double containing the minimum distance to use, null to
	 *            omit
	 * @param newRMS
	 *            - A Double containing the rms to use, null to omit
	 * @param newQuality
	 *            - A String containing the quality to use, null to omit
	 * @param newBayesianDepth
	 *            - A Double containing the bayesian depth to use, null to omit
	 * @param newBayesianRange
	 *            - A Double containing the bayesian range to use, null to omit
	 * @param newDepthImportance
	 *            - A Double containing the depth importance to use, null to
	 *            omit
   * @param newLocatorExitCode
   *            - A String containing the locator exit code, null to omit
	 * @param newErrorEllipse
	 *            - An ErrorEllipse containing the error ellipse to use, null to
	 *            omit
	 */
	public LocationResult(String newID, Hypocenter newHypocenter,
			ArrayList<Pick> newSupportingData, Integer newAssociatedStations,
			Integer newAssociatedPhases, Integer newUsedStations,
			Integer newUsedPhases, Double newGap, Double newSecondaryGap,
			Double newMinimumDistance, Double newRMS, String newQuality,
			Double newBayesianDepth, Double newBayesianRange,
      Double newDepthImportance, String newLocatorExitCode, 
      ErrorEllipse newErrorEllipse) {

		reload(newID, newHypocenter, newSupportingData, newAssociatedStations,
				newAssociatedPhases, newUsedStations, newUsedPhases, newGap,
				newSecondaryGap, newMinimumDistance, newRMS, newQuality,
				newBayesianDepth, newBayesianRange, newDepthImportance,
				newLocatorExitCode, newErrorEllipse);
	}

	/**
	 * Reload Function
	 *
	 * The reload function for the LocationResult class. Initializes members to
	 * provided values.
	 *
	 * @param newID
	 * 					  - A String containing the optional ID
	 * @param newHypocenter
	 *            - A Hypocenter containing the hypocenter to use
	 * @param newSupportingData
	 *            - A ArrayList&lt;Pick&gt; newPickData containing the data that
	 *            went into this location
	 * @param newAssociatedStations
	 *            - An Integer containing the number of associated stations,
	 *            null to omit
	 * @param newAssociatedPhases
	 *            - An Integercontaining the number of associated phases, null
	 *            to omit
	 * @param newUsedStations
	 *            - An Integer containing the number of used stations, null to
	 *            omit
	 * @param newUsedPhases
	 *            - An Integer containing the number of used phases, null to
	 *            omit
	 * @param newGap
	 *            - A Double containing the gap to use, null to omit
	 * @param newSecondaryGap
	 *            - A Double containing the secondary gap to use, null to omit
	 * @param newMinimumDistance
	 *            - A Double containing the minimum distance to use, null to
	 *            omit
	 * @param newRMS
	 *            - A Double containing the rms to use, null to omit
	 * @param newQuality
	 *            - A String containing the quality to use, null to omit
	 * @param newBayesianDepth
	 *            - A Double containing the bayesian depth to use, null to omit
	 * @param newBayesianRange
	 *            - A Double containing the bayesian range to use, null to omit
	 * @param newDepthImportance
	 *            - A Double containing the depth importance to use, null to
	 *            omit
   * @param newLocatorExitCode
   *            - A String containing the locator exit code, null to omit
	 * @param newErrorEllipse
	 *            - An ErrorEllipse containing the error ellipse to use, null to
	 *            omit
	 */
	public void reload(String newID, Hypocenter newHypocenter,
			ArrayList<Pick> newSupportingData, Integer newAssociatedStations,
			Integer newAssociatedPhases, Integer newUsedStations,
			Integer newUsedPhases, Double newGap, Double newSecondaryGap,
			Double newMinimumDistance, Double newRMS, String newQuality,
			Double newBayesianDepth, Double newBayesianRange,
      Double newDepthImportance, String newLocatorExitCode, 
      ErrorEllipse newErrorEllipse) {

		id = newID;
		hypocenter = newHypocenter;
		supportingData = newSupportingData;
		numberOfAssociatedStations = newAssociatedStations;
		numberOfAssociatedPhases = newAssociatedPhases;
		numberOfUsedStations = newUsedStations;
		numberOfUsedPhases = newUsedPhases;
		gap = newGap;
		secondaryGap = newSecondaryGap;
		minimumDistance = newMinimumDistance;
		rms = newRMS;
		quality = newQuality;
		bayesianDepth = newBayesianDepth;
		bayesianRange = newBayesianRange;
    depthImportance = newDepthImportance;
    locatorExitCode = newLocatorExitCode;
		ErrorEllipse = newErrorEllipse;
	}

	/**
	 * Constructs the class from a JSONObject, populating members
	 *
	 * @param newJSONObject
	 *            - A JSONObject.
	 */
	public LocationResult(JSONObject newJSONObject) {
		// Required values
		// hypocenter
		if (newJSONObject.containsKey(HYPOCENTER_KEY)) {
			hypocenter = new Hypocenter(
					(JSONObject) newJSONObject.get(HYPOCENTER_KEY));
		} else {
			hypocenter = null;
		}

		// associated data
		if (newJSONObject.containsKey(SUPPORTINGDATA_KEY)) {

			supportingData = new ArrayList<Pick>();

			// get the array
			JSONArray dataArray = (JSONArray) newJSONObject
					.get(SUPPORTINGDATA_KEY);

			if ((dataArray != null) && (!dataArray.isEmpty())) {

				// go through the whole array
				for (int i = 0; i < dataArray.size(); i++) {

					// add to vector
					supportingData.add(new Pick((JSONObject) dataArray.get(i)));
				}
			}
		} else {
			supportingData = null;
		}

		// optional values
		// id
		if (newJSONObject.containsKey(ID_KEY)) {
			id = newJSONObject.get(ID_KEY).toString();
		} else {
			id = null;
		}

		// associated stations
		if (newJSONObject.containsKey(ASSOCIATEDSTATIONS_KEY)) {
			numberOfAssociatedStations = ((Long) newJSONObject
					.get(ASSOCIATEDSTATIONS_KEY)).intValue();
		} else {
			numberOfAssociatedStations = null;
		}

		// associated phases
		if (newJSONObject.containsKey(ASSOCIATEDPHASES_KEY)) {
			numberOfAssociatedPhases = ((Long) newJSONObject
					.get(ASSOCIATEDPHASES_KEY)).intValue();
		} else {
			numberOfAssociatedPhases = null;
		}

		// used stations
		if (newJSONObject.containsKey(USEDSTATIONS_KEY)) {
			numberOfUsedStations = ((Long) newJSONObject.get(USEDSTATIONS_KEY))
					.intValue();
		} else {
			numberOfUsedStations = null;
		}

		// used phases
		if (newJSONObject.containsKey(USEDPHASES_KEY)) {
			numberOfUsedPhases = ((Long) newJSONObject.get(USEDPHASES_KEY))
					.intValue();
		} else {
			numberOfUsedPhases = null;
		}

		// gap
		if (newJSONObject.containsKey(GAP_KEY)) {
			gap = (double) newJSONObject.get(GAP_KEY);
		} else {
			gap = null;
		}

		// secondary gap
		if (newJSONObject.containsKey(SECONDARYGAP_KEY)) {
			secondaryGap = (double) newJSONObject.get(SECONDARYGAP_KEY);
		} else {
			secondaryGap = null;
		}

		// minimumDistance
		if (newJSONObject.containsKey(MINIMUMDISTANCE_KEY)) {
			minimumDistance = (double) newJSONObject.get(MINIMUMDISTANCE_KEY);
		} else {
			minimumDistance = null;
		}

		// rms
		if (newJSONObject.containsKey(RMS_KEY)) {
			rms = (double) newJSONObject.get(RMS_KEY);
		} else {
			rms = null;
		}

		// quality
		if (newJSONObject.containsKey(QUALITY_KEY)) {
			quality = (String) newJSONObject.get(QUALITY_KEY);
		} else {
			quality = null;
		}

		// baysian depth
		if (newJSONObject.containsKey(BAYESIANDEPTH_KEY)) {
			bayesianDepth = (double) newJSONObject.get(BAYESIANDEPTH_KEY);
		} else {
			bayesianDepth = null;
		}

		// baysian range
		if (newJSONObject.containsKey(BAYESIANRANGE_KEY)) {
			bayesianRange = (double) newJSONObject.get(BAYESIANRANGE_KEY);
		} else {
			bayesianRange = null;
		}

		// depth importance
		if (newJSONObject.containsKey(DEPTHIMPORTANCE_KEY)) {
			depthImportance = (double) newJSONObject.get(DEPTHIMPORTANCE_KEY);
		} else {
			depthImportance = null;
		}

		// locatorExitCode
		if (newJSONObject.containsKey(LOCATOREXITCODE_KEY)) {
			locatorExitCode = (String) newJSONObject.get(LOCATOREXITCODE_KEY);
		} else {
			locatorExitCode = null;
		}

		// error ellipse
		if (newJSONObject.containsKey(ERRORELLIPSE_KEY)) {
			ErrorEllipse = new ErrorEllipse(
					(JSONObject) newJSONObject.get(ERRORELLIPSE_KEY));
		} else {
			ErrorEllipse = null;
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

		String jsonID = getID();
		Hypocenter jsonHypocenter = getHypocenter();
		ArrayList<Pick> jsonSupportingData = getSupportingData();
		Integer jsonNumberOfAssociatedStations = getNumberOfAssociatedStations();
		Integer jsonNumberOfAssociatedPhases = getNumberOfAssociatedPhases();
		Integer jsonNumberOfUsedStations = getNumberOfUsedStations();
		Integer jsonNumberOfUsedPhases = getNumberOfUsedPhases();
		Double jsonGap = getGap();
		Double jsonSecondaryGap = getSecondaryGap();
		Double jsonMinimumDistance = getMinimumDistance();
		Double jsonRMS = getRMS();
		String jsonQuality = getQuality();
		Double jsonBayesianDepth = getBayesianDepth();
		Double jsonBayesianRange = getBayesianRange();
    Double jsonDepthImportance = getDepthImportance();
    String jsonLocatorExitCode = getLocatorExitCode();
		ErrorEllipse jsonErrorEllipse = getErrorEllipse();

		// id
		if (jsonID != null) {
			newJSONObject.put(ID_KEY, jsonID);
		}

		// hypocenter
		if (jsonHypocenter != null) {
			newJSONObject.put(HYPOCENTER_KEY, jsonHypocenter.toJSON());
		}

		// supportingData
		if ((jsonSupportingData != null) && (!jsonSupportingData.isEmpty())) {
			JSONArray dataArray = new JSONArray();

			// enumerate through the whole arraylist
			for (Iterator<Pick> pickIterator = jsonSupportingData
					.iterator(); pickIterator.hasNext();) {

				// convert pick to JSON object
				JSONObject pickObject = ((Pick) pickIterator.next()).toJSON();

				dataArray.add(pickObject);
			}

			if (!dataArray.isEmpty()) {
				newJSONObject.put(SUPPORTINGDATA_KEY, dataArray);
			}
		}

		// number of associated stations
		if (jsonNumberOfAssociatedStations != null) {
			newJSONObject.put(ASSOCIATEDSTATIONS_KEY,
					jsonNumberOfAssociatedStations);
		}

		// number of associated phases
		if (jsonNumberOfAssociatedPhases != null) {
			newJSONObject.put(ASSOCIATEDPHASES_KEY,
					jsonNumberOfAssociatedPhases);
		}

		// number of used stations
		if (jsonNumberOfUsedStations != null) {
			newJSONObject.put(USEDSTATIONS_KEY, jsonNumberOfUsedStations);
		}

		// number of used phases
		if (jsonNumberOfUsedPhases != null) {
			newJSONObject.put(USEDPHASES_KEY, jsonNumberOfUsedPhases);
		}

		// gap
		if (jsonGap != null) {
			newJSONObject.put(GAP_KEY, jsonGap);
		}

		// secondary gap
		if (jsonSecondaryGap != null) {
			newJSONObject.put(SECONDARYGAP_KEY, jsonSecondaryGap);
		}

		// minimumDistance
		if (jsonMinimumDistance != null) {
			newJSONObject.put(MINIMUMDISTANCE_KEY, jsonMinimumDistance);
		}

		// rms
		if (jsonRMS != null) {
			newJSONObject.put(RMS_KEY, jsonRMS);
		}

		// quality
		if (jsonQuality != null) {
			newJSONObject.put(QUALITY_KEY, jsonQuality);
		}

		// bayesian depth
		if (jsonBayesianDepth != null) {
			newJSONObject.put(BAYESIANDEPTH_KEY, jsonBayesianDepth);
		}

		// bayesian range
		if (jsonBayesianRange != null) {
			newJSONObject.put(BAYESIANRANGE_KEY, jsonBayesianRange);
		}

		// depth importance
		if (jsonDepthImportance != null) {
			newJSONObject.put(DEPTHIMPORTANCE_KEY, jsonDepthImportance);
		}

		// locator exit code
		if (jsonLocatorExitCode != null) {
			newJSONObject.put(LOCATOREXITCODE_KEY, jsonLocatorExitCode);
		}

		// error ellipse
		if (jsonErrorEllipse != null) {
			newJSONObject.put(ERRORELLIPSE_KEY, jsonErrorEllipse.toJSON());
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

		Hypocenter jsonHypocenter = getHypocenter();
		ArrayList<Pick> jsonSupportingData = getSupportingData();
		// Integer jsonNumberOfAssociatedStations =
		// getNumberOfAssociatedStations();
		// Integer jsonNumberOfAssociatedPhases = getNumberOfAssociatedPhases();
		// Integer jsonNumberOfUsedStations = getNumberOfUsedStations();
		// Integer jsonNumberOfUsedPhases = getNumberOfUsedPhases();
		Double jsonGap = getGap();
		Double jsonSecondaryGap = getSecondaryGap();
		Double jsonMinimumDistance = getMinimumDistance();
		// Double jsonRMS = getRMS();
		// String jsonQuality = getQuality();
		// Double jsonBayesianDepth = getBayesianDepth();
		// Double jsonBayesianRange = getBayesianRange();
    // Double jsonDepthImportance = getDepthImportance();
    String jsonLocatorExitCode = getLocatorExitCode();
		ErrorEllipse jsonErrorEllipse = getErrorEllipse();

		ArrayList<String> errorList = new ArrayList<String>();

		// hypocenter
		if (jsonHypocenter == null) {
			// hypocenter not found
			errorList.add("No Hypocenter in LocationResult Class.");
		} else if (!jsonHypocenter.isValid()) {
			// hypocenter invalid
			errorList.add("Invalid Hypocenter in LocationResult Class.");
		}

		// Data
		// Picks
		if ((jsonSupportingData != null) && (!jsonSupportingData.isEmpty())) {

			// enumerate through the whole arraylist
			for (Iterator<Pick> pickIterator = jsonSupportingData
					.iterator(); pickIterator.hasNext();) {

				// convert pick to JSON object
				Pick jsonPick = ((Pick) pickIterator.next());

				if (!jsonPick.isValid()) {
          ArrayList<String> pickErrorList = jsonPick.getErrors();

          // combine the errors into a single string
          String errorString = new String();
          for (int i = 0; i < pickErrorList.size(); i++) {
            errorString += " " + pickErrorList.get(i);
          }   
          errorList.add(
          "Invalid Pick in SupportingData in LocationRequest Class: " + errorString);      
					break;
				}
			}
		} else {
			// hypocenter not found
			errorList.add("No pick data in LocationResult Class.");
		}

		// gap
		if (jsonGap != null) {
			if ((jsonGap < 0) || (jsonGap > 360)) {
				// invalid Magnitude
				errorList.add(
						"Gap in LocationResult Class not in the range of 0 to 360.");
			}
		}

		// gap
		if (jsonSecondaryGap != null) {
			if ((jsonSecondaryGap < 0) || (jsonSecondaryGap > 360)) {
				// invalid Magnitude
				errorList.add(
						"Secondary gap in LocationResult Class not in the range of 0 to 360.");
			}
		}

		// minimumDistance
		if (jsonMinimumDistance != null) {
			if (jsonMinimumDistance < 0) {
				// invalid minimum distance
				errorList.add(
						"MinimumDistance in LocationResult Class is not greater than 0.");
			}
		}

    // locator exit code
    if (jsonLocatorExitCode != null) {
      boolean match = false;
			if (jsonLocatorExitCode.equals("Success")) {
				match = true;
			} else if (jsonLocatorExitCode.equals("DidNotMove")) {
				match = true;
			} else if (jsonLocatorExitCode.equals("ErrorsNotComputed")) {
				match = true;
			} else if (jsonLocatorExitCode.equals("Failed")) {
				match = true;
			} else if (jsonLocatorExitCode.equals("Unknown")) {
				match = true;
			} else {
   				match = false;
			}

			if (!match) {
				// invalid eventType
				errorList.add("Invalid locator exit code in LocationResult Class.");
			}
    }
    

		// error ellipse
		if (jsonErrorEllipse != null) {
			if (!jsonErrorEllipse.isValid()) {
				// hypocenter invalid
				errorList.add("Invalid ErrorEllipse in LocationResult Class.");
			}
		}

		// success
		return (errorList);
	}

	/**
	 * Converts the contents of the class to a csv formatted summary string
	 *
	 * @return Returns a String containing the csv formatted summary string
	 */
	public String toCSV() {
		String csvString = "";

		if(getID() != null) {
			csvString += getID() + ",";
		} else {
			csvString += "null,";
		}

		csvString += String.format("%f,", getHypocenter().getLatitude());
		csvString += String.format("%f,", getHypocenter().getLongitude());
		csvString += String.format("%f,", getHypocenter().getDepth());
		csvString += String.format("%f,", (getHypocenter().getTime().getTime() * 0.001d));

		if(getRMS() != null) {
			csvString += String.format("%f,", getRMS());
		} else {
			csvString += "0.0,";
		}

		if(getMinimumDistance() != null) {
			csvString += String.format("%f,", getMinimumDistance());
		} else {
			csvString += "0.0,";
		}

		if(getGap() != null) {
			csvString += String.format("%f,", getGap());
		} else {
			csvString += "0.0,";
		}

		if(getNumberOfAssociatedPhases() != null) {
			csvString += String.format("%d,", getNumberOfAssociatedPhases());
		} else {
			csvString += "0,";
		}

		csvString += "0.0,"; // mag
		csvString += "0.0,"; // detectiontime

		return csvString;
	} 

	/**
	 * @return the id
	 */
	public String getID() {
		return id;
	}

	/**
	 * @return the hypocenter
	 */
	public Hypocenter getHypocenter() {
		return hypocenter;
	}

	/**
	 * @return the supportingData
	 */
	public ArrayList<Pick> getSupportingData() {
		return supportingData;
	}

	/**
	 * @return the numberOfAssociatedStations
	 */
	public Integer getNumberOfAssociatedStations() {
		return numberOfAssociatedStations;
	}

	/**
	 * @return the numberOfAssociatedPhases
	 */
	public Integer getNumberOfAssociatedPhases() {
		return numberOfAssociatedPhases;
	}

	/**
	 * @return the numberOfUsedStations
	 */
	public Integer getNumberOfUsedStations() {
		return numberOfUsedStations;
	}

	/**
	 * @return the numberOfUsedPhases
	 */
	public Integer getNumberOfUsedPhases() {
		return numberOfUsedPhases;
	}

	/**
	 * @return the gap
	 */
	public Double getGap() {
		return gap;
	}

	/**
	 * @return the secondaryGap
	 */
	public Double getSecondaryGap() {
		return secondaryGap;
	}

	/**
	 * @return the minimumDistance
	 */
	public Double getMinimumDistance() {
		return minimumDistance;
	}

	/**
	 * @return the rms
	 */
	public Double getRMS() {
		return rms;
	}

	/**
	 * @return the quality
	 */
	public String getQuality() {
		return quality;
	}

	/**
	 * @return the bayesianDepth
	 */
	public Double getBayesianDepth() {
		return bayesianDepth;
	}

	/**
	 * @return the bayesianRange
	 */
	public Double getBayesianRange() {
		return bayesianRange;
	}

	/**
	 * @return the depthImportance
	 */
	public Double getDepthImportance() {
		return depthImportance;
	}

	/**
	 * @return the locatorExitCode
	 */
	public String getLocatorExitCode() {
		return locatorExitCode;
	}

	/**
	 * @return the ErrorEllipse
	 */
	public ErrorEllipse getErrorEllipse() {
		return ErrorEllipse;
	}

	/**
	 * @param id the id to set
	 */
	public void setID(String id) {
		this.id = id;
	}

	/**
	 * @param hypocenter
	 *            the hypocenter to set
	 */
	public void setHypocenter(Hypocenter hypocenter) {
		this.hypocenter = hypocenter;
	}

	/**
	 * @param supportingData
	 *            the supportingData to set
	 */
	public void setSupportingData(ArrayList<Pick> supportingData) {
		this.supportingData = supportingData;
	}

	/**
	 * @param numberOfAssociatedStations
	 *            the numberOfAssociatedStations to set
	 */
	public void setNumberOfAssociatedStations(
			Integer numberOfAssociatedStations) {
		this.numberOfAssociatedStations = numberOfAssociatedStations;
	}

	/**
	 * @param numberOfAssociatedPhases
	 *            the numberOfAssociatedPhases to set
	 */
	public void setNumberOfAssociatedPhases(Integer numberOfAssociatedPhases) {
		this.numberOfAssociatedPhases = numberOfAssociatedPhases;
	}

	/**
	 * @param numberOfUsedStations
	 *            the numberOfUsedStations to set
	 */
	public void setNumberOfUsedStations(Integer numberOfUsedStations) {
		this.numberOfUsedStations = numberOfUsedStations;
	}

	/**
	 * @param numberOfUsedPhases
	 *            the numberOfUsedPhases to set
	 */
	public void setNumberOfUsedPhases(Integer numberOfUsedPhases) {
		this.numberOfUsedPhases = numberOfUsedPhases;
	}

	/**
	 * @param gap
	 *            the gap to set
	 */
	public void setGap(Double gap) {
		this.gap = gap;
	}

	/**
	 * @param secondaryGap
	 *            the secondaryGap to set
	 */
	public void setSecondaryGap(Double secondaryGap) {
		this.secondaryGap = secondaryGap;
	}

	/**
	 * @param minimumDistance
	 *            the minimumDistance to set
	 */
	public void setMinimumDistance(Double minimumDistance) {
		this.minimumDistance = minimumDistance;
	}

	/**
	 * @param rms
	 *            the rms to set
	 */
	public void setRms(Double rms) {
		this.rms = rms;
	}

	/**
	 * @param quality
	 *            the quality to set
	 */
	public void setQuality(String quality) {
		this.quality = quality;
	}

	/**
	 * @param bayesianDepth
	 *            the bayesianDepth to set
	 */
	public void setBayesianDepth(Double bayesianDepth) {
		this.bayesianDepth = bayesianDepth;
	}

	/**
	 * @param bayesianRange
	 *            the bayesianRange to set
	 */
	public void setBayesianRange(Double bayesianRange) {
		this.bayesianRange = bayesianRange;
	}

	/**
	 * @param depthImportance
	 *            the depthImportance to set
	 */
	public void setDepthImportance(Double depthImportance) {
		this.depthImportance = depthImportance;
	}

	/**
	 * @param locatorExitCode
	 *            the locatorExitCode to set
	 */
	public void setLocatorExitCode(String locatorExitCode) {
		this.locatorExitCode = locatorExitCode;
	}

	/**
	 * @param ErrorEllipse
	 *            the ErrorEllipse to set
	 */
	public void setErrorEllipse(ErrorEllipse ErrorEllipse) {
		this.ErrorEllipse = ErrorEllipse;
	}

}
