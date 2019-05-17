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
        