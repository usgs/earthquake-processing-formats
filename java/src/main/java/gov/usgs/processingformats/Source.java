package gov.usgs.processingformats;

import java.util.ArrayList;
import org.json.simple.JSONObject;

/**
 * a conversion class used to create, parse, and validate source data as part of processing
 * data. @Author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class Source implements ProcessingInt {

  /** JSON Keys */
  public static final String AGENCYID_KEY = "AgencyID";

  public static final String AUTHOR_KEY = "Author";
  public static final String TYPE_KEY = "Type";

  /** Required Agency identifier string */
  public String AgencyID;

  /** Required Author identifier string */
  public String Author;

  /** Required Type identifier string */
  public String Type;

  /** The constructor for the Source class. Initializes members to null values. */
  public Source() {
    AgencyID = null;
    Author = null;
    Type = null;
  }

  /**
   * The alternate advanced constructor for the Source class. Initializes members to provided
   * values.
   *
   * @param newAgencyID - A String containing the agencyID to use
   * @param newAuthor - A String containing the Author to use
   * @param newType - A String containing the Type to use
   */
  public Source(String newAgencyID, String newAuthor, String newType) {

    reload(newAgencyID, newAuthor, newType);
  }

  /**
   * Reload Function
   *
   * <p>The reload function for the source class. Initializes members to provided values.
   *
   * @param newAgencyID - A String containing the agencyID to use
   * @param newAuthor - A String containing the Author to use
   * @param newType - A String containing the Type to use
   */
  public void reload(String newAgencyID, String newAuthor, String newType) {

    AgencyID = newAgencyID;
    Author = newAuthor;
    Type = newType;
  }

  /**
   * Constructs the class from a JSONObject, populating members
   *
   * @param newJSONObject - A JSONObject.
   */
  public Source(JSONObject newJSONObject) {

    // required values
    // agencyID
    if (newJSONObject.containsKey(AGENCYID_KEY)) {
      AgencyID = newJSONObject.get(AGENCYID_KEY).toString();
    } else {
      AgencyID = null;
    }

    // Author
    if (newJSONObject.containsKey(AUTHOR_KEY)) {
      Author = newJSONObject.get(AUTHOR_KEY).toString();
    } else {
      Author = null;
    }

    // Type
    if (newJSONObject.containsKey(TYPE_KEY)) {
      Type = newJSONObject.get(TYPE_KEY).toString();
    } else {
      Type = null;
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

    // required values
    // agencyID
    if ((AgencyID != null) && (!AgencyID.isEmpty())) {
      newJSONObject.put(AGENCYID_KEY, AgencyID);
    }

    // Author
    if ((Author != null) && (!Author.isEmpty())) {
      newJSONObject.put(AUTHOR_KEY, Author);
    }

    // Type
    if ((Type != null) && (!Type.isEmpty())) {
      newJSONObject.put(TYPE_KEY, Type);
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

    // check for required keys
    // agencyID
    if (AgencyID == null) {
      // agencyID not found
      errorList.add("No AgencyID in Source Class.");
    } else if (AgencyID.isEmpty()) {
      // agencyID empty
      errorList.add("Empty AgencyID in Source Class.");
    }

    // Author
    if (Author == null) {
      // Author not found
      errorList.add("No Author in Source Class.");
    } else if (Author.isEmpty()) {
      // Author empty
      errorList.add("Empty Author in Source Class.");
    }

    // Type
    if (Type == null) {
      // Type not found
      errorList.add("No Type in Source Class.");
    } else if (Type.isEmpty()) {
      // Type empty
      errorList.add("Empty Type in Source Class.");
    } else {
      boolean match = false;
      if (Type.equals("Unknown")) {
        match = true;
      } else if (Type.equals("LocalHuman")) {
        match = true;
      } else if (Type.equals("LocalAutomatic")) {
        match = true;
      } else if (Type.equals("ContributedHuman")) {
        match = true;
      } else if (Type.equals("ContributedAutomatic")) {
        match = true;
      } else {
        match = false;
      }

      if (!match) {
        // invalid Type
        errorList.add("Invalid Type in Source Class.");
      }
    }

    // success
    return (errorList);
  }
}
