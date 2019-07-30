package gov.usgs.processingformats;

import static org.junit.Assert.*;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class TravelTimePlotDataSampleTest {

	public static String TRAVELTIMEPLOTDATASAMPLE_STRING = "{\"Distance\":1.2,"
			+ "\"TravelTime\":22.456,\"Observability\":0.34,"
			+ "\"StatisticalSpread\":1.5}";

	public static double DISTANCE = 1.2;
	public static double TRAVELTIME = 22.456;
	public static double STATISTICALSPREAD = 1.5;
	public static double OBSERVABILITY = .34;

	/**
	 * Able to write a JSON string
	 */
	@Test
	public void writesJSON() {

		TravelTimePlotDataSample travelTimePlotDataSampleObject = new TravelTimePlotDataSample(
				DISTANCE, TRAVELTIME, STATISTICALSPREAD, OBSERVABILITY);

		// write out to a string
		String jsonString = Utility
				.toJSONString(travelTimePlotDataSampleObject.toJSON());

		// check the data
		try {
			checkData(new TravelTimePlotDataSample(
					Utility.fromJSONString(jsonString)), "WritesJSON");
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
					new TravelTimePlotDataSample(Utility
							.fromJSONString(TRAVELTIMEPLOTDATASAMPLE_STRING)),
					"ReadsJSON");
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
		TravelTimePlotDataSample travelTimePlotDataSampleObject = new TravelTimePlotDataSample();

		travelTimePlotDataSampleObject.reload(DISTANCE, TRAVELTIME,
				STATISTICALSPREAD, OBSERVABILITY);

		// check data values
		checkData(travelTimePlotDataSampleObject, "Reload Function");
	}

	/**
	 * Set functions fill in members correctly
	 */
	@Test
	public void setters() {

		// use constructor
		TravelTimePlotDataSample travelTimePlotDataSampleObject = new TravelTimePlotDataSample();

		travelTimePlotDataSampleObject.setDistance(DISTANCE);
		travelTimePlotDataSampleObject.setTravelTime(TRAVELTIME);
		travelTimePlotDataSampleObject.setStatisticalSpread(STATISTICALSPREAD);
		travelTimePlotDataSampleObject.setObservability(OBSERVABILITY);

		// check data values
		checkData(travelTimePlotDataSampleObject, "Set Functions");
	}

	/**
	 * Copy constructor fills in members correctly
	 */
	@Test
	public void copyConstructor() {

		// use constructor
		TravelTimePlotDataSample travelTimePlotDataSampleObject = new TravelTimePlotDataSample(
				DISTANCE, TRAVELTIME, STATISTICALSPREAD, OBSERVABILITY);

		TravelTimePlotDataSample travelTimePlotDataSampleObject2 = new TravelTimePlotDataSample(
				travelTimePlotDataSampleObject);

		// check data values
		checkData(travelTimePlotDataSampleObject2, "Copy Constructor");
	}

	/**
	 * Able to run validation function
	 */
	@Test
	public void validate() {

		// use constructor
		TravelTimePlotDataSample travelTimePlotDataSampleObject = new TravelTimePlotDataSample(
				DISTANCE, TRAVELTIME, STATISTICALSPREAD, OBSERVABILITY);

		// Successful validation
		boolean rc = travelTimePlotDataSampleObject.isValid();

		// check return code
		assertEquals("Successful Validation", true, rc);

		// use constructor
		TravelTimePlotDataSample badTravelTimePlotDataSampleObject = new TravelTimePlotDataSample(
				null, TRAVELTIME, STATISTICALSPREAD, null);

		rc = badTravelTimePlotDataSampleObject.isValid();

		// check return code
		assertEquals("Unsuccessful Validation", false, rc);

	}
	
	public void checkData(TravelTimePlotDataSample travelTimePlotDataSampleObject,
			String TestName) {

		// check travelTimeDataObject.distance
		assertEquals(TestName + " Distance Equals", DISTANCE,
				travelTimePlotDataSampleObject.getDistance(), 0);

		// check travelTimeDataObject.travelTime
		assertEquals(TestName + " Travel Time Equals", TRAVELTIME,
				travelTimePlotDataSampleObject.getTravelTime(), 0);

		// check travelTimeDataObject.statisticalSpread
		assertEquals(TestName + " Statistical Spread Equals", STATISTICALSPREAD,
				travelTimePlotDataSampleObject.getStatisticalSpread(), 0);

		// check travelTimeDataObject.observability
		assertEquals(TestName + " Observability Equals", OBSERVABILITY,
				travelTimePlotDataSampleObject.getObservability(), 0);


	}	

}
