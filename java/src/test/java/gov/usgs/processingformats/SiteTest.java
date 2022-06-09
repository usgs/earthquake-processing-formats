package gov.usgs.processingformats;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
    Assertions.assertEquals(true, rc, "Successful Validation");

    // build bad source object
    Site badSiteObject = new Site(null, null, null, null, null, null, null);

    rc = badSiteObject.isValid();

    // check return code
    Assertions.assertEquals(false, rc, "Unsuccessful Validation");
  }

  /** Checks the data in the class */
  public void checkData(Site SiteObject, String TestName) {

    // check SiteObject.Station
    Assertions.assertEquals(STATION, SiteObject.Station, TestName + " Site Equals");

    // check SiteObject.Channel
    Assertions.assertEquals(CHANNEL, SiteObject.Channel, TestName + " Channel Equals");

    // check SiteObject.Network
    Assertions.assertEquals(NETWORK, SiteObject.Network, TestName + " Network Equals");

    // check SiteObject.Location
    Assertions.assertEquals(LOCATION, SiteObject.Location, TestName + " Location Equals");

    // check SiteObject.Latitude
    if (SiteObject.Latitude != null) {
      Assertions.assertEquals(LATITUDE, SiteObject.Latitude, 0, TestName + " Latitude Equals");
    }

    // check SiteObject.Longitude
    if (SiteObject.Longitude != null) {
      Assertions.assertEquals(LONGITUDE, SiteObject.Longitude, 0, TestName + " Longitude Equals");
    }

    // check SiteObject.Elevation
    if (SiteObject.Elevation != null) {
      Assertions.assertEquals(ELEVATION, SiteObject.Elevation, 0, TestName + " Elevation Equals");
    }
  }
}
