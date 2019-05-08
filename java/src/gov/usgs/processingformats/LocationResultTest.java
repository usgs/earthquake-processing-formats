package gov.usgs.processingformats;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.json.simple.parser.ParseException;
import org.junit.Test;

public class LocationResultTest {

	public static final String LOCATIONRESULT_STRING = "{\"MinimumDistance\":2.14,"
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

	public static String SUPPORTINGDATA = "{\"ID\":\"12GFH48776857\","
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

	/**
	 * Able to write a JSON string
	 */
	@Test
	public void writesJSON() {

		LocationResult LocationResultObject = new LocationResult(ID, LATITUDE, LONGITUDE,
				TIME, DEPTH, LATITUDEERROR, LONGITUDEERROR, TIMEERROR,
				DEPTHERROR, buildSupportingData(), NUMASSOCIATEDSTATIONS,
				NUMASSOCIATEDPHASES, NUMUSEDSTATIONS, NUMUSEDPHASES, GAP,
				SECONDARYGAP, MINIMUMDISTANCE, RMS, QUALITY, BAYESIANDEPTH,
				BAYESIANRANGE, DEPTHIMPORTANCE, LOCATOREXITCODE, E0ERROR, E0AZIMUTH, E0DIP,
				E1ERROR, E1AZIMUTH, E1DIP, E2ERROR, E2AZIMUTH, E2DIP,
				MAXIMUMHORIZONTALPROJECTION, MAXIMUMVERTICALPROJECTION,
				EQUIVILENTHORIZONTALRADIUS);

		// write out to a string
		String jsonString = Utility.toJSONString(LocationResultObject.toJSON());

		// check the data
		try {
			checkData(new LocationResult(Utility.fromJSONString(jsonString)),
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

			checkData(
					new LocationResult(
							Utility.fromJSONString(LOCATIONRESULT_STRING)),
					"ReadsJSON");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor fills in members correctly
	 */
	@Test
	public void altConstructors() {
		LocationResult LocationResultObject = new LocationResult(ID, LATITUDE, LONGITUDE,
				TIME, DEPTH, LATITUDEERROR, LONGITUDEERROR, TIMEERROR,
				DEPTHERROR, buildSupportingData());

		checkData(LocationResultObject, "Alternate Constructor 1");

		LocationResult LocationResultObject2 = new LocationResult( 
				new Hypocenter(LATITUDE, LONGITUDE, TIME, DEPTH, LATITUDEERROR,
						LONGITUDEERROR, TIMEERROR, DEPTHERROR),
				buildSupportingData());

		checkData(LocationResultObject2, "Alternate Constructor 2");
	}

	/**
	 * Able to run validation function
	 */
	@Test
	public void validate() {

		LocationResult LocationResultObject = new LocationResult(ID, LATITUDE, LONGITUDE,
				TIME, DEPTH, LATITUDEERROR, LONGITUDEERROR, TIMEERROR,
				DEPTHERROR, buildSupportingData(), NUMASSOCIATEDSTATIONS,
				NUMASSOCIATEDPHASES, NUMUSEDSTATIONS, NUMUSEDPHASES, GAP,
				SECONDARYGAP, MINIMUMDISTANCE, RMS, QUALITY, BAYESIANDEPTH,
				BAYESIANRANGE, DEPTHIMPORTANCE, LOCATOREXITCODE, E0ERROR, E0AZIMUTH, E0DIP,
				E1ERROR, E1AZIMUTH, E1DIP, E2ERROR, E2AZIMUTH, E2DIP,
				MAXIMUMHORIZONTALPROJECTION, MAXIMUMVERTICALPROJECTION,
				EQUIVILENTHORIZONTALRADIUS);

		// Successful validation
		boolean rc = LocationResultObject.isValid();

		// check return code
		assertEquals("Successful Validation", true, rc);

		LocationResult badLocationResultObject = new LocationResult(ID, null, LONGITUDE,
				TIME, DEPTH, LATITUDEERROR, LONGITUDEERROR, TIMEERROR,
				DEPTHERROR, null, NUMASSOCIATEDSTATIONS, NUMASSOCIATEDPHASES,
				NUMUSEDSTATIONS, NUMUSEDPHASES, GAP, SECONDARYGAP,
				MINIMUMDISTANCE, RMS, QUALITY, BAYESIANDEPTH, BAYESIANRANGE,
				DEPTHIMPORTANCE, LOCATOREXITCODE, E0ERROR, E0AZIMUTH, E0DIP, E1ERROR, null,
				E1DIP, E2ERROR, E2AZIMUTH, null, MAXIMUMHORIZONTALPROJECTION,
				MAXIMUMVERTICALPROJECTION, EQUIVILENTHORIZONTALRADIUS);

		rc = badLocationResultObject.isValid();

		// check return code
		assertEquals("Unsuccessful Validation", false, rc);
	}

	public void checkData(LocationResult LocationResultObject, String TestName) {

		// check LocationResultObject.hypocenter.Latitude
		assertEquals(TestName + " Latitude Equals", LATITUDE,
				LocationResultObject.getHypocenter().getLatitude(), 0);

		// check LocationResultObject.hypocenter.Longitude
		assertEquals(TestName + " Longitude Equals", LONGITUDE,
				LocationResultObject.getHypocenter().getLongitude(), 0);

		// check LocationResultObject.hypocenter.Depth
		assertEquals(TestName + " Depth Equals", DEPTH,
				LocationResultObject.getHypocenter().getDepth(), 0);

		// check LocationResultObject.hypocenter.Time
		assertEquals(TestName + " OriginTime Equals", TIME,
				LocationResultObject.getHypocenter().getTime());

		// check LocationResultObject.hypocenter.LatitudeError
		assertEquals(TestName + " LatitudeError Equals", LATITUDEERROR,
				LocationResultObject.getHypocenter().getLatitudeError(), 0);

		// check LocationResultObject.hypocenter.LongitudeError
		assertEquals(TestName + " LongitudError Equals", LONGITUDEERROR,
				LocationResultObject.getHypocenter().getLongitudeError(), 0);

		// check LocationResultObject.hypocenter.DepthError
		assertEquals(TestName + " DepthError Equals", DEPTHERROR,
				LocationResultObject.getHypocenter().getDepthError(), 0);

		// check LocationResultObject.hypocenter.TimeError
		assertEquals(TestName + " TimeError Equals", TIMEERROR,
				LocationResultObject.getHypocenter().getTimeError(), 0);

		// need to check data still!!!!!!
		// somehow?

		// optional values
		// check LocationResultObject.id
		if (LocationResultObject.getID() != null) {
			assertEquals(TestName + " ID Equals",
					ID,
					LocationResultObject.getID());
		}

		// check LocationResultObject.numberOfAssociatedStations
		if (LocationResultObject.getNumberOfAssociatedStations() != null) {
			assertEquals(TestName + " Number of Associated Stations Equals",
					NUMASSOCIATEDSTATIONS,
					LocationResultObject.getNumberOfAssociatedStations(), 0);
		}

		// check LocationResultObject.numberOfAssociatedPhases
		if (LocationResultObject.getNumberOfAssociatedPhases() != null) {
			assertEquals(TestName + " Number of Associated Phases Equals",
					NUMASSOCIATEDPHASES,
					LocationResultObject.getNumberOfAssociatedPhases(), 0);
		}

		// check LocationResultObject.numberOfUsedStations
		if (LocationResultObject.getNumberOfUsedStations() != null) {
			assertEquals(TestName + " Number of Used Stations Equals",
					NUMUSEDSTATIONS,
					LocationResultObject.getNumberOfUsedStations(), 0);
		}

		// check LocationResultObject.numberOfUsedPhases
		if (LocationResultObject.getNumberOfUsedPhases() != null) {
			assertEquals(TestName + " Number of Used Phases Equals",
					NUMUSEDPHASES, LocationResultObject.getNumberOfUsedPhases(),
					0);
		}

		// check LocationResultObject.Gap
		if (LocationResultObject.getGap() != null) {
			assertEquals(TestName + " Gap Equals", GAP,
					LocationResultObject.getGap(), 0);
		}

		// check LocationResultObject.secondaryGap
		if (LocationResultObject.getSecondaryGap() != null) {
			assertEquals(TestName + " Secondary Gap Equals", SECONDARYGAP,
					LocationResultObject.getSecondaryGap(), 0);
		}

		// check LocationResultObject.MinimumDistance
		if (LocationResultObject.getMinimumDistance() != null) {
			assertEquals(TestName + " MinimumDistance Equals", MINIMUMDISTANCE,
					LocationResultObject.getMinimumDistance(), 0);
		}

		// check LocationResultObject.RMS
		if (LocationResultObject.getRMS() != null) {
			assertEquals(TestName + " RMS Equals", RMS,
					LocationResultObject.getRMS(), 0);
		}

		// check LocationResultObject.quality
		if (LocationResultObject.getQuality() != null) {
			assertEquals(TestName + " Quality Equals", QUALITY,
					LocationResultObject.getQuality());
		}

		// check LocationResultObject.baysianDepth
		if (LocationResultObject.getBayesianDepth() != null) {
			assertEquals(TestName + " Bayesian Depth Equals", BAYESIANDEPTH,
					LocationResultObject.getBayesianDepth(), 0);
		}

		// check LocationResultObject.baysianRange
		if (LocationResultObject.getBayesianRange() != null) {
			assertEquals(TestName + " Bayesian Range Equals", BAYESIANRANGE,
					LocationResultObject.getBayesianRange(), 0);
		}

		// check LocationResultObject.depthImportance
		if (LocationResultObject.getDepthImportance() != null) {
			assertEquals(TestName + " Depth Importance Equals", DEPTHIMPORTANCE,
					LocationResultObject.getDepthImportance(), 0);
		}

		// check LocationResultObject.locatorExitCode
		if (LocationResultObject.getLocatorExitCode() != null) {
			assertEquals(TestName + " Locator Exit Code Equals", LOCATOREXITCODE,
					LocationResultObject.getLocatorExitCode());
		}

		// error ellipse
		if (LocationResultObject.getErrorEllipse() != null) {
			// check ellipseObject.e0Error
			assertEquals(TestName + " e0Error Equals", E0ERROR,
					LocationResultObject.getErrorEllipse().getE0Error(), 0);

			// check ellipseObject.e0Azimuth
			assertEquals(TestName + " e0Azimuth Equals", E0AZIMUTH,
					LocationResultObject.getErrorEllipse().getE0Azimuth(), 0);

			// check ellipseObject.e0Dip
			assertEquals(TestName + " e0Dip Equals", E0DIP,
					LocationResultObject.getErrorEllipse().getE0Dip(), 0);

			// check ellipseObject.e1Error
			assertEquals(TestName + " e1Error Equals", E1ERROR,
					LocationResultObject.getErrorEllipse().getE1Error(), 0);

			// check ellipseObject.e1Azimuth
			assertEquals(TestName + " e1Azimuth Equals", E1AZIMUTH,
					LocationResultObject.getErrorEllipse().getE1Azimuth(), 0);

			// check ellipseObject.e1Dip
			assertEquals(TestName + " e1Dip Equals", E1DIP,
					LocationResultObject.getErrorEllipse().getE1Dip(), 0);

			// check ellipseObject.e2Error
			assertEquals(TestName + " e2Error Equals", E2ERROR,
					LocationResultObject.getErrorEllipse().getE2Error(), 0);

			// check ellipseObject.e2Azimuth
			assertEquals(TestName + " e2Azimuth Equals", E2AZIMUTH,
					LocationResultObject.getErrorEllipse().getE2Azimuth(), 0);

			// check ellipseObject.e2Dip
			assertEquals(TestName + " e2Dip Equals", E2DIP,
					LocationResultObject.getErrorEllipse().getE2Dip(), 0);

			// check ellipseObject.maximumHorizontalProjection
			assertEquals(TestName + " maximumHorizontalProjection Equals",
					MAXIMUMHORIZONTALPROJECTION, LocationResultObject
							.getErrorEllipse().getMaximumHorizontalProjection(),
					0);

			// check ellipseObject.maximumVerticalProjection
			assertEquals(TestName + " maximumVerticalProjection Equals",
					MAXIMUMVERTICALPROJECTION, LocationResultObject
							.getErrorEllipse().getMaximumVerticalProjection(),
					0);

			// check ellipseObject.equivalentHorizontalRadius
			assertEquals(TestName + " equivalentHorizontalRadius Equals",
					EQUIVILENTHORIZONTALRADIUS, LocationResultObject
							.getErrorEllipse().getEquivalentHorizontalRadius(),
					0);
		}

	}

	public ArrayList<Pick> buildSupportingData() {
		ArrayList<Pick> newSupportingData = new ArrayList<Pick>();

		// Pick ?need one more?
		try {
			newSupportingData
					.add(new Pick(Utility.fromJSONString(SUPPORTINGDATA)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (newSupportingData);
	}
}
