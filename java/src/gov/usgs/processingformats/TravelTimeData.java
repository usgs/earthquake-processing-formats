package gov.usgs.processingformats;

import java.util.*;

import org.json.simple.JSONObject;

import gov.usgs.processingformats.ProcessingInt;

/**
 * a conversion class used to create, parse, and validate travel time request
 * data
 * 
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class TravelTimeData implements ProcessingInt {

	/**
	 * JSON Keys
	 */
	public static final String PHASE_KEY = "Phase";
	public static final String TRAVELTIME_KEY = "TravelTime";
	public static final String DISTANCEDERIVATIVE_KEY = "DistanceDerivative";
	public static final String DEPTHDERIVATIVE_KEY = "DepthDerivative";
	public static final String RAYDERIVATIVE_KEY = "RayDerivative";
	public static final String STATISTICALSPREAD_KEY = "StatisticalSpread";
	public static final String OBSERVABILITY_KEY = "Observability";
	public static final String TELESEISMICPHASEGROUP_KEY = "TeleseismicPhaseGroup";
	public static final String AUXILIARYPHASEGROUP_KEY = "AuxiliaryPhaseGroup";
	public static final String LOCATIONUSEFLAG_KEY = "LocationUseFlag";
	public static final String ASSOCIATIONWEIGHTFLAG_KEY = "AssociationWeightFlag";

	/**
	 * Required seismic phase code
	 */
	private String phase;

	/**
	 * Required travel time in seconds
	 */
	private Double travelTime;

	/**
	 * Required derivative with respect to distance of the travel time in
	 * seconds/degree.
	 */
	private Double distanceDerivative;

	/**
	 * Required derivative with respect to depth of the travel time in
	 * seconds/kilometer.
	 */
	private Double depthDerivative;

	/**
	 * Required derivative with respect to ray parameter of the travel time in
	 * degrees/second.
	 */
	private Double rayDerivative;

	/**
	 * Required observed travel time scatter in seconds.
	 */
	private Double statisticalSpread;

	/**
	 * Required statistical observability of the seismic phase.
	 */
	private Double observability;

	/**
	 * Required teleseismic phase group identifier.
	 */
	private Long teleseismicPhaseGroup;

	/**
	 * Required auxiliary phase group identifier.
	 */
	private Long auxiliaryPhaseGroup;

	/**
	 * Required flag indicating whether the phase may be used in a location.
	 */
	private Boolean locationUseFlag;

	/**
	 * Required flag indicating whether the phase should be down weighted in
	 * assocation.
	 */
	private Boolean associationWeightFlag;

	/**
	 * The constructor for the TravelTimeData class. Initializes members to null
	 * values.
	 */
	public TravelTimeData() {

		reload(null, null, null, null, null, null, null, null, null, null,
				null);
	}

	/**
	 * Advanced constructor
	 * 
	 * The advanced constructor for the TravelTimeData class. Initializes
	 * members to provided values.
	 * 
	 * @param newPhase
	 *            - A String containing the seismic phase code
	 * @param newTravelTime
	 *            - A Double containing the travel time in seconds
	 * @param newDistanceDerivative
	 *            - A Double containing the derivative with respect to distance
	 *            of the travel time in seconds/degree
	 * @param newDepthDerivative
	 *            - A Double containing the derivative with respect to ray
	 *            parameter of the travel time in degrees/second
	 * @param newRayDerivative
	 *            - A Double containing the derivative with respect to ray
	 *            parameter of the travel time in degrees/second
	 * @param newStatisticalSpread
	 *            - A Double containing the observed travel time scatter in
	 *            seconds
	 * @param newObservability
	 *            - A Double containing the statistical observability of the
	 *            seismic phase
	 * @param newTeleseismicPhaseGroup
	 *            - A Long containing the teleseismic phase group identifier
	 * @param newAuxiliaryPhaseGroup
	 *            - A Long containing the auxiliary phase group identifier
	 * @param newLocationUseFlag
	 *            - A Boolean containing the flag indicating whether the phase
	 *            may be used in a location
	 * @param newAssociationWeightFlag
	 *            - A Boolean containing the flag indicating whether the phase
	 *            should be down weighted in assocation
	 */
	public TravelTimeData(String newPhase, Double newTravelTime,
			Double newDistanceDerivative, Double newDepthDerivative,
			Double newRayDerivative, Double newStatisticalSpread,
			Double newObservability, Long newTeleseismicPhaseGroup,
			Long newAuxiliaryPhaseGroup, Boolean newLocationUseFlag,
			Boolean newAssociationWeightFlag) {

		reload(newPhase, newTravelTime, newDistanceDerivative,
				newDepthDerivative, newRayDerivative, newStatisticalSpread,
				newObservability, newTeleseismicPhaseGroup,
				newAuxiliaryPhaseGroup, newLocationUseFlag,
				newAssociationWeightFlag);
	}

	/**
	 * Constructs the class from a JSONObject, populating members
	 * 
	 * @param newJSONObject
	 *            - A JSONObject.
	 */
	public TravelTimeData(JSONObject newJSONObject) {

		// Required values
		// phase
		if (newJSONObject.containsKey(PHASE_KEY)) {
			phase = newJSONObject.get(PHASE_KEY).toString();
		} else {
			phase = null;
		}

		// travel time
		if (newJSONObject.containsKey(TRAVELTIME_KEY)) {
			travelTime = (double) newJSONObject.get(TRAVELTIME_KEY);
		} else {
			travelTime = null;
		}

		// distance Derivative
		if (newJSONObject.containsKey(DISTANCEDERIVATIVE_KEY)) {
			distanceDerivative = (double) newJSONObject
					.get(DISTANCEDERIVATIVE_KEY);
		} else {
			distanceDerivative = null;
		}

		// depth Derivative
		if (newJSONObject.containsKey(DEPTHDERIVATIVE_KEY)) {
			depthDerivative = (double) newJSONObject.get(DEPTHDERIVATIVE_KEY);
		} else {
			depthDerivative = null;
		}

		// ray Derivative
		if (newJSONObject.containsKey(RAYDERIVATIVE_KEY)) {
			rayDerivative = (double) newJSONObject.get(RAYDERIVATIVE_KEY);
		} else {
			rayDerivative = null;
		}

		// statistical spread
		if (newJSONObject.containsKey(STATISTICALSPREAD_KEY)) {
			statisticalSpread = (double) newJSONObject
					.get(STATISTICALSPREAD_KEY);
		} else {
			statisticalSpread = null;
		}

		// observability
		if (newJSONObject.containsKey(OBSERVABILITY_KEY)) {
			observability = (double) newJSONObject.get(OBSERVABILITY_KEY);
		} else {
			observability = null;
		}

		// teleseismic phase group
		if (newJSONObject.containsKey(TELESEISMICPHASEGROUP_KEY)) {
			teleseismicPhaseGroup = (long) newJSONObject
					.get(TELESEISMICPHASEGROUP_KEY);
		} else {
			teleseismicPhaseGroup = null;
		}

		// auxiliary phase group
		if (newJSONObject.containsKey(AUXILIARYPHASEGROUP_KEY)) {
			auxiliaryPhaseGroup = (long) newJSONObject
					.get(AUXILIARYPHASEGROUP_KEY);
		} else {
			auxiliaryPhaseGroup = null;
		}

		// location use flag
		if (newJSONObject.containsKey(LOCATIONUSEFLAG_KEY)) {
			locationUseFlag = (boolean) newJSONObject.get(LOCATIONUSEFLAG_KEY);
		} else {
			locationUseFlag = null;
		}

		// association weight flag
		if (newJSONObject.containsKey(ASSOCIATIONWEIGHTFLAG_KEY)) {
			associationWeightFlag = (boolean) newJSONObject
					.get(ASSOCIATIONWEIGHTFLAG_KEY);
		} else {
			associationWeightFlag = null;
		}

	}

	/**
	 * Constructs the class from a TravelTimeData object, populating members
	 * (copy constructor)
	 * 
	 * @param sourceObject
	 *            - A TravelTimeData object.
	 */
	public TravelTimeData(TravelTimeData sourceObject) {
		reload(sourceObject.phase, sourceObject.travelTime,
				sourceObject.distanceDerivative, sourceObject.depthDerivative,
				sourceObject.rayDerivative, sourceObject.statisticalSpread,
				sourceObject.observability, sourceObject.teleseismicPhaseGroup,
				sourceObject.auxiliaryPhaseGroup, sourceObject.locationUseFlag,
				sourceObject.associationWeightFlag);
	}

	/**
	 * Reload Function
	 * 
	 * The reload function for the TravelTimeData class. Initializes members to
	 * provided values.
	 * 
	 * @param newPhase
	 *            - A String containing the seismic phase code
	 * @param newTravelTime
	 *            - A Double containing the travel time in seconds
	 * @param newDistanceDerivative
	 *            - A Double containing the derivative with respect to distance
	 *            of the travel time in seconds/degree
	 * @param newDepthDerivative
	 *            - A Double containing the derivative with respect to ray
	 *            parameter of the travel time in degrees/second
	 * @param newRayDerivative
	 *            - A Double containing the derivative with respect to ray
	 *            parameter of the travel time in degrees/second
	 * @param newStatisticalSpread
	 *            - A Double containing the observed travel time scatter in
	 *            seconds
	 * @param newObservability
	 *            - A Double containing the statistical observability of the
	 *            seismic phase
	 * @param newTeleseismicPhaseGroup
	 *            - A Long containing the teleseismic phase group identifier
	 * @param newAuxiliaryPhaseGroup
	 *            - A Long containing the auxiliary phase group identifier
	 * @param newLocationUseFlag
	 *            - A Boolean containing the flag indicating whether the phase
	 *            may be used in a location
	 * @param newAssociationWeightFlag
	 *            - A Boolean containing the flag indicating whether the phase
	 *            should be down weighted in assocation
	 */
	public void reload(String newPhase, Double newTravelTime,
			Double newDistanceDerivative, Double newDepthDerivative,
			Double newRayDerivative, Double newStatisticalSpread,
			Double newObservability, Long newTeleseismicPhaseGroup,
			Long newAuxiliaryPhaseGroup, Boolean newLocationUseFlag,
			Boolean newAssociationWeightFlag) {

		phase = newPhase;
		travelTime = newTravelTime;
		distanceDerivative = newDistanceDerivative;
		depthDerivative = newDepthDerivative;
		rayDerivative = newRayDerivative;
		statisticalSpread = newStatisticalSpread;
		observability = newObservability;
		teleseismicPhaseGroup = newTeleseismicPhaseGroup;
		auxiliaryPhaseGroup = newAuxiliaryPhaseGroup;
		locationUseFlag = newLocationUseFlag;
		associationWeightFlag = newAssociationWeightFlag;

	}

	/**
	 * Converts the contents of the class to a json object
	 * 
	 * @return Returns a JSONObject containing the class contents
	 */
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {

		JSONObject newJSONObject = new JSONObject();

		String jsonPhase = getPhase();
		Double jsonTravelTime = getTravelTime();
		Double jsonDistanceDerivative = getDistanceDerivative();
		Double jsonDepthDerivative = getDepthDerivative();
		Double jsonRayDerivative = getRayDerivative();
		Double jsonStatisticalSpread = getStatisticalSpread();
		Double jsonObservability = getObservability();
		Long jsonTeleseismicPhaseGroup = getTeleseismicPhaseGroup();
		Long jsonAuxiliaryPhaseGroup = getAuxiliaryPhaseGroup();
		Boolean jsonLocationUseFlag = getLocationUseFlag();
		Boolean jsonAssociationWeightFlag = getAssociationWeightFlag();

		// phase
		if (jsonPhase != null) {
			newJSONObject.put(PHASE_KEY, jsonPhase);
		}

		// travel time
		if (jsonTravelTime != null) {
			newJSONObject.put(TRAVELTIME_KEY, jsonTravelTime);
		}

		// distance Derivative
		if (jsonDistanceDerivative != null) {
			newJSONObject.put(DISTANCEDERIVATIVE_KEY, jsonDistanceDerivative);
		}

		// depth Derivative
		if (jsonDepthDerivative != null) {
			newJSONObject.put(DEPTHDERIVATIVE_KEY, jsonDepthDerivative);
		}

		// ray Derivative
		if (jsonRayDerivative != null) {
			newJSONObject.put(RAYDERIVATIVE_KEY, jsonRayDerivative);
		}

		// statistical spread
		if (jsonStatisticalSpread != null) {
			newJSONObject.put(STATISTICALSPREAD_KEY, jsonStatisticalSpread);
		}

		// observability
		if (jsonObservability != null) {
			newJSONObject.put(OBSERVABILITY_KEY, jsonObservability);
		}

		// teleseismic phase group
		if (jsonTeleseismicPhaseGroup != null) {
			newJSONObject.put(TELESEISMICPHASEGROUP_KEY,
					jsonTeleseismicPhaseGroup);
		}

		// auxiliary phase group
		if (jsonAuxiliaryPhaseGroup != null) {
			newJSONObject.put(AUXILIARYPHASEGROUP_KEY, jsonAuxiliaryPhaseGroup);
		}

		// location use flag
		if (jsonLocationUseFlag != null) {
			newJSONObject.put(LOCATIONUSEFLAG_KEY, jsonLocationUseFlag);
		}

		// association use flag
		if (jsonAssociationWeightFlag != null) {
			newJSONObject.put(ASSOCIATIONWEIGHTFLAG_KEY,
					jsonAssociationWeightFlag);
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
		ArrayList<String> errorList = new ArrayList<String>();

		String jsonPhase = getPhase();
		Double jsonTravelTime = getTravelTime();
		Double jsonDistanceDerivative = getDistanceDerivative();
		Double jsonDepthDerivative = getDepthDerivative();
		Double jsonRayDerivative = getRayDerivative();
		Double jsonStatisticalSpread = getStatisticalSpread();
		Double jsonObservability = getObservability();
		Long jsonTeleseismicPhaseGroup = getTeleseismicPhaseGroup();
		Long jsonAuxiliaryPhaseGroup = getAuxiliaryPhaseGroup();
		Boolean jsonLocationUseFlag = getLocationUseFlag();
		Boolean jsonAssociationWeightFlag = getAssociationWeightFlag();

		// phase
		if (jsonPhase == null) {
			// phase not found
			errorList.add("No Phase in TravelTimeData Class.");
		} else if (jsonPhase.isEmpty()) {
			// phase empty
			errorList.add("Empty Phase in TravelTimeData Class.");
		}

		// travel time
		if (jsonTravelTime == null) {
			// travel time not found
			errorList.add("No Travel Time in TravelTimeData Class.");
		}

		// distance derivative
		if (jsonDistanceDerivative == null) {
			// distance derivative not found
			errorList.add("No Distance Derivative in TravelTimeData Class.");
		}

		// depth derivative
		if (jsonDepthDerivative == null) {
			// depth derivative not found
			errorList.add("No Depth Derivative in TravelTimeData Class.");
		}

		// ray derivative
		if (jsonRayDerivative == null) {
			// ray derivative not found
			errorList.add("No Ray Derivative in TravelTimeData Class.");
		}

		// statistical spread
		if (jsonStatisticalSpread == null) {
			// statistical spread not found
			errorList.add("No Statistical Spread in TravelTimeData Class.");
		}

		// observability
		if (jsonObservability == null) {
			// observability not found
			errorList.add("No Observability in TravelTimeData Class.");
		}

		// teleseismic phase group
		if (jsonTeleseismicPhaseGroup == null) {
			// teleseismic phase group not found
			errorList
					.add("No Teleseismic Phase Group in TravelTimeData Class.");
		}

		// auxiliary phase group
		if (jsonAuxiliaryPhaseGroup == null) {
			// auxiliary phase group not found
			errorList.add("No Auxiliary Phase Group in TravelTimeData Class.");
		}

		// location use flag
		if (jsonLocationUseFlag == null) {
			// location use flag not found
			errorList.add("No Location Use Flag in TravelTimeData Class.");
		}

		// association weight flag
		if (jsonAssociationWeightFlag == null) {
			// association weight flag not found
			errorList
					.add("No Association Weight Flag in TravelTimeData Class.");
		}

		return (errorList);
	}

	/**
	 * @return the phase
	 */
	public String getPhase() {
		return phase;
	}

	/**
	 * @param phase
	 *            the phase to set
	 */
	public void setPhase(String phase) {
		this.phase = phase;
	}

	/**
	 * @return the travelTime
	 */
	public Double getTravelTime() {
		return travelTime;
	}

	/**
	 * @param travelTime
	 *            the travelTime to set
	 */
	public void setTravelTime(Double travelTime) {
		this.travelTime = travelTime;
	}

	/**
	 * @return the distanceDerivative
	 */
	public Double getDistanceDerivative() {
		return distanceDerivative;
	}

	/**
	 * @param distanceDerivative
	 *            the distanceDerivative to set
	 */
	public void setDistanceDerivative(Double distanceDerivative) {
		this.distanceDerivative = distanceDerivative;
	}

	/**
	 * @return the depthDerivative
	 */
	public Double getDepthDerivative() {
		return depthDerivative;
	}

	/**
	 * @param depthDerivative
	 *            the depthDerivative to set
	 */
	public void setDepthDerivative(Double depthDerivative) {
		this.depthDerivative = depthDerivative;
	}

	/**
	 * @return the rayDerivative
	 */
	public Double getRayDerivative() {
		return rayDerivative;
	}

	/**
	 * @param rayDerivative
	 *            the rayDerivative to set
	 */
	public void setRayDerivative(Double rayDerivative) {
		this.rayDerivative = rayDerivative;
	}

	/**
	 * @return the statisticalSpread
	 */
	public Double getStatisticalSpread() {
		return statisticalSpread;
	}

	/**
	 * @param statisticalSpread
	 *            the statisticalSpread to set
	 */
	public void setStatisticalSpread(Double statisticalSpread) {
		this.statisticalSpread = statisticalSpread;
	}

	/**
	 * @return the observability
	 */
	public Double getObservability() {
		return observability;
	}

	/**
	 * @param observability
	 *            the observability to set
	 */
	public void setObservability(Double observability) {
		this.observability = observability;
	}

	/**
	 * @return the teleseismicPhaseGroup
	 */
	public Long getTeleseismicPhaseGroup() {
		return teleseismicPhaseGroup;
	}

	/**
	 * @param teleseismicPhaseGroup
	 *            the teleseismicPhaseGroup to set
	 */
	public void setTeleseismicPhaseGroup(Long teleseismicPhaseGroup) {
		this.teleseismicPhaseGroup = teleseismicPhaseGroup;
	}

	/**
	 * @return the auxiliaryPhaseGroup
	 */
	public Long getAuxiliaryPhaseGroup() {
		return auxiliaryPhaseGroup;
	}

	/**
	 * @param auxiliaryPhaseGroup
	 *            the auxiliaryPhaseGroup to set
	 */
	public void setAuxiliaryPhaseGroup(Long auxiliaryPhaseGroup) {
		this.auxiliaryPhaseGroup = auxiliaryPhaseGroup;
	}

	/**
	 * @return the locationUseFlag
	 */
	public Boolean getLocationUseFlag() {
		return locationUseFlag;
	}

	/**
	 * @param locationUseFlag
	 *            the locationUseFlag to set
	 */
	public void setLocationUseFlag(Boolean locationUseFlag) {
		this.locationUseFlag = locationUseFlag;
	}

	/**
	 * @return the associationWeightFlag
	 */
	public Boolean getAssociationWeightFlag() {
		return associationWeightFlag;
	}

	/**
	 * @param associationWeightFlag
	 *            the associationWeightFlag to set
	 */
	public void setAssociationWeightFlag(Boolean associationWeightFlag) {
		this.associationWeightFlag = associationWeightFlag;
	}

}
