#include <ErrorEllipse.h>

#include <string>
#include <limits>
#include <vector>

#define E0_KEY "E0"
#define E1_KEY "E1"
#define E2_KEY "E2"
#define ERROR_KEY "Error"
#define AZIMUTH_KEY "Azimuth"
#define DIP_KEY "Dip"
#define MAXIMUM_HORIZONTAL_KEY "MaximumHorizontalProjection"
#define MAXIMUM_VERTICAL_KEY "MaximumVerticalProjection"
#define EQUIVALENT_HORIZONTAL_KEY "EquivalentHorizontalRadius"

namespace processingformats {

ErrorEllipse::ErrorEllipse() {
	e0Error = std::numeric_limits<double>::quiet_NaN();
	e0Azimuth = std::numeric_limits<double>::quiet_NaN();
	e0Dip = std::numeric_limits<double>::quiet_NaN();
	e1Error = std::numeric_limits<double>::quiet_NaN();
	e1Azimuth = std::numeric_limits<double>::quiet_NaN();
	e1Dip = std::numeric_limits<double>::quiet_NaN();
	e2Error = std::numeric_limits<double>::quiet_NaN();
	e2Azimuth = std::numeric_limits<double>::quiet_NaN();
    e2Dip = std::numeric_limits<double>::quiet_NaN();
	maximumHorizontalProjection = std::numeric_limits<double>::quiet_NaN();
	maximumVerticalProjection = std::numeric_limits<double>::quiet_NaN();
	equivalentHorizontalRadius = std::numeric_limits<double>::quiet_NaN();
}

ErrorEllipse::ErrorEllipse(double newE0Error, double newE0Azimuth,
                           double newE0Dip, double newE1Error,
                           double newE1Azimuth, double newE1Dip,
                           double newE2Error, double newE2Azimuth,
                           double newE2Dip,
                           double newMaximumHorizontalProjection,
                           double newMaximumVerticalProjection,
                           double newEquivalentHorizontalRadius) {
	e0Error = newE0Error;
	e0Azimuth = newE0Azimuth;
	e0Dip = newE0Dip;
	e1Error = newE1Error;
	e1Azimuth = newE1Azimuth;
	e1Dip = newE1Dip;
	e2Error = newE2Error;
	e2Azimuth = newE2Azimuth;
    e2Dip = newE2Dip;
	maximumHorizontalProjection = newMaximumHorizontalProjection;
	maximumVerticalProjection = newMaximumVerticalProjection;
	equivalentHorizontalRadius = newEquivalentHorizontalRadius;
}

ErrorEllipse::ErrorEllipse(rapidjson::Value &json) {
	// Required values
    // E0
    if (json.HasMember(E0_KEY) && (json[E0_KEY].IsObject() == true)) {
        rapidjson::Value & eObject = json[E0_KEY];

        // error
        if ((eObject.HasMember(ERROR_KEY) == true)
			&& (eObject[ERROR_KEY].IsNumber() == true)
			&& (eObject[ERROR_KEY].IsDouble() == true)) {
		    e0Error = eObject[ERROR_KEY].GetDouble();
	    } else {
		    e0Error = std::numeric_limits<double>::quiet_NaN();
        }

        // azimuth
        if ((eObject.HasMember(AZIMUTH_KEY) == true)
			&& (eObject[AZIMUTH_KEY].IsNumber() == true)
			&& (eObject[AZIMUTH_KEY].IsDouble() == true)) {
		    e0Azimuth = eObject[AZIMUTH_KEY].GetDouble();
	    } else {
		    e0Azimuth = std::numeric_limits<double>::quiet_NaN();
        }

        // dip
        if ((eObject.HasMember(DIP_KEY) == true)
			&& (eObject[DIP_KEY].IsNumber() == true)
			&& (eObject[DIP_KEY].IsDouble() == true)) {
		    e0Dip = eObject[DIP_KEY].GetDouble();
	    } else {
		    e0Dip = std::numeric_limits<double>::quiet_NaN();
        }
    } else {
        e0Error = std::numeric_limits<double>::quiet_NaN();
        e0Azimuth = std::numeric_limits<double>::quiet_NaN();
        e0Dip = std::numeric_limits<double>::quiet_NaN();
    }

    // E1
    if (json.HasMember(E1_KEY) && (json[E1_KEY].IsObject() == true)) {
        rapidjson::Value & eObject = json[E1_KEY];

        // error
        if ((eObject.HasMember(ERROR_KEY) == true)
			&& (eObject[ERROR_KEY].IsNumber() == true)
			&& (eObject[ERROR_KEY].IsDouble() == true)) {
		    e1Error = eObject[ERROR_KEY].GetDouble();
	    } else {
		    e1Error = std::numeric_limits<double>::quiet_NaN();
        }

        // azimuth
        if ((eObject.HasMember(AZIMUTH_KEY) == true)
			&& (eObject[AZIMUTH_KEY].IsNumber() == true)
			&& (eObject[AZIMUTH_KEY].IsDouble() == true)) {
		    e1Azimuth = eObject[AZIMUTH_KEY].GetDouble();
	    } else {
		    e1Azimuth = std::numeric_limits<double>::quiet_NaN();
        }

        // dip
        if ((eObject.HasMember(DIP_KEY) == true)
			&& (eObject[DIP_KEY].IsNumber() == true)
			&& (eObject[DIP_KEY].IsDouble() == true)) {
		    e1Dip = eObject[DIP_KEY].GetDouble();
	    } else {
		    e1Dip = std::numeric_limits<double>::quiet_NaN();
        }
    } else {
        e1Error = std::numeric_limits<double>::quiet_NaN();
        e1Azimuth = std::numeric_limits<double>::quiet_NaN();
        e1Dip = std::numeric_limits<double>::quiet_NaN();
    }

    // E2
    if (json.HasMember(E2_KEY) && (json[E2_KEY].IsObject() == true)) {
        rapidjson::Value & eObject = json[E2_KEY];

        // error
        if ((eObject.HasMember(ERROR_KEY) == true)
			&& (eObject[ERROR_KEY].IsNumber() == true)
			&& (eObject[ERROR_KEY].IsDouble() == true)) {
		    e2Error = eObject[ERROR_KEY].GetDouble();
	    } else {
		    e2Error = std::numeric_limits<double>::quiet_NaN();
        }

        // azimuth
        if ((eObject.HasMember(AZIMUTH_KEY) == true)
			&& (eObject[AZIMUTH_KEY].IsNumber() == true)
			&& (eObject[AZIMUTH_KEY].IsDouble() == true)) {
		    e2Azimuth = eObject[AZIMUTH_KEY].GetDouble();
	    } else {
		    e2Azimuth = std::numeric_limits<double>::quiet_NaN();
        }

        // dip
        if ((eObject.HasMember(DIP_KEY) == true)
			&& (eObject[DIP_KEY].IsNumber() == true)
			&& (eObject[DIP_KEY].IsDouble() == true)) {
		    e2Dip = eObject[DIP_KEY].GetDouble();
	    } else {
		    e2Dip = std::numeric_limits<double>::quiet_NaN();
        }
    } else {
        e2Error = std::numeric_limits<double>::quiet_NaN();
        e2Azimuth = std::numeric_limits<double>::quiet_NaN();
        e2Dip = std::numeric_limits<double>::quiet_NaN();
    }

    // maximumHorizontalProjection
    if ((json.HasMember(MAXIMUM_HORIZONTAL_KEY) == true)
			&& (json[MAXIMUM_HORIZONTAL_KEY].IsNumber() == true)
			&& (json[MAXIMUM_HORIZONTAL_KEY].IsDouble() == true)) {
		maximumHorizontalProjection = json[MAXIMUM_HORIZONTAL_KEY].GetDouble();
	} else {
		maximumHorizontalProjection = std::numeric_limits<double>::quiet_NaN();
    }

    // maximumVerticalProjection
    if ((json.HasMember(MAXIMUM_VERTICAL_KEY) == true)
			&& (json[MAXIMUM_VERTICAL_KEY].IsNumber() == true)
			&& (json[MAXIMUM_VERTICAL_KEY].IsDouble() == true)) {
		maximumVerticalProjection = json[MAXIMUM_VERTICAL_KEY].GetDouble();
	} else {
		maximumVerticalProjection = std::numeric_limits<double>::quiet_NaN();
    }

    // equivalentHorizontalRadius
    if ((json.HasMember(EQUIVALENT_HORIZONTAL_KEY) == true)
			&& (json[EQUIVALENT_HORIZONTAL_KEY].IsNumber() == true)
			&& (json[EQUIVALENT_HORIZONTAL_KEY].IsDouble() == true)) {
		equivalentHorizontalRadius = json[EQUIVALENT_HORIZONTAL_KEY].GetDouble();
	} else {
		equivalentHorizontalRadius = std::numeric_limits<double>::quiet_NaN();
    }
}

ErrorEllipse::ErrorEllipse(const ErrorEllipse & newErrorEllipse) {
	e0Error = newErrorEllipse.e0Error;
	e0Azimuth = newErrorEllipse.e0Azimuth;
	e0Dip = newErrorEllipse.e0Dip;
	e1Error = newErrorEllipse.e1Error;
	e1Azimuth = newErrorEllipse.e1Azimuth;
	e1Dip = newErrorEllipse.e1Dip;
	e2Error = newErrorEllipse.e2Error;
	e2Azimuth = newErrorEllipse.e2Azimuth;
    e2Dip = newErrorEllipse.e2Dip;
	maximumHorizontalProjection = newErrorEllipse.maximumHorizontalProjection;
	maximumVerticalProjection = newErrorEllipse.maximumVerticalProjection;
	equivalentHorizontalRadius = newErrorEllipse.equivalentHorizontalRadius;
}

ErrorEllipse::~ErrorEllipse() {
}

rapidjson::Value & ErrorEllipse::toJSON(
		rapidjson::Value &json,
		rapidjson::MemoryPoolAllocator<rapidjson::CrtAllocator> &allocator) {
	json.SetObject();

	// required values
	// e0
    rapidjson::Value e0Value(rapidjson::kObjectType);
	if (std::isnan(e0Error) != true) {
		e0Value.AddMember(ERROR_KEY, e0Error, allocator);
    }
	if (std::isnan(e0Azimuth) != true) {
		e0Value.AddMember(AZIMUTH_KEY, e0Azimuth, allocator);
    }
	if (std::isnan(e0Dip) != true) {
		e0Value.AddMember(DIP_KEY, e0Dip, allocator);
    }
    json.AddMember(E0_KEY, e0Value, allocator);

	// e1
    rapidjson::Value e1Value(rapidjson::kObjectType);
	if (std::isnan(e1Error) != true) {
		e1Value.AddMember(ERROR_KEY, e1Error, allocator);
    }
	if (std::isnan(e1Azimuth) != true) {
		e1Value.AddMember(AZIMUTH_KEY, e1Azimuth, allocator);
    }
	if (std::isnan(e1Dip) != true) {
		e1Value.AddMember(DIP_KEY, e1Dip, allocator);
    }
    json.AddMember(E1_KEY, e1Value, allocator);

	// e2
    rapidjson::Value e2Value(rapidjson::kObjectType);
	if (std::isnan(e2Error) != true) {
		e2Value.AddMember(ERROR_KEY, e2Error, allocator);
    }
	if (std::isnan(e2Azimuth) != true) {
		e2Value.AddMember(AZIMUTH_KEY, e2Azimuth, allocator);
    }
	if (std::isnan(e2Dip) != true) {
		e2Value.AddMember(DIP_KEY, e2Dip, allocator);
    }
    json.AddMember(E2_KEY, e2Value, allocator);

	// maximumHorizontalProjection
	if (std::isnan(maximumHorizontalProjection) != true) {
		json.AddMember(MAXIMUM_HORIZONTAL_KEY, maximumHorizontalProjection,
            allocator);
    }

    // maximumVerticalProjection
	if (std::isnan(maximumVerticalProjection) != true) {
		json.AddMember(MAXIMUM_VERTICAL_KEY, maximumVerticalProjection,
            allocator);
    }

	// equivalentHorizontalRadius
	if (std::isnan(equivalentHorizontalRadius) != true) {
		json.AddMember(EQUIVALENT_HORIZONTAL_KEY, equivalentHorizontalRadius,
            allocator);
    }

    return (json);
}

std::vector<std::string> ErrorEllipse::getErrors() {
	std::vector<std::string> errorList;

	// check required data
	// E0 error
    if (std::isnan(e0Error) == true) {
        // error not found
        errorList.push_back("No E0 error in ErrorEllipse Class.");
    }

    // E0 azimuth
    if (std::isnan(e0Azimuth) == true) {
        // azimuth not found
        errorList.push_back("No E0 azimuth in ErrorEllipse Class.");
    }

    // E0 dip
    if (std::isnan(e0Dip) == true) {
        // dip not found
        errorList.push_back("No E0 dip in ErrorEllipse Class.");
    }

    // E1 error
    if (std::isnan(e1Error) == true) {
        // error not found
        errorList.push_back("No E1 error in ErrorEllipse Class.");
    }

    // E1 azimuth
    if (std::isnan(e1Azimuth) == true) {
        // azimuth not found
        errorList.push_back("No E1 azimuth in ErrorEllipse Class.");
    }

    // E1 dip
    if (std::isnan(e1Dip) == true) {
        // dip not found
        errorList.push_back("No E1 dip in ErrorEllipse Class.");
    }

    // E2 error
    if (std::isnan(e2Error) == true) {
        // error not found
        errorList.push_back("No E2 error in ErrorEllipse Class.");
    }

    // E2 azimuth
    if (std::isnan(e2Azimuth) == true) {
        // azimuth not found
        errorList.push_back("No E2 azimuth in ErrorEllipse Class.");
    }

    // E2 dip
    if (std::isnan(e2Dip) == true) {
        // dip not found
        errorList.push_back("No E2 dip in ErrorEllipse Class.");
    }

    // MaximumHorizontalProjection
    if (std::isnan(maximumHorizontalProjection) == true) {
        // MaximumHorizontalProjection not found
        errorList.push_back(
                "No MaximumHorizontalProjection in ErrorEllipse Class.");
    }

    // MaximumVerticalProjection
    if (std::isnan(maximumVerticalProjection) == true) {
        // MaximumVerticalProjection not found
        errorList.push_back(
            "No MaximumVerticalProjection in ErrorEllipse Class.");
    }

    // EquivalentHorizontalRadius
    if (std::isnan(equivalentHorizontalRadius) == true) {
        // EquivalentHorizontalRadius not found
        errorList.push_back(
                "No EquivalentHorizontalRadius in ErrorEllipse Class.");
    }

	// return the list of errors
	return (errorList);
}

}  // namespace processingformats
