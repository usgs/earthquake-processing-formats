#include "processing-formats.h"
#include <gtest/gtest.h>

#include <string>

// test data
#define TRAVELTIMESESSION_STRING "{\"ConvertTectonic\":true,\"ReturnBackBranches\":true,\"PhaseTypes\":[\"P\",\"S\",\"PDiff\"],\"SourceLatitude\":39.749444,\"ReturnAllPhases\":true,\"EarthModel\":\"AK135\",\"UseRSTT\":false,\"SourceLongitude\":-105.220305,\"IsPlot\":false,\"SourceDepth\":15.2}"

#define SOURCEDEPTH 15.2
#define EARTHMODEL "AK135"
#define NUMPHASES 3
#define PHASETYPE1 "P"
#define PHASETYPE2 "S"
#define PHASETYPE3 "PDiff"
#define SOURCELATITUDE 39.749444
#define SOURCELONGITUDE -105.220305
#define RETURNALLPHASES true
#define RETURNBACKBRANCHES true
#define CONVERTTECTONIC true
#define USERSTT false
#define ISPLOT false

void checkdata(processingformats::TravelTimeSession travelTimeSessionObject,
				std::string testinfo) {

	// check travelTimeSessionObject.sourceDepth
	ASSERT_EQ(SOURCEDEPTH, travelTimeSessionObject.sourceDepth);

	// check travelTimeSessionObject.earthModel
	ASSERT_STREQ(std::string(EARTHMODEL).c_str(),
					travelTimeSessionObject.earthModel.c_str());

	// check phase list
	// check number of phases
	ASSERT_EQ(NUMPHASES, travelTimeSessionObject.phaseTypes.size());

	ASSERT_STREQ(std::string(PHASETYPE1).c_str(),
					travelTimeSessionObject.phaseTypes[0].c_str());

	ASSERT_STREQ(std::string(PHASETYPE2).c_str(),
					travelTimeSessionObject.phaseTypes[1].c_str());

	ASSERT_STREQ(std::string(PHASETYPE3).c_str(),
					travelTimeSessionObject.phaseTypes[2].c_str());

	// check travelTimeSessionObject.sourceLatitude
	ASSERT_EQ(SOURCELATITUDE, travelTimeSessionObject.sourceLatitude);

	// check travelTimeSessionObject.sourceLongitude
	ASSERT_EQ(SOURCELONGITUDE, travelTimeSessionObject.sourceLongitude);

	// check travelTimeSessionObject.returnAllPhases
	ASSERT_EQ(RETURNALLPHASES, travelTimeSessionObject.returnAllPhases);

	// check travelTimeSessionObject.returnBackBranches
	ASSERT_EQ(RETURNBACKBRANCHES, travelTimeSessionObject.returnBackBranches);

	// check travelTimeSessionObject.convertTectonic
	ASSERT_EQ(CONVERTTECTONIC, travelTimeSessionObject.convertTectonic);

	// check travelTimeSessionObject.useRSTT
	ASSERT_EQ(USERSTT, travelTimeSessionObject.useRSTT);

	// check travelTimeSessionObject.isPlot
	ASSERT_EQ(ISPLOT, travelTimeSessionObject.isPlot);
}

std::vector<std::string> buildPhaseTypes() {
	std::vector<std::string> phaseTypes;

	phaseTypes.push_back(std::string(PHASETYPE1));
	phaseTypes.push_back(std::string(PHASETYPE2));
	phaseTypes.push_back(std::string(PHASETYPE3));

	return (phaseTypes);
}

// tests to see if TravelTimeSession can successfully
// write json output
TEST(TravelTimeSessionTest, WritesJSON) {
	processingformats::TravelTimeSession travelTimeSessionObject;
	rapidjson::Document TravelTimeSessiondocument;

	travelTimeSessionObject.sourceDepth = SOURCEDEPTH;
	travelTimeSessionObject.earthModel = EARTHMODEL;
	travelTimeSessionObject.phaseTypes = buildPhaseTypes();
	travelTimeSessionObject.sourceLatitude = SOURCELATITUDE;
	travelTimeSessionObject.sourceLongitude = SOURCELONGITUDE;
	travelTimeSessionObject.returnAllPhases = RETURNALLPHASES;
	travelTimeSessionObject.returnBackBranches = RETURNBACKBRANCHES;
	travelTimeSessionObject.convertTectonic = CONVERTTECTONIC;
	travelTimeSessionObject.useRSTT = USERSTT;
	travelTimeSessionObject.isPlot = ISPLOT;

	// build json string
	std::string TravelTimeSessionjson = processingformats::ToJSONString(
			travelTimeSessionObject.toJSON(
					TravelTimeSessiondocument,
					TravelTimeSessiondocument.GetAllocator()));

	// read it back in
	rapidjson::Document TravelTimeSessiondocument2;
	processingformats::TravelTimeSession travelTimeSessionObject2(
			processingformats::FromJSONString(TravelTimeSessionjson,
												TravelTimeSessiondocument2));

	// check data values
	checkdata(travelTimeSessionObject2, "");

}

// tests to see if TravelTimeSession can successfully
// read json output
TEST(TravelTimeSessionTest, ReadsJSON) {

	// build TravelTimeSession object
	rapidjson::Document TravelTimeSessiondocument;
	processingformats::TravelTimeSession travelTimeSessionObject(
			processingformats::FromJSONString(
					std::string(TRAVELTIMESESSION_STRING),
					TravelTimeSessiondocument));

	// check data values
	checkdata(travelTimeSessionObject, "");
}

// tests to see if TravelTimeSession can successfully
// be constructed
TEST(TravelTimeSessionTest, Constructor) {
	std::vector<processingformats::TravelTimePlotData> newPlotData;
	// use constructor
	processingformats::TravelTimeSession travelTimeSessionObject(
			SOURCEDEPTH, EARTHMODEL, buildPhaseTypes(), SOURCELATITUDE,
			SOURCELONGITUDE,
			RETURNALLPHASES,
			RETURNBACKBRANCHES, CONVERTTECTONIC, USERSTT, ISPLOT);

	// check data values
	checkdata(travelTimeSessionObject, "");
}

// tests to see if TravelTimeSession can successfully
// validate
TEST(TravelTimeSessionTest, Validate) {
	processingformats::TravelTimeSession travelTimeSessionObject;

	travelTimeSessionObject.sourceDepth = SOURCEDEPTH;
	travelTimeSessionObject.earthModel = EARTHMODEL;
	travelTimeSessionObject.phaseTypes = buildPhaseTypes();
	travelTimeSessionObject.sourceLatitude = SOURCELATITUDE;
	travelTimeSessionObject.sourceLongitude = SOURCELONGITUDE;
	travelTimeSessionObject.returnAllPhases = RETURNALLPHASES;
	travelTimeSessionObject.returnBackBranches = RETURNBACKBRANCHES;
	travelTimeSessionObject.convertTectonic = CONVERTTECTONIC;
	travelTimeSessionObject.useRSTT = USERSTT;
	travelTimeSessionObject.isPlot = ISPLOT;

	// successful validation
	bool result = travelTimeSessionObject.isValid();

	if (result == false) {
		std::vector<std::string> errorlist =
				travelTimeSessionObject.getErrors();

		for (int i = 0; i < errorlist.size(); i++) {
			printf("%s\n", errorlist[i].c_str());
		}
	}

	// check return code
	ASSERT_EQ(result, true)<< "Tested for successful validation.";

	travelTimeSessionObject.sourceDepth =
			std::numeric_limits<double>::quiet_NaN();
	travelTimeSessionObject.earthModel = EARTHMODEL;
	travelTimeSessionObject.phaseTypes = buildPhaseTypes();
	travelTimeSessionObject.sourceLatitude = SOURCELATITUDE;

	result = travelTimeSessionObject.isValid();

	// check return code
	ASSERT_EQ(result, false)<< "Tested for unsuccessful Validation";
}

