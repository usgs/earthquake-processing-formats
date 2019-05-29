#!/usr/bin/env python3
# -*- coding: utf-8 -*-


#package imports
import processingformats.hypocenter
import processingformats.errorEllipse

#stdlib imports
import unittest
import datetime

class TestLocationResult(unittest.TestCase):
    
    ID = '12345678'
    #HYPOCENTER INCLUDES: lat, long, depth, time, latError, longError, depthError, timeError
    HYPOCENTER = processingformats.hypocenter.Hypocenter(40.3344, -121.44, 32.44, datetime.datetime(2019, 5, 17, 15, 53, 00, 0), 12.5, 22.64, 2.44, 1.984)
    SUPPORTINGDATA = '{"ID": "12GFH48776857", "Site": {"Station": "BOZ", "Channel": "BHZ", "Network": "US", "Location": "00", "Latitude": 45.59697, "Longitude": -111.62967, "Elevation": 1589.0}, "Source": {"Author": "TestAuthor", "AgencyID": "US", "Type": "Unknown"}, "Time": "2015-12-28T21:32:24.017Z", "Affinity": 1.2, "Quality": 0.45, "Use": True, "PickedPhase": "P", "AssociatedPhase": "P", "LocatedPhase": "P", "Residual": 1.05, "Distance": 2.65, "Azimuth": 21.5, "Weight": 2.65, "Importance": 3.8}'
    ASSOCIATEDSTATIONS = 11
    ASSOCIATEDPHASES = 22
    USEDSTATIONS = 33
    USEDPHASES = 44
    GAP = 33.67
    SECONDARYGAP = 33.67
    MINIMUMDISTANCE = 2.14
    RMS = 3.8
    QUALITY = 'A'
    BAYESIANDEPTH = 66.7
    BAYESIANRANGE = 20.3
    DEPTHIMPORTANCE = 1.8
    LOCATOREXITCODE = 'Success'
    #ERROR ELLIPSE INCLUDES: E0Error, E0Azimuth, E0Dip, E1Error, E1Azimuth, E1Dip, E2Error, E2Azimuth, E2Dip, maxHor, maxVert, equivHorRad
    ERRORELLIPSE = processingformats.errorEllipse.ErrorEllipse(40.3344, -121.44, 32.44, 12.5, 22.64, 2.44, 12.5, 22.64, 2.44, 1.984, 1.984, 1.984)
    
    JSONSTRING = '{"ID": "12345678", "Hypocenter": {"Latitude": 40.3344, "Longitude": -121.44, "Time": "2019-05-17T15:53:00.000Z", "Depth": 32.44, "LatitudeError": 12.5, "LongitudeError": 22.64, "DepthError": 2.44, "TimeError": 1.984}, "SupportingData": [{"ID": "12GFH48776857", "Site": {"Station": "BOZ", "Channel": "BHZ", "Network": "US", "Location": "00", "Latitude": 45.59697, "Longitude": -111.62967, "Elevation": 1589.0}, "Source": {"Author": "TestAuthor", "AgencyID": "US", "Type": "Unknown"}, "Time": "2015-12-28T21:32:24.017Z", "Affinity": 1.2, "Quality": 0.45, "Use": True, "PickedPhase": "P", "AssociatedPhase": "P", "LocatedPhase": "P", "Residual": 1.05, "Distance": 2.65, "Azimuth": 21.5, "Weight": 2.65, "Importance": 3.8}], "NumAssociatedStations": 11, "NumAssociatedPhases": "22", "NumUsedStations": 33, "NumUsedPhases": 44, "Gap": 33.67, "SecondaryGap": 33.67, "MinimumDistance": 2.14, "RMS": 3.8, "Quality": "A", "BayesianDepth": 66.7, "BayesianRange": 20.3, "DepthImportance": 1.8, "LocatorExitCode": "Success", "ErrorEllipse": {"E0Error": 40.3344, "E0Azimuth": -121.44, "E0Dip": 32.44, "E1Error": 12.5, "E1Azimuth": 22.64, "E1Dip": 2.44, "E2Error": 12.5, "E2Azimuth": 22.64, "E2Dip": 2.44, "MaximumHorizontalProjection": 1.984, "MaximumVerticalProjection": 1.984, "EquivalentHorizontalRadius": 1.984}}'
    DICT = {"ID": "12345678", "Hypocenter": {"Latitude": 40.3344, "Longitude": -121.44, "Time": "2019-05-17T15:53:00.000Z", "Depth": 32.44, "LatitudeError": 12.5, "LongitudeError": 22.64, "DepthError": 2.44, "TimeError": 1.984}, "SupportingData": [{"ID": "12GFH48776857", "Site": {"Station": "BOZ", "Channel": "BHZ", "Network": "US", "Location": "00", "Latitude": 45.59697, "Longitude": -111.62967, "Elevation": 1589.0}, "Source": {"Author": "TestAuthor", "AgencyID": "US", "Type": "Unknown"}, "Time": "2015-12-28T21:32:24.017Z", "Affinity": 1.2, "Quality": 0.45, "Use": True, "PickedPhase": "P", "AssociatedPhase": "P", "LocatedPhase": "P", "Residual": 1.05, "Distance": 2.65, "Azimuth": 21.5, "Weight": 2.65, "Importance": 3.8}], "NumAssociatedStations": 11, "NumAssociatedPhases": "22", "NumUsedStations": 33, "NumUsedPhases": 44, "Gap": 33.67, "SecondaryGap": 33.67, "MinimumDistance": 2.14, "RMS": 3.8, "Quality": "A", "BayesianDepth": 66.7, "BayesianRange": 20.3, "DepthImportance": 1.8, "LocatorExitCode": "Success", "ErrorEllipse": {"E0Error": 40.3344, "E0Azimuth": -121.44, "E0Dip": 32.44, "E1Error": 12.5, "E1Azimuth": 22.64, "E1Dip": 2.44, "E2Error": 12.5, "E2Azimuth": 22.64, "E2Dip": 2.44, "MaximumHorizontalProjection": 1.984, "MaximumVerticalProjection": 1.984, "EquivalentHorizontalRadius": 1.984}}
    
    def test_init(self):
        
        locationResult = processingformats.locationResult.LocationResult()
        
        self.assertFalse(hasattr(locationResult, 'id'))
        self.assertFalse(hasattr(locationResult.hypocenter, 'latitude'))
        self.assertFalse(hasattr(locationResult.hypocenter, 'longitude'))
        self.assertFalse(hasattr(locationResult.hypocenter, 'depth'))
        self.assertFalse(hasattr(locationResult.hypocenter, 'time'))
        self.assertFalse(hasattr(locationResult.hypocenter, 'latitudeError'))
        self.assertFalse(hasattr(locationResult.hypocenter, 'longitudeError'))
        self.assertFalse(hasattr(locationResult.hypocenter, 'depthError'))
        self.assertFalse(hasattr(locationResult.hypocenter, 'timeError'))
        self.assertFalse(hasattr(locationResult, 'supportingData'))
        self.assertFalse(hasattr(locationResult, 'associatedStations'))
        self.assertFalse(hasattr(locationResult, 'associatedPhases'))
        self.assertFalse(hasattr(locationResult, 'usedStations'))
        self.assertFalse(hasattr(locationResult, 'usedPhases'))
        self.assertFalse(hasattr(locationResult, 'gap'))
        self.assertFalse(hasattr(locationResult, 'secondary gap'))
        self.assertFalse(hasattr(locationResult, 'minimumDistance'))
        self.assertFalse(hasattr(locationResult, 'rms'))
        self.assertFalse(hasattr(locationResult, 'quality'))
        self.assertFalse(hasattr(locationResult, 'bayesianDepth'))
        self.assertFalse(hasattr(locationResult, 'bayesianRange'))
        self.assertFalse(hasattr(locationResult, 'depthImportance'))
        self.assertFalse(hasattr(locationResult, 'locatorExitCode'))
        self.assertFalse(hasattr(locationResult.errorEllipse, 'E0Error'))
        self.assertFalse(hasattr(locationResult.errorEllipse, 'E0Azimuth'))
        self.assertFalse(hasattr(locationResult.errorEllipse, 'E0Dip'))
        self.assertFalse(hasattr(locationResult.errorEllipse, 'E1Error'))
        self.assertFalse(hasattr(locationResult.errorEllipse, 'E1Azimuth'))
        self.assertFalse(hasattr(locationResult.errorEllipse, 'E1Dip'))
        self.assertFalse(hasattr(locationResult.errorEllipse, 'E2Error'))
        self.assertFalse(hasattr(locationResult.errorEllipse, 'E2Azimuth'))
        self.assertFalse(hasattr(locationResult.errorEllipse, 'E2Dip'))
        self.assertFalse(hasattr(locationResult.errorEllipse, 'maximumHorizontalProjection'))
        self.assertFalse(hasattr(locationResult.errorEllipse, 'maximumVerticalProjection'))
        self.assertFalse(hasattr(locationResult.errorEllipse, 'equivalentHorizontalRadius'))
        
        locationResult = processingformats.locationResult.LocationResult(self.ID, self.HYPOCENTER, self.SUPPORTINGDATA, self.ASSOCIATEDSATAIONS, self.ASSOCIATEDPHASES, self.USEDSTATIONS, self.USEDPHASES, self.GAP, self.SECONDARYGAP, self.MINIMUMDISTANCE, self.RMS, self.QUALITY, self.BAYESIANDEPTH, self.BAYESIANRANGE, self.DEPTHIMPORTANCE, self.LOCATOREXITCODE, self.ERRORELLIPSE)
        
        self.assertTrue(hasattr(locationResult, 'id'))
        self.assertTrue(hasattr(locationResult.hypocenter, 'latitude'))
        self.assertTrue(hasattr(locationResult.hypocenter, 'longitude'))
        self.assertTrue(hasattr(locationResult.hypocenter, 'depth'))
        self.assertTrue(hasattr(locationResult.hypocenter, 'time'))
        self.assertTrue(hasattr(locationResult.hypocenter, 'latitudeError'))
        self.assertTrue(hasattr(locationResult.hypocenter, 'longitudeError'))
        self.assertTrue(hasattr(locationResult.hypocenter, 'depthError'))
        self.assertTrue(hasattr(locationResult.hypocenter, 'timeError'))
        self.assertTrue(hasattr(locationResult, 'supportingData'))
        self.assertTrue(hasattr(locationResult, 'associatedStations'))
        self.assertTrue(hasattr(locationResult, 'associatedPhases'))
        self.assertTrue(hasattr(locationResult, 'usedStations'))
        self.assertTrue(hasattr(locationResult, 'usedPhases'))
        self.assertTrue(hasattr(locationResult, 'gap'))
        self.assertTrue(hasattr(locationResult, 'secondary gap'))
        self.assertTrue(hasattr(locationResult, 'minimumDistance'))
        self.assertTrue(hasattr(locationResult, 'rms'))
        self.assertTrue(hasattr(locationResult, 'quality'))
        self.assertTrue(hasattr(locationResult, 'bayesianDepth'))
        self.assertTrue(hasattr(locationResult, 'bayesianRange'))
        self.assertTrue(hasattr(locationResult, 'depthImportance'))
        self.assertTrue(hasattr(locationResult, 'locatorExitCode'))
        self.assertTrue(hasattr(locationResult.errorEllipse, 'E0Error'))
        self.assertTrue(hasattr(locationResult.errorEllipse, 'E0Azimuth'))
        self.assertTrue(hasattr(locationResult.errorEllipse, 'E0Dip'))
        self.assertTrue(hasattr(locationResult.errorEllipse, 'E1Error'))
        self.assertTrue(hasattr(locationResult.errorEllipse, 'E1Azimuth'))
        self.assertTrue(hasattr(locationResult.errorEllipse, 'E1Dip'))
        self.assertTrue(hasattr(locationResult.errorEllipse, 'E2Error'))
        self.assertTrue(hasattr(locationResult.errorEllipse, 'E2Azimuth'))
        self.assertTrue(hasattr(locationResult.errorEllipse, 'E2Dip'))
        self.assertTrue(hasattr(locationResult.errorEllipse, 'maximumHorizontalProjection'))
        self.assertTrue(hasattr(locationResult.errorEllipse, 'maximumVerticalProjection'))
        self.assertTrue(hasattr(locationResult.errorEllipse, 'equivalentHorizontalRadius'))
        
        self.assertEqual(locationResult.id, self.ID)
        self.assertEqual(locationResult.hypocenter.latitude, locationResult.HYPOCENTER.latitude)
        self.assertEqual(locationResult.hypocenter.longitude, locationResult.HYPOCENTER.longitude)
        self.assertEqual(locationResult.hypocenter.depth, locationResult.HYPOCENTER.depth)
        self.assertEqual(locationResult.hypocenter.time, locationResult.HYPOCENTER.time)
        self.assertEqual(locationResult.hypocenter.latitudeError, locationResult.HYPOCENTER.latitudeError)
        self.assertEqual(locationResult.hypocenter.longitudeError, locationResult.HYPOCENTER.longitudeError)
        self.assertEqual(locationResult.hypocenter.depthError, locationResult.HYPOCENTER.depthError)
        self.assertEqual(locationResult.hypocenter.timeError, locationResult.HYPOCENTER.timeError)
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
        self.assertEqual(locationResult.errorEllipse.E0Error, locationResult.ERRORELLIPSE.E0Error)
        self.assertEqual(locationResult.errorEllipse.E0Azimuth, locationResult.ERRORELLIPSE.E0Azimuth)
        self.assertEqual(locationResult.errorEllipse.E0Dip, locationResult.ERRORELLIPSE.E0Dip)
        self.assertEqual(locationResult.errorEllipse.E1Error, locationResult.ERRORELLIPSE.E1Error)
        self.assertEqual(locationResult.errorEllipse.E1Azimuth, locationResult.ERRORELLIPSE.E1Azimuth)
        self.assertEqual(locationResult.errorEllipse.E1Dip, locationResult.ERRORELLIPSE.E1Dip)
        self.assertEqual(locationResult.errorEllipse.E2Error, locationResult.ERRORELLIPSE.E2Error)
        self.assertEqual(locationResult.errorEllipse.E2Azimuth, locationResult.ERRORELLIPSE.E2Azimuth)
        self.assertEqual(locationResult.errorEllipse.E2Dip, locationResult.ERRORELLIPSE.E2Dip)
        self.assertEqual(locationResult.errorEllipse.maximumHorizontalProjection, locationResult.ERRORELLIPSE.maximumHorizontalProjection)
        self.assertEqual(locationResult.errorEllipse.maximumVerticalProjection, locationResult.ERRORELLIPSE.maximumVerticalProjection)
        self.assertEqual(locationResult.errorEllipse.equivalentHorizontalRadius, locationResult.ERRORELLIPSE.equivalentHorizontalRadius)
        
        
    def test_toJSON(self):
        locationResult = processingformats.locationResult.LocationResult(self.ID, self.HYPOCENTER, self.SUPPORTINGDATA, self.ASSOCIATEDSATAIONS, self.ASSOCIATEDPHASES, self.USEDSTATIONS, self.USEDPHASES, self.GAP, self.SECONDARYGAP, self.MINIMUMDISTANCE, self.RMS, self.QUALITY, self.BAYESIANDEPTH, self.BAYESIANRANGE, self.DEPTHIMPORTANCE, self.LOCATOREXITCODE, self.ERRORELLIPSE)
        self.assertEqual(locationResult.toJSONString(), self.JSONSTRING)
        
        
    def test_fromJSON(self):
        locationResult = processingformats.locationResult.LocationResult()
        locationResult.fromJSONString(self.JSONSTRING)
        
        self.assertEqual(locationResult.id, self.ID)
        self.assertEqual(locationResult.hypocenter.latitude, locationResult.HYPOCENTER.latitude)
        self.assertEqual(locationResult.hypocenter.longitude, locationResult.HYPOCENTER.longitude)
        self.assertEqual(locationResult.hypocenter.depth, locationResult.HYPOCENTER.depth)
        self.assertEqual(locationResult.hypocenter.time, locationResult.HYPOCENTER.time)
        self.assertEqual(locationResult.hypocenter.latitudeError, locationResult.HYPOCENTER.latitudeError)
        self.assertEqual(locationResult.hypocenter.longitudeError, locationResult.HYPOCENTER.longitudeError)
        self.assertEqual(locationResult.hypocenter.depthError, locationResult.HYPOCENTER.depthError)
        self.assertEqual(locationResult.hypocenter.timeError, locationResult.HYPOCENTER.timeError)
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
        self.assertEqual(locationResult.errorEllipse.E0Error, locationResult.ERRORELLIPSE.E0Error)
        self.assertEqual(locationResult.errorEllipse.E0Azimuth, locationResult.ERRORELLIPSE.E0Azimuth)
        self.assertEqual(locationResult.errorEllipse.E0Dip, locationResult.ERRORELLIPSE.E0Dip)
        self.assertEqual(locationResult.errorEllipse.E1Error, locationResult.ERRORELLIPSE.E1Error)
        self.assertEqual(locationResult.errorEllipse.E1Azimuth, locationResult.ERRORELLIPSE.E1Azimuth)
        self.assertEqual(locationResult.errorEllipse.E1Dip, locationResult.ERRORELLIPSE.E1Dip)
        self.assertEqual(locationResult.errorEllipse.E2Error, locationResult.ERRORELLIPSE.E2Error)
        self.assertEqual(locationResult.errorEllipse.E2Azimuth, locationResult.ERRORELLIPSE.E2Azimuth)
        self.assertEqual(locationResult.errorEllipse.E2Dip, locationResult.ERRORELLIPSE.E2Dip)
        self.assertEqual(locationResult.errorEllipse.maximumHorizontalProjection, locationResult.ERRORELLIPSE.maximumHorizontalProjection)
        self.assertEqual(locationResult.errorEllipse.maximumVerticalProjection, locationResult.ERRORELLIPSE.maximumVerticalProjection)
        self.assertEqual(locationResult.errorEllipse.equivalentHorizontalRadius, locationResult.ERRORELLIPSE.equivalentHorizontalRadius)
        
        
    def test_toDict(self):
        locationResult = processingformats.locationResult.LocationResult(self.ID, self.HYPOCENTER, self.SUPPORTINGDATA, self.ASSOCIATEDSATAIONS, self.ASSOCIATEDPHASES, self.USEDSTATIONS, self.USEDPHASES, self.GAP, self.SECONDARYGAP, self.MINIMUMDISTANCE, self.RMS, self.QUALITY, self.BAYESIANDEPTH, self.BAYESIANRANGE, self.DEPTHIMPORTANCE, self.LOCATOREXITCODE, self.ERRORELLIPSE)
        self.assertEqual(locationResult.toDict(), self.DICT)
        
    
    def test_fromDict(self):
        locationResult = processingformats.locationResult.LocationResult()
        locationResult.fromDict(self.DICT)
        
        self.assertEqual(locationResult.id, self.ID)
        self.assertEqual(locationResult.hypocenter.latitude, locationResult.HYPOCENTER.latitude)
        self.assertEqual(locationResult.hypocenter.longitude, locationResult.HYPOCENTER.longitude)
        self.assertEqual(locationResult.hypocenter.depth, locationResult.HYPOCENTER.depth)
        self.assertEqual(locationResult.hypocenter.time, locationResult.HYPOCENTER.time)
        self.assertEqual(locationResult.hypocenter.latitudeError, locationResult.HYPOCENTER.latitudeError)
        self.assertEqual(locationResult.hypocenter.longitudeError, locationResult.HYPOCENTER.longitudeError)
        self.assertEqual(locationResult.hypocenter.depthError, locationResult.HYPOCENTER.depthError)
        self.assertEqual(locationResult.hypocenter.timeError, locationResult.HYPOCENTER.timeError)
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
        self.assertEqual(locationResult.errorEllipse.E0Error, locationResult.ERRORELLIPSE.E0Error)
        self.assertEqual(locationResult.errorEllipse.E0Azimuth, locationResult.ERRORELLIPSE.E0Azimuth)
        self.assertEqual(locationResult.errorEllipse.E0Dip, locationResult.ERRORELLIPSE.E0Dip)
        self.assertEqual(locationResult.errorEllipse.E1Error, locationResult.ERRORELLIPSE.E1Error)
        self.assertEqual(locationResult.errorEllipse.E1Azimuth, locationResult.ERRORELLIPSE.E1Azimuth)
        self.assertEqual(locationResult.errorEllipse.E1Dip, locationResult.ERRORELLIPSE.E1Dip)
        self.assertEqual(locationResult.errorEllipse.E2Error, locationResult.ERRORELLIPSE.E2Error)
        self.assertEqual(locationResult.errorEllipse.E2Azimuth, locationResult.ERRORELLIPSE.E2Azimuth)
        self.assertEqual(locationResult.errorEllipse.E2Dip, locationResult.ERRORELLIPSE.E2Dip)
        self.assertEqual(locationResult.errorEllipse.maximumHorizontalProjection, locationResult.ERRORELLIPSE.maximumHorizontalProjection)
        self.assertEqual(locationResult.errorEllipse.maximumVerticalProjection, locationResult.ERRORELLIPSE.maximumVerticalProjection)
        self.assertEqual(locationResult.errorEllipse.equivalentHorizontalRadius, locationResult.ERRORELLIPSE.equivalentHorizontalRadius)
        
        
    def test_isValid(self):
        locationResult = processingformats.locationResult.LocationResult(self.ID, self.HYPOCENTER, self.SUPPORTINGDATA, self.ASSOCIATEDSATAIONS, self.ASSOCIATEDPHASES, self.USEDSTATIONS, self.USEDPHASES, self.GAP, self.SECONDARYGAP, self.MINIMUMDISTANCE, self.RMS, self.QUALITY, self.BAYESIANDEPTH, self.BAYESIANRANGE, self.DEPTHIMPORTANCE, self.LOCATOREXITCODE, self.ERRORELLIPSE)
        self.assertTrue(locationResult.isValid())
        
        badLocationResult = processingformats.locationResult.LocationResult()
        self.assertFalse(badLocationResult.isValid())
        
        
if __name__ == '__main__':
    unittest.main()

        
        