#!/usr/bin/env python3
# -*- coding: utf-8 -*-


# package imports
import processingformats.errorEllipseAxis
import processingformats.errorEllipse

# stdlib imports
import unittest

class TestErrorEllipse(unittest.TestCase):
    
    E0 = processingformats.errorEllipseAxis.ErrorEllipseAxis(40.3344, -121.44, 32.44)
    E1 = processingformats.errorEllipseAxis.ErrorEllipseAxis(12.5, 22.64, 2.44)
    E2 = processingformats.errorEllipseAxis.ErrorEllipseAxis(12.5, 22.64, 2.44)
    MAXIMUMHORIZONTALPROJECTION = 1.984
    MAXIMUMVERTICALPROJECTION = 1.984
    EQUIVALENTHORIZONTALRADIUS = 1.984
    
    JSONSTRING = '{"E0": {"Error": 40.3344, "Azimuth": -121.44, "Dip": 32.44}, "E1": {"Error": 12.5, "Azimuth": 22.64, "Dip": 2.44}, "E2": {"Error": 12.5, "Azimuth": 22.64, "Dip": 2.44}, "MaximumHorizontalProjection": 1.984, "MaximumVerticalProjection": 1.984, "EquivalentHorizontalRadius": 1.984}'
    DICT = {'E0': {'Error': 40.3344, 'Azimuth': -121.44, 'Dip': 32.44}, 'E1': {'Error': 12.5, 'Azimuth': 22.64, 'Dip': 2.44}, 'E2': {'Error': 12.5, 'Azimuth': 22.64, 'Dip': 2.44}, 'MaximumHorizontalProjection': 1.984, 'MaximumVerticalProjection': 1.984, 'EquivalentHorizontalRadius': 1.984}
    
    def test_init(self):
        errorEllipse = processingformats.errorEllipse.ErrorEllipse()
        
        self.assertFalse(hasattr(errorEllipse.e0, 'error'))
        self.assertFalse(hasattr(errorEllipse.e0, 'azimuth'))
        self.assertFalse(hasattr(errorEllipse.e0, 'dip'))
        self.assertFalse(hasattr(errorEllipse.e1, 'error'))
        self.assertFalse(hasattr(errorEllipse.e1, 'azimuth'))
        self.assertFalse(hasattr(errorEllipse.e1, 'dip'))
        self.assertFalse(hasattr(errorEllipse.e2, 'error'))
        self.assertFalse(hasattr(errorEllipse.e2, 'azimuth'))
        self.assertFalse(hasattr(errorEllipse.e2, 'dip'))
        self.assertFalse(hasattr(errorEllipse, 'maximumHorizontalProjection'))
        self.assertFalse(hasattr(errorEllipse, 'maximumVerticalProjection'))
        self.assertFalse(hasattr(errorEllipse, 'equivalentHorizontalRadius'))
        
        errorEllipse = processingformats.errorEllipse.ErrorEllipse(self.E0, self.E1, self.E2, self.MAXIMUMHORIZONTALPROJECTION, self.MAXIMUMVERTICALPROJECTION, self.EQUIVALENTHORIZONTALRADIUS)
        
        self.assertTrue(hasattr(errorEllipse.e0, 'error'))
        self.assertTrue(hasattr(errorEllipse.e0, 'azimuth'))
        self.assertTrue(hasattr(errorEllipse.e0, 'dip'))
        self.assertTrue(hasattr(errorEllipse.e1, 'error'))
        self.assertTrue(hasattr(errorEllipse.e1, 'azimuth'))
        self.assertTrue(hasattr(errorEllipse.e1, 'dip'))
        self.assertTrue(hasattr(errorEllipse.e2, 'error'))
        self.assertTrue(hasattr(errorEllipse.e2, 'azimuth'))
        self.assertTrue(hasattr(errorEllipse.e2, 'dip'))
        self.assertTrue(hasattr(errorEllipse, 'maximumHorizontalProjection'))
        self.assertTrue(hasattr(errorEllipse, 'maximumVerticalProjection'))
        self.assertTrue(hasattr(errorEllipse, 'equivalentHorizontalRadius'))
        
        self.assertEqual(errorEllipse.e0.error, self.E0.error)
        self.assertEqual(errorEllipse.e0.azimuth, self.E0.azimuth)
        self.assertEqual(errorEllipse.e0.dip, self.E0.dip)
        self.assertEqual(errorEllipse.e1.error, self.E1.error)
        self.assertEqual(errorEllipse.e1.azimuth, self.E1.azimuth)
        self.assertEqual(errorEllipse.e1.dip, self.E1.dip)
        self.assertEqual(errorEllipse.e2.error, self.E2.error)
        self.assertEqual(errorEllipse.e2.azimuth, self.E2.azimuth)
        self.assertEqual(errorEllipse.e2.dip, self.E2.dip)
        self.assertEqual(errorEllipse.maximumHorizontalProjection, self.MAXIMUMHORIZONTALPROJECTION)
        self.assertEqual(errorEllipse.maximumVerticalProjection, self.MAXIMUMVERTICALPROJECTION)
        self.assertEqual(errorEllipse.equivalentHorizontalRadius, self.EQUIVALENTHORIZONTALRADIUS)
        
        
    def test_toJSON(self):
        errorEllipse = processingformats.errorEllipse.ErrorEllipse(self.E0, self.E1, self.E2, self.MAXIMUMHORIZONTALPROJECTION, self.MAXIMUMVERTICALPROJECTION, self.EQUIVALENTHORIZONTALRADIUS)
        self.assertEqual(errorEllipse.toJSONString(), self.JSONSTRING)
        
    
    def test_fromJSON(self):
        errorEllipse = processingformats.errorEllipse.ErrorEllipse()
        errorEllipse.fromJSONString(self.JSONSTRING)
        
        self.assertEqual(errorEllipse.e0.error, self.E0.error)
        self.assertEqual(errorEllipse.e0.azimuth, self.E0.azimuth)
        self.assertEqual(errorEllipse.e0.dip, self.E0.dip)
        self.assertEqual(errorEllipse.e1.error, self.E1.error)
        self.assertEqual(errorEllipse.e1.azimuth, self.E1.azimuth)
        self.assertEqual(errorEllipse.e1.dip, self.E1.dip)
        self.assertEqual(errorEllipse.e2.error, self.E2.error)
        self.assertEqual(errorEllipse.e2.azimuth, self.E2.azimuth)
        self.assertEqual(errorEllipse.e2.dip, self.E2.dip)
        self.assertEqual(errorEllipse.maximumHorizontalProjection, self.MAXIMUMHORIZONTALPROJECTION)
        self.assertEqual(errorEllipse.maximumVerticalProjection, self.MAXIMUMVERTICALPROJECTION)
        self.assertEqual(errorEllipse.equivalentHorizontalRadius, self.EQUIVALENTHORIZONTALRADIUS)
        
    def test_toDict(self):
        errorEllipse = processingformats.errorEllipse.ErrorEllipse(self.E0, self.E1, self.E2, self.MAXIMUMHORIZONTALPROJECTION, self.MAXIMUMVERTICALPROJECTION, self.EQUIVALENTHORIZONTALRADIUS)
        self.assertEqual(errorEllipse.toDict(), self.DICT)
        
        
    def test_fromDict(self):
        errorEllipse = processingformats.errorEllipse.ErrorEllipse()
        errorEllipse.fromDict(self.DICT)
        
        self.assertEqual(errorEllipse.e0.error, self.E0.error)
        self.assertEqual(errorEllipse.e0.azimuth, self.E0.azimuth)
        self.assertEqual(errorEllipse.e0.dip, self.E0.dip)
        self.assertEqual(errorEllipse.e1.error, self.E1.error)
        self.assertEqual(errorEllipse.e1.azimuth, self.E1.azimuth)
        self.assertEqual(errorEllipse.e1.dip, self.E1.dip)
        self.assertEqual(errorEllipse.e2.error, self.E2.error)
        self.assertEqual(errorEllipse.e2.azimuth, self.E2.azimuth)
        self.assertEqual(errorEllipse.e2.dip, self.E2.dip)
        self.assertEqual(errorEllipse.maximumHorizontalProjection, self.MAXIMUMHORIZONTALPROJECTION)
        self.assertEqual(errorEllipse.maximumVerticalProjection, self.MAXIMUMVERTICALPROJECTION)
        self.assertEqual(errorEllipse.equivalentHorizontalRadius, self.EQUIVALENTHORIZONTALRADIUS)
        
        
    def test_isValid(self):
        errorEllipse = processingformats.errorEllipse.ErrorEllipse(self.E0, self.E1, self.E2, self.MAXIMUMHORIZONTALPROJECTION, self.MAXIMUMVERTICALPROJECTION, self.EQUIVALENTHORIZONTALRADIUS)
        self.assertTrue(errorEllipse.isValid())

        badErrorEllipse = processingformats.errorEllipse.ErrorEllipse()
        self.assertFalse(badErrorEllipse.isValid())


if __name__ == '__main__':
    unittest.main()        
