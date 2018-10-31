#include <Pick.h>

#include <string>
#include <limits>
#include <vector>

// JSON Keys
#define ID_KEY "ID"
#define SITE_KEY "Site"
#define SOURCE_KEY "Source"
#define TIME_KEY "Time"
#define AFFINITY_KEY "Affinity"
#define QUALITY_KEY "Quality"
#define USE_KEY "Use"
#define PICKED_PHASE_KEY "PickedPhase"
#define ASSOCIATED_PHASE_KEY "AssociatedPhase"
#define LOCATED_PHASE_KEY "LocatedPhase"
#define RESIDUAL_KEY "Residual"
#define DISTANCE_KEY "Distance"
#define AZIMUTH_KEY "Azimuth"
#define WEIGHT_KEY "Weight"
#define IMPORTANCE_KEY "Importance"

namespace processingformats {
Pick::Pick() {
	id = "";
	site = processingformats::Site();
	source = processingformats::Source();
	time = std::numeric_limits<double>::quiet_NaN();
	affinity = std::numeric_limits<double>::quiet_NaN();
	quality = std::numeric_limits<double>::quiet_NaN();
	use = false;
	pickedPhase = "";
	associatedPhase = "";
	locatedPhase = "";
	residual = std::numeric_limits<double>::quiet_NaN();
	distance = std::numeric_limits<double>::quiet_NaN();
	azimuth = std::numeric_limits<double>::quiet_NaN();
	weight = std::numeric_limits<double>::quiet_NaN();
	importance = std::numeric_limits<double>::quiet_NaN();
}

Pick::Pick(std::string newID, std::string newStation, std::string newChannel,
			std::string newNetwork, std::string newLocation,
			std::string newAgencyID, std::string newAuthor, std::string newType,
			double newTime, double newAffinity, double newQuality, bool newUse,
			std::string newPickedPhase, std::string newAssociatedPhase,
			std::string newLocatedPhase, double newResidual, double newDistance,
			double newAzimuth, double newWeight, double newImportance) {
	id = newID;
	site = processingformats::Site(newStation, newChannel, newNetwork,
									newLocation);
	source = processingformats::Source(newAgencyID, newAuthor, newType);
	time = newTime;
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

Pick::Pick(std::string newID, Site newSite, Source newSource, double newTime,
			double newAffinity, double newQuality, bool newUse,
			std::string newPickedPhase, std::string newAssociatedPhase,
			std::string newLocatedPhase, double newResidual, double newDistance,
			double newAzimuth, double newWeight, double newImportance) {
	id = newID;
	site = newSite;
	source = newSource;
	time = newTime;
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

Pick::Pick(rapidjson::Value &json) {
	// required values
	// id
	if ((json.HasMember(ID_KEY) == true) && (json[ID_KEY].IsString() == true)) {
		id = std::string(json[ID_KEY].GetString(),
							json[ID_KEY].GetStringLength());
	} else {
		id = "";
	}

	// site
	if ((json.HasMember(SITE_KEY) == true)
			&& (json[SITE_KEY].IsObject() == true)) {
		rapidjson::Value & sitevalue = json[SITE_KEY];
		site = processingformats::Site(sitevalue);
	} else {
		site = processingformats::Site();
	}

	// source
	if ((json.HasMember(SOURCE_KEY) == true)
			&& (json[SOURCE_KEY].IsObject() == true)) {
		rapidjson::Value & sourcevalue = json[SOURCE_KEY];
		source = processingformats::Source(sourcevalue);
	} else {
		source = processingformats::Source();
	}

	// time
	if ((json.HasMember(TIME_KEY) == true)
			&& (json[TIME_KEY].IsString() == true)) {
		time = processingformats::ConvertISO8601ToEpochTime(
				std::string(json[TIME_KEY].GetString(),
							json[TIME_KEY].GetStringLength()));
	} else {
		time = std::numeric_limits<double>::quiet_NaN();
	}

	// affinity
	if ((json.HasMember(AFFINITY_KEY) == true)
			&& (json[AFFINITY_KEY].IsNumber() == true)
			&& (json[AFFINITY_KEY].IsDouble() == true)) {
		affinity = json[AFFINITY_KEY].GetDouble();
	} else {
		affinity = std::numeric_limits<double>::quiet_NaN();
	}

	// quality
	if ((json.HasMember(QUALITY_KEY) == true)
			&& (json[QUALITY_KEY].IsNumber() == true)
			&& (json[QUALITY_KEY].IsDouble() == true)) {
		quality = json[QUALITY_KEY].GetDouble();
	} else {
		quality = std::numeric_limits<double>::quiet_NaN();
	}

	// use
	if ((json.HasMember(USE_KEY) == true) && (json[USE_KEY].IsBool() == true)) {
		use = json[USE_KEY].GetBool();
	} else {
		use = false;
	}

	// picked phase
	if ((json.HasMember(PICKED_PHASE_KEY) == true)
			&& (json[PICKED_PHASE_KEY].IsString() == true)) {
		pickedPhase = std::string(json[PICKED_PHASE_KEY].GetString(),
									json[PICKED_PHASE_KEY].GetStringLength());
	} else {
		pickedPhase = "";
	}

	// associated  phase
	if ((json.HasMember(ASSOCIATED_PHASE_KEY) == true)
			&& (json[ASSOCIATED_PHASE_KEY].IsString() == true)) {
		associatedPhase = std::string(
				json[ASSOCIATED_PHASE_KEY].GetString(),
				json[ASSOCIATED_PHASE_KEY].GetStringLength());
	} else {
		associatedPhase = "";
	}

	// optional (output) values
	// located phase
	if ((json.HasMember(LOCATED_PHASE_KEY) == true)
			&& (json[LOCATED_PHASE_KEY].IsString() == true)) {
		locatedPhase = std::string(json[LOCATED_PHASE_KEY].GetString(),
									json[LOCATED_PHASE_KEY].GetStringLength());
	} else {
		locatedPhase = "";
	}

	// residual
	if ((json.HasMember(RESIDUAL_KEY) == true)
			&& (json[RESIDUAL_KEY].IsNumber() == true)
			&& (json[RESIDUAL_KEY].IsDouble() == true)) {
		residual = json[RESIDUAL_KEY].GetDouble();
	} else {
		residual = std::numeric_limits<double>::quiet_NaN();
	}

	// distance
	if ((json.HasMember(DISTANCE_KEY) == true)
			&& (json[DISTANCE_KEY].IsNumber() == true)
			&& (json[DISTANCE_KEY].IsDouble() == true)) {
		distance = json[DISTANCE_KEY].GetDouble();
	} else {
		distance = std::numeric_limits<double>::quiet_NaN();
	}

	// azimuth
	if ((json.HasMember(AZIMUTH_KEY) == true)
			&& (json[AZIMUTH_KEY].IsNumber() == true)
			&& (json[AZIMUTH_KEY].IsDouble() == true)) {
		azimuth = json[AZIMUTH_KEY].GetDouble();
	} else {
		azimuth = std::numeric_limits<double>::quiet_NaN();
	}

	// weight
	if ((json.HasMember(WEIGHT_KEY) == true)
			&& (json[WEIGHT_KEY].IsNumber() == true)
			&& (json[WEIGHT_KEY].IsDouble() == true)) {
		weight = json[WEIGHT_KEY].GetDouble();
	} else {
		weight = std::numeric_limits<double>::quiet_NaN();
	}

	// importance
	if ((json.HasMember(IMPORTANCE_KEY) == true)
			&& (json[IMPORTANCE_KEY].IsNumber() == true)
			&& (json[IMPORTANCE_KEY].IsDouble() == true)) {
		importance = json[IMPORTANCE_KEY].GetDouble();
	} else {
		importance = std::numeric_limits<double>::quiet_NaN();
	}
}

Pick::Pick(const Pick & newPick) {
	id = newPick.id;
	site = newPick.site;
	source = newPick.source;
	time = newPick.time;
	affinity = newPick.affinity;
	quality = newPick.quality;
	use = newPick.use;
	pickedPhase = newPick.pickedPhase;
	associatedPhase = newPick.associatedPhase;
	locatedPhase = newPick.locatedPhase;
	residual = newPick.residual;
	distance = newPick.distance;
	azimuth = newPick.azimuth;
	weight = newPick.weight;
	importance = newPick.importance;
}

Pick::~Pick() {
}

rapidjson::Value & Pick::toJSON(
		rapidjson::Value &json,
		rapidjson::MemoryPoolAllocator<rapidjson::CrtAllocator> &allocator) {
	json.SetObject();

	// required values
	// id
	if (id != "") {
		rapidjson::Value idValue;
		idValue.SetString(rapidjson::StringRef(id.c_str()), allocator);
		json.AddMember(ID_KEY, idValue, allocator);
	}

	// site
	rapidjson::Value siteValue(rapidjson::kObjectType);
	site.toJSON(siteValue, allocator);
	json.AddMember(SITE_KEY, siteValue, allocator);

	// source
	rapidjson::Value sourceValue(rapidjson::kObjectType);
	source.toJSON(sourceValue, allocator);
	json.AddMember(SOURCE_KEY, sourceValue, allocator);

	// time
	if (std::isnan(time) != true) {
		std::string timeString = processingformats::ConvertEpochTimeToISO8601(
				time);
		rapidjson::Value timeValue;
		timeValue.SetString(rapidjson::StringRef(timeString.c_str()),
							allocator);
		json.AddMember(TIME_KEY, timeValue, allocator);
	}

	// affinity
	if (std::isnan(affinity) != true) {
		json.AddMember(AFFINITY_KEY, affinity, allocator);
	}

	// quality
	if (std::isnan(quality) != true) {
		json.AddMember(QUALITY_KEY, quality, allocator);
	}

	// use
	json.AddMember(USE_KEY, use, allocator);

	// pickedPhase
	if (pickedPhase != "") {
		rapidjson::Value pickedPhaseValue;
		pickedPhaseValue.SetString(rapidjson::StringRef(pickedPhase.c_str()),
			allocator);
		json.AddMember(PICKED_PHASE_KEY, pickedPhaseValue, allocator);
	}

	// associatedPhase
	if (associatedPhase != "") {
		rapidjson::Value associatedPhaseValue;
		associatedPhaseValue.SetString(rapidjson::StringRef(associatedPhase.c_str()),
			allocator);
		json.AddMember(ASSOCIATED_PHASE_KEY, associatedPhaseValue, allocator);
	}

	// Optional (output) values
	// locatedPhase
	if (locatedPhase != "") {
		rapidjson::Value locatedPhaseValue;
		locatedPhaseValue.SetString(rapidjson::StringRef(locatedPhase.c_str()),
			allocator);
		json.AddMember(LOCATED_PHASE_KEY, locatedPhaseValue, allocator);
	}

	// residual
	if (std::isnan(residual) != true) {
		json.AddMember(RESIDUAL_KEY, residual, allocator);
	}

	// distance
	if (std::isnan(distance) != true) {
		json.AddMember(DISTANCE_KEY, distance, allocator);
	}

	// azimuth
	if (std::isnan(azimuth) != true) {
		json.AddMember(AZIMUTH_KEY, azimuth, allocator);
	}

	// weight
	if (std::isnan(weight) != true) {
		json.AddMember(WEIGHT_KEY, weight, allocator);
	}

	// importance
	if (std::isnan(importance) != true) {
		json.AddMember(IMPORTANCE_KEY, importance, allocator);
	}

	return (json);
}

std::vector<std::string> Pick::getErrors() {
	std::vector<std::string> errorlist;

	if (id == "") {
		// empty id
		errorlist.push_back("Empty ID in Pick class.");
	}

	// site
	if (site.isValid() != true) {
		std::vector<std::string> siteErrors = site.getErrors();

		std::string errorString = "Site object did not validate in Pick class:";

		for (int i = 0; i < siteErrors.size(); i++) {
			errorString += " " + siteErrors[i];
		}

		// bad site
		errorlist.push_back(errorString);
	}

	// source
	if (source.isValid() != true) {
		std::vector<std::string> sourceErrors = source.getErrors();

		std::string errorString =
				"Source object did not validate in Pick class:";

		for (int i = 0; i < sourceErrors.size(); i++) {
			errorString += " " + sourceErrors[i];
		}

		// bad source
		errorlist.push_back(errorString);
	}

	// time
	if (std::isnan(time) == true) {
		errorlist.push_back("Time is missing in Pick class.");
	} else {
		try {
			if (processingformats::IsStringISO8601(
					processingformats::ConvertEpochTimeToISO8601(time))
					== false) {
				errorlist.push_back("Time did not validate in Pick class.");
			}
		} catch (const std::exception & e) {
			errorlist.push_back(std::string(e.what()));
		}
	}

	// affinity
	if (std::isnan(affinity) == true) {
		errorlist.push_back("No Affinity in Pick Class.");
	}

	// quality
	if (std::isnan(quality) == true) {
		errorlist.push_back("No Quality in Pick Class.");
	}

	// use
	// no validation for booleans

	// pickedPhase
	if (pickedPhase == "") {
		// empty pickedPhase
		errorlist.push_back("Empty Picked Phase in Pick class.");
	}

	// associatedPhase
	if (associatedPhase == "") {
		// empty associatedPhase
		errorlist.push_back("Empty Associated Phase in Pick class.");
	}

	// Optional (Output) Keys
	// Currently no validation criteria for optional (output) values

	// return the list of errors
	return (errorlist);
}

}  // namespace processingformats
