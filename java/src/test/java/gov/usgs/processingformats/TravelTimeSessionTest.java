package gov.usgs.processingformats;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.junit.Test;

public class TravelTimeSessionTest {

	public static String TRAVELTIMESESSION_STRING = "{\"ConvertTectonic\":true,"
			+ "\"ReturnBackBranches\":true,\"PhaseTypes\":[\"P\",\"S\",\"PDiff\"],"
			+ "\"SourceLatitude\":39.749444,\"ReturnAllPhases\":true,"
			+ "\"EarthModel\":\"AK135\",\"UseRSTT\":false,"
			+ "\"SourceLongitude\":-105.220305,\"IsPlot\":false,"
			+ "\"SourceDepth\":15.2}";

	public static double SOURCEDEPTH = 15.2;
	public static String EARTHMODEL = "AK135";
	public static String PHASETYPE1 = "P";
	public static String PHASETYPE2 = "S";
	public static String PHASETYPE3 = "PDiff";
	public static double SOURCELATITUDE = 39.749444;
	public static double SOURCELONGITUDE = -105.220305;
	public static boolean RETURNALLPHASES = true;
	public static boolean RETURNBACKBRANCHES = true;
	public static boolean CONVERTTECTONIC = true;
	public static boolean ISPLOT = false;

	/**
	 * Able to write a JSON string
	 */
	@Test
	public void writesJSON() {

		TravelTimeSession travelTimeSessionObject = new TravelTimeSession(
				SOURCEDEPTH, EARTHMODEL, buildPhaseTypes(), SOURCELATITUDE,
				SOURCELONGITUDE, RETURNALLPHASES, RETURNBACKBRANCHES,
				CONVERTTECTONIC, ISPLOT);

		// write out to a string
		String jsonString = Utility
				.toJSONString(travelTimeSessionObject.toJSON());

		// check the data
		try {
			checkData(new TravelTimeSession(Utility.fromJSONString(jsonString)),
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
					new TravelTimeSession(
							Utility.fromJSONString(TRAVELTIMESESSION_STRING)),
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
		TravelTimeSession travelTimeSessionObject = new TravelTimeSession();

		travelTimeSessionObject.reload(SOURCEDEPTH, EARTHMODEL,
				buildPhaseTypes(), SOURCELATITUDE, SOURCELONGITUDE,
				RETURNALLPHASES, RETURNBACKBRANCHES, CONVERTTECTONIC,
				ISPLOT);

		// check data values
		checkData(travelTimeSessionObject, "Reload Function");
	}

	/**
	 * Set functions fill in members correctly
	 */
	@Test
	public void setters() {

		// use constructor
		TravelTimeSession travelTimeSessionObject = new TravelTimeSession();

		travelTimeSessionObject.SourceDepth = SOURCEDEPTH;
		travelTimeSessionObject.EarthModel = EARTHMODEL;
		travelTimeSessionObject.PhaseTypes = buildPhaseTypes();
		travelTimeSessionObject.SourceLatitude = SOURCELATITUDE;
		travelTimeSessionObject.SourceLongitude = SOURCELONGITUDE;
		travelTimeSessionObject.ReturnAllPhases = RETURNALLPHASES;
		travelTimeSessionObject.ReturnBackBranches = RETURNBACKBRANCHES;
		travelTimeSessionObject.ConvertTectonic = CONVERTTECTONIC;
		travelTimeSessionObject.IsPlot = ISPLOT;

		// check data values
		checkData(travelTimeSessionObject, "Set Functions");
	}

	/**
	 * Copy constructor fills in members correctly
	 */
	@Test
	public void copyConstructor() {

		// use constructor
		TravelTimeSession travelTimeSessionObject = new TravelTimeSession(
				SOURCEDEPTH, EARTHMODEL, buildPhaseTypes(), SOURCELATITUDE,
				SOURCELONGITUDE, RETURNALLPHASES, RETURNBACKBRANCHES,
				CONVERTTECTONIC, ISPLOT);

		TravelTimeSession travelTimeSessionObject2 = new TravelTimeSession(
				travelTimeSessionObject);

		// check data values
		checkData(travelTimeSessionObject2, "Copy Constructor");
	}

	/**
	 * Able to run validation function
	 */
	@Test
	public void validate() {

		// use constructor
		TravelTimeSession travelTimeSessionObject = new TravelTimeSession(
				SOURCEDEPTH, EARTHMODEL, buildPhaseTypes(), SOURCELATITUDE,
				SOURCELONGITUDE, RETURNALLPHASES, RETURNBACKBRANCHES,
				CONVERTTECTONIC, ISPLOT);

		// Successful validation
		boolean rc = travelTimeSessionObject.isValid();

		// check return code
		assertEquals("Successful Validation", true, rc);

		// use constructor
		TravelTimeSession badtravelTimeSessionObject = new TravelTimeSession(
				null, EARTHMODEL, null, -220.0, -220.0, RETURNALLPHASES,
				RETURNBACKBRANCHES, null, ISPLOT);

		rc = badtravelTimeSessionObject.isValid();

		// check return code
		assertEquals("Unsuccessful Validation", false, rc);

	}

	public void checkData(TravelTimeSession travelTimeSessionObject,
			String TestName) {

		// required
		// check travelTimeSessionObject.distance
		assertEquals(TestName + " Source Depth Equals", SOURCEDEPTH,
				travelTimeSessionObject.SourceDepth, 0);

		// optional
		// travelTimeSessionObject.earthModel
		if (travelTimeSessionObject.EarthModel != null) {
			assertEquals(TestName + " Earth Model Equals", EARTHMODEL,
					travelTimeSessionObject.EarthModel);
		}

		// check travelTimeSessionObject.phaseTypes
		if ((travelTimeSessionObject.PhaseTypes != null)
				&& (!travelTimeSessionObject.PhaseTypes.isEmpty())) {

			// check travelTimeSessionObject.phaseTypes[0]
			assertEquals(TestName + " Phase Type 1 Equals", PHASETYPE1,
					travelTimeSessionObject.PhaseTypes.get(0));

			// check travelTimeSessionObject.phaseTypes[1]
			assertEquals(TestName + " Phase Type 2 Equals", PHASETYPE2,
					travelTimeSessionObject.PhaseTypes.get(1));

			// check travelTimeSessionObject.phaseTypes[2]
			assertEquals(TestName + " Phase Type 3 Equals", PHASETYPE3,
					travelTimeSessionObject.PhaseTypes.get(2));
		}

		// check travelTimeSessionObject.sourceLatitude
		if (travelTimeSessionObject.SourceLatitude != null) {
			assertEquals(TestName + " Latitude Equals", SOURCELATITUDE,
					travelTimeSessionObject.SourceLatitude, 0);
		}

		// check travelTimeSessionObject.sourceLongitude
		if (travelTimeSessionObject.SourceLongitude != null) {
			assertEquals(TestName + " Longitude Equals", SOURCELONGITUDE,
					travelTimeSessionObject.SourceLongitude, 0);
		}

		// check travelTimeSessionObject.returnAllPhases
		if (travelTimeSessionObject.ReturnAllPhases != null) {
			assertEquals(TestName + " Return All Phases Equals ",
					travelTimeSessionObject.ReturnAllPhases,
					RETURNALLPHASES);
		}

		// check travelTimeSessionObject.returnBackBranches
		if (travelTimeSessionObject.ReturnBackBranches != null) {
			assertEquals(TestName + " Return Back Branchs Equals ",
					travelTimeSessionObject.ReturnBackBranches,
					RETURNBACKBRANCHES);
		}

		// check travelTimeSessionObject.convertTectonic
		if (travelTimeSessionObject.ConvertTectonic != null) {
			assertEquals(TestName + " Convert Tectonic Equals ",
					travelTimeSessionObject.ConvertTectonic,
					CONVERTTECTONIC);
		}

		// check travelTimeSessionObject.isPLot
		if (travelTimeSessionObject.IsPlot != null) {
			assertEquals(TestName + " Is Plot Equals ",
					travelTimeSessionObject.IsPlot, ISPLOT);
		}
	}

	public ArrayList<String> buildPhaseTypes() {

		ArrayList<String> phaseTypes = new ArrayList<String>();

		phaseTypes.add(PHASETYPE1);
		phaseTypes.add(PHASETYPE2);
		phaseTypes.add(PHASETYPE3);

		return (phaseTypes);
	}

}
