/*****************************************
 * This file is documented for Doxygen.
 * If you modify this file please update
 * the comments so that Doxygen will still
 * be able to work.
 ****************************************/
#ifndef DETECTION_ERRORELLIPSE_H
#define DETECTION_ERRORELLIPSE_H

#include <base.h>

#include <string>
#include <exception>
#include <vector>

namespace processingformats {
/**
 * \brief processingformats Error Ellipse conversion class
 *
 * The processingformats error ellipse class is a conversion class used to 
 * create, parse, and validate error ellipse data as part of processingformats 
 * data.
 *
 */
class ErrorEllipse : public ProcessingBase {
 public:
    /**
	 * \brief ErrorEllipse constructor
	 *
	 * The constructor for the ErrorEllipse class.
	 * Initializes members to null values.
	 */
	ErrorEllipse();

	/**
	 * \brief ErrorEllipse advanced constructor
	 *
	 * The advanced constructor for the ErrorEllipse class.
	 * Initializes members to provided values.
	 *
	 * \param newE0Error - A double containing the E0 error to use
	 * \param newE0Azimuth - A double containing the E0 azimuth to use
	 * \param newE0Dip - A double containing the new newE0 dip to use
	 * \param newE1Error - A double containing the E1 error to use
	 * \param newE1Azimuth - A double containing the E1 azimuth to use
	 * \param newE1Dip - A double containing the new new E1 dip to use
	 * \param newE2Error - A double containing the E2 error to use
	 * \param newE2Azimuth - A double containing the E2 azimuth to use
	 * \param newE2Dip - A double containing the new new E2 dip to use
	 * \param newdepth - A double containing the depth to use
	 * \param newMaximumHorizontalProjection - A double containing the maximum
     *  horizontal projection to use
	 * \param newMaximumVerticalProjection - A double containing the maximum
     *  vertical projection to use
	 * \param newEquivalentHorizontalRadius - A double containing the equivalent
     *  horizontal radius to use
	 */
	ErrorEllipse(double newE0Error, double newE0Azimuth, double newE0Dip,
                double newE1Error, double newE1Azimuth, double newE1Dip,
                double newE2Error, double newE2Azimuth, double newE2Dip,
				double newMaximumHorizontalProjection,
                double newMaximumVerticalProjection,
				double newEquivalentHorizontalRadius);

	/**
	 * \brief ErrorEllipse advanced constructor
	 *
	 * The advanced constructor for the ErrorEllipse class.
	 * Converts the provided object from a json::Object, populating members
	 * \param jsondocument - A json document.
	 */
	explicit ErrorEllipse(rapidjson::Value &json); // NOLINT

	/**
	 * \brief ErrorEllipse copy constructor
	 *
	 * The copy constructor for the ErrorEllipse class.
	 * Copies the provided object from a ErrorEllipse, populating members
	 * \param newEllipse - An ErrorEllipse.
	 */
	ErrorEllipse(const ErrorEllipse & newEllipse);

	/**
	 * \brief ErrorEllipse destructor
	 *
	 * The destructor for the ErrorEllipse class.
	 */
	~ErrorEllipse();

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
     * \brief E0 error value
     * 
	 * A required double containing the E0 error.
	 */
	double e0Error;

	/**
     * \brief E0 azimuth value
     * 
	 * A required double containing the E0 azimuth.
	 */
	double e0Azimuth;

	/**
     * \brief E0 dip value
     * 
	 * A required double containing the E0 dip.
	 */
	double e0Dip;

	/**
     * \brief E1 error value
     * 
	 * A required double containing the E1 error.
	 */
	double e1Error;

	/**
     * \brief E1 azimuth value
     * 
	 * A required double containing the E1 azimuth.
	 */
	double e1Azimuth;

	/**
     * \brief E1 dip value
     * 
	 * A required double containing the E1 dip.
	 */
	double e1Dip;

	/**
     * \brief E2 error value
     * 
	 * A required double containing the E2 error.
	 */
	double e2Error;

	/**
     * \brief E2 azimuth value
     * 
	 * A required double containing the E2 azimuth.
	 */
	double e2Azimuth;

	/**
     * \brief E2 dip value
     * 
	 * A required double containing the E2 dip.
	 */
	double e2Dip;

	/**
     * \brief maximum horizontal projection value
	 * 
     * A required double containing the maximum horizontal projection.
	 */
	double maximumHorizontalProjection;

	/**
     * \brief maximum vertical projection value
     * 
	 * A required double containing the maximum vertical projection.
	 */
	double maximumVerticalProjection;

	/**
     * \brief equivalent horizontal radius value
     * 
	 * A required double defining the equivalent horizontal radius.
	 */
	double equivalentHorizontalRadius;
};
}  // namespace processingformats
#endif  // DETECTION_ERRORELLIPSE_H
