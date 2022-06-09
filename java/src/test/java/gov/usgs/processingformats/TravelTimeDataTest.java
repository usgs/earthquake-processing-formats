package gov.usgs.processingformats;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TravelTimeDataTest {

  public static String TRAVELTIMEDATA_STRING =
      "{\"LocationUseFlag\":true,"
          + "\"DistanceDerivative\":1.2,\"DepthDerivative\":3.45,"
          + "\"AssociationWeightFlag\":true,\"TeleseismicPhaseGroup\":\"P\","
          + "\"Phase\":\"Pg\",\"RayDerivative\":5.67,\"AuxiliaryPhaseGroup\":\"P\","
          + "\"Observability\":0.34,\"StatisticalSpread\":1.5,\"TravelTime\":22.456}";

  public static String PHASE = "Pg";
  public static double TRAVELTIME = 22.456;
  public static double DISTANCEDERIVATIVE = 1.2;
  public static double DEPTHDERIVATIVE = 3.45;
  public static double RAYDERIVATIVE = 5.67;
  public static double STATISTICALSPREAD = 1.5;
  public static double OBSERVABILITY = .34;
  public static String TELESEISMICPHASEGROUP = "P";
  public static String AUXILIARYPHASEGROUP = "P";
  public static boolean LOCATIONUSEFLAG = true;
  public static boolean ASSOCIATIONWEIGHTFLAG = true;

  /** Able to write a JSON string */
  @Test
  public void writesJSON() {

    TravelTimeData travelTimeDataObject =
        new TravelTimeData(
            PHASE,
            TRAVELTIME,
            DISTANCEDERIVATIVE,
            DEPTHDERIVATIVE,
            RAYDERIVATIVE,
            STATISTICALSPREAD,
            OBSERVABILITY,
            TELESEISMICPHASEGROUP,
            AUXILIARYPHASEGROUP,
            LOCATIONUSEFLAG,
            ASSOCIATIONWEIGHTFLAG);

    // write out to a string
    String jsonString = Utility.toJSONString(travelTimeDataObject.toJSON());

    // check the data
    try {
      checkData(new TravelTimeData(Utility.fromJSONString(jsonString)), "WritesJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Able to read a JSON string */
  @Test
  public void readsJSON() {

    // build TravelTimeData object
    try {

      checkData(new TravelTimeData(Utility.fromJSONString(TRAVELTIMEDATA_STRING)), "ReadsJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Reload function fills in members correctly */
  @Test
  public void reload() {

    // use constructor
    TravelTimeData travelTimeDataObject = new TravelTimeData();

    travelTimeDataObject.reload(
        PHASE,
        TRAVELTIME,
        DISTANCEDERIVATIVE,
        DEPTHDERIVATIVE,
        RAYDERIVATIVE,
        STATISTICALSPREAD,
        OBSERVABILITY,
        TELESEISMICPHASEGROUP,
        AUXILIARYPHASEGROUP,
        LOCATIONUSEFLAG,
        ASSOCIATIONWEIGHTFLAG);

    // check data values
    checkData(travelTimeDataObject, "Reload Function");
  }

  /** Set functions fill in members correctly */
  @Test
  public void setters() {

    // use constructor
    TravelTimeData travelTimeDataObject = new TravelTimeData();

    travelTimeDataObject.Phase = PHASE;
    travelTimeDataObject.TravelTime = TRAVELTIME;
    travelTimeDataObject.DistanceDerivative = DISTANCEDERIVATIVE;
    travelTimeDataObject.DepthDerivative = DEPTHDERIVATIVE;
    travelTimeDataObject.RayDerivative = RAYDERIVATIVE;
    travelTimeDataObject.StatisticalSpread = STATISTICALSPREAD;
    travelTimeDataObject.Observability = OBSERVABILITY;
    travelTimeDataObject.TeleseismicPhaseGroup = TELESEISMICPHASEGROUP;
    travelTimeDataObject.AuxiliaryPhaseGroup = AUXILIARYPHASEGROUP;
    travelTimeDataObject.LocationUseFlag = LOCATIONUSEFLAG;
    travelTimeDataObject.AssociationWeightFlag = ASSOCIATIONWEIGHTFLAG;

    // check data values
    checkData(travelTimeDataObject, "Set Functions");
  }

  /** Copy constructor fills in members correctly */
  @Test
  public void copyConstructor() {

    // use constructor
    TravelTimeData travelTimeDataObject =
        new TravelTimeData(
            PHASE,
            TRAVELTIME,
            DISTANCEDERIVATIVE,
            DEPTHDERIVATIVE,
            RAYDERIVATIVE,
            STATISTICALSPREAD,
            OBSERVABILITY,
            TELESEISMICPHASEGROUP,
            AUXILIARYPHASEGROUP,
            LOCATIONUSEFLAG,
            ASSOCIATIONWEIGHTFLAG);

    TravelTimeData travelTimeDataObject2 = new TravelTimeData(travelTimeDataObject);

    // check data values
    checkData(travelTimeDataObject2, "Copy Constructor");
  }

  /** Able to run validation function */
  @Test
  public void validate() {

    // use constructor
    TravelTimeData travelTimeDataObject =
        new TravelTimeData(
            PHASE,
            TRAVELTIME,
            DISTANCEDERIVATIVE,
            DEPTHDERIVATIVE,
            RAYDERIVATIVE,
            STATISTICALSPREAD,
            OBSERVABILITY,
            TELESEISMICPHASEGROUP,
            AUXILIARYPHASEGROUP,
            LOCATIONUSEFLAG,
            ASSOCIATIONWEIGHTFLAG);

    // Successful validation
    boolean rc = travelTimeDataObject.isValid();

    // check return code
    Assertions.assertEquals(true, rc, "Successful Validation");

    // use constructor
    TravelTimeData badTravelTimeDataObject =
        new TravelTimeData(
            PHASE,
            TRAVELTIME,
            null,
            DEPTHDERIVATIVE,
            RAYDERIVATIVE,
            STATISTICALSPREAD,
            null,
            TELESEISMICPHASEGROUP,
            AUXILIARYPHASEGROUP,
            LOCATIONUSEFLAG,
            null);

    rc = badTravelTimeDataObject.isValid();

    // check return code
    Assertions.assertEquals(false, rc, "Unsuccessful Validation");
  }

  public void checkData(TravelTimeData travelTimeDataObject, String TestName) {

    // check travelTimeDataObject.phase
    Assertions.assertEquals(PHASE, travelTimeDataObject.Phase, TestName + " Phase Equals");

    // check travelTimeDataObject.travelTime
    Assertions.assertEquals(
        TRAVELTIME, travelTimeDataObject.TravelTime, 0, TestName + " Travel Time Equals");

    // check travelTimeDataObject.distanceDerivative
    Assertions.assertEquals(
        DISTANCEDERIVATIVE,
        travelTimeDataObject.DistanceDerivative,
        0,
        TestName + " Distance Derivative Equals");

    // check travelTimeDataObject.depthDerivative
    Assertions.assertEquals(
        DEPTHDERIVATIVE,
        travelTimeDataObject.DepthDerivative,
        0,
        TestName + " Depth Derivative Equals");

    // check travelTimeDataObject.rayDerivative
    Assertions.assertEquals(
        RAYDERIVATIVE, travelTimeDataObject.RayDerivative, 0, TestName + " Ray Derivative Equals");

    // check travelTimeDataObject.statisticalSpread
    Assertions.assertEquals(
        STATISTICALSPREAD,
        travelTimeDataObject.StatisticalSpread,
        0,
        TestName + " Statistical Spread Equals");

    // check travelTimeDataObject.observability
    Assertions.assertEquals(
        OBSERVABILITY, travelTimeDataObject.Observability, 0, TestName + " Observability Equals");

    // check travelTimeDataObject.teleseismicPhaseGroup
    Assertions.assertEquals(
        TELESEISMICPHASEGROUP,
        travelTimeDataObject.TeleseismicPhaseGroup,
        TestName + " Teleseismic Phase Group Equals");

    // check travelTimeDataObject.auxiliaryPhaseGroup
    Assertions.assertEquals(
        AUXILIARYPHASEGROUP,
        travelTimeDataObject.AuxiliaryPhaseGroup,
        TestName + " Auxiliary Phase Group Equals");

    // check OriginObject.locationUseFlag
    Assertions.assertEquals(
        LOCATIONUSEFLAG,
        travelTimeDataObject.LocationUseFlag,
        TestName + " Location Use Flag Equals");

    // check travelTimeDataObject.associationWeightFlag
    Assertions.assertEquals(
        ASSOCIATIONWEIGHTFLAG,
        travelTimeDataObject.AssociationWeightFlag,
        TestName + " Association Use Flag Equals");
  }
}
