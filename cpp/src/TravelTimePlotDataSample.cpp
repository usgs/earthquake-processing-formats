#include <TravelTimePlotDataSample.h>

#include <string>
#include <limits>
#include <exception>
#include <vector>

// JSON Keys
#define DISTANCE_KEY "Distance"
#define TRAVELTIME_KEY "TravelTime"
#define STATISTICALSPREAD_KEY "StatisticalSpread"
#define OBSERVABILITY_KEY "Observability"

namespace processingformats {

TravelTimePlotDataSample::TravelTimePlotDataSample() {
	distance = std::numeric_limits<double>::quiet_NaN();
	travelTime = std::numeric_limits<double>::quiet_NaN();
	statisticalSpread = std::numeric_limits<double>::quiet_NaN();
	observability = std::numeric_limits<double>::quiet_NaN();
}

TravelTimePlotDataSample::TravelTimePlotDataSample(double newDistance,
													double newTravelTime,
													double newStatisticalSpread,
													double newObservability) {
	distance = newDistance;
	travelTime = newTravelTime;
	statisticalSpread = newStatisticalSpread;
	observability = newObservability;
}

TravelTimePlotDataSample::TravelTimePlotDataSample(rapidjson::Value &json) {
	// required values
	// distanceDerivative
	if ((json.HasMember(DISTANCE_KEY) == true)
			&& (json[DISTANCE_KEY].IsNumber() == true)
			&& (json[DISTANCE_KEY].IsDouble() == true))
		distance = json[DISTANCE_KEY].GetDouble();
	else
		distance = std::numeric_limits<double>::quiet_NaN();

	// travelTime
	if ((json.HasMember(TRAVELTIME_KEY) == true)
			&& (json[TRAVELTIME_KEY].IsNumber() == true)
			&& (json[TRAVELTIME_KEY].IsDouble() == true))
		travelTime = json[TRAVELTIME_KEY].GetDouble();
	else
		travelTime = std::numeric_limits<double>::quiet_NaN();

	// Optional values
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
}

TravelTimePlotDataSample::TravelTimePlotDataSample(
		const TravelTimePlotDataSample & newTravelTimePlotDataSample) {
	distance = newTravelTimePlotDataSample.distance;
	travelTime = newTravelTimePlotDataSample.travelTime;
	statisticalSpread = newTravelTimePlotDataSample.statisticalSpread;
	observability = newTravelTimePlotDataSample.observability;
}

TravelTimePlotDataSample::~TravelTimePlotDataSample() {
}

rapidjson::Value & TravelTimePlotDataSample::toJSON(
		rapidjson::Value &json,
		rapidjson::MemoryPoolAllocator<rapidjson::CrtAllocator> &allocator) {
	json.SetObject();

	// required values
	// distance
	if (std::isnan(distance) != true)
		json.AddMember(DISTANCE_KEY, distance, allocator);

	// travelTime
	if (std::isnan(travelTime) != true)
		json.AddMember(TRAVELTIME_KEY, travelTime, allocator);

	// optional values
	// statisticalSpread
	if (std::isnan(statisticalSpread) != true)
		json.AddMember(STATISTICALSPREAD_KEY, statisticalSpread, allocator);

	// observability
	if (std::isnan(observability) != true)
		json.AddMember(OBSERVABILITY_KEY, observability, allocator);

	return (json);
}

std::vector<std::string> TravelTimePlotDataSample::getErrors() {
	std::vector<std::string> errorlist;

	// required data
	// distance derivative
	if (std::isnan(distance) == true) {
		// distance derivative not found
		errorlist.push_back(
				"No Distance in TravelTimePlotDataSample Class.");
	}

	// travel time
	if (std::isnan(travelTime) == true) {
		// travel time not found
		errorlist.push_back(
				"No Travel Time in TravelTimePlotDataSample Class.");
	}

	return (errorlist);
}

}  // namespace processingformats
