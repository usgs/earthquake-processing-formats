package gov.usgs.processingformats;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import gov.usgs.processingformats.ProcessingInt;

/**
 * a conversion class used to create, parse, and validate travel time plot data
 * 
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class TravelTimePlotData implements ProcessingInt {

	/**
	 * JSON Keys
	 */
	public static final String TYPE_KEY = "Type";
	public static final String MAXIMIUMTRAVELTIME_KEY = "MaximumTravelTime";
	public static final String BRANCHES_KEY = "Branches";

	/**
	 * Required type of data as a String
	 */
	private String type;	
	
	/**
	 * Required maximum travel time in seconds
	 */
	private Double maximumTravelTime;

	/**
	 * A required vector of TravelTimePlotDataBranch objects
	 */
	private ArrayList<TravelTimePlotDataBranch> branches;

	/**
	 * The constructor for the TravelTimePlotData class. Initializes members to
	 * null values.
	 */
	public TravelTimePlotData() {

		reload(null, null);
	}

	/**
	 * Advanced constructor
	 * 
	 * The advanced constructor for the TravelTimePlotData class. Initializes
	 * members to provided values.
	 * 
	 * @param newMaximumTravelTime
	 *            - A Double containing the seismic phase code
	 * @param newBranches
	 *            - A ArrayList&lt;TravelTimePlotDataSample&gt; containing the
	 *            sample data
	 */
	public TravelTimePlotData(Double newMaximumTravelTime,
			ArrayList<TravelTimePlotDataBranch> newBranches) {

		reload(newMaximumTravelTime, newBranches);
	}

	/**
	 * Constructs the class from a JSONObject, populating members
	 * 
	 * @param newJSONObject
	 *            - A JSONObject.
	 */
	public TravelTimePlotData(JSONObject newJSONObject) {

		// Required values
		// type
		if (newJSONObject.containsKey(TYPE_KEY)) {
			type = newJSONObject
					.get(TYPE_KEY).toString();
		} else {
			type = null;
		}		
		// maximumTravelTime
		if (newJSONObject.containsKey(MAXIMIUMTRAVELTIME_KEY)) {
			maximumTravelTime = (double) newJSONObject
					.get(MAXIMIUMTRAVELTIME_KEY);
		} else {
			maximumTravelTime = null;
		}

		// branches
		if (newJSONObject.containsKey(BRANCHES_KEY)) {

			branches = new ArrayList<TravelTimePlotDataBranch>();

			// get the array
			JSONArray branchesArray = (JSONArray) newJSONObject
					.get(BRANCHES_KEY);

			if ((branchesArray != null) && (!branchesArray.isEmpty())) {

				// go through the whole array
				for (int i = 0; i < branchesArray.size(); i++) {

					// get the object
					JSONObject branchesObject = (JSONObject) branchesArray
							.get(i);

					// add to vector
					branches.add(new TravelTimePlotDataBranch(branchesObject));

				}
			}
		} else {
			branches = null;
		}
	}

	/**
	 * Constructs the class from a TravelTimePlotData object, populating members
	 * (copy constructor)
	 * 
	 * @param sourceObject
	 *            - A TravelTimePlotData object.
	 */
	public TravelTimePlotData(TravelTimePlotData sourceObject) {

		reload(sourceObject.maximumTravelTime, sourceObject.branches);
	}

	/**
	 * Reload Function
	 * 
	 * The reload function for the TravelTimeData class. Initializes members to
	 * provided values.
	 * 
	 * @param newMaximumTravelTime
	 *            - A Double containing the seismic phase code
	 * @param newBranches
	 *            - A ArrayList&lt;TravelTimePlotDataSample&gt; containing the
	 *            sample data
	 */
	public void reload(Double newMaximumTravelTime,
			ArrayList<TravelTimePlotDataBranch> newBranches) {

		type = "TTPlotData";
		maximumTravelTime = newMaximumTravelTime;
		branches = newBranches;

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
		Double jsonMaximumTravelTime = getMaximumTravelTime();
		ArrayList<TravelTimePlotDataBranch> jsonBranches = getBranches();

		// type
		if (jsonType != null) {
			newJSONObject.put(TYPE_KEY, jsonType);
		}		
		
		// phase
		if (jsonMaximumTravelTime != null) {
			newJSONObject.put(MAXIMIUMTRAVELTIME_KEY, jsonMaximumTravelTime);
		}

		// branches
		if ((jsonBranches != null) && (!jsonBranches.isEmpty())) {

			JSONArray branchesArray = new JSONArray();

			// enumerate through the whole arraylist
			for (Iterator<TravelTimePlotDataBranch> branchesIterator = jsonBranches
					.iterator(); branchesIterator.hasNext();) {

				// convert branch to JSON object
				JSONObject branchObject = ((TravelTimePlotDataBranch) branchesIterator
						.next()).toJSON();

				branchesArray.add(branchObject);
			}

			if (!branchesArray.isEmpty()) {
				newJSONObject.put(BRANCHES_KEY, branchesArray);
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
		Double jsonMaximumTravelTime = getMaximumTravelTime();
		ArrayList<TravelTimePlotDataBranch> jsonBranches = getBranches();

		// type
		if (jsonType == null) {
			// type not found
			errorList.add("No Type in TravelTimePlotData Class.");
		} else if (jsonType.isEmpty()) {
			// type empty
			errorList.add("Empty Type in TravelTimePlotData Class.");
		} else if (!jsonType.equals("TTPlotData")) {
			// wrong type
			errorList.add("Non-TTPlotData type in TravelTimePlotData Class.");
		}
		
		// maximumTravelTime
		if (jsonMaximumTravelTime == null) {
			// maximumTravelTime not found
			errorList
					.add("No Maximum Travel Time in TravelTimePlotData Class.");
		}

		// Branches
		if ((jsonBranches != null) && (!jsonBranches.isEmpty())) {

			// enumerate through the whole arraylist
			for (Iterator<TravelTimePlotDataBranch> branchesIterator = jsonBranches
					.iterator(); branchesIterator.hasNext();) {

				// get next branch object
				TravelTimePlotDataBranch branchObject = ((TravelTimePlotDataBranch) branchesIterator
						.next());

				if (!branchObject.isValid()) {
					errorList.add(
							"Invalid TravelTimePlotDataBranch in samples in TravelTimePlotData Class");
					break;
				}
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
	 * @return the maximumTravelTime
	 */
	public Double getMaximumTravelTime() {
		return maximumTravelTime;
	}

	/**
	 * @param maximumTravelTime
	 *            the maximumTravelTime to set
	 */
	public void setMaximumTravelTime(Double maximumTravelTime) {
		this.maximumTravelTime = maximumTravelTime;
	}

	/**
	 * @return the vector of TravelTimePlotDataBranch objects
	 */
	public ArrayList<TravelTimePlotDataBranch> getBranches() {
		return branches;
	}

	/**
	 * @param branches
	 *            the vector of TravelTimePlotDataBranch objects to set
	 */
	public void setBranches(ArrayList<TravelTimePlotDataBranch> branches) {
		this.branches = branches;
	}
}
