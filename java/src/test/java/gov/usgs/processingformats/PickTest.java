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
		assertEquals(TestName + " ID Equals", ID, pickObject.ID);

		// check pickObject.site.Station
		assertEquals(TestName + " Station Equals", STATION,
				pickObject.Site.Station);

		// check pickObject.site.Channel
		assertEquals(TestName + " Channel Equals", CHANNEL,
				pickObject.Site.Channel);

		// check pickObject.site.Network
		assertEquals(TestName + " Network Equals", NETWORK,
				pickObject.Site.Network);

		// check pickObject.site.Location
		assertEquals(TestName + " Location Equals", LOCATION,
				pickObject.Site.Location);

		// check pickObject.site.Latitude
		if (pickObject.Site.Latitude != null) {
			assertEquals(TestName + " Latitude Equals", LATITUDE,
			pickObject.Site.Latitude, 0);
		}

		// check pickObject.site.Longitude
		if (pickObject.Site.Longitude != null) {
			assertEquals(TestName + " Longitude Equals", LONGITUDE,
			pickObject.Site.Longitude, 0);
		}

		// check pickObject.site.Elevation
		if (pickObject.Site.Elevation != null) {
			assertEquals(TestName + " Elevation Equals", ELEVATION,
			pickObject.Site.Elevation, 0);
		}
		
		// check pickObject.Source.AgencyID
		assertEquals(TestName + " AgencyID Equals", AGENCYID,
				pickObject.Source.AgencyID);

		// check pickObject.Source.Author
		assertEquals(TestName + " Author Equals", AUTHOR,
				pickObject.Source.Author);

		// check pickObject.Source.Author
		assertEquals(TestName + " Type Equals", TYPE,
				pickObject.Source.Type);

		// check pickObject.Time
		assertEquals(TestName + " Time Equals", TIME, pickObject.Time);

		// check pickObject.affinity
		assertEquals(TestName + " Affinity Equals", AFFINITY,
				pickObject.Affinity, 0);

		// check pickObject.quality
		assertEquals(TestName + " Quality Equals", QUALITY,
				pickObject.Quality, 0);

		// check pickObject.use
		assertEquals(TestName + " Use Equals", USE, pickObject.Use);

		// check pickObject.pickedPhase
		assertEquals(TestName + " Picked Phase Equals", PICKEDPHASE,
				pickObject.PickedPhase);

		// check pickObject.associatedPhase
		assertEquals(TestName + " Associated Phase Equals", ASSOCIATEDPHASE,
				pickObject.AssociatedPhase);

		// check pickObject.locatedPhase
		assertEquals(TestName + " Located Phase Equals", LOCATEDPHASE,
				pickObject.LocatedPhase);

		// check pickObject.residual
		assertEquals(TestName + " Residual Equals", RESIDUAL,
				pickObject.Residual, 0);

		// check pickObject.distance
		assertEquals(TestName + " Distance Equals", DISTANCE,
				pickObject.Distance, 0);

		// check pickObject.azimuth
		assertEquals(TestName + " Azimuth Equals", AZIMUTH,
				pickObject.Azimuth, 0);

		// check pickObject.weight
		assertEquals(TestName + " Weight Equals", WEIGHT,
				pickObject.Weight, 0);

		// check pickObject.importance
		assertEquals(TestName + " Importance Equals", IMPORTANCE,
				pickObject.Importance, 0);

	}

}
