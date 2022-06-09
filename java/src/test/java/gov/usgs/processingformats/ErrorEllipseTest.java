package gov.usgs.processingformats;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ErrorEllipseTest {

  public static final String ELLIPSE_STRING =
      "{\"MaximumVerticalProjection\":"
          + "1.984,\"EquivalentHorizontalRadius\":1.984,"
          + "\"MaximumHorizontalProjection\":1.984,\"E0\":{\"Azimuth\":-121.44,"
          + "\"Error\":40.3344,\"Dip\":32.44},\"E1\":{\"Azimuth\":22.64,"
          + "\"Error\":12.5,\"Dip\":2.44},\"E2\":{\"Azimuth\":22.64,\"Error\":"
          + "12.5,\"Dip\":2.44}}";

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

    ErrorEllipse ellipseObject =
        new ErrorEllipse(
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
    String jsonString = Utility.toJSONString(ellipseObject.toJSON());

    // check the data
    try {
      checkData(new ErrorEllipse(Utility.fromJSONString(jsonString)), "WritesJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Able to read a JSON string */
  @Test
  public void readsJSON() {

    // build Correlation object
    try {

      checkData(new ErrorEllipse(Utility.fromJSONString(ELLIPSE_STRING)), "ReadsJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Able to run validation function */
  @Test
  public void validate() {

    ErrorEllipse ellipseObject =
        new ErrorEllipse(
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
    boolean rc = ellipseObject.isValid();

    // check return code
    Assertions.assertEquals(true, rc, "Successful Validation");

    // build bad ellipse object
    ErrorEllipse badEllipseObject =
        new ErrorEllipse(
            null,
            E0AZIMUTH,
            E0DIP,
            E1ERROR,
            E1AZIMUTH,
            E1DIP,
            null,
            E2AZIMUTH,
            E2DIP,
            MAXIMUMHORIZONTALPROJECTION,
            MAXIMUMVERTICALPROJECTION,
            null);

    rc = badEllipseObject.isValid();

    // check return code
    Assertions.assertEquals(false, rc, "Unsuccessful Validation");
  }

  public void checkData(ErrorEllipse ellipseObject, String TestName) {

    // check ellipseObject.e0Error
    Assertions.assertEquals(E0ERROR, ellipseObject.E0.Error, 0, TestName + " e0Error Equals");

    // check ellipseObject.e0Azimuth
    Assertions.assertEquals(E0AZIMUTH, ellipseObject.E0.Azimuth, 0, TestName + " e0Azimuth Equals");

    // check ellipseObject.e0Dip
    Assertions.assertEquals(E0DIP, ellipseObject.E0.Dip, 0, TestName + " e0Dip Equals");

    // check ellipseObject.e1Error
    Assertions.assertEquals(E1ERROR, ellipseObject.E1.Error, 0, TestName + " e1Error Equals");

    // check ellipseObject.e1Azimuth
    Assertions.assertEquals(E1AZIMUTH, ellipseObject.E1.Azimuth, 0, TestName + " e1Azimuth Equals");

    // check ellipseObject.e1Dip
    Assertions.assertEquals(E1DIP, ellipseObject.E1.Dip, 0, TestName + " e1Dip Equals");

    // check ellipseObject.e2Error
    Assertions.assertEquals(E2ERROR, ellipseObject.E2.Error, 0, TestName + " e2Error Equals");

    // check ellipseObject.e2Azimuth
    Assertions.assertEquals(E2AZIMUTH, ellipseObject.E2.Azimuth, 0, TestName + " e2Azimuth Equals");

    // check ellipseObject.e2Dip
    Assertions.assertEquals(E2DIP, ellipseObject.E2.Dip, 0, TestName + " e2Dip Equals");

    // check ellipseObject.maximumHorizontalProjection
    Assertions.assertEquals(
        MAXIMUMHORIZONTALPROJECTION,
        ellipseObject.MaximumHorizontalProjection,
        0,
        TestName + " maximumHorizontalProjection Equals");

    // check ellipseObject.maximumVerticalProjection
    Assertions.assertEquals(
        MAXIMUMVERTICALPROJECTION,
        ellipseObject.MaximumVerticalProjection,
        0,
        TestName + " maximumVerticalProjection Equals");

    // check ellipseObject.equivalentHorizontalRadius
    Assertions.assertEquals(
        EQUIVILENTHORIZONTALRADIUS,
        ellipseObject.EquivalentHorizontalRadius,
        0,
        TestName + " equivalentHorizontalRadius Equals");
  }
}
