package gov.usgs.processingformats;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * a conversion class used to create, parse, and validate travel time requests
 *
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class TravelTimeReciever implements ProcessingInt {

  /** JSON Keys */
  public static final String TYPE_KEY = "Type";

  public static final String ID_KEY = "ID";
  public static final String DISTANCE_KEY = "Distance";
  public static final String ELEVATION_KEY = "Elevation";
  public static final String LATITUDE_KEY = "Latitude";
  public static final String LONGITUDE_KEY = "Longitude";
  public static final String BRANCHES_KEY = "Branches";

  /** Required reciver ID */
  public String ID;

  /** Required geographic Distance reciever distance relative to the source in degrees */
  public Double Distance;

  /** Required geographic reciever elevation relative to the WGS84 datum in meters */
  public Double Elevation;

  /** Optional geographic reciever Latitude in degrees */
  public Double Latitude;

  /** Optional geographic reciever Longitude in degrees */
  public Double Longitude;

  /** Returned travel time Branches (empty for requests) */
  public ArrayList<TravelTimeData> Branches;

  /** The constructor for the TravelTimeData class. Initializes members to null values. */
  public TravelTimeReciever() {

    reload(null, null, null, null, null, null);
  }

  /**
   * Advanced constructor
   *
   * <p>The advanced constructor for the TravelTimeReciever class. Initializes members to provided
   * values.
   *
   * @param newID - A required string containing the reciever id
   * @param newDistance - A required Double containing the geographic reciever distance relative to
   *     the source in degrees
   * @param newElevation - A required Double containing the geographic reciever Elevation relative
   *     to the WGS84 datum in meters
   * @param newLatitude - An optional Double containing the geographic reciever Latitude in degrees,
   *     null to omit
   * @param newLongitude - An optional Double containing the geographic reciever Longitude in
   *     degrees, null to omit
   * @param newBranches - A ArrayList&lt;TravelTimeData&gt; containing the returned travel time
   *     Branches
   */
  public TravelTimeReciever(
      String newID,
      Double newDistance,
      Double newElevation,
      Double newLatitude,
      Double newLongitude,
      ArrayList<TravelTimeData> newBranches) {

    reload(newID, newDistance, newElevation, newLatitude, newLongitude, newBranches);
  }

  /**
   * Constructs the class from a JSONObject, populating members
   *
   * @param newJSONObject - A JSONObject.
   */
  public TravelTimeReciever(JSONObject newJSONObject) {

    // Required values
    // ID
    if (newJSONObject.containsKey(ID_KEY)) {
      ID = newJSONObject.get(ID_KEY).toString();
    } else {
      ID = null;
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

    // Branches
    if (newJSONObject.containsKey(BRANCHES_KEY)) {

      Branches = new ArrayList<TravelTimeData>();

      // get the array
      JSONArray BranchesArray = (JSONArray) newJSONObject.get(BRANCHES_KEY);

      if ((BranchesArray != null) && (!BranchesArray.isEmpty())) {

        // go through the whole array
        for (int i = 0; i < BranchesArray.size(); i++) {

          // get the object
          JSONObject DataObject = (JSONObject) BranchesArray.get(i);

          // check for Type
          if (DataObject.containsKey(TYPE_KEY)) {

            // Route based on Type
            String TypeString = (String) DataObject.get(TYPE_KEY);
            if (TypeString.equals("TTData")) {

              // add to vector
              Branches.add(new TravelTimeData(DataObject));
            }
          }
        }
      }
    } else {
      Branches = null;
    }
  }

  /**
   * Constructs the class from a TravelTimeData object, populating members (copy constructor)
   *
   * @param sourceObject - A TravelTimeData object.
   */
  public TravelTimeReciever(TravelTimeReciever sourceObject) {
    reload(
        sourceObject.ID,
        sourceObject.Distance,
        sourceObject.Elevation,
        sourceObject.Latitude,
        sourceObject.Longitude,
        sourceObject.Branches);
  }

  /**
   * Reload Function
   *
   * <p>The reload function for the TravelTimeData class. Initializes members to provided values.
   *
   * @param newID - A required string containing the reciever id
   * @param newDistance - A required Double containing the geographic reciever distance relative to
   *     the source in degrees
   * @param newElevation - A required Double containing the geographic reciever Elevation relative
   *     to the WGS84 datum in meters
   * @param newLatitude - An optional Double containing the geographic reciever Latitude in degrees,
   *     null to omit
   * @param newLongitude - An optional Double containing the geographic reciever Longitude in
   *     degrees, null to omit
   * @param newBranches - A ArrayList&lt;TravelTimeData&gt; containing the returned travel time
   *     Branches
   */
  public void reload(
      String newID,
      Double newDistance,
      Double newElevation,
      Double newLatitude,
      Double newLongitude,
      ArrayList<TravelTimeData> newBranches) {

    ID = newID;
    Distance = newDistance;
    Elevation = newElevation;
    Latitude = newLatitude;
    Longitude = newLongitude;
    Branches = newBranches;
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
    // ID
    if (ID != null) {
      newJSONObject.put(ID_KEY, ID);
    }

    // Distance
    if (Distance != null) {
      newJSONObject.put(DISTANCE_KEY, Distance);
    }

    // Elevation
    if (Elevation != null) {
      newJSONObject.put(ELEVATION_KEY, Elevation);
    }

    // Latitude
    if (Latitude != null) {
      newJSONObject.put(LATITUDE_KEY, Latitude);
    }

    // Longitude
    if (Longitude != null) {
      newJSONObject.put(LONGITUDE_KEY, Longitude);
    }

    // returned Branches
    if (Branches != null) {
      JSONArray BranchesArray = new JSONArray();
      // enumerate through the whole arraylist
      for (Iterator<TravelTimeData> DataIterator = Branches.iterator(); DataIterator.hasNext(); ) {

        // convert pick to JSON object
        JSONObject DataObject = ((TravelTimeData) DataIterator.next()).toJSON();

        BranchesArray.add(DataObject);
      }

      if (!BranchesArray.isEmpty()) {
        newJSONObject.put(BRANCHES_KEY, BranchesArray);
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

    // required values
    // ID
    if (ID == null) {
      // ID not found
      errorList.add("No ID in TravelTimeReciever Class.");
    } else if (ID.isEmpty()) {
      // ID empty
      errorList.add("Empty ID in TravelTimeReciever Class.");
    }

    // Distance / Lat Lon check, need one or the other
    if ((Distance == null) && ((Latitude == null) || (Longitude == null))) {
      // Distance not found
      errorList.add("No Distance or Latitude/Longitude in TravelTimeReciever Class.");
    }

    // Distance
    if (Distance != null) {
      if ((Distance < 0) || (Distance > 360)) {
        // invalid Distance
        errorList.add("Distance in TravelTimeReciever not in the range of 0 to 360.");
      }
    }

    // Elevation
    if (Elevation == null) {
      // Elevation not found
      errorList.add("No Elevation in TravelTimeReciever Class.");
    } else if ((Elevation < -100) || (Elevation > 1000)) {
      // invalid Elevation
      errorList.add("Elevation in TravelTimeReciever not in the range of -100 to 1000.");
    }

    // optional values
    // Latitude
    if (Latitude != null) {
      if ((Latitude < -90) || (Latitude > 90)) {
        // invalid Latitude
        errorList.add("Latitude in TravelTimeReciever not in the range of -90 to 90.");
      }
    }

    // Longitude
    if (Longitude != null) {
      if ((Longitude < -180) || (Longitude > 180)) {
        // invalid Longitude
        errorList.add("Longitude in TravelTimeReciever not in the range of -180 to 180.");
      }
    }

    // Branches
    if (Branches != null) {
      // enumerate through the whole arraylist
      for (Iterator<TravelTimeData> DataIterator = Branches.iterator(); DataIterator.hasNext(); ) {

        // convert pick to JSON object
        TravelTimeData TTData = ((TravelTimeData) DataIterator.next());

        if (!TTData.isValid()) {
          errorList.add("Invalid Branch TravelTimeData in TravelTimeReciever.");
          break;
        }
      }
    }

    return (errorList);
  }
}
