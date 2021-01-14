package gov.usgs.processingformats;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * a conversion class used to create, parse, and validate travel time requests
 *
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class TravelTimePlotRequest implements ProcessingInt {

  /** JSON Keys */
  public static final String SOURCE_KEY = "Source";

  public static final String EARTHMODEL_KEY = "EarthModel";
  public static final String PHASETYPES_KEY = "PhaseTypes";
  public static final String RETURNALLPHASES_KEY = "ReturnAllPhases";
  public static final String RETURNBACKBRANCHES_KEY = "ReturnBackBranches";
  public static final String CONVERTTECTONIC_KEY = "ConvertTectonic";
  public static final String MAXIMUMDISTANCE_KEY = "MaximumDistance";
  public static final String DISTANCESTEP_KEY = "DistanceStep";
  public static final String MAXIMUMTRAVELTIME_KEY = "MaximumTravelTime";

  public static final String RESPONSE_KEY = "Response";

  /** Required source */
  public TravelTimeSource Source;

  /** Optional earth model to use, defaults to the AK135 earth model */
  public String EarthModel;

  /** Optional ArrayList of strings listing the phase types desired */
  public ArrayList<String> PhaseTypes;

  /** Optional flag that indicates whether to return all phases, defaults to false */
  public Boolean ReturnAllPhases;

  /**
   * Optional flag that indicates whether to return all arrivals of all phases, defaults to false
   */
  public Boolean ReturnBackBranches;

  /** Optional flag that indicates whether to convert tectonic phases, defaults to false */
  public Boolean ConvertTectonic;

  /** Optional double containing the maximum desired distance in degrees */
  public Double MaximumDistance;

  /** Optional double containing the maximum desired travel time in seconds */
  public Double MaximumTravelTime;

  /** Optional double containing the distance step increment in degrees */
  public Double DistanceStep;

  /** Returned travel time Data (empty for requests) */
  public ArrayList<TravelTimePlotDataBranch> Response;

  /** The constructor for the TravelTimePlotRequest class. Initializes members to null values. */
  public TravelTimePlotRequest() {

    reload(null, null, null, null, null, null, null, null, null, null);
  }

  /**
   * Advanced constructor
   *
   * <p>The advanced constructor for the TravelTimePlotRequest class. Initializes members to
   * provided values.
   *
   * @param newSource - A TravelTimeSource object containing the source information
   * @param newEarthModel - A String containing the earthmodel
   * @param newPhaseTypes - An ArrayList&lt;String&gt; containing the desired display types
   * @param newReturnAllPhases - A Boolean indicating whether to return all phases
   * @param newReturnBackBranches - A Boolean indicating whether to return all arrivals of all
   *     phases
   * @param newConvertTectonic - A boolean that indicates whether to convert tectonic phases
   * @param newMaxDistance - A double containing the maximum desired distance in degrees
   * @param newDistanceStep - A double containing the distance step increment in degrees
   * @param newMaxTT - A double containing the maximum desired travel time in seconds
   * @param newResponse - An ArrayList&lt;TravelTimePlotDataBranch&gt; objects containing the
   *     returned Receivers with travel time data
   */
  public TravelTimePlotRequest(
      TravelTimeSource newSource,
      String newEarthModel,
      ArrayList<String> newPhaseTypes,
      Boolean newReturnAllPhases,
      Boolean newReturnBackBranches,
      Boolean newConvertTectonic,
      Double newMaxDistance,
      Double newDistanceStep,
      Double newMaxTT,
      ArrayList<TravelTimePlotDataBranch> newResponse) {

    reload(
        newSource,
        newEarthModel,
        newPhaseTypes,
        newReturnAllPhases,
        newReturnBackBranches,
        newConvertTectonic,
        newMaxDistance,
        newDistanceStep,
        newMaxTT,
        newResponse);
  }

  /**
   * Constructs the class from a JSONObject, populating members
   *
   * @param newJSONObject - A JSONObject.
   */
  public TravelTimePlotRequest(JSONObject newJSONObject) {

    // Required values
    // Source
    if (newJSONObject.containsKey(SOURCE_KEY)) {
      Source = new TravelTimeSource((JSONObject) newJSONObject.get(SOURCE_KEY));
    } else {
      Source = null;
    }

    // Optional values
    // EarthModel
    if (newJSONObject.containsKey(EARTHMODEL_KEY)) {
      EarthModel = newJSONObject.get(EARTHMODEL_KEY).toString();
    } else {
      EarthModel = null;
    }

    // PhaseTypes
    if (newJSONObject.containsKey(PHASETYPES_KEY)) {

      PhaseTypes = new ArrayList<String>();

      // get the array
      JSONArray PhaseTypesArray = (JSONArray) newJSONObject.get(PHASETYPES_KEY);

      if ((PhaseTypesArray != null) && (!PhaseTypesArray.isEmpty())) {

        // go through the whole array
        for (int i = 0; i < PhaseTypesArray.size(); i++) {

          // get the String
          String phaseType = PhaseTypesArray.get(i).toString();

          // add to ArrayList
          PhaseTypes.add(phaseType);
        }
      }
    } else {
      PhaseTypes = null;
    }

    // ReturnAllPhases
    if (newJSONObject.containsKey(RETURNALLPHASES_KEY)) {
      ReturnAllPhases = (boolean) newJSONObject.get(RETURNALLPHASES_KEY);
    } else {
      ReturnAllPhases = null;
    }

    // ReturnBackBranches
    if (newJSONObject.containsKey(RETURNBACKBRANCHES_KEY)) {
      ReturnBackBranches = (boolean) newJSONObject.get(RETURNBACKBRANCHES_KEY);
    } else {
      ReturnBackBranches = null;
    }

    // ConvertTectonic
    if (newJSONObject.containsKey(CONVERTTECTONIC_KEY)) {
      ConvertTectonic = (boolean) newJSONObject.get(CONVERTTECTONIC_KEY);
    } else {
      ConvertTectonic = null;
    }

    // MaximumDistance
    if (newJSONObject.containsKey(MAXIMUMDISTANCE_KEY)) {
      MaximumDistance = (double) newJSONObject.get(MAXIMUMDISTANCE_KEY);
    } else {
      MaximumDistance = null;
    }

    // DistanceStep
    if (newJSONObject.containsKey(DISTANCESTEP_KEY)) {
      DistanceStep = (double) newJSONObject.get(DISTANCESTEP_KEY);
    } else {
      DistanceStep = null;
    }

    // MaximumTravelTime
    if (newJSONObject.containsKey(MAXIMUMTRAVELTIME_KEY)) {
      MaximumTravelTime = (double) newJSONObject.get(MAXIMUMTRAVELTIME_KEY);
    } else {
      MaximumTravelTime = null;
    }

    // Response
    if (newJSONObject.containsKey(RESPONSE_KEY)) {

      Response = new ArrayList<TravelTimePlotDataBranch>();

      // get the array
      JSONArray DataArray = (JSONArray) newJSONObject.get(RESPONSE_KEY);

      if ((DataArray != null) && (!DataArray.isEmpty())) {

        // go through the whole array
        for (int i = 0; i < DataArray.size(); i++) {

          // get the object
          JSONObject DataObject = (JSONObject) DataArray.get(i);

          Response.add(new TravelTimePlotDataBranch(DataObject));
        }

      } else {
        Response = null;
      }
    }
  }

  /**
   * Constructs the class from a TravelTimeData object, populating members (copy constructor)
   *
   * @param sourceObject - A TravelTimeData object.
   */
  public TravelTimePlotRequest(TravelTimePlotRequest sourceObject) {
    reload(
        sourceObject.Source,
        sourceObject.EarthModel,
        sourceObject.PhaseTypes,
        sourceObject.ReturnAllPhases,
        sourceObject.ReturnBackBranches,
        sourceObject.ConvertTectonic,
        sourceObject.MaximumDistance,
        sourceObject.DistanceStep,
        sourceObject.MaximumTravelTime,
        sourceObject.Response);
  }

  /**
   * Reload Function
   *
   * <p>The reload function for the TravelTimeData class. Initializes members to provided values.
   *
   * @param newSource - A TravelTimeSource object containing the source information
   * @param newEarthModel - A String containing the earthmodel
   * @param newPhaseTypes - An ArrayList&lt;String&gt; containing the desired display types
   * @param newReturnAllPhases - A Boolean indicating whether to return all phases
   * @param newReturnBackBranches - A Boolean indicating whether to return all arrivals of all
   *     phases
   * @param newConvertTectonic - A boolean that indicates whether to convert tectonic phases
   * @param newMaxDistance - A double containing the maximum desired distance in degrees
   * @param newDistanceStep - A double containing the distance step increment in degrees
   * @param newMaxTT - A double containing the maximum desired travel time in seconds
   * @param newResponse - An ArrayList&lt;TravelTimePlotDataBranch&gt; objects containing the
   *     returned Receivers with travel time data
   */
  public void reload(
      TravelTimeSource newSource,
      String newEarthModel,
      ArrayList<String> newPhaseTypes,
      Boolean newReturnAllPhases,
      Boolean newReturnBackBranches,
      Boolean newConvertTectonic,
      Double newMaxDistance,
      Double newDistanceStep,
      Double newMaxTT,
      ArrayList<TravelTimePlotDataBranch> newResponse) {

    Source = newSource;

    EarthModel = newEarthModel;
    PhaseTypes = newPhaseTypes;
    ReturnAllPhases = newReturnAllPhases;
    ReturnBackBranches = newReturnBackBranches;
    ConvertTectonic = newConvertTectonic;
    MaximumDistance = newMaxDistance;
    DistanceStep = newDistanceStep;
    MaximumTravelTime = newMaxTT;
    Response = newResponse;
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
    // Source
    if (Source != null) {
      newJSONObject.put(SOURCE_KEY, Source.toJSON());
    }

    // EarthModel
    if (EarthModel != null) {
      newJSONObject.put(EARTHMODEL_KEY, EarthModel);
    }

    // PhaseTypes
    if ((PhaseTypes != null) && (!PhaseTypes.isEmpty())) {

      JSONArray PhaseTypesArray = new JSONArray();

      // enumerate through the whole arraylist
      for (Iterator<String> PhaseTypesIterator = PhaseTypes.iterator();
          PhaseTypesIterator.hasNext(); ) {

        // add to  array
        PhaseTypesArray.add(PhaseTypesIterator.next());
      }

      if (!PhaseTypesArray.isEmpty()) {
        newJSONObject.put(PHASETYPES_KEY, PhaseTypesArray);
      }
    }

    // ReturnAllPhases
    if (ReturnAllPhases != null) {
      newJSONObject.put(RETURNALLPHASES_KEY, ReturnAllPhases);
    }

    // ReturnBackBranches
    if (ReturnBackBranches != null) {
      newJSONObject.put(RETURNBACKBRANCHES_KEY, ReturnBackBranches);
    }

    // ConvertTectonic
    if (ConvertTectonic != null) {
      newJSONObject.put(CONVERTTECTONIC_KEY, ConvertTectonic);
    }

    // MaximumDistance
    if (MaximumDistance != null) {
      newJSONObject.put(MAXIMUMDISTANCE_KEY, MaximumDistance);
    }

    // DistanceStep
    if (DistanceStep != null) {
      newJSONObject.put(DISTANCESTEP_KEY, DistanceStep);
    }

    // MaximumTravelTime
    if (MaximumTravelTime != null) {
      newJSONObject.put(MAXIMUMTRAVELTIME_KEY, MaximumTravelTime);
    }

    // Response
    if ((Response != null) && (!Response.isEmpty())) {

      JSONArray ResponseArray = new JSONArray();

      for (Iterator<TravelTimePlotDataBranch> ResponseIterator = Response.iterator();
          ResponseIterator.hasNext(); ) {

        // convert pick to JSON object
        JSONObject ResponseObject = ((TravelTimePlotDataBranch) ResponseIterator.next()).toJSON();

        ResponseArray.add(ResponseObject);
      }

      if (!ResponseArray.isEmpty()) {
        newJSONObject.put(RESPONSE_KEY, ResponseArray);
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
    // Source
    if (Source == null) {
      // Source not found
      errorList.add("No Source in TravelTimePlotRequest Class.");
    } else if (!Source.isValid()) {
      // Hypocenter invalid
      errorList.add("Invalid Source in TravelTimePlotRequest Class.");
    }

    // Optional Output Response
    if ((Response != null) && (!Response.isEmpty())) {

      // enumerate through the whole arraylist
      for (Iterator<TravelTimePlotDataBranch> responseIterator = Response.iterator();
          responseIterator.hasNext(); ) {

        // convert recever to JSON object
        TravelTimePlotDataBranch jsonResponse =
            ((TravelTimePlotDataBranch) responseIterator.next());

        if (!jsonResponse.isValid()) {
          errorList.add("Invalid Response in TravelTimePlotRequest Class.");
          break;
        }
      }
    }
    return (errorList);
  }
}
