/*****************************************
 * This file is documented for Doxygen.
 * If you modify this file please update
 * the comments so that Doxygen will still
 * be able to work.
 ****************************************/
#ifndef PROCESSING_LOCATIONREQUEST_H
#define PROCESSING_LOCATIONREQUEST_H

#include <string>
#include <exception>
#include <vector>

#include "base.h" // NOLINT
#include "Pick.h" // NOLINT
#include "Source.h" // NOLINT
#include "LocationResult.h" // NOLINT

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
   * \param newEarthModel - A std::string containing the name of the Travel Time 
   *  Earth Model to use, empty string for default ("ak135)"
   * \param newSlabResolution - A std::string containing the name of the slab 
   *  resolution to use, empty string for default ("2spd")
   * \param newSourceLatitude - A double containing the latitude to use in degrees
   * \param newSourceLongitude - A double containing the longitude to use in 
	 *  degrees
   * \param newSourceOriginTime - A double containing the origin time to use in
	 *  epoch seconds
   * \param newSourceDepth - A double containing the depth to use in kilometers
   * \param newInputData - A std::vector&lt;processingformats::Pick&gt; newPickData 
   *  containing the data to use for this location
   * \param newIsLocationNew - A bool indicating whether the location is now, 
   * \param newIsLocationHeld - A bool indicating whether to hold the location, 
   * \param newIsDepthHeld - A bool indicating whether to hold the depth, 
   * \param newIsBayesianDepth - A bool indicating whether to use the baysian 
   * \param newBayesianDepth - A double containing the bayesian depth to use in 
   *  kilometers, std::numeric_limits<double>::quiet_NaN() to omit
   * \param newBayesianSpread - A double containing the bayesian spread to use 
   *  in kilometers, std::numeric_limits<double>::quiet_NaN() to omit
   * \param newUseSVD - A bool indicating whether to use SVD
   * \param newReassessInitialPhaseIDs - A bool indicating whether to use reassess initial phase
   *  identifications
   */
	LocationRequest(
      std::string newID,
      std::string newAgencyID,
      std::string newAuthor,
      std::string newType,
      std::string newLocType,
      std::string newEarthModel,
	  std::string newSlabResolution,
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
      bool newUseSVD,
	  bool newReassessInitialPhaseIDs);

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
   * \param newEarthModel - A std::string containing the name of the Travel Time 
   *  Earth Model to use, empty string for default ("ak135)"
   * \param newSlabResolution - A std::string containing the name of the slab 
   *  resolution to use, empty string for default ("2spd")
   * \param newSourceLatitude - A double containing the latitude to use in degrees
   * \param newSourceLongitude - A double containing the longitude to use in 
	 * degrees
   * \param newSourceOriginTime - A double containing the origin time to use in 
	 * epoch seconds
   * \param newSourceDepth - A double containing the depth to use in kilometers
   * \param newInputData - A std::vector&lt;processingformats::Pick&gt; newPickData 
   *  containing the data to use for this location
   * \param newIsLocationNew - A bool indicating whether the location is now, 
   * \param newIsLocationHeld - A bool indicating whether to hold the location, 
   * \param newIsDepthHeld - A bool indicating whether to hold the depth, 
   * \param newIsBayesianDepth - A bool indicating whether to use the baysian 
   * \param newBayesianDepth - A double containing the bayesian depth to use in 
   *  kilometers, std::numeric_limits<double>::quiet_NaN() to omit
   * \param newBayesianSpread - A double containing the bayesian spread to use 
   *  in kilometers, std::numeric_limits<double>::quiet_NaN() to omit
   * \param newUseSVD - A bool indicating whether to use SVD
   * \param newReassessInitialPhaseIDs - A bool indicating whether to use reassess initial phase
   *  identifications
   */
	LocationRequest(
      std::string newID,
      processingformats::Source newSource,
      std::string newLocType,
      std::string newEarthModel,
	  std::string newSlabResolution,
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
      bool newUseSVD,
	  bool newReassessInitialPhaseIDs);

	/**
	 * \brief LocationRequest advanced constructor
	 *
	 * The advanced constructor for the LocationRequest class.
	 * Constructs the object from a rapidjson::Value, populating members
	 * \param json - A reference to a populated rapidjson::Value to use
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
	 * \brief The LocationRequest ID
	 * 
	 * An optional std::string containing the identifier for this LocationRequest
	 */
	std::string id;

	/**
	 * \brief The LocationRequest source
	 *
	 * An optional processingformats::source containing the source for this 
   * LocationRequest
	 */
	processingformats::Source source;

	/**
	 * \brief The LocationRequest type string 
	 *
	 * A required std::string containing the type identifier for this LocationRequest.
	 */
	std::string type;

	/**
	 * \brief The source latitude value
	 *
	 * A required double defining the source latitude of this LocationRequest in
	 * decimal degrees.
	 */
	double sourceLatitude;

	/**
	 * \brief The source longitude value
	 *
	 * A required double defining the sourcelongitude of this LocationRequest in
	 * decimal degrees.
	 */
	double sourceLongitude;

  	/**
	 * \brief The source origin time value
	 *
	 * A required double containing the source origin time for this LocationRequest
	 * in decimal epoch seconds.
	 */
	double sourceOriginTime;

	/**
	 * \brief The source depth value
	 *
	 * A required double containing the source depth for this LocationRequest in
	 * kilometers.
	 */
	double sourceDepth;

	/**
	 * \brief The vector of input data
	 * 
	 * A required vector of input Pick objects for this LocationRequest
	 */
	std::vector<processingformats::Pick> inputData;

	/**
	 * \brief The LocationRequest earth model
	 *
	 * An optional std::string containing the earth model for this LocationRequest, 
	 * default "ak135".
	 */
	std::string earthModel;

	/**
	 * \brief The LocationRequest earth model
	 *
	 * An optional std::string containing the slab resolution for this LocationRequest,
	 * default "2spd".
	 */
	std::string slabResolution;

  	/**
	 * \brief The is location new value
	 *
	 * An optional bool indicating whether the location is new
	 */
	bool isLocationNew;

  	/**
	 * \brief The is location held value
	 *
	 * An optional bool indicating whether the location is held
	 */
	bool isLocationHeld;

  	/**
	 * \brief The is depth held value
	 *
	 * An optional bool indicating whether the depth is held
	 */
	bool isDepthHeld;

  	/**
	 * \brief The depth bayesian value
	 *
	 * An optional bool indicating whether the depth is bayesian
	 */
	bool isBayesianDepth;

  	/**
	 * \brief The bayesian depth value
	 *
	 * An optional double containing the bayesian depth for this LocationRequest
	 * in kilometers.
	 */
	double bayesianDepth;

  	/**
	 * \brief The bayesian depth spread value
	 *
	 * An optional double containing the bayesian depth spread for this 
	 * LocationRequest in kilometers.
	 */
	double bayesianSpread;

  	/**
	 * \brief The use svd value
	 *
	 * An optional bool indicating whether to use singular value decomposition
	 */
	bool useSVD;

  	/**
	 * \brief The reassess initial phase ids value
	 *
	 * An optional bool iindicating whether reassess inital phase identifications
	 */
	bool reassessInitialPhaseIDs;

	/**
	 * \brief The output data
	 *
	 * An optional processingformats::LocationResult containing the output data
   * for this LocationRequest
	 */
	processingformats::LocationResult outputData;
};
}  // namespace processingformats
#endif  // PROCESSING_LOCATIONREQUEST_H
