package gov.usgs.processingformats;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.junit.Test;

public class TravelTimePlotDataBranchTest {

	public static String TRAVELTIMEDATABRANCH_STRING = "{\"Phase\":\"Pg\","
			+ "\"Samples\":[{\"Distance\":1.2,\"Observability\":0.34,"
			+ "\"StatisticalSpread\":1.5,\"TravelTime\":22.456},"
			+ "{\"Distance\":10.5,\"Observability\":1.63,"
			+ "\"StatisticalSpread\":2.1,\"TravelTime\":72.654}]}";

	public static String PHASE = "Pg";
	public static Integer NUMSAMPLES = 2;
	public static String SAMPLEDATA_STRING = "{\"Distance\":1.2,"
			+ "\"TravelTime\":22.456,\"Observability\":0.34,"
			+ "\"StatisticalSpread\":1.5}";
	public static double DISTANCE = 1.2;
	public static double TRAVELTIME = 22.456;
	public static double STATISTICALSPREAD = 1.5;
	public static double OBSERVABILITY = .34;
	public static String SAMPLEDATA2_STRING = "{\"Distance\":10.5,"
			+ "\"TravelTime\":72.654,\"Observability\":1.63,"
			+ "\"StatisticalSpread\":2.1}";
	public static double DISTANCE2 = 10.5;
	public static double TRAVELTIME2 = 72.654;
	public static double STATISTICALSPREAD2 = 2.1;
	public static double OBSERVABILITY2 = 1.63;

	/**
	 * Able to write a JSON string
	 */
	@Test
	public void writesJSON() {

		TravelTimePlotDataBranch travelTimePlotDataBranchObject = new TravelTimePlotDataBranch(
				PHASE, buildSampleData());

		// write out to a string
		String jsonString = Utility
				.toJSONString(travelTimePlotDataBranchObject.toJSON());

		// check the data
		try {
			checkData(new TravelTimePlotDataBranch(
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
					new TravelTimePlotDataBranch(Utility
							.fromJSONString(TRAVELTIMEDATABRANCH_STRING)),
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
		TravelTimePlotDataBranch travelTimePlotDataBranchObject = new TravelTimePlotDataBranch();

		travelTimePlotDataBranchObject.reload(PHASE, buildSampleData());

		// check data values
		checkData(travelTimePlotDataBranchObject, "Reload Function");
	}

	/**
	 * Set functions fill in members correctly
	 */
	@Test
	public void setters() {

		// use constructor
		TravelTimePlotDataBranch travelTimePlotDataBranchObject = new TravelTimePlotDataBranch();

		travelTimePlotDataBranchObject.setPhase(PHASE);
		travelTimePlotDataBranchObject.setSamples(buildSampleData());

		// check data values
		checkData(travelTimePlotDataBranchObject, "Set Functions");
	}

	/**
	 * Copy constructor fills in members correctly
	 */
	@Test
	public void copyConstructor() {

		// use constructor
		TravelTimePlotDataBranch travelTimePlotDataBranchObject = new TravelTimePlotDataBranch(
				PHASE, buildSampleData());

		TravelTimePlotDataBranch travelTimePlotDataBranchObject2 = new TravelTimePlotDataBranch(
				travelTimePlotDataBranchObject);

		// check data values
		checkData(travelTimePlotDataBranchObject2, "Copy Constructor");
	}

	/**
	 * Able to run validation function
	 */
	@Test
	public void validate() {

		// use constructor
		TravelTimePlotDataBranch travelTimePlotDataBranchObject = new TravelTimePlotDataBranch(
				PHASE, buildSampleData());

		// Successful validation
		boolean rc = travelTimePlotDataBranchObject.isValid();

		// check return code
		assertEquals("Successful Validation", true, rc);

		// use constructor
		TravelTimePlotDataBranch badTravelTimePlotDataBranchObjectt = new TravelTimePlotDataBranch(
				null, buildSampleData());

		rc = badTravelTimePlotDataBranchObjectt.isValid();

		// check return code
		assertEquals("Unsuccessful Validation", false, rc);

	}

	public void checkData(
			TravelTimePlotDataBranch travelTimePlotDataBranchObject,
			String TestName) {

		// check travelTimePlotDataBranchObject.phase
		assertEquals(TestName + " Phase Equals", PHASE,
				travelTimePlotDataBranchObject.getPhase());

		// check sample data
		// check number of samples
		assertEquals(TestName + " Number of Samples Equals", (int) NUMSAMPLES,
				travelTimePlotDataBranchObject.getSamples().size(), 0);

		// first sample
		// check travelTimePlotDataBranchObject.samples[0].distance
		assertEquals(TestName + " Distance Equals", DISTANCE,
				travelTimePlotDataBranchObject.getSamples().get(0)
						.getDistance(),
				0);

		// check ravelTimePlotDataBranchObject.samples[0].travelTime
		assertEquals(TestName + " Travel Time Equals", TRAVELTIME,
				travelTimePlotDataBranchObject.getSamples().get(0)
						.getTravelTime(),
				0);

		// check ravelTimePlotDataBranchObject.samples[0].statisticalSpread
		assertEquals(TestName + " Statistical Spread Equals", STATISTICALSPREAD,
				travelTimePlotDataBranchObject.getSamples().get(0)
						.getStatisticalSpread(),
				0);

		// check ravelTimePlotDataBranchObject.samples[0].observability
		assertEquals(TestName + " Observability Equals", OBSERVABILITY,
				travelTimePlotDataBranchObject.getSamples().get(0)
						.getObservability(),
				0);

		// second sample
		// check travelTimePlotDataBranchObject.samples[1].distance
		assertEquals(TestName + " Distance Equals", DISTANCE2,
				travelTimePlotDataBranchObject.getSamples().get(1)
						.getDistance(),
				0);

		// check ravelTimePlotDataBranchObject.samples[1].travelTime
		assertEquals(TestName + " Travel Time Equals", TRAVELTIME2,
				travelTimePlotDataBranchObject.getSamples().get(1)
						.getTravelTime(),
				0);

		// check ravelTimePlotDataBranchObject.samples[1].statisticalSpread
		assertEquals(TestName + " Statistical Spread Equals",
				STATISTICALSPREAD2, travelTimePlotDataBranchObject.getSamples()
						.get(1).getStatisticalSpread(),
				0);

		// check ravelTimePlotDataBranchObject.samples[1].observability
		assertEquals(TestName + " Observability Equals", OBSERVABILITY2,
				travelTimePlotDataBranchObject.getSamples().get(1)
						.getObservability(),
				0);

	}

	public ArrayList<TravelTimePlotDataSample> buildSampleData() {
		ArrayList<TravelTimePlotDataSample> newSampleData = new ArrayList<TravelTimePlotDataSample>();

		// Pick ?need one more?
		try {
			newSampleData.add(new TravelTimePlotDataSample(
					Utility.fromJSONString(SAMPLEDATA_STRING)));
			newSampleData.add(new TravelTimePlotDataSample(
					Utility.fromJSONString(SAMPLEDATA2_STRING)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (newSampleData);
	}

}
