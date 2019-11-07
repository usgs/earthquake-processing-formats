package gov.usgs.processingformats;

import java.util.ArrayList;
import java.util.Date;
import org.json.simple.JSONObject;

/**
 * a conversion class used to create, parse, and validate pick processing data
 *
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class Pick implements ProcessingInt {

  /** JSON Keys */
  public static final String ID_KEY = "ID";

  public static final String SITE_KEY = "Site";
  public static final String SOURCE_KEY = "Source";
  public static final String TIME_KEY = "Time";
  public static final String AFFINITY_KEY = "Affinity";
  public static final String QUALITY_KEY = "Quality";
  public static final String USE_KEY = "Use";
  public static final String PICKED_PHASE_KEY = "PickedPhase";
  public static final String ASSOCIATED_PHASE_KEY = "AssociatedPhase";
  public static final String LOCATED_PHASE_KEY = "LocatedPhase";
  public static final String RESIDUAL_KEY = "Residual";
  public static final String DISTANCE_KEY = "Distance";
  public static final String AZIMUTH_KEY = "Azimuth";
  public static final String WEIGHT_KEY = "Weight";
  public static final String IMPORTANCE_KEY = "Importance";

  /** Required unique identifier for this Pick */
  public String ID;

  /** Required Site. */
  public Site Site;

  /** Required Source. */
  public Source Source;

  /** Required Time for this Pick */
  public Date Time;

  /** Required double containing the Affinity */
  public Double Affinity;

  /** Required double containing the Quality */
  public Double Quality;

  /** Required boolean containing the Use flag */
  public Boolean Use;

  /** Required String containing the picked phase */
  public String PickedPhase;

  /** Required String containing the associated phase */
  public String AssociatedPhase;

  /** Optional (output) String containing the located phase */
  public String LocatedPhase;

  /** Optional (output) double containing the Residual */
  public Double Residual;

  /** Optional (output) double containing the Distance */
  public Double Distance;

  /** Optional (output) double containing the Azimuth */
  public Double Azimuth;

  /** Optional (output) double containing the Weight */
  public Double Weight;

  /** Optional (output) double containing the Importance */
  public Double Importance;

  /** The constructor for the Pick class. Initializes members to null values. */
  public Pick() {

    ID = null;
    Site = null;
    Source = null;
    Time = null;
    Affinity = null;
    Quality = null;
    Use = null;
    PickedPhase = null;
    AssociatedPhase = null;
    LocatedPhase = null;
    Residual = null;
    Distance = null;
    Azimuth = null;
    Weight = null;
    Importance = null;
  }

  /**
   * The advanced constructor for the Pick class. Initializes members to provided values.
   *
   * @param newID - A String containing the ID to Use
   * @param newStation - A String containing the station to Use
   * @param newChannel - A String containing the channel to Use
   * @param newNetwork - A String containing the network to Use
   * @param newLocation - A String containing the location to Use
   * @param newLatitude - A Double containing the latitude to Use
   * @param newLongitude - A Double containing the longitude to Use
   * @param newElevation - A Double containing the elevation to Use
   * @param newAgencyID - A String containing the agencyid to Use
   * @param newAuthor - A String containing the author to Use
   * @param newType - A String containing the type to Use
   * @param newTime - A Date containing the Time to Use
   * @param newAffinity - A Double containing the Affinity to Use
   * @param newQuality - A Double containing the Quality to Use
   * @param newUse - A Boolean containing the Use flag to Use
   * @param newPickedPhase - A String containing the picked phase to Use
   * @param newAssociatedPhase - A String containing the associated phase to Use
   * @param newLocatedPhase - A String containing the located phase to Use, empty string to omit
   * @param newResidual - A Double containing the Residual to Use, null to omit
   * @param newDistance - A Double containing the Distance to Use, null to omit
   * @param newAzimuth - A Double containing the Azimuth to Use, null to omit
   * @param newWeight - A Double containing the Weight to Use, null to omit
   * @param newImportance - A Double containing the Importance to Use, null to omit
   */
  public Pick(
      String newID,
      String newStation,
      String newChannel,
      String newNetwork,
      String newLocation,
      Double newLatitude,
      Double newLongitude,
      Double newElevation,
      String newAgencyID,
      String newAuthor,
      String newType,
      Date newTime,
      Double newAffinity,
      Double newQuality,
      Boolean newUse,
      String newPickedPhase,
      String newAssociatedPhase,
      String newLocatedPhase,
      Double newResidual,
      Double newDistance,
      Double newAzimuth,
      Double newWeight,
      Double newImportance) {

    reload(
        newID,
        new Site(
            newStation,
            newChannel,
            newNetwork,
            newLocation,
            newLatitude,
            newLongitude,
            newElevation),
        new Source(newAgencyID, newAuthor, newType),
        newTime,
        newAffinity,
        newQuality,
        newUse,
        newPickedPhase,
        newAssociatedPhase,
        newLocatedPhase,
        newResidual,
        newDistance,
        newAzimuth,
        newWeight,
        newImportance);
  }

  /**
   * The alternate advanced constructor for the Pick class. Initializes members to provided values.
   *
   * @param newID - A String containing the ID to Use
   * @param newSite - A Site containing the Site to Use
   * @param newSource - A Source containing the Source to Use
   * @param newTime - A Date containing the Time to Use
   * @param newAffinity - A Double containing the Affinity to Use
   * @param newQuality - A Double containing the Quality to Use
   * @param newUse - A Boolean containing the Use flag to Use
   * @param newPickedPhase - A String containing the picked phase to Use
   * @param newAssociatedPhase - A String containing the associated phase to Use
   * @param newLocatedPhase - A String containing the located phase to Use, empty string to omit
   * @param newResidual - A Double containing the Residual to Use, null to omit
   * @param newDistance - A Double containing the Distance to Use, null to omit
   * @param newAzimuth - A Double containing the Azimuth to Use, null to omit
   * @param newWeight - A Double containing the Weight to Use, null to omit
   * @param newImportance - A Double containing the Importance to Use, null to omit
   */
  public Pick(
      String newID,
      Site newSite,
      Source newSource,
      Date newTime,
      Double newAffinity,
      Double newQuality,
      Boolean newUse,
      String newPickedPhase,
      String newAssociatedPhase,
      String newLocatedPhase,
      Double newResidual,
      Double newDistance,
      Double newAzimuth,
      Double newWeight,
      Double newImportance) {

    reload(
        newID,
        newSite,
        newSource,
        newTime,
        newAffinity,
        newQuality,
        newUse,
        newPickedPhase,
        newAssociatedPhase,
        newLocatedPhase,
        newResidual,
        newDistance,
        newAzimuth,
        newWeight,
        newImportance);
  }

  /**
   * Reload Function
   *
   * <p>The reload function for the error ellipse class. Initializes members to provided values.
   *
   * @param newID - A String containing the ID to Use
   * @param newSite - A Site containing the Site to Use
   * @param newSource - A Source containing the Source to Use
   * @param newTime - A Date containing the Time to Use
   * @param newAffinity - A Double containing the Affinity to Use
   * @param newQuality - A Double containing the Quality to Use
   * @param newUse - A Boolean containing the Use flag to Use
   * @param newPickedPhase - A String containing the picked phase to Use
   * @param newAssociatedPhase - A String containing the associated phase to Use
   * @param newLocatedPhase - A String containing the located phase to Use, empty string to omit
   * @param newResidual - A Double containing the Residual to Use, null to omit
   * @param newDistance - A Double containing the Distance to Use, null to omit
   * @param newAzimuth - A Double containing the Azimuth to Use, null to omit
   * @param newWeight - A Double containing the Weight to Use, null to omit
   * @param newImportance - A Double containing the Importance to Use, null to omit
   */
  public void reload(
      String newID,
      Site newSite,
      Source newSource,
      Date newTime,
      Double newAffinity,
      Double newQuality,
      Boolean newUse,
      String newPickedPhase,
      String newAssociatedPhase,
      String newLocatedPhase,
      Double newResidual,
      Double newDistance,
      Double newAzimuth,
      Double newWeight,
      Double newImportance) {

    ID = newID;
    Site = newSite;
    Time = newTime;
    Source = newSource;
    Affinity = newAffinity;
    Quality = newQuality;
    Use = newUse;
    PickedPhase = newPickedPhase;
    AssociatedPhase = newAssociatedPhase;
    LocatedPhase = newLocatedPhase;
    Residual = newResidual;
    Distance = newDistance;
    Azimuth = newAzimuth;
    Weight = newWeight;
    Importance = newImportance;
  }

  /**
   * Constructs the class from a JSONObject, populating members
   *
   * @param newJSONObject - A JSONObject.
   */
  public Pick(JSONObject newJSONObject) {

    // Required values
    // ID
    if (newJSONObject.containsKey(ID_KEY)) {
      ID = newJSONObject.get(ID_KEY).toString();
    } else {
      ID = null;
    }

    // Site
    if (newJSONObject.containsKey(SITE_KEY)) {
      Site = new Site((JSONObject) newJSONObject.get(SITE_KEY));
    } else {
      Site = null;
    }

    // Source
    if (newJSONObject.containsKey(SOURCE_KEY)) {
      Source = new Source((JSONObject) newJSONObject.get(SOURCE_KEY));
    } else {
      Source = null;
    }

    // Time
    if (newJSONObject.containsKey(TIME_KEY)) {
      Time = Utility.getDate(newJSONObject.get(TIME_KEY).toString());
    } else {
      Time = null;
    }

    // Affinity
    if (newJSONObject.containsKey(AFFINITY_KEY)) {
      Affinity = Double.valueOf(newJSONObject.get(AFFINITY_KEY).toString());
    } else {
      Affinity = null;
    }

    // Quality
    if (newJSONObject.containsKey(QUALITY_KEY)) {
      Quality = Double.valueOf(newJSONObject.get(QUALITY_KEY).toString());
    } else {
      Quality = null;
    }

    // Use
    if (newJSONObject.containsKey(USE_KEY)) {
      Use = Boolean.valueOf(newJSONObject.get(USE_KEY).toString());
    } else {
      Use = null;
    }

    // PickedPhase
    if (newJSONObject.containsKey(PICKED_PHASE_KEY)) {
      PickedPhase = newJSONObject.get(PICKED_PHASE_KEY).toString();
    } else {
      PickedPhase = null;
    }

    // AssociatedPhase
    if (newJSONObject.containsKey(ASSOCIATED_PHASE_KEY)) {
      AssociatedPhase = newJSONObject.get(ASSOCIATED_PHASE_KEY).toString();
    } else {
      AssociatedPhase = null;
    }

    // Optional (output) values
    // LocatedPhase
    if (newJSONObject.containsKey(LOCATED_PHASE_KEY)) {
      LocatedPhase = newJSONObject.get(LOCATED_PHASE_KEY).toString();
    } else {
      LocatedPhase = null;
    }

    // Residual
    if (newJSONObject.containsKey(RESIDUAL_KEY)) {
      Residual = Double.valueOf(newJSONObject.get(RESIDUAL_KEY).toString());
    } else {
      Residual = null;
    }

    // Distance
    if (newJSONObject.containsKey(DISTANCE_KEY)) {
      Distance = Double.valueOf(newJSONObject.get(DISTANCE_KEY).toString());
    } else {
      Distance = null;
    }

    // Azimuth
    if (newJSONObject.containsKey(AZIMUTH_KEY)) {
      Azimuth = Double.valueOf(newJSONObject.get(AZIMUTH_KEY).toString());
    } else {
      Azimuth = null;
    }

    // Weight
    if (newJSONObject.containsKey(WEIGHT_KEY)) {
      Weight = Double.valueOf(newJSONObject.get(WEIGHT_KEY).toString());
    } else {
      Weight = null;
    }

    // Importance
    if (newJSONObject.containsKey(IMPORTANCE_KEY)) {
      Importance = Double.valueOf(newJSONObject.get(IMPORTANCE_KEY).toString());
    } else {
      Importance = null;
    }
  }

  /**
   * Converts the contents of the class to a object Overridden from ProcessingBase.
   *
   * @return Returns a JSONObject containing the class contents
   */
  @SuppressWarnings("unchecked")
  public JSONObject toJSON() {

    JSONObject newJSONObject = new JSONObject();

    // Required values
    // ID
    if (ID != null) {
      newJSONObject.put(ID_KEY, ID);
    }

    // Site
    if (Site != null) {
      newJSONObject.put(SITE_KEY, Site.toJSON());
    }

    // Source
    if (Source != null) {
      newJSONObject.put(SOURCE_KEY, Source.toJSON());
    }

    // Time
    if (Time != null) {
      newJSONObject.put(TIME_KEY, Utility.formatDate(Time));
    }

    // Affinity
    if (Affinity != null) {
      newJSONObject.put(AFFINITY_KEY, Affinity);
    }

    // Quality
    if (Quality != null) {
      newJSONObject.put(QUALITY_KEY, Quality);
    }

    // Use
    if (Use != null) {
      newJSONObject.put(USE_KEY, Use);
    }

    // PickedPhase
    if (PickedPhase != null) {
      newJSONObject.put(PICKED_PHASE_KEY, PickedPhase);
    }

    // asociatedPhase
    if (AssociatedPhase != null) {
      newJSONObject.put(ASSOCIATED_PHASE_KEY, AssociatedPhase);
    }

    // Optional (output) values
    // LocatedPhase
    if (LocatedPhase != null) {
      newJSONObject.put(LOCATED_PHASE_KEY, LocatedPhase);
    }

    // Residual
    if (Residual != null) {
      newJSONObject.put(RESIDUAL_KEY, Residual);
    }

    // Distance
    if (Distance != null) {
      newJSONObject.put(DISTANCE_KEY, Distance);
    }

    // Azimuth
    if (Azimuth != null) {
      newJSONObject.put(AZIMUTH_KEY, Azimuth);
    }

    // Weight
    if (Weight != null) {
      newJSONObject.put(WEIGHT_KEY, Weight);
    }

    // Importance
    if (Importance != null) {
      newJSONObject.put(IMPORTANCE_KEY, Importance);
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

    // Required Keys
    // ID
    if (ID == null) {
      // ID not found
      errorList.add("No ID in Pick Class.");
    } else if (ID.isEmpty()) {
      // ID empty
      errorList.add("Empty ID in Pick Class.");
    }

    // Site
    if (Site == null) {
      // Site not found
      errorList.add("No Site in Pick Class.");
    } else if (!Site.isValid()) {
      // Site invalid
      errorList.add("Invalid Site in Pick Class.");
    }

    // Source
    if (Source == null) {
      // Source not found
      errorList.add("No Source in Pick Class.");
    } else if (!Source.isValid()) {
      // Source invalid
      errorList.add("Invalid Source in Pick Class.");
    }

    // Time
    if (Time == null) {
      // Time not found
      errorList.add("No Time in Pick Class.");
    }

    // Affinity
    if (Affinity == null) {
      // Affinity not found
      errorList.add("No Affinity in Pick Class.");
    }

    // Quality
    if (Quality == null) {
      // Quality not found
      errorList.add("No Quality in Pick Class.");
    }

    // Use
    if (Use == null) {
      // Use not found
      errorList.add("No Use in Pick Class.");
    }

    // Optional Keys
    // PickedPhase
    if (PickedPhase != null) {
      if (PickedPhase.isEmpty()) {
        // PickedPhase empty
        errorList.add("Empty Picked Phase in Pick Class.");
      }
    }

    // AssociatedPhase
    if (AssociatedPhase != null) {
      if (AssociatedPhase.isEmpty()) {
        // AssociatedPhase empty
        errorList.add("Empty Associated Phase in Pick Class.");
      }
    }

    // LocatedPhase
    if (LocatedPhase != null) {
      if (LocatedPhase.isEmpty()) {
        // LocatedPhase empty
        errorList.add("Empty Located Phase in Pick Class.");
      }
    }

    // Currently no validation criteria for other optional (output) values

    // success
    return (errorList);
  }
}
