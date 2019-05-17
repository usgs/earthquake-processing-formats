#!/usr/bin/env python3
# -*- coding: utf-8 -*-


#package imports
import processingformats.hypocenter

#stdlib imports
import unittest
import datetime

class TestHypocenter(unittest.TestCase):
    LATITUDE = 45
    LONGITUDE = 111.5
    DEPTH = 12.5
    TIME = datetime.datetime(2019, 5, 17, 9, 24, 59, 0)
    LATITUDEERROR = 1.6
    LONGITUDEERROR = 1.5
    DEPTHERROR = 32.5
    TIMEERROR = 11.2
    JSONSTRING = '{"Latitude": 45.6, "Longitude": 111.5, "Depth": 12.5, "Time": "2019-05-17T09:24:59.000Z", "LatitudeError": 1.6, "LongitudeError": 1.5, "DepthError": 32.5, "TimeError": 11.2}'
    DICT = {"Latitude": 45.6, "Longitude": 111.5, "Depth": 12.5, "Time": "2019-05-17T09:24:59.000Z", "LatitudeError": 1.6, "LongitudeError": 1.5, "DepthError": 32.5, "TimeError": 11.2}
    
    def test_init(self):
        
        hypocenter = processingformats.hypocenter.Hypocenter()
        
        self.assertFalse(hasattr(hypocenter, 'latitude'))
        self.assertFalse(hasattr(hypocenter, 'longitude'))
        self.assertFalse(hasattr(hypocenter, 'depth'))
        self.assertFalse(hasattr(hypocenter, 'time'))
        self.assertFalse(hasattr(hypocenter, 'latitudeError'))
        self.assertFalse(hasattr(hypocenter, 'longitudeError'))
        self.assertFalse(hasattr(hypocenter, 'depthError'))
        self.assertFalse(hasattr(hypocenter, 'timeError'))
        
        hypocenter = processingformats.hypocenter.Hypocenter(self.LATITUDE, self.LONGITUDE, self.DEPTH, self.TIME, self.LATITUDEERROR, self.LONGITUDEERROR, self.DEPTHERROR, self.TIMEERROR)
        
        self.assertTrue(hasattr(hypocenter, 'latitude'))
        self.assertTrue(hasattr(hypocenter, 'longitude'))
        self.assertTrue(hasattr(hypocenter, 'depth'))
        self.assertTrue(hasattr(hypocenter, 'time'))
        self.assertTrue(hasattr(hypocenter, 'latitudeError'))
        self.assertTrue(hasattr(hypocenter, 'longitudeError'))
        self.assertTrue(hasattr(hypocenter, 'depthError'))
        self.assertTrue(hasattr(hypocenter, 'timeError'))
        
        self.assertEqual(hasattr(hypocenter, self.LATITUDE))
        self.assertEqual(hasattr(hypocenter, self.LONGITUDE))
        self.assertEqual(hasattr(hypocenter, self.DEPTH))
        self.assertEqual(hasattr(hypocenter, self.TIME))
        self.assertEqual(hasattr(hypocenter, self.LATITUDEERROR))
        self.assertEqual(hasattr(hypocenter, self.LONGITUDEERROR))
        self.assertEqual(hasattr(hypocenter, self.DEPTHERROR))
        self.assertEqual(hasattr(hypocenter, self.TIMEERROR))
        
    
    def test_toJSON(self):
        hypocenter = processingformats.hypocenter.Hypocenter(self.LATITUDE, self.LONGITUDE, self.DEPTH, self.TIME, self.LATITUDEERROR, self.LONGITUDEERROR, self.DEPTHERROR, self.TIMEERROR)
        
        self.assertEqual(hypocenter.toJSONString(), self.JSONSTRING)
        
    def test_fromJSON(self):
        hypocenter = processingformats.hypocenter.Hypocenter()
        hypocenter.fromJSONString(self.JSONSTRING)
        
        self.assertEqual(hasattr(hypocenter.latitude, self.LATITUDE))
        self.assertEqual(hasattr(hypocenter.longitude, self.LONGITUDE))
        self.assertEqual(hasattr(hypocenter.depth, self.DEPTH))
        self.assertEqual(hasattr(hypocenter.time, self.TIME))
        self.assertEqual(hasattr(hypocenter.latitudeError, self.LATITUDEERROR))
        self.assertEqual(hasattr(hypocenter.longitudeError, self.LONGITUDEERROR))
        self.assertEqual(hasattr(hypocenter.depthError, self.DEPTHERROR))
        self.assertEqual(hasattr(hypocenter.timeError, self.TIMEERROR))
        
    def test_isValid(self):
        hypocenter = processingformats.hypocenter.Hypocenter(self.LATITUDE, self.LONGITUDE, self.DEPTH, self.TIME, self.LATITUDEERROR, self.LONGITUDEERROR, self.DEPTHERROR, self.TIMEERROR)
        self.assertTrue(hypocenter.isValid())
        
        badHypocenter = processingformats.hypocenter.Hypocenter()
        self.assertFalse(badHypocenter.isValid())
        
if __name__ == '__main__':
    unittest.main()