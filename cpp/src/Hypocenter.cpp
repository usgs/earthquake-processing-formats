#include <Hypocenter.h>

#include <string>
#include <limits>
#include <vector>

// JSON Keys
#define LATITUDE_KEY "Latitude"
#define LONGITUDE_KEY "Longitude"
#define DEPTH_KEY "Depth"
#define TIME_KEY "Time"
#define LATITUDE_ERROR_KEY "LatitudeError"
#define LONGITUDE_ERROR_KEY "LongitudeError"
#define DEPTH_ERROR_KEY "DepthError"
#define TIME_ERROR_KEY "TimeError"

namespace processingformats {

Hypocenter::Hypocenter() {
	latitude = std::numeric_limits<double>::quiet_NaN();
	longitude = std::numeric_limits<double>::quiet_NaN();
	depth = std::numeric_limits<double>::quiet_NaN();
	time = std::numeric_limits<double>::quiet_NaN();
	latitudeError = std::numeric_limits<double>::quiet_NaN();
	longitudeError = std::numeric_limits<double>::quiet_NaN();
	depthError = std::numeric_limits<double>::quiet_NaN();
	timeError = std::numeric_limits<double>::quiet_NaN();
}

Hypocenter::Hypocenter(double newlatitude, double newlongitude, double newtime,
						double newdepth, double newlatitudeerror,
						double newlongitudeerror, double newtimeerror,
						double newdeptherror) {
	latitude = newlatitude;
	longitude = newlongitude;
	depth = newdepth;
	time = newtime;
	latitudeError = newlatitudeerror;
	longitudeError = newlongitudeerror;
	depthError = newdeptherror;
	timeError = newtimeerror;
}

Hypocenter::Hypocenter(rapidjson::Value &json) {
	// required values
	// latitude
	if ((json.HasMember(LATITUDE_KEY) == true)
			&& (json[LATITUDE_KEY].IsNumber() == true)
			&& (json[LATITUDE_KEY].IsDouble() == true))
		latitude = json[LATITUDE_KEY].GetDouble();
	else
		latitude = std::numeric_limits<double>::quiet_NaN();

	// longitude
	if ((json.HasMember(LONGITUDE_KEY) == true)
			&& (json[LONGITUDE_KEY].IsNumber() == true)
			&& (json[LONGITUDE_KEY].IsDouble() == true))
		longitude = json[LONGITUDE_KEY].GetDouble();
	else
		longitude = std::numeric_limits<double>::quiet_NaN();

	// time
	if ((json.HasMember(TIME_KEY) == true)
			&& (json[TIME_KEY].IsString() == true))
		time = processingformats::ConvertISO8601ToEpochTime(
				std::string(json[TIME_KEY].GetString(),
							json[TIME_KEY].GetStringLength()));
	else
		time = std::numeric_limits<double>::quiet_NaN();

	// depth
	if ((json.HasMember(DEPTH_KEY) == true)
			&& (json[DEPTH_KEY].IsNumber() == true)
			&& (json[DEPTH_KEY].IsDouble() == true))
		depth = json[DEPTH_KEY].GetDouble();
	else
		depth = std::numeric_limits<double>::quiet_NaN();

	// optional values
	// latitude error
	if ((json.HasMember(LATITUDE_ERROR_KEY) == true)
			&& (json[LATITUDE_ERROR_KEY].IsNumber() == true)
			&& (json[LATITUDE_ERROR_KEY].IsDouble() == true))
		latitudeError = json[LATITUDE_ERROR_KEY].GetDouble();
	else
		latitudeError = std::numeric_limits<double>::quiet_NaN();

	// longitude error
	if ((json.HasMember(LONGITUDE_ERROR_KEY) == true)
			&& (json[LONGITUDE_ERROR_KEY].IsNumber() == true)
			&& (json[LONGITUDE_ERROR_KEY].IsDouble() == true))
		longitudeError = json[LONGITUDE_ERROR_KEY].GetDouble();
	else
		longitudeError = std::numeric_limits<double>::quiet_NaN();

	// time error
	if ((json.HasMember(TIME_ERROR_KEY) == true)
			&& (json[TIME_ERROR_KEY].IsNumber() == true)
			&& (json[TIME_ERROR_KEY].IsDouble() == true))
		timeError = json[TIME_ERROR_KEY].GetDouble();
	else
		timeError = std::numeric_limits<double>::quiet_NaN();

	// depth error
	if ((json.HasMember(DEPTH_ERROR_KEY) == true)
			&& (json[DEPTH_ERROR_KEY].IsNumber() == true)
			&& (json[DEPTH_ERROR_KEY].IsDouble() == true))
		depthError = json[DEPTH_ERROR_KEY].GetDouble();
	else
		depthError = std::numeric_limits<double>::quiet_NaN();
}

Hypocenter::Hypocenter(const Hypocenter & newHypocenter) {
	latitude = newHypocenter.latitude;
	longitude = newHypocenter.longitude;
	depth = newHypocenter.depth;
	time = newHypocenter.time;
	latitudeError = newHypocenter.latitudeError;
	longitudeError = newHypocenter.longitudeError;
	depthError = newHypocenter.depthError;
	timeError = newHypocenter.timeError;
}

Hypocenter::~Hypocenter() {
}

rapidjson::Value & Hypocenter::toJSON(
		rapidjson::Value &json,
		rapidjson::MemoryPoolAllocator<rapidjson::CrtAllocator> &allocator) {
	json.SetObject();

	// required values
	// latitude
	if (std::isnan(latitude) != true)
		json.AddMember(LATITUDE_KEY, latitude, allocator);

	// longitude
	if (std::isnan(longitude) != true)
		json.AddMember(LONGITUDE_KEY, longitude, allocator);

	// time
	if (std::isnan(time) != true) {
		std::string timestring = processingformats::ConvertEpochTimeToISO8601(
				time);
		rapidjson::Value timevalue;
		timevalue.SetString(rapidjson::StringRef(timestring.c_str()),
							allocator);
		json.AddMember(TIME_KEY, timevalue, allocator);
	}

	// depth
	if (std::isnan(depth) != true)
		json.AddMember(DEPTH_KEY, depth, allocator);

	// optional values
	// latitude error
	if (std::isnan(latitudeError) != true)
		json.AddMember(LATITUDE_ERROR_KEY, latitudeError, allocator);

	// longitude error
	if (std::isnan(longitudeError) != true)
		json.AddMember(LONGITUDE_ERROR_KEY, longitudeError, allocator);

	// time error
	if (std::isnan(timeError) != true)
		json.AddMember(TIME_ERROR_KEY, timeError, allocator);

	// depth error
	if (std::isnan(depthError) != true)
		json.AddMember(DEPTH_ERROR_KEY, depthError, allocator);

	return (json);
}

std::vector<std::string> Hypocenter::getErrors() {
	std::vector<std::string> errorlist;

	// check required data
	// latitude
	if (std::isnan(latitude) == true) {
		// latitude not found
		errorlist.push_back("No Latitude in Hypocenter class.");
	} else if ((latitude < -90.0) || (latitude > 90.0)) {
		errorlist.push_back("Invalid Latitude in Hypocenter class.");
	}

	// longitude
	if (std::isnan(longitude) == true) {
		// longitude not found
		errorlist.push_back("No Longitude in Hypocenter class.");
	} else if ((longitude < -180.0) || (longitude > 180.0)) {
		errorlist.push_back("Invalid Longitude in Hypocenter class.");
	}

	// time
	if (std::isnan(time) == true) {
		errorlist.push_back("Time is missing in Hypocenter class.");
	} else {
		try {
			if (processingformats::IsStringISO8601(
					processingformats::ConvertEpochTimeToISO8601(time))
					== false) {
				errorlist.push_back(
						"Time did not validate in Hypocenter class.");
			}
		} catch (const std::exception & e) {
			errorlist.push_back(std::string(e.what()));
		}
	}

	// depth
	if (std::isnan(depth) == true) {
		// depth not found
		errorlist.push_back("No Depth in Hypocenter class.");
	} else if ((depth < -100) || (depth > 1500)) {
		errorlist.push_back("Invalid Depth in Hypocenter class.");
	}

	// optional keys
	// Currently no validation criteria for optional values LatitudeError,
	// LongitudeError, TimeError, and DepthError.

	// return the list of errors
	return (errorlist);
}
}  // namespace processingformats
