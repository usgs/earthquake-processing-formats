#include <LocationData.h>

#include <string>
#include <limits>
#include <vector>

#define HYPOCENTER_KEY "Hypocenter"
#define ASSOCIATEDDATA_KEY "AssociatedData"
#define ASSOCIATEDSTATIONS_KEY "NumberOfAssociatedStations"
#define ASSOCIATEDPHASES_KEY "NumberOfAssociatedPhases"
#define USEDSTATIONS_KEY "NumberOfUsedStations"
#define USEDPHASES_KEY "NumberOfUsedPhases"
#define GAP_KEY "Gap"
#define SECONDARYGAP_KEY "SecondaryGap"
#define MINIMUMDISTANCE_KEY "MinimumDistance"
#define RMS_KEY "RMS"
#define QUALITY_KEY "Quality"
#define BAYESIANDEPTH_KEY "BayesianDepth"
#define BAYESIANRANGE_KEY "BayesianRange"
#define DEPTHIMPORTANCE_KEY "DepthImportance"
#define ERRORELLIPSE_KEY "LocationData"

namespace processingformats {

LocationData::LocationData() {
	hypocenter = processingformats::Hypocenter();
	associatedData.clear();
	numberOfAssociatedStations = std::numeric_limits<int>::quiet_NaN();
	numberOfAssociatedPhases = std::numeric_limits<int>::quiet_NaN();
	numberOfUsedStations = std::numeric_limits<int>::quiet_NaN();
	numberOfUsedPhases = std::numeric_limits<int>::quiet_NaN();
	gap = std::numeric_limits<double>::quiet_NaN();
	secondaryGap = std::numeric_limits<double>::quiet_NaN();
	minimumDistance = std::numeric_limits<double>::quiet_NaN();
	rms = std::numeric_limits<double>::quiet_NaN();
	quality = "";
    bayesianDepth = std::numeric_limits<double>::quiet_NaN();
	bayesianRange = std::numeric_limits<double>::quiet_NaN();
	depthImportance = std::numeric_limits<double>::quiet_NaN();
	errorEllipse = processingformats::ErrorEllipse();
}

LocationData::LocationData(double newLatitude, double newLongitude,
		double newTime, double newDepth, double newLatitudeError,
		double newLongitudeError, double newTimeError, double newDepthError,
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
		double newEquivalentHorizontalRadius) {
	hypocenter = processingformats::Hypocenter(newLatitude, newLongitude,
												newTime, newDepth,
												newLatitudeError,
												newLongitudeError,
												newTimeError,
												newDepthError);


	// copy data
	associatedData.clear();
	for (int i = 0; i < static_cast<int>(newAssociatedData.size()); i++) {
		associatedData.push_back(newAssociatedData[i]);
	}

	numberOfAssociatedStations = newAssociatedStations;
	numberOfAssociatedPhases = newAssociatedPhases;
	numberOfUsedStations = newUsedStations;
	numberOfUsedPhases = newUsedPhases;
	gap = newGap;
	secondaryGap = newSecondaryGap;
	minimumDistance = newMinimumDistance;
	rms = newRMS;
	quality = newQuality;
    bayesianDepth = newBayesianDepth;
	bayesianRange = newBayesianRange;
	depthImportance = newDepthImportance;
	errorEllipse = processingformats::ErrorEllipse(newE0Error, newE0Azimuth,
													newE0Dip, newE1Error,
													newE1Azimuth, newE1Dip,
													newE2Error, newE2Azimuth,
													newE2Dip,
													newMaximumHorizontalProjection,
													newMaximumVerticalProjection,
													newEquivalentHorizontalRadius);
}

LocationData::LocationData(double newLatitude, double newLongitude,
		double newTime, double newDepth, double newLatitudeError,
		double newLongitudeError, double newTimeError, double newDepthError,
        std::vector<processingformats::Pick> newAssociatedData) {
	hypocenter = processingformats::Hypocenter(newLatitude, newLongitude,
												newTime, newDepth,
												newLatitudeError,
												newLongitudeError,
												newTimeError,
												newDepthError);


	// copy data
	associatedData.clear();
	for (int i = 0; i < static_cast<int>(newAssociatedData.size()); i++) {
		associatedData.push_back(newAssociatedData[i]);
	}
}

LocationData::LocationData(processingformats::Hypocenter newHypocenter,
		std::vector<processingformats::Pick> newAssociatedData) {
	hypocenter = newHypocenter;

	// copy data
	associatedData.clear();
	for (int i = 0; i < static_cast<int>(newAssociatedData.size()); i++) {
		associatedData.push_back(newAssociatedData[i]);
	}
}

}  // namespace processingformats
