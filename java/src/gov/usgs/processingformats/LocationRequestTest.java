package gov.usgs.processingformats;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.json.simple.parser.ParseException;
import org.junit.Test;

public class LocationRequestTest {

	public static final String LOCATIONREQUEST_STRING = "{\"EarthModel\":\"AK135\","
			+ "\"SourceLatitude\":40.3344,\"SourceLongitude\":-121.44,"
			+ "\"IsDepthHeld\":false,\"Type\":\"RayLoc\","
			+ "\"SourceDepth\":32.44,\"IsLocationHeld\":false,\"BayesianSpread\":"
			+ "20.3,\"UseSVD\":true,\"BayesianDepth\":66.7,"
			+ "\"SourceOriginTime\":\"2015-12-28T21:32:24.017Z\",\"InputData\":"
			+ "[{\"Site\":{\"Station\":\"BOZ\",\"Channel\":"
			+ "\"BHZ\",\"Network\":\"US\",\"Location\":\"00\","
			+ "\"Latitude\":45.59697,\"Longitude\":-111.62967,"
			+ "\"Elevation\":1589.0},\"PickedPhase\":\"P\",\"Use\":true,"
			+ "\"AssociatedPhase\":\"P\",\"Time\":\"2015-12-28T21:32:24.017Z\","
			+ "\"Residual\":1.05,\"Source\":{\"Type\":\"Unknown\",\"AgencyID\":"
			+ "\"US\",\"Author\":\"TestAuthor\"},\"Weight\":2.65,\"Importance\":"
			+ "3.8,\"Azimuth\":21.5,\"Quality\":0.45,\"Affinity\":1.2,"
			+ "\"ID\":\"12GFH48776857\",\"LocatedPhase\":\"P\",\"Distance\":2.65}],"
			+ "\"IsLocationNew\":false,\"IsBayesianDepth\":true}";

	public static String ID = "12345678";
	public static String TYPE = "RayLoc";
	public static String EARTHMODEL = "AK135";
	public static double SOURCELATITUDE = 40.3344;
	public static double SOURCELONGITUDE = -121.44;
	public static Date SOURCEORIGINTIME = Utility
			.getDate("2015-12-28T21:32:24.017Z");
	public static double SOURCEDEPTH = 32.44;

	public static String INPUTDDATA = "{\"ID\":\"12GFH48776857\","
			+ "\"Site\":{\"Station\":\"BOZ\",\"Channel\":"
			+ "\"BHZ\",\"Network\":\"US\",\"Location\":\"00\","
			+ "\"Latitude\":45.59697,\"Longitude\":-111.62967,"
			+ "\"Elevation\":1589.0},\"Source\":{\"Author\":\"TestAuthor\","
			+ "\"AgencyID\":\"US\",\"Type\":\"Unknown\"},"
			+ "\"Time\":\"2015-12-28T21:32:24.017Z\",\"Affinity\":1.2,"
			+ "\"Quality\":0.45,\"Use\":true,\"PickedPhase\":\"P\","
			+ "\"AssociatedPhase\":\"P\",\"LocatedPhase\":\"P\","
			+ "\"Residual\":1.05,\"Distance\":2.65,\"Azimuth\":21.5,"
			+ "\"Weight\":2.65,\"Importance\":3.8}";

	public static boolean ISLOCATIONNEW = false;
	public static boolean ISLOCATIONHELD = false;
	public static boolean ISDEPTHHELD = false;
	public static boolean ISBAYESIANDEPTH = true;
	public static double BAYESIANDEPTH = 66.7;
	public static double BAYESIANSPREAD = 20.3;
	public static boolean USESVD = true;

	public static final String OUTPUTDATA_STRING = "{\"MinimumDistance\":2.14,"
			+ "\"NumberOfUsedStations\":33,\"BayesianRange\":20.3,"
			+ "\"ErrorEllipse\":{\"MaximumVerticalProjection\":1.984,"
			+ "\"EquivalentHorizontalRadius\":1.984,"
			+ "\"MaximumHorizontalProjection\":1.984,\"E0\":{\"Azimuth\":-121.44,"
			+ "\"Error\":40.3344,\"Dip\":32.44},\"E1\":{\"Azimuth\":22.64,"
			+ "\"Error\":12.5,\"Dip\":2.44},\"E2\":{\"Azimuth\":22.64,"
			+ "\"Error\":12.5,\"Dip\":2.44}},\"SupportingData\":[{\"Site\":"
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

	/**
	 * Able to write a JSON string
	 */
	@Test
	public void writesJSON() {

		LocationRequest locationRequestObject = new LocationRequest(ID, TYPE,
				EARTHMODEL, SOURCELATITUDE, SOURCELONGITUDE, SOURCEORIGINTIME,
				SOURCEDEPTH, buildInputData(), ISLOCATIONNEW, ISLOCATIONHELD,
				ISDEPTHHELD, ISBAYESIANDEPTH, BAYESIANDEPTH, BAYESIANSPREAD,
				USESVD);

		// write out to a string
		String jsonString = Utility
				.toJSONString(locationRequestObject.toJSON());

		// check the data
		try {
			checkData(new LocationRequest(Utility.fromJSONString(jsonString)),
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
					new LocationRequest(
							Utility.fromJSONString(LOCATIONREQUEST_STRING)),
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

		LocationRequest locationRequestObject = new LocationRequest(ID, TYPE,
				EARTHMODEL, SOURCELATITUDE, SOURCELONGITUDE, SOURCEORIGINTIME,
				SOURCEDEPTH, buildInputData(), ISLOCATIONNEW, ISLOCATIONHELD,
				ISDEPTHHELD, ISBAYESIANDEPTH, BAYESIANDEPTH, BAYESIANSPREAD,
				USESVD);


		// Successful validation
		boolean rc = locationRequestObject.isValid();

		// check return code
		assertEquals("Successful Validation", true, rc);

		LocationRequest badLocationRequestObject = new LocationRequest(ID, null,
				EARTHMODEL, SOURCELATITUDE, null, SOURCEORIGINTIME,
				SOURCEDEPTH, null, ISLOCATIONNEW, ISLOCATIONHELD,
				ISDEPTHHELD, ISBAYESIANDEPTH, BAYESIANDEPTH, BAYESIANSPREAD,
				USESVD);

		rc = badLocationRequestObject.isValid();

		// check return code
		assertEquals("Unsuccessful Validation", false, rc);
	}

	public void checkData(LocationRequest locationRequestObject,
			String TestName) {

		// check LocationResultObject.sourceLatitude
		assertEquals(TestName + " Latitude Equals", SOURCELATITUDE,
				locationRequestObject.getSourceLatitude(), 0);

		// check LocationResultObject.sourceLongitude
		assertEquals(TestName + " Longitude Equals", SOURCELONGITUDE,
				locationRequestObject.getSourceLongitude(), 0);

		// check LocationResultObject.sourceDepth
		assertEquals(TestName + " Depth Equals", SOURCEDEPTH,
				locationRequestObject.getSourceDepth(), 0);

		// check LocationResultObject.sourceOriginTime
		assertEquals(TestName + " OriginTime Equals", SOURCEORIGINTIME,
				locationRequestObject.getSourceOriginTime());

		// need to check data still!!!!!!
		// somehow?

		// optional values
		// check locationRequestObject.id
		if (locationRequestObject.getID() != null) {
			assertEquals(TestName + " ID Equals",
					ID,
					locationRequestObject.getID());
		}

		// check locationRequestObject.isLocationNew
		if (locationRequestObject.getIsLocationNew() != null) {
			assertEquals(TestName + " IsLocationNew Equals", ISLOCATIONNEW,
					locationRequestObject.getIsLocationNew());
		}

		// check locationRequestObject.isLocationHeld
		if (locationRequestObject.getIsLocationHeld() != null) {
			assertEquals(TestName + " IsLocationHeld Equals", ISLOCATIONHELD,
					locationRequestObject.getIsLocationHeld());
		}

		// check locationRequestObject.isDepthHeld
		if (locationRequestObject.getIsDepthHeld() != null) {
			assertEquals(TestName + " IsDepthHeld Equals", ISDEPTHHELD,
					locationRequestObject.getIsDepthHeld());
		}

		// check locationRequestObject.isBayesianDepth
		if (locationRequestObject.getIsBayesianDepth() != null) {
			assertEquals(TestName + " IsBayesianDepth Equals", ISBAYESIANDEPTH,
					locationRequestObject.getIsBayesianDepth());
		}

		// check locationRequestObject.baysianDepth
		if (locationRequestObject.getBayesianDepth() != null) {
			assertEquals(TestName + " Bayesian Depth Equals", BAYESIANDEPTH,
					locationRequestObject.getBayesianDepth(), 0);
		}

		// check locationRequestObject.baysianSpread
		if (locationRequestObject.getBayesianSpread() != null) {
			assertEquals(TestName + " Bayesian Spread Equals", BAYESIANSPREAD,
					locationRequestObject.getBayesianSpread(), 0);
		}

		// check locationRequestObject.useSVD
		if (locationRequestObject.getUseSVD() != null) {
			assertEquals(TestName + " UseSVD Equals", USESVD,
					locationRequestObject.getUseSVD());
		}

		// Need to check output data somehow!!
	}

	public ArrayList<Pick> buildInputData() {
		ArrayList<Pick> newInputData = new ArrayList<Pick>();

		// Pick ?need one more?
		try {
			newInputData.add(new Pick(Utility.fromJSONString(INPUTDDATA)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (newInputData);
	}
}
