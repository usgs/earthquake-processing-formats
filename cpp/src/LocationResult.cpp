#include <LocationResult.h>

#include <string>
#include <limits>
#include <vector>

#define HYPOCENTER_KEY "Hypocenter"
#define SUPPORTINGDATA_KEY "SupportingData"
#define ID_KEY "ID"
#define SOURCE_KEY "Source"
#define ASSOCIATEDSTATIONS_KEY "NumberOfAssociatedStations"
#define ASSOCIATEDPHASES_KEY "NumberOfAssociatedPhases"
#define USEDSTATIONS_KEY "NumberOfUsedStations"
#define USEDPHASES_KEY "NumberOfUsedPhases"
#define GAP_KEY "Gap"
#define SECONDARYGAP_KEY "SecondaryGap"
#define MINIMUMDISTANCE_KEY "MinimumDistance"
#define RMS_KEY "RMS"
#define QUALITY_KEY "Quality"
#define BAYESIANDEPTH_KEY "BayesianDepth"
#define BAYESIANRANGE_KEY "BayesianRange"
#define DEPTHIMPORTANCE_KEY "DepthImportance"
#define LOCATOREXITCODE_KEY "LocatorExitCode"
#define ERRORELLIPSE_KEY "ErrorEllipse"

namespace processingformats {

LocationResult::LocationResult() {
	hypocenter = processingformats::Hypocenter();
	supportingData.clear();
	id = "";
	source = processingformats::Source();
	numberOfAssociatedStations = -1;
	numberOfAssociatedPhases = -1;
	numberOfUsedStations = -1;
	numberOfUsedPhases = -1;
	gap = std::numeric_limits<double>::quiet_NaN();
	secondaryGap = std::numeric_limits<double>::quiet_NaN();
	minimumDistance = std::numeric_limits<double>::quiet_NaN();
	rms = std::numeric_limits<double>::quiet_NaN();
	quality = "";
  	bayesianDepth = std::numeric_limits<double>::quiet_NaN();
	bayesianRange = std::numeric_limits<double>::quiet_NaN();
	depthImportance = std::numeric_limits<double>::quiet_NaN();
	locatorExitCode = "";
	errorEllipse = processingformats::ErrorEllipse();
}

LocationResult::LocationResult(std::string newID, std::string newAgencyID,
		std::string newAuthor,
		std::string newType, double newLatitude, double newLongitude,
		double newTime, double newDepth, double newLatitudeError,
		double newLongitudeError, double newTimeError, double newDepthError,
		std::vector<processingformats::Pick> newSupportingData,
		int newAssociatedStations, int newAssociatedPhases,
		int newUsedStations, int newUsedPhases, double newGap,
		double newSecondaryGap, double newMinimumDistance, double newRMS,
		std::string newQuality, double newBayesianDepth,
		double newBayesianRange, double newDepthImportance, std::string newExitCode,
		double newE0Error, double newE0Azimuth,
		double newE0Dip, double newE1Error, double newE1Azimuth,
		double newE1Dip, double newE2Error, double newE2Azimuth,
		double newE2Dip, double newMaximumHorizontalProjection,
		double newMaximumVerticalProjection,
		double newEquivalentHorizontalRadius) {
	hypocenter = processingformats::Hypocenter(newLatitude, newLongitude,
												newTime, newDepth,
												newLatitudeError,
												newLongitudeError,
												newTimeError,
												newDepthError);


	// copy data
	supportingData.clear();
	for (int i = 0; i < static_cast<int>(newSupportingData.size()); i++) {
		supportingData.push_back(newSupportingData[i]);
	}

	id = newID;
	source = processingformats::Source(newAgencyID, newAuthor, newType);
	numberOfAssociatedStations = newAssociatedStations;
	numberOfAssociatedPhases = newAssociatedPhases;
	numberOfUsedStations = newUsedStations;
	numberOfUsedPhases = newUsedPhases;
	gap = newGap;
	secondaryGap = newSecondaryGap;
	minimumDistance = newMinimumDistance;
	rms = newRMS;
	quality = newQuality;
  	bayesianDepth = newBayesianDepth;
	bayesianRange = newBayesianRange;
	depthImportance = newDepthImportance;
	locatorExitCode = newExitCode;
	errorEllipse = processingformats::ErrorEllipse(newE0Error, newE0Azimuth,
													newE0Dip, newE1Error,
													newE1Azimuth, newE1Dip,
													newE2Error, newE2Azimuth,
													newE2Dip,
													newMaximumHorizontalProjection,
													newMaximumVerticalProjection,
													newEquivalentHorizontalRadius);
}

LocationResult::LocationResult(double newLatitude, double newLongitude,
		double newTime, double newDepth, double newLatitudeError,
		double newLongitudeError, double newTimeError, double newDepthError,
        std::vector<processingformats::Pick> newSupportingData) {
	hypocenter = processingformats::Hypocenter(newLatitude, newLongitude,
												newTime, newDepth,
												newLatitudeError,
												newLongitudeError,
												newTimeError,
												newDepthError);


	// copy data
	supportingData.clear();
	for (int i = 0; i < static_cast<int>(newSupportingData.size()); i++) {
		supportingData.push_back(newSupportingData[i]);
	}

	id = "";
	source = processingformats::Source();
	numberOfAssociatedStations = -1;
	numberOfAssociatedPhases = -1;
	numberOfUsedStations = -1;
	numberOfUsedPhases = -1;
	gap = std::numeric_limits<double>::quiet_NaN();
	secondaryGap = std::numeric_limits<double>::quiet_NaN();
	minimumDistance = std::numeric_limits<double>::quiet_NaN();
	rms = std::numeric_limits<double>::quiet_NaN();
	quality = "";
  bayesianDepth = std::numeric_limits<double>::quiet_NaN();
	bayesianRange = std::numeric_limits<double>::quiet_NaN();
	depthImportance = std::numeric_limits<double>::quiet_NaN();
	locatorExitCode = "";
	errorEllipse = processingformats::ErrorEllipse();
}

LocationResult::LocationResult(processingformats::Hypocenter newHypocenter,
		std::vector<processingformats::Pick> newSupportingData) {
	hypocenter = newHypocenter;

	// copy data
	supportingData.clear();
	for (int i = 0; i < static_cast<int>(newSupportingData.size()); i++) {
		supportingData.push_back(newSupportingData[i]);
	}

	id = "";
	source = processingformats::Source();
	numberOfAssociatedStations = -1;
	numberOfAssociatedPhases = -1;
	numberOfUsedStations = -1;
	numberOfUsedPhases = -1;
	gap = std::numeric_limits<double>::quiet_NaN();
	secondaryGap = std::numeric_limits<double>::quiet_NaN();
	minimumDistance = std::numeric_limits<double>::quiet_NaN();
	rms = std::numeric_limits<double>::quiet_NaN();
	quality = "";
  bayesianDepth = std::numeric_limits<double>::quiet_NaN();
	bayesianRange = std::numeric_limits<double>::quiet_NaN();
	depthImportance = std::numeric_limits<double>::quiet_NaN();
	locatorExitCode = "";
	errorEllipse = processingformats::ErrorEllipse();
}

LocationResult::LocationResult(rapidjson::Value &json) {
	// required values
	// hypocenter
	if ((json.HasMember(HYPOCENTER_KEY) == true)
			&& (json[HYPOCENTER_KEY].IsObject() == true)) {
		rapidjson::Value & hypovalue = json[HYPOCENTER_KEY];
		hypocenter = processingformats::Hypocenter(hypovalue);
	} else {
		hypocenter = processingformats::Hypocenter();
	}

	// supporting data
	supportingData.clear();
	if ((json.HasMember(SUPPORTINGDATA_KEY) == true)
			&& (json[SUPPORTINGDATA_KEY].IsArray() == true)) {
		rapidjson::Value dataarray;
		dataarray = json[SUPPORTINGDATA_KEY].GetArray();

		for (rapidjson::SizeType i = 0; i < dataarray.Size(); i++) {
			rapidjson::Value & datavalue = dataarray[i];

			processingformats::Pick newdata(datavalue);

			// add to vector
			supportingData.push_back(newdata);
		}
	}

	// optional values
	// id
	if ((json.HasMember(ID_KEY) == true) && (json[ID_KEY].IsString() == true)) {
		id = std::string(json[ID_KEY].GetString(),
							json[ID_KEY].GetStringLength());
	} else {
		id = "";
	}

	// source
	if ((json.HasMember(SOURCE_KEY) == true)
			&& (json[SOURCE_KEY].IsObject() == true)) {
		rapidjson::Value & sourcevalue = json[SOURCE_KEY];
		source = processingformats::Source(sourcevalue);
	} else {
		source = processingformats::Source();
	}

	// associated stations
	if ((json.HasMember(ASSOCIATEDSTATIONS_KEY) == true)
			&& (json[ASSOCIATEDSTATIONS_KEY].IsNumber() == true)
			&& (json[ASSOCIATEDSTATIONS_KEY].IsInt() == true)) {
		numberOfAssociatedStations = json[ASSOCIATEDSTATIONS_KEY].GetInt();
	} else {
		numberOfAssociatedStations = -1;
	}

	// associated phases
	if ((json.HasMember(ASSOCIATEDPHASES_KEY) == true)
			&& (json[ASSOCIATEDPHASES_KEY].IsNumber() == true)
			&& (json[ASSOCIATEDPHASES_KEY].IsInt() == true)) {
		numberOfAssociatedPhases = json[ASSOCIATEDPHASES_KEY].GetInt();
	} else {
		numberOfAssociatedPhases = -1;
	}

	// used stations
	if ((json.HasMember(USEDSTATIONS_KEY) == true)
			&& (json[USEDSTATIONS_KEY].IsNumber() == true)
			&& (json[USEDSTATIONS_KEY].IsInt() == true)) {
		numberOfUsedStations = json[USEDSTATIONS_KEY].GetInt();
	} else {
		numberOfUsedStations = -1;
	}

	// used phases
	if ((json.HasMember(USEDPHASES_KEY) == true)
			&& (json[USEDPHASES_KEY].IsNumber() == true)
			&& (json[USEDPHASES_KEY].IsInt() == true)) {
		numberOfUsedPhases = json[USEDPHASES_KEY].GetInt();
	} else {
		numberOfUsedPhases = -1;
	}

	// gap
	if ((json.HasMember(GAP_KEY) == true)
			&& (json[GAP_KEY].IsNumber() == true)
			&& (json[GAP_KEY].IsDouble() == true)) {
		gap = json[GAP_KEY].GetDouble();
	} else {
		gap = std::numeric_limits<double>::quiet_NaN();
	}

	// secondaryGap
	if ((json.HasMember(SECONDARYGAP_KEY) == true)
			&& (json[SECONDARYGAP_KEY].IsNumber() == true)
			&& (json[SECONDARYGAP_KEY].IsDouble() == true)) {
		secondaryGap = json[SECONDARYGAP_KEY].GetDouble();
	} else {
		secondaryGap = std::numeric_limits<double>::quiet_NaN();
	}

	// minimumDistance
	if ((json.HasMember(MINIMUMDISTANCE_KEY) == true)
			&& (json[MINIMUMDISTANCE_KEY].IsNumber() == true)
			&& (json[MINIMUMDISTANCE_KEY].IsDouble() == true)) {
		minimumDistance = json[MINIMUMDISTANCE_KEY].GetDouble();
	} else {
		minimumDistance = std::numeric_limits<double>::quiet_NaN();
	}

	// rms
	if ((json.HasMember(RMS_KEY) == true)
			&& (json[RMS_KEY].IsNumber() == true)
			&& (json[RMS_KEY].IsDouble() == true)) {
		rms = json[RMS_KEY].GetDouble();
	} else {
		rms = std::numeric_limits<double>::quiet_NaN();
	}

	// quality
	if ((json.HasMember(QUALITY_KEY) == true)
			&& (json[QUALITY_KEY].IsString() == true)) {
		quality = std::string(json[QUALITY_KEY].GetString(),
									json[QUALITY_KEY].GetStringLength());
	} else {
		quality = "";
	}

	// baysian depth
	if ((json.HasMember(BAYESIANDEPTH_KEY) == true)
			&& (json[BAYESIANDEPTH_KEY].IsNumber() == true)
			&& (json[BAYESIANDEPTH_KEY].IsDouble() == true)) {
		bayesianDepth = json[BAYESIANDEPTH_KEY].GetDouble();
	} else {
		bayesianDepth = std::numeric_limits<double>::quiet_NaN();
	}

	// baysian range
	if ((json.HasMember(BAYESIANRANGE_KEY) == true)
			&& (json[BAYESIANRANGE_KEY].IsNumber() == true)
			&& (json[BAYESIANRANGE_KEY].IsDouble() == true)) {
		bayesianRange = json[BAYESIANRANGE_KEY].GetDouble();
	} else {
		bayesianRange = std::numeric_limits<double>::quiet_NaN();
	}

	// depth importance
	if ((json.HasMember(DEPTHIMPORTANCE_KEY) == true)
			&& (json[DEPTHIMPORTANCE_KEY].IsNumber() == true)
			&& (json[DEPTHIMPORTANCE_KEY].IsDouble() == true)) {
		depthImportance = json[DEPTHIMPORTANCE_KEY].GetDouble();
	} else {
		depthImportance = std::numeric_limits<double>::quiet_NaN();
	}

	// locator exit code
	if ((json.HasMember(LOCATOREXITCODE_KEY) == true)
			&& (json[LOCATOREXITCODE_KEY].IsString() == true)) {
		locatorExitCode = std::string(json[LOCATOREXITCODE_KEY].GetString(),
									json[LOCATOREXITCODE_KEY].GetStringLength());
	} else {
		locatorExitCode = "";
	}

	// error ellipse
	if ((json.HasMember(ERRORELLIPSE_KEY) == true)
			&& (json[ERRORELLIPSE_KEY].IsObject() == true)) {
		rapidjson::Value & ellipsevalue = json[ERRORELLIPSE_KEY];
		errorEllipse = processingformats::ErrorEllipse(ellipsevalue);
	} else {
		errorEllipse = processingformats::ErrorEllipse();
	}
}

LocationResult::LocationResult(const LocationResult & newResult) {
	hypocenter = newResult.hypocenter;

	// copy data
	supportingData.clear();
	for (int i = 0; i < static_cast<int>(newResult.supportingData.size()); i++) {
		supportingData.push_back(newResult.supportingData[i]);
	}

	id = newResult.id;
	source = newResult.source;
	numberOfAssociatedStations = newResult.numberOfAssociatedStations;
	numberOfAssociatedPhases = newResult.numberOfAssociatedPhases;
	numberOfUsedStations = newResult.numberOfUsedStations;
	numberOfUsedPhases = newResult.numberOfUsedPhases;
	gap = newResult.gap;
	secondaryGap = newResult.secondaryGap;
	minimumDistance = newResult.minimumDistance;
	rms = newResult.rms;
	quality = newResult.quality;
	bayesianDepth = newResult.bayesianDepth;
	bayesianRange = newResult.bayesianRange;
	depthImportance = newResult.depthImportance;
	locatorExitCode = newResult.locatorExitCode;
	errorEllipse = newResult.errorEllipse;
}

LocationResult::~LocationResult() {
	supportingData.clear();
}

rapidjson::Value & LocationResult::toJSON(
		rapidjson::Value &json,
		rapidjson::MemoryPoolAllocator<rapidjson::CrtAllocator> &allocator) {
	json.SetObject();

	// required values
	// hypo
	rapidjson::Value hypoValue(rapidjson::kObjectType);
	hypocenter.toJSON(hypoValue, allocator);
	json.AddMember(HYPOCENTER_KEY, hypoValue, allocator);

	// supporting data
	rapidjson::Value dataarray(rapidjson::kArrayType);
	if (supportingData.size() > 0) {
		for (int i = 0; i < static_cast<int>(supportingData.size()); i++) {
			rapidjson::Value datavalue(rapidjson::kObjectType);
			supportingData[i].toJSON(datavalue, allocator);
			dataarray.PushBack(datavalue, allocator);
		}
	}

	if (dataarray.Size() > 0) {
		// data
		json.AddMember(SUPPORTINGDATA_KEY, dataarray, allocator);
	}

	// Optional values
	// id
	if (id != "") {
		rapidjson::Value idValue;
		idValue.SetString(rapidjson::StringRef(id.c_str()), allocator);
		json.AddMember(ID_KEY, idValue, allocator);
	}

	// source
	if (source.isEmpty() == false) {
		rapidjson::Value sourceValue(rapidjson::kObjectType);
		source.toJSON(sourceValue, allocator);
		json.AddMember(SOURCE_KEY, sourceValue, allocator);
	}

	// associated stations
	if (numberOfAssociatedStations >= 0) {
		json.AddMember(ASSOCIATEDSTATIONS_KEY, numberOfAssociatedStations, allocator);
	}

	// associated phases
	if (numberOfAssociatedPhases >= 0) {
		json.AddMember(ASSOCIATEDPHASES_KEY, numberOfAssociatedPhases, allocator);
	}

	// used stations
	if (numberOfUsedStations >= 0) {
		json.AddMember(USEDSTATIONS_KEY, numberOfUsedStations, allocator);
	}

	// used phases
	if (numberOfUsedPhases >= 0) {
		json.AddMember(USEDPHASES_KEY, numberOfUsedPhases, allocator);
	}

	// gap
	if (std::isnan(gap) != true) {
		json.AddMember(GAP_KEY, gap, allocator);
	}

	// secondary gap
	if (std::isnan(secondaryGap) != true) {
		json.AddMember(SECONDARYGAP_KEY, secondaryGap, allocator);
	}

	// minimum distance
	if (std::isnan(minimumDistance) != true) {
		json.AddMember(MINIMUMDISTANCE_KEY, minimumDistance, allocator);
	}

	// rms
	if (std::isnan(rms) != true) {
		json.AddMember(RMS_KEY, rms, allocator);
	}

	// quality
	if (quality != "") {
		rapidjson::Value qualityValue;
		qualityValue.SetString(rapidjson::StringRef(quality.c_str()),
			allocator);
		json.AddMember(QUALITY_KEY, qualityValue, allocator);
	}

	// bayesian depth
	if (std::isnan(bayesianDepth) != true) {
		json.AddMember(BAYESIANDEPTH_KEY, bayesianDepth, allocator);
	}

	// bayesian range
	if (std::isnan(bayesianRange) != true) {
		json.AddMember(BAYESIANRANGE_KEY, bayesianRange, allocator);
	}

	// depth importance
	if (std::isnan(depthImportance) != true) {
		json.AddMember(DEPTHIMPORTANCE_KEY, depthImportance, allocator);
	}

	// locator exit code
	if (locatorExitCode != "") {
		rapidjson::Value locatorExitCodeValue;
		locatorExitCodeValue.SetString(rapidjson::StringRef(locatorExitCode.c_str()),
			allocator);
		json.AddMember(LOCATOREXITCODE_KEY, locatorExitCodeValue, allocator);
	}

	// error ellipse
	if (errorEllipse.isEmpty() == false) {
		rapidjson::Value errorEllipseValue(rapidjson::kObjectType);
		errorEllipse.toJSON(errorEllipseValue, allocator);
		json.AddMember(ERRORELLIPSE_KEY, errorEllipseValue, allocator);
	}

	return (json);
}

std::vector<std::string> LocationResult::getErrors() {
	std::vector<std::string> errorlist;

	// hypocenter
	if (hypocenter.isValid() != true) {
		std::vector<std::string> hypoErrors = hypocenter.getErrors();

		std::string errorString =
				"Hypocenter object did not validate in LocationResult class:";

		for (int i = 0; i < hypoErrors.size(); i++) {
			errorString += " " + hypoErrors[i];
		}

		// bad hypo
		errorlist.push_back(errorString);
	}

	// supportingData
	if (supportingData.size() > 0) {
		for (int i = 0; i < static_cast<int>(supportingData.size()); i++) {
			if (supportingData[i].isValid() != true) {
				std::vector<std::string> dataErrors = supportingData[i].getErrors();

				std::string errorString =
						"Invalid supporting data in LocationResult class:";

				for (int j = 0; j < dataErrors.size(); j++) {
					errorString += " " + dataErrors[j];
				}

				// bad pick
				errorlist.push_back(errorString);
			}
		}
	}

	// optional values
	// source
	if (source.isEmpty() == false) {
		if (source.isValid() != true) {
			std::vector<std::string> sourceErrors = source.getErrors();

			std::string errorString =
					"Source object did not validate in LocationResult class:";

			for (int i = 0; i < sourceErrors.size(); i++) {
				errorString += " " + sourceErrors[i];
			}

			// bad source
			errorlist.push_back(errorString);
		}
	}

	// gap
	if (std::isnan(gap) != true) {
		if ((gap < 0) || (gap > 360)) {
			errorlist.push_back("Invalid Gap in LocationResult class.");
		}
	}

	// secondaryGap
	if (std::isnan(secondaryGap) != true) {
		if ((secondaryGap < 0) || (secondaryGap > 360)) {
			errorlist.push_back("Invalid Secondary Gap in LocationResult class.");
		}
	}

	// minimumDistance
	if (std::isnan(minimumDistance) != true) {
		if (minimumDistance < 0) {
			errorlist.push_back("Invalid Minimum Distance in LocationResult class.");
		}
	}

	// errorEllipse
	if (errorEllipse.isEmpty() == false) {
		if (errorEllipse.isValid() != true) {
			std::vector<std::string> errorEllipseErrors = errorEllipse.getErrors();

			std::string errorString =
					"ErrorEllipse object did not validate in LocationResult class:";

			for (int i = 0; i < errorEllipseErrors.size(); i++) {
				errorString += " " + errorEllipseErrors[i];
			}

			// bad errorEllipse
			errorlist.push_back(errorString);
		}
	}

	// return the list of errors
	return (errorlist);
}

bool LocationResult::isEmpty() {
	if (hypocenter.isEmpty() == false) {
		return (false);
	}
	if (supportingData.size() > 0) {
		return (false);
	}
	if (id != "") {
		return (false);
	}
	if (source.isEmpty() == false) {
		return (false);
	}
	if (numberOfAssociatedStations > 0) {
		return (false);
	}
	if (numberOfAssociatedPhases > 0) {
		return (false);
	}
	if (numberOfUsedStations > 0) {
		return (false);
	}
	if (numberOfUsedPhases > 0) {
		return (false);
	}
	if (std::isnan(gap) != true) {
		return(false);
	}
	if (std::isnan(secondaryGap) != true) {
		return(false);
	}
	if (std::isnan(minimumDistance) != true) {
		return(false);
	}
	if (std::isnan(rms) != true) {
		return(false);
	}
	if (quality != "") {
		return (false);
	}
	if (std::isnan(bayesianDepth) != true) {
		return(false);
	}
	if (std::isnan(bayesianRange) != true) {
		return(false);
	}
	if (std::isnan(depthImportance) != true) {
		return(false);
	}
	if (locatorExitCode != "") {
		return (false);
	}
	if (errorEllipse.isEmpty() == false) {
		return (false);
	}

	return (true);
}

}  // namespace processingformats
