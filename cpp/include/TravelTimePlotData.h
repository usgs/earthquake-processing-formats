/*****************************************
 * This file is documented for Doxygen.
 * If you modify this file please update
 * the comments so that Doxygen will still
 * be able to work.
 ****************************************/
#ifndef PROCESSING_TRAVELTIMEPLOTDATA_H
#define PROCESSING_TRAVELTIMEPLOTDATA_H

#include <string>
#include <exception>
#include <vector>

#include "base.h" // NOLINT
#include "TravelTimePlotDataBranch.h" // NOLINT

namespace processingformats {

/**
 * \brief processingformats TravelTimePlotData conversion class
 *
 * The processingformats TravelTimePlotData class is a conversion class
 * used to create, parse, and validate travel time plot data samples as part of
 * processingformatsdata.
 *
 */
class TravelTimePlotData : public ProcessingBase {
 public:
	/**
	 * \brief TravelTimePlotData constructor
	 *
	 * The constructor for the TravelTimePlotData class.
	 * Initializes members to null values.
	 */
	TravelTimePlotData();

	/**
	 * \brief TravelTimePlotData advanced constructor
	 *
	 * The advanced constructor for the TravelTimePlotData class.
	 * Initializes members to provided values.
	 *
	 * \param newMaximumTravelTime - A double containing the  maximum travel time 
	 * in seconds
	 * \param newBranches - A std::vector of TravelTimePlotDataBranch objects
	 * objects defining the branches
	 */
	TravelTimePlotData(
			double newMaximumTravelTime,
			std::vector<processingformats::TravelTimePlotDataBranch> newBranches);

	/**
	 * \brief TravelTimePlotData advanced constructor
	 *
	 * The advanced constructor for the TravelTimePlotData class.
	 * Constructs the object from a rapidjson::Value, populating members
	 * \param json - A reference to a populated rapidjson::Value to use
	 */
	explicit TravelTimePlotData(rapidjson::Value &json); // NOLINT

	/**
	 * \brief TravelTimePlotData copy constructor
	 *
	 * The copy constructor for the TravelTimePlotData class.
	 * Copies the provided object from a TravelTimePlotData, populating members
	 * \param newTravelTimePlotData - A TravelTimePlotData.
	 */
	TravelTimePlotData(const TravelTimePlotData & newTravelTimePlotData);

	/**
	 * \brief TravelTimePlotData destructor
	 *
	 * The destructor for the TravelTimePlotData class.
	 */
	~TravelTimePlotData();

	/**
	 * \brief Convert to json object function
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
	 * \return Returns a std::vector<std::string> containing the errors, empty
	 * vector if there are no errors.
	 */
	std::vector<std::string> getErrors() override;

	/**
	 * \brief type identifier
	 *
	 * A required std::string containing the type of this message.
	 */
	std::string type;

	/**
	 * \brief travel time
	 *
	 * A required double containing the maximum travel time in seconds
	 */
	double maximumTravelTime;

	/**
	 * \brief branch data vector
	 *
	 * A required vector of TravelTimePlotDataBranch objects defining the branches
	 */
	std::vector<processingformats::TravelTimePlotDataBranch> branches;
};
}  // namespace processingformats
#endif  // PROCESSING_TRAVELTIMEPLOTDATA_H
