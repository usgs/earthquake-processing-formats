package gov.usgs.processingformats;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.json.simple.parser.ParseException;
import org.junit.Test;

public class LocationDataTest {

	public static final String LOCATIONDATA_STRING = "{\"MinimumDistance\":2.14,"
			+ "\"NumberOfUsedStations\":33,\"BayesianRange\":20.3,"
			+ "\"ErrorEllipse\":{\"MaximumVerticalProjection\":1.984,"
			+ "\"EquivalentHorizontalRadius\":1.984,"
			+ "\"MaximumHorizontalProjection\":1.984,\"E0\":{\"Azimuth\":-121.44,"
			+ "\"Error\":40.3344,\"Dip\":32.44},\"E1\":{\"Azimuth\":22.64,"
			+ "\"Error\":12.5,\"Dip\":2.44},\"E2\":{\"Azimuth\":22.64,"
			+ "\"Error\":12.5,\"Dip\":2.44}},\"AssociatedData\":[{\"Site\":"
			+ "{\"Station\":\"BMN\",\"Network\":\"LB\",\"Channel\":\"HHZ\","
			+ "\"Location\":\"01\"},\"PickedPhase\":\"P\",\"Use\":true,"
			+ "\"AssociatedPhase\":\"P\",\"Time\":\"2015-12-28T21:32:24.017Z\","
			+ "\"Residual\":1.05,\"Source\":{\"Type\":\"Unknown\",\"AgencyID\":"
			+ "\"US\",\"Author\":\"TestAuthor\"},\"Weight\":2.65,\"Importance\":"
			+ "3.8,\"Azimuth\":21.5,\"Quality\":0.45,\"Affinity\":1.2,"
			+ "\"ID\":\"12GFH48776857\",\"LocatedPhase\":\"P\",\"Distance\":2.65}],"
			+ "\"Hypocenter\":{\"LatitudeError\":12.5,\"DepthError\":2.44,"
			+ "\"TimeError\":1.984,\"Latitude\":40.3344,\"Time\":"
			+ "\"2015-12-28T21:32:24.017Z\",\"Longitude\":-121.44,\"Depth\":32.44,"
			+ "\"LongitudeError\":22.64},\"DepthImportance\":1.8,\"Quality\":\"A\","
			+ "\"Gap\":33.67,\"BayesianDepth\":66.7,\"SecondaryGap\":33.67,"
			+ "\"RMS\":3.8,\"NumberOfAssociatedStations\":11,"
			+ "\"NumberOfAssociatedPhases\":22,\"NumberOfUsedPhases\":44}";

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

	public static String ASSOCIATEDDATA = "{\"ID\":\"12GFH48776857\","
			+ "\"Site\":{\"Station\":"
			+ "\"BMN\",\"Channel\":\"HHZ\",\"Network\":\"LB\","
			+ "\"Location\":\"01\"},\"Source\":{\"Author\":\"TestAuthor\","
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

		LocationData locationDataObject = new LocationData(LATITUDE, LONGITUDE,
				TIME, DEPTH, LATITUDEERROR, LONGITUDEERROR, TIMEERROR,
				DEPTHERROR, buildAssociatedData(), NUMASSOCIATEDSTATIONS,
				NUMASSOCIATEDPHASES, NUMUSEDSTATIONS, NUMUSEDPHASES, GAP,
				SECONDARYGAP, MINIMUMDISTANCE, RMS, QUALITY, BAYESIANDEPTH,
				BAYESIANRANGE, DEPTHIMPORTANCE, E0ERROR, E0AZIMUTH, E0DIP,
				E1ERROR, E1AZIMUTH, E1DIP, E2ERROR, E2AZIMUTH, E2DIP,
				MAXIMUMHORIZONTALPROJECTION, MAXIMUMVERTICALPROJECTION,
				EQUIVILENTHORIZONTALRADIUS);

		// write out to a string
		String jsonString = Utility.toJSONString(locationDataObject.toJSON());

		// check the data
		try {
			checkData(new LocationData(Utility.fromJSONString(jsonString)),
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
					new LocationData(
							Utility.fromJSONString(LOCATIONDATA_STRING)),
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
		LocationData locationDataObject = new LocationData(LATITUDE, LONGITUDE,
				TIME, DEPTH, LATITUDEERROR, LONGITUDEERROR, TIMEERROR,
				DEPTHERROR, buildAssociatedData());

		checkData(locationDataObject, "Alternate Constructor 1");

		LocationData locationDataObject2 = new LocationData(
				new Hypocenter(LATITUDE, LONGITUDE, TIME, DEPTH, LATITUDEERROR,
						LONGITUDEERROR, TIMEERROR, DEPTHERROR),
				buildAssociatedData());

		checkData(locationDataObject2, "Alternate Constructor 2");
	}

	/**
	 * Able to run validation function
	 */
	@Test
	public void validate() {

		LocationData locationDataObject = new LocationData(LATITUDE, LONGITUDE,
				TIME, DEPTH, LATITUDEERROR, LONGITUDEERROR, TIMEERROR,
				DEPTHERROR, buildAssociatedData(), NUMASSOCIATEDSTATIONS,
				NUMASSOCIATEDPHASES, NUMUSEDSTATIONS, NUMUSEDPHASES, GAP,
				SECONDARYGAP, MINIMUMDISTANCE, RMS, QUALITY, BAYESIANDEPTH,
				BAYESIANRANGE, DEPTHIMPORTANCE, E0ERROR, E0AZIMUTH, E0DIP,
				E1ERROR, E1AZIMUTH, E1DIP, E2ERROR, E2AZIMUTH, E2DIP,
				MAXIMUMHORIZONTALPROJECTION, MAXIMUMVERTICALPROJECTION,
				EQUIVILENTHORIZONTALRADIUS);

		// Successful validation
		boolean rc = locationDataObject.isValid();

		// check return code
		assertEquals("Successful Validation", true, rc);

		LocationData badLocationDataObject = new LocationData(null, LONGITUDE,
				TIME, DEPTH, LATITUDEERROR, LONGITUDEERROR, TIMEERROR,
				DEPTHERROR, null, NUMASSOCIATEDSTATIONS, NUMASSOCIATEDPHASES,
				NUMUSEDSTATIONS, NUMUSEDPHASES, GAP, SECONDARYGAP,
				MINIMUMDISTANCE, RMS, QUALITY, BAYESIANDEPTH, BAYESIANRANGE,
				DEPTHIMPORTANCE, E0ERROR, E0AZIMUTH, E0DIP, E1ERROR, null,
				E1DIP, E2ERROR, E2AZIMUTH, null, MAXIMUMHORIZONTALPROJECTION,
				MAXIMUMVERTICALPROJECTION, EQUIVILENTHORIZONTALRADIUS);

		rc = badLocationDataObject.isValid();

		// check return code
		assertEquals("Unsuccessful Validation", false, rc);
	}

	public void checkData(LocationData locationDataObject, String TestName) {

		// check locationDataObject.hypocenter.Depth
		assertEquals(TestName + " Depth Equals", DEPTH,
				locationDataObject.getHypocenter().getDepth(), 0);

		// check locationDataObject.hypocenter.Time
		assertEquals(TestName + " OriginTime Equals", TIME,
				locationDataObject.getHypocenter().getTime());

		// check locationDataObject.hypocenter.LatitudeError
		assertEquals(TestName + " LatitudeError Equals", LATITUDEERROR,
				locationDataObject.getHypocenter().getLatitudeError(), 0);

		// check locationDataObject.hypocenter.LongitudeError
		assertEquals(TestName + " LongitudError Equals", LONGITUDEERROR,
				locationDataObject.getHypocenter().getLongitudeError(), 0);

		// check locationDataObject.hypocenter.DepthError
		assertEquals(TestName + " DepthError Equals", DEPTHERROR,
				locationDataObject.getHypocenter().getDepthError(), 0);

		// check locationDataObject.hypocenter.TimeError
		assertEquals(TestName + " TimeError Equals", TIMEERROR,
				locationDataObject.getHypocenter().getTimeError(), 0);

		// need to check data still!!!!!!
		// somehow?

		// optional values
		// check locationDataObject.numberOfAssociatedStations
		if (locationDataObject.getNumberOfAssociatedStations() != null) {
			assertEquals(TestName + " Number of Associated Stations Equals",
					NUMASSOCIATEDSTATIONS,
					locationDataObject.getNumberOfAssociatedStations(), 0);
		}

		// check locationDataObject.numberOfAssociatedPhases
		if (locationDataObject.getNumberOfAssociatedPhases() != null) {
			assertEquals(TestName + " Number of Associated Phases Equals",
					NUMASSOCIATEDPHASES,
					locationDataObject.getNumberOfAssociatedPhases(), 0);
		}

		// check locationDataObject.numberOfUsedStations
		if (locationDataObject.getNumberOfUsedStations() != null) {
			assertEquals(TestName + " Number of Used Stations Equals",
					NUMUSEDSTATIONS,
					locationDataObject.getNumberOfUsedStations(), 0);
		}

		// check locationDataObject.numberOfUsedPhases
		if (locationDataObject.getNumberOfUsedPhases() != null) {
			assertEquals(TestName + " Number of Used Phases Equals",
					NUMUSEDPHASES, locationDataObject.getNumberOfUsedPhases(),
					0);
		}

		// check locationDataObject.Gap
		if (locationDataObject.getGap() != null) {
			assertEquals(TestName + " Gap Equals", GAP,
					locationDataObject.getGap(), 0);
		}

		// check locationDataObject.secondaryGap
		if (locationDataObject.getSecondaryGap() != null) {
			assertEquals(TestName + " Secondary Gap Equals", SECONDARYGAP,
					locationDataObject.getSecondaryGap(), 0);
		}

		// check locationDataObject.MinimumDistance
		if (locationDataObject.getMinimumDistance() != null) {
			assertEquals(TestName + " MinimumDistance Equals", MINIMUMDISTANCE,
					locationDataObject.getMinimumDistance(), 0);
		}

		// check locationDataObject.RMS
		if (locationDataObject.getRMS() != null) {
			assertEquals(TestName + " RMS Equals", RMS,
					locationDataObject.getRMS(), 0);
		}

		// check locationDataObject.quality
		if (locationDataObject.getQuality() != null) {
			assertEquals(TestName + " Quality Equals", QUALITY,
					locationDataObject.getQuality());
		}

		// check locationDataObject.baysianDepth
		if (locationDataObject.getBayesianDepth() != null) {
			assertEquals(TestName + " Bayesian Depth Equals", BAYESIANDEPTH,
					locationDataObject.getBayesianDepth(), 0);
		}

		// check locationDataObject.baysianRange
		if (locationDataObject.getBayesianRange() != null) {
			assertEquals(TestName + " Bayesian Range Equals", BAYESIANRANGE,
					locationDataObject.getBayesianRange(), 0);
		}

		// check locationDataObject.depthImportance
		if (locationDataObject.getDepthImportance() != null) {
			assertEquals(TestName + " Depth Importance Equals", DEPTHIMPORTANCE,
					locationDataObject.getDepthImportance(), 0);
		}

		// error ellipse
		if (locationDataObject.getErrorEllipse() != null) {
			// check ellipseObject.e0Error
			assertEquals(TestName + " e0Error Equals", E0ERROR,
					locationDataObject.getErrorEllipse().getE0Error(), 0);

			// check ellipseObject.e0Azimuth
			assertEquals(TestName + " e0Azimuth Equals", E0AZIMUTH,
					locationDataObject.getErrorEllipse().getE0Azimuth(), 0);

			// check ellipseObject.e0Dip
			assertEquals(TestName + " e0Dip Equals", E0DIP,
					locationDataObject.getErrorEllipse().getE0Dip(), 0);

			// check ellipseObject.e1Error
			assertEquals(TestName + " e1Error Equals", E1ERROR,
					locationDataObject.getErrorEllipse().getE1Error(), 0);

			// check ellipseObject.e1Azimuth
			assertEquals(TestName + " e1Azimuth Equals", E1AZIMUTH,
					locationDataObject.getErrorEllipse().getE1Azimuth(), 0);

			// check ellipseObject.e1Dip
			assertEquals(TestName + " e1Dip Equals", E1DIP,
					locationDataObject.getErrorEllipse().getE1Dip(), 0);

			// check ellipseObject.e2Error
			assertEquals(TestName + " e2Error Equals", E2ERROR,
					locationDataObject.getErrorEllipse().getE2Error(), 0);

			// check ellipseObject.e2Azimuth
			assertEquals(TestName + " e2Azimuth Equals", E2AZIMUTH,
					locationDataObject.getErrorEllipse().getE2Azimuth(), 0);

			// check ellipseObject.e2Dip
			assertEquals(TestName + " e2Dip Equals", E2DIP,
					locationDataObject.getErrorEllipse().getE2Dip(), 0);

			// check ellipseObject.maximumHorizontalProjection
			assertEquals(TestName + " maximumHorizontalProjection Equals",
					MAXIMUMHORIZONTALPROJECTION, locationDataObject
							.getErrorEllipse().getMaximumHorizontalProjection(),
					0);

			// check ellipseObject.maximumVerticalProjection
			assertEquals(TestName + " maximumVerticalProjection Equals",
					MAXIMUMVERTICALPROJECTION, locationDataObject
							.getErrorEllipse().getMaximumVerticalProjection(),
					0);

			// check ellipseObject.equivalentHorizontalRadius
			assertEquals(TestName + " equivalentHorizontalRadius Equals",
					EQUIVILENTHORIZONTALRADIUS, locationDataObject
							.getErrorEllipse().getEquivalentHorizontalRadius(),
					0);
		}

	}

	public ArrayList<Pick> buildAssociatedData() {
		ArrayList<Pick> newAssociatedData = new ArrayList<Pick>();

		// Pick ?need one more?
		try {
			newAssociatedData
					.add(new Pick(Utility.fromJSONString(ASSOCIATEDDATA)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (newAssociatedData);
	}
}
