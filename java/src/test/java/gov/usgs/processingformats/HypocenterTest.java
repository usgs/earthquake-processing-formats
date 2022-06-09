package gov.usgs.processingformats;

import java.util.Date;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HypocenterTest {

  public static final String HYPO_STRING =
      "{\"TimeError\":1.984,"
          + "\"Time\":\"2015-12-28T21:32:24.017Z\",\"LongitudeError\":22.64,"
          + "\"LatitudeError\":12.5,\"DepthError\":2.44,\"Latitude\":40.3344,"
          + "\"Longitude\":-121.44,\"Depth\":32.44}";

  public static double LATITUDE = 40.3344;
  public static double LONGITUDE = -121.44;
  public static Date TIME = Utility.getDate("2015-12-28T21:32:24.017Z");
  public static double DEPTH = 32.44;
  public static double LATITUDEERROR = 12.5;
  public static double LONGITUDEERROR = 22.64;
  public static double DEPTHERROR = 2.44;
  public static double TIMEERROR = 1.984;

  /** Able to write a JSON string */
  @Test
  public void writesJSON() {

    Hypocenter hypoObject =
        new Hypocenter(
            LATITUDE, LONGITUDE, TIME, DEPTH, LATITUDEERROR, LONGITUDEERROR, TIMEERROR, DEPTHERROR);

    // write out to a string
    String jsonString = Utility.toJSONString(hypoObject.toJSON());

    // check the data
    try {
      checkData(new Hypocenter(Utility.fromJSONString(jsonString)), "WritesJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Able to read a JSON string */
  @Test
  public void readsJSON() {

    // build Correlation object
    try {

      checkData(new Hypocenter(Utility.fromJSONString(HYPO_STRING)), "ReadsJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Able to run validation function */
  @Test
  public void validate() {

    Hypocenter hypoObject =
        new Hypocenter(
            LATITUDE, LONGITUDE, TIME, DEPTH, LATITUDEERROR, LONGITUDEERROR, TIMEERROR, DEPTHERROR);

    // Successful validation
    boolean rc = hypoObject.isValid();

    // check return code
    Assertions.assertEquals(true, rc, "Successful Validation");

    // build bad Hypo object
    Hypocenter badHypoObject =
        new Hypocenter(
            null, LONGITUDE, null, DEPTH, LATITUDEERROR, LONGITUDEERROR, TIMEERROR, DEPTHERROR);

    rc = badHypoObject.isValid();

    // check return code
    Assertions.assertEquals(false, rc, "Unsuccessful Validation");
  }

  public void checkData(Hypocenter hypoObject, String TestName) {

    // check hypoObject.Latitude
    Assertions.assertEquals(LATITUDE, hypoObject.Latitude, 0, TestName + " Latitude Equals");

    // check hypoObject.Longitude
    Assertions.assertEquals(LONGITUDE, hypoObject.Longitude, 0, TestName + " Longitude Equals");

    // check hypoObject.Depth
    Assertions.assertEquals(DEPTH, hypoObject.Depth, 0, TestName + " Depth Equals");

    // check hypoObject.Time
    Assertions.assertEquals(TIME, hypoObject.Time, TestName + " Time Equals");

    // check hypoObject.LatitudeError
    Assertions.assertEquals(
        LATITUDEERROR, hypoObject.LatitudeError, 0, TestName + " LatitudeError Equals");

    // check hypoObject.LongitudeError
    Assertions.assertEquals(
        LONGITUDEERROR, hypoObject.LongitudeError, 0, TestName + " LongitudError Equals");

    // check hypoObject.DepthError
    Assertions.assertEquals(DEPTHERROR, hypoObject.DepthError, 0, TestName + " DepthError Equals");

    // check hypoObject.TimeError
    Assertions.assertEquals(TIMEERROR, hypoObject.TimeError, 0, TestName + " TimeError Equals");
  }
}
