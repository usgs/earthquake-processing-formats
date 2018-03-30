/*****************************************
 * This file is documented for Doxygen.
 * If you modify this file please update
 * the comments so that Doxygen will still
 * be able to work.
 ****************************************/
#ifndef PROCESSING_TRAVELTIMEREQUEST_H
#define PROCESSING_TRAVELTIMEREQUEST_H

#include <base.h>
#include <TravelTimeData.h>
#include <TravelTimePlotData.h>

#include <string>
#include <exception>
#include <vector>

namespace processingformats {

/**
 * \brief processingformats TravelTimeRequest conversion class
 *
 * The processingformats TravelTimeRequest class is a conversion class used to
 * create, parse, and validate travel time data as part of processingformats
 * data.
 *
 */
class TravelTimeRequest : public ProcessingBase {
 public:
	/**
	 * \brief TravelTimeRequest constructor
	 *
	 * The constructor for the TravelTimeRequest class.
	 * Initializes members to null values.
	 */
	TravelTimeRequest();

	/**
	 * brief TravelTimeRequest Advanced constructor
	 *
	 * The advanced constructor for the TravelTimeRequest class. Initializes
	 * members to provided values.
	 *
	 * \param newType
	 *            - A String containing the request type, "Standard", "Plot", or
	 *            "PlotStatistics", defaults to standard
	 * \param newDistance
	 *            - A double containing the source-receiver distance in degrees
	 * \param newElevation
	 *            - A double containing the receiver elevation relative to the
	 *            WGS84 datum in kilometers
	 * \param newLatitude
	 *            - An optional double containing the geographic receiver
	 *            latitude in degrees, std::numeric_limits<double>::quiet_NaN()
	 *            to omit
	 * \param newLongitude
	 *            - An optional double containing the geographic receiver
	 *            longitude in degrees, std::numeric_limits<double>::quiet_NaN()
	 *            to omit
	 * \param newData
	 *            - A std::vector&lt;TravelTimeData&gt; containing the returned
	 *            travel time data
	 * \param newPlotData
	 *            - A std::vector&lt;TravelTimePlotData&gt;  containing the
	 *            returned travel time plot data
	 */
	TravelTimeRequest(std::string newType, double newDistance,
						double newElevation, double newLatitude,
						double newLongitude,
						std::vector<TravelTimeData> newData,
						std::vector<TravelTimePlotData> newPlotData);

	/**
	 * \brief TravelTimeRequest Advanced constructor
	 *
	 * The advanced constructor for the TravelTimeRequest class. Initializes
	 * members to provided values.
	 *
	 * \param newPhase - A std::string containing the seismic phase code
	 * \param newTravelTime - A double containing the travel time in seconds
	 * \param newDistanceDerivative - A double containing the derivative with
	 * respect to distance of the travel time in seconds/degree
	 * \param newDepthDerivative - A double containing the derivative with
	 *  respect to ray parameter of the travel time in degrees/second
	 * \param newRayDerivative - A double containing the derivative with respect
	 * to ray parameter of the travel time in degrees/second
	 * \param newStatisticalSpread - A double containing the observed travel
	 * time scatter in seconds
	 * \param newObservability - A double containing the statistical
	 * observability of the seismic phase
	 * \param newTeleseismicPhaseGroup - A std::string containing the
	 * teleseismic phase group identifier
	 * \param newAuxiliaryPhaseGroup - A std::string containing the auxiliary
	 * phase group identifier
	 * \param newLocationUseFlag - A bool containing the flag indicating whether
	 * the phase may be used in a location
	 * \param newAssociationWeightFlag - A bool containing the flag indicating
	 * whether the phase should be down weighted in association
	 */
	TravelTimeRequest(std::string newPhase, double newTravelTime,
						double newDistanceDerivative, double newDepthDerivative,
						double newRayDerivative, double newStatisticalSpread,
						double newObservability,
						std::string newTeleseismicPhaseGroup,
						std::string newAuxiliaryPhaseGroup,
						bool newLocationUseFlag, bool newAssociationWeightFlag);

	/**
	 * \brief TravelTimeRequest advanced constructor
	 *
	 * The advanced constructor for the TravelTimeRequest class.
	 * Converts the provided object from a json::Object, populating members
	 * \param jsondocument - A json document.
	 */
	explicit TravelTimeRequest(rapidjson::Value &json);

	/**
	 * \brief TravelTimeRequest copy constructor
	 *
	 * The copy constructor for the TravelTimeRequest class.
	 * Copies the provided object from a TravelTimeRequest, populating members
	 * \param newTravelTimeRequest - A TravelTimeRequest.
	 */
	TravelTimeRequest(const TravelTimeRequest & newTravelTimeRequest);

	/**
	 * \brief TravelTimeRequest destructor
	 *
	 * The destructor for the TravelTimeRequest class.
	 */
	~TravelTimeRequest();

	/**
	 * \brief Convert to json object function
	 *
	 * Converts the contents of the class to a json object
	 * \param jsondocument - a reference to the json document to fill in with
	 * the class contents.
	 * \return Returns rapidjson::Value & if successful
	 */
	rapidjson::Value & toJSON(
			rapidjson::Value &json,
			rapidjson::MemoryPoolAllocator<rapidjson::CrtAllocator> &allocator)
					override;

	/**
	 * \brief Gets any errors in the class
	 *
	 * Gets any formatting errors in the class
	 * \return Returns a std::vector<std::string> containing the errors
	 */
	std::vector<std::string> getErrors() override;

	/**
	 * \brief type identifier
	 *
	 * A required std::string containing the type of this message.
	 */
	std::string type;

	/**
	 * \brief distance
	 *
	 * A required double containing source receiver distance in degrees
	 */
	double distance;

	/**
	 * \brief elevation
	 *
	 * A required double containing the receiver elevation relative to the WGS84
	 * datum in kilometers
	 */
	double elevation;

	/**
	 * \brief latitude
	 *
	 * An optional double containing the geographic receiver latitude in degrees
	 */
	double latitude;

	/**
	 * \brief ray derivative
	 *
	 * An optional double containing the geographic receiver longitude in
	 * degrees
	 */
	double longitude;

	/**
	 * \brief data
	 *
	 * Returned travel time data (empty for requests)
	 */
	std::vector<processingformats::TravelTimeData> data;

	/**
	 * \brief plot data
	 *
	 * Returned travel time plot data (empty for requests)
	 */
	std::vector<processingformats::TravelTimePlotData> plotData;
};
}  // namespace processingformats
#endif  // PROCESSING_TRAVELTIMEREQUEST_H
