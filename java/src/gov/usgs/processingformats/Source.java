package gov.usgs.processingformats;

import java.util.ArrayList;

import org.json.simple.JSONObject;

/**
 * a conversion class used to create, parse, and validate source data as part of
 * processing data.
 *
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class Source implements ProcessingInt {

	/**
	 * JSON Keys
	 */
	public static final String AGENCYID_KEY = "AgencyID";
	public static final String AUTHOR_KEY = "Author";
	public static final String TYPE_KEY = "Type";

	/**
	 * Required Agency identifier
	 */
	private String agencyID;

	/**
	 * Required Author identifier
	 */
	private String author;

	/**
	 * Required Type identifier
	 */
	private String type;

	/**
	 * The constructor for the Source class. Initializes members to null values.
	 */
	public Source() {

		agencyID = null;
		author = null;
		type = null;
	}

	/**
	 * The advanced constructor for the Source class. Initializes members to
	 * provided values.
	 *
	 * @param newAgencyID
	 *            - A String containing the agencyID to use
	 * @param newAuthor
	 *            - A String containing the author to use
	 * @param newType
	 *            - A String containing the type to use
	 */
	public Source(String newAgencyID, String newAuthor, String newType) {

		agencyID = newAgencyID;
		author = newAuthor;
		type = newType;
	}

	/**
	 * Constructs the class from a JSONObject, populating members
	 *
	 * @param newJSONObject
	 *            - A JSONObject.
	 */
	public Source(JSONObject newJSONObject) {

		// required values
		// agencyID
		if (newJSONObject.containsKey(AGENCYID_KEY)) {
			agencyID = newJSONObject.get(AGENCYID_KEY).toString();
		} else {
			agencyID = null;
		}

		// author
		if (newJSONObject.containsKey(AUTHOR_KEY)) {
			author = newJSONObject.get(AUTHOR_KEY).toString();
		} else {
			author = null;
		}

		// type
		if (newJSONObject.containsKey(TYPE_KEY)) {
			type = newJSONObject.get(TYPE_KEY).toString();
		} else {
			type = null;
		}

	}

	/**
	 * Converts the contents of the class to a JSONObject
	 *
	 * @return Returns a JSONObject containing the class contents
	 */
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {

		JSONObject newJSONObject = new JSONObject();

		String jsonAgencyID = getAgencyID();
		String jsonAuthor = getAuthor();
		String jsonType = getType();

		// required values
		// agencyID
		if ((jsonAgencyID != null) && (!jsonAgencyID.isEmpty())) {
			newJSONObject.put(AGENCYID_KEY, jsonAgencyID);
		}

		// author
		if ((jsonAuthor != null) && (!jsonAuthor.isEmpty())) {
			newJSONObject.put(AUTHOR_KEY, jsonAuthor);
		}

		// type
		if ((jsonType != null) && (!jsonType.isEmpty())) {
			newJSONObject.put(TYPE_KEY, jsonType);
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

		String jsonAgencyID = getAgencyID();
		String jsonAuthor = getAuthor();
		String jsonType = getType();

		ArrayList<String> errorList = new ArrayList<String>();

		// check for required keys
		// agencyID
		if (jsonAgencyID == null) {
			// agencyID not found
			errorList.add("No AgencyID in Source Class.");
		} else if (jsonAgencyID.isEmpty()) {
			// agencyID empty
			errorList.add("Empty AgencyID in Source Class.");
		}

		// author
		if (jsonAuthor == null) {
			// author not found
			errorList.add("No Author in Source Class.");
		} else if (jsonAuthor.isEmpty()) {
			// author empty
			errorList.add("Empty Author in Source Class.");
		}

		// type
		if (jsonType == null) {
			// type not found
			errorList.add("No Type in Source Class.");
		} else if (jsonType.isEmpty()) {
			// type empty
			errorList.add("Empty Type in Source Class.");
		} else {
			boolean match = false;
			if (jsonType.equals("Unknown")) {
				match = true;
			} else if (jsonType.equals("LocalHuman")) {
				match = true;
			} else if (jsonType.equals("LocalAutomatic")) {
				match = true;
			} else if (jsonType.equals("ContributedHuman")) {
				match = true;
			} else if (jsonType.equals("ContributedAutomatic")) {
				match = true;
			} else {
				match = false;
			}

			if (!match) {
				// invalid type
				errorList.add("Invalid Type in Source Class.");
			}
		}

		// success
		return (errorList);
	}

	/**
	 * @return the agencyID
	 */
	public String getAgencyID() {
		return agencyID;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
}
