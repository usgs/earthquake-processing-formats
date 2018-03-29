/*****************************************
 * This file is documented for Doxygen.
 * If you modify this file please update
 * the comments so that Doxygen will still
 * be able to work.
 ****************************************/
#ifndef PROCESSING_TRAVELTIMEDATA_H
#define PROCESSING_TRAVELTIMEDATA_H

#include <base.h>

#include <string>
#include <exception>
#include <vector>

namespace processingformats {

/**
 * \brief processingformats TravelTimeData conversion class
 *
 * The processingformats TravelTimeData class is a conversion class used to
 * create, parse, and validate travel time data as part of processingformats
 * data.
 *
 */
class TravelTimeData : public ProcessingBase {
 public:
	/**
	 * \brief TravelTimeData constructor
	 *
	 * The constructor for the TravelTimeData class.
	 * Initializes members to null values.
	 */
	TravelTimeData();

	/**
	 * \brief TravelTimeData Advanced constructor
	 *
	 * The advanced constructor for the TravelTimeData class. Initializes
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
	TravelTimeData(std::string newPhase, double newTravelTime,
					double newDistanceDerivative, double newDepthDerivative,
					double newRayDerivative, double newStatisticalSpread,
					double newObservability,
					std::string newTeleseismicPhaseGroup,
					std::string newAuxiliaryPhaseGroup, bool newLocationUseFlag,
					bool newAssociationWeightFlag);

	/**
	 * \brief TravelTimeData advanced constructor
	 *
	 * The advanced constructor for the TravelTimeData class.
	 * Converts the provided object from a json::Object, populating members
	 * \param jsondocument - A json document.
	 */
	explicit TravelTimeData(rapidjson::Value &json);

	/**
	 * \brief TravelTimeData copy constructor
	 *
	 * The copy constructor for the TravelTimeData class.
	 * Copies the provided object from a TravelTimeData, populating members
	 * \param newTravelTimeData - A TravelTimeData.
	 */
	TravelTimeData(const TravelTimeData & newTravelTimeData);

	/**
	 * \brief TravelTimeData destructor
	 *
	 * The destructor for the TravelTimeData class.
	 */
	~TravelTimeData();

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
	 * \brief phase identifier
	 *
	 * A required std::string containing the seismic phase code
	 */
	std::string phase;

	/**
	 * \brief travel time
	 *
	 * A required double containing the travel time
	 */
	double travelTime;

	/**
	 * \brief distance derivative
	 *
	 * A required double containing the derivative with respect to distance of
	 * the travel time in seconds/degree.
	 */
	double distanceDerivative;

	/**
	 * \brief depth derivative
	 *
	 * A required double containing the derivative with respect to depth of
	 * the travel time in seconds/kilometer.
	 */
	double depthDerivative;

	/**
	 * \brief ray derivative
	 *
	 * A required double containing the derivative with respect to ray parameter
	 * of the travel time in degrees/second.
	 */
	double rayDerivative;

	/**
	 * \brief statistical spread
	 *
	 * A required double containing the statistical observed travel time scatter
	 * in seconds.
	 */
	double statisticalSpread;

	/**
	 * \brief observability
	 *
	 * A required double containing the statistical observability of the seismic
	 * phase
	 */
	double observability;

	/**
	 * \brief teleseismic phase group
	 *
	 * A required std::string containing the teleseismic phase group identifier
	 */
	std::string teleseismicPhaseGroup;

	/**
	 * \brief auxiliary phase group
	 *
	 * A required std::string containing the auxiliary phase group identifier
	 */
	std::string auxiliaryPhaseGroup;

	/**
	 * \brief location use flag
	 *
	 * A required boolean flag indicating whether the phase may be used in a
	 * location.
	 */
	bool locationUseFlag;

	/**
	 * \brief association weight flag
	 *
	 * A required boolean flag indicating whether the phase should be down
	 * weighted in association.
	 */
	bool associationWeightFlag;
};
}  // namespace processingformats
#endif  // PROCESSING_TRAVELTIMEDATA_H
