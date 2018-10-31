#include <processing-formats.h>
#include <gtest/gtest.h>

#include <string>

#define ELLIPSESTRING "{\"MaximumVerticalProjection\":1.984,\"EquivalentHorizontalRadius\":1.984,\"MaximumHorizontalProjection\":1.984,\"E0\":{\"Azimuth\":-121.44,\"Error\":40.3344,\"Dip\":32.44},\"E1\":{\"Azimuth\":22.64,\"Error\":12.5,\"Dip\":2.44},\"E2\":{\"Azimuth\":22.64,\"Error\":12.5,\"Dip\":2.44}}" // NOLINT

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

void checkdata(processingformats::ErrorEllipse ellipseObject,
    std::string testinfo) {

    // check ellipseObject.e0Error
    double ellipseE0Error = ellipseObject.e0Error;
	double expectedE0Error = E0ERROR;
	ASSERT_EQ(ellipseE0Error, expectedE0Error);

    // check ellipseObject.e0Azimuth
    double ellipseE0Azimuth = ellipseObject.e0Azimuth;
	double expectedE0Azimuth = E0AZIMUTH;
	ASSERT_EQ(ellipseE0Azimuth, expectedE0Azimuth);

    // check ellipseObject.e0Dip
    double ellipseE0Dip = ellipseObject.e0Dip;
	double expectedE0Dip = E0DIP;
	ASSERT_EQ(ellipseE0Dip, expectedE0Dip);

    // check ellipseObject.e1Error
    double ellipseE1Error = ellipseObject.e1Error;
	double expectedE1Error = E1ERROR;
	ASSERT_EQ(ellipseE1Error, expectedE1Error);

    // check ellipseObject.e1Azimuth
    double ellipseE1Azimuth = ellipseObject.e1Azimuth;
	double expectedE1Azimuth = E1AZIMUTH;
	ASSERT_EQ(ellipseE1Azimuth, expectedE1Azimuth);

    // check ellipseObject.e1Dip
    double ellipseE1Dip = ellipseObject.e1Dip;
	double expectedE1Dip = E1DIP;
	ASSERT_EQ(ellipseE1Dip, expectedE1Dip);

    // check ellipseObject.e2Error
    double ellipseE2Error = ellipseObject.e2Error;
	double expectedE2Error = E2ERROR;
	ASSERT_EQ(ellipseE2Error, expectedE2Error);

    // check ellipseObject.e2Azimuth
    double ellipseE2Azimuth = ellipseObject.e2Azimuth;
	double expectedE2Azimuth = E2AZIMUTH;
	ASSERT_EQ(ellipseE2Azimuth, expectedE2Azimuth);

    // check ellipseObject.e2Dip
    double ellipseE2Dip = ellipseObject.e2Dip;
	double expectedE2Dip = E2DIP;
	ASSERT_EQ(ellipseE2Dip, expectedE2Dip);

    // check ellipseObject.maximumHorizontalProjection
    double ellipseMaximumHorizontalProjection =
        ellipseObject.maximumHorizontalProjection;
	double expectedMaximumHorizontalProjection = MAXIMUMHORIZONTALPROJECTION;
	ASSERT_EQ(ellipseMaximumHorizontalProjection,
        expectedMaximumHorizontalProjection);

    // check ellipseObject.maximumVerticalProjection
    double ellipseMaximumVerticalProjection =
        ellipseObject.maximumVerticalProjection;
	double expectedMaximumVerticalProjection = MAXIMUMVERTICALPROJECTION;
	ASSERT_EQ(ellipseMaximumVerticalProjection,
        expectedMaximumVerticalProjection);

    // check ellipseObject.equivalentHorizontalRadius
    double ellipseEquivalentHorizontalRadius =
        ellipseObject.equivalentHorizontalRadius;
	double expectedEquivalentHorizontalRadius = EQUIVALENTHORIZONTALRADIUS;
	ASSERT_EQ(ellipseEquivalentHorizontalRadius,
        expectedEquivalentHorizontalRadius);
}

// tests to see if ErrorEllipse can successfully
// write json output
TEST(EllipseTest, WritesJSON) {
	processingformats::ErrorEllipse ellipseObject;

	// build ErrorEllipse object
	ellipseObject.e0Error = E0ERROR;
	ellipseObject.e0Azimuth = E0AZIMUTH;
	ellipseObject.e0Dip = E0DIP;
	ellipseObject.e1Error = E1ERROR;
	ellipseObject.e1Azimuth = E1AZIMUTH;
	ellipseObject.e1Dip = E1DIP;
	ellipseObject.e2Error = E2ERROR;
	ellipseObject.e2Azimuth = E2AZIMUTH;
	ellipseObject.e2Dip = E2DIP;
	ellipseObject.maximumHorizontalProjection = MAXIMUMHORIZONTALPROJECTION;
    ellipseObject.maximumVerticalProjection = MAXIMUMVERTICALPROJECTION;
	ellipseObject.equivalentHorizontalRadius = EQUIVALENTHORIZONTALRADIUS;

	// build json string
	rapidjson::Document ellipseDocument;
	std::string ellipseJSON = processingformats::ToJSONString(
			ellipseObject.toJSON(ellipseDocument, ellipseDocument.GetAllocator()));

	// read it back in
	rapidjson::Document ellipseDocument2;
	processingformats::ErrorEllipse ellipseObject2(
			processingformats::FromJSONString(ellipseJSON, ellipseDocument2));

	// check data values
	checkdata(ellipseObject2, "");
}

// tests to see if ErrorEllipse can successfully
// read json output
TEST(EllipseTest, ReadsJSON) {
	// build associated object
	rapidjson::Document ellipseDocument;
	processingformats::ErrorEllipse ellipseObject(
			processingformats::FromJSONString(std::string(ELLIPSESTRING),
					ellipseDocument));

	// check data values
	checkdata(ellipseObject, "");
}

// tests to see if ErrorEllipse can successfully
// be constructed
TEST(EllipseTest, Constructor) {
	// use constructor
	processingformats::ErrorEllipse ellipseObject(E0ERROR, E0AZIMUTH, E0DIP,
				E1ERROR, E1AZIMUTH, E1DIP, E2ERROR, E2AZIMUTH, E2DIP,
				MAXIMUMHORIZONTALPROJECTION, MAXIMUMVERTICALPROJECTION,
				EQUIVALENTHORIZONTALRADIUS);

	// check data values
	checkdata(ellipseObject, "Tested Constructor");
}

// tests to see if ErrorEllipse can successfully
// be copy constructed
TEST(EllipseTest, CopyConstructor) {
	// use constructor
	processingformats::ErrorEllipse fromEllipseObject(E0ERROR, E0AZIMUTH, E0DIP,
				E1ERROR, E1AZIMUTH, E1DIP, E2ERROR, E2AZIMUTH, E2DIP,
				MAXIMUMHORIZONTALPROJECTION, MAXIMUMVERTICALPROJECTION,
				EQUIVALENTHORIZONTALRADIUS);

	processingformats::ErrorEllipse ellipseObject(fromEllipseObject);

	// check data values
	checkdata(ellipseObject, "");
}

// tests to see if ErrorEllipse can successfully
// validate
TEST(EllipseTest, Validate) {
	processingformats::ErrorEllipse ellipseObject;

	// build ErrorEllipse object
	ellipseObject.e0Error = E0ERROR;
	ellipseObject.e0Azimuth = E0AZIMUTH;
	ellipseObject.e0Dip = E0DIP;
	ellipseObject.e1Error = E1ERROR;
	ellipseObject.e1Azimuth = E1AZIMUTH;
	ellipseObject.e1Dip = E1DIP;
	ellipseObject.e2Error = E2ERROR;
	ellipseObject.e2Azimuth = E2AZIMUTH;
	ellipseObject.e2Dip = E2DIP;
	ellipseObject.maximumHorizontalProjection = MAXIMUMHORIZONTALPROJECTION;
    ellipseObject.maximumVerticalProjection = MAXIMUMVERTICALPROJECTION;
	ellipseObject.equivalentHorizontalRadius = EQUIVALENTHORIZONTALRADIUS;

	// successful validation
	bool result = ellipseObject.isValid();

	// check return code
	ASSERT_EQ(result, true)<< "Tested for successful validation.";

	// build bad ErrorEllipse object
	processingformats::ErrorEllipse badEllipseObject;
	badEllipseObject.e0Error = std::numeric_limits<double>::quiet_NaN();

	result = false;
	try {
		// call validation
		result = badEllipseObject.isValid();
	} catch (const std::exception &) {
		// don't care what the exception was
	}

	// check return code
	ASSERT_EQ(result, false)<< "Tested for unsuccessful validation.";
}





