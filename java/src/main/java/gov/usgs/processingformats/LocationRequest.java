package gov.usgs.processingformats;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * a conversion class used to create, parse, and validate location request data
 *
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class LocationRequest implements ProcessingInt {

  /** JSON Keys */
  public static final String TYPE_KEY = "Type";

  public static final String SOURCE_KEY = "Source";
  public static final String ID_KEY = "ID";
  public static final String EARTHMODEL_KEY = "EarthModel";
  public static final String SLABRESOLUTION_KEY = "SlabResolution";
  public static final String SOURCEORIGINTIME_KEY = "SourceOriginTime";
  public static final String SOURCELATITUDE_KEY = "SourceLatitude";
  public static final String SOURCELONGITUDE_KEY = "SourceLongitude";
  public static final String SOURCEDEPTH_KEY = "SourceDepth";
  public static final String INPUTDATA_KEY = "InputData";
  public static final String ISLOCATIONNEW_KEY = "IsLocationNew";
  public static final String ISLOCATIONHELD_KEY = "IsLocationHeld";
  public static final String ISDEPTHHELD_KEY = "IsDepthHeld";
  public static final String ISBAYESIANDEPTH_KEY = "IsBayesianDepth";
  public static final String BAYESIANDEPTH_KEY = "BayesianDepth";
  public static final String BAYESIANSPREAD_KEY = "BayesianSpread";
  public static final String USESVD_KEY = "UseSVD";
  public static final String OUTPUTDATA_KEY = "OutputData";

  /** Optional string containing the ID. */
  public String ID;

  /** Optional Source. */
  public Source Source;

  /** Required Type identifier for this LocationRequest */
  public String Type;

  /** Required Double containing the Source latitude in decimal degrees */
  public Double SourceLatitude;

  /** Required Double containing the Source longitude in decimal degrees */
  public Double SourceLongitude;

  /** Required time containing the Source time as a java Date */
  public Date SourceOriginTime;

  /** Required Double containing the Source depth in kilometers */
  public Double SourceDepth;

  /** A required vector of input Pick objects for this LocationRequest */
  public ArrayList<Pick> InputData;

  /** Optional earth model for this LocationRequest, defaults to "ak135" */
  public String EarthModel;

  /** Optional slab resoultion for this LocationRequest defaults to "2spd" */
  public String SlabResolution;

  /** Optional Boolean indicating whether the location is new */
  public Boolean IsLocationNew;

  /** Optional Boolean indicating whether the location is held */
  public Boolean IsLocationHeld;

  /** Optional Boolean indicating whether the depth is held */
  public Boolean IsDepthHeld;

  /** Optional Boolean indicating whether the depth is bayesian */
  public Boolean IsBayesianDepth;

  /** Optional Double containing the bayesian depth in kilometers */
  public Double BayesianDepth;

  /** Optional Double containing the bayesian spread in kilometers */
  public Double BayesianSpread;

  /** Optional Boolean indicating whether use SVD */
  public Boolean UseSVD;

  /** A LocationResult object to contain the output from the locator */
  public LocationResult OutputData;

  /** The constructor for the LocationRequest class. Initializes members to null values. */
  public LocationRequest() {
    ID = null;
    Source = null;
    Type = null;
    SourceLatitude = null;
    SourceLongitude = null;
    SourceOriginTime = null;
    SourceDepth = null;
    InputData = null;
    EarthModel = "ak135";
    SlabResolution = "2spd";
    IsLocationNew = null;
    IsLocationHeld = null;
    IsDepthHeld = null;
    IsBayesianDepth = null;
    BayesianDepth = null;
    BayesianSpread = null;
    UseSVD = null;
    OutputData = null;
  }

  /**
   * Advanced constructor
   *
   * <p>The advanced constructor for the LocationResult class. Initializes members to provided
   * values.
   *
   * @param newID - A String containing the optional ID
   * @param newAgencyID - A String containing the agencyid to Use
   * @param newAuthor - A String containing the author to Use
   * @param newType - A String containing the type to Use
   * @param newLocType - A String containing the name of the algorithm this request is valid for
   * @param newEarthModel - A String containing the name of the Travel Time Earth Model to use, null
   *     for default * @param newEarthModel - A String containing the name of the Travel Time Earth
   *     Model to use, null for default
   * @param newSlabResolution - A String containing the name of the slab resolution to use, null for
   *     default
   * @param newSourceLatitude - A Double containing the latitude to use
   * @param newSourceLongitude - A Double containing the longitude to use
   * @param newSourceOriginTime - A Date containing the origin time to use
   * @param newSourceDepth - A Double containing the depth to use
   * @param newInputData - A ArrayList&lt;Pick&gt; newPickData containing the data to use for this
   *     location
   * @param newIsLocationNew - A Boolean indicating whether the location is now, null to omit
   * @param newIsLocationHeld - A Boolean indicating whether to hold the location, null to omit
   * @param newIsDepthHeld - A Boolean indicating whether to hold the depth, null to omit
   * @param newIsBayesianDepth - A Boolean indicating whether to use the baysian depth, null to omit
   * @param newBayesianDepth - A Double containing the bayesian depth to use, null to omit
   * @param newBayesianSpread - A Double containing the bayesian spread to use, null to omit
   * @param newUseSVD - A Boolean indicating whether to use SVD, null to omit
   */
  public LocationRequest(
      String newID,
      String newAgencyID,
      String newAuthor,
      String newType,
      String newLocType,
      String newEarthModel,
      String newSlabResolution,
      Double newSourceLatitude,
      Double newSourceLongitude,
      Date newSourceOriginTime,
      Double newSourceDepth,
      ArrayList<Pick> newInputData,
      Boolean newIsLocationNew,
      Boolean newIsLocationHeld,
      Boolean newIsDepthHeld,
      Boolean newIsBayesianDepth,
      Double newBayesianDepth,
      Double newBayesianSpread,
      Boolean newUseSVD) {

    reload(
        newID,
        new Source(newAgencyID, newAuthor, newType),
        newLocType,
        newEarthModel,
        newSlabResolution,
        newSourceLatitude,
        newSourceLongitude,
        newSourceOriginTime,
        newSourceDepth,
        newInputData,
        newIsLocationNew,
        newIsLocationHeld,
        newIsDepthHeld,
        newIsBayesianDepth,
        newBayesianDepth,
        newBayesianSpread,
        newUseSVD);
  }

  /**
   * Advanced constructor
   *
   * <p>The advanced constructor for the LocationResult class. Initializes members to provided
   * values.
   *
   * @param newID - A String containing the optional ID
   * @param newSource - A Source object containing the optional source
   * @param newLocType - A String containing the name of the algorithm this request is valid for
   * @param newEarthModel - A String containing the name of the Travel Time Earth Model to use, null
   *     for default
   * @param newSlabResolution - A String containing the name of the slab resolution to use, null for
   *     default
   * @param newSourceLatitude - A Double containing the latitude to use
   * @param newSourceLongitude - A Double containing the longitude to use
   * @param newSourceOriginTime - A Date containing the origin time to use
   * @param newSourceDepth - A Double containing the depth to use
   * @param newInputData - A ArrayList&lt;Pick&gt; newPickData containing the data to use for this
   *     location
   * @param newIsLocationNew - A Boolean indicating whether the location is now, null to omit
   * @param newIsLocationHeld - A Boolean indicating whether to hold the location, null to omit
   * @param newIsDepthHeld - A Boolean indicating whether to hold the depth, null to omit
   * @param newIsBayesianDepth - A Boolean indicating whether to use the baysian depth, null to omit
   * @param newBayesianDepth - A Double containing the bayesian depth to use, null to omit
   * @param newBayesianSpread - A Double containing the bayesian spread to use, null to omit
   * @param newUseSVD - A Boolean indicating whether to use SVD, null to omit
   */
  public LocationRequest(
      String newID,
      Source newSource,
      String newLocType,
      String newEarthModel,
      String newSlabResolution,
      Double newSourceLatitude,
      Double newSourceLongitude,
      Date newSourceOriginTime,
      Double newSourceDepth,
      ArrayList<Pick> newInputData,
      Boolean newIsLocationNew,
      Boolean newIsLocationHeld,
      Boolean newIsDepthHeld,
      Boolean newIsBayesianDepth,
      Double newBayesianDepth,
      Double newBayesianSpread,
      Boolean newUseSVD) {

    reload(
        newID,
        newSource,
        newLocType,
        newEarthModel,
        newSlabResolution,
        newSourceLatitude,
        newSourceLongitude,
        newSourceOriginTime,
        newSourceDepth,
        newInputData,
        newIsLocationNew,
        newIsLocationHeld,
        newIsDepthHeld,
        newIsBayesianDepth,
        newBayesianDepth,
        newBayesianSpread,
        newUseSVD);
  }

  /**
   * Reload Function
   *
   * <p>The reload function for the LocationRequest class. Initializes members to provided values.
   *
   * @param newID - A String containing the optional ID
   * @param newSource - A Source containing the optional Source to Use
   * @param newType - A String containing the name of the algorithm this request is valid for
   * @param newEarthModel - A String containing the name of the Travel Time Earth Model to use, null
   *     for default
   * @param newSlabResolution - A String containing the name of the slab resolution to use, null for
   *     default
   * @param newSourceLatitude - A Double containing the latitude to use
   * @param newSourceLongitude - A Double containing the longitude to use
   * @param newSourceOriginTime - A Date containing the origin time to use
   * @param newSourceDepth - A Double containing the depth to use
   * @param newInputData - A ArrayList&lt;Pick&gt; newPickData containing the data to use for this
   *     location
   * @param newIsLocationNew - A Boolean indicating whether the location is now, null to omit
   * @param newIsLocationHeld - A Boolean indicating whether to hold the location, null to omit
   * @param newIsDepthHeld - A Boolean indicating whether to hold the depth, null to omit
   * @param newIsBayesianDepth - A Boolean indicating whether to use the baysian depth, null to omit
   * @param newBayesianDepth - A Double containing the bayesian depth to use, null to omit
   * @param newBayesianSpread - A Double containing the bayesian spread to use, null to omit
   * @param newUseSVD - A Boolean indicating whether to use SVD, null to omit
   */
  public void reload(
      String newID,
      Source newSource,
      String newType,
      String newEarthModel,
      String newSlabResolution,
      Double newSourceLatitude,
      Double newSourceLongitude,
      Date newSourceOriginTime,
      Double newSourceDepth,
      ArrayList<Pick> newInputData,
      Boolean newIsLocationNew,
      Boolean newIsLocationHeld,
      Boolean newIsDepthHeld,
      Boolean newIsBayesianDepth,
      Double newBayesianDepth,
      Double newBayesianSpread,
      Boolean newUseSVD) {

    ID = newID;
    Source = newSource;
    Type = newType;
    if (newEarthModel != null) {
      EarthModel = newEarthModel;
    }
    if (newSlabResolution != null) {
      SlabResolution = newSlabResolution;
    }
    SourceLatitude = newSourceLatitude;
    SourceLongitude = newSourceLongitude;
    SourceOriginTime = newSourceOriginTime;
    SourceDepth = newSourceDepth;
    InputData = newInputData;
    IsLocationNew = newIsLocationNew;
    IsLocationHeld = newIsLocationHeld;
    IsDepthHeld = newIsDepthHeld;
    IsBayesianDepth = newIsBayesianDepth;
    BayesianDepth = newBayesianDepth;
    BayesianSpread = newBayesianSpread;
    UseSVD = newUseSVD;
    OutputData = null;
  }

  /**
   * Constructs the class from a JSONObject, populating members
   *
   * @param newJSONObject - A JSONObject.
   */
  public LocationRequest(JSONObject newJSONObject) {

    // Required values
    // Type
    if (newJSONObject.containsKey(TYPE_KEY)) {
      Type = newJSONObject.get(TYPE_KEY).toString();
    } else {
      Type = null;
    }

    // latitude
    if (newJSONObject.containsKey(SOURCELATITUDE_KEY)) {
      SourceLatitude = (double) newJSONObject.get(SOURCELATITUDE_KEY);
    } else {
      SourceLatitude = null;
    }

    // longitude
    if (newJSONObject.containsKey(SOURCELONGITUDE_KEY)) {
      SourceLongitude = (double) newJSONObject.get(SOURCELONGITUDE_KEY);
    } else {
      SourceLongitude = null;
    }

    // time
    if (newJSONObject.containsKey(SOURCEORIGINTIME_KEY)) {
      SourceOriginTime = Utility.getDate(newJSONObject.get(SOURCEORIGINTIME_KEY).toString());
    } else {
      SourceOriginTime = null;
    }

    // depth
    if (newJSONObject.containsKey(SOURCEDEPTH_KEY)) {
      Object depthObj = newJSONObject.get(SOURCEDEPTH_KEY);

      // Sometimes json turns this into a int or long
      // instead of a double and the cast fails
      if (depthObj instanceof Number) {
        SourceDepth = ((Number) depthObj).doubleValue();
      } else {
        SourceDepth = null;
      }
    } else {
      SourceDepth = null;
    }

    // input data
    if (newJSONObject.containsKey(INPUTDATA_KEY)) {

      InputData = new ArrayList<Pick>();

      // get the array
      JSONArray dataArray = (JSONArray) newJSONObject.get(INPUTDATA_KEY);

      if ((dataArray != null) && (!dataArray.isEmpty())) {

        // go through the whole array
        for (int i = 0; i < dataArray.size(); i++) {

          // add to vector
          InputData.add(new Pick((JSONObject) dataArray.get(i)));
        }
      }
    } else {
      InputData = null;
    }

    // Optional values
    // ID
    if (newJSONObject.containsKey(ID_KEY)) {
      Object idObject = newJSONObject.get(ID_KEY);
      // check type of id object
      if (idObject instanceof String) {
        // the Id *should* be a string
        ID = idObject.toString();
      } else if (idObject instanceof Integer) {
        // but the ID *could* be an int, but we
        // want it to be a string
        ID = String.valueOf((int) idObject);
      } else {
        // any other type isn't a usable id
        ID = null;
      }
    } else {
      ID = null;
    }

    // Source
    if (newJSONObject.containsKey(SOURCE_KEY)) {
      Source = new Source((JSONObject) newJSONObject.get(SOURCE_KEY));
    } else {
      Source = null;
    }

    // EarthModel
    if (newJSONObject.containsKey(EARTHMODEL_KEY)) {
      EarthModel = newJSONObject.get(EARTHMODEL_KEY).toString();
    } else {
      EarthModel = "ak135";
    }

    // SlabResolution
    if (newJSONObject.containsKey(SLABRESOLUTION_KEY)) {
      SlabResolution = newJSONObject.get(SLABRESOLUTION_KEY).toString();
    } else {
      SlabResolution = "2spd";
    }

    // IsLocationNew
    if (newJSONObject.containsKey(ISLOCATIONNEW_KEY)) {
      IsLocationNew = (boolean) newJSONObject.get(ISLOCATIONNEW_KEY);
    } else {
      IsLocationNew = null;
    }

    // IsLocationHeld
    if (newJSONObject.containsKey(ISLOCATIONHELD_KEY)) {
      IsLocationHeld = (boolean) newJSONObject.get(ISLOCATIONHELD_KEY);
    } else {
      IsLocationHeld = null;
    }

    // IsDepthHeld
    if (newJSONObject.containsKey(ISDEPTHHELD_KEY)) {
      IsDepthHeld = (boolean) newJSONObject.get(ISDEPTHHELD_KEY);
    } else {
      IsDepthHeld = null;
    }

    // IsBayesianDepth
    if (newJSONObject.containsKey(ISBAYESIANDEPTH_KEY)) {
      IsBayesianDepth = (boolean) newJSONObject.get(ISBAYESIANDEPTH_KEY);
    } else {
      IsBayesianDepth = null;
    }

    // baysian depth
    if (newJSONObject.containsKey(BAYESIANDEPTH_KEY)) {
      BayesianDepth = (double) newJSONObject.get(BAYESIANDEPTH_KEY);
    } else {
      BayesianDepth = null;
    }

    // baysian spread
    if (newJSONObject.containsKey(BAYESIANSPREAD_KEY)) {
      BayesianSpread = (double) newJSONObject.get(BAYESIANSPREAD_KEY);
    } else {
      BayesianSpread = null;
    }

    // UseSVD
    if (newJSONObject.containsKey(USESVD_KEY)) {
      UseSVD = (boolean) newJSONObject.get(USESVD_KEY);
    } else {
      UseSVD = null;
    }

    // Output values
    // OutputData
    if (newJSONObject.containsKey(OUTPUTDATA_KEY)) {
      OutputData = new LocationResult((JSONObject) newJSONObject.get(OUTPUTDATA_KEY));
    } else {
      OutputData = null;
    }
  }

  /**
   * Converts the contents of the class to a json object
   *
   * @return Returns a JSONObject containing the class contents
   */
  @SuppressWarnings("unchecked")
  public JSONObject toJSON() {

    JSONObject newJSONObject = new JSONObject();

    // ID
    if (ID != null) {
      newJSONObject.put(ID_KEY, ID);
    }

    // Source
    if (Source != null) {
      newJSONObject.put(SOURCE_KEY, Source.toJSON());
    }

    // Required values
    // Type
    if (Type != null) {
      newJSONObject.put(TYPE_KEY, Type);
    }

    // latitude
    if (SourceLatitude != null) {
      newJSONObject.put(SOURCELATITUDE_KEY, SourceLatitude);
    }

    // longitude
    if (SourceLongitude != null) {
      newJSONObject.put(SOURCELONGITUDE_KEY, SourceLongitude);
    }

    // time
    if (SourceOriginTime != null) {
      newJSONObject.put(SOURCEORIGINTIME_KEY, Utility.formatDate(SourceOriginTime));
    }

    // depth
    if (SourceDepth != null) {
      newJSONObject.put(SOURCEDEPTH_KEY, SourceDepth);
    }

    // input data
    if ((InputData != null) && (!InputData.isEmpty())) {
      JSONArray dataArray = new JSONArray();

      // enumerate through the whole arraylist
      for (Iterator<Pick> pickIterator = InputData.iterator(); pickIterator.hasNext(); ) {

        // convert pick to JSON object
        JSONObject pickObject = ((Pick) pickIterator.next()).toJSON();

        dataArray.add(pickObject);
      }

      if (!dataArray.isEmpty()) {
        newJSONObject.put(INPUTDATA_KEY, dataArray);
      }
    }

    // optional values
    // earth model
    if (EarthModel != null) {
      newJSONObject.put(EARTHMODEL_KEY, EarthModel);
    }

    // slab resolution
    if (SlabResolution != null) {
      newJSONObject.put(SLABRESOLUTION_KEY, SlabResolution);
    }

    // IsLocationNew
    if (IsLocationNew != null) {
      newJSONObject.put(ISLOCATIONNEW_KEY, IsLocationNew);
    }

    // IsLocationHeld
    if (IsLocationHeld != null) {
      newJSONObject.put(ISLOCATIONHELD_KEY, IsLocationHeld);
    }

    // IsDepthHeld
    if (IsDepthHeld != null) {
      newJSONObject.put(ISDEPTHHELD_KEY, IsDepthHeld);
    }

    // IsBayesianDepth
    if (IsBayesianDepth != null) {
      newJSONObject.put(ISBAYESIANDEPTH_KEY, IsBayesianDepth);
    }

    // bayesian depth
    if (BayesianDepth != null) {
      newJSONObject.put(BAYESIANDEPTH_KEY, BayesianDepth);
    }

    // bayesian spread
    if (BayesianSpread != null) {
      newJSONObject.put(BAYESIANSPREAD_KEY, BayesianSpread);
    }

    // use SVD
    if (UseSVD != null) {
      newJSONObject.put(USESVD_KEY, UseSVD);
    }

    // output values
    // output data
    if (OutputData != null) {
      newJSONObject.put(OUTPUTDATA_KEY, OutputData.toJSON());
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
    // latitude
    if (SourceLatitude == null) {
      // latitude not found
      errorList.add("No Source Latitude in LocationRequest Class.");
    } else if ((SourceLatitude < -90) || (SourceLatitude > 90)) {
      // invalid latitude
      errorList.add("Source Latitude in LocationRequest Class not in the range of -90 to 90.");
    }

    // longitude
    if (SourceLongitude == null) {
      // longitude not found
      errorList.add("No Source Longitude in LocationRequest Class.");
    } else if ((SourceLongitude < -180) || (SourceLongitude > 180)) {
      // invalid longitude
      errorList.add("Source Longitude in LocationRequest Class not in the range of -180 to 180.");
    }

    // time
    if (SourceOriginTime == null) {
      // time not found
      errorList.add("No Source Origin Time in LocationRequest Class.");
    }

    // depth
    if (SourceDepth == null) {
      // depth not found
      errorList.add("No Depth in LocationRequest Class.");
    } else if ((SourceDepth < -100) || (SourceDepth > 1500)) {
      // invalid depth
      errorList.add("Source Depth in LocationRequest Class not in the range of -100 to 1500.");
    }

    // input data
    if ((InputData != null) && (!InputData.isEmpty())) {

      // enumerate through the whole arraylist
      for (Iterator<Pick> pickIterator = InputData.iterator(); pickIterator.hasNext(); ) {

        // convert pick to JSON object
        Pick aPick = ((Pick) pickIterator.next());

        if (!aPick.isValid()) {
          ArrayList<String> pickErrorList = aPick.getErrors();

          // combine the errors into a single string
          String errorString = new String();
          for (int i = 0; i < pickErrorList.size(); i++) {
            errorString += " " + pickErrorList.get(i);
          }
          errorList.add("Invalid Pick in InputData in LocationRequest Class: " + errorString);
          break;
        }
      }
    } else {
      // hypocenter not found
      errorList.add("No input data in LocationRequest Class.");
    }

    // output data
    if (OutputData != null) {
      if (!OutputData.isValid()) {
        // hypocenter invalid
        errorList.add("Invalid OutputData in LocationRequest Class.");
      }
    }

    // Source
    if ((Source != null) && (!Source.isValid())) {
      // Source invalid
      errorList.add("Invalid Source in LocationRequest Class.");
    }

    // success
    return (errorList);
  }
}
