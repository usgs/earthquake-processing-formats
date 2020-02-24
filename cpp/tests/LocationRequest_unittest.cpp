#include "processing-formats.h"
#include <gtest/gtest.h>

#include <string>

#define LOCATIONREQUEST_STRING "{\"EarthModel\":\"AK135\",\"SourceLatitude\":40.3344,\"SourceLongitude\":-121.44,\"IsDepthHeld\":false,\"Type\":\"RayLoc\",\"SourceDepth\":32.44,\"IsLocationHeld\":false,\"BayesianSpread\":20.3,\"UseSVD\":true,\"BayesianDepth\":66.7,\"SourceOriginTime\":\"2015-12-28T21:32:24.017Z\",\"InputData\":[{\"Site\":{\"Station\":\"BOZ\",\"Channel\":\"BHZ\",\"Network\":\"US\",\"Location\":\"00\",\"Latitude\":45.59697,\"Longitude\":-111.62967,\"Elevation\":1589.0},\"PickedPhase\":\"P\",\"Use\":true,\"AssociatedPhase\":\"P\",\"Time\":\"2015-12-28T21:32:24.017Z\",\"Residual\":1.05,\"Source\":{\"Type\":\"Unknown\",\"AgencyID\":\"US\",\"Author\":\"TestAuthor\"},\"Weight\":2.65,\"Importance\":3.8,\"Azimuth\":21.5,\"Quality\":0.45,\"Affinity\":1.2,\"ID\":\"12GFH48776857\",\"LocatedPhase\":\"P\",\"Distance\":2.65}],\"IsLocationNew\":false,\"IsBayesianDepth\":true}\"ID\":\"12345678\",\"Source\":{\"Author\":\"TestAuthor\",\"AgencyID\":\"US\",\"Type\":\"Unknown\"}}" // NOLINT
#define ID "12345678"
#define AGENCYID "US"
#define AUTHOR "TestAuthor"
#define TYPE "Unknown"
#define LOCTYPE "RayLoc"
#define EARTHMODEL "AK135"
#define SOURCELATITUDE 40.3344
#define SOURCELONGITUDE -121.44
#define SOURCEORIGINTIME "2015-12-28T21:32:24.017Z"
#define SOURCEDEPTH 32.44
#define INPUTDDATA "{\"ID\":\"12GFH48776857\",\"Site\":{\"Station\":\"BOZ\",\"Channel\":\"BHZ\",\"Network\":\"US\",\"Location\":\"00\",\"Latitude\":45.59697,\"Longitude\":-111.62967,\"Elevation\":1589.0},\"Source\":{\"Author\":\"TestAuthor\",\"AgencyID\":\"US\",\"Type\":\"Unknown\"},\"Time\":\"2015-12-28T21:32:24.017Z\",\"Affinity\":1.2,\"Quality\":0.45,\"Use\":true,\"PickedPhase\":\"P\",\"AssociatedPhase\":\"P\",\"LocatedPhase\":\"P\",\"Residual\":1.05,\"Distance\":2.65,\"Azimuth\":21.5,\"Weight\":2.65,\"Importance\":3.8}" // NOLINT 
#define ISLOCATIONNEW false
#define ISLOCATIONHELD false
#define ISDEPTHHELD false
#define ISBAYESIANDEPTH true
#define BAYESIANDEPTH 66.7
#define BAYESIANSPREAD 20.3
#define USESVD true
#define OUTPUTDATA_STRING "{\"MinimumDistance\":2.14,\"NumberOfUsedStations\":33,\"BayesianRange\":20.3,\"ErrorEllipse\":{\"MaximumVerticalProjection\":1.984,\"EquivalentHorizontalRadius\":1.984,\"MaximumHorizontalProjection\":1.984,\"E0\":{\"Azimuth\":-121.44,\"Error\":40.3344,\"Dip\":32.44},\"E1\":{\"Azimuth\":22.64,\"Error\":12.5,\"Dip\":2.44},\"E2\":{\"Azimuth\":22.64,\"Error\":12.5,\"Dip\":2.44}},\"SupportingData\":[{\"Site\":{\"Station\":\"BMN\",\"Network\":\"LB\",\"Channel\":\"HHZ\",\"Location\":\"01\"},\"PickedPhase\":\"P\",\"Use\":true,\"AssociatedPhase\":\"P\",\"Time\":\"2015-12-28T21:32:24.017Z\",\"Residual\":1.05,\"Source\":{\"Type\":\"Unknown\",\"AgencyID\":\"US\",\"Author\":\"TestAuthor\"},\"Weight\":2.65,\"Importance\":3.8,\"Azimuth\":21.5,\"Quality\":0.45,\"Affinity\":1.2,\"ID\":\"12GFH48776857\",\"LocatedPhase\":\"P\",\"Distance\":2.65}],\"Hypocenter\":{\"LatitudeError\":12.5,\"DepthError\":2.44,\"TimeError\":1.984,\"Latitude\":40.3344,\"Time\":\"2015-12-28T21:32:24.017Z\",\"Longitude\":-121.44,\"Depth\":32.44,\"LongitudeError\":22.64},\"DepthImportance\":1.8,\"Quality\":\"A\",\"Gap\":33.67,\"BayesianDepth\":66.7,\"SecondaryGap\":33.67,\"RMS\":3.8,\"NumberOfAssociatedStations\":11,\"NumberOfAssociatedPhases\":22,\"NumberOfUsedPhases\":44}" // NOLINT





