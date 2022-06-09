/*****************************************
 * This file is documented for Doxygen.
 * If you modify this file please update
 * the comments so that Doxygen will still
 * be able to work.
 ****************************************/
#ifndef PROCESSING_BASE_H
#define PROCESSING_BASE_H

#include <string>
#include <vector>
#include <cmath>

#include "util.h" // NOLINT

namespace processingformats {

/**
 * \brief processingformats base class
 *
 * The ProcessingBase is the base class for other objects in the processing
 * formats namespace
 */
class ProcessingBase {
 public:
	/**
	 * \brief ProcessingBase constructor
	 *
	 * The constructor for the ProcessingBase class.
	 * Initializes members to null values.
	 */
	ProcessingBase();

	/**
	 * \brief site destructor
	 *
	 * The destructor for the processingbase class.
	 */
	~ProcessingBase();

	/**
	 * \brief Convert to json value function
	 *
	 * Converts the contents of the class to a json object
	 * \param json - a reference to the rapidjson::Value to fill in with
	 * the class contents.
	 * \param allocator - rapidjson::MemoryPoolAllocator to use
	 * \return A reference to the filled in rapidjson::Value
	 */
	virtual rapidjson::Value & toJSON(
			rapidjson::Value &json, // NOLINT
			rapidjson::MemoryPoolAllocator<rapidjson::CrtAllocator> &allocator) = 0; // NOLINT

	/**
	 * \brief Validates the values in the class
	 *
	 * Validates the values contained in the class
	 * \return Returns 1 if successful, 0 otherwise
	 */
	virtual bool isValid();

	/**
	 * \brief Gets any errors in the class
	 *
	 * Gets any formatting errors in the class
	 * \return Returns a std::vector<std::string> containing the errors
	 */
	virtual std::vector<std::string> getErrors() = 0;
};
}  // namespace processingformats
#endif  // PROCESSING_BASE_H
