package gov.usgs.processingformats;

import java.util.ArrayList;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TravelTimePlotDataBranchTest {

  public static String TRAVELTIMEDATABRANCH_STRING =
      "{\"Phase\":\"Pg\","
          + "\"Samples\":[{\"Distance\":1.2,\"Observability\":0.34,"
          + "\"StatisticalSpread\":1.5,\"TravelTime\":22.456},"
          + "{\"Distance\":10.5,\"Observability\":1.63,"
          + "\"StatisticalSpread\":2.1,\"TravelTime\":72.654}]}";

  public static String PHASE = "Pg";
  public static int NUMSAMPLES = 2;
  public static String SAMPLEDATA_STRING =
      "{\"Distance\":1.2,"
          + "\"TravelTime\":22.456,\"Observability\":0.34,"
          + "\"StatisticalSpread\":1.5}";
  public static double DISTANCE = 1.2;
  public static double TRAVELTIME = 22.456;
  public static double STATISTICALSPREAD = 1.5;
  public static double OBSERVABILITY = .34;
  public static String SAMPLEDATA2_STRING =
      "{\"Distance\":10.5,"
          + "\"TravelTime\":72.654,\"Observability\":1.63,"
          + "\"StatisticalSpread\":2.1}";
  public static double DISTANCE2 = 10.5;
  public static double TRAVELTIME2 = 72.654;
  public static double STATISTICALSPREAD2 = 2.1;
  public static double OBSERVABILITY2 = 1.63;

  /** Able to write a JSON string */
  @Test
  public void writesJSON() {

    TravelTimePlotDataBranch travelTimePlotDataBranchObject =
        new TravelTimePlotDataBranch(PHASE, buildSampleData());

    // write out to a string
    String jsonString = Utility.toJSONString(travelTimePlotDataBranchObject.toJSON());

    // check the data
    try {
      checkData(new TravelTimePlotDataBranch(Utility.fromJSONString(jsonString)), "WritesJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Able to read a JSON string */
  @Test
  public void readsJSON() {

    // build Correlation object
    try {

      checkData(
          new TravelTimePlotDataBranch(Utility.fromJSONString(TRAVELTIMEDATABRANCH_STRING)),
          "ReadsJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Reload function fills in members correctly */
  @Test
  public void reload() {

    // use constructor
    TravelTimePlotDataBranch travelTimePlotDataBranchObject = new TravelTimePlotDataBranch();

    travelTimePlotDataBranchObject.reload(PHASE, buildSampleData());

    // check data values
    checkData(travelTimePlotDataBranchObject, "Reload Function");
  }

  /** Set functions fill in members correctly */
  @Test
  public void setters() {

    // use constructor
    TravelTimePlotDataBranch travelTimePlotDataBranchObject = new TravelTimePlotDataBranch();

    travelTimePlotDataBranchObject.Phase = PHASE;
    travelTimePlotDataBranchObject.Samples = buildSampleData();

    // check data values
    checkData(travelTimePlotDataBranchObject, "Set Functions");
  }

  /** Copy constructor fills in members correctly */
  @Test
  public void copyConstructor() {

    // use constructor
    TravelTimePlotDataBranch travelTimePlotDataBranchObject =
        new TravelTimePlotDataBranch(PHASE, buildSampleData());

    TravelTimePlotDataBranch travelTimePlotDataBranchObject2 =
        new TravelTimePlotDataBranch(travelTimePlotDataBranchObject);

    // check data values
    checkData(travelTimePlotDataBranchObject2, "Copy Constructor");
  }

  /** Able to run validation function */
  @Test
  public void validate() {

    // use constructor
    TravelTimePlotDataBranch travelTimePlotDataBranchObject =
        new TravelTimePlotDataBranch(PHASE, buildSampleData());

    // Successful validation
    boolean rc = travelTimePlotDataBranchObject.isValid();

    // check return code
    Assertions.assertEquals(true, rc, "Successful Validation");

    // use constructor
    TravelTimePlotDataBranch badTravelTimePlotDataBranchObjectt =
        new TravelTimePlotDataBranch(null, buildSampleData());

    rc = badTravelTimePlotDataBranchObjectt.isValid();

    // check return code
    Assertions.assertEquals(false, rc, "Unsuccessful Validation");
  }

  public void checkData(TravelTimePlotDataBranch travelTimePlotDataBranchObject, String TestName) {

    // check travelTimePlotDataBranchObject.phase
    Assertions.assertEquals(
        PHASE, travelTimePlotDataBranchObject.Phase, TestName + " Phase Equals");

    // check sample data
    // check number of samples
    Assertions.assertEquals(
        NUMSAMPLES,
        travelTimePlotDataBranchObject.Samples.size(),
        0,
        TestName + " Number of Samples Equals");

    // first sample
    // check travelTimePlotDataBranchObject.samples[0].distance
    Assertions.assertEquals(
        DISTANCE,
        travelTimePlotDataBranchObject.Samples.get(0).Distance,
        0,
        TestName + " Distance Equals");

    // check ravelTimePlotDataBranchObject.samples[0].travelTime
    Assertions.assertEquals(
        TRAVELTIME,
        travelTimePlotDataBranchObject.Samples.get(0).TravelTime,
        0,
        TestName + " Travel Time Equals");

    // check ravelTimePlotDataBranchObject.samples[0].statisticalSpread
    Assertions.assertEquals(
        STATISTICALSPREAD,
        travelTimePlotDataBranchObject.Samples.get(0).StatisticalSpread,
        0,
        TestName + " Statistical Spread Equals");

    // check ravelTimePlotDataBranchObject.samples[0].observability
    Assertions.assertEquals(
        OBSERVABILITY,
        travelTimePlotDataBranchObject.Samples.get(0).Observability,
        0,
        TestName + " Observability Equals");

    // second sample
    // check travelTimePlotDataBranchObject.samples[1].distance
    Assertions.assertEquals(
        DISTANCE2,
        travelTimePlotDataBranchObject.Samples.get(1).Distance,
        0,
        TestName + " Distance Equals");

    // check ravelTimePlotDataBranchObject.samples[1].travelTime
    Assertions.assertEquals(
        TRAVELTIME2,
        travelTimePlotDataBranchObject.Samples.get(1).TravelTime,
        0,
        TestName + " Travel Time Equals");

    // check ravelTimePlotDataBranchObject.samples[1].statisticalSpread
    Assertions.assertEquals(
        STATISTICALSPREAD2,
        travelTimePlotDataBranchObject.Samples.get(1).StatisticalSpread,
        0,
        TestName + " Statistical Spread Equals");

    // check ravelTimePlotDataBranchObject.samples[1].observability
    Assertions.assertEquals(
        OBSERVABILITY2,
        travelTimePlotDataBranchObject.Samples.get(1).Observability,
        0,
        TestName + " Observability Equals");
  }

  public ArrayList<TravelTimePlotDataSample> buildSampleData() {
    ArrayList<TravelTimePlotDataSample> newSampleData = new ArrayList<TravelTimePlotDataSample>();

    // samples
    try {
      newSampleData.add(new TravelTimePlotDataSample(Utility.fromJSONString(SAMPLEDATA_STRING)));
      newSampleData.add(new TravelTimePlotDataSample(Utility.fromJSONString(SAMPLEDATA2_STRING)));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return (newSampleData);
  }
}
