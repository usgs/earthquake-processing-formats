package gov.usgs.processingformats;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.junit.Test;

public class TravelTimePlotDataTest {

	public static String TRAVELTIMEPLOTDATA_STRING = "{\"Type\":\"TTPlotData\","
			+ "\"Branches\":[{\"Phase\":\"Pg\""
			+ ",\"Samples\":[{\"Distance\":1.2,\"Observability\":0.34,"
			+ "\"StatisticalSpread\":1.5,\"TravelTime\":22.456},{\"Distance\":10.5,"
			+ "\"Observability\":1.63,\"StatisticalSpread\":2.1,\"TravelTime\":72.654}]}"
			+ ",{\"Phase\":\"Sg\",\"Samples\":[{\"Distance\":3.2,\"Observability\":1.14,"
			+ "\"StatisticalSpread\":3.25,\"TravelTime\":132.456},{\"Distance\":100.5,"
			+ "\"Observability\":6.21,\"StatisticalSpread\":5.1,\"TravelTime\":542.654}]}],"
			+ "\"MaximumTravelTime\":12.5}";

	public static double MAXIMIUMTRAVELTIME = 12.5;
	public static int NUMBRANCHES = 2;
	public static int NUMSAMPLES = 2;
	public static String BRANCH_STRING = "{\"Phase\":\"Pg\","
			+ "\"Samples\":[{\"Distance\":1.2,\"Observability\":0.34,"
			+ "\"StatisticalSpread\":1.5,\"TravelTime\":22.456},"
			+ "{\"Distance\":10.5,\"Observability\":1.63,"
			+ "\"StatisticalSpread\":2.1,\"TravelTime\":72.654}]}";
	public static String PHASE1 = "Pg";
	public static double DISTANCE1 = 1.2;
	public static double TRAVELTIME1 = 22.456;
	public static double STATISTICALSPREAD1 = 1.5;
	public static double OBSERVABILITY1 = .34;
	public static double DISTANCE12 = 10.5;
	public static double TRAVELTIME12 = 72.654;
	public static double STATISTICALSPREAD12 = 2.1;
	public static double OBSERVABILITY12 = 1.63;
	public static String BRANCH2_STRING = "{\"Phase\":\"Sg\","
			+ "\"Samples\":[{\"Distance\":3.2,\"Observability\":1.14,"
			+ "\"StatisticalSpread\":3.25,\"TravelTime\":132.456},"
			+ "{\"Distance\":100.5,\"Observability\":6.21,"
			+ "\"StatisticalSpread\":5.1,\"TravelTime\":542.654}]}";
	public static String PHASE2 = "Sg";
	public static double DISTANCE2 = 3.2;
	public static double TRAVELTIME2 = 132.456;
	public static double STATISTICALSPREAD2 = 3.25;
	public static double OBSERVABILITY2 = 1.14;
	public static double DISTANCE22 = 100.5;
	public static double TRAVELTIME22 = 542.654;
	public static double STATISTICALSPREAD22 = 5.1;
	public static double OBSERVABILITY22 = 6.21;

	/**
	 * Able to write a JSON string
	 */
	@Test
	public void writesJSON() {

		TravelTimePlotData travelTimePlotDataObject = new TravelTimePlotData(
				MAXIMIUMTRAVELTIME, buildBranchData());

		// write out to a string
		String jsonString = Utility
				.toJSONString(travelTimePlotDataObject.toJSON());

		// check the data
		try {
			checkData(
					new TravelTimePlotData(Utility.fromJSONString(jsonString)),
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
					new TravelTimePlotData(
							Utility.fromJSONString(TRAVELTIMEPLOTDATA_STRING)),
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
		TravelTimePlotData travelTimePlotDataObject = new TravelTimePlotData();

		travelTimePlotDataObject.reload(MAXIMIUMTRAVELTIME, buildBranchData());

		// check data values
		checkData(travelTimePlotDataObject, "Reload Function");
	}

	/**
	 * Set functions fill in members correctly
	 */
	@Test
	public void setters() {

		// use constructor
		TravelTimePlotData travelTimePlotDataObject = new TravelTimePlotData();

		travelTimePlotDataObject.MaximumTravelTime = MAXIMIUMTRAVELTIME;
		travelTimePlotDataObject.Branches = buildBranchData();

		// check data values
		checkData(travelTimePlotDataObject, "Set Functions");
	}

	/**
	 * Copy constructor fills in members correctly
	 */
	@Test
	public void copyConstructor() {

		// use constructor
		TravelTimePlotData travelTimePlotDataObject = new TravelTimePlotData(
				MAXIMIUMTRAVELTIME, buildBranchData());

		TravelTimePlotData travelTimePlotDataObject2 = new TravelTimePlotData(
				travelTimePlotDataObject);

		// check data values
		checkData(travelTimePlotDataObject2, "Copy Constructor");
	}

	/**
	 * Able to run validation function
	 */
	@Test
	public void validate() {

		// use constructor
		TravelTimePlotData travelTimePlotDataObject = new TravelTimePlotData(
				MAXIMIUMTRAVELTIME, buildBranchData());

		// Successful validation
		boolean rc = travelTimePlotDataObject.isValid();

		// check return code
		assertEquals("Successful Validation", true, rc);

		// use constructor
		TravelTimePlotData badTravelTimePlotDataObject = new TravelTimePlotData(
				null, null);

		rc = badTravelTimePlotDataObject.isValid();

		// check return code
		assertEquals("Unsuccessful Validation", false, rc);

	}

	public void checkData(TravelTimePlotData travelTimePlotDataObject,
			String TestName) {

		// check travelTimePlotDataObject.maximumTravelTime
		assertEquals(TestName + " MaximumTravelTime Equals", MAXIMIUMTRAVELTIME,
				travelTimePlotDataObject.MaximumTravelTime, 0);

		// check branch data
		// check number of branches
		assertEquals(TestName + " Number of Branches Equals", (int) NUMBRANCHES,
				travelTimePlotDataObject.Branches.size(), 0);

		// check travelTimePlotDataBranchObject.phase
		assertEquals(TestName + " Phase Equals", PHASE1,
				travelTimePlotDataObject.Branches.get(0).Phase);

		// check branch data
		// check number of samples
		assertEquals(TestName + " Number of Samples Equals", (int) NUMSAMPLES,
				travelTimePlotDataObject.Branches.get(0).Samples
						.size(),
				0);

		// first sample
		// check travelTimePlotDataBranchObject.samples[0].distance
		assertEquals(TestName + " Distance Equals", DISTANCE1,
				travelTimePlotDataObject.Branches.get(0).Samples
						.get(0).Distance,
				0);

		// check ravelTimePlotDataBranchObject.samples[0].travelTime
		assertEquals(TestName + " Travel Time Equals", TRAVELTIME1,
				travelTimePlotDataObject.Branches.get(0).Samples
						.get(0).TravelTime,
				0);

		// check ravelTimePlotDataBranchObject.samples[0].statisticalSpread
		assertEquals(TestName + " Statistical Spread Equals",
				STATISTICALSPREAD1, travelTimePlotDataObject.Branches
						.get(0).Samples.get(0).StatisticalSpread,
				0);

		// check travelTimePlotDataBranchObject.samples[0].observability
		assertEquals(TestName + " Observability Equals", OBSERVABILITY1,
				travelTimePlotDataObject.Branches.get(0).Samples
						.get(0).Observability,
				0);

		// second sample
		// check travelTimePlotDataBranchObject.samples[1].distance
		assertEquals(TestName + " Distance Equals", DISTANCE12,
				travelTimePlotDataObject.Branches.get(0).Samples
						.get(1).Distance,
				0);

		// check ravelTimePlotDataBranchObject.samples[1].travelTime
		assertEquals(TestName + " Travel Time Equals", TRAVELTIME12,
				travelTimePlotDataObject.Branches.get(0).Samples
						.get(1).TravelTime,
				0);

		// check ravelTimePlotDataBranchObject.samples[1].statisticalSpread
		assertEquals(TestName + " Statistical Spread Equals",
				STATISTICALSPREAD12, travelTimePlotDataObject.Branches
						.get(0).Samples.get(1).StatisticalSpread,
				0);

		// check ravelTimePlotDataBranchObject.samples[1].observability
		assertEquals(TestName + " Observability Equals", OBSERVABILITY12,
				travelTimePlotDataObject.Branches.get(0).Samples
						.get(1).Observability,
				0);

		// check travelTimePlotDataBranchObject.phase
		assertEquals(TestName + " Phase Equals", PHASE2,
				travelTimePlotDataObject.Branches.get(1).Phase);

		// check sample data
		// check number of samples
		assertEquals(TestName + " Number of Samples Equals", (int) NUMSAMPLES,
				travelTimePlotDataObject.Branches.get(1).Samples
						.size(),
				0);

		// first sample
		// check travelTimePlotDataBranchObject.samples[0].distance
		assertEquals(TestName + " Distance Equals", DISTANCE2,
				travelTimePlotDataObject.Branches.get(1).Samples
						.get(0).Distance,
				0);

		// check ravelTimePlotDataBranchObject.samples[0].travelTime
		assertEquals(TestName + " Travel Time Equals", TRAVELTIME2,
				travelTimePlotDataObject.Branches.get(1).Samples
						.get(0).TravelTime,
				0);

		// check ravelTimePlotDataBranchObject.samples[0].statisticalSpread
		assertEquals(TestName + " Statistical Spread Equals",
				STATISTICALSPREAD2, travelTimePlotDataObject.Branches
						.get(1).Samples.get(0).StatisticalSpread,
				0);

		// check ravelTimePlotDataBranchObject.samples[0].observability
		assertEquals(TestName + " Observability Equals", OBSERVABILITY2,
				travelTimePlotDataObject.Branches.get(1).Samples
						.get(0).Observability,
				0);

		// second sample
		// check travelTimePlotDataBranchObject.samples[1].distance
		assertEquals(TestName + " Distance Equals", DISTANCE22,
				travelTimePlotDataObject.Branches.get(1).Samples
						.get(1).Distance,
				0);

		// check ravelTimePlotDataBranchObject.samples[1].travelTime
		assertEquals(TestName + " Travel Time Equals", TRAVELTIME22,
				travelTimePlotDataObject.Branches.get(1).Samples
						.get(1).TravelTime,
				0);

		// check ravelTimePlotDataBranchObject.samples[1].statisticalSpread
		assertEquals(TestName + " Statistical Spread Equals",
				STATISTICALSPREAD22, travelTimePlotDataObject.Branches
						.get(1).Samples.get(1).StatisticalSpread,
				0);

		// check ravelTimePlotDataBranchObject.samples[1].observability
		assertEquals(TestName + " Observability Equals", OBSERVABILITY22,
				travelTimePlotDataObject.Branches.get(1).Samples
						.get(1).Observability,
				0);

	}

	public ArrayList<TravelTimePlotDataBranch> buildBranchData() {
		ArrayList<TravelTimePlotDataBranch> newBranchData = new ArrayList<TravelTimePlotDataBranch>();

		// branches
		try {
			newBranchData.add(new TravelTimePlotDataBranch(
					Utility.fromJSONString(BRANCH_STRING)));
			newBranchData.add(new TravelTimePlotDataBranch(
					Utility.fromJSONString(BRANCH2_STRING)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (newBranchData);
	}

}
