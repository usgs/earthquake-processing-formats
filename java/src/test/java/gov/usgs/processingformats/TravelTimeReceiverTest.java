package gov.usgs.processingformats;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class TravelTimeReceiverTest {

  public static final String RECIEVER_STRING =
      "{\"ID\":\"45\",\"Elevation\":15.0,\"Latitude\":45.905,"
          + "\"Longitude\":-112.778,\"Distance\":22.123,\"Branches\":"
          + "[{\"LocationUseFlag\":true,\"DistanceDerivative\":1.2,"
          + "\"DepthDerivative\":3.45,\"AssociationWeightFlag\":true,"
          + "\"TeleseismicPhaseGroup\":1,\"Phase\":\"Pg\","
          + "\"RayDerivative\":5.67,\"AuxiliaryPhaseGroup\":1,"
          + "\"Observability\":0.34,\"StatisticalSpread\":1.5,"
          + "\"TravelTime\":22.456}]}";

  public static final String ID = "45";
  public static final double ELEVATION = 15.0;
  public static final double DISTANCE = 22.123;
  public static final double LATITUDE = 45.905;
  public static final double LONGITUDE = -112.778;

  public static String TRAVELTIMEDATA_STRING =
      "{\"LocationUseFlag\":true,"
          + "\"DistanceDerivative\":1.2,\"DepthDerivative\":3.45,"
          + "\"AssociationWeightFlag\":true,\"TeleseismicPhaseGroup\":1,"
          + "\"Phase\":\"Pg\",\"RayDerivative\":5.67,\"AuxiliaryPhaseGroup\":1,"
          + "\"Observability\":0.34,\"StatisticalSpread\":1.5,\"TravelTime\":22.456}";

  /** Able to write a JSON string */
  @Test
  public void writesJSON() {

    TravelTimeReceiver sourceObject =
        new TravelTimeReceiver(ID, DISTANCE, ELEVATION, LATITUDE, LONGITUDE, buildData());

    // write out to a string
    String jsonString = Utility.toJSONString(sourceObject.toJSON());

    // check the data
    try {
      checkData(new TravelTimeReceiver(Utility.fromJSONString(jsonString)), "WritesJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Able to read a JSON string */
  @Test
  public void readsJSON() {

    try {

      checkData(new TravelTimeReceiver(Utility.fromJSONString(RECIEVER_STRING)), "ReadsJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Able to run validation function */
  @Test
  public void validate() {

    // build source object
    TravelTimeReceiver sourceObject =
        new TravelTimeReceiver(ID, DISTANCE, ELEVATION, LATITUDE, LONGITUDE, buildData());

    // Successful validation
    boolean rc = sourceObject.isValid();

    // check return code
    assertEquals("Successful Validation", true, rc);

    // build bad source object
    TravelTimeReceiver badReceiverObject = new TravelTimeReceiver(ID, null, null, null, null, null);

    rc = badReceiverObject.isValid();

    // check return code
    assertEquals("Unsuccessful Validation", false, rc);
  }

  /** Checks the data in the class */
  public void checkData(TravelTimeReceiver ReceiverObject, String TestName) {

    // check ReceiverObject.ID
    assertEquals(TestName + " ID Equals", ID, ReceiverObject.ID);

    // check ReceiverObject.Distance
    assertEquals(TestName + " Distance Equals", DISTANCE, ReceiverObject.Distance, 0);

    // check ReceiverObject.Elevation
    assertEquals(TestName + " Elevation Equals", ELEVATION, ReceiverObject.Elevation, 0);

    // check ReceiverObject.Latitude
    assertEquals(TestName + " Latitude Equals", LATITUDE, ReceiverObject.Latitude, 0);

    // check ReceiverObject.Longitude
    assertEquals(TestName + " Longitude Equals", LONGITUDE, ReceiverObject.Longitude, 0);
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
