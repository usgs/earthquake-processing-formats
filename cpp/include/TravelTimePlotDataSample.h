/*****************************************
 * This file is documented for Doxygen.
 * If you modify this file please update
 * the comments so that Doxygen will still
 * be able to work.
 ****************************************/
#ifndef PROCESSING_TRAVELTIMEPLOTDATASAMPLE_H
#define PROCESSING_TRAVELTIMEPLOTDATASAMPLE_H

#include <base.h>

#include <string>
#include <exception>
#include <vector>

namespace processingformats {

/**
 * \brief processingformats TravelTimePlotDataSample conversion class
 *
 * The processingformats TravelTimePlotDataSample class is a conversion class
 * used to create, parse, and validate travel time plot data samples as part of
 * processingformatsdata.
 *
 */
class TravelTimePlotDataSample : public ProcessingBase {
 public:
	/**
	 * \brief TravelTimePlotDataSample constructor
	 *
	 * The constructor for the TravelTimePlotDataSample class.
	 * Initializes members to null values.
	 */
	TravelTimePlotDataSample();

	/**
	 * \brief TravelTimePlotDataSample advanced constructor
	 *
	 * The advanced constructor for the TravelTimePlotDataSample class.
	 * Initializes members to provided values.
	 *
	 * \param newDistance - A double containing the distance in degrees
	 * \param newTravelTime - A double containing the travel time in seconds
	 * \param newStatisticalSpread - An optional double containing the observed
	 * travel time scatter in seconds, std::numeric_limits<double>::quiet_NaN()
	 * to omit
	 * \param newObservability - An optional double containing the statistical
	 * observability of the sample std::numeric_limits<double>::quiet_NaN() to
	 * omit
	 */
	TravelTimePlotDataSample(double newDistance, double newTravelTime,
								double newStatisticalSpread,
								double newObservability);

	/**
	 * \brief TravelTimePlotDataSample advanced constructor
	 *
	 * The advanced constructor for the TravelTimePlotDataSample class.
	 * Converts the provided object from a json::Object, populating members
	 * \param json - A reference to a populated rapidjson::Value to use
	 */
	explicit TravelTimePlotDataSample(rapidjson::Value &json); // NOLINT

	/**
	 * \brief TravelTimePlotDataSample copy constructor
	 *
	 * The copy constructor for the TravelTimePlotDataSample class.
	 * Copies the provided object from a TravelTimePlotDataSample, populating members
	 * \param newTravelTimePlotDataSample - A TravelTimePlotDataSample.
	 */
	TravelTimePlotDataSample(
			const TravelTimePlotDataSample & newTravelTimePlotDataSample);

	/**
	 * \brief TravelTimePlotDataSample destructor
	 *
	 * The destructor for the TravelTimePlotDataSample class.
	 */
	~TravelTimePlotDataSample();

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
	 * \brief distance
	 *
	 * A required double containing the distance in degrees
	 */
	double distance;

	/**
	 * \brief travelTime
	 *
	 * A required double containing the travel time in seconds
	 */
	double travelTime;

	/**
	 * \brief statistical spread
	 *
	 * An optional double containing the statistical observed travel time
	 * scatter in seconds.
	 */
	double statisticalSpread;

	/**
	 * \brief observability
	 *
	 * An optional double containing the statistical observability of the
	 * seismic phase
	 */
	double observability;
};
}  // namespace processingformats
#endif  // PROCESSING_TRAVELTIMEPLOTDATASAMPLE_H
