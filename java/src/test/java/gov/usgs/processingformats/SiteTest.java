package gov.usgs.processingformats;

import static org.junit.Assert.*;

import org.json.simple.parser.ParseException;
import org.junit.Test;

public class SiteTest {

  public static String SITE_STRING =
      "{\"Station\":\"BOZ\",\"Channel\":"
          + "\"BHZ\",\"Network\":\"US\",\"Location\":\"00\","
          + "\"Latitude\":45.59697,\"Longitude\":-111.62967,"
          + "\"Elevation\":1589.0}";
  public static String STATION = "BOZ";
  public static String CHANNEL = "BHZ";
  public static String NETWORK = "US";
  public static String LOCATION = "00";
  public static double LATITUDE = 45.596970;
  public static double LONGITUDE = -111.629670;
  public static double ELEVATION = 1589.000000;

  /** Able to write a JSON string */
  @Test
  public void writesJSON() {

    Site SiteObject = new Site(STATION, CHANNEL, NETWORK, LOCATION, LATITUDE, LONGITUDE, ELEVATION);

    // write out to a string
    String jsonString = Utility.toJSONString(SiteObject.toJSON());

    // check the data
    try {
      checkData(new Site(Utility.fromJSONString(jsonString)), "WritesJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Able to read a JSON string */
  @Test
  public void readsJSON() {
    // build Amplitude object
    try {
      checkData(new Site(Utility.fromJSONString(SITE_STRING)), "ReadsJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Able to run validation function */
  @Test
  public void validate() {

    Site siteObject = new Site(STATION, CHANNEL, NETWORK, LOCATION, LATITUDE, LONGITUDE, ELEVATION);

    // Successful validation
    boolean rc = siteObject.isValid();

    // check return code
    assertEquals("Successful Validation", true, rc);

    // build bad source object
    Site badSiteObject = new Site(null, null, null, null, null, null, null);

    rc = badSiteObject.isValid();

    // check return code
    assertEquals("Unsuccessful Validation", false, rc);
  }

  /** Checks the data in the class */
  public void checkData(Site SiteObject, String TestName) {

    // check SiteObject.Station
    assertEquals(TestName + " Site Equals", STATION, SiteObject.Station);

    // check SiteObject.Channel
    assertEquals(TestName + " Channel Equals", CHANNEL, SiteObject.Channel);

    // check SiteObject.Network
    assertEquals(TestName + " Network Equals", NETWORK, SiteObject.Network);

    // check SiteObject.Location
    assertEquals(TestName + " Location Equals", LOCATION, SiteObject.Location);

    // check SiteObject.Latitude
    if (SiteObject.Latitude != null) {
      assertEquals(TestName + " Latitude Equals", LATITUDE, SiteObject.Latitude, 0);
    }

    // check SiteObject.Longitude
    if (SiteObject.Longitude != null) {
      assertEquals(TestName + " Longitude Equals", LONGITUDE, SiteObject.Longitude, 0);
    }

    // check SiteObject.Elevation
    if (SiteObject.Elevation != null) {
      assertEquals(TestName + " Elevation Equals", ELEVATION, SiteObject.Elevation, 0);
    }
  }
}
