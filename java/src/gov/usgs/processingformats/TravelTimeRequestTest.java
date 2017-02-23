package gov.usgs.processingformats;

import static org.junit.Assert.*;

import org.json.simple.parser.ParseException;
import org.junit.Test;

public class TravelTimeRequestTest {

	public static String TRAVELTIMEREQUESTSTANDARD_STRING = "{\"Data\":"
			+ "{\"LocationUseFlag\":true,\"DistanceDerivative\":1.2,"
			+ "\"DepthDerivative\":3.45,\"AssociationWeightFlag\":true,"
			+ "\"Type\":\"TTData\",\"TeleseismicPhaseGroup\":1,\"Phase\":\"Pg\","
			+ "\"RayDerivative\":5.67,\"AuxiliaryPhaseGroup\":1,"
			+ "\"Observability\":0.34,\"StatisticalSpread\":1.5,"
			+ "\"TravelTime\":22.456},\"Distance\":12.45,\"Type\":\"Standard\","
			+ "\"Elevation\":5280.5,\"Latitude\":39.749444,"
			+ "\"Longitude\":-105.220305}";

	public static String TRAVELTIMEREQUESTPLOT_STRING = "{\"Data\":"
			+ "{\"Branches\":[{\"Phase\":\"Pg\",\"Samples\":[{\"Distance\":1.2,"
			+ "\"Observability\":0.34,\"StatisticalSpread\":1.5,"
			+ "\"TravelTime\":22.456},{\"Distance\":10.5,\"Observability\":1.63,"
			+ "\"StatisticalSpread\":2.1,\"TravelTime\":72.654}]},"
			+ "{\"Phase\":\"Sg\",\"Samples\":[{\"Distance\":3.2,"
			+ "\"Observability\":1.14,\"StatisticalSpread\":3.25,"
			+ "\"TravelTime\":132.456},{\"Distance\":100.5,"
			+ "\"Observability\":6.21,\"StatisticalSpread\":5.1,"
			+ "\"TravelTime\":542.654}]}],\"Type\":\"TTPlotData\","
			+ "\"MaximumTravelTime\":12.5},\"Distance\":12.45,\"Type\":\"Plot\","
			+ "\"Elevation\":5280.5,\"Latitude\":39.749444,\"Longitude\":-105.220305}";

	public static String TRAVELTIMEREQUESTPLOTSTATISTICS_STRING = "{\"Data\":"
			+ "{\"Branches\":[{\"Phase\":\"Pg\",\"Samples\":[{\"Distance\":1.2,"
			+ "\"Observability\":0.34,\"StatisticalSpread\":1.5,"
			+ "\"TravelTime\":22.456},{\"Distance\":10.5,\"Observability\":1.63,"
			+ "\"StatisticalSpread\":2.1,\"TravelTime\":72.654}]},"
			+ "{\"Phase\":\"Sg\",\"Samples\":[{\"Distance\":3.2,"
			+ "\"Observability\":1.14,\"StatisticalSpread\":3.25,"
			+ "\"TravelTime\":132.456},{\"Distance\":100.5,"
			+ "\"Observability\":6.21,\"StatisticalSpread\":5.1,"
			+ "\"TravelTime\":542.654}]}],\"Type\":\"TTPlotData\","
			+ "\"MaximumTravelTime\":12.5},\"Distance\":12.45,"
			+ "\"Type\":\"PlotStatistics\",\"Elevation\":5280.5,"
			+ "\"Latitude\":39.749444,\"Longitude\":-105.220305}";

	public static String TRAVELTIMEDATA_STRING = "{\"Type\":\"TTData\","
			+ "\"LocationUseFlag\":true,"
			+ "\"DistanceDerivative\":1.2,\"DepthDerivative\":3.45,"
			+ "\"AssociationWeightFlag\":true,\"TeleseismicPhaseGroup\":1,"
			+ "\"Phase\":\"Pg\",\"RayDerivative\":5.67,\"AuxiliaryPhaseGroup\":1,"
			+ "\"Observability\":0.34,\"StatisticalSpread\":1.5,\"TravelTime\":22.456}";

	public static String TRAVELTIMEPLOTDATA_STRING = "{\"Type\":\"TTPlotData\","
			+ "\"Branches\":[{\"Phase\":\"Pg\""
			+ ",\"Samples\":[{\"Distance\":1.2,\"Observability\":0.34,"
			+ "\"StatisticalSpread\":1.5,\"TravelTime\":22.456},{\"Distance\":10.5,"
			+ "\"Observability\":1.63,\"StatisticalSpread\":2.1,\"TravelTime\":72.654}]}"
			+ ",{\"Phase\":\"Sg\",\"Samples\":[{\"Distance\":3.2,\"Observability\":1.14,"
			+ "\"StatisticalSpread\":3.25,\"TravelTime\":132.456},{\"Distance\":100.5,"
			+ "\"Observability\":6.21,\"StatisticalSpread\":5.1,\"TravelTime\":542.654}]}],"
			+ "\"MaximumTravelTime\":12.5}";

	public static String TYPE_STANDARD = "Standard";
	public static String TYPE_PLOT = "Plot";
	public static String TYPE_PLOTSTATISTICS = "PlotStatistics";
	public static double DISTANCE = 12.45;
	public static double ELEVATION = 5280.5;
	public static double LATITUDE = 39.749444;
	public static double LONGITUDE = -105.220305;

	/**
	 * Able to write a JSON string
	 */
	@Test
	public void writesJSON() {

		// standard request
		TravelTimeRequest travelTimeRequestStandardObject = new TravelTimeRequest(
				TYPE_STANDARD, DISTANCE, ELEVATION, LATITUDE, LONGITUDE,
				buildData(), null);

		// write out to a string
		String jsonStandardString = Utility
				.toJSONString(travelTimeRequestStandardObject.toJSON());

		// check the data
		try {
			checkData(
					new TravelTimeRequest(
							Utility.fromJSONString(jsonStandardString)),
					"WritesJSON - Standard");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// plot request
		TravelTimeRequest travelTimeRequestPlotObject = new TravelTimeRequest(
				TYPE_PLOT, DISTANCE, ELEVATION, LATITUDE, LONGITUDE, null,
				buildPlotData());

		// write out to a string
		String jsonPlotString = Utility
				.toJSONString(travelTimeRequestPlotObject.toJSON());

		// check the data
		try {
			checkData(
					new TravelTimeRequest(
							Utility.fromJSONString(jsonPlotString)),
					"WritesJSON - Standard");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// plot statistics request
		TravelTimeRequest travelTimeRequestPlotStatisticsObject = new TravelTimeRequest(
				TYPE_PLOTSTATISTICS, DISTANCE, ELEVATION, LATITUDE, LONGITUDE,
				null, buildPlotData());

		// write out to a string
		String jsonPlotStatsiticsString = Utility
				.toJSONString(travelTimeRequestPlotStatisticsObject.toJSON());

		// check the data
		try {
			checkData(
					new TravelTimeRequest(
							Utility.fromJSONString(jsonPlotStatsiticsString)),
					"WritesJSON - Standard");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Able to read a JSON string
	 */
	@Test
	public void readsJSON() {

		// standard request
		try {

			checkData(
					new TravelTimeRequest(Utility
							.fromJSONString(TRAVELTIMEREQUESTSTANDARD_STRING)),
					"ReadsJSON - Standard");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// plot request
		try {

			checkData(
					new TravelTimeRequest(Utility
							.fromJSONString(TRAVELTIMEREQUESTPLOT_STRING)),
					"ReadsJSON - Plot");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// plot statistics request
		try {

			checkData(
					new TravelTimeRequest(Utility.fromJSONString(
							TRAVELTIMEREQUESTPLOTSTATISTICS_STRING)),
					"ReadsJSON - Plot Statistics ");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reload function fills in members correctly
	 */
	@Test
	public void reload() {

		// use constructor
		TravelTimeRequest travelTimeRequestObject = new TravelTimeRequest();

		travelTimeRequestObject.reload(TYPE_STANDARD, DISTANCE, ELEVATION,
				LATITUDE, LONGITUDE, buildData(), null);

		// check data values
		checkData(travelTimeRequestObject, "Reload Function");
	}

	/**
	 * Set functions fill in members correctly
	 */
	@Test
	public void setters() {

		// use constructor
		TravelTimeRequest travelTimeRequestObject = new TravelTimeRequest();

		travelTimeRequestObject.setType(TYPE_STANDARD);
		travelTimeRequestObject.setDistance(DISTANCE);
		travelTimeRequestObject.setElevation(ELEVATION);
		travelTimeRequestObject.setLatitude(LATITUDE);
		travelTimeRequestObject.setLongitude(LONGITUDE);
		travelTimeRequestObject.setData(buildData());
		travelTimeRequestObject.setPlotData(buildPlotData());

		// check data values
		checkData(travelTimeRequestObject, "Set Functions");
	}

	/**
	 * Copy constructor fills in members correctly
	 */
	@Test
	public void copyConstructor() {

		// use constructor
		TravelTimeRequest travelTimeRequestObject = new TravelTimeRequest(
				TYPE_STANDARD, DISTANCE, ELEVATION, LATITUDE, LONGITUDE,
				buildData(), null);

		TravelTimeRequest travelTimeRequestObject2 = new TravelTimeRequest(
				travelTimeRequestObject);

		// check data values
		checkData(travelTimeRequestObject2, "Copy Constructor");
	}

	/**
	 * Able to run validation function
	 */
	@Test
	public void validate() {

		// use constructor
		TravelTimeRequest travelTimeRequestObject = new TravelTimeRequest(
				TYPE_STANDARD, DISTANCE, ELEVATION, LATITUDE, LONGITUDE,
				buildData(), null);

		// Successful validation
		boolean rc = travelTimeRequestObject.isValid();

		// check return code
		assertEquals("Successful Validation", true, rc);

		// use constructor
		TravelTimeRequest badTravelTimeRequestObject = new TravelTimeRequest(
				TYPE_STANDARD, null, null, LATITUDE, LONGITUDE,
				buildData(), buildPlotData());

		rc = badTravelTimeRequestObject.isValid();

		// check return code
		assertEquals("Unsuccessful Validation", false, rc);

	}

	public void checkData(TravelTimeRequest travelTimeRequestObject,
			String TestName) {

		// check travelTimeRequestObject.type
		assertNotNull(TestName + " Type exists",
				travelTimeRequestObject.getType());

		// check type value
		if (!(travelTimeRequestObject.getType().equals("Standard")
				|| (travelTimeRequestObject.getType().equals("Plot"))
				|| (travelTimeRequestObject.getType()
						.equals("PlotStatistics")))) {
			fail(TestName + " Type is not valid");
		}

		// required
		// check travelTimeRequestObject.distance
		assertEquals(TestName + " Distance Equals", DISTANCE,
				travelTimeRequestObject.getDistance(), 0);

		// check travelTimeRequestObject.elevation
		assertEquals(TestName + " Elevation Equals", ELEVATION,
				travelTimeRequestObject.getElevation(), 0);

		// optional
		// check travelTimeRequestObject.latitude
		if (travelTimeRequestObject.getLatitude() != null) {
			assertEquals(TestName + " Latitude Equals", LATITUDE,
					travelTimeRequestObject.getLatitude(), 0);
		}
		// check travelTimeRequestObject.longitude
		if (travelTimeRequestObject.getLongitude() != null) {
			assertEquals(TestName + " Longitude Equals", LONGITUDE,
					travelTimeRequestObject.getLongitude(), 0);
		}
		// check travelTimeRequestObject.data
		if (travelTimeRequestObject.getData() != null) {
			assertTrue(TestName + " Data is valid",
					travelTimeRequestObject.getData().isValid());
		}
		// check travelTimeRequestObject.plotData
		if (travelTimeRequestObject.getPlotData() != null) {
			assertTrue(TestName + " Data is valid",
					travelTimeRequestObject.getPlotData().isValid());
		}
	}

	public TravelTimeData buildData() {

		try {
			return (new TravelTimeData(
					Utility.fromJSONString(TRAVELTIMEDATA_STRING)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (null);
		}

	}

	public TravelTimePlotData buildPlotData() {

		try {
			return (new TravelTimePlotData(
					Utility.fromJSONString(TRAVELTIMEPLOTDATA_STRING)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (null);
		}

	}

}
