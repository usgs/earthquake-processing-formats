package gov.usgs.processingformats;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class TravelTimeRecieverTest {

  public static final String RECIEVER_STRING =
      "{\"ID\":\"45\",\"Elevation\":15.0,\"Latitude\":45.905,"
          + "\"Longitude\":-112.778,\"Distance\":22.123,\"Branches\":"
          + "[{\"LocationUseFlag\":true,\"DistanceDerivative\":1.2,"
          + "\"DepthDerivative\":3.45,\"AssociationWeightFlag\":true,"
          + "\"Type\":\"TTData\",\"TeleseismicPhaseGroup\":1,\"Phase\":\"Pg\","
          + "\"RayDerivative\":5.67,\"AuxiliaryPhaseGroup\":1,"
          + "\"Observability\":0.34,\"StatisticalSpread\":1.5,"
          + "\"TravelTime\":22.456}]}";

  public static final String ID = "45";
  public static final double ELEVATION = 15.0;
  public static final double DISTANCE = 22.123;
  public static final double LATITUDE = 45.905;
  public static final double LONGITUDE = -112.778;

  public static String TRAVELTIMEDATA_STRING =
      "{\"Type\":\"TTData\","
          + "\"LocationUseFlag\":true,"
          + "\"DistanceDerivative\":1.2,\"DepthDerivative\":3.45,"
          + "\"AssociationWeightFlag\":true,\"TeleseismicPhaseGroup\":1,"
          + "\"Phase\":\"Pg\",\"RayDerivative\":5.67,\"AuxiliaryPhaseGroup\":1,"
          + "\"Observability\":0.34,\"StatisticalSpread\":1.5,\"TravelTime\":22.456}";

  /** Able to write a JSON string */
  @Test
  public void writesJSON() {

    TravelTimeReciever sourceObject =
        new TravelTimeReciever(ID, DISTANCE, ELEVATION, LATITUDE, LONGITUDE, buildData());

    // write out to a string
    String jsonString = Utility.toJSONString(sourceObject.toJSON());

    // check the data
    try {
      checkData(new TravelTimeReciever(Utility.fromJSONString(jsonString)), "WritesJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Able to read a JSON string */
  @Test
  public void readsJSON() {

    try {

      checkData(new TravelTimeReciever(Utility.fromJSONString(RECIEVER_STRING)), "ReadsJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Able to run validation function */
  @Test
  public void validate() {

    // build source object
    TravelTimeReciever sourceObject =
        new TravelTimeReciever(ID, DISTANCE, ELEVATION, LATITUDE, LONGITUDE, buildData());

    // Successful validation
    boolean rc = sourceObject.isValid();

    // check return code
    assertEquals("Successful Validation", true, rc);

    // build bad source object
    TravelTimeReciever badRecieverObject = new TravelTimeReciever(ID, null, null, null, null, null);

    rc = badRecieverObject.isValid();

    // check return code
    assertEquals("Unsuccessful Validation", false, rc);
  }

  /** Checks the data in the class */
  public void checkData(TravelTimeReciever RecieverObject, String TestName) {

    // check RecieverObject.ID
    assertEquals(TestName + " ID Equals", ID, RecieverObject.ID);

    // check RecieverObject.Distance
    assertEquals(TestName + " Distance Equals", DISTANCE, RecieverObject.Distance, 0);

    // check RecieverObject.Elevation
    assertEquals(TestName + " Elevation Equals", ELEVATION, RecieverObject.Elevation, 0);

    // check RecieverObject.Latitude
    assertEquals(TestName + " Latitude Equals", LATITUDE, RecieverObject.Latitude, 0);

    // check RecieverObject.Longitude
    assertEquals(TestName + " Longitude Equals", LONGITUDE, RecieverObject.Longitude, 0);
  }

  public ArrayList<TravelTimeData> buildData() {
    ArrayList<TravelTimeData> newData = new ArrayList<TravelTimeData>();
    try {
      newData.add(new TravelTimeData(Utility.fromJSONString(TRAVELTIMEDATA_STRING)));
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return (null);
    }
    return (newData);
  }
}
