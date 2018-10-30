/*****************************************
 * This file is documented for Doxygen.
 * If you modify this file please update
 * the comments so that Doxygen will still
 * be able to work.
 ****************************************/
#ifndef PROCESSING_PICK_H
#define PROCESSING_PICK_H

#include <string>
#include <exception>
#include <vector>

#include <Site.h>
#include <Source.h>

namespace processingformats {
/**
 * \brief processingformats Pick conversion class
 *
 * The processingformats Pick class is a conversion class used to create, parse,
 * and validate Pick data as part of processing data.
 *
 */
class Pick : public ProcessingBase {
 public:
	/**
	 * \brief Pick constructor
	 *
	 * The constructor for the Pick class.
	 * Initializes members to null values.
	 */
	Pick();

	/**
	 * The advanced constructor for the Pick class. Initializes members to
	 * provided values.
	 *
	 * \param newID
	 *            - A std::string containing the id to use
	 * \param newStation
	 *            - A std::string containing the station to use
	 * \param newChannel
	 *            - A std::string containing the channel to use
	 * \param newNetwork
	 *            - A std::string containing the network to use
	 * \param newLocation
	 *            - A std::string containing the location to use
	 * \param newAgencyID
	 *            - A std::string containing the agencyid to use
	 * \param newAuthor
	 *            - A std::string containing the author to use
	 * \param newType
	 *            - A std::string containing the type to use
	 * \param newTime
	 *            - A double containing the time to use
	 * \param newAffinity
	 *            - A double containing the affinity to use
	 * \param newQuality
	 *            - A double containing the quality to use
	 * \param newUse
	 *            - A bool containing the use flag to use
	 * \param newPickedPhase
	 *            - A std::string containing the picked phase to use
	 * \param newAssociatedPhase
	 *            - A std::string containing the associated phase to use
	 * \param newLocatedPhase
	 *            - A std::string containing the located phase to use, empty string
	 *            to omit
	 * \param newResidual
	 *            - A double containing the residual to use, null to omit
	 * \param newDistance
	 *            - A double containing the distance to use, null to omit
	 * \param newAzimuth
	 *            - A double containing the azimuth to use, null to omit
	 * \param newWeight
	 *            - A double containing the weight to use, null to omit
	 * \param newImportance
	 *            - A double containing the importance to use, null to omit
	 */
	Pick(std::string newID, std::string newStation, std::string newChannel,
			std::string newNetwork, std::string newLocation,
			std::string newAgencyID, std::string newAuthor, std::string newType,
			double newTime, double newAffinity, double newQuality, bool newUse,
			std::string newPickedPhase, std::string newAssociatedPhase,
			std::string newLocatedPhase, double newResidual, double newDistance,
			double newAzimuth, double newWeight, double newImportance);

	/**
	 * The alternate advanced constructor for the Pick class. Initializes
	 * members to provided values.
	 *
	 * \param newID
	 *            - A std::string containing the id to use
	 * \param newSite
	 *            - A Site containing the site to use
	 * \param newSource
	 *            - A Source containing the source to use
	 * \param newTime
	 *            - A double containing the time to use
	 * \param newAffinity
	 *            - A double containing the affinity to use
	 * \param newQuality
	 *            - A double containing the quality to use
	 * \param newUse
	 *            - A bool containing the use flag to use
	 * \param newPickedPhase
	 *            - A std::string containing the picked phase to use
	 * \param newAssociatedPhase
	 *            - A std::string containing the associated phase to use
	 * \param newLocatedPhase
	 *            - A std::string containing the located phase to use, empty string
	 *            to omit
	 * \param newResidual
	 *            - A double containing the residual to use, null to omit
	 * \param newDistance
	 *            - A double containing the distance to use, null to omit
	 * \param newAzimuth
	 *            - A double containing the azimuth to use, null to omit
	 * \param newWeight
	 *            - A double containing the weight to use, null to omit
	 * \param newImportance
	 *            - A double containing the importance to use, null to omit
	 */
	Pick(std::string newID, Site newSite, Source newSource, double newTime,
			double newAffinity, double newQuality, bool newUse,
			std::string newPickedPhase, std::string newAssociatedPhase,
			std::string newLocatedPhase, double newResidual, double newDistance,
			double newAzimuth, double newWeight, double newImportance);

	/**
	 * \brief Pick advanced constructor
	 *
	 * The advanced constructor for the Pick class.
	 * Converts the provided object from a rapidjson::Value, populating members
	 * \param json - A rapidjson::Value document.
	 */
	explicit Pick(rapidjson::Value &json); // NOLINT

	/**
	 * \brief Pick copy constructor
	 *
	 * The copy constructor for the Pick class.
	 * Copies the provided object from a Pick, populating members
	 * \param newPick - A Pick.
	 */
	Pick(const Pick & newPick);

	/**
	 * \brief Pick destructor
	 *
	 * The destructor for the Pick class.
	 */
	~Pick();

	/**
	 * \brief Convert to json object function
	 *
	 * Converts the contents of the class to a json object
	 * \param json - a reference to the rapidjson::Value document to fill in with
	 * the class contents.
	 * \param allocator - A reference to the rapidjson allocator used
	 * \return Returns rapidjson::Value & if successful
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
	 * \brief pick id
	 *
	 * A required std::string containing the id of this pick message
	 */
	std::string id;

	/**
	 * \brief pick site
	 *
	 * A required processingformats::site containing the site for this pick
	 * message
	 */
	processingformats::Site site;

	/**
	 * \brief pick source
	 *
	 * A required processingformats::source containing the source for this pick
	 * message
	 */
	processingformats::Source source;

	/**
	 * \brief pick time
	 *
	 * A required double containing the time for this pick
	 */
	double time;

	/**
	 * Required double containing the affinity
	 */
	double affinity;

	/**
	 * Required double containing the quality
	 */
	double quality;

	/**
	 * Required boolean containing the use flag
	 */
	bool use;

	/**
	 * \brief picked phase
	 *
	 * A required std::string containing the picked phase for this pick
	 */
	std::string pickedPhase;

	/**
	 * \brief associated phase
	 *
	 * A required std::string containing the associated phase for this pick
	 */
	std::string associatedPhase;

	/**
	 * \brief located phase
	 *
	 * An optional (output) std::string containing the located phase
	 */
	std::string locatedPhase;

	/**
	 * \brief residual
	 *
	 * An optional (output) double containing the lresidual
	 */
	double residual;

	/**
	 * \brief distance
	 *
	 * An optional (output) double containing the distance
	 */
	double distance;

	/**
	 * \brief azimuth
	 *
	 * An optional (output) double containing the azimuth
	 */
	double azimuth;

	/**
	 * \brief weight
	 *
	 * An optional (output) double containing the weight
	 */
	double weight;

	/**
	 * \brief importance
	 *
	 * An optional (output) double containing the importance
	 */
	double importance;
};
}  // namespace processingformats
#endif  // PROCESSING_SOURCE_H
