#include <TravelTimeSession.h>

#include <string>
#include <limits>
#include <exception>
#include <vector>

// JSON Keys
#define SOURCEDEPTH_KEY "SourceDepth"
#define EARTHMODEL_KEY "EarthModel"
#define PHASETYPES_KEY "PhaseTypes"
#define SOURCELATITUDE_KEY "SourceLatitude"
#define SOURCELONGITUDE_KEY "SourceLongitude"
#define RETURNALLPHASES_KEY "ReturnAllPhases"
#define RETURNBACKBRANCHES_KEY "ReturnBackBranches"
#define CONVERTTECTONIC_KEY "ConvertTectonic"
#define USERSTT_KEY "UseRSTT"
#define ISPLOT_KEY "IsPlot"

namespace processingformats {

TravelTimeSession::TravelTimeSession() {
	sourceDepth = std::numeric_limits<double>::quiet_NaN();
	earthModel = "";
	phaseTypes.clear();
	sourceLatitude = std::numeric_limits<double>::quiet_NaN();
	sourceLongitude = std::numeric_limits<double>::quiet_NaN();
	returnAllPhases = false;
	returnBackBranches = false;
	convertTectonic = false;
	useRSTT = false;
	isPlot = false;
}

TravelTimeSession::TravelTimeSession(double newSourceDepth,
										std::string newEarthModel,
										std::vector<std::string> newPhaseTypes,
										double newSourceLatitude,
										double newSourceLongitude,
										bool newReturnAllPhases,
										bool newReturnBackBranches,
										bool newConvertTectonic,
										bool newUseRSTT, bool newIsPlot) {
	sourceDepth = newSourceDepth;
	earthModel = newEarthModel;

	phaseTypes.clear();
	for (int i = 0; i < static_cast<int>(newPhaseTypes.size()); i++) {
		phaseTypes.push_back(newPhaseTypes[i]);
	}

	sourceLatitude = newSourceLatitude;
	sourceLongitude = newSourceLongitude;
	returnAllPhases = newReturnAllPhases;
	returnBackBranches = newReturnBackBranches;
	convertTectonic = newConvertTectonic;
	useRSTT = newUseRSTT;
	isPlot = newIsPlot;
}

TravelTimeSession::TravelTimeSession(rapidjson::Value &json) {
	// required values
	// sourceDepth
	if ((json.HasMember(SOURCEDEPTH_KEY) == true)
			&& (json[SOURCEDEPTH_KEY].IsNumber() == true)
			&& (json[SOURCEDEPTH_KEY].IsDouble() == true))
		sourceDepth = json[SOURCEDEPTH_KEY].GetDouble();
	else
		sourceDepth = std::numeric_limits<double>::quiet_NaN();

	// optional values
	// earthModel
	if ((json.HasMember(EARTHMODEL_KEY) == true)
			&& (json[EARTHMODEL_KEY].IsString() == true)) {
		earthModel = std::string(json[EARTHMODEL_KEY].GetString(),
									json[EARTHMODEL_KEY].GetStringLength());
	} else {
		earthModel = "";
	}

	// phaseTypes
	phaseTypes.clear();
	if ((json.HasMember(PHASETYPES_KEY) == true)
			&& (json[PHASETYPES_KEY].IsArray() == true)) {
		rapidjson::Value dataarray;
		dataarray = json[PHASETYPES_KEY].GetArray();

		for (rapidjson::SizeType i = 0; i < dataarray.Size(); i++) {
			// parse
			rapidjson::Value & datavalue = dataarray[i];

			if (datavalue.IsString() == true) {
				phaseTypes.push_back(datavalue.GetString());
			}
		}
	}

	// sourceLatitude
	if ((json.HasMember(SOURCELATITUDE_KEY) == true)
			&& (json[SOURCELATITUDE_KEY].IsNumber() == true)
			&& (json[SOURCELATITUDE_KEY].IsDouble() == true))
		sourceLatitude = json[SOURCELATITUDE_KEY].GetDouble();
	else
		sourceLatitude = std::numeric_limits<double>::quiet_NaN();

	// sourceLongitude
	if ((json.HasMember(SOURCELONGITUDE_KEY) == true)
			&& (json[SOURCELONGITUDE_KEY].IsNumber() == true)
			&& (json[SOURCELONGITUDE_KEY].IsDouble() == true))
		sourceLongitude = json[SOURCELONGITUDE_KEY].GetDouble();
	else
		sourceLongitude = std::numeric_limits<double>::quiet_NaN();

	// returnAllPhases
	if ((json.HasMember(RETURNALLPHASES_KEY) == true)
			&& (json[RETURNALLPHASES_KEY].IsBool() == true)) {
		returnAllPhases = json[RETURNALLPHASES_KEY].GetBool();
	} else {
		returnAllPhases = true;
	}

	// returnBackBranches
	if ((json.HasMember(RETURNBACKBRANCHES_KEY) == true)
			&& (json[RETURNBACKBRANCHES_KEY].IsBool() == true)) {
		returnBackBranches = json[RETURNBACKBRANCHES_KEY].GetBool();
	} else {
		returnBackBranches = true;
	}

	// convertTectonic
	if ((json.HasMember(CONVERTTECTONIC_KEY) == true)
			&& (json[CONVERTTECTONIC_KEY].IsBool() == true)) {
		convertTectonic = json[CONVERTTECTONIC_KEY].GetBool();
	} else {
		convertTectonic = true;
	}

	// useRSTT
	if ((json.HasMember(USERSTT_KEY) == true)
			&& (json[USERSTT_KEY].IsBool() == true)) {
		useRSTT = json[USERSTT_KEY].GetBool();
	} else {
		useRSTT = true;
	}

	// isPlot
	if ((json.HasMember(ISPLOT_KEY) == true)
			&& (json[ISPLOT_KEY].IsBool() == true)) {
		isPlot = json[ISPLOT_KEY].GetBool();
	} else {
		isPlot = true;
	}
}

TravelTimeSession::TravelTimeSession(
		const TravelTimeSession & newTravelTimeSession) {
	sourceDepth = newTravelTimeSession.sourceDepth;
	earthModel = newTravelTimeSession.earthModel;

	phaseTypes.clear();
	for (int i = 0;
			i < static_cast<int>(newTravelTimeSession.phaseTypes.size()); i++) {
		phaseTypes.push_back(newTravelTimeSession.phaseTypes[i]);
	}

	sourceLatitude = newTravelTimeSession.sourceLatitude;
	sourceLongitude = newTravelTimeSession.sourceLongitude;
	returnAllPhases = newTravelTimeSession.returnAllPhases;
	returnBackBranches = newTravelTimeSession.returnBackBranches;
	convertTectonic = newTravelTimeSession.convertTectonic;
	useRSTT = newTravelTimeSession.useRSTT;
	isPlot = newTravelTimeSession.isPlot;
}

TravelTimeSession::~TravelTimeSession() {
}

rapidjson::Value & TravelTimeSession::toJSON(
		rapidjson::Value &json,
		rapidjson::MemoryPoolAllocator<rapidjson::CrtAllocator> &allocator) {
	json.SetObject();

	// required values
	// sourceDepth
	if (std::isnan(sourceDepth) != true)
		json.AddMember(SOURCEDEPTH_KEY, sourceDepth, allocator);

	// optional values
	// earthModel
	if (earthModel != "") {
		rapidjson::Value earthModelValue;
		earthModelValue.SetString(rapidjson::StringRef(earthModel.c_str()),
									allocator);
		json.AddMember(EARTHMODEL_KEY, earthModelValue, allocator);
	}

	if (phaseTypes.size() > 0) {
		// build json array
		rapidjson::Value dataarray(rapidjson::kArrayType);

		for (int i = 0; i < static_cast<int>(phaseTypes.size()); i++) {
			rapidjson::Value aPhaseType(phaseTypes[i].c_str(), allocator);
			dataarray.PushBack(aPhaseType, allocator);
		}

		if (dataarray.Size() > 0) {
			json.AddMember(PHASETYPES_KEY, dataarray, allocator);
		}
	}

	// latitude
	if (std::isnan(sourceLatitude) != true)
		json.AddMember(SOURCELATITUDE_KEY, sourceLatitude, allocator);

	// longitude
	if (std::isnan(sourceLongitude) != true)
		json.AddMember(SOURCELONGITUDE_KEY, sourceLongitude, allocator);

	// returnAllPhases
	json.AddMember(RETURNALLPHASES_KEY, returnAllPhases, allocator);

	// returnBackBranches
	json.AddMember(RETURNBACKBRANCHES_KEY, returnBackBranches, allocator);

	// convertTectonic
	json.AddMember(CONVERTTECTONIC_KEY, convertTectonic, allocator);

	// useRSTT
	json.AddMember(USERSTT_KEY, useRSTT, allocator);

	// isPlot
	json.AddMember(ISPLOT_KEY, isPlot, allocator);

	return (json);
}

std::vector<std::string> TravelTimeSession::getErrors() {
	std::vector<std::string> errorlist;

	// required data
	// sourceDepth
	if (std::isnan(sourceDepth) == true) {
		// depth found
		errorlist.push_back("No Source Depth in TravelTimeSession Class.");
	} else if ((sourceDepth < -100) || (sourceDepth > 1500)) {
		// invalid depth
		errorlist.push_back(
				"Source Depth in TravelTimeSession Class not in the range of "
				"-100 to 1500.");
	}

	// optional data
	// sourceLatitude
	if (std::isnan(sourceLatitude) != true) {
		if ((sourceLatitude < -90) || (sourceLatitude > 90)) {
			errorlist.push_back(
					"Invalid Source Latitude in TravelTimeSession class.");
		}
	}

	// sourceLongitude
	if (std::isnan(sourceLongitude) != true) {
		if ((sourceLongitude < -180) || (sourceLongitude > 180)) {
			errorlist.push_back(
					"Invalid Source Longitude in TravelTimeSession class.");
		}
	}

	// earthModel and phaseTypes are strings, validation could be done on
	// them in the future
	// no validation necessary for boolean values

	return (errorlist);
}
}  // namespace processingformats
