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
	 * Constructs the object from a rapidjson::Value, populating members
	 * \param json - A reference to a populated rapidjson::Value to use
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
	 * \brief The E0 error value
	 * 
	 * A required double containing the length of the first error semi-axis in
	 * kilometers.
	 */
	double e0Error;

	/**
   * \brief The E0 azimuth value
   * 
	 * A required double containing the azimuth of the first error semi-axis in
	 * decimal degrees.
	 */
	double e0Azimuth;

	/**
   * \brief The E0 dip value
   * 
	 * A required double containing the dip of the first error semi-axis in
	 * decimal degrees.
	 */
	double e0Dip;

	/**
   * \brief The E1 error value
   * 
	 * A required double containing the length of the second error semi-axis in
	 * kilometers.
	 */
	double e1Error;

	/**
   * \brief The E1 azimuth value
   * 
	 * A required double containing the azimuth of the second error semi-axis in
	 * decimal degrees.
	 */
	double e1Azimuth;

	/**
   * \brief The E1 dip value
   * 
	 * A required double containing the dip of the second error semi-axis in
	 * decimal degrees.
	 */
	double e1Dip;

	/**
   * \brief The E2 error value
   * 
	 * A required double containing the length of the third error semi-axis in
	 * kilometers.
	 */
	double e2Error;

	/**
   * \brief The E2 azimuth value
   * 
	 * A required double containing the azimuth of the third error semi-axis in
	 * decimal degrees.
	 */
	double e2Azimuth;

	/**
   * \brief The E2 dip value
   * 
	 * A required double containing the dip of the third error semi-axis in
	 * decimal degrees.
	 */
	double e2Dip;

	/**
   * \brief The horizontal projection of the error ellipsoid in kilometers
	 * 
   * A required double containing the maximum horizontal projection.
	 */
	double maximumHorizontalProjection;

	/**
   * \brief The maximum vertical projection of the error ellipsoid in kilometers.
   * 
	 * A required double containing the vertical projection 
	 */
	double maximumVerticalProjection;

	/**
	 * \brief The equivalent radius of the horizontal error ellipsoid in kilometers.
	 * 
	 * A required double containing the equivalent horizontal radius
	 */
	double equivalentHorizontalRadius;
};
}  // namespace processingformats
#endif  // DETECTION_ERRORELLIPSE_H
