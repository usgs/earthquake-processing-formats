package gov.usgs.processingformats;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * a conversion class used to create, parse, and validate pick processing data
 *
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class Pick implements ProcessingInt {

	/**
	 * JSON Keys
	 */
	public static final String ID_KEY = "ID";
	public static final String SITE_KEY = "Site";
	public static final String SOURCE_KEY = "Source";
	public static final String TIME_KEY = "Time";
	public static final String AFFINITY_KEY = "Affinity";
	public static final String QUALITY_KEY = "Quality";
	public static final String USE_KEY = "Use";
	public static final String PICKED_PHASE_KEY = "PickedPhase";
	public static final String ASSOCIATED_PHASE_KEY = "AssociatedPhase";
	public static final String LOCATED_PHASE_KEY = "LocatedPhase";
	public static final String RESIDUAL_KEY = "Residual";
	public static final String DISTANCE_KEY = "Distance";
	public static final String AZIMUTH_KEY = "Azimuth";
	public static final String WEIGHT_KEY = "Weight";
	public static final String IMPORTANCE_KEY = "Importance";

	/**
	 * Required unique identifier for this Pick
	 */
	private String id;

	/**
	 * Required site.
	 */
	private Site site;

	/**
	 * Required source.
	 */
	private Source source;

	/**
	 * Required time for this Pick
	 */
	private Date time;

	/**
	 * Required double containing the affinity
	 */
	private Double affinity;

	/**
	 * Required double containing the quality
	 */
	private Double quality;

	/**
	 * Required boolean containing the use flag
	 */
	private Boolean use;

	/**
	 * Required String containing the picked phase
	 */
	private String pickedPhase;

	/**
	 * Required String containing the associated phase
	 */
	private String associatedPhase;

	/**
	 * Optional (output) String containing the located phase
	 */
	private String locatedPhase;

	/**
	 * Optional (output) double containing the residual
	 */
	private Double residual;

	/**
	 * Optional (output) double containing the distance
	 */
	private Double distance;

	/**
	 * Optional (output) double containing the azimuth
	 */
	private Double azimuth;

	/**
	 * Optional (output) double containing the weight
	 */
	private Double weight;

	/**
	 * Optional (output) double containing the importance
	 */
	private Double importance;

	/**
	 * The constructor for the Pick class. Initializes members to null values.
	 */
	public Pick() {

		id = null;
		site = null;
		source = null;
		time = null;
		affinity = null;
		quality = null;
		use = null;
		pickedPhase = null;
		associatedPhase = null;
		locatedPhase = null;
		residual = null;
		distance = null;
		azimuth = null;
		weight = null;
		importance = null;
	}

	/**
	 * The advanced constructor for the Pick class. Initializes members to
	 * provided values.
	 *
	 * @param newID
	 *            - A String containing the id to use
	 * @param newStation
	 *            - A String containing the station to use
	 * @param newChannel
	 *            - A String containing the channel to use
	 * @param newNetwork
	 *            - A String containing the network to use
	 * @param newLocation
	 *            - A String containing the location to use
	 * @param newLatitude
	 *            - A Double containing the latitude to use
	 * @param newLongitude
	 *            - A Double containing the longitude to use
	 * @param newElevation
	 *            - A Double containing the elevation to use
	 * @param newAgencyID
	 *            - A String containing the agencyid to use
	 * @param newAuthor
	 *            - A String containing the author to use
	 * @param newType
	 *            - A String containing the type to use
	 * @param newTime
	 *            - A Date containing the time to use
	 * @param newAffinity
	 *            - A Double containing the affinity to use
	 * @param newQuality
	 *            - A Double containing the quality to use
	 * @param newUse
	 *            - A Boolean containing the use flag to use
	 * @param newPickedPhase
	 *            - A String containing the picked phase to use
	 * @param newAssociatedPhase
	 *            - A String containing the associated phase to use
	 * @param newLocatedPhase
	 *            - A String containing the located phase to use, empty string
	 *            to omit
	 * @param newResidual
	 *            - A Double containing the residual to use, null to omit
	 * @param newDistance
	 *            - A Double containing the distance to use, null to omit
	 * @param newAzimuth
	 *            - A Double containing the azimuth to use, null to omit
	 * @param newWeight
	 *            - A Double containing the weight to use, null to omit
	 * @param newImportance
	 *            - A Double containing the importance to use, null to omit
	 */
	public Pick(String newID, String newStation, String newChannel,
			String newNetwork, String newLocation, Double newLatitude, 
			Double newLongitude, Double newElevation, String newAgencyID,
			String newAuthor, String newType, Date newTime, Double newAffinity,
			Double newQuality, Boolean newUse, String newPickedPhase,
			String newAssociatedPhase, String newLocatedPhase,
			Double newResidual, Double newDistance, Double newAzimuth,
			Double newWeight, Double newImportance) {

		reload(newID, new Site(newStation, newChannel, newNetwork, newLocation, 
				newLatitude, newLongitude, newElevation),
				new Source(newAgencyID, newAuthor, newType), newTime,
				newAffinity, newQuality, newUse, newPickedPhase,
				newAssociatedPhase, newLocatedPhase, newResidual, newDistance,
				newAzimuth, newWeight, newImportance);
	}

	/**
	 * The alternate advanced constructor for the Pick class. Initializes
	 * members to provided values.
	 *
	 * @param newID
	 *            - A String containing the id to use
	 * @param newSite
	 *            - A Site containing the site to use
	 * @param newSource
	 *            - A Source containing the source to use
	 * @param newTime
	 *            - A Date containing the time to use
	 * @param newAffinity
	 *            - A Double containing the affinity to use
	 * @param newQuality
	 *            - A Double containing the quality to use
	 * @param newUse
	 *            - A Boolean containing the use flag to use
	 * @param newPickedPhase
	 *            - A String containing the picked phase to use
	 * @param newAssociatedPhase
	 *            - A String containing the associated phase to use
	 * @param newLocatedPhase
	 *            - A String containing the located phase to use, empty string
	 *            to omit
	 * @param newResidual
	 *            - A Double containing the residual to use, null to omit
	 * @param newDistance
	 *            - A Double containing the distance to use, null to omit
	 * @param newAzimuth
	 *            - A Double containing the azimuth to use, null to omit
	 * @param newWeight
	 *            - A Double containing the weight to use, null to omit
	 * @param newImportance
	 *            - A Double containing the importance to use, null to omit
	 */
	public Pick(String newID, Site newSite, Source newSource, Date newTime,
			Double newAffinity, Double newQuality, Boolean newUse,
			String newPickedPhase, String newAssociatedPhase,
			String newLocatedPhase, Double newResidual, Double newDistance,
			Double newAzimuth, Double newWeight, Double newImportance) {

		reload(newID, newSite, newSource, newTime, newAffinity, newQuality,
				newUse, newPickedPhase, newAssociatedPhase, newLocatedPhase,
				newResidual, newDistance, newAzimuth, newWeight, newImportance);
	}

	/**
	 * Reload Function
	 *
	 * The reload function for the error ellipse class. Initializes members to
	 * provided values.
	 *
	 * @param newID
	 *            - A String containing the id to use
	 * @param newSite
	 *            - A Site containing the site to use
	 * @param newSource
	 *            - A Source containing the source to use
	 * @param newTime
	 *            - A Date containing the time to use
	 * @param newAffinity
	 *            - A Double containing the affinity to use
	 * @param newQuality
	 *            - A Double containing the quality to use
	 * @param newUse
	 *            - A Boolean containing the use flag to use
	 * @param newPickedPhase
	 *            - A String containing the picked phase to use
	 * @param newAssociatedPhase
	 *            - A String containing the associated phase to use
	 * @param newLocatedPhase
	 *            - A String containing the located phase to use, empty string
	 *            to omit
	 * @param newResidual
	 *            - A Double containing the residual to use, null to omit
	 * @param newDistance
	 *            - A Double containing the distance to use, null to omit
	 * @param newAzimuth
	 *            - A Double containing the azimuth to use, null to omit
	 * @param newWeight
	 *            - A Double containing the weight to use, null to omit
	 * @param newImportance
	 *            - A Double containing the importance to use, null to omit
	 */
	public void reload(String newID, Site newSite, Source newSource,
			Date newTime, Double newAffinity, Double newQuality, Boolean newUse,
			String newPickedPhase, String newAssociatedPhase,
			String newLocatedPhase, Double newResidual, Double newDistance,
			Double newAzimuth, Double newWeight, Double newImportance) {

		id = newID;
		site = newSite;
		time = newTime;
		source = newSource;
		affinity = newAffinity;
		quality = newQuality;
		use = newUse;
		pickedPhase = newPickedPhase;
		associatedPhase = newAssociatedPhase;
		locatedPhase = newLocatedPhase;
		residual = newResidual;
		distance = newDistance;
		azimuth = newAzimuth;
		weight = newWeight;
		importance = newImportance;
	}

	/**
	 * Constructs the class from a JSONObject, populating members
	 *
	 * @param newJSONObject
	 *            - A JSONObject.
	 */
	public Pick(JSONObject newJSONObject) {

		// Required values
		// id
		if (newJSONObject.containsKey(ID_KEY)) {
			id = newJSONObject.get(ID_KEY).toString();
		} else {
			id = null;
		}

		// site
		if (newJSONObject.containsKey(SITE_KEY)) {
			site = new Site((JSONObject) newJSONObject.get(SITE_KEY));
		} else {
			site = null;
		}

		// source
		if (newJSONObject.containsKey(SOURCE_KEY)) {
			source = new Source((JSONObject) newJSONObject.get(SOURCE_KEY));
		} else {
			source = null;
		}

		// time
		if (newJSONObject.containsKey(TIME_KEY)) {
			time = Utility.getDate(newJSONObject.get(TIME_KEY).toString());
		} else {
			time = null;
		}

		// affinity
		if (newJSONObject.containsKey(AFFINITY_KEY)) {
			affinity = Double
					.valueOf(newJSONObject.get(AFFINITY_KEY).toString());
		} else {
			affinity = null;
		}

		// quality
		if (newJSONObject.containsKey(QUALITY_KEY)) {
			quality = Double.valueOf(newJSONObject.get(QUALITY_KEY).toString());
		} else {
			quality = null;
		}

		// use
		if (newJSONObject.containsKey(USE_KEY)) {
			use = Boolean.valueOf(newJSONObject.get(USE_KEY).toString());
		} else {
			use = null;
		}

		// pickedPhase
		if (newJSONObject.containsKey(PICKED_PHASE_KEY)) {
			pickedPhase = newJSONObject.get(PICKED_PHASE_KEY).toString();
		} else {
			pickedPhase = null;
		}

		// associatedPhase
		if (newJSONObject.containsKey(ASSOCIATED_PHASE_KEY)) {
			associatedPhase = newJSONObject.get(ASSOCIATED_PHASE_KEY)
					.toString();
		} else {
			associatedPhase = null;
		}

		// Optional (output) values
		// locatedPhase
		if (newJSONObject.containsKey(LOCATED_PHASE_KEY)) {
			locatedPhase = newJSONObject.get(LOCATED_PHASE_KEY).toString();
		} else {
			locatedPhase = null;
		}

		// residual
		if (newJSONObject.containsKey(RESIDUAL_KEY)) {
			residual = Double
					.valueOf(newJSONObject.get(RESIDUAL_KEY).toString());
		} else {
			residual = null;
		}

		// distance
		if (newJSONObject.containsKey(DISTANCE_KEY)) {
			distance = Double
					.valueOf(newJSONObject.get(DISTANCE_KEY).toString());
		} else {
			distance = null;
		}

		// azimuth
		if (newJSONObject.containsKey(AZIMUTH_KEY)) {
			azimuth = Double.valueOf(newJSONObject.get(AZIMUTH_KEY).toString());
		} else {
			azimuth = null;
		}

		// weight
		if (newJSONObject.containsKey(WEIGHT_KEY)) {
			weight = Double.valueOf(newJSONObject.get(WEIGHT_KEY).toString());
		} else {
			weight = null;
		}

		// importance
		if (newJSONObject.containsKey(IMPORTANCE_KEY)) {
			importance = Double
					.valueOf(newJSONObject.get(IMPORTANCE_KEY).toString());
		} else {
			importance = null;
		}
	}

	/**
	 * Converts the contents of the class to a json object Overridden from
	 * ProcessingBase.
	 *
	 * @return Returns a JSONObject containing the class contents
	 */
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {

		JSONObject newJSONObject = new JSONObject();

		String jsonID = getID();
		Site jsonSite = getSite();
		Source jsonSource = getSource();
		Date jsonTime = getTime();
		Double jsonAffinity = getAffinity();
		Double jsonQuality = getQuality();
		Boolean jsonUse = getUse();
		String jsonPickedPhase = getPickedPhase();
		String jsonAssociatedPhase = getAssociatedPhase();
		String jsonLocatedPhase = getLocatedPhase();
		Double jsonResidual = getResidual();
		Double jsonDistance = getDistance();
		Double jsonAzimuth = getAzimuth();
		Double jsonWeight = getWeight();
		Double jsonImportance = getImportance();

		// Required values
		// id
		if (jsonID != null) {
			newJSONObject.put(ID_KEY, jsonID);
		}

		// site
		if (jsonSite != null) {
			newJSONObject.put(SITE_KEY, jsonSite.toJSON());
		}

		// source
		if (jsonSource != null) {
			newJSONObject.put(SOURCE_KEY, jsonSource.toJSON());
		}

		// time
		if (jsonTime != null) {
			newJSONObject.put(TIME_KEY, Utility.formatDate(jsonTime));
		}

		// affinity
		if (jsonAffinity != null) {
			newJSONObject.put(AFFINITY_KEY, jsonAffinity);
		}

		// quality
		if (jsonQuality != null) {
			newJSONObject.put(QUALITY_KEY, jsonQuality);
		}

		// use
		if (jsonUse != null) {
			newJSONObject.put(USE_KEY, jsonUse);
		}

		// pickedPhase
		if (jsonPickedPhase != null) {
			newJSONObject.put(PICKED_PHASE_KEY, jsonPickedPhase);
		}

		// asociatedPhase
		if (jsonAssociatedPhase != null) {
			newJSONObject.put(ASSOCIATED_PHASE_KEY, jsonAssociatedPhase);
		}

		// Optional (output) values
		// locatedPhase
		if (jsonLocatedPhase != null) {
			newJSONObject.put(LOCATED_PHASE_KEY, jsonLocatedPhase);
		}

		// residual
		if (jsonResidual != null) {
			newJSONObject.put(RESIDUAL_KEY, jsonResidual);
		}

		// distance
		if (jsonDistance != null) {
			newJSONObject.put(DISTANCE_KEY, jsonDistance);
		}

		// azimuth
		if (jsonAzimuth != null) {
			newJSONObject.put(AZIMUTH_KEY, jsonAzimuth);
		}

		// weight
		if (jsonWeight != null) {
			newJSONObject.put(WEIGHT_KEY, jsonWeight);
		}

		// importance
		if (jsonImportance != null) {
			newJSONObject.put(IMPORTANCE_KEY, jsonImportance);
		}

		return (newJSONObject);

	}

	/**
	 * Validates the class.
	 *
	 * @return Returns true if successful
	 */
	public boolean isValid() {
		if (getErrors() == null) {
			return (true);
		} else if (getErrors().size() == 0) {
			return (true);
		} else {
			return (false);
		}
	}

	/**
	 * Gets any validation errors in the class.
	 *
	 * @return Returns a List&lt;String&gt; of any errors found
	 */
	public ArrayList<String> getErrors() {

		String jsonID = getID();
		Site jsonSite = getSite();
		Source jsonSource = getSource();
		Date jsonTime = getTime();
		Double jsonAffinity = getAffinity();
		Double jsonQuality = getQuality();
		Boolean jsonUse = getUse();
		String jsonPickedPhase = getPickedPhase();
		String jsonAssociatedPhase = getAssociatedPhase();
		// String jsonLocatedPhase = getLocatedPhase();
		// Double jsonResidual = getResidual();
		// Double jsonDistance = getDistance();
		// Double jsonAzimuth = getAzimuth();
		// Double jsonWeight = getWeight();
		// Double jsonImportance = getImportance();

		ArrayList<String> errorList = new ArrayList<String>();

		// Required Keys
		// id
		if (jsonID == null) {
			// id not found
			errorList.add("No ID in Pick Class.");
		} else if (jsonID.isEmpty()) {
			// id empty
			errorList.add("Empty ID in Pick Class.");
		}

		// site
		if (jsonSite == null) {
			// site not found
			errorList.add("No Site in Pick Class.");
		} else if (!jsonSite.isValid()) {
			// site invalid
			errorList.add("Invalid Site in Pick Class.");
		}

		// source
		if (jsonSource == null) {
			// source not found
			errorList.add("No Source in Pick Class.");
		} else if (!jsonSource.isValid()) {
			// source invalid
			errorList.add("Invalid Source in Pick Class.");
		}

		// time
		if (jsonTime == null) {
			// time not found
			errorList.add("No Time in Pick Class.");
		}

		// affinity
		if (jsonAffinity == null) {
			// affinity not found
			errorList.add("No Affinity in Pick Class.");
		}

		// quality
		if (jsonQuality == null) {
			// quality not found
			errorList.add("No Quality in Pick Class.");
		}

		// use
		if (jsonUse == null) {
			// use not found
			errorList.add("No Use in Pick Class.");
		}

		// pickedPhase
		if (jsonPickedPhase == null) {
			// pickedPhase not found
			errorList.add("No Picked Phase in Pick Class.");
		} else if (jsonPickedPhase.isEmpty()) {
			// pickedPhase empty
			errorList.add("Empty Picked Phase in Pick Class.");
		}

		// associatedPhase
		if (jsonAssociatedPhase == null) {
			// associatedPhase not found
			errorList.add("No Associated Phase in Pick Class.");
		} else if (jsonAssociatedPhase.isEmpty()) {
			// associatedPhase empty
			errorList.add("Empty Associated Phase in Pick Class.");
		}

		// Optional (Output) Keys
		// Currently no validation criteria for optional (output) values

		// success
		return (errorList);
	}

	/**
	 * @return the id
	 */
	public String getID() {
		return id;
	}

	/**
	 * @return the site
	 */
	public Site getSite() {
		return site;
	}

	/**
	 * @return the source
	 */
	public Source getSource() {
		return source;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @return the affinity
	 */
	public Double getAffinity() {
		return affinity;
	}

	/**
	 * @return the quality
	 */
	public Double getQuality() {
		return quality;
	}

	/**
	 * @return the use
	 */
	public Boolean getUse() {
		return use;
	}

	/**
	 * @return the pickedPhase
	 */
	public String getPickedPhase() {
		return pickedPhase;
	}

	/**
	 * @return the associatedPhase
	 */
	public String getAssociatedPhase() {
		return associatedPhase;
	}

	/**
	 * @return the locatedPhase
	 */
	public String getLocatedPhase() {
		return locatedPhase;
	}

	/**
	 * @return the residual
	 */
	public Double getResidual() {
		return residual;
	}

	/**
	 * @return the distance
	 */
	public Double getDistance() {
		return distance;
	}

	/**
	 * @return the azimuth
	 */
	public Double getAzimuth() {
		return azimuth;
	}

	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * @return the importance
	 */
	public Double getImportance() {
		return importance;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param site
	 *            the site to set
	 */
	public void setSite(Site site) {
		this.site = site;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(Source source) {
		this.source = source;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @param affinity
	 *            the affinity to set
	 */
	public void setAffinity(Double affinity) {
		this.affinity = affinity;
	}

	/**
	 * @param quality
	 *            the quality to set
	 */
	public void setQuality(Double quality) {
		this.quality = quality;
	}

	/**
	 * @param use
	 *            the use to set
	 */
	public void setUse(Boolean use) {
		this.use = use;
	}

	/**
	 * @param pickedPhase
	 *            the pickedPhase to set
	 */
	public void setPickedPhase(String pickedPhase) {
		this.pickedPhase = pickedPhase;
	}

	/**
	 * @param associatedPhase
	 *            the associatedPhase to set
	 */
	public void setAssociatedPhase(String associatedPhase) {
		this.associatedPhase = associatedPhase;
	}

	/**
	 * @param locatedPhase
	 *            the locatedPhase to set
	 */
	public void setLocatedPhase(String locatedPhase) {
		this.locatedPhase = locatedPhase;
	}

	/**
	 * @param residual
	 *            the residual to set
	 */
	public void setResidual(Double residual) {
		this.residual = residual;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(Double distance) {
		this.distance = distance;
	}

	/**
	 * @param azimuth
	 *            the azimuth to set
	 */
	public void setAzimuth(Double azimuth) {
		this.azimuth = azimuth;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/**
	 * @param importance
	 *            the importance to set
	 */
	public void setImportance(Double importance) {
		this.importance = importance;
	}
}
