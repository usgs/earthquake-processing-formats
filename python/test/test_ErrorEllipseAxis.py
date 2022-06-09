#!/usr/bin/env python3
# -*- coding: utf-8 -*-


# package imports
import processingformats.errorEllipseAxis

# stdlib imports
import unittest


class TestErrorEllipseAxis(unittest.TestCase):
    ERROR = 40.3344
    AZIMUTH = -121.44
    DIP = 32.44

    JSONSTRING = '{"Error": 40.3344, "Azimuth": -121.44, "Dip": 32.44}'
    DICT = {"Error": 40.3344, "Azimuth": -121.44, "Dip": 32.44}

    def test_init(self):
        errorEllipseAxis = processingformats.errorEllipseAxis.ErrorEllipseAxis()

        self.assertFalse(hasattr(errorEllipseAxis, "error"))
        self.assertFalse(hasattr(errorEllipseAxis, "azimuth"))
        self.assertFalse(hasattr(errorEllipseAxis, "dip"))

        errorEllipseAxis = processingformats.errorEllipseAxis.ErrorEllipseAxis(
            self.ERROR, self.AZIMUTH, self.DIP
        )

        self.assertTrue(hasattr(errorEllipseAxis, "error"))
        self.assertTrue(hasattr(errorEllipseAxis, "azimuth"))
        self.assertTrue(hasattr(errorEllipseAxis, "dip"))

        self.assertEqual(errorEllipseAxis.error, self.ERROR)
        self.assertEqual(errorEllipseAxis.azimuth, self.AZIMUTH)
        self.assertEqual(errorEllipseAxis.dip, self.DIP)

    def test_toJSON(self):
        errorEllipseAxis = processingformats.errorEllipseAxis.ErrorEllipseAxis(
            self.ERROR, self.AZIMUTH, self.DIP
        )
        self.assertEqual(errorEllipseAxis.toJSONString(), self.JSONSTRING)

    def test_fromJSON(self):
        errorEllipseAxis = processingformats.errorEllipseAxis.ErrorEllipseAxis()
        errorEllipseAxis.fromJSONString(self.JSONSTRING)

        self.assertEqual(errorEllipseAxis.error, self.ERROR)
        self.assertEqual(errorEllipseAxis.azimuth, self.AZIMUTH)
        self.assertEqual(errorEllipseAxis.dip, self.DIP)

    def test_toDict(self):
        errorEllipseAxis = processingformats.errorEllipseAxis.ErrorEllipseAxis(
            self.ERROR, self.AZIMUTH, self.DIP
        )
        self.assertEqual(errorEllipseAxis.toDict(), self.DICT)

    def test_isValid(self):
        errorEllipseAxis = processingformats.errorEllipseAxis.ErrorEllipseAxis(
            self.ERROR, self.AZIMUTH, self.DIP
        )
        self.assertTrue(errorEllipseAxis.isValid())

        badErrorEllipseAxis = processingformats.errorEllipseAxis.ErrorEllipseAxis()
        self.assertFalse(badErrorEllipseAxis.isValid())

    def test_isEmpty(self):
        errorEllipseAxis = processingformats.errorEllipseAxis.ErrorEllipseAxis(
            self.ERROR, self.AZIMUTH, self.DIP
        )
        self.assertFalse(errorEllipseAxis.isEmpty())

        emptyErrorEllipseAxis = processingformats.errorEllipseAxis.ErrorEllipseAxis()
        self.assertTrue(emptyErrorEllipseAxis.isEmpty())


if __name__ == "__main__":
    unittest.main()
