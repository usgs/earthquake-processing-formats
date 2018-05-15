#include "processing-formats.h"
#include <gtest/gtest.h>

#include <string>

// test data
#define TRAVELTIMEDATA_STRING "{\"Type\":\"TTData\",\"LocationUseFlag\":true,\"DistanceDerivative\":1.2,\"DepthDerivative\":3.45,\"AssociationWeightFlag\":true,\"TeleseismicPhaseGroup\":\"P\",\"Phase\":\"Pg\",\"RayDerivative\":5.67,\"AuxiliaryPhaseGroup\":\"P\",\"Observability\":0.34,\"StatisticalSpread\":1.5,\"TravelTime\":22.456}"
#define PHASE "Pg"
#define TRAVELTIME 22.456
#define DISTANCEDERIVATIVE 1.2
#define DEPTHDERIVATIVE 3.45
#define RAYDERIVATIVE 5.67
#define STATISTICALSPREAD 1.5
#define OBSERVABILITY .34
#define TELESEISMICPHASEGROUP "P"
#define AUXILIARYPHASEGROUP "P"
#define LOCATIONUSEFLAG true
#define ASSOCIATIONWEIGHTFLAG true

void checkdata(processingformats::TravelTimeData travelTimeDataObject,
				std::string testinfo) {
	// check travelTimeDataObject.phase
	ASSERT_STREQ(std::string(PHASE).c_str(),
					travelTimeDataObject.phase.c_str());

	// check travelTimeDataObject.travelTime
	ASSERT_EQ(TRAVELTIME, travelTimeDataObject.travelTime);

	// check travelTimeDataObject.distanceDerivative
	ASSERT_EQ(DISTANCEDERIVATIVE, travelTimeDataObject.distanceDerivative);

	// check travelTimeDataObject.depthDerivative
	ASSERT_EQ(DEPTHDERIVATIVE, travelTimeDataObject.depthDerivative);

	// check travelTimeDataObject.rayDerivative
	ASSERT_EQ(RAYDERIVATIVE, travelTimeDataObject.rayDerivative);

	// check travelTimeDataObject.statisticalSpread
	ASSERT_EQ(STATISTICALSPREAD, travelTimeDataObject.statisticalSpread);

	// check travelTimeDataObject.observability
	ASSERT_EQ(OBSERVABILITY, travelTimeDataObject.observability);

	// check travelTimeDataObject.teleseismicPhaseGroup
	ASSERT_STREQ(std::string(TELESEISMICPHASEGROUP).c_str(),
					travelTimeDataObject.teleseismicPhaseGroup.c_str());

	// check travelTimeDataObject.auxiliaryPhaseGroup
	ASSERT_STREQ(std::string(AUXILIARYPHASEGROUP).c_str(),
					travelTimeDataObject.auxiliaryPhaseGroup.c_str());

	// check OriginObject.locationUseFlag
	ASSERT_EQ(LOCATIONUSEFLAG, travelTimeDataObject.locationUseFlag);

	// check travelTimeDataObject.associationWeightFlag
	ASSERT_EQ(ASSOCIATIONWEIGHTFLAG,
				travelTimeDataObject.associationWeightFlag);

}

// tests to see if TravelTimeData can successfully
// write json output
TEST(TravelTimeDataTest, WritesJSON) {
	processingformats::TravelTimeData travelTimeDataObject;

	// build TravelTimeData object
	travelTimeDataObject.phase = PHASE;
	travelTimeDataObject.travelTime = TRAVELTIME;
	travelTimeDataObject.distanceDerivative = DISTANCEDERIVATIVE;
	travelTimeDataObject.depthDerivative = DEPTHDERIVATIVE;
	travelTimeDataObject.rayDerivative = RAYDERIVATIVE;
	travelTimeDataObject.statisticalSpread = STATISTICALSPREAD;
	travelTimeDataObject.observability = OBSERVABILITY;
	travelTimeDataObject.teleseismicPhaseGroup = TELESEISMICPHASEGROUP;
	travelTimeDataObject.auxiliaryPhaseGroup = AUXILIARYPHASEGROUP;
	travelTimeDataObject.locationUseFlag = LOCATIONUSEFLAG;
	travelTimeDataObject.associationWeightFlag = ASSOCIATIONWEIGHTFLAG;

	// build json string
	rapidjson::Document TravelTimeDatadocument;
	std::string TravelTimeDatajson = processingformats::ToJSONString(
			travelTimeDataObject.toJSON(TravelTimeDatadocument,
										TravelTimeDatadocument.GetAllocator()));

	// read it back in
	rapidjson::Document TravelTimeDatadocument2;
	processingformats::TravelTimeData travelTimeDataObject2(
			processingformats::FromJSONString(TravelTimeDatajson,
												TravelTimeDatadocument2));

	// check data values
	checkdata(travelTimeDataObject2, "");
}

// tests to see if TravelTimeData can successfully
// read json output
TEST(TravelTimeDataTest, ReadsJSON) {

	// build TravelTimeData object
	rapidjson::Document TravelTimeDatadocument;
	processingformats::TravelTimeData travelTimeDataObject(
			processingformats::FromJSONString(
					std::string(TRAVELTIMEDATA_STRING),
					TravelTimeDatadocument));

	// check data values
	checkdata(travelTimeDataObject, "");
}

// tests to see if TravelTimeData can successfully
// be constructed
TEST(TravelTimeDataTest, Constructor) {
	// use constructor
	processingformats::TravelTimeData travelTimeDataObject(
			PHASE,
			TRAVELTIME,
			DISTANCEDERIVATIVE, DEPTHDERIVATIVE, RAYDERIVATIVE,
			STATISTICALSPREAD,
			OBSERVABILITY, TELESEISMICPHASEGROUP,
			AUXILIARYPHASEGROUP,
			LOCATIONUSEFLAG, ASSOCIATIONWEIGHTFLAG);

	// check data values
	checkdata(travelTimeDataObject, "");
}

// tests to see if TravelTimeData can successfully
// validate
TEST(TravelTimeDataTest, Validate) {
	processingformats::TravelTimeData travelTimeDataObject;

	// build TravelTimeData object
	travelTimeDataObject.phase = PHASE;
	travelTimeDataObject.travelTime = TRAVELTIME;
	travelTimeDataObject.distanceDerivative = DISTANCEDERIVATIVE;
	travelTimeDataObject.depthDerivative = DEPTHDERIVATIVE;
	travelTimeDataObject.rayDerivative = RAYDERIVATIVE;
	travelTimeDataObject.statisticalSpread = STATISTICALSPREAD;
	travelTimeDataObject.observability = OBSERVABILITY;
	travelTimeDataObject.teleseismicPhaseGroup = TELESEISMICPHASEGROUP;
	travelTimeDataObject.auxiliaryPhaseGroup = AUXILIARYPHASEGROUP;
	travelTimeDataObject.locationUseFlag = LOCATIONUSEFLAG;
	travelTimeDataObject.associationWeightFlag = ASSOCIATIONWEIGHTFLAG;

	// successful validation
	bool result = travelTimeDataObject.isValid();

	// check return code
	ASSERT_EQ(result, true)<< "Tested for successful validation.";

	travelTimeDataObject.distanceDerivative =
			std::numeric_limits<double>::quiet_NaN();

	result = travelTimeDataObject.isValid();

	// check return code
	ASSERT_EQ(result, false)<< "Tested for unsuccessful Validation";
}

