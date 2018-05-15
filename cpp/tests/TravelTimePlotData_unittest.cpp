#include "processing-formats.h"
#include <gtest/gtest.h>

#include <string>

// test data
#define TRAVELTIMEPLOTDATA_STRING "{\"Type\":\"TTPlotData\",\"Branches\":[{\"Phase\":\"Pg\",\"Samples\":[{\"Distance\":1.2,\"Observability\":0.34,\"StatisticalSpread\":1.5,\"TravelTime\":22.456},{\"Distance\":10.5,\"Observability\":1.63,\"StatisticalSpread\":2.1,\"TravelTime\":72.654}]},{\"Phase\":\"Sg\",\"Samples\":[{\"Distance\":3.2,\"Observability\":1.14,\"StatisticalSpread\":3.25,\"TravelTime\":132.456},{\"Distance\":100.5,\"Observability\":6.21,\"StatisticalSpread\":5.1,\"TravelTime\":542.654}]}],\"MaximumTravelTime\":12.5}"
#define MAXIMIUMTRAVELTIME 12.5
#define NUMBRANCHES 2
#define NUMSAMPLES 2
#define BRANCH_STRING "{\"Phase\":\"Pg\",\"Samples\":[{\"Distance\":1.2,\"Observability\":0.34,\"StatisticalSpread\":1.5,\"TravelTime\":22.456},{\"Distance\":10.5,\"Observability\":1.63,\"StatisticalSpread\":2.1,\"TravelTime\":72.654}]}"
#define PHASE1 "Pg"
#define DISTANCE1 1.2
#define TRAVELTIME1 22.456
#define STATISTICALSPREAD1 1.5
#define OBSERVABILITY1 .34
#define DISTANCE12 10.5
#define TRAVELTIME12 72.654
#define STATISTICALSPREAD12 2.1
#define OBSERVABILITY12 1.63
#define BRANCH2_STRING "{\"Phase\":\"Sg\",\"Samples\":[{\"Distance\":3.2,\"Observability\":1.14,\"StatisticalSpread\":3.25,\"TravelTime\":132.456},{\"Distance\":100.5,\"Observability\":6.21,\"StatisticalSpread\":5.1,\"TravelTime\":542.654}]}"
#define PHASE2 "Sg"
#define DISTANCE2 3.2
#define TRAVELTIME2 132.456
#define STATISTICALSPREAD2 3.25
#define OBSERVABILITY2 1.14
#define DISTANCE22 100.5
#define TRAVELTIME22 542.654
#define STATISTICALSPREAD22 5.1
#define OBSERVABILITY22 6.21

void checkdata(processingformats::TravelTimePlotData travelTimePlotDataObject,
				std::string testinfo) {
	// check travelTimePlotDataObject.maximumTravelTime
	ASSERT_EQ(MAXIMIUMTRAVELTIME, travelTimePlotDataObject.maximumTravelTime);

	// check branch data
	// check number of branches
	ASSERT_EQ(NUMBRANCHES, travelTimePlotDataObject.branches.size());

	// check travelTimePlotDataBranchObject.phase
	ASSERT_STREQ(std::string(PHASE1).c_str(),
					travelTimePlotDataObject.branches[0].phase.c_str());

	// check branch data
	// check number of samples
	ASSERT_EQ(NUMSAMPLES, travelTimePlotDataObject.branches[0].samples.size());

	// first sample
	// check travelTimePlotDataSampleObject.distance
	ASSERT_EQ(DISTANCE1, travelTimePlotDataObject.branches[0].samples[0].distance);

	// check travelTimePlotDataSampleObject.travelTime
	ASSERT_EQ(TRAVELTIME1, travelTimePlotDataObject.branches[0].samples[0].travelTime);

	// check travelTimePlotDataSampleObject.statisticalSpread
	ASSERT_EQ(STATISTICALSPREAD1,
				travelTimePlotDataObject.branches[0].samples[0].statisticalSpread);

	// check travelTimePlotDataSampleObject.observability
	ASSERT_EQ(OBSERVABILITY1, travelTimePlotDataObject.branches[0].samples[0].observability);

	// second sample
	// check travelTimePlotDataSampleObject.distance
	ASSERT_EQ(DISTANCE12, travelTimePlotDataObject.branches[0].samples[1].distance);

	// check travelTimePlotDataSampleObject.travelTime
	ASSERT_EQ(TRAVELTIME12, travelTimePlotDataObject.branches[0].samples[1].travelTime);

	// check travelTimePlotDataSampleObject.statisticalSpread
	ASSERT_EQ(STATISTICALSPREAD12,
				travelTimePlotDataObject.branches[0].samples[1].statisticalSpread);

	// check travelTimePlotDataSampleObject.observability
	ASSERT_EQ(OBSERVABILITY12,
				travelTimePlotDataObject.branches[0].samples[1].observability);

	// check travelTimePlotDataBranchObject.phase
	ASSERT_STREQ(std::string(PHASE2).c_str(),
					travelTimePlotDataObject.branches[1].phase.c_str());

	// check branch data
	// check number of samples
	ASSERT_EQ(NUMSAMPLES, travelTimePlotDataObject.branches[1].samples.size());

	// first sample
	// check travelTimePlotDataSampleObject.distance
	ASSERT_EQ(DISTANCE2, travelTimePlotDataObject.branches[1].samples[0].distance);

	// check travelTimePlotDataSampleObject.travelTime
	ASSERT_EQ(TRAVELTIME2, travelTimePlotDataObject.branches[1].samples[0].travelTime);

	// check travelTimePlotDataSampleObject.statisticalSpread
	ASSERT_EQ(STATISTICALSPREAD2,
				travelTimePlotDataObject.branches[1].samples[0].statisticalSpread);

	// check travelTimePlotDataSampleObject.observability
	ASSERT_EQ(OBSERVABILITY2, travelTimePlotDataObject.branches[1].samples[0].observability);

	// second sample
	// check travelTimePlotDataSampleObject.distance
	ASSERT_EQ(DISTANCE22, travelTimePlotDataObject.branches[1].samples[1].distance);

	// check travelTimePlotDataSampleObject.travelTime
	ASSERT_EQ(TRAVELTIME22, travelTimePlotDataObject.branches[1].samples[1].travelTime);

	// check travelTimePlotDataSampleObject.statisticalSpread
	ASSERT_EQ(STATISTICALSPREAD22,
				travelTimePlotDataObject.branches[1].samples[1].statisticalSpread);

	// check travelTimePlotDataSampleObject.observability
	ASSERT_EQ(OBSERVABILITY22,
				travelTimePlotDataObject.branches[1].samples[1].observability);
}

std::vector<processingformats::TravelTimePlotDataBranch> buildBranchData() {
	std::vector<processingformats::TravelTimePlotDataBranch> newBranchData;

	rapidjson::Document TravelTimePlotDataSampleDocument;

	processingformats::TravelTimePlotDataBranch travelTimePlotDataBranchObject(
			processingformats::FromJSONString(
					std::string(BRANCH_STRING),
					TravelTimePlotDataSampleDocument));

	processingformats::TravelTimePlotDataBranch travelTimePlotDataBranchObject2(
			processingformats::FromJSONString(
					std::string(BRANCH2_STRING),
					TravelTimePlotDataSampleDocument));

	newBranchData.push_back(travelTimePlotDataBranchObject);
	newBranchData.push_back(travelTimePlotDataBranchObject2);

	return (newBranchData);
}

// tests to see if TravelTimePlotData can successfully
// write json output
TEST(TravelTimePlotDataTest, WritesJSON) {
	processingformats::TravelTimePlotData travelTimePlotDataObject;

	// build TravelTimePlotData object
	travelTimePlotDataObject.maximumTravelTime = MAXIMIUMTRAVELTIME;
	travelTimePlotDataObject.branches = buildBranchData();

	// build json string
	rapidjson::Document TravelTimePlotDatadocument;
	std::string TravelTimePlotDatajson = processingformats::ToJSONString(
			travelTimePlotDataObject.toJSON(
					TravelTimePlotDatadocument,
					TravelTimePlotDatadocument.GetAllocator()));

	// read it back in
	rapidjson::Document TravelTimePlotDatadocument2;
	processingformats::TravelTimePlotData travelTimePlotDataObject2(
			processingformats::FromJSONString(TravelTimePlotDatajson,
												TravelTimePlotDatadocument2));

	// check data values
	checkdata(travelTimePlotDataObject2, "");
}

// tests to see if TravelTimePlotData can successfully
// read json output
TEST(TravelTimePlotDataTest, ReadsJSON) {

	// build TravelTimePlotData object
	rapidjson::Document TravelTimePlotDatadocument;
	processingformats::TravelTimePlotData travelTimePlotDataObject(
			processingformats::FromJSONString(
					std::string(TRAVELTIMEPLOTDATA_STRING),
					TravelTimePlotDatadocument));

	// check data values
	checkdata(travelTimePlotDataObject, "");
}

// tests to see if TravelTimePlotData can successfully
// be constructed
TEST(TravelTimePlotDataTest, Constructor) {
	// use constructor
	processingformats::TravelTimePlotData travelTimePlotDataObject(
			MAXIMIUMTRAVELTIME, buildBranchData());

	// check data values
	checkdata(travelTimePlotDataObject, "");
}

// tests to see if TravelTimePlotData can successfully
// validate
TEST(TravelTimePlotDataTest, Validate) {
	processingformats::TravelTimePlotData travelTimePlotDataObject;

	// build TravelTimePlotData object
	travelTimePlotDataObject.maximumTravelTime = MAXIMIUMTRAVELTIME;
	travelTimePlotDataObject.branches = buildBranchData();

	// successful validation
	bool result = travelTimePlotDataObject.isValid();

	// check return code
	ASSERT_EQ(result, true)<< "Tested for successful validation.";

	travelTimePlotDataObject.maximumTravelTime = std::numeric_limits<
			double>::quiet_NaN();

	result = travelTimePlotDataObject.isValid();

	// check return code
	ASSERT_EQ(result, false)<< "Tested for unsuccessful Validation";
}

