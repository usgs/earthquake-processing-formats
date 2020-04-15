/*****************************************
 * This file is documented for Doxygen.
 * If you modify this file please update
 * the comments so that Doxygen will still
 * be able to work.
 ****************************************/
#ifndef PROCESSING_SITE_H
#define PROCESSING_SITE_H

#include <string>
#include <exception>
#include <vector>

#include "base.h" // NOLINT

namespace processingformats {
/**
 * \brief processingformats Site conversion class
 *
 * The processingformats Site class is a conversion class used to create, parse,
 * and validate Site data as part of processing data.
 *
 */
class Site : public ProcessingBase {
 public:
	/**
	 * \brief Site constructor
	 *
	 * The constructor for the Site class.
	 * Initializes members to null values.
	 */
	Site();

	/**
	 * \brief Site advanced constructor
	 *
	 * The advanced constructor for the source class.
	 * Initializes members to provided values.
	 *
	 * \param newstation - A std::string containing the station to use
	 * \param newchannel - A std::string containing the channel to use
	 * \param newnetwork - A std::string containing the network to use
	 * \param newlocation - A std::string containing the location to use
	 * \param newlatitude - A double containing the latitude in degrees to use, 
	 * std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newlongitude - A double containing the longitude in degrees to use, 
	 * std::numeric_limits<double>::quiet_NaN() to omit
	 * \param newelevation - A double containing the elevation in meters to use, 
	 * std::numeric_limits<double>::quiet_NaN() to omit
	 */
	Site(std::string newstation, std::string newchannel, std::string newnetwork,
			std::string newlocation, double newlatitude, double newlongitude,
			double newelevation);

	/**
	 * \brief Site advanced constructor
	 *
	 * The advanced constructor for the Site class.
	 * Constructs the object from a rapidjson::Value, populating members
	 * \param json - A reference to a populated rapidjson::Value to use
	 */
	explicit Site(rapidjson::Value &json); // NOLINT

	/**
	 * \brief Site copy constructor
	 *
	 * The copy constructor for the Site class.
	 * Copies the provided object from a Site, populating members
	 * \param newSite - A Site.
	 */
	Site(const Site & newSite);

	/**
	 * \brief Site destructor
	 *
	 * The destructor for the Site class.
	 */
	~Site();

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
	 * \brief Site station code
	 *
	 * A required std::string containing the station code for this Site.
	 */
	std::string station;

	/**
	 * \brief Site channel code
	 *
	 * An optional std::string containing the channel code for this Site.
	 */
	std::string channel;

	/**
	 * \brief Site network code
	 *
	 * A required std::string containing the network code for this Site.
	 */
	std::string network;

	/**
	 * \brief Site location code
	 *
	 * An optional std::string containing the location code for this Site.
	 */
	std::string location;

	/**
	 * \brief latitude value
	 *
	 * An optional double defining the latitude of this site in decimal degrees.
	 */
	double latitude;

	/**
	 * \brief longitude value
	 *
	 * An optional double defining the longitude of this site in decimal degrees.
	 */
	double longitude;

	/**
	 * \brief elevation value
	 *
	 * An optional double containing the elevation for this site in meters.
	 */
	double elevation;
};
}  // namespace processingformats
#endif  // PROCESSING_SITE_H
