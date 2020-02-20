#include <LocationRequest.h>

#include <string>
#include <limits>
#include <vector>

// JSON Keys
#define TYPE_KEY "Type"
#define SOURCE_KEY "Source"
#define ID_KEY "ID"
#define EARTHMODEL_KEY "EarthModel"
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
    source = processingformats::source();
    type = "";
    earthModel = "";
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
  LocationRequest::source = processingformats::source(newAgencyID, newAuthor,
    newType);
	type = newType;
	sourceLatitude = newSourceLatitude;
  sourceLongitude = newSourceLongitude;
  sourceOriginTime = newSourceOriginTime;
  sourceDepth = newSourceDepth;
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
      processingformats::Source newSource
      std::string newLocType,
      std::string newEarthModel,
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
      bool newUseSVD)) {
	id = newID;
  LocationRequest::source = newSource;
	type = newType;
	sourceLatitude = newSourceLatitude;
  sourceLongitude = newSourceLongitude;
  sourceOriginTime = newSourceOriginTime;
  sourceDepth = newSourceDepth;
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

	// earthModel
	if ((json.HasMember(EARTHMODEL_KEY) == true)
			&& (json[EARTHMODEL_KEY].IsString() == true)) {
		earthModel = std::string(json[EARTHMODEL_KEY].GetString(),
								json[EARTHMODEL_KEY].GetStringLength());
	} else {
		earthModel = "";
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
    earthModel = newLocationRequest.earthModel;
    sourceLatitude = newLocationRequest.sourceLatitude;
    sourceLongitude = newLocationRequest.sourceLongitude;
    sourceOriginTime = newLocationRequest.sourceOriginTime;
    sourceDepth = newLocationRequest.sourceDepth;

    // copy data
    inputData.clear();
    for (int i = 0; i < static_cast<int>(newLocationRequest.inputData.size()); 
        i++) {
		  inputData.push_back(newResult.inputData[i]);
	  }

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

	// earthModel
	if (earthModel != "") {
		rapidjson::Value earthModelValue;
		earthModelValue.SetString(rapidjson::StringRef(earthModel.c_str()),
								allocator);
		json.AddMember(EARTHMODEL_KEY, earthModelValue, allocator);
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





	if (channel != "") {
		rapidjson::Value channelvalue;
		channelvalue.SetString(rapidjson::StringRef(channel.c_str()),
								allocator);
		json.AddMember(CHANNEL_KEY, channelvalue, allocator);
	}

	// location
	if (location != "") {
		rapidjson::Value locationvalue;
		locationvalue.SetString(rapidjson::StringRef(location.c_str()),
								allocator);
		json.AddMember(LOCATION_KEY, locationvalue, allocator);
	}

	// latitude
	if (std::isnan(latitude) != true) {
		json.AddMember(LATITUDE_KEY, latitude, allocator);
	}

	// longitude
	if (std::isnan(longitude) != true) {
		json.AddMember(LONGITUDE_KEY, longitude, allocator);
	}

	// elevation
	if (std::isnan(elevation) != true) {
		json.AddMember(ELEVATION_KEY, elevation, allocator);
	}

	return (json);
}

std::vector<std::string> LocationRequest::getErrors() {
	std::vector<std::string> errorlist;

	// check for required keys
	// Station
	if (station == "") {
		// empty Station
		errorlist.push_back("Empty Station in LocationRequest class.");
	}

	// Network
	if (network == "") {
		// empty network
		errorlist.push_back("Empty Network in LocationRequest class.");
	}

	// latitude
	if (std::isnan(latitude) != true) {
		if ((latitude < -90) || (latitude > 90)) {
			errorlist.push_back("Latitude in LocationRequest class not in the range of -90 to "
				"90 degrees.");
		}
	}

	// longitude
	if (std::isnan(longitude) != true) {
		if ((longitude < -180) || (longitude > 180)) {
			errorlist.push_back("Longitude in LocationRequest class not in the range of -180 "
				"to 180 degrees.");
		}
	}

	// elevation
	if (std::isnan(elevation) != true) {
		if ((elevation < -500) || (elevation > 8900)) {
			errorlist.push_back("Elevation in LocationRequest class not in the range of -500 "
				"to 8900 meters.");
		}
	}

	// since station, channel, network, and location are free text strings, no
	// further  validation is required.  channel and location are also optional
	// NOTE: Further validation COULD be done to confirm that values matched
	// seed standards.

	// return the list of errors
	return (errorlist);
}
}  // namespace processingformats
