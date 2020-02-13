#include "processing-formats.h"
#include <gtest/gtest.h>

#include <string>

// test data
#define SITESTRING "{\"Station\":\"BOZ\",\"Channel\":\"BHZ\",\"Network\":\"US\",\"Location\":\"00\",\"Latitude\":45.59697,\"Longitude\":-111.62967,\"Elevation\":1589.0}" // NOLINT
#define STATION "BOZ"
#define CHANNEL "BHZ"
#define NETWORK "US"
#define LOCATION "00"
#define LATITUDE 45.596970
#define LONGITUDE -111.629670
#define ELEVATION 1589.000000

void checkdata(processingformats::Site Siteobject, std::string testinfo) {
	// check station
	std::string Sitestation = Siteobject.station;
	std::string expectedstation = std::string(STATION);
	ASSERT_STREQ(Sitestation.c_str(), expectedstation.c_str());

	// check channel
	std::string Sitechannel = Siteobject.channel;
	std::string expectedchannel = std::string(CHANNEL);
	ASSERT_STREQ(Sitechannel.c_str(), expectedchannel.c_str());

	// check network
	std::string Sitenetwork = Siteobject.network;
	std::string expectednetwork = std::string(NETWORK);
	ASSERT_STREQ(Sitenetwork.c_str(), expectednetwork.c_str());

	// check location
	std::string Sitelocation = Siteobject.location;
	std::string expectedlocation = std::string(LOCATION);
	ASSERT_STREQ(Sitelocation.c_str(), expectedlocation.c_str());

	// check latitude
	if (std::isnan(Siteobject.latitude) != true) {
		double sitelatitude = Siteobject.latitude;
		double expectedlatitude = LATITUDE;
		ASSERT_EQ(sitelatitude, expectedlatitude);
	}

	// check longitude
	if (std::isnan(Siteobject.longitude) != true) {
		double sitelongitude = Siteobject.longitude;
		double expectedlongitude = LONGITUDE;
		ASSERT_EQ(sitelongitude, expectedlongitude);
	}

	// check elevation
	if (std::isnan(Siteobject.elevation) != true) {
		double siteelevation = Siteobject.elevation;
		double expectedelevation = ELEVATION;
		ASSERT_EQ(siteelevation, expectedelevation);
	}
}

// tests to see if Site can successfully
// write json output
TEST(SiteTest, WritesJSON) {
	processingformats::Site Siteobject;

	// build Site object
	Siteobject.station = std::string(STATION);
	Siteobject.channel = std::string(CHANNEL);
	Siteobject.network = std::string(NETWORK);
	Siteobject.location = std::string(LOCATION);
	Siteobject.latitude = LATITUDE;
	Siteobject.longitude = LONGITUDE;
	Siteobject.elevation = ELEVATION;

	// build json string
	rapidjson::Document Sitedocument;
	std::string Sitejson = processingformats::ToJSONString(
			Siteobject.toJSON(Sitedocument, Sitedocument.GetAllocator()));

	// read it back in
	rapidjson::Document Sitedocument2;
	processingformats::Site Siteobject2(
			processingformats::FromJSONString(Sitejson, Sitedocument2));

	// check data values
	checkdata(Siteobject2, "");
}

// tests to see if Site can successfully
// read json output
TEST(SiteTest, ReadsJSON) {
	// build Site object
	rapidjson::Document Sitedocument;
	processingformats::Site Siteobject(
			processingformats::FromJSONString(std::string(SITESTRING),
												Sitedocument));

	// check data values
	checkdata(Siteobject, "");
}

// tests to see if Site can successfully
// be constructed
TEST(SiteTest, Constructors) {
	// use constructor
	processingformats::Site Siteobject(STATION, CHANNEL, NETWORK, LOCATION, 
		LATITUDE, LONGITUDE, ELEVATION);

	// check data values
	checkdata(Siteobject, "");
}

// tests to see if Site can successfully
// validate
TEST(SiteTest, Validate) {
	processingformats::Site Siteobject;

	// build Site object
	Siteobject.station = std::string(STATION);
	Siteobject.channel = std::string(CHANNEL);
	Siteobject.network = std::string(NETWORK);
	Siteobject.location = std::string(LOCATION);
	Siteobject.latitude = LATITUDE;
	Siteobject.longitude = LONGITUDE;
	Siteobject.elevation = ELEVATION;

	// successful validation
	bool result = Siteobject.isValid();

	// check return code
	ASSERT_EQ(result, true)<< "Tested for successful validation.";

	// build bad Site object
	processingformats::Site badSiteobject;
	badSiteobject.location = std::string(LOCATION);

	result = false;
	try {
		// call validation
		result = badSiteobject.isValid();
	} catch (const std::exception &) {
		// don't care what the exception was
	}

	// check return code
	ASSERT_EQ(result, false)<< "Tested for unsuccessful validation.";
}
