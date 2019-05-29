#!/usr/bin/env python3
# -*- coding: utf-8 -*-


#package imports
import processingformats.errorEllipse

#stdlib imports
import unittest

class TestErrorEllipse(unittest.TestCase):
    
    E0ERROR = 40.3344
    E0AZIMUTH = -121.44
    E0DIP = 32.44
    E1ERROR = 12.5
    E1AZIMUTH = 22.64
    E1DIP = 2.44
    E2ERROR = 12.5
    E2AZIMUTH = 22.64
    E2DIP = 2.44
    MAXIMUMHORIZONTALPROJECTION = 1.984
    MAXIMUMVERTICALPROJECTION = 1.984
    EQUIVALENTHORIZONTALRADIUS = 1.984
    
    JSONSTRING = '{"E0Error": 40.3344, "E0Azimuth": -121.44, "E0Dip": 32.44, "E1Error": 12.5, "E1Azimuth": 22.64, "E1Dip": 2.44, "E2Error": 12.5, "E2Azimuth": 22.64, "E2Dip": 2.44, "MaximumHorizontalProjection": 1.984, "MaximumVerticalProjection": 1.984, "EquivalentHorizontalRadius": 1.984}'
    DICT = {'E0Error': 40.3344, 'E0Azimuth': -121.44, 'E0Dip': 32.44, 'E1Error': 12.5, 'E1Azimuth': 22.64, 'E1Dip': 2.44, 'E2Error': 12.5, 'E2Azimuth': 22.64, 'E2Dip': 2.44, 'MaxumumHorizontalProjection': 1.984, 'MaximumVerticalProjection': 1.984, 'EquivalentHorizontalRadius': 1.984}
    
    def test_init(self):
        errorEllipse = processingformats.errorEllipse.ErrorEllipse()
        
        self.assertFalse(hasattr(errorEllipse, 'E0Error'))
        self.assertFalse(hasattr(errorEllipse, 'E0Azimuth'))
        self.assertFalse(hasattr(errorEllipse, 'E0Dip'))
        self.assertFalse(hasattr(errorEllipse, 'E1Error'))
        self.assertFalse(hasattr(errorEllipse, 'E1Azimuth'))
        self.assertFalse(hasattr(errorEllipse, 'E1Dip'))
        self.assertFalse(hasattr(errorEllipse, 'E2Error'))
        self.assertFalse(hasattr(errorEllipse, 'E2Azimuth'))
        self.assertFalse(hasattr(errorEllipse, 'E2Dip'))
        self.assertFalse(hasattr(errorEllipse, 'maximumHorizontalProjection'))
        self.assertFalse(hasattr(errorEllipse, 'maximumVerticalProjection'))
        self.assertFalse(hasattr(errorEllipse, 'equivalentHorizontalRadius'))
        
        errorEllipse = processingformats.errorEllipse.ErrorEllipse(errorEllipse.E0ERROR, errorEllipse.E0AZIMUTH, errorEllipse.E0DIP, errorEllipse.E1ERROR, errorEllipse.E1AZIMUTH, errorEllipse.E1DIP, errorEllipse.E2ERROR, errorEllipse.E2AZIMUTH, errorEllipse.E2DIP, errorEllipse.MAXIMUMHORIZONTALPROJECTION, errorEllipse.MAXIMUMVERTICALPROJECTION, errorEllipse.EQUIVALENTHORIZONTALRADIUS)
        
        self.assertTrue(hasattr(errorEllipse, 'E0Error'))
        self.assertTrue(hasattr(errorEllipse, 'E0Azimuth'))
        self.assertTrue(hasattr(errorEllipse, 'E0Dip'))
        self.assertTrue(hasattr(errorEllipse, 'E1Error'))
        self.assertTrue(hasattr(errorEllipse, 'E1Azimuth'))
        self.assertTrue(hasattr(errorEllipse, 'E1Dip'))
        self.assertTrue(hasattr(errorEllipse, 'E2Error'))
        self.assertTrue(hasattr(errorEllipse, 'E2Azimuth'))
        self.assertTrue(hasattr(errorEllipse, 'E2Dip'))
        self.assertTrue(hasattr(errorEllipse, 'maximumHorizontalProjection'))
        self.assertTrue(hasattr(errorEllipse, 'maximumVerticalProjection'))
        self.assertTrue(hasattr(errorEllipse, 'equivalentHorizontalRadius'))
        
        self.assertEqual(errorEllipse.E0Error, self.E0ERROR)
        self.assertEqual(errorEllipse.E0Azimuth, self.E0AZIMUTH)
        self.assertEqual(errorEllipse.E0Dip, self.E0DIP)
        self.assertEqual(errorEllipse.E1Error, self.E1ERROR)
        self.assertEqual(errorEllipse.E1Azimuth, self.E1AZIMUTH)
        self.assertEqual(errorEllipse.E1Dip, self.E1DIP)
        self.assertEqual(errorEllipse.E2Error, self.E2ERROR)
        self.assertEqual(errorEllipse.E2Azimuth, self.E2AZIMUTH)
        self.assertEqual(errorEllipse.E2Dip, self.E2DIP)
        self.assertEqual(errorEllipse.maxmimumHorizontalProjection, self.MAXIMUMHORIZONTALPROJECTION)
        self.assertEqual(errorEllipse.maximumVerticalProjection, self.MAXIMUMVERTICALPROJECTION)
        self.assertEqual(errorEllipse.equivalentHorizontalRadius, self.EQUIVALENTHORIZONTALRADIUS)
        
        
    def test_toJSON(self):
        errorEllipse = processingformats.errorEllipse.ErrorEllipse(self.E0ERROR, self.E0AZIMUTH, self.E0DIP, self.E1ERROR, self.E1AZIMUTH, self.E1DIP, self.E2ERROR, self.E2AZIMUTH, self.E2DIP, self.MAXIMUMHORIZONTALPROJECTION, self.MAXIMUMVERTICALPROJECTION, self.EQUIVALENTHORIZONTALRADIUS)
        self.assertEqual(errorEllipse.toJSONString(), self.JOSNSTRING)
        
    
    def test_fromJSON(self):
        errorEllipse = processingformats.errorEllipse.ErrorEllipse()
        errorEllipse.fromJSONString(self.JSONSTRING)
        
        self.assertEqual(errorEllipse.E0Error, self.E0ERROR)
        self.assertEqual(errorEllipse.E0Azimuth, self.E0AZIMUTH)
        self.assertEqual(errorEllipse.E0Dip, self.E0DIP)
        self.assertEqual(errorEllipse.E1Error, self.E1ERROR)
        self.assertEqual(errorEllipse.E1Azimuth, self.E1AZIMUTH)
        self.assertEqual(errorEllipse.E1Dip, self.E1DIP)
        self.assertEqual(errorEllipse.E2Error, self.E2ERROR)
        self.assertEqual(errorEllipse.E2Azimuth, self.E2AZIMUTH)
        self.assertEqual(errorEllipse.E2Dip, self.E2DIP)
        self.assertEqual(errorEllipse.maxmimumHorizontalProjection, self.MAXIMUMHORIZONTALPROJECTION)
        self.assertEqual(errorEllipse.maximumVerticalProjection, self.MAXIMUMVERTICALPROJECTION)
        self.assertEqual(errorEllipse.equivalentHorizontalRadius, self.EQUIVALENTHORIZONTALRADIUS)
        
    def test_toDict(self):
        errorEllipse = processingformats.errorEllipse.ErrorEllipse(self.E0ERROR, self.E0AZIMUTH, self.E0DIP, self.E1ERROR, self.E1AZIMUTH, self.E1DIP, self.E2ERROR, self.E2AZIMUTH, self.E2DIP, self.MAXIMUMHORIZONTALPROJECTION, self.MAXIMUMVERTICALPROJECTION, self.EQUIVALENTHORIZONTALRADIUS)
        self.assertEqual(errorEllipse.toDict(), self.DICT)
        
        
    def test_fromDict(self):
        errorEllipse = processingformats.errorEllipse.ErrorEllipse()
        errorEllipse.fromDict(self.DICT)
        
        self.assertEqual(errorEllipse.E0Error, self.E0ERROR)
        self.assertEqual(errorEllipse.E0Azimuth, self.E0AZIMUTH)
        self.assertEqual(errorEllipse.E0Dip, self.E0DIP)
        self.assertEqual(errorEllipse.E1Error, self.E1ERROR)
        self.assertEqual(errorEllipse.E1Azimuth, self.E1AZIMUTH)
        self.assertEqual(errorEllipse.E1Dip, self.E1DIP)
        self.assertEqual(errorEllipse.E2Error, self.E2ERROR)
        self.assertEqual(errorEllipse.E2Azimuth, self.E2AZIMUTH)
        self.assertEqual(errorEllipse.E2Dip, self.E2DIP)
        self.assertEqual(errorEllipse.maxmimumHorizontalProjection, self.MAXIMUMHORIZONTALPROJECTION)
        self.assertEqual(errorEllipse.maximumVerticalProjection, self.MAXIMUMVERTICALPROJECTION)
        self.assertEqual(errorEllipse.equivalentHorizontalRadius, self.EQUIVALENTHORIZONTALRADIUS)
        
        
    def test_isValid(self):
        errorEllipse = processingformats.errorEllipse.ErrorEllipse(self.E0ERROR, self.E0AZIMUTH, self.E0DIP, self.E1ERROR, self.E1AZIMUTH, self.E1DIP, self.E2ERROR, self.E2AZIMUTH, self.E2DIP, self.MAXIMUMHORIZONTALPROJECTION, self.MAXIMUMVERTICALPROJECTION, self.EQUIVALENTHORIZONTALRADIUS)
        self.assertTrue(errorEllipse.isValid())

        badErrorEllipse = processingformats.errorEllipse.ErrorEllipse()
        self.assertFalse(badErrorEllipse.isValid())


if __name__ == '__main__':
    unittest.main()        
