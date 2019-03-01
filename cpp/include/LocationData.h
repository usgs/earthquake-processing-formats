/*****************************************
 * This file is documented for Doxygen.
 * If you modify this file please update
 * the comments so that Doxygen will still
 * be able to work.
 ****************************************/
#ifndef DETECTION_LOCATIONDATA_H
#define DETECTION_LOCATIONDATA_H

#include <base.h>
#include <LocationData.h>
#include <Hypocenter.h>
#include <ErrorEllipse.h>
#include <Pick.h>

#include <string>
#include <exception>
#include <vector>

namespace processingformats {
/**
 * \brief processingformats location data conversion class
 *
 * The processingformats location data class is a conversion class used to 
 * create, parse, and validate location data as part of processingformats data.
 *
 */
class LocationData : public ProcessingBase {
 public:
    /**
	 * \brief LocationData constructor
	 *
	 * The constructor for the LocationData class.
	 * Initializes members to null values.
	 */
	LocationData();

    /**
	 * \brief LocationData advanced constructor
	 *
	 * The advanced constructor for the LocationData class.
	 * Initializes members to provided values.
	 *
     * \param newLatitude - A double containing the latitude to use
	 * \param newLongitude - A double containing the longitude to use
	 * \param newTime - A double containing the origin time to use
	 * \param newDepth - A double containing the depth to use
	 * \param newLatitudeError - A double containing the latitude error to use, 
     *  std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newLongitudeError - A double containing the longitude error to use, 
     *  std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newTimeError - A double containing the new time error to use, 
     *  std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newDepthError - A double containing the depth error to use, 
     *  std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newAssociatedData - A std::vector&lt;Pick&gt; containing the data 
     *  that went into this location
	 * \param newAssociatedStations - An int containing the number of associated 
     *  stations, std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newAssociatedPhases - An intcontaining the number of associated 
     *  phases, std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newUsedStations - An int containing the number of used stations, 
     *  std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newUsedPhases - An int containing the number of used phases, 
     *  std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newGap - A double containing the gap to use, 
     *  std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newSecondaryGap - A double containing the secondary gap to use, 
     *  std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newMinimumDistance - A double containing the minimum distance to 
     *  use, std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newRMS - A double containing the rms to use, 
     *  std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newQuality - A std::string containing the quality to use, 
     *  std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newBayesianDepth - A double containing the bayesian depth to use, 
     *  std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newBayesianRange - A double containing the bayesian range to use, 
     *  std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newDepthImportance - A double containing the depth importance to 
     *  use, std::numeric_limits<double>::quiet_NaN() to omit
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
	LocationData(double newLatitude, double newLongitude, double newTime,
			double newDepth, double newLatitudeError, double newLongitudeError,
			double newTimeError, double newDepthError,
			std::vector<processingformats::Pick> newAssociatedData,
            int newAssociatedStations, int newAssociatedPhases,
            int newUsedStations, int newUsedPhases, double newGap,
            double newSecondaryGap, double newMinimumDistance, double newRMS,
            std::string newQuality, double newBayesianDepth,
			double newBayesianRange, double newDepthImportance,
            double newE0Error, double newE0Azimuth,
			double newE0Dip, double newE1Error, double newE1Azimuth,
			double newE1Dip, double newE2Error, double newE2Azimuth,
			double newE2Dip, double newMaximumHorizontalProjection,
			double newMaximumVerticalProjection,
			double newEquivalentHorizontalRadius);

    /**
     * Alternate Advanced constructor
     *
     * The alternate advanced constructor for the LocationData class.
     * Initializes members to provided values.
     *
     * \param newLatitude - A double containing the latitude to use
     * \param newLongitude - A double containing the longitude to use
     * \param newTime - A double containing the origin time to use
     * \param newDepth - A double containing the depth to use
     * \param newLatitudeError - A double containing the latitude error to use, 
     *  std::numeric_limits<double>::quiet_NaN() to omit
     * \param newLongitudeError - A double containing the longitude error to use, 
     *  std::numeric_limits<double>::quiet_NaN() to omit
     * \param newTimeError - A double containing the new time error to use, 
     *  std::numeric_limits<double>::quiet_NaN() to omit
     * \param newDepthError - A double containing the depth error to use, 
     *  std::numeric_limits<double>::quiet_NaN() to omit
     * \param newAssociatedData - A std::vector&lt;Pick&gt; newPickData 
     *  containing the data that went into this location
     */
    LocationData(double newLatitude, double newLongitude, double newTime,
            double newDepth, double newLatitudeError, double newLongitudeError,
            double newTimeError, double newDepthError,
            std::vector<processingformats::Pick> newAssociatedData);

    /**
	 * Alternate Advanced constructor
	 *
	 * The alternate advanced constructor for the LocationData class.
	 * Initializes members to provided values.
	 *
	 * \param newHypocenter - A Hypocenter containing the hypocenter to use
	 * \param newAssociatedData - A std::vector&lt;Pick&gt; newPickData 
     *  containing the data that went into this location
	 */
	LocationData(processingformats::Hypocenter newHypocenter,
			std::vector<processingformats::Pick> newAssociatedData);

	/**
	 * \brief LocationData advanced constructor
	 *
	 * The advanced constructor for the LocationData class.
	 * Converts the provided object from a json::Object, populating members
	 * \param jsondocument - A json document.
	 */
	explicit LocationData(rapidjson::Value &json); // NOLINT

	/**
	 * \brief LocationData copy constructor
	 *
	 * The copy constructor for the LocationData class.
	 * Copies the provided object from a LocationData, populating members
	 * \param newData - A LocationData.
	 */
	LocationData(const LocationData & newData);

	/**
	 * \brief LocationData destructor
	 *
	 * The destructor for the LocationData class.
	 */
	~LocationData();

	/**
	 * \brief Convert to json object function
	 *
	 * Converts the contents of the class to a json object
	 * \param jsondocument - a reference to the json document to fill in with
	 * 		the class contents.
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
	 * Required hypocenter
	 */
	processingformats::Hypocenter hypocenter;

	/**
	 * A required vector of Pick objects used to generate this location
	 */
	std::vector<processingformats::Pick> associatedData;

	/**
	 * Optional integer containing the number of associated stations
	 */
	int numberOfAssociatedStations;

	/**
	 * Optional integer containing the number of associated phases
	 */
	int numberOfAssociatedPhases;

	/**
	 * Optional integer containing the number of used stations
	 */
	int numberOfUsedStations;

	/**
	 * Optional integer containing the number of used phases
	 */
	int numberOfUsedPhases;

	/**
	 * Optional double containing the gap
	 */
	double gap;

	/**
	 * Optional double containing the secondary gap
	 */
	double secondaryGap;

	/**
	 * Required double containing the Detection minimum distance
	 */
	double minimumDistance;

	/**
	 * Optional double containing the rms
	 */
	double rms;

	/**
	 * Optional std::string containing the quality flag
	 */
	std::string quality;

	/**
	 * Optional double containing the bayesan depth
	 */
	double bayesianDepth;

	/**
	 * Optional double containing the bayesian range
	 */
	double bayesianRange;

	/**
	 * Optional double containing the depth importance
	 */
	double depthImportance;

	/**
	 * Optional error ellipse
	 */
	processingformats::ErrorEllipse errorEllipse;
};
}  // namespace processingformats
#endif  // DETECTION_LOCATIONDATA_H
