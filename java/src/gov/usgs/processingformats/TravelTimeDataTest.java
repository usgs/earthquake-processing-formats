package gov.usgs.processingformats;

import static org.junit.Assert.*;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class TravelTimeDataTest {

	public static String TRAVELTIMEDATA_STRING = "{\"LocationUseFlag\":true,"
			+ "\"DistanceDerivative\":1.2,\"DepthDerivative\":3.45,"
			+ "\"AssociationWeightFlag\":true,\"TeleseismicPhaseGroup\":1,"
			+ "\"Phase\":\"Pg\",\"RayDerivative\":5.67,\"AuxiliaryPhaseGroup\":1,"
			+ "\"Observability\":0.34,\"StatisticalSpread\":1.5,\"TravelTime\":22.456}";

	public static String PHASE = "Pg";
	public static double TRAVELTIME = 22.456;
	public static double DISTANCEDERIVATIVE = 1.2;
	public static double DEPTHDERIVATIVE = 3.45;
	public static double RAYDERIVATIVE = 5.67;
	public static double STATISTICALSPREAD = 1.5;
	public static double OBSERVABILITY = .34;
	public static long TELESEISMICPHASEGROUP = 1;
	public static long AUXILIARYPHASEGROUP = 1;
	public static boolean LOCATIONUSEFLAG = true;
	public static boolean ASSOCIATIONWEIGHTFLAG = true;

	/**
	 * Able to write a JSON string
	 */
	@Test
	public void writesJSON() {

		TravelTimeData travelTimeDataObject = new TravelTimeData(PHASE,
				TRAVELTIME, DISTANCEDERIVATIVE, DEPTHDERIVATIVE, RAYDERIVATIVE,
				STATISTICALSPREAD, OBSERVABILITY, TELESEISMICPHASEGROUP,
				AUXILIARYPHASEGROUP, LOCATIONUSEFLAG, ASSOCIATIONWEIGHTFLAG);

		// write out to a string
		String jsonString = Utility.toJSONString(travelTimeDataObject.toJSON());

		// check the data
		try {
			checkData(new TravelTimeData(Utility.fromJSONString(jsonString)),
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
					new TravelTimeData(
							Utility.fromJSONString(TRAVELTIMEDATA_STRING)),
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
		TravelTimeData travelTimeDataObject = new TravelTimeData();

		travelTimeDataObject.reload(PHASE, TRAVELTIME, DISTANCEDERIVATIVE,
				DEPTHDERIVATIVE, RAYDERIVATIVE, STATISTICALSPREAD,
				OBSERVABILITY, TELESEISMICPHASEGROUP, AUXILIARYPHASEGROUP,
				LOCATIONUSEFLAG, ASSOCIATIONWEIGHTFLAG);

		// check data values
		checkData(travelTimeDataObject, "Reload Function");
	}

	/**
	 * Set functions fill in members correctly
	 */
	@Test
	public void setters() {

		// use constructor
		TravelTimeData travelTimeDataObject = new TravelTimeData();

		travelTimeDataObject.setPhase(PHASE);
		travelTimeDataObject.setTravelTime(TRAVELTIME); 
		travelTimeDataObject.setDistanceDerivative(DISTANCEDERIVATIVE);
		travelTimeDataObject.setDepthDerivative(DEPTHDERIVATIVE); 
		travelTimeDataObject.setRayDerivative(RAYDERIVATIVE); 
		travelTimeDataObject.setStatisticalSpread(STATISTICALSPREAD);
		travelTimeDataObject.setObservability(OBSERVABILITY); 
		travelTimeDataObject.setTeleseismicPhaseGroup(TELESEISMICPHASEGROUP); 
		travelTimeDataObject.setAuxiliaryPhaseGroup(AUXILIARYPHASEGROUP);
		travelTimeDataObject.setLocationUseFlag(LOCATIONUSEFLAG); 
		travelTimeDataObject.setAssociationWeightFlag(ASSOCIATIONWEIGHTFLAG);

		// check data values
		checkData(travelTimeDataObject, "Set Functions");
	}	
	
	/**
	 * Copy constructor fills in members correctly
	 */
	@Test
	public void copyConstructor() {

		// use constructor
		TravelTimeData travelTimeDataObject = new TravelTimeData(PHASE,
				TRAVELTIME, DISTANCEDERIVATIVE, DEPTHDERIVATIVE, RAYDERIVATIVE,
				STATISTICALSPREAD, OBSERVABILITY, TELESEISMICPHASEGROUP,
				AUXILIARYPHASEGROUP, LOCATIONUSEFLAG, ASSOCIATIONWEIGHTFLAG);

		TravelTimeData travelTimeDataObject2 = new TravelTimeData(
				travelTimeDataObject);

		// check data values
		checkData(travelTimeDataObject2, "Copy Constructor");
	}

	/**
	 * Able to run validation function
	 */
	@Test
	public void validate() {

		// use constructor
		TravelTimeData travelTimeDataObject = new TravelTimeData(PHASE,
				TRAVELTIME, DISTANCEDERIVATIVE, DEPTHDERIVATIVE, RAYDERIVATIVE,
				STATISTICALSPREAD, OBSERVABILITY, TELESEISMICPHASEGROUP,
				AUXILIARYPHASEGROUP, LOCATIONUSEFLAG, ASSOCIATIONWEIGHTFLAG);

		// Successful validation
		boolean rc = travelTimeDataObject.isValid();

		// check return code
		assertEquals("Successful Validation", true, rc);

		// use constructor
		TravelTimeData badTravelTimeDataObject = new TravelTimeData(PHASE,
				TRAVELTIME, null, DEPTHDERIVATIVE, RAYDERIVATIVE,
				STATISTICALSPREAD, null, TELESEISMICPHASEGROUP,
				AUXILIARYPHASEGROUP, LOCATIONUSEFLAG, null);

		rc = badTravelTimeDataObject.isValid();

		// check return code
		assertEquals("Unsuccessful Validation", false, rc);

	}

	public void checkData(TravelTimeData travelTimeDataObject,
			String TestName) {

		// check travelTimeDataObject.phase
		assertEquals(TestName + " Phase Equals", PHASE,
				travelTimeDataObject.getPhase());

		// check travelTimeDataObject.travelTime
		assertEquals(TestName + " Travel Time Equals", TRAVELTIME,
				travelTimeDataObject.getTravelTime(), 0);

		// check travelTimeDataObject.distanceDerivative
		assertEquals(TestName + " Distance Derivative Equals",
				DISTANCEDERIVATIVE,
				travelTimeDataObject.getDistanceDerivative(), 0);

		// check travelTimeDataObject.depthDerivative
		assertEquals(TestName + " Depth Derivative Equals", DEPTHDERIVATIVE,
				travelTimeDataObject.getDepthDerivative(), 0);

		// check travelTimeDataObject.rayDerivative
		assertEquals(TestName + " Ray Derivative Equals", RAYDERIVATIVE,
				travelTimeDataObject.getRayDerivative(), 0);

		// check travelTimeDataObject.statisticalSpread
		assertEquals(TestName + " Statistical Spread Equals", STATISTICALSPREAD,
				travelTimeDataObject.getStatisticalSpread(), 0);

		// check travelTimeDataObject.observability
		assertEquals(TestName + " Observability Equals", OBSERVABILITY,
				travelTimeDataObject.getObservability(), 0);

		// check travelTimeDataObject.teleseismicPhaseGroup
		assertEquals(TestName + " Teleseismic Phase Group Equals",
				TELESEISMICPHASEGROUP,
				travelTimeDataObject.getTeleseismicPhaseGroup(), 0);

		// check travelTimeDataObject.auxiliaryPhaseGroup
		assertEquals(TestName + " Auxiliary Phase Group Equals",
				AUXILIARYPHASEGROUP,
				travelTimeDataObject.getAuxiliaryPhaseGroup(), 0);

		// check OriginObject.locationUseFlag
		assertEquals(TestName + " Location Use Flag Equals", LOCATIONUSEFLAG,
				travelTimeDataObject.getLocationUseFlag());

		// check travelTimeDataObject.associationWeightFlag
		assertEquals(TestName + " Association Use Flag Equals",
				ASSOCIATIONWEIGHTFLAG,
				travelTimeDataObject.getAssociationWeightFlag());

	}

}
