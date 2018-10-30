#include "processing-formats.h"
#include <gtest/gtest.h>

#include <string>

// test data
#define SOURCESTRING "{\"AgencyID\":\"US\",\"Author\":\"TestAuthor\",\"Type\":\"LocalHuman\"}" // NOLINT
#define AGENCYID "US"
#define AUTHOR "TestAuthor"
#define TYPE "LocalHuman"

// tests to see if Source can successfully
// write json output
TEST(SourceTest, WritesJSON) {
	processingformats::Source sourceObject;

	// build Source object
	sourceObject.agencyId = std::string(AGENCYID);
	sourceObject.author = std::string(AUTHOR);
	sourceObject.type = std::string(TYPE);

	// build json string
	rapidjson::Document sourceDocument;
	std::string sourceJSON = processingformats::ToJSONString(
			sourceObject.toJSON(sourceDocument, sourceDocument.GetAllocator()));

	// read it back in
	rapidjson::Document sourceDocument2;
	processingformats::Source sourceObject2(
			processingformats::FromJSONString(sourceJSON, sourceDocument2));

	// check agencyid
	std::string sourceAgencyId = sourceObject2.agencyId;
	std::string expectedAgencyId = std::string(AGENCYID);
	ASSERT_STREQ(sourceAgencyId.c_str(), expectedAgencyId.c_str());

	// check author
	std::string sourceAuthor = sourceObject2.author;
	std::string expectedAuthor = std::string(AUTHOR);
	ASSERT_STREQ(sourceAuthor.c_str(), expectedAuthor.c_str());

	// check type
	std::string sourceType = sourceObject2.type;
	std::string expectedType = std::string(TYPE);
	ASSERT_STREQ(sourceType.c_str(), expectedType.c_str());
}

// tests to see if Source can successfully
// read json output
TEST(SourceTest, ReadsJSON) {
	// build associated object
	rapidjson::Document sourceDocument;
	processingformats::Source sourceObject(
			processingformats::FromJSONString(std::string(SOURCESTRING),
												sourceDocument));

	// check agencyid
	std::string sourceAgencyId = sourceObject.agencyId;
	std::string expectedAgencyId = std::string(AGENCYID);
	ASSERT_STREQ(sourceAgencyId.c_str(), expectedAgencyId.c_str());

	// check author
	std::string sourceAuthor = sourceObject.author;
	std::string expectedAuthor = std::string(AUTHOR);
	ASSERT_STREQ(sourceAuthor.c_str(), expectedAuthor.c_str());

	// check type
	std::string sourceType = sourceObject.type;
	std::string expectedType = std::string(TYPE);
	ASSERT_STREQ(sourceType.c_str(), expectedType.c_str());
}

// tests to see if Source can successfully
// be constructed
TEST(SourceTest, Constructor) {
	// use constructor
	processingformats::Source sourceObject(AGENCYID, AUTHOR, TYPE);

	// check agencyid
	std::string Sourceagencyid = sourceObject.agencyId;
	std::string expectedagencyid = std::string(AGENCYID);
	ASSERT_STREQ(Sourceagencyid.c_str(), expectedagencyid.c_str());

	// check author
	std::string Sourceauthor = sourceObject.author;
	std::string expectedauthor = std::string(AUTHOR);
	ASSERT_STREQ(Sourceauthor.c_str(), expectedauthor.c_str());

	// check type
	std::string sourceType = sourceObject.type;
	std::string expectedType = std::string(TYPE);
	ASSERT_STREQ(sourceType.c_str(), expectedType.c_str());
}

// tests to see if Source can successfully
// validate
TEST(SourceTest, Validate) {
	processingformats::Source sourceObject;

	// build Source object
	sourceObject.agencyId = std::string(AGENCYID);
	sourceObject.author = std::string(AUTHOR);
	sourceObject.type = std::string(TYPE);

	// successful validation
	bool result = sourceObject.isValid();

	// check return code
	ASSERT_EQ(result, true)<< "Tested for successful validation.";

	// build bad Source object
	processingformats::Source badSourceObject;
	badSourceObject.agencyId = std::string(AGENCYID);

	result = false;
	try {
		// call validation
		result = badSourceObject.isValid();
	} catch (const std::exception &) {
		// don't care
	}

	// check return code
	ASSERT_EQ(result, false)<< "Tested for unsuccessful validation.";
}
