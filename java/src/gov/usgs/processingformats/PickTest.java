package gov.usgs.processingformats;

import static org.junit.Assert.*;

import java.util.Date;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class PickTest {

	public static String PICK_STRING = "{\"ID\":\"12GFH48776857\","
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
	public static String ID = "12GFH48776857";
	public static String STATION = "BOZ";
	public static String CHANNEL = "BHZ";
	public static String NETWORK = "US";
	public static String LOCATION = "00";
	public static double LATITUDE = 45.596970;
	public static double LONGITUDE = -111.629670;
	public static double ELEVATION = 1589.000000;
	public static String AGENCYID = "US";
	public static String AUTHOR = "TestAuthor";
	public static String TYPE = "Unknown";
	public static Date TIME = Utility.getDate("2015-12-28T21:32:24.017Z");
	public static double AFFINITY = 1.2;
	public static double QUALITY = 0.45;
	public static boolean USE = true;
	public static String PICKEDPHASE = "P";
	public static String ASSOCIATEDPHASE = "P";
	public static String LOCATEDPHASE = "P";
	public static double RESIDUAL = 1.05;
	public static double DISTANCE = 2.65;
	public static double AZIMUTH = 21.5;
	public static double WEIGHT = 2.65;
	public static double IMPORTANCE = 3.8;

	/**
	 * Able to write a JSON string
	 */
	@Test
	public void writesJSON() {

		Pick pickObject = new Pick(ID, STATION, CHANNEL, NETWORK, LOCATION, 
				LATITUDE, LONGITUDE, ELEVATION, AGENCYID, AUTHOR, TYPE, TIME, 
				AFFINITY, QUALITY, USE, PICKEDPHASE, ASSOCIATEDPHASE, 
				LOCATEDPHASE, RESIDUAL, DISTANCE, AZIMUTH, WEIGHT, IMPORTANCE);

		// write out to a string
		String jsonString = Utility.toJSONString(pickObject.toJSON());

		// check the data
		try {
			checkData(new Pick(Utility.fromJSONString(jsonString)),
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

			checkData(new Pick(Utility.fromJSONString(PICK_STRING)),
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

		// use constructor
		Pick altPickObject = new Pick(ID,
				new Site(STATION, CHANNEL, NETWORK, LOCATION, LATITUDE, 
					LONGITUDE, ELEVATION),
				new Source(AGENCYID, AUTHOR, TYPE), TIME, AFFINITY, QUALITY,
				USE, PICKEDPHASE, ASSOCIATEDPHASE, LOCATEDPHASE, RESIDUAL,
				DISTANCE, AZIMUTH, WEIGHT, IMPORTANCE);

		// check data values
		checkData(altPickObject, "Alternate Constructor 1");
	}

	/**
	 * Able to run validation function
	 */
	@Test
	public void validate() {

		Pick pickObject = new Pick(ID, STATION, CHANNEL, NETWORK, LOCATION, 
			LATITUDE, LONGITUDE, ELEVATION, AGENCYID, AUTHOR, TYPE, TIME, 
			AFFINITY, QUALITY, USE, PICKEDPHASE, ASSOCIATEDPHASE, 
			LOCATEDPHASE, RESIDUAL, DISTANCE, AZIMUTH, WEIGHT, IMPORTANCE);

		// Successful validation
		boolean rc = pickObject.isValid();

		// check return code
		assertEquals("Successful Validation", true, rc);

		// build bad Pick object
		Pick badPickObject = new Pick("", null, CHANNEL, NETWORK, LOCATION,
				LATITUDE, LONGITUDE, ELEVATION, AGENCYID, AUTHOR, null, null, 
				AFFINITY, QUALITY, USE, PICKEDPHASE, ASSOCIATEDPHASE, 
				LOCATEDPHASE, RESIDUAL, DISTANCE, AZIMUTH, WEIGHT, IMPORTANCE);

		rc = badPickObject.isValid();

		// check return code
		assertEquals("Unsuccessful Validation", false, rc);
	}

	public void checkData(Pick pickObject, String TestName) {

		// check pickObject.ID
		assertEquals(TestName + " ID Equals", ID, pickObject.getID());

		// check pickObject.site.Station
		assertEquals(TestName + " Station Equals", STATION,
				pickObject.getSite().getStation());

		// check pickObject.site.Channel
		assertEquals(TestName + " Channel Equals", CHANNEL,
				pickObject.getSite().getChannel());

		// check pickObject.site.Network
		assertEquals(TestName + " Network Equals", NETWORK,
				pickObject.getSite().getNetwork());

		// check pickObject.site.Location
		assertEquals(TestName + " Location Equals", LOCATION,
				pickObject.getSite().getLocation());

		// check pickObject.site.Latitude
		if (pickObject.getSite().getLatitude() != null) {
			assertEquals(TestName + " Latitude Equals", LATITUDE,
			pickObject.getSite().getLatitude(), 0);
		}

		// check pickObject.site.Longitude
		if (pickObject.getSite().getLongitude() != null) {
			assertEquals(TestName + " Longitude Equals", LONGITUDE,
			pickObject.getSite().getLongitude(), 0);
		}

		// check pickObject.site.Elevation
		if (pickObject.getSite().getElevation() != null) {
			assertEquals(TestName + " Elevation Equals", ELEVATION,
			pickObject.getSite().getElevation(), 0);
		}
		
		// check pickObject.Source.AgencyID
		assertEquals(TestName + " AgencyID Equals", AGENCYID,
				pickObject.getSource().getAgencyID());

		// check pickObject.Source.Author
		assertEquals(TestName + " Author Equals", AUTHOR,
				pickObject.getSource().getAuthor());

		// check pickObject.Source.Author
		assertEquals(TestName + " Type Equals", TYPE,
				pickObject.getSource().getType());

		// check pickObject.Time
		assertEquals(TestName + " Time Equals", TIME, pickObject.getTime());

		// check pickObject.affinity
		assertEquals(TestName + " Affinity Equals", AFFINITY,
				pickObject.getAffinity(), 0);

		// check pickObject.quality
		assertEquals(TestName + " Quality Equals", QUALITY,
				pickObject.getQuality(), 0);

		// check pickObject.use
		assertEquals(TestName + " Use Equals", USE, pickObject.getUse());

		// check pickObject.pickedPhase
		assertEquals(TestName + " Picked Phase Equals", PICKEDPHASE,
				pickObject.getPickedPhase());

		// check pickObject.associatedPhase
		assertEquals(TestName + " Associated Phase Equals", ASSOCIATEDPHASE,
				pickObject.getAssociatedPhase());

		// check pickObject.locatedPhase
		assertEquals(TestName + " Located Phase Equals", LOCATEDPHASE,
				pickObject.getLocatedPhase());

		// check pickObject.residual
		assertEquals(TestName + " Residual Equals", RESIDUAL,
				pickObject.getResidual(), 0);

		// check pickObject.distance
		assertEquals(TestName + " Distance Equals", DISTANCE,
				pickObject.getDistance(), 0);

		// check pickObject.azimuth
		assertEquals(TestName + " Azimuth Equals", AZIMUTH,
				pickObject.getAzimuth(), 0);

		// check pickObject.weight
		assertEquals(TestName + " Weight Equals", WEIGHT,
				pickObject.getWeight(), 0);

		// check pickObject.importance
		assertEquals(TestName + " Importance Equals", IMPORTANCE,
				pickObject.getImportance(), 0);

	}

}
