#!/usr/bin/env python3
# -*- coding: utf-8 -*-

#stdlib import
import json

class ErrorEllipse:
    """
    A conversion class usd to create, parse, and validate error ellipse data
    """
    
    #JSON Keys
    E0_KEY = "E0" #Required
    E1_KEY = "E1" #Required
    E2_KEY = "E2" #Required
    ERROR_KEY = "Error" #Required
    AZIMUTH_KEY = "Azimuth" #Required
    DIP_KEY = "Dip" #Required
    MAXIMUM_HORIZONTAL_KEY = "MaximumHorizontalProjection" #Required
    MAXIMUM_VERTICAL_KEY = "MaximumVerticalProjection" #Required
    EQUIVALENT_HORIZONTAL_KEY = "EquivalentHorizontalRadius" #Required

    def __init__ (self, newE0Error = None, newE0Azimuth = None, newE0Dip = None, 
                  newE1Error = None, newE1Azimuth = None, newE1Dip = None, 
                  newE2Error = None, newE2Azimuth = None, newE2Dip = None, 
                  newMaximumHorizontalProjection = None, 
                  newMaximumVerticalProjection = None, 
                  newEquivalentHorizontalRadius = None):
    ''' Initializing the object. Constructs an empty object if all arguments are None.
    
        newE0Error: a double containing the length of the first axis of the error
                ellipsoid in kilometers
        newE0Azimuth: a double containing the azimuth of the first axis of the
                error ellipsoid in degrees
        newE0Dip: a double containing the dip of the first axis of error ellipsoid
                in degrees
        newE1Error: a double containing the length of the second axis of the error
                error ellipsoid in kilometers
        newE1Azimuth: a double containing the azimuth of the second axis of error
                error ellipsoid in degrees
        newE1Dip: a double containing the dip of the second axis of error ellispoid
                in degrees
        newE2Error: a double containing the length of the third axis of error
                ellipsoid in kilometers
        newE2Azimuth: a double containing the azimth of the third axis of error
                ellipsoid in degrees
        newE2Dip: a double containing the dip of the third axis of error ellipsoid
                in degrees
        newMaximumHorizontalProjection: a double containing the horizontal projection
                of the error ellipsoid in kilometers
        newMaximumVerticalProjection: a double containing the vertical projection
                of the error ellipsoid in kilometers
        newEquivalentHorizontalRadius: a double containing the equivalent radius
                of the horizontal error ellipsoid in kilometers
    '''
        
        if newE0Error is not None:
            self.E0Error = newE0Error
            
        if newE0Azimuth is not None:
            self.E0Azimuth = newE0Azimuth
            
        if newE0Dip is not None:
            self.E0Dip = newE0Dip
            
        if newE1Error is not None:
            self.E1Error = newE1Error
        
        if newE1Azimuth is not None:
            self.E1Azimuth = newE1Azimuth
            
        if newE1Dip is not None:
            self.E1Dip = newE1Dip
            
        if newE2Error is not None:
            self.E2Error = newE2Error
        
        if newE2Azimuth is not None:
            self.E2Azimuth = newE2Azimuth
            
        if newE2Dip is not None:
            self.E2Dip = newE2Dip

    def fromJSONString (self, JSONString):
    ''' Populates object from a JSON string
    
        JSONString: a requred string containing the JSON formatted text
    '''
        JSONObject = json.loads(JSONString)
        self.fromDict(JSONObject)

    def fromDict (self, aDict):
    ''' Populates object from a dictionary
    
        aDict: required dictionary
    '''
        
        try:
            self.E0Error = aDict[self.E0ERROR_KEY]
            self.E0Azimuth = aDict[self.E0AZIMUTH_KEY]
            self.E0Dip = aDict[self.E0DIP_KEY]
            self.E1Error = aDict[self.]

    def toJSONString(self):
    ''' Converts object to JSON formatted string
        
        Returns: the JSON fromatted message as a string
    '''
        JSONObject = self.toDict()
        
        return json.dumps(JSONObject, ensure_ascii=False)

    def toDict(self):
    ''' Converts object to a dictionary
    
        Returns: the dictionary
    '''
        
        aDict = {}
        
        #Required Keys

    def isValid (self):
    ''' Checks to see if object is valid
        
        Returns: True if object is valid, False otherwise
    '''
        errorList = self.getErrors()
        
        return not errorList

    def getErrors (self):
    ''' Gets a list of object validation errors
        
        Returns: a list of strings containing the validation error messages
    '''
        errorList = []
        
        #E0 Error
        try:
            self.E0Error 
        except(NameError, AttributeError):
            errorList.append('No first axis error in ErrorEllipse Class')
        
        #E0 Azimuth
        try:
            self.E0Azimuth
        except(NameError, AttributeError):
            errorList.append('No first axis azimuth in ErrorEllipse Class')
        
        #E0 Dip
        try:
            self.E0Dip
        except(NameError, AttributeError):
            errorList.append('No first axis dip in ErrorEllipse Class')
        
        #E1 Error
        try:
            self.E1Error
        except(NameError, AttributeError):
            errorList.append('No second axis error in ErrorEllipse class')
        
        #E1 Azimuth
        try:
            self.E1Azimuth
        except(NameError, AttributeError):
            errorList.append('No second axis azimuth in ErrorEllipse class')
        
        #E1 Dip
        try:
            self.E1Dip
        except(NameError, AttributeError):
            errorList.append('No second axis dip in ErrorEllipse class')
        
        #E2 Error
        try:
            self.E2Error
        except(NameError, AttributeError):
            errorList.append('No third axis error in ErrorEllipse class.')
        
        #E2 Azimuth
        try:
            self.E2Azimuth
        except(NameError, AttributeError):
            errorList.append('No third axis azimuth in ErrorEllipse class')
            
        #E2 Dip
        try:
            self.E2Dip
        except(NameError, AttributeError):
            errorList.append('No third axis dip in ErrorEllipse class')
        
        #MaximumHorizontalProjection
        try:
            self.maximumHorizontalProjection
        except(NameError, AttributeError):
            errorList.append('No MaximumHorizontalProjection in ErrorEllipse Class.')
        
        #MaximumVerticalProjection
        try:
            self.maximumVerticalProjection
        except(NameError, AttributeError):
            errorList.append('No MaximumVerticalProjection in ErrorEllipse Class')
        
        #EquivalentHorizontalRadius
        try:
            self.equivalentHorizontalRadius
        except(NameError, AttributeError):
            errorList.append('No EquivalentHorizontalRadius in ErrorEllipse class')
        
        return errorList

    def isEmpty(self):
    ''' Checks to see if object is empty
    
        Returns: True if the object has no attributes, False otherwise
    '''
        
        if hasattr(self, 'E0Error'):
            return False
        
        if hasattr(self, 'E0Azimuth'):
            return False
        
        if hasattr(self, 'E0Dip'):
            return False
        
        if hasattr(self, 'E1Error'):
            return False
        
        if hasattr(self, 'E1Azimuth'):
            return False
        
        if hasattr(self, 'E1Dip'):
            return False
        
        if hasattr(self, 'E2Error'):
            return False
        
        if hasattr(self, 'E2Azimuth'):
            return False
        
        if hasattr(self, 'E2Dip'):
            return False
        
        if hasattr(self, 'maximumHorizontalProjection'):
            return False
        
        if hasattr(self, 'maximumVerticalProjection'):
            return False
        
        if hasattr(self, 'equivalentHorizontalRadius'):
            return False
        
        return True
