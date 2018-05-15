#include "processing-formats.h"
#include <gtest/gtest.h>

#include <string>

// test data
#define TRAVELTIMEPLOTDATASAMPLE_STRING "{\"Distance\":1.2,\"TravelTime\":22.456,\"Observability\":0.34,\"StatisticalSpread\":1.5}"
#define DISTANCE 1.2
#define TRAVELTIME 22.456
#define STATISTICALSPREAD 1.5
#define OBSERVABILITY 0.34

void checkdata(
		processingformats::TravelTimePlotDataSample travelTimePlotDataSampleObject,
		std::string testinfo) {
	// check travelTimePlotDataSampleObject.distance
	ASSERT_EQ(DISTANCE, travelTimePlotDataSampleObject.distance);

	// check travelTimePlotDataSampleObject.travelTime
	ASSERT_EQ(TRAVELTIME, travelTimePlotDataSampleObject.travelTime);

	// check travelTimePlotDataSampleObject.statisticalSpread
	ASSERT_EQ(STATISTICALSPREAD,
				travelTimePlotDataSampleObject.statisticalSpread);

	// check travelTimePlotDataSampleObject.observability
	ASSERT_EQ(OBSERVABILITY, travelTimePlotDataSampleObject.observability);
}

// tests to see if TravelTimePlotDataSample can successfully
// write json output
TEST(TravelTimePlotDataSampleTest, WritesJSON) {
	processingformats::TravelTimePlotDataSample travelTimePlotDataSampleObject;

	// build TravelTimePlotDataSample object
	travelTimePlotDataSampleObject.distance = DISTANCE;
	travelTimePlotDataSampleObject.travelTime = TRAVELTIME;
	travelTimePlotDataSampleObject.statisticalSpread = STATISTICALSPREAD;
	travelTimePlotDataSampleObject.observability = OBSERVABILITY;

	// build json string
	rapidjson::Document TravelTimePlotDataSampledocument;
	std::string TravelTimePlotDataSamplejson = processingformats::ToJSONString(
			travelTimePlotDataSampleObject.toJSON(
					TravelTimePlotDataSampledocument,
					TravelTimePlotDataSampledocument.GetAllocator()));

	// read it back in
	rapidjson::Document TravelTimePlotDataSampledocument2;
	processingformats::TravelTimePlotDataSample travelTimePlotDataSampleObject2(
			processingformats::FromJSONString(
					TravelTimePlotDataSamplejson,
					TravelTimePlotDataSampledocument2));

	// check data values
	checkdata(travelTimePlotDataSampleObject2, "");
}

// tests to see if TravelTimePlotDataSample can successfully
// read json output
TEST(TravelTimePlotDataSampleTest, ReadsJSON) {

	// build TravelTimePlotDataSample object
	rapidjson::Document TravelTimePlotDataSampledocument;
	processingformats::TravelTimePlotDataSample travelTimePlotDataSampleObject(
			processingformats::FromJSONString(
					std::string(TRAVELTIMEPLOTDATASAMPLE_STRING),
					TravelTimePlotDataSampledocument));

	// check data values
	checkdata(travelTimePlotDataSampleObject, "");
}

// tests to see if TravelTimePlotDataSample can successfully
// be constructed
TEST(TravelTimePlotDataSampleTest, Constructor) {
	// use constructor
	processingformats::TravelTimePlotDataSample travelTimePlotDataSampleObject(
			DISTANCE,
			TRAVELTIME,
			STATISTICALSPREAD,
			OBSERVABILITY);

	// check data values
	checkdata(travelTimePlotDataSampleObject, "");
}

// tests to see if TravelTimePlotDataSample can successfully
// validate
TEST(TravelTimePlotDataSampleTest, Validate) {
	processingformats::TravelTimePlotDataSample travelTimePlotDataSampleObject;

	// build TravelTimePlotDataSample object
	travelTimePlotDataSampleObject.distance = DISTANCE;
	travelTimePlotDataSampleObject.travelTime = TRAVELTIME;
	travelTimePlotDataSampleObject.statisticalSpread = STATISTICALSPREAD;
	travelTimePlotDataSampleObject.observability = OBSERVABILITY;

	// successful validation
	bool result = travelTimePlotDataSampleObject.isValid();

	// check return code
	ASSERT_EQ(result, true)<< "Tested for successful validation.";

	travelTimePlotDataSampleObject.distance = std::numeric_limits<
			double>::quiet_NaN();

	result = travelTimePlotDataSampleObject.isValid();

	// check return code
	ASSERT_EQ(result, false)<< "Tested for unsuccessful Validation";
}

