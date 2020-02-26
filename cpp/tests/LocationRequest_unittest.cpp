#include "processing-formats.h"
#include <gtest/gtest.h>

#include <string>

#define LOCATIONREQUEST_STRING "{\"EarthModel\":\"AK135\",\"SourceLatitude\":40.3344,\"SourceLongitude\":-121.44,\"IsDepthHeld\":false,\"Type\":\"RayLoc\",\"SourceDepth\":32.44,\"IsLocationHeld\":false,\"BayesianSpread\":20.3,\"UseSVD\":true,\"BayesianDepth\":66.7,\"SourceOriginTime\":\"2015-12-28T21:32:24.017Z\",\"InputData\":[{\"Site\":{\"Station\":\"BOZ\",\"Channel\":\"BHZ\",\"Network\":\"US\",\"Location\":\"00\",\"Latitude\":45.59697,\"Longitude\":-111.62967,\"Elevation\":1589.0},\"PickedPhase\":\"P\",\"Use\":true,\"AssociatedPhase\":\"P\",\"Time\":\"2015-12-28T21:32:24.017Z\",\"Residual\":1.05,\"Source\":{\"Type\":\"Unknown\",\"AgencyID\":\"US\",\"Author\":\"TestAuthor\"},\"Weight\":2.65,\"Importance\":3.8,\"Azimuth\":21.5,\"Quality\":0.45,\"Affinity\":1.2,\"ID\":\"12GFH48776857\",\"LocatedPhase\":\"P\",\"Distance\":2.65}],\"IsLocationNew\":false,\"IsBayesianDepth\":true,\"ID\":\"12345678\",\"Source\":{\"Author\":\"TestAuthor\",\"AgencyID\":\"US\",\"Type\":\"Unknown\"}}" // NOLINT
#define ID "12345678"
#define AGENCYID "US"
#define AUTHOR "TestAuthor"
#define TYPE "Unknown"
#define LOCTYPE "RayLoc"
#define EARTHMODEL "AK135"
#define SOURCELATITUDE 40.3344
#define SOURCELONGITUDE -121.44
#define SOURCEORIGINTIME "2015-12-28T21:32:24.017Z"
#define SOURCEDEPTH 32.44
#define INPUTDDATA_STRING "{\"ID\":\"12GFH48776857\",\"Site\":{\"Station\":\"BOZ\",\"Channel\":\"BHZ\",\"Network\":\"US\",\"Location\":\"00\",\"Latitude\":45.59697,\"Longitude\":-111.62967,\"Elevation\":1589.0},\"Source\":{\"Author\":\"TestAuthor\",\"AgencyID\":\"US\",\"Type\":\"Unknown\"},\"Time\":\"2015-12-28T21:32:24.017Z\",\"Affinity\":1.2,\"Quality\":0.45,\"Use\":true,\"PickedPhase\":\"P\",\"AssociatedPhase\":\"P\",\"LocatedPhase\":\"P\",\"Residual\":1.05,\"Distance\":2.65,\"Azimuth\":21.5,\"Weight\":2.65,\"Importance\":3.8}" // NOLINT 
#define ISLOCATIONNEW false
#define ISLOCATIONHELD false
#define ISDEPTHHELD false
#define ISBAYESIANDEPTH true
#define BAYESIANDEPTH 66.7
#define BAYESIANSPREAD 20.3
#define USESVD true
#define OUTPUTDATA_STRING "{\"MinimumDistance\":2.14,\"NumberOfUsedStations\":33,\"BayesianRange\":20.3,\"ErrorEllipse\":{\"MaximumVerticalProjection\":1.984,\"EquivalentHorizontalRadius\":1.984,\"MaximumHorizontalProjection\":1.984,\"E0\":{\"Azimuth\":-121.44,\"Error\":40.3344,\"Dip\":32.44},\"E1\":{\"Azimuth\":22.64,\"Error\":12.5,\"Dip\":2.44},\"E2\":{\"Azimuth\":22.64,\"Error\":12.5,\"Dip\":2.44}},\"SupportingData\":[{\"Site\":{\"Station\":\"BMN\",\"Network\":\"LB\",\"Channel\":\"HHZ\",\"Location\":\"01\"},\"PickedPhase\":\"P\",\"Use\":true,\"AssociatedPhase\":\"P\",\"Time\":\"2015-12-28T21:32:24.017Z\",\"Residual\":1.05,\"Source\":{\"Type\":\"Unknown\",\"AgencyID\":\"US\",\"Author\":\"TestAuthor\"},\"Weight\":2.65,\"Importance\":3.8,\"Azimuth\":21.5,\"Quality\":0.45,\"Affinity\":1.2,\"ID\":\"12GFH48776857\",\"LocatedPhase\":\"P\",\"Distance\":2.65}],\"Hypocenter\":{\"LatitudeError\":12.5,\"DepthError\":2.44,\"TimeError\":1.984,\"Latitude\":40.3344,\"Time\":\"2015-12-28T21:32:24.017Z\",\"Longitude\":-121.44,\"Depth\":32.44,\"LongitudeError\":22.64},\"DepthImportance\":1.8,\"Quality\":\"A\",\"Gap\":33.67,\"BayesianDepth\":66.7,\"SecondaryGap\":33.67,\"RMS\":3.8,\"NumberOfAssociatedStations\":11,\"NumberOfAssociatedPhases\":22,\"NumberOfUsedPhases\":44}" // NOLINT
#define OUTPUTLATITUDE 40.3344

std::vector<processingformats::Pick> buildInputData() {
	std::vector<processingformats::Pick> newInputData;

	// pick ?need one more?
	rapidjson::Document supportingDataDocument;
	processingformats::Pick pickobject(
			processingformats::FromJSONString(std::string(INPUTDDATA_STRING),
					supportingDataDocument));
	newInputData.push_back(pickobject);

	return (newInputData);
}

void checkdata(processingformats::LocationRequest locationRequestObject, std::string testinfo) {
  // check id
	if (locationRequestObject.id.empty() != true) {
		std::string id = locationRequestObject.id;
		std::string expectedID = std::string(ID);
		ASSERT_STREQ(id.c_str(), expectedID.c_str())<< testinfo.c_str();
	}

	// check agencyId
	if (locationRequestObject.source.agencyId.empty() != true) {
		std::string sourceagencyid = locationRequestObject.source.agencyId;
		std::string expectedagencyid = std::string(AGENCYID);
		ASSERT_STREQ(sourceagencyid.c_str(),
			expectedagencyid.c_str())<< testinfo.c_str();
	}

	// check author
	if (locationRequestObject.source.author.empty() != true) {
		std::string sourceauthor = locationRequestObject.source.author;
		std::string expectedauthor = std::string(AUTHOR);
		ASSERT_STREQ(sourceauthor.c_str(),
			expectedauthor.c_str())<< testinfo.c_str();
	}

	// check type
	if (locationRequestObject.source.type.empty() != true) {
		std::string sourcetype = locationRequestObject.source.type;
		std::string expectedtype = std::string(TYPE);
		ASSERT_STREQ(sourcetype.c_str(),
			expectedtype.c_str())<< testinfo.c_str();
	}

  // check location type
	if (locationRequestObject.type.empty() != true) {
		std::string type = locationRequestObject.type;
		std::string expectedtype = std::string(LOCTYPE);
		ASSERT_STREQ(type.c_str(), expectedtype.c_str())<< testinfo.c_str();
	}

  // check earth model
	if (locationRequestObject.earthModel.empty() != true) {
		std::string earthModel = locationRequestObject.id;
		std::string expectedEarthModel = std::string(ID);
		ASSERT_STREQ(earthModel.c_str(), expectedEarthModel.c_str())<< testinfo.c_str();
	}

  // check sourceLatitude
	if (std::isnan(locationRequestObject.sourceLatitude) != true) {
		double latitude = locationRequestObject.sourceLatitude;
		double expectedlatitude = SOURCELATITUDE;
		ASSERT_EQ(latitude, expectedlatitude);
	}

	// check sourceLongitude
	if (std::isnan(locationRequestObject.sourceLongitude) != true) {
		double longitude = locationRequestObject.sourceLongitude;
		double expectedlongitude = SOURCELONGITUDE;
		ASSERT_EQ(longitude, expectedlongitude);
	}

	// check sourceOriginTime
	if (std::isnan(locationRequestObject.sourceOriginTime) != true) {
		double time = locationRequestObject.sourceOriginTime;
		double expectedtime = processingformats::ConvertISO8601ToEpochTime(
				std::string(SOURCEORIGINTIME));
		ASSERT_EQ(time, expectedtime)<< testinfo.c_str();
	}

	// check sourceDepth
	if (std::isnan(locationRequestObject.sourceDepth) != true) {
		double depth = locationRequestObject.sourceDepth;
		double expecteddepth = SOURCEDEPTH;
		ASSERT_EQ(depth, expecteddepth);
	}

	// check isLocationNew
  bool isLocationNew = locationRequestObject.isLocationNew;
	bool expectedIsLocationNew = ISLOCATIONNEW;
	ASSERT_EQ(isLocationNew, expectedIsLocationNew);

	// check isLocationHeld
  bool isLocationHeld = locationRequestObject.isLocationHeld;
	bool expectedIsLocationHeld = ISLOCATIONHELD;
	ASSERT_EQ(isLocationHeld, expectedIsLocationHeld);

	// check isDepthHeld
  bool isDepthHeld = locationRequestObject.isDepthHeld;
	bool expectedIsDepthHeld = ISDEPTHHELD;
	ASSERT_EQ(isDepthHeld, expectedIsDepthHeld);

	// check isBayesianDepth
  bool isBayesianDepth = locationRequestObject.isBayesianDepth;
	bool expectedIsBayesianDepth = ISBAYESIANDEPTH;
	ASSERT_EQ(isBayesianDepth, expectedIsBayesianDepth);  

  // check bayesianDepth
	if (std::isnan(locationRequestObject.bayesianDepth) != true) {
		double bayesianDepth = locationRequestObject.bayesianDepth;
		double expectedBayesianDepth = BAYESIANDEPTH;
		ASSERT_EQ(bayesianDepth, expectedBayesianDepth);
	}

	// check bayesianSpread
	if (std::isnan(locationRequestObject.bayesianSpread) != true) {
		double bayesianSpread = locationRequestObject.bayesianSpread;
		double expectedBayesianSpread = BAYESIANSPREAD;
		ASSERT_EQ(bayesianSpread, expectedBayesianSpread);
	}

	// check useSVD
  bool useSVD = locationRequestObject.useSVD;
	bool expectedUseSVD = USESVD;
	ASSERT_EQ(useSVD, expectedUseSVD);  

  // check outputData
  if (locationRequestObject.outputData.isEmpty() != true) {
    if (std::isnan(locationRequestObject.outputData.hypocenter.latitude) != true) {
      double hypocenterlatitude = locationRequestObject.outputData.hypocenter.latitude;
      double expectedlatitude = OUTPUTLATITUDE;
      ASSERT_EQ(hypocenterlatitude, expectedlatitude);
    }
  }
}

// tests to see if LocationRequest can successfully
// write json output
TEST(LocationRequestTest, WritesJSON) {
	processingformats::LocationRequest locationRequestObject;

	// build LocationRequest Object
	locationRequestObject.id = std::string(ID);
	locationRequestObject.source.agencyId = std::string(AGENCYID);
	locationRequestObject.source.author = std::string(AUTHOR);
	locationRequestObject.source.type = std::string(TYPE);
  locationRequestObject.type = std::string(LOCTYPE);	
  locationRequestObject.earthModel = std::string(EARTHMODEL);	
	locationRequestObject.sourceLatitude = SOURCELATITUDE;
	locationRequestObject.sourceLongitude = SOURCELONGITUDE;
	locationRequestObject.sourceDepth = SOURCEDEPTH;
	locationRequestObject.sourceOriginTime =
		processingformats::ConvertISO8601ToEpochTime(std::string(SOURCEORIGINTIME));	
	locationRequestObject.inputData = buildInputData();
	locationRequestObject.isLocationNew = ISLOCATIONNEW;
	locationRequestObject.isLocationHeld = ISLOCATIONHELD;
	locationRequestObject.isDepthHeld = ISDEPTHHELD;
	locationRequestObject.isBayesianDepth = ISBAYESIANDEPTH;
	locationRequestObject.bayesianDepth = BAYESIANDEPTH;
	locationRequestObject.bayesianSpread = BAYESIANSPREAD;
	locationRequestObject.useSVD = USESVD;

  rapidjson::Document ResultDocument;
	processingformats::LocationResult locationResultObject(
			processingformats::FromJSONString(std::string(OUTPUTDATA_STRING),
												ResultDocument));
	locationRequestObject.outputData = locationResultObject;
	

	// build json string
	rapidjson::Document LocationRequestdocument;
	std::string LocationRequestjson = processingformats::ToJSONString(
			locationRequestObject.toJSON(LocationRequestdocument, LocationRequestdocument.GetAllocator()));

	// read it back in
	rapidjson::Document LocationRequestdocument2;
	processingformats::LocationRequest locationRequestObject2(
			processingformats::FromJSONString(LocationRequestjson, LocationRequestdocument2));

	// check data values
	checkdata(locationRequestObject2, "");
}

// tests to see if LocationRequest can successfully
// read json output
TEST(LocationRequestTest, ReadsJSON) {
	// build LocationRequest Object
	rapidjson::Document LocationRequestdocument;
	processingformats::LocationRequest locationRequestObject(
			processingformats::FromJSONString(std::string(LOCATIONREQUEST_STRING),
												LocationRequestdocument));

	// check data values
	checkdata(locationRequestObject, "");
}

// tests to see if LocationRequest can successfully
// be constructed
TEST(LocationRequestTest, Constructors) {
	// use constructor
	processingformats::LocationRequest locationRequestObject(std::string(ID), 
      std::string(AGENCYID), std::string(AUTHOR), std::string(TYPE),
      std::string(LOCTYPE), std::string(EARTHMODEL), SOURCELATITUDE, SOURCELONGITUDE,
      processingformats::ConvertISO8601ToEpochTime(std::string(SOURCEORIGINTIME)),
      SOURCEDEPTH, buildInputData(), ISLOCATIONNEW, ISLOCATIONHELD, ISDEPTHHELD,
      ISBAYESIANDEPTH, BAYESIANDEPTH, BAYESIANSPREAD, USESVD);

	// check data values
	checkdata(locationRequestObject, "");

  // use alt constructor
	processingformats::LocationRequest locationRequestObject2(std::string(ID), 
      processingformats::Source(std::string(AGENCYID), std::string(AUTHOR), 
        std::string(TYPE)),
      std::string(LOCTYPE), std::string(EARTHMODEL), SOURCELATITUDE, SOURCELONGITUDE,
      processingformats::ConvertISO8601ToEpochTime(std::string(SOURCEORIGINTIME)),
      SOURCEDEPTH, buildInputData(), ISLOCATIONNEW, ISLOCATIONHELD, ISDEPTHHELD,
      ISBAYESIANDEPTH, BAYESIANDEPTH, BAYESIANSPREAD, USESVD);

	// check data values
	checkdata(locationRequestObject2, "");
}

// tests to see if LocationRequest can successfully
// validate
TEST(LocationRequestTest, Validate) {
	processingformats::LocationRequest locationRequestObject;

	// build LocationRequest Object
	locationRequestObject.id = std::string(ID);
	locationRequestObject.source.agencyId = std::string(AGENCYID);
	locationRequestObject.source.author = std::string(AUTHOR);
	locationRequestObject.source.type = std::string(TYPE);
  locationRequestObject.type = std::string(LOCTYPE);	
  locationRequestObject.earthModel = std::string(EARTHMODEL);	
	locationRequestObject.sourceLatitude = SOURCELATITUDE;
	locationRequestObject.sourceLongitude = SOURCELONGITUDE;
	locationRequestObject.sourceDepth = SOURCEDEPTH;
	locationRequestObject.sourceOriginTime =
		processingformats::ConvertISO8601ToEpochTime(std::string(SOURCEORIGINTIME));	
	locationRequestObject.inputData = buildInputData();
	locationRequestObject.isLocationNew = ISLOCATIONNEW;
	locationRequestObject.isLocationHeld = ISLOCATIONHELD;
	locationRequestObject.isDepthHeld = ISDEPTHHELD;
	locationRequestObject.isBayesianDepth = ISBAYESIANDEPTH;
	locationRequestObject.bayesianDepth = BAYESIANDEPTH;
	locationRequestObject.bayesianSpread = BAYESIANSPREAD;
	locationRequestObject.useSVD = USESVD;


	// successful validation
	bool result = locationRequestObject.isValid();

	// check return code
	ASSERT_EQ(result, true)<< "Tested for successful validation.";

	// build bad LocationRequest Object
	processingformats::LocationRequest badlocationRequestObject;
	badlocationRequestObject.id = std::string(ID);

	result = false;
	try {
		// call validation
		result = badlocationRequestObject.isValid();
	} catch (const std::exception &) {
		// don't care what the exception was
	}

	// check return code
	ASSERT_EQ(result, false)<< "Tested for unsuccessful validation.";
}



