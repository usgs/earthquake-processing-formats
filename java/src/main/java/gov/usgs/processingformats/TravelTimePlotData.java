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
	 * Required Type of data as a String
	 */
	public String Type;	
	
	/**
	 * Required maximum travel time in seconds
	 */
	public Double MaximumTravelTime;

	/**
	 * A required vector of TravelTimePlotDataBranch objects
	 */
	public ArrayList<TravelTimePlotDataBranch> Branches;

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
		// Type
		if (newJSONObject.containsKey(TYPE_KEY)) {
			Type = newJSONObject
					.get(TYPE_KEY).toString();
		} else {
			Type = null;
		}		
		// MaximumTravelTime
		if (newJSONObject.containsKey(MAXIMIUMTRAVELTIME_KEY)) {
			MaximumTravelTime = (double) newJSONObject
					.get(MAXIMIUMTRAVELTIME_KEY);
		} else {
			MaximumTravelTime = null;
		}

		// Branches
		if (newJSONObject.containsKey(BRANCHES_KEY)) {

			Branches = new ArrayList<TravelTimePlotDataBranch>();

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
					Branches.add(new TravelTimePlotDataBranch(branchesObject));

				}
			}
		} else {
			Branches = null;
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

		reload(sourceObject.MaximumTravelTime, sourceObject.Branches);
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

		Type = "TTPlotData";
		MaximumTravelTime = newMaximumTravelTime;
		Branches = newBranches;

	}

	/**
	 * Converts the contents of the class to a  object
	 * 
	 * @return Returns a JSONObject containing the class contents
	 */
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {

		JSONObject newJSONObject = new JSONObject();

		// Type
		if (Type != null) {
			newJSONObject.put(TYPE_KEY, Type);
		}		
		
		// phase
		if (MaximumTravelTime != null) {
			newJSONObject.put(MAXIMIUMTRAVELTIME_KEY, MaximumTravelTime);
		}

		// Branches
		if ((Branches != null) && (!Branches.isEmpty())) {

			JSONArray branchesArray = new JSONArray();

			// enumerate through the whole arraylist
			for (Iterator<TravelTimePlotDataBranch> branchesIterator = Branches
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

		// Type
		if (Type == null) {
			// Type not found
			errorList.add("No Type in TravelTimePlotData Class.");
		} else if (Type.isEmpty()) {
			// Type empty
			errorList.add("Empty Type in TravelTimePlotData Class.");
		} else if (!Type.equals("TTPlotData")) {
			// wrong Type
			errorList.add("Non-TTPlotData Type in TravelTimePlotData Class.");
		}
		
		// MaximumTravelTime
		if (MaximumTravelTime == null) {
			// MaximumTravelTime not found
			errorList
					.add("No Maximum Travel Time in TravelTimePlotData Class.");
		}

		// Branches
		if ((Branches != null) && (!Branches.isEmpty())) {

			// enumerate through the whole arraylist
			for (Iterator<TravelTimePlotDataBranch> branchesIterator = Branches
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

	
}
