#include "processing-formats.h"
#include <gtest/gtest.h>

#include <string>

// test data
#define TRAVELTIMEREQUESTSTANDARD_STRING "{\"Data\":[{\"LocationUseFlag\":true,\"DistanceDerivative\":1.2,\"DepthDerivative\":3.45,\"AssociationWeightFlag\":true,\"Type\":\"TTData\",\"TeleseismicPhaseGroup\":1,\"Phase\":\"Pg\",\"RayDerivative\":5.67,\"AuxiliaryPhaseGroup\":1,\"Observability\":0.34,\"StatisticalSpread\":1.5,\"TravelTime\":22.456}],\"Distance\":12.45,\"Type\":\"Standard\",\"Elevation\":5280.5,\"Latitude\":39.749444,\"Longitude\":-105.220305}"
#define TRAVELTIMEREQUESTPLOT_STRING "{\"Data\":[{\"Branches\":[{\"Phase\":\"Pg\",\"Samples\":[{\"Distance\":1.2,\"Observability\":0.34,\"StatisticalSpread\":1.5,\"TravelTime\":22.456},{\"Distance\":10.5,\"Observability\":1.63,\"StatisticalSpread\":2.1,\"TravelTime\":72.654}]},{\"Phase\":\"Sg\",\"Samples\":[{\"Distance\":3.2,\"Observability\":1.14,\"StatisticalSpread\":3.25,\"TravelTime\":132.456},{\"Distance\":100.5,\"Observability\":6.21,\"StatisticalSpread\":5.1,\"TravelTime\":542.654}]}],\"Type\":\"TTPlotData\",\"MaximumTravelTime\":12.5}],\"Distance\":12.45,\"Type\":\"Plot\",\"Elevation\":5280.5,\"Latitude\":39.749444,\"Longitude\":-105.220305}"
#define TRAVELTIMEREQUESTPLOTSTATISTICS_STRING "{\"Data\":[{\"Branches\":[{\"Phase\":\"Pg\",\"Samples\":[{\"Distance\":1.2,\"Observability\":0.34,\"StatisticalSpread\":1.5,\"TravelTime\":22.456},{\"Distance\":10.5,\"Observability\":1.63,\"StatisticalSpread\":2.1,\"TravelTime\":72.654}]},{\"Phase\":\"Sg\",\"Samples\":[{\"Distance\":3.2,\"Observability\":1.14,\"StatisticalSpread\":3.25,\"TravelTime\":132.456},{\"Distance\":100.5,\"Observability\":6.21,\"StatisticalSpread\":5.1,\"TravelTime\":542.654}]}],\"Type\":\"TTPlotData\",\"MaximumTravelTime\":12.5}],\"Distance\":12.45,\"Type\":\"PlotStatistics\",\"Elevation\":5280.5,\"Latitude\":39.749444,\"Longitude\":-105.220305}"
#define TRAVELTIMEDATA_STRING "{\"Type\":\"TTData\",\"LocationUseFlag\":true,\"DistanceDerivative\":1.2,\"DepthDerivative\":3.45,\"AssociationWeightFlag\":true,\"TeleseismicPhaseGroup\":\"P\",\"Phase\":\"Pg\",\"RayDerivative\":5.67,\"AuxiliaryPhaseGroup\":\"P\",\"Observability\":0.34,\"StatisticalSpread\":1.5,\"TravelTime\":22.456}"
#define TRAVELTIMEPLOTDATA_STRING "{\"Type\":\"TTPlotData\",\"Branches\":[{\"Phase\":\"Pg\",\"Samples\":[{\"Distance\":1.2,\"Observability\":0.34,\"StatisticalSpread\":1.5,\"TravelTime\":22.456},{\"Distance\":10.5,\"Observability\":1.63,\"StatisticalSpread\":2.1,\"TravelTime\":72.654}]},{\"Phase\":\"Sg\",\"Samples\":[{\"Distance\":3.2,\"Observability\":1.14,\"StatisticalSpread\":3.25,\"TravelTime\":132.456},{\"Distance\":100.5,\"Observability\":6.21,\"StatisticalSpread\":5.1,\"TravelTime\":542.654}]}],\"MaximumTravelTime\":12.5}"
#define TYPE_STANDARD "Standard"
#define TYPE_PLOT "Plot"
#define TYPE_PLOTSTATISTICS "PlotStatistics"
#define DISTANCE 12.45
#define ELEVATION 5280.5
#define LATITUDE 39.749444
#define LONGITUDE -105.220305

void checkdata(processingformats::TravelTimeRequest travelTimeRequestObject,
				std::string testinfo) {

	// check type value
	if (!((travelTimeRequestObject.type == TYPE_STANDARD)
			|| (travelTimeRequestObject.type == TYPE_PLOT)
			|| (travelTimeRequestObject.type == TYPE_PLOTSTATISTICS))) {
		FAIL()<< " Type is not valid";
	}

	// check travelTimeRequestObject.distance
	ASSERT_EQ(DISTANCE, travelTimeRequestObject.distance);

	// check travelTimeRequestObject.elevation
	ASSERT_EQ(ELEVATION, travelTimeRequestObject.elevation);

	// check travelTimeRequestObject.latitude
	ASSERT_EQ(LATITUDE, travelTimeRequestObject.latitude);

	// check travelTimeRequestObject.longitude
	ASSERT_EQ(LONGITUDE, travelTimeRequestObject.longitude);
}

std::vector<processingformats::TravelTimeData> buildData() {
	std::vector<processingformats::TravelTimeData> newData;

	rapidjson::Document TravelTimeDataDocument;

	processingformats::TravelTimeData travelTimeDataObject(
			processingformats::FromJSONString(
					std::string(TRAVELTIMEDATA_STRING),
					TravelTimeDataDocument));

	newData.push_back(travelTimeDataObject);

	return (newData);
}

std::vector<processingformats::TravelTimePlotData> buildPlotData() {
	std::vector<processingformats::TravelTimePlotData> newPlotData;

	rapidjson::Document TravelTimePlotDataDocument;

	processingformats::TravelTimePlotData travelTimePlotDataObject(
			processingformats::FromJSONString(
					std::string(TRAVELTIMEPLOTDATA_STRING),
					TravelTimePlotDataDocument));

	newPlotData.push_back(travelTimePlotDataObject);

	return (newPlotData);
}

// tests to see if TravelTimeRequest can successfully
// write json output
TEST(TravelTimeRequestTest, WritesJSON) {
	processingformats::TravelTimeRequest travelTimeRequestObject;
	rapidjson::Document TravelTimeRequestdocument;
	// standard request
	travelTimeRequestObject.type = TYPE_STANDARD;
	travelTimeRequestObject.distance = DISTANCE;
	travelTimeRequestObject.elevation = ELEVATION;
	travelTimeRequestObject.latitude = LATITUDE;
	travelTimeRequestObject.longitude = LONGITUDE;
	travelTimeRequestObject.data = buildData();

	// build json string
	std::string TravelTimeRequestjson = processingformats::ToJSONString(
			travelTimeRequestObject.toJSON(
					TravelTimeRequestdocument,
					TravelTimeRequestdocument.GetAllocator()));

	// read it back in
	rapidjson::Document TravelTimeRequestdocument2;
	processingformats::TravelTimeRequest travelTimeRequestObject2(
			processingformats::FromJSONString(TravelTimeRequestjson,
												TravelTimeRequestdocument2));

	// check data values
	checkdata(travelTimeRequestObject2, "");

	// plot request
	travelTimeRequestObject.type = TYPE_PLOT;
	travelTimeRequestObject.distance = DISTANCE;
	travelTimeRequestObject.elevation = ELEVATION;
	travelTimeRequestObject.latitude = LATITUDE;
	travelTimeRequestObject.longitude = LONGITUDE;
	travelTimeRequestObject.plotData = buildPlotData();

	// build json string
	TravelTimeRequestjson = processingformats::ToJSONString(
			travelTimeRequestObject.toJSON(
					TravelTimeRequestdocument,
					TravelTimeRequestdocument.GetAllocator()));

	// read it back in
	rapidjson::Document TravelTimeRequestdocument3;
	processingformats::TravelTimeRequest travelTimeRequestObject3(
			processingformats::FromJSONString(TravelTimeRequestjson,
												TravelTimeRequestdocument3));

	// check data values
	checkdata(travelTimeRequestObject3, "");

	// plot statistics request
	travelTimeRequestObject.type = TYPE_PLOTSTATISTICS;
	travelTimeRequestObject.distance = DISTANCE;
	travelTimeRequestObject.elevation = ELEVATION;
	travelTimeRequestObject.latitude = LATITUDE;
	travelTimeRequestObject.longitude = LONGITUDE;
	travelTimeRequestObject.plotData = buildPlotData();

	// build json string
	TravelTimeRequestjson = processingformats::ToJSONString(
			travelTimeRequestObject.toJSON(
					TravelTimeRequestdocument,
					TravelTimeRequestdocument.GetAllocator()));

	// read it back in
	rapidjson::Document TravelTimeRequestdocument4;
	processingformats::TravelTimeRequest travelTimeRequestObject4(
			processingformats::FromJSONString(TravelTimeRequestjson,
												TravelTimeRequestdocument4));

	// check data values
	checkdata(travelTimeRequestObject4, "");
}

// tests to see if TravelTimeRequest can successfully
// read json output
TEST(TravelTimeRequestTest, ReadsJSON) {

	// build TravelTimeRequest object
	rapidjson::Document TravelTimeRequestdocument;
	processingformats::TravelTimeRequest travelTimeRequestObject(
			processingformats::FromJSONString(
					std::string(TRAVELTIMEREQUESTSTANDARD_STRING),
					TravelTimeRequestdocument));

	// check data values
	checkdata(travelTimeRequestObject, "");

	// build TravelTimeRequest object
	rapidjson::Document TravelTimeRequestdocument2;
	processingformats::TravelTimeRequest travelTimeRequestObject2(
			processingformats::FromJSONString(
					std::string(TRAVELTIMEREQUESTPLOT_STRING),
					TravelTimeRequestdocument2));

	// check data values
	checkdata(travelTimeRequestObject2, "");

	// build TravelTimeRequest object
	rapidjson::Document TravelTimeRequestdocument3;
	processingformats::TravelTimeRequest travelTimeRequestObject3(
			processingformats::FromJSONString(
					std::string(TRAVELTIMEREQUESTPLOTSTATISTICS_STRING),
					TravelTimeRequestdocument3));

	// check data values
	checkdata(travelTimeRequestObject3, "");

}

// tests to see if TravelTimeRequest can successfully
// be constructed
TEST(TravelTimeRequestTest, Constructor) {
	std::vector<processingformats::TravelTimePlotData> newPlotData;
	// use constructor
	processingformats::TravelTimeRequest travelTimeRequestObject(
			TYPE_STANDARD, DISTANCE, ELEVATION, LATITUDE, LONGITUDE,
			buildData(), newPlotData);

	// check data values
	checkdata(travelTimeRequestObject, "");
}

// tests to see if TravelTimeRequest can successfully
// validate
TEST(TravelTimeRequestTest, Validate) {
	processingformats::TravelTimeRequest travelTimeRequestObject;

	// standard request
	travelTimeRequestObject.type = TYPE_STANDARD;
	travelTimeRequestObject.distance = DISTANCE;
	travelTimeRequestObject.elevation = ELEVATION;
	travelTimeRequestObject.latitude = LATITUDE;
	travelTimeRequestObject.longitude = LONGITUDE;
	travelTimeRequestObject.data = buildData();

	// successful validation
	bool result = travelTimeRequestObject.isValid();

	if (result == false) {
		std::vector<std::string> errorlist =
				travelTimeRequestObject.getErrors();

		for (int i = 0; i < errorlist.size(); i++) {
			printf("%s\n", errorlist[i].c_str());
		}
	}

	// check return code
	ASSERT_EQ(result, true)<< "Tested for successful validation.";

	// standard request
	travelTimeRequestObject.type = TYPE_STANDARD;
	travelTimeRequestObject.distance = std::numeric_limits<double>::quiet_NaN();
	travelTimeRequestObject.elevation =
			std::numeric_limits<double>::quiet_NaN();
	travelTimeRequestObject.latitude = LATITUDE;
	travelTimeRequestObject.longitude = LONGITUDE;
	travelTimeRequestObject.data = buildData();

	result = travelTimeRequestObject.isValid();

	// check return code
	ASSERT_EQ(result, false)<< "Tested for unsuccessful Validation";
}

