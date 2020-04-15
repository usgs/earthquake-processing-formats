/*****************************************
 * This file is documented for Doxygen.
 * If you modify this file please update
 * the comments so that Doxygen will still
 * be able to work.
 ****************************************/
#ifndef DETECTION_LOCATIONRESULT_H
#define DETECTION_LOCATIONRESULT_H

#include <string>
#include <exception>
#include <vector>

#include "base.h" // NOLINT
#include "Hypocenter.h" // NOLINT
#include "ErrorEllipse.h" // NOLINT
#include "Pick.h" // NOLINT
#include "Source.h" // NOLINT

namespace processingformats {
/**
 * \brief processingformats location data conversion class
 *
 * The processingformats location data class is a conversion class used to 
 * create, parse, and validate location data as part of processingformats data.
 *
 */
class LocationResult : public ProcessingBase {
 public:
    /**
	 * \brief LocationResult constructor
	 *
	 * The constructor for the LocationResult class.
	 * Initializes members to null values.
	 */
	LocationResult();

  /**
	 * \brief LocationResult advanced constructor
	 *
	 * The advanced constructor for the LocationResult class.
	 * Initializes members to provided values.
	 *
	 * \param newID - A std::string containing the id to use
	 * \param newAgencyID - A std::string containing the agencyId to use
	 * \param newAuthor - A std::string containing the author to use
	 * \param newType - A std::string containing the type to use
   * \param newLatitude - A double containing the latitude in degrees to use
	 * \param newLongitude - A double containing the longitude in degrees to use
	 * \param newTime - A double containing the origin time in epoch seconds to use
	 * \param newDepth - A double containing the depth in kilometers to use
	 * \param newLatitudeError - A double containing the latitude error to use, 
   *  std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newLongitudeError - A double containing the longitude error to use, 
   *  std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newTimeError - A double containing the new time error to use, 
   *  std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newDepthError - A double containing the depth error to use, 
   *  std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newSupportingData - A std::vector&lt;Pick&gt; containing the data 
   *  that went into this location
	 * \param newAssociatedStations - An int containing the number of associated 
   *  stations, std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newAssociatedPhases - An intcontaining the number of associated 
   *  phases, std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newUsedStations - An int containing the number of used stations, 
   *  std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newUsedPhases - An int containing the number of used phases, 
   *  std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newGap - A double containing the gap in degrees to use, 
   *  std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newSecondaryGap - A double containing the secondary gap in degrees to 
   *  use, std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newMinimumDistance - A double containing the minimum distance in 
   *  degrees to use, std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newRMS - A double containing the rms to use, 
   *  std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newQuality - A std::string containing the quality to use, 
   *  std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newBayesianDepth - A double containing the bayesian depth in
   *  kilometers to use, std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newBayesianRange - A double containing the bayesian range 
   *  kilometers to use, std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newDepthImportance - A double containing the depth importance to 
   *  use, std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newExitCode - A string containing the locator exit code 
	 * \param newE0Error - A double containing the length of the first axis of 
   *  the error ellipsoid in kilometers
	 * \param newE0Azimuth - A double containing the azimuth of the first axis 
   *  of the error ellipsoid in degrees
	 * \param newE0Dip - A double containing the dip of the first axis of error 
   *  ellipsoid in degrees.
	 * \param newE1Error - A double containing the length of the second axis of 
   *  the error ellipsoid in kilometers
	 * \param newE1Azimuth - A double containing the azimuth of the second axis 
   *  of the error ellipsoid in degrees
	 * \param newE1Dip - A double containing the dip of the second axis of error 
   *  ellipsoid in degrees.
	 * \param newE2Error - A double containing the length of the third axis of 
   *  the error ellipsoid in kilometers
	 * \param newE2Azimuth - A double containing the the azimuth of the third 
   *  axis of the error ellipsoid in degrees
	 * \param newE2Dip - A double containing the dip of the third axis of error 
   *  ellipsoid in degrees.
	 * \param newMaximumHorizontalProjection - A double containing the 
   *  horizontal projection of the error ellipsoid in kilometers
	 * \param newMaximumVerticalProjection - A double containing the vertical 
   *  projection of the error ellipsoid in km in kilometers
	 * \param newEquivalentHorizontalRadius - A double containing the equivalent 
   *  radius of the horizontal error ellipsoid in kilometers
	 */
	LocationResult(std::string newID, std::string newAgencyID,
		std::string newAuthor,
		std::string newType, double newLatitude, double newLongitude,
		double newTime, double newDepth, double newLatitudeError,
		double newLongitudeError, double newTimeError, double newDepthError,
		std::vector<processingformats::Pick> newSupportingData,
		int newAssociatedStations, int newAssociatedPhases,
		int newUsedStations, int newUsedPhases, double newGap,
		double newSecondaryGap, double newMinimumDistance, double newRMS,
		std::string newQuality, double newBayesianDepth,
		double newBayesianRange, double newDepthImportance, std::string newExitCode,
		double newE0Error, double newE0Azimuth,
		double newE0Dip, double newE1Error, double newE1Azimuth,
		double newE1Dip, double newE2Error, double newE2Azimuth,
		double newE2Dip, double newMaximumHorizontalProjection,
		double newMaximumVerticalProjection,
		double newEquivalentHorizontalRadius);

    /**
     * Alternate Advanced constructor
     *
     * The alternate advanced constructor for the LocationResult class.
     * Initializes members to provided values.
     *
		 * \param newLatitude - A double containing the latitude in degrees to use
		 * \param newLongitude - A double containing the longitude in degrees to use
		 * \param newTime - A double containing the origin time in epoch seconds to use
		 * \param newDepth - A double containing the depth in kilometers to use
     * \param newLatitudeError - A double containing the latitude error to use, 
     *  std::numeric_limits<double>::quiet_NaN() to omit
     * \param newLongitudeError - A double containing the longitude error to use, 
     *  std::numeric_limits<double>::quiet_NaN() to omit
     * \param newTimeError - A double containing the new time error to use, 
     *  std::numeric_limits<double>::quiet_NaN() to omit
     * \param newDepthError - A double containing the depth error to use, 
     *  std::numeric_limits<double>::quiet_NaN() to omit
     * \param newSupportingData - A std::vector&lt;Pick&gt; newPickData 
     *  containing the data that went into this location
     */
    LocationResult(double newLatitude, double newLongitude, double newTime,
            double newDepth, double newLatitudeError, double newLongitudeError,
            double newTimeError, double newDepthError,
            std::vector<processingformats::Pick> newSupportingData);

    /**
	 * Alternate Advanced constructor
	 *
	 * The alternate advanced constructor for the LocationResult class.
	 * Initializes members to provided values.
	 *
	 * \param newHypocenter - A Hypocenter containing the hypocenter to use
	 * \param newSupportingData - A std::vector&lt;Pick&gt; newPickData 
     *  containing the data that went into this location
	 */
	LocationResult(processingformats::Hypocenter newHypocenter,
			std::vector<processingformats::Pick> newSupportingData);

	/**
	 * \brief LocationResult advanced constructor
	 *
	 * The advanced constructor for the LocationResult class.
	 * Constructs the object from a rapidjson::Value, populating members
	 * \param json - A reference to a populated rapidjson::Value to use
	 */
	explicit LocationResult(rapidjson::Value &json); // NOLINT

	/**
	 * \brief LocationResult copy constructor
	 *
	 * The copy constructor for the LocationResult class.
	 * Copies the provided object from a LocationResult, populating members
	 * \param newData - A LocationResult.
	 */
	LocationResult(const LocationResult & newData);

	/**
	 * \brief LocationResult destructor
	 *
	 * The destructor for the LocationResult class.
	 */
	~LocationResult();

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
	 * \brief Empty check
	 *
	 * Checks to see if this object is empty
	 * \return Returns true if empty, false otherwise.
	 */
	bool isEmpty();

  /**
	 * \brief The 
	 * Required hypocenter
	 */
	processingformats::Hypocenter hypocenter;

	/**
	 * \brief The vector of supporting data
	 * 
	 * A required vector of Pick objects used to generate this location
	 */
	std::vector<processingformats::Pick> supportingData;

	/**
	 * \brief The result ID
	 * 
	 * An optional std::string containing the event identifier for this 
	 * LocationResult 
	 */
	std::string id;

	/**
	 * \brief The Result source
	 *
	 * An optional processingformats::source containing the source for this 
	 * LocationResult.
	 */
	processingformats::Source source;

	/**
	 * \brief The number of associated stations
	 * 
	 * An optional integer containing the number of associated stations for this 
	 * LocationResult.
	 */
	int numberOfAssociatedStations;

	/**
	 * \brief The number of associated phases
	 * 
	 * An optional integer containing the number of associated phases for this 
	 * LocationResult.
	 */
	int numberOfAssociatedPhases;

	/**
	 * \brief The number of used stations
	 * 
	 * An optional integer containing the number of used stations for this 
	 * LocationResult.
	 */
	int numberOfUsedStations;

	/**
	 * \brief The number of used phases
	 * 
	 * An optional integer containing the number of used phases for this 
	 * LocationResult.
	 */
	int numberOfUsedPhases;

	/**
	 * \brief The gap
	 * 
	 * An optional double containing the gap for this LocationResult in decimal
	 * degrees.
	 */
	double gap;

	/**
	 * \brief The secondary gap
	 * 
	 * An optional double containing the secondary gap for this LocationResult in
	 * decimal degrees.
	 */
	double secondaryGap;

	/**
	 * \brief The minimum distance
	 * 
	 * An optional double containing the Detection minimum distance for this 
	 * LocationResult in decimal degrees.
	 */
	double minimumDistance;

	/**
	 * \brief The RMS value
	 * 
	 * An optional double containing the rms for this LocationResult in decimal 
	 * seconds.
	 */
	double rms;

	/**
	 * \brief The location quality flags
	 * 
	 * An optional std::string containing the location quality flags for this 
	 * LocationResult
	 */
	std::string quality;

	/**
	 * \brief The location bayesian depth
	 * 
	 * An optional double containing the bayesian depth for this 
	 * LocationResult in kilometers.
	 */
	double bayesianDepth;

	/**
	 * \brief The location bayesian depth range
	 * 
	 * An optional double containing the bayesian depth range for this 
	 * LocationResult in kilometers.
	 */
	double bayesianRange;

	/**
	 * \brief The depth importance
	 * 
	 * An optional double containing the depth importance for this LocationResult.
	 */
	double depthImportance;

	/**
	 * \brief The locator exit code string
	 * 
	 * An optional std::string containing the locator exit code for this 
	 * LocationResult.
	 */
	std::string locatorExitCode;

	/**
	 * \brief The error ellipse
	 * 
	 * An optional processingformats::ErrorEllipse object containing the error 
	 * ellipse for this LocationResult
	 */
	processingformats::ErrorEllipse errorEllipse;
};
}  // namespace processingformats
#endif  // DETECTION_LOCATIONRESULT_H
