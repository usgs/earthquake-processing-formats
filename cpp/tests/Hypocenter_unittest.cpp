#include <processing-formats.h>
#include <gtest/gtest.h>

#include <string>

// test data
#define HYPOSTRING "{\"TimeError\":1.984,\"Time\":\"2015-12-28T21:32:24.017Z\",\"LongitudeError\":22.64,\"LatitudeError\":12.5,\"DepthError\":2.44,\"Latitude\":40.3344,\"Longitude\":-121.44,\"Depth\":32.44}" // NOLINT
#define LATITUDE 40.3344
#define LONGITUDE -121.44
#define TIME "2015-12-28T21:32:24.017Z"
#define DEPTH 32.44
#define LATITUDEERROR 12.5
#define LONGITUDEERROR 22.64
#define DEPTHERROR 2.44
#define TIMEERROR 1.984

void checkdata(processingformats::Hypocenter hypoobject, std::string testinfo) {
	// check latitude
	double hypolatitude = hypoobject.latitude;
	double expectedlatitude = LATITUDE;
	ASSERT_EQ(hypolatitude, expectedlatitude);

	// check longitude
	double hypolongitude = hypoobject.longitude;
	double expectedlongitude = LONGITUDE;
	ASSERT_EQ(hypolongitude, expectedlongitude);

	// check time
	double hypotime = hypoobject.time;
	double expectedtime = processingformats::ConvertISO8601ToEpochTime(
			std::string(TIME));
	ASSERT_EQ(hypotime, expectedtime);

	// check depth
	double hypodepth = hypoobject.depth;
	double expecteddepth = DEPTH;
	ASSERT_EQ(hypodepth, expecteddepth);

	// check latitude error
	double hypolatitudeerror = hypoobject.latitudeError;
	double expectedlatitudeerror = LATITUDEERROR;
	ASSERT_EQ(hypolatitudeerror, expectedlatitudeerror);

	// check longitude error
	double hypolongitudeerror = hypoobject.longitudeError;
	double expectedlongitudeerror = LONGITUDEERROR;
	ASSERT_EQ(hypolongitudeerror, expectedlongitudeerror);

	// check time error
	double hypotimeerror = hypoobject.timeError;
	double expectedtimeerror = TIMEERROR;
	ASSERT_EQ(hypotimeerror, expectedtimeerror);

	// check depth error
	double hypodeptherror = hypoobject.depthError;
	double expecteddeptherror = DEPTHERROR;
	ASSERT_EQ(hypodeptherror, expecteddeptherror);
}

// tests to see if Hypocenter can successfully
// write json output
TEST(HypoTest, WritesJSON) {
	processingformats::Hypocenter hypoobject;

	// build Hypocenter object
	hypoobject.latitude = LATITUDE;
	hypoobject.longitude = LONGITUDE;
	hypoobject.time = processingformats::ConvertISO8601ToEpochTime(
			std::string(TIME));
	hypoobject.depth = DEPTH;
	hypoobject.latitudeError = LATITUDEERROR;
	hypoobject.longitudeError = LONGITUDEERROR;
	hypoobject.timeError = TIMEERROR;
	hypoobject.depthError = DEPTHERROR;

	// build json string
	rapidjson::Document hypodocument;
	std::string hypojson = processingformats::ToJSONString(
			hypoobject.toJSON(hypodocument, hypodocument.GetAllocator()));

	// read it back in
	rapidjson::Document hypodocument2;
	processingformats::Hypocenter hypoobject2(
			processingformats::FromJSONString(hypojson, hypodocument2));

	// check data values
	checkdata(hypoobject2, "");
}

// tests to see if Hypocenter can successfully
// read json output
TEST(HypoTest, ReadsJSON) {
	// build associated object
	rapidjson::Document hypodocument;
	processingformats::Hypocenter hypoobject(
			processingformats::FromJSONString(std::string(HYPOSTRING),
					hypodocument));

	// check data values
	checkdata(hypoobject, "");
}

// tests to see if Hypocenter can successfully
// be constructed
TEST(HypoTest, Constructor) {
	// use constructor
	processingformats::Hypocenter hypoobject(LATITUDE, LONGITUDE,
			processingformats::ConvertISO8601ToEpochTime(std::string(TIME)),
			DEPTH,
			LATITUDEERROR, LONGITUDEERROR, TIMEERROR, DEPTHERROR);

	// check data values
	checkdata(hypoobject, "Tested Constructor");
}

// tests to see if Hypocenter can successfully
// be copy constructed
TEST(HypoTest, CopyConstructor) {
	// use constructor
	processingformats::Hypocenter fromhypoobject(LATITUDE, LONGITUDE,
			processingformats::ConvertISO8601ToEpochTime(std::string(TIME)),
			DEPTH,
			LATITUDEERROR, LONGITUDEERROR, TIMEERROR, DEPTHERROR);

	processingformats::Hypocenter hypoobject(fromhypoobject);

	// check data values
	checkdata(hypoobject, "");
}

// tests to see if Hypocenter can successfully
// validate
TEST(HypoTest, Validate) {
	processingformats::Hypocenter hypoobject;

	// build Hypocenter object
	hypoobject.latitude = LATITUDE;
	hypoobject.longitude = LONGITUDE;
	hypoobject.time = processingformats::ConvertISO8601ToEpochTime(
			std::string(TIME));
	hypoobject.depth = DEPTH;
	hypoobject.latitudeError = LATITUDEERROR;
	hypoobject.longitudeError = LONGITUDEERROR;
	hypoobject.timeError = TIMEERROR;
	hypoobject.depthError = DEPTHERROR;

	// successful validation
	bool result = hypoobject.isValid();

	// check return code
	ASSERT_EQ(result, true)<< "Tested for successful validation.";

	// build bad Hypocenter object
	processingformats::Hypocenter badhypoobject;
	badhypoobject.latitude = std::numeric_limits<double>::quiet_NaN();

	result = false;
	try {
		// call validation
		result = badhypoobject.isValid();
	} catch (const std::exception &) {
		// don't care what the exception was
	}

	// check return code
	ASSERT_EQ(result, false)<< "Tested for unsuccessful validation.";
}
