/*****************************************
 * This file is documented for Doxygen.
 * If you modify this file please update
 * the comments so that Doxygen will still
 * be able to work.
 ****************************************/
#ifndef PROCESSING_LOCATIONREQUEST_H
#define PROCESSING_LOCATIONREQUEST_H

#include <base.h>
#include <Pick.h>
#include <Source.h>
#include <LocationResult.h>

#include <string>
#include <exception>
#include <vector>

namespace processingformats {
/**
 * \brief processingformats LocationRequest conversion class
 *
 * The processingformats LocationRequest class is a conversion class used to create, parse,
 * and validate LocationRequest data as part of processing data.
 *
 */
class LocationRequest : public ProcessingBase {
 public:
	/**
	 * \brief LocationRequest constructor
	 *
	 * The constructor for the LocationRequest class.
	 * Initializes members to null values.
	 */
	LocationRequest();

	/**
	 * \brief LocationRequest advanced constructor
	 *
	 * The advanced constructor for the LocationRequest class.
	 * Initializes members to provided values.
	 *
	 * \param newID - A std::string containing the optional ID
   * \param newAgencyID - A std::string containing the agencyid to Use
   * \param newAuthor - A std::string containing the author to Use
   * \param newType - A std::string containing the type to Use
   * \param newLocType - A std::string containing the name of the algorithm this 
   *  request is valid for
   * \param newEarthModel - A std::string containing the name of theTravel Time 
   *  Earth Model to use
   * \param newSourceLatitude - A double containing the latitude to use
   * \param newSourceLongitude - A double containing the longitude to use
   * \param newSourceOriginTime - A double containing the origin time to use
   * \param newSourceDepth - A double containing the depth to use
   * \param newInputData - A std::vector&lt;processingformats::Pick&gt; newPickData 
   *  containing the data to use for this location
   * \param newIsLocationNew - A bool indicating whether the location is now, 
   * \param newIsLocationHeld - A bool indicating whether to hold the location, 
   * \param newIsDepthHeld - A bool indicating whether to hold the depth, 
   * \param newIsBayesianDepth - A bool indicating whether to use the baysian 
   * \param newBayesianDepth - A double containing the bayesian depth to use, 
   *  std::numeric_limits<double>::quiet_NaN() to omit
   * \param newBayesianSpread - A double containing the bayesian spread to use, 
   *  std::numeric_limits<double>::quiet_NaN() to omit
   * \param newUseSVD - A bool indicating whether to use SVD
   */
	LocationRequest(
      std::string newID,
      std::string newAgencyID,
      std::string newAuthor,
      std::string newType,
      std::string newLocType,
      std::string newEarthModel,
      double newSourceLatitude,
      double newSourceLongitude,
      double newSourceOriginTime,
      double newSourceDepth,
      std::vector<processingformats::Pick> newInputData,
      bool newIsLocationNew,
      bool newIsLocationHeld,
      bool newIsDepthHeld,
      bool newIsBayesianDepth,
      double newBayesianDepth,
      double newBayesianSpread,
      bool newUseSVD);

  /**
	 * \brief LocationRequest advanced constructor
	 *
	 * The advanced constructor for the LocationRequest class.
	 * Initializes members to provided values.
	 *
	 * \param newID - A std::string containing the optional ID
   * \param newSource - A Source containing the source to use
   * \param newLocType - A std::string containing the name of the algorithm this 
   *  request is valid for
   * \param newEarthModel - A std::string containing the name of theTravel Time 
   *  Earth Model to use
   * \param newSourceLatitude - A double containing the latitude to use
   * \param newSourceLongitude - A double containing the longitude to use
   * \param newSourceOriginTime - A double containing the origin time to use
   * \param newSourceDepth - A double containing the depth to use
   * \param newInputData - A std::vector&lt;processingformats::Pick&gt; newPickData 
   *  containing the data to use for this location
   * \param newIsLocationNew - A bool indicating whether the location is now, 
   * \param newIsLocationHeld - A bool indicating whether to hold the location, 
   * \param newIsDepthHeld - A bool indicating whether to hold the depth, 
   * \param newIsBayesianDepth - A bool indicating whether to use the baysian 
   * \param newBayesianDepth - A double containing the bayesian depth to use, 
   *  std::numeric_limits<double>::quiet_NaN() to omit
   * \param newBayesianSpread - A double containing the bayesian spread to use, 
   *  std::numeric_limits<double>::quiet_NaN() to omit
   * \param newUseSVD - A bool indicating whether to use SVD
   */
	LocationRequest(
      std::string newID,
      processingformats::Source newSource
      std::string newLocType,
      std::string newEarthModel,
      double newSourceLatitude,
      double newSourceLongitude,
      double newSourceOriginTime,
      double newSourceDepth,
      std::vector<processingformats::Pick> newInputData,
      bool newIsLocationNew,
      bool newIsLocationHeld,
      bool newIsDepthHeld,
      bool newIsBayesianDepth,
      double newBayesianDepth,
      double newBayesianSpread,
      bool newUseSVD);

	/**
	 * \brief LocationRequest advanced constructor
	 *
	 * The advanced constructor for the LocationRequest class.
	 * Converts the provided object from a json::Object, populating members
	 * \param jsondocument - A json document.
	 */
	explicit LocationRequest(rapidjson::Value &json); // NOLINT

	/**
	 * \brief LocationRequest copy constructor
	 *
	 * The copy constructor for the LocationRequest class.
	 * Copies the provided object from a LocationRequest, populating members
	 * \param newLocationRequest - A LocationRequest.
	 */
	LocationRequest(const LocationRequest & newLocationRequest);

	/**
	 * \brief LocationRequest destructor
	 *
	 * The destructor for the LocationRequest class.
	 */
	~LocationRequest();

	/**
	 * \brief Convert to json object function
	 *
	 * Converts the contents of the class to a json object
	 * \param jsondocument - a reference to the json document to fill in with
	 * the class contents.
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
	 * Optional std::string containing the identifier for this LocationRequest
	 */
	std::string id;

	/**
	 * \brief pick source
	 *
	 * Optional processingformats::source containing the source for this 
   * LocationRequest
	 */
	processingformats::Source source;

	/**
	 * \brief LocationRequest type string 
	 *
	 * A required std::string containing the type identifier for this LocationRequest.
	 */
	std::string type;

	/**
	 * \brief LocationRequest earth model
	 *
	 * A required std::string containing the earth model for this LocationRequest.
	 */
	std::string earthModel;

	/**
	 * \brief source latitude value
	 *
	 * A required double defining the source latitude of this LocationRequest.
	 */
	double sourceLatitude;

	/**
	 * \brief source longitude value
	 *
	 * A required double defining the sourcelongitude of this LocationRequest.
	 */
	double sourceLongitude;

  /**
	 * \brief source origin time value
	 *
	 * A required double containing the source origin time for this LocationRequest
	 */
	double sourceOriginTime;

	/**
	 * \brief source depth value
	 *
	 * A required double containing the source depth for this LocationRequest.
	 */
	double sourceDepth;

	/**
	 * A required vector of input Pick objects for this LocationRequest
	 */
	std::vector<processingformats::Pick> inputData;

  /**
	 * \brief is location new value
	 *
	 * An optional bool indicating whether the location is new
	 */
	bool isLocationNew;

  /**
	 * \brief is location held value
	 *
	 * An optional bool indicating whether the location is held
	 */
	bool isLocationHeld;

  /**
	 * \brief is depth held value
	 *
	 * An optional bool indicating whether the depth is held
	 */
	bool isDepthHeld;

  /**
	 * \brief is depth bayesian value
	 *
	 * An optional bool indicating whether the depth is bayesian
	 */
	bool isBayesianDepth;

  /**
	 * \brief bayesian depth value
	 *
	 * An optional double containing the bayesian depth for this LocationRequest.
	 */
	double bayesianDepth;

  /**
	 * \brief bayesian spread value
	 *
	 * An optional double containing the bayesian spread for this LocationRequest.
	 */
	double bayesianSpread;

  /**
	 * \brief use svd value
	 *
	 * An optional bool indicating whether to use singular value decomposition
	 */
	bool useSVD;

	/**
	 * \brief output data
	 *
	 * Optional processingformats::LocationResult containing the output data
   * for this LocationRequest
	 */
	processingformats::LocationResult outputData;
};
}  // namespace processingformats
#endif  // PROCESSING_LOCATIONREQUEST_H
