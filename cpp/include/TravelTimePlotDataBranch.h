/*****************************************
 * This file is documented for Doxygen.
 * If you modify this file please update
 * the comments so that Doxygen will still
 * be able to work.
 ****************************************/
#ifndef PROCESSING_TRAVELTIMEPLOTDATABRANCH_H
#define PROCESSING_TRAVELTIMEPLOTDATABRANCH_H

#include <base.h>
#include <TravelTimePlotDataSample.h>

#include <string>
#include <exception>
#include <vector>

namespace processingformats {

/**
 * \brief processingformats TravelTimePlotDataBranch conversion class
 *
 * The processingformats TravelTimePlotDataBranch class is a conversion class
 * used to create, parse, and validate travel time plot data samples as part of
 * processingformatsdata.
 *
 */
class TravelTimePlotDataBranch : public ProcessingBase {
 public:
	/**
	 * \brief TravelTimePlotDataBranch constructor
	 *
	 * The constructor for the TravelTimePlotDataBranch class.
	 * Initializes members to null values.
	 */
	TravelTimePlotDataBranch();

	/**
	 * \brief TravelTimePlotDataBranch advanced constructor
	 *
	 * The advanced constructor for the TravelTimePlotDataBranch class.
	 * Initializes members to provided values.
	 *
	 * \param newPhase - A string containing the phase code
	 * \param newSamples - A std::vector of TravelTimePlotDataSample objets
	 * containing the samples
	 */
	TravelTimePlotDataBranch(
			std::string newPhase,
			std::vector<processingformats::TravelTimePlotDataSample> newSamples);

	/**
	 * \brief TravelTimePlotDataBranch advanced constructor
	 *
	 * The advanced constructor for the TravelTimePlotDataBranch class.
	 * Converts the provided object from a json::Object, populating members
	 * \param json - A reference to a populated rapidjson::Value to use
	 */
	explicit TravelTimePlotDataBranch(rapidjson::Value &json); // NOLINT

	/**
	 * \brief TravelTimePlotDataBranch copy constructor
	 *
	 * The copy constructor for the TravelTimePlotDataBranch class.
	 * Copies the provided object from a TravelTimePlotDataBranch, populating members
	 * \param newTravelTimePlotDataBranch - A TravelTimePlotDataBranch.
	 */
	TravelTimePlotDataBranch(
			const TravelTimePlotDataBranch & newTravelTimePlotDataBranch);

	/**
	 * \brief TravelTimePlotDataBranch destructor
	 *
	 * The destructor for the TravelTimePlotDataBranch class.
	 */
	~TravelTimePlotDataBranch();

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
	 * \brief phase identifier
	 *
	 * A required std::string containing the seismic phase code
	 */
	std::string phase;

	/**
	 * \brief samples vector
	 *
	 * A required vector of TravelTimePlotDataSample objects
	 */
	std::vector<processingformats::TravelTimePlotDataSample> samples;
};
}  // namespace processingformats
#endif  // PROCESSING_TRAVELTIMEPLOTDATABRANCH_H
