#!/usr/bin/env python3
# -*- coding: utf-8 -*-


# package imports
import processingformats.pick
import processingformats.locationRequest

# stdlib imports
import datetime
import unittest

class TestLocationRequest(unittest.TestCase):
    
    ID = '12345678'
    SOURCE = processingformats.source.Source('US', 'TestAuthor', 'Unknown')
    TYPE = 'RayLoc'
    EARTHMODEL = 'ak135'
    SLABRESOLUTION = '2spd'
    SOURCELATITUDE = 40.3344
    SOURCELONGITUDE = -121.44
    SOURCEORIGINTIME = datetime.datetime(2019, 5, 21, 13, 3, 59, 0)
    SOURCEDEPTH = 32.44
    PICKJSON = '{"ID": "12GFH48776857", "Site": {"Station": "BOZ", "Channel": "BHZ", "Network": "US", "Location": "00", "Latitude": 45.59697, "Longitude": -111.62967, "Elevation": 1589.0}, "Source": {"Author": "TestAuthor", "AgencyID": "US", "Type": "Unknown"}, "Time": "2015-12-28T21:32:24.017Z", "Affinity": 1.2, "Quality": 0.45, "Use": true, "PickedPhase": "P", "AssociatedPhase": "P", "LocatedPhase": "P", "Residual": 1.05, "Distance": 2.65, "Azimuth": 21.5, "Weight": 2.65, "Importance": 3.8}'
    PICK =  processingformats.pick.Pick()
    PICK.fromJSONString(PICKJSON)
    INPUTDATA = [ PICK ]
    ISLOCATIONNEW = False
    ISLOCATIONHELD = False
    ISDEPTHHELD = False
    ISBAYESIANDEPTH = True
    BAYESIANDEPTH = 66.7
    BAYESIANSPREAD = 20.3
    USESVD = True
    REASSESSINITIALPHASEIDS = True

    OUTPUTDATA = '{"MinimumDistance": 2.14, "NumberOfUsedStations": 33, "BayesianRange": 20.3, "ErrorEllipse": {"MaximumVerticalProjection": 1.984, "EquivalentHorizontalRadius": 1.984, "MaximumHorizontalProjection": 1.984, "E0": {"Azimuth": -121.44, "Error": 40.3344, "Dip": 32.44}, "E1": {"Azimuth": 22.64, "Error": 12.5, "Dip": 2.44}, "E2": {"Azimuth": 22.64, "Error": 12.5, "Dip": 2.44}, "SupportingData": [{"Site": {"Station": "BMN", "Network": "LB", "Channel": "HHZ", "Location": "01"}, "PickedPhase": "P", "Use": true, "AssociatedPhase": "P", "Time": "2019-05-21T13:03:59.000Z", "Residual": 1.05, "Source": {"Type": "Unknown", "AgencyID": "US", "Author": "TestAuthor"}, "Weight": 2.65, "Importance": 3.8, "Azimuth": 21.5, "Quality": 0.45, "Affinity": 1.2, "ID": "12GFH48776857", "LocatedPhase": "P", "Distance": 2.65}], "Hypocenter": {"LatitudeError": 12.5, "DepthError": 2.44, "TimeError": 1.984, "Latitude": 40.3344, "Time": "2019-05-21T13:03:59.000Z", "Longitude": -121.44, "Depth": 32.44, "LongitudeError": 22.64}, "DepthImportance": 1.8, "Quality": "A", "Gap": 33.67, "BayesianDepth": 66.7, "SecondaryGap": 33.67, "RMS": 3.8, "NumberOfAssociatedStations": 11, "NumberOfAssociatedPhases": 22, "NumberOfUsedPhases": 44}'
    
    JSONSTRING = '{"Type": "RayLoc", "EarthModel": "ak135", "SlabResolution": "2spd", "SourceLatitude": 40.3344, "SourceLongitude": -121.44, "SourceDepth": 32.44, "SourceOriginTime": "2019-05-21T13:03:59.000Z", "InputData": [{"ID": "12GFH48776857", "Site": {"Station": "BOZ", "Network": "US", "Latitude": 45.59697, "Longitude": -111.62967, "Elevation": 1589.0, "Channel": "BHZ", "Location": "00"}, "Source": {"AgencyID": "US", "Author": "TestAuthor", "Type": "Unknown"}, "Time": "2015-12-28T21:32:24.017Z", "Affinity": 1.2, "Quality": 0.45, "Use": true, "PickedPhase": "P", "AssociatedPhase": "P", "LocatedPhase": "P", "Residual": 1.05, "Distance": 2.65, "Azimuth": 21.5, "Weight": 2.65, "Importance": 3.8}], "ID": "12345678", "Source": {"AgencyID": "US", "Author": "TestAuthor", "Type": "Unknown"}, "IsLocationNew": false, "IsLocationHeld": false, "IsDepthHeld": false, "IsBayesianDepth": true, "BayesianDepth": 66.7, "BayesianSpread": 20.3, "UseSVD": true, "ReassessInitialPhaseIDs": true}'
    DICT = {'ID': '12345678', 'Source': {'AgencyID': 'US', 'Author': 'TestAuthor', 'Type': 'Unknown'}, 'Type': 'RayLoc', 'EarthModel': 'ak135', 'SlabResolution': '2spd', 'SourceLatitude': 40.3344, 'SourceLongitude': -121.44, 'SourceOriginTime': '2019-05-21T13:03:59.000Z', 'SourceDepth': 32.44, 'InputData': [{"ID": "12GFH48776857", "Site": {"Station": "BOZ", "Channel": "BHZ", "Network": "US", "Location": "00", "Latitude": 45.59697, "Longitude": -111.62967, "Elevation": 1589.0}, "Source": {"Author": "TestAuthor", "AgencyID": "US", "Type": "Unknown"}, "Time": "2015-12-28T21:32:24.017Z", "Affinity": 1.2, "Quality": 0.45, "Use": True, "PickedPhase": "P", "AssociatedPhase": "P", "LocatedPhase": "P", "Residual": 1.05, "Distance": 2.65, "Azimuth": 21.5, "Weight": 2.65, "Importance": 3.8}], 'IsLocationNew': False, 'IsLocationHeld': False, 'IsDepthHeld': False, 'IsBayesianDepth': True, 'BayesianDepth': 66.7, 'BayesianSpread': 20.3, 'UseSVD': True, 'ReassessInitialPhaseIDs': True}
    
        
    def test_init(self):
        locationRequest = processingformats.locationRequest.LocationRequest()
        
        self.assertFalse(hasattr(locationRequest, 'id'))
        self.assertFalse(hasattr(locationRequest.source, 'agencyID'))
        self.assertFalse(hasattr(locationRequest.source, 'author'))
        self.assertFalse(hasattr(locationRequest.source, 'type'))
        self.assertFalse(hasattr(locationRequest, 'type'))
        self.assertFalse(hasattr(locationRequest, 'earthModel'))
        self.assertFalse(hasattr(locationRequest, 'slabResolution'))
        self.assertFalse(hasattr(locationRequest, 'sourceLatitude'))
        self.assertFalse(hasattr(locationRequest, 'sourceLongitude'))
        self.assertFalse(hasattr(locationRequest, 'sourceOriginTime'))
        self.assertFalse(hasattr(locationRequest, 'sourceDepth'))
        self.assertFalse(hasattr(locationRequest, 'inputData'))
        self.assertFalse(hasattr(locationRequest, 'isLocationNew'))
        self.assertFalse(hasattr(locationRequest, 'isLocationHeld'))
        self.assertFalse(hasattr(locationRequest, 'isDepthHeld'))
        self.assertFalse(hasattr(locationRequest, 'isBayesianDepth'))
        self.assertFalse(hasattr(locationRequest, 'bayesianDepth'))
        self.assertFalse(hasattr(locationRequest, 'bayesianSpread'))
        self.assertFalse(hasattr(locationRequest, 'useSVD'))
        self.assertFalse(hasattr(locationRequest, 'reassessInitialPhaseIDs'))
        
        locationRequest = processingformats.locationRequest.LocationRequest(self.ID, self.SOURCE, self.TYPE, self.EARTHMODEL, self.SOURCELATITUDE, self.SOURCELONGITUDE, self.SOURCEORIGINTIME, self.SOURCEDEPTH, self.INPUTDATA, self.ISLOCATIONNEW, self.ISLOCATIONHELD, self.ISDEPTHHELD, self.ISBAYESIANDEPTH, self.BAYESIANDEPTH, self.BAYESIANSPREAD, self.USESVD, self.REASSESSINITIALPHASEIDS)
        
        self.assertTrue(hasattr(locationRequest, 'id'))
        self.assertTrue(hasattr(locationRequest.source, 'agencyID'))
        self.assertTrue(hasattr(locationRequest.source, 'author'))
        self.assertTrue(hasattr(locationRequest.source, 'type'))
        self.assertTrue(hasattr(locationRequest, 'type'))
        self.assertTrue(hasattr(locationRequest, 'earthModel'))
        self.assertTrue(hasattr(locationRequest, 'slabResolution'))
        self.assertTrue(hasattr(locationRequest, 'sourceLatitude'))
        self.assertTrue(hasattr(locationRequest, 'sourceLongitude'))
        self.assertTrue(hasattr(locationRequest, 'sourceOriginTime'))
        self.assertTrue(hasattr(locationRequest, 'sourceDepth'))
        self.assertTrue(hasattr(locationRequest, 'inputData'))
        self.assertTrue(hasattr(locationRequest, 'isLocationNew'))
        self.assertTrue(hasattr(locationRequest, 'isLocationHeld'))
        self.assertTrue(hasattr(locationRequest, 'isDepthHeld'))
        self.assertTrue(hasattr(locationRequest, 'isBayesianDepth'))
        self.assertTrue(hasattr(locationRequest, 'bayesianDepth'))
        self.assertTrue(hasattr(locationRequest, 'bayesianSpread'))
        self.assertTrue(hasattr(locationRequest, 'useSVD'))
        self.assertTrue(hasattr(locationRequest, 'reassessInitialPhaseIDs'))
        
        self.assertEqual(locationRequest.id, self.ID)
        self.assertEqual(locationRequest.source.agencyID, self.SOURCE.agencyID)
        self.assertEqual(locationRequest.source.author, self.SOURCE.author)
        self.assertEqual(locationRequest.source.type, self.SOURCE.type) 
        self.assertEqual(locationRequest.type, self.TYPE)
        self.assertEqual(locationRequest.earthModel, self.EARTHMODEL)
        self.assertEqual(locationRequest.slabResolution, self.SLABRESOLUTION)
        self.assertEqual(locationRequest.sourceLatitude, self.SOURCELATITUDE)
        self.assertEqual(locationRequest.sourceLongitude, self.SOURCELONGITUDE)
        self.assertEqual(locationRequest.sourceOriginTime, self.SOURCEORIGINTIME)
        self.assertEqual(locationRequest.sourceDepth, self.SOURCEDEPTH)
        self.assertEqual(locationRequest.inputData, self.INPUTDATA)
        self.assertEqual(locationRequest.isLocationNew, self.ISLOCATIONNEW)
        self.assertEqual(locationRequest.isLocationHeld, self.ISLOCATIONHELD)
        self.assertEqual(locationRequest.isDepthHeld, self.ISDEPTHHELD)
        self.assertEqual(locationRequest.isBayesianDepth, self.ISBAYESIANDEPTH)
        self.assertEqual(locationRequest.bayesianDepth, self.BAYESIANDEPTH)
        self.assertEqual(locationRequest.bayesianSpread, self.BAYESIANSPREAD)
        self.assertEqual(locationRequest.useSVD, self.USESVD)
        self.assertEqual(locationRequest.reassessInitialPhaseIDs, self.REASSESSINITIALPHASEIDS)
        
        
    def test_toJSON(self):
        locationRequest = processingformats.locationRequest.LocationRequest(self.ID, self.SOURCE, self.TYPE, self.EARTHMODEL, self.SOURCELATITUDE, self.SOURCELONGITUDE, self.SOURCEORIGINTIME, self.SOURCEDEPTH, self.INPUTDATA, self.ISLOCATIONNEW, self.ISLOCATIONHELD, self.ISDEPTHHELD, self.ISBAYESIANDEPTH, self.BAYESIANDEPTH, self.BAYESIANSPREAD, self.USESVD, self.REASSESSINITIALPHASEIDS)
        self.maxDiff = None
        self.assertEqual(locationRequest.toJSONString(), self.JSONSTRING)
        
    
    def test_fromJSON(self):
        locationRequest = processingformats.locationRequest.LocationRequest()
        locationRequest.fromJSONString(self.JSONSTRING)
        
        self.assertEqual(locationRequest.id, self.ID)
        self.assertEqual(locationRequest.source.agencyID, self.SOURCE.agencyID)
        self.assertEqual(locationRequest.source.author, self.SOURCE.author)
        self.assertEqual(locationRequest.source.type, self.SOURCE.type) 
        self.assertEqual(locationRequest.type, self.TYPE)
        self.assertEqual(locationRequest.earthModel, self.EARTHMODEL)
        self.assertEqual(locationRequest.slabResolution, self.SLABRESOLUTION)
        self.assertEqual(locationRequest.sourceLatitude, self.SOURCELATITUDE)
        self.assertEqual(locationRequest.sourceLongitude, self.SOURCELONGITUDE)
        self.assertEqual(locationRequest.sourceOriginTime, self.SOURCEORIGINTIME)
        self.assertEqual(locationRequest.sourceDepth, self.SOURCEDEPTH)

        self.assertEqual(locationRequest.isLocationNew, self.ISLOCATIONNEW)
        self.assertEqual(locationRequest.isLocationHeld, self.ISLOCATIONHELD)
        self.assertEqual(locationRequest.isDepthHeld, self.ISDEPTHHELD)
        self.assertEqual(locationRequest.isBayesianDepth, self.ISBAYESIANDEPTH)
        self.assertEqual(locationRequest.bayesianDepth, self.BAYESIANDEPTH)
        self.assertEqual(locationRequest.bayesianSpread, self.BAYESIANSPREAD)
        self.assertEqual(locationRequest.useSVD, self.USESVD)
        self.assertEqual(locationRequest.reassessInitialPhaseIDs, self.REASSESSINITIALPHASEIDS)
    
    
    def test_toDict(self):
        locationRequest = processingformats.locationRequest.LocationRequest(self.ID, self.SOURCE, self.TYPE, self.EARTHMODEL, self.SOURCELATITUDE, self.SOURCELONGITUDE, self.SOURCEORIGINTIME, self.SOURCEDEPTH, self.INPUTDATA, self.ISLOCATIONNEW, self.ISLOCATIONHELD, self.ISDEPTHHELD, self.ISBAYESIANDEPTH, self.BAYESIANDEPTH, self.BAYESIANSPREAD, self.USESVD, self.REASSESSINITIALPHASEIDS)
        self.assertEqual(locationRequest.toDict(), self.DICT)
        
        
    def test_fromDict(self):
        locationRequest = processingformats.locationRequest.LocationRequest()
        locationRequest.fromDict(self.DICT)
        
        self.assertEqual(locationRequest.id, self.ID)
        self.assertEqual(locationRequest.source.agencyID, self.SOURCE.agencyID)
        self.assertEqual(locationRequest.source.author, self.SOURCE.author)
        self.assertEqual(locationRequest.source.type, self.SOURCE.type) 
        self.assertEqual(locationRequest.type, self.TYPE)
        self.assertEqual(locationRequest.earthModel, self.EARTHMODEL)
        self.assertEqual(locationRequest.slabResolution, self.SLABRESOLUTION)
        self.assertEqual(locationRequest.sourceLatitude, self.SOURCELATITUDE)
        self.assertEqual(locationRequest.sourceLongitude, self.SOURCELONGITUDE)
        self.assertEqual(locationRequest.sourceOriginTime, self.SOURCEORIGINTIME)
        self.assertEqual(locationRequest.sourceDepth, self.SOURCEDEPTH)

        self.assertEqual(locationRequest.isLocationNew, self.ISLOCATIONNEW)
        self.assertEqual(locationRequest.isLocationHeld, self.ISLOCATIONHELD)
        self.assertEqual(locationRequest.isDepthHeld, self.ISDEPTHHELD)
        self.assertEqual(locationRequest.isBayesianDepth, self.ISBAYESIANDEPTH)
        self.assertEqual(locationRequest.bayesianDepth, self.BAYESIANDEPTH)
        self.assertEqual(locationRequest.bayesianSpread, self.BAYESIANSPREAD)
        self.assertEqual(locationRequest.useSVD, self.USESVD)
        self.assertEqual(locationRequest.reassessInitialPhaseIDs, self.REASSESSINITIALPHASEIDS)
        
        
    def test_isValid(self):
        locationRequest = processingformats.locationRequest.LocationRequest(self.ID, self.SOURCE, self.TYPE, self.EARTHMODEL, self.SOURCELATITUDE, self.SOURCELONGITUDE, self.SOURCEORIGINTIME, self.SOURCEDEPTH, self.INPUTDATA, self.ISLOCATIONNEW, self.ISLOCATIONHELD, self.ISDEPTHHELD, self.ISBAYESIANDEPTH, self.BAYESIANDEPTH, self.BAYESIANSPREAD, self.USESVD, self.REASSESSINITIALPHASEIDS)
        self.assertTrue(locationRequest.isValid())
        
        badLocationRequest = processingformats.locationRequest.LocationRequest()
        self.assertFalse(badLocationRequest.isValid())

if __name__ == '__main__':
    unittest.main()        