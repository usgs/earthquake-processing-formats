/*****************************************
 * This file is documented for Doxygen.
 * If you modify this file please update
 * the comments so that Doxygen will still
 * be able to work.
 ****************************************/
#ifndef PROCESSING_TRAVELTIMEDATA_H
#define PROCESSING_TRAVELTIMEDATA_H

#include <string>
#include <exception>

#include "base.h"

namespace processingformats {

/**
 * \brief processingformats amplitude conversion class
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
	 * The constructor for the amplitude class.
	 * Initilizes members to null values.
	 */
	TravelTimeData();

};
}  // namespace processingformats
#endif  // PROCESSING_TRAVELTIMEDATA_H
