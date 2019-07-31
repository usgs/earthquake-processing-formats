package gov.usgs.processingformats;

import static org.junit.Assert.*;

import org.json.simple.parser.ParseException;
import org.junit.Test;

public class ErrorEllipseTest {

	public static final String ELLIPSE_STRING = "{\"MaximumVerticalProjection\":"
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

	/**
	 * Able to write a JSON string
	 */
	@Test
	public void writesJSON() {

		ErrorEllipse ellipseObject = new ErrorEllipse(E0ERROR, E0AZIMUTH, E0DIP,
				E1ERROR, E1AZIMUTH, E1DIP, E2ERROR, E2AZIMUTH, E2DIP,
				MAXIMUMHORIZONTALPROJECTION, MAXIMUMVERTICALPROJECTION,
				EQUIVILENTHORIZONTALRADIUS);

		// write out to a string
		String jsonString = Utility.toJSONString(ellipseObject.toJSON());

		// check the data
		try {
			checkData(new ErrorEllipse(Utility.fromJSONString(jsonString)),
					"WritesJSON");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Able to read a JSON string
	 */
	@Test
	public void readsJSON() {

		// build Correlation object
		try {

			checkData(new ErrorEllipse(Utility.fromJSONString(ELLIPSE_STRING)),
					"ReadsJSON");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Able to run validation function
	 */
	@Test
	public void validate() {

		ErrorEllipse ellipseObject = new ErrorEllipse(E0ERROR, E0AZIMUTH, E0DIP,
				E1ERROR, E1AZIMUTH, E1DIP, E2ERROR, E2AZIMUTH, E2DIP,
				MAXIMUMHORIZONTALPROJECTION, MAXIMUMVERTICALPROJECTION,
				EQUIVILENTHORIZONTALRADIUS);

		// Successful validation
		boolean rc = ellipseObject.isValid();

		// check return code
		assertEquals("Successful Validation", true, rc);

		// build bad ellipse object
		ErrorEllipse badEllipseObject = new ErrorEllipse(null, E0AZIMUTH, E0DIP,
				E1ERROR, E1AZIMUTH, E1DIP, null, E2AZIMUTH, E2DIP,
				MAXIMUMHORIZONTALPROJECTION, MAXIMUMVERTICALPROJECTION, null);

		rc = badEllipseObject.isValid();

		// check return code
		assertEquals("Unsuccessful Validation", false, rc);
	}

	public void checkData(ErrorEllipse ellipseObject, String TestName) {

		// check ellipseObject.e0Error
		assertEquals(TestName + " e0Error Equals", E0ERROR,
				ellipseObject.E0.Error, 0);

		// check ellipseObject.e0Azimuth
		assertEquals(TestName + " e0Azimuth Equals", E0AZIMUTH,
				ellipseObject.E0.Azimuth, 0);

		// check ellipseObject.e0Dip
		assertEquals(TestName + " e0Dip Equals", E0DIP,
				ellipseObject.E0.Dip, 0);

		// check ellipseObject.e1Error
		assertEquals(TestName + " e1Error Equals", E1ERROR,
				ellipseObject.E1.Error, 0);

		// check ellipseObject.e1Azimuth
		assertEquals(TestName + " e1Azimuth Equals", E1AZIMUTH,
				ellipseObject.E1.Azimuth, 0);

		// check ellipseObject.e1Dip
		assertEquals(TestName + " e1Dip Equals", E1DIP,
				ellipseObject.E1.Dip, 0);

		// check ellipseObject.e2Error
		assertEquals(TestName + " e2Error Equals", E2ERROR,
				ellipseObject.E2.Error, 0);

		// check ellipseObject.e2Azimuth
		assertEquals(TestName + " e2Azimuth Equals", E2AZIMUTH,
				ellipseObject.E2.Azimuth, 0);

		// check ellipseObject.e2Dip
		assertEquals(TestName + " e2Dip Equals", E2DIP,
				ellipseObject.E2.Dip, 0);

		// check ellipseObject.maximumHorizontalProjection
		assertEquals(TestName + " maximumHorizontalProjection Equals",
				MAXIMUMHORIZONTALPROJECTION,
				ellipseObject.MaximumHorizontalProjection, 0);

		// check ellipseObject.maximumVerticalProjection
		assertEquals(TestName + " maximumVerticalProjection Equals",
				MAXIMUMVERTICALPROJECTION,
				ellipseObject.MaximumVerticalProjection, 0);

		// check ellipseObject.equivalentHorizontalRadius
		assertEquals(TestName + " equivalentHorizontalRadius Equals",
				EQUIVILENTHORIZONTALRADIUS,
				ellipseObject.EquivalentHorizontalRadius, 0);

	}

}
