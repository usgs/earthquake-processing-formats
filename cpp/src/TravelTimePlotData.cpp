#include <TravelTimePlotData.h>

#include <string>
#include <limits>
#include <exception>
#include <vector>

// JSON Keys
#define TYPE_KEY "Type"
#define MAXIMIUMTRAVELTIME_KEY "MaximumTravelTime"
#define BRANCHES_KEY "Branches"

namespace processingformats {

TravelTimePlotData::TravelTimePlotData() {
	type = "TTPlotData";
	maximumTravelTime = std::numeric_limits<double>::quiet_NaN();
	branches.clear();
}

TravelTimePlotData::TravelTimePlotData(
		double newMaximumTravelTime,
		std::vector<processingformats::TravelTimePlotDataBranch> newBranches) {
	type = "TTPlotData";
	maximumTravelTime = newMaximumTravelTime;
	branches.clear();
	for (int i = 0; i < static_cast<int>(newBranches.size()); i++) {
		branches.push_back(newBranches[i]);
	}
}

TravelTimePlotData::TravelTimePlotData(rapidjson::Value &json) {
	// required values
	// type
	if ((json.HasMember(TYPE_KEY) == true)
			&& (json[TYPE_KEY].IsString() == true)) {
		type = std::string(json[TYPE_KEY].GetString(),
							json[TYPE_KEY].GetStringLength());
	} else {
		type = "";
	}

	// travelTime
	if ((json.HasMember(MAXIMIUMTRAVELTIME_KEY) == true)
			&& (json[MAXIMIUMTRAVELTIME_KEY].IsNumber() == true)
			&& (json[MAXIMIUMTRAVELTIME_KEY].IsDouble() == true))
		maximumTravelTime = json[MAXIMIUMTRAVELTIME_KEY].GetDouble();
	else
		maximumTravelTime = std::numeric_limits<double>::quiet_NaN();

	// branches
	branches.clear();
	if ((json.HasMember(BRANCHES_KEY) == true)
			&& (json[BRANCHES_KEY].IsArray() == true)) {
		rapidjson::Value dataarray;
		dataarray = json[BRANCHES_KEY].GetArray();

		for (rapidjson::SizeType i = 0; i < dataarray.Size(); i++) {
			// parse
			rapidjson::Value & datavalue = dataarray[i];
			processingformats::TravelTimePlotDataBranch newBranch(datavalue);

			// add to vector
			branches.push_back(newBranch);
		}
	}
}

TravelTimePlotData::TravelTimePlotData(
		const TravelTimePlotData & newTravelTimePlotData) {
	type = "TTPlotData";
	maximumTravelTime = newTravelTimePlotData.maximumTravelTime;

	branches.clear();
	for (int i = 0; i < static_cast<int>(newTravelTimePlotData.branches.size());
			i++) {
		branches.push_back(newTravelTimePlotData.branches[i]);
	}
}

TravelTimePlotData::~TravelTimePlotData() {
}

rapidjson::Value & TravelTimePlotData::toJSON(
		rapidjson::Value &json,
		rapidjson::MemoryPoolAllocator<rapidjson::CrtAllocator> &allocator) {
	json.SetObject();

	// required values
	// type
	rapidjson::Value typevalue;
	typevalue.SetString(rapidjson::StringRef(type.c_str()), allocator);
	json.AddMember(TYPE_KEY, typevalue, allocator);

	// maximumTravelTime
	if (std::isnan(maximumTravelTime) != true)
		json.AddMember(MAXIMIUMTRAVELTIME_KEY, maximumTravelTime, allocator);

	// branches
	if (branches.size() > 0) {
		// build json array
		rapidjson::Value dataarray(rapidjson::kArrayType);

		for (int i = 0; i < static_cast<int>(branches.size()); i++) {
			rapidjson::Value samplevalue(rapidjson::kObjectType);
			branches[i].toJSON(samplevalue, allocator);
			dataarray.PushBack(samplevalue, allocator);
		}

		if (dataarray.Size() > 0) {
			json.AddMember(BRANCHES_KEY, dataarray, allocator);
		}
	}

	return (json);
}

std::vector<std::string> TravelTimePlotData::getErrors() {
	std::vector<std::string> errorlist;

	// required data
	// type
	if (type == "") {
		// type empty
		errorlist.push_back("Empty Type in TravelTimePlotData Class.");
	} else if (type != "TTPlotData") {
		// wrong type
		errorlist.push_back("Non-TTPlotData type in TravelTimePlotData Class.");
	}

	// maximumTravelTime
	if (std::isnan(maximumTravelTime) == true) {
		// maximumTravelTime not found
		errorlist.push_back("No Maximum Travel Time in TravelTimePlotData "
				"Class.");
	}

	// branches
	if (branches.size() == 0) {
		// no branches
		errorlist.push_back("No branches in TravelTimePlotData Class.");
	}

	return (errorlist);
}
}  // namespace processingformats
