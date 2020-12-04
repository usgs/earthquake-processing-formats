package gov.usgs.processingformats;

import java.util.*;
import org.json.simple.JSONObject;

/**
 * a conversion class used to create, parse, and validate travel time request data
 *
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class TravelTimeData implements ProcessingInt {

  /** JSON Keys */
  public static final String PHASE_KEY = "Phase";

  public static final String TRAVELTIME_KEY = "TravelTime";
  public static final String DISTANCEDERIVATIVE_KEY = "DistanceDerivative";
  public static final String DEPTHDERIVATIVE_KEY = "DepthDerivative";
  public static final String RAYDERIVATIVE_KEY = "RayDerivative";
  public static final String STATISTICALSPREAD_KEY = "StatisticalSpread";
  public static final String OBSERVABILITY_KEY = "Observability";
  public static final String TELESEISMICPHASEGROUP_KEY = "TeleseismicPhaseGroup";
  public static final String AUXILIARYPHASEGROUP_KEY = "AuxiliaryPhaseGroup";
  public static final String LOCATIONUSEFLAG_KEY = "LocationUseFlag";
  public static final String ASSOCIATIONWEIGHTFLAG_KEY = "AssociationWeightFlag";

  /** Required seismic Phase code */
  public String Phase;

  /** Required travel time in seconds */
  public Double TravelTime;

  /** Required derivative with respect to distance of the travel time in seconds/degree. */
  public Double DistanceDerivative;

  /** Required derivative with respect to depth of the travel time in seconds/kilometer. */
  public Double DepthDerivative;

  /** Required derivative with respect to ray parameter of the travel time in degrees/second. */
  public Double RayDerivative;

  /** Required observed travel time scatter in seconds. */
  public Double StatisticalSpread;

  /** Required statistical Observability of the seismic Phase. */
  public Double Observability;

  /** Required teleseismic Phase group identifier. */
  public String TeleseismicPhaseGroup;

  /** Required auxiliary Phase group identifier. */
  public String AuxiliaryPhaseGroup;

  /** Required flag indicating whether the Phase may be used in a location. */
  public Boolean LocationUseFlag;

  /** Required flag indicating whether the Phase should be down weighted in association. */
  public Boolean AssociationWeightFlag;

  /** The constructor for the TravelTimeData class. Initializes members to null values. */
  public TravelTimeData() {

    reload(null, null, null, null, null, null, null, null, null, null, null);
  }

  /**
   * Advanced constructor
   *
   * <p>The advanced constructor for the TravelTimeData class. Initializes members to provided
   * values.
   *
   * @param newPhase - A String containing the seismic Phase code
   * @param newTravelTime - A Double containing the travel time in seconds
   * @param newDistanceDerivative - A Double containing the derivative with respect to distance of
   *     the travel time in seconds/degree
   * @param newDepthDerivative - A Double containing the derivative with respect to ray parameter of
   *     the travel time in degrees/second
   * @param newRayDerivative - A Double containing the derivative with respect to ray parameter of
   *     the travel time in degrees/second
   * @param newStatisticalSpread - A Double containing the observed travel time scatter in seconds
   * @param newObservability - A Double containing the statistical Observability of the seismic
   *     Phase
   * @param newTeleseismicPhaseGroup - A String containing the teleseismic Phase group identifier
   * @param newAuxiliaryPhaseGroup - A String containing the auxiliary Phase group identifier
   * @param newLocationUseFlag - A Boolean containing the flag indicating whether the Phase may be
   *     used in a location
   * @param newAssociationWeightFlag - A Boolean containing the flag indicating whether the Phase
   *     should be down weighted in assocation
   */
  public TravelTimeData(
      String newPhase,
      Double newTravelTime,
      Double newDistanceDerivative,
      Double newDepthDerivative,
      Double newRayDerivative,
      Double newStatisticalSpread,
      Double newObservability,
      String newTeleseismicPhaseGroup,
      String newAuxiliaryPhaseGroup,
      Boolean newLocationUseFlag,
      Boolean newAssociationWeightFlag) {

    reload(
        newPhase,
        newTravelTime,
        newDistanceDerivative,
        newDepthDerivative,
        newRayDerivative,
        newStatisticalSpread,
        newObservability,
        newTeleseismicPhaseGroup,
        newAuxiliaryPhaseGroup,
        newLocationUseFlag,
        newAssociationWeightFlag);
  }

  /**
   * Constructs the class from a JSONObject, populating members
   *
   * @param newJSONObject - A JSONObject.
   */
  public TravelTimeData(JSONObject newJSONObject) {

    // Required values
    // Phase
    if (newJSONObject.containsKey(PHASE_KEY)) {
      Phase = newJSONObject.get(PHASE_KEY).toString();
    } else {
      Phase = null;
    }

    // travel time
    if (newJSONObject.containsKey(TRAVELTIME_KEY)) {
      TravelTime = (double) newJSONObject.get(TRAVELTIME_KEY);
    } else {
      TravelTime = null;
    }

    // distance Derivative
    if (newJSONObject.containsKey(DISTANCEDERIVATIVE_KEY)) {
      DistanceDerivative = (double) newJSONObject.get(DISTANCEDERIVATIVE_KEY);
    } else {
      DistanceDerivative = null;
    }

    // depth Derivative
    if (newJSONObject.containsKey(DEPTHDERIVATIVE_KEY)) {
      DepthDerivative = (double) newJSONObject.get(DEPTHDERIVATIVE_KEY);
    } else {
      DepthDerivative = null;
    }

    // ray Derivative
    if (newJSONObject.containsKey(RAYDERIVATIVE_KEY)) {
      RayDerivative = (double) newJSONObject.get(RAYDERIVATIVE_KEY);
    } else {
      RayDerivative = null;
    }

    // statistical spread
    if (newJSONObject.containsKey(STATISTICALSPREAD_KEY)) {
      StatisticalSpread = (double) newJSONObject.get(STATISTICALSPREAD_KEY);
    } else {
      StatisticalSpread = null;
    }

    // Observability
    if (newJSONObject.containsKey(OBSERVABILITY_KEY)) {
      Observability = (double) newJSONObject.get(OBSERVABILITY_KEY);
    } else {
      Observability = null;
    }

    // teleseismic Phase group
    if (newJSONObject.containsKey(TELESEISMICPHASEGROUP_KEY)) {
      TeleseismicPhaseGroup = newJSONObject.get(TELESEISMICPHASEGROUP_KEY).toString();
    } else {
      TeleseismicPhaseGroup = null;
    }

    // auxiliary Phase group
    if (newJSONObject.containsKey(AUXILIARYPHASEGROUP_KEY)) {
      AuxiliaryPhaseGroup = newJSONObject.get(AUXILIARYPHASEGROUP_KEY).toString();
    } else {
      AuxiliaryPhaseGroup = null;
    }

    // location use flag
    if (newJSONObject.containsKey(LOCATIONUSEFLAG_KEY)) {
      LocationUseFlag = (boolean) newJSONObject.get(LOCATIONUSEFLAG_KEY);
    } else {
      LocationUseFlag = null;
    }

    // association weight flag
    if (newJSONObject.containsKey(ASSOCIATIONWEIGHTFLAG_KEY)) {
      AssociationWeightFlag = (boolean) newJSONObject.get(ASSOCIATIONWEIGHTFLAG_KEY);
    } else {
      AssociationWeightFlag = null;
    }
  }

  /**
   * Constructs the class from a TravelTimeData object, populating members (copy constructor)
   *
   * @param sourceObject - A TravelTimeData object.
   */
  public TravelTimeData(TravelTimeData sourceObject) {
    reload(
        sourceObject.Phase,
        sourceObject.TravelTime,
        sourceObject.DistanceDerivative,
        sourceObject.DepthDerivative,
        sourceObject.RayDerivative,
        sourceObject.StatisticalSpread,
        sourceObject.Observability,
        sourceObject.TeleseismicPhaseGroup,
        sourceObject.AuxiliaryPhaseGroup,
        sourceObject.LocationUseFlag,
        sourceObject.AssociationWeightFlag);
  }

  /**
   * Reload Function
   *
   * <p>The reload function for the TravelTimeData class. Initializes members to provided values.
   *
   * @param newPhase - A String containing the seismic Phase code
   * @param newTravelTime - A Double containing the travel time in seconds
   * @param newDistanceDerivative - A Double containing the derivative with respect to distance of
   *     the travel time in seconds/degree
   * @param newDepthDerivative - A Double containing the derivative with respect to ray parameter of
   *     the travel time in degrees/second
   * @param newRayDerivative - A Double containing the derivative with respect to ray parameter of
   *     the travel time in degrees/second
   * @param newStatisticalSpread - A Double containing the observed travel time scatter in seconds
   * @param newObservability - A Double containing the statistical Observability of the seismic
   *     Phase
   * @param newTeleseismicPhaseGroup - A String containing the teleseismic Phase group identifier
   * @param newAuxiliaryPhaseGroup - A String containing the auxiliary Phase group identifier
   * @param newLocationUseFlag - A Boolean containing the flag indicating whether the Phase may be
   *     used in a location
   * @param newAssociationWeightFlag - A Boolean containing the flag indicating whether the Phase
   *     should be down weighted in association
   */
  public void reload(
      String newPhase,
      Double newTravelTime,
      Double newDistanceDerivative,
      Double newDepthDerivative,
      Double newRayDerivative,
      Double newStatisticalSpread,
      Double newObservability,
      String newTeleseismicPhaseGroup,
      String newAuxiliaryPhaseGroup,
      Boolean newLocationUseFlag,
      Boolean newAssociationWeightFlag) {

    Phase = newPhase;
    TravelTime = newTravelTime;
    DistanceDerivative = newDistanceDerivative;
    DepthDerivative = newDepthDerivative;
    RayDerivative = newRayDerivative;
    StatisticalSpread = newStatisticalSpread;
    Observability = newObservability;
    TeleseismicPhaseGroup = newTeleseismicPhaseGroup;
    AuxiliaryPhaseGroup = newAuxiliaryPhaseGroup;
    LocationUseFlag = newLocationUseFlag;
    AssociationWeightFlag = newAssociationWeightFlag;
  }

  /**
   * Converts the contents of the class to a json object
   *
   * @return Returns a JSONObject containing the class contents
   */
  @SuppressWarnings("unchecked")
  public JSONObject toJSON() {

    JSONObject newJSONObject = new JSONObject();

    // Phase
    if (Phase != null) {
      newJSONObject.put(PHASE_KEY, Phase);
    }

    // travel time
    if (TravelTime != null) {
      newJSONObject.put(TRAVELTIME_KEY, TravelTime);
    }

    // distance Derivative
    if (DistanceDerivative != null) {
      newJSONObject.put(DISTANCEDERIVATIVE_KEY, DistanceDerivative);
    }

    // depth Derivative
    if (DepthDerivative != null) {
      newJSONObject.put(DEPTHDERIVATIVE_KEY, DepthDerivative);
    }

    // ray Derivative
    if (RayDerivative != null) {
      newJSONObject.put(RAYDERIVATIVE_KEY, RayDerivative);
    }

    // statistical spread
    if (StatisticalSpread != null) {
      newJSONObject.put(STATISTICALSPREAD_KEY, StatisticalSpread);
    }

    // Observability
    if (Observability != null) {
      newJSONObject.put(OBSERVABILITY_KEY, Observability);
    }

    // teleseismic Phase group
    if (TeleseismicPhaseGroup != null) {
      newJSONObject.put(TELESEISMICPHASEGROUP_KEY, TeleseismicPhaseGroup);
    }

    // auxiliary Phase group
    if (AuxiliaryPhaseGroup != null) {
      newJSONObject.put(AUXILIARYPHASEGROUP_KEY, AuxiliaryPhaseGroup);
    }

    // location use flag
    if (LocationUseFlag != null) {
      newJSONObject.put(LOCATIONUSEFLAG_KEY, LocationUseFlag);
    }

    // association use flag
    if (AssociationWeightFlag != null) {
      newJSONObject.put(ASSOCIATIONWEIGHTFLAG_KEY, AssociationWeightFlag);
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

    if (Phase == null) {
      // Phase not found
      errorList.add("No Phase in TravelTimeData Class.");
    } else if (Phase.isEmpty()) {
      // Phase empty
      errorList.add("Empty Phase in TravelTimeData Class.");
    }

    // travel time
    if (TravelTime == null) {
      // travel time not found
      errorList.add("No Travel Time in TravelTimeData Class.");
    }

    // distance derivative
    if (DistanceDerivative == null) {
      // distance derivative not found
      errorList.add("No Distance Derivative in TravelTimeData Class.");
    }

    // depth derivative
    if (DepthDerivative == null) {
      // depth derivative not found
      errorList.add("No Depth Derivative in TravelTimeData Class.");
    }

    // ray derivative
    if (RayDerivative == null) {
      // ray derivative not found
      errorList.add("No Ray Derivative in TravelTimeData Class.");
    }

    // statistical spread
    if (StatisticalSpread == null) {
      // statistical spread not found
      errorList.add("No Statistical Spread in TravelTimeData Class.");
    }

    // Observability
    if (Observability == null) {
      // Observability not found
      errorList.add("No Observability in TravelTimeData Class.");
    }

    // teleseismic Phase group
    if (TeleseismicPhaseGroup == null) {
      // teleseismic Phase group not found
      errorList.add("No Teleseismic Phase Group in TravelTimeData Class.");
    }

    // auxiliary Phase group
    if (AuxiliaryPhaseGroup == null) {
      // auxiliary Phase group not found
      errorList.add("No Auxiliary Phase Group in TravelTimeData Class.");
    }

    // location use flag
    if (LocationUseFlag == null) {
      // location use flag not found
      errorList.add("No Location Use Flag in TravelTimeData Class.");
    }

    // association weight flag
    if (AssociationWeightFlag == null) {
      // association weight flag not found
      errorList.add("No Association Weight Flag in TravelTimeData Class.");
    }

    return (errorList);
  }
}
