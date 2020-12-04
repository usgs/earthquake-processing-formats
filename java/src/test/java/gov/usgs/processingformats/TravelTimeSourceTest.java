package gov.usgs.processingformats;

import static org.junit.Assert.*;

import org.json.simple.parser.ParseException;
import org.junit.Test;

public class TravelTimeSourceTest {

  public static final String SOURCE_STRING =
      "{\"Latitude\":45.905,\"Longitude\":-112.778,\"Depth\":15.0}";
  public static final double LATITUDE = 45.905;
  public static final double LONGITUDE = -112.778;
  public static final double DEPTH = 15.0;

  /** Able to write a JSON string */
  @Test
  public void writesJSON() {

    TravelTimeSource sourceObject = new TravelTimeSource(LATITUDE, LONGITUDE, DEPTH);

    // write out to a string
    String jsonString = Utility.toJSONString(sourceObject.toJSON());

    // check the data
    try {
      checkData(new TravelTimeSource(Utility.fromJSONString(jsonString)), "WritesJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Able to read a JSON string */
  @Test
  public void readsJSON() {

    try {

      checkData(new TravelTimeSource(Utility.fromJSONString(SOURCE_STRING)), "ReadsJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Able to run validation function */
  @Test
  public void validate() {

    // build source object
    TravelTimeSource sourceObject = new TravelTimeSource(LATITUDE, LONGITUDE, DEPTH);

    // Successful validation
    boolean rc = sourceObject.isValid();

    // check return code
    assertEquals("Successful Validation", true, rc);

    // build bad source object
    TravelTimeSource badSourceObject = new TravelTimeSource(LATITUDE, null, null);

    rc = badSourceObject.isValid();

    // check return code
    assertEquals("Unsuccessful Validation", false, rc);
  }

  /** Checks the data in the class */
  public void checkData(TravelTimeSource SourceObject, String TestName) {

    // check SourceObject.Latitude
    assertEquals(TestName + " Latitude Equals", LATITUDE, SourceObject.Latitude, 0);

    // check SourceObject.Longitude
    assertEquals(TestName + " Longitude Equals", LONGITUDE, SourceObject.Longitude, 0);

    // check SourceObject.Depth
    assertEquals(TestName + " Depth Equals", DEPTH, SourceObject.Depth, 0);
  }
}
