package gov.usgs.processingformats;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * a conversion class used to create, parse, and validate location request data
 *
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class LocationRequest implements ProcessingInt {

	/**
	 * JSON Keys
	 */
	public static final String TYPE_KEY = "Type";
	public static final String EARTHMODEL_KEY = "EarthModel";
	public static final String SOURCEORIGINTIME_KEY = "SourceOriginTime";
	public static final String SOURCELATITUDE_KEY = "SourceLatitude";
	public static final String SOURCELONGITUDE_KEY = "SourceLongitude";
	public static final String SOURCEDEPTH_KEY = "SourceDepth";
	public static final String INPUTDATA_KEY = "InputData";
	public static final String ISLOCATIONNEW_KEY = "IsLocationNew";
	public static final String ISLOCATIONHELD_KEY = "IsLocationHeld";
	public static final String ISDEPTHHELD_KEY = "IsDepthHeld";
	public static final String ISBAYESIANDEPTH_KEY = "IsBayesianDepth";
	public static final String BAYESIANDEPTH_KEY = "BayesianDepth";
	public static final String BAYESIANSPREAD_KEY = "BayesianSpread";
	public static final String USESVD_KEY = "UseSVD";
	public static final String OUTPUTDATA_KEY = "OutputData";

	/**
	 * Required type identifier for this LocationRequest
	 */
	private String type;

	/**
	 * Required earth model for this LocationRequest
	 */
	private String earthModel;

	/**
	 * Required Double containing the source latitude
	 */
	private Double sourceLatitude;

	/**
	 * Required Double containing the source longitude
	 */
	private Double sourceLongitude;

	/**
	 * Required time containing the source time
	 */
	private Date sourceOriginTime;

	/**
	 * Required Double containing the sourcedepth
	 */
	private Double sourceDepth;

	/**
	 * A required vector of input Pick objects for this LocationRequest
	 */
	private ArrayList<Pick> inputData;

	/**
	 * Optional Boolean indicating whether the location is new
	 */
	private Boolean isLocationNew;

	/**
	 * Optional Boolean indicating whether the location is held
	 */
	private Boolean isLocationHeld;

	/**
	 * Optional Boolean indicating whether the depth is held
	 */
	private Boolean isDepthHeld;

	/**
	 * Optional Boolean indicating whether the depth is bayesian
	 */
	private Boolean isBayesianDepth;

	/**
	 * Optional Double containing the bayesan depth
	 */
	private Double bayesianDepth;

	/**
	 * Optional Double containing the bayesian spread
	 */
	private Double bayesianSpread;

	/**
	 * Optional Boolean indicating whether use SVD
	 */
	private Boolean useSVD;
	/**
	 * A LocationResult object to contain the output from the locator
	 */
	private LocationResult outputData;

	/**
	 * The constructor for the LocationRequest class. Initializes members to
	 * null values.
	 */
	public LocationRequest() {
		type = null;
		earthModel = null;
		sourceLatitude = null;
		sourceLongitude = null;
		sourceOriginTime = null;
		sourceDepth = null;
		inputData = null;
		isLocationNew = null;
		isLocationHeld = null;
		isDepthHeld = null;
		isBayesianDepth = null;
		bayesianDepth = null;
		bayesianSpread = null;
		useSVD = null;
		outputData = null;
	}

	/**
	 * Advanced constructor
	 *
	 * The advanced constructor for the LocationResult class. Initializes members
	 * to provided values.
	 *
	 * @param newType
	 *            - A String containing the name of the algorithm this request
	 *            is valid for
	 * @param newEarthModel
	 *            - A String containing the name of theTravel Time Earth Model
	 *            to use
	 * @param newSourceLatitude
	 *            - A Double containing the latitude to use
	 * @param newSourceLongitude
	 *            - A Double containing the longitude to use
	 * @param newSourceOriginTime
	 *            - A Date containing the origin time to use
	 * @param newSourceDepth
	 *            - A Double containing the depth to use
	 * @param newInputData
	 *            - A ArrayList&lt;Pick&gt; newPickData containing the data to
	 *            use for this location
	 * @param newIsLocationNew
	 *            - A Boolean indicating whether the location is now, null to
	 *            omit
	 * @param newIsLocationHeld
	 *            - A Boolean indicating whether to hold the location, null to
	 *            omit
	 * @param newIsDepthHeld
	 *            - A Boolean indicating whether to hold the depth, null to omit
	 * @param newIsBayesianDepth
	 *            - A Boolean indicating whether to use the baysian depth, null
	 *            to omit
	 * @param newBayesianDepth
	 *            - A Double containing the bayesian depth to use, null to omit
	 * @param newBayesianSpread
	 *            - A Double containing the bayesian spread to use, null to omit
	 * @param newUseSVD
	 *            - A Boolean indicating whether to use SVD, null to omit
	 */
	public LocationRequest(String newType, String newEarthModel,
			Double newSourceLatitude, Double newSourceLongitude,
			Date newSourceOriginTime, Double newSourceDepth,
			ArrayList<Pick> newInputData, Boolean newIsLocationNew,
			Boolean newIsLocationHeld, Boolean newIsDepthHeld,
			Boolean newIsBayesianDepth, Double newBayesianDepth,
			Double newBayesianSpread, Boolean newUseSVD) {

		reload(newType, newEarthModel, newSourceLatitude, newSourceLongitude,
				newSourceOriginTime, newSourceDepth, newInputData,
				newIsLocationNew, newIsLocationHeld, newIsDepthHeld,
				newIsBayesianDepth, newBayesianDepth, newBayesianSpread,
				newUseSVD);
	}

	/**
	 * Reload Function
	 *
	 * The reload function for the LocationRequest class. Initializes members to
	 * provided values.
	 *
	 *
	 * @param newType
	 *            - A String containing the name of the algorithm this request
	 *            is valid for
	 * @param newEarthModel
	 *            - A String containing the name of theTravel Time Earth Model
	 *            to use
	 * @param newSourceLatitude
	 *            - A Double containing the latitude to use
	 * @param newSourceLongitude
	 *            - A Double containing the longitude to use
	 * @param newSourceOriginTime
	 *            - A Date containing the origin time to use
	 * @param newSourceDepth
	 *            - A Double containing the depth to use
	 * @param newInputData
	 *            - A ArrayList&lt;Pick&gt; newPickData containing the data to
	 *            use for this location
	 * @param newIsLocationNew
	 *            - A Boolean indicating whether the location is now, null to
	 *            omit
	 * @param newIsLocationHeld
	 *            - A Boolean indicating whether to hold the location, null to
	 *            omit
	 * @param newIsDepthHeld
	 *            - A Boolean indicating whether to hold the depth, null to omit
	 * @param newIsBayesianDepth
	 *            - A Boolean indicating whether to use the baysian depth, null
	 *            to omit
	 * @param newBayesianDepth
	 *            - A Double containing the bayesian depth to use, null to omit
	 * @param newBayesianSpread
	 *            - A Double containing the bayesian spread to use, null to omit
	 * @param newUseSVD
	 *            - A Boolean indicating whether to use SVD, null to omit
	 */
	public void reload(String newType, String newEarthModel,
			Double newSourceLatitude, Double newSourceLongitude,
			Date newSourceOriginTime, Double newSourceDepth,
			ArrayList<Pick> newInputData, Boolean newIsLocationNew,
			Boolean newIsLocationHeld, Boolean newIsDepthHeld,
			Boolean newIsBayesianDepth, Double newBayesianDepth,
			Double newBayesianSpread, Boolean newUseSVD) {

		type = newType;
		earthModel = newEarthModel;
		sourceLatitude = newSourceLatitude;
		sourceLongitude = newSourceLongitude;
		sourceOriginTime = newSourceOriginTime;
		sourceDepth = newSourceDepth;
		inputData = newInputData;
		isLocationNew = newIsLocationNew;
		isLocationHeld = newIsLocationHeld;
		isDepthHeld = newIsDepthHeld;
		isBayesianDepth = newIsBayesianDepth;
		bayesianDepth = newBayesianDepth;
		bayesianSpread = newBayesianSpread;
		useSVD = newUseSVD;
		outputData = null;
	}

	/**
	 * Constructs the class from a JSONObject, populating members
	 *
	 * @param newJSONObject
	 *            - A JSONObject.
	 */
	public LocationRequest(JSONObject newJSONObject) {

		// Required values
		// type
		if (newJSONObject.containsKey(TYPE_KEY)) {
			type = newJSONObject.get(TYPE_KEY).toString();
		} else {
			type = null;
		}

		// earthModel
		if (newJSONObject.containsKey(EARTHMODEL_KEY)) {
			earthModel = newJSONObject.get(EARTHMODEL_KEY).toString();
		} else {
			earthModel = null;
		}

		// latitude
		if (newJSONObject.containsKey(SOURCELATITUDE_KEY)) {
			sourceLatitude = (double) newJSONObject.get(SOURCELATITUDE_KEY);
		} else {
			sourceLatitude = null;
		}

		// longitude
		if (newJSONObject.containsKey(SOURCELONGITUDE_KEY)) {
			sourceLongitude = (double) newJSONObject.get(SOURCELONGITUDE_KEY);
		} else {
			sourceLongitude = null;
		}

		// time
		if (newJSONObject.containsKey(SOURCEORIGINTIME_KEY)) {
			sourceOriginTime = Utility.getDate(
					newJSONObject.get(SOURCEORIGINTIME_KEY).toString());
		} else {
			sourceOriginTime = null;
		}

		// depth
		if (newJSONObject.containsKey(SOURCEDEPTH_KEY)) {
			sourceDepth = (double) newJSONObject.get(SOURCEDEPTH_KEY);
		} else {
			sourceDepth = null;
		}

		// input data
		if (newJSONObject.containsKey(INPUTDATA_KEY)) {

			inputData = new ArrayList<Pick>();

			// get the array
			JSONArray dataArray = (JSONArray) newJSONObject.get(INPUTDATA_KEY);

			if ((dataArray != null) && (!dataArray.isEmpty())) {

				// go through the whole array
				for (int i = 0; i < dataArray.size(); i++) {

					// add to vector
					inputData.add(new Pick((JSONObject) dataArray.get(i)));
				}
			}
		} else {
			inputData = null;
		}

		// Optional values
		// isLocationNew
		if (newJSONObject.containsKey(ISLOCATIONNEW_KEY)) {
			isLocationNew = (boolean) newJSONObject.get(ISLOCATIONNEW_KEY);
		} else {
			isLocationNew = null;
		}

		// isLocationHeld
		if (newJSONObject.containsKey(ISLOCATIONHELD_KEY)) {
			isLocationHeld = (boolean) newJSONObject.get(ISLOCATIONHELD_KEY);
		} else {
			isLocationHeld = null;
		}

		// isDepthHeld
		if (newJSONObject.containsKey(ISDEPTHHELD_KEY)) {
			isDepthHeld = (boolean) newJSONObject.get(ISDEPTHHELD_KEY);
		} else {
			isDepthHeld = null;
		}

		// isBayesianDepth
		if (newJSONObject.containsKey(ISBAYESIANDEPTH_KEY)) {
			isBayesianDepth = (boolean) newJSONObject.get(ISBAYESIANDEPTH_KEY);
		} else {
			isBayesianDepth = null;
		}

		// baysian depth
		if (newJSONObject.containsKey(BAYESIANDEPTH_KEY)) {
			bayesianDepth = (double) newJSONObject.get(BAYESIANDEPTH_KEY);
		} else {
			bayesianDepth = null;
		}

		// baysian spread
		if (newJSONObject.containsKey(BAYESIANSPREAD_KEY)) {
			bayesianSpread = (double) newJSONObject.get(BAYESIANSPREAD_KEY);
		} else {
			bayesianSpread = null;
		}

		// useSVD
		if (newJSONObject.containsKey(USESVD_KEY)) {
			useSVD = (boolean) newJSONObject.get(USESVD_KEY);
		} else {
			useSVD = null;
		}

		// Output values
		// outputData
		if (newJSONObject.containsKey(OUTPUTDATA_KEY)) {
			outputData = new LocationResult(
					(JSONObject) newJSONObject.get(OUTPUTDATA_KEY));
		} else {
			outputData = null;
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

		String jsonType = getType();
		String jsonEarthModel = getEarthModel();
		Double jsonSourceLatitude = getSourceLatitude();
		Double jsonSourceLongitude = getSourceLongitude();
		Date jsonSourceOriginTime = getSourceOriginTime();
		Double jsonSourceDepth = getSourceDepth();
		ArrayList<Pick> jsonInputData = getInputData();

		Boolean jsonIsLocationNew = getIsLocationNew();
		Boolean jsonIsLocationHeld = getIsLocationHeld();
		Boolean jsonIsDepthHeld = getIsDepthHeld();
		Boolean jsonIsBayesianDepth = getIsBayesianDepth();
		Double jsonBayesianDepth = getBayesianDepth();
		Double jsonBayesianSpread = getBayesianSpread();
		Boolean jsonUseSVD = getUseSVD();
		LocationResult jsonOutputData = getOutputData();

		// Required values
		// type
		if (jsonType != null) {
			newJSONObject.put(TYPE_KEY, jsonType);
		}

		// earth model
		if (jsonEarthModel != null) {
			newJSONObject.put(EARTHMODEL_KEY, jsonEarthModel);
		}

		// latitude
		if (jsonSourceLatitude != null) {
			newJSONObject.put(SOURCELATITUDE_KEY, jsonSourceLatitude);
		}

		// longitude
		if (jsonSourceLongitude != null) {
			newJSONObject.put(SOURCELONGITUDE_KEY, jsonSourceLongitude);
		}

		// time
		if (jsonSourceOriginTime != null) {
			newJSONObject.put(SOURCEORIGINTIME_KEY,
					Utility.formatDate(jsonSourceOriginTime));
		}

		// depth
		if (jsonSourceDepth != null) {
			newJSONObject.put(SOURCEDEPTH_KEY, jsonSourceDepth);
		}

		// input data
		if ((jsonInputData != null) && (!jsonInputData.isEmpty())) {
			JSONArray dataArray = new JSONArray();

			// enumerate through the whole arraylist
			for (Iterator<Pick> pickIterator = jsonInputData
					.iterator(); pickIterator.hasNext();) {

				// convert pick to JSON object
				JSONObject pickObject = ((Pick) pickIterator.next()).toJSON();

				dataArray.add(pickObject);
			}

			if (!dataArray.isEmpty()) {
				newJSONObject.put(INPUTDATA_KEY, dataArray);
			}
		}

		// optional values
		// isLocationNew
		if (jsonIsLocationNew != null) {
			newJSONObject.put(ISLOCATIONNEW_KEY, jsonIsLocationNew);
		}

		// isLocationHeld
		if (jsonIsLocationHeld != null) {
			newJSONObject.put(ISLOCATIONHELD_KEY, jsonIsLocationHeld);
		}

		// isDepthHeld
		if (jsonIsDepthHeld != null) {
			newJSONObject.put(ISDEPTHHELD_KEY, jsonIsDepthHeld);
		}

		// isBayesianDepth
		if (jsonIsBayesianDepth != null) {
			newJSONObject.put(ISBAYESIANDEPTH_KEY, jsonIsBayesianDepth);
		}

		// bayesian depth
		if (jsonBayesianDepth != null) {
			newJSONObject.put(BAYESIANDEPTH_KEY, jsonBayesianDepth);
		}

		// bayesian spread
		if (jsonBayesianSpread != null) {
			newJSONObject.put(BAYESIANSPREAD_KEY, jsonBayesianSpread);
		}

		// use SVD
		if (jsonUseSVD != null) {
			newJSONObject.put(USESVD_KEY, jsonUseSVD);
		}

		// output values
		// output data
		if (jsonOutputData != null) {
			newJSONObject.put(OUTPUTDATA_KEY, jsonOutputData.toJSON());
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

		// String jsonType = getType();
		// String jsonEarthModel = getEarthModel();
		Double jsonSourceLatitude = getSourceLatitude();
		Double jsonSourceLongitude = getSourceLongitude();
		Date jsonSourceOriginTime = getSourceOriginTime();
		Double jsonSourceDepth = getSourceDepth();
		ArrayList<Pick> jsonInputData = getInputData();

		// Boolean jsonIsLocationNew = getIsLocationNew();
		// Boolean jsonIsLocationHeld = getIsLocationHeld();
		// Boolean jsonIsDepthHeld = getIsDepthHeld();
		// Boolean jsonIsBayesianDepth = getIsBayesianDepth();
		// Double jsonBayesianDepth = getBayesianDepth();
		// Double jsonBayesianSpread = getBayesianSpread();
		// Boolean jsonUseSVD = getUseSVD();
		LocationResult jsonOutputData = getOutputData();

		ArrayList<String> errorList = new ArrayList<String>();

		// Required Keys
		// latitude
		if (jsonSourceLatitude == null) {
			// latitude not found
			errorList.add("No Source Latitude in LocationRequest Class.");
		} else if ((jsonSourceLatitude < -90) || (jsonSourceLatitude > 90)) {
			// invalid latitude
			errorList.add(
					"Source Latitude in LocationRequest Class not in the range of -90 to 90.");
		}

		// longitude
		if (jsonSourceLongitude == null) {
			// longitude not found
			errorList.add("No Source Longitude in LocationRequest Class.");
		} else if ((jsonSourceLongitude < -180)
				|| (jsonSourceLongitude > 180)) {
			// invalid longitude
			errorList.add(
					"Source Longitude in LocationRequest Class not in the range of -180 to 180.");
		}

		// time
		if (jsonSourceOriginTime == null) {
			// time not found
			errorList.add("No Source Origin Time in LocationRequest Class.");
		}

		// depth
		if (jsonSourceDepth == null) {
			// depth not found
			errorList.add("No Depth in LocationRequest Class.");
		} else if ((jsonSourceDepth < -100) || (jsonSourceDepth > 1500)) {
			// invalid depth
			errorList.add(
					"Source Depth in LocationRequest Class not in the range of -100 to 1500.");
		}

		// input data
		if ((jsonInputData != null) && (!jsonInputData.isEmpty())) {

			// enumerate through the whole arraylist
			for (Iterator<Pick> pickIterator = jsonInputData
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
              "Invalid Pick in InputData in LocationRequest Class: " + errorString);          
					break;
				}
			}
		} else {
			// hypocenter not found
			errorList.add("No input data in LocationRequest Class.");
		}

		// output data
		if (jsonOutputData != null) {
			if (!jsonOutputData.isValid()) {
				// hypocenter invalid
				errorList.add("Invalid OutputData in LocationRequest Class.");
			}
		}

		// success
		return (errorList);
	}

	/**
	 * @return the outputData
	 */
	public LocationResult getOutputData() {
		return outputData;
	}
	/**
	 * @param outputData
	 *            the outputData to set
	 */
	public void setOutputData(LocationResult outputData) {
		this.outputData = outputData;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return the earthModel
	 */
	public String getEarthModel() {
		return earthModel;
	}
	/**
	 * @return the sourceLatitude
	 */
	public Double getSourceLatitude() {
		return sourceLatitude;
	}
	/**
	 * @return the sourceLongitude
	 */
	public Double getSourceLongitude() {
		return sourceLongitude;
	}
	/**
	 * @return the sourceOriginTime
	 */
	public Date getSourceOriginTime() {
		return sourceOriginTime;
	}
	/**
	 * @return the sourceDepth
	 */
	public Double getSourceDepth() {
		return sourceDepth;
	}
	/**
	 * @return the inputData
	 */
	public ArrayList<Pick> getInputData() {
		return inputData;
	}
	/**
	 * @return the isLocationNew
	 */
	public Boolean getIsLocationNew() {
		return isLocationNew;
	}
	/**
	 * @return the isLocationHeld
	 */
	public Boolean getIsLocationHeld() {
		return isLocationHeld;
	}
	/**
	 * @return the isDepthHeld
	 */
	public Boolean getIsDepthHeld() {
		return isDepthHeld;
	}
	/**
	 * @return the isBayesianDepth
	 */
	public Boolean getIsBayesianDepth() {
		return isBayesianDepth;
	}
	/**
	 * @return the bayesianDepth
	 */
	public Double getBayesianDepth() {
		return bayesianDepth;
	}
	/**
	 * @return the bayesianSpread
	 */
	public Double getBayesianSpread() {
		return bayesianSpread;
	}
	/**
	 * @return the useSVD
	 */
	public Boolean getUseSVD() {
		return useSVD;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param earthModel
	 *            the earthModel to set
	 */
	public void setEarthModel(String earthModel) {
		this.earthModel = earthModel;
	}

	/**
	 * @param sourceLatitude
	 *            the sourceLatitude to set
	 */
	public void setSourceLatitude(Double sourceLatitude) {
		this.sourceLatitude = sourceLatitude;
	}

	/**
	 * @param sourceLongitude
	 *            the sourceLongitude to set
	 */
	public void setSourceLongitude(Double sourceLongitude) {
		this.sourceLongitude = sourceLongitude;
	}

	/**
	 * @param sourceOriginTime
	 *            the sourceOriginTime to set
	 */
	public void setSourceOriginTime(Date sourceOriginTime) {
		this.sourceOriginTime = sourceOriginTime;
	}

	/**
	 * @param sourceDepth
	 *            the sourceDepth to set
	 */
	public void setSourceDepth(Double sourceDepth) {
		this.sourceDepth = sourceDepth;
	}

	/**
	 * @param inputData
	 *            the inputData to set
	 */
	public void setInputData(ArrayList<Pick> inputData) {
		this.inputData = inputData;
	}

	/**
	 * @param isLocationNew
	 *            the isLocationNew to set
	 */
	public void setIsLocationNew(Boolean isLocationNew) {
		this.isLocationNew = isLocationNew;
	}

	/**
	 * @param isLocationHeld
	 *            the isLocationHeld to set
	 */
	public void setIsLocationHeld(Boolean isLocationHeld) {
		this.isLocationHeld = isLocationHeld;
	}

	/**
	 * @param isDepthHeld
	 *            the isDepthHeld to set
	 */
	public void setIsDepthHeld(Boolean isDepthHeld) {
		this.isDepthHeld = isDepthHeld;
	}

	/**
	 * @param isBayesianDepth
	 *            the isBayesianDepth to set
	 */
	public void setIsBayesianDepth(Boolean isBayesianDepth) {
		this.isBayesianDepth = isBayesianDepth;
	}

	/**
	 * @param bayesianDepth
	 *            the bayesianDepth to set
	 */
	public void setBayesianDepth(Double bayesianDepth) {
		this.bayesianDepth = bayesianDepth;
	}

	/**
	 * @param bayesianSpread
	 *            the bayesianSpread to set
	 */
	public void setBayesianSpread(Double bayesianSpread) {
		this.bayesianSpread = bayesianSpread;
	}

	/**
	 * @param useSVD
	 *            the useSVD to set
	 */
	public void setUseSVD(Boolean useSVD) {
		this.useSVD = useSVD;
	}
}
