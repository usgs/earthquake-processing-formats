/*****************************************
 * This file is documented for Doxygen.
 * If you modify this file please update
 * the comments so that Doxygen will still
 * be able to work.
 ****************************************/
#ifndef PROCESSING_TRAVELTIMESESSION_H
#define PROCESSING_TRAVELTIMESESSION_H

#include <base.h>

#include <string>
#include <exception>
#include <vector>

namespace processingformats {

/**
 * \brief processingformats TravelTimeSession conversion class
 *
 * The processingformats TravelTimeSession class is a conversion class used to
 * create, parse, and validate a travel time session as part of processingformats
 * data.
 *
 */
class TravelTimeSession : public ProcessingBase {
 public:
	/**
	 * \brief TravelTimeSession constructor
	 *
	 * The constructor for the TravelTimeSession class.
	 * Initializes members to null values.
	 */
	TravelTimeSession();

	/**
	 * \brief TravelTimeSession Advanced constructor
	 *
	 * The advanced constructor for the TravelTimeSession class. Initializes
	 * members to provided values.
	 *
	 * /param newSourceDepth - A double containing source depth in kilometers
	 * /param newEarthModel - An optional string containing the earth model to
	 * use, empty string to omit
	 * /param newPhaseTypes - An optional std::vector of std::strings containing
	 * phases desired, empty vector to omit
	 * /param newSourceLatitude - An optional double containing source latitude
	 * in degrees, std::numeric_limits<double>::quiet_NaN() to omit
	 * /param newSourceLongitude - An optional double containing source
	 * longitude in degrees, std::numeric_limits<double>::quiet_NaN() to omit
	 * /param newReturnAllPhases - A bool containing whether to return all
	 * phases
	 * /param newReturnBackBranches - A bool containing whether to return back
	 * branches
	 * /param newConvertTectonic - A bool containing whether to convert tectonic
	 * phases
	 * /param newUseRSTT - A bool containing whether to use rstt
	 * /param newIsPlot  - A bool containing whether this session is a plot
	 * session
	 */
	TravelTimeSession(double newSourceDepth, std::string newEarthModel,
						std::vector<std::string> newPhaseTypes,
						double newSourceLatitude, double newSourceLongitude,
						bool newReturnAllPhases, bool newReturnBackBranches,
						bool newConvertTectonic, bool newUseRSTT,
						bool newIsPlot);

	/**
	 * \brief TravelTimeSession advanced constructor
	 *
	 * The advanced constructor for the TravelTimeSession class.
	 * Converts the provided object from a json::Object, populating members
	 * \param jsondocument - A json document.
	 */
	explicit TravelTimeSession(rapidjson::Value &json);

	/**
	 * \brief TravelTimeSession copy constructor
	 *
	 * The copy constructor for the TravelTimeSession class.
	 * Copies the provided object from a TravelTimeSession, populating members
	 * \param newTravelTimeSession - A TravelTimeSession.
	 */
	TravelTimeSession(const TravelTimeSession & newTravelTimeSession);

	/**
	 * \brief TravelTimeSession destructor
	 *
	 * The destructor for the TravelTimeSession class.
	 */
	~TravelTimeSession();

	/**
	 * \brief Convert to json object function
	 *
	 * Converts the contents of the class to a json object
	 * \param jsondocument - a reference to the json document to fill in with
	 * the class contents.
	 * \return Returns populated rapidjson::Value & if successful, empty
	 *  rapidjson::Value & if not
	 */
	rapidjson::Value & toJSON(
			rapidjson::Value &json,
			rapidjson::MemoryPoolAllocator<rapidjson::CrtAllocator> &allocator)
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
	 * \brief source depth
	 *
	 * A required double containing the geographic source depth in kilometers
	 */
	double sourceDepth;

	/**
	 * \brief earth model
	 *
	 * An optional string containing the earth model to use, defaults to the
	 * AK135 earth model
	 */
	std::string earthModel;

	/**
	 * \brief phases list
	 *
	 * Optional vector of strings listing the phase types desired
	 */
	std::vector<std::string> phaseTypes;

	/**
	 * \brief source latitude
	 *
	 * An optional double containing the geographic source latitude in degrees
	 */
	double sourceLatitude;

	/**
	 * \brief source longitude
	 *
	 * An optional double containing the geographic source longitude in
	 * degrees
	 */
	double sourceLongitude;

	/**
	 * \brief return all phases
	 *
	 * An optional boolean flag that indicates whether to return all phases,
	 * defaults to false
	 */
	bool returnAllPhases;

	/**
	 * \brief return back branches
	 *
	 * An optional boolean flag that indicates whether to return all arrivals
	 * of all phases, defaults to false
	 */
	bool returnBackBranches;

	/**
	 * \brief return back branches
	 *
	 * An optional boolean flag that indicates whether theRSTT regional
	 * travel-time model is to be used, defaults to false
	 */
	bool convertTectonic;

	/**
	 * \brief return back branches
	 *
	 * An optional boolean flag that indicates whether to return all arrivals
	 * of all phases, defaults to false
	 */
	bool useRSTT;

	/**
	 * is plot
	 * An optional boolean flag that indicates whether the travel-time session
	 * is for plotting rather than lookups
	 */
	bool isPlot;
};
}  // namespace processingformats
#endif  // PROCESSING_TRAVELTIMESESSION_H
