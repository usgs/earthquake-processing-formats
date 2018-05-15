#include <TravelTimePlotDataBranch.h>

#include <string>
#include <limits>
#include <exception>
#include <vector>

// JSON Keys
#define PHASE_KEY "Phase"
#define SAMPLES_KEY "Samples"

namespace processingformats {

TravelTimePlotDataBranch::TravelTimePlotDataBranch() {
	phase = "";
	samples.clear();
}

TravelTimePlotDataBranch::TravelTimePlotDataBranch(
		std::string newPhase,
		std::vector<processingformats::TravelTimePlotDataSample> newSamples) {
	phase = newPhase;
	samples.clear();
	for (int i = 0; i < static_cast<int>(newSamples.size()); i++) {
		samples.push_back(newSamples[i]);
	}
}

TravelTimePlotDataBranch::TravelTimePlotDataBranch(rapidjson::Value &json) {
	// required values
	// phase
	if ((json.HasMember(PHASE_KEY) == true)
			&& (json[PHASE_KEY].IsString() == true)) {
		phase = std::string(json[PHASE_KEY].GetString(),
							json[PHASE_KEY].GetStringLength());
	} else {
		phase = "";
	}

	// samples
	samples.clear();
	if ((json.HasMember(SAMPLES_KEY) == true)
			&& (json[SAMPLES_KEY].IsArray() == true)) {
		rapidjson::Value dataarray;
		dataarray = json[SAMPLES_KEY].GetArray();

		for (rapidjson::SizeType i = 0; i < dataarray.Size(); i++) {
			// parse
			rapidjson::Value & datavalue = dataarray[i];
			processingformats::TravelTimePlotDataSample newSample(datavalue);

			// add to vector
			samples.push_back(newSample);
		}
	}
}

TravelTimePlotDataBranch::TravelTimePlotDataBranch(
		const TravelTimePlotDataBranch & newTravelTimePlotDataBranch) {
	phase = newTravelTimePlotDataBranch.phase;

	samples.clear();
	for (int i = 0;
			i < static_cast<int>(newTravelTimePlotDataBranch.samples.size());
			i++) {
		samples.push_back(newTravelTimePlotDataBranch.samples[i]);
	}
}

TravelTimePlotDataBranch::~TravelTimePlotDataBranch() {
}

rapidjson::Value & TravelTimePlotDataBranch::toJSON(
		rapidjson::Value &json,
		rapidjson::MemoryPoolAllocator<rapidjson::CrtAllocator> &allocator) {
	json.SetObject();

	// required values
	// phase
	if (phase != "") {
		rapidjson::Value phasevalue;
		phasevalue.SetString(rapidjson::StringRef(phase.c_str()), allocator);
		json.AddMember(PHASE_KEY, phasevalue, allocator);
	}

	// samples
	if (samples.size() > 0) {
		// build json array
		rapidjson::Value dataarray(rapidjson::kArrayType);

		for (int i = 0; i < static_cast<int>(samples.size()); i++) {
			rapidjson::Value samplevalue(rapidjson::kObjectType);
			samples[i].toJSON(samplevalue, allocator);
			dataarray.PushBack(samplevalue, allocator);
		}

		if (dataarray.Size() > 0) {
			json.AddMember(SAMPLES_KEY, dataarray, allocator);
		}
	}

	return (json);
}

std::vector<std::string> TravelTimePlotDataBranch::getErrors() {
	std::vector<std::string> errorlist;

	// required data
	// phase
	if (phase == "") {
		// phase empty
		errorlist.push_back("Empty Phase in TravelTimePlotDataBranch Class.");
	}

	// samples
	if (samples.size() == 0) {
		// no samples
		errorlist.push_back("No samples in TravelTimePlotDataBranch Class.");
	}

	return (errorlist);
}
}  // namespace processingformats
