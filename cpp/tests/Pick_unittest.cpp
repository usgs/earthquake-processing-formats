#include "processing-formats.h"
#include <gtest/gtest.h>

#include <string>

// test data
#define PICK_STRING "{\"ID\":\"12GFH48776857\",\"Site\":{\"Station\":\"BOZ\",\"Channel\":\"BHZ\",\"Network\":\"US\",\"Location\":\"00\",\"Latitude\":45.59697,\"Longitude\":-111.62967,\"Elevation\":1589.0},\"Source\":{\"Author\":\"TestAuthor\",\"AgencyID\":\"US\",\"Type\":\"Unknown\"},\"Time\":\"2015-12-28T21:32:24.017Z\",\"Affinity\":1.2,\"Quality\":0.45,\"Use\":true,\"PickedPhase\":\"P\",\"AssociatedPhase\":\"Pn\",\"LocatedPhase\":\"Pg\",\"Residual\":1.05,\"Distance\":2.65,\"Azimuth\":21.5,\"Weight\":2.65,\"Importance\":3.8}" // NOLINT
#define ID "12GFH48776857"
#define STATION "BOZ"
#define CHANNEL "BHZ"
#define NETWORK "US"
#define LOCATION "00"
#define LATITUDE 45.596970
#define LONGITUDE -111.629670
#define ELEVATION 1589.000000
#define AGENCYID "US"
#define AUTHOR "TestAuthor"
#define TYPE "Unknown"
#define TIME "2015-12-28T21:32:24.017Z"
#define AFFINITY 1.2
#define QUALITY 0.45
#define USE true
#define PICKEDPHASE "P"
#define ASSOCIATEDPHASE "Pn"
#define LOCATEDPHASE "Pg"
#define RESIDUAL 1.05
#define DISTANCE 2.65
#define AZIMUTH 21.5
#define WEIGHT 2.65
#define IMPORTANCE 3.8

void checkdata(processingformats::Pick pickobject, std::string testinfo) {
	// check id
	if (pickobject.id.empty() != true) {
		std::string pickid = pickobject.id;
		std::string expectedid = std::string(ID);
		ASSERT_STREQ(pickid.c_str(), expectedid.c_str())<< testinfo.c_str();
	}

	// check station
	if (pickobject.site.station.empty() != true) {
		std::string sitestation = pickobject.site.station;
		std::string expectedstation = std::string(STATION);
		ASSERT_STREQ(sitestation.c_str(),
			expectedstation.c_str())<< testinfo.c_str();
	}

	// check channel
	if (pickobject.site.channel.empty() != true) {
		std::string sitechannel = pickobject.site.channel;
		std::string expectedchannel = std::string(CHANNEL);
		ASSERT_STREQ(sitechannel.c_str(),
			expectedchannel.c_str())<< testinfo.c_str();
	}

	// check network
	if (pickobject.site.network.empty() != true) {
		std::string sitenetwork = pickobject.site.network;
		std::string expectednetwork = std::string(NETWORK);
		ASSERT_STREQ(sitenetwork.c_str(),
			expectednetwork.c_str())<< testinfo.c_str();
	}

	// check location
	if (pickobject.site.location.empty() != true) {
		std::string sitelocation = pickobject.site.location;
		std::string expectedlocation = std::string(LOCATION);
		ASSERT_STREQ(sitelocation.c_str(),
			expectedlocation.c_str())<< testinfo.c_str();
	}

	// check latitude
	if (std::isnan(pickobject.site.latitude) != true) {
		double sitelatitude = pickobject.site.latitude;
		double expectedlatitude = LATITUDE;
		ASSERT_EQ(sitelatitude, expectedlatitude);
	}

	// check longitude
	if (std::isnan(pickobject.site.longitude) != true) {
		double sitelongitude = pickobject.site.longitude;
		double expectedlongitude = LONGITUDE;
		ASSERT_EQ(sitelongitude, expectedlongitude);
	}

	// check elevation
	if (std::isnan(pickobject.site.elevation) != true) {
		double siteelevation = pickobject.site.elevation;
		double expectedelevation = ELEVATION;
		ASSERT_EQ(siteelevation, expectedelevation);
	}

	// check agencyId
	if (pickobject.source.agencyId.empty() != true) {
		std::string sourceagencyid = pickobject.source.agencyId;
		std::string expectedagencyid = std::string(AGENCYID);
		ASSERT_STREQ(sourceagencyid.c_str(),
			expectedagencyid.c_str())<< testinfo.c_str();
	}

	// check author
	if (pickobject.source.author.empty() != true) {
		std::string sourceauthor = pickobject.source.author;
		std::string expectedauthor = std::string(AUTHOR);
		ASSERT_STREQ(sourceauthor.c_str(),
			expectedauthor.c_str())<< testinfo.c_str();
	}

	// check type
	if (pickobject.source.type.empty() != true) {
		std::string sourcetype = pickobject.source.type;
		std::string expectedtype = std::string(TYPE);
		ASSERT_STREQ(sourcetype.c_str(),
			expectedtype.c_str())<< testinfo.c_str();
	}

	// check time
	if (std::isnan(pickobject.time) != true) {
		double picktime = pickobject.time;
		double expectedtime = processingformats::ConvertISO8601ToEpochTime(
				std::string(TIME));
		ASSERT_EQ(picktime, expectedtime)<< testinfo.c_str();
	}

	// check affinity
	if (std::isnan(pickobject.affinity) != true) {
		double pickaffinity = pickobject.affinity;
		double expectedaffinity = AFFINITY;
		ASSERT_EQ(pickaffinity, expectedaffinity)<< testinfo.c_str();
	}

	// check quality
	if (std::isnan(pickobject.quality) != true) {
		double pickquality = pickobject.quality;
		double expectedquality = QUALITY;
		ASSERT_EQ(pickquality, expectedquality)<< testinfo.c_str();
	}

	// check pickedPhase
	if (pickobject.pickedPhase.empty() != true) {
		std::string pickphase = pickobject.pickedPhase;
		std::string expectedphase = std::string(PICKEDPHASE);
		ASSERT_STREQ(pickphase.c_str(), expectedphase.c_str())<< testinfo.c_str();
	}

	// check associatedPhase
	if (pickobject.associatedPhase.empty() != true) {
		std::string associatedPhase = pickobject.associatedPhase;
		std::string expectedphase = std::string(ASSOCIATEDPHASE);
		ASSERT_STREQ(associatedPhase.c_str(), expectedphase.c_str())<< testinfo.c_str();
	}

	// check locatedPhase
	if (pickobject.locatedPhase.empty() != true) {
		std::string locatedPhase = pickobject.locatedPhase;
		std::string expectedphase = std::string(LOCATEDPHASE);
		ASSERT_STREQ(locatedPhase.c_str(), expectedphase.c_str())<< testinfo.c_str();
	}

	// check residual
	if (std::isnan(pickobject.residual) != true) {
		double pickresidual = pickobject.residual;
		double expectedresidual = RESIDUAL;
		ASSERT_EQ(pickresidual, expectedresidual)<< testinfo.c_str();
	}

	// check distance
	if (std::isnan(pickobject.distance) != true) {
		double pickdistance = pickobject.distance;
		double expecteddistance = DISTANCE;
		ASSERT_EQ(pickdistance, expecteddistance)<< testinfo.c_str();
	}

	// check azimuth
	if (std::isnan(pickobject.azimuth) != true) {
		double pickazimuth = pickobject.azimuth;
		double expectedazimuth = AZIMUTH;
		ASSERT_EQ(pickazimuth, expectedazimuth)<< testinfo.c_str();
	}

	// check weight
	if (std::isnan(pickobject.weight) != true) {
		double pickweight = pickobject.weight;
		double expectedweight = WEIGHT;
		ASSERT_EQ(pickweight, expectedweight)<< testinfo.c_str();
	}

	// check importance
	if (std::isnan(pickobject.importance) != true) {
		double pickimportance = pickobject.importance;
		double expectedimportance = IMPORTANCE;
		ASSERT_EQ(pickimportance, expectedimportance)<< testinfo.c_str();
	}
}

// tests to see if Pick can successfully
// write json output
TEST(PickTest, WritesJSON) {
	processingformats::Pick Pickobject;

	// build Pick object
	Pickobject.id = std::string(ID);
	Pickobject.site.station = std::string(STATION);
	Pickobject.site.channel = std::string(CHANNEL);
	Pickobject.site.network = std::string(NETWORK);
	Pickobject.site.location = std::string(LOCATION);
	Pickobject.site.latitude = LATITUDE;
	Pickobject.site.longitude = LONGITUDE;
	Pickobject.site.elevation = ELEVATION;
	Pickobject.source.agencyId = std::string(AGENCYID);
	Pickobject.source.author = std::string(AUTHOR);
	Pickobject.source.type = std::string(TYPE);
	Pickobject.time = processingformats::ConvertISO8601ToEpochTime(
			std::string(TIME));
	Pickobject.affinity = AFFINITY;
	Pickobject.quality = QUALITY;
	Pickobject.affinity = AFFINITY;
	Pickobject.use = USE;
	Pickobject.pickedPhase = std::string(PICKEDPHASE);
	Pickobject.associatedPhase = std::string(ASSOCIATEDPHASE);
	Pickobject.locatedPhase = std::string(LOCATEDPHASE);
	Pickobject.residual = RESIDUAL;
	Pickobject.distance = DISTANCE;
	Pickobject.azimuth = AZIMUTH;
	Pickobject.weight = WEIGHT;
	Pickobject.importance = IMPORTANCE;

	// build json string
	rapidjson::Document Pickdocument;
	std::string Pickjson = processingformats::ToJSONString(
			Pickobject.toJSON(Pickdocument, Pickdocument.GetAllocator()));

	// read it back in
	rapidjson::Document Pickdocument2;
	processingformats::Pick Pickobject2(
			processingformats::FromJSONString(Pickjson, Pickdocument2));

	// check data values
	checkdata(Pickobject2, "");
}

// tests to see if Pick can successfully
// read json output
TEST(PickTest, ReadsJSON) {
	// build Pick object
	rapidjson::Document Pickdocument;
	processingformats::Pick Pickobject(
			processingformats::FromJSONString(std::string(PICK_STRING),
												Pickdocument));

	// check data values
	checkdata(Pickobject, "");
}

// tests to see if Pick can successfully
// be constructed
TEST(PickTest, Constructor) {
	// use constructor
	processingformats::Pick Pickobject(
            std::string(ID),
            processingformats::Site(STATION,
            CHANNEL,
            NETWORK,
            LOCATION,
            LATITUDE,
            LONGITUDE,
            ELEVATION),
            processingformats::Source(AGENCYID,
            AUTHOR,
            TYPE),
            processingformats::ConvertISO8601ToEpochTime(std::string(TIME)),
            AFFINITY,
            QUALITY,
            USE,
            std::string(PICKEDPHASE),
            std::string(ASSOCIATEDPHASE),
            std::string(LOCATEDPHASE),
            RESIDUAL,
            DISTANCE,
            AZIMUTH,
            WEIGHT,
            IMPORTANCE);

	// check data values
	checkdata(Pickobject, "");

	processingformats::Pick Pickobject2(
            std::string(ID),
            STATION,
            CHANNEL,
            NETWORK,
            LOCATION,
            LATITUDE,
            LONGITUDE,
            ELEVATION,
            AGENCYID,
            AUTHOR,
            TYPE,
            processingformats::ConvertISO8601ToEpochTime(std::string(TIME)),
            AFFINITY,
            QUALITY,
            USE,
            std::string(PICKEDPHASE),
            std::string(ASSOCIATEDPHASE),
            std::string(LOCATEDPHASE),
            RESIDUAL,
            DISTANCE,
            AZIMUTH,
            WEIGHT,
            IMPORTANCE);

	// check data values
	checkdata(Pickobject2, "");
}

// tests to see if Pick can successfully
// validate
TEST(PickTest, Validate) {
	processingformats::Pick Pickobject;

	// build Pick object
	Pickobject.id = std::string(ID);
	Pickobject.site.station = std::string(STATION);
	Pickobject.site.channel = std::string(CHANNEL);
	Pickobject.site.network = std::string(NETWORK);
	Pickobject.site.location = std::string(LOCATION);
	Pickobject.site.latitude = LATITUDE;
	Pickobject.site.longitude = LONGITUDE;
	Pickobject.site.elevation = ELEVATION;
	Pickobject.source.agencyId = std::string(AGENCYID);
	Pickobject.source.author = std::string(AUTHOR);
	Pickobject.source.type = std::string(TYPE);
	Pickobject.time = processingformats::ConvertISO8601ToEpochTime(
			std::string(TIME));
	Pickobject.affinity = AFFINITY;
	Pickobject.quality = QUALITY;
	Pickobject.affinity = AFFINITY;
	Pickobject.use = USE;
	Pickobject.pickedPhase = std::string(PICKEDPHASE);
	Pickobject.associatedPhase = std::string(ASSOCIATEDPHASE);
	Pickobject.locatedPhase = std::string(LOCATEDPHASE);
	Pickobject.residual = RESIDUAL;
	Pickobject.distance = DISTANCE;
	Pickobject.azimuth = AZIMUTH;
	Pickobject.weight = WEIGHT;
	Pickobject.importance = IMPORTANCE;

	// successful validation
	bool result = Pickobject.isValid();

	// check return code
	ASSERT_EQ(result, true)<< "Tested for successful validation.";

	// build bad Pick object
	processingformats::Pick badPickobject;
	badPickobject.site.location = std::string(LOCATION);

	result = false;
	try {
		// call validation
		result = badPickobject.isValid();
	} catch (const std::exception &) {
		// don't care what the exception was
	}

	// check return code
	ASSERT_EQ(result, false)<< "Tested for unsuccessful validation.";
}
