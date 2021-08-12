#include <LocationRequest.h>

#include <string>
#include <limits>
#include <vector>

// JSON Keys
#define TYPE_KEY "Type"
#define SOURCE_KEY "Source"
#define ID_KEY "ID"
#define EARTHMODEL_KEY "EarthModel"
#define SLABRESOLUTION_KEY "SlabResolution"
#define SOURCEORIGINTIME_KEY "SourceOriginTime"
#define SOURCELATITUDE_KEY "SourceLatitude"
#define SOURCELONGITUDE_KEY "SourceLongitude"
#define SOURCEDEPTH_KEY "SourceDepth"
#define INPUTDATA_KEY "InputData"
#define ISLOCATIONNEW_KEY "IsLocationNew"
#define ISLOCATIONHELD_KEY "IsLocationHeld"
#define ISDEPTHHELD_KEY "IsDepthHeld"
#define ISBAYESIANDEPTH_KEY "IsBayesianDepth"
#define BAYESIANDEPTH_KEY "BayesianDepth"
#define BAYESIANSPREAD_KEY "BayesianSpread"
#define USESVD_KEY "UseSVD"
#define OUTPUTDATA_KEY "OutputData"

namespace processingformats {
LocationRequest::LocationRequest() {
	id = "";
    source = processingformats::Source();
    type = "";
    earthModel = "ak135";
	slabResolution = "20spd";
    sourceLatitude = std::numeric_limits<double>::quiet_NaN();
    sourceLongitude = std::numeric_limits<double>::quiet_NaN();
    sourceOriginTime = std::numeric_limits<double>::quiet_NaN();
    sourceDepth = std::numeric_limits<double>::quiet_NaN();
    inputData.clear();
    isLocationNew = false;
    isLocationHeld = false;
    isDepthHeld = false;
    isBayesianDepth = false;
    bayesianDepth = std::numeric_limits<double>::quiet_NaN();
    bayesianSpread = std::numeric_limits<double>::quiet_NaN();
    useSVD = false;
    outputData = processingformats::LocationResult();
}

LocationRequest::LocationRequest(
      std::string newID,
      std::string newAgencyID,
      std::string newAuthor,
      std::string newType,
      std::string newLocType,
      std::string newEarthModel,
	  std::string newSlabResolution,
      double newSourceLatitude,
      double newSourceLongitude,
      double newSourceOriginTime,
      double newSourceDepth,
      std::vector<processingformats::Pick> newInputData,
      bool newIsLocationNew,
      bool newIsLocationHeld,
      bool newIsDepthHeld,
      bool newIsBayesianDepth,
      double newBayesianDepth,
      double newBayesianSpread,
      bool newUseSVD) {
	id = newID;
	LocationRequest::source = processingformats::Source(newAgencyID, newAuthor,
		newType);
	type = newLocType;
	sourceLatitude = newSourceLatitude;
	sourceLongitude = newSourceLongitude;
	sourceOriginTime = newSourceOriginTime;
	sourceDepth = newSourceDepth;
	inputData.clear();
	for (int i = 0; i < static_cast<int>(newInputData.size()); i++) {
		inputData.push_back(newInputData[i]);
	}
	if (newEarthModel != "") {
		earthModel = newEarthModel;
	}
	if (newSlabResolution != "") {
		slabResolution= newSlabResolution;
	}
	isLocationNew = newIsLocationNew;
	isLocationHeld = newIsLocationHeld;
	isDepthHeld = newIsDepthHeld;
	isBayesianDepth = newIsBayesianDepth;
	bayesianDepth = newBayesianDepth;
	bayesianSpread = newBayesianSpread;
	useSVD = newUseSVD;
	outputData = processingformats::LocationResult();
}

LocationRequest::LocationRequest(
      std::string newID,
      processingformats::Source newSource,
      std::string newLocType,
      std::string newEarthModel,
	  std::string newSlabResolution,
      double newSourceLatitude,
      double newSourceLongitude,
      double newSourceOriginTime,
      double newSourceDepth,
      std::vector<processingformats::Pick> newInputData,
      bool newIsLocationNew,
      bool newIsLocationHeld,
      bool newIsDepthHeld,
      bool newIsBayesianDepth,
      double newBayesianDepth,
      double newBayesianSpread,
      bool newUseSVD) {
	id = newID;
	LocationRequest::source = newSource;
	type = newLocType;
	sourceLatitude = newSourceLatitude;
	sourceLongitude = newSourceLongitude;
	sourceOriginTime = newSourceOriginTime;
	sourceDepth = newSourceDepth;
	inputData.clear();
	for (int i = 0; i < static_cast<int>(newInputData.size()); i++) {
		inputData.push_back(newInputData[i]);
	}
	if (newEarthModel != "") {
		earthModel = newEarthModel;
	}
	if (newSlabResolution != "") {
		slabResolution= newSlabResolution;
	}  
	isLocationNew = newIsLocationNew;
	isLocationHeld = newIsLocationHeld;
	isDepthHeld = newIsDepthHeld;
	isBayesianDepth = newIsBayesianDepth;
	bayesianDepth = newBayesianDepth;
	bayesianSpread = newBayesianSpread;
	useSVD = newUseSVD;
	outputData = processingformats::LocationResult();
}

LocationRequest::LocationRequest(rapidjson::Value &json) {
	// required values
	// type
	if ((json.HasMember(TYPE_KEY) == true)
			&& (json[TYPE_KEY].IsString() == true)) {
		type = std::string(json[TYPE_KEY].GetString(),
								json[TYPE_KEY].GetStringLength());
	} else {
		type = "";
	}

    // sourceLatitude
	if ((json.HasMember(SOURCELATITUDE_KEY) == true)
			&& (json[SOURCELATITUDE_KEY].IsNumber() == true)
			&& (json[SOURCELATITUDE_KEY].IsDouble() == true)) {
		sourceLatitude = json[SOURCELATITUDE_KEY].GetDouble();
	} else {
		sourceLatitude = std::numeric_limits<double>::quiet_NaN();
	}

	// sourceLongitude
	if ((json.HasMember(SOURCELONGITUDE_KEY) == true)
			&& (json[SOURCELONGITUDE_KEY].IsNumber() == true)
			&& (json[SOURCELONGITUDE_KEY].IsDouble() == true)) {
		sourceLongitude = json[SOURCELONGITUDE_KEY].GetDouble();
	} else {
		sourceLongitude = std::numeric_limits<double>::quiet_NaN();
	}

	// sourceDepth
	if ((json.HasMember(SOURCEDEPTH_KEY) == true)
			&& (json[SOURCEDEPTH_KEY].IsNumber() == true)
			&& (json[SOURCEDEPTH_KEY].IsDouble() == true)) {
		sourceDepth = json[SOURCEDEPTH_KEY].GetDouble();
	} else {
		sourceDepth = std::numeric_limits<double>::quiet_NaN();
	}

    // sourceOriginTime
	if ((json.HasMember(SOURCEORIGINTIME_KEY) == true)
			&& (json[SOURCEORIGINTIME_KEY].IsString() == true)) {
		sourceOriginTime = processingformats::ConvertISO8601ToEpochTime(
				std::string(json[SOURCEORIGINTIME_KEY].GetString(),
							json[SOURCEORIGINTIME_KEY].GetStringLength()));
	} else {
		sourceOriginTime = std::numeric_limits<double>::quiet_NaN();
	}

    // input data
	inputData.clear();
	if ((json.HasMember(INPUTDATA_KEY) == true)
			&& (json[INPUTDATA_KEY].IsArray() == true)) {
		rapidjson::Value dataarray;
		dataarray = json[INPUTDATA_KEY].GetArray();

		for (rapidjson::SizeType i = 0; i < dataarray.Size(); i++) {
			rapidjson::Value & datavalue = dataarray[i];

			processingformats::Pick newdata(datavalue);

			// add to vector
			inputData.push_back(newdata);
		}
	}

	// optional values
	// id
	// check type of id object
	if ((json.HasMember(ID_KEY) == true) && (json[ID_KEY].IsString() == true)) {
		// the Id *should* be a string
		id = std::string(json[ID_KEY].GetString(),
							json[ID_KEY].GetStringLength());
	} else if ((json.HasMember(ID_KEY) == true) 
		&& (json[ID_KEY].IsNumber() == true) 
		&& (json[ID_KEY].IsInt() == true)) {
		// but the ID *could* be an int, but we 
        // want it to be a string
		id = std::to_string(json[SOURCELONGITUDE_KEY].GetInt());
	} else {
		// any other type isn't a usable id
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

	// earthModel
	if ((json.HasMember(EARTHMODEL_KEY) == true)
			&& (json[EARTHMODEL_KEY].IsString() == true)) {
		earthModel = std::string(json[EARTHMODEL_KEY].GetString(),
								json[EARTHMODEL_KEY].GetStringLength());
	} else {
		earthModel = "ak135";
	}

	// slabResolution
	if ((json.HasMember(SLABRESOLUTION_KEY) == true)
			&& (json[SLABRESOLUTION_KEY].IsString() == true)) {
		slabResolution = std::string(json[SLABRESOLUTION_KEY].GetString(),
								json[SLABRESOLUTION_KEY].GetStringLength());
	} else {
		slabResolution = "20spd";
	}

  	// isLocationNew
	if ((json.HasMember(ISLOCATIONNEW_KEY) == true)
			&& (json[ISLOCATIONNEW_KEY].IsBool() == true)) {
		isLocationNew = json[ISLOCATIONNEW_KEY].GetBool();
	} else {
		isLocationNew = false;
	}

	// isLocationHeld
	if ((json.HasMember(ISLOCATIONHELD_KEY) == true)
			&& (json[ISLOCATIONHELD_KEY].IsBool() == true)) {
		isLocationHeld = json[ISLOCATIONHELD_KEY].GetBool();
	} else {
		isLocationHeld = false;
	}

	// isDepthHeld
	if ((json.HasMember(ISDEPTHHELD_KEY) == true)
			&& (json[ISDEPTHHELD_KEY].IsBool() == true)) {
		isDepthHeld = json[ISDEPTHHELD_KEY].GetBool();
	} else {
		isDepthHeld = false;
	}

	// isBayesianDepth
	if ((json.HasMember(ISBAYESIANDEPTH_KEY) == true)
			&& (json[ISBAYESIANDEPTH_KEY].IsBool() == true)) {
		isBayesianDepth = json[ISBAYESIANDEPTH_KEY].GetBool();
	} else {
		isBayesianDepth = false;
	}

	// bayesianDepth
	if ((json.HasMember(BAYESIANDEPTH_KEY) == true)
			&& (json[BAYESIANDEPTH_KEY].IsNumber() == true)
			&& (json[BAYESIANDEPTH_KEY].IsDouble() == true)) {
		bayesianDepth = json[BAYESIANDEPTH_KEY].GetDouble();
	} else {
		bayesianDepth = std::numeric_limits<double>::quiet_NaN();
	}

	// bayesianSpread
	if ((json.HasMember(BAYESIANSPREAD_KEY) == true)
			&& (json[BAYESIANSPREAD_KEY].IsNumber() == true)
			&& (json[BAYESIANSPREAD_KEY].IsDouble() == true)) {
		bayesianSpread = json[BAYESIANSPREAD_KEY].GetDouble();
	} else {
		bayesianSpread = std::numeric_limits<double>::quiet_NaN();
	}

	// useSVD
	if ((json.HasMember(USESVD_KEY) == true)
			&& (json[USESVD_KEY].IsBool() == true)) {
		useSVD = json[USESVD_KEY].GetBool();
	} else {
		useSVD = false;
	}

	// outputData
	if ((json.HasMember(OUTPUTDATA_KEY) == true)
			&& (json[OUTPUTDATA_KEY].IsObject() == true)) {
		rapidjson::Value & sourcevalue = json[OUTPUTDATA_KEY];
		outputData = processingformats::LocationResult(sourcevalue);
	} else {
		outputData = processingformats::LocationResult();
	}
}

LocationRequest::LocationRequest(const LocationRequest & newLocationRequest) {
	  id = newLocationRequest.id;
    source = newLocationRequest.source;
    type = newLocationRequest.type;
    sourceLatitude = newLocationRequest.sourceLatitude;
    sourceLongitude = newLocationRequest.sourceLongitude;
    sourceOriginTime = newLocationRequest.sourceOriginTime;
    sourceDepth = newLocationRequest.sourceDepth;

    // copy data
    inputData.clear();
    for (int i = 0; i < static_cast<int>(newLocationRequest.inputData.size());
        i++) {
		  inputData.push_back(newLocationRequest.inputData[i]);
	  }

    earthModel = newLocationRequest.earthModel;
	slabResolution = newLocationRequest.slabResolution;
    isLocationNew = newLocationRequest.isLocationNew;
    isLocationHeld = newLocationRequest.isLocationHeld;
    isDepthHeld = newLocationRequest.isDepthHeld;
    isBayesianDepth = newLocationRequest.isBayesianDepth;
    bayesianDepth = newLocationRequest.bayesianDepth;
    bayesianSpread = newLocationRequest.bayesianSpread;
    useSVD = newLocationRequest.useSVD;
    outputData = newLocationRequest.outputData;
}

LocationRequest::~LocationRequest() {
	inputData.clear();
}

rapidjson::Value & LocationRequest::toJSON(
		rapidjson::Value &json,
		rapidjson::MemoryPoolAllocator<rapidjson::CrtAllocator> &allocator) {
	json.SetObject();

	// required values
	// type
	if (type != "") {
		rapidjson::Value typeValue;
		typeValue.SetString(rapidjson::StringRef(type.c_str()),
								allocator);
		json.AddMember(TYPE_KEY, typeValue, allocator);
	}

	// sourceLatitude
	if (std::isnan(sourceLatitude) != true) {
		json.AddMember(SOURCELATITUDE_KEY, sourceLatitude, allocator);
	}

	// sourceLongitude
	if (std::isnan(sourceLongitude) != true) {
		json.AddMember(SOURCELONGITUDE_KEY, sourceLongitude, allocator);
  	}

	// sourceDepth
	if (std::isnan(sourceDepth) != true) {
		json.AddMember(SOURCEDEPTH_KEY, sourceDepth, allocator);
  	}

	// sourceOriginTime
	if (std::isnan(sourceOriginTime) != true) {
		std::string timestring = processingformats::ConvertEpochTimeToISO8601(
				sourceOriginTime);
		rapidjson::Value timevalue;
		timevalue.SetString(rapidjson::StringRef(timestring.c_str()),
							allocator);
		json.AddMember(SOURCEORIGINTIME_KEY, timevalue, allocator);
	}

  	// input data
	rapidjson::Value dataarray(rapidjson::kArrayType);
	if (inputData.size() > 0) {
		for (int i = 0; i < static_cast<int>(inputData.size()); i++) {
			rapidjson::Value datavalue(rapidjson::kObjectType);
			inputData[i].toJSON(datavalue, allocator);
			dataarray.PushBack(datavalue, allocator);
		}
	}

	if (dataarray.Size() > 0) {
		// data
		json.AddMember(INPUTDATA_KEY, dataarray, allocator);
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

	// earthModel
	rapidjson::Value earthModelValue;
	earthModelValue.SetString(rapidjson::StringRef(earthModel.c_str()),
							allocator);
	json.AddMember(EARTHMODEL_KEY, earthModelValue, allocator);

	// slabResolution
	rapidjson::Value slabResolutionValue;
	slabResolutionValue.SetString(rapidjson::StringRef(slabResolution.c_str()),
							allocator);
	json.AddMember(SLABRESOLUTION_KEY, slabResolutionValue, allocator);

	// isLocationNew
	json.AddMember(ISLOCATIONNEW_KEY, isLocationNew, allocator);

	// isLocationHeld
	json.AddMember(ISLOCATIONHELD_KEY, isLocationHeld, allocator);

	// isDepthHeld
	json.AddMember(ISDEPTHHELD_KEY, isDepthHeld, allocator);

	// isBayesianDepth
	json.AddMember(ISBAYESIANDEPTH_KEY, isBayesianDepth, allocator);

	// bayesianDepth
	if (std::isnan(bayesianDepth) != true) {
		json.AddMember(BAYESIANDEPTH_KEY, bayesianDepth, allocator);
  	}

	// bayesianSpread
	if (std::isnan(bayesianSpread) != true) {
		json.AddMember(BAYESIANSPREAD_KEY, bayesianSpread, allocator);
  	}

	// useSVD
	json.AddMember(USESVD_KEY, useSVD, allocator);

	// outputData
	if (outputData.isEmpty() == false) {
		rapidjson::Value outputDataValue(rapidjson::kObjectType);
		outputData.toJSON(outputDataValue, allocator);
		json.AddMember(OUTPUTDATA_KEY, outputDataValue, allocator);
	}

	return (json);
}

std::vector<std::string> LocationRequest::getErrors() {
	std::vector<std::string> errorlist;

	// check required data
	// sourceLatitude
	if (std::isnan(sourceLatitude) == true) {
		// sourceLatitude not found
		errorlist.push_back("No sourceLatitude in LocationRequest class.");
	} else if ((sourceLatitude < -90.0) || (sourceLatitude > 90.0)) {
		errorlist.push_back("Invalid sourceLatitude in LocationRequest class.");
	}

	// sourceLongitude
	if (std::isnan(sourceLongitude) == true) {
		// sourceLongitude not found
		errorlist.push_back("No sourceLongitude in LocationRequest class.");
	} else if ((sourceLongitude < -180.0) || (sourceLongitude > 180.0)) {
		errorlist.push_back("Invalid sourceLongitude in LocationRequest class.");
	}

	// sourceOriginTime
	if (std::isnan(sourceOriginTime) == true) {
		errorlist.push_back("sourceOriginTime is missing in LocationRequest class.");
	} else {
		try {
			if (processingformats::IsStringISO8601(
					processingformats::ConvertEpochTimeToISO8601(sourceOriginTime))
					== false) {
				errorlist.push_back(
						"sourceOriginTime did not validate in LocationRequest class.");
			}
		} catch (const std::exception & e) {
			errorlist.push_back(std::string(e.what()));
		}
	}

	// sourceDepth
	if (std::isnan(sourceDepth) == true) {
		// sourceDepth not found
		errorlist.push_back("No sourceDepth in LocationRequest class.");
	} else if ((sourceDepth < -100) || (sourceDepth > 1500)) {
		errorlist.push_back("Invalid sourceDepth in LocationRequest class.");
	}

  	// inputData
	if (inputData.size() > 0) {
		for (int i = 0; i < static_cast<int>(inputData.size()); i++) {
			if (inputData[i].isValid() != true) {
				std::vector<std::string> dataErrors = inputData[i].getErrors();

				std::string errorString =
						"Invalid input data in LocationRequest class:";

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
				"Source object did not validate in LocationRequest class:";

			for (int i = 0; i < sourceErrors.size(); i++) {
				errorString += " " + sourceErrors[i];
			}

			// bad source
			errorlist.push_back(errorString);
		}
	}

	// outputData
	if (outputData.isEmpty() == false) {
		if (outputData.isValid() != true) {
			std::vector<std::string> outputDataErrors = outputData.getErrors();

			std::string errorString =
					"outputData object did not validate in LocationRequest class:";

			for (int i = 0; i < outputDataErrors.size(); i++) {
				errorString += " " + outputDataErrors[i];
			}

			// bad outputData
			errorlist.push_back(errorString);
		}
	}

	// return the list of errors
	return (errorlist);
}
}  // namespace processingformats
