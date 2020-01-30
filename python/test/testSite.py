#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# package imports
import processingformats.site

# stdlib imports
import unittest

class TestSite(unittest.TestCase):
    
    STATION = 'BOZ'
    CHANNEL = 'BHZ'
    NETWORK = 'US'
    LOCATION = '00'
    LATITUDE = 45.596970
    LONGITUDE = -111.629670
    ELEVATION = 1589.000000
    JSONSTRING = '{"Station": "BOZ", "Network": "US", "Latitude": 45.59697, "Longitude": -111.62967, "Elevation": 1589.0, "Channel": "BHZ", "Location": "00"}'
    DICT = {'Station': 'BOZ', 'Channel': 'BHZ', 'Network': 'US', 'Location': '00', 'Latitude': 45.596970, 'Longitude': -111.629670, 'Elevation': 1589.000000}
    
    def test_init(self):
        site = processingformats.site.Site()
        
        self.assertFalse(hasattr(site, 'station'))
        self.assertFalse(hasattr(site, 'channel'))
        self.assertFalse(hasattr(site, 'network'))
        self.assertFalse(hasattr(site, 'location'))
        self.assertFalse(hasattr(site, 'latitude'))
        self.assertFalse(hasattr(site, 'longitude'))
        self.assertFalse(hasattr(site, 'elevation'))
        
        site = processingformats.site.Site(self.STATION, self.CHANNEL, self.NETWORK, self.LOCATION, self.LATITUDE, self.LONGITUDE, self.ELEVATION)
        
        self.assertTrue(hasattr(site, 'station'))
        self.assertTrue(hasattr(site, 'channel'))
        self.assertTrue(hasattr(site, 'network'))
        self.assertTrue(hasattr(site, 'location'))
        self.assertTrue(hasattr(site, 'latitude'))
        self.assertTrue(hasattr(site, 'longitude'))
        self.assertTrue(hasattr(site, 'elevation'))
        
        self.assertEqual(site.station, self.STATION)
        self.assertEqual(site.channel, self.CHANNEL)
        self.assertEqual(site.network, self.NETWORK)
        self.assertEqual(site.location, self.LOCATION)
        self.assertEqual(site.latitude, self.LATITUDE)
        self.assertEqual(site.longitude, self.LONGITUDE)
        self.assertEqual(site.elevation, self.ELEVATION)
        
    def test_toJSON(self):
        site = processingformats.site.Site(self.STATION, self.CHANNEL, self.NETWORK, self.LOCATION, self.LATITUDE, self.LONGITUDE, self.ELEVATION)
        
        self.assertEqual(site.toJSONString(), self.JSONSTRING)
        
    def test_fromJSON(self):
        site = processingformats.site.Site()
        site.fromJSONString(self.JSONSTRING)
        
        self.assertEqual(site.station, self.STATION)
        self.assertEqual(site.channel, self.CHANNEL)
        self.assertEqual(site.network, self.NETWORK)
        self.assertEqual(site.location, self.LOCATION)
        self.assertEqual(site.latitude, self.LATITUDE)
        self.assertEqual(site.longitude, self.LONGITUDE)
        self.assertEqual(site.elevation, self.ELEVATION)
        
    def test_toDict(self):
        site = processingformats.site.Site(self.STATION, self.CHANNEL, self.NETWORK, self.LOCATION, self.LATITUDE, self.LONGITUDE, self.ELEVATION)
        self.assertEqual(site.toDict(), self.DICT)
        
    def test_fromDict(self):
        site = processingformats.site.Site()
        site.fromDict(self.DICT)
        
        self.assertEqual(site.station, self.STATION)
        self.assertEqual(site.channel, self.CHANNEL)
        self.assertEqual(site.network, self.NETWORK)
        self.assertEqual(site.location, self.LOCATION)
        self.assertEqual(site.latitude, self.LATITUDE)
        self.assertEqual(site.longitude, self.LONGITUDE)
        self.assertEqual(site.elevation, self.ELEVATION)
        
    def test_isValid(self):
        site = processingformats.site.Site(self.STATION, self.CHANNEL, self.NETWORK, self.LOCATION, self.LATITUDE, self.LONGITUDE, self.ELEVATION)
        self.assertTrue(site.isValid())
        
        badSite = processingformats.site.Site()
        self.assertFalse(badSite.isValid())
        
if __name__ == '__main__':
    unittest.main()
