package gov.usgs.processingformats;

import java.util.ArrayList;
import org.json.simple.*;

/**
 * a conversion class used to create, parse, and validate site data as part of processing data.
 *
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class Site implements ProcessingInt {
  /** JSON Keys */
  public static final String STATION_KEY = "Station";

  public static final String CHANNEL_KEY = "Channel";
  public static final String NETWORK_KEY = "Network";
  public static final String LOCATION_KEY = "Location";
  public static final String LATITUDE_KEY = "Latitude";
  public static final String LONGITUDE_KEY = "Longitude";
  public static final String ELEVATION_KEY = "Elevation";

  /** Required Station code. */
  public String Station;

  /** Optional Network/component code. */
  public String Channel;

  /** Required Network code. */
  public String Network;

  /** Optional Location code. */
  public String Location;

  /** Required Double containing the Latitude in decimal degrees */
  public Double Latitude;

  /** Required Double containing the Longitude in decimal degrees */
  public Double Longitude;

  /** Required Double containing the Elevation in meters */
  public Double Elevation;

  /** The constructor for the Site class. Initializes members to null values. */
  public Site() {
    Station = null;
    Channel = null;
    Network = null;
    Location = null;
    Latitude = null;
    Longitude = null;
    Elevation = null;
  }

  /**
   * The alternate advanced constructor for the Site class. Initializes members to provided values.
   *
   * @param newStation - A String containing the Station to use
   * @param newChannel - A String containing the Channel to use (null omit)
   * @param newNetwork - A String containing the Network to use
   * @param newLocation - A String containing the Location to use (null omit)
   * @param newLatitude - A Double containing the Latitude in degrees to use
   * @param newLongitude - A Double containing the Longitude in degrees to use
   * @param newElevation - A Double containing the Elevation in meters to use
   */
  public Site(
      String newStation,
      String newChannel,
      String newNetwork,
      String newLocation,
      Double newLatitude,
      Double newLongitude,
      Double newElevation) {

    reload(
        newStation, newChannel, newNetwork, newLocation, newLatitude, newLongitude, newElevation);
  }

  /**
   * Reload Function
   *
   * <p>The reload function for the site class. Initializes members to provided values.
   *
   * @param newStation - A String containing the Station to use
   * @param newChannel - A String containing the Channel to use (null omit)
   * @param newNetwork - A String containing the Network to use
   * @param newLocation - A String containing the Location to use (null omit)
   * @param newLatitude - A Double containing the Latitude in degrees to use
   * @param newLongitude - A Double containing the Longitude in degrees to use
   * @param newElevation - A Double containing the Elevation in meters to use
   */
  public void reload(
      String newStation,
      String newChannel,
      String newNetwork,
      String newLocation,
      Double newLatitude,
      Double newLongitude,
      Double newElevation) {
    Station = newStation;
    Channel = newChannel;
    Network = newNetwork;
    Location = newLocation;
    Latitude = newLatitude;
    Longitude = newLongitude;
    Elevation = newElevation;
  }

  /**
   * Constructs the class from a JSONObject, populating members
   *
   * @param newJSONObject - A JSONObject.
   */
  public Site(JSONObject newJSONObject) {
    // required values
    // Station
    if (newJSONObject.containsKey(STATION_KEY)) {
      Station = newJSONObject.get(STATION_KEY).toString();
    } else {
      Station = null;
    }

    // Network
    if (newJSONObject.containsKey(NETWORK_KEY)) {
      Network = newJSONObject.get(NETWORK_KEY).toString();
    } else {
      Network = null;
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

    // Elevation
    if (newJSONObject.containsKey(ELEVATION_KEY)) {
      Elevation = (double) newJSONObject.get(ELEVATION_KEY);
    } else {
      Elevation = null;
    }

    // optional values
    // Channel
    if (newJSONObject.containsKey(CHANNEL_KEY)) {
      Channel = newJSONObject.get(CHANNEL_KEY).toString();
    } else {
      Channel = null;
    }

    // Location
    if (newJSONObject.containsKey(LOCATION_KEY)) {
      Location = newJSONObject.get(LOCATION_KEY).toString();
    } else {
      Location = null;
    }
  }

  /**
   * Converts the contents of the class to a JSONObject
   *
   * @return Returns a JSONObject containing the class contents
   */
  @SuppressWarnings("unchecked")
  public JSONObject toJSON() {
    JSONObject NewJSONObject = new JSONObject();

    // required values
    // Station
    if ((Station != null) && (!Station.isEmpty())) {
      NewJSONObject.put(STATION_KEY, Station);
    }

    // Network
    if ((Network != null) && (!Network.isEmpty())) {
      NewJSONObject.put(NETWORK_KEY, Network);
    }

    // Latitude
    if (Latitude != null) {
      NewJSONObject.put(LATITUDE_KEY, Latitude);
    }

    // Longitude
    if (Longitude != null) {
      NewJSONObject.put(LONGITUDE_KEY, Longitude);
    }

    // Elevation
    if (Elevation != null) {
      NewJSONObject.put(ELEVATION_KEY, Elevation);
    }

    // optional values
    // Channel
    if ((Channel != null) && (!Channel.isEmpty())) {
      NewJSONObject.put(CHANNEL_KEY, Channel);
    }

    // Location
    if ((Location != null) && (!Location.isEmpty())) {
      NewJSONObject.put(LOCATION_KEY, Location);
    }

    // return valid object
    return (NewJSONObject);
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
    // Station
    if (Station == null) {
      // Station not found
      errorList.add("No Station in Site Class.");
    } else if (Station.isEmpty()) {
      // Station empty
      errorList.add("Empty Station in Site Class.");
    }

    // Network
    if (Network == null) {
      // Network not found
      errorList.add("No Network in Site Class.");
    } else if (Network.isEmpty()) {
      // Network empty
      errorList.add("Empty Network in Site Class.");
    }

    // Latitude
    if (Latitude == null) {
      // Latitude not found
      errorList.add("No Latitude in Site Class.");
    } else if ((Latitude < -90.0) || (Latitude > 90.0)) {
      // invalid Latitude
      errorList.add("Latitude in Site Class not in the range of -90 to 90.");
    }

    // Longitude
    if (Longitude == null) {
      // Longitude not found
      errorList.add("No Longitude in Site Class.");
    } else if ((Longitude < -180.0) || (Longitude > 180.0)) {
      // invalid Longitude
      errorList.add("Longitude in Site Class not in the range of -180 to 180.");
    }

    // Elevation
    if (Elevation == null) {
      // Elevation not found
      errorList.add("No Elevation in Site Class.");
    }

    // since Station, Channel, Network, and Location are free text
    // strings, no further validation is required. Channel and Location
    // are also optional.
    // NOTE: Further validation COULD be done to confirm that values matched
    // seed standards.

    // success
    return (errorList);
  }
}
