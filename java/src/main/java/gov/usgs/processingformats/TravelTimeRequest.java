package gov.usgs.processingformats;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * a conversion class used to create, parse, and validate travel time requests
 *
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class TravelTimeRequest implements ProcessingInt {

  /** JSON Keys */
  public static final String SOURCE_KEY = "Source";

  public static final String RECEIVERS_KEY = "Receivers";
  public static final String EARTHMODEL_KEY = "EarthModel";
  public static final String PHASETYPES_KEY = "PhaseTypes";
  public static final String RETURNALLPHASES_KEY = "ReturnAllPhases";
  public static final String RETURNBACKBRANCHES_KEY = "ReturnBackBranches";
  public static final String CONVERTTECTONIC_KEY = "ConvertTectonic";

  public static final String RESPONSE_KEY = "Response";

  /** Required source */
  public TravelTimeSource Source;

  /** Required receivers */
  public ArrayList<TravelTimeReceiver> Receivers;

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

  /** Returned travel time Data (empty for requests) */
  public ArrayList<TravelTimeReceiver> Response;

  /** The constructor for the TravelTimeRequest class. Initializes members to null values. */
  public TravelTimeRequest() {

    reload(null, null, null, null, null, null, null, null);
  }

  /**
   * Advanced constructor
   *
   * <p>The advanced constructor for the TravelTimeRequest class. Initializes members to provided
   * values.
   *
   * @param newSource - A TravelTimeSource object containing the source information
   * @param newReceivers - An ArrayList&lt;TravelTimeReceiver&gt; objects containing the desired
   *     Receivers
   * @param newEarthModel - A String containing the earthmodel
   * @param newPhaseTypes - An ArrayList&lt;String&gt; containing the desired display types
   * @param newReturnAllPhases - A Boolean indicating whether to return all phases
   * @param newReturnBackBranches - A Boolean indicating whether to return all arrivals of all
   *     phases
   * @param newConvertTectonic - A boolean that indicates whether to convert tectonic phases
   * @param newResponse - An ArrayList&lt;TravelTimeReceiver&gt; objects containing the returned
   *     Receivers with travel time data
   */
  public TravelTimeRequest(
      TravelTimeSource newSource,
      ArrayList<TravelTimeReceiver> newReceivers,
      String newEarthModel,
      ArrayList<String> newPhaseTypes,
      Boolean newReturnAllPhases,
      Boolean newReturnBackBranches,
      Boolean newConvertTectonic,
      ArrayList<TravelTimeReceiver> newResponse) {

    reload(
        newSource,
        newReceivers,
        newEarthModel,
        newPhaseTypes,
        newReturnAllPhases,
        newReturnBackBranches,
        newConvertTectonic,
        newResponse);
  }

  /**
   * Constructs the class from a JSONObject, populating members
   *
   * @param newJSONObject - A JSONObject.
   */
  public TravelTimeRequest(JSONObject newJSONObject) {

    // Required values
    // Source
    if (newJSONObject.containsKey(SOURCE_KEY)) {
      Source = new TravelTimeSource((JSONObject) newJSONObject.get(SOURCE_KEY));
    } else {
      Source = null;
    }

    // Receivers
    if (newJSONObject.containsKey(RECEIVERS_KEY)) {

      Receivers = new ArrayList<TravelTimeReceiver>();

      // get the array
      JSONArray ReceiversArray = (JSONArray) newJSONObject.get(RECEIVERS_KEY);

      if ((ReceiversArray != null) && (!ReceiversArray.isEmpty())) {

        // go through the whole array
        for (int i = 0; i < ReceiversArray.size(); i++) {

          // get the object
          JSONObject DataObject = (JSONObject) ReceiversArray.get(i);

          Receivers.add(new TravelTimeReceiver(DataObject));
        }
      }
    } else {
      Receivers = null;
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

    // Response
    if (newJSONObject.containsKey(RESPONSE_KEY)) {

      Response = new ArrayList<TravelTimeReceiver>();

      // get the array
      JSONArray DataArray = (JSONArray) newJSONObject.get(RESPONSE_KEY);

      if ((DataArray != null) && (!DataArray.isEmpty())) {

        // go through the whole array
        for (int i = 0; i < DataArray.size(); i++) {

          // get the object
          JSONObject DataObject = (JSONObject) DataArray.get(i);

          Response.add(new TravelTimeReceiver(DataObject));
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
  public TravelTimeRequest(TravelTimeRequest sourceObject) {
    reload(
        sourceObject.Source,
        sourceObject.Receivers,
        sourceObject.EarthModel,
        sourceObject.PhaseTypes,
        sourceObject.ReturnAllPhases,
        sourceObject.ReturnBackBranches,
        sourceObject.ConvertTectonic,
        sourceObject.Response);
  }

  /**
   * Reload Function
   *
   * <p>The reload function for the TravelTimeData class. Initializes members to provided values.
   *
   * @param newSource - A TravelTimeSource object containing the source information
   * @param newReceivers - An ArrayList&lt;TravelTimeReceiver&gt; objects containing the desired
   *     Receivers
   * @param newEarthModel - A String containing the earthmodel
   * @param newPhaseTypes - An ArrayList&lt;String&gt; containing the desired display types
   * @param newReturnAllPhases - A Boolean indicating whether to return all phases
   * @param newReturnBackBranches - A Boolean indicating whether to return all arrivals of all
   *     phases
   * @param newConvertTectonic - A boolean that indicates whether to convert tectonic phases
   * @param newResponse - An ArrayList&lt;TravelTimeReceiver&gt; objects containing the returned
   *     Receivers with travel time data
   */
  public void reload(
      TravelTimeSource newSource,
      ArrayList<TravelTimeReceiver> newReceivers,
      String newEarthModel,
      ArrayList<String> newPhaseTypes,
      Boolean newReturnAllPhases,
      Boolean newReturnBackBranches,
      Boolean newConvertTectonic,
      ArrayList<TravelTimeReceiver> newResponse) {

    Source = newSource;
    Receivers = newReceivers;

    EarthModel = newEarthModel;
    PhaseTypes = newPhaseTypes;
    ReturnAllPhases = newReturnAllPhases;
    ReturnBackBranches = newReturnBackBranches;
    ConvertTectonic = newConvertTectonic;

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

    // Receivers
    if ((Receivers != null) && (!Receivers.isEmpty())) {

      JSONArray ReceiversArray = new JSONArray();

      for (Iterator<TravelTimeReceiver> ReceiversIterator = Receivers.iterator();
          ReceiversIterator.hasNext(); ) {

        // convert pick to JSON object
        JSONObject ReceiverObject = ((TravelTimeReceiver) ReceiversIterator.next()).toJSON();

        ReceiversArray.add(ReceiverObject);
      }

      if (!ReceiversArray.isEmpty()) {
        newJSONObject.put(RECEIVERS_KEY, ReceiversArray);
      }
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

    // Response
    if ((Response != null) && (!Response.isEmpty())) {

      JSONArray ResponseArray = new JSONArray();

      for (Iterator<TravelTimeReceiver> ResponseIterator = Response.iterator();
          ResponseIterator.hasNext(); ) {

        // convert pick to JSON object
        JSONObject ResponseObject = ((TravelTimeReceiver) ResponseIterator.next()).toJSON();

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
      errorList.add("No Source in TravelTimeRequest Class.");
    } else if (!Source.isValid()) {
      // Hypocenter invalid
      errorList.add("Invalid Source in TravelTimeRequest Class.");
    }

    // Receivers
    if (Receivers == null) {
      // Receivers not found
      errorList.add("No Receivers in TravelTimeRequest Class.");
    } else if (Receivers.isEmpty()) {
      // Receivers not found
      errorList.add("Empty Receivers in TravelTimeRequest Class.");
    } else {
      // enumerate through the whole arraylist
      for (Iterator<TravelTimeReceiver> receiverIterator = Receivers.iterator();
          receiverIterator.hasNext(); ) {

        // convert recever to JSON object
        TravelTimeReceiver jsonReceiver = ((TravelTimeReceiver) receiverIterator.next());

        if (!jsonReceiver.isValid()) {
          errorList.add("Invalid Receiver in TravelTimeRequest Class.");
          break;
        }
      }
    }

    // Optional Output Response
    if ((Response != null) && (!Response.isEmpty())) {

      // enumerate through the whole arraylist
      for (Iterator<TravelTimeReceiver> responseIterator = Response.iterator();
          responseIterator.hasNext(); ) {

        // convert recever to JSON object
        TravelTimeReceiver jsonResponse = ((TravelTimeReceiver) responseIterator.next());

        if (!jsonResponse.isValid()) {
          errorList.add("Invalid Response in TravelTimeRequest Class.");
          break;
        }
      }
    }
    return (errorList);
  }
}
