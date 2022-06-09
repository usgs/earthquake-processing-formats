package gov.usgs.processingformats;

import java.util.ArrayList;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TravelTimePlotRequestTest {

  public static String TRAVELTIMEPLOTREQUEST_STRING =
      "{\"Source\": {\"Latitude\":45.905,\"Longitude\":-112.778,\"Depth\":15.0},"
          + "\"ConvertTectonic\":true,\"ReturnAllPhases\":true,"
          + "\"EarthModel\":\"AK135\","
          + "\"ReturnBackBranches\":true,\"PhaseTypes\":[\"P\",\"S\",\"PDiff\"],"
          + "\"MaximumDistance\":90.0,\"DistanceStep\":1.0,\"MaximumTravelTime\":2700.0,"
          + "\"Response\":[{\"Phase\":\"Pg\","
          + "\"Samples\":[{\"Distance\":1.2,\"Observability\":0.34,"
          + "\"StatisticalSpread\":1.5,\"TravelTime\":22.456},"
          + "{\"Distance\":10.5,\"Observability\":1.63,"
          + "\"StatisticalSpread\":2.1,\"TravelTime\":72.654}]}]}";

  public static final String SOURCE_STRING =
      "{\"Latitude\":45.905,\"Longitude\":-112.778,\"Depth\":15.0}";

  public static final String RESPONSE_STRING =
      "{\"Phase\":\"Pg\","
          + "\"Samples\":[{\"Distance\":1.2,\"Observability\":0.34,"
          + "\"StatisticalSpread\":1.5,\"TravelTime\":22.456},"
          + "{\"Distance\":10.5,\"Observability\":1.63,"
          + "\"StatisticalSpread\":2.1,\"TravelTime\":72.654}]}";

  public static String EARTHMODEL = "AK135";
  public static String PHASETYPE1 = "P";
  public static String PHASETYPE2 = "S";
  public static String PHASETYPE3 = "PDiff";
  public static boolean RETURNALLPHASES = true;
  public static boolean RETURNBACKBRANCHES = true;
  public static boolean CONVERTTECTONIC = true;
  public static double MAXIMUMDISTANCE = 90.0;
  public static double DISTANCESTEP = 1.0;
  public static double MAXIMUMTRAVELTIME = 2700.00;

  /** Able to write a JSON string */
  @Test
  public void writesJSON() {

    // standard request
    TravelTimePlotRequest travelTimeRequestObject =
        new TravelTimePlotRequest(
            buildSource(),
            EARTHMODEL,
            buildPhaseTypes(),
            RETURNALLPHASES,
            RETURNBACKBRANCHES,
            CONVERTTECTONIC,
            MAXIMUMDISTANCE,
            DISTANCESTEP,
            MAXIMUMTRAVELTIME,
            buildResponse());

    // write out to a string
    String jsonStandardString = Utility.toJSONString(travelTimeRequestObject.toJSON());

    // check the data
    try {
      checkData(
          new TravelTimePlotRequest(Utility.fromJSONString(jsonStandardString)), "WritesJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Able to read a JSON string */
  @Test
  public void readsJSON() {

    // standard request
    try {
      checkData(
          new TravelTimePlotRequest(Utility.fromJSONString(TRAVELTIMEPLOTREQUEST_STRING)),
          "ReadsJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Reload function fills in members correctly */
  @Test
  public void reload() {

    // use constructor
    TravelTimePlotRequest travelTimeRequestObject = new TravelTimePlotRequest();

    travelTimeRequestObject.reload(
        buildSource(),
        EARTHMODEL,
        buildPhaseTypes(),
        RETURNALLPHASES,
        RETURNBACKBRANCHES,
        CONVERTTECTONIC,
        MAXIMUMDISTANCE,
        DISTANCESTEP,
        MAXIMUMTRAVELTIME,
        buildResponse());

    // check data values
    checkData(travelTimeRequestObject, "Reload Function");
  }

  /** Set functions fill in members correctly */
  @Test
  public void setters() {

    // use constructor
    TravelTimePlotRequest travelTimeRequestObject = new TravelTimePlotRequest();

    travelTimeRequestObject.Source = buildSource();
    travelTimeRequestObject.EarthModel = EARTHMODEL;
    travelTimeRequestObject.PhaseTypes = buildPhaseTypes();
    travelTimeRequestObject.ReturnAllPhases = RETURNALLPHASES;
    travelTimeRequestObject.ReturnBackBranches = RETURNBACKBRANCHES;
    travelTimeRequestObject.ConvertTectonic = CONVERTTECTONIC;
    travelTimeRequestObject.MaximumDistance = MAXIMUMDISTANCE;
    travelTimeRequestObject.DistanceStep = DISTANCESTEP;
    travelTimeRequestObject.MaximumTravelTime = MAXIMUMTRAVELTIME;
    travelTimeRequestObject.Response = buildResponse();

    // check data values
    checkData(travelTimeRequestObject, "Set Functions");
  }

  /** Copy constructor fills in members correctly */
  @Test
  public void copyConstructor() {

    // use constructor
    TravelTimePlotRequest travelTimeRequestObject =
        new TravelTimePlotRequest(
            buildSource(),
            EARTHMODEL,
            buildPhaseTypes(),
            RETURNALLPHASES,
            RETURNBACKBRANCHES,
            CONVERTTECTONIC,
            MAXIMUMDISTANCE,
            DISTANCESTEP,
            MAXIMUMTRAVELTIME,
            buildResponse());

    TravelTimePlotRequest travelTimeRequestObject2 =
        new TravelTimePlotRequest(travelTimeRequestObject);

    // check data values
    checkData(travelTimeRequestObject2, "Copy Constructor");
  }

  /** Able to run validation function */
  @Test
  public void validate() {

    // use constructor
    TravelTimePlotRequest travelTimeRequestObject =
        new TravelTimePlotRequest(
            buildSource(),
            EARTHMODEL,
            buildPhaseTypes(),
            RETURNALLPHASES,
            RETURNBACKBRANCHES,
            CONVERTTECTONIC,
            MAXIMUMDISTANCE,
            DISTANCESTEP,
            MAXIMUMTRAVELTIME,
            buildResponse());

    // Successful validation
    boolean rc = travelTimeRequestObject.isValid();

    ArrayList<String> errors = travelTimeRequestObject.getErrors();

    // check return code
    Assertions.assertEquals(true, rc, "Successful Validation");

    // use constructor
    TravelTimePlotRequest badTravelTimePlotRequestObject =
        new TravelTimePlotRequest(null, null, null, null, null, null, null, null, null, null);

    rc = badTravelTimePlotRequestObject.isValid();

    // check return code
    Assertions.assertEquals(false, rc, "Unsuccessful Validation");
  }

  public void checkData(TravelTimePlotRequest travelTimeRequestObject, String TestName) {

    // travelTimeRequestObject.earthModel
    if (travelTimeRequestObject.EarthModel != null) {
      Assertions.assertEquals(
          EARTHMODEL, travelTimeRequestObject.EarthModel, TestName + " Earth Model Equals");
    }

    // check travelTimeRequestObject.phaseTypes
    if ((travelTimeRequestObject.PhaseTypes != null)
        && (!travelTimeRequestObject.PhaseTypes.isEmpty())) {

      // check travelTimeRequestObject.phaseTypes[0]
      Assertions.assertEquals(
          PHASETYPE1, travelTimeRequestObject.PhaseTypes.get(0), TestName + " Phase Type 1 Equals");

      // check travelTimeRequestObject.phaseTypes[1]
      Assertions.assertEquals(
          PHASETYPE2, travelTimeRequestObject.PhaseTypes.get(1), TestName + " Phase Type 2 Equals");

      // check travelTimeRequestObject.phaseTypes[2]
      Assertions.assertEquals(
          PHASETYPE3, travelTimeRequestObject.PhaseTypes.get(2), TestName + " Phase Type 3 Equals");
    }

    // check travelTimeRequestObject.returnAllPhases
    if (travelTimeRequestObject.ReturnAllPhases != null) {
      Assertions.assertEquals(
          travelTimeRequestObject.ReturnAllPhases,
          RETURNALLPHASES,
          TestName + " Return All Phases Equals ");
    }

    // check travelTimeRequestObject.returnBackBranches
    if (travelTimeRequestObject.ReturnBackBranches != null) {
      Assertions.assertEquals(
          travelTimeRequestObject.ReturnBackBranches,
          RETURNBACKBRANCHES,
          TestName + " Return Back Branchs Equals ");
    }

    // check travelTimeRequestObject.convertTectonic
    if (travelTimeRequestObject.ConvertTectonic != null) {
      Assertions.assertEquals(
          travelTimeRequestObject.ConvertTectonic,
          CONVERTTECTONIC,
          TestName + " Convert Tectonic Equals ");
    }

    // check travelTimeRequestObject.MaximumDistance
    if (travelTimeRequestObject.MaximumDistance != null) {
      Assertions.assertEquals(
          travelTimeRequestObject.MaximumDistance,
          MAXIMUMDISTANCE,
          0,
          TestName + " Maximum Distance Equals ");
    }

    // check travelTimeRequestObject.DistanceStep
    if (travelTimeRequestObject.DistanceStep != null) {
      Assertions.assertEquals(
          travelTimeRequestObject.DistanceStep,
          DISTANCESTEP,
          0,
          TestName + " Distance Step Equals ");
    }

    // check travelTimeRequestObject.MaximumTravelTime
    if (travelTimeRequestObject.MaximumTravelTime != null) {
      Assertions.assertEquals(
          travelTimeRequestObject.MaximumTravelTime,
          MAXIMUMTRAVELTIME,
          0,
          TestName + " Maximum TravelTime Equals ");
    }
  }

  public TravelTimeSource buildSource() {
    try {
      TravelTimeSource newSource = new TravelTimeSource(Utility.fromJSONString(SOURCE_STRING));
      return (newSource);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return (null);
    }
  }

  public ArrayList<String> buildPhaseTypes() {

    ArrayList<String> phaseTypes = new ArrayList<String>();

    phaseTypes.add(PHASETYPE1);
    phaseTypes.add(PHASETYPE2);
    phaseTypes.add(PHASETYPE3);

    return (phaseTypes);
  }

  public ArrayList<TravelTimePlotDataBranch> buildResponse() {
    ArrayList<TravelTimePlotDataBranch> newResponse = new ArrayList<TravelTimePlotDataBranch>();
    try {
      newResponse.add(new TravelTimePlotDataBranch(Utility.fromJSONString(RESPONSE_STRING)));
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return (null);
    }
    return (newResponse);
  }
}
