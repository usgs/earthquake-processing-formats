#include <Source.h>

#include <string>
#include <limits>
#include <vector>

// JSON Keys
#define AGENCYID_KEY "AgencyID"
#define AUTHOR_KEY "Author"
#define TYPE_KEY "Type"

namespace processingformats {
Source::Source() {
	agencyId = "";
	author = "";
	type = "";
}

Source::Source(std::string newAgencyId, std::string newAuthor,
		std::string newType) {
	agencyId = newAgencyId;
	author = newAuthor;
	type = newType;
}

Source::Source(rapidjson::Value &json) {
	// required values
	// agencyId
	if ((json.HasMember(AGENCYID_KEY) == true)
			&& (json[AGENCYID_KEY].IsString() == true)) {
		agencyId = std::string(json[AGENCYID_KEY].GetString(),
								json[AGENCYID_KEY].GetStringLength());
	} else {
		agencyId = "";
	}

	// author
	if ((json.HasMember(AUTHOR_KEY) == true)
			&& (json[AUTHOR_KEY].IsString() == true)) {
		author = std::string(json[AUTHOR_KEY].GetString(),
								json[AUTHOR_KEY].GetStringLength());
	} else {
		author = "";
	}

	// type
	if ((json.HasMember(TYPE_KEY) == true)
			&& (json[TYPE_KEY].IsString() == true)) {
		type = std::string(json[TYPE_KEY].GetString(),
								json[TYPE_KEY].GetStringLength());
	} else {
		type = "";
	}
}

Source::Source(const Source & newSource) {
	agencyId = newSource.agencyId;
	author = newSource.author;
	type = newSource.type;
}

Source::~Source() {
	agencyId = "";
	author = "";
	type = "";
}

rapidjson::Value & Source::toJSON(
		rapidjson::Value &json,
		rapidjson::MemoryPoolAllocator<rapidjson::CrtAllocator> &allocator) {
	json.SetObject();

	// required values
	// agencyId
	if (agencyId != "") {
		rapidjson::Value agencyIdValue;
		agencyIdValue.SetString(rapidjson::StringRef(agencyId.c_str()),
								allocator);
		json.AddMember(AGENCYID_KEY, agencyIdValue, allocator);
	}

	// author
	if (author != "") {
		rapidjson::Value authorValue;
		authorValue.SetString(rapidjson::StringRef(author.c_str()), allocator);
		json.AddMember(AUTHOR_KEY, authorValue, allocator);
	}

	// type
	if (type != "") {
		rapidjson::Value typeValue;
		typeValue.SetString(rapidjson::StringRef(type.c_str()), allocator);
		json.AddMember(TYPE_KEY, typeValue, allocator);
	}

	return (json);
}

std::vector<std::string> Source::getErrors() {
	std::vector<std::string> errorlist;

	// agencyId
	if (agencyId == "") {
		// empty agencyid
		errorlist.push_back("Empty AgencyID in Source class.");
	}

	// author
	if (author == "") {
		// empty author
		errorlist.push_back("Empty Author in Source class.");
	}

	// since agencyid and author are free text strings, no further validation is
	// required for them

	// type
	if (type == "") {
		// empty type
		errorlist.push_back("Empty Type in Source class.");
	} else {
		bool match = false;
		if (type == "Unknown") {
			match = true;
		} else if (type == "LocalHuman") {
			match = true;
		} else if (type == "LocalAutomatic") {
			match = true;
		} else if (type == "ContributedHuman") {
			match = true;
		} else if (type == "ContributedAutomatic") {
			match = true;
		} else {
			match = false;
		}

		if (!match) {
			// invalid type
			errorlist.push_back("Invalid Type in Source Class.");
		}
	}

	// return the list of errors
	return (errorlist);
}

bool Source::isEmpty() {
	if (agencyId != "") {
		return (false);
	}
	if (author != "") {
		return (false);
	}
	if (type != "") {
		return (false);
	}

	return (true);
}
}  // namespace processingformats
