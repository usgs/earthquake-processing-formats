package gov.usgs.processingformats;

import java.util.Date;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PickTest {

  public static String PICK_STRING =
      "{\"ID\":\"12GFH48776857\","
          + "\"Site\":{\"Station\":\"BOZ\",\"Channel\":"
          + "\"BHZ\",\"Network\":\"US\",\"Location\":\"00\","
          + "\"Latitude\":45.59697,\"Longitude\":-111.62967,"
          + "\"Elevation\":1589.0},\"Source\":{\"Author\":\"TestAuthor\","
          + "\"AgencyID\":\"US\",\"Type\":\"Unknown\"},"
          + "\"Time\":\"2015-12-28T21:32:24.017Z\",\"Affinity\":1.2,"
          + "\"Quality\":0.45,\"Use\":true,\"PickedPhase\":\"P\","
          + "\"AssociatedPhase\":\"Pn\",\"LocatedPhase\":\"Pg\","
          + "\"Residual\":1.05,\"Distance\":2.65,\"Azimuth\":21.5,"
          + "\"Weight\":2.65,\"Importance\":3.8}";
  public static String ID = "12GFH48776857";
  public static String STATION = "BOZ";
  public static String CHANNEL = "BHZ";
  public static String NETWORK = "US";
  public static String LOCATION = "00";
  public static double LATITUDE = 45.596970;
  public static double LONGITUDE = -111.629670;
  public static double ELEVATION = 1589.000000;
  public static String AGENCYID = "US";
  public static String AUTHOR = "TestAuthor";
  public static String TYPE = "Unknown";
  public static Date TIME = Utility.getDate("2015-12-28T21:32:24.017Z");
  public static double AFFINITY = 1.2;
  public static double QUALITY = 0.45;
  public static boolean USE = true;
  public static String PICKEDPHASE = "P";
  public static String ASSOCIATEDPHASE = "Pn";
  public static String LOCATEDPHASE = "Pg";
  public static double RESIDUAL = 1.05;
  public static double DISTANCE = 2.65;
  public static double AZIMUTH = 21.5;
  public static double WEIGHT = 2.65;
  public static double IMPORTANCE = 3.8;

  /** Able to write a JSON string */
  @Test
  public void writesJSON() {

    Pick pickObject =
        new Pick(
            ID,
            STATION,
            CHANNEL,
            NETWORK,
            LOCATION,
            LATITUDE,
            LONGITUDE,
            ELEVATION,
            AGENCYID,
            AUTHOR,
            TYPE,
            TIME,
            AFFINITY,
            QUALITY,
            USE,
            PICKEDPHASE,
            ASSOCIATEDPHASE,
            LOCATEDPHASE,
            RESIDUAL,
            DISTANCE,
            AZIMUTH,
            WEIGHT,
            IMPORTANCE);

    // write out to a string
    String jsonString = Utility.toJSONString(pickObject.toJSON());

    // check the data
    try {
      checkData(new Pick(Utility.fromJSONString(jsonString)), "WritesJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Able to read a JSON string */
  @Test
  public void readsJSON() {

    // build Correlation object
    try {

      checkData(new Pick(Utility.fromJSONString(PICK_STRING)), "ReadsJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Constructor fills in members correctly */
  @Test
  public void altConstructors() {

    // use constructor
    Pick altPickObject =
        new Pick(
            ID,
            new Site(STATION, CHANNEL, NETWORK, LOCATION, LATITUDE, LONGITUDE, ELEVATION),
            new Source(AGENCYID, AUTHOR, TYPE),
            TIME,
            AFFINITY,
            QUALITY,
            USE,
            PICKEDPHASE,
            ASSOCIATEDPHASE,
            LOCATEDPHASE,
            RESIDUAL,
            DISTANCE,
            AZIMUTH,
            WEIGHT,
            IMPORTANCE);

    // check data values
    checkData(altPickObject, "Alternate Constructor 1");
  }

  /** Able to run validation function */
  @Test
  public void validate() {

    Pick pickObject =
        new Pick(
            ID,
            STATION,
            CHANNEL,
            NETWORK,
            LOCATION,
            LATITUDE,
            LONGITUDE,
            ELEVATION,
            AGENCYID,
            AUTHOR,
            TYPE,
            TIME,
            AFFINITY,
            QUALITY,
            USE,
            PICKEDPHASE,
            ASSOCIATEDPHASE,
            LOCATEDPHASE,
            RESIDUAL,
            DISTANCE,
            AZIMUTH,
            WEIGHT,
            IMPORTANCE);

    // Successful validation
    boolean rc = pickObject.isValid();

    // check return code
    Assertions.assertEquals(true, rc, "Successful Validation");

    // build bad Pick object
    Pick badPickObject =
        new Pick(
            "",
            null,
            CHANNEL,
            NETWORK,
            LOCATION,
            LATITUDE,
            LONGITUDE,
            ELEVATION,
            AGENCYID,
            AUTHOR,
            null,
            null,
            AFFINITY,
            QUALITY,
            USE,
            PICKEDPHASE,
            ASSOCIATEDPHASE,
            LOCATEDPHASE,
            RESIDUAL,
            DISTANCE,
            AZIMUTH,
            WEIGHT,
            IMPORTANCE);

    rc = badPickObject.isValid();

    // check return code
    Assertions.assertEquals(false, rc, "Unsuccessful Validation");
  }

  public void checkData(Pick pickObject, String TestName) {

    // check pickObject.ID
    Assertions.assertEquals(ID, pickObject.ID, TestName + " ID Equals");

    // check pickObject.site.Station
    Assertions.assertEquals(STATION, pickObject.Site.Station, TestName + " Station Equals");

    // check pickObject.site.Channel
    Assertions.assertEquals(CHANNEL, pickObject.Site.Channel, TestName + " Channel Equals");

    // check pickObject.site.Network
    Assertions.assertEquals(NETWORK, pickObject.Site.Network, TestName + " Network Equals");

    // check pickObject.site.Location
    Assertions.assertEquals(LOCATION, pickObject.Site.Location, TestName + " Location Equals");

    // check pickObject.site.Latitude
    if (pickObject.Site.Latitude != null) {
      Assertions.assertEquals(LATITUDE, pickObject.Site.Latitude, 0, TestName + " Latitude Equals");
    }

    // check pickObject.site.Longitude
    if (pickObject.Site.Longitude != null) {
      Assertions.assertEquals(
          LONGITUDE, pickObject.Site.Longitude, 0, TestName + " Longitude Equals");
    }

    // check pickObject.site.Elevation
    if (pickObject.Site.Elevation != null) {
      Assertions.assertEquals(
          ELEVATION, pickObject.Site.Elevation, 0, TestName + " Elevation Equals");
    }

    // check pickObject.Source.AgencyID
    Assertions.assertEquals(AGENCYID, pickObject.Source.AgencyID, TestName + " AgencyID Equals");

    // check pickObject.Source.Author
    Assertions.assertEquals(AUTHOR, pickObject.Source.Author, TestName + " Author Equals");

    // check pickObject.Source.Author
    Assertions.assertEquals(TYPE, pickObject.Source.Type, TestName + " Type Equals");

    // check pickObject.Time
    Assertions.assertEquals(TIME, pickObject.Time, TestName + " Time Equals");

    // check pickObject.affinity
    Assertions.assertEquals(AFFINITY, pickObject.Affinity, 0, TestName + " Affinity Equals");

    // check pickObject.quality
    Assertions.assertEquals(QUALITY, pickObject.Quality, 0, TestName + " Quality Equals");

    // check pickObject.use
    Assertions.assertEquals(USE, pickObject.Use, TestName + " Use Equals");

    // check pickObject.pickedPhase
    Assertions.assertEquals(PICKEDPHASE, pickObject.PickedPhase, TestName + " Picked Phase Equals");

    // check pickObject.associatedPhase
    Assertions.assertEquals(
        ASSOCIATEDPHASE, pickObject.AssociatedPhase, TestName + " Associated Phase Equals");

    // check pickObject.locatedPhase
    Assertions.assertEquals(
        LOCATEDPHASE, pickObject.LocatedPhase, TestName + " Located Phase Equals");

    // check pickObject.residual
    Assertions.assertEquals(RESIDUAL, pickObject.Residual, 0, TestName + " Residual Equals");

    // check pickObject.distance
    Assertions.assertEquals(DISTANCE, pickObject.Distance, 0, TestName + " Distance Equals");

    // check pickObject.azimuth
    Assertions.assertEquals(AZIMUTH, pickObject.Azimuth, 0, TestName + " Azimuth Equals");

    // check pickObject.weight
    Assertions.assertEquals(WEIGHT, pickObject.Weight, 0, TestName + " Weight Equals");

    // check pickObject.importance
    Assertions.assertEquals(IMPORTANCE, pickObject.Importance, 0, TestName + " Importance Equals");
  }
}
