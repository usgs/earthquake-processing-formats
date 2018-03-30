#include <TravelTimeData.h>

#include <string>
#include <limits>
#include <exception>
#include <vector>

// JSON Keys
#define TYPE_KEY "Type"
#define PHASE_KEY "Phase"
#define TRAVELTIME_KEY "TravelTime"
#define DISTANCEDERIVATIVE_KEY "DistanceDerivative"
#define DEPTHDERIVATIVE_KEY "DepthDerivative"
#define RAYDERIVATIVE_KEY "RayDerivative"
#define STATISTICALSPREAD_KEY "StatisticalSpread"
#define OBSERVABILITY_KEY "Observability"
#define TELESEISMICPHASEGROUP_KEY "TeleseismicPhaseGroup"
#define AUXILIARYPHASEGROUP_KEY "AuxiliaryPhaseGroup"
#define LOCATIONUSEFLAG_KEY "LocationUseFlag"
#define ASSOCIATIONWEIGHTFLAG_KEY "AssociationWeightFlag"

namespace processingformats {

TravelTimeData::TravelTimeData() {
	type = "TTData";
	phase = "";
	travelTime = std::numeric_limits<double>::quiet_NaN();
	distanceDerivative = std::numeric_limits<double>::quiet_NaN();
	depthDerivative = std::numeric_limits<double>::quiet_NaN();
	rayDerivative = std::numeric_limits<double>::quiet_NaN();
	statisticalSpread = std::numeric_limits<double>::quiet_NaN();
	observability = std::numeric_limits<double>::quiet_NaN();
	teleseismicPhaseGroup = "";
	auxiliaryPhaseGroup = "";
	locationUseFlag = false;
	associationWeightFlag = false;
}

TravelTimeData::TravelTimeData(std::string newPhase, double newTravelTime,
								double newDistanceDerivative,
								double newDepthDerivative,
								double newRayDerivative,
								double newStatisticalSpread,
								double newObservability,
								std::string newTeleseismicPhaseGroup,
								std::string newAuxiliaryPhaseGroup,
								bool newLocationUseFlag,
								bool newAssociationWeightFlag) {
	type = "TTData";
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

TravelTimeData::TravelTimeData(rapidjson::Value &json) {
	// required values
	// type
	if ((json.HasMember(TYPE_KEY) == true)
			&& (json[TYPE_KEY].IsString() == true)) {
		type = std::string(json[TYPE_KEY].GetString(),
							json[TYPE_KEY].GetStringLength());
	} else {
		type = "";
	}

	// phase
	if ((json.HasMember(PHASE_KEY) == true)
			&& (json[PHASE_KEY].IsString() == true)) {
		phase = std::string(json[PHASE_KEY].GetString(),
							json[PHASE_KEY].GetStringLength());
	} else {
		phase = "";
	}

	// travelTime
	if ((json.HasMember(TRAVELTIME_KEY) == true)
			&& (json[TRAVELTIME_KEY].IsNumber() == true)
			&& (json[TRAVELTIME_KEY].IsDouble() == true))
		travelTime = json[TRAVELTIME_KEY].GetDouble();
	else
		travelTime = std::numeric_limits<double>::quiet_NaN();

	// distanceDerivative
	if ((json.HasMember(DISTANCEDERIVATIVE_KEY) == true)
			&& (json[DISTANCEDERIVATIVE_KEY].IsNumber() == true)
			&& (json[DISTANCEDERIVATIVE_KEY].IsDouble() == true))
		distanceDerivative = json[DISTANCEDERIVATIVE_KEY].GetDouble();
	else
		distanceDerivative = std::numeric_limits<double>::quiet_NaN();

	// depthDerivative
	if ((json.HasMember(DEPTHDERIVATIVE_KEY) == true)
			&& (json[DEPTHDERIVATIVE_KEY].IsNumber() == true)
			&& (json[DEPTHDERIVATIVE_KEY].IsDouble() == true))
		depthDerivative = json[DEPTHDERIVATIVE_KEY].GetDouble();
	else
		depthDerivative = std::numeric_limits<double>::quiet_NaN();

	// rayDerivative
	if ((json.HasMember(RAYDERIVATIVE_KEY) == true)
			&& (json[RAYDERIVATIVE_KEY].IsNumber() == true)
			&& (json[RAYDERIVATIVE_KEY].IsDouble() == true))
		rayDerivative = json[RAYDERIVATIVE_KEY].GetDouble();
	else
		rayDerivative = std::numeric_limits<double>::quiet_NaN();

	// statisticalSpread
	if ((json.HasMember(STATISTICALSPREAD_KEY) == true)
			&& (json[STATISTICALSPREAD_KEY].IsNumber() == true)
			&& (json[STATISTICALSPREAD_KEY].IsDouble() == true))
		statisticalSpread = json[STATISTICALSPREAD_KEY].GetDouble();
	else
		statisticalSpread = std::numeric_limits<double>::quiet_NaN();

	// observability
	if ((json.HasMember(OBSERVABILITY_KEY) == true)
			&& (json[OBSERVABILITY_KEY].IsNumber() == true)
			&& (json[OBSERVABILITY_KEY].IsDouble() == true))
		observability = json[OBSERVABILITY_KEY].GetDouble();
	else
		observability = std::numeric_limits<double>::quiet_NaN();

	// teleseismicPhaseGroup
	if ((json.HasMember(TELESEISMICPHASEGROUP_KEY) == true)
			&& (json[TELESEISMICPHASEGROUP_KEY].IsString() == true))
		teleseismicPhaseGroup = std::string(
				json[TELESEISMICPHASEGROUP_KEY].GetString(),
				json[TELESEISMICPHASEGROUP_KEY].GetStringLength());
	else
		teleseismicPhaseGroup = "";

	// auxiliaryPhaseGroup
	if ((json.HasMember(AUXILIARYPHASEGROUP_KEY) == true)
			&& (json[AUXILIARYPHASEGROUP_KEY].IsString() == true))
		auxiliaryPhaseGroup = std::string(
				json[AUXILIARYPHASEGROUP_KEY].GetString(),
				json[AUXILIARYPHASEGROUP_KEY].GetStringLength());
	else
		auxiliaryPhaseGroup = "";

	// locationUseFlag
	if ((json.HasMember(LOCATIONUSEFLAG_KEY) == true)
			&& (json[LOCATIONUSEFLAG_KEY].IsBool() == true)) {
		locationUseFlag = json[LOCATIONUSEFLAG_KEY].GetBool();
	} else {
		locationUseFlag = true;
	}

	// associationWeightFlag
	if ((json.HasMember(ASSOCIATIONWEIGHTFLAG_KEY) == true)
			&& (json[ASSOCIATIONWEIGHTFLAG_KEY].IsBool() == true)) {
		associationWeightFlag = json[ASSOCIATIONWEIGHTFLAG_KEY].GetBool();
	} else {
		associationWeightFlag = true;
	}
}

TravelTimeData::TravelTimeData(const TravelTimeData & newTravelTimeData) {
	type = "TTData";
	phase = newTravelTimeData.phase;
	travelTime = newTravelTimeData.travelTime;
	distanceDerivative = newTravelTimeData.distanceDerivative;
	depthDerivative = newTravelTimeData.depthDerivative;
	rayDerivative = newTravelTimeData.rayDerivative;
	statisticalSpread = newTravelTimeData.statisticalSpread;
	observability = newTravelTimeData.observability;
	teleseismicPhaseGroup = newTravelTimeData.teleseismicPhaseGroup;
	auxiliaryPhaseGroup = newTravelTimeData.auxiliaryPhaseGroup;
	locationUseFlag = newTravelTimeData.locationUseFlag;
	associationWeightFlag = newTravelTimeData.associationWeightFlag;
}

TravelTimeData::~TravelTimeData() {
}

rapidjson::Value & TravelTimeData::toJSON(
		rapidjson::Value &json,
		rapidjson::MemoryPoolAllocator<rapidjson::CrtAllocator> &allocator) {
	json.SetObject();

	// required values
	// type
	rapidjson::Value typevalue;
	typevalue.SetString(rapidjson::StringRef(type.c_str()), allocator);
	json.AddMember(TYPE_KEY, typevalue, allocator);

	// phase
	if (phase != "") {
		rapidjson::Value phasevalue;
		phasevalue.SetString(rapidjson::StringRef(phase.c_str()), allocator);
		json.AddMember(PHASE_KEY, phasevalue, allocator);
	}

	// travelTime
	if (std::isnan(travelTime) != true)
		json.AddMember(TRAVELTIME_KEY, travelTime, allocator);

	// distanceDerivative
	if (std::isnan(distanceDerivative) != true)
		json.AddMember(DISTANCEDERIVATIVE_KEY, distanceDerivative, allocator);

	// depthDerivative
	if (std::isnan(depthDerivative) != true)
		json.AddMember(DEPTHDERIVATIVE_KEY, depthDerivative, allocator);

	// rayDerivative
	if (std::isnan(rayDerivative) != true)
		json.AddMember(RAYDERIVATIVE_KEY, rayDerivative, allocator);

	// statisticalSpread
	if (std::isnan(statisticalSpread) != true)
		json.AddMember(STATISTICALSPREAD_KEY, statisticalSpread, allocator);

	// observability
	if (std::isnan(observability) != true)
		json.AddMember(OBSERVABILITY_KEY, observability, allocator);

	// teleseismicPhaseGroup
	if (teleseismicPhaseGroup != "") {
		rapidjson::Value televalue;
		televalue.SetString(rapidjson::StringRef(teleseismicPhaseGroup.c_str()),
							allocator);
		json.AddMember(TELESEISMICPHASEGROUP_KEY, televalue, allocator);
	}

	// snr
	if (auxiliaryPhaseGroup != "") {
		rapidjson::Value auxvalue;
		auxvalue.SetString(rapidjson::StringRef(auxiliaryPhaseGroup.c_str()),
							allocator);
		json.AddMember(AUXILIARYPHASEGROUP_KEY, auxvalue, allocator);
	}

	// locationUseFlag
	json.AddMember(LOCATIONUSEFLAG_KEY, locationUseFlag, allocator);

	// associationWeightFlag
	json.AddMember(ASSOCIATIONWEIGHTFLAG_KEY, associationWeightFlag, allocator);

	return (json);
}

std::vector<std::string> TravelTimeData::getErrors() {
	std::vector<std::string> errorlist;

	// required data
	// type
	if (type == "") {
		// type empty
		errorlist.push_back("Empty Type in TravelTimeData Class.");
	} else if (type != "TTData") {
		// wrong type
		errorlist.push_back("Non-TTData type in TravelTimeData Class.");
	}

	// phase
	if (phase == "") {
		// phase empty
		errorlist.push_back("Empty Phase in TravelTimeData Class.");
	}

	// travel time
	if (std::isnan(travelTime) != true) {
		// travel time not found
		errorlist.push_back("No Travel Time in TravelTimeData Class.");
	}

	// distance derivative
	if (std::isnan(distanceDerivative) != true) {
		// distance derivative not found
		errorlist.push_back("No Distance Derivative in TravelTimeData Class.");
	}

	// depth derivative
	if (std::isnan(depthDerivative) != true) {
		// depth derivative not found
		errorlist.push_back("No Depth Derivative in TravelTimeData Class.");
	}

	// ray derivative
	if (std::isnan(rayDerivative) != true) {
		// ray derivative not found
		errorlist.push_back("No Ray Derivative in TravelTimeData Class.");
	}

	// statistical spread
	if (std::isnan(statisticalSpread) != true) {
		// statistical spread not found
		errorlist.push_back("No Statistical Spread in TravelTimeData Class.");
	}

	// observability
	if (std::isnan(observability) != true) {
		// observability not found
		errorlist.push_back("No Observability in TravelTimeData Class.");
	}

	// teleseismic phase group
	if (teleseismicPhaseGroup == "") {
		// teleseismic phase group not found
		errorlist.push_back(
				"No Teleseismic Phase Group in TravelTimeData Class.");
	}

	// auxiliary phase group
	if (auxiliaryPhaseGroup == "") {
		// auxiliary phase group not found
		errorlist.push_back(
				"No Auxiliary Phase Group in TravelTimeData Class.");
	}

	return (errorlist);
}
}  // namespace processingformats
