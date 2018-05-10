#include "processing-formats.h"
#include <gtest/gtest.h>

#include <string>

// test data
#define TRAVELTIMEDATABRANCH_STRING "{\"Phase\":\"Pg\",\"Samples\":[{\"Distance\":1.2,\"Observability\":0.34,\"StatisticalSpread\":1.5,\"TravelTime\":22.456},{\"Distance\":10.5,\"Observability\":1.63,\"StatisticalSpread\":2.1,\"TravelTime\":72.654}]}"
#define PHASE "Pg"
#define NUMSAMPLES 2
#define SAMPLEDATA_STRING "{\"Distance\":1.2,\"TravelTime\":22.456,\"Observability\":0.34,\"StatisticalSpread\":1.5}"
#define DISTANCE 1.2
#define TRAVELTIME 22.456
#define STATISTICALSPREAD 1.5
#define OBSERVABILITY 0.34
#define SAMPLEDATA2_STRING "{\"Distance\":10.5,\"TravelTime\":72.654,\"Observability\":1.63,\"StatisticalSpread\":2.1}"
#define DISTANCE2 10.5
#define TRAVELTIME2 72.654
#define STATISTICALSPREAD2 2.1
#define OBSERVABILITY2 1.63

void checkdata(
		processingformats::TravelTimePlotDataBranch travelTimePlotDataBranchObject,
		std::string testinfo) {
	// check travelTimePlotDataBranchObject.phase
	ASSERT_STREQ(std::string(PHASE).c_str(),
					travelTimePlotDataBranchObject.phase.c_str());

	// check travelTimePlotDataBranchObject.samples.size()
	ASSERT_EQ(NUMSAMPLES, travelTimePlotDataBranchObject.samples.size());

	// first sample
	// check travelTimePlotDataSampleObject.distance
	ASSERT_EQ(DISTANCE, travelTimePlotDataBranchObject.samples[0].distance);

	// check travelTimePlotDataSampleObject.travelTime
	ASSERT_EQ(TRAVELTIME, travelTimePlotDataBranchObject.samples[0].travelTime);

	// check travelTimePlotDataSampleObject.statisticalSpread
	ASSERT_EQ(STATISTICALSPREAD,
				travelTimePlotDataBranchObject.samples[0].statisticalSpread);

	// check travelTimePlotDataSampleObject.observability
	ASSERT_EQ(OBSERVABILITY,
				travelTimePlotDataBranchObject.samples[0].observability);

	// second sample
	// check travelTimePlotDataSampleObject.distance
	ASSERT_EQ(DISTANCE2, travelTimePlotDataBranchObject.samples[1].distance);

	// check travelTimePlotDataSampleObject.travelTime
	ASSERT_EQ(TRAVELTIME2,
				travelTimePlotDataBranchObject.samples[1].travelTime);

	// check travelTimePlotDataSampleObject.statisticalSpread
	ASSERT_EQ(STATISTICALSPREAD2,
				travelTimePlotDataBranchObject.samples[1].statisticalSpread);

	// check travelTimePlotDataSampleObject.observability
	ASSERT_EQ(OBSERVABILITY2,
				travelTimePlotDataBranchObject.samples[1].observability);
}

std::vector<processingformats::TravelTimePlotDataSample> buildSampleData() {
	std::vector<processingformats::TravelTimePlotDataSample> newSampleData;

	rapidjson::Document TravelTimePlotDataSampleDocument;

	processingformats::TravelTimePlotDataSample travelTimePlotDataBranchObject(
			processingformats::FromJSONString(
					std::string(SAMPLEDATA_STRING),
					TravelTimePlotDataSampleDocument));

	processingformats::TravelTimePlotDataSample travelTimePlotDataBranchObject2(
			processingformats::FromJSONString(
					std::string(SAMPLEDATA2_STRING),
					TravelTimePlotDataSampleDocument));

	newSampleData.push_back(travelTimePlotDataBranchObject);
	newSampleData.push_back(travelTimePlotDataBranchObject2);

	return (newSampleData);
}

// tests to see if TravelTimePlotDataBranch can successfully
// write json output
TEST(TravelTimePlotDataBranchTest, WritesJSON) {
	processingformats::TravelTimePlotDataBranch travelTimePlotDataBranchObject;

	// build TravelTimePlotDataBranch object
	travelTimePlotDataBranchObject.phase = PHASE;
	travelTimePlotDataBranchObject.samples = buildSampleData();

	// build json string
	rapidjson::Document TravelTimePlotDataBranchdocument;
	std::string TravelTimePlotDataBranchjson = processingformats::ToJSONString(
			travelTimePlotDataBranchObject.toJSON(
					TravelTimePlotDataBranchdocument,
					TravelTimePlotDataBranchdocument.GetAllocator()));

	// read it back in
	rapidjson::Document TravelTimePlotDataBranchdocument2;
	processingformats::TravelTimePlotDataBranch travelTimePlotDataBranchObject2(
			processingformats::FromJSONString(
					TravelTimePlotDataBranchjson,
					TravelTimePlotDataBranchdocument2));

	// check data values
	checkdata(travelTimePlotDataBranchObject2, "");
}

// tests to see if TravelTimePlotDataBranch can successfully
// read json output
TEST(TravelTimePlotDataBranchTest, ReadsJSON) {

	// build TravelTimePlotDataBranch object
	rapidjson::Document TravelTimePlotDataBranchdocument;
	processingformats::TravelTimePlotDataBranch travelTimePlotDataBranchObject(
			processingformats::FromJSONString(
					std::string(TRAVELTIMEDATABRANCH_STRING),
					TravelTimePlotDataBranchdocument));

	// check data values
	checkdata(travelTimePlotDataBranchObject, "");
}

// tests to see if TravelTimePlotDataBranch can successfully
// be constructed
TEST(TravelTimePlotDataBranchTest, Constructor) {
	// use constructor
	processingformats::TravelTimePlotDataBranch travelTimePlotDataBranchObject(
			PHASE, buildSampleData());

	// check data values
	checkdata(travelTimePlotDataBranchObject, "");
}

// tests to see if TravelTimePlotDataBranch can successfully
// validate
TEST(TravelTimePlotDataBranchTest, Validate) {
	processingformats::TravelTimePlotDataBranch travelTimePlotDataBranchObject;

	// build TravelTimePlotDataBranch object
	travelTimePlotDataBranchObject.phase = PHASE;
	travelTimePlotDataBranchObject.samples = buildSampleData();

	// successful validation
	bool result = travelTimePlotDataBranchObject.isValid();

	// check return code
	ASSERT_EQ(result, true)<< "Tested for successful validation.";

	travelTimePlotDataBranchObject.phase = "";

	result = travelTimePlotDataBranchObject.isValid();

	// check return code
	ASSERT_EQ(result, false)<< "Tested for unsuccessful Validation";
}

