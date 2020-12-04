package gov.usgs.processingformats;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class TravelTimeRequestTest {

  public static String TRAVELTIMEREQUEST_STRING =
      "{\"Type\":\"Standard\","
          + "\"Source\": {\"Latitude\":45.905,\"Longitude\":-112.778,\"Depth\":15.0},"
          + "\"Recievers\": [{\"ID\":\"45\",\"Elevation\":15.0,\"Latitude\":45.905,"
          + "\"Longitude\":-112.778,\"Distance\":22.123}],"
          + "\"ConvertTectonic\":true,\"ReturnAllPhases\":true,"
          + "\"EarthModel\":\"AK135\","
          + "\"ReturnBackBranches\":true,\"PhaseTypes\":[\"P\",\"S\",\"PDiff\"],"
          + "\"Response\":"
          + "[{\"ID\":\"45\",\"Elevation\":15.0,\"Latitude\":45.905,"
          + "\"Longitude\":-112.778,\"Distance\":22.123,\"Branches\":"
          + "[{\"LocationUseFlag\":true,\"DistanceDerivative\":1.2,"
          + "\"DepthDerivative\":3.45,\"AssociationWeightFlag\":true,"
          + "\"Type\":\"TTData\",\"TeleseismicPhaseGroup\":1,\"Phase\":\"Pg\","
          + "\"RayDerivative\":5.67,\"AuxiliaryPhaseGroup\":1,"
          + "\"Observability\":0.34,\"StatisticalSpread\":1.5,"
          + "\"TravelTime\":22.456}]}]}";

  public static final String SOURCE_STRING =
      "{\"Latitude\":45.905,\"Longitude\":-112.778,\"Depth\":15.0}";

  public static final String RECIEVERS_STRING =
      "{\"ID\":\"45\",\"Elevation\":15.0,\"Latitude\":45.905,"
          + "\"Longitude\":-112.778,\"Distance\":22.123}";

  public static final String RESPONSE_STRING =
      "{\"ID\":\"45\",\"Elevation\":15.0,\"Latitude\":45.905,"
          + "\"Longitude\":-112.778,\"Distance\":22.123,\"Branches\":"
          + "[{\"LocationUseFlag\":true,\"DistanceDerivative\":1.2,"
          + "\"DepthDerivative\":3.45,\"AssociationWeightFlag\":true,"
          + "\"Type\":\"TTData\",\"TeleseismicPhaseGroup\":1,\"Phase\":\"Pg\","
          + "\"RayDerivative\":5.67,\"AuxiliaryPhaseGroup\":1,"
          + "\"Observability\":0.34,\"StatisticalSpread\":1.5,"
          + "\"TravelTime\":22.456}]}";

  public static String TYPE = "Standard";

  public static String EARTHMODEL = "AK135";
  public static String PHASETYPE1 = "P";
  public static String PHASETYPE2 = "S";
  public static String PHASETYPE3 = "PDiff";
  public static boolean RETURNALLPHASES = true;
  public static boolean RETURNBACKBRANCHES = true;
  public static boolean CONVERTTECTONIC = true;

  /** Able to write a JSON string */
  @Test
  public void writesJSON() {

    // standard request
    TravelTimeRequest travelTimeRequestObject =
        new TravelTimeRequest(
            TYPE,
            buildSource(),
            buildRecievers(),
            EARTHMODEL,
            buildPhaseTypes(),
            RETURNALLPHASES,
            RETURNBACKBRANCHES,
            CONVERTTECTONIC,
            buildResponse());

    // write out to a string
    String jsonStandardString = Utility.toJSONString(travelTimeRequestObject.toJSON());

    // check the data
    try {
      checkData(new TravelTimeRequest(Utility.fromJSONString(jsonStandardString)), "WritesJSON");
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
          new TravelTimeRequest(Utility.fromJSONString(TRAVELTIMEREQUEST_STRING)), "ReadsJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Reload function fills in members correctly */
  @Test
  public void reload() {

    // use constructor
    TravelTimeRequest travelTimeRequestObject = new TravelTimeRequest();

    travelTimeRequestObject.reload(
        TYPE,
        buildSource(),
        buildRecievers(),
        EARTHMODEL,
        buildPhaseTypes(),
        RETURNALLPHASES,
        RETURNBACKBRANCHES,
        CONVERTTECTONIC,
        buildResponse());

    // check data values
    checkData(travelTimeRequestObject, "Reload Function");
  }

  /** Set functions fill in members correctly */
  @Test
  public void setters() {

    // use constructor
    TravelTimeRequest travelTimeRequestObject = new TravelTimeRequest();

    travelTimeRequestObject.Type = TYPE;
    travelTimeRequestObject.Source = buildSource();
    travelTimeRequestObject.Recievers = buildRecievers();
    travelTimeRequestObject.EarthModel = EARTHMODEL;
    travelTimeRequestObject.PhaseTypes = buildPhaseTypes();
    travelTimeRequestObject.ReturnAllPhases = RETURNALLPHASES;
    travelTimeRequestObject.ReturnBackBranches = RETURNBACKBRANCHES;
    travelTimeRequestObject.ConvertTectonic = CONVERTTECTONIC;
    travelTimeRequestObject.Response = buildResponse();

    // check data values
    checkData(travelTimeRequestObject, "Set Functions");
  }

  /** Copy constructor fills in members correctly */
  @Test
  public void copyConstructor() {

    // use constructor
    TravelTimeRequest travelTimeRequestObject =
        new TravelTimeRequest(
            TYPE,
            buildSource(),
            buildRecievers(),
            EARTHMODEL,
            buildPhaseTypes(),
            RETURNALLPHASES,
            RETURNBACKBRANCHES,
            CONVERTTECTONIC,
            buildResponse());

    TravelTimeRequest travelTimeRequestObject2 = new TravelTimeRequest(travelTimeRequestObject);

    // check data values
    checkData(travelTimeRequestObject2, "Copy Constructor");
  }

  /** Able to run validation function */
  @Test
  public void validate() {

    // use constructor
    TravelTimeRequest travelTimeRequestObject =
        new TravelTimeRequest(
            TYPE,
            buildSource(),
            buildRecievers(),
            EARTHMODEL,
            buildPhaseTypes(),
            RETURNALLPHASES,
            RETURNBACKBRANCHES,
            CONVERTTECTONIC,
            buildResponse());

    // Successful validation
    boolean rc = travelTimeRequestObject.isValid();

    ArrayList<String> errors = travelTimeRequestObject.getErrors();

    // check return code
    assertEquals("Successful Validation", true, rc);

    // use constructor
    TravelTimeRequest badTravelTimeRequestObject =
        new TravelTimeRequest(null, null, null, null, null, null, null, null, null);

    rc = badTravelTimeRequestObject.isValid();

    // check return code
    assertEquals("Unsuccessful Validation", false, rc);
  }

  public void checkData(TravelTimeRequest travelTimeRequestObject, String TestName) {

    // check travelTimeRequestObject.type
    assertNotNull(TestName + " Type exists", travelTimeRequestObject.Type);

    // check type value
    if (!(travelTimeRequestObject.Type.equals("Standard"))) {
      fail(TestName + " Type is not valid");
    }

    // travelTimeRequestObject.earthModel
    if (travelTimeRequestObject.EarthModel != null) {
      assertEquals(
          TestName + " Earth Model Equals", EARTHMODEL, travelTimeRequestObject.EarthModel);
    }

    // check travelTimeRequestObject.phaseTypes
    if ((travelTimeRequestObject.PhaseTypes != null)
        && (!travelTimeRequestObject.PhaseTypes.isEmpty())) {

      // check travelTimeRequestObject.phaseTypes[0]
      assertEquals(
          TestName + " Phase Type 1 Equals", PHASETYPE1, travelTimeRequestObject.PhaseTypes.get(0));

      // check travelTimeRequestObject.phaseTypes[1]
      assertEquals(
          TestName + " Phase Type 2 Equals", PHASETYPE2, travelTimeRequestObject.PhaseTypes.get(1));

      // check travelTimeRequestObject.phaseTypes[2]
      assertEquals(
          TestName + " Phase Type 3 Equals", PHASETYPE3, travelTimeRequestObject.PhaseTypes.get(2));
    }

    // check travelTimeRequestObject.returnAllPhases
    if (travelTimeRequestObject.ReturnAllPhases != null) {
      assertEquals(
          TestName + " Return All Phases Equals ",
          travelTimeRequestObject.ReturnAllPhases,
          RETURNALLPHASES);
    }

    // check travelTimeRequestObject.returnBackBranches
    if (travelTimeRequestObject.ReturnBackBranches != null) {
      assertEquals(
          TestName + " Return Back Branchs Equals ",
          travelTimeRequestObject.ReturnBackBranches,
          RETURNBACKBRANCHES);
    }

    // check travelTimeRequestObject.convertTectonic
    if (travelTimeRequestObject.ConvertTectonic != null) {
      assertEquals(
          TestName + " Convert Tectonic Equals ",
          travelTimeRequestObject.ConvertTectonic,
          CONVERTTECTONIC);
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

  public ArrayList<TravelTimeReciever> buildRecievers() {
    ArrayList<TravelTimeReciever> newRecievers = new ArrayList<TravelTimeReciever>();
    try {
      newRecievers.add(new TravelTimeReciever(Utility.fromJSONString(RECIEVERS_STRING)));
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return (null);
    }
    return (newRecievers);
  }

  public ArrayList<String> buildPhaseTypes() {

    ArrayList<String> phaseTypes = new ArrayList<String>();

    phaseTypes.add(PHASETYPE1);
    phaseTypes.add(PHASETYPE2);
    phaseTypes.add(PHASETYPE3);

    return (phaseTypes);
  }

  public ArrayList<TravelTimeReciever> buildResponse() {
    ArrayList<TravelTimeReciever> newResponse = new ArrayList<TravelTimeReciever>();
    try {
      newResponse.add(new TravelTimeReciever(Utility.fromJSONString(RESPONSE_STRING)));
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return (null);
    }
    return (newResponse);
  }
}
