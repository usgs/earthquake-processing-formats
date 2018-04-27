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

	// distance
	if ((json.HasMember(DISTANCE_KEY) == true)
			&& (json[DISTANCE_KEY].IsNumber() == true)
			&& (json[DISTANCE_KEY].IsDouble() == true))
		distance = json[DISTANCE_KEY].GetDouble();
	else
		distance = std::numeric_limits<double>::quiet_NaN();

	// elevation
	if ((json.HasMember(ELEVATION_KEY) == true)
			&& (json[ELEVATION_KEY].IsNumber() == true)
			&& (json[ELEVATION_KEY].IsDouble() == true))
		elevation = json[ELEVATION_KEY].GetDouble();
	else
		elevation = std::numeric_limits<double>::quiet_NaN();

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

	// data
	data.clear();
	plotData.clear();
	if ((json.HasMember(DATA_KEY) == true)
			&& (json[DATA_KEY].IsArray() == true)) {
		rapidjson::Value dataarray;
		dataarray = json[DATA_KEY].GetArray();

		for (rapidjson::SizeType i = 0; i < dataarray.Size(); i++) {
			// parse
			rapidjson::Value & datavalue = dataarray[i];

			if ((datavalue.HasMember(TYPE_KEY) == true)
					&& (datavalue[TYPE_KEY].IsString() == true)) {
				std::string typeString = datavalue[TYPE_KEY].GetString();

				if (typeString == "TTData") {
					processingformats::TravelTimeData newData(datavalue);

					// add to vector
					data.push_back(newData);
				} else if (typeString == "TTPlotData") {
					processingformats::TravelTimePlotData newData(datavalue);

					// add to vector
					plotData.push_back(newData);
				}
			}
		}
	}
}

TravelTimeRequest::TravelTimeRequest(
		const TravelTimeRequest & newTravelTimeRequest) {
	type = newTravelTimeRequest.type;
	distance = newTravelTimeRequest.distance;
	elevation = newTravelTimeRequest.elevation;
	latitude = newTravelTimeRequest.latitude;
	longitude = newTravelTimeRequest.longitude;

	data.clear();
	for (int i = 0; i < static_cast<int>(newTravelTimeRequest.data.size());
			i++) {
		data.push_back(newTravelTimeRequest.data[i]);
	}

	plotData.clear();
	for (int i = 0; i < static_cast<int>(newTravelTimeRequest.plotData.size());
			i++) {
		plotData.push_back(newTravelTimeRequest.plotData[i]);
	}
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

	// distance
	if (std::isnan(distance) != true)
		json.AddMember(DISTANCE_KEY, distance, allocator);

	// elevation
	if (std::isnan(elevation) != true)
		json.AddMember(ELEVATION_KEY, elevation, allocator);

	// latitude
	if (std::isnan(latitude) != true)
		json.AddMember(LATITUDE_KEY, latitude, allocator);

	// longitude
	if (std::isnan(longitude) != true)
		json.AddMember(LONGITUDE_KEY, longitude, allocator);

	// data
	// build json array
	rapidjson::Value dataarray(rapidjson::kArrayType);
	if (type == "Standard") {
		if (data.size() > 0) {
			// build json array
			rapidjson::Value dataarray(rapidjson::kArrayType);

			for (int i = 0; i < static_cast<int>(data.size()); i++) {
				rapidjson::Value datavalue(rapidjson::kObjectType);
				data[i].toJSON(datavalue, allocator);
				dataarray.PushBack(datavalue, allocator);
			}
		}
	} else if (type == "Plot") {
		if (plotData.size() > 0) {
			// build json array
			rapidjson::Value dataarray(rapidjson::kArrayType);

			for (int i = 0; i < static_cast<int>(plotData.size()); i++) {
				rapidjson::Value datavalue(rapidjson::kObjectType);
				plotData[i].toJSON(datavalue, allocator);
				dataarray.PushBack(datavalue, allocator);
			}
		}
	} else if (type == "PlotStatistics") {
		if (plotData.size() > 0) {
			// build json array
			rapidjson::Value dataarray(rapidjson::kArrayType);

			for (int i = 0; i < static_cast<int>(plotData.size()); i++) {
				rapidjson::Value datavalue(rapidjson::kObjectType);
				plotData[i].toJSON(datavalue, allocator);
				dataarray.PushBack(datavalue, allocator);
			}
		}
	}

	if (dataarray.Size() > 0) {
		json.AddMember(DATA_KEY, dataarray, allocator);
	}

	return (json);
}

std::vector<std::string> TravelTimeRequest::getErrors() {
	std::vector<std::string> errorlist;

	// required data
	// type
	if (type == "") {
		// type empty
		errorlist.push_back("Empty Type in TravelTimeRequest Class.");
	} else if ((type != "Standard") || (type != "Plot")
			|| (type != "PlotStatistics")) {
		// wrong type
		errorlist.push_back("Unsupported type in TravelTimeRequest Class.");
	}

	// distance
	if (std::isnan(distance) == true) {
		// distance found
		errorlist.push_back("No Distance in TravelTimeRequest Class.");
	}

	// elevation
	if (std::isnan(elevation) == true) {
		// elevation not found
		errorlist.push_back("No Elevation in TravelTimeRequest Class.");
	}

	// optional data
	// latitude
	if (std::isnan(latitude) != true) {
		if ((latitude < -90) || (latitude > 90)) {
			errorlist.push_back("Invalid Latitude in TravelTimeRequest class.");
		}
	}

	// longitude
	if (std::isnan(longitude) != true) {
		if ((longitude < -180) || (longitude > 180)) {
			errorlist.push_back(
					"Invalid Longitude in TravelTimeRequest class.");
		}
	}

	if ((type != "") && (type == "Standard")) {
		if (data.size() > 0) {
			for (int i = 0; i < static_cast<int>(data.size()); i++) {
				if (data[i].isValid() == false) {
					errorlist.push_back(
							"Invalid TravelTimeData in TravelTimeRequest Class "
							"of type Standard.");
					break;
				}
			}
		}
	} else if ((type != "") && (type == "Plot")) {
		if (plotData.size() > 0) {
			for (int i = 0; i < static_cast<int>(plotData.size()); i++) {
				if (plotData[i].isValid() == false) {
					errorlist.push_back(
							"Invalid TravelTimeData in TravelTimeRequest Class "
							"of type Plot.");
					break;
				}
			}
		}
	} else if ((type != "") && (type == "PlotStatistics")) {
		if (plotData.size() > 0) {
			for (int i = 0; i < static_cast<int>(plotData.size()); i++) {
				if (plotData[i].isValid() == false) {
					errorlist.push_back(
							"Invalid TravelTimeData in TravelTimeRequest Class "
							"of type PlotStatistics.");
					break;
				}
			}
		}
	}

	return (errorlist);
}
}  // namespace processingformats
