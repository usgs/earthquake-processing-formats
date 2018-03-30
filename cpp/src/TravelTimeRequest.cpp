#include <TravelTimeRequest.h>

#include <string>
#include <limits>
#include <exception>
#include <vector>

// JSON Keys
#define TYPE_KEY "Type"
#define DISTANCE_KEY "Distance"
#define ELEVATION_KEY "Elevation"
#define LATITUDE_KEY "Latitude"
#define LONGITUDE_KEY "Longitude"
#define DATA_KEY "Data"

namespace processingformats {

TravelTimeRequest::TravelTimeRequest() {
	type = "Standard";
	distance = std::numeric_limits<double>::quiet_NaN();
	elevation = std::numeric_limits<double>::quiet_NaN();
	latitude = std::numeric_limits<double>::quiet_NaN();
	longitude = std::numeric_limits<double>::quiet_NaN();
	data.clear();
	plotData.clear();
}

TravelTimeRequest::TravelTimeRequest(
		std::string newType, double newDistance, double newElevation,
		double newLatitude, double newLongitude,
		std::vector<TravelTimeData> newData,
		std::vector<TravelTimePlotData> newPlotData) {
	if (newType == "") {
		type = "Standard";
	} else {
		type = newType;
	}
	distance = newDistance;
	elevation = newElevation;
	latitude = newLatitude;
	longitude = newLongitude;
	data.clear();
	for (int i = 0; i < static_cast<int>(newData.size()); i++) {
		data.push_back(newData[i]);
	}
	plotData.clear();
	for (int i = 0; i < static_cast<int>(newPlotData.size()); i++) {
		plotData.push_back(newPlotData[i]);
	}
}

TravelTimeRequest::TravelTimeRequest(rapidjson::Value &json) {
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

TravelTimeRequest::TravelTimeRequest(
		const TravelTimeRequest & newTravelTimeRequest) {
	type = "TTData";
	phase = newTravelTimeRequest.phase;
	travelTime = newTravelTimeRequest.travelTime;
	distanceDerivative = newTravelTimeRequest.distanceDerivative;
	depthDerivative = newTravelTimeRequest.depthDerivative;
	rayDerivative = newTravelTimeRequest.rayDerivative;
	statisticalSpread = newTravelTimeRequest.statisticalSpread;
	observability = newTravelTimeRequest.observability;
	teleseismicPhaseGroup = newTravelTimeRequest.teleseismicPhaseGroup;
	auxiliaryPhaseGroup = newTravelTimeRequest.auxiliaryPhaseGroup;
	locationUseFlag = newTravelTimeRequest.locationUseFlag;
	associationWeightFlag = newTravelTimeRequest.associationWeightFlag;
}

TravelTimeRequest::~TravelTimeRequest() {
}

rapidjson::Value & TravelTimeRequest::toJSON(
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

std::vector<std::string> TravelTimeRequest::getErrors() {
	std::vector<std::string> errorlist;

	// required data
	// type
	if (type == "") {
		// type empty
		errorlist.push_back("Empty Type in TravelTimeRequest Class.");
	} else if (type != "TTData") {
		// wrong type
		errorlist.push_back("Non-TTData type in TravelTimeRequest Class.");
	}

	// phase
	if (phase == "") {
		// phase empty
		errorlist.push_back("Empty Phase in TravelTimeRequest Class.");
	}

	// travel time
	if (std::isnan(travelTime) != true) {
		// travel time not found
		errorlist.push_back("No Travel Time in TravelTimeRequest Class.");
	}

	// distance derivative
	if (std::isnan(distanceDerivative) != true) {
		// distance derivative not found
		errorlist.push_back(
				"No Distance Derivative in TravelTimeRequest Class.");
	}

	// depth derivative
	if (std::isnan(depthDerivative) != true) {
		// depth derivative not found
		errorlist.push_back("No Depth Derivative in TravelTimeRequest Class.");
	}

	// ray derivative
	if (std::isnan(rayDerivative) != true) {
		// ray derivative not found
		errorlist.push_back("No Ray Derivative in TravelTimeRequest Class.");
	}

	// statistical spread
	if (std::isnan(statisticalSpread) != true) {
		// statistical spread not found
		errorlist.push_back(
				"No Statistical Spread in TravelTimeRequest Class.");
	}

	// observability
	if (std::isnan(observability) != true) {
		// observability not found
		errorlist.push_back("No Observability in TravelTimeRequest Class.");
	}

	// teleseismic phase group
	if (teleseismicPhaseGroup == "") {
		// teleseismic phase group not found
		errorlist.push_back(
				"No Teleseismic Phase Group in TravelTimeRequest Class.");
	}

	// auxiliary phase group
	if (auxiliaryPhaseGroup == "") {
		// auxiliary phase group not found
		errorlist.push_back(
				"No Auxiliary Phase Group in TravelTimeRequest Class.");
	}

	return (errorlist);
}
}  // namespace processingformats
