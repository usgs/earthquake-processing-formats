package gov.usgs.processingformats;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class LocationResultTest {

  public static final String LOCATIONRESULT_STRING =
      "{\"MinimumDistance\":2.14,"
          + "\"NumberOfUsedStations\":33,\"BayesianRange\":20.3,"
          + "\"ErrorEllipse\":{\"MaximumVerticalProjection\":1.984,"
          + "\"EquivalentHorizontalRadius\":1.984,"
          + "\"MaximumHorizontalProjection\":1.984,\"E0\":{\"Azimuth\":-121.44,"
          + "\"Error\":40.3344,\"Dip\":32.44},\"E1\":{\"Azimuth\":22.64,"
          + "\"Error\":12.5,\"Dip\":2.44},\"E2\":{\"Azimuth\":22.64,"
          + "\"Error\":12.5,\"Dip\":2.44}},\"SupportingData\":[{\"Site\":"
          + "{\"Station\":\"BOZ\",\"Channel\":"
          + "\"BHZ\",\"Network\":\"US\",\"Location\":\"00\","
          + "\"Latitude\":45.59697,\"Longitude\":-111.62967,"
          + "\"Elevation\":1589.0},\"PickedPhase\":\"P\",\"Use\":true,"
          + "\"AssociatedPhase\":\"P\",\"Time\":\"2015-12-28T21:32:24.017Z\","
          + "\"Residual\":1.05,\"Source\":{\"Type\":\"Unknown\",\"AgencyID\":"
          + "\"US\",\"Author\":\"TestAuthor\"},\"Weight\":2.65,\"Importance\":"
          + "3.8,\"Azimuth\":21.5,\"Quality\":0.45,\"Affinity\":1.2,"
          + "\"ID\":\"12GFH48776857\",\"LocatedPhase\":\"P\",\"Distance\":2.65}],"
          + "\"Hypocenter\":{\"LatitudeError\":12.5,\"DepthError\":2.44,"
          + "\"TimeError\":1.984,\"Latitude\":40.3344,\"Time\":"
          + "\"2015-12-28T21:32:24.017Z\",\"Longitude\":-121.44,\"Depth\":32.44,"
          + "\"LongitudeError\":22.64},\"DepthImportance\":1.8,\"LocatorExitCode\":"
          + "\"Success\",\"Quality\":\"A\",\"Gap\":33.67,\"BayesianDepth\":66.7,"
          + "\"SecondaryGap\":33.67,\"RMS\":3.8,\"NumberOfAssociatedStations\":11,"
          + "\"NumberOfAssociatedPhases\":22,\"NumberOfUsedPhases\":44}";

  public static String ID = "12345678";
  public static double LATITUDE = 40.3344;
  public static double LONGITUDE = -121.44;
  public static Date TIME = Utility.getDate("2015-12-28T21:32:24.017Z");
  public static double DEPTH = 32.44;
  public static double LATITUDEERROR = 12.5;
  public static double LONGITUDEERROR = 22.64;
  public static double DEPTHERROR = 2.44;
  public static double TIMEERROR = 1.984;

  public static int NUMASSOCIATEDSTATIONS = 11;
  public static int NUMASSOCIATEDPHASES = 22;

  public static int NUMUSEDSTATIONS = 33;
  public static int NUMUSEDPHASES = 44;

  public static String SUPPORTINGDATA =
      "{\"ID\":\"12GFH48776857\","
          + "\"Site\"{\"Station\":\"BOZ\",\"Channel\":"
          + "\"BHZ\",\"Network\":\"US\",\"Location\":\"00\","
          + "\"Latitude\":45.59697,\"Longitude\":-111.62967,"
          + "\"Elevation\":1589.0},\"Source\":{\"Author\":\"TestAuthor\","
          + "\"AgencyID\":\"US\",\"Type\":\"Unknown\"},"
          + "\"Time\":\"2015-12-28T21:32:24.017Z\",\"Affinity\":1.2,"
          + "\"Quality\":0.45,\"Use\":true,\"PickedPhase\":\"P\","
          + "\"AssociatedPhase\":\"P\",\"LocatedPhase\":\"P\","
          + "\"Residual\":1.05,\"Distance\":2.65,\"Azimuth\":21.5,"
          + "\"Weight\":2.65,\"Importance\":3.8}";

  public static double GAP = 33.67;
  public static double SECONDARYGAP = 33.67;
  public static double MINIMUMDISTANCE = 2.14;
  public static double RMS = 3.8;
  public static String QUALITY = "A";
  public static double BAYESIANDEPTH = 66.7;
  public static double BAYESIANRANGE = 20.3;
  public static double DEPTHIMPORTANCE = 1.8;
  public static String LOCATOREXITCODE = "Success";

  public static double E0ERROR = 40.3344;
  public static double E0AZIMUTH = -121.44;
  public static double E0DIP = 32.44;
  public static double E1ERROR = 12.5;
  public static double E1AZIMUTH = 22.64;
  public static double E1DIP = 2.44;
  public static double E2ERROR = 12.5;
  public static double E2AZIMUTH = 22.64;
  public static double E2DIP = 2.44;
  public static double MAXIMUMHORIZONTALPROJECTION = 1.984;
  public static double MAXIMUMVERTICALPROJECTION = 1.984;
  public static double EQUIVILENTHORIZONTALRADIUS = 1.984;

  /** Able to write a JSON string */
  @Test
  public void writesJSON() {

    LocationResult LocationResultObject =
        new LocationResult(
            ID,
            LATITUDE,
            LONGITUDE,
            TIME,
            DEPTH,
            LATITUDEERROR,
            LONGITUDEERROR,
            TIMEERROR,
            DEPTHERROR,
            buildSupportingData(),
            NUMASSOCIATEDSTATIONS,
            NUMASSOCIATEDPHASES,
            NUMUSEDSTATIONS,
            NUMUSEDPHASES,
            GAP,
            SECONDARYGAP,
            MINIMUMDISTANCE,
            RMS,
            QUALITY,
            BAYESIANDEPTH,
            BAYESIANRANGE,
            DEPTHIMPORTANCE,
            LOCATOREXITCODE,
            E0ERROR,
            E0AZIMUTH,
            E0DIP,
            E1ERROR,
            E1AZIMUTH,
            E1DIP,
            E2ERROR,
            E2AZIMUTH,
            E2DIP,
            MAXIMUMHORIZONTALPROJECTION,
            MAXIMUMVERTICALPROJECTION,
            EQUIVILENTHORIZONTALRADIUS);

    // write out to a string
    String jsonString = Utility.toJSONString(LocationResultObject.toJSON());

    // check the data
    try {
      checkData(new LocationResult(Utility.fromJSONString(jsonString)), "WritesJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Able to read a JSON string */
  @Test
  public void readsJSON() {

    // build Correlation object
    try {

      checkData(new LocationResult(Utility.fromJSONString(LOCATIONRESULT_STRING)), "ReadsJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Constructor fills in members correctly */
  @Test
  public void altConstructors() {
    LocationResult LocationResultObject =
        new LocationResult(
            ID,
            LATITUDE,
            LONGITUDE,
            TIME,
            DEPTH,
            LATITUDEERROR,
            LONGITUDEERROR,
            TIMEERROR,
            DEPTHERROR,
            buildSupportingData());

    checkData(LocationResultObject, "Alternate Constructor 1");

    LocationResult LocationResultObject2 =
        new LocationResult(
            new Hypocenter(
                LATITUDE,
                LONGITUDE,
                TIME,
                DEPTH,
                LATITUDEERROR,
                LONGITUDEERROR,
                TIMEERROR,
                DEPTHERROR),
            buildSupportingData());

    checkData(LocationResultObject2, "Alternate Constructor 2");
  }

  /** Able to run validation function */
  @Test
  public void validate() {

    LocationResult LocationResultObject =
        new LocationResult(
            ID,
            LATITUDE,
            LONGITUDE,
            TIME,
            DEPTH,
            LATITUDEERROR,
            LONGITUDEERROR,
            TIMEERROR,
            DEPTHERROR,
            buildSupportingData(),
            NUMASSOCIATEDSTATIONS,
            NUMASSOCIATEDPHASES,
            NUMUSEDSTATIONS,
            NUMUSEDPHASES,
            GAP,
            SECONDARYGAP,
            MINIMUMDISTANCE,
            RMS,
            QUALITY,
            BAYESIANDEPTH,
            BAYESIANRANGE,
            DEPTHIMPORTANCE,
            LOCATOREXITCODE,
            E0ERROR,
            E0AZIMUTH,
            E0DIP,
            E1ERROR,
            E1AZIMUTH,
            E1DIP,
            E2ERROR,
            E2AZIMUTH,
            E2DIP,
            MAXIMUMHORIZONTALPROJECTION,
            MAXIMUMVERTICALPROJECTION,
            EQUIVILENTHORIZONTALRADIUS);

    // Successful validation
    boolean rc = LocationResultObject.isValid();

    // check return code
    assertEquals("Successful Validation", true, rc);

    LocationResult badLocationResultObject =
        new LocationResult(
            ID,
            null,
            LONGITUDE,
            TIME,
            DEPTH,
            LATITUDEERROR,
            LONGITUDEERROR,
            TIMEERROR,
            DEPTHERROR,
            null,
            NUMASSOCIATEDSTATIONS,
            NUMASSOCIATEDPHASES,
            NUMUSEDSTATIONS,
            NUMUSEDPHASES,
            GAP,
            SECONDARYGAP,
            MINIMUMDISTANCE,
            RMS,
            QUALITY,
            BAYESIANDEPTH,
            BAYESIANRANGE,
            DEPTHIMPORTANCE,
            LOCATOREXITCODE,
            E0ERROR,
            E0AZIMUTH,
            E0DIP,
            E1ERROR,
            null,
            E1DIP,
            E2ERROR,
            E2AZIMUTH,
            null,
            MAXIMUMHORIZONTALPROJECTION,
            MAXIMUMVERTICALPROJECTION,
            EQUIVILENTHORIZONTALRADIUS);

    rc = badLocationResultObject.isValid();

    // check return code
    assertEquals("Unsuccessful Validation", false, rc);
  }

  public void checkData(LocationResult LocationResultObject, String TestName) {

    // check LocationResultObject.hypocenter.Latitude
    assertEquals(
        TestName + " Latitude Equals", LATITUDE, LocationResultObject.Hypocenter.Latitude, 0);

    // check LocationResultObject.hypocenter.Longitude
    assertEquals(
        TestName + " Longitude Equals", LONGITUDE, LocationResultObject.Hypocenter.Longitude, 0);

    // check LocationResultObject.hypocenter.Depth
    assertEquals(TestName + " Depth Equals", DEPTH, LocationResultObject.Hypocenter.Depth, 0);

    // check LocationResultObject.hypocenter.Time
    assertEquals(TestName + " OriginTime Equals", TIME, LocationResultObject.Hypocenter.Time);

    // check LocationResultObject.hypocenter.LatitudeError
    assertEquals(
        TestName + " LatitudeError Equals",
        LATITUDEERROR,
        LocationResultObject.Hypocenter.LatitudeError,
        0);

    // check LocationResultObject.hypocenter.LongitudeError
    assertEquals(
        TestName + " LongitudeError Equals",
        LONGITUDEERROR,
        LocationResultObject.Hypocenter.LongitudeError,
        0);

    // check LocationResultObject.hypocenter.DepthError
    assertEquals(
        TestName + " DepthError Equals", DEPTHERROR, LocationResultObject.Hypocenter.DepthError, 0);

    // check LocationResultObject.hypocenter.TimeError
    assertEquals(
        TestName + " TimeError Equals", TIMEERROR, LocationResultObject.Hypocenter.TimeError, 0);

    // need to check data still!!!!!!
    // somehow?

    // optional values
    // check LocationResultObject.id
    if (LocationResultObject.ID != null) {
      assertEquals(TestName + " ID Equals", ID, LocationResultObject.ID);
    }

    // check LocationResultObject.numberOfAssociatedStations
    if (LocationResultObject.NumberOfAssociatedStations != null) {
      assertEquals(
          TestName + " Number of Associated Stations Equals",
          NUMASSOCIATEDSTATIONS,
          LocationResultObject.NumberOfAssociatedStations,
          0);
    }

    // check LocationResultObject.numberOfAssociatedPhases
    if (LocationResultObject.NumberOfAssociatedPhases != null) {
      assertEquals(
          TestName + " Number of Associated Phases Equals",
          NUMASSOCIATEDPHASES,
          LocationResultObject.NumberOfAssociatedPhases,
          0);
    }

    // check LocationResultObject.numberOfUsedStations
    if (LocationResultObject.NumberOfUsedStations != null) {
      assertEquals(
          TestName + " Number of Used Stations Equals",
          NUMUSEDSTATIONS,
          LocationResultObject.NumberOfUsedStations,
          0);
    }

    // check LocationResultObject.numberOfUsedPhases
    if (LocationResultObject.NumberOfUsedPhases != null) {
      assertEquals(
          TestName + " Number of Used Phases Equals",
          NUMUSEDPHASES,
          LocationResultObject.NumberOfUsedPhases,
          0);
    }

    // check LocationResultObject.Gap
    if (LocationResultObject.Gap != null) {
      assertEquals(TestName + " Gap Equals", GAP, LocationResultObject.Gap, 0);
    }

    // check LocationResultObject.secondaryGap
    if (LocationResultObject.SecondaryGap != null) {
      assertEquals(
          TestName + " Secondary Gap Equals", SECONDARYGAP, LocationResultObject.SecondaryGap, 0);
    }

    // check LocationResultObject.MinimumDistance
    if (LocationResultObject.MinimumDistance != null) {
      assertEquals(
          TestName + " MinimumDistance Equals",
          MINIMUMDISTANCE,
          LocationResultObject.MinimumDistance,
          0);
    }

    // check LocationResultObject.RMS
    if (LocationResultObject.RMS != null) {
      assertEquals(TestName + " RMS Equals", RMS, LocationResultObject.RMS, 0);
    }

    // check LocationResultObject.quality
    if (LocationResultObject.Quality != null) {
      assertEquals(TestName + " Quality Equals", QUALITY, LocationResultObject.Quality);
    }

    // check LocationResultObject.baysianDepth
    if (LocationResultObject.BayesianDepth != null) {
      assertEquals(
          TestName + " Bayesian Depth Equals",
          BAYESIANDEPTH,
          LocationResultObject.BayesianDepth,
          0);
    }

    // check LocationResultObject.baysianRange
    if (LocationResultObject.BayesianRange != null) {
      assertEquals(
          TestName + " Bayesian Range Equals",
          BAYESIANRANGE,
          LocationResultObject.BayesianRange,
          0);
    }

    // check LocationResultObject.depthImportance
    if (LocationResultObject.DepthImportance != null) {
      assertEquals(
          TestName + " Depth Importance Equals",
          DEPTHIMPORTANCE,
          LocationResultObject.DepthImportance,
          0);
    }

    // check LocationResultObject.locatorExitCode
    if (LocationResultObject.LocatorExitCode != null) {
      assertEquals(
          TestName + " Locator Exit Code Equals",
          LOCATOREXITCODE,
          LocationResultObject.LocatorExitCode);
    }

    // error ellipse
    if (LocationResultObject.ErrorEllipse != null) {
      // check ellipseObject.e0Error
      assertEquals(
          TestName + " e0Error Equals", E0ERROR, LocationResultObject.ErrorEllipse.E0.Error, 0);

      // check ellipseObject.e0Azimuth
      assertEquals(
          TestName + " e0Azimuth Equals",
          E0AZIMUTH,
          LocationResultObject.ErrorEllipse.E0.Azimuth,
          0);

      // check ellipseObject.e0Dip
      assertEquals(TestName + " e0Dip Equals", E0DIP, LocationResultObject.ErrorEllipse.E0.Dip, 0);

      // check ellipseObject.e1Error
      assertEquals(
          TestName + " e1Error Equals", E1ERROR, LocationResultObject.ErrorEllipse.E1.Error, 0);

      // check ellipseObject.e1Azimuth
      assertEquals(
          TestName + " e1Azimuth Equals",
          E1AZIMUTH,
          LocationResultObject.ErrorEllipse.E1.Azimuth,
          0);

      // check ellipseObject.e1Dip
      assertEquals(TestName + " e1Dip Equals", E1DIP, LocationResultObject.ErrorEllipse.E1.Dip, 0);

      // check ellipseObject.e2Error
      assertEquals(
          TestName + " e2Error Equals", E2ERROR, LocationResultObject.ErrorEllipse.E2.Error, 0);

      // check ellipseObject.e2Azimuth
      assertEquals(
          TestName + " e2Azimuth Equals",
          E2AZIMUTH,
          LocationResultObject.ErrorEllipse.E2.Azimuth,
          0);

      // check ellipseObject.e2Dip
      assertEquals(TestName + " e2Dip Equals", E2DIP, LocationResultObject.ErrorEllipse.E2.Dip, 0);

      // check ellipseObject.maximumHorizontalProjection
      assertEquals(
          TestName + " maximumHorizontalProjection Equals",
          MAXIMUMHORIZONTALPROJECTION,
          LocationResultObject.ErrorEllipse.MaximumHorizontalProjection,
          0);

      // check ellipseObject.maximumVerticalProjection
      assertEquals(
          TestName + " maximumVerticalProjection Equals",
          MAXIMUMVERTICALPROJECTION,
          LocationResultObject.ErrorEllipse.MaximumVerticalProjection,
          0);

      // check ellipseObject.equivalentHorizontalRadius
      assertEquals(
          TestName + " equivalentHorizontalRadius Equals",
          EQUIVILENTHORIZONTALRADIUS,
          LocationResultObject.ErrorEllipse.EquivalentHorizontalRadius,
          0);
    }
  }

  public ArrayList<Pick> buildSupportingData() {
    ArrayList<Pick> newSupportingData = new ArrayList<Pick>();

    // Pick ?need one more?
    try {
      newSupportingData.add(new Pick(Utility.fromJSONString(SUPPORTINGDATA)));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return (newSupportingData);
  }
}
