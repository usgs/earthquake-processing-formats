#!/usr/bin/env python3
# -*- coding: utf-8 -*-


# package import
import processingformats.pick
import processingformats.source
import processingformats.site

# stdlib import
import unittest
import datetime

class TestPick(unittest.TestCase):
        
    ID = '12GFH48776857'
    # SITE INCLUDES: station, channel, network, location, latitude, longitude, and elevation
    SITE = processingformats.site.Site('BOZ', 'BHZ', 'US', '00', 45.596970, -111.629670, 1589.000000)
    # SOURCE INCLUDES: agencyID, author, and type 
    SOURCE = processingformats.source.Source('US', 'TestAuthor', 'Unknown')
    TIME = datetime.datetime(2019, 5, 17, 13, 6, 59, 0)
    AFFINITY = 1.2
    QUALITY = 0.45
    USE = True
    PICKEDPHASE = 'P'
    ASSOCIATEDPHASE = 'P'
    LOCATEDPHASE = 'P'
    RESIDUAL = 1.05
    DISTANCE = 2.65
    AZIMUTH = 21.5
    WEIGHT = 2.65
    IMPORTANCE = 3.8
    JSONSTRING = '{"ID": "12GFH48776857", "Site": {"Station": "BOZ", "Network": "US", "Latitude": 45.59697, "Longitude": -111.62967, "Elevation": 1589.0, "Channel": "BHZ", "Location": "00"}, "Source": {"AgencyID": "US", "Author": "TestAuthor", "Type": "Unknown"}, "Time": "2019-05-17T13:06:59.000Z", "Affinity": 1.2, "Quality": 0.45, "Use": true, "PickedPhase": "P", "AssociatedPhase": "P", "LocatedPhase": "P", "Residual": 1.05, "Distance": 2.65, "Azimuth": 21.5, "Weight": 2.65, "Importance": 3.8}'
    DICT = {'ID': '12GFH48776857', 'Site': {'Station': 'BOZ', 'Channel': 'BHZ', 'Network': 'US', 'Location': '00', 'Latitude': 45.596970, 'Longitude': -111.629670, 'Elevation': 1589.000000}, 'Source': {'AgencyID': 'US', 'Author': 'TestAuthor', 'Type': 'Unknown'}, 'Time': '2019-05-17T13:06:59.000Z', 'Affinity': 1.2, 'Quality': 0.45, 'Use': True, 'PickedPhase': 'P', 'AssociatedPhase': 'P', 'LocatedPhase': 'P', 'Residual': 1.05, 'Distance': 2.65, 'Azimuth': 21.5, 'Weight': 2.65, 'Importance': 3.8}
    
    
    def test_init(self):
        
        pick = processingformats.pick.Pick()
        
        self.assertFalse(hasattr(pick, 'id'))
        self.assertFalse(hasattr(pick.site, 'station'))
        self.assertFalse(hasattr(pick.site, 'channel'))
        self.assertFalse(hasattr(pick.site, 'network'))
        self.assertFalse(hasattr(pick.site, 'location'))
        self.assertFalse(hasattr(pick.site, 'latitude'))
        self.assertFalse(hasattr(pick.site, 'longitude'))
        self.assertFalse(hasattr(pick.site, 'elevation'))
        self.assertFalse(hasattr(pick.source, 'agencyID'))
        self.assertFalse(hasattr(pick.source, 'author'))
        self.assertFalse(hasattr(pick.source, 'type'))
        self.assertFalse(hasattr(pick, 'time'))
        self.assertFalse(hasattr(pick, 'affinity'))
        self.assertFalse(hasattr(pick, 'quality'))
        self.assertFalse(hasattr(pick, 'use'))
        self.assertFalse(hasattr(pick, 'pickedPhase'))
        self.assertFalse(hasattr(pick, 'associatedPhase'))
        self.assertFalse(hasattr(pick, 'locatedPhase'))
        self.assertFalse(hasattr(pick, 'residual'))
        self.assertFalse(hasattr(pick, 'distance'))
        self.assertFalse(hasattr(pick, 'azimuth'))
        self.assertFalse(hasattr(pick, 'weight'))
        self.assertFalse(hasattr(pick, 'importance'))
        
        pick = processingformats.pick.Pick(self.ID, self.SITE, self.SOURCE, self.TIME, self.AFFINITY, self.QUALITY, self.USE, self.PICKEDPHASE, self.ASSOCIATEDPHASE, self.LOCATEDPHASE, self.RESIDUAL, self.DISTANCE, self.AZIMUTH, self.WEIGHT, self.IMPORTANCE)
        
        self.assertTrue(hasattr(pick, 'id'))
        self.assertTrue(hasattr(pick.site, 'station'))
        self.assertTrue(hasattr(pick.site, 'channel'))
        self.assertTrue(hasattr(pick.site, 'network'))
        self.assertTrue(hasattr(pick.site, 'location'))
        self.assertTrue(hasattr(pick.site, 'latitude'))
        self.assertTrue(hasattr(pick.site, 'longitude'))
        self.assertTrue(hasattr(pick.site, 'elevation'))
        self.assertTrue(hasattr(pick.source, 'agencyID'))
        self.assertTrue(hasattr(pick.source, 'author'))
        self.assertTrue(hasattr(pick.source, 'type'))
        self.assertTrue(hasattr(pick, 'time'))
        self.assertTrue(hasattr(pick, 'affinity'))
        self.assertTrue(hasattr(pick, 'quality'))
        self.assertTrue(hasattr(pick, 'use'))
        self.assertTrue(hasattr(pick, 'pickedPhase'))
        self.assertTrue(hasattr(pick, 'associatedPhase'))
        self.assertTrue(hasattr(pick, 'locatedPhase'))
        self.assertTrue(hasattr(pick, 'residual'))
        self.assertTrue(hasattr(pick, 'distance'))
        self.assertTrue(hasattr(pick, 'azimuth'))
        self.assertTrue(hasattr(pick, 'weight'))
        self.assertTrue(hasattr(pick, 'importance'))
        
        self.assertEqual(pick.id, self.ID)
        self.assertEqual(pick.site.station, self.SITE.station)
        self.assertEqual(pick.site.channel, self.SITE.channel)
        self.assertEqual(pick.site.network, self.SITE.network)
        self.assertEqual(pick.site.location, self.SITE.location)
        self.assertEqual(pick.site.latitude, self.SITE.latitude)
        self.assertEqual(pick.site.longitude, self.SITE.longitude)
        self.assertEqual(pick.site.elevation, self.SITE.elevation)
        self.assertEqual(pick.source.agencyID, self.SOURCE.agencyID)
        self.assertEqual(pick.source.author, self.SOURCE.author)
        self.assertEqual(pick.source.type, self.SOURCE.type)
        self.assertEqual(pick.time, self.TIME)
        self.assertEqual(pick.affinity, self.AFFINITY)
        self.assertEqual(pick.quality, self.QUALITY)
        self.assertEqual(pick.use, self.USE)
        self.assertEqual(pick.pickedPhase, self.PICKEDPHASE)
        self.assertEqual(pick.associatedPhase, self.ASSOCIATEDPHASE)
        self.assertEqual(pick.locatedPhase, self.LOCATEDPHASE)
        self.assertEqual(pick.residual, self.RESIDUAL)
        self.assertEqual(pick.distance, self.DISTANCE)
        self.assertEqual(pick.azimuth, self.AZIMUTH)
        self.assertEqual(pick.weight, self.WEIGHT)
        self.assertEqual(pick.importance, self.IMPORTANCE)
        
        
    def test_toJSON(self):
        pick = processingformats.pick.Pick(self.ID, self.SITE, self.SOURCE, self.TIME, self.AFFINITY, self.QUALITY, self.USE, self.PICKEDPHASE, self.ASSOCIATEDPHASE, self.LOCATEDPHASE, self.RESIDUAL, self.DISTANCE, self.AZIMUTH, self.WEIGHT, self.IMPORTANCE)
        self.maxDiff = None
        self.assertEqual(pick.toJSONString(), self.JSONSTRING)
        
    def test_fromJSON(self):
        pick = processingformats.pick.Pick()
        pick.fromJSONString(self.JSONSTRING)
        
        self.assertEqual(pick.id, self.ID)
        self.assertEqual(pick.site.station, self.SITE.station)
        self.assertEqual(pick.site.channel, self.SITE.channel)
        self.assertEqual(pick.site.network, self.SITE.network)
        self.assertEqual(pick.site.location, self.SITE.location)
        self.assertEqual(pick.site.latitude, self.SITE.latitude)
        self.assertEqual(pick.site.longitude, self.SITE.longitude)
        self.assertEqual(pick.site.elevation, self.SITE.elevation)
        self.assertEqual(pick.source.agencyID, self.SOURCE.agencyID)
        self.assertEqual(pick.source.author, self.SOURCE.author)
        self.assertEqual(pick.source.type, self.SOURCE.type)
        self.assertEqual(pick.time, self.TIME)
        self.assertEqual(pick.affinity, self.AFFINITY)
        self.assertEqual(pick.quality, self.QUALITY)
        self.assertEqual(pick.use, self.USE)
        self.assertEqual(pick.pickedPhase, self.PICKEDPHASE)
        self.assertEqual(pick.associatedPhase, self.ASSOCIATEDPHASE)
        self.assertEqual(pick.locatedPhase, self.LOCATEDPHASE)
        self.assertEqual(pick.residual, self.RESIDUAL)
        self.assertEqual(pick.distance, self.DISTANCE)
        self.assertEqual(pick.azimuth, self.AZIMUTH)
        self.assertEqual(pick.weight, self.WEIGHT)
        self.assertEqual(pick.importance, self.IMPORTANCE)
        
        
    def test_toDict(self):
        pick = processingformats.pick.Pick(self.ID, self.SITE, self.SOURCE, self.TIME, self.AFFINITY, self.QUALITY, self.USE, self.PICKEDPHASE, self.ASSOCIATEDPHASE, self.LOCATEDPHASE, self.RESIDUAL, self.DISTANCE, self.AZIMUTH, self.WEIGHT, self.IMPORTANCE)
        self.assertEqual(pick.toDict(), self.DICT)
        
    def test_fromDict(self):
        pick = processingformats.pick.Pick()
        pick.fromDict(self.DICT)
        
        self.assertEqual(pick.id, self.ID)
        self.assertEqual(pick.site.station, self.SITE.station)
        self.assertEqual(pick.site.channel, self.SITE.channel)
        self.assertEqual(pick.site.network, self.SITE.network)
        self.assertEqual(pick.site.location, self.SITE.location)
        self.assertEqual(pick.site.latitude, self.SITE.latitude)
        self.assertEqual(pick.site.longitude, self.SITE.longitude)
        self.assertEqual(pick.site.elevation, self.SITE.elevation)
        self.assertEqual(pick.source.agencyID, self.SOURCE.agencyID)
        self.assertEqual(pick.source.author, self.SOURCE.author)
        self.assertEqual(pick.source.type, self.SOURCE.type)
        self.assertEqual(pick.time, self.TIME)
        self.assertEqual(pick.affinity, self.AFFINITY)
        self.assertEqual(pick.quality, self.QUALITY)
        self.assertEqual(pick.use, self.USE)
        self.assertEqual(pick.pickedPhase, self.PICKEDPHASE)
        self.assertEqual(pick.associatedPhase, self.ASSOCIATEDPHASE)
        self.assertEqual(pick.locatedPhase, self.LOCATEDPHASE)
        self.assertEqual(pick.residual, self.RESIDUAL)
        self.assertEqual(pick.distance, self.DISTANCE)
        self.assertEqual(pick.azimuth, self.AZIMUTH)
        self.assertEqual(pick.weight, self.WEIGHT)
        self.assertEqual(pick.importance, self.IMPORTANCE)
        
    
    def test_isValid(self):
        pick = processingformats.pick.Pick(self.ID, self.SITE, self.SOURCE, self.TIME, self.AFFINITY, self.QUALITY, self.USE, self.PICKEDPHASE, self.ASSOCIATEDPHASE, self.LOCATEDPHASE, self.RESIDUAL, self.DISTANCE, self.AZIMUTH, self.WEIGHT, self.IMPORTANCE)
        self.assertTrue(pick.isValid())
        
        badPick = processingformats.pick.Pick()
        self.assertFalse(badPick.isValid())
        
        
if __name__ == '__main__':
    unittest.main()
        