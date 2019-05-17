#!/usr/bin/env python3
# -*- coding: utf-8 -*-

#stdlib import
import json

class ErrorEllipse:
    """
    A conversion class usd to cretae, parse, and validate error ellipse data
    """
    
    #JSON Keys
    E0_KEY = "E0"
    E1_KEY = "E1"
    E2_KEY = "E2"
    ERROR_KEY = "Error"
    AZIMUTH_KEY = "Azimuth"
    DIP_KEY = "Dip"
    MAXIMUM_HORIZONTAL_KEY = "MaximumHorizontalProjection"
    MAXIMUM_VERTICAL_KEY = "MaximumVerticalProjection"
    EQUIVALENT_HORIZONTAL_KEY = "EquivalentHorizontalRadius"
    
    #initialize the object
    def __init__ (self, newE0Error = None, newE0Azimuth = None, newE0Dip = None, 
                  newE1Error = None, newE1Azimuth = None, newE1Dip = None, 
                  newE2Error = None, newE2Azimuth = None, newE2Dip = None, 
                  newMaximumHorizontalProjection = None, 
                  newMaximumVerticalProjection = None, 
                  newEquivalentHorizontalRadius = None):
        
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
            
    #Populates object from JSON string
    def fromJSONString (self, JSONString):
        JSONObject = json.loads(JSONString)
        self.fromDict(JSONObject)
        
    #Populates object from a dictionary
    def fromDict (self, aDict):
        return None
        
    #Converts the object to a JSON formatted string
    def toJSONString(self):
        JSONObject = self.toDict()
        
        return json.dumps(JSONObject, ensure_ascii=False)
    
    #Converts the object to a dictionary
    def toDict(self):
        
        aDict = {}
        
        #Required Keys
    
    #Check to see if object is valid
    def isValid (self):
        errorList = self.getErrors()
        
        return not errorList
    
    #Gets list of object validation errors
    def getErrors (self):
        errorList = []
        
        #E0 Error
        
        
        #E0 Azimuth
        
        #E0 Dip
        
        #E1 Error
        
        #E1 Azimuth
        
        #E1 Dip
        
        #E2 Error
        
        #E2 Azimuth
        
        #E2 Dip
        
        #MaximumHorizontalProjection
        
        #MaximumVerticalProjection
        
        #EquivalentHorizontalRadius\
        
        return errorList
    
    #checks to see if object is empty
    def isEmpty(self):
        
        if hasattr(self, ''):
            return False
        
        return True
