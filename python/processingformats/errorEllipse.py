#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# package imports
import processingformats.errorEllipseAxis

# stdlib imports
import json


class ErrorEllipse:
    """
    A conversion class usd to create, parse, and validate error ellipse data
    """

    # JSON Keys
    E0_KEY = "E0"  # Required
    E1_KEY = "E1"  # Required
    E2_KEY = "E2"  # Required
    MAXIMUM_HORIZONTAL_KEY = "MaximumHorizontalProjection"  # Required
    MAXIMUM_VERTICAL_KEY = "MaximumVerticalProjection"  # Required
    EQUIVALENT_HORIZONTAL_KEY = "EquivalentHorizontalRadius"  # Required

    def __init__(
        self,
        newE0=None,
        newE1=None,
        newE2=None,
        newMaximumHorizontalProjection=None,
        newMaximumVerticalProjection=None,
        newEquivalentHorizontalRadius=None,
    ):
        """Initializing the object. Constructs an empty object if all arguments are None.

        newE0: an ErrorEllipseAxis containing the first semi-axis of the error ellipsoid
        newE1: an ErrorEllipseAxis containing the second semi-axis of the error ellipsoid
        newE2: an ErrorEllipseAxis containing the third semi-axis of error ellipsoid
        newMaximumHorizontalProjection: a double containing the horizontal projection
                of the error ellipsoid in kilometers
        newMaximumVerticalProjection: a double containing the vertical projection
                of the error ellipsoid in kilometers
        newEquivalentHorizontalRadius: a double containing the equivalent radius
                of the horizontal error ellipsoid in kilometers
        """

        # Required Keys
        if newE0 is not None:
            self.e0 = newE0
        else:
            self.e0 = processingformats.errorEllipseAxis.ErrorEllipseAxis()

        if newE1 is not None:
            self.e1 = newE1
        else:
            self.e1 = processingformats.errorEllipseAxis.ErrorEllipseAxis()

        if newE2 is not None:
            self.e2 = newE2
        else:
            self.e2 = processingformats.errorEllipseAxis.ErrorEllipseAxis()

        if newMaximumHorizontalProjection is not None:
            self.maximumHorizontalProjection = newMaximumHorizontalProjection

        if newMaximumVerticalProjection is not None:
            self.maximumVerticalProjection = newMaximumVerticalProjection

        if newEquivalentHorizontalRadius is not None:
            self.equivalentHorizontalRadius = newEquivalentHorizontalRadius

    def fromJSONString(self, JSONString):
        """Populates object from a JSON string

        JSONString: a required string containing the JSON formatted text
        """
        JSONObject = json.loads(JSONString)
        self.fromDict(JSONObject)

    def fromDict(self, aDict):
        """Populates object from a dictionary

        aDict: required dictionary
        """

        try:
            self.e0.fromDict(aDict[self.E0_KEY])
            self.e1.fromDict(aDict[self.E1_KEY])
            self.e2.fromDict(aDict[self.E2_KEY])
            self.maximumHorizontalProjection = aDict[self.MAXIMUM_HORIZONTAL_KEY]
            self.maximumVerticalProjection = aDict[self.MAXIMUM_VERTICAL_KEY]
            self.equivalentHorizontalRadius = aDict[self.EQUIVALENT_HORIZONTAL_KEY]
        except (ValueError, KeyError, TypeError) as e:
            print("Dictionary format error, missing required keys: %s" % e)

    def toJSONString(self):
        """Converts object to JSON formatted string

        Returns: the JSON fromatted message as a string
        """
        JSONObject = self.toDict()

        return json.dumps(JSONObject, ensure_ascii=False)

    def toDict(self):
        """Converts object to a dictionary

        Returns: the dictionary
        """

        aDict = {}

        # Required Keys
        try:
            aDict[self.E0_KEY] = self.e0.toDict()
            aDict[self.E1_KEY] = self.e1.toDict()
            aDict[self.E2_KEY] = self.e2.toDict()
            aDict[self.MAXIMUM_HORIZONTAL_KEY] = self.maximumHorizontalProjection
            aDict[self.MAXIMUM_VERTICAL_KEY] = self.maximumVerticalProjection
            aDict[self.EQUIVALENT_HORIZONTAL_KEY] = self.equivalentHorizontalRadius

        except (NameError, AttributeError) as e:
            print("Missing required data error: %s" % e)

        return aDict

    def isValid(self):
        """Checks to see if object is valid

        Returns: True if object is valid, False otherwise
        """
        errorList = self.getErrors()

        return not errorList

    def getErrors(self):
        """Gets a list of object validation errors

        Returns: a list of strings containing the validation error messages
        """
        errorList = []

        # E0
        try:
            if not self.e0.isValid():
                errorList.append("Invalid first error axis in ErrorEllipse Class")
        except (NameError, AttributeError):
            errorList.append("No first error axis in ErrorEllipse Class.")

        # E1
        try:
            if not self.e1.isValid():
                errorList.append("Invalid second error axis in ErrorEllipse Class")
        except (NameError, AttributeError):
            errorList.append("No second error axis in ErrorEllipse Class.")

        # E2
        try:
            if not self.e2.isValid():
                errorList.append("Invalid third error axis in ErrorEllipse Class")
        except (NameError, AttributeError):
            errorList.append("No third error axis in ErrorEllipse Class.")

        # maximumHorizontalProjection
        try:
            self.maximumHorizontalProjection
        except (NameError, AttributeError):
            errorList.append("No MaximumHorizontalProjection in ErrorEllipse Class.")

        # maximumVerticalProjection
        try:
            self.maximumVerticalProjection
        except (NameError, AttributeError):
            errorList.append("No MaximumVerticalProjection in ErrorEllipse Class")

        # equivalentHorizontalRadius
        try:
            self.equivalentHorizontalRadius
        except (NameError, AttributeError):
            errorList.append("No EquivalentHorizontalRadius in ErrorEllipse class")

        return errorList

    def isEmpty(self):
        """Checks to see if object is empty

        Returns: True if the object has no attributes, False otherwise
        """

        if hasattr(self, "e0"):
            return False
        else:
            if not self.e0.isEmpty():
                return False

        if hasattr(self, "e1"):
            return False
        else:
            if not self.e2.isEmpty():
                return False

        if hasattr(self, "e2"):
            return False
        else:
            if not self.e2.isEmpty():
                return False

        if hasattr(self, "maximumHorizontalProjection"):
            return False

        if hasattr(self, "maximumVerticalProjection"):
            return False

        if hasattr(self, "equivalentHorizontalRadius"):
            return False

        return True
