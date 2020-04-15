/*****************************************
 * This file is documented for Doxygen.
 * If you modify this file please update
 * the comments so that Doxygen will still
 * be able to work.
 ****************************************/
#ifndef PROCESSING_SOURCE_H
#define PROCESSING_SOURCE_H

#include <string>
#include <exception>
#include <vector>

#include "base.h" // NOLINT

namespace processingformats {
/**
 * \brief processingformats Source conversion class
 *
 * The processingformats Source class is a conversion class used to create,
 * parse, and validate Source data as part of processing data.
 *
 */
class Source : public ProcessingBase {
 public:
	/**
	 * \brief Source constructor
	 *
	 * The constructor for the Source class.
	 * Initializes members to null values.
	 */
	Source();

	/**
	 * \brief Source advanced constructor
	 *
	 * The advanced constructor for the Source class.
	 * Initializes members to provided values.
	 *
	 * \param newAgencyId - A std::string containing the Agency Id to use
	 * \param newAuthor - A std::string containing the Author to use
	 * \param newType - A std::string containing the Type to use
	 */
	Source(std::string newAgencyId, std::string newAuthor, std::string newType);

	/**
	 * \brief Source advanced constructor
	 *
	 * The advanced constructor for the Source class.
	 * Constructs the object from a rapidjson::Value, populating members
	 * \param json - A reference to a populated rapidjson::Value to use
	 */
	explicit Source(rapidjson::Value &json); // NOLINT

	/**
	 * \brief Source copy constructor
	 *
	 * The copy constructor for the Source class.
	 * Copies the provided object from a Source, populating members
	 * \param newSource - A Source.
	 */
	Source(const Source &newSource);

	/**
	 * \brief Source destructor
	 *
	 * The destructor for the Source class.
	 */
	~Source();

	/**
	 * \brief Convert to json value function
	 *
	 * Converts the contents of the class to a json object
	 * \param json - a reference to the rapidjson::Value to fill in with
	 * the class contents.
	 * \param allocator - rapidjson::MemoryPoolAllocator to use
	 * \return A reference to the filled in rapidjson::Value
	 */
	rapidjson::Value & toJSON(
			rapidjson::Value &json, // NOLINT
			rapidjson::MemoryPoolAllocator<rapidjson::CrtAllocator> &allocator) // NOLINT
					override;

	/**
	 * \brief Gets any errors in the class
	 *
	 * Gets any formatting errors in the class
	 * \return Returns a std::vector<std::string> containing the errors
	 */
	std::vector<std::string> getErrors() override;

	/**
	 * \brief Empty check
	 *
	 * Checks to see if this object is empty
	 * \return Returns true if empty, false otherwise.
	 */
	bool isEmpty();

	/**
	 * \brief Source agency identifier
	 *
	 * A required std::string containing an agency identifier for this Source.
	 */
	std::string agencyId;

	/**
	 * \brief Source author
	 *
	 * A required std::string containing the author for this Source.
	 */
	std::string author;

	/**
	 * \brief Source type
	 *
	 * A required std::string containing the type for this Source.
	 */
	std::string type;
};
}  // namespace processingformats
#endif  // PROCESSING_SOURCE_H
