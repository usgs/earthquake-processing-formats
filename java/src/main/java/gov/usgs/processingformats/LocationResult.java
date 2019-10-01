package gov.usgs.processingformats;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * a conversion class used to create, parse, and validate location processing data
 *
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class LocationResult implements ProcessingInt {

  /** JSON Keys */
  public static final String ID_KEY = "ID";

  public static final String HYPOCENTER_KEY = "Hypocenter";
  public static final String SUPPORTINGDATA_KEY = "SupportingData";
  public static final String ASSOCIATEDSTATIONS_KEY = "NumberOfAssociatedStations";
  public static final String ASSOCIATEDPHASES_KEY = "NumberOfAssociatedPhases";
  public static final String USEDSTATIONS_KEY = "NumberOfUsedStations";
  public static final String USEDPHASES_KEY = "NumberOfUsedPhases";
  public static final String GAP_KEY = "Gap";
  public static final String SECONDARYGAP_KEY = "SecondaryGap";
  public static final String MINIMUMDISTANCE_KEY = "MinimumDistance";
  public static final String RMS_KEY = "RMS";
  public static final String QUALITY_KEY = "Quality";
  public static final String BAYESIANDEPTH_KEY = "BayesianDepth";
  public static final String BAYESIANRANGE_KEY = "BayesianRange";
  public static final String DEPTHIMPORTANCE_KEY = "DepthImportance";
  public static final String LOCATOREXITCODE_KEY = "LocatorExitCode";
  public static final String ERRORELLIPSE_KEY = "ErrorEllipse";

  /** Optional string containing the ID. */
  private String ID;

  /** Required Hypocenter */
  private Hypocenter Hypocenter;

  /** A required vector of Pick objects used to generate this location */
  private ArrayList<Pick> SupportingData;

  /** Optional integer containing the number of associated stations */
  private Integer NumberOfAssociatedStations;

  /** Optional integer containing the number of associated phases */
  private Integer NumberOfAssociatedPhases;

  /** Optional integer containing the number of used stations */
  private Integer NumberOfUsedStations;

  /** Optional integer containing the number of used phases */
  private Integer NumberOfUsedPhases;

  /** Optional Double containing the Gap */
  private Double Gap;

  /** Optional Double containing the secondary Gap */
  private Double SecondaryGap;

  /** Required Double containing the Detection minimum distance */
  private Double MinimumDistance;

  /** Optional Double containing the RMS */
  private Double RMS;

  /** Optional String containing the Quality flag */
  private String Quality;

  /** Optional Double containing the bayesan depth */
  private Double BayesianDepth;

  /** Optional Double containing the bayesian range */
  private Double BayesianRange;

  /** Optional Double containing the depth importance */
  private Double DepthImportance;

  /** Optional String containing the locator exit code */
  private String LocatorExitCode;

  /** Optional error ellipse */
  public ErrorEllipse ErrorEllipse;

  /** The constructor for the LocationResult class. Initializes members to null values. */
  public LocationResult() {
    ID = null;
    Hypocenter = null;
    SupportingData = null;
    NumberOfAssociatedStations = null;
    NumberOfAssociatedPhases = null;
    NumberOfUsedStations = null;
    NumberOfUsedPhases = null;
    Gap = null;
    SecondaryGap = null;
    MinimumDistance = null;
    RMS = null;
    Quality = null;
    BayesianDepth = null;
    BayesianRange = null;
    DepthImportance = null;
    LocatorExitCode = null;
    ErrorEllipse = null;
  }

  /**
   * Advanced constructor
   *
   * <p>The advanced constructor for the LocationResult class. Initializes members to provided
   * values.
   *
   * @param newID - A String containing the optional ID
   * @param newLatitude - A Double containing the latitude to use
   * @param newLongitude - A Double containing the longitude to use
   * @param newTime - A Date containing the origin time to use
   * @param newDepth - A Double containing the depth to use
   * @param newLatitudeError - A Double containing the latitude error to use, null to omit
   * @param newLongitudeError - A Double containing the longitude error to use, null to omit
   * @param newTimeError - A Double containing the new time error to use, null to omit
   * @param newDepthError - A Double containing the depth error to use, null to omit
   * @param newSupportingData - A ArrayList&lt;Pick&gt; newPickData containing the data that went
   *     into this location
   * @param newAssociatedStations - An Integer containing the number of associated stations, null to
   *     omit
   * @param newAssociatedPhases - An Integercontaining the number of associated phases, null to omit
   * @param newUsedStations - An Integer containing the number of used stations, null to omit
   * @param newUsedPhases - An Integer containing the number of used phases, null to omit
   * @param newGap - A Double containing the Gap to use, null to omit
   * @param newSecondaryGap - A Double containing the secondary Gap to use, null to omit
   * @param newMinimumDistance - A Double containing the minimum distance to use, null to omit
   * @param newRMS - A Double containing the RMS to use, null to omit
   * @param newQuality - A String containing the Quality to use, null to omit
   * @param newBayesianDepth - A Double containing the bayesian depth to use, null to omit
   * @param newBayesianRange - A Double containing the bayesian range to use, null to omit
   * @param newDepthImportance - A Double containing the depth importance to use, null to omit
   * @param newLocatorExitCode - A String containing the locator exit code, null to omit
   * @param newE0Error - A Double containing the length of the first axis of the error ellipsoid in
   *     kilometers
   * @param newE0Azimuth - A Double containing the azimuth of the first axis of the error ellipsoid
   *     in degrees
   * @param newE0Dip - A Double containing the dip of the first axis of error ellipsoid in degrees.
   * @param newE1Error - A Double containing the length of the second axis of the error ellipsoid in
   *     kilometers
   * @param newE1Azimuth - A Double containing the azimuth of the second axis of the error ellipsoid
   *     in degrees
   * @param newE1Dip - A Double containing the dip of the second axis of error ellipsoid in degrees.
   * @param newE2Error - A Double containing the length of the third axis of the error ellipsoid in
   *     kilometers
   * @param newE2Azimuth - A Double containing the the azimuth of the third axis of the error
   *     ellipsoid in degrees
   * @param newE2Dip - A Double containing the dip of the third axis of error ellipsoid in degrees.
   * @param newMaximumHorizontalProjection - A Double containing the horizontal projection of the
   *     error ellipsoid in kilometers
   * @param newMaximumVerticalProjection - A Double containing the vertical projection of the error
   *     ellipsoid in km in kilometers
   * @param newEquivalentHorizontalRadius - A Double containing the equivalent radius of the
   *     horizontal error ellipsoid in kilometers
   */
  public LocationResult(
      String newID,
      Double newLatitude,
      Double newLongitude,
      Date newTime,
      Double newDepth,
      Double newLatitudeError,
      Double newLongitudeError,
      Double newTimeError,
      Double newDepthError,
      ArrayList<Pick> newSupportingData,
      Integer newAssociatedStations,
      Integer newAssociatedPhases,
      Integer newUsedStations,
      Integer newUsedPhases,
      Double newGap,
      Double newSecondaryGap,
      Double newMinimumDistance,
      Double newRMS,
      String newQuality,
      Double newBayesianDepth,
      Double newBayesianRange,
      Double newDepthImportance,
      String newLocatorExitCode,
      Double newE0Error,
      Double newE0Azimuth,
      Double newE0Dip,
      Double newE1Error,
      Double newE1Azimuth,
      Double newE1Dip,
      Double newE2Error,
      Double newE2Azimuth,
      Double newE2Dip,
      Double newMaximumHorizontalProjection,
      Double newMaximumVerticalProjection,
      Double newEquivalentHorizontalRadius) {

    this(
        newID,
        new Hypocenter(
            newLatitude,
            newLongitude,
            newTime,
            newDepth,
            newLatitudeError,
            newLongitudeError,
            newTimeError,
            newDepthError),
        newSupportingData,
        newAssociatedStations,
        newAssociatedPhases,
        newUsedStations,
        newUsedPhases,
        newGap,
        newSecondaryGap,
        newMinimumDistance,
        newRMS,
        newQuality,
        newBayesianDepth,
        newBayesianRange,
        newDepthImportance,
        newLocatorExitCode,
        new ErrorEllipse(
            newE0Error,
            newE0Azimuth,
            newE0Dip,
            newE1Error,
            newE1Azimuth,
            newE1Dip,
            newE2Error,
            newE2Azimuth,
            newE2Dip,
            newMaximumHorizontalProjection,
            newMaximumVerticalProjection,
            newEquivalentHorizontalRadius));
  }

  /**
   * Alternate Advanced constructor
   *
   * <p>The alternate advanced constructor for the LocationResult class. Initializes members to
   * provided values.
   *
   * @param newID - A String containing the optional ID
   * @param newLatitude - A Double containing the latitude to use
   * @param newLongitude - A Double containing the longitude to use
   * @param newTime - A Date containing the origin time to use
   * @param newDepth - A Double containing the depth to use
   * @param newLatitudeError - A Double containing the latitude error to use, null to omit
   * @param newLongitudeError - A Double containing the longitude error to use, null to omit
   * @param newTimeError - A Double containing the new time error to use, null to omit
   * @param newDepthError - A Double containing the depth error to use, null to omit
   * @param newSupportingData - A ArrayList&lt;Pick&gt; newPickData containing the data that went
   *     into this location
   */
  public LocationResult(
      String newID,
      Double newLatitude,
      Double newLongitude,
      Date newTime,
      Double newDepth,
      Double newLatitudeError,
      Double newLongitudeError,
      Double newTimeError,
      Double newDepthError,
      ArrayList<Pick> newSupportingData) {
    this(
        newID,
        new Hypocenter(
            newLatitude,
            newLongitude,
            newTime,
            newDepth,
            newLatitudeError,
            newLongitudeError,
            newTimeError,
            newDepthError),
        newSupportingData,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null);
  }

  /**
   * Alternate Advanced constructor
   *
   * <p>The alternate advanced constructor for the LocationResult class. Initializes members to
   * provided values.
   *
   * @param newHypocenter - A Hypocenter containing the Hypocenter to use
   * @param newSupportingData - A ArrayList&lt;Pick&gt; newPickData containing the data that went
   *     into this location
   */
  public LocationResult(Hypocenter newHypocenter, ArrayList<Pick> newSupportingData) {

    this(
        null,
        newHypocenter,
        newSupportingData,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null);
  }

  /**
   * Alternate advanced constructor
   *
   * <p>The alternate advanced constructor for the LocationResult class. Initializes members to
   * provided values.
   *
   * @param newID - A String containing the optional ID
   * @param newHypocenter - A Hypocenter containing the Hypocenter to use
   * @param newSupportingData - A ArrayList&lt;Pick&gt; newPickData containing the data that went
   *     into this location
   * @param newAssociatedStations - An Integer containing the number of associated stations, null to
   *     omit
   * @param newAssociatedPhases - An Integercontaining the number of associated phases, null to omit
   * @param newUsedStations - An Integer containing the number of used stations, null to omit
   * @param newUsedPhases - An Integer containing the number of used phases, null to omit
   * @param newGap - A Double containing the Gap to use, null to omit
   * @param newSecondaryGap - A Double containing the secondary Gap to use, null to omit
   * @param newMinimumDistance - A Double containing the minimum distance to use, null to omit
   * @param newRMS - A Double containing the RMS to use, null to omit
   * @param newQuality - A String containing the Quality to use, null to omit
   * @param newBayesianDepth - A Double containing the bayesian depth to use, null to omit
   * @param newBayesianRange - A Double containing the bayesian range to use, null to omit
   * @param newDepthImportance - A Double containing the depth importance to use, null to omit
   * @param newLocatorExitCode - A String containing the locator exit code, null to omit
   * @param newErrorEllipse - An ErrorEllipse containing the error ellipse to use, null to omit
   */
  public LocationResult(
      String newID,
      Hypocenter newHypocenter,
      ArrayList<Pick> newSupportingData,
      Integer newAssociatedStations,
      Integer newAssociatedPhases,
      Integer newUsedStations,
      Integer newUsedPhases,
      Double newGap,
      Double newSecondaryGap,
      Double newMinimumDistance,
      Double newRMS,
      String newQuality,
      Double newBayesianDepth,
      Double newBayesianRange,
      Double newDepthImportance,
      String newLocatorExitCode,
      ErrorEllipse newErrorEllipse) {

    reload(
        newID,
        newHypocenter,
        newSupportingData,
        newAssociatedStations,
        newAssociatedPhases,
        newUsedStations,
        newUsedPhases,
        newGap,
        newSecondaryGap,
        newMinimumDistance,
        newRMS,
        newQuality,
        newBayesianDepth,
        newBayesianRange,
        newDepthImportance,
        newLocatorExitCode,
        newErrorEllipse);
  }

  /**
   * Reload Function
   *
   * <p>The reload function for the LocationResult class. Initializes members to provided values.
   *
   * @param newID - A String containing the optional ID
   * @param newHypocenter - A Hypocenter containing the Hypocenter to use
   * @param newSupportingData - A ArrayList&lt;Pick&gt; newPickData containing the data that went
   *     into this location
   * @param newAssociatedStations - An Integer containing the number of associated stations, null to
   *     omit
   * @param newAssociatedPhases - An Integercontaining the number of associated phases, null to omit
   * @param newUsedStations - An Integer containing the number of used stations, null to omit
   * @param newUsedPhases - An Integer containing the number of used phases, null to omit
   * @param newGap - A Double containing the Gap to use, null to omit
   * @param newSecondaryGap - A Double containing the secondary Gap to use, null to omit
   * @param newMinimumDistance - A Double containing the minimum distance to use, null to omit
   * @param newRMS - A Double containing the RMS to use, null to omit
   * @param newQuality - A String containing the Quality to use, null to omit
   * @param newBayesianDepth - A Double containing the bayesian depth to use, null to omit
   * @param newBayesianRange - A Double containing the bayesian range to use, null to omit
   * @param newDepthImportance - A Double containing the depth importance to use, null to omit
   * @param newLocatorExitCode - A String containing the locator exit code, null to omit
   * @param newErrorEllipse - An ErrorEllipse containing the error ellipse to use, null to omit
   */
  public void reload(
      String newID,
      Hypocenter newHypocenter,
      ArrayList<Pick> newSupportingData,
      Integer newAssociatedStations,
      Integer newAssociatedPhases,
      Integer newUsedStations,
      Integer newUsedPhases,
      Double newGap,
      Double newSecondaryGap,
      Double newMinimumDistance,
      Double newRMS,
      String newQuality,
      Double newBayesianDepth,
      Double newBayesianRange,
      Double newDepthImportance,
      String newLocatorExitCode,
      ErrorEllipse newErrorEllipse) {

    ID = newID;
    Hypocenter = newHypocenter;
    SupportingData = newSupportingData;
    NumberOfAssociatedStations = newAssociatedStations;
    NumberOfAssociatedPhases = newAssociatedPhases;
    NumberOfUsedStations = newUsedStations;
    NumberOfUsedPhases = newUsedPhases;
    Gap = newGap;
    SecondaryGap = newSecondaryGap;
    MinimumDistance = newMinimumDistance;
    RMS = newRMS;
    Quality = newQuality;
    BayesianDepth = newBayesianDepth;
    BayesianRange = newBayesianRange;
    DepthImportance = newDepthImportance;
    LocatorExitCode = newLocatorExitCode;
    ErrorEllipse = newErrorEllipse;
  }

  /**
   * Constructs the class from a JSONObject, populating members
   *
   * @param newJSONObject - A JSONObject.
   */
  public LocationResult(JSONObject newJSONObject) {
    // Required values
    // Hypocenter
    if (newJSONObject.containsKey(HYPOCENTER_KEY)) {
      Hypocenter = new Hypocenter((JSONObject) newJSONObject.get(HYPOCENTER_KEY));
    } else {
      Hypocenter = null;
    }

    // associated data
    if (newJSONObject.containsKey(SUPPORTINGDATA_KEY)) {

      SupportingData = new ArrayList<Pick>();

      // get the array
      JSONArray dataArray = (JSONArray) newJSONObject.get(SUPPORTINGDATA_KEY);

      if ((dataArray != null) && (!dataArray.isEmpty())) {

        // go through the whole array
        for (int i = 0; i < dataArray.size(); i++) {

          // add to vector
          SupportingData.add(new Pick((JSONObject) dataArray.get(i)));
        }
      }
    } else {
      SupportingData = null;
    }

    // optional values
    // ID
    if (newJSONObject.containsKey(ID_KEY)) {
      ID = newJSONObject.get(ID_KEY).toString();
    } else {
      ID = null;
    }

    // associated stations
    if (newJSONObject.containsKey(ASSOCIATEDSTATIONS_KEY)) {
      NumberOfAssociatedStations = ((Long) newJSONObject.get(ASSOCIATEDSTATIONS_KEY)).intValue();
    } else {
      NumberOfAssociatedStations = null;
    }

    // associated phases
    if (newJSONObject.containsKey(ASSOCIATEDPHASES_KEY)) {
      NumberOfAssociatedPhases = ((Long) newJSONObject.get(ASSOCIATEDPHASES_KEY)).intValue();
    } else {
      NumberOfAssociatedPhases = null;
    }

    // used stations
    if (newJSONObject.containsKey(USEDSTATIONS_KEY)) {
      NumberOfUsedStations = ((Long) newJSONObject.get(USEDSTATIONS_KEY)).intValue();
    } else {
      NumberOfUsedStations = null;
    }

    // used phases
    if (newJSONObject.containsKey(USEDPHASES_KEY)) {
      NumberOfUsedPhases = ((Long) newJSONObject.get(USEDPHASES_KEY)).intValue();
    } else {
      NumberOfUsedPhases = null;
    }

    // Gap
    if (newJSONObject.containsKey(GAP_KEY)) {
      Gap = (double) newJSONObject.get(GAP_KEY);
    } else {
      Gap = null;
    }

    // secondary Gap
    if (newJSONObject.containsKey(SECONDARYGAP_KEY)) {
      SecondaryGap = (double) newJSONObject.get(SECONDARYGAP_KEY);
    } else {
      SecondaryGap = null;
    }

    // MinimumDistance
    if (newJSONObject.containsKey(MINIMUMDISTANCE_KEY)) {
      MinimumDistance = (double) newJSONObject.get(MINIMUMDISTANCE_KEY);
    } else {
      MinimumDistance = null;
    }

    // RMS
    if (newJSONObject.containsKey(RMS_KEY)) {
      RMS = (double) newJSONObject.get(RMS_KEY);
    } else {
      RMS = null;
    }

    // Quality
    if (newJSONObject.containsKey(QUALITY_KEY)) {
      Quality = (String) newJSONObject.get(QUALITY_KEY);
    } else {
      Quality = null;
    }

    // baysian depth
    if (newJSONObject.containsKey(BAYESIANDEPTH_KEY)) {
      BayesianDepth = (double) newJSONObject.get(BAYESIANDEPTH_KEY);
    } else {
      BayesianDepth = null;
    }

    // baysian range
    if (newJSONObject.containsKey(BAYESIANRANGE_KEY)) {
      BayesianRange = (double) newJSONObject.get(BAYESIANRANGE_KEY);
    } else {
      BayesianRange = null;
    }

    // depth importance
    if (newJSONObject.containsKey(DEPTHIMPORTANCE_KEY)) {
      DepthImportance = (double) newJSONObject.get(DEPTHIMPORTANCE_KEY);
    } else {
      DepthImportance = null;
    }

    // LocatorExitCode
    if (newJSONObject.containsKey(LOCATOREXITCODE_KEY)) {
      LocatorExitCode = (String) newJSONObject.get(LOCATOREXITCODE_KEY);
    } else {
      LocatorExitCode = null;
    }

    // error ellipse
    if (newJSONObject.containsKey(ERRORELLIPSE_KEY)) {
      ErrorEllipse = new ErrorEllipse((JSONObject) newJSONObject.get(ERRORELLIPSE_KEY));
    } else {
      ErrorEllipse = null;
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

    String jsonID = getID();
    Hypocenter jsonHypocenter = getHypocenter();
    ArrayList<Pick> jsonSupportingData = getSupportingData();
    Integer jsonNumberOfAssociatedStations = getNumberOfAssociatedStations();
    Integer jsonNumberOfAssociatedPhases = getNumberOfAssociatedPhases();
    Integer jsonNumberOfUsedStations = getNumberOfUsedStations();
    Integer jsonNumberOfUsedPhases = getNumberOfUsedPhases();
    Double jsonGap = getGap();
    Double jsonSecondaryGap = getSecondaryGap();
    Double jsonMinimumDistance = getMinimumDistance();
    Double jsonRMS = getRMS();
    String jsonQuality = getQuality();
    Double jsonBayesianDepth = getBayesianDepth();
    Double jsonBayesianRange = getBayesianRange();
    Double jsonDepthImportance = getDepthImportance();
    String jsonLocatorExitCode = getLocatorExitCode();
    ErrorEllipse jsonErrorEllipse = getErrorEllipse();

    // ID
    if (jsonID != null) {
      newJSONObject.put(ID_KEY, jsonID);
    }

    // Hypocenter
    if (jsonHypocenter != null) {
      newJSONObject.put(HYPOCENTER_KEY, jsonHypocenter.toJSON());
    }

    // SupportingData
    if ((jsonSupportingData != null) && (!jsonSupportingData.isEmpty())) {
      JSONArray dataArray = new JSONArray();

      // enumerate through the whole arraylist
      for (Iterator<Pick> pickIterator = jsonSupportingData.iterator(); pickIterator.hasNext(); ) {

        // convert pick to JSON object
        JSONObject pickObject = ((Pick) pickIterator.next()).toJSON();

        dataArray.add(pickObject);
      }

      if (!dataArray.isEmpty()) {
        newJSONObject.put(SUPPORTINGDATA_KEY, dataArray);
      }
    }

    // number of associated stations
    if (jsonNumberOfAssociatedStations != null) {
      newJSONObject.put(ASSOCIATEDSTATIONS_KEY, jsonNumberOfAssociatedStations);
    }

    // number of associated phases
    if (jsonNumberOfAssociatedPhases != null) {
      newJSONObject.put(ASSOCIATEDPHASES_KEY, jsonNumberOfAssociatedPhases);
    }

    // number of used stations
    if (jsonNumberOfUsedStations != null) {
      newJSONObject.put(USEDSTATIONS_KEY, jsonNumberOfUsedStations);
    }

    // number of used phases
    if (jsonNumberOfUsedPhases != null) {
      newJSONObject.put(USEDPHASES_KEY, jsonNumberOfUsedPhases);
    }

    // Gap
    if (jsonGap != null) {
      newJSONObject.put(GAP_KEY, jsonGap);
    }

    // secondary Gap
    if (jsonSecondaryGap != null) {
      newJSONObject.put(SECONDARYGAP_KEY, jsonSecondaryGap);
    }

    // MinimumDistance
    if (jsonMinimumDistance != null) {
      newJSONObject.put(MINIMUMDISTANCE_KEY, jsonMinimumDistance);
    }

    // RMS
    if (jsonRMS != null) {
      newJSONObject.put(RMS_KEY, jsonRMS);
    }

    // Quality
    if (jsonQuality != null) {
      newJSONObject.put(QUALITY_KEY, jsonQuality);
    }

    // bayesian depth
    if (jsonBayesianDepth != null) {
      newJSONObject.put(BAYESIANDEPTH_KEY, jsonBayesianDepth);
    }

    // bayesian range
    if (jsonBayesianRange != null) {
      newJSONObject.put(BAYESIANRANGE_KEY, jsonBayesianRange);
    }

    // depth importance
    if (jsonDepthImportance != null) {
      newJSONObject.put(DEPTHIMPORTANCE_KEY, jsonDepthImportance);
    }

    // locator exit code
    if (jsonLocatorExitCode != null) {
      newJSONObject.put(LOCATOREXITCODE_KEY, jsonLocatorExitCode);
    }

    // error ellipse
    if (jsonErrorEllipse != null) {
      newJSONObject.put(ERRORELLIPSE_KEY, jsonErrorEllipse.toJSON());
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

    Hypocenter jsonHypocenter = getHypocenter();
    ArrayList<Pick> jsonSupportingData = getSupportingData();
    // Integer jsonNumberOfAssociatedStations =
    // getNumberOfAssociatedStations();
    // Integer jsonNumberOfAssociatedPhases = getNumberOfAssociatedPhases();
    // Integer jsonNumberOfUsedStations = getNumberOfUsedStations();
    // Integer jsonNumberOfUsedPhases = getNumberOfUsedPhases();
    Double jsonGap = getGap();
    Double jsonSecondaryGap = getSecondaryGap();
    Double jsonMinimumDistance = getMinimumDistance();
    // Double jsonRMS = getRMS();
    // String jsonQuality = getQuality();
    // Double jsonBayesianDepth = getBayesianDepth();
    // Double jsonBayesianRange = getBayesianRange();
    // Double jsonDepthImportance = getDepthImportance();
    String jsonLocatorExitCode = getLocatorExitCode();
    ErrorEllipse jsonErrorEllipse = getErrorEllipse();

    ArrayList<String> errorList = new ArrayList<String>();

    // Hypocenter
    if (jsonHypocenter == null) {
      // Hypocenter not found
      errorList.add("No Hypocenter in LocationResult Class.");
    } else if (!jsonHypocenter.isValid()) {
      // Hypocenter invalid
      errorList.add("Invalid Hypocenter in LocationResult Class.");
    }

    // Data
    // Picks
    if ((jsonSupportingData != null) && (!jsonSupportingData.isEmpty())) {

      // enumerate through the whole arraylist
      for (Iterator<Pick> pickIterator = jsonSupportingData.iterator(); pickIterator.hasNext(); ) {

        // convert pick to JSON object
        Pick jsonPick = ((Pick) pickIterator.next());

        if (!jsonPick.isValid()) {
          ArrayList<String> pickErrorList = jsonPick.getErrors();

          // combine the errors into a single string
          String errorString = new String();
          for (int i = 0; i < pickErrorList.size(); i++) {
            errorString += " " + pickErrorList.get(i);
          }
          errorList.add("Invalid Pick in SupportingData in LocationRequest Class: " + errorString);
          break;
        }
      }
    } else {
      // Hypocenter not found
      errorList.add("No pick data in LocationResult Class.");
    }

    // Gap
    if (jsonGap != null) {
      if ((jsonGap < 0) || (jsonGap > 360)) {
        // invalid Magnitude
        errorList.add("Gap in LocationResult Class not in the range of 0 to 360.");
      }
    }

    // Gap
    if (jsonSecondaryGap != null) {
      if ((jsonSecondaryGap < 0) || (jsonSecondaryGap > 360)) {
        // invalid Magnitude
        errorList.add("Secondary Gap in LocationResult Class not in the range of 0 to 360.");
      }
    }

    // MinimumDistance
    if (jsonMinimumDistance != null) {
      if (jsonMinimumDistance < 0) {
        // invalid minimum distance
        errorList.add("MinimumDistance in LocationResult Class is not greater than 0.");
      }
    }

    // locator exit code
    if (jsonLocatorExitCode != null) {
      boolean match = false;
      if (jsonLocatorExitCode.equals("Success")) {
        match = true;
      } else if (jsonLocatorExitCode.equals("DidNotMove")) {
        match = true;
      } else if (jsonLocatorExitCode.equals("ErrorsNotComputed")) {
        match = true;
      } else if (jsonLocatorExitCode.equals("Failed")) {
        match = true;
      } else if (jsonLocatorExitCode.equals("Unknown")) {
        match = true;
      } else {
        match = false;
      }

      if (!match) {
        // invalid eventType
        errorList.add("Invalid locator exit code in LocationResult Class.");
      }
    }

    // error ellipse
    if (jsonErrorEllipse != null) {
      if (!jsonErrorEllipse.isValid()) {
        // Hypocenter invalid
        errorList.add("Invalid ErrorEllipse in LocationResult Class.");
      }
    }

    // success
    return (errorList);
  }

  /**
   * Converts the contents of the class to a csv formatted summary string
   *
   * @return Returns a String containing the csv formatted summary string
   */
  public String toCSV() {
    String csvString = "";

    if (getID() != null) {
      csvString += getID() + ",";
    } else {
      csvString += "null,";
    }

    csvString += String.format("%f,", getHypocenter().Latitude);
    csvString += String.format("%f,", getHypocenter().Longitude);
    csvString += String.format("%f,", getHypocenter().Depth);
    csvString += String.format("%f,", (getHypocenter().Time.getTime() * 0.001d));

    if (getRMS() != null) {
      csvString += String.format("%f,", getRMS());
    } else {
      csvString += "0.0,";
    }

    if (getMinimumDistance() != null) {
      csvString += String.format("%f,", getMinimumDistance());
    } else {
      csvString += "0.0,";
    }

    if (getGap() != null) {
      csvString += String.format("%f,", getGap());
    } else {
      csvString += "0.0,";
    }

    if (getNumberOfAssociatedPhases() != null) {
      csvString += String.format("%d,", getNumberOfAssociatedPhases());
    } else {
      csvString += "0,";
    }

    csvString += "0.0,"; // mag
    csvString += "0.0,"; // detectiontime

    return csvString;
  }

  /** @return the ID */
  public String getID() {
    return ID;
  }

  /** @return the Hypocenter */
  public Hypocenter getHypocenter() {
    return Hypocenter;
  }

  /** @return the SupportingData */
  public ArrayList<Pick> getSupportingData() {
    return SupportingData;
  }

  /** @return the NumberOfAssociatedStations */
  public Integer getNumberOfAssociatedStations() {
    return NumberOfAssociatedStations;
  }

  /** @return the NumberOfAssociatedPhases */
  public Integer getNumberOfAssociatedPhases() {
    return NumberOfAssociatedPhases;
  }

  /** @return the NumberOfUsedStations */
  public Integer getNumberOfUsedStations() {
    return NumberOfUsedStations;
  }

  /** @return the NumberOfUsedPhases */
  public Integer getNumberOfUsedPhases() {
    return NumberOfUsedPhases;
  }

  /** @return the Gap */
  public Double getGap() {
    return Gap;
  }

  /** @return the SecondaryGap */
  public Double getSecondaryGap() {
    return SecondaryGap;
  }

  /** @return the MinimumDistance */
  public Double getMinimumDistance() {
    return MinimumDistance;
  }

  /** @return the RMS */
  public Double getRMS() {
    return RMS;
  }

  /** @return the Quality */
  public String getQuality() {
    return Quality;
  }

  /** @return the BayesianDepth */
  public Double getBayesianDepth() {
    return BayesianDepth;
  }

  /** @return the BayesianRange */
  public Double getBayesianRange() {
    return BayesianRange;
  }

  /** @return the DepthImportance */
  public Double getDepthImportance() {
    return DepthImportance;
  }

  /** @return the LocatorExitCode */
  public String getLocatorExitCode() {
    return LocatorExitCode;
  }

  /** @return the ErrorEllipse */
  public ErrorEllipse getErrorEllipse() {
    return ErrorEllipse;
  }

  /** @param ID the ID to set */
  public void setID(String ID) {
    this.ID = ID;
  }

  /** @param Hypocenter the Hypocenter to set */
  public void setHypocenter(Hypocenter Hypocenter) {
    this.Hypocenter = Hypocenter;
  }

  /** @param SupportingData the SupportingData to set */
  public void setSupportingData(ArrayList<Pick> SupportingData) {
    this.SupportingData = SupportingData;
  }

  /** @param NumberOfAssociatedStations the NumberOfAssociatedStations to set */
  public void setNumberOfAssociatedStations(Integer NumberOfAssociatedStations) {
    this.NumberOfAssociatedStations = NumberOfAssociatedStations;
  }

  /** @param NumberOfAssociatedPhases the NumberOfAssociatedPhases to set */
  public void setNumberOfAssociatedPhases(Integer NumberOfAssociatedPhases) {
    this.NumberOfAssociatedPhases = NumberOfAssociatedPhases;
  }

  /** @param NumberOfUsedStations the NumberOfUsedStations to set */
  public void setNumberOfUsedStations(Integer NumberOfUsedStations) {
    this.NumberOfUsedStations = NumberOfUsedStations;
  }

  /** @param NumberOfUsedPhases the NumberOfUsedPhases to set */
  public void setNumberOfUsedPhases(Integer NumberOfUsedPhases) {
    this.NumberOfUsedPhases = NumberOfUsedPhases;
  }

  /** @param Gap the Gap to set */
  public void setGap(Double Gap) {
    this.Gap = Gap;
  }

  /** @param SecondaryGap the SecondaryGap to set */
  public void setSecondaryGap(Double SecondaryGap) {
    this.SecondaryGap = SecondaryGap;
  }

  /** @param MinimumDistance the MinimumDistance to set */
  public void setMinimumDistance(Double MinimumDistance) {
    this.MinimumDistance = MinimumDistance;
  }

  /** @param RMS the RMS to set */
  public void setRms(Double RMS) {
    this.RMS = RMS;
  }

  /** @param Quality the Quality to set */
  public void setQuality(String Quality) {
    this.Quality = Quality;
  }

  /** @param BayesianDepth the BayesianDepth to set */
  public void setBayesianDepth(Double BayesianDepth) {
    this.BayesianDepth = BayesianDepth;
  }

  /** @param BayesianRange the BayesianRange to set */
  public void setBayesianRange(Double BayesianRange) {
    this.BayesianRange = BayesianRange;
  }

  /** @param DepthImportance the DepthImportance to set */
  public void setDepthImportance(Double DepthImportance) {
    this.DepthImportance = DepthImportance;
  }

  /** @param LocatorExitCode the LocatorExitCode to set */
  public void setLocatorExitCode(String LocatorExitCode) {
    this.LocatorExitCode = LocatorExitCode;
  }

  /** @param ErrorEllipse the ErrorEllipse to set */
  public void setErrorEllipse(ErrorEllipse ErrorEllipse) {
    this.ErrorEllipse = ErrorEllipse;
  }
}
