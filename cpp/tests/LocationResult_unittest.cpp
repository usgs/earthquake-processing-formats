#include "processing-formats.h"
#include <gtest/gtest.h>

#include <string>

#define LOCATIONRESULT_STRING "{\"MinimumDistance\":2.14,\"NumberOfUsedStations\":33,\"BayesianRange\":20.3,\"ErrorEllipse\":{\"MaximumVerticalProjection\":1.984,\"EquivalentHorizontalRadius\":1.984,\"MaximumHorizontalProjection\":1.984,\"E0\":{\"Azimuth\":-121.44,\"Error\":40.3344,\"Dip\":32.44},\"E1\":{\"Azimuth\":22.64,\"Error\":12.5,\"Dip\":2.44},\"E2\":{\"Azimuth\":22.64,\"Error\":12.5,\"Dip\":2.44}},\"SupportingData\":[{\"Site\":{\"Station\":\"BOZ\",\"Channel\":\"BHZ\",\"Network\":\"US\",\"Location\":\"00\",\"Latitude\":45.59697,\"Longitude\":-111.62967,\"Elevation\":1589.0},\"PickedPhase\":\"P\",\"Use\":true,\"AssociatedPhase\":\"P\",\"Time\":\"2015-12-28T21:32:24.017Z\",\"Residual\":1.05,\"Source\":{\"Type\":\"Unknown\",\"AgencyID\":\"US\",\"Author\":\"TestAuthor\"},\"Weight\":2.65,\"Importance\":3.8,\"Azimuth\":21.5,\"Quality\":0.45,\"Affinity\":1.2,\"ID\":\"12GFH48776857\",\"LocatedPhase\":\"P\",\"Distance\":2.65}],\"Hypocenter\":{\"LatitudeError\":12.5,\"DepthError\":2.44,\"TimeError\":1.984,\"Latitude\":40.3344,\"Time\":\"2015-12-28T21:32:24.017Z\",\"Longitude\":-121.44,\"Depth\":32.44,\"LongitudeError\":22.64},\"DepthImportance\":1.8,\"LocatorExitCode\":\"Success\",\"Quality\":\"A\",\"Gap\":33.67,\"BayesianDepth\":66.7,\"SecondaryGap\":33.67,\"RMS\":3.8,\"NumberOfAssociatedStations\":11,\"NumberOfAssociatedPhases\":22,\"NumberOfUsedPhases\":44,\"ID\":\"12345678\",\"Source\":{\"Author\":\"TestAuthor\",\"AgencyID\":\"US\",\"Type\":\"Unknown\"}}" // NOLINT
#define ID "12345678"
#define AGENCYID "US"
#define AUTHOR "TestAuthor"
#define TYPE "Unknown"
#define LATITUDE 40.3344
#define LONGITUDE -121.44
#define TIME "2015-12-28T21:32:24.017Z"
#define DEPTH 32.44
#define LATITUDEERROR 12.5
#define LONGITUDEERROR 22.64
#define DEPTHERROR 2.44
#define TIMEERROR 1.984
#define NUMASSOCIATEDSTATIONS 11
#define NUMASSOCIATEDPHASES 22
#define NUMUSEDSTATIONS 33
#define NUMUSEDPHASES 44
#define SUPPORTINGDATASTRING "{\"ID\":\"12GFH48776857\",\"Site\":{\"Station\":\"BOZ\",\"Channel\":\"BHZ\",\"Network\":\"US\",\"Location\":\"00\",\"Latitude\":45.59697,\"Longitude\":-111.62967,\"Elevation\":1589.0},\"Source\":{\"Author\":\"TestAuthor\",\"AgencyID\":\"US\",\"Type\":\"Unknown\"},\"Time\":\"2015-12-28T21:32:24.017Z\",\"Affinity\":1.2,\"Quality\":0.45,\"Use\":true,\"PickedPhase\":\"P\",\"AssociatedPhase\":\"P\",\"LocatedPhase\":\"P\",\"Residual\":1.05,\"Distance\":2.65,\"Azimuth\":21.5,\"Weight\":2.65,\"Importance\":3.8}" // NOLINT
#define GAP 33.67
#define SECONDARYGAP 33.67
#define MINIMUMDISTANCE 2.14
#define RMS 3.8
#define QUALITY "A"
#define BAYESIANDEPTH 66.7
#define BAYESIANRANGE 20.3
#define DEPTHIMPORTANCE 1.8
#define LOCATOREXITCODE "Success"
#define E0ERROR 40.3344
#define E0AZIMUTH -121.44
#define E0DIP 32.44
#define E1ERROR 12.5
#define E1AZIMUTH 22.64
#define E1DIP 2.44
#define E2ERROR 12.5
#define E2AZIMUTH 22.64
#define E2DIP 2.44
#define MAXIMUMHORIZONTALPROJECTION 1.984
#define MAXIMUMVERTICALPROJECTION 1.984
#define EQUIVALENTHORIZONTALRADIUS 1.984

std::vector<processingformats::Pick> buildSupportingData() {
	std::vector<processingformats::Pick> newSupportingData;

	// pick ?need one more?
	rapidjson::Document supportingDataDocument;
	processingformats::Pick pickobject(
			processingformats::FromJSONString(std::string(SUPPORTINGDATASTRING),
					supportingDataDocument));
	newSupportingData.push_back(pickobject);

	return (newSupportingData);
}

void checkdata(processingformats::LocationResult locationResultObject, std::string testinfo) {
	// check id
	if (locationResultObject.id.empty() != true) {
		std::string pickid = locationResultObject.id;
		std::string expectedid = std::string(ID);
		ASSERT_STREQ(pickid.c_str(), expectedid.c_str())<< testinfo.c_str();
	}

	// check agencyId
	if (locationResultObject.source.agencyId.empty() != true) {
		std::string sourceagencyid = locationResultObject.source.agencyId;
		std::string expectedagencyid = std::string(AGENCYID);
		ASSERT_STREQ(sourceagencyid.c_str(),
			expectedagencyid.c_str())<< testinfo.c_str();
	}

	// check author
	if (locationResultObject.source.author.empty() != true) {
		std::string sourceauthor = locationResultObject.source.author;
		std::string expectedauthor = std::string(AUTHOR);
		ASSERT_STREQ(sourceauthor.c_str(),
			expectedauthor.c_str())<< testinfo.c_str();
	}

	// check type
	if (locationResultObject.source.type.empty() != true) {
		std::string sourcetype = locationResultObject.source.type;
		std::string expectedtype = std::string(TYPE);
		ASSERT_STREQ(sourcetype.c_str(),
			expectedtype.c_str())<< testinfo.c_str();
	}

	// check hypocenter
	// check latitude
	if (std::isnan(locationResultObject.hypocenter.latitude) != true) {
		double hypocenterlatitude = locationResultObject.hypocenter.latitude;
		double expectedlatitude = LATITUDE;
		ASSERT_EQ(hypocenterlatitude, expectedlatitude);
	}

	// check longitude
	if (std::isnan(locationResultObject.hypocenter.longitude) != true) {
		double hypocenterlongitude = locationResultObject.hypocenter.longitude;
		double expectedlongitude = LONGITUDE;
		ASSERT_EQ(hypocenterlongitude, expectedlongitude);
	}

	// check time
	if (std::isnan(locationResultObject.hypocenter.time) != true) {
		double hypotime = locationResultObject.hypocenter.time;
		double expectedtime = processingformats::ConvertISO8601ToEpochTime(
				std::string(TIME));
		ASSERT_EQ(hypotime, expectedtime)<< testinfo.c_str();
	}

	// check depth
	if (std::isnan(locationResultObject.hypocenter.depth) != true) {
		double hypocenterdepth = locationResultObject.hypocenter.depth;
		double expecteddepth = DEPTH;
		ASSERT_EQ(hypocenterdepth, expecteddepth);
	}

	// check latitudeError
	if (std::isnan(locationResultObject.hypocenter.latitudeError) != true) {
		double hypocenterlatitudeError = locationResultObject.hypocenter.latitudeError;
		double expectedlatitudeError = LATITUDEERROR;
		ASSERT_EQ(hypocenterlatitudeError, expectedlatitudeError);
	}

	// check longitudeError
	if (std::isnan(locationResultObject.hypocenter.longitudeError) != true) {
		double hypocenterlongitudeError = locationResultObject.hypocenter.longitudeError;
		double expectedlongitudeError = LONGITUDEERROR;
		ASSERT_EQ(hypocenterlongitudeError, expectedlongitudeError);
	}

	// check timeError
	if (std::isnan(locationResultObject.hypocenter.timeError) != true) {
		double hypotimeError = locationResultObject.hypocenter.timeError;
		double expectedtimeError = TIMEERROR;
		ASSERT_EQ(hypotimeError, expectedtimeError)<< testinfo.c_str();
	}

	// check depthError
	if (std::isnan(locationResultObject.hypocenter.depthError) != true) {
		double hypocenterdepthError = locationResultObject.hypocenter.depthError;
		double expecteddepthError = DEPTHERROR;
		ASSERT_EQ(hypocenterdepthError, expecteddepthError);
	}

	// check numberOfAssociatedStations
	if (locationResultObject.numberOfAssociatedStations > 0) {
		int numberOfAssociatedStations = locationResultObject.numberOfAssociatedStations;
		int expectednumberOfAssociatedStations = NUMASSOCIATEDSTATIONS;
		ASSERT_EQ(numberOfAssociatedStations, expectednumberOfAssociatedStations);
	}

	// check numberOfAssociatedPhases
	if (locationResultObject.numberOfAssociatedPhases > 0) {
		int numberOfAssociatedPhases = locationResultObject.numberOfAssociatedPhases;
		int expectednumberOfAssociatedPhases = NUMASSOCIATEDPHASES;
		ASSERT_EQ(numberOfAssociatedPhases, expectednumberOfAssociatedPhases);
	}

	// check numberOfUsedStations
	if (locationResultObject.numberOfUsedStations > 0) {
		int numberOfUsedStations = locationResultObject.numberOfUsedStations;
		int expectednumberOfUsedStations = NUMUSEDSTATIONS;
		ASSERT_EQ(numberOfUsedStations, expectednumberOfUsedStations);
	}

	// check numberOfUsedPhases
	if (locationResultObject.numberOfUsedPhases > 0) {
		int numberOfUsedPhases = locationResultObject.numberOfUsedPhases;
		int expectednumberOfUsedPhases = NUMUSEDPHASES;
		ASSERT_EQ(numberOfUsedPhases, expectednumberOfUsedPhases);
	}

	// check gap
	if (std::isnan(locationResultObject.gap) != true) {
		double gap = locationResultObject.gap;
		double expectedgap = GAP;
		ASSERT_EQ(gap, expectedgap)<< testinfo.c_str();
	}

	// check secondaryGap
	if (std::isnan(locationResultObject.secondaryGap) != true) {
		double secondaryGap = locationResultObject.secondaryGap;
		double expectedsecondaryGap = SECONDARYGAP;
		ASSERT_EQ(secondaryGap, expectedsecondaryGap)<< testinfo.c_str();
	}

	// check minimumDistance
	if (std::isnan(locationResultObject.minimumDistance) != true) {
		double minimumDistance = locationResultObject.minimumDistance;
		double expectedminimumDistance = MINIMUMDISTANCE;
		ASSERT_EQ(minimumDistance, minimumDistance)<< testinfo.c_str();
	}

	// check rms
	if (std::isnan(locationResultObject.rms) != true) {
		double rms = locationResultObject.rms;
		double expectedrms = RMS;
		ASSERT_EQ(rms, expectedrms)<< testinfo.c_str();
	}

	// check quality
	if (locationResultObject.quality.empty() != true) {
		std::string quality = locationResultObject.quality;
		std::string expectedquality = std::string(QUALITY);
		ASSERT_STREQ(quality.c_str(), expectedquality.c_str())<< testinfo.c_str();
	}

	// check bayesianDepth
	if (std::isnan(locationResultObject.bayesianDepth) != true) {
		double bayesianDepth = locationResultObject.bayesianDepth;
		double expectedbayesianDepth = BAYESIANDEPTH;
		ASSERT_EQ(bayesianDepth, expectedbayesianDepth)<< testinfo.c_str();
	}

	// check bayesianRange
	if (std::isnan(locationResultObject.bayesianRange) != true) {
		double bayesianRange = locationResultObject.bayesianRange;
		double expectedbayesianRange = BAYESIANRANGE;
		ASSERT_EQ(bayesianRange, expectedbayesianRange)<< testinfo.c_str();
	}

	// check depthImportance
	if (std::isnan(locationResultObject.depthImportance) != true) {
		double depthImportance = locationResultObject.depthImportance;
		double expecteddepthImportance = DEPTHIMPORTANCE;
		ASSERT_EQ(depthImportance, expecteddepthImportance)<< testinfo.c_str();
	}

	// check locatorExitCode
	if (locationResultObject.locatorExitCode.empty() != true) {
		std::string locatorExitCode = locationResultObject.locatorExitCode;
		std::string expectedlocatorExitCode = std::string(LOCATOREXITCODE);
		ASSERT_STREQ(locatorExitCode.c_str(), expectedlocatorExitCode.c_str())<< testinfo.c_str();
	}

  // check errorEllipse.e0Error
	if (std::isnan(locationResultObject.errorEllipse.e0Error) != true) {	
		double ellipseE0Error = locationResultObject.errorEllipse.e0Error;
		double expectedE0Error = E0ERROR;
		ASSERT_EQ(ellipseE0Error, expectedE0Error);
	}

  // check errorEllipse.e0Azimuth
	if (std::isnan(locationResultObject.errorEllipse.e0Azimuth) != true) {	
		double ellipseE0Azimuth = locationResultObject.errorEllipse.e0Azimuth;
		double expectedE0Azimuth = E0AZIMUTH;
		ASSERT_EQ(ellipseE0Azimuth, expectedE0Azimuth);
	}

  // check errorEllipse.e0Dip
	if (std::isnan(locationResultObject.errorEllipse.e0Dip) != true) {
    double ellipseE0Dip = locationResultObject.errorEllipse.e0Dip;
		double expectedE0Dip = E0DIP;
		ASSERT_EQ(ellipseE0Dip, expectedE0Dip);
	}

  // check errorEllipse.e1Error
	if (std::isnan(locationResultObject.errorEllipse.e1Error) != true) {	
    double ellipseE1Error = locationResultObject.errorEllipse.e1Error;
		double expectedE1Error = E1ERROR;
		ASSERT_EQ(ellipseE1Error, expectedE1Error);
	}

  // check errorEllipse.e1Azimuth
	if (std::isnan(locationResultObject.errorEllipse.e1Azimuth) != true) {	
    double ellipseE1Azimuth = locationResultObject.errorEllipse.e1Azimuth;
		double expectedE1Azimuth = E1AZIMUTH;
		ASSERT_EQ(ellipseE1Azimuth, expectedE1Azimuth);
	}

  // check errorEllipse.e1Dip
	if (std::isnan(locationResultObject.errorEllipse.e1Dip) != true) {	
    double ellipseE1Dip = locationResultObject.errorEllipse.e1Dip;
		double expectedE1Dip = E1DIP;
		ASSERT_EQ(ellipseE1Dip, expectedE1Dip);
	}

  // check errorEllipse.e2Error
	if (std::isnan(locationResultObject.errorEllipse.e2Error) != true) {	
    double ellipseE2Error = locationResultObject.errorEllipse.e2Error;
		double expectedE2Error = E2ERROR;
		ASSERT_EQ(ellipseE2Error, expectedE2Error);
	}

  // check errorEllipse.e2Azimuth
	if (std::isnan(locationResultObject.errorEllipse.e2Azimuth) != true) {	
    double ellipseE2Azimuth = locationResultObject.errorEllipse.e2Azimuth;
		double expectedE2Azimuth = E2AZIMUTH;
		ASSERT_EQ(ellipseE2Azimuth, expectedE2Azimuth);
	}

  // check errorEllipse.e2Dip
  if (std::isnan(locationResultObject.errorEllipse.e2Dip) != true) {	
		double ellipseE2Dip = locationResultObject.errorEllipse.e2Dip;
		double expectedE2Dip = E2DIP;
		ASSERT_EQ(ellipseE2Dip, expectedE2Dip);
	}

  // check errorEllipse.maximumHorizontalProjection
	if (std::isnan(locationResultObject.errorEllipse.maximumHorizontalProjection)
		!= true) {	
    double ellipseMaximumHorizontalProjection =
      locationResultObject.errorEllipse.maximumHorizontalProjection;
		double expectedMaximumHorizontalProjection = MAXIMUMHORIZONTALPROJECTION;
		ASSERT_EQ(ellipseMaximumHorizontalProjection,
			expectedMaximumHorizontalProjection);
	}

  // check errorEllipse.maximumVerticalProjection
	if (std::isnan(locationResultObject.errorEllipse.maximumVerticalProjection)
		!= true) {
    double ellipseMaximumVerticalProjection =
      locationResultObject.errorEllipse.maximumVerticalProjection;
		double expectedMaximumVerticalProjection = MAXIMUMVERTICALPROJECTION;
		ASSERT_EQ(ellipseMaximumVerticalProjection,
      expectedMaximumVerticalProjection);
	}

  // check errorEllipse.equivalentHorizontalRadius
	if (std::isnan(locationResultObject.errorEllipse.equivalentHorizontalRadius)
		!= true) {
    double ellipseEquivalentHorizontalRadius =
      locationResultObject.errorEllipse.equivalentHorizontalRadius;
		double expectedEquivalentHorizontalRadius = EQUIVALENTHORIZONTALRADIUS;
		ASSERT_EQ(ellipseEquivalentHorizontalRadius,
      expectedEquivalentHorizontalRadius);
	}
}

// tests to see if LocationResult can successfully
// write json output
TEST(ResultTest, WritesJSON) {
	processingformats::LocationResult locationResultObject;

	// build LocationResult object
	locationResultObject.id = std::string(ID);
	locationResultObject.source.agencyId = std::string(AGENCYID);
	locationResultObject.source.author = std::string(AUTHOR);
	locationResultObject.source.type = std::string(TYPE);	
	locationResultObject.hypocenter.latitude = LATITUDE;
	locationResultObject.hypocenter.longitude = LONGITUDE;
	locationResultObject.hypocenter.depth = DEPTH;
	locationResultObject.hypocenter.time =
		processingformats::ConvertISO8601ToEpochTime(std::string(TIME));	
	locationResultObject.hypocenter.latitudeError = LATITUDEERROR;
	locationResultObject.hypocenter.longitudeError = LONGITUDEERROR;
	locationResultObject.hypocenter.depthError = DEPTHERROR;
	locationResultObject.hypocenter.timeError = TIMEERROR;
	locationResultObject.supportingData = buildSupportingData();
	locationResultObject.numberOfAssociatedStations = NUMASSOCIATEDSTATIONS;
	locationResultObject.numberOfAssociatedPhases = NUMASSOCIATEDPHASES;
	locationResultObject.numberOfUsedStations = NUMUSEDSTATIONS;
	locationResultObject.numberOfUsedPhases = NUMUSEDPHASES;
	locationResultObject.gap = GAP;
	locationResultObject.secondaryGap = SECONDARYGAP;
	locationResultObject.minimumDistance = MINIMUMDISTANCE;
	locationResultObject.rms = RMS;
	locationResultObject.quality = std::string(QUALITY);
	locationResultObject.bayesianDepth = BAYESIANDEPTH;
	locationResultObject.bayesianRange = BAYESIANRANGE;
	locationResultObject.depthImportance = DEPTHIMPORTANCE;
	locationResultObject.locatorExitCode = std::string(LOCATOREXITCODE);
	locationResultObject.errorEllipse.e0Error = E0ERROR;
	locationResultObject.errorEllipse.e0Azimuth = E0AZIMUTH;
	locationResultObject.errorEllipse.e0Dip = E0DIP;
	locationResultObject.errorEllipse.e1Error = E1ERROR;
	locationResultObject.errorEllipse.e1Azimuth = E1AZIMUTH;
	locationResultObject.errorEllipse.e1Dip = E1DIP;
	locationResultObject.errorEllipse.e2Error = E2ERROR;
	locationResultObject.errorEllipse.e2Azimuth = E2AZIMUTH;
	locationResultObject.errorEllipse.e2Dip = E2DIP;
	locationResultObject.errorEllipse.maximumHorizontalProjection =
		MAXIMUMHORIZONTALPROJECTION;
  locationResultObject.errorEllipse.maximumVerticalProjection =
		MAXIMUMVERTICALPROJECTION;
	locationResultObject.errorEllipse.equivalentHorizontalRadius =
		EQUIVALENTHORIZONTALRADIUS;

	// build json string
	rapidjson::Document ResultDocument;
	std::string resultJSON = processingformats::ToJSONString(
			locationResultObject.toJSON(ResultDocument, ResultDocument.GetAllocator()));

	// read it back in
	rapidjson::Document ResultDocument2;
	processingformats::LocationResult locationResultObject2(
			processingformats::FromJSONString(resultJSON, ResultDocument2));

	// check data values
	checkdata(locationResultObject2, "");
}

// tests to see if LocationResult can successfully
// read json output
TEST(ResultTest, ReadsJSON) {
	// build LocationResult object
	rapidjson::Document ResultDocument;
	processingformats::LocationResult locationResultObject(
			processingformats::FromJSONString(std::string(LOCATIONRESULT_STRING),
												ResultDocument));

	// check data values
	checkdata(locationResultObject, "");
}

// tests to see if LocationResult can successfully
// be constructed
TEST(ResultTest, Constructor) {
	// use constructor
	processingformats::LocationResult locationResultObject(
            std::string(ID), std::string(AGENCYID), std::string(AUTHOR), 
						std::string(TYPE), LATITUDE, LONGITUDE,
						processingformats::ConvertISO8601ToEpochTime(std::string(TIME)),
						DEPTH, LATITUDEERROR, LONGITUDEERROR, TIMEERROR, DEPTHERROR,
            buildSupportingData(), NUMASSOCIATEDSTATIONS, NUMASSOCIATEDPHASES,
						NUMUSEDSTATIONS, NUMUSEDPHASES, GAP, SECONDARYGAP, MINIMUMDISTANCE,
						RMS, std::string(QUALITY), BAYESIANDEPTH, BAYESIANRANGE, 
						DEPTHIMPORTANCE, std::string(LOCATOREXITCODE), E0ERROR, E0AZIMUTH,
						E0DIP, E1ERROR, E1AZIMUTH, E1DIP, E2ERROR, E2AZIMUTH, E2DIP,
						MAXIMUMHORIZONTALPROJECTION, MAXIMUMVERTICALPROJECTION, 
						EQUIVALENTHORIZONTALRADIUS);

	// check data values
	checkdata(locationResultObject, "");

	processingformats::LocationResult locationResultObject2(
            LATITUDE, LONGITUDE,
						processingformats::ConvertISO8601ToEpochTime(std::string(TIME)),
						DEPTH, LATITUDEERROR, LONGITUDEERROR, TIMEERROR, DEPTHERROR,
            buildSupportingData());

	// check data values
	checkdata(locationResultObject2, "Constructor 2");

	processingformats::LocationResult locationResultObject3(
            processingformats::Hypocenter(LATITUDE, LONGITUDE,
						processingformats::ConvertISO8601ToEpochTime(std::string(TIME)),
						DEPTH, LATITUDEERROR, LONGITUDEERROR, TIMEERROR, DEPTHERROR),
            buildSupportingData());

	// check data values
	checkdata(locationResultObject3, "Constructor 3");	
}

// tests to see if LocationResult can successfully
// validate
TEST(ResultTest, Validate) {
	processingformats::LocationResult locationResultObject;

	// build LocationResult object
	locationResultObject.id = std::string(ID);
	locationResultObject.source.agencyId = std::string(AGENCYID);
	locationResultObject.source.author = std::string(AUTHOR);
	locationResultObject.source.type = std::string(TYPE);	
	locationResultObject.hypocenter.latitude = LATITUDE;
	locationResultObject.hypocenter.longitude = LONGITUDE;
	locationResultObject.hypocenter.depth = DEPTH;
	locationResultObject.hypocenter.time =
		processingformats::ConvertISO8601ToEpochTime(std::string(TIME));	
	locationResultObject.hypocenter.latitudeError = LATITUDEERROR;
	locationResultObject.hypocenter.longitudeError = LONGITUDEERROR;
	locationResultObject.hypocenter.depthError = DEPTHERROR;
	locationResultObject.hypocenter.timeError = TIMEERROR;
	locationResultObject.supportingData = buildSupportingData();
	locationResultObject.numberOfAssociatedStations = NUMASSOCIATEDSTATIONS;
	locationResultObject.numberOfAssociatedPhases = NUMASSOCIATEDPHASES;
	locationResultObject.numberOfUsedStations = NUMUSEDSTATIONS;
	locationResultObject.numberOfUsedPhases = NUMUSEDPHASES;
	locationResultObject.gap = GAP;
	locationResultObject.secondaryGap = SECONDARYGAP;
	locationResultObject.minimumDistance = MINIMUMDISTANCE;
	locationResultObject.rms = RMS;
	locationResultObject.quality = std::string(QUALITY);
	locationResultObject.bayesianDepth = BAYESIANDEPTH;
	locationResultObject.bayesianRange = BAYESIANRANGE;
	locationResultObject.depthImportance = DEPTHIMPORTANCE;
	locationResultObject.locatorExitCode = std::string(LOCATOREXITCODE);
	locationResultObject.errorEllipse.e0Error = E0ERROR;
	locationResultObject.errorEllipse.e0Azimuth = E0AZIMUTH;
	locationResultObject.errorEllipse.e0Dip = E0DIP;
	locationResultObject.errorEllipse.e1Error = E1ERROR;
	locationResultObject.errorEllipse.e1Azimuth = E1AZIMUTH;
	locationResultObject.errorEllipse.e1Dip = E1DIP;
	locationResultObject.errorEllipse.e2Error = E2ERROR;
	locationResultObject.errorEllipse.e2Azimuth = E2AZIMUTH;
	locationResultObject.errorEllipse.e2Dip = E2DIP;
	locationResultObject.errorEllipse.maximumHorizontalProjection =
		MAXIMUMHORIZONTALPROJECTION;
  locationResultObject.errorEllipse.maximumVerticalProjection =
		MAXIMUMVERTICALPROJECTION;
	locationResultObject.errorEllipse.equivalentHorizontalRadius =
		EQUIVALENTHORIZONTALRADIUS;

	// successful validation
	bool result = locationResultObject.isValid();

	// check return code
	ASSERT_EQ(result, true)<< "Tested for successful validation.";

	// build bad LocationResult object
	processingformats::LocationResult badlocationResultObject;
	badlocationResultObject.hypocenter.latitude = LATITUDE;

	result = false;
	try {
		// call validation
		result = badlocationResultObject.isValid();
	} catch (const std::exception &) {
		// don't care what the exception was
	}

	// check return code
	ASSERT_EQ(result, false)<< "Tested for unsuccessful validation.";
}