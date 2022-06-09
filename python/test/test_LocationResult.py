#!/usr/bin/env python3
# -*- coding: utf-8 -*-


# package imports
import processingformats.locationResult
import processingformats.hypocenter
import processingformats.errorEllipse
import processingformats.source

# stdlib imports
import unittest
import datetime


class TestLocationResult(unittest.TestCase):

    ID = "12345678"
    SOURCE = processingformats.source.Source("US", "TestAuthor", "Unknown")
    # HYPOCENTER INCLUDES: lat, long, depth, time, latError, longError, depthError, timeError
    HYPOCENTER = processingformats.hypocenter.Hypocenter(
        40.3344,
        -121.44,
        32.44,
        datetime.datetime(2019, 5, 17, 15, 53, 00, 0),
        12.5,
        22.64,
        2.44,
        1.984,
    )
    PICKJSON = '{"ID": "12GFH48776857", "Site": {"Station": "BOZ", "Channel": "BHZ", "Network": "US", "Location": "00", "Latitude": 45.59697, "Longitude": -111.62967, "Elevation": 1589.0}, "Source": {"Author": "TestAuthor", "AgencyID": "US", "Type": "Unknown"}, "Time": "2015-12-28T21:32:24.017Z", "Affinity": 1.2, "Quality": 0.45, "Use": true, "PickedPhase": "P", "AssociatedPhase": "P", "LocatedPhase": "P", "Residual": 1.05, "Distance": 2.65, "Azimuth": 21.5, "Weight": 2.65, "Importance": 3.8}'
    PICK = processingformats.pick.Pick()
    PICK.fromJSONString(PICKJSON)
    SUPPORTINGDATA = [PICK]
    ASSOCIATEDSTATIONS = 11
    ASSOCIATEDPHASES = 22
    USEDSTATIONS = 33
    USEDPHASES = 44
    GAP = 33.67
    SECONDARYGAP = 33.67
    MINIMUMDISTANCE = 2.14
    RMS = 3.8
    QUALITY = "A"
    BAYESIANDEPTH = 66.7
    BAYESIANRANGE = 20.3
    DEPTHIMPORTANCE = 1.8
    LOCATOREXITCODE = "Success"

    # ERROR ELLIPSE INCLUDES: E0Error, E0Azimuth, E0Dip, E1Error, E1Azimuth, E1Dip, E2Error, E2Azimuth, E2Dip, maxHor, maxVert, equivHorRad
    E0 = processingformats.errorEllipseAxis.ErrorEllipseAxis(40.3344, -121.44, 32.44)
    E1 = processingformats.errorEllipseAxis.ErrorEllipseAxis(12.5, 22.64, 2.44)
    E2 = processingformats.errorEllipseAxis.ErrorEllipseAxis(12.5, 22.64, 2.44)
    ERRORELLIPSE = processingformats.errorEllipse.ErrorEllipse(
        E0, E1, E2, 1.984, 1.984, 1.984
    )

    JSONSTRING = '{"Hypocenter": {"Latitude": 40.3344, "Longitude": -121.44, "Depth": 32.44, "Time": "2019-05-17T15:53:00.000Z", "LatitudeError": 12.5, "LongitudeError": 22.64, "DepthError": 2.44, "TimeError": 1.984}, "MinimumDistance": 2.14, "SupportingData": [{"ID": "12GFH48776857", "Site": {"Station": "BOZ", "Network": "US", "Latitude": 45.59697, "Longitude": -111.62967, "Elevation": 1589.0, "Channel": "BHZ", "Location": "00"}, "Source": {"AgencyID": "US", "Author": "TestAuthor", "Type": "Unknown"}, "Time": "2015-12-28T21:32:24.017Z", "Affinity": 1.2, "Quality": 0.45, "Use": true, "PickedPhase": "P", "AssociatedPhase": "P", "LocatedPhase": "P", "Residual": 1.05, "Distance": 2.65, "Azimuth": 21.5, "Weight": 2.65, "Importance": 3.8}], "ID": "12345678", "Source": {"AgencyID": "US", "Author": "TestAuthor", "Type": "Unknown"}, "NumberOfAssociatedStations": 11, "NumberOfAssociatedPhases": 22, "NumberOfUsedStations": 33, "NumberOfUsedPhases": 44, "Gap": 33.67, "SecondaryGap": 33.67, "RMS": 3.8, "Quality": "A", "BayesianDepth": 66.7, "BayesianRange": 20.3, "DepthImportance": 1.8, "LocatorExitCode": "Success", "ErrorEllipse": {"E0": {"Error": 40.3344, "Azimuth": -121.44, "Dip": 32.44}, "E1": {"Error": 12.5, "Azimuth": 22.64, "Dip": 2.44}, "E2": {"Error": 12.5, "Azimuth": 22.64, "Dip": 2.44}, "MaximumHorizontalProjection": 1.984, "MaximumVerticalProjection": 1.984, "EquivalentHorizontalRadius": 1.984}}'
    DICT = {
        "ID": "12345678",
        "Source": {"AgencyID": "US", "Author": "TestAuthor", "Type": "Unknown"},
        "Hypocenter": {
            "Latitude": 40.3344,
            "Longitude": -121.44,
            "Time": "2019-05-17T15:53:00.000Z",
            "Depth": 32.44,
            "LatitudeError": 12.5,
            "LongitudeError": 22.64,
            "DepthError": 2.44,
            "TimeError": 1.984,
        },
        "SupportingData": [
            {
                "ID": "12GFH48776857",
                "Site": {
                    "Station": "BOZ",
                    "Channel": "BHZ",
                    "Network": "US",
                    "Location": "00",
                    "Latitude": 45.59697,
                    "Longitude": -111.62967,
                    "Elevation": 1589.0,
                },
                "Source": {"Author": "TestAuthor", "AgencyID": "US", "Type": "Unknown"},
                "Time": "2015-12-28T21:32:24.017Z",
                "Affinity": 1.2,
                "Quality": 0.45,
                "Use": True,
                "PickedPhase": "P",
                "AssociatedPhase": "P",
                "LocatedPhase": "P",
                "Residual": 1.05,
                "Distance": 2.65,
                "Azimuth": 21.5,
                "Weight": 2.65,
                "Importance": 3.8,
            }
        ],
        "NumberOfAssociatedStations": 11,
        "NumberOfAssociatedPhases": 22,
        "NumberOfUsedStations": 33,
        "NumberOfUsedPhases": 44,
        "Gap": 33.67,
        "SecondaryGap": 33.67,
        "MinimumDistance": 2.14,
        "RMS": 3.8,
        "Quality": "A",
        "BayesianDepth": 66.7,
        "BayesianRange": 20.3,
        "DepthImportance": 1.8,
        "LocatorExitCode": "Success",
        "ErrorEllipse": {
            "E0": {"Error": 40.3344, "Azimuth": -121.44, "Dip": 32.44},
            "E1": {"Error": 12.5, "Azimuth": 22.64, "Dip": 2.44},
            "E2": {"Error": 12.5, "Azimuth": 22.64, "Dip": 2.44},
            "MaximumHorizontalProjection": 1.984,
            "MaximumVerticalProjection": 1.984,
            "EquivalentHorizontalRadius": 1.984,
        },
    }

    def test_init(self):

        locationResult = processingformats.locationResult.LocationResult()

        self.assertFalse(hasattr(locationResult, "id"))
        self.assertFalse(hasattr(locationResult.source, "agencyID"))
        self.assertFalse(hasattr(locationResult.source, "author"))
        self.assertFalse(hasattr(locationResult.source, "type"))
        self.assertFalse(hasattr(locationResult.hypocenter, "latitude"))
        self.assertFalse(hasattr(locationResult.hypocenter, "longitude"))
        self.assertFalse(hasattr(locationResult.hypocenter, "depth"))
        self.assertFalse(hasattr(locationResult.hypocenter, "time"))
        self.assertFalse(hasattr(locationResult.hypocenter, "latitudeError"))
        self.assertFalse(hasattr(locationResult.hypocenter, "longitudeError"))
        self.assertFalse(hasattr(locationResult.hypocenter, "depthError"))
        self.assertFalse(hasattr(locationResult.hypocenter, "timeError"))
        self.assertFalse(hasattr(locationResult, "supportingData"))
        self.assertFalse(hasattr(locationResult, "associatedStations"))
        self.assertFalse(hasattr(locationResult, "associatedPhases"))
        self.assertFalse(hasattr(locationResult, "usedStations"))
        self.assertFalse(hasattr(locationResult, "usedPhases"))
        self.assertFalse(hasattr(locationResult, "gap"))
        self.assertFalse(hasattr(locationResult, "secondary gap"))
        self.assertFalse(hasattr(locationResult, "minimumDistance"))
        self.assertFalse(hasattr(locationResult, "rms"))
        self.assertFalse(hasattr(locationResult, "quality"))
        self.assertFalse(hasattr(locationResult, "bayesianDepth"))
        self.assertFalse(hasattr(locationResult, "bayesianRange"))
        self.assertFalse(hasattr(locationResult, "depthImportance"))
        self.assertFalse(hasattr(locationResult, "locatorExitCode"))
        self.assertFalse(hasattr(locationResult.errorEllipse.e0, "error"))
        self.assertFalse(hasattr(locationResult.errorEllipse.e0, "azimuth"))
        self.assertFalse(hasattr(locationResult.errorEllipse.e0, "dip"))
        self.assertFalse(hasattr(locationResult.errorEllipse.e1, "error"))
        self.assertFalse(hasattr(locationResult.errorEllipse.e1, "azimuth"))
        self.assertFalse(hasattr(locationResult.errorEllipse.e1, "dip"))
        self.assertFalse(hasattr(locationResult.errorEllipse.e2, "error"))
        self.assertFalse(hasattr(locationResult.errorEllipse.e2, "azimuth"))
        self.assertFalse(hasattr(locationResult.errorEllipse.e2, "dip"))
        self.assertFalse(
            hasattr(locationResult.errorEllipse, "maximumHorizontalProjection")
        )
        self.assertFalse(
            hasattr(locationResult.errorEllipse, "maximumVerticalProjection")
        )
        self.assertFalse(
            hasattr(locationResult.errorEllipse, "equivalentHorizontalRadius")
        )

        locationResult = processingformats.locationResult.LocationResult(
            self.ID,
            self.SOURCE,
            self.HYPOCENTER,
            self.SUPPORTINGDATA,
            self.ASSOCIATEDSTATIONS,
            self.ASSOCIATEDPHASES,
            self.USEDSTATIONS,
            self.USEDPHASES,
            self.GAP,
            self.SECONDARYGAP,
            self.MINIMUMDISTANCE,
            self.RMS,
            self.QUALITY,
            self.BAYESIANDEPTH,
            self.BAYESIANRANGE,
            self.DEPTHIMPORTANCE,
            self.LOCATOREXITCODE,
            self.ERRORELLIPSE,
        )

        self.assertTrue(hasattr(locationResult, "id"))
        self.assertTrue(hasattr(locationResult.source, "agencyID"))
        self.assertTrue(hasattr(locationResult.source, "author"))
        self.assertTrue(hasattr(locationResult.source, "type"))
        self.assertTrue(hasattr(locationResult.hypocenter, "latitude"))
        self.assertTrue(hasattr(locationResult.hypocenter, "longitude"))
        self.assertTrue(hasattr(locationResult.hypocenter, "depth"))
        self.assertTrue(hasattr(locationResult.hypocenter, "time"))
        self.assertTrue(hasattr(locationResult.hypocenter, "latitudeError"))
        self.assertTrue(hasattr(locationResult.hypocenter, "longitudeError"))
        self.assertTrue(hasattr(locationResult.hypocenter, "depthError"))
        self.assertTrue(hasattr(locationResult.hypocenter, "timeError"))
        self.assertTrue(hasattr(locationResult, "supportingData"))
        self.assertTrue(hasattr(locationResult, "associatedStations"))
        self.assertTrue(hasattr(locationResult, "associatedPhases"))
        self.assertTrue(hasattr(locationResult, "usedStations"))
        self.assertTrue(hasattr(locationResult, "usedPhases"))
        self.assertTrue(hasattr(locationResult, "gap"))
        self.assertTrue(hasattr(locationResult, "secondaryGap"))
        self.assertTrue(hasattr(locationResult, "minimumDistance"))
        self.assertTrue(hasattr(locationResult, "rms"))
        self.assertTrue(hasattr(locationResult, "quality"))
        self.assertTrue(hasattr(locationResult, "bayesianDepth"))
        self.assertTrue(hasattr(locationResult, "bayesianRange"))
        self.assertTrue(hasattr(locationResult, "depthImportance"))
        self.assertTrue(hasattr(locationResult, "locatorExitCode"))
        self.assertTrue(hasattr(locationResult.errorEllipse.e0, "error"))
        self.assertTrue(hasattr(locationResult.errorEllipse.e0, "azimuth"))
        self.assertTrue(hasattr(locationResult.errorEllipse.e0, "dip"))
        self.assertTrue(hasattr(locationResult.errorEllipse.e1, "error"))
        self.assertTrue(hasattr(locationResult.errorEllipse.e1, "azimuth"))
        self.assertTrue(hasattr(locationResult.errorEllipse.e1, "dip"))
        self.assertTrue(hasattr(locationResult.errorEllipse.e2, "error"))
        self.assertTrue(hasattr(locationResult.errorEllipse.e2, "azimuth"))
        self.assertTrue(hasattr(locationResult.errorEllipse.e2, "dip"))
        self.assertTrue(
            hasattr(locationResult.errorEllipse, "maximumHorizontalProjection")
        )
        self.assertTrue(
            hasattr(locationResult.errorEllipse, "maximumVerticalProjection")
        )
        self.assertTrue(
            hasattr(locationResult.errorEllipse, "equivalentHorizontalRadius")
        )

        self.assertEqual(locationResult.id, self.ID)
        self.assertEqual(locationResult.source.agencyID, self.SOURCE.agencyID)
        self.assertEqual(locationResult.source.author, self.SOURCE.author)
        self.assertEqual(locationResult.source.type, self.SOURCE.type)
        self.assertEqual(locationResult.hypocenter.latitude, self.HYPOCENTER.latitude)
        self.assertEqual(locationResult.hypocenter.longitude, self.HYPOCENTER.longitude)
        self.assertEqual(locationResult.hypocenter.depth, self.HYPOCENTER.depth)
        self.assertEqual(locationResult.hypocenter.time, self.HYPOCENTER.time)
        self.assertEqual(
            locationResult.hypocenter.latitudeError, self.HYPOCENTER.latitudeError
        )
        self.assertEqual(
            locationResult.hypocenter.longitudeError, self.HYPOCENTER.longitudeError
        )
        self.assertEqual(
            locationResult.hypocenter.depthError, self.HYPOCENTER.depthError
        )
        self.assertEqual(locationResult.hypocenter.timeError, self.HYPOCENTER.timeError)
        self.assertEqual(locationResult.supportingData, self.SUPPORTINGDATA)
        self.assertEqual(locationResult.associatedStations, self.ASSOCIATEDSTATIONS)
        self.assertEqual(locationResult.associatedPhases, self.ASSOCIATEDPHASES)
        self.assertEqual(locationResult.usedStations, self.USEDSTATIONS)
        self.assertEqual(locationResult.usedPhases, self.USEDPHASES)
        self.assertEqual(locationResult.gap, self.GAP)
        self.assertEqual(locationResult.secondaryGap, self.SECONDARYGAP)
        self.assertEqual(locationResult.minimumDistance, self.MINIMUMDISTANCE)
        self.assertEqual(locationResult.rms, self.RMS)
        self.assertEqual(locationResult.quality, self.QUALITY)
        self.assertEqual(locationResult.bayesianDepth, self.BAYESIANDEPTH)
        self.assertEqual(locationResult.bayesianRange, self.BAYESIANRANGE)
        self.assertEqual(locationResult.depthImportance, self.DEPTHIMPORTANCE)
        self.assertEqual(locationResult.locatorExitCode, self.LOCATOREXITCODE)
        self.assertEqual(
            locationResult.errorEllipse.e0.error, self.ERRORELLIPSE.e0.error
        )
        self.assertEqual(
            locationResult.errorEllipse.e0.azimuth, self.ERRORELLIPSE.e0.azimuth
        )
        self.assertEqual(locationResult.errorEllipse.e0.dip, self.ERRORELLIPSE.e0.dip)
        self.assertEqual(
            locationResult.errorEllipse.e1.error, self.ERRORELLIPSE.e1.error
        )
        self.assertEqual(
            locationResult.errorEllipse.e1.azimuth, self.ERRORELLIPSE.e1.azimuth
        )
        self.assertEqual(locationResult.errorEllipse.e1.dip, self.ERRORELLIPSE.e1.dip)
        self.assertEqual(
            locationResult.errorEllipse.e2.error, self.ERRORELLIPSE.e2.error
        )
        self.assertEqual(
            locationResult.errorEllipse.e2.azimuth, self.ERRORELLIPSE.e2.azimuth
        )
        self.assertEqual(locationResult.errorEllipse.e2.dip, self.ERRORELLIPSE.e2.dip)
        self.assertEqual(
            locationResult.errorEllipse.maximumHorizontalProjection,
            self.ERRORELLIPSE.maximumHorizontalProjection,
        )
        self.assertEqual(
            locationResult.errorEllipse.maximumVerticalProjection,
            self.ERRORELLIPSE.maximumVerticalProjection,
        )
        self.assertEqual(
            locationResult.errorEllipse.equivalentHorizontalRadius,
            self.ERRORELLIPSE.equivalentHorizontalRadius,
        )

    def test_toJSON(self):
        locationResult = processingformats.locationResult.LocationResult(
            self.ID,
            self.SOURCE,
            self.HYPOCENTER,
            self.SUPPORTINGDATA,
            self.ASSOCIATEDSTATIONS,
            self.ASSOCIATEDPHASES,
            self.USEDSTATIONS,
            self.USEDPHASES,
            self.GAP,
            self.SECONDARYGAP,
            self.MINIMUMDISTANCE,
            self.RMS,
            self.QUALITY,
            self.BAYESIANDEPTH,
            self.BAYESIANRANGE,
            self.DEPTHIMPORTANCE,
            self.LOCATOREXITCODE,
            self.ERRORELLIPSE,
        )
        self.maxDiff = None
        self.assertEqual(locationResult.toJSONString(), self.JSONSTRING)

    def test_fromJSON(self):
        locationResult = processingformats.locationResult.LocationResult()
        locationResult.fromJSONString(self.JSONSTRING)

        self.assertEqual(locationResult.id, self.ID)
        self.assertEqual(locationResult.source.agencyID, self.SOURCE.agencyID)
        self.assertEqual(locationResult.source.author, self.SOURCE.author)
        self.assertEqual(locationResult.source.type, self.SOURCE.type)
        self.assertEqual(locationResult.hypocenter.latitude, self.HYPOCENTER.latitude)
        self.assertEqual(locationResult.hypocenter.longitude, self.HYPOCENTER.longitude)
        self.assertEqual(locationResult.hypocenter.depth, self.HYPOCENTER.depth)
        self.assertEqual(locationResult.hypocenter.time, self.HYPOCENTER.time)
        self.assertEqual(
            locationResult.hypocenter.latitudeError, self.HYPOCENTER.latitudeError
        )
        self.assertEqual(
            locationResult.hypocenter.longitudeError, self.HYPOCENTER.longitudeError
        )
        self.assertEqual(
            locationResult.hypocenter.depthError, self.HYPOCENTER.depthError
        )
        self.assertEqual(locationResult.hypocenter.timeError, self.HYPOCENTER.timeError)
        self.assertEqual(locationResult.associatedStations, self.ASSOCIATEDSTATIONS)
        self.assertEqual(locationResult.associatedPhases, self.ASSOCIATEDPHASES)
        self.assertEqual(locationResult.usedStations, self.USEDSTATIONS)
        self.assertEqual(locationResult.usedPhases, self.USEDPHASES)
        self.assertEqual(locationResult.gap, self.GAP)
        self.assertEqual(locationResult.secondaryGap, self.SECONDARYGAP)
        self.assertEqual(locationResult.minimumDistance, self.MINIMUMDISTANCE)
        self.assertEqual(locationResult.rms, self.RMS)
        self.assertEqual(locationResult.quality, self.QUALITY)
        self.assertEqual(locationResult.bayesianDepth, self.BAYESIANDEPTH)
        self.assertEqual(locationResult.bayesianRange, self.BAYESIANRANGE)
        self.assertEqual(locationResult.depthImportance, self.DEPTHIMPORTANCE)
        self.assertEqual(locationResult.locatorExitCode, self.LOCATOREXITCODE)
        self.assertEqual(
            locationResult.errorEllipse.e0.error, self.ERRORELLIPSE.e0.error
        )
        self.assertEqual(
            locationResult.errorEllipse.e0.azimuth, self.ERRORELLIPSE.e0.azimuth
        )
        self.assertEqual(locationResult.errorEllipse.e0.dip, self.ERRORELLIPSE.e0.dip)
        self.assertEqual(
            locationResult.errorEllipse.e1.error, self.ERRORELLIPSE.e1.error
        )
        self.assertEqual(
            locationResult.errorEllipse.e1.azimuth, self.ERRORELLIPSE.e1.azimuth
        )
        self.assertEqual(locationResult.errorEllipse.e1.dip, self.ERRORELLIPSE.e1.dip)
        self.assertEqual(
            locationResult.errorEllipse.e2.error, self.ERRORELLIPSE.e2.error
        )
        self.assertEqual(
            locationResult.errorEllipse.e2.azimuth, self.ERRORELLIPSE.e2.azimuth
        )
        self.assertEqual(locationResult.errorEllipse.e2.dip, self.ERRORELLIPSE.e2.dip)
        self.assertEqual(
            locationResult.errorEllipse.maximumHorizontalProjection,
            self.ERRORELLIPSE.maximumHorizontalProjection,
        )
        self.assertEqual(
            locationResult.errorEllipse.maximumVerticalProjection,
            self.ERRORELLIPSE.maximumVerticalProjection,
        )
        self.assertEqual(
            locationResult.errorEllipse.equivalentHorizontalRadius,
            self.ERRORELLIPSE.equivalentHorizontalRadius,
        )

    def test_toDict(self):
        locationResult = processingformats.locationResult.LocationResult(
            self.ID,
            self.SOURCE,
            self.HYPOCENTER,
            self.SUPPORTINGDATA,
            self.ASSOCIATEDSTATIONS,
            self.ASSOCIATEDPHASES,
            self.USEDSTATIONS,
            self.USEDPHASES,
            self.GAP,
            self.SECONDARYGAP,
            self.MINIMUMDISTANCE,
            self.RMS,
            self.QUALITY,
            self.BAYESIANDEPTH,
            self.BAYESIANRANGE,
            self.DEPTHIMPORTANCE,
            self.LOCATOREXITCODE,
            self.ERRORELLIPSE,
        )
        self.maxDiff = None
        self.assertEqual(locationResult.toDict(), self.DICT)

    def test_fromDict(self):
        locationResult = processingformats.locationResult.LocationResult()
        locationResult.fromDict(self.DICT)

        self.assertEqual(locationResult.id, self.ID)
        self.assertEqual(locationResult.source.agencyID, self.SOURCE.agencyID)
        self.assertEqual(locationResult.source.author, self.SOURCE.author)
        self.assertEqual(locationResult.source.type, self.SOURCE.type)
        self.assertEqual(locationResult.hypocenter.latitude, self.HYPOCENTER.latitude)
        self.assertEqual(locationResult.hypocenter.longitude, self.HYPOCENTER.longitude)
        self.assertEqual(locationResult.hypocenter.depth, self.HYPOCENTER.depth)
        self.assertEqual(locationResult.hypocenter.time, self.HYPOCENTER.time)
        self.assertEqual(
            locationResult.hypocenter.latitudeError, self.HYPOCENTER.latitudeError
        )
        self.assertEqual(
            locationResult.hypocenter.longitudeError, self.HYPOCENTER.longitudeError
        )
        self.assertEqual(
            locationResult.hypocenter.depthError, self.HYPOCENTER.depthError
        )
        self.assertEqual(locationResult.hypocenter.timeError, self.HYPOCENTER.timeError)
        self.assertEqual(locationResult.associatedStations, self.ASSOCIATEDSTATIONS)
        self.assertEqual(locationResult.associatedPhases, self.ASSOCIATEDPHASES)
        self.assertEqual(locationResult.usedStations, self.USEDSTATIONS)
        self.assertEqual(locationResult.usedPhases, self.USEDPHASES)
        self.assertEqual(locationResult.gap, self.GAP)
        self.assertEqual(locationResult.secondaryGap, self.SECONDARYGAP)
        self.assertEqual(locationResult.minimumDistance, self.MINIMUMDISTANCE)
        self.assertEqual(locationResult.rms, self.RMS)
        self.assertEqual(locationResult.quality, self.QUALITY)
        self.assertEqual(locationResult.bayesianDepth, self.BAYESIANDEPTH)
        self.assertEqual(locationResult.bayesianRange, self.BAYESIANRANGE)
        self.assertEqual(locationResult.depthImportance, self.DEPTHIMPORTANCE)
        self.assertEqual(locationResult.locatorExitCode, self.LOCATOREXITCODE)
        self.assertEqual(
            locationResult.errorEllipse.e0.error, self.ERRORELLIPSE.e0.error
        )
        self.assertEqual(
            locationResult.errorEllipse.e0.azimuth, self.ERRORELLIPSE.e0.azimuth
        )
        self.assertEqual(locationResult.errorEllipse.e0.dip, self.ERRORELLIPSE.e0.dip)
        self.assertEqual(
            locationResult.errorEllipse.e1.error, self.ERRORELLIPSE.e1.error
        )
        self.assertEqual(
            locationResult.errorEllipse.e1.azimuth, self.ERRORELLIPSE.e1.azimuth
        )
        self.assertEqual(locationResult.errorEllipse.e1.dip, self.ERRORELLIPSE.e1.dip)
        self.assertEqual(
            locationResult.errorEllipse.e2.error, self.ERRORELLIPSE.e2.error
        )
        self.assertEqual(
            locationResult.errorEllipse.e2.azimuth, self.ERRORELLIPSE.e2.azimuth
        )
        self.assertEqual(locationResult.errorEllipse.e2.dip, self.ERRORELLIPSE.e2.dip)
        self.assertEqual(
            locationResult.errorEllipse.maximumHorizontalProjection,
            self.ERRORELLIPSE.maximumHorizontalProjection,
        )
        self.assertEqual(
            locationResult.errorEllipse.maximumVerticalProjection,
            self.ERRORELLIPSE.maximumVerticalProjection,
        )
        self.assertEqual(
            locationResult.errorEllipse.equivalentHorizontalRadius,
            self.ERRORELLIPSE.equivalentHorizontalRadius,
        )

    def test_isValid(self):
        locationResult = processingformats.locationResult.LocationResult(
            self.ID,
            self.SOURCE,
            self.HYPOCENTER,
            self.SUPPORTINGDATA,
            self.ASSOCIATEDSTATIONS,
            self.ASSOCIATEDPHASES,
            self.USEDSTATIONS,
            self.USEDPHASES,
            self.GAP,
            self.SECONDARYGAP,
            self.MINIMUMDISTANCE,
            self.RMS,
            self.QUALITY,
            self.BAYESIANDEPTH,
            self.BAYESIANRANGE,
            self.DEPTHIMPORTANCE,
            self.LOCATOREXITCODE,
            self.ERRORELLIPSE,
        )
        self.assertTrue(locationResult.isValid())

        badLocationResult = processingformats.locationResult.LocationResult()
        self.assertFalse(badLocationResult.isValid())


if __name__ == "__main__":
    unittest.main()
